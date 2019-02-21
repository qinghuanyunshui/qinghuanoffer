<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="./error.jsp"%>
<%@ page
	import="com.qinghuan.dao.CompanyDAO,com.qinghuan.bean.Company,com.qinghuan.bean.Job"%>
<%@ page import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	// 获得请求的绝对地址
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>服务_云水官网-大学生求职,大学生就业,IT行业招聘，IT企业快速入职 - 云水网</title>
<%-- <base href="<%=basePath%>"> --%>
<meta name="renderer" content="ie-stand">
<link rel="shortcut icon" href="http://www.itoffer.cn/favicon.ico"
	type="image/x-icon" />
<meta
	content="大学生求职,大学生就业,大学生招聘,IT人才,IT人才招聘,大学生名企招聘,,大学生找工作,IT名企招聘，IT行业招聘，IT企业快速入职"
	name="keywords">
<meta
	content="云水专注于为企业提供高效的人力资源解决方案，同时面向IT类技术人才推出快速一站式免费就业服务。秉承QST青软实训人才服务理念，为数千家企业量身定做个性化、全程化的人才培养体系，同时帮助中高级人才铺设成功之路，为人才和企业架设起沟通之桥。"
	name="description">
<link href="../css/base.css" type="text/css" rel="stylesheet" />
<link href="../css/index.css" type="text/css" rel="stylesheet" />
</head>
<body class="tn-page-bg">
	<!-- 网站公共头文件 -->
	<jsp:include page="./top.jsp"></jsp:include>
	<!-- 企业列表展示 -->
	<jsp:include page='${"CompanyServlet"}'>
		<jsp:param value="pageList" name="type"/>
		<jsp:param value="${param.pageNo }" name="pageNo"/>
	</jsp:include>
	<!-- 网站公共尾部 -->
	<iframe src="./foot.html" width="100%" height="150" scrolling="no"
		frameborder="0"></iframe>
</body>
</html>