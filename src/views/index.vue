<template>
<div class="app-container nickstyle" >
  <div class="home ui-flex justify-center center nickstyle" >

    <img class="indexlogoimg" v-bind:src="logo" alt=" " />
    <div class="indexlogotext" >{{title}}</div>
  </div>


</div>


</template>

<script>
import Logo from "@/assets/logo/clock_black.png";
import Noti from '../components/Noti' //暂未完全封装
import { listInfo, getInfo,setSureState } from "@/api/happyclock/noti";

// import notidetail from '../utils/notidetail.js'


export default {
  // components: { Logo },
  name: "index",
  components: {
    Noti},
  data() {
    return {
      title: this.getTitle,
      // 版本号
      version: "3.3.0",
      logo:Logo,
      // notifyPromise:Promise.resolve(),
      timer1:'' ,
      timer2:'' ,
      notilist: [],
      link:{},
      notiarr: [],
      form:{},
      formlist:[],
      // firstload:1
    };
  },
  created() {
    // // console.log('created XXXXXXXXXXXX'+this.formlist)
    // this.getList();
    // // console.log('created getlist之后'+this.formlist);
    // this.getDicts("hc_v_hospital").then(response => {
    //   this.hospnameOptions = response.data;
    // });
    // this.getDicts("hc_inputorname").then(response => {
    //   this.inputornameOptions = response.data;
    // });
    // this.getDicts("hc_sure_state").then(response => {
    //   this.sureStateOptions = response.data;
    // });
  },
  mounted(){

    //！！切换其他标签后回到当前标签，重复创建了定时任务
    this.timer1 ='';
    this.timer2 ='';

    //首次加载
    // console.log("//首次加载");

    // console.log('mounted getlist之后'+this.formlist);

    // this.timerGetList();
    // timer1 timer2 ??
      //快速 持续读取队列
      // this.timer1 =
      // setInterval(this.timerNotify, 180);
      // //慢速 请求新数据并清空当前提醒
      // this.timer2 =
      // setInterval(this.timerGetList, 60000);


              // this.getNotiList();
              // let listitem =this.form.patientname;
  },
  beforeUpdate(){

  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    },

    open1(listitem) {
                const h = this.$createElement;
                let i =this.notiarr.length;
                this.notiarr.push(this.$notify({
                    title: '提醒',
                    message: h('p', null, [
                        h(Noti, {
                                  props: {
                                    msg: listitem,
                                    // theparaent:this.$data.link

                                  }
                                }),
                        h('a', {
                            on:{
                                click:this.clickA
                            }
                        }, "可点击的标签"),
                        h('button', {
                            on:{
                                //click://this.clickBtn
                                click:()=>{this.notiarr[i].close()}
                            }
                        }, "按钮")
                    ]),
                    position: 'bottom-right',
                    duration: 0
                })
                )

                // this.link = para
            },

          open2(listitem) {
              const h = this.$createElement;
              let i =this.notiarr.length;
              this.notiarr.push(this.$notify({
                  title: '提醒',
                  message: h('div', {class:""},[
                      h(
                    'div',
                    {class:"el-notification__content"},[
                    h('span',null,"PT姓名"),
                    h('span',{class:"nick-code"},listitem.patientname),
                    h('span',null,"，当前用药量为"),
                    h('span',{class:"nick-code"},listitem.dosage),
                    h('span',null,"支，已达提醒日期"),
                    h('span',{class:"nick-code"},this.parseTime(listitem.inputtime, '{y}年{m}月{d}日')),//!!!
                    h('span',null,"，请继续用药，医院名称为"),
                    h('span',{class:"nick-code"},listitem.real_hospname),
                    h('span',null,"，医生姓名为"),
                    h('span',{class:"nick-code"},listitem.doctorname),
                    h('span',null,"。"),
                    ]
                  ),
                  h(
                    'div',
                    {
                      class:"el-message-box__btns",
                      style:"min-width:289px"
                    },[
                      h('button', {
                        class:"el-button el-button--default el-button--mini",
                          on:{
                              //click://this.clickBtn
                              click:()=>{this.notiarr[i].close()}
                          }
                      }, "取 消"),
                      h('button', {
                        class:"el-button el-button--primary el-button--mini",
                          on:{
                              //click://this.clickBtn
                              click:()=>{
                                this.clickYes(
                                  {
                                    //复用更新接口，
                                    //仅传入 ID 和固定的sureState 以确认
                                    patientsId:listitem.patientsId,
                                    sureState:1
                                  });
                                this.notiarr[i].close()}
                          }
                      }, "确 认")
                  ]
                  )
                  ]


                  ),
                  position: 'bottom-right',
                  duration: 0,
                  customClass:"nick-el-notification__group"
              })
              )

              // this.link = para
          },

            clickYes(i){
                //  alert("处理点击标签");
                 setSureState(i);

            },
            clickBtn(){
                alert("处理点击按钮");
                this.notiarr[i].close();

            },
            nicknotify(msg) {
                      const h = this.$createElement;
                      this.notifyPromise = this.notifyPromise.then(this.$nextTick).then(()=>{
                        this.$notify({
                          title: "提醒",
                          message: h(Noti, {
                                              props: {
                                                msg: msg
                                            }
                                          }),
                          dangerouslyUseHTMLString: true,
                          duration: 0,
                          position: "bottom-right"
                        });
                    })
             },

            testNotify(){
              for(let i of this.notilist){
                  this.nicknotify(i);
                }
            },
            //只负责从队列读取并创建noti
            timerNotify(){
              // let listitem =this.notilist.shift();
              //直接取formlist
              let listitem =this.formlist.shift();

              // this.getNotiList();
              // let listitem =this.form.patientname;
              if(listitem != undefined){
                // this.nicknotify(listitem);
                this.open2(listitem);
              }
            },
            //定时请求接口
            //
            timerGetList(){
              this.closeAllNoti();
              //this.getNotiList();
              this.getList();//!!!异步操作？
              //异步操作！！！不再push，直接读取formlist
              // for(let i of this.formlist){
              //     this.notilist.push(i)
              //     // console.log(i)
              // }
            },
            getNotiList(row) {
              // this.reset();
              const patientsId = 7//row.patientsId || this.ids
              getInfo(patientsId).then(response => {
                this.form = response.data;
                // console.log('XXXXXX'+this.form)
              });
            },
            //只负责请求接口并更新数据
            getList() {
              listInfo().then(response => {
                this.formlist = response.rows;
                // console.log('加载完毕'+this.formlist)
              //清空当然noti

                  // this.timerNotify();
                  // this.firstload=0;


              });
            },
            closeAllNoti(){
              for(let i of this.notiarr){
                  i.close();
              }
            }



  },
  //销毁定时器与现有noti
  beforeDestroy() {
      clearInterval(this.timer1);
      clearInterval(this.timer2);
      this.closeAllNoti();
    }

};
</script>

<style scoped lang="scss">
.ui-flex {
            display: -ms-flexbox !important;
            display: flex !important;
            -ms-flex-wrap: wrap;
            flex-wrap: wrap
        }

        .ui-flex, .ui-flex *, .ui-flex :after, .ui-flex :before {
            box-sizing: border-box
        }

        .ui-flex.justify-center {


            -ms-flex-pack: center;
            justify-content: center
        }
        .ui-flex.center {
            -ms-flex-pack: center;
            justify-content: center;


            -ms-flex-align: center;
            align-items: center
        }

        .indexlogoimg{
          zoom:60%;
        }

        .indexlogotext{
          font-weight: 600;
          color:#b2c0d1;
          line-height: 50px;
          font-size: 24px;
          font-family: Avenir, Helvetica Neue, Arial, Helvetica, sans-serif;
        }
        .nickstyle{
          min-height: calc(100vh - 84px);
        }
        .nick-code
        {
          background-color: #f9fafc;
          padding: 0 4px;
          border: 1px solid #eaeefb;
          border-radius: 4px;
        }
</style>

