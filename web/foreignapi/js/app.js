/**
 * Created by Sandeep on 01/06/14.
 */

angular.module('personApp',
    ['ui.router','ngResource','personApp.controllers','personApp.services']);

angular.module('personApp').config(function($stateProvider,$httpProvider){
    $stateProvider.state('persons',{
        url:'/persons',
        templateUrl:'test/partials/persons.html',
        controller:'PersonListController'
    }).state('viewPerson',{
       url:'/persons/:id/view',
       templateUrl:'test/partials/person-view.html',
       controller:'PersonViewController'
    }).state('newPerson',{
        url:'/persons/new',
        templateUrl:'test/partials/person-add.html',
        controller:'PersonCreateController'
    }).state('editPerson',{
        url:'/persons/:id/edit',
        templateUrl:'test/partials/person-edit.html',
        controller:'PersonEditController'
    });
}).run(function($state){
   $state.go('persons');
});