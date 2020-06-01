<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <title>내가 쓴 게시물</title>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>

<jsp:include page="/WEB-INF/views/header/header.jsp"></jsp:include>
   <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>
    <script src=" https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"></script>

<script type="text/javascript">
var lang_kor = {
        "decimal" : "",
        "emptyTable" : "데이터가 없습니다.",
        "info" : "_START_ - _END_ (총 _TOTAL_ 명)",
        "infoEmpty" : "0명",
        "infoFiltered" : "(전체 _MAX_ 명 중 검색결과)",
        "infoPostFix" : "",
        "thousands" : ",",
        "lengthMenu" : "_MENU_",
        "loadingRecords" : "로딩중...",
        "processing" : "처리중...",
        "search" : "검색 : ",
        "zeroRecords" : "검색된 데이터가 없습니다.",
 /*        "paginate" : {
            "first" : "첫 페이지",
            "last" : "마지막 페이지",
            "next" : "다음",
            "previous" : "이전"
        },
        "aria" : {
            "sortAscending" : " :  오름차순 정렬",
            "sortDescending" : " :  내림차순 정렬"
        } */
    }; 

jQuery(function($){ 
   $("#table_id").DataTable({
      "paging" : true,
        "pagingType"   : "full_numbers",
       "ordering" : true,
       "info" : true,
       "filter" : true,
      "lengthChange" : true,
      
    /*   "columns": 
           { "width": "20%" }, */
      /*  "scrollX":true,  -이거하면 table head가 짤리더라고요.아직 필요없는 기능같으니 보류  */
        "lengthMenu": [ 10, 20, 30, 40, 50 ],
      // "displayLength": 10,
      " stateSave": true,
        language: lang_kor,
        "scrollY": 400,
        "paging" : false
  
   }); 
   }); 
</script>
<style >
.dataTable{
   table-layout:fixed;
}
/* table tbody td{
min-width:500px;} */
</style>
<body>
   <div id="main" class="wrapper style1">
      <div class="container">
         <header class="major">
            <h2>내가 쓴 글</h2>
         </header>
         
         <div class="table-wrapper">
            <table id="table_id">
               <thead>
                  <tr>
                     <th width="10%">글번호</th>
                     <th width="50%">글제목</th>
                     <th width="10%">글쓴이</th>
                     <th width="10%">작성일</th>
                     <th width="10%">조회수</th>
                  </tr>
               </thead>
               <tbody>
                  <c:set var="boardlist" value="${requestScope.boardlist}" />
                  
                  <!-- 게시물이 한 건도 없을 경우의 처리 (추가) -->
                  
                   <c:forEach var="list" items="${boardlist}">
                  <tr>
                     <td>${list.boardnum}</td>
                     <td>
                        <a href="FreeBoardRead.Board?boardnum=${list.boardnum}&cp=${requestScope.currentpage}&ps=${requestScope.pagesize}">
                           <c:choose>
                              <c:when test="${list.boardtitle != null && fn:length(list.boardtitle) > 30}">
                                        ${fn:substring(list.boardtitle, 0, 30)}...
                                    </c:when>
                              <c:otherwise>
                                           ${list.boardtitle}
                                     </c:otherwise>
                           </c:choose>
                        </a>
                     </td>
                     
                     <td>${list.id}</td>
                     <td>${list.boarddate}</td>
                     <td>${list.boardhit}</td>
                  </tr>
                  </c:forEach> 
               </tbody>
               <tfoot>
                  <tr>
                     <td colspan="5">
               <%--          <c:if test="${currentpage > 1}">
                           <a href="FreeBoardList.Board?cp=${currentpage-1}&ps=${pagesize}">이전</a>
                        </c:if> --%>
<%-- 
                        <c:forEach var="i" begin="1" end="${pagecount}" step="1">
                           <c:choose>
                              <c:when test="${currentpage == i}">
                                 <font color="tomato">[${i}]</font>
                              </c:when>
                              <c:otherwise>
                                 <a href="FreeBoardList.Board?cp=${i}&ps=${pagesize}">[${i}]</a>
                              </c:otherwise>
                           </c:choose>
                        </c:forEach>

                        <c:if test="${currentpage < pagecount}">
                           <a href="FreeBoardList.Board?cp=${currentpage+1}&ps=${pagesize}">다음</a>
                        </c:if> --%>
                        
                     </td>
                  </tr>
               </tfoot>
            </table>
         </div> <!-- table-wrapper end -->
      </div> <!-- container end -->
   </div> <!-- wrapper end -->
</body>
</html>