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
      <button type="submit"> asdasdsad</button>
      <button type="button" class="btn btn-primary" @click="login()"> 로그인 </button>
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

// store변수에 user정보를 집어넣고 이것을 마이페이지에서 가져오기위함
import { storeToRefs } from 'pinia'
import {useUserInfo} from '@/stores/user'

import { useRouter } from 'vue-router'
const router = useRouter()

const userStore = useUserInfo()
const { user_info } = storeToRefs(userStore)

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
/**
 * 
 const params = {
  userId : id.value,
  userPassword : password.value
}

const response = await axios({
      method: 'post',
      // id, password로 user테이블에서 select하는 쿼리문 들어있는 url
      //=========================================================================        
      baseURL: 'http://localhost',
      url: 'user/login',
      //========================================================================= 
      data: params,
      timeout: 5000,
      responseType: 'json'
    })
    
    console.log(`응답 -> ${JSON.stringify(response.data)}`)
    */

    const params = new URLSearchParams();
    params.append('userId', id.value);
    params.append('userPassword', password.value);

    const response = await axios.post('http://localhost/user/login', params, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    });
    console.log(`응답 -> ${JSON.stringify(response.data)}`)

    if (response.data.length === 0 ) {
      if(confirm(`일치하는 정보가 없습니다. 회원가입으로 이동하시겠습니까?`)) {
        router.push('/sign-up')
      }
    }

    /**
     * 
     if (response.data.status == "success") {
      // isadmin이라는 컬럼의 값이 null인경우 = 일반사용자
      // isadmin이라는 컬럼의 값이 있을 경우 = 관리자
      const userProfile = response.data.data.data[0]
      if (userProfile.isadmin === null ) {
        // select로 받은 데이터는 아이디 중복방지로 인해 1개만 추출되기 때문에
        // 제일 첫번째의 데이터를 store변수인 user_info에 추가
        // 비밀번호는 변수에 추가하지 않음
          user_info.value.user_id = userProfile.user_id
          user_info.value.user_name = userProfile.user_name
          user_info.value.user_birth_date = userProfile.user_birth_date
          user_info.value.user_age = userProfile.user_age
          user_info.value.user_address = userProfile.user_address
          user_info.value.user_mobile = userProfile.user_mobile
          config.value = "user"
        }
        
        if (userProfile.isadmin !== null ) {
          user_info.value.user_id = userProfile.user_id
          user_info.value.user_name = userProfile.user_name
          user_info.value.user_birth_date = userProfile.user_birth_date
          user_info.value.user_age = userProfile.user_age
          user_info.value.user_address = userProfile.user_address
          user_info.value.user_mobile = userProfile.user_mobile
          config.value = "admin"
        }
        
        // store 변수 저장 이후 메인페이지 이동
        router.push('/')
        
      } else {
        alert(`일치하는 정보가 없습니다.`)
      return
    }
    */
    
  } catch (err) {
    console.log(`login함수 실행중 에러발생 -> ${err}`)
  }
}

</script>

<style scoped>
  /** 아래 부분은 Test용
   */
.find-id-pw {
  color: #000000 !important;
  background-color: #ffffff;
  border-color: #ffffff !important;
  box-shadow: none !important;
}
</style>