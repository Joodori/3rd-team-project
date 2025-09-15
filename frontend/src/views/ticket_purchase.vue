<template>
  <div class="container d-flex flex-column w-100 h-100">
    <div class="container d-flex flex-column w-100 h-25">
      <lable>날짜선택</lable><!--날자선택-->
      <div class="calendar-container"><!--외부 install 외부 라이브러리 넣어야됨 -->
  <input type="date" v-model="selectedDate"></input>
      </div><!--25퍼 세로로-->
      <div class="container d-flex flex-column w-100 h-25"> <!--라디오버든 1개만 선택-->
        <label>입장권 종류</label>
        <div class="card"> <!--카드로 감싸기-->
          <div v-for="option in ticketOptions" :key="option.value" class="form-check">
            <input class="form-check-input" type="radio" :value="option" v-model="selectedTicket"
              :id="'ticket' + option.name" name="ticketType" />
            <label :for="'ticket' + option.name" class="form-check-label">
              {{ option.name }}
            </label>
            <span>{{ option.value }}원</span>
          </div>
        </div>

        <div class=" d-flex flex-column w-100 h-25">
          <div class="container">
            <label>인원수</label>
            <div class="card p-4 w-100 h-">
              <div class="counter-item"><!--띄어쓰기-->
                <button type="button" class="count-btn" @click="decrementPerson">
                  <i class="bi bi-dash-square"></i>
                </button>
                <span class="label">인원수{{ personCount }}</span>
                <button type="button" class="count-btn" @click="incrementPerson">
                  <i class="bi bi-plus-square"></i>
                </button>
              </div>
            </div>
          </div><!--75퍼까지 3개 차지-->
        </div>
      </div><!--전체 100 100-->
    </div><!--두번째-->
  </div><!--전체-->
  <div class="d-flex flex-column align-items-end w-25 h-25">
    <button @click="buyTicket()">예약</button>
  </div>
</template>

<script setup>
import {useRouter} from 'vue-router' 
import { ref, onMounted } from 'vue'; //계산식이랑 임포트
import 'v-calendar/style.css';
import axios from 'axios';
import { storeToRefs } from 'pinia';
import { useUserInfo } from '@/stores/user'
const router = useRouter()
const userStore = useUserInfo()
const { user_info, loginStatus } = storeToRefs(userStore)

// 주형 수정 티켓 선택 부분 option리스트에 박아놓음
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
    if(confirm(`로그인 후 이용 가능합니다. 로그인 페이지로 이동하시겠습니까?`)) {
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
.calendar-container {
  max-width: 800px;
  /* 달력의 최대 너비 지정 */
  margin: 20px auto;
  /* 페이지 중앙에 위치 */
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 8px;
}
</style>