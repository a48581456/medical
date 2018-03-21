<%@page import="common.Constants"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<!-- Standard Meta -->
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<title>医疗专家</title>
<s:include value="/comm_inc.jsp" />
<script type="text/javascript">
	
	function doBack() {
		if ($("#mainBack").val() == 'main') {
			$("#expertForm").attr("action","mainAction");
			$("#expertForm").submit();
		} else {
			$("#expertForm").attr("action","expertAction!expertFrontList");
			$("#expertForm").submit();
		}
	}
	
	function docView(fileName) {
		$("#docViewInfo").attr("src","docViewAction!docView?fileName="+fileName);
		$('#docView').modal({
			backdrop:'static'
		});
	}
	function more(obj) {
		var commentDiv = $(".detail-content-div").children("div:first-child");
		var detailDivHeight = $(".detail-content-div").height() + 600;
		if (detailDivHeight < commentDiv.height()) {
			$(".detail-content-div").css("height", detailDivHeight);
		} else {
			$(".detail-content-div").css("height", commentDiv.height());
			$(obj).hide();
		}
	}
	
	$(function () {
		var commentDiv = $(".detail-content-div").children("div:first-child");
		var detailDivHeight = $(".detail-content-div").height() + 600;
		if (commentDiv.height() < 600) {
			$(".detail-content-div").css("height", commentDiv.height()+40);
		} else {
			$(".detail-content-div").css("height", "640px");
			$("#moreId").removeClass("hide");
		}
	});
	
	$(window).on('scroll',function(){
		var t = document.documentElement.scrollTop || document.body.scrollTop;  //离上方的距离
	    var h =window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight; 
	    var commentDiv = $(".detail-content-div").children("div:first-child");
	    if( t >= document.documentElement.scrollHeight -h && $(".detail-content-div").height()< commentDiv.height()) {
			 $(".detail-content-div").css("height", $(".detail-content-div").height()+600);
	    }
	});
</script>
</head>
<body>
	<s:include value="/header.jsp" />
	<form id="expertForm" action="" method="post">
		<input type="hidden" id="expert_id_search" name="seaExpert.expert_id" value="${seaExpert.expert_id}">
		<input type="hidden" id="expert_name_search" name="seaExpert.expert_name" value="${seaExpert.expert_name}">
		<input type="hidden" id="status_search" name="seaExpert.status" value="${seaExpert.status}">
		<input type="hidden" id="departmentid_search" name="seaExpert.departmentid" value="${seaExpert.departmentid}">
		<input type="hidden" id="page" name="pagination.page" value="${pagination.page}">
		<input type="hidden" id="mainBack" name="mainBack" value="${mainBack }">
		<input type="hidden" id="flag" name="seaExpert.flag" value="${seaExpert.flag}">
		<input type="hidden" id="title" name="seaExpert.title" value="${seaExpert.title}">
		
		<div class="select-top" >
		    <div class="containers">
				<ul class="select-nav" >
				    <li><a href="#"  onclick="menuHref('mainAction');">首页</a></li>
				    <li><a onclick="doBack();">专家咨询</a></li>
				    <li>${expertBean.expert_name}</li>
				</ul>
		    </div>
	    </div>
		
		<!-- 详情分类标题 -->
		<div class="detail-title">
			<div class="containers">
			<div class="detail-title-line">
			    <div class="detail-title-top">医疗专家</div>
			    <div class="detail-title-body">Expert</div>
<!-- 			    <div class="detail-title-foot">doctors</div> -->
			</div>
			<button type="button" class="btn-back" onclick="doBack();">返回</button> 
			</div>
		</div>
		
	
				<!-- 详情内容 -->
			<div class="body detail">
			    <div class="detail-content containers">
			        <div class="detail-content-title">${expertBean.expert_name}个人简介</div>
			      <div class="row">
			        <div class="col-md-5 col-xs-5" style="width: 295px;">
	                  <img id="img" class="img-col"  src="<%=request.getContextPath()%>/<%=common.Constants.UPLOAD_PATH%>/<%=common.Constants.USER%>/${expertBean.photoPath}"></img>
			          <div class="expert-detail-btn">
							<button type="button" style="background-color: #0078bb;" onclick="menuHref('<%=request.getContextPath()%>/remoteAction!add_remote?remoteBean.doc_name=${expertBean.expert_name }&remoteBean.expert_id=${expertBean.expert_id }');">申请远程会诊</button>
							<s:if test="#item.onloginStatus == 0">
<%
								parameter.UserBean user = (parameter.UserBean)filter.TimeOutFilter.getSessionLoginFlag();
								// 如果值班不能咨询
								if ((Constants.EXPERT_PERMISSIONS.equals(user.getPermiss()) && user.isScheduFlag() == true) 
										|| Constants.ADMIN.equals(user.getPermiss()) 
										|| Constants.DEPARTMENT_MANAGE.equals(user.getPermiss())) {
%>
								<button type="button" class="disabled" disabled="disabled">在线咨询</button>
<% 								} else { %>
									<s:if test="#item.expert_id == '<%=user.getUser_id() %>'">
										<button type="button" class="disabled" disabled="disabled">在线咨询</button>
									</s:if>
									<s:else>
										<button type="button" style="background-color: #8cc341;" onclick="webImUser('${expertBean.expert_id}')">在线咨询</button>
									</s:else>
<%								} %>
							</s:if>
							<s:else>
								<button type="button" class="disabled" disabled="disabled">在线咨询</button>
							</s:else>
						</div>
			        </div>
			        <div class="detail-content-div col-md-7 col-xs-7" style="position: relative;overflow: hidden;">
<%-- 			        <p>姓名：${expertBean.expert_name}</p> --%>
<%-- 			      	<p>科室：${expertBean.departmentName}</p> --%>
<%-- 		            <p>所属：${expertBean.hospital}</p> --%>
<!-- 		            <p class="detail-content-comment" style="height: 135px;"> -->
<%-- 		            	<s:if test="expertBean.coprofile.length() > 200"> --%>
<%-- 							简介：<s:property value="expertBean.coprofile.substring(0, 200)" />... --%>
<%-- 						</s:if> --%>
<%-- 						<s:else> --%>
<%-- 							简介：<s:property value="expertBean.coprofile" /> --%>
<%-- 						</s:else> --%>
<!-- 		            </p> -->
			        	<div>
			        		${expertBean.comment}
			        	</div>
<%-- 			        <button type="button" class="detail-button" onclick="docView('<%=common.Constants.EXPERT %>/${expertBean.comment}');">详细介绍</button> --%>
<!-- 			        <div id="moreId" onclick="more(this);" class="hide" style="text-align: center;cursor:pointer;font-size: 16px;color: #0099cc;height: 40px;position: absolute;bottom: 0;width: 100%;background-color: #fff;line-height: 40px;"> -->
<%-- 			        	<img src="<%=request.getContextPath()%>/image/arrowDown.png">&nbsp;&nbsp;查看更多 --%>
<!-- 			        </div> -->
			        </div>
			    </div>
			</div>
		</div>
		<s:include value="/footer.jsp" />
	</form>
</body>
</html>
<%!
private String htmlRepBr(String str) {
	str = str.replaceAll("\r", "<br>");
	str = str.replaceAll(" ", "&nbsp;&nbsp;&nbsp;&nbsp;");
	return str;
}
%>