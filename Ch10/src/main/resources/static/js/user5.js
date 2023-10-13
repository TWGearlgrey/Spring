$(function(){
	
	// User5
	$('#btnUser5s').click(function(){
		$.ajax({
			url: '/Ch10/user5',
			type: 'GET',
			dataType: 'json',
			success: function(data) {
				console.log(data);
				alert(JSON.stringify(data));
			}
		});
	});
	
	$('#btnUser5').click(function(){
		$.ajax({
			url: '/Ch10/user5/a102',
			type: 'GET',
			dataType: 'json',
			success: function(data) {
				console.log(data);
				alert(JSON.stringify(data));
			}
		});
	});
	
	$('#btnUser5Register').click(function(){
		
		const jsonData = {
			id: 's101',
			name: '홍길동',
			gender: 'M',
			age: 22,
			addr: '서울시'
		};
		
		$.ajax({
			url: '/Ch10/user5',
			type: 'POST',
			data: jsonData,
			dataType: 'json',
			success: function(data) {
				console.log(data);
				alert(JSON.stringify(data));
			}
		});
	});
	
	$('#btnUser5Modify').click(function(){
		
		const jsonData = {
			id: 's101',
			name: '홍길동',
			gender: 'M',
			age: 24,
			addr: '서울시'
		};
		
		$.ajax({
			url: '/Ch10/user5',
			type: 'PUT',
			data: jsonData,
			dataType: 'json',
			success: function(data) {
				console.log(data);
				alert(JSON.stringify(data));
			}
		});
	});
	
	$('#btnUser5Delete').click(function(){
		$.ajax({
			url: '/Ch10/user5/s101',
			type: 'DELETE',
			dataType: 'json',
			success: function(data) {
				console.log(data);
			}
		});
	});
});