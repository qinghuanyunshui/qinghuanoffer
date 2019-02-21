package com.qinghuan.bean;

import java.util.ArrayList;
import java.util.List;

import com.qinghuan.bean.Company;
import com.qinghuan.dao.CompanyDAO;

public class ComanyPageBean {
	// ÿҳ��ʾ��¼��
	private int pageSize = 10;
	// ��ǰҳ��
	private int pageNo = 1;
	// ��ҳ��
	private int totalPages;
	// ÿҳ���ݼ�¼����
	private List<Company> pageData = new ArrayList<Company>();
	// �Ƿ�����һҳ
	private boolean hasNextPage;
	// �Ƿ�����һҳ
	private boolean hasPreviousPage;

	public ComanyPageBean() {
	}

	public ComanyPageBean(int pageSize, int pageNo) {
		this.pageSize = pageSize;
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalPages() {
		// ��ȡ�ܼ�¼��
		CompanyDAO dao = new CompanyDAO();
		int recordCount = dao.getRecordCount();
		// ��ȡ��������ҳ��
		return (recordCount + pageSize - 1) / pageSize;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<Company> getPageData() {
		// ��ѯ��ҳ��¼
		CompanyDAO dao = new CompanyDAO();
		List<Company> list = dao.getCompanyPageList(pageNo, pageSize);
		return list;
	}

	public void setPageData(List<Company> pageData) {
		this.pageData = pageData;
	}

	public boolean isHasNextPage() {
		return (this.getPageNo() < this.getTotalPages());
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public boolean isHasPreviousPage() {
		return (this.getPageNo() > 1);
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

}
