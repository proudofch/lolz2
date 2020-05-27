package com.bit.lolz.boardService;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.lolz.action.Action;
import com.bit.lolz.action.ActionForward;
import com.bit.lolz.dao.boarddao;
import com.bit.lolz.dto.BoardDto;


public class FreeBoardEditService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		String strboardnum = request.getParameter("boardnum");
		
		if(strboardnum == null || strboardnum.trim().equals("")) {
			try {
				response.sendRedirect("FreeBoardList.Board");
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
				response.sendRedirect("FreeBoardList.Board");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		ActionForward forward = new ActionForward();
		request.setAttribute("boarddata", boarddata);
		//request.setAttribute("boardnum", boardnum); //strboardnum으로 써야할지도..? //어차피 boarddata에 포함됐는데? 안 써도 될 듯

		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/board/BoardEdit.jsp");
		
		return forward;
	}

}
