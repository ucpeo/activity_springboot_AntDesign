(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-43e9cc40"],{"27fd":function(e,t,i){"use strict";var a=i("92fa"),r=i.n(a),n=i("41b2"),s=i.n(n),o=i("6042"),l=i.n(o),c=i("4df5"),d=i("0c63"),h=i("daa3"),f=i("4d91"),u={name:"AAvatar",props:{prefixCls:{type:String,default:void 0},shape:{validator:function(e){return["circle","square"].includes(e)},default:"circle"},size:{validator:function(e){return"number"===typeof e||["small","large","default"].includes(e)},default:"default"},src:String,srcSet:String,icon:f["a"].any,alt:String,loadError:Function},inject:{configProvider:{default:function(){return c["a"]}}},data:function(){return{isImgExist:!0,isMounted:!1,scale:1}},watch:{src:function(){var e=this;this.$nextTick((function(){e.isImgExist=!0,e.scale=1,e.$forceUpdate()}))}},mounted:function(){var e=this;this.$nextTick((function(){e.setScale(),e.isMounted=!0}))},updated:function(){var e=this;this.$nextTick((function(){e.setScale()}))},methods:{setScale:function(){if(this.$refs.avatarChildren&&this.$refs.avatarNode){var e=this.$refs.avatarChildren.offsetWidth,t=this.$refs.avatarNode.offsetWidth;0===e||0===t||this.lastChildrenWidth===e&&this.lastNodeWidth===t||(this.lastChildrenWidth=e,this.lastNodeWidth=t,this.scale=t-8<e?(t-8)/e:1)}},handleImgLoadError:function(){var e=this.$props.loadError,t=e?e():void 0;!1!==t&&(this.isImgExist=!1)}},render:function(){var e,t,i=arguments[0],a=this.$props,n=a.prefixCls,o=a.shape,c=a.size,f=a.src,u=a.alt,v=a.srcSet,p=Object(h["f"])(this,"icon"),g=this.configProvider.getPrefixCls,b=g("avatar",n),m=this.$data,C=m.isImgExist,$=m.scale,x=m.isMounted,k=(e={},l()(e,b+"-lg","large"===c),l()(e,b+"-sm","small"===c),e),S=s()(l()({},b,!0),k,(t={},l()(t,b+"-"+o,o),l()(t,b+"-image",f&&C),l()(t,b+"-icon",p),t)),y="number"===typeof c?{width:c+"px",height:c+"px",lineHeight:c+"px",fontSize:p?c/2+"px":"18px"}:{},E=this.$slots["default"];if(f&&C)E=i("img",{attrs:{src:f,srcSet:v,alt:u},on:{error:this.handleImgLoadError}});else if(p)E="string"===typeof p?i(d["a"],{attrs:{type:p}}):p;else{var T=this.$refs.avatarChildren;if(T||1!==$){var w="scale("+$+") translateX(-50%)",P={msTransform:w,WebkitTransform:w,transform:w},I="number"===typeof c?{lineHeight:c+"px"}:{};E=i("span",{class:b+"-string",ref:"avatarChildren",style:s()({},I,P)},[E])}else{var j={};x||(j.opacity=0),E=i("span",{class:b+"-string",ref:"avatarChildren",style:{opacity:0}},[E])}}return i("span",r()([{ref:"avatarNode"},{on:Object(h["j"])(this),class:S,style:y}]),[E])}},v=i("db14");u.install=function(e){e.use(v["a"]),e.component(u.name,u)};t["a"]=u},"3a34":function(e,t,i){"use strict";i.r(t);var a=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",[i("a-card",{attrs:{bordered:!1}},[i("div",[i("div",{staticStyle:{width:"300px","text-align":"center",margin:"0 auto"}},[i("a-avatar",{attrs:{size:64,src:e.logo,shape:"circle"}}),i("div",[i("h2",{staticStyle:{"margin-top":"20px"}},[e._v("天天活动")]),i("p",[e._v("欢迎来到这个世界，你好，我爱这个世界！")])])],1)])])],1)},r=[],n=(i("ac1f"),i("5319"),i("2b0e")),s=i("cdeb"),o=i("7571"),l=i("0c63"),c=i("ed3b"),d=i("27fd"),h=i("cf05"),f=i.n(h),u={Card:s["a"],Tag:o["a"],Icon:l["a"],Modal:c["a"],Avatar:d["a"]};for(var v in u){var p=v.replace(/([A-Z])/g,"-$&").toLowerCase();p=p.replace(/^-*/g,""),n["a"].use(u[v])}var g={data:function(){return{logo:f.a}},name:"about"},b=g,m=i("2877"),C=Object(m["a"])(b,a,r,!1,null,"4ebc9b26",null);t["default"]=C.exports},5319:function(e,t,i){"use strict";var a=i("d784"),r=i("825a"),n=i("7b0b"),s=i("50c4"),o=i("a691"),l=i("1d80"),c=i("8aa5"),d=i("14c3"),h=Math.max,f=Math.min,u=Math.floor,v=/\$([$&'`]|\d\d?|<[^>]*>)/g,p=/\$([$&'`]|\d\d?)/g,g=function(e){return void 0===e?e:String(e)};a("replace",2,(function(e,t,i,a){var b=a.REGEXP_REPLACE_SUBSTITUTES_UNDEFINED_CAPTURE,m=a.REPLACE_KEEPS_$0,C=b?"$":"$0";return[function(i,a){var r=l(this),n=void 0==i?void 0:i[e];return void 0!==n?n.call(i,r,a):t.call(String(r),i,a)},function(e,a){if(!b&&m||"string"===typeof a&&-1===a.indexOf(C)){var n=i(t,e,this,a);if(n.done)return n.value}var l=r(e),u=String(this),v="function"===typeof a;v||(a=String(a));var p=l.global;if(p){var x=l.unicode;l.lastIndex=0}var k=[];while(1){var S=d(l,u);if(null===S)break;if(k.push(S),!p)break;var y=String(S[0]);""===y&&(l.lastIndex=c(u,s(l.lastIndex),x))}for(var E="",T=0,w=0;w<k.length;w++){S=k[w];for(var P=String(S[0]),I=h(f(o(S.index),u.length),0),j=[],_=1;_<S.length;_++)j.push(g(S[_]));var A=S.groups;if(v){var O=[P].concat(j,I,u);void 0!==A&&O.push(A);var N=String(a.apply(void 0,O))}else N=$(P,u,I,j,A,a);I>=T&&(E+=u.slice(T,I)+N,T=I+P.length)}return E+u.slice(T)}];function $(e,i,a,r,s,o){var l=a+e.length,c=r.length,d=p;return void 0!==s&&(s=n(s),d=v),t.call(o,d,(function(t,n){var o;switch(n.charAt(0)){case"$":return"$";case"&":return e;case"`":return i.slice(0,a);case"'":return i.slice(l);case"<":o=s[n.slice(1,-1)];break;default:var d=+n;if(0===d)return t;if(d>c){var h=u(d/10);return 0===h?t:h<=c?void 0===r[h-1]?n.charAt(1):r[h-1]+n.charAt(1):t}o=r[d-1]}return void 0===o?"":o}))}}))},7571:function(e,t,i){"use strict";var a=i("92fa"),r=i.n(a),n=i("6042"),s=i.n(n),o=i("4d91"),l=i("0c63"),c=i("94eb"),d=i("0464"),h=i("a9d4"),f=i("daa3"),u=i("b488"),v=i("4df5"),p=i("6a21"),g=["pink","red","yellow","orange","cyan","green","blue","purple","geekblue","magenta","volcano","gold","lime"],b=new RegExp("^("+g.join("|")+")(-inverse)?$"),m={name:"ATag",mixins:[u["a"]],model:{prop:"visible",event:"close.visible"},props:{prefixCls:o["a"].string,color:o["a"].string,closable:o["a"].bool.def(!1),visible:o["a"].bool,afterClose:o["a"].func},inject:{configProvider:{default:function(){return v["a"]}}},data:function(){var e=!0,t=Object(f["k"])(this);return"visible"in t&&(e=this.visible),Object(p["a"])(!("afterClose"in t),"Tag","'afterClose' will be deprecated, please use 'close' event, we will remove this in the next version."),{_visible:e}},watch:{visible:function(e){this.setState({_visible:e})}},methods:{setVisible:function(e,t){this.$emit("close",t),this.$emit("close.visible",!1);var i=this.afterClose;i&&i(),t.defaultPrevented||Object(f["r"])(this,"visible")||this.setState({_visible:e})},handleIconClick:function(e){e.stopPropagation(),this.setVisible(!1,e)},isPresetColor:function(){var e=this.$props.color;return!!e&&b.test(e)},getTagStyle:function(){var e=this.$props.color,t=this.isPresetColor();return{backgroundColor:e&&!t?e:void 0}},getTagClassName:function(e){var t,i=this.$props.color,a=this.isPresetColor();return t={},s()(t,e,!0),s()(t,e+"-"+i,a),s()(t,e+"-has-color",i&&!a),t},renderCloseIcon:function(){var e=this.$createElement,t=this.$props.closable;return t?e(l["a"],{attrs:{type:"close"},on:{click:this.handleIconClick}}):null}},render:function(){var e=arguments[0],t=this.$props.prefixCls,i=this.configProvider.getPrefixCls,a=i("tag",t),n=this.$data._visible,s=e("span",r()([{directives:[{name:"show",value:n}]},{on:Object(d["a"])(Object(f["j"])(this),["close"])},{class:this.getTagClassName(a),style:this.getTagStyle()}]),[this.$slots["default"],this.renderCloseIcon()]),o=Object(c["a"])(a+"-zoom",{appear:!1});return e(h["a"],[e("transition",o,[s])])}},C={name:"ACheckableTag",model:{prop:"checked"},props:{prefixCls:o["a"].string,checked:Boolean},inject:{configProvider:{default:function(){return v["a"]}}},computed:{classes:function(){var e,t=this.checked,i=this.prefixCls,a=this.configProvider.getPrefixCls,r=a("tag",i);return e={},s()(e,""+r,!0),s()(e,r+"-checkable",!0),s()(e,r+"-checkable-checked",t),e}},methods:{handleClick:function(){var e=this.checked;this.$emit("input",!e),this.$emit("change",!e)}},render:function(){var e=arguments[0],t=this.classes,i=this.handleClick,a=this.$slots;return e("div",{class:t,on:{click:i}},[a["default"]])}},$=i("db14");m.CheckableTag=C,m.install=function(e){e.use($["a"]),e.component(m.name,m),e.component(m.CheckableTag.name,m.CheckableTag)};t["a"]=m},cf05:function(e,t,i){e.exports=i.p+"img/logo.a30d4552.png"}}]);
//# sourceMappingURL=chunk-43e9cc40.cc738bf6.js.map