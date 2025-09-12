<!-- 회원 마이페이지 -->

<template>
    <div style="width: 500px;">

        <!--begin::기본정보-->
        <!-- v-if="user === true" 일때는 일반 유저일때 정보 -->
        <div v-if="user === true" class="d-flex flex-column justify-content-center align-items-center py-5  ">
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
                    <span>나이 :</span> <span>{{ user_info.user_age }}</span>
                </div>
                <div class="fs-2 fw-bold mb-3">
                    <span>주소 :</span> <span>{{ user_info.user_address }}</span>
                </div>
                <div class="fs-2 fw-bold mb-4">
                    <span>전화번호 :</span> <span>{{ user_info.user_mobile }}</span>
                </div>

                <div class="d-flex justify-content-center gap-3">
                    <button class="btn btn-primary px-4" @click="goToHome">돌아가기</button>
                    <button class="btn btn-danger px-4" @click="goToModifyInfo()">수정하기</button>
                    <button class="btn btn-info px-4" @click="logout()">로그아웃하기</button>
                </div>

            </div>

        </div>

        <div v-if="user === false" class="d-flex flex-column justify-content-center align-items-center py-5">
            <div class="bg-white rounded p-4 w-100">
                <h2 class="mb-4 text-center fw-bold">로그인 유저의 정보</h2>
                <div class="fs-2 fw-bold mb-3">
                    <span>ID :</span> <span>{{ user_info.user_id }}</span>
                </div>
                <div class="fs-2 fw-bold mb-3">
                    <span>이름 :</span> <span>{{ user_info.user_name }}</span>
                </div>
                <div class="fs-2 fw-bold mb-3">
                    <span>나이 :</span> <span>{{ user_info.user_age }}</span>
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


        <div v-if="user === true" class="  w-100">
            <!-- 입장권 내역 -->
            <div class="d-flex flex-row m-5 p-5 gap-3  ">
                <div class="d-flex align-items-center  " style="width: 30%;">
                    <h1>귀여운 이미지</h1> <!-- 티켓같이 귀여운거 넣쟈-->
                    <img src="" />
                </div>
                <div class="d-flex flex-column  " style="width: 50%;">
                    <h1>{{ ticket.ticket_name }}</h1>
                    <h1>{{ ticket.ticket_reserve_date }}</h1>
                    <h1>{{ ticket_ticket_amount }}</h1>
                </div>
                <div class="d-flex flex-column  " style="width: 20%;">
                    <h1>{{ ticket.ticket_status }}</h1>
                    <button class="btn btn-info" @click="send_money()"
                        style="height: 30px; font-size: 8px;">입금하기</button>
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
                    <div class="d-flex flex-row justify-content-around  ">
                        <tr>
                            <th>놀이기구 이름</th>
                            <th>놀이기구 예약시간</th>
                            <th>놀이기구 예약인원</th>
                            <th>취소하기</th>
                        </tr>
                    </div>

                    <div v-for="(item, index) in ride" :key="ride.ride_book_confirm_no">
                        <tr>
                            <td>{{ item.ride_book_confirm_no }}</td> <!-- 이건 그냥 깡통 auto-increament-->
                            <td>{{ item.facility_name }}</td>
                            <td>{{ item.ride_book_info_time }}</td>
                            <td>{{ item.ride_book_person_amount }}</td>
                        </tr>
                    </div>
                </div>
            </div>
        </div>

        <div v-if="user === false">
            <!-- 관리자가 보는 입장권 입금확인부분 -->
            <div>

            </div>
        </div>

    </div>
</template>


<script setup>
import { storeToRefs } from 'pinia';
import { useUserInfo } from '@/stores/user'
import { ref, onMounted } from 'vue'
import { useRouter} from 'vue-router'
const router = useRouter()

const userStore = useUserInfo()
const { user, user_info, ticket, ride } = storeToRefs(userStore)

const no_reserve = ref(true)

onMounted(() => {
    console.log(`user_info 호출됨`)
    configUserInfo()
})

async function configUserInfo() {

    if (true) {

    }

    try {

        const response = await axios({
            method: 'post',
            //==============================================================
            baseURL: 'http://localhost:8001',
            url: '/market/v1/read-item',
            data: {
                uploader_name: customer.value.user_id
                //==============================================================
            },
            timeout: 5000,
            responseType: 'json'
        })

        console.log(`응답 -> ${JSON.stringify(response.data)}`)
        //==============================================================
        const rideList = response.data.data.data
        userStore.ride = rideList
        //==============================================================
        if (response.data.data.data.length === 0) {
            user.value = false;
        }

    } catch (err) {
        console.error(`물품목록::에러발생 -> ${err}`)
    }
}

function send_money() {
    console.log(`send_money 호출됨`)
    if (confirm(`XX은행 1002-757-04-8585 에 입금하셨습니까?`)) {
        ticket.ticket_status = "입금완료"
    }

}

function goToHome() {
    router.push('/')
}

</script>

<style scoped>
.my-border {
    border: 2px dashed violet;
}
</style>
