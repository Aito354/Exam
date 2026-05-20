package scoremanager.main;

import bean.Student;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse res)
            throws Exception {

        // URLパラメータから学生番号を取得
        String no = req.getParameter("no");

        // 学生番号が指定されていない場合は一覧へ戻す
        if (no == null || no.isEmpty()) {
            res.sendRedirect("StudentList.action");
            return;
        }

        // 学生情報を取得
        StudentDao dao = new StudentDao();
        Student student = dao.get(no);

        // 該当する学生が見つからない場合は一覧へ戻す
        if (student == null) {
            res.sendRedirect("StudentList.action");
            return;
        }

        // JSPに学生情報を渡す
        req.setAttribute("student", student);

        // 学生変更画面を表示
        // JSPの保存場所:
        // src/main/webapp/scoremanager/main/student_update.jsp
        req.getRequestDispatcher(
            "/scoremanager/main/student_update.jsp"
        ).forward(req, res);
    }
}