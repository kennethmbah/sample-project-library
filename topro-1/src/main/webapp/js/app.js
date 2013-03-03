'use strict';

// Initialises the angular app called 'topro' as declared in ng-app in main.jsp. Injects the Angular ngResouces
// module and our own services. We also configure our route provide which remains empty since we don't use 
// ng-view's yet.
var app=angular.module('topro', ['ngResource','topicsServices']);



/**
 * Configure the routes.
 */
app.config(['$routeProvider', function($routeProvider) {
    // nop - we don't use routes and views in this application (yet)
}]);



/**
 * Register an AppContext provider that allows the AppContext object to be injected providing the 
 * injectee with the URL application context to use for REST calls.
 */
app.factory('AppContext',function(){
    var AppContext = '/topro-1';
    return AppContext;
    // This is not the optimal solution. Better would be to get the from the JSP context path to avoid
    // manual configuration in the Javascript. We will solve this issue later.
});