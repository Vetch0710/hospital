(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-10134181"],{"38da":function(e,t,r){"use strict";r.d(t,"b",(function(){return s})),r.d(t,"a",(function(){return o})),r.d(t,"c",(function(){return a}));var i=r("b775");r("c38a");function s(){return Object(i["a"])({url:"/web/patient/list",method:"get"})}function o(e){return Object(i["a"])({url:"/register",method:"post",data:e})}function a(e){return Object(i["a"])({url:"/web/patient",method:"put",data:e})}},7803:function(e,t,r){"use strict";r.r(t);var i=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"register"},[r("el-form",{ref:"registerForm",staticClass:"register-form",attrs:{model:e.registerForm,rules:e.registerRules}},[r("h3",{staticClass:"registerTitle"},[e._v("用户注册")]),e._v(" "),r("div",{staticStyle:{height:"45px"}},[r("router-link",{attrs:{to:"/login"}},[r("h3",{staticClass:"goBackToLogin"},[e._v("已有账号？立即登陆")])])],1),e._v(" "),r("el-form-item",{attrs:{label:"用户名",prop:"username","label-width":"80px"}},[r("el-input",{style:{width:"100%"},attrs:{type:"text","auto-complete":"off",placeholder:"账号"},model:{value:e.registerForm.username,callback:function(t){e.$set(e.registerForm,"username",t)},expression:"registerForm.username"}},[r("svg-icon",{staticClass:"el-input__icon input-icon",attrs:{slot:"prefix","icon-class":"user"},slot:"prefix"})],1)],1),e._v(" "),r("el-form-item",{attrs:{label:"密码",prop:"password","label-width":"80px"}},[r("el-input",{attrs:{type:"password","auto-complete":"off",placeholder:"密码"},model:{value:e.registerForm.password,callback:function(t){e.$set(e.registerForm,"password",t)},expression:"registerForm.password"}},[r("svg-icon",{staticClass:"el-input__icon input-icon",attrs:{slot:"prefix","icon-class":"password"},slot:"prefix"})],1)],1),e._v(" "),r("el-form-item",{attrs:{label:"身份证",prop:"idCard","label-width":"80px"}},[r("el-input",{attrs:{type:"text","auto-complete":"off",placeholder:"身份证"},model:{value:e.registerForm.idCard,callback:function(t){e.$set(e.registerForm,"idCard",t)},expression:"registerForm.idCard"}},[r("svg-icon",{staticClass:"el-input__icon input-icon",attrs:{slot:"prefix","icon-class":"password"},slot:"prefix"})],1)],1),e._v(" "),r("el-form-item",{attrs:{prop:"code",label:"验证码","label-width":"80px"}},[r("el-input",{staticStyle:{width:"55%"},attrs:{"auto-complete":"off",placeholder:"验证码"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleRegister(t)}},model:{value:e.registerForm.code,callback:function(t){e.$set(e.registerForm,"code",t)},expression:"registerForm.code"}},[r("svg-icon",{staticClass:"el-input__icon input-icon",attrs:{slot:"prefix","icon-class":"validCode"},slot:"prefix"})],1),e._v(" "),r("div",{staticClass:"register-code"},[r("img",{attrs:{src:e.codeUrl},on:{click:e.getCode}})])],1),e._v(" "),r("el-form-item",{staticStyle:{width:"100%"}},[r("el-button",{staticStyle:{width:"100%","margin-top":"20px"},attrs:{loading:e.loading,size:"medium",type:"primary"},nativeOn:{click:function(t){return t.preventDefault(),e.handleRegister(t)}}},[e.loading?r("span",[e._v("注 册 中...")]):r("span",[e._v("注册")])])],1)],1)],1)},s=[],o=r("7ded"),a=r("38da"),n={name:"register",data:function(){return{codeUrl:"",cookiePassword:"",registerForm:{username:"",password:"",idCard:"",code:"",uuid:""},registerRules:{username:[{required:!0,trigger:"blur",message:"用户名不能为空"}],password:[{required:!0,trigger:"blur",message:"密码不能为空"}],idCard:[{required:!0,trigger:"blur",message:"身份证不能为空"}],code:[{required:!0,trigger:"blur",message:"验证码不能为空"}]},loading:!1,redirect:void 0}},watch:{},created:function(){this.getCode()},methods:{getCode:function(){var e=this;Object(o["a"])().then((function(t){e.codeUrl="data:image/gif;base64,"+t.img,e.registerForm.uuid=t.uuid}))},handleRegister:function(){var e=this;console.log(this.registerForm),this.$refs.registerForm.validate((function(t){t&&(e.loading=!0,Object(a["a"])(e.registerForm).then((function(t){200===t.code?(e.msgSuccess("注册成功"),e.$router.push("/login")):e.msgError("注册失败，请检查您的数据及网络连接")})).catch((function(){e.loading=!1,e.getCode()})))}))}}},l=n,c=(r("9b5e"),r("2877")),u=Object(c["a"])(l,i,s,!1,null,null,null);t["default"]=u.exports},"9b5e":function(e,t,r){"use strict";r("b32c")},b32c:function(e,t,r){}}]);