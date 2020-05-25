package com.bit.lolz.boardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.lolz.action.Action;
import com.bit.lolz.action.ActionForward;
import com.bit.lolz.dao.boarddao;

public class FreeBoardListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		boarddao dao = new boarddao();
		int totalBoardCount = dao.totalBoardCount(); //전체 게시물 건수 얻기
		
		String psStr = request.getParameter("ps"); //pagesize
		String cpStr = request.getParameter("cp"); //currentpage
		
		if(psStr == null || psStr.trim().equals("")) {
			psStr = "10"; //request 객체에 값이 없을 경우 default - 10건
		}
		
		if(cpStr == null || cpStr.trim().equals("")) {
			cpStr = "1"; //request 객체에 값이 없을 경우  default - 1페이지
		}
		
		int pageSize = Integer.parseInt(psStr);
		int currentPage = Integer.parseInt(cpStr);
		
		int pageCount = 0; //페이지 개수
		
		if(totalBoardCount % pageSize == 0) { 
			pageCount = totalBoardCount / pageSize;
		} else { 
			pageCount = (totalBoardCount / pageSize) + 1; //딱 떨어지지 않는다 = 게시물이 남는다 -> 페이지 하나 늘림
		}
		
		
		
		
		return null;
	}

}
