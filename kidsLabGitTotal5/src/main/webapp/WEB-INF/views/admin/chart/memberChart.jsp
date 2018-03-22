<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	// Pie 차트
	google.charts.load("current", {
		packages : [ "corechart" ]
	});

	google.charts.setOnLoadCallback(drawChart);

	function drawChart() {
		var data = google.visualization.arrayToDataTable([
			${rootList}
		]);

		var options = {
			title : '알게된 경로',
			is3D : true,
		};

		var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
		chart.draw(data, options);
	}
	
	// Bar 차트
	google.charts.load("current", {packages:['corechart']});
    google.charts.setOnLoadCallback(drawBarChart);
    function drawBarChart() {
      var data = google.visualization.arrayToDataTable([
        ${barChart}
      ]);
      var view = new google.visualization.DataView(data);
      view.setColumns([0, 1,
                       { calc: "stringify",
                         sourceColumn: 1,
                         type: "string",
                         role: "annotation" },
                       2]);

      var options = {
        title: "학년 별 인원",
        width: 600,
        height: 400,
        bar: {groupWidth: "95%"},
        legend: { position: "none" },
      };
      var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
      chart.draw(view, options);
      }
    
    // 라인차트
    var chartDrowFun = {
    		 
    	    chartDrow : function(){
    	        var chartData = '';
    	 
    	        //날짜형식 변경하고 싶으시면 이 부분 수정하세요.
    	        var chartDateformat     = 'yy년MM월';
    	        //라인차트의 라인 수
    	        var chartLineCount    = 10;
    	        //컨트롤러 바 차트의 라인 수
    	        var controlLineCount    = 10;
    	 
    	 
    	        function drawDashboard() {
    	 
    	          var data = new google.visualization.DataTable();
    	          //그래프에 표시할 컬럼 추가
    	          data.addColumn('datetime' , '날짜');
    	          data.addColumn('number'   , '결제금액');
    	          data.addColumn('number'   , '환불금액');
    	          data.addColumn('number'   , '순이익');
    	          //그래프에 표시할 데이터
    	          data.addRows([
    	        	  ${lineChart}
    	       		]);

    	            var chart = new google.visualization.ChartWrapper({
    	              chartType   : 'LineChart',
    	              containerId : 'lineChartArea', //라인 차트 생성할 영역
    	              options     : {
    	                              isStacked   : 'percent',
    	                              focusTarget : 'category',
    	                              height          : 500,
    	                              width              : '100%',
    	                              legend          : { position: "top", textStyle: {fontSize: 13}},
    	                              pointSize        : 5,
    	                              tooltip          : {textStyle : {fontSize:12}, showColorCode : true,trigger: 'both'},
    	                              hAxis              : {format: chartDateformat, gridlines:{count:chartLineCount,units: {
    	                                                                  years : {format: ['yyyy년']},
    	                                                                  months: {format: ['MM월']},
    	                                                                  days  : {format: ['dd일']},
    	                                                                  hours : {format: ['HH시']}}
    	                                                                },textStyle: {fontSize:12}},
    	                vAxis              : {minValue: 100,viewWindow:{min:0},gridlines:{count:-1},textStyle:{fontSize:12}},
    	                animation        : {startup: true,duration: 1000,easing: 'in' },
    	                annotations    : {pattern: chartDateformat,
    	                                textStyle: {
    	                                fontSize: 15,
    	                                bold: true,
    	                                italic: true,
    	                                color: '#871b47',
    	                                auraColor: '#d799ae',
    	                                opacity: 0.8,
    	                                pattern: chartDateformat
    	                              }
    	                            }
    	              }
    	            });
    	 
    	            var control = new google.visualization.ControlWrapper({
    	              controlType: 'ChartRangeFilter',
    	              containerId: 'controlsArea',  //control bar를 생성할 영역
    	              options: {
    	                  ui:{
    	                        chartType: 'LineChart',
    	                        chartOptions: {
    	                        chartArea: {'width': '60%','height' : 80},
    	                          hAxis: {'baselineColor': 'none', format: chartDateformat, textStyle: {fontSize:12},
    	                            gridlines:{count:controlLineCount,units: {
    	                                  years : {format: ['yyyy년']},
    	                                  months: {format: ['MM월']},
    	                                  days  : {format: ['dd일']},
    	                                  hours : {format: ['HH시']}}
    	                            }}
    	                        }
    	                  },
    	                    filterColumnIndex: 0
    	                }
    	            });
    	 
    	            var date_formatter = new google.visualization.DateFormat({ pattern: chartDateformat});
    	            date_formatter.format(data, 0);
    	 
    	            var dashboard = new google.visualization.Dashboard(document.getElementById('Line_Controls_Chart'));
    	            window.addEventListener('resize', function() { dashboard.draw(data); }, false); //화면 크기에 따라 그래프 크기 변경
    	            dashboard.bind([control], [chart]);
    	            dashboard.draw(data);
    	 
    	        }
    	          google.charts.setOnLoadCallback(drawDashboard);
    	 
    	      }
    	    }
    	 
    	$(document).ready(function(){
    	  google.charts.load('current', {'packages':['line','controls']});
    	  chartDrowFun.chartDrow(); //chartDrow() 실행
    	});


</script>
<style type="text/css">
#cl-dashboard {
	display: none;
}
</style>
</head>
<body>
	<div id="piechart_3d" style="width: 700px; height: 500px;"></div>
	<div id="columnchart_values" style="width: 900px; height: 500px;"></div>

	<div id="Line_Controls_Chart">
		<!-- 라인 차트 생성할 영역 -->
		<div id="lineChartArea" style="padding: 0px 20px 0px 0px;"></div>
		<!-- 컨트롤바를 생성할 영역 -->
		<div id="controlsArea" style="padding: 0px 20px 0px 0px;"></div>
	</div>

</body>
</html>