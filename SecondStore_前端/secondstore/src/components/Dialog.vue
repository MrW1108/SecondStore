<template>
    <el-dialog
        :title="addFlag?'添加上架商品':'修改商品信息'"
        :visible.sync="dialogVisible"
        width="500px"
        :before-close="handleClose">
        <el-form :model="form" size="small" label-width="80px"  >
            <el-form-item label="商品名称">
                <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="商品类别">
                <el-select v-model="form.kindId" placeholder="请选择商品类别">
                    <el-option v-for="kind in kinds" :key="kind.id" :label="kind.name" :value="kind.id"></el-option>
                </el-select>
            </el-form-item> 
            <el-form-item label="商品价格">
                <el-input v-model="form.price"></el-input>
            </el-form-item>
            <el-form-item label="商品库存">
                <el-input v-model="form.inventory"></el-input>
            </el-form-item>
            <el-form-item label="优惠活动">
                <el-select v-model="form.discount">
                    <el-option label="原价" value="1"></el-option>
                    <el-option label="9折" value="0.9" ></el-option>
                    <el-option label="8折" value="0.8" ></el-option>
                    <el-option label="3折" value="0.3" ></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="是否议价">
                <el-switch v-model="form.bargain"></el-switch>
            </el-form-item>
            <el-form-item label="商品介绍">
                <el-input type="textarea" v-model="form.instruction"></el-input>
            </el-form-item>
            <el-upload
                class="upload-demo"
                :action="action"
                ref="upload"
                accept="image/*"
                :auto-upload="false"
                :on-preview="handlePreview"
                :on-remove="handleRemove"
                :data="u">
                <el-button slot="trigger" size="small" type="primary">点击上传</el-button>
                <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
            </el-upload>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="dialogHide">取消</el-button>
            <el-button type="primary" v-if="addFlag" @click="addCommodity">确 定</el-button>
            <el-button type="primary" v-if="!addFlag" @click="updateCommodity">确 定</el-button>
        </span>
    </el-dialog>
</template>
<script>
import { mapState } from 'vuex'
export default {
    name:"Dialog",
    props:['form','addFlag','dialogVisible'],
    created(){
        //获取商品分类信息
        this.axios.get('/kind').then(response => {
           if(response.status == 200){
               this.kinds = response.data.data; 
           }
       }).catch(error => this.$message(error.message));
    },
    computed:{
        ...mapState('user',{
            sell: 'sell'
        }),
    },
    data(){
        return{
            kinds:[],   //商品类别信息
            fileList:[],  //图片列表
            u:{     //upload提交时附加的data
                commodityId: 0
            },
            action: this.HOST+"/commodityPhoto"
        }
    },
    methods:{  
        //添加商品
        addCommodity(){     
            this.form.shopId = this.sell.id;
            if(this.form.bargain==false){      //0不许议价，1允许议价
                this.form.bargain=0;
            }else if(this.form.bargain==true){
                 this.form.bargain=1;
            }
            this.axios.post('/commodity',this.form).then(response=>{
                if(response.data.code==0){
                    this.u.commodityId = response.data.data;
                    this.$refs.upload.submit();     //手动提交upload
                    this.dialogHide();
                    // this.$router.go(0);
                    this.$message("添加成功");
                }else{
                    this.$message("添加失败！")
                }
            }).catch(error => alert(error));
            // this.dialogVisible = false;
        },
        
        //修改商品信息
        updateCommodity(){
            this.$confirm('确认修改？')
            .then(() => {
                if(this.form.bargain==false){      //0不许议价，1允许议价
                    this.form.bargain=0;
                }else if(this.form.bargain==true){
                        this.form.bargain=1;
                }
               this.axios.post("/commodity/update",this.form).then(response => {
                   if(response.data.code==0){
                       this.$emit('update',this.form);
                       this.dialogHide();
                       this.$router.go(0);
                       this.$message({
                           message:"修改成功",
                           type:"success"
                       })
                   }
               })
            })
            .catch(() => {});
        },

        //通知父组件关闭dialog
        dialogHide(){
            this.$emit("dialogHide");
        },

        //上传图片
        handleRemove(file, fileList) {
            console.log(file, fileList);
        },
        handlePreview(file) {
            console.log(file);
        },

        handleClose() {
            this.$confirm('确认关闭？')
            .then(() => {
                this.dialogHide();
            })
            .catch(() => {});
        },
    }
}
</script>