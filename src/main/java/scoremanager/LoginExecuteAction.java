package scoremanager;

import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LoginExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String id = req.getParameter("id");
		String password = req.getParameter("password");

		TeacherDao teacherDao = new TeacherDao();
		Teacher teacher = teacherDao.login(id, password);

		if (teacher != null) {

			HttpSession session = req.getSession(true);

			teacher.setAuthenticated(true);

			session.setAttribute("user", teacher);

			// ★これ追加
			session.setAttribute("school", teacher.getSchool());

			res.sendRedirect("main/Menu.action");

		} else {

			List<String> errors = new ArrayList<>();
			errors.add("IDまたはパスワードが確認できませんでした");

			req.setAttribute("errors", errors);
			req.setAttribute("id", id);

			req.getRequestDispatcher("login.jsp").forward(req, res);
		}
	}
}