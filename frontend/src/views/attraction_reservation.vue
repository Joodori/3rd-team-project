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
              <a class="navbar-brand">예약 가능 어트렉션 조회</a>
              <form class="d-flex" @submit.prevent="searchByNo(facility_selected)">
                <select v-model="facility_selected">
                  <option disabled value="">하나를 선택하세요</option>
                  <option v-for="facility in facilityList" :key="facility.facilityNo" :value="facility.facilityNo">
                    {{ facility.facilityName }}, {{ facility.facilityNo }}
                  </option>
                </select>
                <button class="btn btn-outline-success" type="submit">조회</button>
              </form>
            </div>
            <div class="container d-flex flex-column w-100 h-50">

            </div>
          </nav>

        </div>

        <!-- 이 부분이 검색하면 나타나게 수정 -->
        <!-- v-for를 이용해서 검색한 facilityName으로만 특정 놀이기구예약가능리스트가 뜨게 만듦  -->
        <!-- 쿼리문은 주형이가 짤테니 넌 그냥 여기서 받는거만 만들면 돼-->
        <div> <!-- 이 div 태그부터 v-for 적용-->
          <div v-for="(rideItem, index) in rideList" :key="rideItem.rideBookNumber"> <!--놀이기구이름이랑 시간 쫙 뜨꼐하기-->
            <div v-if="!search" class=" d-flex flex-column w-100 h-25">
              <div class="container">
                <h5>{{ rideItem.facilityName }}</h5>
                <p>예약시간{{ rideItem.rideBookTime }}</p>
                <label>인원수</label>
                <div class=" d-flex flex-column w-100 h-25">
                  <div class="container">
                    <div class="card p-4 w-100 h-">
                      <div class="counter-item"><!--띄어쓰기-->
                        <button type="button" class="count-btn" @click="decrementAdults">
                          <i class="bi bi-dash-square"></i>
                        </button>
                        <span class="label">인원{{ personCount }}</span>
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

        </div>
      </div>




    </div><!--두번째-->
  </div><!--전체-->
  <div class="d-flex flex-column align-items-end w-25 h-25">
    <button @click="handleReservation" console.log(`버튼 눌림;`)>예약</button>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'; // 페이지 실행되고 바로실행
import 'v-calendar/style.css';
import { Modal } from 'bootstrap';
import dayjs from "dayjs";
import axios from 'axios'; //api 호출을 위한 추가
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

const facilityList = ref([]) //facilty 테이브 데이터
const selectedFacilityNo = ref('');//선택된 시설번호
const selected = ref('')
const personCount = ref(0); //인원수 함수 
const defaultDay = dayjs();
const currentTime = dayjs().format('YYYY-MM-DD HH:mm:ss');
const dtabase = ref();
const search = ref(true)
const facility_selected = ref('')
const FacilityNumber = ref([])
const rideList = ref([])
const incrementPerson = () => {
  personCount.value++;
  console.log(`인원수증가`);
}
const decrementAdults = () => {
  if (personCount.value > 0)
    personCount.value--;
  console.log(`인원수감소`);
}

// 주형수정
async function loadFacilities() {
  try {
    const response = await axios.post('http://localhost/getRideReserveList'); //원격 데이터 베이스에서 가져와야ㅐ됨
    // response라고 대답을 받아왔어
    // 무슨 말로 했지? 일단 나는 대답은 알고있긴한데 무슨 언어로 말했는지를 모르겠어 일본어?> 영어? 스페인어? 뭐지?
    console.log(`응답 -> ${response}`)
    facilityList.value = response.data;
    console.log('시설 목록 로드 완료:', facilityList.value);
  } catch (error) {
    console.error('시설 목록 로드 실패:', error);
  }
}


// start 
// 여기서부터 검색버튼 누르면 검색한 놀이기구의 이름만 예약가능목록이 뜨게 하는 메서드 작성 
async function searchByNo(facility_no) {

  console.log(`넘겨지는 수 ; ${facility_no}`)


  const response = await axios.get(`http://localhost/getRideReserveByNo?facility_no=${facility_no}`, {
    headers: {
      'Content-Type': 'application/json'
    }
  })

  console.log(`응답 ->${JSON.stringify(response)}`)
  rideList.value = response.data
  search.value = false
}
// end


//시설 목록을 데이터 베이스에서 불러옴
const handleSubmit = async () => {
  if (!selected.value) {
    alert('시설을 선택해주세요.');
    return;
  }
  if (personCount.value < 0) {
    alert(`인원수를 입력하세요`);
    return;
  }
  try {
    const params = {
      facilityNo: selected.value,
      personCount: personCount.value
    };
    const response = await axios.post('http://localhost/getRideReserveByNo', params, {
      headers: {
        'Content-Type': 'application/json'
      }
    });
    console.log(`응답 -> ${response}`)
    console.log('예약 응답:', response.data);
  } catch (error) {
    console.error('예약 실패', error);
    //에러처리
  }
}
onMounted(async () => {
  await loadFacilities(); //초기로딩에 시설목록 불러오기
})

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