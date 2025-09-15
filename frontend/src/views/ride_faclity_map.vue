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
// Vue의 ref(반응형 변수), onMounted(생명주기 훅)를 가져옵니다.
// ref: 값이 바뀌면 화면이 자동으로 업데이트되는 변수를 만듭니다.
// onMounted: 컴포넌트가 화면에 완전히 그려진 후 실행할 코드를 정의합니다.
import { ref, onMounted } from 'vue';
// HTTP 요청을 보내고 응답을 받기 위한 axios 라이브러리를 가져옵니다. (API 호출용)
import axios from 'axios';

// --- 지도 및 히트맵 상태 변수 ---
// 지도 객체를 저장할 변수입니다. 처음에는 비어 있습니다(null).
let map = null;
// 히트맵 레이어 객체를 저장할 변수입니다.
let heatmapLayer = null;

// --- 설정 변수 ---
// 데이터를 요청할 API의 기본 URL 주소입니다.
const BASE_API_URL = 'http://localhost/heatmap/points';

// 지도의 기본 중심 좌표입니다. (예: 구로디지털단지 근처)
const CENTER_COORD = { lat: 37.494665, lng: 126.887733 };

// 데이터를 자동으로 다시 불러올 시간 간격입니다. (60000 밀리초 = 1분)
const UPDATE_INTERVAL = 60000;

// --- 반응형 상태 (Reactivity State) ---
// 사용자가 선택한 시간(분)을 저장하는 반응형 변수입니다. 기본값은 10분입니다.
// 이 값이 바뀌면 <template> 부분의 버튼 스타일이 자동으로 변경됩니다.
const startTime = ref();
const endTime = ref();

/**
 * API 서버에서 좌표 데이터를 가져와 히트맵을 업데이트하는 함수.
 */
// async 키워드는 이 함수가 비동기 작업을 포함하고 있음을 나타냅니다.
const fetchDataAndUpdateHeatmap = async () => {
  // try-catch 문으로 API 요청 중 발생할 수 있는 오류를 처리합니다.
  try {
    // startTime와 endTime 현재 값을 이용해 전체 API URL을 동적으로 만듭니다. (예: .../points?startTime=?endTime=)
    const apiUrl = `${BASE_API_URL}?startTime=${startTime.value}&endTime=${endTime.value}`;

    // 개발자 도구 콘솔에 현재 진행 상황을 로그로 남깁니다.
    console.log(`데이터를 가져오는 중... (URL: ${apiUrl})`);
    // axios를 사용해 해당 URL에 GET 요청을 보내고, 응답이 올 때까지 기다립니다(await).
    const response = await axios.get(apiUrl);
    // 응답받은 데이터(JSON 배열)를 points 변수에 저장합니다.
    const points = response.data;

    // 서버에서 받은 데이터를 Google 지도 히트맵이 사용할 수 있는 형식으로 변환합니다.
    const googleMapsPoints = points
        // .map()은 배열의 각 항목을 순회하며 새로운 배열을 만듭니다.
        .map(point => {
          // 서버 데이터의 키 이름이 'latitude' 또는 'lat'일 수 있으므로, '??' 연산자로 둘 다 처리합니다.
          const lat = point.latitude ?? point.lat;
          // 경도도 'longitude' 또는 'lng' 키를 모두 처리합니다.
          const lng = point.longitude ?? point.lng;

          // 위도(lat)와 경도(lng)가 유효한 숫자인지 검사합니다.
          if (typeof lat === 'number' && typeof lng === 'number' && !isNaN(lat) && !isNaN(lng)) {
            // 유효하면 Google 지도의 LatLng 객체를 생성하여 반환합니다.
            return new google.maps.LatLng(lat, lng);
          }
          // 데이터가 유효하지 않으면 null을 반환합니다.
          return null;
        })
        // .filter()는 배열에서 특정 조건에 맞는 항목만 남깁니다.
        // 위에서 null로 반환된 유효하지 않은 데이터들을 배열에서 제거합니다.
        .filter(p => p !== null);


    // 히트맵 레이어에 새로 가공된 좌표 데이터를 설정하여 화면에 표시합니다.
    heatmapLayer.setData(googleMapsPoints);
    // 처리 완료 후 콘솔에 로그를 남깁니다.
    console.log(`${googleMapsPoints.length}개의 유효한 좌표로 히트맵이 업데이트되었습니다.`);

    // try 블록에서 오류가 발생하면 이 catch 블록이 실행됩니다.
  } catch (error) {
    // 콘솔에 에러 메시지를 출력합니다.
    console.error('데이터를 가져오는 중 오류가 발생했습니다:', error);
    // 에러 발생 시 히트맵 데이터를 비워서 지도에서 사라지게 합니다.
    heatmapLayer.setData([]);
  }
};


/**
 * Google 지도를 생성하고 초기화하는 함수.
 */
const initMap = () => {
  // `id="map"`인 div 요소를 찾아 Google 지도를 생성하고 map 변수에 저장합니다.
  map = new google.maps.Map(document.getElementById('map'), {
    center: CENTER_COORD, // 지도 중심 좌표 설정
    zoom: 18,             // 지도 확대 레벨 설정
  });

  // Google 지도의 시각화 라이브러리를 사용해 히트맵 레이어를 생성합니다.
  heatmapLayer = new google.maps.visualization.HeatmapLayer({
    data: [],       // 초기 데이터는 비어있는 배열
    map: map,       // 이 히트맵을 위에서 생성한 지도(map)에 연결
    radius: 20,     // 각 데이터 포인트의 영향 반경 (픽셀 단위)
    opacity: 0.7    // 히트맵의 불투명도 (0.0 ~ 1.0)
  });

  // 지도가 처음 로딩될 때 데이터를 한 번 가져옵니다.
  fetchDataAndUpdateHeatmap();
  // UPDATE_INTERVAL(1분)마다 주기적으로 fetchDataAndUpdateHeatmap 함수를 실행하여 데이터를 자동 갱신합니다.
  setInterval(fetchDataAndUpdateHeatmap, UPDATE_INTERVAL);
};


// onMounted: Vue 컴포넌트가 화면에 마운트(준비)된 후에 이 코드가 실행됩니다.
onMounted(() => {
  // 만약 Google 지도 스크립트가 이미 페이지에 로드되어 있다면,
  if (window.google && window.google.maps) {
    // 바로 initMap 함수를 호출하여 지도를 초기화합니다.
    initMap();
  } else {
    // 스크립트가 없다면, 동적으로 <script> 태그를 생성합니다.
    const script = document.createElement('script');
    // 생성된 스크립트 태그의 소스(src)를 Google Maps API 주소로 설정합니다.
    // key 부분에는 발급받은 API 키가 들어가며, 'visualization' 라이브러리를 함께 로드합니다.
    script.src = `https://maps.googleapis.com/maps/api/js?key=AIzaSyAIX1c39RvGr95miO1ux6IRfxYDssqSNfU&libraries=visualization`;
    // async, defer 속성으로 비동기 로드를 설정하여 페이지 렌더링을 방해하지 않도록 합니다.
    script.async = true;
    script.defer = true;
    // 생성된 스크립트 태그를 HTML의 <head> 부분에 추가하여 로드를 시작합니다.
    document.head.appendChild(script);

    // 스크립트 로드가 완료되면 initMap 함수를 실행하도록 콜백을 설정합니다.
    // 이를 통해 API가 준비되기 전에 지도를 초기화하려는 시도를 방지합니다.
    script.onload = initMap;
  }
});
</script>

<style scoped>
</style>

