brandData.controller("brandController",function ($scope,brandService,$controller) {
    $controller('baseController',{$scope:$scope});
    //获取数据
    $scope.findAll = function () {
        //http发送请求
        brandService.findAll().success(
            function (result) {
                $scope.list = result;
            }
        )

    }


    //增加品牌
    $scope.save = function () {
        var metho=brandService.add($scope.num);
        if($scope.num.id!=null){
            metho=brandService.update($scope.num);
        }
        metho.success(
            function (response) {
                if (response.success) {
                    //增加品牌成功,刷新页面;
                    $scope.reloadList();
                } else {
                    //增加品牌失败,弹出窗口提示失败
                    alert(response.message);
                }

            }
        )

    }
    //修改品牌的第一步查询出品牌;
    $scope.findOne=function (id) {
        brandService.findOne(id).success(
            function (response) {
                $scope.num=response;

            }
        )

    }



    //删除时调用的方法;
    $scope.delete=function () {
        brandService.dele($scope.selectIds).success(
            function (response) {
                if(response.success){
                    //删除成功,刷新
                    $scope.reloadList();
                }else{
                    //删除失败
                    alert(response.message);
                }

            }
        )

    }


    $scope.searchEntity={};//定义搜索对象为空对象防止传递给null的情况。
    //搜素
    $scope.search=function (pageNum,pageSize) {
        brandService.search(pageNum,pageSize,$scope.searchEntity).success(
            function (response) {
                $scope.list=response.rows;
                $scope.paginationConf.totalItems=response.total;


            }
        )

    }

});