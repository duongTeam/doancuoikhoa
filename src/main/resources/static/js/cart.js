$(document).ready(function () {
    function checkMail(form) {
        var controlNumber = 0;
        if(form === 'sendMail') {
            if($('#contact-name').val()==="") {
                $('#contact-name').parent().find('.alert').show();
                controlNumber ++;
            }
            if($('#contact-email').val() === "" || validateEmail($('#contact-email').val()) === false) {
                $('#contact-email').parent().find('.alert').show();
                controlNumber++;
            }
            if($('#contact-message').val() === "") {
                $('#contact-message').parent().find('.alert').show();
                controlNumber++;
            }
        }
        return controlNumber;
    }

    function validateEmail(email) {
        var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    }

    $('#sendMailButton').on('click',function () {
        if(checkMail($('form#sendMail').attr('id')) === 0) {
            $('.modal-footer button').hide();
            $('.modal-body').find('form').hide().end()
                .find('form input').html('').end()
                .find('#thanks').show();
        }
        return false;
    })
})