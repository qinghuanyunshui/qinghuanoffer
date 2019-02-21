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
 * ��ְ�ߵ�¼����ʵ��
 * 
 * @author QST����ʵѵ
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
		// �����������Ӧ����
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		// ��ȡ�������
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String rememberMe = request.getParameter("rememberMe");
		String requestPath = request.getParameter("requestPath");
		// ��¼��֤
		ApplicantDAO dao = new ApplicantDAO();
		int applicantID = dao.login(email, password);
		if (applicantID != 0) {
			// �û���¼�ɹ�,����ְ����Ϣ����session
			Applicant applicant = new Applicant(applicantID, email, password);
			request.getSession().setAttribute("SESSION_APPLICANT", applicant);
			// ͨ��Cookie��ס���������
			rememberMe(rememberMe, email, password, request, response);
			//�ж��Ƿ��Ѵ�������·��
			if(!"".equals(requestPath) && null!=requestPath){
				response.sendRedirect(requestPath);
			}else{
				// �ж��Ƿ�����д����
				int resumeID = dao.isExistResume(applicantID);
				if (resumeID != 0){
					request.getSession().setAttribute("SESSION_RESUMEID", resumeID);
					// �������Ѵ��ڣ�������ҳ
					response.sendRedirect("./index.jsp");
				}else
					// �����������ڣ�����������д��ҳ��
					response.sendRedirect("./resumeGuide.jsp");
			}
		} else {
			// �û���¼��Ϣ���󣬽��д�����ʾ
			out.print("<script type='text/javascript'>");
			out.print("alert('�û���������������������룡');");
			out.print("window.location='./login.jsp';");
			out.print("</script>");
		}
	}

	private void rememberMe(String rememberMe, String email, String password,
			HttpServletRequest request, HttpServletResponse response) {
		// �ж��Ƿ���Ҫͨ��Cookie��ס���������
		if ("true".equals(rememberMe)) {
			// ��ס���估����
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
			// �����估����Cookie���
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
