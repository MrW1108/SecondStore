<template>
    <div class="sellRateManagement">
        <h3>修改商家等级</h3>
        <el-table :data="sellList" stripe style="width: 100%">
            <el-table-column  width="20"></el-table-column>
            <el-table-column type="index" width="80"></el-table-column>
            <el-table-column prop="id" label="ID" width="100"></el-table-column>
            <el-table-column prop="name" label="商家名称" width="180"></el-table-column>
            <el-table-column prop="level" label="商家等级" width="180"></el-table-column>
            <el-table-column label="操作" width="190">
                <template slot-scope="scope">
                    <el-button size="mini" @click="handleChangeLevel(scope.row)">修改</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-dialog title="商家等级修改" :visible.sync="dialogVisible" width="400px" :before-close="handleClose">
            <el-select v-model="level">
                <el-option label="1" value="1"></el-option>
                <el-option label="2" value="2" ></el-option>
                <el-option label="3" value="3" ></el-option>
                <el-option label="4" value="4" ></el-option>
                <el-option label="5" value="5" ></el-option>
            </el-select>
            <span slot="footer" class="dialog-footer">
                <el-button size="mini" @click="dialogVisible = false">取消</el-button>
                <el-button size="mini" type="primary"  @click="changeLevel">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
    name: "sellRateManagement",
    data(){
        return{
            sellList:[],
            dialogVisible: false,
            level: 5,
            sell:null
        }
    },
    created(){
        this.axios.get("sell",{params:{page:1,status:1}}).then(response => {
            if(response.status == 200){
                this.sellList = response.data.data.records;
            }
        })
    },
    methods:{
        handleChangeLevel(row){
            this.sell = row;
            this.dialogVisible = true;
            this.level = row.level;
        },
        handleClose() {
            this.$confirm('确认关闭？')
            .then(() => {
                this.dialogVisible = false;
            })
            .catch(() => {});
        },
        changeLevel(){
            this.$confirm("确认修改？").then(()=>{
                this.sell.level = this.level;
                this.axios.post("/sell/update",this.sell).then(response => {
                    if(response.data.code == 0){
                        this.dialogVisible = false;
                        this.$message({type:'success',message:"修改成功！"});
                    }
                })
            })
        }
    }
}
</script>

<style>
.sellRateManagement{
    width: 750px;
    margin: 0 auto; 
    margin-top: 30px;
}

.sellRateManagement h3{
    margin-bottom: 10px;
}
</style>