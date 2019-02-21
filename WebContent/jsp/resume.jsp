<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="./error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page
	import="com.qinghuan.dao.ResumeDAO,com.qinghuan.bean.ResumeBasicinfo,com.qinghuan.bean.Applicant"%>
<%-- <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>  --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的简历 - 云水网</title>
<%-- <base href="<%=basePath%>"> --%>
<link href="../css/base.css" type="text/css" rel="stylesheet" />
<link href="../css/resume.css" type="text/css" rel="stylesheet" />
<meta
	content="大学生求职,大学生就业,大学生招聘,IT人才,IT人才招聘,大学生名企招聘,,大学生找工作,IT名企招聘，IT行业招聘，IT企业快速入职"
	name="keywords">
<meta
	content="云水专注于为企业提供高效的人力资源解决方案，同时面向IT类技术人才推出快速一站式免费就业服务。秉承QST青软实训人才服务理念，为数千家企业量身定做个性化、全程化的人才培养体系，同时帮助中高级人才铺设成功之路，为人才和企业架设起沟通之桥。"
	name="description">
</head>
<body>
	<jsp:include page="./top.jsp"></jsp:include>
	
	<!-- 从request对象中获取一个JavaBean对象 -->
	<jsp:useBean id="basicinfo" class="com.qinghuan.bean.ResumeBasicinfo" scope="request"></jsp:useBean>
	
	<!-- 我的简历页面开始 -->
	<div class="resume_con">
		<!--tab设置-->
		<div class="user_operate">
			<ul style="float: left">
			  <li> <a href="ResumeBasicinfoServlet?type=select">我的简历</a></li>
              <li class="tn-tabs-selected"> <a href="JobApplyServlet?type=myapply">我的申请</a></li>
			</ul>
			<div class="clear"></div>
		</div>
		<!--主体部分-->
		<div class="resume_main">
			<!--左边-->
			<div class="resume_left">
				<div class="resume_title">
					<div style="float: left">基本信息</div>
					<c:if test="${empty sessionScope.SESSION_RESUMEID }"> 
					<div class="btn">
						<a href="./resumeBasicInfoAdd.jsp">添加</a>
					</div></c:if> 
					<c:if test="${not empty sessionScope.SESSION_RESUMEID }"> 
					<div class="btn">
						<a href="ResumeBasicinfoServlet?type=updateSelect">修改</a>
					</div></c:if> 
				</div>
				<div class="all_resume">
					<div class="table_style">
						<table width="300" border="0" cellpadding="3" cellspacing="1"
							bgcolor="#EEEEEE">
							<tr>
								<td width="110" align="right" bgcolor="#F8F8F8"
									style="color: gray;">姓名：</td>
								<td bgcolor="#F8F8F8">${basicinfo.realName }</td>
							</tr>
						</table>
						<div class="he"></div>
						<table width="300" border="0" cellpadding="3" cellspacing="1"
							bgcolor="#EEEEEE">
							<tr>
								<td width="110" align="right" bgcolor="#F8F8F8"
									style="color: gray;">性别：</td>
								<td bgcolor="#F8F8F8">${basicinfo.gender }</td>
							</tr>
						</table>
						<div class="he"></div>
						<table width="300" border="0" cellpadding="3" cellspacing="1"
							bgcolor="#EEEEEE">
							<tr>
								<td width="110" align="right" bgcolor="#F8F8F8"
									style="color: gray;">出生日期：</td>
								<td bgcolor="#F8F8F8">${basicinfo.birthday }</td>
							</tr>
						</table>
						<div class="he"></div>
						<table width="300" border="0" cellpadding="3" cellspacing="1"
							bgcolor="#EEEEEE">
							<tr>
								<td width="110" align="right" bgcolor="#F8F8F8"
									style="color: gray;">当前所在地：</td>
								<td bgcolor="#F8F8F8">${basicinfo.currentLoc }</td>
							</tr>
						</table>
						<div class="he"></div>
						<table width="300" border="0" cellpadding="3" cellspacing="1"
							bgcolor="#EEEEEE">
							<tr>
								<td width="110" align="right" bgcolor="#F8F8F8"
									style="color: gray;">户口所在地：</td>
								<td bgcolor="#F8F8F8">${basicinfo.residentLoc }</td>
							</tr>
						</table>
						<div class="he"></div>

						<table width="300" border="0" cellpadding="3" cellspacing="1"
							bgcolor="#EEEEEE">
							<tr>
								<td width="110" align="right" bgcolor="#F8F8F8"
									style="color: gray;">手机：</td>
								<td bgcolor="#F8F8F8">${basicinfo.telephone }</td>
							</tr>
						</table>
						<div class="he"></div>
						<table width="300" border="0" cellpadding="3" cellspacing="1"
							bgcolor="#EEEEEE">
							<tr>
								<td width="110" align="right" bgcolor="#F8F8F8"
									style="color: gray;">邮件：</td>
								<td bgcolor="#F8F8F8">${basicinfo.email }</td>
							</tr>
						</table>
						<div class="he"></div>
						<table width="300" border="0" cellpadding="3" cellspacing="1"
							bgcolor="#EEEEEE">
							<tr>
								<td width="110" align="right" bgcolor="#F8F8F8"
									style="color: gray;">求职意向：</td>
								<td bgcolor="#F8F8F8">${basicinfo.jobIntension }</td>
							</tr>
						</table>
						<div class="he"></div>
						<table width="300" border="0" cellpadding="3" cellspacing="1"
							bgcolor="#EEEEEE">
							<tr>
								<td width="110" align="right" bgcolor="#F8F8F8"
									style="color: gray;">工作经验：</td>
								<td bgcolor="#F8F8F8">${basicinfo.jobExperience }</td>
							</tr>
						</table>
						<div class="he"></div>
					</div>
					<div style="float: right" class="uploade">
						<% if("".equals(basicinfo.getHeadShot()) || null==basicinfo.getHeadShot()){ %>
						<img src="applicant/images/anonymous.png">
						<% }else{ %>
						<img src="applicant/images/<jsp:getProperty property="headShot" name="basicinfo"/>" width="140" height="140">
						<% } %>
						<p>&nbsp;</p>
						<div align="center">
							<a href="./resumeBasicInfoPicUpload.jsp" class="uploade_btn">更换照片</a>
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="resume_title">
					<div style="float: left">教育经历</div>
					<div class="btn">添加</div>
				</div>
				<div class="it-table-grid">
					<ul>
						<li class="tn-border-gray tn-border-bottom it-table-grid-header">
							<p class="tn-name">毕业院校</p>
							<p class="tn-date">就读时间</p>
							<p class="tn-degree">学历</p>
							<p class="tn-fieldofstudy">专业</p>
						</li>
					</ul>
				</div>
				<div class="resume_title">
					<div style="float: left">项目经验</div>
					<div class="btn">添加</div>
				</div>
				<div class="it-table-grid">
					<ul>
						<li class="tn-border-gray tn-border-bottom it-table-grid-header">
							<p class="tn-name">项目名称</p>
							<p class="tn-date">参与时间</p>
							<p class="tn-degree">担任职位</p>
						</li>
					</ul>
				</div>
				<div class="resume_title">
					<div style="float: left">简历附件</div>
					<div class="btn">添加</div>
				</div>
				<div class="it-table-grid">
					<div class="it-table-grid">暂无附件！</div>
				</div>
			</div>
			<!-- 右侧公共部分：简历完善度 -->
			<iframe src="./resume_right.jsp" width="297" height="440"
				scrolling="no" frameborder="0"></iframe>
			<div style="clear: both"></div>
		</div>
	</div>
	<!-- 我的简历页面结束 -->
	<!-- 网站公共尾部 -->
	<iframe src="./foot.html" width="100%" height="150" scrolling="no"
		frameborder="0"></iframe>
</body>
</html>