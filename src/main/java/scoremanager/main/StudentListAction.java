package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Student;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // 検索条件取得
        String entYear = req.getParameter("entYear");
        String classNum = req.getParameter("classNum");
        String isAttend = req.getParameter("isAttend");

        // DAO
        StudentDao dao = new StudentDao();

        // 学生一覧取得
        List<Student> studentList = dao.filter(
                entYear,
                classNum,
                isAttend != null
        );

        // 入学年度一覧
        List<Integer> entYearList = new ArrayList<>();
        for (int year = 2020; year <= 2030; year++) {
            entYearList.add(year);
        }

        // クラス一覧
        List<String> classNumList = new ArrayList<>();
        classNumList.add("101");
        classNumList.add("102");
        classNumList.add("201");
        classNumList.add("202");

        // JSPへ渡す
        req.setAttribute("studentList", studentList);
        req.setAttribute("entYearList", entYearList);
        req.setAttribute("classNumList", classNumList);

        // student_list.jsp を表示
        // 保存場所:
        // src/main/webapp/scoremanager/main/student_list.jsp
        req.getRequestDispatcher("/scoremanager/main/student_list.jsp")
           .forward(req, res);
    }
}