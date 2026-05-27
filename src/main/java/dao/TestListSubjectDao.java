package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

    public List<TestListSubject> filter(
            int entYear,
            int classNum,
            Subject subject,
            School school
    ) throws Exception {

        List<TestListSubject> list = new ArrayList<>();

        Connection con = getConnection();

        String sql = """
            SELECT
                s.ent_year,
                s.class_num,
                s.no,
                s.name,
                t.no AS test_no,
                t.point
            FROM student s
            JOIN test t
                ON s.no = t.student_no
            WHERE s.ent_year = ?
              AND s.class_num = ?
              AND t.subject_cd = ?
              AND s.school_cd = ?
            ORDER BY s.no, t.no
        """;

        PreparedStatement st = con.prepareStatement(sql);

        
        st.setInt(1, entYear);
        st.setInt(2, classNum);              
        st.setString(3, subject.getCd());
        st.setString(4, school.getCd());

        ResultSet rs = st.executeQuery();

        Map<String, TestListSubject> map = new HashMap<>();

        while (rs.next()) {

            String no = rs.getString("no");

            TestListSubject tls = map.get(no);

            if (tls == null) {

                tls = new TestListSubject();

                tls.setEntYear(rs.getInt("ent_year"));
                tls.setClassNum(rs.getString("class_num"));
                tls.setStudentNo(no);
                tls.setStudentName(rs.getString("name"));

                tls.setPoints(new HashMap<Integer, Integer>());

                map.put(no, tls);
            }

            tls.putPoint(
                    rs.getInt("test_no"),
                    rs.getInt("point")
            );
        }

        list.addAll(map.values());

        rs.close();
        st.close();
        con.close();

        return list;
    }
}