<#import "skysail.server.restletosgi:dump.ftl" as dumper>
<#assign foo = data />

<html>
<head>
	<title>Skysail Server</title>
    <#include "skysail.server.restletosgi:style.css">
</head>
<body>

<h1>Skysail RestletOsgi Server - Menu</h1>

<#include "skysail.server.restletosgi:navigation.ftl">

<h2>Message</h2>
${message}

<h2>Data</h2>

<#list data as component>
  
  <#if component.class.simpleName == "GridData">
	<#assign columns = component.columns>
	<form action="#">
	<table>
	<tr>
	  <#list columns as column>
		<th>${column.columnName}</th>  
	  </#list>
	  <th>Columns</th>
	  <th>Data</th>
	</tr>
    <#list component.gridData as row>
	  <#assign columns = row.columnData>
	  <tr>
	    <#list columns as columnData>
		<td>
		  <#if columnData_index == 0>
		      <a href='${columnData}/'>${columnData}</a>
		      <#assign name = columnData />
		  <#else>
			  ${columnData}
		  </#if>
		</td>
		</#list>
		<td>
		      <a href='${name}/columns/'>show</a>
		</td>
		<td>
		      <a href='${name}/data/'>show</a>
		</td>
	  </tr>  
	</#list>
	
	</table>  
	</form>
  </#if>  

</#list>

<#include "skysail.server.restletosgi:debug.ftl">

</body>
</html>