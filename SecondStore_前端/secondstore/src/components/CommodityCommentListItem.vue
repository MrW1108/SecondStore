<template>
  <div class="commodityCommentListItem">
    <div>
      <span>{{ item.name }}:</span>
    </div>
    <el-rate v-model="item.score" :colors="colors" disabled></el-rate>
    <div>{{ item.evaluation }}</div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
  export default {
    name: 'commodityCommentListItem',
    props: {
      item: {
        type: Object,
        default: () => {}
      }
    },
    computed:{
      ...mapState('user',['user'])
    },
    data(){
      return{
        colors: ['#99A9BF', '#F7BA2A', '#FF9900']   //星级评定不同分数的颜色
      }
    }
  }
</script>

<style scoped>
.commodityCommentListItem{
  border-bottom: solid 1px rgb(240, 230, 230);
  margin-bottom: 10px;
  font-size: 12px;
}
.commodityCommentListItem span{
  margin-right: 20px;
}
</style>