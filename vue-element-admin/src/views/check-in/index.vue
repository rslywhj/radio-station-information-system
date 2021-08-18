<template>
  <div class="app-container">
    <el-button  type="primary" @click.prevent.stop="checkIn">
      签到
    </el-button>
   <aside align="center">打卡记录</aside>
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
  import {check_In} from "@/api/check-in";

  import {selectPersonCheck} from "@/api/check-in";
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
      selectPersonCheck(this.data).then(response => {
        this.data = response.data
        // Just to simulate the time of the request
      })
    },
    checkIn() {
      check_In().then(response =>{
        this.msg = response.msg
        console.log(this.msg)
        this.$message.success(this.msg)
        this.getList()
      })
      /* alert(this.msg)*/

    }
  }}
</script>
