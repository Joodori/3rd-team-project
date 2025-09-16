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
    user_info.vlaue = {
        user_id: '',
        user_name: '',
        user_birth_date: '',
        user_age: '',
        user_address: '',
        user_mobile: ''
    }
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
async function readTickets() { /* ...원본 그대로... */ }

// 놀이기구예약
async function readRideBooks() { /* ...원본 그대로... */ }

// 입장권 입금 & 관리자 확인
async function send_money(ticket_no, moneyStatus) { /* ...원본 그대로... */ }

// 예약 취소
async function cancelReservation(reserve_no, facility_name) { /* ...원본 그대로... */ }

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
