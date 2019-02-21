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
 * 职位信息处理Servlet
 *
 * @author QST青软实训
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
		// 获取操作类型
		String type = request.getParameter("type");
		if("select".equals(type)){
			// 获取职位编号
			String jobid = request.getParameter("jobid");
			// 根据职位编号查询职位详细信息
			JobDAO dao = new JobDAO();
			Job job = dao.getJobByID(jobid);
			// 将职位信息对象存入request对象
			request.setAttribute("job", job);
			// 将企业信息对象存入request对象
			request.setAttribute("company", job.getCompany());
			request.getRequestDispatcher("./job.jsp").forward(request, response);
		}
	}

}
