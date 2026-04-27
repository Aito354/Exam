package scoremanager.main;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LogoutAction extends Action {

    @Override
    public void execute(
        HttpServletRequest req,
        HttpServletResponse res
    ) throws Exception {

        // セッション取得
        HttpSession session = req.getSession(false);

        // ログアウト処理（セッション破棄）
        if (session != null) {
            session.invalidate();
        }

        // ログアウト画面へフォワード
        RequestDispatcher rd = req.getRequestDispatcher("/scoremanager/logout.jsp");
        rd.forward(req, res);
}
}