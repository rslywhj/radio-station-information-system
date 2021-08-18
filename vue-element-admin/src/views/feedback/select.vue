<template>
  <div class="app-container">
    <el-table :data="articles" style="width: 100%;margin-top:30px;" border>
      <el-table-column align="center" label="id" width="400" v-if="show">
        <template slot-scope="scope">
          {{scope.row.feedbackId}}
        </template>
      </el-table-column>
      <el-table-column align="center" label="反馈内容" width="400">
        <template slot-scope="scope">
         {{scope.row.content}}
        </template>
      </el-table-column>
      <el-table-column align="center" label="反馈提交时间" width="300">
        <template slot-scope="scope">
          {{ scope.row.feedbackDate | parseTime('{y}-{m}-{d} {h}:{i}')}}
        </template>
      </el-table-column>
      <el-table-column align="center" label="反馈人" width="150">
        <template slot-scope="scope">
          {{ scope.row.feedbackUsername }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="反馈状态" width="150">
        <template slot-scope="scope">
          {{ scope.row.status }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button type="danger" size="small" @click="Tong(scope)">处理</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>

  import { selectFeedback, updateFeedback } from '@/api/feedback'

  export default {
    customer: "builder",
    /*数据*/
    data() {
      return {
        show:false,
        title:'',
        /*表中的数据显示*/
        articles: {},
      }
    },
    /*开启挂载*/
    created() {
      //挂载显示数据方法
      this.getArticles()
    },
    methods: {
      findArticle(scope) {
        var title=scope.row.articleTitle
      },
      /*获取表中的数据*/
      getArticles() {
        this.loading = true
        selectFeedback().then(response => {
          //console.log(" response:" + response)
          this.articles = response.data
          this.loading = false
        })
      },
      //解决问题
      Tong(scope) {
        this.$confirm('请确认已修复反馈?', '通过提醒', {
          confirmButtonText: '确认',
          cancelButtonText: '取消'
        })
          .then(async () => {
            updateFeedback(scope.row.feedbackId)
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
  #t{
    text-transform: capitalize;
    font-weight:bold;
  }

</style>
