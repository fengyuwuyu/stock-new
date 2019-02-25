/**
 * 股票监控管理初始化
 */
var Monitor = {
    id: "MonitorTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Monitor.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {
            title: '序号',
            field: '',
            align: 'center',
            formatter: Feng.getTableSerialNumberFunc(Monitor.id)
        },
            {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '股票代码', field: 'symbol', visible: true, align: 'center', valign: 'middle'},
            {title: '开始时间', field: 'beginDate', visible: true, align: 'center', valign: 'middle'},
            {title: '结束时间', field: 'endDate', visible: true, align: 'center', valign: 'middle'},
            {title: '买入价格', field: 'buyPrice', visible: true, align: 'center', valign: 'middle'},
            {title: '最高出售价格', field: 'sellPriceHigh', visible: true, align: 'center', valign: 'middle'},
            {title: '最低出售价格', field: 'sellPriceLow', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'monitorType', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createDate', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateDate', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Monitor.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Monitor.seItem = selected[0];
        return true;
    }
};

/**
 * 重置搜索表单，并刷新
 */
Monitor.resetSearchForm = function () {
    Feng.clearSearchForm(function() {
        Monitor.search();
    });
};

/**
 * 点击添加股票监控
 */
Monitor.openAddMonitor = function () {
    var index = layer.open({
        type: 2,
        title: '添加股票监控',
        area: ['800px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/monitor/monitor_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看股票监控详情
 */
Monitor.openMonitorDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '股票监控详情',
            area: ['800px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/monitor/monitor_update/' + Monitor.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除股票监控
 */
Monitor.delete = function () {
    if (this.check()) {
	    var operation = function() {
	    	var ajax = new $ax(Feng.ctxPath + "/monitor/delete", function (data) {
	            Feng.success("删除成功!");
	            Monitor.table.refresh();
        	}, function (data) {
            	Feng.error("删除失败!" + data.responseJSON.message + "!");
        	});
	        ajax.set("monitorId",Monitor.seItem.id);
	        ajax.start();
	    };
        
        Feng.confirm("是否删除该选项？", operation);
    }
};

/**
 * 查询股票监控列表
 */
Monitor.search = function () {
    var queryData = $('#searchForm').serializeObject();
    Monitor.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Monitor.initColumn();
    var table = new BSTable(Monitor.id, "/monitor/list", defaultColunms);
    Monitor.table = table.init();
});
