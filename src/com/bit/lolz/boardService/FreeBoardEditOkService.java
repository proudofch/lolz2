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

public class FreeBoardEditOkService implements Action {

	@Override
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
			String dbFileName = multi.getFilesystemName(file);
			String prvboardfile = multi.getParameter("prvboardfile");
			
			if(dbFileName == null) {
				dbFileName = prvboardfile;
			}
			
			String originalName = multi.getOriginalFileName(file);
			
			BoardDto boarddata = new BoardDto();
			boarddata.setBoardtitle((multi.getParameter("boardtitle")));
			boarddata.setBoardcontent(multi.getParameter("boardcontent"));
			boarddata.setBoardfile(dbFileName);
			boarddata.setId(multi.getParameter("id"));
			boarddata.setBoardnum(Integer.parseInt(multi.getParameter("boardnum")));
			
			String notice = multi.getParameter("notice");
			String prvnotice = multi.getParameter("prvnotice");
			
			System.out.println("notice: "+notice);
			System.out.println("prvnotice: "+prvnotice);
			
			if(notice == null) {
				notice = prvnotice;
			}
			
			boarddata.setBoardnotice(notice);
			
			boarddao dao = new boarddao();
			int result = dao.editOk(boarddata);
			
			String msg = "";
			String url = "";
			
			if(result > 0) {
				msg = "수정 성공!";
				url = "FreeBoardList.Board";
			} else {
				msg = "수정 실패";
				url = "FreeBoardWrite.Board"; 
			}
			
			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/redirect.jsp");
			
		} catch (Exception e) {
			System.out.println("글 수정하기에 문제 발생");
			e.printStackTrace();
		}
		
		return forward;
	
		}
		

}
