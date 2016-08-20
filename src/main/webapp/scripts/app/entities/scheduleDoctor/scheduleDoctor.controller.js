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
            });
            $scope.loadEvents(id);
        };

        $scope.loadEvents = function (id) {
            $scope.events = [];
            ScheduleDoctor.getListEvent({id: id}, function (result) {
                for (var i = 0; i < result.length; i++) {
                    var patientRecord = {};
                    patientRecord["id"] = result[i].patient.id;
                    patientRecord["text"] = result[i].patient.lastName;
                    patientRecord["start"] = result[i].start;
                    patientRecord["end"] = result[i].end;
                    $scope.events.push(patientRecord);
                }
            });
        };

        $scope.saveRecord = function () {
            var start = new Date(Date.parse($scope.data.date + ", " + $scope.data.time));
            $scope.record.start = start.getFullYear().toString() + '-' + ((start.getMonth() + 1).toString()[1] ? (start.getMonth() + 1).toString() : "0" + (start.getMonth() + 1).toString()[0]) + '-' + (start.getDate().toString()[1] ? start.getDate().toString() : "0" + start.getDate().toString()[0]) + 'T' + (start.getHours().toString()[1] ? start.getHours().toString() : "0" + start.getHours().toString()[0]) + ':' + (start.getMinutes().toString()[1] ? start.getMinutes().toString() : "0" + start.getMinutes().toString()[0]) + ':' + (start.getSeconds().toString()[1] ? start.getSeconds().toString() : "0" + start.getSeconds().toString()[0]);
            var end = new Date(Date.parse($scope.data.date + ", " + $scope.data.time) + 3600000);
            $scope.record.end = end.getFullYear().toString() + '-' + ((end.getMonth() + 1).toString()[1] ? (end.getMonth() + 1).toString() : "0" + (end.getMonth() + 1).toString()[0]) + '-' + (end.getDate().toString()[1] ? end.getDate().toString() : "0" + end.getDate().toString()[0]) + 'T' + (end.getHours().toString()[1] ? end.getHours().toString() : "0" + end.getHours().toString()[0]) + ':' + (end.getMinutes().toString()[1] ? end.getMinutes().toString() : "0" + end.getMinutes().toString()[0]) + ':' + (end.getSeconds().toString()[1] ? end.getSeconds().toString() : "0" + end.getSeconds().toString()[0]);
            $scope.record.doctor = $scope.doctor;
            ScheduleDoctor.createEvent($scope.record, function (result) {
            });
            $scope.LoadAll($stateParams.id);
        };


        $scope.LoadAll($stateParams.id);//точка входа
    });


