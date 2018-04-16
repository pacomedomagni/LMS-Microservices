lmsApp.config(["$routeProvider", function($routeProvider){
	return $routeProvider.when("/",{
		redirectTo: "/home"
	}).when("/home",{
		templateUrl: "home.html"
	}).when("/librarian",{
		templateUrl: "librarian.html"
	}).when("/librarian/addbranch", {
		templateUrl: "addbranch.html"
	}).when("/librarian/viewbranchs",{
		templateUrl: "viewbranchs.html"
	}).when("/librarian/checkout",{
		templateUrl: "checkout.html"
	}).when("/librarian/returnbook",{
		templateUrl:"returnbook.html"
	})
}])