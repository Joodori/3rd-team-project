<!-- 로그인 페이지 -->

<template>
  <link rel="shortcut icon" href="assets/media/logos/favicon.ico" />

  <div>
    <h1>아이디 입력</h1>
    <input type="text" placeholder="아이디를 입력하세요" v-model="id"></input>
    <h1>패스워드 입력</h1>
    <input type="password" placeholder="비밀번호를 입력하세요" v-model="password"></input>
    <br />
    <div class="d-flex gap-3">
      <div class="d-flex gap-3">
        <button type="button" class="btn btn-primary" @click="login()"> 로그인 </button>
        <button class="btn btn-primary" @click="signup()">회원가입</button>
      </div>
      <div class="d-flex">
        <button class="btn btn-outline-info" @click="showDialogId()">아이디 찾기</button>
        <button class="btn btn-outline-info" @click="showDialogPw()">비밀번호 찾기</button>
      </div>
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

  <!-- ===== begin::아이디 찾기 대화상자 ===== -->
  <div class="modal fade" id="myDialogId">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content rounded">
        <div class="modal-header">
          <span class="fs-1 fw-bold">아이디 찾기</span>
        </div>
        <div class="modal-body p-10">
          <div>
            <label >이름 : </label>
            <input type="text" class="form-control-solid" placeholder="이름을 입력하세요" v-model="findIdByName">
          </div>
          <div>
            <label>이메일 : </label>
            <input type="text" class="form-control-solid" placeholder="이메일을 입력하세요" v-model="findIdByEmail">
          </div>        
          <button class="btn btn-outline-info" @click="findId()">아이디 찾기</button>
          <div>
            <span>당신의 ID는 :</span> <span v-if="findUserId">{{ findUserId }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- ===== end::아이디 찾기 대화상자 ===== -->
  <!-- ===== begin::비밀번호 찾기 대화상자 ===== -->
  <div class="modal fade" id="myDialogPw">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content rounded">
        <div class="modal-header">
          <span class="fs-1 fw-bold">비밀번호 찾기</span>
        </div>
        <div class="modal-body p-10">
          <div>
            <label>아이디 : </label>
            <input type="text" class="form-control-solid" placeholder="아이디를 입력하세요" v-model="findPwById">
          </div>
          <div>
            <label>이름 : </label>
            <input type="text" class="form-control-solid" placeholder="이름을 입력하세요" v-model="findPwByName">
          </div>
          <div>
            <label>이메일 : </label>
            <input type="text" class="form-control-solid" placeholder="이메일을 입력하세요" v-model="findPwByEmail">
          </div>
          <button class="btn btn-outline-info" @click="findPw()">비밀번호 찾기</button>
          <div>
            <span>당신의 PassWord는 :</span> <span v-if="findUserPw">{{ findUserPw }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- ===== end::비밀번호 찾기 대화상자 ===== -->



  <!-- sign in with google, facebook, istagram-->

</template>


<script setup>



// store변수에 user정보를 집어넣고 이것을 마이페이지에서 가져오기위함
import { storeToRefs } from 'pinia'
import { useUserInfo } from '@/stores/user'

import { useRouter } from 'vue-router'
const router = useRouter()

const userStore = useUserInfo()
const { user_info, config, loginStatus } = storeToRefs(userStore)

import { ref, onMounted } from 'vue'
import axios from 'axios'


const id = ref('')
const password = ref('')

import { Modal } from 'bootstrap' //모달 부트스트랩 import
let myDialogId; // 대화상자 객체를 담아둘 변수 - 아이디 찾기
let myDialogPw; // 대화상자 객체를 담아둘 변수 - 비밀번호 찾기
const findIdByName = ref('')
const findIdByEmail = ref('')
const findPwById = ref('')
const findPwByName = ref('')
const findPwByEmail = ref('')
const findUserId = ref('')
const findUserPw = ref('') 

// // 대화상자 안 - 아이디, 비번
// onMounted(() => {
//     console.log(findUserId)
// })

async function login() {


  // 아이디와 비밀번호 입력 안된 부분이 있을 떄 alert => return
  if (!id.value || !password.value) {
    alert(`아이디와 비밀번호를 모두 입력해주세요`)
    return
  }

  try {
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
    console.log(response.data.length)
    console.log(`응답 -> ${JSON.stringify(response)}`)
    if (response.data.length === 0) {
      if (confirm(`일치하는 정보가 없습니다. 회원가입으로 이동하시겠습니까?`)) {
        router.push('/sign-up')
      }
    }
    console.log(`userProfile : ${userProfile.userId}`)
    console.log(`응답 -> ${JSON.stringify(response.status)}`)
    console.log(`응답 -> ${JSON.stringify(response.data)}`)
    console.log(`userID :  ${JSON.stringify(response.data.userName)}`)


    if (userProfile == []) {
      console.log(`없음`)
    }


    if (userProfile.length != 0) {


      // isadmin이라는 컬럼의 값이 null인경우 = 일반사용자
      // isadmin이라는 컬럼의 값이 있을 경우 = 관리자
      user_info.value.user_no = userProfile.userNo
      user_info.value.user_id = userProfile.userId
      user_info.value.user_name = userProfile.userName
      user_info.value.user_birth_date = userProfile.userBirthDate
      user_info.value.user_age = userProfile.userAge
      user_info.value.user_address = userProfile.userAddress
      user_info.value.user_mobile = userProfile.userMobile
      config.value = user_info.value.user_id == "admin" ? 'admin' : 'user'
      console.log(`!!!! config value : ${config.value}`)
      // test
      console.log("user_info userNO : " + user_info.value.user_no)
      console.log("user_info userId : " + user_info.value.user_id)
      console.log("user_info userName : " + user_info.value.user_name)
      console.log("user_info userBirthDate : " + user_info.value.user_birth_date)

      // store 변수 저장 이후 메인페이지 이동
      console.log(user_info.value)
      console.log(`before loginStatus : ${loginStatus.value}`)
      loginStatus.value = true
      console.log(`After loginStatus : ${loginStatus.value}`)

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

// 대화상자 띄우기 - 아이디 찾기 눌렀을 때 실행
function showDialogId() {
  findIdByName.value = ''
  findIdByEmail.value = ''
  findUserId.value = ''
  const elem = document.querySelector('#myDialogId')
  myDialogId = new Modal(elem)
  myDialogId.show()
}

// 대화상자 띄우기 - 비밀번호 찾기 눌렀을 때 실행
function showDialogPw() {
  findPwById.value = ''
  findPwByName.value = ''
  findPwByEmail.value = ''
  findUserPw.value = ''
  const elem = document.querySelector('#myDialogPw')
  myDialogPw = new Modal(elem)
  myDialogPw.show()
}

// 아이디 찾기 버튼 눌렀을 때 실행
async function findId() {
  if (!findIdByName.value || !findIdByEmail.value) {
    alert(`아이디, 비밀번호 모두 입력해라`)
    return
  }
  try {
    const params = {
      "userName": findIdByName.value,
      "userAddress": findIdByEmail.value
    }
    const response = await axios.post('http://localhost/user/find-id', params, {
      headers: {
        'Content-Type': 'application/json'
      }
    });
    findUserId.value = response.data
    console.log(`응답 -> ${JSON.stringify(response)}`)
    console.log(`응답 -> ${JSON.stringify(response.status)}`)
    console.log(`응답 -> ${JSON.stringify(response.data)}`)
    if (response.data == "") {
      alert(`일치하는 정보가 없습니다. 다시 입력하세요.`)
    }
  } catch (err) {
    console.log(`findId함수 실행중 에러발생 -> ${err}`)
  }
}

// 비밀번호 찾기 버튼 눌렀을 때 실행
async function findPw() {
  if (!findPwByName.value || !findPwById.value || !findPwByEmail.value) {
    alert(`이름, 아이디, 이메일 모두 입력해라`)
    return
  }
  try {
    const params = {
      "userName": findPwByName.value,
      "userId": findPwById.value,
      "userAddress": findPwByEmail.value
    }
    const response = await axios.post('http://localhost/user/find-pw', params, {
      headers: {
        'Content-Type': 'application/json'
      }
    });
    findUserPw.value = response.data
    console.log(`응답 -> ${JSON.stringify(response)}`)
    console.log(`응답 -> ${JSON.stringify(response.status)}`)
    console.log(`응답 -> ${JSON.stringify(response.data)}`)
    if (response.data == "") {
      alert(`일치하는 정보가 없습니다. 다시 입력하세요.`)
    }
  } catch (err) {
    console.log(`findPw함수 실행중 에러발생 -> ${err}`)
  }
}

</script>

<style scoped>

</style>