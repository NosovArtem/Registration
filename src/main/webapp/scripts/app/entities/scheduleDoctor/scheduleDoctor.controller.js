angular.module('registrationApp')
    .controller('ScheduleDoctorController', function ($scope, $state, $stateParams, ScheduleDoctor) {

        $scope.events = [];
        $scope.data = {};
        $scope.doctorLastName = {};

        $scope.weekConfig = {
            viewType: "Week"
        };

        $scope.navigatorConfig = {
            selectMode: "day",
            showMonths: 1,
            skipMonths: 1,
            onTimeRangeSelected: function (args) {
                $scope.weekConfig.startDate = args.day;
                loadEvents($stateParams.id);
            }
        };

        $scope.loadEvents = function (id) {
            ScheduleDoctor.getListEvent({id: id}, function (result) {
                $scope.doctorLastName = result[0][4];
                $scope.data.doctorId = result[0][5];
                for (var i = 0; i < result.length; i++) {
                    var patientRecord = {};
                    patientRecord["id"] = result[i][0];
                    patientRecord["text"] = result[i][1];
                    patientRecord["start"] = result[i][2];
                    patientRecord["end"] = result[i][3];
                    $scope.events.push(patientRecord);
                }

            });
        };
        //to do: доделать метод createEvent на контролере ScheduleDoctor
        $scope.saveRecord = function () {
            ScheduleDoctor.createEvent($scope.data, function () {
                showMessage($("#saveSuccess"));
            });
        };

        $scope.loadEvents($stateParams.id);
    });


