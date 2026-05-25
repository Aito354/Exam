package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req,
            HttpServletResponse res)
            throws Exception {

        // セッション取得
        HttpSession session = req.getSession();
        School school =
            (School)session.getAttribute("school");

        // 入力値取得
        String cd = req.getParameter("cd");
        String name = req.getParameter("name");

        // trim()で空白除去
        if (cd != null) {
            cd = cd.trim();
        }

        if (name != null) {
            name = name.trim();
        }

        // エラー格納用
        List<String> errors =
            new ArrayList<>();

        // ---------------------
        // 入力チェック
        // ---------------------

        // 科目コード未入力
        if (cd == null || cd.isEmpty()) {
            errors.add(
                "科目コードを入力してください");
        }

        // 科目名未入力
        if (name == null || name.isEmpty()) {
            errors.add(
                "科目名を入力してください");
        }

        // 3文字チェック
        if (cd != null
                && !cd.isEmpty()
                && cd.length() != 3) {

            errors.add(
                "科目コードは3文字で入力してください");
        }

        // ---------------------
        // エラー時
        // ---------------------

        if (!errors.isEmpty()) {

            req.setAttribute(
                "errors", errors);

            req.setAttribute(
                "cd", cd);

            req.setAttribute(
                "name", name);

            req.getRequestDispatcher(
                "/scoremanager/main/subject_create.jsp")
                .forward(req, res);

            return;
        }

        SubjectDao dao =
            new SubjectDao();

        // ---------------------
        // 重複チェック
        // ---------------------

        if (dao.get(cd) != null) {

            errors.add(
                "科目コードが重複しています");

            req.setAttribute(
                "errors", errors);

            req.setAttribute(
                "cd", cd);

            req.setAttribute(
                "name", name);

            req.getRequestDispatcher(
                "/scoremanager/main/subject_create.jsp")
                .forward(req, res);

            return;
        }

        // ---------------------
        // 登録処理
        // ---------------------

        Subject subject =
            new Subject();

        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(school);

        dao.save(subject);

        // 完了画面へ
        req.setAttribute(
            "subject", subject);

        req.getRequestDispatcher(
            "/scoremanager/main/subject_create_done.jsp")
            .forward(req, res);
    }
}