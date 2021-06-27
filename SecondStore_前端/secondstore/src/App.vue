<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
import { mapMutations } from 'vuex'
export default {
  name:"App",
  created(){
    // document.cookie = "username=wjz"
    // document.cookie = "user={id:1,name:3}"
    // console.log(document.cookie)
    //在页面加载(刷新时读取sessionStorage里的状态信息),传给vuex
    if(sessionStorage.getItem("user")){
       //sessionStorage存储键值对形式，存储的字符串，所以保存的时候要是用JSON.stringify()，使用JSON.parse()将字符串转化为对象
       this.saveUser(Object.assign({},JSON.parse(sessionStorage.getItem("user"))))   
    }else if(sessionStorage.getItem("sell")){
      // console.log(sessionStorage.getItem("sell"));
      this.saveSell(Object.assign({},JSON.parse(sessionStorage.getItem("sell"))))
    }else if(sessionStorage.getItem("admin")){
      this.saveAdmin(Object.assign({},JSON.parse(sessionStorage.getItem("admin"))))
    }
  },
  methods:{
    ...mapMutations('user',['saveUser','saveSell','saveAdmin'])
  }
}
</script>

<style>
#app {  
  width: 100%;
  background-color: white;
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}
*{
  margin: 0;
  padding: 0;
}
</style>
