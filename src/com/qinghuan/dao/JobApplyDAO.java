package com.qinghuan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qinghuan.bean.Company;
import com.qinghuan.bean.Job;
import com.qinghuan.bean.JobApply;
import com.qinghuan.util.DBUtil;

/**
 * 职位申请信息数据处理类
 * 
 * @author QST青软实训
 *
 */
public class JobApplyDAO {

	/**
	 * 保存职位申请信息
	 * 
	 * @param jobid
	 * @param applicantId
	 */
	public void save(String jobid, int applicantId) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO tb_jobapply("
				+ "APPLY_ID,JOB_ID,APPLICANT_ID,APPLY_DATE,APPLY_STATE"
				+ ") VALUES(seq_itoffer_jobapply.nextval,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(jobid));
			pstmt.setInt(2, applicantId);
			pstmt.setTimestamp(3, new Timestamp(new Date().getTime()));
			pstmt.setInt(4, 1);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
	}

	/**
	 * 查询职位申请列表
	 * 
	 * @param applicantId
	 * @return
	 */
	public List<JobApply> getJobApplyList(int applicantId) {
		List<JobApply> list = new ArrayList<JobApply>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT a.apply_id,a.apply_state,a.apply_date,j.job_id,j.job_name,c.company_id,c.company_name "
				+ "FROM tb_jobapply a , tb_job j ,tb_company c "
				+ "WHERE a.job_id=j.job_id and j.company_id=c.company_id "
				+ "and a.applicant_id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, applicantId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				JobApply ja = new JobApply();
				ja.setApplyId(rs.getInt(1));
				ja.setApplyState(rs.getInt(2));
				ja.setApplicantId(applicantId);
				ja.setApplyDate(rs.getTimestamp(3));
				Job job = new Job();
				job.setJobId(rs.getInt(4));
				job.setJobName(rs.getString(5));
				Company company = new Company();
				company.setCompanyId(rs.getInt(6));
				company.setCompayName(rs.getString(7));
				job.setCompany(company);

				ja.setJob(job);
				list.add(ja);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
		return list;
	}

}
