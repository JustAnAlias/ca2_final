

var addPerson = function () {
//    var formData = JSON.stringify($("#userForm").serializeArray());
    var person2 = {
        "firstName" : document.getElementById('firstName').value,
        "lastName" : document.getElementById('lastName').value,
        "email" : document.getElementById('email').value,
        "phones" : [{
            "number" : document.getElementById('phoneNumber').value,
            "description" : document.getElementById('phoneDescription').value
        }]
        };
    

    $.ajax({
        type: "POST",
        url: "api/search/",
        data: JSON.stringify(person2),
        success: function () {},
        dataType: "json",
        contentType: "application/json"
    });
};