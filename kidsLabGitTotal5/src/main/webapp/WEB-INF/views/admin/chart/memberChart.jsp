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
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.5/jspdf.min.js"></script>
<script type="text/javascript">
	// Pie 차트
	// n이 한자리 수가되면 앞에 0을 추가하는 함수
	function addzero(n) {
      return n < 10 ? "0" + n : n;
   }
	google.charts.load("current", {
		packages : [ "corechart" ]
	});
	
	google.charts.setOnLoadCallback(drawChart);

	function drawChart() {
		var data = google.visualization.arrayToDataTable([
			${rootList}
		]);
		var now = new Date();
	    var nowDate = now.getFullYear() + "-" + addzero((now.getMonth() + 1)) + "-" + addzero(now.getDate()) + " " + addzero(now.getHours());

		var options = {
			title : '알게된 경로 (' + nowDate + '시)',
			is3D : true,
		};

		var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
		chart.draw(data, options);
		
		var content = '<img src="' + chart.getImageURI() + '">'; 
		$('#graph-images').append(content);
	}
	
	// Bar 차트
	google.charts.load("current", {packages:['corechart']});
    google.charts.setOnLoadCallback(drawBarChart);
    function drawBarChart() {
    	var now = new Date();
	    var nowDate = now.getFullYear() + "-" + addzero((now.getMonth() + 1)) + "-" + addzero(now.getDate()) + " " + addzero(now.getHours());
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
        title: "학년 별 인원 (" + nowDate + ")시",
        width: 600,
        height: 400,
        bar: {groupWidth: "95%"},
        legend: { position: "none" },
      };
      var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
      chart.draw(view, options);
      var content = '<img src="' + chart.getImageURI() + '">'; 
		$('#graph-images').append(content);
      }
    
    // PDF 용 라인차트
    google.charts.load('current', {packages: ['corechart', 'line']});
	google.charts.setOnLoadCallback(drawAxisTickColors);
    var chartDateformat     = 'yy년MM월';
  	//라인차트의 라인 수
    var chartLineCount    = 10;
	function drawAxisTickColors() {
    	var data = new google.visualization.DataTable();
    	data.addColumn('datetime' , '날짜');
        data.addColumn('number'   , '결제금액');
        data.addColumn('number'   , '환불금액');
        data.addColumn('number'   , '순이익');

     
        data.addRows([
       		${lineChart}
        ]);

        
      var options = {
              isStacked   : 'percent',
              focusTarget : 'category',
              height          : 500,
              width              : '100%',
              legend          : { position: "top", textStyle: {fontSize: 9}},
              pointSize        : 5,
              tooltip          : {textStyle : {fontSize:12}, showColorCode : true,trigger: 'both'},
              hAxis              : {format: chartDateformat, gridlines:{count:chartLineCount,units: {
                                                  years : {format: ['yyyy년']},
                                                  months: {format: ['MM월']},
                                                  days  : {format: ['dd일']},
                                                  hours : {format: ['HH시']}}
                                                },textStyle: {fontSize:11}},
			vAxis              : {minValue: 100,viewWindow:{min:0},gridlines:{count:-1},textStyle:{fontSize:11}},
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
      };
      var date_formatter = new google.visualization.DateFormat({ pattern: chartDateformat});
      date_formatter.format(data, 0);
      var chart = new google.visualization.LineChart(document.getElementById('line_chart'));
      chart.draw(data, options);
      var content = '<img src="' + chart.getImageURI() + '">'; 
		$('#graph-images').append(content);
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
    	 
    	        google.charts.setOnLoadCallback(drawDashboard);
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
    	                        chartArea: {'width': '60%','height' : '100%'},
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
    	      }
    	    }
    	$(document).ready(function(){
    	  google.charts.load('current', {'packages':['line','controls']});
    	  chartDrowFun.chartDrow(); //chartDrow() 실행
    	});
    /* 차트 이미지 PDF화 함수 */
   $(function() {
      $('#chartToPdf').click(
            function() {
               var doc = new jsPDF('p', 'pt', 'a4', false); // 새로운 문서 생성
               doc.setFontSize(15); //문서의 폰트사이즈
               var xAxis = 50;
               var yAxis = 70;
               var imageTags = $("#graph-images img");
               console.log(imageTags);
               for (var i = 0; i < imageTags.length; i++) {
            	   var someText = 'StatsChart' + (i + 1);
            	   if(i<2) {
                       doc.text(xAxis + 60, yAxis, someText);
                       doc.addImage(imageTags[i], 'png', xAxis, yAxis, 300, 300,
                               undefined, 'none');
                         xAxis = xAxis + 250;
            	   } else if(i == 2) {
            		   xAxis = 280;
            		   doc.text(xAxis , yAxis + 300, someText);
            		   yAxis = yAxis + 300;  
                 	  doc.addImage(imageTags[i], 'png', 0, yAxis, 650, 420,
                               undefined, 'none');  
            	   }
               }
               doc.save('memberChart.pdf');
            });
   });

</script>
<style type="text/css">
#cl-dashboard {
	display: none;
}

#first {
	width: 100%;
	float: left;
}

#piechart_3d {
	width: 50%;
	height: 500px;
	float: left;
	padding-left: 5%;
}

#columnchart_values {
	width: 50%;
	height: 500px;
	float: right;
}
</style>
</head>
<body>
	<div>
		<div id="first">
			<div id="piechart_3d"></div>
			<div id="columnchart_values"></div>
		</div>

		<div id="Line_Controls_Chart" style="width: 100%; float: right;">
			<!-- 라인 차트 생성할 영역 -->
			<div id="lineChartArea" style="padding: 0px 20px 0px 0px;"></div>
			<!-- 컨트롤바를 생성할 영역 -->
			<div id="controlsArea" style="padding: 0px 20px 0px 0px;"></div>
		</div>

		<div>
			<div id="line_chart"></div>
		</div>

		<div id="graph-images" hidden=""></div>
		<div>
			<input type="button" id="chartToPdf" value="PDF저장하기">
		</div>
	</div>
</body>
</html>