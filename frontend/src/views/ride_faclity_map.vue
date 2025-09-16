<template>
  <!-- position-relative와 vh-100 클래스로 컨테이너를 화면 전체에 맞게 설정 -->
  <div class="position-absolute top-0 bottom-0 start-0 end-0">
    <!-- 지도가 표시될 DOM 요소 -->
    <div id="map" class="w-100 h-100"></div>

    <div class="position-absolute top-0 end-0 p-5 z-index-1 bg-white rounded shadow-sm">
      <!-- 시작 시간 -->
      <div class="mb-3">
        <label for="start-time" class="form-label"><b>시작 시간</b></label>
        <input type="datetime-local" class="form-control" v-model="startTime">
      </div>
      <!-- 종료 시간 -->
      <div class="mb-3">
        <label for="end-time" class="form-label"><b>종료 시간</b></label>
        <input type="datetime-local" class="form-control" v-model="endTime">
      </div>

      <!-- 히트맵 조회 버튼 -->
      <button type="button" class="btn btn-success w-100" @click="fetchDataAndUpdateHeatmap">
        히트맵 조회
      </button>
    </div>

  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

// --- 지도 및 히트맵 상태 변수 ---
let map = null;
let heatmapLayer = null;

// --- 설정 변수 ---
const BASE_API_URL = 'http://localhost/heatmap/points';
const CENTER_COORD = { lat: 37.494665, lng: 126.887733 };
const UPDATE_INTERVAL = 60000;

// --- 반응형 상태 (Reactivity State) ---
const now = new Date();
const tenMinutesAgo = new Date(now.getTime() - 10 * 60000);
const formatForInput = (date) => date.toISOString().slice(0, 16);
const startTime = ref(formatForInput(tenMinutesAgo));
const endTime = ref(formatForInput(now));

/**
 * 1. 놀이기구 데이터 정의
 */
const ridesData = [
  { name: '롤러코스터', position: { lat: 37.495421, lng: 126.887801 } },
  { name: '회전목마', position: { lat: 37.494664, lng: 126.888772 } },
  { name: '먹거리존', position: { lat: 37.494294, lng: 126.887646 } }
];

/**
 * 2. 폴리곤 좌표 정의
 */
const parkBoundaryCoords = [
  { lat: 37.495939, lng: 126.888231 },
  { lat: 37.495863, lng: 126.888080 },
  { lat: 37.495831, lng: 126.887776 },
  { lat: 37.494964, lng: 126.886074 },
  { lat: 37.493623, lng: 126.887278 },
  { lat: 37.493893, lng: 126.888692 },
  { lat: 37.494364, lng: 126.889432 },
];

/**
 * API 서버에서 좌표 데이터를 가져와 히트맵을 업데이트하는 함수.
 */
const fetchDataAndUpdateHeatmap = async () => {
  console.log('fetchDataAndUpdateHeatmap::호출됨 (API에서 데이터 가져와 히트맵 업데이트)');
  try {
    if (!startTime.value || !endTime.value) {
      console.log('시작 시간 또는 종료 시간이 설정되지 않았습니다.');
      return;
    }
    const apiUrl = `${BASE_API_URL}?startTime=${startTime.value}&endTime=${endTime.value}`;
    const response = await axios.get(apiUrl);
    const points = response.data.map(point => new google.maps.LatLng(point.latitude ?? point.lat, point.longitude ?? point.lng))
        .filter(p => p !== null && !isNaN(p.lat()) && !isNaN(p.lng()));

    heatmapLayer.setData(points);
    console.log(`fetchDataAndUpdateHeatmap::완료 (${points.length}개의 좌표 업데이트)`);
  } catch (error) {
    console.error('데이터를 가져오는 중 오류가 발생했습니다:', error);
    heatmapLayer.setData([]);
  }
};

/**
 * 놀이기구 데이터를 받아 지도에 마커와 정보창을 생성하는 함수
 */
function createRideMarkers(map, ridesData) {
  console.log('createRideMarkers::호출됨 (놀이기구 위치 마커 생성)');

  const markers = [];

  ridesData.forEach(ride => {
    const marker = new google.maps.Marker({
      position: ride.position,
      map: map,
      title: ride.name,
    });

    // 각 마커마다 새로운 정보창을 생성합니다.
    const infowindow = new google.maps.InfoWindow({
      content: `<h3>${ride.name}</h3>`,
    });

    // 마커가 생성되자마자 정보창을 엽니다 (항상 열려있게 됨).
    infowindow.open(map, marker);

    // 마커를 클릭했을 때도 정보창을 다시 엽니다.
    marker.addListener("click", () => {
      infowindow.open(map, marker);
    });

    markers.push(marker);
  });
  // 생성된 마커 배열을 반환합니다.
  return markers;
}

/**
 * 경계선 폴리곤 생성 함수
 */
function createParkBoundaryPolygon(map, coords) {
  console.log('createParkBoundaryPolygon::호출됨 (경계 폴리곤 생성)');
  const parkBoundary = new google.maps.Polygon({
    paths: coords,
    strokeColor: "#4A00E0",
    strokeOpacity: 1,
    strokeWeight: 5,
    fillOpacity: 0.6, // 내부 투명도
    fillColor: "#FFFFFF",
    zIndex: -1
  });
  parkBoundary.setMap(map);
  return parkBoundary;
}

/**
 * Google 지도 생성 및 초기화 함수
 */
const initMap = () => {
  console.log('initMap::호출됨 (지도 초기화 시작)');

  // 1. 지도 로드
  map = new google.maps.Map(document.getElementById('map'), {
    center: CENTER_COORD,
    zoom: 18,
  });

  // 2. 그 위에 경계선 폴리곤과 마커 그리기
  createParkBoundaryPolygon(map, parkBoundaryCoords);
  createRideMarkers(map, ridesData);

  // 히트맵 레이어 생성 및 데이터 로드
  heatmapLayer = new google.maps.visualization.HeatmapLayer({
    data: [],
    map: map,
    radius: 20,
    opacity: 0.7
  });
  fetchDataAndUpdateHeatmap();
  setInterval(fetchDataAndUpdateHeatmap, UPDATE_INTERVAL);

  console.log('initMap::완료 (모든 요소 초기화 완료)');
};

onMounted(() => {
  console.log('onMounted::호출됨 (컴포넌트 마운트 완료, 지도 API 로드 시작)');

  const GOOGLE_MAPS_API_KEY = "AIzaSyAIX1c39RvGr95miO1ux6IRfxYDssqSNfU"; // 보안을 위해 환경 변수로 관리하는 것을 권장합니다.

  if (window.google && window.google.maps) {
    console.log('onMounted::지도 API가 이미 로드되어 있음');
    initMap();
  } else {
    console.log('onMounted::지도 API 스크립트 동적 로드');
    const script = document.createElement('script');
    script.src = `https://maps.googleapis.com/maps/api/js?key=${GOOGLE_MAPS_API_KEY}&libraries=visualization`;
    script.async = true;
    script.defer = true;
    document.head.appendChild(script);
    script.onload = initMap;
  }
});

</script>
<style scoped>
</style>

