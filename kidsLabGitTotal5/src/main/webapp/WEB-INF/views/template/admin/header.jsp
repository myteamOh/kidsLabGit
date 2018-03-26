<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page trimDirectiveWhitespaces="true"%>
<div class="container-fluid">

	<div class="navbar-header">
		<button type="button" class="navbar-toggle collapsed"
			data-toggle="collapse" data-artger="#navbar" aria-expanded="false"
			aria-controls="navbar">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<c:if test="${sessionScope.adminLogin != null }">
			<a class="navbar-brand" href="/admin/payment/paymentList">My
				Project[ 관리자 페이지 ]</a>
		</c:if>
		<c:if test="${sessionScope.adminLogin == null }">
			<a class="navbar-brand" href="/admin/login">My Project[ 관리자 페이지 ]</a>
		</c:if>

	</div>

	<div id="navbar" class="navbar-collapse collapse">
		<ul class="nav navbar-nav navbar-right">
			<li><a href="/">사용자 페이지로 이동</a></li>
			<li><a href="/admin/logout.do">LOG-OUT</a></li>
		</ul>
	</div>

</div>