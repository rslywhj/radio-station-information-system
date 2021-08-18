<style lang="scss" rel="stylesheet/scss">
  @media screen and (min-width: 460px) {
    .wh_item_date:hover {
      background: #71c7a5;
      cursor: pointer;
    }
  }

  * {
    margin: 0;
    padding: 0;
  }

  .wh_container {
    max-width: 410px;
    margin: auto;
  }

  li {
    list-style-type: none;
  }

  .wh_top_title {
    display: flex;
  }

  .wh_top_title li {
    cursor: pointer;
    display: flex;
    color: #fff;
    font-size: 18px;
    flex: 1;
    justify-content: center;
    align-items: center;
    height: 47px;
  }

  .wh_top_title .wh_content_li {
    cursor: auto;
    flex: 2.5;
    color: black;
  }

  .wh_content_all {
    font-family: -apple-system, BlinkMacSystemFont, "PingFang SC",
    "Helvetica Neue", STHeiti, "Microsoft Yahei", Tahoma, Simsun, sans-serif;
    background-color: white;
    width: 100%;
    overflow: hidden;
    padding-bottom: 8px;
  }

  .wh_content {
    display: flex;
    flex-wrap: wrap;
    padding: 0 3% 0 3%;
    width: 100%;
  }

  .wh_content:first-child .wh_content_item_tag,
  .wh_content:first-child .wh_content_item {
    color: #ddd;
    font-size: 16px;
  }

  .wh_content_item,
  wh_content_item_tag {
    font-size: 15px;
    width: 13.4%;
    text-align: center;
    color: #fff;
    position: relative;
  }

  .wh_content_item {
    height: 40px;
  }

  .wh_top_tag {
    width: 40px;
    height: 40px;
    line-height: 40px;
    margin: auto;
    display: flex;
    justify-content: center;
    align-items: center;
    color: black;
  }

  .wh_item_date {
    width: 30px;
    height: 30px;
    line-height: 30px;
    margin: auto;
    display: flex;
    justify-content: center;
    align-items: center;
    color: black;

    .smallDot {
      background-color: #f99341;
      width: 5px;
      height: 5px;
      border-radius: 50%;
      text-align: center;
      margin-left: 13px;
    }

    .smallDot1 {
      background-color: #1989fa;
      width: 5px;
      height: 5px;
      border-radius: 50%;
      text-align: center;
      margin-left: 13px;
    }
  }

  .wh_left {
    width: 12px;
    height: 12px;
    border-top: 2px solid #ffffff;
    border-left: 2px solid #ffffff;
    transform: rotate(-45deg);
    border-color: black;
  }

  .wh_left:active,
  .wh_right:active {
    border-color: #ddd;
  }

  .wh_right {
    width: 12px;
    height: 12px;
    border-top: 2px solid #ffffff;
    border-right: 2px solid #ffffff;
    transform: rotate(45deg);
    border-color: black;
  }

  .wh_content_item > .wh_isMark {
    margin: auto;
    border-radius: 50%;
    background: blue;
    z-index: 2;
  }

  .wh_content_item .wh_other_dayHide {
    color: #bfbfbf;
  }

  .wh_content_item .wh_want_dayHide {
    color: #bfbfbf;
  }

  .wh_content_item .wh_isToday {
    background: #77adfa;
    border-radius: 50%;
  }

  .wh_content_item .wh_chose_day {
    background: #1989fa;
    border-radius: 50%;
  }
</style>

