<!-- 로그인 페이지 -->

<template>
  <form>
    <div>
      <h1>아이디 입력</h1>
      <input type="text" placeholder="아이디를 입력하세요"></input>
      <h1>패스워드 입력</h1>
      <input type="password" placeholder="비밀번호를 입력하세요"></input>
      <br/>
      <button type="submit" class="btn btn-primary"> 로그인 </button>
    </div>
  </form>
    
  <div>
    <button class="btn btn-primary" @click="signup()">회원가입</button>
  </div>

  <!-- sign in with google, facebook, istagram-->

</template>


<script setup>
</script>