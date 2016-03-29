var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope) {
    $scope.firstName= "John";
    $scope.lastName= "Doe";
    $scope.getFullName = function(){
      return $scope.firstName + " " + $scope.lastName; 
    };
});
var newApp = angular.module('myNewApp', []);
newApp.controller('getAllUsers', function($scope, $http) {
    $http.get('api/search/person/1')
    .then(function(response) {
        $scope.allUsers = response.data;
                console.log($scope.allUsers);
    });
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