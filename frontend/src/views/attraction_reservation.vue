<!--매직패스 당일 예약-->
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
</style>
