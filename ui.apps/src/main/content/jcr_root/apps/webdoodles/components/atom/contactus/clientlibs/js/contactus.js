$('#send-message-btn').on('click', function(e) {
	e.preventDefault();

    $.ajax({
        url : '/content/webdoodles/en/jcr:content/root/responsivegrid/contactus.json',
        type: "POST",
        data: $('#contact-form').serialize(),
        success: function(msg) {
            alert(msg);
        },
        error: function() {
            alert('failed');
        }
    });
});