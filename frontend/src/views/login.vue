<!-- 로그인 페이지 -->

<template>
  <link rel="shortcut icon" href="assets/media/logos/favicon.ico" />

    <div>
      <h1>아이디 입력</h1>
      <input type="text" placeholder="아이디를 입력하세요" v-model="id"></input>
      <h1>패스워드 입력</h1>
      <input type="password" placeholder="비밀번호를 입력하세요" v-model="password"></input>
      <br />
      <button type="button" class="btn btn-primary" @click="login()"> 로그인 </button>
      <div class="d-flex m-3 p-3 gap-5">
        <button class="btn btn-outline-info find-id-pw">아이디 찾기</button>
        <button class="btn btn-outline-info find-id-pw">비밀번호 찾기</button>
      </div>
    </div>



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
import { useUserInfo } from '@/stores/user'

import { useRouter } from 'vue-router'
const router = useRouter()

const userStore = useUserInfo()
const { user_info, config , loginStatus} = storeToRefs(userStore)

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

    const params = {
      "userId": id.value,
      "userPassword": password.value
    }
    const response = await axios.post('http://localhost/user/login', params, {
      headers: {
        'Content-Type': 'application/json'
      }
    });
    const userProfile = response.data[0]
    console.log(`응답 -> ${JSON.stringify(response)}`)
    console.log(`userProfile : ${userProfile.userId}`)
    console.log(`응답 -> ${JSON.stringify(response.status)}`)
    console.log(`응답 -> ${JSON.stringify(response.data)}`)
    console.log(`userID :  ${JSON.stringify(response.data.userName)}`)
    console.log(`${response.data.length}`)

    if (response.status == 400) {
      if (confirm(`일치하는 정보가 없습니다. 회원가입으로 이동하시겠습니까?`)) {
        router.push('/sign-up')
      }
    }

     if (userProfile.length != 0) {


      // isadmin이라는 컬럼의 값이 null인경우 = 일반사용자
      // isadmin이라는 컬럼의 값이 있을 경우 = 관리자
          user_info.value.user_id = userProfile.userId
          user_info.value.user_name = userProfile.userName
          user_info.value.user_birth_date = userProfile.userBirthDate
          user_info.value.user_age = userProfile.userAge
          user_info.value.user_address = userProfile.userAddress
          user_info.value.user_mobile = userProfile.userMobile
          config.value = user_info ? 'admin' : 'user'

          // test
          console.log("user_info userId : " + user_info.value.user_id)
          console.log("user_info userName : " + user_info.value.user_name)
          console.log("user_info userBirthDate : " + user_info.value.user_birth_date)
          console.log("userProfile ID : " + userProfile.user_id)
        
        // store 변수 저장 이후 메인페이지 이동
        console.log(user_info.value)
        console.log(`before loginStatus ${loginStatus.value}`)
        loginStatus.value = true
        console.log(`After loginStatus ${loginStatus.value}`)

        router.push('/')
        
      } else {
        alert(`일치하는 정보가 없습니다.`)
      return
    }

  } catch (err) {
    console.log(`login함수 실행중 에러발생 -> ${err}`)
  }
}

function signup() {
  router.push('sign-up')
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