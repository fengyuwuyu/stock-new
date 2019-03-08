/**
 * 最近最大涨幅分析管理初始化
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
            {title: '主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '股票代码', field: 'symbol', visible: true, align: 'center', valign: 'middle'},
            {title: '当天涨幅', field: 'increase', visible: true, align: 'center', valign: 'middle'},
            {title: '2天涨幅', field: 'twoIncrease', visible: true, align: 'center', valign: 'middle'},
            {title: '3天涨幅', field: 'thressIncrease', visible: true, align: 'center', valign: 'middle'},
            {title: '4天涨幅', field: 'fourIncrease', visible: true, align: 'center', valign: 'middle'},
            {title: '5天涨幅', field: 'fiveIncrease', visible: true, align: 'center', valign: 'middle'},
            {title: '10天涨幅', field: 'tenIncrease', visible: true, align: 'center', valign: 'middle'},
            {title: '15天涨幅', field: 'fifteenIncrease', visible: true, align: 'center', valign: 'middle'},
            {title: '20天涨幅', field: 'twentyIncrease', visible: true, align: 'center', valign: 'middle'},
            {title: '截止当前涨幅', field: 'maxIncrease', visible: true, align: 'center', valign: 'middle'},
            {title: '近十天涨幅', field: 'increases', visible: true, align: 'center', valign: 'middle'},
            {title: '近十天成交量', field: 'volumes', visible: true, align: 'center', valign: 'middle'},
            {title: '未来五天涨幅', field: 'futureFiveDayIncrease', visible: true, align: 'center', valign: 'middle'},
            {title: '未来十天涨幅', field: 'futureTenDayIncrease', visible: true, align: 'center', valign: 'middle'},
            {title: '未来十天涨幅', field: 'futureIncreases', visible: true, align: 'center', valign: 'middle'},
            {title: '未来十天成交量', field: 'futureVolumes', visible: true, align: 'center', valign: 'middle'},
            {title: '当天成交量比', field: 'dayVolumeAvg', visible: true, align: 'center', valign: 'middle'},
            {title: '两天成交量比', field: 'twoVolumeAvg', visible: true, align: 'center', valign: 'middle'},
            {title: '三天成交量比', field: 'threeVolumeAvg', visible: true, align: 'center', valign: 'middle'},
            {title: '四天成交量比', field: 'fourVolumeAvg', visible: true, align: 'center', valign: 'middle'},
            {title: '五天成交量比', field: 'fiveVolumeAvg', visible: true, align: 'center', valign: 'middle'},
            {title: '分析日期', field: 'msaDay', visible: true, align: 'center', valign: 'middle'}
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
 * 点击添加最近最大涨幅分析
 */
CurrentIncrease.openAddCurrentIncrease = function () {
    var index = layer.open({
        type: 2,
        title: '添加最近最大涨幅分析',
        area: ['800px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/currentIncrease/currentIncrease_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看最近最大涨幅分析详情
 */
CurrentIncrease.openCurrentIncreaseDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '最近最大涨幅分析详情',
            area: ['800px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/currentIncrease/currentIncrease_update/' + CurrentIncrease.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除最近最大涨幅分析
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
 * 查询最近最大涨幅分析列表
 */
CurrentIncrease.search = function () {
    var queryData = $('#searchForm').serializeObject();
    CurrentIncrease.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CurrentIncrease.initColumn();
    var table = new BSTable(CurrentIncrease.id, "/currentIncrease/list", defaultColunms);
    CurrentIncrease.table = table.init({pageSize: 100});
});
