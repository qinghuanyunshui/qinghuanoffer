package com.qinghuan.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qinghuan.bean.Applicant;
import com.qinghuan.bean.JobApply;
import com.qinghuan.dao.JobApplyDAO;

/**
 * 职位申请处理Servlet
 * 
 * @author QST青软实训
 *
 */
@WebServlet("/jsp/JobApplyServlet")
public class JobApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public JobApplyServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取操作类型
		String type = request.getParameter("type");

		if ("apply".equals(type)) {
			// 获取职位编号
			String jobid = request.getParameter("jobid");
			// 获取登录用户
			Applicant applicant = (Applicant) request.getSession()
					.getAttribute("SESSION_APPLICANT");
			// 添加此用户对此职位的申请
			JobApplyDAO dao = new JobApplyDAO();
			dao.save(jobid, applicant.getApplicantId());
			response.sendRedirect("JobApplyServlet?type=myapply");

		} else if ("myapply".equals(type)) {
			// 获取登录用户
			Applicant applicant = (Applicant) request.getSession()
					.getAttribute("SESSION_APPLICANT");
			// 根据用户标识查询此用户申请的所有职位
			JobApplyDAO dao = new JobApplyDAO();
			List<JobApply> jobList = dao.getJobApplyList(applicant
					.getApplicantId());
			request.setAttribute("jobList", jobList);
			request.getRequestDispatcher("./jobApply.jsp").forward(
					request, response);
		}
	}

}
