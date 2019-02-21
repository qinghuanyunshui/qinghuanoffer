package com.qinghuan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.qinghuan.bean.Company;
import com.qinghuan.bean.Job;
import com.qinghuan.util.DBUtil;

public class CompanyDAO {

	/**
	 * 查询所有正在招聘中的企业信息以及该企业的最新职位信息
	 * 
	 * @return
	 */
	public List<Company> getCompanyList() {
		List<Company> list = new ArrayList<Company>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT tb_company.company_id,company_pic,job_id,job_name,job_salary,job_area,job_endtime "
					+ "FROM tb_company "
					+ "LEFT OUTER JOIN tb_job ON tb_job.company_id=tb_company.company_id "
					+ "WHERE company_state=1 and job_id IN ("
					+ "SELECT MAX(job_id) FROM tb_job WHERE job_state=1 GROUP BY company_id"
					+ ")";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Company company = new Company();
				Job job = new Job();
				company.setCompanyId(rs.getInt("company_id"));
				company.setCompanyPic(rs.getString("company_pic"));
				job.setJobId(rs.getInt("job_id"));
				job.setJobName(rs.getString("job_name"));
				job.setJobSalary(rs.getString("job_salary"));
				job.setJobArea(rs.getString("job_area"));
				job.setJobEnddate(rs.getTimestamp("job_endtime"));
				company.getJobs().add(job);
				list.add(company);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 根据企业标识查询企业详情
	 * 
	 * @param companyID
	 * @return
	 */
	public Company getCompanyByID(String companyID) {
		Company company = new Company();
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM tb_company WHERE company_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(companyID));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				company.setCompanyId(rs.getInt("company_id"));
				company.setCompanyArea(rs.getString("company_area"));
				company.setCompanyBrief(rs.getString("company_brief"));
				company.setCompanyPic(rs.getString("company_pic"));
				company.setCompanySize(rs.getString("company_size"));
				company.setCompanyType(rs.getString("company_type"));
				company.setCompanyViewnum(rs.getInt("company_viewnum"));
				company.setCompayName(rs.getString("company_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
		return company;
	}
	
	/**
	 * 分页查询首页所需要的所有企业信息及职位信息
	 * @return
	 */
	public List<Company> getCompanyPageList(int pageNo,int pageSize) {
		// 定义本页记录索引值
		int firstIndex = pageSize * (pageNo-1);
		List<Company> list = new ArrayList<Company>();
		Connection connection = DBUtil.getConnection();
		if (connection == null)
			return null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection
					.prepareStatement("SELECT * FROM ( SELECT a.* , ROWNUM rn FROM ( "
							+ "SELECT tb_company.company_id,company_pic,job_id,"
							+ "job_name,job_salary,job_area,job_endtime "
							+ "FROM tb_company "
							+ "LEFT OUTER JOIN tb_job ON tb_company.company_id=tb_job.company_id "
							+ "WHERE company_state=1 and job_id IN ("
							+ "SELECT MAX(job_id) FROM tb_job WHERE job_state=1 GROUP BY company_id"
							+ ")) a WHERE ROWNUM<=? ) WHERE rn>? ");
			pstmt.setInt(1, firstIndex+pageSize);
			pstmt.setInt(2, firstIndex);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Company company = new Company();
				Job job = new Job();
				company.setCompanyId(rs.getInt("company_id"));
				company.setCompanyPic(rs.getString("company_pic"));
				job.setJobId(rs.getInt("job_id"));
				job.setJobName(rs.getString("job_name"));
				job.setJobSalary(rs.getString("job_salary"));
				job.setJobArea(rs.getString("job_area"));
				job.setJobEnddate(rs.getTimestamp("job_endtime"));
				company.getJobs().add(job);
				list.add(company);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, connection);
		}
		return list;
	}
	
	/**
	 * 查询所需分页的总记录数
	 * @param pageSize
	 * @return
	 */
	public int getRecordCount() {
		int recordCount = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT count(*) FROM tb_company "
					+ "LEFT OUTER JOIN tb_job ON tb_job.company_id=tb_company.company_id "
					+ "WHERE company_state=1 and job_id IN ("
					+ "SELECT MAX(job_id) FROM tb_job WHERE job_state=1 GROUP BY company_id"
					+ ")";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				recordCount = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
		return recordCount;
	}
	/**
	 * 更新企业的浏览次数
	 * @param id
	 */
	public void updateCompanyViewCount(int id) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "UPDATE tb_company "
					+ "SET company_viewnum=company_viewnum+1 "
					+ "WHERE company_id=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
	}
}
