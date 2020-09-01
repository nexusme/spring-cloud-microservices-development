/**
 * report 页面下拉框事件
 */
$(function () {
    //在function中监听一个事件
    $("#selectCityId").change(function () {
        var cityId=$("#selectCityId").val();
        var  url='/report/cityId/'+cityId;
        //使用window.location.herf
        window.location.href=url;
    })
})