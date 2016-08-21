angular.module('registrationApp')
    .controller('ScheduleDoctorController', function ($scope, $state, $stateParams, ScheduleDoctor, ListDoctor) {

        $scope.data = {};
        $scope.doctor = {};
        $scope.record = {};

        $scope.options = {
            min: [7, 0],
            max: [18, 0],
            interval: 60
        };

        $scope.weekConfig = {
            viewType: "Week"
        };

        $scope.navigatorConfig = {
            showMonths: 1,
            skipMonths: 1,
            onTimeRangeSelected: function (args) {
                $scope.weekConfig.startDate = args.day;
            }
        };

        $scope.LoadAll = function (id) {
            ListDoctor.getUserDoctor({id: id}, function (result) {
                $scope.doctor = result;
                $scope.loadEvents(id);
            });
        };

        $scope.loadEvents = function (id) {
            $scope.events = [];
            ScheduleDoctor.getListEvent({id: id}, function (result) {
                for (var i = 0; i < result.length; i++) {
                    var patientRecord = {};
                    patientRecord["id"] = result[i].patient.id;
                    patientRecord["text"] = result[i].patient.firstName.concat(" ").concat(result[i].patient.lastName);
                    patientRecord["start"] = result[i].startForJS;
                    patientRecord["end"] = result[i].endForJS;
                    $scope.events.push(patientRecord);
                }
            });
        };

        $scope.saveRecord = function () {
            $scope.record.start = new Date(Date.parse($scope.data.date + ", " + $scope.data.time));
            $scope.record.end = new Date(Date.parse($scope.data.date + ", " + $scope.data.time) + 3600000);
            $scope.record.doctor = $scope.doctor;
            ScheduleDoctor.createEvent($scope.record, function (result) {
                $scope.LoadAll($stateParams.id);
            });
        };


        $scope.LoadAll($stateParams.id);//точка входа
    });


