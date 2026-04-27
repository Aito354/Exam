package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Teacher;

public class TeacherDao extends Dao {

	public Teacher get(String id) throws Exception {
	
		Teacher teacher = new Teacher();
		
		Connection connection = getConnection();
		
		PreparedStatement statement = null;

		try {
			
			statement = connection.prepareStatement("select * from teacher where id=?");
			
			statement.setString(1, id);
		
			ResultSet resultSet = statement.executeQuery();

			
			SchoolDao schoolDao = new SchoolDao();

			if (resultSet.next()) {
				
				teacher.setId(resultSet.getString("id"));
				teacher.setPassword(resultSet.getString("password"));
				teacher.setName(resultSet.getString("name"));
				
				teacher.setSchool(schoolDao.get(resultSet.getString("school_cd")));
			} else {
		
				teacher = null;
			}
		} catch (Exception e) {
			throw e;
		} finally {

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return teacher;
	}

	
	public Teacher login(String id, String password) throws Exception {
		
		Teacher teacher = get(id);
	
		if (teacher == null || !teacher.getPassword().equals(password)) {
			return null;
		}
		return teacher;
	}
}
