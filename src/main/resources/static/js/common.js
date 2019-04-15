/**
 * Created by xuguobing on 2017/5/5.
 */
$(function(){
    mini.parse();
    $(".sidebar>ul>li>a").click(function () {
        var $li = $(this).parent();
        if ($li.hasClass("selected")) {
            $li.removeClass("selected");
        } else {
            $(".sidebar>ul>li.selected").removeClass("selected");
            $li.addClass("selected");
        }
    });
    $(".data-href").click(function(){
        $(".clickLi").removeClass("selected");
        $(this).parent().addClass("selected");
        var href = $(this).attr("data-href");
        $("#mainframe").attr("src",href);
    });
});

var re_submit_flag={};
function submitForm(formId, url, fun, saveType) {
    var form = new mini.Form("#" + formId);
    form.validate();
    if (form.isValid() == false){
        miniErrorLocation(form);
        mini.showTips({
            content: "您有必输项未填或有输入错误，请检查您的输入",
            state: "warning",
            x: "center",
            y: "center",
            timeout: 3000
        });
        return;
    }
    if(re_submit_flag[formId]){
        return;
    }
    re_submit_flag[formId]=true;
    mini.mask({
        el: document.body,
        cls: 'mini-mask-loading',
        html: '加载中...'
    });
    //提交数据
    var data = form.getData(true, false);
    data.status=saveType;
    $.ajax({
        url: url,
        type: "post",
        data: data,
        dataType: "json",
        success: function (result) {
            mini.unmask(document.body);
            if (result.ok) {
                mini.showTips({
                    content: result.msg,
                    state: "success",
                    x: "center",
                    y: "center",
                    timeout: 500
                });
                if (fun != undefined && fun != "") {
                    eval(fun).apply(this, [result]);
                } else {
                    setTimeout(function () {
                        try {
                            window.parent.window.loadList();
                        } catch (e) {
                        }
                    }, 500);
                }
            } else {
                if (result.msg == "登录超时") {
                    window.location.href = ctx + "/login";
                } else {
                    window.location.href = ctx + "/authFail";
                }
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            window.location.href = ctx + "/authFail";
        }
    });
}

//自动定位到第一个报错的miniui空间
function miniErrorLocation(){
    var errorObj=$(".mini-error:first");
    if(errorObj!=undefined){
        var id=errorObj.attr("id");
        if(isNotEmpty(id)){
            mini.get(id).focus();
        }
    }

}
function closeForm() {
    try{
        window.parent.window.loadList();
    }catch (e){
    }
}

function displaynavbar(obj) {
    var layout = mini.get("layout");
    if ($(obj).hasClass("open")) {
        $(obj).removeClass("open");
        layout.showRegion("west");
    } else {
        $(obj).addClass("open");
        layout.hideRegion("west");
    }
}

function gridLoadSuccess(sender) {
    try {
        if (sender.data.length == 0) {
            var result = $.parseJSON(sender.text);
            if (result.ok == false && result.msg == "登录超时") {
                window.location.href = app_ctx + "/login";
            } else {
                mini.showTips({
                    content: "未检索到任何符合条件的结果",
                    state: "info",
                    x: "Center",
                    y: "Center",
                    timeout: 2000
                });
            }
        }
    } catch (e) {
    }
}

function gridLoadError(sender, xhr, errorMsg, errorCode) {
    // try {
    //     var text = sender.xhr.responseText;
    //     if ($.trim(text) != "") {
    //         mini.showTips({
    //             content: text,
    //             state: "danger",
    //             x: "center",
    //             y: "center",
    //             timeout: 3000
    //         });
    //     }
    // } catch (e) {
    // }
}