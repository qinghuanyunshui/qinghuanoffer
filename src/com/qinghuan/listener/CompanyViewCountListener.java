package com.qinghuan.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

import com.qinghuan.dao.CompanyDAO;
/**
 * ��ҵ��Ϣ�������ͳ�Ƽ�����
 * @author QST����ʵѵ
 *
 */
@WebListener
public class CompanyViewCountListener implements ServletRequestListener {

	public CompanyViewCountListener() {

	}

	public void requestDestroyed(ServletRequestEvent sre) {

	}

	public void requestInitialized(ServletRequestEvent sre) {
		HttpServletRequest request = (HttpServletRequest) sre
				.getServletRequest();
		String requestURI = request.getRequestURI();
		String queryString = request.getQueryString() == null ? "" : request
				.getQueryString();
		// �ж��Ƿ�������ҵ����Servlet���������󣬲��Һ��б�ʾ��ҵ��Ϣ�鿴���������
		if (requestURI.indexOf("CompanyServlet") >= 0
				&& (queryString.indexOf("select") >= 0)) {
			// �������ַ��������л�ȡ��ҵ���
			int id = Integer.parseInt(queryString.substring(queryString
					.lastIndexOf('=') + 1));
			// ���´���ҵ��Ϣ���������
			CompanyDAO dao = new CompanyDAO();
			dao.updateCompanyViewCount(id);
		}
	}

}
