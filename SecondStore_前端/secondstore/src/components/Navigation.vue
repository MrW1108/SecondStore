<template>
    <!-- 顶部导航 -->
    <div class="navigation">
        <ul style="list-style:none">
            <li style="float: left">
                <a>
                    <i class="el-icon-location-outline"></i>
                    <span>北京</span>
                </a>
            </li>
            <li v-if="!user">
                <span class="loginSpan"><router-link to="/login">你好，请登录  </router-link><router-link to="/register"><span>免费注册</span></router-link></span>
            </li>
            <li v-else>
                <span class="loginSpan">欢迎您，{{user.name}},<a href="javascript:;" @click="exit">退出登录</a></span>
            </li>
            <li class="spacer"></li>
            <li><router-link to="/order">我的订单</router-link></li>
            <li class="spacer"></li>
            <li><router-link to="/account">我的钱包</router-link></li>
        </ul>
    </div>
</template>

<script>
import { mapMutations, mapState } from 'vuex'
export default {
    name: 'Navigation',
    computed: {
        //user模块带有命名空间
        ...mapState('user',{
            //将this.user映射为this.$store.state.user.user  因为定义变量名称相同，可简写成 ['user']
            user: 'user'
        }),
    },
    methods: {
        exit(){
            this.deleteUser();
            sessionStorage.removeItem("user");
        },
        ...mapMutations('user',['deleteUser'])  //将this.deleteUser映射为this.$store.state.commit('user/deleteUser')
    }
}
</script>


<style scoped>
.navigation{    
    position: relative;
    background-color: #e3e4e5;
    padding-left: 10%;
    /* height: 30px;
    background-color: #e3e4e5;
    margin-top: 0;
    margin-bottom: 30px;
    border: 1px solid palevioletred; */
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