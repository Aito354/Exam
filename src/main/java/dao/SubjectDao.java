package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;

public class SubjectDao extends Dao {

    // 科目一覧取得
    public List<Subject> filter() throws Exception {

        List<Subject> list = new ArrayList<>();

        Connection con = getConnection();

        PreparedStatement st =
                con.prepareStatement("SELECT * FROM subject");

        ResultSet rs = st.executeQuery();

        while (rs.next()) {

            Subject s = new Subject();

            s.setCd(rs.getString("cd"));
            s.setName(rs.getString("name"));

            list.add(s);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }

    // 科目登録
    public void save(Subject subject) throws Exception {

        Connection con = getConnection();

        PreparedStatement st =
            con.prepareStatement(
                "INSERT INTO subject(school_cd, cd, name) VALUES(?, ?, ?)"
            );

        st.setString(1, subject.getSchool().getCd());
        st.setString(2, subject.getCd());
        st.setString(3, subject.getName());

        st.executeUpdate();

        st.close();
        con.close();
    }

    // 科目1件取得
    public Subject get(String cd) throws Exception {

        Subject subject = null;

        Connection con = getConnection();

        PreparedStatement st =
            con.prepareStatement(
                "SELECT * FROM subject WHERE cd=?"
            );

        st.setString(1, cd);

        ResultSet rs = st.executeQuery();

        if(rs.next()) {

            subject = new Subject();

            subject.setCd(rs.getString("cd"));
            subject.setName(rs.getString("name"));
        }

        rs.close();
        st.close();
        con.close();

        return subject;
    }

    // 科目変更
    public void update(String cd, String name)
            throws Exception {

        Connection con = getConnection();

        PreparedStatement st =
            con.prepareStatement(
                "UPDATE subject SET name=? WHERE cd=?"
            );

        st.setString(1, name);
        st.setString(2, cd);

        st.executeUpdate();

        st.close();
        con.close();
    }

    // 科目削除
    public void delete(String cd) throws Exception {

        Connection con = getConnection();

        PreparedStatement st =
            con.prepareStatement(
                "DELETE FROM subject WHERE cd=?"
            );

        st.setString(1, cd);

        st.executeUpdate();

        st.close();
        con.close();
    }
}