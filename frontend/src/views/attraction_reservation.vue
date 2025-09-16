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
            is-panded

            v-model="selectedDate"
            :main-date="today"
            :max-date="oneWeekLater"
        />
      </div><!--25퍼 세로로-->
      <div class="container d-flex flex-column w-100 h-25"> <!--라디오버든 1개만 선택-->
        <label>입장권 종류</label>
        <div class="card"> <!--카드로 감싸기-->
          <div class="form-check">
            <input class="form-check-input" type="radio"
                   :value="35000" id="ticketFree"
                   name="ticketType"
                   v-model="selectedTicketPrice"/>
            <label class="form-check-label" for="tiketFree">
              자유이용권 35000원
            </label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="radio"
                   :value="20000" id="ticketAfternoon" name="ticketType"
                   v-model="selectedTicketPrice"/>
            <label class="form-check-label" for="tiketAfternoon"><!--라디오 선택 항목-->
              오후권 20000원
            </label>
          </div>

          <div class="form-check">
            <input class="form-check-input" type="radio" :value="40000" id="ticketVip"
                   v-model="selectedTicketPrice"/>
            <label class="form-check-label" for="tiketType">
              vip이용권 40000원
            </label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="radio" :value="50000" id="ticketVvip"
                   v-model="selectedTicketPrice"/>
            <label class="form-check-label" for="tiketType">
              vvip이용권 50000원
            </label>
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
            <div class="d-flex flex-column w-100 h-25">
              <div class="card">
                <div class="card p-4 w-100 h-100">
                  <div class="cost">
                    <span>총금액 :{{ totalCostFormatted }}원 </span>
                  </div>

                </div>
              </div>
            </div>

          </div><!--75퍼까지 3개 차지-->


        </div>
      </div><!--전체 100 100-->


    </div><!--두번째-->


  </div><!--전체-->
  <div class="d-flex flex-column align-items-end w-25 h-25">
    <button @click="handleReservation" console.log(`버튼 눌림;`)>예약</button>
  </div>
</template>

<script setup lang="ts">
import {ref, computed} from 'vue'; //계산식이랑 임포트
import {Calendar} from 'v-calendar';
import 'v-calendar/style.css';
import axios from 'axios';


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
const selectedTicketPrice = ref(0); //위에 선언한 티켓값
const selectedDate = ref(null);

const incrementPerson = () => {
  personCount.value++;
  console.log(`인원수증가`);
}
const decrementPerson = () => {
  if (personCount.value > 0)
    personCount.value--;
  console.log(`인원수감소`);

}


const totalCostFormatted = computed(() => { // 계산 + 출력까지 반응형으로
  return (selectedTicketPrice.value * personCount.value).toLocaleString('ko-KR');
})
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
// 라디오 버튼값이 때른 티켓이름을 반환하는 함수
const getTicketName = (price) => {
  switch (price) {
    case 35000:
      return "자유이용권";
    case 20000:
      return "오후권";
    case 40000:
      return "vip이용권";
    case 50000:
      return "vvip이용권";
  }
}

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

  // 3. DTO 에 맞는 객체 데이터로 객체 수정
  const reservationData = {
    ticketName: getTicketName(selectedTicketPrice.value), // 선택한 티켓 이름
    ticketAmount: personCount.value, // 티켓 수량 (인원수)
    ticketMoneyStatus: "결제완료", // 결제 상태
    ticketReserveDate: formattedDate // 예약 날짜 (필요시 추가)
  };
  console.log("전송할 예약 데이터:", reservationData);

  const response = await axios.post('http://localhost/api/reservations', reservationData, {
    headers: {
      'Content-Type': 'application/json'
    }
  });
  console.log("서버응답:", response.data);
  alert(`예약이 완료되었습니다!\n날짜: ${formattedDate}\n티켓: ${getTicketName(selectedTicketPrice.value)}
     \n인원: ${personCount.value}명\n총 금액: ${totalCostFormatted.value}원`);
}, catch(error)
{
  console.error("예약 실패:", error);
  alert("예약에 실패했습니다. 다시 시도해주세요.");
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