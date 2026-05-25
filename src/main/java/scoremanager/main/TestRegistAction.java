package scoremanager.main;


import java.time.LocalDate;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;



public class TestRegistAction extends Action {
	
	 @Override
	    public void execute(
	            HttpServletRequest request,
	            HttpServletResponse response
	    ) throws Exception {
		 
		 HttpSession session = request.getSession();
 	    request.setAttribute("user", session.getAttribute("user"));
		 
		 School school = new School();
	        school.setCd("oom");

	        int year = LocalDate.now().getYear();
	        request.setAttribute("year", year);

	        ClassNumDao cDao = new ClassNumDao();
	        request.setAttribute(
	                "classList",
	                cDao.filter(school)
	        );

	        SubjectDao sDao = new SubjectDao();
	        request.setAttribute(
	                "subjectList",
	                sDao.filter()
	        );

	        String entYearStr = request.getParameter("f1");
	        String classNum = request.getParameter("f2");
	        String subjectCd = request.getParameter("f3");
	        String numStr = request.getParameter("f4");
	        
	        if (entYearStr == null) entYearStr = "";
	        if (classNum == null) classNum = "";
	        if (subjectCd == null) subjectCd = "";
	        if (numStr == null) numStr = "";

	        request.setAttribute("f1", entYearStr);
	        request.setAttribute("f2", classNum);
	        request.setAttribute("f3", subjectCd);
	        request.setAttribute("f4", numStr);

	        if (
	            entYearStr == null || entYearStr.isEmpty() ||
	            classNum == null || classNum.isEmpty() ||
	            subjectCd == null || subjectCd.isEmpty() ||
	            numStr == null || numStr.isEmpty()
	        ) {

	            request.setAttribute(
	                "error",
	                "入学年度とクラスと科目と回数を選択してください"
	            );

	            request.getRequestDispatcher(
	                    "/scoremanager/test_regist.jsp"
	            ).forward(request, response);

	            return;
	        }

	        int entYear = Integer.parseInt(entYearStr);
	        int num = Integer.parseInt(numStr);
	        
	        try {
	            entYear = Integer.parseInt(entYearStr);
	            num = Integer.parseInt(numStr);
	        } catch (NumberFormatException e) {

	            request.setAttribute(
	                "error",
	                "数値が不正です"
	            );

	            request.getRequestDispatcher(
	                "/scoremanager/test_regist.jsp"
	            ).forward(request, response);

	            return;
	        }

	        Subject subject = sDao.get(subjectCd);

	        TestDao dao = new TestDao();

	        List<Test> list = dao.filter(
	                entYear,
	                classNum,
	                subject,
	                num,
	                school
	        );

	        request.setAttribute("list", list);

	        request.getRequestDispatcher(
	                "/scoremanager/test_regist.jsp"
	        ).forward(request, response);
	    }
	}
	 

	

    