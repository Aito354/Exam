package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Student;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        // セッションから学校情報を取得
        HttpSession session = req.getSession();
        School school = (School) session.getAttribute("school");

        // DAO
        StudentDao dao = new StudentDao();
        List<Student> list = dao.filterBySchool(school);

        // JSPへ渡す
        req.setAttribute("students", list);

        // 画面表示
        req.getRequestDispatcher("/scoremanager/student_list.jsp")
           .forward(req, res);
    }
}