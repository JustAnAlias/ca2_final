/**
 * Created by Sandeep on 01/06/14.
 */

angular.module('personApp.services',[]).factory('Person',function($resource){
    return $resource('https://ca2-cborum.rhcloud.com/CA2/api/person/:id',{id:'@_id'},{
        update: {
            method: 'PUT'
        }
    });
}).service('popupService',function($window){
    this.showPopup=function(message){
        return $window.confirm(message);
    }
});