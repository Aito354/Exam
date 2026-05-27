package scoremanager.main;

import java.util.List;

import bean.Student;
import bean.TestListStudent;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    @Override
    public void execute(
            HttpServletRequest req,
            HttpServletResponse res
    ) throws Exception {

        // 学生番号取得
        String noStr =
                req.getParameter("no");

        // 未入力チェック
        if (noStr == null || noStr.isEmpty()) {

            req.setAttribute(
                    "error",
                    "学生番号を入力してください"
            );

            req.getRequestDispatcher(
                    "test_list_student.jsp"
            ).forward(req, res);

            return;
        }

        // 数値変換
        int no;

        try {

            no = Integer.parseInt(noStr);

        } catch (NumberFormatException e) {

            req.setAttribute(
                    "error",
                    "学生番号は数字で入力してください"
            );

            req.getRequestDispatcher(
                    "test_list_student.jsp"
            ).forward(req, res);

            return;
        }

        // Student
        Student student =
                new Student();

        student.setNo(no);

        // DAO
        TestListStudentDao dao =
                new TestListStudentDao();

        // 検索
        List<TestListStudent> list =
                dao.filter(student);

        // データなし
        if (list == null || list.isEmpty()) {

            req.setAttribute(
                    "error",
                    "学生情報が存在しませんでした"
            );

            req.getRequestDispatcher(
                    "test_list_student.jsp"
            ).forward(req, res);

            return;
        }

        // JSPへ
        req.setAttribute(
                "student",
                student
        );

        req.setAttribute(
                "list",
                list
        );

        req.getRequestDispatcher(
                "test_list_student.jsp"
        ).forward(req, res);
    }
}