<!-- MyPage.vue -->
<template>
    <div class="mx-auto" style="max-width:520px;">
        <!-- ========== 일반 사용자 정보 ========== -->
        <div v-if="config === 'user'" class="d-flex flex-column align-items-center py-5">
            <div class="bg-white rounded-4 shadow p-5 w-100">
                <h2 class="fw-bold text-center mb-4">내 정보</h2>

                <ul class="list-unstyled mb-4 fs-5 fw-semibold">
                    <li class="mb-2"><span class="text-muted">ID :</span> {{ user_info.user_id }}</li>
                    <li class="mb-2"><span class="text-muted">이름 :</span> {{ user_info.user_name }}</li>
                    <li class="mb-2"><span class="text-muted">생일 :</span> {{ user_info.user_birth_date }}</li>
                    <li class="mb-2"><span class="text-muted">메일 :</span> {{ user_info.user_address }}</li>
                    <li><span class="text-muted">전화 :</span> {{ user_info.user_mobile }}</li>
                </ul>

                <div class="d-flex justify-content-center gap-3">
                    <button class="btn btn-primary px-4" @click="goToHome">돌아가기</button>
                    <button class="btn btn-info px-4" @click="logout()">로그아웃</button>
                    <button class="btn btn-danger px-4" @click="quit()">탈퇴</button>
                </div>
            </div>
        </div>

        <!-- ========== 관리자 정보 ========== -->
        <div v-if="config === 'admin'" class="d-flex flex-column align-items-center py-5">
            <div class="bg-white rounded-4 shadow p-5 w-100">
                <h2 class="fw-bold text-center mb-4">관리자 페이지</h2>

                <ul class="list-unstyled mb-4 fs-5 fw-semibold">
                    <li class="mb-2"><span class="text-muted">ID :</span> {{ user_info.user_id }}</li>
                    <li class="mb-2"><span class="text-muted">이름 :</span> {{ user_info.user_name }}</li>
                    <li><span class="text-muted">전화 :</span> {{ user_info.user_mobile }}</li>
                </ul>

                <div class="d-flex justify-content-center gap-3">
                    <button class="btn btn-primary px-4" @click="goToHome">돌아가기</button>
                    <button class="btn btn-info px-4" @click="logout()">로그아웃</button>
                </div>
            </div>
        </div>

        <!-- ========== 일반 사용자 입장권 & 예약 ========== -->
        <div v-if="config === 'user'" class="w-100">
            <div v-for="(ticket, index) in ticket" :key="ticket.ticketNo" class="w-100 mb-4">

                <!-- 1) 썸네일 이미지 -->
                <img src='###' alt="티켓 이미지" class="rounded mb-3" style="width:100%;height:160px;object-fit:cover;" />

                <!-- 2) 티켓 정보 영역 -->
                <div class="d-flex gap-3">

                    <!-- 상태 배지 -->
                    <div class="d-flex flex-column align-items-center" style="width:25%;">
                        <span class="badge"
                            :class="ticket.ticketMoneyStatus === '입금대기' ? 'text-bg-warning' : 'text-bg-success'">
                            {{ ticket.ticketMoneyStatus }}
                        </span>
                    </div>

                    <!-- 상세 텍스트 -->
                    <div class="flex-grow-1">
                        <h5 class="fw-bold mb-1">{{ ticket.ticketName }}</h5>
                        <div class="small text-muted mb-1">{{ ticket.ticketReserveDate }}</div>
                        <div class="small">수량: {{ ticket.ticketAmount }}</div>
                    </div>

                    <!-- 액션 버튼 -->
                    <div class="d-flex flex-column align-items-end justify-content-between">
                        <button v-if="ticket.ticketMoneyStatus === '입금대기'" class="btn btn-sm btn-info"
                            @click="send_money(ticket.ticketNo)">
                            입금하기
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <!-- 놀이기구 예약 -->
        <div class="bg-white rounded-4 shadow p-4">
            <h4 class="fw-bold text-center mb-3">
                {{ user_info.user_name }}님의 놀이기구 예약 내역
            </h4>

            <div v-if="no_reserve" class="text-center py-3 fs-5">예약 내역이 없습니다.</div>

            <table v-else class="table align-middle">
                <thead class="table-light">
                    <tr>
                        <th>예약 번호</th>
                        <th>어트랙션</th>
                        <th>시간</th>
                        <th>인원</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="item in ride" :key="item.rideBookConfirmNo">
                        <td>{{ item.rideBookConfirmNo }}</td>
                        <td>{{ item.facilityName }}</td>
                        <td>{{ item.rideBookTime ?? '미정' }}</td>
                        <td>{{ item.rideBookPersonAmount }}</td>
                        <td>
                            <button class="btn btn-sm btn-danger"
                                @click="cancelReservation(item.rideBookConfirmNo, item.facilityName)">
                                취소
                            </button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

    <!-- ========== 관리자 티켓 관리 ========== -->
    <div v-if="config === 'admin'" class="w-100">
        <div v-for="ticket in ticket" :key="ticket.ticketNo" class="d-flex gap-3 p-4 mb-4 bg-white rounded-3 shadow-sm">
            <div class="d-flex align-items-center justify-content-center bg-light rounded"
                style="width:30%;min-height:90px;">🎫</div>

            <div class="flex-grow-1">
                <h5 class="fw-bold mb-1">USER NO: {{ ticket.userNo }}</h5>
                <div class="small mb-1">{{ ticket.ticketName }} ({{ ticket.ticketReserveDate }})</div>
                <div class="small">수량: {{ ticket.ticketAmount }}</div>
            </div>

            <div class="d-flex flex-column align-items-end justify-content-between">
                <span class="badge"
                    :class="ticket.ticketMoneyStatus === '예약확정' ? 'text-bg-success' : 'text-bg-warning'">
                    {{ ticket.ticketMoneyStatus }}
                </span>
                <button class="btn btn-sm btn-info mt-2" @click="send_money(ticket.ticketNo, ticket.ticketMoneyStatus)">
                    확인
                </button>
            </div>
        </div>
    </div>
