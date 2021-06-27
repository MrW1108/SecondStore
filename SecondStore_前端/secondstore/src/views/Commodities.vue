<template>
    <div class="commodity">
        <!--商品排序-->
        <h3 v-if="!commodities.length">目前没有商品</h3>
        <div v-if="commodities.length">
            <div class="commoidtySort">
                <div class="sort">
                    <a href="javascript:;" class="bt curr" @click="handleGetCommodityList(0,'price',0)"><span>综合</span><i class="el-icon-d-caret"></i></a>
                    <a href="javascript:;" class="bt"  @click="handleGetCommodityList('1','inventory',1)"><span>销量</span><i class="el-icon-d-caret"></i></a>
                    <a href="javascript:;" class="bt"  @click="handleGetCommodityList('1','evaluation_count',2)"><span>评论数</span><i class="el-icon-d-caret"></i></a>
                    <a href="javascript:;" class="bt"  @click="handleGetCommodityList('1','price',3)"><span>价格</span><i class="el-icon-d-caret"></i>
                        <!-- <em><i class="el-icon-caret-top"></i><i class="el-icon-caret-bottom"></i></em> -->
                    </a>
                </div>
                <div class="selectPrice">  
                </div>
                <div class="page">
                    <a href="javascript:;" @click="prevPage" class="prev">&lt;</a>
                    <a href="javascript:;" @click="nextPage" class="next">&gt;</a>
                </div>
                <div class="result-sum">
                    共<span class="sum">{{total}}+ </span>件商品 
                </div>
            </div>
        </div>


        <commodityList v-if="sell" @update="update" @updateCommodity='updateCommodity' :list = "commodities" :isNeedMask="true" />   <!-- 商家加载 -->
        <Dialog v-if="sell" @update="update" @dialogHide="dialogHide" :dialogVisible="dialogVisible" :addFlag="false"  :form="form"/>   <!-- 只有商家和管理员加载 -->


        <commodityList v-if="!sell" :list = "commodities"/>  <!-- 普通用户加载 -->

        <h1>{{message}}</h1>
        <el-pagination
            :hide-on-single-page="commodities.length==0"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            @prev-click="handleCurrentChange"
            @next-click="handleCurrentChange"
            :current-page=currentPage
            :page-sizes=pageSizes
            :page-size=pageSize
            layout="total, sizes, prev, pager, next, jumper"
            :total=total>
        </el-pagination>
    </div>
</template>

