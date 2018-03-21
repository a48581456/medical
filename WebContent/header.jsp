<%@page import="util.Util"%>
<%@page import="common.Constants"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script type="text/javascript">
	function menuHref(action) {
		window.location.href = action;
	}
	$(function () {
		if ($("#advSuLeftSta").val() == '2') {
			$("#advLeft").show();
		}
		if ($("#advSuRightSta").val() == '2') {
			$("#advRight").show();
		}
	});
	
	function closeLeft(){
		$("#advLeft").addClass("hidden");
	}
	
	function closeRight(){
		$("#advRight").addClass("hidden");
	}
	
	function doAllSearch(){
		$("#allSearchText1").val(document.getElementById("allSearchText").value);
		$('#headerForm').attr("action","searchAction!select");
		$('#headerForm').submit();
	}
	
	function dropDownChange(id,name,idVal, nameVal) {
		$("#"+id).val(idVal);
		$("#"+name).val(nameVal);
	}
	
	function changeHospital(id,name,idVal, nameVal, actName) {
		
		$("#"+id).val(idVal);
		$("#"+name).val(nameVal);
		
		if (idVal == '0') {
			$("#hospitalName").removeClass("hide");
			$("#hospitalName").attr("name", actName);
			$("#hospitalHid").removeAttr("name");
		} else {
			$("#hospitalName").addClass("hide");
			$("#hospitalName").removeAttr("name");
			$("#hospitalHid").attr("name", actName);
			
			$("#hospitalHid").val(nameVal);
		}
	}
	
	
	$(function() {
	   
		 var num =0;
	    function goLeft() {
	    	 var Notwidth=$("#notice_width").width()+1000;
// 	    	 var clssWidth=$("#notice_width").width()+'px';
	    	
	    if(Notwidth/2>1000){
	    	var Notwidth=2000;
	    	 $('.winBox').css("width","1000px");
	    	 $('.scroll').css("margin-left","1000px");
	    	 $('.scroll').css("width","1000px");
	    }else{
	    	var Notwidth;
	    	 $('.winBox').css("width","1000px");
	    	 $('.scroll').css("margin-left","1000px");
	    }	
	   
	       
	        if (num == -Notwidth) {
	            num = 0;
	        }
	        num -= 1;
	        $(".scroll").css({
	            left: num
	        })
	    }

	    //设置滚动速度
	    var timer = setInterval(goLeft, 20);
	   
	    //设置鼠标经过时滚动停止
	    $(".box").hover(function() {
	        clearInterval(timer);
	    },
	    function() {
	        timer = setInterval(goLeft, 20);
	        
	    })
	})
	
	  function NoticeDetail(noticeId) {
    	$("#notice_id").val(noticeId);
        $("#headerForm").attr("action","advAction!notice_detail");
    	$("#headerForm").submit();	
    }
	
</script>
<form id="headerForm" action="" method="post">
    <input type="hidden" id="allSearchText1" name="searchBean.allSearchText" value="">
     <input type="hidden" id="notice_id" name="noticeBean.notice_id" value="${noticeBean.notice_id}">
	<header class="containers-all" style="background: #fff;">
	 <div class="header-title">
	 	<div class="containers">
	 		<div style="float: left;">
				<font>
					您好，欢迎访问北京健康促进会医疗协作平台
				</font>
			</div>
			<div style="float: right">
				<div class="searchAll-header">
			      <input type="text" class="form-control" style="height: 30px;" placeholder="请输入要搜索的内容" id="allSearchText" name="allSearchText">
			      <span>
			      	<img src="<%=request.getContextPath()%>/image/search_glass.png" class="img-col" onclick="doAllSearch();">
			      </span>
			    </div>
<%
						parameter.UserBean user = (parameter.UserBean)filter.TimeOutFilter.getSessionLoginFlag();
						if (user != null) {
						
						if (!Constants.DEPARTMENT_MANAGE.equals(user.getPermiss()) && !Constants.ADMIN.equals(user.getPermiss())) {
%>
					<div style="float: left;"><img style="margin-right: 10px;" src="<%=request.getContextPath()%>/picture/log-1-1.jpg"/><a href="<%=request.getContextPath()%>/personCenter/index.jsp"><%=((parameter.UserBean)filter.TimeOutFilter.getSessionLoginFlag()).getName() %></a></div>
					<div style="float: left;border: 0.5px solid;margin: 10px;height: 20px;"></div>
<%
	}
%>
				<div style="float: left;"><a href="<%=request.getContextPath()%>/loginAction!logout">退出</a></div>
<%
						if (Constants.DEPARTMENT_MANAGE.equals(user.getPermiss()) || Constants.ADMIN.equals(user.getPermiss())) {
%>
					<div style="float: left;border: 0.5px solid;margin: 10px;height: 20px;"></div>
					<div style="float: left;"><a href="<%=request.getContextPath()%>/mainAction!systemMain">后台入口</a></div>
<%
	}
		} else {
%>
				<div style="float: left;"><img style="margin-right: 10px;" src="picture/log-1-1.jpg"/><a href="<%=request.getContextPath()%>/register.jsp">免费注册</a></div>
				<div style="float: left;border: 0.5px solid;margin: 10px;height: 20px;"></div>
				<div style="float: left;"><a href="<%=request.getContextPath()%>/login.jsp">登录</a></div>
<%
		}
