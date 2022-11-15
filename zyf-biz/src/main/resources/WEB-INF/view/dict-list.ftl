<!DOCTYPE html>
<html>
<head>
    <title>${title}</title>
    <link rel="icon" href="data:;base64,=">
    <script src="/vue.js"></script>
    <script src="/axios.min.js"></script>
    <script src="/request.js"></script>
</head>
<body>
<div>
    <h1><a href="/department/view/list">部门列表页</a></h1>
    <h1>${title}</h1>
</div>
<div id="app">
    <div>
        <input v-show="!isShowAddForm &&!isShowModify&&!isChangeSort" type="button" value="新增" @click="showAddForm">
        <input v-show="isShowAddForm ||isShowModify||isChangeSort" type="button" value="关闭" @click="hideAll">
    </div>

    <div v-show="isShowAddForm">
        <div>
            <span>上级字典:</span>
            <select v-model="form.parentId">
                <option value="-1">请选择</option>
                <option v-for="item in list" :value="item.id">{{item.dicDescription}}</option>
            </select>
        </div>
        <div>
            <span>字典名:</span>
            <input type="text" v-model="form.dicDescription"/>
        </div>
        <div>
            <span>字典编码:</span>
            <input type="text" v-model="form.dicCode"/>
        </div>
        <div>
            <span>字典值:</span>
            <input type="text" v-model="form.dicValue"/>
        </div>
        <div>
            <input type="button" value="提交" @click="save"/>
        </div>
    </div>


    <div v-if="isShowModify">
        <div>
            <span>上级字典:</span>
            <select v-model="updateForm.parentId">
                <option value="-1">请选择</option>
                <option v-for="item in list" :value="item.id">{{item.dicDescription}}</option>
            </select>
        </div>
        <div>
            <span>字典名:</span>
            <input type="text" v-model="updateForm.dicDescription"/>
        </div>
        <div>
            <span>字典编码:</span>
            <input type="text" v-model="updateForm.dicCode"/>
        </div>
        <div>
            <span>字典值:</span>
            <input type="text" v-model="updateForm.dicValue"/>
        </div>
        <div>
            <input type="button" value="提交" @click="update"/>
        </div>
    </div>

    <div style="height: 20px;">
        <div>{{successMsg}}</div>
        <div>{{errorMsg}}</div>
    </div>

    <div v-if="isChangeSort">
        <span>交换对象:</span>
        <select v-model="targetId" :value="targetId" @change="changeSortConfirm">
            <option value="-1">请选择</option>
            <option v-for="item in changeList" :value="item.id">{{item.dicDescription}}</option>
        </select>
    </div>


    <div>
        <ul>
            <li v-for="item in list">
                <span>&nbsp;&nbsp;</span>
                <span>&nbsp;&nbsp;</span>
                <span>【{{item.dicDescription}}】     @    {{item.dicCode}}</span>
                <span>&nbsp;&nbsp;</span>
                <input type="button" value="删除" @click="del(item);">
                <span>&nbsp;&nbsp;</span>
                <input type="button" value="置顶" @click="upTop(item);">
                <span>&nbsp;&nbsp;</span>
                <input type="button" value="上移" @click="upMove(item);">
                <span>&nbsp;&nbsp;</span>
                <input type="button" value="下移" @click="downMove(item);">
                <span>&nbsp;&nbsp;</span>
                <input type="button" value="交换" @click="changeSort(item);">
                <span>&nbsp;&nbsp;</span>
                <input type="button" value="修改" @click="showModify(item);">

                <treeitem v-if="item.children&&item.children.length>0"
                          :item="item"
                          @del="del"
                          @up-top="upTop"
                          @up-move="upMove"
                          @down-move="downMove"
                          @change-sort="changeSort"
                          @show-modify="showModify"></treeitem>

            </li>
        </ul>
    </div>

