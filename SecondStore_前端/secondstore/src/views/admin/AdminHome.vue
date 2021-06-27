<template>
    <div class="adminHome">
        <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b">
        <el-submenu index="1">
            <template slot="title">处理中心</template>
            <el-menu-item index="1-1"><router-link to="/admin/audit/user" >用户注册审核</router-link></el-menu-item>
            <el-menu-item index="1-2"><router-link to="/admin/audit/commodity" >发布商品审核</router-link></el-menu-item>
            <el-menu-item index="1-3"><router-link to="/admin/sellRate" >商家等级管理</router-link></el-menu-item>
        </el-submenu>
        <el-menu-item index="2"><router-link to="/admin/recharge" >充值操作</router-link></el-menu-item>
        <el-menu-item index="3"><router-link to="/admin/bargain" >交易管理</router-link></el-menu-item>
        </el-menu>
        <span class="loginSpan"><b>欢迎您，{{admin.name}},</b><a href="javascript:;" @click="exit">退出登录</a></span>
        <router-view/>
    </div>
</template>
<script>
import { mapMutations, mapState } from 'vuex';
export default {
    name: "AdminHome",
    data() {
      return {
        activeIndex: '1',
      };
    },
    computed:{
        ...mapState('user',['admin'])
    },
    watch:{
        admin(val){
            if(val==null){
                this.$router.push("/login")
            }
        }
    },
    methods:{
        exit(){
            sessionStorage.removeItem("admin");
            this.deleteAdmin();          
        },
        ...mapMutations('user',['deleteAdmin'])  //将this.deleteAdmin映射为this.$store.state.commit('user/deleteAdmin')
    }
}
</script>

<style scoped>
.adminHome{
    position: relative;
    background-color: #f4f4f4;
    color: #846e6e;
    min-height: 100vh;
}
a{  
    text-decoration: none;
    color: #fff;
}
.loginSpan{
    position: absolute;
    right: 70px;
    top: 15px;
}
</style>