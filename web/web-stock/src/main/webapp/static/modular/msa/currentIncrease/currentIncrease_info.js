/**
 * 初始化每日股票汇总详情对话框
 */
var CurrentIncreaseInfoDlg = {

};

/**
 * 关闭此对话框
 */
CurrentIncreaseInfoDlg.close = function() {
    parent.layer.close(window.parent.CurrentIncrease.layerIndex);
}


/**
 * 提交添加
 */
CurrentIncreaseInfoDlg.addSubmit = function() {
	var addForm = $('#addForm');
	if (!Feng.validateForm(addForm)) {
		return;
	}
	
	var data = addForm.serializeObjectFilterNull();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/currentIncrease/add", function(data){
        Feng.success("添加成功!");
        window.parent.CurrentIncrease.table.refresh();
        CurrentIncreaseInfoDlg.close();
    });
    ajax.set(data);
    ajax.start();
}

/**
 * 提交修改
 */
CurrentIncreaseInfoDlg.editSubmit = function() {
	var editForm = $('#editForm');
	if (!Feng.validateForm(editForm)) {
		return;
	}
	
	var data = editForm.serializeObjectFilterNull();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/currentIncrease/update", function(data){
        Feng.success("修改成功!");
        window.parent.CurrentIncrease.table.refresh();
        CurrentIncreaseInfoDlg.close();
    });
    ajax.set(data);
    ajax.start();
}

$(function() {

});
