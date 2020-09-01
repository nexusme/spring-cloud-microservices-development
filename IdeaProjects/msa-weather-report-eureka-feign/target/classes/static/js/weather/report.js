/**
 * report 页面下拉框事件
 * auther:lucheg
 * 
 */
$(function () {
    //在function中我们来监听一个事件
    $("#selectCityId").change(function () {
        var cityId=$("#selectCityId").val();
        var  url='/report/cityId/'+cityId;
        //如何来触发这个url呢我们使用window.location.herf
        window.location.href=url;
    })
})