package com.qinghuan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.qinghuan.bean.Company;
import com.qinghuan.bean.Job;
import com.qinghuan.util.DBUtil;

/**
 * 职位数据操作
 * 
 * @author QST青软实训
 *
 */
public class JobDAO {

	/**
	 * 根据企业编号查询此企业的所有招聘职位
	 * 
	 * @param companyID
	 * @return
	 */
	public List<Job> getJobListByCompanyID(String companyID) {
		List<Job> list = new ArrayList<Job>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM tb_job WHERE company_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,Integer.parseInt(companyID));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Job job = new Job();
				job.setJobId(rs.getInt("job_id"));
				job.setJobName(rs.getString("job_name"));
				job.setJobSalary(rs.getString("job_salary"));
				job.setJobArea(rs.getString("job_area"));
				job.setJobEnddate(rs.getTimestamp("job_endtime"));
				list.add(job);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 根据职位编号查询职位详细信息
	 * @param jobid
	 * @return
	 */
	public Job getJobByID(String jobid) {
		Job job = new Job();
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT tb_job.*,company_pic "
					+ "FROM tb_job "
					+ "INNER JOIN tb_company on tb_job.company_id =  tb_company.company_id "
					+ "WHERE job_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,Integer.parseInt(jobid));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				job.setJobId(rs.getInt("job_id"));
				job.setJobName(rs.getString("job_name"));
				job.setJobHiringnum(rs.getInt("job_hiringnum"));
				job.setJobSalary(rs.getString("job_salary"));
				job.setJobArea(rs.getString("job_area"));
				job.setJobDesc(rs.getString("job_desc"));
				job.setJobEnddate(rs.getTimestamp("job_endtime"));
				job.setJobState(rs.getInt("job_state"));
				Company company = new Company();
				company.setCompanyId(rs.getInt("company_id"));
				company.setCompanyPic(rs.getString("company_pic"));
				job.setCompany(company);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
		return job;
	}

}
