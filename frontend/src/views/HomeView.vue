<template>
<div class="magic-park-grid">
  <!-- Header Strip -->
  <header class="park-header">
    <div class="container-fluid">
      <div class="header-content">
        <div class="park-brand">
          <span class="brand-icon">🎡</span>
          <span class="brand-name">MAGIC LAND</span>
        </div>
        <div class="header-actions">
          <button @click="goToLogin()" class="header-btn">LOGIN</button>
          <button @click="goToSignup()" class="header-btn signup">JOIN</button>
        </div>
      </div>
    </div>
  </header>

  <!-- Main Grid Layout -->
  <main class="grid-container">
    <div class="container-fluid p-4">
      
      <!-- Welcome Message -->
      <div class="welcome-strip">
        <h1 class="welcome-text">🎪 환상의 세계로 떠나보세요! 🎢</h1>
      </div>

      <!-- Main Grid -->
      <div class="park-grid">
        
        <!-- Large Ticket Tile -->
        <div class="grid-item ticket-tile large-tile" @click="ticket_reservation()">
          <div class="tile-background">
            <div class="tile-pattern"></div>
          </div>
          <div class="tile-content">
            <div class="tile-icon">🎫</div>
            <h2 class="tile-title">입장권<br>예매</h2>
            <p class="tile-subtitle">온라인 특가</p>
            <div class="price-circle">30%<br>OFF</div>
          </div>
        </div>

        <!-- Medium Attraction Tile -->
        <div class="grid-item attraction-tile medium-tile" @click="book_confirm()">
          <div class="tile-content">
            <div class="tile-icon">🎠</div>
            <h3 class="tile-title">어트랙션<br>예약</h3>
            <p class="tile-subtitle">대기없이 바로!</p>
          </div>
        </div>

        <!-- Medium Facility Tile -->
        <div class="grid-item facility-tile medium-tile" @click="ride_faclityinfo()">
          <div class="tile-content">
            <div class="tile-icon">🗺️</div>
            <h3 class="tile-title">시설<br>안내</h3>
            <p class="tile-subtitle">위치 & 정보</p>
          </div>
        </div>

        <!-- Small Info Tiles -->
        <div class="grid-item event-tile small-tile" @click="event()">
          <div class="tile-content">
            <div class="tile-icon">🎉</div>
            <span class="small-title">이벤트</span>
            <div class="notification-dot"></div>
          </div>
        </div>

        <div class="grid-item weather-tile small-tile">
          <div class="tile-content">
            <div class="tile-icon">☀️</div>
            <span class="small-title">날씨</span>
            <span class="weather-temp">25°</span>
          </div>
        </div>

        <div class="grid-item hours-tile small-tile">
          <div class="tile-content">
            <div class="tile-icon">🕐</div>
            <span class="small-title">운영중</span>
            <span class="status-indicator">OPEN</span>
          </div>
        </div>

        <!-- Wide Carousel Tile -->
        <div class="grid-item carousel-tile wide-tile">
          <div class="carousel-header">
            <h3>🎪 인기 어트랙션</h3>
          </div>
          
          <div id="gridCarousel" class="carousel slide" data-bs-ride="carousel" data-bs-interval="3000">
            <div class="carousel-inner">
              <div class="carousel-item active">
                <div class="attraction-card">
                  <img src="/assets/imageshompage/hesung.jpg" class="attraction-thumb" alt="혜성">
                  <div class="attraction-info">
                    <h4>🚀 혜성 롤러코스터</h4>
                    <span class="thrill-level">스릴 ★★★★★</span>
                  </div>
                </div>
              </div>
              <div class="carousel-item">
                <div class="attraction-card">
                  <img src="/assets/imageshompage/baiking.jpg" class="attraction-thumb" alt="바이킹">
                  <div class="attraction-info">
                    <h4>⚓ 바이킹</h4>
                    <span class="thrill-level">스릴 ★★★★☆</span>
                  </div>
                </div>
              </div>
              <div class="carousel-item">
                <div class="attraction-card">
                  <img src="/assets/imageshompage/holombride.jpg" class="attraction-thumb" alt="홀로브라이드">
                  <div class="attraction-info">
                    <h4>👻 홀로브라이드</h4>
                    <span class="thrill-level">공포 ★★★★★</span>
                  </div>
                </div>
              </div>
              <div class="carousel-item">
                <div class="attraction-card">
                  <img src="/assets/imageshompage/jailoswing.jpg" class="attraction-thumb" alt="자이로스윙">
                  <div class="attraction-info">
                    <h4>🎪 자이로스윙</h4>
                    <span class="thrill-level">스릴 ★★★★★</span>
                  </div>
                </div>
              </div>
              <div class="carousel-item">
                <div class="attraction-card">
                  <img src="/assets/imageshompage/jailoompdroop.jpg" class="attraction-thumb" alt="자이로드롭">
                  <div class="attraction-info">
                    <h4>🎢 자이로드롭</h4>
                    <span class="thrill-level">스릴 ★★★★★</span>
                  </div>
                </div>
              </div>
            </div>
            
            <button class="carousel-control-prev" type="button" data-bs-target="#gridCarousel" data-bs-slide="prev">
              <span class="carousel-control-prev-icon"></span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#gridCarousel" data-bs-slide="next">
              <span class="carousel-control-next-icon"></span>
            </button>
          </div>
        </div>

      </div>
    </div>
  </main>
