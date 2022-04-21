<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 목록</title>
</head>
<body>

	<h2>자유게시판 글 목록</h2>
	<hr>
	<table width="1000" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td align="center" bgcolor="pink"><b>번호</b></td>
			<td align="center" bgcolor="pink"><b>글쓴이</b></td>
			<td align="center" bgcolor="pink"><b>글제목</b></td>
			<td align="center" bgcolor="pink"><b>등록일</b></td>
			<td align="center" bgcolor="EAEAEA"><b>조회수</b></td>
		</tr>
		<c:forEach items="${list }" var="dto">
		<tr>
			<td align="center">${dto.bId }</td>
			<td align="center">${dto.bName }</td>			
			<td>
				<c:forEach begin="1" end="${dto.bIndent }">&nbsp;&nbsp;re:</c:forEach>	
				<a href="content_view?bId=${dto.bId }">${dto.bTitle }</a>
			</td>
			<td align="center">${dto.bDate }</td>
			<td align="center">${dto.bHit }</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="5" align="right"><a href="write_view">글쓰기</a></td>
		</tr>
	
	</table>
	
</body>
</html>