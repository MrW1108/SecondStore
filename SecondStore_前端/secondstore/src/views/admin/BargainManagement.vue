<template>
    <div class="bargain">
        <h3>交易抽成记录</h3>
        <el-table :data="successedOrders" stripe style="width: 100%">
            <el-table-column width="30"></el-table-column>
            <el-table-column type="index" width="50"></el-table-column>
            <el-table-column prop="id"  label="订单ID"  width="80"></el-table-column>
            <el-table-column prop="amount" label="总价"  width="100"></el-table-column>
            <el-table-column prop="level" label="商家等级" width="100"></el-table-column>
            <el-table-column prop="creatTime" label="订单开始时间" width="200"></el-table-column>
            <el-table-column prop="endTime" label="订单结束时间"  width="200"></el-table-column>
            <el-table-column prop="commission" label="抽成金额" :formatter="formatterCommission" width="100" ></el-table-column>
        </el-table>
    </div>
</template>

<script>
export default {
    name: 'Bargain',
    data(){
        return{
            successedOrders:[]
        }
    },
    created(){
        this.axios.get("/order",{params:{page:1}}).then(response=>{
            if(response.data.code == 0){
                this.successedOrders = response.data.data.records
            }
        })
    },
    methods:{
        formatterCommission(row){
            let commission = 0
            if(row.level == 1){
                commission = row.amount * 0.001
            }else if(row.level == 2){
                commission = row.amount * 0.002
            }else if(row.level == 3){
                commission = row.amount * 0.005
            }else if(row.level == 4){
                commission = row.amount * 0.0075
            }else
                commission = row.amount * 0.01
            return commission.toFixed(2)
        }
    }
}
</script>

<style>
.bargain{
    width: 860px;
    margin: 0 auto;
    margin-top: 20px;
}
.bargain h3{
    margin-bottom: 10px;
}
</style>