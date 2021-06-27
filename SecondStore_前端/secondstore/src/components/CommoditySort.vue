<template>
    <div class="commoidtySort">
        <div class="sort">
            <a href="javascript:;" class="curr" @click="getCommodityList(0,'price')"><span>综合</span><i class="arrow"></a>
            <a href="javascript:;"  @click="getCommodityList('')"><span>销量</span><i class="arrow"></a>
            <a href="javascript:;"  @click="getCommodityList(0,'comment')"><span>评论数</span><i class="arrow"></a>
            <!-- <a href="javascript:;" @click="getCommodityList('')"><span>新品</span><i class="arrow"></a> -->
            <a href="javascript:;"  @click="getCommodityList(sortType,'price')"><span>价格</span><i class="arrow"></a>
        </div>
        <div class="selectPrice">
            
        </div>
        <div class="result-sum">
            共<span>list.l</span>件商品 
        </div>
        <div class="page">
            
        </div>
    </div>
</template>

<script>
export default {
    name: "CommoditySort",
    data(){
        return{
            sortType: 1     //0为降序，1位升序
        }
    },
    methods:{
        getCommodityList(type, key){    //排序方式默认都是降序，key为排序关键字
            
            //分类商品和搜索结果使用同一组件，但向服务端请求的接口不同，所以动态设置url
            let url = this.$route.fullPath;     //fullPath 带参数的路由地址
            if(url.indexOf('/category')!=-1){
                url = "/commodity" + url;
            }
            this.axios.get(url).then(response => {
                if(response.status == 200){
                    this.$emit("sortList",response.data);   //子组件给父组件传参，传递排序后的商品列表
                }
            }).catch(error => alert(error));
            
            if(key == "price"){  //点击按钮切换排序方式
                if(this.sortType == 1){
                    this.sortType = 0;
                }else{
                    this.sortType = 1;
                }
            };
        }
    }
}
</script>

<style scoped>
.commoidtySort{
    width: 100%;
    padding: 6px 8px;
    border-bottom: 1px solid #E7E3E7;
}
.commoidtySort .sort .curr{
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
}
</style>