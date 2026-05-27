package scoremanager.main;

import java.time.LocalDate;
import java.util.List;

import bean.School;
import bean.Student;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.SubjectDao;
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

        // 学校情報
        School school = new School();
        school.setCd("oom");

        // 年度
        int year = LocalDate.now().getYear();

        req.setAttribute(
                "year",
                year
        );

        // クラス一覧
        ClassNumDao cDao =
                new ClassNumDao();

        req.setAttribute(
                "classList",
                cDao.filter(school)
        );

        // 科目一覧
        SubjectDao sDao =
                new SubjectDao();

        req.setAttribute(
                "subjectList",
                sDao.filter()
        );

        // パラメータ取得
        String entYear =
                req.getParameter("f1");

        String classNum =
                req.getParameter("f2");

        String subjectCd =
                req.getParameter("f3");

        String noStr =
                req.getParameter("f4");

        // 初回表示
        if (req.getParameter("f1") == null
                && req.getParameter("f2") == null
                && req.getParameter("f3") == null
                && req.getParameter("f4") == null) {

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