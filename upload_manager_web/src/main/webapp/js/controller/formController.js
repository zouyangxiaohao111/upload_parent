app.controller("formController",function($scope,$controller,$http){

    // controller的继承，本质就是共用一个$scope
    $controller('baseController',{$scope:$scope});

    $scope.downLoad =function(url){
        $http.get("../down/download/1").success(function (resp) {
            if (resp.success){
                alert("下载成功");
            }
            else {alert("下载失败")}

        });


    }


    // 文件上传
    $scope.uploadFile=function(){
        // 存储表单数据
        var formData=new FormData();
        // 向表单中添加file属性和值
        formData.append("file",file.files[0]);
        // 文件上传
        $http({
            method:'POST',
            url:"../upload",
            data: formData,
            headers: {'Content-Type':undefined},
            transformRequest: angular.identity}).
        then(function successCallback(resp) {
            // 请求成功执行代码
            if(resp.data.success){
                alert(resp.data.message);
                $scope.url = resp.data.data;
            }else{
                alert(resp.data.message);
            }

        });
    };

});