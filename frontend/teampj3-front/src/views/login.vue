<!-- 로그인 페이지 -->

<template>
  <link rel="shortcut icon" href="assets/media/logos/favicon.ico" />
  <form>
    <div>
      <h1>아이디 입력</h1>
      <input type="text" placeholder="아이디를 입력하세요" v-model="id"></input>
      <h1>패스워드 입력</h1>
      <input type="password" placeholder="비밀번호를 입력하세요" v-model="password"></input>
      <br />
      <button type="submit" class="btn btn-primary" @click="login()"> 로그인 </button>
      <div class="d-flex m-3 p-3 gap-5">
        <button class="btn btn-outline-info find-id-pw">아이디 찾기</button>
        <button class="btn btn-outline-info find-id-pw">비밀번호 찾기</button>
      </div>
    </div>

  </form>

  <!-- Sign in with 구글, 애플, 인스타 -->
  <div class="sns-wrapper ">
    <div class="col-md-6">
      <a href="#"
        class="btn btn-flex btn-outline btn-text-gray-700 btn-active-color-primary bg-state-light flex-center text-nowrap w-100">
        <img alt="Logo" src="/assets/media/svg/brand-logos/google-icon.svg" class="h-15px me-3" />Sign in with
        Google</a>
    </div>
    <div class="col-md-6">
      <a href="#"
        class="btn btn-flex btn-outline btn-text-gray-700 btn-active-color-primary bg-state-light flex-center text-nowrap w-100">
        <img alt="Logo" src="/assets/media/svg/brand-logos/apple-black.svg" class="theme-light-show h-15px me-3" />
        <img alt="Logo" src="/assets/media/svg/brand-logos/apple-black-dark.svg"
          class="theme-dark-show h-15px me-3" />Sign in with Apple</a>
    </div>
    <div class="col-md-6">
      <a href="#"
        class="btn btn-flex btn-outline btn-text-gray-700 btn-active-color-primary bg-state-light flex-center text-nowrap w-100">
        <img alt="Logo" src="/assets/media/svg/brand-logos/instagram-2016.svg" class="h-15px me-3" />Sign in with
        Instagram</a>
    </div>
  </div>


  <div>
    <button class="btn btn-primary" @click="signup()">회원가입</button>
  </div>

  <!-- sign in with google, facebook, istagram-->

</template>


<script setup>

import { ref } from 'vue'
import axios from 'axios'

const id = ref('')
const password = ref('')

async function login() {

  // 아이디와 비밀번호 입력 안된 부분이 있을 떄 alert => return
  if (!id.value || !password.value) {
    alert(`아이디와 비밀번호를 모두 입력해주세요`)
    return
  }

  try {

    const params = {
      id : id.value,
      password : password.value
    }

    const response = await axios({
      method: 'post',
      // id, password로 user테이블에서 select하는 쿼리문 들어있는 url
      //=========================================================================        
      baseURL: 'http://localhost:8001',
      url: 'market/v1/signup',
      //========================================================================= 
      data: params,
      timeout: 5000,
      responseType: 'json'
    })

    console.log(`응답 -> ${JSON.stringify(response.data)}`)

    if (response.data.code == 200 ) {
      /// 이 부분부터 수정해야함
    }

  } catch (err) {
    console.log(`login함수 실행중 에러발생 -> err`)
  }
}

</script>


<style scoped>
.find-id-pw {
  color: #000000 !important;
  background-color: #ffffff;
  border-color: #ffffff !important;
  box-shadow: none !important;
}
</style>