<script>
import { mapState } from 'vuex';
import commodityList from '../components/CommodityList'
import Dialog from '../components/Dialog.vue'
export default {
    name: 'commodity',
    components: {
      commodityList,Dialog
    },
    data(){
        return {
            commodities:[],
            message: '',
            sortType: 1,     //0为降序，1位升序
            currentPage: 1,  //当前页数
            total: 0, //总条数
            pageSize: 10,  //页面大小，默认一页有10条
            pageSizes: [10],  //分页数量
            state:{ //记录当前排序状态
                index: -1,
                key: ""
            },
            form:{},//dialog组件传值,深拷贝后的
            item:{},//商品card中的原值
            dialogVisible: false,//dialog默认不显示           
        }
    },
    computed:{
        ...mapState('user',['user','sell']),
    },
    created(){
        const url = this.$route.fullPath;
        if(url.indexOf("/home")!=-1){
           this.getCommoditiesByHome();
        }else if(url.indexOf("/shop")!=-1){
            this.getCommoditiesByShop(this.sell.id)
        }
    },
    //分类商品、搜索结果、商家店铺商品都使用同一组件，但向服务端请求的接口不同，所以定义setRequestUrl方法来动态设置请求的URL 
   beforeRouteEnter(to, from, next){
      next(vm => {
        vm.title = to.meta.title;
        if(to.fullPath.indexOf("/searchOrder")!=-1){
            let keyword = to.query.keyword;
            vm.getCommoditiesBySearch(keyword);
        }else if(to.path.indexOf("/shop")!=-1){
            vm.getCommoditiesByShop(to.params.id);
        }else{
            vm.getCommoditiesByKind(to.params.id);
        }
      });
    },
    
    beforeRouteUpdate(to, from, next){
        //在当前路由改变，但是该组件被复用时调用
        if(to.fullPath.indexOf("/searchOrder")!=-1){
            let keyword = to.query.keyword;
            this.getCommoditiesBySearch(keyword);
        }else if(to.path.indexOf("/shop")!=-1){
            this.getCommoditiesByShop(to.params.id);
        }else{
            this.getCommoditiesByKind(to.params.id);
        }
        next(); //该函数之后组件实例才会被创建
    },

    //分类商品、搜索结果、商家店铺商品都使用同一组件，但向服务端请求的接口不同，所以定义不同的接口
    methods:{
        getCommoditiesByHome(){      //获取主页商品
             this.axios.get('/commodity',{params:{page:this.currentPage}}).then(response => {
                if(response.status == 200){
                    this.commodities = response.data.data.records; 
                    this.total = response.data.data.total;
                }
            })
        },

        getCommoditiesByKind(kindID){       //类别使用的方法
            this.axios.post("/commodity/searchOrder",{kind_id:[kindID]}).then(response =>{
                if(response.data.code==0){
                    this.commodities = response.data.data.records;
                    this.total = response.data.data.total;
                }
            }).catch(error => alert(error));
        },

        getCommoditiesBySearch(keyword){       //搜索框使用该方法
            this.axios.post("/commodity/searchOrder",{"keyword":[keyword]}).then(response =>{
                if(response.status == 200){
                    this.commodities = response.data.data.records;
                    this.total = response.data.data.total;
                }
            }).catch(error => alert(error));
        },

        getCommoditiesByShop(shopID){   //获取店铺商品
            this.axios.post("/commodity/searchOrder",{shop_id:[shopID]}).then(response =>{
                if(response.data.code==0){
                    this.commodities = response.data.data.records;
                    this.total = response.data.data.total;
                }
            }).catch(error => alert(error));
        },

        handleSizeChange(val){
            console.log("每页条数："+val);
        },

        //当前页改变，获取当前页的商品
        handleCurrentChange(val){
            this.currentPage = val;
            if(this.state.index != -1){   //说明处于排序状态
                if(this.state.key == 'price' && this.state.index == 0)
                    this.getCommodityList('0',this.state.key,this.state.index);
                else this.getCommodityList('1',this.state.key,this.state.index);            
            }else  
                this.getCommoditiesByHome();
        },
        //向前翻页
        prevPage(){
            if(this.currentPage>1){
                this.currentPage--;
                if(this.state.index != -1){  
                    if(this.state.key == 'price' && this.state.index == 0)
                        this.getCommodityList('0',this.state.key,this.state.index);
                    else this.getCommodityList('1',this.state.key,this.state.index);            
                }else  
                    this.getCommoditiesByHome();
            }
        },
        //向后翻页
        nextPage(){
            if(this.currentPage<(this.total/this.pageSize)){
                this.currentPage++;
                if(this.state.index != -1){  
                    if(this.state.key == 'price' && this.state.index == 0)
                        this.getCommodityList('0',this.state.key,this.state.index);
                    else this.getCommodityList('1',this.state.key,this.state.index);            
                }else  
                    this.getCommoditiesByHome();
            }
        },

        handleGetCommodityList(type, key, index){
            this.currentPage = 1;      //切换排序时重置为第一页
            this.getCommodityList(type, key, index);
        },
        //获取排序后的商品列表
        getCommodityList(type, key, index){
            this.state.index = index;
            this.state.key = key;
            this.changeButtonStyle(index);
            var map = {};
            map.page = [this.currentPage];
            if(this.$route.fullPath.indexOf("/searchOrder")!=-1){   //带关键词的排序
               map.keyword = [this.$route.query.keyword]
            }
            else if(this.$route.path.indexOf("/kind")!=-1){  //类别商品的排序
                map.kind_id = [this.$route.params.id]
            }
            else if(this.$route.path.indexOf("/shop")!=-1){
                map.shop_id = [this.$route.params.id]
            }
            if(type=="1"){
                map.desc = [key]     //降序 参数为关键词数组，可以对多个关键词进行排序
            }else if(type == "0"){
                map.asc = [key]  //升序
            }
            this.axios.post("/commodity/searchOrder", map).then(response => {
                if(response.status == 200){
                    this.commodities = response.data.data.records;
                    this.total = response.data.data.total;
                }
            }).catch(error => alert(error));
        },

        //点击按钮实现选中效果
        changeButtonStyle(index){
            var bt = document.querySelectorAll("a.bt");     //选择所有class="bt"的a元素
            for(let i=0;i<bt.length;i++){
                if(index == i){
                    bt[i].classList.add("curr");
                }else{
                    bt[i].classList.remove("curr");
                }
            }
        },


        // 商家系统中的操作
        //修改商品信息
        updateCommodity(value){
            this.item = value;
            this.form = Object.assign({},value);
            this.dialogVisible = true;
        },
        dialogHide(){
            this.dialogVisible = false;
        },
        update(value){    //更改原值，vuex
            this.item = value;
        },
    }
}
</script>
<style scoped>
.commodity{
    width: 1200px;
    margin: 0 auto;
    padding-bottom: 50px;
    font-size: 12px;
}
.commodity .commoidtySort{
    padding: 6px 8px;
    border-bottom: 1px solid #E7E3E7;
    background: #F1F1F1;
    overflow: hidden;
}
.commodity .commoidtySort .sort .curr{
    border-color: #e4393c;
    background: #e4393c;
    color: #FFF;
}
.commoidtySort .sort a{
    float: left;
    padding: 0 9px;
    height: 23px;
    border: 1px solid #CCC;
    line-height: 23px;
    text-decoration: none;
    color: black;
}
.commoidtySort .result-sum{
    float: right;
    height: 25px;
    line-height: 25px;
    margin-right: 10px;
    margin-right: 10px;
    color: #999;
}

.commoidtySort .result-sum .sum{
    color: #666;
    font-family: verdana;
}
.commoidtySort .sort a:hover{
    color: red;
    border-color: red;
}
.commoidtySort .page{
    float: right;
}
.commoidtySort .page a:hover{
    border-color: rgb(233, 103, 103);
}
.commoidtySort .page .prev,.next{
    display: inline-block;
    width: 46px;
    height: 23px;
    border: 1px solid #DDD;
    background: #FFF;
    line-height: 23px;
    font-family: "\5b8b\4f53";
    text-align: center;
    font-size: 16px;
    color: #AAA;
    text-decoration: none;
}
</style>