lmsApp.controller("borrowerController", function($scope, $http, $window, $location, lmsFactory, adminConstants, Pagination){
	
	$scope.checkValidity = function(){
		lmsFactory.saveObject("http://localhost:8080/lms/validateBorrower/" +$scope.cardNo).then(function(data){
			$scope.ans= data;
			if(ans==true){
				console.log( valide card);
			}else{
				console.log(wrong card);
			}
			$window.location.href = "#/admin/viewbooks"
		})
	}
})