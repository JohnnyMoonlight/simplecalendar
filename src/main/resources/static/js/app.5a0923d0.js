(function(e){function t(t){for(var o,r,s=t[0],c=t[1],l=t[2],u=0,d=[];u<s.length;u++)r=s[u],Object.prototype.hasOwnProperty.call(a,r)&&a[r]&&d.push(a[r][0]),a[r]=0;for(o in c)Object.prototype.hasOwnProperty.call(c,o)&&(e[o]=c[o]);f&&f(t);while(d.length)d.shift()();return i.push.apply(i,l||[]),n()}function n(){for(var e,t=0;t<i.length;t++){for(var n=i[t],o=!0,r=1;r<n.length;r++){var s=n[r];0!==a[s]&&(o=!1)}o&&(i.splice(t--,1),e=c(c.s=n[0]))}return e}var o={},r={app:0},a={app:0},i=[];function s(e){return c.p+"js/"+({about:"about"}[e]||e)+"."+{about:"03a245a9"}[e]+".js"}function c(t){if(o[t])return o[t].exports;var n=o[t]={i:t,l:!1,exports:{}};return e[t].call(n.exports,n,n.exports,c),n.l=!0,n.exports}c.e=function(e){var t=[],n={about:1};r[e]?t.push(r[e]):0!==r[e]&&n[e]&&t.push(r[e]=new Promise((function(t,n){for(var o="css/"+({about:"about"}[e]||e)+"."+{about:"bf2ad24c"}[e]+".css",a=c.p+o,i=document.getElementsByTagName("link"),s=0;s<i.length;s++){var l=i[s],u=l.getAttribute("data-href")||l.getAttribute("href");if("stylesheet"===l.rel&&(u===o||u===a))return t()}var d=document.getElementsByTagName("style");for(s=0;s<d.length;s++){l=d[s],u=l.getAttribute("data-href");if(u===o||u===a)return t()}var f=document.createElement("link");f.rel="stylesheet",f.type="text/css",f.onload=t,f.onerror=function(t){var o=t&&t.target&&t.target.src||a,i=new Error("Loading CSS chunk "+e+" failed.\n("+o+")");i.code="CSS_CHUNK_LOAD_FAILED",i.request=o,delete r[e],f.parentNode.removeChild(f),n(i)},f.href=a;var m=document.getElementsByTagName("head")[0];m.appendChild(f)})).then((function(){r[e]=0})));var o=a[e];if(0!==o)if(o)t.push(o[2]);else{var i=new Promise((function(t,n){o=a[e]=[t,n]}));t.push(o[2]=i);var l,u=document.createElement("script");u.charset="utf-8",u.timeout=120,c.nc&&u.setAttribute("nonce",c.nc),u.src=s(e);var d=new Error;l=function(t){u.onerror=u.onload=null,clearTimeout(f);var n=a[e];if(0!==n){if(n){var o=t&&("load"===t.type?"missing":t.type),r=t&&t.target&&t.target.src;d.message="Loading chunk "+e+" failed.\n("+o+": "+r+")",d.name="ChunkLoadError",d.type=o,d.request=r,n[1](d)}a[e]=void 0}};var f=setTimeout((function(){l({type:"timeout",target:u})}),12e4);u.onerror=u.onload=l,document.head.appendChild(u)}return Promise.all(t)},c.m=e,c.c=o,c.d=function(e,t,n){c.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:n})},c.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},c.t=function(e,t){if(1&t&&(e=c(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var n=Object.create(null);if(c.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var o in e)c.d(n,o,function(t){return e[t]}.bind(null,o));return n},c.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return c.d(t,"a",t),t},c.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},c.p="/",c.oe=function(e){throw console.error(e),e};var l=window["webpackJsonp"]=window["webpackJsonp"]||[],u=l.push.bind(l);l.push=t,l=l.slice();for(var d=0;d<l.length;d++)t(l[d]);var f=u;i.push([0,"chunk-vendors"]),n()})({0:function(e,t,n){e.exports=n("56d7")},"034f":function(e,t,n){"use strict";n("85ec")},"23f8":function(e,t,n){"use strict";n("48c5")},2450:function(e,t,n){},"3b42":function(e,t,n){"use strict";n.r(t);var o=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("section",{staticClass:"rooms-vue"},[n("h1",[e._v("Rooms ")]),e._v(" Available rooms: "+e._s(e.rooms.length)+" "),n("div",{staticClass:"overflow-auto"},[n("b-pagination",{attrs:{"total-rows":e.totalRows,"per-page":e.perPage,"aria-controls":"roomTable"},model:{value:e.currentPage,callback:function(t){e.currentPage=t},expression:"currentPage"}}),n("b-table",{attrs:{id:"roomTable",fields:e.fields,striped:"",hover:"",items:e.rooms,"per-page":e.perPage,"current-page":e.currentPage},scopedSlots:e._u([{key:"cell(index)",fn:function(t){return[n("span",[e._v(" "+e._s(t.index+1)+" ")])]}},{key:"cell(name)",fn:function(t){return[n("span",[e._v(" "+e._s(t.value)+" "),t.item.icon?n("font-awesome-icon",{attrs:{icon:t.item.icon}}):n("span",[n("small",[e._v("No icon configured.")])])],1)]}},{key:"cell(actions)",fn:function(t){return[n("b-button-group",[n("b-button",{attrs:{title:"Feature under development...",disabled:!0,variant:"success"}},[n("span",[e._v("Edit")])]),n("b-button",{attrs:{variant:"info"},on:{click:function(n){return e.info(t.item,t.index,n.target)}}},[e._v(" Details ")]),n("b-button",{attrs:{variant:"danger"},on:{click:function(n){return e.showDeleteModal(t.item,t.index,n.target)}}},[e._v(" Delete ")])],1)]}}])}),n("b-modal",{ref:"deleteModal",attrs:{"hide-footer":"",id:e.deleteModal.id,title:"Delete "+e.deleteModal.title},on:{hide:e.resetDeleteModal}},[n("b-alert",{attrs:{variant:"danger",show:""}},[e._v("Deleting this room will also delete all associated appointments.")]),n("b-button-group",[n("b-button",{attrs:{variant:"danger"},on:{click:function(t){e.deleteRoomWithId(e.deleteModal.room.roomId),e.hideDeleteModal()}}},[e._v("Delete")]),n("b-button",{on:{click:function(t){e.resetDeleteModal(),e.hideDeleteModal()}}},[e._v("Cancel")])],1)],1),n("b-modal",{attrs:{id:e.infoModal.id,title:e.infoModal.title,"ok-only":""},on:{hide:e.resetInfoModal}},[n("ul",{attrs:{id:"appointmentList"}},[e.infoModal.content.appointments?n("span",[e._v(" Appointments: "+e._s(e.infoModal.content.appointments.length)+" ")]):e._e(),e._l(e.infoModal.content.appointments,(function(t,o){return n("li",{key:t.id},[n("div",[e._v(" "+e._s(o+1)+" ")]),n("div",[n("small",[e._v(" Start: "+e._s(new Date(JSON.parse(t.startTime)).toLocaleDateString()+" - "+new Date(JSON.parse(t.startTime)).toLocaleTimeString())+" ")])]),n("div",[n("small",[e._v(" End: "+e._s(new Date(JSON.parse(t.endTime)).toLocaleDateString()+" - "+new Date(JSON.parse(t.endTime)).toLocaleTimeString())+" ")])])])}))],2)]),n("div",{staticStyle:{width:"99%"}},[n("b-form",[n("b-row",{staticClass:"justify-content-md-center"},[n("b-col",{attrs:{cols:"8"}},[n("b-form-group",{attrs:{id:"input-group-1",label:"Room name:","label-for":"input-1",description:"Create a new room to book appointments."}},[n("b-form-input",{attrs:{id:"input-1",type:"text",placeholder:"Enter room name",required:""},model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1)],1),n("b-col",{attrs:{cols:"2"}},[n("IconSelector",{on:{iconEmitted:function(t){return e.setIcon(t)}}})],1)],1),n("b-button",{attrs:{variant:"success"},on:{click:e.postData}},[e._v("Submit")]),n("b-button",{attrs:{type:"reset",variant:"danger"}},[e._v("Reset")])],1)],1)],1)])},r=[],a=(n("b0c0"),n("d3b7"),n("96cf"),n("1da1")),i=n("ecee"),s=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("section",{staticClass:"icon-selector"},[n("label",{attrs:{for:"selectIcon"}},[e._v("Icon:")]),n("div",{staticClass:"input-group-prepend"},[n("select",{directives:[{name:"model",rawName:"v-model",value:e.selectedIcon,expression:"selectedIcon"}],staticClass:"form-control iconSelect",attrs:{id:"selectIcon"},on:{change:[function(t){var n=Array.prototype.filter.call(t.target.options,(function(e){return e.selected})).map((function(e){var t="_value"in e?e._value:e.value;return t}));e.selectedIcon=t.target.multiple?n:n[0]},function(t){return e.emitIcon(t.target.value)}]}},[n("option",{attrs:{value:""}},[e._v("Kein Icon")]),n("option",{attrs:{value:"user-secret"}},[e._v("User-Secret")]),n("option",{attrs:{value:"coffee"}},[e._v("Kaffee")]),n("option",{attrs:{value:"archway"}},[e._v("Archway")])]),n("span",{staticClass:"iconContainer input-group-text"},[n("font-awesome-icon",{attrs:{icon:e.selectedIcon}})],1)])])},c=[],l=n("c074");i["c"].add(l["d"],l["b"],l["a"]);var u={name:"icon-selector",props:[],mounted:function(){},data:function(){return{selectedIcon:null}},methods:{emitIcon:function(e){this.$emit("iconEmitted",e)}},computed:{}},d=u,f=(n("6519"),n("2877")),m=Object(f["a"])(d,s,c,!1,null,"a6a4f3b8",null),p=m.exports,h={name:"rooms-vue",components:{IconSelector:p},props:[],data:function(){return{selected:null,options:[],rooms:[],fields:[{key:"index",sortable:!0},{key:"name",sortable:!0},"actions"],isLoggedIn:!0,perPage:10,currentPage:1,form:{name:"",icon:"",roomId:null},infoModal:{id:"info-modal",title:"",content:""},deleteModal:{id:"delete-modal",title:"",room:""}}},created:function(){this.fetchRooms()},methods:{debug:function(e){console.log(e)},info:function(e,t,n){this.infoModal.title="Room : ".concat(e.name),this.infoModal.content=e,this.$root.$emit("bv::show::modal",this.infoModal.id,n)},showDeleteModal:function(e,t,n){this.deleteModal.title=e.name,this.deleteModal.room=e,this.$root.$emit("bv::show::modal",this.deleteModal.id,n)},resetInfoModal:function(){this.infoModal.title="",this.infoModal.content=""},resetDeleteModal:function(){this.deleteModal.title="",this.deleteModal.room=""},hideDeleteModal:function(){this.$refs["deleteModal"].hide()},setIcon:function(e){this.form.icon=e},postData:function(){var e=this;return Object(a["a"])(regeneratorRuntime.mark((function t(){var n;return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:return t.next=2,fetch("api/createRoom",{method:"POST",headers:{"Content-Type":"application/json"},body:JSON.stringify(e.form)});case 2:n=t.sent,n.ok?e.fetchRooms():403==n.status&&console.log("Not authenticated.");case 4:case"end":return t.stop()}}),t)})))()},deleteRoomWithId:function(e){var t=this;return Object(a["a"])(regeneratorRuntime.mark((function n(){var o,r;return regeneratorRuntime.wrap((function(n){while(1)switch(n.prev=n.next){case 0:return o={method:"delete"},n.next=3,fetch("api/deleteRoom/"+e,o);case 3:r=n.sent,console.log(r),t.fetchRooms();case 6:case"end":return n.stop()}}),n)})))()},fetchRooms:function(){var e=this;return Object(a["a"])(regeneratorRuntime.mark((function t(){var n,o;return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:return t.next=2,fetch("api/allRooms",{headers:{Accept:"application/json"}});case 2:return n=t.sent,t.prev=3,t.next=6,n.json();case 6:o=t.sent,e.rooms=o,t.next=13;break;case 10:t.prev=10,t.t0=t["catch"](3),e.rooms=[];case 13:case"end":return t.stop()}}),t,null,[[3,10]])})))()}},computed:{totalRows:function(){return this.rooms.length}}},v=h,b=(n("5bfa"),Object(f["a"])(v,o,r,!1,null,null,null));t["default"]=b.exports},"48c5":function(e,t,n){},"56d7":function(e,t,n){"use strict";n.r(t);n("e260"),n("e6cf"),n("cca6"),n("a79d");var o=n("2b0e"),r=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"app"}},[n("NavBar"),n("router-view")],1)},a=[],i=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"nav"}},[n("router-link",{attrs:{to:"/"}},[e._v("Home")]),e._v(" | "),n("router-link",{attrs:{to:"/rooms"}},[e._v("Rooms")]),e._v(" | "),n("router-link",{attrs:{to:"/calendar"}},[e._v("Calendar")]),e._v(" | "),"ROLE_ANONYMOUS"==e.userStatus.authorities?n("a",{attrs:{href:"/login.html"}},[n("span",[e._v("Sign In")])]):n("a",{attrs:{href:"/logout"}},[e._v(" Logout user "),n("i",[e._v(e._s(e.userStatus.name))])])],1)},s=[],c=(n("caad"),n("d3b7"),n("2532"),n("96cf"),n("1da1")),l={name:"nav-bar",props:[],beforeCreated:function(){this.getUserStatus()},data:function(){return{userStatus:{name:"anonymousUser",isAuthenticated:!1,authorities:["ROLE_ANONYMOUS"]}}},methods:{getUserStatus:function(){var e=this;return Object(c["a"])(regeneratorRuntime.mark((function t(){var n,o;return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:return t.next=2,fetch("/api/users/status");case 2:if(n=t.sent,!n.ok){t.next=8;break}return t.next=6,n.json();case 6:o=t.sent,e.userStatus=o;case 8:case"end":return t.stop()}}),t)})))()},userHasMoreRolesThanAnonymous:function(){if(this.userStatus.authorities.includes("ROLE_ANONYMOUS"))return!0}},computed:{}},u=l,d=(n("23f8"),n("2877")),f=Object(d["a"])(u,i,s,!1,null,"7ecebcc3",null),m=f.exports,p={components:{NavBar:m}},h=p,v=(n("034f"),Object(d["a"])(h,r,a,!1,null,null,null)),b=v.exports,g=n("8c4f"),_=function(){var e=this,t=e.$createElement;e._self._c;return e._m(0)},w=[function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"home"},[n("h1",[e._v(" Welcome to your Room Appointment Management App ")]),e._v(" This app allows you to create rooms as ressources and book timed appointments in them. ")])}],y={name:"Home",components:{},methods:{}},M=y,S=Object(d["a"])(M,_,w,!1,null,null,null),O=S.exports;n("3b42");o["a"].use(g["a"]);var k=[{path:"/",name:"Home",component:O},{path:"/rooms",name:"RoomsView",component:function(){return n.e("about").then(n.bind(null,"3b42"))}},{path:"/calendar",name:"Calendar",component:function(){return n.e("about").then(n.bind(null,"ac0f"))}}],x=new g["a"]({routes:k}),R=x,j=n("ecee"),D=n("c074"),I=n("ad3d"),E=n("0065"),C=(n("719c"),n("7e05")),N=n("aa2f");n("5b3d"),n("7db1");j["c"].add(D["c"]),o["a"].use(C["a"]),o["a"].use(N["a"]),o["a"].component("font-awesome-icon",I["a"]),o["a"].use(E["Datetime"]),o["a"].config.productionTip=!1,new o["a"]({router:R,render:function(e){return e(b)}}).$mount("#app")},"5bfa":function(e,t,n){"use strict";n("2450")},6519:function(e,t,n){"use strict";n("f1be")},"85ec":function(e,t,n){},f1be:function(e,t,n){}});
//# sourceMappingURL=app.5a0923d0.js.map