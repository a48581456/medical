<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="common.Constants"%>
<!DOCTYPE html>
<html>
<head>
<!-- Standard Meta -->
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<title>医疗专家</title>
<s:include value="/comm_inc.jsp" />
<script type="text/javascript">

	function searchOver(){
		$("#searchImg").attr("src","image/search-white.png");
	}
	
	function searchOut(){
		$("#searchImg").attr("src","image/search-grey.png");
	}
	
	function closeOver(){
		$("#closeImg").attr("src","image/close-white.png");
	}
	
	function closeOut(){
		$("#closeImg").attr("src","image/close-grey.png");
	}
	
	$(function () {
		
		$('#btnClear').on('click', function (e) {
			$("#expertId").val('');
			$("#expert_name").val('');
			// 管理员才可选部门
			if ($("#permiss").val() == '4') {
				$("#departmentid").val('');
				$("#departmentName").val('');
			}
			$("#status").val('');
			$("#statusName").val('');
			$("#title").val('');
			$("#titleName").val('');
		 });
		
		$('#btnSearch').on('click', function (e) {
			$("#expertForm").attr("action","expertAction!expertSearchFrontList");
			$("#expertForm").submit();
		 });
		
	});
	function searchHid() {
		
		if ($('#search-body').is(":hidden")) {
			$('#search-body').removeClass('hidden');
			$('#search-body').addClass('show');
		} else {
			$('#search-body').removeClass('show');
			$('#search-body').addClass('hidden');
		}
	}
	
	function onDetailInfo(expertId) {
		$("#expert_id").val(expertId);
		
		$("#expertForm").attr("action","expertAction!detailFrontInfo");
		$("#expertForm").submit();
	}
	
</script>
</head>
<body>
<div id="wrapper">
	<s:include value="/header.jsp" />
	<form id="expertForm" action="" method="post">
		<input type="hidden" id="okmessage" name="okmessage" value="${okmessage}">
		<input type="hidden" id="expert_id" name="expertBean.expert_id">
		<input type="hidden" id="permiss" value="<%=((parameter.UserBean)filter.TimeOutFilter.getSessionLoginFlag()).getPermiss() %>">
		<input type="hidden" id="flag" name="seaExpert.flag" value="${seaExpert.flag}">
		
		<div class="select-top" >
	    <div class="containers">
			<ul class="select-nav" >
			    <li><a href="#"  onclick="menuHref('mainAction');">首页</a></li>
			    <li>学术交流</li>
			    <li>专家展示</li>
			</ul>
	    </div>
    </div>
	   
	<div class="select-content">
		<div class="select-search">
			<div class="containers">
				<div class="select-title">
				    <div class="detail-title-top">医疗专家</div>
				    <div class="detail-title-body">Expert</div>
<!-- 				    <div class="detail-title-foot">doctors</div> -->
				</div>
				<div class="select-condition">
					<div class="select-text">
			    		<input type="text" class="form-control" placeholder="关键字" id="expert_name" name="seaExpert.expert_name" value="${seaExpert.expert_name}">
					</div>
					
<%
						parameter.UserBean user = (parameter.UserBean)filter.TimeOutFilter.getSessionLoginFlag();
						if (Constants.ADMIN.equals(user.getPermiss())) {
%>
					<div class="select-text">
						<div class="dropdown" style="width: 279px;">
							 <input type="hidden" id="departmentid" name="seaExpert.departmentid" value="${seaExpert.departmentid}">
							 <s:set name="departmentFlag" value=""></s:set>
							 <s:iterator var="item" value="@util.Util@getDepartmentList()">
							 	<s:if test="#item.id == seaExpert.departmentid">
							 		<s:set name="departmentFlag" value="#item.name"></s:set>
							 	</s:if>
							 </s:iterator>
							 <s:if test="#departmentFlag == ''">
						 		<input class="form-control dropdown-toggle dropDown-input" readonly="readonly" placeholder="科室" type="text" id="departmentName" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
							 </s:if>
							 <s:else>
							 	<input class="form-control dropdown-toggle dropDown-input" readonly="readonly" value="${departmentFlag}" placeholder="科室" type="text" id="departmentName" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
							 </s:else>
							 <span class="caret dropDown-span" style="position: absolute;right: 10px;top: 40%;"></span>
							  <ul class="dropdown-menu dropDown-ul" aria-labelledby="departmentName">
							  	<li><a href="javascript:void(0);" onclick="dropDownChange('departmentid','departmentName','', '')"></a></li>
							  	<s:iterator var="item" value="@util.Util@getDepartmentList()">
							  		<li><a href="javascript:void(0);" onclick="dropDownChange('departmentid','departmentName','${item.id}', '${item.name }')">${item.name }</a></li>
							  	</s:iterator>
							  </ul>
						</div>
					</div>
<%
	}
