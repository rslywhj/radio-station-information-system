<template>
  <div class="app-container">
    <el-table :data="articles" style="width: 100%;margin-top:30px;" border>
      <el-table-column align="center" label="反馈内容" width="400">
        <template slot-scope="scope">
          {{scope.row.content}}
        </template>
      </el-table-column>
      <el-table-column align="center" label="反馈提交时间" width="400">
        <template slot-scope="scope">
          {{ scope.row.feedbackDate | parseTime('{y}-{m}-{d} {h}:{i}')}}
        </template>
      </el-table-column>
      <el-table-column align="center" label="反馈人" width="240">
        <template slot-scope="scope">
          {{ scope.row.feedbackUsername }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="反馈状态" width="244">
        <template slot-scope="scope">
          {{ scope.row.status }}
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>

  import { selectAll } from '@/api/feedback'

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
        selectAll().then(response => {
          //console.log(" response:" + response)
          this.articles = response.data
          this.loading = false
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
