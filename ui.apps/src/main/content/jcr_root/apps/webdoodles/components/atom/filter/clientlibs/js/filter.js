$('.filter-dropdown').on('change', function() {
    $.ajax({
        url : '/bin/webdoodles/filter?tag='+$(this).val(),
        type: "GET",
        dataType: 'json',
        success: function(msg) {
        	var html = "";
        	$.each(msg, function(index, msgItem) {
				//html = html + '<div class="col-md-4"><img class="img-fluid" src="'+ msgItem.path+'"/><p>'+msgItem.name+'</p></div>';
        		html = html + '<div class="col-lg-4 col-md-12 col-sm-12"><div class="card"><div class="card m-2">' +
        					   '<img class="card-img-top mt-2" src="'+ msgItem.path +'"/><div class="card-body">' +
        					   '<h5 class="card-title font-weight-bold display-5">'+ msgItem.name +'</h5>'+
        					   '</div></div></div></div>'
    		});
            $('.filter-result').html(html);
        },
        error: function() {
            alert('failed');
        }
    });
})