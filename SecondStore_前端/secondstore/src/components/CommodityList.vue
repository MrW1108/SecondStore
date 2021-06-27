<template>
    <div class="commodityList">
        <ul>
            <li class="commodity" v-for="commodity in list" :key="commodity.id">
                <CommodityCard @updateCommodity='updateCommodity' @update='update' :item='commodity' :isNeedMask="isNeedMask"></CommodityCard>
            </li>
        </ul>
    </div>
</template>

<script>
import CommodityCard from '../components/CommodityCard'
export default {
    name: 'CommodityList',
    // 由于commodityList组件会被多个页面复用，并且请求的数据接口不同，所以只定义一个list prop用来接收父组件传递进来的商品列表数据
    props: {
        list: {
            type: Array,
            default: () => []
        },
        isNeedMask:{
            type: Boolean,
            default: () => false
        }
    },
    components:{
        CommodityCard
    },
    methods:{
        updateCommodity(value){
            this.$emit('updateCommodity',value);
        },
        update(value){
            this.$emit('update',value);
        }
    }
}
</script>

<style scoped>
*{
  padding: 0;
  margin: 0;
}

.commodityList{
  position: relative;
  margin-top: 20px;
  background-color: white;
  width: 100%;
  overflow: hidden;     /* 清除浮动*/
}

.commodityList .commodity{
  position: relative;
  float: left;
  width: 240px;
  height: 364px;
  margin-top: 5px;
  list-style: none;
  background-color: white;
}
</style>