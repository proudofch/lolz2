package com.bit.lolz.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.lolz.action.Action;
import com.bit.lolz.action.ActionForward;
import com.bit.lolz.memberService.LoginOkService;
import com.bit.lolz.memberService.LogoutService;
import com.bit.lolz.memberService.MemberAddService;
import com.bit.lolz.memberService.MemberBoardListService;
import com.bit.lolz.memberService.MemberDeleteService;
import com.bit.lolz.memberService.MemberEditPrvService;
import com.bit.lolz.memberService.MemberEditService;
import com.bit.lolz.memberService.MemberIdCheckService;
import com.bit.lolz.memberService.MemberListService;





@WebServlet("*.Lolz")
public class FrontMemberController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public FrontMemberController() {
        super();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_Command = requestURI.substring(contextPath.length());

    	Action action = null;
    	ActionForward forward = null;
    	
    	 
    	if(url_Command.equals("/Login.Lolz")) { //로그인 UI
            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/login/login.jsp");
		}
		 else if(url_Command.equals("/LoginOk.Lolz")) { //로그인 처리
			 System.out.println("loginok 탐");
			 action = new LoginOkService();
			 forward = action.execute(request, response);
		 }
		 else if(url_Command.equals("/Logout.Lolz")) { //로그아웃 처리
			 action = new LogoutService();
			 forward = action.execute(request, response);
			} 
		else if(url_Command.equals("/MemberList.Lolz")) { //회원 목록 보기
			action = new MemberListService();
			forward = action.execute(request, response);
				
		} else if(url_Command.equals("/MemberEditOk.Lolz")) { //내 정보 수정
		  action = new MemberEditService();
   		forward = action.execute(request, response); 
			  
		} else if(url_Command.equals("/prvMemberEdit.Lolz")) { //내 정보 수정 [이전꺼 불러오기]		
			action = new MemberEditPrvService();
     		forward = action.execute(request, response); 
    		
        }  else if(url_Command.equals("/MemberAdd.Lolz")) { //회원가입		
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/WEB-INF/views/login/memberadd.jsp");
    		
        } else if(url_Command.equals("/IdCheck.Lolz")) { //회원가입시 id 중복 체크
    		action = new MemberIdCheckService();
    		forward = action.execute(request, response);
    		
    	} else if(url_Command.equals("/MemberAddOk.Lolz")) { //회원가입 UI에서 받은 정보 처리
        	action = new MemberAddService();
     		forward = action.execute(request, response); 
     		
		} else if(url_Command.equals("/Index.Lolz")) { //메인페이지로 가기
        	action = new MemberAddService();
     		forward = action.execute(request, response); 
     		forward.setPath("/index.jsp");
		} else if(url_Command.equals("/MyPage.Lolz")) { //마이 페이지
			action = new MemberAddService();
			forward = action.execute(request, response);
			forward.setPath("/WEB-INF/views/mypage.jsp");
		}
		  else if(url_Command.equals("/MemberDelete.Lolz")) {//회원탈퇴
			action = new MemberDeleteService();
			forward = action.execute(request,response);
		} else if(url_Command.equals("/MemberBoardList.Lolz")) {//내가 쓴 글 목록
			action = new MemberBoardListService();
			forward = action.execute(request,response);
		}  
    	
    	if(forward != null) {
    		RequestDispatcher dis  = request.getRequestDispatcher(forward.getPath());
    		dis.forward(request, response);
    	}

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}
