'use strict';

angular.module('registrationApp')
    .factory('errorHandlerInterceptor', function ($q, $rootScope) {
        return {
            'responseError': function (response) {
                if (!(response.status == 401 && response.data.path.indexOf("/api/account") == 0 )){
	                $rootScope.$emit('registrationApp.httpError', response);
	            }
                return $q.reject(response);
            }
        };
    });