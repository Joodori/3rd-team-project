<template>
  <div class="container d-flex flex-column w-100 h-100">
    <div class="container d-flex flex-column w-100 h-25">

      <lable>날짜선택</lable><!--날자선택-->
      <div class="calendar-container"><!--외부 install 외부 라이브러리 넣어야됨 -->
        <Calendar view="weekly" :attributes="attributes" :initial-page="startPage" locale="ko-KR" is-panded
          v-model="selectedDate" :main-date="today" :max-date="oneWeekLater" />
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
    <button @click="handleReservation()">예약</button>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'; //계산식이랑 임포트
import { Calendar } from 'v-calendar';
import 'v-calendar/style.css';
import axios from 'axios';
import { storeToRefs } from 'pinia';
import { useUserInfo } from '@/stores/user'
const userStore = useUserInfo()
const { user_info } = storeToRefs(userStore)

// 주형 수정 티켓 선택 부분 option리스트에 박아놓음
const ticketOptions = ref([
  { name: '자유이용권', value: 35000 },
  { name: '오후권', value: 20000 },
  { name: 'vip이용권', value: 40000 },
  { name: 'vvip이용권', value: 50000 },
]);

// 여기서 selectedTicket은 선택된 티켓에대한 값을 가져옴
const selectedTicket = ref(ticketOptions.value);



// 오늘 날짜를 기준으로 초기 페이지를 설정합니다.
const today = new Date(); // 최소 날짜 (오늘)
const oneWeekLater = new Date(); // 최대 날짜 (7일 뒤)
oneWeekLater.setDate(today.getDate() + 7);

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
const personCount = ref(0); //인원수 반응형 함수
const selectedDate = ref('');

const incrementPerson = () => {
  personCount.value++;
  console.log(`인원수증가`);
}
const decrementPerson = () => {
  if (personCount.value > 0)
    personCount.value--;
  console.log(`인원수감소`);

}


const getFormattedDate = (dateObj) => {
  // 1. 날짜가 선택되지 않았으면(null) 오류 방지
  if (!dateObj) {
    return null;
  }
  // 2. 년/월/일 추출
  const year = dateObj.getFullYear();
  const month = (dateObj.getMonth() + 1).toString().padStart(2, '0'); // getMonth()는 0부터 시작(+1 필요)
  const day = dateObj.getDate().toString().padStart(2, '0'); // 2자리 수 맞춤

  // 3. 문자열로 조합
  return `${year}-${month}-${day}`;
};

/**
 * [B] 예약 버튼을 클릭하면 실행될 함수
 */
const handleReservation = async () => {
  // 1. 위에서 만든 함수를 호출하여 형변환 수행
  const formattedDate = getFormattedDate(selectedDate.value); // 예: '2025-09-15'

  // 2. 유효성 검사
  if (!formattedDate) {
    alert("날짜를 선택하세요.");
    return;
  }
  if (personCount.value <= 0) {
    alert("인원을 1명 이상 선택하세요.");
    return;
  }
  if (selectedTicketPrice.value === 0) {
    alert("티켓 종류를 선택하세요.");
    return;
  }

  // 3. 백엔드로 보낼 최종 데이터 객체
  // userNo, ticketName, ticketAmount, ticketReserveDate만 들어가면 Ok
  const params = {
    userNo: user_info.value.user_no,
    ticketName: selectedTicket.value.name,
    ticketReserveDate: formattedDate, // <--- 팀원이 요청한 'YYYY-MM-DD' 문자열
    count: personCount.value
  };
  console.log("DB로 전송할 데이터:", params);

  // 4. 서버에 데이터(params)를 보내서 응답을 response에 받아옴
  const response = await axios.post('http://localhost/user/login', params, {
    headers: {
      'Content-Type': 'application/json'
    }
  });

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

  
  

};
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