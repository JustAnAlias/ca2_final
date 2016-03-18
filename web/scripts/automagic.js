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

