<template>
  <div class="app-container">
    <el-calendar>
      <template
        slot="dateCell"
        slot-scope="{date, data}">
        <!--弹出框，显示详细安排信息-->
        <el-popover
          placement="top-start"
          trigger="hover">
          <div v-html="neirong"></div>
          <p slot="reference" :class="rqi.indexOf(data.day) > -1 ? 'is-selected' : ''" @click="handleBianJi(data.day)"
             v-on:mouseenter="viewDetails(data.day)" v-on:mouseout="clearCache()">
            {{ data.day.split('-').slice(1).join('-') }} {{ rqi.indexOf(data.day) > -1 ? '✔️' : ''}}
          </p>
        </el-popover>
      </template>
    </el-calendar>

    <!-- 添加或修改日程管理 - 日程对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!--<el-input type="hidden" v-model="form.id" v-bind:value="rqineirong[form.date].id" />-->
        <el-form-item label="时间" prop="date">
          <el-date-picker clearable size="small" style="width: 200px"
                          v-model="form.date"
                          type="date"
                          value-format="yyyy-MM-dd"
                          disabled="null">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="编辑" prop="edit">
          <!--<el-input v-model="form.edit" placeholder="请选择值班编辑" />-->
          <el-select v-model="form.edit" placeholder="请选择值班编辑" clearable :style="{ width: '200px' }">
            <el-option
              v-for="(item,index) in edit"
              :key="index"
              :label="item.name"
              :value="item"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="播音" prop="broadcast">
          <!--<el-input v-model="form.broadcast" placeholder="请选择值班播音" />-->
          <el-select v-model="form.broadcast" placeholder="请选择值班播音" clearable :style="{ width: '200px' }">
            <el-option
              v-for="(item1,index1) in broadcast"
              :key="index1"
              :label="item1.name"
              :value="item1"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="类型" prop="group">
          <el-select v-model="form.group" placeholder="请选择类型" clearable :style="{ width: '200px' }">
            <el-option value="英语"/>
            <el-option value="体育"/>
            <el-option value="文学"/>
            <el-option value="音乐"/>
            <el-option value="生活"/>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
        <el-button v-if="title === '编辑日程'" @click="deleteForm" :style="{ background: 'red',color:'white'}">删除
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { updateFdjjr, selectEdit, selectBroadcast, delFdjjr,selectWXEdit} from "@/api/date";

  export default {
    data() {
      return {
        //弹出框内容缓存
        neirong: "",
        //控制需要弹出的
        /* disabled: "false",*/
        //编辑列表
        edit: {},
        //播音列表
        broadcast: {},
        //日期数组
        rqi: [],
        //日期内容
        rqineirong: {},
        // 遮罩层
        loading: true,
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          date: [
            {required: true}
          ],
          edit: [
            {required: true, message: "编辑不能为空", trigger: "blur"}
          ],
          broadcast: [
            {required: true, message: "编辑不能为空", trigger: "blur"}
          ],
          group: [
            {required: true, message: "类型不能为空", trigger: "change"}
          ],
        },
        // 日程类型
        jjrlxOptions: [],
      };
    },
    created() {
      this.getList()
      this.getMember()
    },
    activated() {
      this.getList()
    },
    methods: {
      /*鼠标逗留，查看详情*/
      viewDetails(day) {
        if (this.rqi.indexOf(day) > -1) {
          this.neirong ="写稿编辑："+
            this.rqineirong[day].author
        }
        console.log(this.rqi.indexOf(day) > -1)
      },
      /*鼠标离开，清除缓存*/
      clearCache() {
        this.neirong = ""
      },
      /*只有有内容的才能显示弹出框*/
      /*canView(){
        this.
      },*/
      /** 查询日程管理 - 日程列表 */
      getList() {
        this.loading = true;
        this.rqineirong = {}
        selectWXEdit().then(response => {
          for (let i = 0; i < response.data.length; i++) {
            this.rqi.push(response.data[i].arrangeDate)
            this.rqineirong[response.data[i].arrangeDate] = response.data[i]
          }
          console.log("rqi" + this.rqi)
          console.log(this.rqineirong)
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          edit: undefined,
          broadcast: undefined,
          date: undefined,
          group: undefined,
          articleId: undefined
        };
        this.resetForm("form");
      },
      /** 提交按钮 */
      submitForm: function () {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != undefined) {
              updateFdjjr(this.form).then(response => {
                /*this.msgSuccess("修改成功");*/
                this.open = false;
                this.$message({
                  type: 'success',
                  message: '修改成功'
                })
                this.reset();
              });
            } else {
              addFdjjr(this.form).then(response => {
                /*this.msgSuccess("新增成功");*/
                this.open = false;
                this.$message({
                  type: 'success',
                  message: '新增成功'
                })
                this.reset();
              });
            }
            this.getList();
            /*this.$router.go(0); // 强制刷新，用户不友好 */
          }
        });
      },
      getMember() {
        selectEdit().then(response => {
          for (let i = 0; i < response.data.length; i++) {
            this.edit[i] = response.data[i]
          }
          console.log(this.edit)
        });
        selectBroadcast().then(response => {
          for (let i = 0; i < response.data.length; i++) {
            this.broadcast[i] = response.data[i]
          }
          console.log(this.broadcast)
        });
      },
      /** 编辑日期操作 */
      handleBianJi(day) {
        if (this.rqineirong[day]) {
          console.log(this.rqineirong[day])
          this.form = this.rqineirong[day];
          this.title = '编辑日程';
        } else {
          let obj = {};
          obj.date = day.valueOf()
          this.form = obj
          this.title = '新增日程';
        }
        this.open = true;
      },
      deleteForm() {
        delFdjjr(this.form.id).then(response => {
          this.open = false;
          this.$message({
            type: 'success',
            message: '删除成功'
          })
          this.reset();
          this.getList();
        });
      }
    }
  };
</script>
<style lang="scss" scoped>
  .is-selected {
    color: white;
    background: rgba(112,161,255, 0.8);
  }

  .el-calendar {
    background-color: rgba(255, 255, 255, 0.9);
  }
</style>
