$(function(){
	
	var id = 0;
	
	// User6
	$('#btnUser6s').click(function(){
		$.ajax({
			url: '/Ch10/user6',
			type: 'GET',
			dataType: 'json',
			success: function(data) {
				console.log(data);
				alert(JSON.stringify(data));
			}
		});
	});
	
	$('#btnUser6').click(function(){
		$.ajax({
			url: '/Ch10/user6/1',
			type: 'GET',
			dataType: 'json',
			success: function(data) {
				console.log(data);
				alert(JSON.stringify(data));
			}
		});
	});
	
	$('#btnUser6Register').click(function(){
		
		const jsonData = {
			name: '홍길동',
			gender: 'M',
			age: 22,
			addr: '서울시'
		};
		
		$.ajax({
			url: '/Ch10/user6',
			type: 'POST',
			data: jsonData,
			dataType: 'json',
			success: function(data) {
				console.log(data);
				alert(JSON.stringify(data));
				
				id = data.seq;
				console.log('id : ' + id);
			}
		});
	});
	
	$('#btnUser6Modify').click(function(){
		
		console.log('id : ' + id);
		
		const jsonData = {
			seq: id,
			name: '홍길동',
			gender: 'M',
			age: 25,
			addr: '서울시'
		};
		
		$.ajax({
			url: '/Ch10/user6',
			type: 'PUT',
			data: jsonData,
			dataType: 'json',
			success: function(data) {
				console.log(data);
				alert(JSON.stringify(data));
			}
		});
	});
	
	$('#btnUser6Delete').click(function(){
		
		url = '/Ch10/user6/' + id;
		console.log(url);
		
		$.ajax({
			url: url,
			type: 'DELETE',
			dataType: 'json',
			success: function(data) {
				console.log(data);
			}
		});
	});
});