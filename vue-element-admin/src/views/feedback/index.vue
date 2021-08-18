<template>
  <div id="t">
    <el-form ref="form" :model="form" label-width="100px">
      <el-form-item id="label" label="请输入反馈">
        <el-input id="text" type="textarea" v-model="form.desc" placeholder="请输入反馈"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">提交</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import { insertFeedback } from '@/api/feedback'

  export default {
    data() {
      return {
        form: {
          desc: ''
        }
      }
    },
    methods: {
      onSubmit() {
        console.log(this.form.desc)
        let a=this.form.desc
        insertFeedback(a).then(response=>{
          this.$message({
            type: 'success',
            message: '提交成功'
          })
        }).catch(err => {
            console.error(err)
          })
      }
    }
  }
</script>

<style>
  #text {
    width: 700px;
  }

  #t {
    margin: 2% 20% 0 10%;
  }
</style>
