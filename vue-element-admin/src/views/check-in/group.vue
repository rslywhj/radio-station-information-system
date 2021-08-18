<template>
  <div class="app-container">
    <aside align="center">今日打卡情况</aside>
    <el-table :data="data" style="width: 100%;margin-top:30px;" border>
      <el-table-column align="center" label="打卡人" width="585">
        <template slot-scope="scope">
          {{scope.row.username}}
        </template>
      </el-table-column>
      <el-table-column align="center" label="打卡时间" width="585">
        <template slot-scope="scope">
          {{ scope.row.checkDatetime }}
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  import {selectGroup} from "@/api/check-in";
  export default {
    data() {
      return {
        data:{},
      }
    },
    created() {
      this.getList()
    },
    methods: {
      getList() {
        this.listLoading = true
        selectGroup(this.data).then(response => {
          this.data = response.data
          // Just to simulate the time of the request
        })
      },
      checkIn() {
        check_In().then(response =>{
          this.msg = response.msg
          console.log(this.msg)
          alert(this.msg)
          this.$router.go(0)
        })
        /* alert(this.msg)*/

      }
    }}
</script>
