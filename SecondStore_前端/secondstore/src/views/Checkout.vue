<template>
  <div class="shoppingCart">
    <el-table :data="payItems" stripe style="width: 100%">
      <el-table-column prop="name"  label="商品名称"  width="180"></el-table-column>
      <el-table-column prop="shopName"  label="店铺"  width="180"></el-table-column>
      <el-table-column prop="price" label="单价" :formatter="formatterPrice" width="180"></el-table-column>
      <el-table-column  prop="quantity" label="数量" width="180"></el-table-column>
      <el-table-column prop="amount" label="金额" :formatter="formatterAmount" width="180"></el-table-column>
    </el-table>
    <p>
      <span><el-button size="mini" class="submit" @click="submitOrder">提交订单</el-button></span>
      <span>总价：{{ totalPrice | currency}}</span>
    </p>
  </div>
</template>

<script>
import { mapGetters, mapState } from 'vuex'
export default {
  name: "checkout",
  computed: {
    ...mapState('cart',['payItems']),
    ...mapState('user',['user']),
    ...mapGetters('cart',['totalPrice']),
  },
  methods: {
    formatterAmount(row){
      return (row.quantity * row.price * row.discount).toFixed(2);  //保留两位小数（四舍五入）
    },
    formatterPrice(row){
      return row.discount * row.price;
    },

    //提交订单
    submitOrder(){
      this.$confirm("确认提交订单？").then(() => {
        this.axios.post("/shoppingCart/checkOut",this.payItems).then(response => {
          if(response.data.code == 0){
            this.$router.replace("/order")
          }else{
            this.$message(response.data.msg);
          }
        }).catch(() =>{error => alert(error)});
      }).catch(() => {});
    },
  }
};
</script>

<style scoped>
.shoppingCart {
  margin: 0 auto;
  text-align: center;
  width: 900px;
  margin-top: 50px;
}
.shoppingCart span {
  float: right;
  padding-right: 15px;
}

.shoppingCart .submit{
    float: right;
    height: 30px;
    margin: 0;
    border: none;
    color: white;
    background-color: red;
    cursor: pointer;
}

.shoppingCart p{
  margin-top: 10px;
}
</style>