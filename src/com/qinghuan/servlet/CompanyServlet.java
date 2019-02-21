package com.qinghuan.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qinghuan.bean.ComanyPageBean;
import com.qinghuan.bean.Company;
import com.qinghuan.bean.Job;
import com.qinghuan.dao.CompanyDAO;
import com.qinghuan.dao.JobDAO;

/**
 * ��ҵ��Ϣ����Servlet
 * 
 * @author QST����ʵѵ
 *
 */
@WebServlet("/jsp/CompanyServlet")
public class CompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CompanyServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// ��ȡ����ҵ��Ϣ�������������
		String type = request.getParameter("type");
		if ("select".equals(type)) {
			// ��ȡ�����ѯ����ҵ���
			String companyID = request.getParameter("id");
			// ������ҵ��Ų�ѯ��ҵ��ϸ��Ϣ
			CompanyDAO dao = new CompanyDAO();
			Company company = dao.getCompanyByID(companyID);
			// ����ѯ������ҵ��Ϣ����request������
			request.setAttribute("company", company);
			// ������ҵ��Ų�ѯ��ҵ��������Ƹְλ
			JobDAO jobdao = new JobDAO();
			List<Job> jobList = jobdao.getJobListByCompanyID(companyID);
			// ����ѯ����ְλ�б����request������
			request.setAttribute("joblist", jobList);
			// ����ת��
			request.getRequestDispatcher("./company.jsp").forward(
					request, response);
		} else if ("pageList".equals(type)) {
			// ��ҵ�б��ҳ��ʾ����
			String pageNo = request.getParameter("pageNo");
			if (pageNo == null || "".equals(pageNo))
				pageNo = "1";
			// ��ҵ��Ϣ��ҳչʾ����ʵ��JavaBean
			ComanyPageBean pagination = new ComanyPageBean(2,
					Integer.parseInt(pageNo));
			request.setAttribute("pagination", pagination);
			request.getRequestDispatcher("./include_companyList.jsp").include(
					request, response);
		}
	}

}
