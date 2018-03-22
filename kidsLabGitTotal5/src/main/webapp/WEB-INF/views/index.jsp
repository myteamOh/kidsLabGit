<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$(function() {
		$(".goDetail").click(function() {
			var notice_no = $(this).parents("tr").attr("data-num");
			$("#notice_no").val(notice_no);
			// 상세 페이지로 이동하기 위해 form 추가(id : noticeDetailForm)
			$("#noticeDetailForm").attr({
				"method" : "get",
				"action" : "/notice/noticeDetail"
			});
			$("#noticeDetailForm").submit();
		});
	});
</script>
<tiles:insertDefinition name="intro"></tiles:insertDefinition>
