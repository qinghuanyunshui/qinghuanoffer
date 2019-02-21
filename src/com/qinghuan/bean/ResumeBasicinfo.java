package com.qinghuan.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.qinghuan.dao.ResumeDAO;

public class ResumeBasicinfo {
	// ������ʶ
	private int basicinfoId;
	// ���� 
	private String realName;
	// �Ա�
	private String gender;
	// ��������
	private Date birthday;
	// ��ǰ���ڵ�
	private String currentLoc;
	// �������ڵ�
	private String residentLoc;
	// �ֻ�
	private String telephone;
	// �ʼ�
	private String email;
	// ��ְ����
	private String jobIntension;
	// ��������
	private String jobExperience;
	// ͷ��
	private String headShot;
	// ������Ϣ���½��
	private String resumeUpdateResult;
	// �������ڵ��ַ�����ʽ
	private String strbirthday;
	
	public ResumeBasicinfo() {
	}

	public ResumeBasicinfo(String realName, String gender, Date birthday,
			String currentLoc, String residentLoc, String telephone,
			String email, String jobIntension, String jobExperience) {
		super();
		this.realName = realName;
		this.gender = gender;
		this.birthday = birthday;
		this.currentLoc = currentLoc;
		this.residentLoc = residentLoc;
		this.telephone = telephone;
		this.email = email;
		this.jobIntension = jobIntension;
		this.jobExperience = jobExperience;
	}

	public void setResumeUpdate(ResumeBasicinfo resumeBasicinfo){
		// ���¼���������Ϣ
		try{
			ResumeDAO dao = new ResumeDAO();
			dao.update(resumeBasicinfo);
		}catch(Exception e){
			resumeUpdateResult="����ʧ�ܣ�";
		}
		resumeUpdateResult="���³ɹ���";
	}
	
	public String getResumeUpdateResult(){
		return resumeUpdateResult;
	}
	
	public void setStrbirthday(String strbirthday){
		this.strbirthday = strbirthday;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date birthdayDate = null;
		try {
			birthdayDate = sdf.parse(strbirthday);
		} catch (ParseException e) {
			birthdayDate = null;
		}
		this.setBirthday(birthdayDate);
	}
	public String getStrbirthday(){
		return strbirthday;
	}
	public int getBasicinfoId() {
		return basicinfoId;
	}
	public void setBasicinfoId(int basicinfoId) {
		this.basicinfoId = basicinfoId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.strbirthday = sdf.format(birthday == null ? "" : birthday);
	}
	public String getCurrentLoc() {
		return currentLoc;
	}
	public void setCurrentLoc(String currentLoc) {
		this.currentLoc = currentLoc;
	}
	public String getResidentLoc() {
		return residentLoc;
	}
	public void setResidentLoc(String residentLoc) {
		this.residentLoc = residentLoc;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJobIntension() {
		return jobIntension;
	}
	public void setJobIntension(String jobIntension) {
		this.jobIntension = jobIntension;
	}
	public String getJobExperience() {
		return jobExperience;
	}
	public void setJobExperience(String jobExperience) {
		this.jobExperience = jobExperience;
	}

	public String getHeadShot() {
		return headShot;
	}

	public void setHeadShot(String headShot) {
		this.headShot = headShot;
	}
	
}
