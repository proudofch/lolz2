package com.bit.lolz.memberService;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.lolz.action.Action;
import com.bit.lolz.action.ActionForward;
import com.bit.lolz.dao.MemberDao;

public class MemberIdCheckService  implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		String memberIdCheck = null;
		
		try {
			String id = request.getParameter("id");
	       	MemberDao dao = new MemberDao();
	       	memberIdCheck = dao.isCheckByMemberId(id);
//	    	request.setAttribute("empmessage", empNoCheck);
	    	
	    	PrintWriter out = response.getWriter();
	    	out.print(memberIdCheck);
	    	out.close();
//	    	forward = new ActionForward();
//	    	forward.setRedirect(false);
//	    	forward.setPath("/WEB-INF/views/uservalid.jsp");
	    	
//	    	forward = new ActionForward();
//	    	forward.setRedirect(false);
//	    	forward.setPath("/WEB-INF/views/uservalid.jsp");
//	    	
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return forward;
	}

}
