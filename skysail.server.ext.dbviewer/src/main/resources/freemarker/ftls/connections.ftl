<#import "skysail.server.restletosgi:dump.ftl" as dumper>
<#assign foo = data />

<html>
<head>
	<title>Skysail Server</title>
    <#include "skysail.server.restletosgi:style.ftl">
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
	  <th>Maven Project</th>
	</tr>
	<tr>
	  <#list columns as column>
		<td>
		<#if column.columnName == "symbolicName">
		<input type="text" name="filterSymbolicName" value="skysail" />
		<input type="submit" value="filter"/>
		</#if>
		</td>  
	  </#list>
	  <td>&nbsp;</td>
	</tr>
    <#list component.gridData as row>
	  <#assign columns = row.columnData>
	  <tr>
	    <#list columns as columnData>
		<td>
		  <#if columnData_index == 0>
		      <a href='${columnData}/'>${columnData}</a>
		  <#else>
			  ${columnData}
		  </#if>
		</td>
		</#list>
		<td><#if columns[1]?starts_with("skysail.")>
			<a href='http://www.evandor.de:8787/job/${columns[1]}/' target='_blank'>project</a>&nbsp;
			<a href='http://www.evandor.de:8787/job/${columns[1]}/site/${columns[1]}/' target='_blank'>site</a>&nbsp;			
			<a href='http://www.evandor.de:9000/dashboard/index/de.evandor:${columns[1]}' target='_blank'>Sonar</a>&nbsp;			
			</#if>
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