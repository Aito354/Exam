
package scoremanager.main;
import java.util.List;

import bean.Student;
import bean.TestListStudent;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListStudentExecuteAction extends Action{
	
@Override
public void execute(HttpServletRequest req,
HttpServletResponse res)throws Exception{
int no=Integer.parseInt(req.getParameter("no"));
Student student=new Student();
student.setNo(no);
TestListStudentDao dao=new TestListStudentDao();
List<TestListStudent> list=dao.filter(student);
req.setAttribute("list",list);
req.getRequestDispatcher("test_list_student.jsp")
.forward(req,res);
}
}