%>
				
			</div>
		</div>
	 </div>
<% 
    if(Util.getNoticeType()==2){ 
 %>  
<div class="box" style="margin-left: 300px;">
  <img alt="" src="<%=request.getContextPath()%>/image/notice.png" style="float: left; margin-top: 5px; margin-right: 5px;">

  <div class="winBox" >
    <div class="scroll"> 
   <a id="notice_width" style="text-decoration: none; color: #000;" onclick="NoticeDetail('<%=Util.getNoticeId()%>')"><%=Util.getSimpleText(Util.getNoticeContent())%></a>    
    </div>
   
  </div>
</div>
 <%
 	} 
 %> 

	 
	 <div class="containers header-logo">
	 	<div class="menu-div" style="width: 100%;margin: 0;">
	 		<img style="width: 570px;" src="<%=request.getContextPath()%>/picture/logo.jpg" />
		  	<ul class="row menu-ul" style="float: right;margin-top: 30px;">
   	    		<li onclick="menuHref('<%=request.getContextPath()%>/mainAction');">网站首页</li>
<%--    	    		<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/remoteAction!expertList');"> --%>
   	    		<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/gridAction!expert?funName=remoteAction!expertList');">
<!--    	    		<li class="text-center"> -->
   	    			远程会诊
<!--    	    			<ul> -->
<%--    	    				<li onclick="menuHref('<%=request.getContextPath()%>/remoteAction!expertList');">医生浏览</li> --%>
<%--    	    				<li onclick="menuHref('<%=request.getContextPath()%>/remoteAction!add_remote');">提报申请</li> --%>
<%--    	    				<li onclick="menuHref('<%=request.getContextPath()%>/remoteAction!remoteList');">查询结果</li> --%>
<%--    	    				<li onclick="menuHref('<%=request.getContextPath()%>/policyAction!search');">政策法规</li> --%>
<!--    	    			</ul> -->
   	    		</li>
   	    		<li class="text-center">
   	    			新闻公告
   	    			<ul>
   	    				<li onclick="menuHref('<%=request.getContextPath()%>/newsAction!select');">热门新闻</li>
   	    				<li onclick="menuHref('<%=request.getContextPath()%>/policyAction!search');">政策法规</li>
   	    			</ul>
   	    		</li>
   	    		<li class="text-center">
   	    			学术交流
   	    			<ul>
   	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/caseAction!select');">
   	    					经典病例
<!--    	    					<ul> -->
<%-- 		   	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/gridAction?funName=caseAction!select');">病例浏览</li> --%>
<%-- 		   	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/gridAction?funName=caseAction!solicitation_select&funType=solicitation');">病例上传</li> --%>
<%-- 		   	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/caseAction!select');">病例浏览</li> --%>
<%-- 		   	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/caseAction!solicitation_select');">病例上传</li> --%>
<%-- 		   	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/caseAction!activity_select');">活动浏览</li> --%>
<!-- 		   	    			</ul> -->
   	    				</li>
   	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/videoAction!select');">
   	    					教学资源
<!--    	    					<ul> -->
<%-- 		   	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/gridAction?funName=videoAction!select');">视频浏览</li> --%>
<%-- 		   	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/gridAction?funName=videoAction!solicitation_select&funType=solicitation');">视频上传</li> --%>
<%-- 		   	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/videoAction!select');">视频浏览</li> --%>
<%-- 		   	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/videoAction!solicitation_select');">视频上传</li> --%>
<!-- 		   	    			</ul> -->
   	    				</li>
<%--    	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/expertAction!expertFrontList');">医疗专家</li> --%>
   	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/gridAction!expert?funName=expertAction!expertFrontList');">医疗专家</li>
   	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/storyAction!select');">医患故事</li>
<!--    	    				<li class="text-center" > -->
<!--    	    					话题查询 -->
<!-- 	   	    					<ul> -->
<%-- 		   	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/topicAction!select');">话题浏览</li> --%>
<%-- 		   	    				<li class="text-center"  onclick="menuHref('<%=request.getContextPath()%>/topicAction!manage_soAdd');">话题发布</li> --%>
<!-- 		   	    			</ul> -->
<!--    	    				</li> -->
   	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/curriculumAction!select');">
   	    					微课堂
<!--    	    					<ul> -->
<%-- 		   	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/gridAction?funName=curriculumAction!select');">课程查询</li> --%>
<%-- 		   	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/curriculumAction!select');">课程查询</li> --%>
<%-- 								<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/curriculumAction!curriculum_add');">开启课程</li> --%>
		   	    				
