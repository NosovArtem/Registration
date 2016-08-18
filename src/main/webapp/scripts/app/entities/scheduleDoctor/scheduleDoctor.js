angular.module('registrationApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('scheduleDoctor', {
                parent: 'entity',
                url: '/scheduleDoctor/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'registrationApp.scheduleDoctor.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/scheduleDoctor/scheduleDoctor.html',
                        controller: 'ScheduleDoctorController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('scheduleDoctor');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'ScheduleDoctor', function($stateParams, ScheduleDoctor) {
                        return ScheduleDoctor.getListEvent({id : $stateParams.id});
                    }]
                }
            })
    });
