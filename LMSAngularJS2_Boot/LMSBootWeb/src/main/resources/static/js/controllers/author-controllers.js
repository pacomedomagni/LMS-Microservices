lmsApp.controller("authorController", function($scope, $http, $window, $location, lmsFactory, adminConstants, Pagination){
	
	if($location.path === '/addauthor'){
		$http.get(adminConstants.GET_ALL_AUTHORS).success(function(data){
			$scope.author = data;
		})
	}else{
		lmsFactory.readAllObjects("http://localhost:8080/lms/listAuthors").then(function(data){
			$scope.authors = data;
			$scope.pagination = Pagination.getNew(10);
			$scope.pagination.numPages = Math.ceil($scope.authors.length/$scope.pagination.perPage);
		})
	}
	
	$scope.saveAuthor = function(){
		lmsFactory.saveObject("http://localhost:8080/lms/createAuthor", $scope.author).then(function(data){
			$window.location.href = "#/admin/viewauthors"
		})
	}
	
	$scope.deleteAuthor = function(authorId){
		var author = {
				"authorId": authorId
		}
		
		lmsFactory.saveObject("http://localhost:8080/lms/deleteAuthor", authorId).then(function(data){
			lmsFactory.readAllObjects("http://localhost:8080/lms/listAuthors").then(function(data){
				$scope.authors = data;
				$scope.authors = data;
				$scope.pagination = Pagination.getNew(10);
				$scope.pagination.numPages = Math.ceil($scope.authors.length/$scope.pagination.perPage);
				$window.location.href = "#/admin/viewauthors"
			})
			
		})
	}
	
	$scope.searchAuthors = function(){
		lmsFactory.readAllObjects("http://localhost:8080/lms/readAuthorsByName/"+$scope.searchString).then(function(data){
			$scope.authors = data;
			$scope.pagination = Pagination.getNew(10);
			$scope.pagination.numPages = Math.ceil($scope.authors.length/$scope.pagination.perPage);
			$scope.authors = data;
		})
	}
	
	$scope.openModal = function(authorId){
		$http.get("http://localhost:8080/lms/getAuthorByPk/"+authorId).success(function(data){
			$scope.author = data;
		})
		$scope.editAuthorModal = true;
	}
	
	$scope.close = function(){
		$scope.editAuthorModal = false;
	}
	
	$scope.save = function(authorName){
	
		$scope.author.authorName = authorName;
		console.log($scope.author);
		lmsFactory.saveObject("http://localhost:8080/lms/updateAuthor", $scope.author).then(function(data){
			$scope.close();
			lmsFactory.readAllObjects("http://localhost:8080/lms/listAuthors").then(function(data){
				$scope.authors = data;
				$window.location.href = "#/admin/viewauthors"
			})	
		})
	}
})