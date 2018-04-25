//代码抽取
brandData.service("brandService",function ($http) {
    this.findAll=function () {
        return   $http.get("/brand/findAll.do");

    };
    this.add=function (num) {
        return  $http.post("../brand/add.do", num);
    };
    this.update=function (num) {
        return  $http.post("../brand/update.do", num);
    };
    this.findOne=function (id) {
        return  $http.get("../brand/findOne.do?id="+id);
    };
    this.dele=function (selectIds) {
        return $http.get("../brand/delete.do?ids="+selectIds);
    };
    this.search=function (pageNum,pageSize,searchEntity) {
        return $http.post("../brand/search.do?pageNum="+pageNum+"&pageSize="+pageSize,searchEntity);
    };


})