<template>
  <div class="mapArea">
    <p class="subTit">위치정보</p>
    <p class="caption">어드벤처 3층 동굴구역 내</p>

    <div class="map">
      <div id="map" class="map-box">
        <!-- 배경 지도 -->
        <img src="/assets/attraction_falicity/map3Layer.png" id="mapImg" width="836" alt="map layer" />
        <!-- 핀(Here) -->
        <p id="mapCheck" class="pin" style="top: 340px; left: 202px;">
          <img src="/assets/attraction_falicity/map_check.png" alt="here" />
        </p>
      </div>
    </div>

    <div class="zoomBtn">
      <a href="javascript:" id="zoomIn" class="zoomIn">
        <img src="/assets/attraction_falicity/zoomIn_btn.gif" alt="확대" />
      </a>
      <a href="javascript:" id="zoomOut" class="zoomOut">
        <img src="/assets/attraction_falicity/zoomOut_btn.gif" alt="축소" />
      </a>
    </div>
  </div>
</template>

<script setup>
// 일단 표시만. (줌 로직은 나중에 붙여도 돼)
</script>

<style scoped>
.map-box { position: relative; float: left; width: 836px; }
.pin { position: absolute; margin: 0; }
.zoomBtn { margin-top: 12px; display: flex; gap: 8px; }
</style>
