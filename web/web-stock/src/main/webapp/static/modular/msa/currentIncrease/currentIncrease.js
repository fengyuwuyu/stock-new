/**
 * 每日股票汇总管理初始化
 */
var CurrentIncrease = {
    id: "CurrentIncreaseTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CurrentIncrease.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {
            title: '序号',
            field: '',
            align: 'center',
            formatter: Feng.getTableSerialNumberFunc(CurrentIncrease.id)
        },
            {title: '主键', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '股票代码', field: 'symbol', visible: true, align: 'center', valign: 'middle'},
            {title: '第一阶段天', field: 'firstLevelDay', visible: false, align: 'center', valign: 'middle'},
            {title: '第一阶段涨幅', field: 'firstLevelIncrease', visible: true, align: 'center', valign: 'middle'},
            {title: '第二阶段天', field: 'secondLevelDay', visible: false, align: 'center', valign: 'middle'},
            {title: '第二阶段涨幅', field: 'secondLevelIncrease', visible: true, align: 'center', valign: 'middle'},
            {title: '第三阶段天', field: 'thirdLevelDay', visible: false, align: 'center', valign: 'middle'},
            {title: '第三阶段涨幅', field: 'thirdLevelIncrease', visible: true, align: 'center', valign: 'middle'},
            {title: '第四阶段天', field: 'fourLevelDay', visible: false, align: 'center', valign: 'middle'},
            {title: '第四阶段涨幅', field: 'fourLevelIncrease', visible: false, align: 'center', valign: 'middle'},
            {title: '第五阶段天', field: 'fiveLevelDay', visible: false, align: 'center', valign: 'middle'},
            {title: '第五阶段涨幅', field: 'fiveLevelIncrease', visible: false, align: 'center', valign: 'middle'},
            {title: '股票类型', field: 'stockType', visible: true, align: 'center', valign: 'middle', formatter: function(value) {
            	switch(value) {
            		case 1:
            			return  '上涨';
            		case 2:
            			return  '下跌';
            		case 3:
            			return  '反弹';
            	}
            }},
            {title: '名称', field: 'name', visible: false, align: 'center', valign: 'middle'},
            {title: '代码', field: 'code', visible: false, align: 'center', valign: 'middle'},
            {title: '最高价', field: 'max', visible: false, align: 'center', valign: 'middle'},
            {title: '最低价', field: 'min', visible: false, align: 'center', valign: 'middle'},
            {title: '当天涨幅', field: 'increase', visible: true, align: 'center', valign: 'middle'},
            {title: '近十天涨幅', field: 'increases', visible: true, align: 'center', valign: 'middle'},
            {title: '近十天成交量', field: 'volumes', visible: true, align: 'center', valign: 'middle'},
            {title: '2天涨幅', field: 'twoIncrease', visible: true, align: 'center', valign: 'middle'},
            {title: '3天涨幅', field: 'thressIncrease', visible: true, align: 'center', valign: 'middle'},
            {title: '4天涨幅', field: 'fourIncrease', visible: false, align: 'center', valign: 'middle'},
            {title: '5天涨幅', field: 'fiveIncrease', visible: true, align: 'center', valign: 'middle'},
            {title: '10天涨幅', field: 'tenIncrease', visible: true, align: 'center', valign: 'middle'},
            {title: '15天涨幅', field: 'fifteenIncrease', visible: false, align: 'center', valign: 'middle'},
            {title: '20天涨幅', field: 'twentyIncrease', visible: false, align: 'center', valign: 'middle'},
            {title: '截止当前涨幅', field: 'maxIncrease', visible: true, align: 'center', valign: 'middle'},
            {title: '未来五天涨幅', field: 'futureFiveDayIncrease', visible: true, align: 'center', valign: 'middle'},
            {title: '未来十天涨幅', field: 'futureTenDayIncrease', visible: false, align: 'center', valign: 'middle'},
            {title: '未来十五天涨幅', field: 'futureFifteenDayIncrease', visible: false, align: 'center', valign: 'middle'},
            {title: '未来二十天涨幅', field: 'futureTwentyDayIncrease', visible: false, align: 'center', valign: 'middle'},
            {title: '未来十天涨幅', field: 'futureIncreases', visible: false, align: 'center', valign: 'middle'},
            {title: '未来十天成交量', field: 'futureVolumes', visible: false, align: 'center', valign: 'middle'},
            {title: '当天成交量比', field: 'dayVolumeAvg', visible: false, align: 'center', valign: 'middle'},
            {title: '两天成交量比', field: 'twoVolumeAvg', visible: false, align: 'center', valign: 'middle'},
            {title: '三天成交量比', field: 'threeVolumeAvg', visible: false, align: 'center', valign: 'middle'},
            {title: '四天成交量比', field: 'fourVolumeAvg', visible: false, align: 'center', valign: 'middle'},
            {title: '五天成交量比', field: 'fiveVolumeAvg', visible: false, align: 'center', valign: 'middle'},
            {title: '分析日期', field: 'msaDay', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
CurrentIncrease.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CurrentIncrease.seItem = selected[0];
        return true;
    }
};

/**
 * 重置搜索表单，并刷新
 */
CurrentIncrease.resetSearchForm = function () {
    Feng.clearSearchForm(function() {
        CurrentIncrease.search();
    });
};

/**
 * 点击添加每日股票汇总
 */
CurrentIncrease.openAddCurrentIncrease = function () {
    var index = layer.open({
        type: 2,
        title: '添加每日股票汇总',
        area: ['800px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/currentIncrease/currentIncrease_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看每日股票汇总详情
 */
CurrentIncrease.openCurrentIncreaseDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '每日股票汇总详情',
            area: ['800px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/currentIncrease/currentIncrease_update/' + CurrentIncrease.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除每日股票汇总
 */
CurrentIncrease.delete = function () {
    if (this.check()) {
	    var operation = function() {
	    	var ajax = new $ax(Feng.ctxPath + "/currentIncrease/delete", function (data) {
	            Feng.success("删除成功!");
	            CurrentIncrease.table.refresh();
        	}, function (data) {
            	Feng.error("删除失败!" + data.responseJSON.message + "!");
        	});
	        ajax.set("currentIncreaseId",CurrentIncrease.seItem.id);
	        ajax.start();
	    };
        
        Feng.confirm("是否删除该选项？", operation);
    }
};

/**
 * 查询每日股票汇总列表
 */
CurrentIncrease.search = function () {
    var queryData = $('#searchForm').serializeObject();
    CurrentIncrease.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CurrentIncrease.initColumn();
    var table = new BSTable(CurrentIncrease.id, "/currentIncrease/list", defaultColunms, {pageSize: 50});
    CurrentIncrease.table = table.init();
});
