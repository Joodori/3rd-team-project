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
// (개선 제안) 초기값을 설정하여 사용자 경험을 향상시킬 수 있습니다.
const now = new Date();
const tenMinutesAgo = new Date(now.getTime() - 10 * 60000);

// ISO 8601 형식에서 초와 밀리초를 제거하여 datetime-local input에 맞게 포맷합니다.
const formatForInput = (date) => date.toISOString().slice(0, 16);

const startTime = ref(formatForInput(tenMinutesAgo));
const endTime = ref(formatForInput(now));

/**
 * API 서버에서 좌표 데이터를 가져와 히트맵을 업데이트하는 함수.
 */
const fetchDataAndUpdateHeatmap = async () => {
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
 * Google 지도를 생성하고 초기화하는 함수.
 */
const initMap = () => {
  map = new google.maps.Map(document.getElementById('map'), {
    center: CENTER_COORD,
    zoom: 18,
  });

  heatmapLayer = new google.maps.visualization.HeatmapLayer({
    data: [],
    map: map,
    radius: 20,
    opacity: 0.7
  });

  // ✨ --- START: 수정된 부분 --- ✨
  // 폴리곤의 좌표를 먼저 정의합니다.
  // 이 좌표는 예시이며, 원하시는 지역의 좌표로 변경하시면 됩니다.
  const triangleCoords = [
    { lat: 37.495939, lng: 126.888231 },
    { lat: 37.495863, lng: 126.888080 },
    { lat: 37.495831, lng: 126.887776 },
    { lat: 37.494964, lng: 126.886074 },
    { lat: 37.493623, lng: 126.887278 },
    { lat: 37.493893, lng: 126.888692 },
    { lat: 37.494364, lng: 126.889432 },

  ];

  // 폴리곤을 생성합니다.
  const bermudaTriangle = new google.maps.Polygon({
    paths: triangleCoords, // 이제 이 변수는 정의되었습니다.
    strokeColor: "#FFFFFF",
    strokeOpacity: 0.8,
    strokeWeight: 2,
    fillColor: "#FFFFFF",
    fillOpacity: 1,
  });

  // 지도에 폴리곤을 추가합니다.
  bermudaTriangle.setMap(map);
  // ✨ --- END: 수정된 부분 --- ✨

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

