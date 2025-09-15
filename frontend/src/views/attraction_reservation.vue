<template>
  <div class="container d-flex flex-column w-100 h-100">
    <div class="container d-flex flex-column w-100 h-25">
      <div>
        <label>현재시각:{{ currentTime }}</label>
  </div><!--25퍼 세로로-->
  <div class="container d-flex flex-column w-100 h-25"> <!--라디오버든 1개만 선택-->
    <div class="card"> <!--카드로 감싸기-->
    <nav class="navbar navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand">예약 가능 어트렉션 조회{{ selectedRideName }}</a>
    <form class="d-flex">
      <select v-model="selected">
        <option disabled value="">하나를 선택하세요</option>
        <option v-for="r in rides" :key="r.rideBookNo" value="r.rideBookNo">
          <select>
            {{  }}
          </select>
        </option>
        </select>
      <button class="btn btn-outline-success" type="submit">예약</button><!--검색 버튼 생성-->
    </form>
  </div>
  <div class="container d-flex flex-column w-100 h-50">

  </div>
</nav>
  
  </div>
  

  <div class=" d-flex flex-column w-100 h-25">
    <div class="container">
    <label>인원수</label>
  
    
    <div class=" d-flex flex-column w-100 h-25">
    <div class="container">
    <div class="card p-4 w-100 h-"><div class="counter-item"><!--띄어쓰기-->
        <button type="button" class="count-btn" @click="decrementAdults">
        <i class="bi bi-dash-square"></i>
      </button>

       <span class="label">인원{{ personCount}}</span>
      <button type="button" class="count-btn" @click="incrementPerson">
       <i class="bi bi-plus-square"></i>  
      </button>
      

    </div>
    
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
import { usePagination } from '@/util/pagination.js'
import { onMounted ,ref } from 'vue'; // 페이지 실행되고 바로실행
import { Calendar } from 'v-calendar';
import 'v-calendar/style.css';
import{Modal} from 'bootstrap';
import dayjs from "dayjs";
import axios from 'axios'; //api 호출을 위한 추가
// 오늘 날짜를 기준으로 초기 페이지를 설정합니다.

// 달력에 표시할 데이터를 정의하는 부분입니다. (핵심!)
// ref를 사용해 반응형 데이터로 만듭니다.
const selected = ref()
const personCount = ref(0); //어른 반응형 함수 
const defaultDay = dayjs();
const currentTime = dayjs().format('YYYY-MM-DD HH:mm:ss');

const selectedRideNo = ref(''); //선택한 어트렉션 번호
const userNo = ref(1);
const incrementPerson = () => {
  personCount.value++;
  console.log(`인원수증가`);
}
const decrementAdults =() =>{
  if(personCount.value>0)
  personCount.value--;
console.log(`인원수감소`);
}
onMounted(()=>{

})
const rides: {
  
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