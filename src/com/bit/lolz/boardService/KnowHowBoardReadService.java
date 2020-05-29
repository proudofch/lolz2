package com.bit.lolz.boardService;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.lolz.action.Action;
import com.bit.lolz.action.ActionForward;
import com.bit.lolz.dao.boarddao;
import com.bit.lolz.dto.BoardDto;

public class KnowHowBoardReadService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String boardNumStr = request.getParameter("boardnum");
		
		if(boardNumStr == null || boardNumStr.trim().equals("")) {
			try {
				response.sendRedirect("KnowHowBoardList.Board");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("boardnum 없어서 sendredirect 했는데 문제 발생");
			}
		}
		
		boardNumStr = boardNumStr.trim();
		
		String cpstr = request.getParameter("cp");
		String psstr = request.getParameter("ps");
		
		if(cpstr == null || cpstr.trim().equals("")) {
			cpstr = "1";
		}
		
		if(psstr == null || psstr.trim().equals("")) {
			psstr = "10";
		}
		
		int boardnum = Integer.parseInt(boardNumStr);
		
		boarddao dao = new boarddao();

		//조회수 증가
		boolean flag = dao.getBoardHit(boardnum);
		
		BoardDto dto = dao.boardRead(boardnum);
		
		//존재하지 않는 글번호로 게시글을 조회했을 때 게시판으로 돌려보낸다.
		if(dto == null) {
			try {
				response.sendRedirect("KnowHowBoardList.Board");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("존재하지 않는 글번호 때문에 redirect하는데 오류 발생");
			}
		}
		
		ActionForward forward = new ActionForward();
		request.setAttribute("dto", dto); //게시물번호로 조회한 글 내용 1건 담긴 dto
		request.setAttribute("boardnum", boardnum);
		request.setAttribute("cp", cpstr);
		request.setAttribute("ps", psstr);
		request.setAttribute("dao", dao); //얘는 왜?
		
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/board/KnowHowBoardContent.jsp");
		
		return forward;
	}

}
