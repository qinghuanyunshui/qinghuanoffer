package com.qinghuan.bean;

import java.util.Date;

/**
 * ��ְ��ʵ����
 * 
 * @author QST����ʵѵ
 *
 */
public class Applicant {
	// ��ְ�߱�ʶ
	private int applicantId;
	// ��ְ������
	private String applicantEmail;
	// ��ְ������
	private String applicantPwd;
	// ��ְ��ע��ʱ��
	private Date applicantRegistDate;

	public Applicant() {
		super();
	}

	public Applicant(int applicantId, String applicantEmail, String applicantPwd) {
		super();
		this.applicantId = applicantId;
		this.applicantEmail = applicantEmail;
		this.applicantPwd = applicantPwd;
	}

	public int getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(int applicantId) {
		this.applicantId = applicantId;
	}

	public String getApplicantEmail() {
		return applicantEmail;
	}

	public void setApplicantEmail(String applicantEmail) {
		this.applicantEmail = applicantEmail;
	}

	public String getApplicantPwd() {
		return applicantPwd;
	}

	public void setApplicantPwd(String applicantPwd) {
		this.applicantPwd = applicantPwd;
	}

	public Date getApplicantRegistDate() {
		return applicantRegistDate;
	}

	public void setApplicantRegistDate(Date applicantRegistDate) {
		this.applicantRegistDate = applicantRegistDate;
	}
}
