<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<title>오시는 길</title>

<style type="text/css">
#businfo {
	border: 1px solid olivedrab;
}

#map {
	border: 1px solid olivedrab;
}
</style>

</head>

<body>

	<div class="container" align="center">
		<!-- START THE FEATURETTES -->

		<div id="map" style="width: 500px; height: 500px;">
			<script type="text/javascript"
				src="//dapi.kakao.com/v2/maps/sdk.js?appkey=409aef9c43bc3326d76893ba72d2d957"></script>
			<script>
				var container = document.getElementById('map');
				var options = {
					center : new daum.maps.LatLng(37.562275, 127.03514),
					level : 3
				};

				var map = new daum.maps.Map(container, options);

				// 마커의 이미지 정보를 가지고 있는 마커이미지 생성.
				var markerImageSrc = '/resources/images/image/kidslablogo.png', //마커의 이미지 주소
				markerImageSize = new daum.maps.Size(64, 69), markerImageOption = {
					offset : new daum.maps.Point(27, 69)
				}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

				// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다.
				var markerImage = new daum.maps.MarkerImage(markerImageSrc,
						markerImageSize, markerImageOption), markerPosition = new daum.maps.LatLng(
						37.562275, 127.03514); // 마커가 표시될 위치입니다.

				// 마커를 생성합니다.		
				var marker = new daum.maps.Marker({
					position : markerPosition,
					image : markerImage
				//마커 이미지 설정
				});

				// 마커가 지도 위에 표시되도록 설정합니다
				marker.setMap(map);

				// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
				var zoomControl = new daum.maps.ZoomControl();
				map.addControl(zoomControl, daum.maps.ControlPosition.RIGHT);
			</script>

		</div>

		<div align="left">
			<button type="button"
				onclick="location.href='http://map.daum.net/link/to/27477251'"
				>길찾기</button>
		</div>
		<hr class="featurette-divider">
		<div id="businfo">
			<div>
				<p>교통정보</p>
			</div>
			<div>
				<img alt="교통정보" src="/resources/images/image/busInfo.png">
			</div>
		</div>

		<!-- /END THE FEATURETTES -->


	</div>
	<!-- /.container -->



</body>
</html>