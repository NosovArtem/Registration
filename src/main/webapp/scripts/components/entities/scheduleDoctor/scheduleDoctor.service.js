angular.module('registrationApp')
    .factory('ScheduleDoctor', function ($resource, DateUtils) {
        return $resource('/api/events', {}, {
            'getListEvent': {
                method: 'GET',
                url: '/api/events/:id',
                isArray: true
            },
            createEvent: {
                method: 'POST',
                url: 'api/events/create'
                }
        });
    });
