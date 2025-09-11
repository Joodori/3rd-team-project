<!-- 회원 마이페이지 -->

<template>
  <!--
    <div class="d-flex flex-column justify-content-center align-items-center py-5">
      <div class="bg-white rounded p-4 w-80">
        <h2 class="mb-4 text-center fw-bold">로그인 유저의 정보</h2>
            <div class="fs-2 fw-bold mb-3">
              <span>ID :</span> <span>{{ customer.user_id }}</span>
            </div>
            <div class="fs-2 fw-bold mb-3">
                <span>이름 :</span> <span>{{ customer.user_name }}</span>
              </div>
              <div class="fs-2 fw-bold mb-3">
                <span>나이 :</span> <span>{{ customer.user_age }}</span>
              </div>
              <div class="fs-2 fw-bold mb-4">
                <span>전화번호 :</span> <span>{{ customer.user_mobile }}</span>
              </div>
              
              <div class="d-flex justify-content-center gap-3">
                <button class="btn btn-primary px-4" @click="goToHome">돌아가기</button>
                <button class="btn btn-danger px-4" @click="goToModifyInfo()">수정하기</button>
                <button class="btn btn-info px-4" @click="logout()">로그아웃하기</button>
              </div>
            </div>
          </div>
          -->
          
    <div v-if="user" class="d-flex flex-column justify-content-center align-items-center py-5">
        <div class="bg-white rounded p-4 w-100">
            <h2 class="mb-4 text-center fw-bold">로그인 유저의 정보</h2>
            <div class="fs-2 fw-bold mb-3">
                <span>ID :</span> <span>유저</span>
            </div>
            <div class="fs-2 fw-bold mb-3">
                <span>이름 :</span> <span>유저</span>
            </div>
            <div class="fs-2 fw-bold mb-3">
                <span>나이 :</span> <span>유저</span>
            </div>
            <div class="fs-2 fw-bold mb-4">
                <span>전화번호 :</span> <span>유저</span>
            </div>

            <div class="d-flex justify-content-center gap-3">
                <button class="btn btn-primary px-4" @click="goToHome">돌아가기</button>
                <button class="btn btn-danger px-4" @click="goToModifyInfo()">수정하기</button>
                <button class="btn btn-info px-4" @click="logout()">로그아웃하기</button>
            </div>
        </div>
    </div>
    <div v-if="!user" class="d-flex flex-column justify-content-center align-items-center py-5">
        <div class="bg-white rounded p-4 w-100">
            <h2 class="mb-4 text-center fw-bold">로그인 유저의 정보</h2>
            <div class="fs-2 fw-bold mb-3">
                <span>ID :</span> <span>관리자</span>
            </div>
            <div class="fs-2 fw-bold mb-3">
                <span>이름 :</span> <span>관리자</span>
            </div>
            <div class="fs-2 fw-bold mb-3">
                <span>나이 :</span> <span>관리자</span>
            </div>
            <div class="fs-2 fw-bold mb-4">
                <span>전화번호 :</span> <span>관리자</span>
            </div>

            <div class="d-flex justify-content-center gap-3">
                <button class="btn btn-primary px-4" @click="goToHome">돌아가기</button>
                <button class="btn btn-danger px-4" @click="goToModifyInfo()">수정하기</button>
                <button class="btn btn-info px-4" @click="logout()">로그아웃하기</button>
            </div>
        </div>
    </div>

</template>


<script setup>
import { storeToRefs } from 'pinia';
import {useUserInfo} from '@/stores/user'
import { ref, onMounted } from 'vue'

const userStore = useUserInfo()
const {user} = storeToRefs(useUserInfo)

onMounted(()=> {
  console.log(`user_info 호출됨`)
  configUserInfo()
})

async function configUserInfo() {
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
customer_item.value = response.data.data.data
//==============================================================
if (response.data.data.data.length === 0) {
            user.value = false;
        }

    } catch (err) {
        console.error(`물품목록::에러발생 -> ${err}`)
    }
}

</script>
