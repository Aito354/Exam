package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao{

	private String baseSql =
	   "select" +
	   "sub.name as subject_name," +
	   "sub.cd as subject_cd," +
	   "t.no,"+
	   "t.point" +
	   "from test t" +
	   "join subject sub on t.subject_cd = sub.cd";
	
	public List<TestListStudent> filter(Student student) throws Exception{
		List<TestListStudent> list = new ArrayList<>();
		Connection con = getConnection();
		String sql = baseSql + "where t.student_no = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, student.getNo());
		ResultSet rs = st.executeQuery();
		list = postFilter(rs);
		st.close();
		con.close();
		
		return list;
			
	}
	private List<TestListStudent> postFilter(ResultSet rs)
			throws Exception{
		List<TestListStudent> list = new ArrayList<>();
		while (rs.next()) {
			TestListStudent bean = new TestListStudent();
			
			bean.setSubjectName(rs.getString("subject_name"));
			bean.setSubjectCd(rs.getString("subject_cd"));
			bean.setNum(rs.getInt("no"));
			bean.setPoint(rs.getInt("point"));
			
			list.add(bean);
		}
		return list;
	}
	
}