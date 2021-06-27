<template>
    <div class="account">
        <div class="wallet">
            <h3>我的钱包</h3>
            <h5>余额：</h5><span>{{wallet.toFixed(2)}}</span>
        </div>
        <div class="integral">
            <h3>我的积分</h3>
            <h5>积分：</h5><span>{{points}}</span>
        </div>
    </div>
</template>

<script>
import {mapState} from "vuex";
export default {
    name: "Account",
    data(){
        return{
            wallet:0,//余额
            points: 0,  //积分
        }
    },
    created(){
        this.axios.get('/user/'+this.user.id)
        .then(response => {
             if(response.status === 200){
                this.wallet = response.data.data.wallet;
                this.points = response.data.data.points;
            }
        })
    },
    computed:{
        ...mapState('user',{
            user: 'user'
        })
    }
}
</script>
<style scoped>
.account{
    width: 1200px;
    margin: 0 auto;
    text-align: left;
    height: 75vh;
    line-height: 30px;
}
.account .integral{
    margin-top: 30px;
}
</style>