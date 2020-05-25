package com.bit.lolz.service;


import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.empDao;

public class EmpAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String uploadpath = request.getServletContext().getRealPath("upload"); //application.getRealPath를 쓸 수 없어서 대신 이렇게... 이거 더 공부
		System.out.println("uploadpath: "+uploadpath);
		int size = 1024*1024*10; //이미지 크기 설정
		
		ActionForward forward = null;
		
		try {
			
			MultipartRequest multi = new MultipartRequest(
					request, //기존에 있는 request 객체 주소값
					uploadpath, //실제 저장 경로
					size,
					"UTF-8",
					new DefaultFileRenamePolicy() //파일명 중복 시, 파일명=파일명+숫자
					);
			
			Enumeration filenames = multi.getFileNames();
			
			String file = (String)filenames.nextElement();
			
			String img = multi.getFilesystemName(file); //db에 등록한 파일 이름이 들어감
			
			if(img == null) {
				img = "default.jpg";
			}
			
			String originalname = multi.getOriginalFileName(file);
				System.out.println("img: "+img); //등록한 파일 이름 출력
				System.out.println("originalname: "+originalname);
			
			int empNo = Integer.parseInt(multi.getParameter("empNo"));
			String ename = multi.getParameter("ename");
			String job = multi.getParameter("job");
			int mgr = Integer.parseInt(multi.getParameter("mgr"));
			String hireDate = multi.getParameter("hireDate");
			int sal = Integer.parseInt(multi.getParameter("sal"));
			int comm = Integer.parseInt(multi.getParameter("comm"));
			int deptNo = Integer.parseInt(multi.getParameter("deptNo"));
			System.out.println("deptNo: "+deptNo);
			System.out.println("param: "+multi.getParameter("deptNo"));
			
			empDao dao = new empDao(); //POINT
			int result = dao.insertEmp(empNo, ename, job, mgr, hireDate, sal, comm, deptNo, img);
			
			String msg = "";
			String url = "";
			if(result > 0) {
				msg = "등록 성공";
				url = "Emplist.Emp";
			} else {
				msg = "등록 실패";
				url = "emp.jsp";
			}
			
			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/redirect.jsp");
						
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
		
	}
}