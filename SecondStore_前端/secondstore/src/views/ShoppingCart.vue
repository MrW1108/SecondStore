<template>
  <div class="shoppingCart">
    <el-button class="operation" size="mini" @click="handleSetCartItems">清空购物车</el-button>
    <el-button class="operation" size="mini" @click="handleDeleteCartItem">删除</el-button>
    <el-table :data="commodities" stripe  @selection-change="handleSelectionChange" style="width: 100%">
      <el-table-column type="selection" width="50"></el-table-column>
      <el-table-column prop="name"  label="商品名称"  width="180"></el-table-column>
      <el-table-column prop="shopName"  label="店铺"  width="180"></el-table-column>
      <el-table-column prop="price" label="单价" :formatter="formatterPrice" width="180"></el-table-column>
      <el-table-column  prop="quantity" label="数量" width="130"></el-table-column>
      <el-table-column label="操作" width="180">
        <template slot-scope="scope"> 
          <el-button size="mini" @click="handleSubtract(scope.row)">-</el-button>
          <el-button size="mini" @click="handleAdd(scope.row)">+</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="amount" label="金额" :formatter="formatterAmount" width="180"></el-table-column>
    </el-table>
    <p>
      <span><button class="checkout" @click="checkout">结算</button></span>
      <span>总价：{{ cartTotalPrice | currency}}</span>
    </p>
  </div>
</template>

<script>
import { mapMutations, mapState } from 'vuex'
export default {
  name: "ShoppingCart",
  created(){
      this.axios.get("/shoppingCart/"+this.user.id,{params:{page:1}}).then(response => {
        if(response.status == 200){
          this.getItems(response.data.data.records);
        }
      })
  },
  computed: {
    ...mapState('cart', {
      commodities: 'items'
    }),
    ...mapState('user',['user']),
  },

  data(){
    return{
      cartTotalPrice: 0,   //要结算的商品总价
      selectCartItems:[], //选中的商品
    }
  },

  methods: {
    formatterAmount(row){
      return (row.quantity * row.price * row.discount).toFixed(2);  //保留两位小数（四舍五入）
    },
    formatterPrice(row){
      return row.discount * row.price;
    },
    ...mapMutations('cart',['getItems','reduceItemQuantity','deleteCartItem','incrementItemQuantity','setCartItems','getPayItems']),

    //减少数量
    handleSubtract(row){
      var quantity = row.quantity -1; 
      if(quantity <= 0){
        this.$confirm('您确定要删除商品吗？').then(() => {
            this.axios.post('shoppingCart/delete',[row]).then(response => {
              if(response.data.code == 0){
                this.deleteCartItem([row]);
              }
            }).catch(() =>{error => alert(error)});
        }).catch(() => {});
      }
      else{
        this.reduceItemQuantity({id:row.id,quantity:1})
        this.updateShoppingCartItem(row);
      }        
    },

    //增加数量
    handleAdd(row){
      this.incrementItemQuantity({id:row.id,quantity:1});
      this.updateShoppingCartItem(row);        
    },

    //更改购物车
    updateShoppingCartItem(row){
      this.axios.post('shoppingCart/update',row)
    },

    //选中购物车商品
    handleSelectionChange(val){   //val(Array)
      this.selectCartItems = val;
      this.cartTotalPrice = val.reduce((total, product) => {
        return total + product.price * product.discount * product.quantity
      },0).toFixed(2)
    },

    //选择商品删除（可多选）
    handleDeleteCartItem(){
      if(this.selectCartItems.length == 0)
        this.$message("请先选择要删除商品！")
      else{
        this.axios.post('shoppingCart/delete',this.selectCartItems).then(response => {
          if(response.data.code == 0){
            this.deleteCartItem(this.selectCartItems)
          }
        }).catch(() =>{error => alert(error)});
      }
    },

    //清空购物车
    handleSetCartItems(){
      this.$confirm('确定要清空购物车吗？').then(() => {
            this.axios.post('shoppingCart/delete',this.commodities).then(response => {
              if(response.data.code == 0){
                this.setCartItems();
              }
            }).catch(() =>{error => alert(error)});
        }).catch(() => {});
    },

    //结算
    checkout(){
      if(this.selectCartItems!=0){
        this.getPayItems(this.selectCartItems);
        this.$router.push("/check");
      }else{
        this.$message("请先选择商品！");
      }
    },
  }
};
</script>

<style scoped>
.shoppingCart {
  margin: 0 auto;
  text-align: center;
  width: 1080px;
  margin-top: 50px;
}
.shoppingCart span {
  float: right;
  padding-right: 15px;
}
.shoppingCart .checkout{
    float: right;
    width: 60px;
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

.shoppingCart .operation{
  float: right;
}
</style>