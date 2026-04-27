package scoremanager.main;

import java.util.List;

import bean.School;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        HttpSession session = req.getSession();
        School school = (School) session.getAttribute("school");

        
        ClassNumDao dao = new ClassNumDao();
        List<String> classList = dao.filter(school);

        req.setAttribute("classList", classList);

        
        req.getRequestDispatcher("/scoremanager/student_create.jsp")
           .forward(req, res);
    }
}