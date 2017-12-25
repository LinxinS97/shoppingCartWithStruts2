$(function () {
    $.ajax({
        type:   "get",
        url:    "getLocation",
        data:   {},
        success:function (data) {
            let $class = $("."+data.location);
            let $div   = $("#"+data.location);
            if(data.location === "price_desc"){
                $(".price").addClass("active");
            }
            $class.addClass("active");
            $div.addClass("in");
            $div.addClass("active");
        }
    })
});