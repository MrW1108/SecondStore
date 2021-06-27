<template>
    <div class="commodityAudit">
        <h3>待审核商品</h3>
        <CommodityList :list="list" :isNeedMask="true"/>
    </div>
</template>
<script>
import CommodityList from '../../components/CommodityList.vue';
export default {
  components: { CommodityList },
    name: 'CommodityAudit',
    data(){
        return{
            list:[]
        }
    },
    created(){
        this.axios.post("/commodity/audit",{"status": 0}).then(response => {
            if(response.status == 200){
                this.list = response.data.data;
            }
        }).catch(() => {});
    }
}
</script>