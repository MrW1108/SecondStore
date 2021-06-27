<template>
    <div class="headerCart">
        <!-- @click.prevent: 阻止a标签跳转，仅执行函数handleCart -->
        <router-link to="/cart"> 
            <span>购物车<span v-if="cartItemsCount!=0">{{cartItemsCount}}</span></span>
        </router-link>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
    name: 'HeaderCart',
    components: {},
    computed: {
        //cart模块带有命名空间
        //mapGetters辅助函数仅仅是将 store 中的 getter(state中派生出一些状态) 映射到局部计算属性
        //...：使用对象展开运算符将 getter 混入 computed 对象中
        ...mapGetters('cart',{
            //Vuex的store中定义的一个getter,得到购物车中商品的数量
            //将 this.cartItemsCount 映射为 this.$store.getters['cart/itemCount']
            cartItemsCount: 'itemsCount'
        })
    }
}
</script>

<style scoped>
*{
    margin: 0;
    padding: 0;
}
.headerCart {
    float: left; 
    text-align: center;
    width: 100px;
    height: 40px;
    line-height: 40px;
    cursor: pointer;  /* 设置鼠标样式 */
    margin-left: 50px;
    background-color:red;
    vertical-align: middle;
}

.headerCart a {
    text-decoration: none;
    color: white;  
}
</style>