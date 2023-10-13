$(function(){
	
	// User3
	$('#btnUser3s').click(function(){
		$.ajax({
			url: '/Ch10/user3',
			type: 'GET',
			dataType: 'json',
			success: function(data) {
				console.log(data);
				alert(JSON.stringify(data));
			}
		});
	});
	
	$('#btnUser3').click(function(){
		$.ajax({
			url: '/Ch10/user3/a102',
			type: 'GET',
			dataType: 'json',
			success: function(data) {
				console.log(data);
				alert(JSON.stringify(data));
			}
		});
	});
	
	$('#btnUser3Register').click(function(){
		
		const jsonData = {
			id: 's101',
			name: '홍길동',
			hp: '010-1111-1001',
			age: 22
		};
		
		$.ajax({
			url: '/Ch10/user3',
			type: 'POST',
			data: jsonData,
			dataType: 'json',
			success: function(data) {
				console.log(data);
				alert(JSON.stringify(data));
			}
		});
	});
	
	$('#btnUser3Modify').click(function(){
		
		const jsonData = {
			id: 's101',
			name: '홍길동',
			hp: '010-1111-1001',
			age: 24
		};
		
		$.ajax({
			url: '/Ch10/user3',
			type: 'PUT',
			data: jsonData,
			dataType: 'json',
			success: function(data) {
				console.log(data);
				alert(JSON.stringify(data));
			}
		});
	});
	
	$('#btnUser3Delete').click(function(){
		$.ajax({
			url: '/Ch10/user3/s101',
			type: 'DELETE',
			dataType: 'json',
			success: function(data) {
				console.log(data);
			}
		});
	});
});