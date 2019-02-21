package com.qinghuan.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qinghuan.bean.Applicant;
import com.qinghuan.dao.ApplicantDAO;
import com.qinghuan.util.CookieEncryptTool;

/**
 * 求职者登录功能实现
 * 
 * @author QST青软实训
 *
 */
@WebServlet("/jsp/ApplicantLoginServlet1")
public class ApplicantLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ApplicantLoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 设置请求和响应编码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		// 获取请求参数
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String rememberMe = request.getParameter("rememberMe");
		String requestPath = request.getParameter("requestPath");
		// 登录验证
		ApplicantDAO dao = new ApplicantDAO();
		int applicantID = dao.login(email, password);
		if (applicantID != 0) {
			// 用户登录成功,将求职者信息存入session
			Applicant applicant = new Applicant(applicantID, email, password);
			request.getSession().setAttribute("SESSION_APPLICANT", applicant);
			// 通过Cookie记住邮箱和密码
			rememberMe(rememberMe, email, password, request, response);
			//判断是否已存在请求路径
			if(!"".equals(requestPath) && null!=requestPath){
				response.sendRedirect(requestPath);
			}else{
				// 判断是否已填写简历
				int resumeID = dao.isExistResume(applicantID);
				if (resumeID != 0){
					request.getSession().setAttribute("SESSION_RESUMEID", resumeID);
					// 若简历已存在，跳到首页
					response.sendRedirect("./index.jsp");
				}else
					// 若简历不存在，跳到简历填写向导页面
					response.sendRedirect("./resumeGuide.jsp");
			}
		} else {
			// 用户登录信息错误，进行错误提示
			out.print("<script type='text/javascript'>");
			out.print("alert('用户名或密码错误，请重新输入！');");
			out.print("window.location='./login.jsp';");
			out.print("</script>");
		}
	}

	private void rememberMe(String rememberMe, String email, String password,
			HttpServletRequest request, HttpServletResponse response) {
		// 判断是否需要通过Cookie记住邮箱和密码
		if ("true".equals(rememberMe)) {
			// 记住邮箱及密码
			Cookie cookie = new Cookie("COOKIE_APPLICANTEMAIL",
					CookieEncryptTool.encodeBase64(email));
			cookie.setPath("/");
			cookie.setMaxAge(365 * 24 * 3600);
			response.addCookie(cookie);

			cookie = new Cookie("COOKIE_APPLICANTPWD",
					CookieEncryptTool.encodeBase64(password));
			cookie.setPath("/");
			cookie.setMaxAge(365 * 24 * 3600);
			response.addCookie(cookie);
		} else {
			// 将邮箱及密码Cookie清空
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if ("COOKIE_APPLICANTEMAIL".equals(cookie.getName())
							|| "COOKIE_APPLICANTPWD".equals(cookie.getName())) {
						cookie.setMaxAge(0);
						cookie.setPath("/");
						response.addCookie(cookie);
					}
				}
			}
		}
	}

}