%>
					
					<div class="select-text">
						<!-- 下拉框 -->
						<div class="dropdown" style="width: 279px;">
							 <input type="hidden" id="status" name="seaExpert.status" value="${seaExpert.status}">
							 <s:if test="seaExpert.status == '' || seaExpert.status == null">
						 		<input class="form-control dropdown-toggle dropDown-input" readonly="readonly" placeholder="状态" type="text" id="statusName" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
							 </s:if>
							 <s:elseif test="seaExpert.status == 0">
							 	<input class="form-control dropdown-toggle dropDown-input" readonly="readonly" value="在线" placeholder="状态" type="text" id="statusName" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
							 </s:elseif>
							 <s:else>
							 	<input class="form-control dropdown-toggle dropDown-input" readonly="readonly" value="离线" placeholder="状态" type="text" id="statusName" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
							 </s:else>
							 <span class="caret dropDown-span" style="position: absolute;right: 10px;top: 40%;"></span>
							  <ul class="dropdown-menu dropDown-ul" aria-labelledby="statusName">
							  	<li><a href="javascript:void(0);" onclick="dropDownChange('status','statusName','', '')"></a></li>
						  		<li><a href="javascript:void(0);" onclick="dropDownChange('status','statusName','0', '在线')">在线</a></li>
						  		<li><a href="javascript:void(0);" onclick="dropDownChange('status','statusName','1', '离线')">离线</a></li>
							  </ul>
						</div>
					</div>
					
					<div class="select-text">
						<!-- 下拉框 -->
						<div class="dropdown" style="width: 279px;">
							 <input type="hidden" id="title" name="seaExpert.title" value="${seaExpert.title}">
							 <s:set name="titleFlag" value=""></s:set>
							 <s:iterator var="item" value="@util.Util@getTitleList()">
							 	<s:if test="#item.id == seaExpert.title">
							 		<s:set name="titleFlag" value="#item.name"></s:set>
							 	</s:if>
							 </s:iterator>
							 <s:if test="#titleFlag == ''">
						 		<input class="form-control dropdown-toggle dropDown-input" readonly="readonly" placeholder="职称" type="text" id="titleName" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
							 </s:if>
							 <s:else>
							 	<input class="form-control dropdown-toggle dropDown-input" readonly="readonly" value="${titleFlag}" placeholder="职称" type="text" id="titleName" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
							 </s:else>
							 <span class="caret dropDown-span" style="position: absolute;right: 10px;top: 40%;"></span>
							  <ul class="dropdown-menu dropDown-ul" aria-labelledby="titleName">
							  	<li><a href="javascript:void(0);" onclick="dropDownChange('title','titleName','', '')"></a></li>
							  	<s:iterator var="item" value="@util.Util@getTitleList()">
							  		<li><a href="javascript:void(0);" onclick="dropDownChange('title','titleName','${item.id}', '${item.name }')">${item.name }</a></li>
							  	</s:iterator>
							  </ul>
						</div>
					</div>
					</div>
					<div  class="select-btn">
						<button type="button" class="btn-white" id="btnSearch"  onmouseover="searchOver()" onmouseout="searchOut()"><img id="searchImg" src="image/search-grey.png" >检索</button>
						<button type="button" class="btn-white" id="btnClear" onmouseover="closeOver()" onmouseout="closeOut()"><img id="closeImg" src="image/close-grey.png">清除</button>
					</div>
				</div>
			</div>
		</div>
	    <div style="clear:both"></div> 
		<div class="select-show">
		    <div class="containers data-list" style="padding: 30px 0;height: auto;">
		        <div class="select-data">
		        	<s:iterator var="item" value="expertBeanList" status="index">
		        	<s:if test="(#index.index + 1)%4 == 0">
						<div class="expert-body" style="margin-bottom: 20px;margin-right: 0">
		        	</s:if>
		        	<s:else>
		        		<div class="expert-body" style="margin-bottom: 20px;">
		        	</s:else>
						<div class="expert-img-body">
							<img class="img-col" onclick="onDetailInfo('${item.expert_id }');" src="<%=request.getContextPath()%>/uploadFile/<%=Constants.USER%>/${item.photoPath}" />
							<div class="divBtn">
								<button type="button" style="background-color: #0078bb;" onclick="menuHref('<%=request.getContextPath()%>/remoteAction!add_remote?remoteBean.doc_name=${item.expert_name }&remoteBean.expert_id=${item.expert_id }');">申请远程会诊</button>
								<s:if test="#item.onloginStatus == 0">
