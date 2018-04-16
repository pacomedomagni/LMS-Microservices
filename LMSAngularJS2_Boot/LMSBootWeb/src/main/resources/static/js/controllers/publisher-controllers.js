lmsApp.controller("publisherController", function($scope, $http, $window, $location, lmsFactory, adminConstants, Pagination){
	
	if($location.path === '/addpublisher'){
		$http.get(adminConstants.GET_ALL_PUBLISHERS).success(function(data){
			$scope.publisher = data;
		})
	}else{
		lmsFactory.readAllObjects("http://localhost:8080/lms/listPublishers").then(function(data){
			$scope.publishers = data;
			$scope.pagination = Pagination.getNew(10);
			$scope.pagination.numPages = Math.ceil($scope.publishers.length/$scope.pagination.perPage);
		})
	}
	
	$scope.savePublisher = function(){
		lmsFactory.saveObject("http://localhost:8080/lms/createPublisher", $scope.publisher).then(function(data){
			$window.location.href = "#/admin/viewpublishers"
		})
	}
	
	$scope.deletePublisher = function(publisherId){
		var publisher = {
				"publisherId": publisherId
		}
		lmsFactory.saveObject("http://localhost:8080/lms/deletePublisher",publisherId).then(function(data){
			lmsFactory.readAllObjects("http://localhost:8080/lms/listPublishers").then(function(data){
				$scope.publishers = data;
				$scope.publishers = data;
				$scope.pagination = Pagination.getNew(10);
				$scope.pagination.numPages = Math.ceil($scope.publishers.length/$scope.pagination.perPage);
				$window.location.href = "#/admin/viewpublishers"
			})
		})
	}
	
	$scope.searchPublishers = function(){
		lmsFactory.readAllObjects("http://localhost:8080/lms/searchPublishers/"+$scope.searchString).then(function(data){
			$scope.publishers = data;
			$scope.pagination = Pagination.getNew(10);
			$scope.pagination.numPages = Math.ceil($scope.publishers.length/$scope.pagination.perPage);
			$scope.publishers = data;
		})
	}
	
	$scope.openModal = function(PublisherId){
		$http.get("http://localhost:8080/lms/getPublisherByPk/"+publisherId).success(function(data){
			$scope.publisher = data;
		})
		$scope.editPublisherModal = true;
	}
	
	$scope.close = function(){
		$scope.editPublisherModal = false;
	}
	
	$scope.save = function(title){
		
		$scope.publisher.publisherName = publisherName;
		console.log($scope.publisher);
		lmsFactory.saveObject("http://localhost:8080/lms/updatePublisher", $scope.publisher).then(function(data){
			$scope.close();
			lmsFactory.readAllObjects("http://localhost:8080/lms/listPublishers").then(function(data){
				$scope.publishers = data;
				$window.location.href = "#/admin/viewpublishers"
			})
		})
	}
})