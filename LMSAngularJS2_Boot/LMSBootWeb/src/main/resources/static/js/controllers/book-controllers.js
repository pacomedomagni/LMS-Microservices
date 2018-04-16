lmsApp.controller("bookController", function($scope, $http, $window, $location, lmsFactory, adminConstants, Pagination){
	
	if($location.path === '/addbook'){
		$http.get(adminConstants.GET_ALL_BOOKS).success(function(data){
			$scope.book = data;
		})
	}else{
		lmsFactory.readAllObjects("http://localhost:8080/lms/listOfBooks").then(function(data){
			$scope.books = data;
			$scope.pagination = Pagination.getNew(10);
			$scope.pagination.numPages = Math.ceil($scope.books.length/$scope.pagination.perPage);
		})
	}
	
	$scope.saveBook = function(){
		lmsFactory.saveObject("http://localhost:8080/lms/createBook", $scope.book).then(function(data){
			$window.location.href = "#/admin/viewbooks"
		})
	}
	
	$scope.deleteBook = function(bookId){
		var book = {
				"bookId": bookId
		}
		lmsFactory.saveObject("http://localhost:8080/lms/deleteBook", bookId).then(function(data){
			lmsFactory.readAllObjects("http://localhost:8080/lms/listOfBooks").then(function(data){
				$scope.books = data;
				$scope.books = data;
				$scope.pagination = Pagination.getNew(10);
				$scope.pagination.numPages = Math.ceil($scope.books.length/$scope.pagination.perPage);
				$window.location.href = "#/admin/viewbooks"
			})
		})
	}
	
	$scope.searchBooks = function(){
		lmsFactory.readAllObjects("http://localhost:8080/lms/searchBookByTitle/"+$scope.searchString).then(function(data){
			$scope.books = data;
			$scope.pagination = Pagination.getNew(10);
			$scope.pagination.numPages = Math.ceil($scope.books.length/$scope.pagination.perPage);
			$scope.books = data;
		})
	}
	
	$scope.openModal = function(bookId){
		$http.get("http://localhost:8080/lms/getBookByPk/"+bookId).success(function(data){
			$scope.book = data;
		})
		$scope.editBookModal = true;
	}
	
	$scope.close = function(){
		$scope.editBookModal = false;
	}
	
	$scope.save = function(title){
		
		$scope.book.title = title;
		console.log($scope.book);
		lmsFactory.saveObject("http://localhost:8080/lms/updateBook", $scope.book).then(function(data){
			$scope.close();
			lmsFactory.readAllObjects("http://localhost:8080/lms/listOfBooks").then(function(data){
				$scope.books = data;
				$window.location.href = "#/admin/viewbooks"
			})
		})
	}
})