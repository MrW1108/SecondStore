<template>
  <el-carousel :interval="3000" type="card" height="300px">
    <el-carousel-item v-for="item in imgList" :key="item.id">
      <router-link :to="/commodity/+item.id"><img :src="item.img" /></router-link>
    </el-carousel-item>
  </el-carousel>
</template>

<script>
export default {
    name:'HomeScrollPic',
    data(){
        return{
            imgList:[
                {img:require("../assets/img1.jpg"),id:32},
                {img:require("../assets/img2.jpg"),id:31},
                {img:require("../assets/img3.jpg"),id:33},
                {img:require("../assets/img4.jpg"),id:30},
                {img:require("../assets/img5.jpg"),id:29},
            ]
        }
    }
}
</script>

<style>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }
  
  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }
  
  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }

  img{
      width: 100%;
      height: 100%;
  }
</style>