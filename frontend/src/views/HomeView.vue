<template>
  <div class="main-container d-flex flex-column gap-20">
    <div class="container d-flex flex-column w-100 h-50 mb-5">

      <!-- 상단 버튼 섹션 -->
      <div class="button-container d-flex flex-column justify-content-between w-100 h-100">
        
        <!-- 티켓 예약 -->
        <button @click="ticket_reservation()" class="custom-btn ticket-btn">
          <div class="btn-content d-flex flex-column">
            <div class="btn-icon">🎫</div>
            <div class="btn-text">
              <h3 class="btn-title">입장권 예매</h3>
              <p class="btn-desc">온라인 할인가로 미리 예매하세요</p>
              <span class="discount-tag">최대 30% 할인</span>
            </div>
            <div class="btn-arrow">▶</div>
          </div>
        </button>

        <!-- 어트랙션 예약 -->
        <button @click="book_confirm()" class="custom-btn attraction-btn">
          <div class="btn-content d-flex flex-column">
            <div class="btn-icon">🎠</div>
            <div class="btn-text">
              <h3 class="btn-title">어트랙션 예약</h3>
              <p class="btn-desc">인기 놀이기구 미리 예약</p>
            </div>
            <div class="btn-arrow">▶</div>
          </div>
        </button>

        <!-- 시설 안내 -->
        <button @click="ride_faclityinfo()" class="custom-btn facility-btn">
          <div class="btn-content d-flex flex-column">
            <div class="btn-icon">🗺️</div>
            <div class="btn-text">
              <h3 class="btn-title">시설 안내</h3>
              <p class="btn-desc">놀이기구 위치와 정보</p>
            </div>
            <div class="btn-arrow">▶</div>
          </div>
        </button>

      </div>
    </div>

    <!-- Attraction Gallery -->
    <div class="attraction-gallery w-100 h-50 mt-4">
      <h3 class="gallery-title">🎪 인기 어트랙션 갤러리</h3>

      
      <div id="attractionCarousel" class="carousel slide  mt-3" data-bs-ride="carousel" data-bs-interval="3500">

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

        <!-- 좌우 화살표 중앙 배치 -->
        <button class="carousel-control-prev" type="button" data-bs-target="#attractionCarousel" data-bs-slide="prev">
          <span class="carousel-control-prev-icon"></span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#attractionCarousel" data-bs-slide="next">
          <span class="carousel-control-next-icon"></span>
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
const router = useRouter()

const book_confirm = () => router.push('/attraction_reservation')
const ticket_reservation = () => router.push('/ticket_purchase')
const ride_faclityinfo = () => router.push('/ride_faclity_map')

import axios from 'axios'
import { Modal } from 'bootstrap'
</script>

<style scoped>
/* 전체 컨테이너 */
.main-container {
  width: 480px;
  margin: 0 auto;
  padding: 16px;
  background: linear-gradient(to bottom, #dff6ff, #ffffff);
  border-radius: 16px;
  box-shadow: 0 4px 12px rgba(0,0,0,0.1);
}

/* 버튼 컨테이너 */
.button-container {
  gap: 12px;
  margin-top: 12px;
}

/* 공통 버튼 스타일 */
.custom-btn {
  background: #fff;
  border: none;
  border-radius: 16px;
  padding: 16px;
  box-shadow: 0 4px 8px rgba(0,0,0,0.1);
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.custom-btn:hover {
  transform: translateY(-3px);
  box-shadow: 0 6px 14px rgba(0,0,0,0.15);
}

.btn-icon {
  font-size: 2rem;
}

.btn-title {
  font-weight: 700;
  font-size: 1.2rem;
}

.btn-desc {
  font-size: 0.9rem;
  color: #555;
}

.discount-tag {
  background-color: #ff8c8c;
  color: white;
  font-size: 0.8rem;
  padding: 2px 6px;
  border-radius: 8px;
  margin-top: 4px;
  display: inline-block;
}

/* 갤러리 */
.attraction-gallery {
  margin-top: 24px;
}

.gallery-title {
  text-align: center;
  font-weight: 700;
  margin-bottom: 8px;
}

.attraction-img {
  width: 100%;
  height: 260px;
  object-fit: cover;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0,0,0,0.15);
  transition: transform 0.3s ease;
}

.attraction-img:hover {
  transform: scale(1.02);
}

/* 슬라이드 캡션 중앙 배치 */
.slide-caption {
  position: absolute;
  bottom: 10px;
  left: 50%;
  transform: translateX(-50%);
  text-align: center;
  background: rgba(255, 255, 255, 0.55);
  color: black;
  padding: 8px 12px;
  border-radius: 8px;
  max-width: 90%;
  font-size: 15px;
}

/* 화살표 중앙 정렬 */
.carousel-control-prev,
.carousel-control-next {
  top: 50%;
  transform: translateY(-50%);
  width: 8%;
}
</style>
