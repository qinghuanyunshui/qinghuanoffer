package com.qinghuan.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.qinghuan.bean.Applicant;
import com.qinghuan.dao.ResumeDAO;

/**
 * 简历头像图片上传
 * 
 * @author QST青软实训
 *
 */
@WebServlet("/jsp/ResumePicUploadServlet")
@MultipartConfig
public class ResumePicUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ResumePicUploadServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取上传文件域
		Part part = request.getPart("headShot");
		// 获取上传文件名称
		String fileName = part.getSubmittedFileName();
		// 为防止上传文件重名，对文件进行重命名
		String newFileName = System.currentTimeMillis()
				+ fileName.substring(fileName.lastIndexOf("."));
		// 将上传的文件保存在服务器项目发布路径的applicant/images目录下
		String filepath = getServletContext().getRealPath("/applicant/images");
		System.out.println("头像保存路径为：" + filepath);
		File f = new File(filepath);
		if (!f.exists())
			f.mkdirs();
		part.write(filepath + "/" + newFileName);
		// 从会话对象中获取当前用户简历标识
		int resumeID = (Integer)request.getSession().getAttribute("SESSION_RESUMEID");
		// 更新简历照片
		ResumeDAO dao = new ResumeDAO();
		dao.updateHeadShot(resumeID, newFileName);
		// 照片更新成功，回到“我的简历”页面
		response.sendRedirect("ResumeBasicinfoServlet?type=select");
	}

}
