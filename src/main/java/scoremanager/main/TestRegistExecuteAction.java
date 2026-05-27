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

        String[] studentNo = request.getParameterValues("studentNo");
        String[] point = request.getParameterValues("point");

        String subjectCd = request.getParameter("subjectCd");
        String classNum = request.getParameter("classNum");
        String entYear = request.getParameter("entYear");
        String numStr = request.getParameter("num");

        // =========================
        // 未入力チェック（session禁止）
        // =========================
        if (studentNo == null || point == null ||
                subjectCd == null || classNum == null ||
                entYear == null || numStr == null) {

            request.setAttribute(
                    "error",
                    "未入力の項目があります"
            );

            request.getRequestDispatcher(
                    "/scoremanager/test_regist.jsp"
            ).forward(request, response);

            return;
        }

        int num;

        // =========================
        // 回数チェック
        // =========================
        try {
            num = Integer.parseInt(numStr);
        } catch (Exception e) {

            request.setAttribute(
                    "error",
                    "回数が不正です"
            );

            request.getRequestDispatcher(
                    "/scoremanager/test_regist.jsp"
            ).forward(request, response);

            return;
        }

        SubjectDao subDao = new SubjectDao();
        Subject subject = subDao.get(subjectCd);

        StudentDao stuDao = new StudentDao();

        Dao dao = new Dao();
        Connection connection = dao.getConnection();

        TestDao testDao = new TestDao();

        // =========================
        // 登録処理
        // =========================
        for (int i = 0; i < studentNo.length; i++) {

            if (point[i] == null || point[i].isEmpty()) {
                continue;
            }

            int p;

            try {
                p = Integer.parseInt(point[i]);
            } catch (Exception e) {

                request.setAttribute(
                        "error",
                        "数値を入力してください"
                );

                request.getRequestDispatcher(
                        "/scoremanager/test_regist.jsp"
                ).forward(request, response);

                connection.close();
                return;
            }

            if (p < 0 || p > 100) {

                request.setAttribute(
                        "error",
                        "0〜100の範囲で入力してください"
                );

                request.getRequestDispatcher(
                        "/scoremanager/test_regist.jsp"
                ).forward(request, response);

                connection.close();
                return;
            }

            Student student = stuDao.get(studentNo[i]);

            if (student == null || subject == null) {
                continue;
            }

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

        // =========================
        // 完了画面
        // =========================
        request.getRequestDispatcher(
                "/scoremanager/test_regist_done.jsp"
        ).forward(request, response);
    }
}