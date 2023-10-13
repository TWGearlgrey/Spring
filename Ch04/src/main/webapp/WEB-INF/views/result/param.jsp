<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>result::Param</title>
	</head>
	<body>
		<h3>@RequestParam Annotation 결과</h3>
		
		<a href="/Ch04/">index</a>
		
		<h4>Param1 결과</h4>
		<p>
			uid : ${uid}
		</p>
		
		<h4>Param2 결과</h4>
		<p>
			uid  : ${uid}<br>
			name : ${name}<br>
		</p>
		
		<h4>Param3 결과</h4>
		<p>
			uid  : ${uid}<br>
			name : ${name}<br>
			hp   : ${hp}<br>
			age  : ${age}<br>
		</p>
	</body>
</html>