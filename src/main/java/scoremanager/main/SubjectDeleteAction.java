package scoremanager.main;

import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectDeleteAction extends Action {

    @Override
    public void execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        String cd = request.getParameter("cd");

        SubjectDao dao = new SubjectDao();

        dao.delete(cd);

        response.sendRedirect("SubjectList.action");
    }
}