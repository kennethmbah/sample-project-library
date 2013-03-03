'use strict';


/* Controllers */
/* ----------- */

/*
 * Handles global things within the module (application),
 * especially failed pre-controller promises and application global configuration.
 * All scopes of sub-controllers with inherit this scope. Avoids polluting
 * the $rootScope btw.
 */
app.controller("AppController",function AppController($scope) {

    // Configuration available in all sub-scopes. (See initTopics() below)
    $scope.proposalTypes=initProposalTypes();
});



/*
 * Handles all operations around manipulating topics.
 *
 * '$scope','$resource' and 'Topics' are injected based on the name. Dependency Injection in weakly
 * typed programming languages is made based on the name and not the type. When creating the controller,
 * AngularJS looks for a factory with the name 'xxxProvider' that are either provided by AngularJS like for
 * '$scope' or by our own services like for 'Topics'.
 */
app.controller("TopicsController",function TopicsController($scope,$resource,Topics) {

    // Resets the 'new topic' object and form
    $scope.resetNewTopic=function() {
        $scope.newTopic={"title":"",
                         "summary":"",
                         "comment":"",
                         "type":$scope.proposalTypes[0].id};
    }


    // Submits a new topic to the server using a REST call.
    // Doc: It is important to realize that invoking a $resource object method immediately returns an empty
    //      reference. Once the data is returned from the server the existing reference is populated with the actual data.
    $scope.submitTopic=function() {
        $scope.newTopic.proposalDate=new Date().getTime(),
        Topics.save(
            $scope.newTopic,
            function(request) { // OK
                $scope.topics.push($scope.newTopic);
                $scope.resetNewTopic();
            },
            function(request) { // Error
                alert("Sorry, REST submission to server failed!");
            });

        // Other operations: Topics.query(); or Topics.get({id:33}); et al
    }

    // We call a pre-generated Javascript function to load the data
    // from a list of eagerly generated objects that is in fact a snapshot
    // of the data at page loading time. In pure AngularJS/REST, we
    // would have to make a REST call to the server to get the resource.
    // We use the JSP templating to avoid this additional call, simplifying
    // error handling (fail fast) and doing all in one single transaction
    // on the server. This is especially useful when we have 20 different
    // controls to populate with reference data, other than the
    // main business objects. No error handling on client-side is not needed too.
    $scope.topics=initTopics();

    $scope.resetNewTopic();

}); // TopicsController



