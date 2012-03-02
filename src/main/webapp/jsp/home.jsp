<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
%>

<%@ include file="/jsp/init.jsp"%>

<jsp:useBean id="statistics" type="java.util.List<java.lang.String>" scope="request" />

<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
	google.load("visualization", "1", {packages:["corechart"]});
	google.setOnLoadCallback(drawChart);
	function drawChart() {
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'Date');
		data.addColumn('number', 'Finished');
		data.addColumn('number', 'Error');
		data.addColumn('number', 'Running');
		data.addRows([
		<%
		for (String string : statistics) {
			String[] tokens = string.split(";");
			out.println("[\'"+tokens[0]+"\', "+tokens[1]+", "+tokens[2]+", "+tokens[3]+"],");
		}
		%>
		]);
		
		var options = {
			title: 'Job Statistics'
		};
		
		var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
		chart.draw(data, options);
	}
</script>


<div id="chart_div"></div>