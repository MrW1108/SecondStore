<template>
    <div class="userAudit">
        <h3>普通用户</h3>
        <el-table :data="userList"  style="width: 80%">
            <el-table-column prop="id" label="ID" width="180">
            </el-table-column>
            <el-table-column label="姓名" width="180">
            <template slot-scope="scope">
                <el-popover trigger="hover" placement="top">
                <p>姓名: {{ scope.row.name }}</p>
                <p>电话: {{ scope.row.phone }}</p>
                <p>城市: {{ scope.row.city }}</p>
                <div slot="reference" class="name-wrapper">
                    <el-tag size="medium">{{ scope.row.name }}</el-tag>
                </div>
                </el-popover>
            </template>
            </el-table-column>
            <el-table-column
            prop="status"
            label="状态"
            width="150"
            :filters="[{ text: '待审核', value: 0 }, { text: '审核通过', value: 1 },{ text: '审核失败', value: 2 }]"
            :filter-method="filterTag"
            filter-placement="bottom-end">
            <template slot-scope="scope">
                <el-tag
                :type="getTap(scope.row)"
                disable-transitions>{{scope.row.status | statusValue}}</el-tag>
            </template>
            </el-table-column>
            <el-table-column label="审核结果">
            <template slot-scope="scope">
                <el-button
                size="mini"
                @click="handleEdit(scope.row,'user')">通过</el-button>
                <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.row,'user')">失败</el-button>
            </template>
            </el-table-column>
        </el-table>


        <h3>商家</h3>
        <el-table :data="sellList"  style="width: 80%">
            <el-table-column prop="id" label="ID" width="180">
            </el-table-column>
            <el-table-column label="姓名" width="180">
            <template slot-scope="scope">
                <el-popover trigger="hover" placement="top">
                <p>姓名: {{ scope.row.name }}</p>
                <p>电话: {{ scope.row.phone }}</p>
                <div slot="reference" class="name-wrapper">
                    <el-tag size="medium">{{ scope.row.name }}</el-tag>
                </div>
                </el-popover>
            </template>
            </el-table-column>
            <el-table-column
            prop="status"
            label="状态"
            width="150"
            :filters="[{ text: '待审核', value: 0 }, { text: '审核通过', value: 1 },{ text: '审核失败', value: 2 }]"
            :filter-method="filterTag"
            filter-placement="bottom-end">
            <template slot-scope="scope">
                <el-tag
                :type="getTap(scope.row)"
                disable-transitions>{{scope.row.status | statusValue}}</el-tag>
            </template>
            </el-table-column>
            <el-table-column label="审核结果">
            <template slot-scope="scope">
                <el-button
                size="mini"
                @click="handleEdit(scope.row,'sell')">通过</el-button>
                <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.row,'sell')">失败</el-button>
            </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
export default {
    name: 'UserAudit',
    data(){
        return{
            userList:[],
            sellList:[],
        }
    },
    created(){
        this.axios.get("/user",{params:{page:1}}).then(response => {
            if(response.data.code==0){
                this.userList = response.data.data.records;
            }
        });
        this.axios.get("/sell",{params:{page:1}}).then(response => {
            if(response.data.code==0){
                this.sellList = response.data.data.records;
            }
        });
    },
    methods:{
        handleEdit(row,identity){
            this.$confirm("确认审核通过？").then(()=>{
                row.status = 1;
                this.axios.post(identity+'/update',row).then(response =>{
                    if(response.data.code == 0){
                        this.$message({
                            type:'success',
                            message:"操作成功！"
                        })
                    }else{
                        this.$message("操作失败！")
                    }
                });
            }).catch(() => {});
        },

        handleDelete(row,identity){
            this.$confirm("确认审核失败？").then(()=>{
                row.status = 3;
                this.axios.post(identity+'/update',row).then(response =>{
                    if(response.data.code == 0){
                        this.$message({
                            type:'success',
                            message:"操作成功！"
                        })
                    }else{
                        this.$message("操作失败！")
                    }
                });
            }).catch(() => {});
        },

        filterTag(value, row) {
            return row.tag === value;
        },

        getTap(row){
            if(row.status =='0'){
                return ""
            }else if(row.status == '1'){
                return "success"
            }else
                return "danger"
        }
    },
    filters:{
        statusValue: function(value){
            if(value=='0'){
                return "待审核"
            }else if(value == '1'){
                return "审核通过"
            }else
                return "审核失败"
        }
    }
}
</script>

<style scoped>
h3{
    
    margin: 20px 0;
}
.el-table{
    margin: 0 auto;
}
</style>