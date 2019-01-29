/**
 * 初始化股票监控详情对话框
 */
var MonitorInfoDlg = {

};

/**
 * 关闭此对话框
 */
MonitorInfoDlg.close = function() {
    parent.layer.close(window.parent.Monitor.layerIndex);
}


/**
 * 提交添加
 */
MonitorInfoDlg.addSubmit = function() {
	var addForm = $('#addForm');
	if (!Feng.validateForm(addForm)) {
		return;
	}
	
	var data = addForm.serializeObjectFilterNull();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/monitor/add", function(data){
        Feng.success("添加成功!");
        window.parent.Monitor.table.refresh();
        MonitorInfoDlg.close();
    });
    ajax.set(data);
    ajax.start();
}

/**
 * 提交修改
 */
MonitorInfoDlg.editSubmit = function() {
	var editForm = $('#editForm');
	if (!Feng.validateForm(editForm)) {
		return;
	}
	
	var data = editForm.serializeObjectFilterNull();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/monitor/update", function(data){
        Feng.success("修改成功!");
        window.parent.Monitor.table.refresh();
        MonitorInfoDlg.close();
    });
    ajax.set(data);
    ajax.start();
}

$(function() {

});
