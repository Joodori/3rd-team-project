<template>
  <div class="d-flex justify-content-center">
    <div class="d-flex flex-column justify-content-center align-items-center p-4 my-5"
      style="width: 400px; background: #fff; border-radius: 12px;">

      <h2 class="mb-4 fw-bold" style="text-align: center;">회원가입</h2>

      <!-- 아이디 입력 -->
      <div class="d-flex flex-column align-self-start mb-3 w-100">
        <label class="form-label fw-bold">아이디 입력</label>
        <div class="d-flex gap-2">
          <input type="text" placeholder="아이디를 입력하세요" v-model="id" class="form-control" :disabled="!check" />
          <div class="d-flex align-self-end">
            <button v-if="!checked" class="btn btn-light-danger" style="width:120px" @click="searchSameID">중복확인</button>
          </div>
        </div>
      </div>

      <!-- 비밀번호 입력 -->
      <div class="d-flex flex-column align-self-start mb-3 w-100">
        <label class="form-label fw-bold">비밀번호 입력</label>
        <input type="password" placeholder="비밀번호를 입력하세요" v-model="password" class="form-control" />
      </div>

      <!-- 비밀번호 확인 -->
      <div class="d-flex flex-column align-self-start mb-3 w-100">
        <label class="form-label fw-bold">비밀번호 확인</label>
        <input type="password" placeholder="비밀번호 재확인" v-model="password_re" ref="passwordCheck" class="form-control" />
      </div>

      <!-- 이름 입력 -->
      <div class="d-flex flex-column align-self-start mb-3 w-100">
        <label class="form-label fw-bold">이름</label>
        <input type="text" placeholder="사용하실 이름을 입력하세요" v-model="user_name" class="form-control" />
      </div>

      <!-- 생일 입력 -->
      <div class="d-flex flex-column align-self-start mb-3 w-100">
        <label class="form-label fw-bold">생년월일</label>
        <input type="date" v-model="user_birth_date" class="form-control"></input>
      </div>

      <!-- 나이 입력 -->
      <div class="d-flex flex-column align-self-start mb-3 w-100">
        <label class="form-label fw-bold">나이</label>
        <input type="text" placeholder="나이를 입력하세요" v-model="user_age" ref="ageInput" class="form-control" />
      </div>

      <!-- 이메일주소 입력 -->
      <div class="d-flex flex-column align-self-start mb-3 w-100">
        <label class="form-label fw-bold">이메일주소</label>
        <input type="email" placeholder="이메일주소를 입력해주세요" v-model="user_address" class="form-control" />
      </div>

      <!-- 핸드폰번호 입력 -->
      <div class="d-flex flex-column align-self-start mb-4 w-100">
        <label class="form-label fw-bold">핸드폰번호</label>
        <input type="tel" placeholder="전화번호를 입력하세요" v-model="user_mobile" class="form-control" />
      </div>

      <!-- 버튼들 -->
      <div class="d-flex justify-content-between w-100">
        <button class="btn btn-secondary fw-bold px-4" @click="goToMain">취소</button>
        <button class="btn btn-primary fw-bold px-4" @click="signup">회원가입</button>
      </div>


    </div>
  </div>

</template>

<script setup>

// 입력하지 않은 부분이 있다면 체크하기위해서 ref를 불러옴
import { ref } from 'vue'

// 중복확인 버튼 눌렀을 때 v-if를 사용하기위해 ref로 감시
const checked = ref(false);

// 취소, 회원가입시에 이동을 위한 useRouter
import { useRouter } from 'vue-router'
const router = useRouter();

// SQL왔다리갔다리 axios
import axios from 'axios'

const id = ref('')
const password = ref('')
const password_re = ref('')
const user_name = ref('')
const user_birth_date = ref('')
const user_age = ref('')
const user_address = ref('')
const user_mobile = ref('')
const ageInput = ref(null)
const passwordCheck = ref(null)
const check = ref(true)

function goToMain() {
  router.push('/')
}

function goToLogin() {
  router.push('/login')
}

// ============회원가입 클릭시===============
async function signup() {
  console.log(`signup 호출됨`)

  // =======================================
  // 회원가입 시 특정조건을 만족했는지 확인
  if (checked.value != true) {
    alert(`아이디 중복확인을 진행해주세요`)
    return
  }

  if (password.value != password_re.value) {
    alert(`비밀번호가 일치하지 않습니다. 다시 입력해주세요`)
    password_re.value = '';
    passwordCheck.value.focus()
    return
  }

  const ageNum = Number(user_age.value)
  if (isNaN(ageNum)) {
    alert(`나이 칸에는 숫자를 입력해주세요`)
    user_age.value = ''
    ageInput.value.focus()
    return
  }
  // 확인 끝
  // ==========================================

  // ==========================================
  // 제대로 입력이 되었으면 그 부분에서 쿼리문 출발
  if (id.value && password.value && user_name.value && user_age.value && user_mobile.value) {
    console.log(user_birth_date.value)
    console.log(`입력값이 모두 입력됨`);

    try {
      const params = {
        "userId" : id.value,
        "userPassword" : password.value,
        "userName": user_name.value,
        "userBirthDate": user_birth_date.value,
        "userAge": user_age.value,
        "userAddress": user_address.value,
        "userMobile": user_mobile.value
      }
    const response = await axios.post('http://localhost/user/insert', params, {
      headers: {
        'Content-Type': 'application/json' 
      }
    });

      if (response.data.length == 0) {
        alert(`회원가입 중 오류가 발생했습니다.`)
      }

      alert(`회원가입이 완료되었습니다. 로그인화면으로 이동합니다.`)

      goToLogin();

    } catch (err) {
      console.error(`error -> ${err}`);
    }
  }

  // 쿼리문 끝남 로그인창으로 이동됨
  // ==========================================


  // ==========================================
  // 만약 뭔가를 빼먹었다면 alert사용
  else {
    console.log(`id : ${id.value}, pw : ${password.value}, name : ${user_name.value}, age : ${user_age.value}`)

    alert('입력해야하는 칸을 모두 입력해주세요!');

  }
}


// ==========================================
// 아이디 중복확인버튼 함수
async function searchSameID() {

  // 아이디값이 없다면 아이디 입력하라고 시킴
  if (!id.value) {
    alert(`아이디를 입력해주세요`)
    return
  }

  // 정상적으로 입력했다면 함수 시작
  // + 쿼리문 출발
  console.log(`searchSameID 호출됨`)
  try {

    console.log(`id = ${id.value}`)
    const params = {
      "userId": id.value,
    }
    const response = await axios.post('http://localhost/user/check-id', params, {
      headers: {
        'Content-Type': 'application/json'
      }
    });
    console.log(`응답 -> ${JSON.stringify(response.data)}`)
    // ==========================================
    // 이 부분이 아이디를 DB에서 검색했을 때 만족하는 아이디가 없으므로 사용가능한 아이디
    // checked.value를 true로 만들어서 버튼이 사라지게 만들었음
    if (response.data.length == 0) {
      alert(`사용가능한 아이디입니다.`)
      checked.value = true
      check.value = false
    }
    // ==========================================
    // 만약 쿼리문에서 응답(res.d.d.d 에 만족하는 값)이 있다면 id값을 없애고 다시 보냄)
    // checked.value가 바뀌지 않아서 아이디를 바꾸고 다시 클릭해야함
    else {
      alert(`이미 사용중인 아이디입니다.`)
      id.value = ''
      return
    }


  } catch (err) {
    console.log(`에러발생 -> ${err}`)
  }
}
</script>