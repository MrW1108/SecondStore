<template>
    <div class="recharge">
        <el-form ref="form" :inline="true" :model="form" class="demo-form-inline">
        <el-form-item label="选择用户" prop="name">
            <el-input v-model="form.name" placeholder="输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="用户类型" prop="identity">
            <el-select v-model="form.identity" placeholder="用户类型">
            <el-option label="普通用户" value="user"></el-option>
            <el-option label="商家" value="sell"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="searchUser">查询</el-button>
        </el-form-item>
        </el-form>

        <div v-if="userInfo" class="info">
            <p><span>用户名：</span>{{userInfo.name}}</p>
            <p><span>类型：</span>{{currentIdentity | formatIdentity}}</p>
            <p><span>账户余额：</span>{{userInfo.wallet}}</p>
            <el-form  :model="infoForm" ref="info" :rules="rules" :inline="true" >
                <el-form-item label="充值" prop="rechargeAmount">
                    <el-input type="number" v-model="rechargeAmount" placeholder="输入充值金额"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="recharge">充值</el-button>
                </el-form-item>
            </el-form>
        </div>

    </div>
</template>

<script>
export default {
    name:"recharge",
    data(){
        var checkRechargeAmount = (rule, value, callback) => {
            if (this.rechargeAmount=="") {
                return callback(new Error('充值金额不能为空'));
            }else {
                callback();
            }
        };
        return{
            form:{  //要查询的用户
                name: "",
                identity: "user"
            }, 
            infoForm:{},  //无用 为了使用表单的验证
            currentIdentity: "",
            userInfo: null, //查询得到的用户信息
            rechargeAmount: "",   //充值金额
            rules:{
                rechargeAmount:[{validator: checkRechargeAmount, trigger: 'blur'}]
            }
        }
    },
    methods:{
        searchUser(){   
            this.axios.post("/"+this.form.identity,{"name":this.form.name}).then(response => {
                if(response.data.code == 0){
                    console.log(response.data.data.length)
                    if(response.data.data.length == 1){
                        this.userInfo = response.data.data[0];
                        this.currentIdentity = this.form.identity;
                        this.$refs["form"].resetFields();   
                    }else{
                        this.$message("对不起，没有此用户！")
                    }
                }else{
                    this.$message("查询失败！")
                }
            });
        },

        //充值
        recharge(){
            this.$refs['info'].validate((valid) => {
                if (valid) {
                    this.userInfo.wallet = (Number)(this.userInfo.wallet) + (Number)(this.rechargeAmount);
                    this.axios.post("/"+this.currentIdentity+"/update",this.userInfo).then(response => {
                        if(response.data.code == 0){
                            this.rechargeAmount = "";
                            this.$message({
                                type: 'success',
                                message: "充值成功"
                            })
                        }
                    }).catch(error => {alert(error.message)})
                } else {
                    return false;
                }
            });
        }
    },
    filters:{
        formatIdentity: function(value){
            if(value=='user')   return "普通用户"
            else if(value=='sell') return "商家"
        }
    }
}
</script>

<style scoped>
.recharge{
    color: #846e6e;
    padding: 40px 60px;
    text-align: left;
}

.recharge .info{
    text-align: left;
}
p{
    margin: 20px 0;
}
span{
    color: #846e6e;
}
</style>