<!-- <!-- <template>
<div class="container d-flex flex-column w-100 h-100">
<div class="containner d-flex w-100 h-50 "><!-- 1 -->
    <div class ="d-flex flex-column w-50 h-100"><!-- a -->
      
      <div class="w-100 h-50 grid gap-3"><!--한번더위에 이등분-->
        <button class="btn btn-primary w-100 mt-3">놀이동산 예약</button>
      </div>

      <div class="w-100 h-50 grid gap-3"><!--한번더위에 이등분-->
        <button class="btn btn-primary w-100 mt-3">놀이동산 예약</button>
      </div>
      
    </div>

    <div class="d-flex flex-column w-50 h-100 justify-content-between gap-10"><!-- b -->
      
      <div class="h-25 w-100">
       <button class="btn btn-primary w-100">놀이기구 및 시설안내</button>
      </div>

         <div class="h-25 w-100">
       <button class="btn btn-primary w-100">입장</button>
      </div>

         <div class="h-25 w-100">
       <button class="btn btn-primary w-100">티켓예메</button>
      </div>


    </div>
    
    
  </div>
  
  <div id="carousel" class=" w-100 h-50 carousel slide mt-5" data-bs-ride="carousel" data-bs-interval="3000" data-bs-pause="hover">
  <div class="carousel-inner">
    <!-- 첫 슬라이드만 active -->
    <div class="carousel-item active">
      <img src="/assets/hesung.jpg" class="d-block w-100" alt="hesung">
    </div>
    <div class="carousel-item">
      <img src="/assets/baiking.jpg" class="d-block w-100" alt="baiking">
    </div>
    <div class="carousel-item">
      <img src="/assets/holombride.jpg" class="d-block w-100" alt="holombride">
    </div>
    <div class="carousel-item">
      <img src="/assets/jailoswing.jpg" class="d-block w-100" alt="jailoswing">
    </div>
    <div class="carousel-item">
      <img src="/assets/jailoompdroop.jpg" class="d-block w-100" alt="jailoompdroop">
    </div>
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

// 케레셸







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
</style> -->
<!-- src/views/Reservation.vue-->
<template>
  <v-form @submit.prevent="submitReservation" ref="formRef">
    <v-container>
      <!-- 날짜 선택 -->
      <v-row dense class="py-1">
        <v-col cols="12">
          <v-text-field
            label="예약 날짜"
            v-model="form.date"
            type="date"
            :rules="[rules.required, rules.notPast]"
            required
            dense
          />
        </v-col>
      </v-row>

      <!-- 시간 선택 영역 -->
      <v-row>
        <v-col cols="12">
          <v-label class="mb-2 font-weight-bold">시간 선택</v-label>
          <v-input
            :rules="[rules.required]"
            v-model="form.timeSlots"
            class="py-1"
          >
            <template #default>
              <v-checkbox
                v-for="slot in allTimeSlots"
                :key="slot"
                v-model="form.timeSlots"
                :label="slot"
                :value="slot"
                color="primary"
                hide-details
              />
            </template>
          </v-input>
        </v-col>
      </v-row>

      <!-- 메모 -->
      <v-row dense class="py-1">
        <v-col cols="12">
          <v-textarea
            label="메모"
            v-model="form.memo"
            auto-grow
            clearable
            rows="2"
            class="text-caption"
          />
        </v-col>
      </v-row>

      <!-- 버튼 -->
      <v-row dense class="py-1">
        <v-col cols="12">
          <v-btn type="submit" color="primary" block>예약하기</v-btn>
        </v-col>
      </v-row>

      <!-- 예약 성공 모달 -->
    <v-dialog v-model="dialog" max-width="400" @click:outside="closeDialog" @keydown.esc="closeDialog">
        <v-card v-if="reservationResult">
          <v-card-title class="headline">예약 완료</v-card-title>
          <v-card-text>
            <v-table>
              <tbody>
                <tr>
                  <th class="text-left">예약 ID</th>
                  <td>{{ reservationResult.id }}</td>
                </tr>
                <tr>
                  <th class="text-left">예약 날짜</th>
                  <td>{{ reservationResult.date }}</td>
                </tr>
                <tr>
                  <th class="text-left">시간</th>
                  <td>{{ reservationResult.timeSlots?.join(', ') || '' }}</td>
                </tr>
                <tr>
                  <th class="text-left">메모</th>
                  <td>{{ reservationResult.memo || '없음' }}</td>
                </tr>
                <tr>
                  <th class="text-left">상태</th>
                  <td>{{ reservationResult.status }}</td>
                </tr>
                <tr>
                  <th class="text-left">생성 시간</th>
                  <td>{{ formatDateTime(reservationResult.createdAt) }}</td>
                </tr>
              </tbody>
            </v-table>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="primary" text @click="closeDialog">확인</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-container>
  </v-form>
</template>


<script setup>
import { ref } from 'vue'
import { useReservationStore } from '@/stores/reservationStore'

const reservationStore = useReservationStore()
const formRef = ref()

const form = ref({
  date: '',
  timeSlots: [],
  memo: '',
})

const allTimeSlots = [
  '09:00~10:00',
  '10:00~11:00',
  '11:00~12:00',
  '13:00~14:00',
  '14:00~15:00',
]

const rules = {
  required: v => (Array.isArray(v) ? v.length > 0 : !!v) || '필수 입력 항목입니다',
  notPast: v => {
    if (!v) return true
    const selectedDate = new Date(v)
    const today = new Date()
    today.setHours(0, 0, 0, 0) // 오늘 00:00으로 초기화
    return selectedDate >= today || '오늘 이전 날짜는 선택할 수 없습니다'
  }
}

// 모달 열림 상태
const dialog = ref(false)

// 예약 결과 저장용
const reservationResult = ref(null)

const submitReservation = async () => {
  const { valid } = await formRef.value.validate()
  if (!valid) return

  try {
    const res = await reservationStore.submitReservation(form.value)
    reservationResult.value = res // 결과 저장
    dialog.value = true           // 모달 열기
  } catch (err) {
    alert('예약 실패: ' + (err.response?.data?.message || err.message))
  }
}

const closeDialog = () => {
  dialog.value = false
  reservationResult.value = null
  // 필요하면 폼 초기화
  form.value = { date: '', timeSlots: [], memo: '' }
}

const formatDateTime = (value) => {
  const date = new Date(value)
  return date.toLocaleString('ko-KR', {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit',
    hour: '2-digit',
    minute: '2-digit'
  })
}
</script>

<style scoped>
/* 테이블 셀에 여백 주기 */
.v-simple-table th,
.v-simple-table td {
  padding: 12px 16px;
}
</style> -->
