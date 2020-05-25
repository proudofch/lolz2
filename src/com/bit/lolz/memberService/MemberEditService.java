package com.bit.lolz.memberService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bit.lolz.action.Action;
import com.bit.lolz.action.ActionForward;
import com.bit.lolz.dao.MemberDao;
import com.bit.lolz.dto.MemberDto;

public class MemberEditService implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		MemberDto dto = new MemberDto();
		
		//와 수정성공^^~id받는게 관건... url로 넘어오는 파라미터를 받아서 입력시키면되는데..
		dto.setId(request.getAttribute("a")) ;
		dto.setPwd(request.getParameter("pwd")) ;
		dto.setEmail(request.getParameter("email"));
		dto.setSummonerId(request.getParameter("summonerId")) ;
		dto.setBd(request.getParameter("bd")) ;
		//틀렸었던거!
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String bd = request.getParameter("bd");
		String summonerId = request.getParameter("summonerId");
		
		System.out.println("membereditservice의 summonerId : "+summonerId);
		System.out.println("memss id:"+request.getParameter("id"));
		
		MemberDao dao = new MemberDao(); //POINT
		int result = dao.updateMember(dto);

		 	String msg="";
		 	String url="";
		    if(result > 0){
		    	msg ="회원정보가 수정되었습니다.";
		    	url ="Index.Lolz";
		    }else{
		    	msg="수정을 실패했습니다.";
		    	url="MemberEdit.Lolz";
		    }
		    
		    request.setAttribute("board_msg",msg);
		    request.setAttribute("board_url", url);
		    		
		    ActionForward forward = new ActionForward();
		    forward.setRedirect(false);
		    forward.setPath("/WEB-INF/views/redirect.jsp");
		    
		
		
		return forward;
	}
}
