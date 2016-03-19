

var updatePerson = function () {
//    var formData = JSON.stringify($("#userForm").serializeArray());
    var person = {
        "firstName" : document.getElementById('firstName').value,
        "lastName" : document.getElementById('lastName').value,
        "email" : document.getElementById('email').value,
        "phones" : [{
            "number" : document.getElementById('phoneNumber').value,
            "description" : document.getElementById('phoneDescription').value
        }]
        };
    

    $.ajax({
        type: "PUT",
        url: "api/search/",
        data: JSON.stringify(person),
        success: function () {},
        dataType: "json",
        contentType: "application/json"
    });
};