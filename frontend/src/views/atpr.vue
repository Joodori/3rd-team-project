<template>
    <div class="container d-flex flex-column w-100 h-100">
        <div class="container d-flex flex-column w-100 h-25">
            <div>
                <label>현제시간:{{ currentTime }}</label>
            </div><!--25퍼 세로로 차지-->
        </div>
        <div class = "container d-flex flex-column w-100 h-25">
            <div class="card">
                <nav class="navbar navbar-light bg-light">
                    <div class ="container-fluid">
                        <a class="nabar-brand">예약 가능 어트렉션 조회</a>
                        <form class="d-flex">
                            <select v-model="sekected">
                                <option disavled value="">하나를 선택하세요</option>
                                <option v-for="facility in facilityList" :key="faclity.facilityNo">
                                    {{ facility.facilityName }}
                                </option>
                            </select>
                            <button class="btn btn-outline-sucess" type="submit">조회</button>
                        </form>
                    </div>
                    <div class ="container d-flex flex-column w-100 h-50">

                    </div>
                </nav>
            </div>
            <div v-if="!search" class="d-flex flex-column w-100 h-25">
                <div class="container">
                    <label>인원수</label>
                    <div class = "d-flex flex-column w-100 h-25">
                        <div class = "container">
                            <div class="card p-4 w-100 h-">
                    <div class="counter-item"><!--띄어쓰기-->
                      <button type="button" class="count-btn" @click="decrementAdults">
                          <i class="bi bi-dash-square"></i>
                      </button>

                      <span class="label">인원{{ personCount }}</span>
                      <button type="button" class="count-btn" @click="incrementPerson">
                        <i class="bi bi-plus-square"></i>
                      </button>

                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
    </template>
    <script setup>
    import dayjs from 'dayjs';
import {ref} from 'vue'; //페이지 실행되고 바로 실행 
    const currentTime = dayjs().format('YYYY-MM-DD HH:mm:ss');
</script>