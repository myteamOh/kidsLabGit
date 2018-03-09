<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의신청(수강신청 및 결제)</title>

<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
	
</head>
<body>
	<section>
		<div>
			<form>
				<div>
					<table>
						<caption>수강정보</caption>
						<tr>
							<td>강의명</td>
							<td>강의명표시</td>
						</tr>
						<tr>
							<td>강의 기간</td>
							<td>기간 ~ 기간</td>
						</tr>
						<tr>
							<td>강의 요일 및 시간</td>
							<td>요일 / 시간</td>
						</tr>
						<tr>
							<td>수강학생</td>
							<td><select>
									<c:forEach var="" items="">
										<option></option>
									</c:forEach>
							</select></td>
						</tr>
					</table>
				</div>

				<div>
					<label>환불규정 안내</label>
					<div>환불규정</div>
				</div>

				<div>
					<table>
						<caption>결제하기</caption>
						<tr>
							<td>결제금액</td>
							<td>금액표시</td>
						</tr>
						<tr>
							<td>결제방법</td>
							<td><input type="radio" id="bankbookDeposit" value="bankbookDeposit"><label for="bankbookDeposit">무통장입금</label></td>
						</tr>
					</table>
				</div>

				<h5>현재는 무통장입금만 가능합니다.</h5>
				
				<div>
				<input type="button" id="" value="결제">
				<input type="button" id="" value="취소">
				</div>
			</form>
		</div>
	</section>
</body>
</html>