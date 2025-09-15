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
 * 2. 폴리곤의 좌표를 먼저 정의
 * 구로구청 인근 7점 좌표
 */
const parkBoundaryCoords  = [
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
  console.log(`fetchDataAndUpdateHeatmap::호출됨 (히트맵 표시)`);
  try {
    if (!startTime.value || !endTime.value) {
      console.log('시작 시간 또는 종료 시간이 설정되지 않았습니다.');
      return;
    }
    const apiUrl = `${BASE_API_URL}?startTime=${startTime.value}&endTime=${endTime.value}`;
    console.log(`데이터를 가져오는 중... (URL: ${apiUrl})`);
    const response = await axios.get(apiUrl);
    const points = response.data;
    const googleMapsPoints = points
        .map(point => {
          const lat = point.latitude ?? point.lat;
          const lng = point.longitude ?? point.lng;
          if (typeof lat === 'number' && typeof lng === 'number' && !isNaN(lat) && !isNaN(lng)) {
            return new google.maps.LatLng(lat, lng);
          }
          return null;
        })
        .filter(p => p !== null);

    heatmapLayer.setData(googleMapsPoints);
    console.log(`${googleMapsPoints.length}개의 유효한 좌표로 히트맵이 업데이트되었습니다.`);
  } catch (error) {
    console.error('데이터를 가져오는 중 오류가 발생했습니다:', error);
    heatmapLayer.setData([]);
  }
};

/**
 * 놀이기구 데이터를 받아 지도에 마커와 정보창을 생성하는 함수
 * @param {google.maps.Map} map - 마커를 추가할 지도 객체
 * @param {Array} ridesData - 마커를 생성할 데이터 배열
 */
function createRideMarkers(map, ridesData) {
  console.log(`createRideMarkers::호출됨 (놀이기구 위치 마커표시)`);

  // ✨ InfoWindow 객체를 반복문 밖에서 한 번만 생성
  const infowindow = new google.maps.InfoWindow();
  const markers = [];

  ridesData.forEach(ride => {
    const marker = new google.maps.Marker({
      position: ride.position,
      map: map,
      title: ride.name,
    });

    // ✨ 클릭했을 때만 정보창의 내용을 설정하고 열도록 변경
    marker.addListener("click", () => {
      infowindow.setContent(`<h3>${ride.name}</h3>`);
      infowindow.open(map, marker);
    });

    markers.push(marker);
  });
  return markers;
}

/**
 * 좌표 데이터를 받아 지도에 경계선 폴리곤을 생성하고 반환하는 함수
 * @param {google.maps.Map} map - 폴리곤을 추가할 지도 객체
 * @param {Array} coords - 폴리곤을 구성할 좌표 배열
 * @returns {google.maps.Polygon} 생성된 폴리곤 객체
 */
function createParkBoundaryPolygon(map, coords) {
  console.log(`createParkBoundaryPolygon:: 폴리곤 생성함수 호출됨`)
  const parkBoundary = new google.maps.Polygon({
    paths: coords,
    strokeColor: "#4A00E0",
    strokeOpacity: 1,
    strokeWeight: 5,
    fillOpacity: 0.6,
    fillColor: "#FFFFFF",
    zIndex: -1
  });

  parkBoundary.setMap(map);

  // 나중에 제어할 수 있도록 생성된 폴리곤 객체를 반환합니다.
  return parkBoundary;
}


/**
 * Google 지도를 생성하고 초기화하는 함수.
 */
const initMap = () => {
  // 1. 지도 생성
  map = new google.maps.Map(document.getElementById('map'), {
    center: CENTER_COORD,
    zoom: 18,
  });

  // 2. 마커 생성 함수 호출
  createRideMarkers(map, ridesData);

  // 3. 폴리곤 생성 함수 호출
  createParkBoundaryPolygon(map, parkBoundaryCoords);

  // 4. 히트맵 뿌리기 함수 호출
  heatmapLayer = new google.maps.visualization.HeatmapLayer({
    data: [],
    map: map,
    radius: 20,
    opacity: 0.7
  });
  fetchDataAndUpdateHeatmap();
  setInterval(fetchDataAndUpdateHeatmap, UPDATE_INTERVAL);
};


onMounted(() => {
  // 💡 (개선 제안) API 키를 환경 변수로 관리하는 것이 안전합니다.
  const GOOGLE_MAPS_API_KEY = "AIzaSyAIX1c39RvGr95miO1ux6IRfxYDssqSNfU"; // 여기에 실제 API 키를 넣거나 환경 변수에서 가져오세요.

  if (window.google && window.google.maps) {
    initMap();
  } else {
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

