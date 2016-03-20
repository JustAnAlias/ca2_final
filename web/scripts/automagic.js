/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

document.getElementById("automagic").addEventListener("keyup", function(){
    var input = document.getElementById('automagic').value;
    if (input.length > 1){
        $.ajax({
           data: input,
           dataType: 'json',
           type: 'get',
           url: '/api/search/',
           success: function(result, status, xhr){
                console.log(result);
           }
           
        });
    }
    
});

var addCompany = function () {
//    var formData = JSON.stringify($("#userForm").serializeArray());
    var company = {
        "companyName" : document.getElementById('companyName').value,
        "cvr" : document.getElementById('cvr').value,
        "description" : document.getElementById('description').value,
        "numEmployees" : document.getElementById('numEmployees').value,
        "marketValue" : document.getElementById('marketValue').value
        };
    

    $.ajax({
        type: "POST",
        url: "api/search/",
        data: JSON.stringify(company),
        success: function () {},
        dataType: "json",
        contentType: "application/json"
    });
};

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

//document.addEventListener("click", function(){
//    document.getElementById("automagic").value = "Hello World";
//});

var ajaxCall = function(input){
    
};

//var query = function(data){
//    var search_word = {};
//    $.ajax({
//	type: "GET",
//    url: "/api/city/zip/" + data,
//    data: search_word,
//    cache: false,
//    beforeSend: function(html) {
//   
//	document.getElementById("insert_search").innerHTML = ''; 
//	$("#flash").show();
//	$("#searchword").show();
//	$(".searchword").html(data);
//	$("#flash").html('<img src="ajax-loader.gif" align="absmiddle">&nbsp;Loading Results...');
//	               
//            },
//    success: function(html){
//   $("#insert_search").show();
//   $("#insert_search").append(html);
//   $("#flash").hide();
//  }
//});
//};
//

