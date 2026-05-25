package scoremanager.main;

import java.util.List;

import bean.Student;
import bean.TestListStudent;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public void execute(
            HttpServletRequest req,
            HttpServletResponse res
    ) throws Exception {

        // パラメータ取得
        String entYear =
                req.getParameter("entYear");

        String classNum =
                req.getParameter("classNum");

        String subjectCd =
                req.getParameter("subjectCd");

        String noStr =
                req.getParameter("no");

        // 初回表示
        if (req.getParameter("entYear") == null
                && req.getParameter("classNum") == null
                && req.getParameter("subjectCd") == null
                && req.getParameter("no") == null) {

            req.getRequestDispatcher(
                    "/scoremanager/test_list.jsp"
            ).forward(req, res);

            return;
        }

        // 未入力チェック
        if (entYear == null || entYear.isEmpty()
                || classNum == null || classNum.isEmpty()
                || subjectCd == null || subjectCd.isEmpty()) {

            req.setAttribute(
                    "error",
                    "入学年度とクラスと科目を選択してください"
            );

            req.getRequestDispatcher(
                    "/scoremanager/test_list.jsp"
            ).forward(req, res);

            return;
        }

        // 学生番号未入力
        if (noStr == null || noStr.isEmpty()) {

            req.setAttribute(
                    "error",
                    "学生番号を入力してください"
            );

            req.getRequestDispatcher(
                    "/scoremanager/test_list.jsp"
            ).forward(req, res);

            return;
        }

        // String → int
        int no;

        try {

            no = Integer.parseInt(noStr);

        } catch (NumberFormatException e) {

            req.setAttribute(
                    "error",
                    "学生番号は数字で入力してください"
            );

            req.getRequestDispatcher(
                    "/scoremanager/test_list.jsp"
            ).forward(req, res);

            return;
        }

        // DAO
        TestListStudentDao dao =
                new TestListStudentDao();

        // 学生情報
        Student student =
                new Student();

        student.setNo(no);

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
                    "/scoremanager/test_list.jsp"
            ).forward(req, res);

            return;
        }

        // JSPへ
        req.setAttribute(
                "list",
                list
        );

        req.setAttribute(
                "student",
                student
        );

        req.getRequestDispatcher(
                "/scoremanager/test_list.jsp"
        ).forward(req, res);
    }
}