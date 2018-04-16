lmsApp.factory("lmsFactory", function($http){
	return{
		readAllObjects: function(url){
			var listObjects = {};
			return $http({
				url: url
			}).success(function(data){
				listObjects = data;
			}).then(function(){
				return listObjects;
			})
		},
		saveObject: function(url, obj){
			var listObjects = {};
			return $http.post(url, obj).success(function(data){
				listObjects = data;
			}).then(function(){
				return listObjects;
			})
		}
	}
})