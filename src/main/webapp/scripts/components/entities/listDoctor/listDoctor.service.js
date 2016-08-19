angular.module('registrationApp')
    .factory('ListDoctor', function ($resource, DateUtils) {
        return $resource('api/users/:id', {}, {
            'getListUserDoctor': {
                method: 'GET',
                url: '/api/users/doctor',
                isArray: true
            },
            'getUserDoctor': {
                method: 'GET',
                url: '/api/users/doctor/:id'
            }
        });
    });
