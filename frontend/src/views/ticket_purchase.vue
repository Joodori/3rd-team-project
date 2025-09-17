<template>
  <div class="ticket-wrapper container py-5">

    <!-- 날짜 -->
    <div class="fancy-block mb-5">
      <label class="block-title">날짜 선택</label>
      <input type="date" v-model="selectedDate" class="form-control form-control-lg fancy-input" />
    </div>

    <!-- 티켓 종류 -->
    <div class="fancy-block mb-5">
      <label class="block-title">입장권 종류</label>
      <div class="d-grid gap-2">
        <label v-for="option in ticketOptions" :key="option.value"
          class="ticket-option d-flex align-items-center justify-content-between p-3 rounded">
          <div class="d-flex align-items-center gap-2">
            <input type="radio" class="form-check-input" :value="option"
             v-model="selectedTicket" name="ticketType" />
            <span class="fw-bold text-dark fs-4">{{ option.name }}</span>
          </div>
          <span class="price-tag">{{ option.value.toLocaleString() }}원</span>
        </label>
      </div>
    </div>

    <!-- 인원수 -->
    <div class="fancy-block mb-5">
      <label class="block-title">인원수</label>
      <div class="counter-box d-flex align-items-center justify-content-between px-4 py-3 rounded">
        <button type="button" class="btn-counter" @click="decrementPerson">
          <i class="bi bi-dash-lg"></i>
        </button>
        <span class="fs-3 fw-bold text-dark">{{ personCount }}</span>
        <button type="button" class="btn-counter" @click="incrementPerson">
          <i class="bi bi-plus-lg"></i>
        </button>
      </div>
    </div>

    <!-- 예약 버튼 -->
    <div class="d-flex justify-content-center text-end">
      <button class="btn btn-lg fancy-btn px-5" @click="buyTicket()">예약</button>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { ref, onMounted } from 'vue'; //계산식이랑 임포트
import 'v-calendar/style.css';
import axios from 'axios';
import { storeToRefs } from 'pinia';
import { useUserInfo } from '@/stores/user'
const router = useRouter()
const userStore = useUserInfo()
const { user_info, loginStatus } = storeToRefs(userStore)

// 주형 수정 티켓 선택 부분 option리스트에서 출력
const ticketOptions = ref([
  { name: '자유이용권', value: 35000 },
  { name: '오후권', value: 20000 },
  { name: 'vip이용권', value: 40000 },
  { name: 'vvip이용권', value: 50000 },
]);
// 여기서 selectedTicket은 선택된 티켓에대한 값을 가져옴
const selectedTicket = ref(ticketOptions.value);
const selectedDate = ref('')
const personCount = ref(0); //인원수 반응형 함수
const incrementPerson = () => {
  personCount.value++;
  console.log(`인원수증가`);
}
const decrementPerson = () => {
  if (personCount.value > 0)
    personCount.value--;
  console.log(`인원수감소`);
}
onMounted(() => {
  console.log(`티켓구매화면 호출됨`)
  checkLogin()
})
function checkLogin() {
  if (loginStatus.value === false) {
    if (confirm(`로그인 후 이용 가능합니다. 로그인 페이지로 이동하시겠습니까?`)) {
      router.push(`/login`)
    } else {
      alert(`메인페이지로 이동합니다.`)
      router.push('/')
    }
  }
}
/**
 * [B] 예약 버튼을 클릭하면 실행될 함수
 */
async function buyTicket() {
  // 2. 유효성 검사
  if (!selectedDate.value === null) {
    console.log(`날짜 : ${selectedDate.value}`)
    alert("날짜를 선택하세요.");
    return;
  }
  if (personCount.value <= 0) {
    alert("인원을 1명 이상 선택하세요.");
    return;
  }
  // 3. 백엔드로 보낼 최종 데이터 객체
  // userNo, ticketName, ticketAmount, ticketReserveDate만 들어가면 Ok
  const params = {
    userNo: user_info.value.user_no,
    ticketName: selectedTicket.value.name,
    //ticketReserveDate: selectedDate.value, // <--- 팀원이 요청한 'YYYY-MM-DD' 문자열
    ticketAmount: personCount.value
  };
  console.log("DB로 전송할 데이터:", params);
  // 4. 서버에 데이터(params)를 보내서 응답을 response에 받아옴
  const response = await axios.post('http://localhost/buyticket', params, {
    headers: {
      'Content-Type': 'application/json'
    }
  });
  console.log(`응답 -> ${JSON.stringify(response)}`)
  if (response.stsatus === 200) {
    console.log(`status : 200`)
  }
  if (response.data.length === 0) {
    alert(`관리자에게 문의바랍니다.`)
    return
  }
  if (confirm(`예약이 완료되었습니다. 마이페이지로 이동하여 입금을 완료하시겠습니까?`)) {
    router.push('/user-info')
  } else {
    alert('메인페이지로 이동합니다. 입금이 완료되면 마이페이지에서 수정 부탁드립니다.')
    router.push('/')
  }
}
</script>

<style scoped>
/* 배경 */
.ticket-wrapper {
  background: #f1f5f9;
  /* 밝은 회색 톤 */
  border-radius: 12px;
}

/* 섹션 카드 */
.fancy-block {
  width: 300px;
  background: #ffffff;
  border-radius: 12px;
  padding: 25px 28px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, .06);
}

/* 섹션 제목 */
.block-title {
  font-weight: 700;
  font-size: 1.05rem;
  color: #1e293b;
  /* 짙은 글자색 */
  margin-bottom: 15px;
}

/* 입력 */
.fancy-input {
  height: 50px;
  border: 2px solid #e2e8f0;
}

.fancy-input:focus {
  border-color: #4c51bf;
  /* 선명한 보라 */
  box-shadow: 0 0 0 .2rem rgba(76, 81, 191, .15);
}

/* 티켓 옵션 */
.ticket-option {
  background: #f8fafc;
  transition: background .25s;
}

.ticket-option:hover {
  background: #e9edf5;
}

.price-tag {
  font-weight: 600;
  color: #ed8936;
  /* 주황 포인트 */
}

/* 카운터 */
.counter-box {
  background: #f8fafc;
}

.btn-counter {
  background: #e2e8f0;
  border: none;
  width: 46px;
  height: 46px;
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 1.25rem;
  color: #1e293b;
}

.btn-counter:hover {
  background: #d4d9e2;
}

/* 예약 버튼 */
.fancy-btn {
  background: #4c51bf;
  /* 보라 메인 */
  color: #fff;
  border: none;
  border-radius: 40px;
  width: 200px;
}

</style>
