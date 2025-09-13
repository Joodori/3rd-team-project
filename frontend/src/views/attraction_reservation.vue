<template>
  <div class="container d-flex flex-column w-100 h-100">
    <div class="container d-flex flex-column w-100 h-25">

    <label>날짜선택</label><!--날자선택-->
  <div class="calendar-container"><!--외부 install 외부 라이브러리 넣어야됨 -->
    <Calendar  
      view="weekly" 
      :attributes="attributes" 
      :initial-page="startPage" 
      locale="ko-KR"
      is-expanded 
    />
  </div><!--25퍼 세로로-->
  <div class="container d-flex flex-column w-100 h-25"> <!--라디오버든 1개만 선택-->
    <div class="card"> <!--카드로 감싸기-->
    <nav class="navbar navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand">놀이기구 검색</a>
    <form class="d-flex">
      <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success" type="submit">Search</button>
    </form>
  </div>
  <div class="container d-flex flex-column w-100 h-50">

  </div>
</nav>
  
  </div>
  

  <div class=" d-flex flex-column w-100 h-25">
    <div class="container">
    <label>인원수</label>
    <div class="card p-4 w-100 h-"><div class="counter-item"><!--띄어쓰기-->
        <button type="button" class="count-btn" @click="decrementChildren">
        <i class="bi bi-dash-square"></i>
      </button>

       <span class="label">어린이{{ childCount}}</span>
      <button type="button" class="count-btn" @click="incrementChildren">
       <i class="bi bi-plus-square"></i>  
      </button>
      

    </div>
    
    </div>
    </div>
    <div class=" d-flex flex-column w-100 h-25">
    <div class="container">
    <div class="card p-4 w-100 h-"><div class="counter-item"><!--띄어쓰기-->
        <button type="button" class="count-btn" @click="decrementAdults">
        <i class="bi bi-dash-square"></i>
      </button>

       <span class="label">어른{{ adultCount}}</span>
      <button type="button" class="count-btn" @click="incrementAdults">
       <i class="bi bi-plus-square"></i>  
      </button>
      

    </div>
    
    </div>
    </div>
    </div><!--75퍼까지 3개 차지-->

     </div>
     
  </div>
  

     
  
  </div><!--두번째-->

 
     
  </div><!--전체-->
</template>

<script setup lang="ts">
import Pagination from '@/components/Pagination.vue'
import { usePagination } from '@/util/pagination.js'
import { ref } from 'vue';
import { Calendar } from 'v-calendar';
import 'v-calendar/style.css';
import{Modal} from 'bootstrap';
//페이지 네이션 메서드
const { makePagination } = usePagination()
const pagination1 = ref({})
// 오늘 날짜를 기준으로 초기 페이지를 설정합니다.
const startPage = {
  year: new Date().getFullYear(),
  month: new Date().getMonth() + 1, // month는 1부터 시작
  day: new Date().getDate()
};

// 달력에 표시할 데이터를 정의하는 부분입니다. (핵심!)
// ref를 사용해 반응형 데이터로 만듭니다.
const attributes = ref([
  // 1. 오늘 날짜를 강조 표시 (highlight)
  {
    key: 'today',
    highlight: {
      color: 'purple', // 하이라이트 색상
      fillMode: 'solid',
    },
    dates: new Date(), // 오늘 날짜
  },
  // 2. 특정 날짜에 이벤트 점(dot) 표시
  {
    key: 'events',
    dot: 'blue', // 점 색상
    dates: [
      new Date(2025, 8, 15), // 2025년 9월 15일 (JS에서 월은 0부터 시작)
      new Date(2025, 8, 19), // 2025년 9월 19일
    ],
  },
]);
const adultCount = ref(0); //어른 반응형 함수 
const childCount = ref(0); //어린이 반응형 함수

const incrementAdults = () => {
  adultCount.value++;
  console.log(`인원수증가`);
}
const decrementAdults =() =>{
  if(adultCount.value>0)
  adultCount.value--;
console.log(`인원수감소`);
}
const incrementChildren = () =>{
  childCount.value++;
  console.log(`인원수증가`);
}
const decrementChildren =() =>{
  if(childCount.value > 0)
  childCount.value--;
console.log(`인원수감소`);

}
</script>

<style scoped>
.calendar-container {
  max-width: 800px; /* 달력의 최대 너비 지정 */
  margin: 20px auto; /* 페이지 중앙에 위치 */
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 8px;
}
</style>