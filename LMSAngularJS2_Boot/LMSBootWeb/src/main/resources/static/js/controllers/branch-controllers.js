lmsApp.controller("branchController", function($scope, $http, $window, $location, lmsFactory, adminConstants, Pagination){
	
	if($location.path === '/addbranch'){
		$http.get(adminConstants.GET_ALL_BRANCHS).success(function(data){
			$scope.branch = data;
		})
	}else{
		lmsFactory.readAllObjects("http://localhost:8080/lms/listBranchs").then(function(data){
			$scope.branchs = data;
			$scope.pagination = Pagination.getNew(10);
			$scope.pagination.numPages = Math.ceil($scope.branchs.length/$scope.pagination.perPage);
		})
	}
	
	$scope.saveBranch = function(){
		lmsFactory.saveObject("http://localhost:8080/lms/createBranch", $scope.branch).then(function(data){
			$window.location.href = "#/librarian/viewbranchs"
		})
	}
	
	$scope.deleteBranch = function(branchId){
		var branch = {
				"branchId": branchId
		}
		
		lmsFactory.saveObject("http://localhost:8080/lms/deleteBranch", branchId).then(function(data){
			lmsFactory.readAllObjects("http://localhost:8080/lms/listBranchs").then(function(data){
				$scope.branchs = data;
				$scope.branchs = data;
				$scope.pagination = Pagination.getNew(10);
				$scope.pagination.numPages = Math.ceil($scope.branchs.length/$scope.pagination.perPage);
				$window.location.href = "#/librarian/viewbranchs"
			})
			
		})
	}
	
	$scope.searchBranchs = function(){
		lmsFactory.readAllObjects("http://localhost:8080/lms/readBranchByName/"+$scope.searchString).then(function(data){
			$scope.branchs = data;
			$scope.pagination = Pagination.getNew(10);
			$scope.pagination.numPages = Math.ceil($scope.authors.length/$scope.pagination.perPage);
			$scope.branchs = data;
		})
	}
	
	$scope.openModal = function(branchId){
		$http.get("http://localhost:8080/lms/getBranchByPk/"+branchId).success(function(data){
			$scope.branch = data;
		})
		$scope.editBranchModal = true;
	}
	
	$scope.close = function(){
		$scope.editBranchModal = false;
	}
	
	$scope.save = function(branchAddress){
	
		$scope.branch.branchAddress = branchAddress;
		console.log($scope.branch);
		lmsFactory.saveObject("http://localhost:8080/lms/updateBranch", $scope.branch).then(function(data){
			$scope.close();
			lmsFactory.readAllObjects("http://localhost:8080/lms/listBranchs").then(function(data){
				$scope.branchs = data;
				$window.location.href = "#/librarian/viewbranchs"
			})	
		})
	}
	$scope.openModal = function(branchId){
		$http.get("http://localhost:8080/lms/getBranchByPk/"+branchId).success(function(data){
			$scope.branch = data;
		})
		$scope.editBranchNameModal = true;
	}
	
	$scope.close = function(){
		$scope.editBranchNameModal = false;
	}
	
	$scope.save = function(branchName){
	
		$scope.branch.branchName = branchName;
		console.log($scope.branch);
		lmsFactory.saveObject("http://localhost:8080/lms/updateBranch", $scope.branch).then(function(data){
			$scope.close();
			lmsFactory.readAllObjects("http://localhost:8080/lms/listBranchs").then(function(data){
				$scope.branchs = data;
				$window.location.href = "#/librarian/viewbranchs"
			})	
		})
	}
	
})