</template>

<script setup>
/* ========= 원본 script 그대로 ========= */
import { storeToRefs } from 'pinia';
import { useUserInfo } from '@/stores/user'
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
const router = useRouter()

const userStore = useUserInfo()
const { user_info, ticket, ride, config, loginStatus } = storeToRefs(userStore)

const no_reserve = ref(true)

onMounted(() => {
    console.log(user_info.value)
    console.log(loginStatus.value)
    console.log(`config : ${config.value}`)
    console.log(`userName : ${user_info.value.user_name}`)
    console.log(`user_info 호출됨`)
    checkLogin()
    readTickets()
    readRideBooks()
})

function checkLogin() {
    if (loginStatus.value != true) {
        alert(`로그인 후 이용가능합니다.`)
        router.push('/login')
    }
}

// 로그아웃 함수
function logout() {
    alert(`로그아웃 되었습니다.`)
    loginStatus.value = false
    router.push('/')
}

// 회원탈퇴 함수
async function quit() {
    if (confirm(`회원탈퇴를 진행하시겠습니까?`)) {
        const params = {
            userNo: user_info.value.user_no
        }
        const response = await axios.post(`http://localhost/user/delete-account`, {
            headers: {
                'Content-Type': 'application/json'
            }
        });
        loginStatus.value = false
        router.push('/')
    }
}

// 입장권 내역
async function readTickets() {
    // 일반 사용자일때 사용자No로 입장권 내역 가져옴
    if (config.value === 'user') {
        try {

            const response = await axios.get(`http://localhost/getticket?user_no=${user_info.value.user_no}`, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });

            console.log(`응답 -> ${JSON.stringify(response.data)}`)
            // 여기서 response.data는 list형태로 나옴
            // ticket이라는 store변수는 ref([]) 배열형태로 만들어놓았기 때문에
            // 들어온거(배열_response.data) => 넣음을 당하는거(배열_ticket이라는 stroe변수)
            ticket.value = response.data
            if (ticket.value.length == 0) {
                console.log(`입장권 구매내역이 없어요`)
            }

        } catch (err) {
            console.error(`물품목록::에러발생 -> ${err}`)
        }
    }

    // 관리자일때는 모든 사용자의 입장권구매내역이 나옴
    else {
        try {

            const response = await axios.get(`http://localhost/getticketAdmin`, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });

            console.log(`응답 -> ${JSON.stringify(response.data)}`)
            ticket.value = response.data
            if (ticket.value.length == 0) {
                console.log(`입장권을 구매한 사용자가 없어요`)
            }

        } catch (err) {
            console.error(`물품목록::에러발생 -> ${err}`)
        }

    }
}

