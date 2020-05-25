package com.bit.lolz.memberService;


import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import com.bit.lolz.action.Action;
import com.bit.lolz.action.ActionForward;
import com.bit.lolz.dao.empDao;

public class MemberAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		ActionForward forward = null;
		
		try {
			
			int empNo = Integer.parseInt(multi.getParameter("empNo"));
			String ename = multi.getParameter("ename");
			String job = multi.getParameter("job");
			int mgr = Integer.parseInt(multi.getParameter("mgr"));
			String hireDate = multi.getParameter("hireDate");
			int sal = Integer.parseInt(multi.getParameter("sal"));
			int comm = Integer.parseInt(multi.getParameter("comm"));
			int deptNo = Integer.parseInt(multi.getParameter("deptNo"));
	
	
			
			empDao dao = new empDao(); //POINT
			int result = dao.insertLolz(id,pwd,email, bd, summonerId);
			
			String msg = "";
			String url = "";
			if(result > 0) {
				msg = "등록 성공";
				url = "Emplist.Emp";
			} else {
				msg = "등록 실패";
				url = "emp.jsp";
			}
			
			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/redirect.jsp");
						
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
		
	}
}