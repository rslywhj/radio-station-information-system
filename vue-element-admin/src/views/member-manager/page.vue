<template>
  <div class="app-container">
    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
      @sort-change="sortChange"
    >
      <!--row 相当与你的一行数据，不需要修改直接用就可以 -->
      <!--把你查询的信息放在（this.list = response.data） 括号内容ctrl+f-->
      <!--id-->
      <el-table-column align="center" label="编号" width="150">
        <template slot-scope="scope">
          {{scope.row.memberId}}
        </template>
      </el-table-column>
      <el-table-column align="center" label="名称" width="150">
        <template slot-scope="scope">
          {{ scope.row.memberName }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="密码" width="150">
        <template slot-scope="scope">
          {{ scope.row.password }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="用户名" width="150">
        <template slot-scope="scope">
          {{ scope.row.username }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="加入时间" width="150">
        <template slot-scope="scope">
          {{ scope.row.joinTime | parseTime('{y}-{m}-{d} {h}:{i}')}}
        </template>
      </el-table-column>
      <el-table-column align="center" label="创建时间" width="150">
        <template slot-scope="scope">
          {{ scope.row.createTime | parseTime('{y}-{m}-{d} {h}:{i}')}}
        </template>
      </el-table-column>
      <el-table-column align="center" label="更新时间" width="150">
        <template slot-scope="scope">
          {{ scope.row.updateTime | parseTime('{y}-{m}-{d} {h}:{i}')}}
        </template>
      </el-table-column>

      <el-table-column align="center" label="操作" >
        <template slot-scope="scope">
          <el-button type="danger" size="small" @click="deleteForm(scope.row.memberId)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--分页不需要动-->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="listQuery.page"
      :limit.sync="listQuery.limit"
      @pagination="getList"
    />

  </div>
</template>

<script>
  /*这个包是按钮水波纹的引入 可以用*/
  import waves from '@/directive/waves' // waves directive
  /*这个包内部是对分页的一些数据的操作*/
  import Pagination from '@/components/Pagination'
  import {findAll ,delMember} from "@/api/member";

  export default {
    name: 'ComplexTable',
    components: { Pagination },
    directives: { waves },
    data() {
      return {
        listLoading: true,
        /*存放分页信息*/
        listQuery: {
          page: 1,
          limit: 20
        },
      }
    },
    created() {
      this.getList()
    },
    methods: {
      getList() {
        this.listLoading = true
        findAll(this.listQuery).then(response => {
          this.list = response.data.list
          this.total = response.data.total
          console.log('listQuery=' + this.listQuery)
          this.total = response.data.total
          // Just to simulate the time of the request
          setTimeout(() => {
            this.listLoading = false
          }, 1.5 * 1000)
        })
      },
      deleteForm(memberId){
        console.log(memberId)
        delMember(memberId).then(response => {
          this.open = false;
          this.$message({
            type: 'success',
            message: '删除成功'
          })
          this.reset();
          this.getList();
        });
      }
    },
  }
</script>


