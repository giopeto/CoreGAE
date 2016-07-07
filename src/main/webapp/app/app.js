//Define an angular module for our app
var ngApp = angular.module('ngApp', ['ngRoute', 'ngResource', 'satellizer']);

ngApp.controller('mainCtrl', function($scope, $http, $log, localStorageService, GroupFactory, PSFactory) {

	$scope.main = {
		user: localStorageService.get("user"),
		allGroups:  GroupFactory.query(),
		ps: PSFactory.query({id: localStorageService.get("psId")}),
	};

	$scope.logOut = function () {
		localStorageService.remove("user");
		$scope.main.user = {};
	};

});

ngApp.config(function ($controllerProvider, $compileProvider, $filterProvider, $provide, $routeProvider, $httpProvider, $locationProvider, $authProvider) {

	$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
	$locationProvider.html5Mode = true;

	ngApp.lazy = {
        controller: $controllerProvider.register,
       	directive: $compileProvider.directive,
        filter: $filterProvider.register,
        factory: $provide.factory,
        service: $provide.service
    };

	$authProvider.google({
		clientId:"946982369515-rmb4je4se68n2j02nr9fqgg2rgrn9nb7.apps.googleusercontent.com", //Move this
		url: '/auth/google',
		authorizationEndpoint: 'https://accounts.google.com/o/oauth2/auth',
		redirectUri: window.location.origin,
		requiredUrlParams: ['scope'],
		optionalUrlParams: ['display'],
		scope: ['profile', 'email'],
		scopePrefix: 'openid',
		scopeDelimiter: ' ',
		display: 'popup',
		type: '2.0',
		popupOptions: { width: 452, height: 633 }
	});

	$authProvider.facebook({
		clientId: '1541083682868013' //Move this
	});




	$routeProvider.when('/items', {
		templateUrl: 'app/templates/items/items.html',
		resolve: {
			load: ['$q', '$rootScope', function ($q, $rootScope) {
				var deferred = $q.defer();
				require([
					'app/controllers/items/items.controller.js',
					'app/services/items/item.service.js',
					'app/services/ps/ps.service.js',
					'app/services/users/user.service.js'
					/*'app/services/groups/group.service.js',*/
				], function () {
					$rootScope.$apply(function () {
						deferred.resolve();
					}, function (err) {
						console.log ('RouteProvider resolve error: ', err );
					});
				});
				return deferred.promise;
			}]
		}

	}).when('/items_add_edit/:id', {
		templateUrl: 'app/templates/items/items_add_edit.html',
			resolve: {
			load: ['$q', '$rootScope', function ($q, $rootScope) {
				var deferred = $q.defer();
				require([
					'app/controllers/items/items.controller.js',
					'app/services/items/item.service.js',
					'app/services/users/user.service.js'
					/*'app/services/groups/group.service.js',*/
				], function () {
					$rootScope.$apply(function () {
						deferred.resolve();
					}, function (err) {
						console.log ('RouteProvider resolve error: ', err );
					});
				});
				return deferred.promise;
			}]
		}

	}).when('/item_info/:id', {
		templateUrl: 'app/templates/items/item_info.html',
		resolve: {
			load: ['$q', '$rootScope', function ($q, $rootScope) {
				var deferred = $q.defer();
				require([
					'app/controllers/items/items.controller.js',
					'app/services/items/item.service.js',
					'app/services/users/user.service.js'
					/*'app/services/groups/group.service.js',*/
				], function () {
					$rootScope.$apply(function () {
						deferred.resolve();
					}, function (err) {
						console.log ('RouteProvider resolve error: ', err );
					});
				});
				return deferred.promise;
			}]
		}


	}).when('/items_list/:groupId', {
		templateUrl: 'app/templates/items/items_list.html',
		resolve: {
			load: ['$q', '$rootScope', function ($q, $rootScope) {
				var deferred = $q.defer();
				require([
					'app/controllers/items/items.controller.js',
					'app/services/items/item.service.js',
					'app/services/users/user.service.js'
				], function () {
					$rootScope.$apply(function () {
						deferred.resolve();
					}, function (err) {
						console.log ('RouteProvider resolve error: ', err );
					});
				});
				return deferred.promise;
			}]
		}
	}).when('/groups', {
		templateUrl: 'app/templates/groups/groups.html',
		resolve: {
			load: ['$q', '$rootScope', function ($q, $rootScope) {
				var deferred = $q.defer();
				require([
					'app/controllers/groups/groups.controller.js',
					/*'app/services/groups/group.service.js',*/
				], function () {
					$rootScope.$apply(function () {
						deferred.resolve();
					}, function () {
						console.log ('ERROR');
					});
				});
				return deferred.promise;
			}]
		}
	}).when('/groups_add_edit/:id', {
		templateUrl: 'app/templates/groups/groups_add_edit.html',
		resolve: {
			load: ['$q', '$rootScope', function ($q, $rootScope) {
				var deferred = $q.defer();
				require([
					'app/controllers/groups/groups.controller.js',
					/*'app/services/groups/group.service.js',*/
				], function () {
					$rootScope.$apply(function () {
						deferred.resolve();
					}, function () {
						console.log ('ERROR');
					});
				});
				return deferred.promise;
			}]
		}
	}).when('/files_add_edit/:id', {
		templateUrl: 'app/templates/files/files_add_edit.jsp',
		resolve: {
			load: ['$q', '$rootScope', function ($q, $rootScope) {
				var deferred = $q.defer();
				require([
					'app/controllers/files/files_add_edit.js',
					'app/services/items/item.service.js',
				], function () {
					$rootScope.$apply(function () {
						deferred.resolve();
					}, function (error) {
						console.log ('ERROR: ', error);
					});
				});
				return deferred.promise;
			}]
		}

	}).when('/ps_add_edit/:id', {
		templateUrl: 'app/templates/ps/ps_add_edit.html',
		resolve: {
			load: ['$q', '$rootScope', function ($q, $rootScope) {
				var deferred = $q.defer();
				require([
					'app/controllers/ps/ps.controller.js',
					/*'app/services/items/item.service.js',*/
				], function () {
					$rootScope.$apply(function () {
						deferred.resolve();
					}, function (error) {
						console.log ('ERROR: ', error);
					});
				});
				return deferred.promise;
			}]
		}

	}).when('/login', {
		templateUrl: 'app/templates/auth/login.html',
		resolve: {
			load: ['$q', '$rootScope', function ($q, $rootScope) {
				var deferred = $q.defer();
				require([
					'app/controllers/auth/login.controller.js',

				], function () {
					$rootScope.$apply(function () {
						deferred.resolve();
					}, function (error) {
						console.log ('ERROR: ', error);
					});
				});
				return deferred.promise;
			}]
		}
	}).when('/home', {
		templateUrl: 'app/templates/home/home.html'

	}).otherwise({
		redirectTo: '/home',
	});


});