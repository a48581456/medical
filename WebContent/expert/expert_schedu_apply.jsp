<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<!-- Standard Meta -->
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<title>排班申请</title>
<s:include value="/comm_inc.jsp" />
<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/datetimepicker/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<link href="<%=request.getContextPath()%>/bootstrap/datetimepicker/css/datetimepicker.min.css" rel="stylesheet" media="screen">
<%-- <link href="<%=request.getContextPath()%>/bootstrap/datetimepicker/sample in bootstrap v3/css/bootstrap.min.css" rel="stylesheet" media="screen"> --%>
<%-- <link href="<%=request.getContextPath()%>/bootstrap/datetimepicker/sample in bootstrap v3/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen"> --%>
<script type="text/javascript">
	
	
	$(function () {
		
		$('.form_datetime').datetimepicker({
	        language:  'zh-CN',
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0,
			format: 'yyyy/mm/dd'
	    });
		
		$('#btnSubmit').on('click', function (e) {
			
			if ($('#before_date').val() == '') {
				e.preventDefault();
		 	    $.scojs_message('调休前日期不能为空！', $.scojs_message.TYPE_ERROR);
			} else if ($('#after_date').val() == '') {
				e.preventDefault();
		 	    $.scojs_message('调休后日期不能为空！', $.scojs_message.TYPE_ERROR);
			} else if ($('#cause').val() == '') {
				e.preventDefault();
		 	    $.scojs_message('原因不能为空！', $.scojs_message.TYPE_ERROR);
			} else {
				if (window.confirm("确定提交调休？")) {
					$("#expertForm").attr("action","expertAction!scheduApply");
					$("#expertForm").submit();
				}
			}
		 });
		
		
		if ($('#message').val() != '') {
			$.scojs_message($('#message').val(), $.scojs_message.TYPE_ERROR);
		}
		
		if ($('#okmessage').val() != '') {
			$.scojs_message($('#okmessage').val(), $.scojs_message.TYPE_OK);
			$('#btnSubmit').attr("disabled", "true");
			
			setTimeout("menuHref('<%=request.getContextPath()%>/expertAction!expertSchedu?theFrom=front')", 2000);
		}
	});
	
	function onBack() {
		$("#expertForm").attr("action","expertAction!expertSchedu");
		$("#expertForm").submit();
	}
	
	function menuHref(url){
		window.location.href = url;
	}
	
</script>
</head>
<body>
	<form id="expertForm" action="" method="post" enctype="multipart/form-data">
		<input type="hidden" id="message" name="message" value="${errormessage}">
		<input type="hidden" id="okmessage" name="okmessage" value="${okmessage}">
		<input type="hidden" id="theFrom" name="theFrom" value="front">
		<input type="hidden" id="applyId" name="scheduApplyBean.id" value="${scheduApplyBean.id}">
		<input type="hidden" id="flag" name="seaExpert.flag" value="${seaExpert.flag}">
		<input type="hidden" id="title" name="seaExpert.title" value="${seaExpert.title}">
		<div class="person-content">
			<div class="iframe-title">|&nbsp;&nbsp;基础信息</div>
			<div class="itemInfo-body">
				<div class="itemInfo-item">
					
					<div class="input-group  date form_datetime"  data-date-format="yyyy/MM/dd" data-link-field="before_date">
			             <input class="form-control time-group" placeholder="调休前日期" type="text" value="<s:date name="scheduApplyBean.before_date" format="yyyy/MM/dd" nice="false"/>" disabled="disabled" >
						 <span class="input-group-addon group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
			        </div>
					<input type="hidden" id="before_date" value="<s:date name="scheduApplyBean.before_date" format="yyyy/MM/dd" nice="false"/>" name="scheduApplyBean.before_date"/><br/>
					
					<div class="input-group  date form_datetime"  data-date-format="yyyy/MM/dd" data-link-field="after_date">
			             <input class="form-control time-group" placeholder="调休后日期" type="text" value="<s:date name="scheduApplyBean.after_date" format="yyyy/MM/dd" nice="false"/>" disabled="disabled" >
						 <span class="input-group-addon group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
			        </div>
					<input type="hidden" id="after_date" value="<s:date name="scheduApplyBean.after_date" format="yyyy/MM/dd" nice="false"/>" name="scheduApplyBean.after_date"/><br/>
				
					<div class="textarea-box" >
						<textarea placeholder="原因" id="cause" name="scheduApplyBean.cause">${scheduApplyBean.cause}</textarea>
					</div>
				</div>
			</div>
			<button type="button" class="btn btn-primary detail-button" id="btnSubmit" style="margin-left: 20px;">提交</button>
			<button type="button" class="btn btn-primary message-button" onclick="onBack();">返回</button>
		</div>
	</form>
</body>
</html>