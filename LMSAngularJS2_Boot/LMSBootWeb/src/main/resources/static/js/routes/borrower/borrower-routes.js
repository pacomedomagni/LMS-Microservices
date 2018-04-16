lmsApp.config(["$routeProvider", function($routeProvider){
	return $routeProvider.when("/",{
		redirectTo: "/home"
	}).when("/home",{
		templateUrl: "home.html"
	}).when("/borrower",{
		templateUrl: "borrower.html"
	}).when("/borrower/validity", {
		templateUrl: "validity.html"
	}).when("/librarian/viewbranchs",{
		templateUrl: "viewbranchs.html"
	}).when("/librarian/checkout",{
		templateUrl: "checkout.html"
	}).when("/librarian/returnbook",{
		templateUrl:"returnbook.html"
	})
}])