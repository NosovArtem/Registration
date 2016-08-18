angular.module('registrationApp')
    .factory('ListDoctor', function ($resource, DateUtils) {
        return $resource('api/users/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'getListUserDoctor': {
                method: 'GET',
                url: '/api/users/doctor',
                isArray: true
            }
        });
    });
