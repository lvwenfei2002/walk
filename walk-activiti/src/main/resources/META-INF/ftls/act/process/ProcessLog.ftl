<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#include "../../common/base/meta.ftl">
<#include "../../common/base/resources.ftl">
<title>流程日志</title>
</head>
<body>
<div class="wrapper">
	<div class="w-panel">
		<!-- 数据表 -->
		<div class="w-panel-content">
			<table id="dataGrid" class="easyui-datagrid" url="${request.contextPath}/act/process/queryProcessLogList/${orderId}" pagination="false">
				<thead>
					<tr>
						<th data-options="field:'logId',width:180">日志流水</th>
						<th data-options="field:'orderId',width:150">工单ID</th>
						<th data-options="field:'beginTaskDefName',width:150">开始节点</th>
						<th data-options="field:'endTaskDefName',width:150">结束节点</th>
						<th data-options="field:'dealStaffId',width:150">节点处理人</th>
						<th data-options="field:'dealTime',width:180">处理时间</th>
						<th data-options="field:'dealInfo',width:260,tip:true">处理信息</th>
						<th data-options="field:'candidateUsers',width:150,tip:true">节点候选人</th>
						<th data-options="field:'candidateGroups',width:150,tip:true">节点候选组</th>
						<th data-options="field:'backTag',width:120,formatter:function(val){return val=='1'?'回退':'正常'}">回退标识</th>
						<th data-options="field:'createTime',width:180">创建时间</th>
						<th data-options="field:'createStaffId',width:150">创建人</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
</body>
</html>
