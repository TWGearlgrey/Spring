$(function(){
	
	// User2
	$('#btnUser2s').click(function(){
		$.ajax({
			url: '/Ch10/user2',
			type: 'GET',
			dataType: 'json',
			success: function(data) {
				console.log(data);
				alert(JSON.stringify(data));
			}
		});
	});
	
	$('#btnUser2').click(function(){
		$.ajax({
			url: '/Ch10/user2/a102',
			type: 'GET',
			dataType: 'json',
			success: function(data) {
				console.log(data);
				alert(JSON.stringify(data));
			}
		});
	});
	
	$('#btnUser2Register').click(function(){
		
		const jsonData = {
			id: 's101',
			name: '홍길동',
			hp: '010-1111-1001',
			age: 22
		};
		
		$.ajax({
			url: '/Ch10/user2',
			type: 'POST',
			data: jsonData,
			dataType: 'json',
			success: function(data) {
				console.log(data);
				alert(JSON.stringify(data));
			}
		});
	});
	
	$('#btnUser2Modify').click(function(){
		
		const jsonData = {
			id: 's101',
			name: '홍길동',
			hp: '010-1111-1001',
			age: 24
		};
		
		$.ajax({
			url: '/Ch10/user2',
			type: 'PUT',
			data: jsonData,
			dataType: 'json',
			success: function(data) {
				console.log(data);
				alert(JSON.stringify(data));
			}
		});
	});
	
	$('#btnUser2Delete').click(function(){
		$.ajax({
			url: '/Ch10/user2/s101',
			type: 'DELETE',
			dataType: 'json',
			success: function(data) {
				console.log(data);
			}
		});
	});
});