</div>
</template>

<script setup>
import { useRouter } from 'vue-router'
const router = useRouter()

const book_confirm = () => {
  router.push('/attraction_reservation')
}

const ticket_reservation = () => {
  router.push('/ticket_purchase')
}

const ride_faclityinfo = () => {
  router.push('/ride_faclity_map')
}

const event = () => {
  console.log('이벤트 조회하기')
}

const goToLogin = () => {
  router.push('/login')
}

const goToSignup = () => {
  router.push('/signup')
}

import axios from 'axios'
import { Modal } from 'bootstrap'
</script>

<style scoped>
/* Modern Tile Design - Smaller Fixed Width */
.magic-park-grid {
  min-height: 100vh;
  background: linear-gradient(45deg, #667eea 0%, #764ba2 50%, #f093fb 100%);
  font-family: 'Inter', sans-serif;
}

/* Header */
.park-header {
  background: rgba(255,255,255,0.15);
  backdrop-filter: blur(20px);
  border-bottom: 1px solid rgba(255,255,255,0.2);
  position: sticky;
  top: 0;
  z-index: 1000;
}

.header-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem 1.5rem;
  max-width: 700px; /* 700px로 축소 */
  margin: 0 auto;
}

.park-brand {
  display: flex;
  align-items: center;
  gap: 8px;
  color: white;
}

.brand-icon {
  font-size: 1.8rem; /* 조금 축소 */
}

.brand-name {
  font-size: 1.3rem; /* 조금 축소 */
  font-weight: 900;
  letter-spacing: 1.5px;
}

.header-btn {
  background: rgba(255,255,255,0.2);
  color: white;
  border: 1px solid rgba(255,255,255,0.3);
  padding: 6px 16px; /* 조금 축소 */
  margin-left: 8px;
  border-radius: 20px;
  font-weight: 600;
  font-size: 0.9rem; /* 조금 축소 */
  transition: all 0.3s ease;
  cursor: pointer;
}

.header-btn:hover {
  background: rgba(255,255,255,0.3);
  transform: translateY(-2px);
}

.header-btn.signup {
  background: rgba(255,255,255,0.9);
  color: #667eea;
}

.header-btn.signup:hover {
  background: white;
}

/* Welcome Strip */
.welcome-strip {
  text-align: center;
  padding: 1.5rem 0; /* 조금 축소 */
  max-width: 700px;
  margin: 0 auto;
}

