<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>result::Model</title>
	</head>
	<body>
		<h3>@ModelAttribute Annotation 결과</h3>
		
		<a href="/Ch04/">index</a>
		
		<h4>Model1 결과</h4>
		<p>
			uid : ${modelDTO.uid}
		</p>
		
		<h4>Model2 결과</h4>
		<p>
			uid  : ${user.uid}<br>
			name : ${user.name}<br>
		</p>
		
		<h4>Model3 결과</h4>
		<p>
			uid  : ${modelDTO.uid}<br>
			name : ${modelDTO.name}<br>
			hp   : ${modelDTO.hp}<br>
			age  : ${modelDTO.age}<br>
		</p>
	</body>
</html>