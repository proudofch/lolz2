package com.bit.lolz.boardService;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.lolz.action.Action;
import com.bit.lolz.action.ActionForward;
import com.bit.lolz.dao.boarddao;
import com.bit.lolz.dto.BoardDto;

public class KnowHowBoardEditService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		String strboardnum = request.getParameter("boardnum");
		
		if(strboardnum == null || strboardnum.trim().equals("")) {
			try {
				response.sendRedirect("KnowHowBoardList.Board");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		int boardnum = Integer.parseInt(strboardnum);
		
		boarddao dao = new boarddao();
		BoardDto boarddata = dao.getEditContent(boardnum);
		
		if(boarddata == null) {
			System.out.println("수정 데이터 오류");
			try {
				response.sendRedirect("KnowHowBoardList.Board");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		ActionForward forward = new ActionForward();
		request.setAttribute("boarddata", boarddata);

		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/board/KnowHowBoardEdit.jsp");
		
		return forward;
	}

}
