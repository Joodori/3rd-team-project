<template>
  <div class="theme-park-home-v2 mobile-only">
    <div class="theme-park-home-v2">
      <!-- Top Navigation -->
      <nav class="top-nav">
        <div class="container-fluid">

          <!-- if 로그인 안한상태-->
          <div class="d-flex justify-content-between align-items-center py-3" v-if="!loginStatus">
            <h1 class="park-logo">🎢 매직랜드 🎠</h1>
            <div class="nav-buttons">
              <button @click="goToLogin()" class="nav-btn login-btn">로그인</button>
              <button @click="goToSignup()" class="nav-btn signup-btn">회원가입</button>
            </div>
          </div>
          
          <!-- if 로그인 한 상태-->
          <div class="d-flex justify-content-center align-items-center py-3" v-if="loginStatus">
            <h1 class="park-logo">🎢 매직랜드 🎠</h1>
          </div>

        </div>
      </nav>

      <!-- Main Welcome Section -->
      <div class="welcome-banner">
        <div class="container-fluid">
          <div class="text-center py-4">
            <h2 class="welcome-title">환상의 놀이공원에 오신 것을 환영합니다! 🎪</h2>
            <p class="welcome-subtitle">즐거운 하루를 보내세요!</p>
          </div>
        </div>
      </div>

      <!-- Main Content Area -->
      <div class="container-fluid px-4 py-4">
        <div class="row">
          <!-- Left Column - Main Actions -->
          <div class="col-lg-8 mb-4">

            <!-- Primary Action - Ticket Booking -->
            <div class="action-section mb-4">
              <button @click="ticket_reservation()" class="primary-btn ticket-btn w-100">
                <div class="btn-content">
                  <div class="btn-icon">🎫</div>
                  <div class="btn-text">
                    <h3 class="btn-title">입장권 예매</h3>
                    <p class="btn-desc">온라인 할인가로 미리 예매하세요</p>
                    <span class="discount-tag">최대 30% 할인</span>
                  </div>
                  <div class="btn-arrow">▶</div>
                </div>
              </button>
            </div>

            <!-- Secondary Actions -->
            <div class="row g-3">
              <div class="col-md-6">
                <button @click="book_confirm()" class="secondary-btn attraction-btn w-100">
                  <div class="secondary-content">
                    <div class="secondary-icon">🎠</div>
                    <div class="secondary-text">
                      <h4>어트랙션 예약</h4>
                      <p>인기 놀이기구 미리 예약</p>
                    </div>
                  </div>
                </button>
              </div>
              <div class="col-md-6">
                <button @click="ride_faclityinfo()" class="secondary-btn facility-btn w-100">
                  <div class="secondary-content">
                    <div class="secondary-icon">🗺️</div>
                    <div class="secondary-text">
                      <h4>시설 안내</h4>
                      <p>놀이기구 위치와 정보</p>
                    </div>
                  </div>
                </button>
              </div>
            </div>

          </div> <!-- /col-lg-8 -->
        </div> <!-- /row -->

        <!-- Attraction Gallery -->
        <div class="attraction-gallery mt-5">
          <h3 class="gallery-title">🎪 인기 어트랙션 갤러리</h3>

          <div id="attractionCarousel" class="carousel slide mt-3" data-bs-ride="carousel" data-bs-interval="3500">
            <div class="carousel-indicators">
              <button type="button" data-bs-target="#attractionCarousel" data-bs-slide-to="0" class="active"></button>
              <button type="button" data-bs-target="#attractionCarousel" data-bs-slide-to="1"></button>
              <button type="button" data-bs-target="#attractionCarousel" data-bs-slide-to="2"></button>
              <button type="button" data-bs-target="#attractionCarousel" data-bs-slide-to="3"></button>
              <button type="button" data-bs-target="#attractionCarousel" data-bs-slide-to="4"></button>
            </div>

            <div class="carousel-inner">
              <div class="carousel-item active">
                <div class="attraction-slide">
                  <img src="/assets/imageshompage/hesung.jpg" class="attraction-img" alt="혜성">
                  <div class="slide-caption">
                    <h4>🚀 혜성 롤러코스터</h4>
                    <p>스릴 만점의 초고속 체험!</p>
                  </div>
                </div>
              </div>
              <div class="carousel-item">
                <div class="attraction-slide">
                  <img src="/assets/imageshompage/baiking.jpg" class="attraction-img" alt="바이킹">
                  <div class="slide-caption">
                    <h4>⚓ 바이킹</h4>
                    <p>좌우로 흔들리는 스릴!</p>
                  </div>
                </div>
              </div>
              <div class="carousel-item">
                <div class="attraction-slide">
                  <img src="/assets/imageshompage/holombride.jpg" class="attraction-img" alt="홀로브라이드">
                  <div class="slide-caption">
                    <h4>👻 홀로브라이드</h4>
                    <p>오싹한 공포 체험!</p>
                  </div>
                </div>
              </div>
              <div class="carousel-item">
                <div class="attraction-slide">
                  <img src="/assets/imageshompage/jailoswing.jpg" class="attraction-img" alt="자이로스윙">
                  <div class="slide-caption">
                    <h4>🎪 자이로스윙</h4>
                    <p>360도 회전의 짜릿함!</p>
                  </div>
                </div>
              </div>
              <div class="carousel-item">
                <div class="attraction-slide">
                  <img src="/assets/imageshompage/jailoompdroop.jpg" class="attraction-img" alt="자이로드롭">
                  <div class="slide-caption">
                    <h4>🎢 자이로드롭</h4>
                    <p>하늘에서 떨어지는 스릴!</p>
                  </div>
                </div>
              </div>
            </div>

            <button class="carousel-control-prev" type="button" data-bs-target="#attractionCarousel" data-bs-slide="prev">
              <span class="carousel-control-prev-icon"></span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#attractionCarousel" data-bs-slide="next">
              <span class="carousel-control-next-icon"></span>
            </button>
          </div>
        </div> <!-- /attraction-gallery -->
      </div> <!-- /container-fluid -->
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
const router = useRouter()