// 놀이기구예약
async function readRideBooks() {
    // 관리자가 로그인 한 경우에는 return시킴
    if (config.value === 'admin') {
        return
    }

    try {
        const response = await axios.get(`http://localhost/getRideBookList?user_no=${user_info.value.user_no}`, {
            headers: {
                'Content-Type': 'application/json'
            }
        })

        console.log(`응답 -> ${JSON.stringify(response.data)}`)
        console.log(`time data type : ${typeof (response.data[0].rideBookTime)}`)
        ride.value = response.data

        if (ride.value.length != 0) {
            no_reserve.value = false
        } else {
            console.log(`놀이기구 예약내역 없음`)
        }
    } catch (err) {
        console.error(`물품목록::에러발생 -> ${err}`)
    }
}

// 입장권 입금 함수 + 관리자 입금확인 함수
async function send_money(ticket_no, moneyStatus) {
    console.log(`send_money 호출됨`)
    if (moneyStatus != "입금대기") {
        return
    }
    // 일반 사용자일때 입금대기 -> 입금완료로 바뀌게 만듦
    if (config.value === "user") {

        if (confirm(`XX은행 1002-757-04-8585 에 입금하셨습니까?`)) {

            try {
                const params = {
                    "ticketNo": ticket_no
                }

                const response = await axios.patch('http://localhost/updateMoneyStatusUser', params, {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                });

                console.log(`응답 -> ${JSON.stringify(response.date)}`)

                // 만약 update에 실패했다면
                if (response.data == "") {
                    alert(`응답이없으,,ㅁ`)
                }
                readTickets()

            } catch (error) {

            }
        }
    } else {

        if (moneyStatus === "입금대기") {
            alert(`회원이 입금하기 전입니다.`)
            return
        }

        if (moneyStatus === "예약확정") {
            alert(`이미 처리되었습니다.`)
            return
        }


        if (confirm(`입급확인 처리하시겠습니까?`)) {

            try {
                const params = {
                    "ticketNo": ticket_no,
                }

                const response = await axios.patch('http://localhost/updateMoneyStatusManager', params, {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                });

                console.log(`응답 -> ${JSON.stringify(response.date)}`)

                // 만약 update에 실패했다면
                if (response.data == "") {
                    alert(`서버측에 문의해주세요`)
                }
                readTickets()

            } catch (error) {

            }
        }
    }

}

// 예약 취소
async function cancelReservation(reserve_no,facility_name) {
    console.log(typeof(reserve_no))
    try {

        const response = await axios.delete(`http://localhost/cancelReservation?ride_book_confirm_no=${reserve_no}`, {
            headers: {
                'Content-Type': 'application/json'
            }
        });

        console.log(`응답 -> ${JSON.stringify(response.date)}`)

        // 만약 update에 실패했다면
        if (response.data == "") {
            alert(`응답이없으,,ㅁ`)
        } else {
            alert(`놀이기구명 : ${facility_name} 예약이 취소되었습니다.`)
        }
        readRideBooks()

    } catch (err) {
        console.error(`취소 중 에러발생 -> ${err}`)
    }
}


// 홈 이동
function goToHome() {
    router.push('/')
}
</script>

<style scoped>
/* 공통 포커스 */
.table>:not(caption)>*>* {
    vertical-align: middle;
}

.thumb-wrapper {
    overflow: hidden;
    /* 라운드 모서리 내부에 오버레이 자르기 */
}

.thumb-overlay {
    position: absolute;
    inset: 0;
    /* 이미지 전체 덮기 */
    background: rgba(0, 0, 0, .45);
    /* 반투명 검정 필터 */
    padding: 12px 16px;
    text-align: center;
}
</style>
