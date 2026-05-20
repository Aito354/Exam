package scoremanager.main;

import bean.Student;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // フォームから送信された値を取得
        String no = req.getParameter("no");
        String name = req.getParameter("name");
        String entYearStr = req.getParameter("entYear");
        String classNumStr = req.getParameter("classNum");

        // チェックボックス（チェックされている場合のみ送信される）
        boolean isAttend = req.getParameter("isAttend") != null;

        // Student オブジェクト作成
        Student student = new Student();

        // String → int に変換して設定
        student.setNo(Integer.parseInt(no));
        student.setName(name);
        student.setEntYear(Integer.parseInt(entYearStr));
        student.setClassNum(Integer.parseInt(classNumStr));
        student.setAttend(isAttend);

        // 更新処理（save() ではなく update() を使用）
        StudentDao dao = new StudentDao();
        boolean result = dao.update(student);

        // 結果に応じて画面遷移
        if (result) {
            req.setAttribute("student", student);
            req.getRequestDispatcher("/scoremanager/main/student_update_done.jsp")
               .forward(req, res);
        } else {
            req.setAttribute("error", "更新に失敗しました。");
            req.setAttribute("student", student);
            req.getRequestDispatcher("/scoremanager/main/student_update.jsp")
               .forward(req, res);
        }
    }
}