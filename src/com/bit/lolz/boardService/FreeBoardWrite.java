package com.bit.lolz.boardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.lolz.action.Action;
import com.bit.lolz.action.ActionForward;
import com.bit.lolz.dao.boarddao;
import com.bit.lolz.dto.BoardDto;

public class FreeBoardWrite implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		boarddao dao = new boarddao();
		BoardDto boarddata = new BoardDto();
		
		int result = dao.writeOk(boarddata, 1);
		
		return null;
	}

}
