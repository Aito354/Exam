package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends Dao {

  
    // ■ 1件取得

    public ClassNum get(String class_num, School school) throws Exception {

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "SELECT school_cd, class_num FROM class_num " +
            "WHERE school_cd = ? AND class_num = ?"
        );

        st.setString(1, school.getCd());
        st.setString(2, class_num);

        ResultSet rs = st.executeQuery();

        ClassNum c = null;

        if (rs.next()) {
            c = new ClassNum();
            c.setSchoolCd(rs.getString("school_cd"));
            c.setClassNum(rs.getString("class_num"));
        }

        st.close();
        con.close();

        return c;
    }


    // ■ 一覧取得（クラス番号だけ）
    
    public List<String> filter(School school) throws Exception {

        List<String> list = new ArrayList<>();

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "SELECT class_num FROM class_num WHERE school_cd = ?"
        );

        st.setString(1, school.getCd());

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            list.add(rs.getString("class_num"));
        }

        st.close();
        con.close();

        return list;
    }

    
    // ■ 新規登録
    
    public boolean save(ClassNum classNum) throws Exception {

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "INSERT INTO class_num (school_cd, class_num) VALUES (?, ?)"
        );

        st.setString(1, classNum.getSchoolCd());
        st.setString(2, classNum.getClassNum());

        int line = st.executeUpdate();

        st.close();
        con.close();

        return line > 0;
    }

    
    // ■ 更新（クラス番号変更）
    
    public boolean save(ClassNum classNum, String newClassNum) throws Exception {

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "UPDATE class_num SET class_num = ? " +
            "WHERE school_cd = ? AND class_num = ?"
        );

        st.setString(1, newClassNum);
        st.setString(2, classNum.getSchoolCd());
        st.setString(3, classNum.getClassNum());

        int line = st.executeUpdate();

        st.close();
        con.close();

        return line > 0;
    }
}
