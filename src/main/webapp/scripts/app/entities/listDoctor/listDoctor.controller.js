angular.module('registrationApp')
    .controller('ListDoctorController', function ($scope, $state, ListDoctor) {
        $scope.listDoctors = [];
        $scope.loadAll = function() {
            ListDoctor.getListUserDoctor(function(result) {
                $scope.listDoctors = result;
            });
        };
        $scope.loadAll();
    });
