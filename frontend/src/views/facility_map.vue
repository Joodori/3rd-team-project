<template>
  		<p class="subTit">위치정보</p>
				<div class="mapArea" data-controller="controller/lwc/mapController">
					<p class="caption">어드벤처 3층 동굴구역 내</p>
					<div class="map">
						<div id="map" style="position:relative; float:left;">
							<img src="/common/images/map_A0203.png" id="mapImg" width="836" />
							<p id="mapCheck" style="position: absolute; top: 340px; left: 202px;"><img src="/common/images/map_check.png"></p>
						</div>
					</div>
					<div class="zoomBtn">
						<a href="javascript:" id="zoomIn" class="zoomIn"><img src="/common/images/btn/zoomIn_btn.gif" alt="확대" /></a>
						<a href="javascript:" id="zoomOut" class="zoomOut"><img src="/common/images/btn/zoomOut_btn.gif" alt="축소" /></a>
					</div>
          </div>
</template>

<script setup>

</script>
