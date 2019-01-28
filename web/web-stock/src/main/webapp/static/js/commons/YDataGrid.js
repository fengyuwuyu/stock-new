//匿名函数
var YDataGrid = function(config){
		config = config || {};
		var dataGrid = config.dataGrid || {};
		var actionUrl =  config.action || {};
		var msg = config.msg;
		var Action = {	
						'save':actionUrl.save || 'save.do',
						'getById':actionUrl.getById || 'getById.do',
						'remove':actionUrl.remove || 'remove.do'
						};		
		//数据表格
		var Grid = $('#data-list');
		//表单
		var Form = {
						search:$('#searchForm'),
						list:$('#listForm'),
						edit:$('#editForm')
					};
		//添加、修改对话框
		var Win = { edit:$('#edit-win') };			
		//默认方法
		var Handler = {
						//查询方法
						search:function(callback){
							Events.refresh();
							//回调函数
							if(jQuery.isFunction(callback)){
								callback();
							}
							return false;	
						},
						//重置方法
						reset:function(callback){
							Form.search.form('reset');
							//回调函数
							if(jQuery.isFunction(callback)){
								callback();
							}
							return false;	
						},
						//添加方法
						add:function(callback){
							Win.edit.dialog('open');
							Form.edit.form('reset');
							//回调函数
							if(jQuery.isFunction(callback)){
								callback();
							}
						},
						//修改方法
						edit:function(callback){
							var rows = Utils.getCheckedRows();
							if (Utils.checkSelectOne(rows)){
								var data ={};
								var idKey = dataGrid.idField || 'id';//主键名称
			 					data[idKey] = (rows[0][idKey]);
								YiYa.getById(Action.getById,data,function(result){
									Form.edit.form('reset');
									Form.edit.form('load',result.data);
									Win.edit.dialog('open'); 
									//回调函数
									if(jQuery.isFunction(callback)){
										callback(result);
									}
								});
							}
						},
						//刷新方法
						refresh:function(callback){
							var param = Form.search.serializeObject();
							//过滤掉为空的参数
							param = YiYa.filterNull(param);
							Grid.datagrid('reload',param);
							//回调函数
							if(jQuery.isFunction(callback)){
								callback();
							}
						},
						//删除方法
						remove:function(callback){
							var rows = Utils.getCheckedRows();
							if (Utils.checkSelectOne(rows)){
								YiYa.confirm('询问信息',msg||'确认要删除记录吗？',function(r){  
								    if (r){
								    	var data ={};
										var idKey = dataGrid.idField || 'id';//主键名称
					 					data[idKey] = (rows[0][idKey]);
								   		YiYa.remove(Action.remove,data,function(result){
											Events.refresh();
											//回调函数
											if(jQuery.isFunction(callback)){
												callback(result);
											}
										});
								    }  
								});
							}
						},
						//保存方法
						save:function(callback){
							if(Form.edit.form('validate')){
								Form.edit.attr('action',Action.save);
								YiYa.save(Form.edit,function(data){
										 Win.edit.dialog('close');
									     Events.refresh();
									     //回调函数
										if(jQuery.isFunction(callback)){
											callback(data);
										}
								});
							 }
						},
						//关闭方法
						close:function (callback){
							YiYa.confirm('询问信息','确认要关闭窗口吗？',function(r){  
							    if (r){  
							     	Win.edit.dialog('close');
							     	//回调函数
									if(jQuery.isFunction(callback)){
										callback(data);
									}
							    }
							});
						}
					};		
		//工具类
		var Utils = {
						getCheckedRows:function(){
							return Grid.datagrid('getChecked');			
						},
						//检查数据表格是否有勾选的行, 有返回 true,没有返回false
						checkSelect:function(rows){
							if(rows && rows.length > 0){
								return true;
							}
							YiYa.alert('警告信息','未选中记录！','warning');  
							return false;
							
						},
						//检查数据表格是否只勾选了一行,是返回 true,否返回false
						checkSelectOne:function(rows){
							if(!Utils.checkSelect(rows)){
								return false;
							}
							if(rows.length == 1){
								return true;
							}
							YiYa.alert('警告信息','只能选择一行记录！','warning');  
							return false;
						},
						//检查数据表格是否只勾选了一行,是返回 true,否返回false
						checkNextOne:function(rows){
							if(!Utils.checkSelect(rows)){
								return false;
							}							
							var maxIndex = Grid.datagrid('getRows').length;
							var rowIndex = Grid.datagrid('getRowIndex',rows[0]);
							if(rowIndex + 1 < maxIndex){
								Grid.datagrid('selectRow',rowIndex + 1);
								return true;
							}else{
								YiYa.alert('警告信息','只能为当页用户连续开卡！','warning');  
								return false;
							}
						}
					};		
		//自定义事件
		var evt= config.event || {};		
		//默认事件
		var Events ={
						//查询事件
						search:evt.search || Handler.search,
						//查询事件
						reset:evt.reset || Handler.reset,
						//添加事件
						add:evt.add || Handler.add,
						//修改事件
						edit:evt.edit || Handler.edit,
						//刷新事件
						refresh:evt.refresh || Handler.refresh,
						//删除事件
						remove:evt.remove || Handler.remove,
						//保存事件
						save:evt.save || Handler.save,
						//关闭事件
						close:evt.close ||  Handler.close
					};		
		//初始化工具栏并绑定事件
		var bar_add = {	
						id:'btn-add',
						text:'添加',
						iconCls:'icon-add',
						btnType:'add',
						handler: Events.add
					  };
		var bar_edit = {
							id:'btn-edit',
							text:'修改',
							iconCls:'icon-edit',
							btnType:'edit',
							handler: Events.edit
					   };
		var bar_remove = { 	id:'btn-remove',
							text:'删除',
							iconCls:'icon-remove',
							btnType:'remove',
							handler:Events.remove	
						 };
		var toolbarConfig = [bar_add,bar_edit,bar_remove];
		var getToolbar = function (){			
			var tbars = [];
			if (dataGrid.toolbar != undefined && dataGrid.toolbar.length > 0) {
				for ( var i = 0; i < dataGrid.toolbar.length; i++) {
					var bar = dataGrid.toolbar[i];
					if(!bar){
						continue;
					}
					if(bar.btnType=='add'){
						tbars.push({id:bar.id || bar_add.id,text:bar.text || bar_add.text ,iconCls: bar.iconCls || bar_add.iconCls, btnType: bar.btnType || bar_add.btnType,handler:bar.handler || bar_add.handler});
						continue;
					}
					if(bar.btnType=='edit'){
						tbars.push({id:bar.id || bar_edit.id,text:bar.text || bar_edit.text ,iconCls: bar.iconCls || bar_edit.iconCls,btnType: bar.btnType || bar_edit.btnType,handler:bar.handler || bar_edit.handler});
						continue;
					}
					if(bar.btnType=='remove'){
						tbars.push({id:bar.id || bar_remove.id,text:bar.text || bar_remove.text ,iconCls: bar.iconCls || bar_remove.iconCls,btnType: bar.btnType || bar_remove.btnType,handler:bar.handler || bar_remove.handler});
						continue;
					}
					tbars.push({id:bar.id,text:bar.text,iconCls:bar.iconCls,btnType: bar.btnType,handler:bar.handler,disabled:bar.disabled});
				}
			}else{
				tbars = toolbarConfig;
			}
			return tbars;			
		};	
		//初始化数据表格
		var initGrid = function(){
			var fitColumns;
			if (dataGrid.fitColumns != undefined) {
				fitColumns = dataGrid.fitColumns;
			}else{
				fitColumns = true;
			}
			var singleSelect;
			if (dataGrid.singleSelect != undefined) {
				singleSelect = dataGrid.singleSelect;
			}else{
				singleSelect = true;
			}
			var dataconfig = {
				title:dataGrid.showGridTitle?dataGrid.title || '数据表格':null,
				iconCls:dataGrid.iconCls || 'icon-grid',
				height:dataGrid.height || YiYa.fixWidth(0.8)/2,
				width:dataGrid.width || YiYa.fixWidth(1),
				fitColumns:fitColumns,
				fit : dataGrid.fit||false,
				nowrap:false,
				autoRowHeight:false,
				striped:true,
				//collapsible:true,
				remoteSort:false,
				pagination:dataGrid.pagination|true,
				pageSize : dataGrid.pageSize || 15,
				pageList : dataGrid.pageList || [15,30,45,60,75],
				rownumbers:true,
				singleSelect:singleSelect,
				checkOnSelect:true,
				selectOnCheck:true,
				url:dataGrid.url,
				method:dataGrid.method || 'post',
				loadMsg:dataGrid.loadMsg || '加载中 ... ',
				idField:dataGrid.idField || 'id',
				columns:dataGrid.columns,
				toolbar:dataGrid.showButton==false?[]:dataGrid.toolbar2 || getToolbar(),
				onLoadSuccess:dataGrid.onLoadSuccess || function(){
					Grid.datagrid('clearSelections');
					Grid.datagrid('clearChecked');
				}
			};
			if(dataGrid.onDblClickRow){
				dataconfig.onDblClickRow = dataGrid.onDblClickRow;
			}
			Grid.datagrid(dataconfig);
		};	
		//为查询按钮绑定事件
		var initForm = function(){
			if(Form.search && Form.search[0]){
				Form.search.find('#btn-search').click(Events.search); 
				$('#btn-reset').click(Events.reset); 
			}
		};	
		//为保存、关闭按钮绑定事件
		var initWin = function(){
			if(Win.edit && Win.edit[0]){
				//判断页面是否设置buttons，如果没有设置默认按钮
				var btns = Win.edit.attr('buttons');
				if(!btns){
					//设置保存,关闭按钮
					Win.edit.dialog({
						buttons:[
							{
								text:'保存',
								handler:Events.save
							},
							{
								text:'关闭',
								handler:Events.close
							}
						]
					});
				}else{
					//Win.edit.find('#btn-save').click(Events.save);//保存事件
					//Win.edit.find('#btn-close').click(Events.close);//关闭窗口
					$('#btn-save').click(Events.save);//保存事件
					$('#btn-close').click(Events.close);//关闭窗口
				}
			}
		};		
		//返回属性		
		this.grid = Grid;		
		this.form = Form;
		this.win = Win;
		this.handler = Handler;		
		this.utils = Utils;		
		//初始化方法
		this.init = function(){
			initGrid();
			initForm();
			initWin();
		}
};