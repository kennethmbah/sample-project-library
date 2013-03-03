'use strict';

/* Services */
/* -------- */

/**
 * Creates a new service provider i.e. a factory, actually 'TopicsProvider', so it can be injected into 
 * controllers by simply specifying the 'Topics' parameter. We choose to define the service in an idependent 
 * module, but we could also have had added the provider/factory to the 'topro' module.
 */
angular.module('topicsServices', ['ngResource']).
    factory('Topics',function($resource,AppContext){
        // Returns the topics rest resource, replacing POST by PUT on the update operation.
        // All other methods stay on the default behaviour.
        // http://docs.angularjs.org/api/ngResource.$resource
        return $resource(AppContext+'/res/topics/:id', {}, { update: {method:'POST'} });
    });

