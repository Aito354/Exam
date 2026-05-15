package scoremanager.main;

import java.util.List;

import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectListAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // DAO生成
        SubjectDao dao = new SubjectDao();

        // 科目一覧取得
        List<Subject> list = dao.filter();

        // JSPへ渡す
        request.setAttribute("list", list);

        // 画面表示
        request.getRequestDispatcher("../subject_list.jsp")
               .forward(request, response);
    }
}