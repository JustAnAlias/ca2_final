var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
    
    $scope.firstName = "John";
    $scope.lastName = "Doe";
    console.log('1yay');
    $scope.allUsers = null;
    $http.get('api/search/person/all')
        .success(function(data) {
            $scope.allUsers=data;
                    console.log(data);
        })
        .error(function(data,status,error,config){
            $scope.allUsers = {firstName:"Error",lastName:"Could not load json data"};
        });
    $scope.getFullName = function(){
      return $scope.firstName + " " + $scope.lastName; 
    };
//    $scope.getAllUsers = function(){
//        console.log('yay');
//    
//    };
});


var getPeople = function(){
        var persons = [];
        $.ajax({
//            data: input,
//            dataType: 'json',
            type: 'get',
            url: 'api/search/person/all',
            success: function (result, status, xhr) {
                persons = JSON.stringify(result);
                
                console.log(persons);
            }

        });
        return persons;
    };
    
    var aNewFunction = function(){
      console.log('hello');  
    };