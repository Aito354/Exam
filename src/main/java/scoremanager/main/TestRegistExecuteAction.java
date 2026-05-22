package scoremanager.main;

import java.sql.Connection;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;
import dao.Dao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistExecuteAction extends Action {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {

        HttpSession session = request.getSession();
        request.setAttribute("user", session.getAttribute("user"));

        School school = new School();
        school.setCd("oom");

        String[] studentNo =
                request.getParameterValues("studentNo");

        String[] point =
                request.getParameterValues("point");

        String subjectCd =
                request.getParameter("subjectCd");

        String classNum =
                request.getParameter("classNum");

        String entYear =
                request.getParameter("entYear");

        int num = Integer.parseInt(
                request.getParameter("num")
        );

        SubjectDao subDao = new SubjectDao();
        Subject subject = subDao.get(subjectCd);

        StudentDao stuDao = new StudentDao();

        Dao dao = new Dao();
        Connection connection = dao.getConnection();

        TestDao testDao = new TestDao();

        for (int i = 0; i < studentNo.length; i++) {

            if (point[i] == null ||
                    point[i].isEmpty()) {

                continue;
            }

            int p;

            try {

                p = Integer.parseInt(point[i]);

            } catch (Exception e) {

                request.getSession().setAttribute(
                        "error",
                        "数値を入力してください"
                );

                response.sendRedirect(
                    "TestRegist.action?f1=" + entYear
                    + "&f2=" + classNum
                    + "&f3=" + subjectCd
                    + "&f4=" + num
                );

                return;
            }

            if (p < 0 || p > 100) {

                request.getSession().setAttribute(
                        "error",
                        "0〜100の範囲で入力してください"
                );

                response.sendRedirect(
                    "TestRegist.action?f1=" + entYear
                    + "&f2=" + classNum
                    + "&f3=" + subjectCd
                    + "&f4=" + num
                );

                return;
            }

            Student student =
                    stuDao.get(studentNo[i]);

            Test test = new Test();

            test.setStudent(student);
            test.setClassNum(classNum);
            test.setSubject(subject);
            test.setSchool(school);
            test.setNo(num);
            test.setPoint(p);

            testDao.save(test, connection);
        }

        connection.close();

        request.getRequestDispatcher(
                "/scoremanager/test_regist_done.jsp"
        ).forward(request, response);
    }
}