<template>
  <div class="login">
    <form>
      <div class="lable">
        <label for="name"><i class="el-icon-user-solid"></i></label>
        <input
          id="name"
          name="name"
          type="text"
          v-model.trim="name"
          placeholder="用户名"
        />
      </div>
      <div class="lable">
        <label for="password"><i class="el-icon-lock"></i></label>
        <input
          id="password"
          type="password"
          v-model.trim="password"
          placeholder="密码"
        />
      </div>
      <div class="identity">  
        <input type="radio" id="user" name="identity" value="user" v-model="identity">
        <label for="user">用户</label>
        <input type="radio" id="merchant" name="identity" value="sell" v-model="identity">
        <label for="merchant">商家</label>
        <input type="radio" id="admin" name="identity" value="admin" v-model="identity">
        <label for="admin">管理员</label>
        
      </div>
      <div class="submit">
        <input type="submit" @click.prevent="login" value="登录" />
      </div>
    </form>
    <div class="toRegister">
      <router-link to="/register">立即注册</router-link>
    </div>
  </div>
</template>

<script>
import { mapMutations, mapState } from 'vuex';
export default {
  name: "UserLogin",
  data() {
    return {
      name: '',
      password: '',
      identity: "user"
    };
  },
  computed:{
    ...mapState('user',{
      user:'user',
      sell:'sell',
      admin:'admin'
    })
  },
  methods: {
    login(){
      if(!this.checkForm())
        return;
      this.axios.post(this.identity+"/login", 
        {name: this.name, password: this.password})
        .then(response => {
          if(response.data.code == 0){
            this.name = '';
            this.password = '';
            //普通用户登录
            if(this.identity=="user"){
              this.saveUser(response.data.data);
              sessionStorage.setItem("user",JSON.stringify(this.user));
              this.$message({
                message:"登录成功！",
                type:"success"
              });
              //如果存在查询参数
              if(this.$route.query.redirect){   
                  let redirect = this.$route.query.redirect;
                  //跳转至进入登录页前的路由
                  this.$router.replace(redirect);   //跳转到指定url路径，但是history栈中不会有记录，点击返回会跳转到上上个页面
              }else{
                  // 否则跳转至首页
                  this.$router.replace('/home');    
              }
            }else if(this.identity=="sell"){  //
                this.saveSell(response.data.data);
                sessionStorage.setItem("sell",JSON.stringify(this.sell));
                this.$message({
                  message:"登录成功！",
                  type:"success"
               });
                //如果存在查询参数
                if(this.$route.query.redirect){   
                    let redirect = this.$route.query.redirect;
                    //跳转至进入登录页前的路由
                    this.$router.replace(redirect);   //跳转到指定url路径，但是history栈中不会有记录，点击返回会跳转到上上个页面
                }else{
                    // 否则跳转至首页
                    this.$router.replace('/shop/shopId/'+this.sell.id);    
                }
              }else{    //管理员登录
                this.saveAdmin(response.data.data);
                sessionStorage.setItem("admin",JSON.stringify(this.admin));
                this.$message({
                  message:"登录成功！",
                  type:"success"
                });
                this.$router.replace('/admin');  
              }
          }else{
            this.$message({
              message: response.data.msg,
              type: 'error'
            });
          }
        })
        .catch(error => {
          alert(error.message)
        })
    },
    ...mapMutations('user', [
      'saveUser','saveSell','saveAdmin'
    ]),
    checkForm(){
      if(!this.name || !this.password){
        this.$message("用户名和密码不能为空");
        return false;
      }
      return true;
    }
  }
};
</script>
<style scoped>
.login {
  position: relative;
  margin: 200px auto 0 auto;
  font-size: 14px;
  width: 350px;
}
.login .lable{
  position: relative;
}
.login .lable label{
  position: absolute;
  width: 40px;
  height: 40px;
  left: 0;
  top: 10px;
  border-right: solid 1px #ccc;
}
i{
  margin-top: 12px;
}
.login .error{
  height: 30px;
  color: red;
  font-weight: bold;
  font-size: 0.5em;
} 
.identity label{
  margin-right: 10px;
}
.login .lable input{
  width: 300px;
  height: 20px;
  padding: 10px 0 10px 50px;
  font-size: 14px;
  margin: 10px 0px;
  outline: none;
  font-weight: 500;
  background: #eee;
  border: solid 1px #ccc;
}

.login .submit input{
    border: 1px solid #e85356;
    width: 352px;
    padding: 10px 0;
    margin: 15px 0px 30px 0px;
    background: #e4393c;
    color: #fff;
    cursor: pointer;
}

.login .toRegister{
  position: absolute;
  right: 0;
  bottom: 0;
}

a{
  text-decoration: none;
  color: #e4393c;
}
</style>