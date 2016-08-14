'use strict';

angular.module('registrationApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


