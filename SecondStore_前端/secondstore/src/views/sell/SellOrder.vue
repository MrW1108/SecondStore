<template>
    <div class="order">
        <el-table :data="orders" stripe style="width: 100%">
            <el-table-column width="30"></el-table-column>
            <el-table-column prop="commodityId"  label="商品ID"  width="80"></el-table-column>
            <el-table-column prop="quantity"  label="数量"  width="80"></el-table-column>
            <el-table-column prop="amount" label="总价"  width="100"></el-table-column>
            <el-table-column  prop="creatTime" label="订单开始时间" width="200"></el-table-column>
            <el-table-column prop="endTime" label="订单结束时间"  width="200"></el-table-column>
            <el-table-column prop="status" label="标签" width="140"
                :filters="[{ text: '待发货', value: 2},{ text: '已收货', value: 4 },
                { text: '退货待审核', value: 5 },{ text: '已完成', value: 8 }]"
                :filter-method="filterTag"
                filter-placement="bottom-end">
                <template slot-scope="scope">
                    <el-tag
                    :type="scope.row.status === 8 ? 'success' : 'primary'"
                    disable-transitions>{{scope.row.status | formatStatus }}</el-tag>
                </template>
            </el-table-column>
            <el-table-column label="操作" width="200">
                <template slot-scope="scope">
                    <el-button v-if="determineType(scope.row.status,'发货')" size="mini" type="danger" @click="handleSend(scope.row)">发货</el-button>
                    <el-button v-if="determineType(scope.row.status,'退货')" size="mini"  @click="handleAgreeReturn(scope.row)">退货</el-button>
                    <el-button v-if="determineType(scope.row.status,'拒绝退货')" size="mini"  @click="handleNotAgreeReturn(scope.row)">拒绝退货</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
import { mapState } from 'vuex';
export default {
    name: "SellOrder",
    data(){
        return{
            orders:[],
        }
    },
    created(){
        this.axios.get("/order/shop/"+this.sell.id,{params:{page:1}}).then(response => {
            if(response.status == 200){
                this.orders = response.data.data.records;
            }
        })
    },
    computed:{
        ...mapState('user',['sell']),
    },
    methods:{
        filterTag(value, row) {
            return row.status === value;
        },
        //发货
        handleSend(row){
            this.$confirm("确认发货?").then(() =>{
                row.status = 3;
                this.axios.post("/order/update",row).then(response => {
                    if(response.data.code == 0){
                        this.$message({type:'success',message:"发货成功！"})
                    }else{
                        this.$message(response.data.msg)
                    }
                }).catch(() => {error => alert(error)});
            }).catch(()=>{})
        },
        //同意退货
        handleAgreeReturn(row){
            this.$confirm("确认同意退货?").then(() =>{
                row.status = 6;
                this.axios.post("/order/update",row).then(response => {
                    if(response.data.code == 0){
                        this.$message({type:'success',message:"退货成功！"})
                    }else{
                        this.$message(response.data.msg)
                    }
                }).catch(() => {error => alert(error)});
            }).catch(()=>{})
        },
        //拒绝退货
        handleNotAgreeReturn(row){
            this.$confirm("确认拒绝退货?").then(() =>{
                row.status = 7;
                this.axios.post("/order/update",row).then(response => {
                    if(response.data.code == 0){
                        this.$message({type:'success',message:"拒绝退货成功！"})
                    }else{
                        this.$message(response.data.msg)
                    }
                }).catch(() => {error => alert(error)});
            }).catch(()=>{})
        },

        
        //判断订单处于哪一阶段，显示该阶段的操作
        determineType(status, operation){
            if(operation == "发货"){
                if(status == 2) return true
            }else if(operation == "退货"){
                if(status == 5) return true
            }else{  //拒绝退货
                if(status == 5) return true
            }
            return false
        },
    },
    filters:{
        formatStatus(val){
            let value = "";
            switch(val) {
                case 2:
                    value = "待发货";
                    break;
                case 3:
                    value = "运输中";
                    break;
                case 4:
                    value = "已收货";
                    break;
                case 5:
                    value = "退货待审核";
                    break;
                case 6:
                    value = "已退货";
                    break;
                case 7:
                    value = "拒绝退货";
                    break;
                case 8:
                    value = "已完成";
                    break;                    
            }
            return value;
        }
    }
}
</script>

<style scoped>
    .order{
        width: 1030px;
        margin: 0 auto;
        margin-top: 50px;
    }
</style>