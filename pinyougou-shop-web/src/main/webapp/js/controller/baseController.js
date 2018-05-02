brandData.controller("baseController",function($scope){

    $scope.reloadList=function(){
        $scope.search( $scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    };


    $scope.paginationConf = {
        currentPage: 1,//当前页码
        totalItems: 10, //
        itemsPerPage: 10,
        perPageOptions: [10, 20, 30, 40, 50],
        onChange: function () {
            $scope.reloadList();
        }
    };

    //删除品牌必须传过去的是数组;所以我们需要定义一个数组
    $scope.selectIds=[];//ID集合
    //点击复选框就会触发,将Id传入数组
    $scope.cilck=function ($event,id) {
        if($event.target.checked){

            $scope.selectIds.push(id);
        }else{
            //参数一:传入id在数组中的索引
            //参数二:删除第几个
            $scope.selectIds.splice($scope.selectIds.indexOf(id),1);
        }

    }

});