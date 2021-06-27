<template>
    <div class="sellHome">
         <!-- 顶部导航 -->
        <div class="navigation">
        <ul style="list-style:none">
            <li style="float: left">
                <router-link :to="'/shop/shopId/'+sell.id">
                    <i class="el-icon-location-outline"></i>
                    <span>店铺首页</span>
                </router-link>
            </li>
            <li v-if="!sell">
                <span class="loginSpan"><router-link to="/login">你好，请登录  </router-link><router-link to="/sellRegister"><span>免费注册</span></router-link></span>
            </li>
            <li v-else>
                <span class="loginSpan">欢迎您，{{sell.name}},<a href="javascript:;" @click="exit">退出登录</a></span>
            </li>
            <li class="spacer"></li>
            <li><router-link to="/shop/order">我的订单</router-link></li>
            <li class="spacer"></li>
            <li><router-link to="/shop/info">我的信息</router-link></li>
        </ul>
    </div>
    <router-view/>
    </div>
</template>
<script>
import { mapMutations, mapState } from 'vuex'
export default {
    name: "SellHome",
    computed:{
        ...mapState('user',{
            sell: 'sell'
        })
    },
    watch:{
        sell(val){
            if(val==null){
                this.$router.push("/login")
            }
        }
    },
     methods: {
        exit(){
            this.deleteSell();
            sessionStorage.removeItem("sell");
        },
        ...mapMutations('user',['deleteSell'])  //将this.deleteUser映射为this.$store.state.commit('user/deleteUser')
    }
}
</script>

<style scoped>
.sellHome{
    min-height: 100vh;
}
.navigation{    
    position: relative;
    background-color: #f4f4f4;
    padding-left: 10%;
}
li >a {
    font-size: 5px;
    text-decoration: none;
    display: block;
    color: rgb(53, 143, 80);
    height: 30px;
    line-height: 30px;
}
.loginSpan{
    font-size: 5px;
    color: rgb(53, 143, 80);
    display: block;
    height: 30px;
    line-height: 30px;
}
.loginSpan > a{
    text-decoration: none;
}
li.spacer {
    overflow: hidden;
    width: 1px;
    height: 10px;
    background-color: #ccc;
}
.link-regist{
    color: red;
}
li {
    margin-right: 15px;
    display: inline-block;
}
li a:hover {
    color: red
}
</style>