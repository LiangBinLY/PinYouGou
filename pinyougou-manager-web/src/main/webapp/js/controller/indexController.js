brandData.controller('indexController' ,function($scope,$controller,loginService){
    $scope.showLoginName=function () {
        loginService.getLoginName().success(
            function (response) {
                $scope.loginName=response.loginName;
            }
        )

    }
});