<template>
  <section class="wh_container">
    <div class="wh_content_all">
      <div class="wh_top_title">
        <li @click="PreMonth(myDate,false)">
          <div class="wh_left"></div>
        </li>
        <li class="wh_content_li">{{dateTop}}</li>
        <li @click="NextMonth(myDate,false)">
          <div class="wh_right"></div>
        </li>
      </div>
      <div class="wh_content">
        <div class="wh_content_item" v-for="tag in textTop">
          <div class="wh_top_tag">{{tag}}</div>
        </div>
      </div>
      <div class="wh_content">
        <div class="wh_content_item" v-for="(item,index) in list" @click="clickDay(item,index)">
          <div class="wh_item_date" style="display: block"
               v-bind:class="[{ wh_isMark: item.isMark},{wh_other_dayHide:item.otherMonth!=='nowMonth'},{wh_want_dayHide:item.dayHide},{wh_isToday:item.isToday},{wh_chose_day:item.chooseDay},setClass(item)]">
            {{item.id}}
            <!--这里是控制异常、正常的那个小圆点-->
            <span v-for="(date,index) in dateList" :key="index">
                            <span v-if="date.offDutyTime&&date.onDutyTime&&formatDate(date.recordDate)==item.date&&(isLate(date.serverEndTime,date.offDutyTime)||isLate(date.onDutyTime,date.serverStartTime))">
                                    <div class="smallDot"></div>
                            </span>
                            <span v-if="date.offDutyTime&&date.onDutyTime&&formatDate(date.recordDate)==item.date&&!isLate(date.serverEndTime,date.offDutyTime)&&!isLate(date.onDutyTime,date.serverStartTime)">
                                <div class="smallDot1"></div>
                            </span>
                        </span>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
  import timeUtil from "./calendar";

  export default {
    data() {
      return {
        myDate: [],
        list: [],
        historyChose: [],
        dateTop: "",
        loginNannyUser: {},
        loginGzhUser: {},
        dateList: []
      };
    },
    props: {
      markDate: {
        type: Array,
        default: () => []
      },
      markDateMore: {
        type: Array,
        default: () => []
      },
      textTop: {
        type: Array,
        default: () => ["一", "二", "三", "四", "五", "六", "日"]
      },
      sundayStart: {
        type: Boolean,
        default: () => false
      },
      agoDayHide: {
        type: String,
        default: `0`
      },
      futureDayHide: {
        type: String,
        default: `2554387200`
      },
      joinWishId: {
        default: null
      }
    },
    created() {
      let $this = this;
      this.getLoginAllUser("nanny", {}, function () {//这里只是我这边的业务，删除就行

      });
      this.intStart();//初始化数据
      this.myDate = new Date();
    },
    methods: {
      isLate(str, str1) {//判断两个时分秒大小
        return new Date((this.formatDates(new Date()) + " " + str).replace(/-/g, '/')) > new Date((this.formatDates(new Date()) + " " + str1).replace(/-/g, '/'));
      },
      formatDate(date) {
        date = typeof date === 'string' ? new Date(date.replace(/\-/g, '/')) : date;
        return date.getFullYear() + '/' + (date.getMonth() + 1) + '/'
          + date.getDate();
      },
      intStart() {
        timeUtil.sundayStart = this.sundayStart;
      },
      setClass(data) {
        let obj = {};
        obj[data.markClassName] = data.markClassName;
        return obj;
      },
      clickDay(item, index) {
        if (item.otherMonth === "nowMonth" && !item.dayHide) {
          this.getList(this.myDate, item.date);
        }
        if (item.otherMonth !== "nowMonth") {
          item.otherMonth === "preMonth"
            ? this.PreMonth(item.date)
            : this.NextMonth(item.date);
        }
      },
      ChoseMonth(date, isSelectedDay = true) {
        date = timeUtil.dateFormat(date);
        this.myDate = new Date(date);
        this.$emit("changeMonth", timeUtil.dateFormat(this.myDate));
        if (isSelectedDay) {
          this.getList(this.myDate, date, isSelectedDay);
        } else {
          this.getList(this.myDate);
        }
      },
      PreMonth(date, isSelectedDay = true) {
        date = timeUtil.dateFormat(date);
        this.myDate = timeUtil.getOtherMonth(this.myDate, "preMonth");
        this.$emit("changeMonth", timeUtil.dateFormat(this.myDate));
        this.axiosPost("/nannyCheckIn/findMonthList.n", {date: this.myDate,joinWishListId: this.joinWishId,}, function (resData) {
          this.dateList = resData.list;
        });
        if (isSelectedDay) {
          this.getList(this.myDate, date, isSelectedDay);
        } else {
          this.getList(this.myDate);
        }
      },
      NextMonth(date, isSelectedDay = true) {
        date = timeUtil.dateFormat(date);
        this.myDate = timeUtil.getOtherMonth(this.myDate, "nextMonth");
        this.$emit("changeMonth", timeUtil.dateFormat(this.myDate));
        this.axiosPost("/nannyCheckIn/findMonthList.n", {date: this.myDate,joinWishListId: this.joinWishId,}, function (resData) {
          this.dateList = resData.list;
        });
        if (isSelectedDay) {
          this.getList(this.myDate, date, isSelectedDay);
        } else {
          this.getList(this.myDate);
        }
      },
      forMatArgs() {
        let markDate = this.markDate;
        let markDateMore = this.markDateMore;
        markDate = markDate.map(k => {
          return timeUtil.dateFormat(k);
        });
        markDateMore = markDateMore.map(k => {
          k.date = timeUtil.dateFormat(k.date);
          return k;
        });
        return [markDate, markDateMore];
      },
      getList(date, chooseDay, isSelectedDay = true) {
        const [markDate, markDateMore] = this.forMatArgs();
        this.dateTop = `${date.getFullYear()}年${date.getMonth() + 1}月`;

        let arr = timeUtil.getMonthList(this.myDate);
        for (let i = 0; i < arr.length; i++) {
          let markClassName = "";
          let k = arr[i];
          k.chooseDay = false;
          const nowTime = k.date;
          const t = new Date(nowTime).getTime() / 1000;
          //看每一天的class
          for (const c of markDateMore) {
            if (c.date === nowTime) {
              markClassName = c.className || "";
            }
          }
          //标记选中某些天 设置class
          k.markClassName = markClassName;
          k.isMark = markDate.indexOf(nowTime) > -1;
          //无法选中某天
          k.dayHide = t < this.agoDayHide || t > this.futureDayHide;
          if (k.isToday) {
            this.$emit("isToday", nowTime);
          }
          let flag = !k.dayHide && k.otherMonth === "nowMonth";
          if (chooseDay && chooseDay === nowTime && flag) {
            this.$emit("choseDay", nowTime);
            this.historyChose.push(nowTime);
            k.chooseDay = true;
          } else if (
            this.historyChose[this.historyChose.length - 1] === nowTime &&
            !chooseDay &&
            flag
          ) {
            k.chooseDay = true;
          }
        }
        this.list = arr;
      }
    },
    mounted() {
      this.getList(this.myDate);
      this.axiosPost("/nannyCheckIn/findMonthList.n", {//业务，根据自己需要修改
        joinWishListId: this.joinWishId,
      }, function (resData) {
        this.dateList = resData.list;
      });
    },
    watch: {
      markDate: {
        handler(val, oldVal) {
          this.getList(this.myDate);
        },
        deep: true
      },
      markDateMore: {
        handler(val, oldVal) {
          this.getList(this.myDate);
        },
        deep: true
      },
      agoDayHide: {
        handler(val, oldVal) {
          this.getList(this.myDate);
        },
        deep: true
      },
      futureDayHide: {
        handler(val, oldVal) {
          this.getList(this.myDate);
        },
        deep: true
      },
      sundayStart: {
        handler(val, oldVal) {
          this.intStart();
          this.getList(this.myDate);
        },
        deep: true
      },
      joinWishId: {//监听这个是因为要切换工单，换数据
        handler(val, oldVal) {
          this.axiosPost("/nannyCheckIn/findMonthList.n", {
            joinWishListId: val,
          }, function (resData) {
            this.dateList = resData.list;
          });
        },
        deep: true
      }
    }
  };
</script>
