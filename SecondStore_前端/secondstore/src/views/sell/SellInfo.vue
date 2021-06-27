<template>
    <div class="sellInfo">
        <div class="wallet">
            <h3>我的钱包</h3>
            <h5>余额：</h5><span>{{wallet}}</span>
        </div>
        <div class="integral">
            <h3>我的等级</h3>
            <h5>等级：</h5><span>{{level}}</span>
        </div>
    </div>
</template>

<script>
import {mapState} from "vuex";
export default {
    name: "sellInfo",
    computed:{
        ...mapState('user',{
            sell: 'sell'
        })
    },
    data(){
        return{
            wallet:0,
            level:"",
            count:0
        }
    },
    created(){
        this.axios.get('/sell/'+this.sell.id)
        .then(response => {
             if(response.status === 200){
                 this.wallet = response.data.data.wallet;
                 this.level = response.data.data.level;
            }
        })
    },
}
</script>
<style scoped>
.sellInfo{
    width: 1200px;
    margin: 0 auto;
    text-align: left;
    height: 75vh;
    line-height: 30px;
}
.sellInfo .integral{
    margin-top: 30px;
}
</style>