// 주형 수정
import { onMounted, ref } from 'vue'
import { storeToRefs } from 'pinia'
import { useUserInfo } from '@/stores/user'
const userStore = useUserInfo()
const { loginStatus } = storeToRefs(userStore)
const checkLoginStatus = ref(false)
// onMounted로 login, 회원가입 div태그 막아버림
onMounted(() => {
  if (loginStatus.value === true) {
    checkLoginStatus.value = true
  }
})
// 주형 수정

const book_confirm = () => router.push('/attraction_reservation')
const ticket_reservation = () => router.push('/ticket_purchase')
const ride_faclityinfo = () => router.push('/ride_faclity_map')
const goToLogin = () => router.push('/login')
const goToSignup = () => router.push('/sign-up')

import axios from 'axios'
import { Modal } from 'bootstrap'
</script>

<style scoped>
/* 새로운 놀이공원 테마 */
.theme-park-home-v2 {
  min-height: 100vh;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  font-family: 'Inter', sans-serif;
}

/* Top Navigation */
.top-nav {
  background: rgba(255, 255, 255, 0.95);
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  position: sticky;
  top: 0;
  z-index: 1000;
}

.park-logo {
  color: #2d3748;
  font-size: 1.8rem;
  font-weight: 800;
  margin: 0;
}

.nav-btn {
  padding: 8px 20px;
  border-radius: 20px;
  border: none;
  font-weight: 600;
  margin-left: 10px;
  transition: all 0.3s ease;
  cursor: pointer;
}

.login-btn {
  background: #e2e8f0;
  color: #4a5568;
}

.login-btn:hover {
  background: #cbd5e0;
  transform: translateY(-2px);
}

.signup-btn {
  background: #ed8936;
  color: white;
}

.signup-btn:hover {
  background: #dd6b20;
  transform: translateY(-2px);
}

/* Welcome Banner */
.welcome-banner {
  background: rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(10px);
  color: white;
}

.welcome-title {
  color: rgb(238, 255, 0);
  font-size: 1.8rem;
  font-weight: 700;
  margin-bottom: 8px;
}

.welcome-subtitle {
  font-size: 1.1rem;
  opacity: 0.9;
  margin: 0;
}

