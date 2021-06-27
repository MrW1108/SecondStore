<template>
<div class="homePage">
    <home-scroll-pic/>
    <h3>新品上市</h3>
    <Commodities/>
</div>
</template>

<script>
import HomeScrollPic from '../components/HomeScrollPic.vue'
import Commodities from './Commodities.vue'
export default {
    name: 'homePage',
    components: {
        Commodities,
        HomeScrollPic,
    }
}
</script>

<style>
.homePage {  
  width: 1200px;
  margin: 0 auto;
  text-align: center;
  overflow: hidden;
}
.homePage > h3{
    margin: 20px 0;
}
</style>