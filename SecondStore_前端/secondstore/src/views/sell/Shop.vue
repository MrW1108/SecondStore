<template>
    <div class="shop">
        <h1>{{sell.name}}的店铺<el-button class="addBt" icon="el-icon-plus" size="mini" @click="addCommodity">添加商品</el-button></h1>
        
        <Dialog @update="update" @dialogHide="dialogHide" :dialogVisible="dialogVisible" :addFlag="addFlag"  :form="form"/>
        
        <div class="waitCheck" v-if="waitCheck!=null">
            <h3>待审核商品</h3>
            <CommodityList @update="update" @updateCommodity="updateCommodity" :list="waitCheck" :isNeedMask="true"/>
        </div>
        <div class="soldOut" v-if="soldOut!=null">
            <h3>已下架商品</h3>
            <CommodityList @update="update" @updateCommodity="updateCommodity" :list="soldOut" />
        </div>
        <h3>店内商品</h3>
        <Commodities/>
    </div>
</template>
<script>
import { mapMutations, mapState } from 'vuex'
import CommodityList from '../../components/CommodityList.vue'
import Dialog from '../../components/Dialog.vue'
import Commodities from '../Commodities.vue'
export default {
    name: "Shop",
    computed: {
        ...mapState(['waitCheck','soldOut']),  //待审核商品
        ...mapState('user',['sell'])

    },
    components: { Commodities, CommodityList, Dialog },
    created(){
       this.getWaitCheckList(); //获得商家待审核商品列表
       this.getSoldOutList();   //获得商家已下架商品列表
    },
    data(){
        return{
            form:{},
            dialogVisible: false,
            addFlag:true,   //添加商品还是修改商品
            item:{},
        }
    },
    methods:{
        addCommodity(){
            this.form = {
                shopId:"",
                name: "华为手机",
                kindId:"1",
                price:"2988",
                discount: "1",//折扣
                instruction:"商品质量完好，商家信誉保证，大小适中，造型精致，非常适合",
                bargain:false,
            }
            this.addFlag = true;
            this.dialogVisible = true;
        },
        getWaitCheckList(){     //获得商家待审核商品列表
             this.axios.post('/commodity/searchOrder',{ status: [0], shop_id:[this.$route.params.id] }).then(response =>{
                if(response.data.code == 0){
                    this.getWaitCheck(response.data.data.records);
                }
            }).catch(error => alert(error));
        },
         getSoldOutList(){     //获得商家已下架商品列表
             this.axios.post('/commodity/searchOrder',{ status:[3], shop_id:[this.$route.params.id] }).then(response =>{
                if(response.data.code == 0){
                    this.getSoldOut(response.data.data.records);
                }
            }).catch(error => alert(error));
        },
        dialogHide(){
            this.dialogVisible = false;
        },
        ...mapMutations(['getWaitCheck','getSoldOut']),

        //子组件传值 更新商品
        updateCommodity(item){
            this.item = item;
            this.form = Object.assign({},item)
            this.addFlag = false;
            this.dialogVisible = true;
        },
        update(item){
            this.item = item;
        }
    }
}
</script>
<style scoped>
.shop{
    position: relative;
    color: #846e6e;
    width: 1200px;
    text-align: left;
    margin: 0 auto;
}
.shop h3{
    text-align: center;
    margin: 25px 0;
}
.shop .addBt{
    position: absolute;
    margin-left: 20px;
    top: 10px;
}

</style>