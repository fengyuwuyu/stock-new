$package("YiYa.stockMain");

YiYa.stockMain = function(){
	_box = null;
	_this = {
		config : {
			dataGrid : {
				url : Feng.ctxPath +'searcher/findIncreaseTopn',
				idField : 'symbol',
				columns:[[
					{field:'ck',checkbox:true},
					{field : 'symbol',title:'股票编号',align:'center',width:80},
					{field : 'increase',title:'增长比',align:'center',width:80,sortable : true, hidden: false},
					{field : 'hasIncrease',title:'过去十天增长',align:'center',width:80,sortable : true},
					{field : 'maxIncrease',title:'最大增长',align:'center',width:80,sortable : true, hidden: true},
					{field : 'futureIncrease',title:'未來十天增长',align:'center',width:80,sortable : true, hidden: true},
					{field : 'volumeRatio',title:'成交量比率',align:'center',width:80,sortable : true, hidden: true},
					/*{field : 'open',title:'开盘价',align:'center',width:80},
					{field : 'close',title:'收盘价',align:'center',width:80},
					{field : 'volume',title:'成交量',align:'center',width:80},*/
					{field : 'increases',title:'历史涨幅',align:'center',width:200},
					{field : 'futureIncreases',title:'未来涨幅',align:'center',width:200, hidden: true},
					{field : 'volumes',title:'历史成交量',align:'center',width:200, hidden: false}/*,
					{field : 'closes',title:'历史收盘价',align:'center',width:200}*/
				]],
				onDblClickRow : function(index,row){
					var begin = $('#begin').val();
					window.open(Feng.ctxPath+'/stock/showChartIndex?symbol='+row.symbol+'&begin='+begin + '&type=1');
				},
				toolbar: [{
					id:'btn-detail',
					text:'走势详情',
					iconCls:'icon-edit',
//					btnType:'detail',
					handler: function() {
						var rows = _box.utils.getCheckedRows();
						if (_box.utils.checkSelectOne(rows)){
							var begin = $('#begin').val();
							window.open(Feng.ctxPath+'/stock/showChartIndex?symbol='+rows[0].symbol+'&begin='+begin + '&type=2');
						}
					}
				}, {
					id:'btn-detail',
					text:'未来涨幅',
					iconCls:'icon-edit',
//					btnType:'detail',
					handler: function() {
						var rows = _box.utils.getCheckedRows();
						if (_box.utils.checkSelectOne(rows)){
							alert(rows[0].futureIncrease)
						}
					}
				}]
			}
		},
		checkTime : function(){
			var time = this.getQueryTime();
			if(!time.begin||!begin.end){
				return false;
			}
			return true;
		},
		init : function(){
			_box = new YDataGrid(this.config);
			_box.init();
		}
	};
	return _this;
}();

$(function(){
	YiYa.stockMain.init();
});