<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<!-- Standard Meta -->
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<title>专家排班</title>
<s:include value="/comm_inc.jsp" />
<script type="text/javascript">
	
	$(function () {
		
		$('#btnSubmit').on('click', function (e) {
			if (window.confirm("确定提交修改？")) {
				$("#expertForm").attr("action","expertAction!scheduSubmit");
				$("#expertForm").submit();
			}
		 });
		
		
		if ($('#message').val() != '') {
			$.scojs_message($('#message').val(), $.scojs_message.TYPE_ERROR);
			$('#message').val("");
		}
		
		if ($('#okmessage').val() != '') {
			$.scojs_message($('#okmessage').val(), $.scojs_message.TYPE_OK);
			$('#okmessage').val("");
			$('#btnSubmit').attr("disabled", "true");
			
			setTimeout("menuHref('<%=request.getContextPath()%>/expertAction!expertFrontList')", 2000);
		}
		
	});
	
	
	function onScheduApply(obj, date, name) {
		if ($(obj).hasClass("active") ) {
			$("#before_date").val(date);
		} else {
			$("#after_date").val(date);
		}
		
		$("#update_user").val(name);
		
		$("#expertForm").attr("action","expertAction!scheduApplyInfo");
		$("#expertForm").submit();
	}
	
	
	function onCheckSchedu(obj, expertId) {
		
		// 如果已经选中
		if ($(obj).hasClass("active")) {
			$(obj).removeClass("active");
			$("#"+expertId).val("1");
		} else {
			$(obj).addClass("active");
			$("#"+expertId).val("0");
		}
	}
	
	function depChange() {
		
		$("#expertForm").attr("action","expertAction!expertSchedu");
		$("#expertForm").submit();
	}
	
	function curWeek() {
		var date = new Date();
		$("#weekFirDate").val(date.toLocaleDateString());
		
		$("#expertForm").attr("action","expertAction!expertSchedu");
		$("#expertForm").submit();
	}
	function preWeek() {
		var date = new Date($("#weekFirDate").val());
		
		// 获取上周
		date.setDate(date.getDate()-7); 
		
		$("#weekFirDate").val(date.toLocaleDateString());
		
		$("#expertForm").attr("action","expertAction!expertSchedu");
		$("#expertForm").submit();
	}
	function nextWeek() {
		
		var date = new Date($("#weekFirDate").val());
		
		// 获取下周
		date.setDate(date.getDate()+7); 
		
		$("#weekFirDate").val(date.toLocaleDateString());
		
		$("#expertForm").attr("action","expertAction!expertSchedu");
		$("#expertForm").submit();
	}
	
</script>
</head>
<body>
	<form id="expertForm" action="" method="post">
		<input type="hidden" id="message" name="message" value="${errormessage}">
		<input type="hidden" id="okmessage" name="okmessage" value="${okmessage}">
		<input type="hidden" id="weekFirDate" name="weekFirDate" value="${weekFirDate}">
		<input type="hidden" id="theFrom" name="theFrom" value="${theFrom}">
		<input type="hidden" id="expert_id_search" name="seaExpert.expert_id" value="${seaExpert.expert_id}">
		<input type="hidden" id="expert_name_search" name="seaExpert.expert_name" value="${seaExpert.expert_name}">
		<input type="hidden" id="status_search" name="seaExpert.status" value="${seaExpert.status}">
		<input type="hidden" id="departmentid_search" name="seaExpert.departmentid" value="${seaExpert.departmentid}">
		<input type="hidden" id="before_date" name="scheduApplyBean.before_date">
		<input type="hidden" id="after_date" name="scheduApplyBean.after_date">
		<input type="hidden" id="update_user" name="scheduApplyBean.update_user">
		<input type="hidden" id="flag" name="seaExpert.flag" value="${seaExpert.flag}">
		<input type="hidden" id="title" name="seaExpert.title" value="${seaExpert.title}">
		
		<div class="person-content">
			<div class="iframe-title">|&nbsp;&nbsp;我的排班</div>
			
			<div class="itemInfo-body">
					<ul>
						<li class="leftArrow" onclick="preWeek();"><img class="img-col" alt="" src="<%=request.getContextPath()%>/image/left_arrow.png"></li>
						<li class="preWeek" onclick="preWeek();"><a>上一周</a></li>
						<li class="curWeek" onclick="curWeek();"><a>当前周</a></li>
						<li class="nextWeek" onclick="nextWeek();"><a>下一周</a></li>
						<li class="rightArrow" onclick="nextWeek();"><img class="img-col" alt="" src="<%=request.getContextPath()%>/image/right_arrow.png"></li>
					</ul>
				<div class="itemInfo-item">
					<table class="schedu-table">
						<tr class="header">
							<td></td>
							<td><s:property value="weekList.get(0).substring(5, 10)"></s:property>(一)</td>
							<td><s:property value="weekList.get(1).substring(5, 10)"></s:property>(二)</td>
							<td><s:property value="weekList.get(2).substring(5, 10)"></s:property>(三)</td>
							<td><s:property value="weekList.get(3).substring(5, 10)"></s:property>(四)</td>
							<td><s:property value="weekList.get(4).substring(5, 10)"></s:property>(五)</td>
							<td style="color: #ce2020;"><s:property value="weekList.get(5).substring(5, 10)"></s:property>(六)</td>
							<td style="color: #ce2020;"><s:property value="weekList.get(6).substring(5, 10)"></s:property>(日)</td>
						</tr>
						<s:iterator var="expert" value="expertBeanList" status="status">
							<tr class="item">
								<td>
									${expert.expert_name }
									<input type="hidden" id="expert_id_${expert.expert_id }" name="expertBean.expertBeanList[${status.index }].expert_id" value="${expert.expert_id }">
								</td>
								<s:iterator var="scheduTmp" value="#expert.scheduList" status="index">
									<s:if test="#scheduTmp == 'none'">
										<td onclick="onCheckSchedu(this, '${expert.expert_id}${index.index }' );">
											<input type="hidden" id="${expert.expert_id}${index.index }" name="expertBean.expertBeanList[${status.index }].scheduBeanList[${index.index }].delete_flag" value="1">
											<input type="hidden" name="expertBean.expertBeanList[${status.index }].scheduBeanList[${index.index }].schedu_date" value="${weekList.get(index.index)}">
										</td>
									</s:if>
									<s:else>
										<td class="active" onclick="onCheckSchedu(this, '${expert.expert_id}${index.index }' );">
											<input type="hidden" id="${expert.expert_id}${index.index }" name="expertBean.expertBeanList[${status.index }].scheduBeanList[${index.index }].delete_flag" value="0">
											<input type="hidden" name="expertBean.expertBeanList[${status.index }].scheduBeanList[${index.index }].schedu_date" value="${weekList.get(index.index)}">
										</td>
									</s:else>
								</s:iterator>
							</tr>
						</s:iterator>
					</table>
				</div>
				<div class="text-box save-button" style="margin: 30px auto;width: 120px;">
					<button type="button" id="btnSubmit">提交 ></button>
				</div>
			</div>
		</div>
	</form>
</body>
</html>