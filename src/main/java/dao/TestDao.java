package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

    private String baseSql =
        "select t.*, s.ent_year " +
        "from test t " +
        "join student s " +
        "on t.student_no = s.no " +
        "where s.ent_year=? " +
        "and t.class_num=? " +
        "and t.subject_cd=? " +
        "and t.no=? " +
        "and t.school_cd=?";

    // 成績検索
    public List<Test> filter(
            int entYear,
            String classNum,
            Subject subject,
            int num,
            School school
    ) throws Exception {

        List<Test> list = new ArrayList<>();

        Connection connection = getConnection();

        PreparedStatement statement =
            connection.prepareStatement(baseSql);

        statement.setInt(1, entYear);
        statement.setInt(2, Integer.parseInt(classNum));
        statement.setString(3, subject.getCd());
        statement.setInt(4, num);
        statement.setString(5, school.getCd());

        ResultSet rs = statement.executeQuery();

        StudentDao sDao = new StudentDao();

        while (rs.next()) {

            Test test = new Test();

            Student student =
                sDao.get(
                    String.valueOf(rs.getInt("student_no"))
                );

            test.setStudent(student);

            test.setClassNum(
                String.valueOf(rs.getInt("class_num"))
            );

            test.setSubject(subject);

            test.setSchool(school);

            test.setNo(rs.getInt("no"));

            test.setPoint(rs.getInt("point"));

            list.add(test);
        }

        rs.close();
        statement.close();
        connection.close();

        return list;
    }

    // 成績保存（PostgreSQL対応）
    public boolean save(
            Test test,
            Connection connection
    ) throws Exception {

        String sql =
            "insert into test " +
            "(student_no, subject_cd, school_cd, no, point, class_num) " +
            "values (?, ?, ?, ?, ?, ?) " +
            "on conflict (student_no, subject_cd, school_cd, no) " +
            "do update set " +
            "point = excluded.point, " +
            "class_num = excluded.class_num";

        PreparedStatement statement =
            connection.prepareStatement(sql);

        statement.setInt(1, test.getStudent().getNo());
        statement.setString(2, test.getSubject().getCd());
        statement.setString(3, test.getSchool().getCd());
        statement.setInt(4, test.getNo());
        statement.setInt(5, test.getPoint());
        statement.setInt(6,Integer.parseInt(test.getClassNum()));

        int count = statement.executeUpdate();

        statement.close();

        return count > 0;
    }
}

