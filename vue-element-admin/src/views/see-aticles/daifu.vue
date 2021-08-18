<template>
  <div class="app-container">
    <el-table :data="articles" style="width: 100%;margin-top:30px;" border>
      <el-table-column align="center" label="编稿标题" width="400" >
        <template slot-scope="scope" >
          <span @click="findArticle(scope)">
            <router-link :to="{name:'art',params: {title:scope.row.articleTitle}}" id="t">{{scope.row.articleTitle}}</router-link><br>
          </span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="上交时间" width="300">
        <template slot-scope="scope">
          {{ scope.row.createTime | parseTime('{y}-{m}-{d} {h}:{i}')}}
        </template>
      </el-table-column>
      <el-table-column align="center" label="作者" width="150">
        <template slot-scope="scope">
          {{ scope.row.author }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="状态" width="150">
        <template slot-scope="scope">
          {{ scope.row.status }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button type="danger" size="small" @click="Tong(scope)">通过</el-button>
          <el-button type="danger" size="small" @click="dialogVisible = true">不通过</el-button>
          <el-dialog title="退回原因" :visible.sync="dialogVisible">
            <el-select v-model="value" clearable placeholder="请选择">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
            <span slot="footer" class="dialog-footer">
              <el-button @click="dialogVisible = false">取 消</el-button>
              <el-button type="primary" @click="BuTong(scope),dialogVisible = false">确认退回</el-button>
            </span>
          </el-dialog>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  import {selectAllF,updateStatusT,noUpdateStatusT} from "@/api/articles";

  const defaultServiceBuilder = {
    articleTitle: '',
    createTime: '',
    author: '',
    status:''
  }
  export default {
    customer: "builder",
    /*数据*/
    data() {
      return {
        options: [{
          value: '主题不符',
          label: '主题不符'
        }, {
          value: '格式有误',
          label: '格式有误'
        }, {
          value: '疑似含有敏感信息',
          label: '疑似含有敏感信息'
        }, {
          value: '写的太差了',
          label: '写的太差了'
        }, {
          value: '没了',
          label: '没了'
        }],
        value: '',
        label: '',
        articless: Object.assign({}, defaultServiceBuilder),
        dialogType: 'new',
        dialogVisible: false,
        dialogVisible1: false,
        title:'',
        /*表中的数据显示*/
        articles: {},
      }
    },
    /*开启挂载*/
    created() {
      //挂载显示数据方法
      this.getarticles()
    },
    methods: {
      findArticle(scope) {
        var title=scope.row.articleTitle
      },
      /*获取表中的数据*/
      getarticles() {
        this.loading = true
        selectAllF().then(response => {
          //console.log(" response:" + response)
          this.articles = response.data
          this.loading = false
        })
      },
      //通过
      Tong(scope) {
        console.log(scope.row.articleTitle)
        this.$confirm('确认通过这个稿子吗?', '通过提醒', {
          confirmButtonText: '确认',
          cancelButtonText: '取消'
        })
          .then(async () => {
            /*await removeCustomerservice(row.id)*/
            updateStatusT(scope.row.articleTitle)
            this.$message({
              type: 'success',
              message: '通过成功'
            })
            this.$router.go(0)
          })
          .catch(err => {
            console.error(err)
          })
      },
      //不通过
      BuTong(scope) {
        var status=this.value
        this.$confirm('确认退回这个稿子吗?', '通过提醒', {
            confirmButtonText: '确认',
            cancelButtonText: '取消'
          },
        )
          .then(async () => {
            noUpdateStatusT(status,scope.row.articleTitle)
            this.$message({
              type: 'success',
              message: '退回成功'
            })
            this.$router.go(0)
          })
          .catch(err => {
            console.error(err)
          })
      },
    }
  }
</script>

<style scoped>
</style>

