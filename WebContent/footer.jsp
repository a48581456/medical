<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script type="text/javascript">
	$(function () {
		if (window.innerWidth){
			winWidth = window.innerWidth;
		} else if ((document.body) && (document.body.clientWidth)){
			winWidth = document.body.clientWidth;
		}
		if (winWidth > 1200) {
			var width = 1200 + (winWidth-1200)/2;
			$("#pagetop").css("left", width+"px");
		}else {
			$("#pagetop").css("left", "1200px");
		}
	});
	
	window.onresize=function(){  
		if (window.innerWidth){
			winWidth = window.innerWidth;
		} else if ((document.body) && (document.body.clientWidth)){
			winWidth = document.body.clientWidth;
		}
		if (winWidth > 1200) {
			var width = 1200 + (winWidth-1200)/2;
			$("#pagetop").css("left", width+"px");
		}else {
			$("#pagetop").css("left", "1200px");
		}
	}
	
</script>
</head>
<form id="footerForm" action="" method="post" style="background: #fff;">
	<div class="containers" style="position: relative;">
		<div class="footer-title">
			<img src="<%=request.getContextPath()%>/picture/logo.jpg" style="float: left;"/>
			<img src="<%=request.getContextPath()%>/picture/pic-1-35.jpg" style="float: right;"/>
		</div>
		<div class="footer-middle">
			<div class="footer-menu">
				<div class="item"><a href="javascript:void(0);" onclick="menuHref('<%=request.getContextPath()%>/mainAction');">网站首页</a></div>
				<div class="item"><a href="javascript:void(0);" onclick="menuHref('<%=request.getContextPath()%>/remoteAction!expertList');">远程会诊</a></div>
				<div class="item"><a>学术交流</a></div>
				<div class="item"><a>科研创新</a></div>
				<div class="item"><a href="javascript:void(0);" onclick="menuHref('<%=request.getContextPath()%>/literatureAction!select');">医学著作</a></div>
				<div style="float: right: ;"><a>产品展示</a></div>
			</div>
			<div class="beian">版权所有：北京健康促进会医疗协作平台&nbsp;&nbsp; &nbsp;备案号：京ICP备11009309号&nbsp;&nbsp;&nbsp;E-mail:info@hoffen.com.cn</div>
			<div class="friend"><a href="http://www.chinahpa.org/">友情链接：北京健康促进会</a></div>
		</div>
		<div id="pagetop" class="select-pagetop"><a href="#top"><img src="<%=request.getContextPath()%>/image/pagetop.png"  alt=""></a></div>
	</div>
<%
	parameter.UserBean user  =  (parameter.UserBean)filter.TimeOutFilter.getSessionLoginFlag();
	if (user != null && !"3".equals(user.getPermiss()) && !"4".equals(user.getPermiss())) {
%>
	<div class="webExpertIm" onclick="webImExpert()">
		咨&nbsp;询&nbsp;问&nbsp;答
		<i id="newMess" style="display: none"></i>
	</div>
	<div class="modal fade" id="webImExpert" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document" style="margin: 100px auto;width: 1010px;">
	    <div class="modal-content" style="width: 100%;">
	      <div class="modal-header" style="padding: 10px;">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	      </div>
	      <div class="modal-body" style="height: 620px;padding: 0;">
	        <iframe class="iframe-body" src="" id="webImExpertInfo" name="webImExpertInfo"></iframe>
	      </div>
	    </div>
	  </div>
	</div>
	<input type="hidden" id="ImUserId" value="<%=((parameter.UserBean)filter.TimeOutFilter.getSessionLoginFlag()).getUser_id() %>">
	<input type="hidden" id="ImToken" value="<%=((parameter.UserBean)filter.TimeOutFilter.getSessionLoginFlag()).getToken() %>">
	<script src="<%=request.getContextPath()%>/nim_web/webdemo/3rd/NIM_Web_NIM_v4.8.0.js"></script>
	<script src="<%=request.getContextPath()%>/nim_web/webdemo/im/js/util.js"></script>
	<script src="<%=request.getContextPath()%>/nim_web/webdemo/im/js/link.js"></script>
	<script src="<%=request.getContextPath()%>/nim_web/webdemo/im/js/config.js"></script>
	<script type="text/javascript">
		
	var data = {};
	// 注意这里, 引入的 SDK 文件不一样的话, 你可能需要使用 SDK.NIM.getInstance 来调用接口
	var nim = NIM.getInstance({
	    // debug: true,
	    appKey: CONFIG.appkey,
	    account: $("#ImUserId").val(),
	    token: $("#ImToken").val(),
	    onconnect: onConnect,
	    onwillreconnect: onWillReconnect,
	    ondisconnect: onDisconnect,
	    onerror: onError,
	    onmsg: onMsg
	});
	function onConnect() {
	    console.log('连接成功');
	    delCookie('uid');
	    delCookie('sdktoken');
	    setCookie('uid',$("#ImUserId").val());
		setCookie('sdktoken',$("#ImToken").val());
		$("#webImExpertInfo").attr("src","<%=request.getContextPath()%>/nim_web/webdemo/im/main.jsp");
	}
	function onWillReconnect(obj) {
	    // 此时说明 SDK 已经断开连接, 请开发者在界面上提示用户连接已断开, 而且正在重新建立连接
	    console.log('即将重连');
	    console.log(obj.retryCount);
	    console.log(obj.duration);
	}
	function onDisconnect(error) {
	    // 此时说明 SDK 处于断开状态, 开发者此时应该根据错误码提示相应的错误信息, 并且跳转到登录页面
	    console.log('丢失连接');
	    console.log(error);
	    if (error) {
	        switch (error.code) {
	        // 账号或者密码错误, 请跳转到登录页面并提示错误
	        case 302:
	            break;
	        // 重复登录, 已经在其它端登录了, 请跳转到登录页面并提示错误
	        case 417:
	            break;
	        // 被踢, 请提示错误后跳转到登录页面
	        case 'kicked':
	            break;
	        default:
	            break;
	        }
	    }
	}
	function onError(error) {
	    console.log(error);
	}
	
	function onMsg(msg) {
	    $("#newMess").css("display","block");
	}
	
	function webImExpert() {
		$("#newMess").css("display","none");
		$('#webImExpert').modal({
			backdrop:'static'
		});
	}
		
	function webImUser(expert_id) {
		window.frames["webImExpertInfo"].webImUser(expert_id);
		$('#webImExpert').modal({
			backdrop:'static'
		});
	}
	
	</script>
<%
	}
%>
</form>
