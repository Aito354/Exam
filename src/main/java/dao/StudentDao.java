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
<<<<<<< HEAD
=======
    // コメントを追加
>>>>>>> branch 'master' of https://github.com/Aito354/Exam.git
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

        rs.close();
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

        rs.close();
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

    // ■ 更新
    public boolean update(Student s) throws Exception {

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "UPDATE student " +
            "SET name = ?, " +
            "    ent_year = ?, " +
            "    class_num = ?, " +
            "    is_attend = ? " +
            "WHERE no = ?"
        );

        st.setString(1, s.getName());
        st.setInt(2, s.getEntYear());
        st.setInt(3, s.getClassNum());
        st.setBoolean(4, s.isAttend());
        st.setInt(5, s.getNo());

        int line = st.executeUpdate();

        st.close();
        con.close();

        return line > 0;
    }

    // ■ 条件検索（学生管理一覧画面用）
    public List<Student> filter(String entYear, String classNum, boolean isAttend) throws Exception {

        List<Student> list = new ArrayList<>();

        Connection con = getConnection();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT no, name, ent_year, class_num, is_attend, school_cd ");
        sql.append("FROM student WHERE 1=1 ");

        if (entYear != null && !entYear.isEmpty()) {
            sql.append("AND ent_year = ? ");
        }

        if (classNum != null && !classNum.isEmpty()) {
            sql.append("AND class_num = ? ");
        }

        if (isAttend) {
            sql.append("AND is_attend = true ");
        }

        sql.append("ORDER BY no");

        PreparedStatement st = con.prepareStatement(sql.toString());

        int index = 1;

        if (entYear != null && !entYear.isEmpty()) {
            st.setInt(index++, Integer.parseInt(entYear));
        }

        if (classNum != null && !classNum.isEmpty()) {
            st.setInt(index++, Integer.parseInt(classNum));
        }

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

        rs.close();
        st.close();
        con.close();

        return list;
    }
}