.welcome-text {
  color: white;
  font-size: 1.8rem; /* 조금 축소 */
  font-weight: 800;
  text-shadow: 2px 2px 4px rgba(0,0,0,0.3);
  margin: 0;
}

/* Grid Layout - 더 작은 고정 크기 */
.park-grid {
  display: grid;
  grid-template-columns: repeat(6, 110px); /* 110px로 축소 */
  grid-template-rows: repeat(4, 110px); /* 110px로 축소 */
  gap: 15px; /* gap도 축소 */
  width: 700px; /* 700px로 축소 */
  margin: 0 auto;
  justify-content: center;
}

/* Grid Item Base */
.grid-item {
  border-radius: 16px; /* 조금 축소 */
  cursor: pointer;
  transition: all 0.4s ease;
  overflow: hidden;
  position: relative;
  box-shadow: 0 6px 24px rgba(0,0,0,0.2); /* 조금 축소 */
}

.grid-item:hover {
  transform: translateY(-4px) scale(1.02); /* 조금 축소 */
  box-shadow: 0 12px 32px rgba(0,0,0,0.3);
}

/* Tile Sizes - 축소된 크기 */
.large-tile {
  grid-column: span 3; /* 330px 너비 */
  grid-row: span 2; /* 220px 높이 */
}

.medium-tile {
  grid-column: span 2; /* 220px 너비 */
  grid-row: span 2; /* 220px 높이 */
}

.small-tile {
  grid-column: span 1; /* 110px 너비 */
  grid-row: span 1; /* 110px 높이 */
}

.wide-tile {
  grid-column: span 6; /* 700px 너비 */
  grid-row: span 2; /* 220px 높이 */
}

