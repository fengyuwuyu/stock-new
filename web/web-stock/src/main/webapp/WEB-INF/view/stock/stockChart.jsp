<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>k线图</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/jquery-easyui-1.4.0/themes/icon.css" />
</head>

<body>
	<input type="hidden" id="b" value="${begin }" />
	<input type="hidden" id="e" value="${end }" />
	<!-- <input  id="bb" type="hidden" class="easyui-datebox" />
 	<input id="aa" type="hidden" class="easyui-datebox" />  -->
	<input type="hidden" id="symbol" value="${symbol }" />
	<div id="container" style="width: 100%, height:100%"></div>

	<script type="text/javascript" src="${ctxPath}/js/commons/jquery-1.11.0-min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.4.0/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.4.0/locale/easyui-lang-zh_CN.js"></script>	
	 
 		<!-- 
 		<script src="https://img.hcharts.cn/highstock/highstock.js"></script>
        <script src="https://img.hcharts.cn/highcharts/modules/exporting.js"></script>
        <script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
        <script src="https://img.hcharts.cn/highcharts/modules/drag-panes.js"></script>
        <script src="https://img.hcharts.cn/highstock/indicators/indicators.js"></script>
        <script src="https://img.hcharts.cn/highstock/indicators/volume-by-price.js"></script> 
        
        -->


	<script type="text/javascript" src="${ctxPath}/js/commons/highstock.js"></script>
	<!-- 
	
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://code.highcharts.com/stock/highstock.js"></script>
	<script src="https://code.highcharts.com/stock/modules/drag-panes.js"></script>
	<script src="https://code.highcharts.com/stock/modules/exporting.js"></script> -->
	<script type="text/javascript" src="${ctxPath}/js/commons/urls.js"></script>
	<script type="text/javascript" src="${ctxPath}/static/modular/stock/stockChart.js"></script>

</body>
</html>
