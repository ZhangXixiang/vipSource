<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<div class="list">
	<p class="personalInfor"><i></i><span id="loginInfor"></span>
	<%= session.getAttribute("userName")%>
	</p>
	<ul class="leftMenu" id="LiftMenuContent"></ul>
	
</div>