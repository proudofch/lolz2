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
import com.bit.lolz.memberService.MemberAddService;
import com.bit.lolz.memberService.MemberIdCheckService;





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
    	
    	 /*
    	if(url_Command.equals("/Adminlogin.Emp")) { //로그인 UI
            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/adminlogin.jsp");
            
		}
			 * else if(url_Command.equals("/adminloginok.Emp")) { //로그인 처리 action = new
			 * AdminLoginOkService(); forward = action.execute(request, response);
			 * 
			 * } else if(url_Command.equals("/Adminlogout.Emp")) { //로그아웃 처리 action = new
			 * AdminLogoutService(); forward = action.execute(request, response);
			 * 
			 * } else if(url_Command.equals("/Emplist.Emp")) { //전체 사원 목록 보기 action = new
			 * EmpListService(); forward = action.execute(request, response);
			 * 
			 * } else if(url_Command.equals("/Deptlist.Emp")) { //전체 부서 목록 보기 action = new
			 * DeptListService(); forward = action.execute(request, response);
			 * 
			 * }
			  else*/ if(url_Command.equals("/MemberAdd.Lolz")) { //회원가입		
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/WEB-INF/views/memberadd.jsp");
    		
        } else if(url_Command.equals("/IdCheck.Lolz")) { //회원가입시 id 중복 체크
    		action = new MemberIdCheckService();
    		forward = action.execute(request, response);
    		
    	} else if(url_Command.equals("/MemberAddOk.Lolz")) { //사원 추가 UI에서 받은 정보 처리
        	action = new MemberAddService();
     		forward = action.execute(request, response); 
     		
		} /*
			 * else if(url_Command.equals("/DeptAdd.Emp")) { //부서 추가(지금까진 부서 추가 UI 페이지 뿌리는
			 * 상태 > 수정해야 할 듯) forward = new ActionForward(); forward.setRedirect(false);
			 * forward.setPath("/WEB-INF/views/deptadd.jsp");
			 * 
			 * } else if(url_Command.equals("/DeptNoCheck.Emp")) { //부서 번호 중복 확인 action =
			 * new DeptNoCheckService(); forward = action.execute(request, response);
			 * 
			 * } else if(url_Command.equals("/DeptAddOk.Emp")) { //부서 추가 정보 처리 action = new
			 * DeptAddService(); forward = action.execute(request, response);
			 * 
			 * } else if(url_Command.equals("/Empsearch.Emp")) { //사원 검색 UI forward = new
			 * ActionForward(); forward.setRedirect(false);
			 * forward.setPath("/WEB-INF/views/empsearch.jsp");
			 * 
			 * //비동기 검색은 전체 사원 목록에 DataTable 붙이면서 그쪽에서 다 되고 있음
			 * 
			 * } else if(url_Command.equals("/EmpSearchByNo.Emp")) { //사번으로 검색 action = new
			 * EmpSearchService(); forward = action.execute(request, response);
			 * 
			 * } else if(url_Command.equals("/EmpSearchByEname.Emp")) { //이름으로 검색 action =
			 * new EmpSearchService(); forward = action.execute(request, response);
			 * 
			 * } else if(url_Command.equals("/EmpSearchByDeptno.Emp")) { //부서 번호로 검색 action
			 * = new EmpSearchService(); forward = action.execute(request, response);
			 * 
			 * } else if(url_Command.equals("/EmpDelete.Emp")) { //사원 삭제 //이걸 굳이 두 개로 나눌 필요가
			 * 있을까... action = new EmpDeleteService(); forward = action.execute(request,
			 * response);
			 * 
			 * } else if(url_Command.equals("/EmpDeleteOK.Emp")) { //사원 삭제 OK action = new
			 * EmpDeleteOkService(); forward = action.execute(request, response);
			 * 
			 * } else if(url_Command.equals("/prvEmpModify.Emp")) { //사원 수정 action = new
			 * EmpModifyPrvService(); forward = action.execute(request, response);
			 * 
			 * } else if(url_Command.equals("/EmpModify.Emp")) { //사원 수정 OK action = new
			 * EmpModifyService(); forward = action.execute(request, response);
			 * 
			 * } else if(url_Command.equals("/Chart.Emp")) { //차트 action = new
			 * ChartAction(); forward = action.execute(request, response); }
			 */
    	
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
