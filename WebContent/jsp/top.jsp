<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="./error.jsp"%>
<%@ page import="com.qinghuan.bean.Applicant"%>
<%-- <%
	// 获得请求的绝对地址
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>云水招聘网</title>
<!-- 设置网页的基链接地址 -->
<%-- <base href="<%=basePath%>"> --%>
<link href="../css/base.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="head">
		<div class="head_area">
			<div class="head_nav">
				<ul>
					<li><img src="../images/nav_inc1.png" /><a href="./index.jsp">首页</a></li>
					<li><img src="../images/nav_inc2.png" /><a href="#">成功案例</a></li>
					<li><img src="../images/nav_inc3.png" /><a href="#">关于云水</a></li>
				</ul>
			</div>
			<div class="head_logo">
				<!-- <img style="width:100px;height:30px" src="../images/云水logo.png" /> -->
			</div>
			<div class="head_user">
				<%
					if (session.getAttribute("SESSION_APPLICANT") == null) {
				%>
				<a href="./login.jsp" target="_parent"><span class="type1">登录</span></a><a
					href="./register.jsp" target="_parent"><span class="type2">注册</span></a>
				<%
					} else {
				%>
				<a href="ResumeBasicinfoServlet?type=select">${sessionScope.SESSION_APPLICANT.applicantEmail}<%//=sessionApplicant.getApplicantEmail()%></a>&nbsp;&nbsp;
				<a href="ApplicantLogoutServlet">退出</a>
				<%
					}
				%>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<div class="top_main">
		<div class="top_logo">
			<span style="color: #42ADBC;font: '微软雅黑';font-size: 20px;">云水招聘<span>
		</div>
		<div class="top_instr">提供岗前培训的IT职位</div>
		<!-- <div class="top_tel">
			<img src="../images/it-phone.png" />
		</div> -->
	</div>
	<div class="clear"></div>
</body>
</html>