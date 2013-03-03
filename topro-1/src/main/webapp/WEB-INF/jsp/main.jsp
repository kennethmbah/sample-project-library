<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib tagdir="/WEB-INF/tags/ui" prefix="ui"%>
<%@ taglib tagdir="/WEB-INF/tags/js" prefix="js"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">

<ui:head title="Topic Proposer" />

<body ng-app="topro" ng-controller="AppController">

    <%-- EAGER DATA INITIALISATION --%>
    <%-- Use JSP templating avoiding addtional REST calls. See TopicsController() in controllers.js for details --%>

    <script type="text/javascript">
        function initTopics() {
            return <c:out value="${topics}" escapeXml="false"/>
        }
        function initProposalTypes() {
            return <c:out value="${proposalTypes}" escapeXml="false"/>
        }
    </script>


    <%-- TOP BAR --%>

    <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container">
                <a class="brand" href="${pageContext.request.contextPath}/res/pages/main">Lucubratory.eu - Topic Proposer v.1</a>
                <div class="navbar-form pull-right">
                </div>
            </div>
        </div>
    </div>

    <%-- BODY --%>

    <div class="container">

        <%-- Main message section --%>
        <div class="hero-unit">
            <div class="row-fluid">
                Propose your conference topics here...
            </div>
        </div>

        <%-- Row of columns zone --%>
        <div class="row-fluid" ng-controller="TopicsController">

            <div class="span4" >

                <h2>Propose...</h2>
                <div class="hero-unit">
                    <p>Title: <input type="text" ng-model="newTopic.title" maxlength="100" class="input-block-level"><p/>
                    <p>Summary: <textarea ng-model="newTopic.summary" rows="5" class="input-block-level" style="max-width: 260px;"></textarea><p/>
                    <p>Comment: <textarea ng-model="newTopic.comment" rows="5" class="input-block-level" style="max-width: 260px;"></textarea></p>
                    <p>
                        <div ng-repeat="ptype in proposalTypes">
                            <label class="radio inline">
                                <input type="radio" ng-model="newTopic.type" value="{{ptype.id}}">
                                {{ptype.label}}
                            </label>
                        </div>
                    </p>
                    <a class="btn" href="#" ng-click="submitTopic()">Submit</a>
                </div>

            </div>

            <div class="span4">

                <h2>Proposed Topics</h2>
                <div ng-repeat="topic in topics" class="hero-unit">
                    <p style="font-size: 18px; font-style: oblique; font-weight:bold;">{{topic.title}}<p/>
                    <p>{{topic.summary}}<p/>
                    <p>{{topic.proposalDate | date:'yyyy-MM-dd'}}<p/>
                    <p style="font-size: 9px; font-style: oblique; ">{{topic.comment}}</p>
                    <p style="font-weight:bold;">{{topic.type | pronounce:proposalTypes}}</p>
                </div>

            </div>

            <div class="span4">
                <!-- Placeholder for later -->
            </div>
        </div>

        <%-- FOOTER (inside the container as part of the scrolling page) --%>

        <hr>
        <footer>
            <p><small><center>
                Blog Project - (cc) 2013 at <a href="http://lucubratory.eu" target="_blank">http://lucubratory.eu</a><br/>
                Made with Bootstrap (obviously), Angular JS (&lt;3), JAX-RS/Jersey, JSP/JSTL for templating (yepp!), Java EE 6 stuff<br/>
                Code is FOSS.
            </center></small></p>
        </footer>

    </div>
    <%-- /container --%>

    <%-- Load JS at the end of the document so the pages load faster --%>
    <js:main />

</body>
</html>
