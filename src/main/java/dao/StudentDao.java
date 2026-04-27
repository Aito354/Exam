package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao {

   
    // ■ 学校別一覧取得
    
    public List<Student> filterBySchool(School school) throws Exception {

        List<Student> list = new ArrayList<>();

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "SELECT no, name, ent_year, class_num, is_attend, school_cd " +
            "FROM student WHERE school_cd = ?"
        );

        st.setString(1, school.getCd());

        ResultSet rs = st.executeQuery();

        while (rs.next()) {

            Student s = new Student();

            s.setNo(rs.getInt("no"));             
            s.setName(rs.getString("name"));
            s.setEntYear(rs.getInt("ent_year"));
            s.setClassNum(rs.getInt("class_num")); 
            s.setAttend(rs.getBoolean("is_attend"));
            s.setSchoolCd(rs.getString("school_cd"));

            list.add(s);
        }

        st.close();
        con.close();

        return list;
    }


    // ■ 1件取得（重複チェック用）
    
    public Student get(String no) throws Exception {

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "SELECT no, name, ent_year, class_num, is_attend, school_cd " +
            "FROM student WHERE no = ?"
        );

        st.setInt(1, Integer.parseInt(no)); 

        ResultSet rs = st.executeQuery();

        Student s = null;

        if (rs.next()) {

            s = new Student();

            s.setNo(rs.getInt("no"));              
            s.setName(rs.getString("name"));
            s.setEntYear(rs.getInt("ent_year"));
            s.setClassNum(rs.getInt("class_num")); 
            s.setAttend(rs.getBoolean("is_attend"));
            s.setSchoolCd(rs.getString("school_cd"));
        }

        st.close();
        con.close();

        return s;
    }

    
    // ■ 登録
    
    public boolean save(Student s) throws Exception {

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "INSERT INTO student " +
            "(no, name, ent_year, class_num, is_attend, school_cd) " +
            "VALUES (?, ?, ?, ?, ?, ?)"
        );

        st.setInt(1, s.getNo());          
        st.setString(2, s.getName());
        st.setInt(3, s.getEntYear());
        st.setInt(4, s.getClassNum());   
        st.setBoolean(5, s.isAttend());
        st.setString(6, s.getSchoolCd());

        int line = st.executeUpdate();

        st.close();
        con.close();

        return line > 0;
    }
}