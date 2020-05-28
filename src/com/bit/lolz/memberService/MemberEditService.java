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
		

		dto.setId(request.getParameter("id")) ;
		dto.setPwd(request.getParameter("pwd")) ;
		dto.setEmail(request.getParameter("email"));
		dto.setSummonerId(request.getParameter("summonerId")) ;
		dto.setSummonerScore(request.getParameter("summonerScore")) ;

		String id = request.getParameter("id");

		
		MemberDao dao = new MemberDao(); //POINT
		int result = dao.updateMember(dto);

		 	String msg="";
		 	String url="";
		    if(result > 0){
		    	msg ="회원정보가 수정되었습니다.";
		    	url ="Index.Lolz";
		    }else{
		    	msg="수정을 실패했습니다.";
		    	url="prvMemberEdit.Lolz?id="+id;
		    }
		    
		    request.setAttribute("board_msg",msg);
		    request.setAttribute("board_url", url);
		    		
		    ActionForward forward = new ActionForward();
		    forward.setRedirect(false);
		    forward.setPath("/WEB-INF/views/redirect.jsp");
		    
		
		
		return forward;
	}
}
