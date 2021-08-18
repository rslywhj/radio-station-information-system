<template>
  <div class="app-container">
    <el-table :data="articles" style="width: 100%;margin-top:30px;" border>
      <el-table-column align="center" label="编稿标题" width="350"  >
        <template slot-scope="scope">
          <span @click="findArticle(scope)">
            <router-link :to="{name:'arttt',params: {title:scope.row.articleTitle}}" id="t">{{scope.row.articleTitle}}</router-link><br>
          </span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="上交时间" width="250">
        <template slot-scope="scope">
          {{ scope.row.createTime | parseTime('{y}-{m}-{d} {h}:{i}')}}
        </template>
      </el-table-column>
      <el-table-column align="center" label="更改时间" width="250">
        <template slot-scope="scope">
          {{ scope.row.updateTime | parseTime('{y}-{m}-{d} {h}:{i}')}}
        </template>
      </el-table-column>
      <el-table-column align="center" label="作者" width="217">
        <template slot-scope="scope">
          {{ scope.row.author }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="状态" width="217">
        <template slot-scope="scope">
          {{ scope.row.status }}
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  import { selectByEdit } from '@/api/articles'

  const defaultServiceBuilder = {
    articleTitle: '',
    createTime: '',
    updateTime: '',
    author: '',
    status: ''
  }


  export default {
    customer: 'builder',
    /*数据*/
    data() {
      return {
        drawer: false,
        articless: Object.assign({}, defaultServiceBuilder),
        dialogType: 'new',
        dialogVisible: false,
        dialogVisible1: false,
        title: '',
        /*表中的数据显示*/
        articles: {}
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
        selectByEdit().then(response => {
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
