<template>
<div class ="container d-flex flex-row "> <!--영역하나로-->
  <div class="row g-4"><!--여백설정 칸마다-->
<div class="col-md-6">
  <button class="btn btn-primary w-50">놀이동산 예약</button>
</div>
<div class="col-md-6">
  <button class="btn btn-primary w-50">입장권 예메</button>
</div>
  </div>
  <div>
    <div class = "containner col md-6 d-grid gap-3">
     <button type="reservationbutton" class="btn btn-primary btn-lg w-100">놀이동산예약 정보</button> <!--row -->
    <button type="attraction_information" class="btn btn-primary primary btn-lg w-100">놀이기구 정보</button>
    <button type="Amenities_information" class="btn btn-primary btn-lg w-100">편의시설 정보</button>
</div>
</div>

</div>




</template>

<script setup>
//import { useRouter } from 'vue-router'
//const router = useRouter()

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

// 업로드
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
  
</style>
