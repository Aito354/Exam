package tool;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "*.action" })
public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            /*
             * URL例:
             *   /exam/scoremanager/Login.action
             *   → scoremanager.LoginAction
             *
             *   /exam/scoremanager/main/StudentCreateExecute.action
             *   → scoremanager.main.StudentCreateExecuteAction
             */

            // 先頭の "/" を除去
            String path = req.getServletPath().substring(1);
            // 例:
            // scoremanager/Login.action
            // scoremanager/main/StudentCreateExecute.action

            // ".action" → "Action"
            // "/" → "."
            String name = path.replace(".action", "Action")
                              .replace('/', '.');

            // デバッグ表示
            System.out.println("Action Class = " + name);

            // クラスを生成
            Action action = (Action) Class.forName(name)
                                          .getDeclaredConstructor()
                                          .newInstance();

            // Action実行
            action.execute(req, res);

        } catch (Exception e) {
            e.printStackTrace();

            req.setAttribute("error", e);

            // エラーページへ遷移
            req.getRequestDispatcher("/error.jsp")
               .forward(req, res);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        doGet(req, res);
    }
}