<%
												// 如果值班不能咨询
												if ((Constants.EXPERT_PERMISSIONS.equals(user.getPermiss()) && user.isScheduFlag() == true) 
														|| Constants.ADMIN.equals(user.getPermiss()) 
														|| Constants.DEPARTMENT_MANAGE.equals(user.getPermiss())) {
%>
												<button type="button" class="disabled" disabled="disabled">在线咨询</button>
<% 												} else { %>
													<s:if test="#item.expert_id == '<%=user.getUser_id() %>'">
														<button type="button" class="disabled" disabled="disabled">在线咨询</button>
													</s:if>
													<s:else>
														<button type="button" style="background-color: #8cc341;" onclick="webImUser('${item.expert_id}')">在线咨询</button>
													</s:else>
<%												} %>
								</s:if>
								<s:else>
									<button type="button" class="disabled" disabled="disabled">在线咨询</button>
								</s:else>
							</div>
						</div>
						<div class="expert-content-body" onclick="onDetailInfo('${item.expert_id }');" >
							<font class="expert-name">
								${item.expert_name }
							</font>
							<font class="item">
								医院：${item.hospital }
							</font>
							<font class="item">
								职称：${item.title }
							</font>
<!-- 							<font class="item"> -->
<%-- 								科室：${item.departmentName } --%>
<!-- 							</font> -->
							<font style="color:#999999;font-size:14px;">
								状态：
							</font>
							<s:if test="#item.onloginStatus == 0">
								<font style="color:#009966;font-size:14px;">在线</font>
							</s:if>
							<s:else>
								<font style="color:red;font-size:14px;">离线</font>
							</s:else>
						</div>
					</div>
					</s:iterator>
		        </div>
		        <div style="clear:both"></div> 
		        <div class="pageDiv-content" style="padding-left: 0">
					<s:if test="pagination.pageCount != 0">
						<s:if test="pagination.page > pagination.pageCount">
							<input type="hidden" id="page" name="pagination.page" value="${pagination.page - 1}">
						</s:if>
						<s:else>
							<input type="hidden" id="page" name="pagination.page" value="${pagination.page}">
						</s:else>
						<ul class="pagination di_left" style="margin-right: 20px;margin-bottom: 10px;">
							<s:if test="pagination.page == 1 || (pagination.page > pagination.pageCount && pagination.page == 2) ">
								<li class="disabled"><a style="background-color:#eee" href="javascript:void(0);">&laquo;&nbsp;上一页</a></li>
							</s:if>
							<s:else>
								<li><a style="background-color:#eee" href="javascript:void(0);" onclick="prePage();">&laquo;&nbsp;上一页</a></li>
							</s:else>
							<s:iterator begin="1" end="pagination.pageCount" status="status">
								<s:if test="pagination.page > pagination.pageCount">
									<s:if test="pagination.page - 1 == #status.index + 1">
										<li class="active"><a style="background-color:#eee" href="javascript:void(0);">${status.index+ 1 }</a></li>
									</s:if>
									<s:else>
										<li><a style="background-color:#eee" href="javascript:void(0);" onclick="curPage('${status.index+ 1 }');">${status.index+ 1 }</a></li>
									</s:else>
								</s:if>
								<s:else>
									<s:if test="pagination.page == #status.index + 1">
										<li class="active"><a style="background-color:#eee" href="javascript:void(0);">${status.index+ 1 }</a></li>
									</s:if>
									<s:else>
										<li><a style="background-color:#eee" href="javascript:void(0);" onclick="curPage('${status.index+ 1 }');">${status.index+ 1 }</a></li>
									</s:else>
								</s:else>
							</s:iterator>
							<s:if test="pagination.page >= pagination.pageCount">
								<li class="disabled"><a style="background-color:#eee" href="javascript:void(0);">下一页&nbsp;&raquo;</a></li>
							</s:if>
							<s:else>
								<li><a style="background-color:#eee" href="javascript:void(0);" onclick="nextPage();">下一页&nbsp;&raquo;</a></li>
							</s:else>
						</ul>
					</s:if>
					<s:else>
						<ul class="pagination di_left" style="margin-right: 20px;">
							<li class="disabled"><a style="background-color:#eee" href="javascript:void(0);">&laquo;</a></li>
							<li class="active"><a style="background-color:#eee" href="javascript:void(0);">1</a></li>
							<li class="disabled"><a style="background-color:#eee" href="javascript:void(0);">&raquo;</a></li>
						</ul>
					</s:else>
				</div>
				<script type="text/javascript">
					function prePage() {
						$("#page").val(parseInt($("#page").val()) - 1);
						
						$("#expertForm").attr("action","expertAction!expertFrontList");
						$("#expertForm").submit();
					}
					
					function nextPage() {
						$("#page").val(parseInt($("#page").val()) + 1);
						
						$("#expertForm").attr("action","expertAction!expertFrontList");
						$("#expertForm").submit();
					}
					
					function curPage(page) {
						$("#page").val(page);
						
						$("#expertForm").attr("action","expertAction!expertFrontList");
						$("#expertForm").submit();
					}
				</script>
		    </div>
		</div>
	</div>
		<s:include value="/footer.jsp" />
	</form>
</div>
</body>
</html>