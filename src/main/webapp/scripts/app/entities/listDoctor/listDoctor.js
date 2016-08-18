angular.module('registrationApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('listDoctor', {
                parent: 'entity',
                url: '/listDoctor',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'registrationApp.listDoctor.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/listDoctor/listDoctor.html',
                        controller: 'ListDoctorController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('listDoctor');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
    });
