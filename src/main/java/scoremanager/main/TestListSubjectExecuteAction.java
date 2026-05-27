package scoremanager.main;

import java.time.LocalDate;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {

        // セッション
        HttpSession session = request.getSession();
        request.setAttribute("user", session.getAttribute("user"));

        // 学校情報
        School school = new School();
        school.setCd("oom");

        
        int entYear = Integer.parseInt(request.getParameter("f1"));
        int classNum = Integer.parseInt(request.getParameter("f2")); 
        String subjectCd = request.getParameter("f3");

        // 科目取得
        SubjectDao sDao = new SubjectDao();
        Subject subject = sDao.get(subjectCd);

        // 成績検索
        TestListSubjectDao dao = new TestListSubjectDao();

        List<TestListSubject> list = dao.filter(
                entYear,
                classNum,   // intで渡す
                subject,
                school
        );

        request.setAttribute("list", list);

        // 初期表示
        int year = LocalDate.now().getYear();
        request.setAttribute("year", year);

        ClassNumDao cDao = new ClassNumDao();
        request.setAttribute("classList", cDao.filter(school));

        request.setAttribute("subjectList", sDao.filter());

        // 選択保持
        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", subjectCd);

        request.setAttribute("type", "subject");

        // JSPへ
     // 科目情報渡す
        request.setAttribute("subject", subject);

        // 成績一覧（科目）へ
        request.getRequestDispatcher(
                "/scoremanager/test_list_subject.jsp"
        ).forward(request, response);
    }
}