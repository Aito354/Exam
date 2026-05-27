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

        HttpSession session = request.getSession();
        request.setAttribute("user", session.getAttribute("user"));

        School school = new School();
        school.setCd("oom");

     
        //  画面表示用データ
       
        int year = LocalDate.now().getYear();
        request.setAttribute("year", year);

        ClassNumDao cDao = new ClassNumDao();
        request.setAttribute("classList", cDao.filter(school));

        SubjectDao sDao = new SubjectDao();
        request.setAttribute("subjectList", sDao.filter());

        
        // パラメータ取得）
        
        String f1 = request.getParameter("f1");
        String f2 = request.getParameter("f2");
        String f3 = request.getParameter("f3");

        
        //  未入力チェック
        
        if (f1 == null || f1.isEmpty()
                || f2 == null || f2.isEmpty()
                || f3 == null || f3.isEmpty()) {

            request.setAttribute("error",
                    "入学年度とクラスと科目を選択してください");

            request.getRequestDispatcher(
                    "/scoremanager/test_list_subject.jsp"
            ).forward(request, response);

            return;
        }

        
        //  数値変換
        
        int entYear = Integer.parseInt(f1);
        int classNum = Integer.parseInt(f2);
        String subjectCd = f3;

        
        // 科目取得
       
        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.get(subjectCd);
        request.setAttribute("subject", subject);

      
        //  検索処理
        
        TestListSubjectDao dao = new TestListSubjectDao();

        List<TestListSubject> list = dao.filter(
                entYear,
                classNum,
                subject,
                school
        );

       
        if (list == null || list.isEmpty()) {

            request.setAttribute("error",
                    "学生情報が存在しませんでした");

            request.getRequestDispatcher(
                    "/scoremanager/test_list_subject.jsp"
            ).forward(request, response);

            return;
        }

     
        request.setAttribute("list", list);

        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", subjectCd);

        request.setAttribute("type", "subject");

        request.getRequestDispatcher(
                "/scoremanager/test_list_subject.jsp"
        ).forward(request, response);
    }
}