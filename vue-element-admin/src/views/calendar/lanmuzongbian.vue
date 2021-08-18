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
          <p slot="reference" :class="rqi2.indexOf(data.day) > -1 ? 'is-anpai' :rqi.indexOf(data.day) > -1?'is-selected':rqi3.indexOf(data.day) >-1 ?'is-xiegao':'is-noanpai'
          " @click="handleBianJi(data.day)" v-on:mouseenter="viewDetails(data.day)" v-on:mouseout="clearCache()">{{
            data.day.split('-').slice(1).join('-') }} {{ rqi.indexOf(data.day) > -1 ? '' : ''}}
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
          <el-select v-model="form.edit" placeholder="值班编辑" clearable :style="{ width: '200px' }" disabled="null">
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
          <el-select v-model="form.broadcast" placeholder="值班播音" clearable :style="{ width: '200px' }" disabled="null">
            <el-option
              v-for="(item1,index1) in broadcast"
              :key="index1"
              :label="item1.name"
              :value="item1"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="类型" prop="group">
          <el-select v-model="form.group" placeholder="类型" clearable :style="{ width: '200px' }" disabled="null">
            <el-option value="英语"/>
            <el-option value="体育"/>
            <el-option value="文学"/>
            <el-option value="音乐"/>
            <el-option value="生活"/>
          </el-select>
        </el-form-item>

        <el-form-item label="稿件" prop="article">
          <!--<el-input v-model="form.broadcast" placeholder="请选择值班播音" />-->
          <el-select v-model="form.article" placeholder="请选择安排的稿件" clearable :style="{ width: '200px' }">
            <el-option
              v-for="(item2,index2) in article"
              :key="index2.articleId"
              :label="item2.articleTitle"
              :value="item2.articleTitle"
            />
          </el-select>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
        <!--<el-button v-if="title === '编辑日程'" @click="deleteForm" :style="{ background: 'red',color:'white'}">删除
        </el-button>-->
      </div>
    </el-dialog>

    <!-- 添加写稿安排 对话框 -->
    <el-dialog :title="title" :visible.sync="open2" width="500px" append-to-body>
      <el-form ref="form2" :model="form2" :rules="rules2" label-width="80px">
        <el-form-item label="截止时间" prop="arrangeDate">
          <el-date-picker clearable size="small" style="width: 200px"
                          v-model="form2.arrangeDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          disabled="null">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="写稿编辑" prop="author">
          <el-select v-model="form2.author" placeholder="请选择写稿编辑" clearable :style="{ width: '200px' }">
            <el-option
              v-for="item in edit"
              :key="item"
              :label="item"
              :value="item"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm2">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
        <el-button v-if="title === '编辑写稿安排'" @click="deleteForm2" :style="{ background: 'red',color:'white'}">删除
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {
    listFdjjr,
    editArrange,
    update2,
    selectArticle,
    delFdjjr,
    selectEdit,
    selectArrange,
    deleteArrange
  } from "@/api/date";

  export default {
    data() {
      return {
        //控制弹出框是否弹出
        visible:false,
        //弹出框内容缓存
        neirong: "",
        //控制需要弹出的
        /* disabled: "false",*/
        //编辑列表
        edit: {},
        //播音列表
        broadcast: {},
        //稿子列表
        article: {},
        //日期数组
        rqi: [],
        rqi2: [],
        rqi3: [],
        //日期内容
        rqineirong: {},
        editneirong: {},
        // 遮罩层
        loading: true,
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        open2: false,
        // 表单参数
        form: {},
        form2: {},
        // 表单校验
        rules: {
          date: [
            {}
          ],
          edit: [
            {message: "编辑不能为空", trigger: "blur"}
          ],
          broadcast: [
            {message: "编辑不能为空", trigger: "blur"}
          ],
          group: [
            {message: "类型不能为空", trigger: "change"}
          ],
          article: [
            {required: true, message: " 稿件不能为空", trigger: "change"}
          ],
        },
        rules2: {
          arrangeDate: [
            {required: true}
          ],
          author: [
            {required: true, message: "编辑不能为空", trigger: "blur"}
          ],
        },
        // 日程类型
        jjrlxOptions: [],
      };
    },
    created() {
      this.getList()
      this.getEditArrange()
      this.getArticle()
      this.getMember()
    },
    activated() {
      this.getList()
      this.getEditArrange()
    },
    methods: {
      /*鼠标逗留，查看详情*/
      viewDetails(day) {
        this.neirong = ""
        if (this.rqi.indexOf(day) > -1) {
          this.visible = true
          this.neirong = "编辑：" +
            this.rqineirong[day].edit +
            "<br/>" + " 播音：" +
            this.rqineirong[day].broadcast +
            "<br/>" + "类型：" +
            this.rqineirong[day].group
          if (this.rqineirong[day].article) {
            this.neirong = this.neirong + "<br/>" + "稿件：" +
              this.rqineirong[day].article
          }
        }
        if (this.editneirong[day].author) {
          this.neirong = this.neirong + "<br/>" + "写稿编辑：" +
            this.editneirong[day].author
        }
        console.log(this.editneirong[day])
        /*console.log(this.rqi.indexOf(day) > -1)*/
      },
      /*鼠标离开，清除缓存*/
      clearCache() {
        this.neirong = ""
        this.visible = false
      },
      /** 查询日程管理 - 日程列表 */
      getList() {
        this.loading = true;
        listFdjjr().then(response => {
          this.rqi = [];
          this.rqi2 = [];
          this.rqineirong = {};
          for (let i = 0; i < response.data.length; i++) {
            this.rqi.push(response.data[i].date)
            this.rqineirong[response.data[i].date] = response.data[i]
            if (response.data[i].article !== null) {
              this.rqi2.push(response.data[i].date)
            }
          }
          console.log("rqi2" + this.rqi2)
          /*console.log("rqi" + this.rqi)
          console.log("rqi2" + this.rqi2)
          console.log(this.rqineirong)*/
        });
      },
      getEditArrange() {
        selectArrange().then(response => {
          this.rqi3 = [];
          this.editneirong = {};
          for (let i = 0; i < response.data.length; i++) {
            this.rqi3.push(response.data[i].arrangeDate)
            this.editneirong[response.data[i].arrangeDate] = response.data[i]
          }
          /*console.log("rqi3" + this.rqi3)
          console.log(this.editneirong)*/
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.open2 = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          edit: undefined,
          broadcast: undefined,
          date: undefined,
          group: undefined,
          article: ""
        };
        this.resetForm("form");
      },
      reset2() {
        this.form2 = {
          date2: undefined,
          edit2: undefined,
        };
        this.resetForm("form2");
      },
      /** 提交按钮 */
      submitForm: function () {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != undefined) {
              update2(this.form).then(response => {
                /*this.msgSuccess("修改成功");*/
                this.open = false;
                this.$message({
                  type: 'success',
                  message: '修改成功'
                })
                this.reset();
              });
            }
            this.getList();
            this.$router.go(0); // 强制刷新，用户不友好
          }
        });
      },
      submitForm2: function () {
        this.$refs["form2"].validate(valid => {
          if (valid) {
            editArrange(this.form2).then(response => {
              this.open2 = false;
              this.$message({
                type: 'success',
                message: '安排写稿成功'
              })
              this.reset2();
            });
            this.getList();
            /*this.$router.go(0); // 强制刷新，用户不友好*/
          }
        });
      },
      /*调用查询稿件列表*/
      getArticle() {
        selectArticle().then(response => {
          for (let i = 0; i < response.data.length; i++) {
            this.article[i] = response.data[i]
          }
          console.log(this.article)
        });
      },
      /*调用查询编辑列表*/
      getMember() {
        selectEdit().then(response => {
          for (let i = 0; i < response.data.length; i++) {
            this.edit[i] = response.data[i]
          }
          console.log(this.edit)
        });
      },
      /** 编辑日期操作 */
      handleBianJi(day) {
        if (this.rqineirong[day]) {
          /*console.log(this.rqineirong[day])*/
          this.form = this.rqineirong[day];
          this.title = '安排稿件';
          this.open = true;
        } else if (this.editneirong[day]) {
          console.log(this.editneirong[day])
          this.form2 = this.editneirong[day];
          this.title = '编辑写稿安排';
          this.open2 = true;
        } else {
          let obj = {};
          obj.arrangeDate = day.valueOf()
          this.form2 = obj
          this.title = '新增写稿安排';
          this.open2 = true;
        }
      },
      deleteForm() {
        delFdjjr(this.form.id).then(response => {
          this.open = false;
          this.open2 = false;
          this.$message({
            type: 'success',
            message: '删除成功'
          })
          this.reset();
          this.getList();
        });
      },
      deleteForm2() {
        console.log(this.form2.id)
        deleteArrange(this.form2.id).then(response => {
          this.open2 = false;
          this.$message({
            type: 'success',
            message: '删除成功'
          })
          this.reset2();
          this.getList();
        });
      }
    }
  };
</script>
<style lang="scss" scoped>
  .is-selected {
    color: white;
    background: rgba(112, 161, 255, 0.8);
  }

  .is-noselected {
    display: none;
  }

  .is-anpai {
    color: white;
    background: rgba(0, 127, 0, 0.8);
  }

  .is-noanpai {

  }

  .is-xiegao {
    color: white;
    background: rgba(255, 127, 80, 0.8);
  }

  .el-calendar {
    background-color: rgba(255, 255, 255, 0.9);
  }
</style>
