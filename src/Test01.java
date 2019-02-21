import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.qinghuan.util.DBUtil;

public class Test01 {
public static void main(String[] args) {
	Connection conn = null;
	conn = DBUtil.getConnection();
	Statement stmt = null;
	ResultSet rs = null;
	int i = 0;
	try {
		stmt = conn.createStatement();
		String sql = "select * from tb_applicant";
		rs = stmt.executeQuery(sql);
		while(rs.next()) {
			System.out.println(rs.getInt(1));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}
}
