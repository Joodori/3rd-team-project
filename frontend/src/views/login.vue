<!-- LoginPage.vue -->
<template>
  <!-- favicon (필요 없다면 삭제) -->
  <link rel="shortcut icon" href="assets/media/logos/favicon.ico" />

  <!-- 전체 래퍼 -->
  <div class="login-page theme-park-home-v2 d-flex flex-column align-items-center justify-content-center py-5">

    <!-- 로그인 카드 -->
    <div class="auth-card bg-white rounded-4 shadow-lg p-5 w-100" style="max-width:440px;">
      <h2 class="text-center fw-bold mb-4">🎢 매직랜드 로그인</h2>

      <!-- 아이디 입력 -->
      <div class="mb-3">
        <label class="form-label fw-semibold">아이디</label>
        <input type="text"
               class="form-control form-control-lg"
               placeholder="아이디를 입력하세요"
               v-model="id">
      </div>

      <!-- 비밀번호 입력 -->
      <div class="mb-4">
        <label class="form-label fw-semibold">비밀번호</label>
        <input type="password"
               class="form-control form-control-lg"
               placeholder="비밀번호를 입력하세요"
               v-model="password">
      </div>

      <!-- 기본 액션 -->
      <div class="d-flex flex-column gap-3">
        <button type="button"
                class="btn btn-primary btn-lg w-100"
                @click="login()">
          로그인
        </button>

        <button class="btn btn-outline-primary btn-lg w-100"
                @click="signup()">
          회원가입
        </button>
      </div>

      <!-- 부가 링크 -->
      <div class="d-flex justify-content-between mt-4">
        <button class="btn btn-link p-0 text-decoration-none text-primary-emphasis"
                @click="showDialogId()">
          아이디 찾기
        </button>
        <button class="btn btn-link p-0 text-decoration-none text-primary-emphasis"
                @click="showDialogPw()">
          비밀번호 찾기
        </button>
      </div>

      <!-- SNS 로그인 -->
      <div class="sns-wrapper d-flex flex-column gap-3 mt-5">
        <a href="#"
           class="btn btn-flex btn-outline btn-light-secondary w-100 d-flex align-items-center justify-content-center gap-2">
          <img src="/assets/media/svg/brand-logos/google-icon.svg" class="h-20px" alt="G">
          <span class="fw-semibold">Sign in with Google</span>
        </a>

        <a href="#"
           class="btn btn-flex btn-outline btn-light-secondary w-100 d-flex align-items-center justify-content-center gap-2">
          <img src="/assets/media/svg/brand-logos/apple-black.svg" class="h-20px theme-light-show" alt="A">
          <img src="/assets/media/svg/brand-logos/apple-black-dark.svg" class="h-20px theme-dark-show" alt="A">
          <span class="fw-semibold">Sign in with Apple</span>
        </a>

        <a href="#"
           class="btn btn-flex btn-outline btn-light-secondary w-100 d-flex align-items-center justify-content-center gap-2">
          <img src="/assets/media/svg/brand-logos/instagram-2016.svg" class="h-20px" alt="I">
          <span class="fw-semibold">Sign in with Instagram</span>
        </a>
      </div>

      <!-- 홈으로 -->
      <button class="btn btn-light-danger fw-bold w-100 mt-4"
              style="height:48px;"
              @click="goToHome()">
        홈으로
      </button>
    </div>
  </div>

  <!-- ===== begin::아이디 찾기 대화상자 ===== -->
  <div class="modal fade" id="myDialogId">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content rounded-3">
        <div class="modal-header border-0">
          <h3 class="modal-title fw-bold">아이디 찾기</h3>
          <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
        </div>
        <div class="modal-body p-4">
          <div class="mb-3">
            <label class="form-label">이름</label>
            <input type="text" class="form-control" placeholder="이름을 입력하세요" v-model="findIdByName">
          </div>
          <div class="mb-3">
            <label class="form-label">이메일</label>
            <input type="text" class="form-control" placeholder="이메일을 입력하세요" v-model="findIdByEmail">
          </div>
          <button class="btn btn-outline-info w-100" @click="findId()">아이디 찾기</button>
          <p class="mt-3">당신의 ID는 : <span class="fw-semibold">{{ findUserId }}</span></p>
        </div>
      </div>
    </div>
  </div>
  <!-- ===== end::아이디 찾기 대화상자 ===== -->

  <!-- ===== begin::비밀번호 찾기 대화상자 ===== -->
  <div class="modal fade" id="myDialogPw">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content rounded-3">
        <div class="modal-header border-0">
          <h3 class="modal-title fw-bold">비밀번호 찾기</h3>
          <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
        </div>
        <div class="modal-body p-4">
          <div class="mb-3">
            <label class="form-label">아이디</label>
            <input type="text" class="form-control" placeholder="아이디를 입력하세요" v-model="findPwById">
          </div>
          <div class="mb-3">
            <label class="form-label">이름</label>
            <input type="text" class="form-control" placeholder="이름을 입력하세요" v-model="findPwByName">
          </div>
          <div class="mb-3">
            <label class="form-label">이메일</label>
            <input type="text" class="form-control" placeholder="이메일을 입력하세요" v-model="findPwByEmail">
          </div>
          <button class="btn btn-outline-info w-100" @click="findPw()">비밀번호 찾기</button>
          <p class="mt-3">당신의 PassWord는 : <span class="fw-semibold">{{ findUserPw }}</span></p>
        </div>
      </div>
    </div>
  </div>
  <!-- ===== end::비밀번호 찾기 대화상자 ===== -->
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
     console.log(`응답 -> ${JSON.stringify(response.data)}`)

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


 function goToHome() {
   router.push('/')
 }
</script>

<style scoped>
/* 테마 전체 그라데이션은 theme-park-home-v2 클래스에 이미 정의 */

/* 카드 내 제목 */
.auth-card h2 {
  color:#2d3748;
}

/* 인풋 포커스 효과 */
.form-control:focus {
  border-color:#ed8936;
  box-shadow:0 0 0 0.25rem rgba(237,137,54,.25);
}
</style>
