<template>
  <div class="app-container">
    <el-table :data="serviceList" style="width: 100%;margin-top:30px;" border>
      <el-table-column align="center" label="编号" width="75">
        <template slot-scope="scope">
          <!--          {{ scope.$index + 1 }}-->
          {{scope.row.memberId}}
        </template>
      </el-table-column>
      <el-table-column align="center" label="名称" width="100">
        <template slot-scope="scope">
          {{ scope.row.memberName }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="密码" width="100">
        <template slot-scope="scope">
          {{ scope.row.password }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="用户名" width="150">
        <template slot-scope="scope">
          {{ scope.row.username }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="加入时间" width="100">
        <template slot-scope="scope">
          {{ scope.row.joinTime | parseTime('{y}-{m}-{d} {h}:{i}')}}
        </template>
      </el-table-column>
      <el-table-column align="center" label="创建时间" width="100">
        <template slot-scope="scope">
          {{ scope.row.createTime | parseTime('{y}-{m}-{d} {h}:{i}')}}
        </template>
      </el-table-column>
      <el-table-column align="center" label="更新时间" width="100">
        <template slot-scope="scope">
          {{ scope.row.updateTime | parseTime('{y}-{m}-{d} {h}:{i}')}}
        </template>
      </el-table-column>

      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button type="danger" size="small" @click="handleDelete(scope)">删除</el-button>
        </template>
      </el-table-column>

    </el-table>

  </div>
</template>

<script>
  import {findAll} from "@/api/member";

  const defaultServiceBuilder = {
    id: '',
    customer: '',
    serveType: '',
    options: [{
      value: '6',
      label: '咨询'
    }, {
      value: '7',
      label: '建议'
    }, {
      value: '8',
      label: '投诉'
    }],
    overview: '',
    serviceProce: '',
    serviceProcePeople:''
  }
  export default {
    customer: "builder",
    /*数据*/
    data() {
      return {
        serviceBuilder: Object.assign({}, defaultServiceBuilder),
        dialogType: 'new',
        dialogVisible: false,
        dialogVisible1: false,

        /*表中的数据显示*/
        serviceList: {},

        temp: {
          customer: '',   //客户名称
          dicValue: '',   //服务类型
          overview: '',   //概要
          serviceProce: '',     //服务类容
          createPeople: '',     //创建人
          serviceProcePeople: '',     //处理人
          createDate: '',      //创建时间
          upateTime: '',      //完成时间
          serviceProceResult: '',    //处理结果
          myd: '',      //客户评价
          serviceRequest:''   //处理内容
        },
      }
    },
    /*开启挂载*/
    created() {
      //挂载显示数据方法
      this.getserviceList()
    },
    methods: {
      /*获取表中的数据*/
      getserviceList() {
        this.loading = true
        findAll().then(response => {
          //console.log(" response:" + response)
          this.serviceList = response.data
          //console.log(this.serviceList)
          this.loading = false
        })
      },
      /*点击显示*/
      handleAddService() {
        this.serviceBuilder = Object.assign({}, defaultServiceBuilder)
        this.dialogType = 'new'
        this.dialogVisible = true
      },
      /*确定取消*/
      async confirmRole() {
        const isEdit = this.dialogType === 'edit'
        console.log("isEdit:" + isEdit)
        if (isEdit) {
          /*修改*/
          await updateRole(this.serviceBuilder)
        } else {
          /*console.log("serviceBuilder:" + JSON.stringify(this.serviceBuilder))*/
          //新增
          const {data} = await addServive(this.serviceBuilder)
          //更新数据
          this.getserviceList()
        }
        const {overview, customer, serveType} = this.serviceBuilder
        this.dialogVisible = false
        this.$notify({
          title: 'Success',
          dangerouslyUseHTMLString: true,
          message: `
            <div>客户姓名: ${customer}</div>
            <div>服务概要: ${overview}</div>
            <div>服务类型编号: ${serveType}</div>
          `,
          type: 'success'
        })
      },



      //删除
      handleDelete({$index, row}) {
        this.$confirm('确认删除该条数据吗?', '删除提醒', {
          confirmButtonText: '确认',
          cancelButtonText: '取消',
          type: 'warning'
        })
          .then(async () => {
            await removeCustomerservice(row.id)

            this.getserviceList()
            this.$message({
              type: 'success',
              message: '删除成功'
            })
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
