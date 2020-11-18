<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>visualization</title>

<!-- Styles -->
<style>
#chartdiv {
  width: 100%;
  height: 500px;
}
#chartdiv1 {
  width: 100%;
  height: 500px;
}
#chartdiv2 {
  width: 100%;
  height: 500px;
}

</style>


<!-- Resources -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.amcharts.com/lib/4/core.js"></script>
<script src="https://cdn.amcharts.com/lib/4/charts.js"></script>
<script src="https://cdn.amcharts.com/lib/4/themes/material.js"></script>
<script src="https://cdn.amcharts.com/lib/4/themes/animated.js"></script>

<!-- Chart code -->
<script>
var cate = '<%= request.getParameter("views")%>'

$(function() {
	
	if(cate == 'people'){
		cate = 'kids';
	}else if(cate == 'music'){
		cate = 'cooking';
	}
	
	$('#reco').append("<h1>추천 카테고리 : <a href='#' onclick='moving()'>"+cate+"</a>(으)로 이동</h1>")
}	
)


function moving(){
	
	location.href=cate+"_index.jsp";
}
am4core.ready(function() {
	
	am4core.useTheme(am4themes_material);
	am4core.useTheme(am4themes_animated);

var chartData = {
  "남자": [
    { "sector": "Game", "size": 1.5 },
    { "sector": "Kids", "size": 1.2 },
    { "sector": "Sports", "size": 1.3 },
    { "sector": "cook", "size": 1 },
     ]

};

// Create chart instance
var chart = am4core.create("chartdiv", am4charts.PieChart);

// Add data
chart.data = [
    { "sector": "Game", "size": 1.5 },
    { "sector": "Kids", "size": 1.2 },
    { "sector": "Sports", "size": 1.3 },
    { "sector": "cook", "size": 1 },
     ];

// Add label
chart.innerRadius = 100;
var label = chart.seriesContainer.createChild(am4core.Label);
label.text = "남자";
label.horizontalCenter = "middle";
label.verticalCenter = "middle";
label.fontSize = 50;

// Add and configure Series
var pieSeries = chart.series.push(new am4charts.PieSeries());
pieSeries.dataFields.value = "size";
pieSeries.dataFields.category = "sector";




//==============================================================여자

var chartData1 = {
	  "여자": [
	    { "sector": "Game", "size": 0.8 },
	    { "sector": "Kids", "size": 1.2 },
	    { "sector": "Sports", "size": 0.3 },
	    { "sector": "cook", "size": 2 },
	     ]

};

	// Create chart instance
var chart1 = am4core.create("chartdiv1", am4charts.PieChart);

// Add data
chart1.data = [
    { "sector": "Game", "size": 0.8 },
    { "sector": "Kids", "size": 1.2 },
    { "sector": "Sports", "size": 0.3 },
    { "sector": "cook", "size": 2 },
     ];

// Add label
chart1.innerRadius = 100;
var label1 = chart1.seriesContainer.createChild(am4core.Label);
label1.text = "여자";
label1.horizontalCenter = "middle";
label1.verticalCenter = "middle";
label1.fontSize = 50;

// Add and configure Series
var pieSeries1 = chart1.series.push(new am4charts.PieSeries());
pieSeries1.dataFields.value = "size";
pieSeries1.dataFields.category = "sector";


// ====================================================== 장르별 조회수

var chart2 = am4core.create("chartdiv2", am4charts.XYChart3D);

//Add data
chart2.data = [{
"category": "Game",
"views": 1500,
"color": chart.colors.next()
}, {
"category": "Kids",
"views": 2000,
"color": chart.colors.next()
}, {
"category": "Cooking",
"views": 2500,
"color": chart.colors.next()
}, {
"category": "Sports",
"views": 1000,
"color": chart.colors.next()
}];

//Create axes
var categoryAxis = chart2.yAxes.push(new am4charts.CategoryAxis());
categoryAxis.dataFields.category = "category";
categoryAxis.renderer.inversed = true;

var  valueAxis = chart2.xAxes.push(new am4charts.ValueAxis()); 

//Create series
var series = chart2.series.push(new am4charts.ColumnSeries3D());
series.dataFields.valueX = "views";
series.dataFields.categoryY = "category";
series.name = "views";
series.columns.template.propertyFields.fill = "color";
series.columns.template.tooltipText = "조회수 : {valueX}";
series.columns.template.column3D.stroke = am4core.color("#fff");
series.columns.template.column3D.strokeOpacity = 0.2;

}); // end am4core.ready()

</script>



</head>
<body>
<!-- HTML -->
<div id = "reco"></div>
<div id="chartdiv"></div>
<div id="chartdiv1"></div>
<div id="chartdiv2"></div>

</body>
</html>