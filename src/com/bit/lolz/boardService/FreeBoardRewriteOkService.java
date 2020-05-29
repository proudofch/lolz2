package com.bit.lolz.boardService;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.lolz.action.Action;
import com.bit.lolz.action.ActionForward;
import com.bit.lolz.dao.boarddao;

import com.bit.lolz.dto.BoardDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FreeBoardRewriteOkService implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String uploadPath = request.getServletContext().getRealPath("upload");
		System.out.println("uploadpath: "+uploadPath);
	int size = 1024*1024*10; //파일 크기 설정
	
	ActionForward forward = null;

	try {
		MultipartRequest multi = new MultipartRequest(
				request, //기존에 있는 request 객체 주소값
				uploadPath, //실제 저장 경로
				size,
				"UTF-8",
				new DefaultFileRenamePolicy() //파일명 중복 시, 파일명=파일명+숫자
				);
		
		Enumeration fileNames = multi.getFileNames();
		String file = (String)fileNames.nextElement();
		String dbFileName = multi.getFilesystemName(file); //클라이언트가 올린 파일이 중복일 경우 숫자 붙는 파일명
			System.out.println("dbFileName: "+dbFileName);
		String originalName = multi.getOriginalFileName(file); //클라이언트가 올리려 한 실제 파일명
			System.out.println("originalName: "+originalName);
		
		BoardDto boarddata = new BoardDto();
		boarddata.setBoardtitle((multi.getParameter("title")));
		boarddata.setBoardcontent(multi.getParameter("content"));
		boarddata.setBoardfile(dbFileName);

		String boardnum =multi.getParameter("boardnum");
		boarddata.setBoardnum(Integer.parseInt(boardnum));

		
		
		String notice = multi.getParameter("notice");
		
		if(notice != null) {
			boarddata.setBoardnotice(notice);
		} else {
			boarddata.setBoardnotice("N");
		}
		
		boarddata.setId(multi.getParameter("id"));
			System.out.println("id: "+boarddata.getId());
		
		boarddao dao = new boarddao();
		int result = dao.rewriteOk(boarddata, 1);
		
		String msg = "";
		String url = "";
		
		if(result > 0) {
			msg = "답글 쓰기 성공";
			url = "FreeBoardList.Board"; 
		} else {
			msg = "답글 쓰기 실패";
			url = "FreeBoardList.Board"; 
		}
		
		request.setAttribute("board_msg", msg);
		request.setAttribute("board_url", url);
		
		forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/redirect.jsp");
		
	} catch (Exception e) {
		System.out.println("자유게시판 글쓰기ok에 문제 발생");
		e.printStackTrace();
	}
	
	return forward;

	}
}
