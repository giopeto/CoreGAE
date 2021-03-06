'use strict';

/* Items Controller */

ngApp.lazy.controller('itemsCtrl', function($scope, $log, $routeParams, $location, $http, ItemFactory, GroupFactory, PSFactory, localStorageService, UserFactory) {
	var vm = this;

	vm.isLoading = false;
	vm.errorMsg = {};
	vm.obj = {};
	vm.allObj = [];
	vm.allGroups =  [];
	vm.allGroups =  GroupFactory.query();

	vm.save = save;
	vm.get = get;
	vm.update = update;
	vm.addEdit = addEdit;
	vm.info = info;
	vm.remove = remove;
	vm.changeFilesOrder = changeFilesOrder;
	vm.addToBasket = addToBasket;
	vm.addComment = addComment;
	vm.removeFile = removeFile;

	var skipGoBack = 0;

	function save () {

		if (!vm.obj.groupId) {
			vm.errorMsg.groupErr = 1;
			return;
		}

		changeLoadingState();
		ItemFactory.save(vm.obj, function () {
			goBack();
		}, function (error) {
			$log.log("Error: ", error);
			changeLoadingState();
		});
	};

	function update () {
		changeLoadingState();

		vm.obj.fileIds = {};
		vm.obj.aFileIds.forEach(function(val, key) {
			vm.obj.fileIds[key] = val.toString();
		});

		ItemFactory.update(vm.obj, function (data) {
			if (!skipGoBack) {
				goBack();
			}
		}, function (error) {
			$log.log("Error: ", error);
			changeLoadingState();
		});
	};

	function remove (args) {
		changeLoadingState();
		ItemFactory.delete(args, function (data) {
			vm.allObj.splice(args.index, 1);
			changeLoadingState();
		}, function (error) {
			$log.log ("Error: ", error);
			changeLoadingState();
		});
	};

	function get () {
		changeLoadingState();
		vm.allObj = ItemFactory.query({}, function() {
			makeViewItem(vm.allObj);
			changeLoadingState();
		}, function (error) {
			$log.log ("Error: ", error);
			changeLoadingState();
		});
	};

	function addEdit (args){
		var id = args.id > 0 ? args.id : 0;
		$location.path('/items_add_edit/'+id);
	};

	function info (args){
		$location.path('/item_info/'+args.id);
	};

	function goBack () {
		$location.path('/items');
	};

	function changeLoadingState(){
		vm.isLoading = !vm.isLoading;
	};


	function makeViewItem (args) {
		var arr = [];
		args.forEach(function(value, key) {
			value.groupId = String(value.groupId);
			angular.forEach(value.fileIds, function (fileValue, fileKey){
				arr[parseInt(fileKey)] = fileValue;
			});
			value.aFileIds = arr.filter(function(n){ return n != undefined });
			arr = [];
		});

	};

	function changeFilesOrder (args) {
		var tmpArr = [];
		angular.copy(vm.obj.aFileIds, tmpArr);

		if (args.direction === 'up') {
			tmpArr[args.index] = vm.obj.aFileIds[args.index-1];
			tmpArr[args.index-1] = vm.obj.aFileIds[args.index];
		} else {
			tmpArr[args.index] = vm.obj.aFileIds[args.index+1];
			tmpArr[args.index+1] = vm.obj.aFileIds[args.index];
		}

		vm.obj.aFileIds = tmpArr;
	};

	function removeFile (args) {
		vm.obj.aFileIds.splice(args.index, 1);
	};


	function addToBasket (args) {
		changeLoadingState();

		args.quantity = 1;
		$scope.main.ps.items.push(args);
		PSFactory.update($scope.main.ps, function () {
			changeLoadingState();
			alert ("Success")
		}, function (error) {
			$log.log("Error: ", error);
			changeLoadingState();
		});

	};


	function addComment (args) {
		if (!args.comment) {
			return;
		}

		var thisComment = {
			userId: $scope.main.user.id,
			comment: args.comment,
			userName: $scope.main.user.given_name + " " + $scope.main.user.family_name,
			picture: $scope.main.user.picture
		};

		args.row.comments.unshift(thisComment);
		vm.obj = args.row;
		skipGoBack = 1;
		args.row.comment = '';
		update();
	};

	if ($routeParams.id > 0) {
		changeLoadingState();
		vm.obj = ItemFactory.get({ id: $routeParams.id }, function (data) {
			changeLoadingState();
			makeViewItem([vm.obj]);
		}, function (error) {
			$log.log ("Error: ", error);
			changeLoadingState();
		});
	} else if ($routeParams.groupId > 0){
		changeLoadingState();
		$http.get('items/getByGroupId/'+$routeParams.groupId, {

		}).success(function(data) {
			vm.allObj = data;
			makeViewItem(vm.allObj);
			changeLoadingState();
		}).error(function(error) {
			$log.log("ERROR: "+error);
			changeLoadingState();
		});
	} else {
		get();
	}

});

