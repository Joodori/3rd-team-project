<template>
  <!-- position-relative와 vh-100 클래스로 컨테이너를 화면 전체에 맞게 설정 -->
  <div class="position-absolute top-0 bottom-0 start-0 end-0">
    <!-- 지도가 표시될 DOM 요소 -->
    <div id="map" class="w-100 h-100"></div>

    <!-- 시간 선택 버튼 UI: Metronic/Bootstrap 스타일 적용 -->
    <!-- position-absolute, p-5, z-index-1 클래스로 위치와 레이어 순서 지정 -->
    <div class="position-absolute top-0 start-0 p-5 z-index-1">
      <!-- .btn-group과 .shadow-sm으로 버튼들을 그룹화하고 그림자 효과 추가 -->
      <div class="btn-group shadow-sm">
        <button
            type="button"
            class="btn"
            :class="selectedMinutes === 10 ? 'btn-primary' : 'btn-light'"
            @click="updateTimeRange(10)">
          최근 10분
        </button>
        <button
            type="button"
            class="btn"
            :class="selectedMinutes === 30 ? 'btn-primary' : 'btn-light'"
            @click="updateTimeRange(30)">
          최근 30분
        </button>
        <button
            type="button"
            class="btn"
            :class="selectedMinutes === 60 ? 'btn-primary' : 'btn-light'"
            @click="updateTimeRange(60)">
          최근 1시간
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
// Vue의 ref(반응형 변수), onMounted(생명주기 훅)를 가져옵니다.
import { ref, onMounted } from 'vue';
import axios from 'axios'; // HTTP 요청을 위한 axios 라이브러리

// --- 지도 및 히트맵 상태 변수 ---
let map = null;
let heatmapLayer = null;

// --- 설정 변수 ---
// API 기본 URL (파라미터 제외)
const BASE_API_URL = 'http://localhost/api/heatmap/points';
// 지도 중심 좌표
const CENTER_COORD = { lat: 37.494665, lng: 126.887733 };
// 데이터 자동 갱신 주기 (밀리초 단위, 예: 60000ms = 1분)
const UPDATE_INTERVAL = 60000;

// --- 반응형 상태 (Reactivity State) ---
// 사용자가 선택한 시간(분)을 저장하는 반응형 변수. 기본값 10분.
const selectedMinutes = ref(10);

/**
 * API 서버에서 좌표 데이터를 가져와 히트맵을 업데이트하는 함수.
 */
const fetchDataAndUpdateHeatmap = async () => {
  try {
    // selectedMinutes 값을 사용하여 동적으로 API URL을 생성합니다.
    const apiUrl = `${BASE_API_URL}?minutes=${selectedMinutes.value}`;

    console.log(`데이터를 가져오는 중... (URL: ${apiUrl})`);
    const response = await axios.get(apiUrl);
    const points = response.data;

    const googleMapsPoints = points
        .map(point => {
          // 서버에서 오는 키 이름이 latitude/longitude 또는 lat/lng 일 수 있으므로 모두 대응
          const lat = point.latitude ?? point.lat;
          const lng = point.longitude ?? point.lng;

          // 위도와 경도 값이 유효한 숫자인지 확인
          if (typeof lat === 'number' && typeof lng === 'number' && !isNaN(lat) && !isNaN(lng)) {
            return new google.maps.LatLng(lat, lng);
          }
          // 유효하지 않은 데이터는 null 반환
          return null;
        })
        // 배열에서 null 값을 제거하여 유효한 데이터만 남김
        .filter(p => p !== null);


    // 히트맵 레이어에 새로운 데이터 설정
    heatmapLayer.setData(googleMapsPoints);
    console.log(`${googleMapsPoints.length}개의 유효한 좌표로 히트맵이 업데이트되었습니다.`);

  } catch (error) {
    console.error('데이터를 가져오는 중 오류가 발생했습니다:', error);
    heatmapLayer.setData([]);
  }
};

/**
 * 시간 선택 버튼을 클릭했을 때 호출되는 함수.
 * @param {number} minutes - 새로 선택된 시간(분)
 */
const updateTimeRange = (minutes) => {
  console.log(`시간 범위를 ${minutes}분으로 변경합니다.`);
  selectedMinutes.value = minutes;
  // 시간 범위를 변경했으니 즉시 데이터를 다시 불러옵니다.
  fetchDataAndUpdateHeatmap();
};


/**
 * Google Maps 스크립트를 동적으로 로드하고 지도를 초기화하는 함수.
 */
const initMap = () => {
  map = new google.maps.Map(document.getElementById('map'), {
    center: CENTER_COORD,
    zoom: 18, // ★ 기본 확대 레벨을 조금 낮춰서 넓은 영역이 보이도록 수정
  });

  heatmapLayer = new google.maps.visualization.HeatmapLayer({
    data: [],
    map: map,
    radius: 20,
    opacity: 0.7
  });

  // 최초 데이터 로드 및 주기적 갱신 설정
  fetchDataAndUpdateHeatmap();
  setInterval(fetchDataAndUpdateHeatmap, UPDATE_INTERVAL);
};


// onMounted: Vue 컴포넌트가 화면에 준비되면 실행됩니다.
onMounted(() => {
  if (window.google && window.google.maps) {
    initMap();
  } else {
    // 스크립트가 로드되지 않았다면 동적으로 추가
    const script = document.createElement('script');
    script.src = `https://maps.googleapis.com/maps/api/js?key=AIzaSyAIX1c39RvGr95miO1ux6IRfxYDssqSNfU&libraries=visualization`;
    script.async = true;
    script.defer = true;
    document.head.appendChild(script);

    // 스크립트가 로드 완료되면 initMap 함수를 실행하도록 콜백 설정
    script.onload = initMap;
  }
});
</script>

<style scoped>
</style>

