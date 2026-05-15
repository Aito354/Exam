package scoremanager.main;

import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        SubjectDao dao = new SubjectDao();

        dao.update(cd, name);

        response.sendRedirect("SubjectList.action");
    }
}