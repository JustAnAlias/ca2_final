//var addPerson = function () {
//    var person = {};
//    person.firstName = document.getElementById('firstName').value;
//    person.lastName = document.getElementById('lastName').value;
//    person.email = document.getElementById('email').value;
//    person.phone = {number: document.getElementById('phoneNumber').value, description: document.getElementById('phoneDescription').value};
////  person.address = {street : document.getElementById('street'), additionalInfo : document.getElementById('additionalInfo')};
//    console.log(JSON.stringify(person));
//    $.ajax({
//        type: "POST",
//        url: 'api/search/',
//        data: JSON.stringify(person),
//        success: function (result, status, xhr) {
//            console.log(result);},
//        dataType: "json",
//        contentType: "application/json",
//        complete: function (jqXHR, textStatus) {
//            console.log('TRANSFER COMPLETE. MAYBE WITH ERRORS.. WHO KNOWS?');
//        }
//    });
//
//  person.firstName = document.getElementById('firstName');
//
//};


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
    


//    var person = {};
//    person.firstName = document.getElementById('firstName').value;
//    person.lastName = document.getElementById('lastName').value;
//    person.email = document.getElementById('email').value;
//    person.phone = {"number": document.getElementById('phoneNumber').value, "description": document.getElementById('phoneDescription').value};
////  person.address = {street : document.getElementById('street'), additionalInfo : document.getElementById('additionalInfo')};
//    console.log(JSON.stringify(person));
    
    $.ajax({
        type: "POST",
        url: "api/search/",
        data: JSON.stringify(person2),
        success: function () {},
        dataType: "json",
        contentType: "application/json"
    });
};