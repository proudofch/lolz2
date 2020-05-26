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

public class FreeBoardWriteOkService implements Action {

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
			String dbFileName = multi.getFilesystemName(file); //db에 이 이름으로 들어감
				System.out.println("뭐가 숫자 붙는 이름일까요- 이건 dbfilename: "+dbFileName);
			String originalName = multi.getOriginalFileName(file);
				System.out.println("등록된 파일 이름: "+originalName);
			
			BoardDto boarddata = new BoardDto();
			boarddata.setBoardtitle((multi.getParameter("title")));
			boarddata.setBoardcontent(multi.getParameter("content"));
			boarddata.setBoardfile(multi.getParameter("file"));
			
			boarddata.setId(multi.getParameter("id"));
				System.out.println("id: "+boarddata.getId());
			
			boarddao dao = new boarddao();
			int result = dao.writeOk(boarddata, 1);
			
			String msg = "";
			String url = "";
			
			if(result > 0) {
				msg = "글쓰기 성공!";
				url = "FreeBoardList.Board"; //보통 성공하면 쓴 글 상세보기로 감... 나중에 고치기
			} else {
				msg = "글쓰기 실패";
				url = "FreeBoardWrite.Board"; //실패하면 목록으로 가고? 아님 글 쓰던 상태로 가던가? 훔 ... 이것도 나중에 수정
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