/* Ticket Tile - Primary */
.ticket-tile {
  background: linear-gradient(135deg, #ff6b6b 0%, #ee5a24 100%);
  color: white;
}

.tile-background {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  opacity: 0.1;
}

.tile-pattern {
  width: 100%;
  height: 100%;
  background-image: 
    radial-gradient(circle at 25% 25%, white 2px, transparent 2px),
    radial-gradient(circle at 75% 75%, white 2px, transparent 2px);
  background-size: 25px 25px; /* 조금 축소 */
}

.tile-content {
  position: relative;
  z-index: 2;
  padding: 18px; /* 축소 */
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.large-tile .tile-icon {
  font-size: 3rem; /* 축소 */
  margin-bottom: 8px;
}

.large-tile .tile-title {
  font-size: 1.6rem; /* 축소 */
  font-weight: 800;
  line-height: 1.1;
  margin-bottom: 8px;
}

.large-tile .tile-subtitle {
  font-size: 0.9rem; /* 축소 */
  opacity: 0.9;
  margin-bottom: 0;
}

.price-circle {
  position: absolute;
  top: 15px;
  right: 15px;
  width: 55px; /* 축소 */
  height: 55px;
  background: rgba(255,255,255,0.2);
  border: 2px solid rgba(255,255,255,0.5);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 0.7rem; /* 축소 */
  font-weight: 700;
  text-align: center;
  line-height: 1.1;
}

/* Attraction Tile */
.attraction-tile {
  background: linear-gradient(135deg, #a29bfe 0%, #6c5ce7 100%);
  color: white;
}

.medium-tile .tile-icon {
  font-size: 2.4rem; /* 축소 */
  margin-bottom: 12px;
}

.medium-tile .tile-title {
  font-size: 1.1rem; /* 축소 */
  font-weight: 700;
  line-height: 1.2;
  margin-bottom: 8px;
}

.medium-tile .tile-subtitle {
  font-size: 0.8rem; /* 축소 */
  opacity: 0.9;
}

/* Facility Tile */
.facility-tile {
  background: linear-gradient(135deg, #00b894 0%, #00a085 100%);
  color: white;
}

/* Small Tiles */
.small-tile .tile-content {
  padding: 15px; /* 축소 */
  text-align: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 6px;
}

.small-tile .tile-icon {
  font-size: 1.6rem; /* 축소 */
}

.small-title {
  font-size: 0.7rem; /* 축소 */
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

/* Event Tile */
.event-tile {
  background: linear-gradient(135deg, #fdcb6e 0%, #e17055 100%);
  color: white;
  position: relative;
}

.notification-dot {
  position: absolute;
  top: 8px;
  right: 8px;
  width: 10px; /* 축소 */
  height: 10px;
  background: #ff3838;
  border-radius: 50%;
  animation: pulse 2s infinite;
}

@keyframes pulse {
  0% { transform: scale(1); opacity: 1; }
  50% { transform: scale(1.2); opacity: 0.7; }
  100% { transform: scale(1); opacity: 1; }
}

/* Weather Tile */
.weather-tile {
  background: linear-gradient(135deg, #74b9ff 0%, #0984e3 100%);
  color: white;
}

.weather-temp {
  font-size: 1rem; /* 축소 */
  font-weight: 700;
}

/* Hours Tile */
.hours-tile {
  background: linear-gradient(135deg, #55a3ff 0%, #003d82 100%);
  color: white;
}

.status-indicator {
  font-size: 0.6rem; /* 축소 */
  font-weight: 700;
  color: #00ff88;
  text-shadow: 0 0 8px #00ff88;
}

/* Carousel Tile */
.carousel-tile {
  background: rgba(255,255,255,0.95);
  padding: 0;
}

.carousel-header {
  padding: 15px 20px 12px 20px; /* 축소 */
  border-bottom: 2px solid #f1f3f4;
}

.carousel-header h3 {
  color: #2d3748;
  font-size: 1.1rem; /* 축소 */
  font-weight: 700;
  margin: 0;
}

.attraction-card {
  display: flex;
  align-items: center;
  padding: 15px 20px; /* 축소 */
  gap: 15px;
}

.attraction-thumb {
  width: 60px; /* 축소 */
  height: 60px;
  border-radius: 10px;
  object-fit: cover;
  box-shadow: 0 3px 12px rgba(0,0,0,0.2);
}

.attraction-info h4 {
  color: #2d3748;
  font-size: 0.95rem; /* 축소 */
  font-weight: 600;
  margin-bottom: 4px;
}

.thrill-level {
  color: #e17055;
  font-size: 0.75rem; /* 축소 */
  font-weight: 600;
}

/* Container 고정 너비 설정 */
.container-fluid {
  max-width: 740px; /* 패딩 포함 고정 너비 */
  margin: 0 auto;
}

/* 작은 화면에서의 최소 크기 보장 */
@media (max-width: 780px) {
  .magic-park-grid {
    overflow-x: auto;
    padding: 0 15px;
  }
  
  .park-grid {
    min-width: 700px;
  }
  
  .header-content,
  .welcome-strip,
  .container-fluid {
    min-width: 700px;
  }
}

/* 매우 작은 화면 대응 */
@media (max-width: 600px) {
  .park-grid {
    grid-template-columns: repeat(4, 90px); /* 더 작은 고정 크기 */
    grid-template-rows: repeat(6, 90px);
    width: 570px; /* 작은 고정 너비 */
    gap: 12px;
  }
  
  .large-tile {
    grid-column: span 2;
    grid-row: span 2;
  }
  
  .medium-tile {
    grid-column: span 2;
    grid-row: span 1;
  }
  
  .wide-tile {
    grid-column: span 4;
    grid-row: span 2;
  }
  
  .header-content,
  .welcome-strip,
  .container-fluid {
    min-width: 570px;
  }
  
  .welcome-text {
    font-size: 1.4rem;
  }
  
  .brand-name {
    font-size: 1.1rem;
  }
  
  /* 텍스트 크기 추가 조정 */
  .large-tile .tile-title {
    font-size: 1.3rem;
  }
  
  .medium-tile .tile-title {
    font-size: 0.95rem;
  }
  
  .tile-content {
    padding: 12px;
  }
}
</style>
