package com.qinghuan.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qinghuan.bean.Applicant;
import com.qinghuan.bean.Company;
import com.qinghuan.bean.Job;
import com.qinghuan.bean.JobApply;
import com.qinghuan.dao.CompanyDAO;
import com.qinghuan.dao.JobApplyDAO;
import com.qinghuan.dao.JobDAO;
/**
 * ְλ��Ϣ����Servlet
 *
 * @author QST����ʵѵ
 *
 */
@WebServlet("/jsp/JobServlet")
public class JobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JobServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡ��������
		String type = request.getParameter("type");
		if("select".equals(type)){
			// ��ȡְλ���
			String jobid = request.getParameter("jobid");
			// ����ְλ��Ų�ѯְλ��ϸ��Ϣ
			JobDAO dao = new JobDAO();
			Job job = dao.getJobByID(jobid);
			// ��ְλ��Ϣ�������request����
			request.setAttribute("job", job);
			// ����ҵ��Ϣ�������request����
			request.setAttribute("company", job.getCompany());
			request.getRequestDispatcher("./job.jsp").forward(request, response);
		}
	}

}
