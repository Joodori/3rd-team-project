<!-- 로그인 페이지 -->

<template>
  <link rel="shortcut icon" href="assets/media/logos/favicon.ico" />
  <form>
    <div>
      <h1>아이디 입력</h1>
      <input type="text" placeholder="아이디를 입력하세요"></input>
      <h1>패스워드 입력</h1>
      <input type="password" placeholder="비밀번호를 입력하세요"></input>
      <br />
      <button type="submit" class="btn btn-primary"> 로그인 </button>
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
       <img alt="Logo" src="/assets/media/svg/brand-logos/google-icon.svg" class="h-15px me-3" />Sign in with Google</a>
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
      <img alt="Logo" src="/assets/media/svg/brand-logos/instagram-2016.svg" class="h-15px me-3" />Sign in with Instagram</a>
    </div>
  </div>
    

  <div>
    <button class="btn btn-primary" @click="signup()">회원가입</button>
  </div>

  <!-- sign in with google, facebook, istagram-->

</template>


<script setup>
</script>

<style scoped>

.find-id-pw {
  color : #000000 !important;
  background-color: #ffffff;
  border-color : #ffffff  !important; 
  box-shadow: none !important;
}

</style>