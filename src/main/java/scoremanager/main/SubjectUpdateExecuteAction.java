package scoremanager.main;

import bean.Subject;
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

        // 未入力チェック
        if (name == null || name.trim().isEmpty()) {

            Subject subject = new Subject();
            subject.setCd(cd);
            subject.setName(name);

            request.setAttribute("subject", subject);
            request.setAttribute("error", "科目名を入力してください");

            request.getRequestDispatcher(
                "/scoremanager/main/subject_update.jsp")
                .forward(request, response);

            return;
        }

        SubjectDao dao = new SubjectDao();
        dao.update(cd, name);

        response.sendRedirect("SubjectList.action");
    }
}