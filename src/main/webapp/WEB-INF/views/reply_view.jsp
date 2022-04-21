<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답변 쓰기</title>
</head>
<body>
	<h2>덧글 입력</h2>
	<hr>
	
	<table width="600" cellpadding="0" cellspacing="0" border="1">
		<form action="reply">
		<input type="hidden" name="bid" value="${reply_view.bId }">
		<input type="hidden" name="bgroup" value="${reply_view.bGroup }">	<!-- 얘부터 indent 까지 dto안에 들어있어서 쓴건가 -->
		<input type="hidden" name="bstep" value="${reply_view.bStep }">
		<input type="hidden" name="bindent" value="${reply_view.bIndent }">
	
			<tr>
				<td>번 호</td>
				<td>${reply_view.bId }</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td>${reply_view.bHit }</td>
			</tr>
			<tr>
				<td>이 름</td>
				<td><input type="text" name="bname" size="60" value="${reply_view.bName }"></td>
			</tr>
			<tr>
				<td>제 목</td>
				<td><input type="text" name="btitle" size="60" value="[답변] ${reply_view.bTitle }"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">내 용</td>
			</tr>
			<tr>
				<td colspan="2"><textarea name="bcontent" rows="10" cols="100">
				${reply_view.bContent }
				
				
				[덧글 입력]
				==================================================
				
				
				</textarea></td>
			</tr>
			<tr>
				<td align="right" colspan="2">
					<a href="list">글 목록 보기</a>&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="덧글입력"></td>
			</tr>
			
		</form>
	</table>
	
</body>
</html>