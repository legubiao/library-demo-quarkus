import{c as x,a as q,h as T,d as V,_ as j,i as z,r as c,a2 as L,Q as r,a6 as C,B as d,aq as Q,a4 as y,W as l,S as a,ar as P,as as R,R as u,X as H,Y as E,a5 as F,at as G,au as O,a7 as U,a8 as W,Z as f,$ as X}from"./index.bc8510fa.js";import{c as Y,Q as _,a as h,b as p}from"./QList.050f4af1.js";import{Q as Z}from"./QBtnDropdown.fb669136.js";import{B as A,Q as J,a as K,b as $}from"./BookDialog.5092da2e.js";import{Q as ee}from"./use-cache.df12cf91.js";import{Q as ae}from"./QInnerLoading.b919ddb6.js";import{C as g}from"./ClosePopup.a0e9a3ff.js";import{u as le}from"./use-quasar.28424290.js";import"./selection.4f5ad97a.js";import"./QResizeObserver.d171c610.js";var oe=x({name:"QTr",props:{props:Object,noHover:Boolean},setup(s,{slots:o}){const k=q(()=>"q-tr"+(s.props===void 0||s.props.header===!0?"":" "+s.props.__trClass)+(s.noHover===!0?" q-tr--no-hover":""));return()=>T("tr",{class:k.value},V(o.default))}});const te=[{name:"id",label:"\u6761\u7801\u53F7",align:"left",field:"id"},{name:"title",label:"\u6807\u9898",align:"left",field:"title",sortable:!0},{name:"author",label:"\u4F5C\u8005",align:"left",field:"author",sortable:!0},{name:"publisher",label:"\u51FA\u7248\u793E",align:"left",field:"publisher",sortable:!0},{name:"callNumber",label:"\u7D22\u4E66\u53F7",align:"left",field:"callNumber",sortable:!0},{name:"isbn",label:"ISBN",align:"left",field:"isbn",sortable:!0}],ne={name:"BookSearch",components:{BookDialog:A},setup(){const s=le(),o=z("api").value,k=c(!1),e=c(!1),i=c([]),m=c(""),b=c("\u5728\u6B64\u8F93\u5165\u56FE\u4E66\u6807\u9898"),t=c("\u9996\u5B57\u641C\u7D22");function v(){m.value="",i.value=[]}function B(){k.value=!0}function S(n){switch(t.value=n,n){case"\u7D22\u4E66\u53F7\u641C\u7D22":b.value="\u5728\u6B64\u8F93\u5165\u5B8C\u6574\u7684\u7D22\u4E66\u53F7";break;case"\u6761\u7801\u53F7\u641C\u7D22":b.value="\u5728\u6B64\u8F93\u5165\u5B8C\u6574\u7684\u6761\u7801\u53F7";break;default:b.value="\u5728\u6B64\u8F93\u5165\u56FE\u4E66\u6807\u9898";break}}function N(){switch(e.value=!0,t.value){case"\u7D22\u4E66\u53F7\u641C\u7D22":I();break;case"\u6761\u7801\u53F7\u641C\u7D22":M();break;default:D();break}}function D(){const n={title:m.value,mode:""};switch(t.value){case"\u7CBE\u786E\u641C\u7D22":break;case"\u9996\u5B57\u641C\u7D22":n.mode="prefixMatch";break;case"\u6A21\u7CCA\u641C\u7D22":n.mode="fuzzySearch";break}o.get("book/title",{params:n}).then(w=>{i.value=w.data,s.notify({message:"\u5171\u641C\u7D22\u5230"+i.value.length+"\u6761\u8BB0\u5F55",type:"info"})}).finally(()=>{e.value=!1})}function I(){const n={callNumber:m.value};o.get("book/callNumber",{params:n}).then(w=>{w.data!==""&&(i.value=w.data,s.notify({message:"\u5171\u641C\u7D22\u5230"+i.value.length+"\u6761\u8BB0\u5F55",type:"info"}))}).finally(()=>{e.value=!1})}function M(){o.get("book/id/"+m.value).then(n=>{n.data!==""&&i.value.push(n.data)}).finally(()=>{e.value=!1})}return{bookColumns:te,bookDialog:c(),visible:k,loading:e,books:i,keyword:m,placeholder:b,searchMode:t,show:B,switchMode:S,search:N,clear:v}}},se={class:"items-center content-center justify-center flex q-gutter-md"},re={class:"text-h4"},ie={class:"full-width justify-center row"},ce=f("\u7D22\u4E66\u53F7\u641C\u7D22"),de=f("\u6761\u7801\u53F7\u641C\u7D22"),ue=f("\u6807\u9898\u641C\u7D22"),fe=f("\u7CBE\u786E\u641C\u7D20"),me=f("\u9996\u5B57\u641C\u7D22"),be=f("\u6A21\u7CCA\u641C\u7D22");function he(s,o,k,e,i,m){const b=L("book-dialog");return r(),C("div",se,[d(y("div",re,"\u56FE\u4E66\u68C0\u7D22\u5F15\u64CE",512),[[Q,e.books.length===0]]),y("div",ie,[l(G,null,{default:a(()=>[l(P,{class:"q-pa-none"},{default:a(()=>[l(R,{outlined:"",modelValue:e.keyword,"onUpdate:modelValue":o[5]||(o[5]=t=>e.keyword=t),placeholder:e.placeholder},{append:a(()=>[e.keyword!==""?(r(),u(H,{key:0,name:"close",onClick:e.clear,class:"cursor-pointer"},null,8,["onClick"])):E("",!0),l(Z,{color:"primary",disable:e.keyword==="",icon:"search",label:e.searchMode,split:"",onClick:e.search},{default:a(()=>[l(Y,null,{default:a(()=>[d((r(),u(p,{clickable:"",onClick:o[0]||(o[0]=t=>e.switchMode("\u7D22\u4E66\u53F7\u641C\u7D22"))},{default:a(()=>[l(_,null,{default:a(()=>[l(h,null,{default:a(()=>[ce]),_:1})]),_:1})]),_:1})),[[g]]),d((r(),u(p,{clickable:"",onClick:o[1]||(o[1]=t=>e.switchMode("\u6761\u7801\u53F7\u641C\u7D22"))},{default:a(()=>[l(_,null,{default:a(()=>[l(h,null,{default:a(()=>[de]),_:1})]),_:1})]),_:1})),[[g]]),l(F,{spaced:"true"}),l(h,{header:""},{default:a(()=>[ue]),_:1}),d((r(),u(p,{clickable:"",onClick:o[2]||(o[2]=t=>e.switchMode("\u7CBE\u786E\u641C\u7D22"))},{default:a(()=>[l(_,null,{default:a(()=>[l(h,null,{default:a(()=>[fe]),_:1})]),_:1})]),_:1})),[[g]]),d((r(),u(p,{clickable:"",onClick:o[3]||(o[3]=t=>e.switchMode("\u9996\u5B57\u641C\u7D22"))},{default:a(()=>[l(_,null,{default:a(()=>[l(h,null,{default:a(()=>[me]),_:1})]),_:1})]),_:1})),[[g]]),d((r(),u(p,{clickable:"",onClick:o[4]||(o[4]=t=>e.switchMode("\u6A21\u7CCA\u641C\u7D22"))},{default:a(()=>[l(_,null,{default:a(()=>[l(h,null,{default:a(()=>[be]),_:1})]),_:1})]),_:1})),[[g]])]),_:1})]),_:1},8,["disable","label","onClick"])]),_:1},8,["modelValue","placeholder"])]),_:1})]),_:1})]),l(K,null,{default:a(()=>[d(y("div",null,[l(ee,{rows:e.books,columns:e.bookColumns,pagination:{rowsPerPage:10},"row-key":"bookID",class:"sticky-header-table",style:O(s.$q.screen.lt.sm?"height: calc(100vh - 13.3rem)":"height: calc(100vh - 9.5rem)")},{body:a(t=>[l(oe,{props:t,onClick:v=>e.bookDialog.show(t.row)},{default:a(()=>[(r(!0),C(U,null,W(e.bookColumns,v=>(r(),u($,{key:v.name,props:t},{default:a(()=>[f(X(t.row[v.name]),1)]),_:2},1032,["props"]))),128))]),_:2},1032,["props","onClick"])]),_:1},8,["rows","columns","style"]),l(ae,{showing:e.loading},{default:a(()=>[l(J,{size:"50px",color:"primary"})]),_:1},8,["showing"])],512),[[Q,e.books.length!==0]])]),_:1}),l(b,{ref:"bookDialog",onRefresh:e.search},null,8,["onRefresh"])])}var Se=j(ne,[["render",he]]);export{Se as default};