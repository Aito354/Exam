package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        HttpSession session = req.getSession();
        School school = (School) session.getAttribute("school");

        String no = req.getParameter("no");
        String name = req.getParameter("name");
        String entYear = req.getParameter("entYear");
        String classNum = req.getParameter("classNum");

        List<String> errors = new ArrayList<>();

        
        // 入力チェック
        
        if (no == null || no.isEmpty()) {
            errors.add("学生番号を入力してください");
        }

        if (name == null || name.isEmpty()) {
            errors.add("氏名を入力してください");
        }

        if (entYear == null || entYear.isEmpty()) {
            errors.add("入学年度を選択してください");
        }

        // 数値チェック
        int noInt = 0;
        int entYearInt = 0;
        int classNumInt = 0;

        try {
            noInt = Integer.parseInt(no);
        } catch (Exception e) {
            errors.add("学生番号は数字で入力してください");
        }

        try {
            entYearInt = Integer.parseInt(entYear);
        } catch (Exception e) {
            errors.add("入学年度が不正です");
        }

        try {
            classNumInt = Integer.parseInt(classNum);
        } catch (Exception e) {
            errors.add("クラス番号が不正です");
        }

        
        // エラーがある場合
        
        if (errors.size() > 0) {

            req.setAttribute("errors", errors);
            req.setAttribute("no", no);
            req.setAttribute("name", name);
            req.setAttribute("entYear", entYear);
            req.setAttribute("classNum", classNum);

            req.getRequestDispatcher("/scoremanager/student_create.jsp")
               .forward(req, res);
            return;
        }

        
        StudentDao dao = new StudentDao();

        if (dao.get(no) != null) {
            errors.add("学生番号が重複しています");

            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/scoremanager/student_create.jsp")
               .forward(req, res);
            return;
        }

        
        // 登録
        
        Student s = new Student();

        s.setNo(noInt);
        s.setName(name);
        s.setEntYear(entYearInt);
        s.setClassNum(classNumInt);
        s.setAttend(true);
        s.setSchoolCd(school.getCd());

        dao.save(s);

       
        // 完了画面
        
        req.getRequestDispatcher("/scoremanager/student_create_done.jsp")
           .forward(req, res);
    }
}