<%-- 		   	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/gridAction?funName=videoAction!select');">教学视频查询</li> --%>
<%-- 		   	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/videoAction!select');">教学视频查询</li> --%>
<%-- 								<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/curriculumAction!go_video_add');">上传教学视频</li> --%>
<!-- 		   	    			</ul> -->
   	    				</li>
<%--    	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/gridAction?funName=educationAction!select');">进修报名</li> --%>
   	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/educationAction!select');">进修报名</li>
   	    			</ul>
   	    		</li>
<!--    	    		<li class="text-center"> -->
<!--    	    			医患故事 -->
<!--    	    			<ul> -->
<%--    	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/storyAction!select');">医患故事浏览</li> --%>
<%--    	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/storyAction!solicitation_select');">医患故事上传</li> --%>
<!--    	    			</ul> -->
<!--    	    		</li> -->
   	    		<li class="text-center">
   	    			创新科研
   	    			<ul>
<!--    	    				<li class="text-center"> -->
<!--    	    					前沿技术 -->
<!--    	    					<ul> -->
<%-- 		   	    				<li class="text-center"  onclick="menuHref('<%=request.getContextPath()%>/technologyAction!select');">前沿技术浏览</li> --%>
<%-- 		   	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/technologyAction!sol_select');">前沿技术上传</li> --%>
<!-- 		   	    			</ul> -->
<!--    	    				</li> -->
						<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/achievementsAction!select');">
<!--    	    				<li class="text-center"> -->
   	    					科研转化
<!--    	    					<ul> -->
<%-- 		   	    				<li class="text-center"  onclick="menuHref('<%=request.getContextPath()%>/gridAction?funName=achievementsAction!select');">成果浏览</li> --%>
<%-- 		   	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/gridAction?funName=achievementsAction!sol_select&funType=solicitation');">成果上传</li> --%>
<%-- 		   	    				<li class="text-center"  onclick="menuHref('<%=request.getContextPath()%>/achievementsAction!select');">成果浏览</li> --%>
<%-- 		   	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/achievementsAction!sol_select');">成果上传</li> --%>
<!-- 		   	    			</ul> -->
   	    				</li>
   	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/drugsAction!select');">
<!--    	    				<li class="text-center"> -->
   	    					医械创新
   	    				</li>
   	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/patentAction!add_patent');">
<!--    	    				<li class="text-center"> -->
   	    					专利申报
	   	    					<!-- <ul>
		   	    				<li class="text-center" >专利申报</li>
			   	    				<li class="text-center">专利转化</li>
		   	    			</ul> -->
   	    				</li>
   	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/multicenterAction!select');">
<!--    	    				<li class="text-center"> -->
   	    					多中心合作
<!--    	    					<ul> -->
<%-- 		   	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/multicenterAction!select');">课题查询</li> --%>
<%-- 		   	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/multicenterAction!add');">课题发布</li> --%>
<!-- 		   	    			</ul> -->
   	    				</li>
   	    			</ul>
   	    		</li>
   	    		<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/literatureAction!select');">
   	    			医学著作
<!--    	    			<ul> -->
<%--    	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/gridAction?funName=literatureAction!select&funType=solicitation');">医学著作</li> --%>
<%--    	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/gridAction?funName=drugsAction!select&funType=solicitation');">新特药品</li> --%>
<%--    	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/gridAction?funName=apparatusAction!select&funType=solicitation');">先进器械</li> --%>
<%--    	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/literatureAction!select');">医学著作</li> --%>
<%--    	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/drugsAction!select');">新特药品</li> --%>
<%--    	    				<li class="text-center" onclick="menuHref('<%=request.getContextPath()%>/apparatusAction!select');">先进器械</li> --%>
<!--    	    			</ul> -->
   	    		</li>
   	    	</ul>
	  	</div>
	 </div>
	</header>
	<!-- 悬浮广告 -->
	<div class="advSuspen advLeft" id="advLeft">
		<img class="img-col" alt="" style="display: inherit;" src="<%=request.getContextPath()%>/uploadFile/<%=common.Constants.ADVERTISING%>/<%=util.Util.getAdvSuspenInfo().get(0).getPic_path()%>">
		<a href="javascript:void(0)" onclick="closeLeft();">关闭</a>
	</div>
	<div class="advSuspen advRight" id="advRight">
		<img class="img-col" alt="" style="display: inherit;" src="<%=request.getContextPath()%>/uploadFile/<%=common.Constants.ADVERTISING%>/<%=util.Util.getAdvSuspenInfo().get(1).getPic_path()%>">
		<a href="javascript:void(0)" onclick="closeRight();">关闭</a>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="docView" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document" style="margin: 30px auto;width: 70%;">
	    <div class="modal-content" style="width: 100%;">
	      <div class="modal-header" style="padding: 10px;">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	      </div>
	      <div class="modal-body" style="height: 700px;padding: 0;">
	        <iframe class="iframe-body" src="" id="docViewInfo"></iframe>
	      </div>
	    </div>
	  </div>
	</div>
</form>
