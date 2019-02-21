package com.qinghuan.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * ��ҵ��Ϣʵ����
 * 
 * @author QST����ʵѵ
 *
 */
public class Company {
	// ��ҵ��ʶ
	private int companyId;
	// ��ҵ����
	private String compayName;
	// ��ҵ���ڵ���
	private String companyArea;
	// ��ҵ��ģ
	private String companySize;
	// ��ҵ����
	private String companyType;
	// ��ҵ���
	private String companyBrief;
	// ��Ƹ״̬:1��Ƹ�� 2����ͣ 3�ѽ���
	private int companyState;
	// �������
	private int comanySort;
	// �����
	private int companyViewnum;
	// ����ͼƬ
	private String companyPic;
	// ְλ
	private Set<Job> jobs = new HashSet<Job>();

	public Company() {
		super();
	}

	public Company(int companyId, String compayName, String companyArea,
			String companySize, String companyType, String companyBrief,
			int companyState, int comanySort, int companyViewnum,
			String companyPic) {
		super();
		this.companyId = companyId;
		this.compayName = compayName;
		this.companyArea = companyArea;
		this.companySize = companySize;
		this.companyType = companyType;
		this.companyBrief = companyBrief;
		this.companyState = companyState;
		this.comanySort = comanySort;
		this.companyViewnum = companyViewnum;
		this.companyPic = companyPic;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompayName() {
		return compayName;
	}

	public void setCompayName(String compayName) {
		this.compayName = compayName;
	}

	public String getCompanyArea() {
		return companyArea;
	}

	public void setCompanyArea(String companyArea) {
		this.companyArea = companyArea;
	}

	public String getCompanySize() {
		return companySize;
	}

	public void setCompanySize(String companySize) {
		this.companySize = companySize;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getCompanyBrief() {
		return companyBrief;
	}

	public void setCompanyBrief(String companyBrief) {
		this.companyBrief = companyBrief;
	}

	public int getCompanyState() {
		return companyState;
	}

	public void setCompanyState(int companyState) {
		this.companyState = companyState;
	}

	public int getComanySort() {
		return comanySort;
	}

	public void setComanySort(int comanySort) {
		this.comanySort = comanySort;
	}

	public int getCompanyViewnum() {
		return companyViewnum;
	}

	public void setCompanyViewnum(int companyViewnum) {
		this.companyViewnum = companyViewnum;
	}

	public String getCompanyPic() {
		return companyPic;
	}

	public void setCompanyPic(String companyPic) {
		this.companyPic = companyPic;
	}

	public Set<Job> getJobs() {
		return jobs;
	}

	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}

}
