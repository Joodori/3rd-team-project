<template>
<div class="container d-flex flex-column w-100 h-100">


<div class="containner d-flex w-100 h-100"><!-- 1 -->
    <div class ="d-flex flex-column justify-content-between w-50 h-100 me-2 gap-5" ><!-- a -->
      
      <div class="w-100 h-100 d-grid gap-3"><!--한번더위에 이등분-->
        <button class="btn btn-primary w-100 h-100 ">놀이동산 예약</button>
      </div>

      <div class="w-100 h-50 d-grid gap-3"><!--한번더위에 이등분-->
        <button @click="ticket_reservation()" class="btn btn-primary w-100  h-100 mt-3">어트렉션 예약</button>
      </div>
      
    </div>

    <div class="d-flex flex-column w-50 h-100 justify-content-between align-items-center gap-5"><!-- b -->
      
      <div class="h-25 w-100">
       <button class="btn btn-primary w-100">놀이기구 및 시설안내</button>
      </div>

         <div class="h-25 w-100">
       <button class="btn btn-primary w-100">입장</button>
      </div>

         <div class="h-25 w-100">
       <button @click="book_confirm()" class="btn btn-primary w-100">티켓예메</button>
      </div>


    </div>
    
    
  </div>
  </div>
  
  <div id="carousel" class=" w-100 h-50 carousel slide mt-5" data-bs-ride="carousel" data-bs-interval="3000" data-bs-pause="hover">
  <div class="carousel-inner">
    <!-- 첫 슬라이드만 active -->
    <div class="carousel-item active">
      <img src="/assets/hesung.jpg" class="d-block w-100 h-100" alt="hesung">
    </div>
    <div class="carousel-item">
      <img src="/assets/baiking.jpg" class="d-block w-100 h-100" alt="baiking">
    </div>
    <div class="carousel-item">
      <img src="/assets/holombride.jpg" class="d-block w-100 h-100" alt="holombride">
    </div>
    <div class="carousel-item">
      <img src="/assets/jailoswing.jpg" class="d-block w-100 h-100" alt="jailoswing">
    </div>
    <div class="carousel-item">
      <img src="/assets/jailoompdroop.jpg" class="d-block w-100 h-100" alt="jailoompdroop">
    </div>
  </div>
  </div>
  



</template>

<script setup>
// 뷰라우터 사용하고
import { useRouter } from 'vue-router'
const router = useRouter()
//그 날짜마 놀이기구 예약이 가능한 놀이기구 예약 시스탬
const book_confirm =() =>{
  router.push('/attraction_reservation')
}
//티켓 예약 페이지로 이동하는 메소드 
const ticket_reservation =() =>{
  router.push('/ticket_purchase')
}

// 스토어 (공통저장소에 있는 것 사용하기)
//import { storeToRefs } from 'pinia'

//import { useAnimalStore } from '@/stores/animal'
//const animalStore = useAnimalStore()
//const { animals, mode, selectedIndex } = storeToRefs(animalStore)

// 웹서버로 요청하기 위한 모듈
import axios from 'axios'


// 페이지네이션 혹시 필요하게 되면 사용하기
//import Pagination from '@/views/Pagination.vue'
//import { usePagination } from '@/views/Pagination.vue'
//const { makePagination } = usePagination()

//const pagination1 = ref({})


// 부트스트랩
import { Modal } from 'bootstrap'

//예메 페이지로 이동 







</script>
<style scoped>
  .row > div{
   height:150px;
  }
  button {
     background-color: skyblue;
    border: 2px solid black;
    height:100px;
  }
  .carouse{
    max-height: 50vh;
    margin-left: 10px;
    margin-right: 10px;
  }

  .my-border {
    border: 2px dashed violet;
  }
</style>