</div>
</body>
<script>
    var app = new Vue({
      el: '#app',
      props:[],
      data: {
        query: {
            parentId: '',
            dicDescription: '',
            dicCode: '',
            dicValue: '',
        },
        form: {
            parentId: '-1',
            dicDescription: '',
            dicCode: '',
            dicValue: '',
        },
        updateForm: {
            id: '',
            parentId: '-1',
            dicDescription: '',
            dicCode: '',
            dicValue: '',
        },
        list: [],

        successMsg: '',
        errorMsg: '',

        isShowAddForm: false,
        isShowModify: false,
        isChangeSort: false,
        source: {
            parentId: '-1',
        },
        targetId: '',

      },
      watch: {
          // source(newVal, oldVal) {},
      },
      async created() {
        await this.getList()
      },
      async mounted() {
      },
      methods: {

        async getList() {
            let res = await api({
                method: 'post',
                url:"/dict/list",
                headers: {
                  "Content-Type": "application/json",
                },
                data: this.query,
            })
            if (res) {
                // this.showMsg('查询成功!')
                let dataList = res.data
                // console.log(dataList)
                // 找出根节点
                let root = dataList.filter( temp => !dataList.find(item => item.id === temp.parentId) )
                root.sort((a,b)=> {
                    return a.sort-b.sort
                })

                const getChilds = newlist => {
                    return newlist.map(item => {
                        let children = dataList.filter(temp => temp.parentId === item.id)
                        children.sort((a,b)=> {
                            return a.sort-b.sort
                        })
                        item.children = getChilds(children)
                        return item
                    })
                }
                dataList = getChilds(root)
                this.list = dataList
            }
        },

        async save() {
            let res = await api({
                method: 'post',
                url:"/dict/add",
                headers: {
                  "Content-Type": "application/json",
                },
                data: this.form,
            })
            if (res) {
                this.showMsg('新增成功!')
                this.hideAll()
                await this.getList()
            }
        },

        async update() {
            let res = await api({
                method: 'post',
                url:"/dict/update",
                headers: {
                  "Content-Type": "application/json",
                },
                data: this.updateForm,
            })
            if (res) {
                this.showMsg('修改成功!')
                this.hideAll()
                await this.getList()
            }
        },


        showAddForm() {
           this.isShowAddForm = true
           this.isShowModify = false
           this.isChangeSort = false
        },


        showModify(item) {
           this.isShowAddForm = false
           this.isShowModify = true
           this.isChangeSort = false
           this.updateForm = JSON.parse(JSON.stringify(item))
        },

        async del(item) {
            let res = await api({
                method: 'post',
                url:"/dict/del",
                headers: {
                  "Content-Type": "application/json",
                },
                data: {id:item.id},
            })
            if (res) {
                this.showMsg('删除成功!')
                await this.getList()
            }
        },


        async upTop(item) {
            let res = await api({
                method: 'post',
                url:"/dict/upTop",
                headers: {
                  "Content-Type": "application/json",
                },
                data: {id:item.id, parentId: item.parentId},
            })
            if (res) {
                this.showMsg('置顶成功!')
                await this.getList()
            }
        },

        async upMove(item) {
            let res = await api({
                method: 'post',
                url:"/dict/upMove",
                headers: {
                  "Content-Type": "application/json",
                },
                data: {id:item.id, parentId: item.parentId},
            })
            if (res) {
                this.showMsg('上移成功!')
                await this.getList()
            }
        },

        async downMove(item) {
            let res = await api({
                method: 'post',
                url:"/dict/downMove",
                headers: {
                  "Content-Type": "application/json",
                },
                data: {id:item.id, parentId: item.parentId},
            })
            if (res) {
                this.showMsg('下移成功!')
                await this.getList()
            }
        },

        async changeSort(item) {
            this.isShowAddForm = false
            this.isChangeSort = true
            this.isShowModify = false
            this.source = item
        },


        async changeSortConfirm() {
            let res = await api({
                method: 'post',
                url:"/dict/changeSort?preId=" + this.source.id + "&nextId=" + this.targetId,
            })
            if (res) {
                this.showMsg('交换成功!')
                this.hideAll()
                await this.getList()
            }
        },

        showMsg(msg) {
            this.successMsg = msg
            this.cleanMsg()
        },

        cleanMsg() {
            setTimeout(() => {
                this.successMsg = ''
            }, 1000)
        },

        hideAll() {
            this.isShowAddForm = false
            this.isChangeSort = false
            this.isShowModify = false
        },

      },
      computed: {
          changeList() {
              if (this.source.parentId === '-1') {
                  return this.list.filter(s=>s.id!==this.source.id)
              } else {
                  const parent = this.list.find(s=>s.id === this.source.parentId)
                  if (parent.children&&parent.children.length>0) {
                      return parent.children.filter(s=>s.id!==this.source.id)
                  } else {
                      return []
                  }
              }
          }
      },
      components: {
         'treeitem': Vue.component('tree-item',{
            props: ['item'],
            data: function () {
              return {
              }
            },
            methods: {
                showModify(iitem) {
                   this.$emit('show-modify', iitem)
                },

                async del(iitem) {
                   this.$emit('del', iitem)
                },

                async upTop(iitem) {
                   this.$emit('up-top', iitem)
                },

                async upMove(iitem) {
                   this.$emit('up-move', iitem)
                },

                async downMove(iitem) {
                   this.$emit('down-move', iitem)
                },

                async changeSort(iitem) {
                   this.$emit('change-sort', iitem)
                },
            },
            template: '<ul><li v-for="iitem in item.children"> <span>【{{iitem.dicDescription}}】     @    {{iitem.dicCode}}</span> <input type="button" value="删除" @click="del(iitem);"> <input type="button" value="置顶" @click="upTop(iitem);"> <input type="button" value="上移" @click="upMove(iitem);"> <input type="button" value="下移" @click="downMove(iitem);"> <input type="button" value="交换" @click="changeSort(iitem);"> <input type="button" value="修改" @click="showModify(iitem);"> <tree-item v-if="iitem.children&&iitem.children.length>0" :item="iitem" @del="del" @up-top="upTop" @up-move="upMove" @down-move="downMove" @change-sort="changeSort" @show-modify="showModify"></tree-item></li></ul>'
         }),
      },


    })


</script>
</html>
