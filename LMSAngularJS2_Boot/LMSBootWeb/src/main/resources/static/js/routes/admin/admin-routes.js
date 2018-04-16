lmsApp.config(["$routeProvider", function($routeProvider){
	return $routeProvider.when("/",{
		redirectTo: "/home"
	}).when("/home", {
		templateUrl: "home.html"
	}).when("/admin", {
		templateUrl: "admin.html"
	}).when("/admin/author", {
		templateUrl: "author.html"
	}).when("/admin/addauthor", {
		templateUrl: "addauthor.html"
	}).when("/admin/viewauthors", {
		templateUrl: "viewauthors.html"
	}).when("/admin/viewbooks",{
		templateUrl: "viewbooks.html"
	}).when("/admin/book",{
		templateUrl: "book.html"
	}).when("/admin/addbook",{
		templateUrl: "addbook.html"
	}).when("/admin/addpublisher",{
		templateUrl: "addpublisher.html"
	}).when("/admin/addbranch",{
		templateUrl: "addbranch.html"
	}).when("/admin/viewpublishers",{
		templateUrl: "viewpublishers.html"
	})
	}])