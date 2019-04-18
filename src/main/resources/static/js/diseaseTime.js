var Disease = function () {

    return {
        showTime : function (id) {
            var v = mini.get(id).getValue();
            var obj = mini.get(id).getEl();
             if (v == "1") {
                $(obj).parents(".diseaseTime").children(":gt(0)").show();
             } else {
                $(obj).parents(".diseaseTime").children(":gt(0)").hide();
             }
        }
    }
}();