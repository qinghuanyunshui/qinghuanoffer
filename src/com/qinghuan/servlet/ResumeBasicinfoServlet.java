package com.qinghuan.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qinghuan.bean.Applicant;
import com.qinghuan.bean.ResumeBasicinfo;
import com.qinghuan.dao.ResumeDAO;

/**
 * ����������Ϣ����Servlet
 * 
 * @author QST����ʵѵ
 *
 */
@WebServlet("/jsp/ResumeBasicinfoServlet")
public class ResumeBasicinfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ResumeBasicinfoServlet() {
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
		// ��ȡ�����������
		String type = request.getParameter("type");
		PrintWriter out = response.getWriter();
		// ������Ӳ���
		if ("add".equals(type)) {
			// ��װ��������
			ResumeBasicinfo basicinfo = this.requestDataObj(request);
			// �ӻỰ�����л�ȡ��ǰ��¼�û���ʶ
			Applicant applicant = (Applicant)request.getSession().getAttribute("SESSION_APPLICANT");
			// �����ݿ�����ӵ�ǰ�û��ļ���
			ResumeDAO dao = new ResumeDAO();
			int basicinfoID = dao.add(basicinfo, applicant.getApplicantId());
			request.getSession().setAttribute("SESSION_RESUMEID", basicinfoID);
			// �����ɹ���ת���ҵļ�����ҳ��
			response.sendRedirect("ResumeBasicinfoServlet?type=select");
		}else if("select".equals(type)){ // ������ѯ����
			// �ӻỰ�����л�ȡ��ǰ��¼�û���ʶ
			Applicant applicant = (Applicant)request.getSession().getAttribute("SESSION_APPLICANT");
			// ���ݼ�����ʶ��ѯ����������Ϣ
			ResumeDAO dao = new ResumeDAO();
			ResumeBasicinfo basicinfo = dao.selectBasicinfoByID(applicant.getApplicantId());
			// ������������Ϣ����request�����������ת��
			request.setAttribute("basicinfo", basicinfo);
			request.getRequestDispatcher("./resume.jsp").forward(request, response);
		}else if("updateSelect".equals(type)){ // ��������ǰ�Ĳ�ѯ
			// �ӻỰ�����л�ȡ��ǰ��¼�û���ʶ
			Applicant applicant = (Applicant)request.getSession().getAttribute("SESSION_APPLICANT");
			// ���ݼ�����ʶ��ѯ����������Ϣ
			ResumeDAO dao = new ResumeDAO();
			ResumeBasicinfo basicinfo = dao.selectBasicinfoByID(applicant.getApplicantId());
			// ������������Ϣ����request�����������ת��
			request.setAttribute("basicinfo", basicinfo);
			request.getRequestDispatcher("./resumeBasicinfoUpdate.jsp").forward(request, response);
		}else if("update".equals(type)){
			// ��װ��������
			ResumeBasicinfo basicinfo = this.requestDataObj(request);
			int basicinfoId = Integer.parseInt(request.getParameter("basicinfoId"));
			basicinfo.setBasicinfoId(basicinfoId);
			// ���¼�����Ϣ
			basicinfo.setResumeUpdate(basicinfo);
			request.setAttribute("basicinfo", basicinfo);
			request.getRequestDispatcher("./resumeBasicinfoUpdate.jsp").forward(request, response);
		}
	}

	/**
	 * ������ļ������ݷ�װ��һ������
	 * 
	 * @param request
	 * @return
	 * @throws ItOfferException
	 */
	private ResumeBasicinfo requestDataObj(HttpServletRequest request) {
		ResumeBasicinfo basicinfo = null;
		// �����������
		String realname = request.getParameter("realName");
		String gender = request.getParameter("gender");
		String birthday = request.getParameter("birthday");
		String currentLoca = request.getParameter("currentLoc");
		String residentLoca = request.getParameter("residentLoc");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		String jobIntension = request.getParameter("jobIntension");
		String jobExperience = request.getParameter("jobExperience");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date birthdayDate = null;
		try {
			birthdayDate = sdf.parse(birthday);
		} catch (ParseException e) {
			birthdayDate = null;
		}
		// ���������ݷ�װ��һ������������Ϣ����
		basicinfo = new ResumeBasicinfo(realname, gender, birthdayDate,
				currentLoca, residentLoca, telephone, email, jobIntension,
				jobExperience);
		return basicinfo;
	}
}