/* Primary Button - Ticket */
.primary-btn {
  background: #ffffff;
  border: 3px solid #e2e8f0;
  border-radius: 15px;
  padding: 25px;
  cursor: pointer;
  transition: all 0.3s ease;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
  height: 140px;
}

.primary-btn:hover {
  transform: translateY(-3px);
  box-shadow: 0 8px 25px rgba(0, 0, 0, 0.15);
  border-color: #ed8936;
}

.btn-content {
  display: flex;
  align-items: center;
  gap: 20px;
  height: 100%;
}

.btn-icon {
  font-size: 3.5rem;
  flex-shrink: 0;
}

.btn-title {
  color: #2d3748;
  font-size: 1.6rem;
  font-weight: 700;
  margin-bottom: 8px;
}

.btn-desc {
  color: #4a5568;
  font-size: 1rem;
  margin-bottom: 8px;
}

.discount-tag {
  background: #ed8936;
  color: white;
  padding: 4px 12px;
  border-radius: 15px;
  font-size: 0.85rem;
  font-weight: 600;
}

.btn-arrow {
  font-size: 1.5rem;
  color: #ed8936;
  margin-left: auto;
  transition: transform 0.3s ease;
}

.primary-btn:hover .btn-arrow {
  transform: translateX(8px);
}

/* Secondary Buttons */
.secondary-btn {
  background: #ffffff;
  border: 2px solid #e2e8f0;
  border-radius: 12px;
  padding: 20px;
  cursor: pointer;
  transition: all 0.3s ease;
  box-shadow: 0 3px 10px rgba(0, 0, 0, 0.08);
  height: 120px;
}

.secondary-btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 20px rgba(0, 0, 0, 0.12);
}

.attraction-btn {
  border-left: 4px solid #9f7aea;
}

.facility-btn {
  border-left: 4px solid #38b2ac;
}

.secondary-content {
  display: flex;
  align-items: flex-start;
  gap: 15px;
  height: 100%;
}

.secondary-icon {
  font-size: 2.5rem;
  flex-shrink: 0;
}

.secondary-text h4 {
  color: #2d3748;
  font-size: 1.2rem;
  font-weight: 600;
  margin-bottom: 5px;
}

.secondary-text p {
  color: #4a5568;
  font-size: 0.9rem;
  margin: 0;
}

/* Attraction Gallery */
.attraction-gallery {
  background: #ffffff;
  border: 2px solid #e2e8f0;
  border-radius: 15px;
  padding: 25px;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
}

.gallery-title {
  color: #2d3748;
  font-size: 1.5rem;
  font-weight: 700;
  text-align: center;
  margin-bottom: 0;
}

.attraction-slide {
  position: relative;
  border-radius: 12px;
  overflow: hidden;
  height: 300px;
}

.attraction-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.slide-caption {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  background: linear-gradient(transparent, rgba(0, 0, 0, 0.7));
  color: white;
  padding: 25px;
  text-align: center;
}

.slide-caption h4 {
  font-size: 1.3rem;
  font-weight: 700;
  color: white;
  margin-bottom: 5px;
}

.slide-caption p {
  font-size: 0.95rem;
  margin: 0;
}

/* Carousel Controls */
.carousel-indicators button {
  width: 10px;
  height: 10px;
  border-radius: 50%;
  border: none;
  background: rgba(255, 255, 255, 0.5);
}

.carousel-indicators .active {
  background: #ed8936;
}

.mobile-only {
  display: none;
}

@media (max-width: 768px) {
  .mobile-only {
    display: block !important;
  }
}

/* Responsive Design */
@media (max-width: 768px) {
  .park-logo {
    font-size: 1.4rem;
  }

  .welcome-title {
    font-size: 1.4rem;
  }

  .btn-content {
    flex-direction: column;
    text-align: center;
    gap: 10px;
  }

  .primary-btn {
    height: auto;
    padding: 20px;
  }

  .secondary-btn {
    height: auto;
    padding: 15px;
  }

  .secondary-content {
    flex-direction: column;
    text-align: center;
    gap: 10px;
  }

  .attraction-slide {
    height: 250px;
  }
}
</style>
