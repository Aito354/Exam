package scoremanager.main;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        Subject subject = new Subject();

        subject.setCd(cd);
        subject.setName(name);

        // 学校情報
        School school = new School();

        school.setCd("tes");

        subject.setSchool(school);

        SubjectDao dao = new SubjectDao();

        dao.save(subject);

        response.sendRedirect("SubjectList.action");
    }
}