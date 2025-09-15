<!-- 회원 마이페이지 -->

<template>
    <div style="width: 500px;">

        <!--begin::기본정보-->
        <!-- v-if="user === true" 일때는 일반 유저일때 정보 -->
        <div v-if="config === 'user'" class="d-flex flex-column justify-content-center align-items-center py-5  ">
            <div class="bg-white rounded p-4 w-100">
                <h2 class="mb-4 text-center fw-bold">로그인 유저의 정보</h2>
                <div class="fs-2 fw-bold mb-3">
                    <span>ID :</span> <span>{{ user_info.user_id }}</span>
                </div>
                <div class="fs-2 fw-bold mb-3">
                    <span>이름 :</span> <span>{{ user_info.user_name }}</span>
                </div>
                <div class="fs-2 fw-bold mb-3">
                    <span>생일 :</span> <span>{{ user_info.user_birth_date }}</span>
                </div>
                <div class="fs-2 fw-bold mb-3">
                    <span>메일주소 :</span> <span>{{ user_info.user_address }}</span>
                </div>
                <div class="fs-2 fw-bold mb-4">
                    <span>전화번호 :</span> <span>{{ user_info.user_mobile }}</span>
                </div>

                <div class="d-flex justify-content-center gap-3">
                    <button class="btn btn-primary px-4" @click="goToHome">돌아가기</button>
                    <button class="btn btn-info px-4" @click="logout()">로그아웃하기</button>
                    <button class="btn btn-danger px-4" @click="quit()">탈퇴하기</button>
                </div>

            </div>

        </div>

        <!-- 관리자 기본정보 -->
        <div v-if="config === 'admin'" class="d-flex flex-column justify-content-center align-items-center py-5">
            <div class="bg-white rounded p-4 w-100">
                <h2 class="mb-4 text-center fw-bold">이용자들의 입금내역 확인하는 관리자 페이지입니다</h2>
                <div class="fs-2 fw-bold mb-3">
                    <span>ID :</span> <span>{{ user_info.user_id }}</span>
                </div>
                <div class="fs-2 fw-bold mb-3">
                    <span>이름 :</span> <span>{{ user_info.user_name }}</span>
                </div>
                <div class="fs-2 fw-bold mb-4">
                    <span>전화번호 :</span> <span>{{ user_info.user_mobile }}</span>
                </div>

                <div class="d-flex justify-content-center gap-3">
                    <button class="btn btn-primary px-4" @click="goToHome">돌아가기</button>
                    <button class="btn btn-info px-4" @click="logout()">로그아웃하기</button>
                </div>

            </div>

        </div>
        <!--end::기본정보-->


        <div v-if="config === 'user'" class="  w-100">
            <!-- 입장권 내역 -->
            <div v-for="(ticket, index) in ticket" :key="ticket.ticketNo">
                <div class="d-flex flex-row m-5 p-5 gap-3  ">
                    <div class="d-flex align-items-center  " style="width: 30%;">
                        <h1>귀여운 이미지</h1> <!-- 티켓같이 귀여운거 넣쟈-->
                        <img src="" />
                    </div>
                    <div class="d-flex flex-column  " style="width: 50%;">
                        <h1>{{ ticket.ticketName }}</h1>
                        <h1>{{ ticket.ticketReserveDate }}</h1>
                        <h1>{{ ticketTicketAmount }}</h1>
                    </div>
                    <div class="d-flex flex-column  " style="width: 20%;">
                        <h1>{{ ticket.ticketMoneyStatus }}</h1>
                        <button class="btn btn-info" @click="send_money(ticket.ticketNo)"
                            style="height: 30px; font-size: 8px;">입금하기</button>
                    </div>
                </div>
            </div>

            <!-- 놀이기구 예약 내역 -->
            <div class="d-flex flex-column m-5 p-5 gap-3  ">

                <!-- -->
                <div class="d-flex justify-content-center">
                    <h1>{{ user_info.user_name }}님의 놀이기구 예약 내역</h1>
                    <div v-if="no_reserve">
                        <h1>이 없습니다.</h1>
                    </div>
                </div>

                <div v-if="!no_reserve">
                    <table class="table">
                        <thead class="thead-light">
                            <tr>
                                <th>예약 번호</th>
                                <th>놀이기구 이름</th>
                                <th>예약 시간</th>
                                <th>예약 인원</th>
                                <th>취소하기</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="item in ride" :key="item.rideBookConfirmNo">
                                <td>{{ item.rideBookConfirmNo }}</td>
                                <td>{{ item.facilityName }}</td>
                                <td>{{ item.rideBookTime ?? '예약시간 없음' }}</td>
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
        </div>

        <div v-if="config === 'admin'">
            <!-- 관리자가 보는 입장권 입금확인부분 -->
            <div v-for="(ticket, index) in ticket" :key="ticket.ticketNo">
                <div class="d-flex flex-row m-5 p-5 gap-3  ">
                    <div class="d-flex align-items-center  " style="width: 30%;">
                        <h1>귀여운 이미지</h1> <!-- 티켓같이 귀여운거 넣쟈-->
                        <img src="" />
                    </div>
                    <div class="d-flex flex-column  " style="width: 50%;">
                        <h1>USER NO : {{ ticket.userNo }}</h1>
                        <h1>TICKET NAME : {{ ticket.ticketName }}</h1>
                        <h1>RESERVED DATE : {{ ticket.ticketReserveDate }}</h1>
                        <h1>TICKET AMOUNT : {{ ticket.ticketAmount }}</h1>
                    </div>
                    <div class="d-flex flex-column  " style="width: 20%;">
                        <h1>입금 상태 : {{ ticket.ticketMoneyStatus }}</h1>
                        <button class="btn btn-info" @click="send_money(ticket.ticketNo, ticket.ticketMoneyStatus)"
                            style="height: 30px; font-size: 8px;">확인하기</button>
                    </div>
                </div>
            </div>
        </div>

    </div>
</template>


<script setup>
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
        // 로그인을 해야 뭘 할수있어서 이놈만 막으면 됨
        loginStatus.value = false
        router.push('/')
    }
}

// 입장권 예매내역 함수
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

// 놀이기구예약 함수
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

// 놀이기구 예약 취소 함수
async function cancelReservation(reserve_no,facility_name) {
    console.log(typeof(reserve_no))
    try {
        const params = {
            rideBookConfirmNo : reserve_no
        }

        const response = await axios.delete('http://localhost/cancelReservation', params, {
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

// 돌아가기 버튼 함수
function goToHome() {
    router.push('/')
}

</script>

<style scoped>
.my-border {
    border: 2px dashed violet;
}
</style>
