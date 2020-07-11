/*
 * 参与表 状态:注册
 */
var PART_STATE_SIGN = 0;
/*
 * 参与表 状态:等待
 */

var PART_STATE_WAIT = 1;
/*
 * 参与表 状态:任务中
 */
var PART_STATE_ING = 2;
/*
 * 参与表 状态:提交
 */
var PART_STATE_SUBMIT = 3;
/*
 * 参与表 状态:审核
 */
var PART_STATE_CHECK = 4;
/*
 * 参与表 状态:申诉
 */
var PART_STATE_APPEAL = 5;
/*
 * 参与表 状态:撤销
 */
var PART_STATE_BREAK = 6;
/*
 * 参与表 状态:完成
 */
var PART_STATE_SUCCESS = 7;
PART_STATE = ["注册", "等待", "任务中", "提交", "审核", "申诉", "撤销", "完成"]

var ACT_STATE_CREATE = 0; //创建
var ACT_STATE_AGGRE = 1; //集结
var ACT_STATE_TASK = 2; //任务中
var ACT_STATE_FINISH = 3; //完结

var ACT_STATE = ["创建", "集结", "任务中", "完结"];


//全局配置
layui.jquery.ajaxSetup({
    beforeSend: function (jqXHR, settings) {
        //在请求前给修改url（增加一个时间戳参数）
        settings.url += settings.url.match(/\?/) ? "&" : "?";
        settings.url += "_tms=" + new Date().getTime();
    },
});

var $ = layui.jquery;


//前端地址栏参数转对象
function getSearchObj() {
    var obj = {};
    var url = location.search;
    if (url.indexOf("?") === 0)
        url = url.substring(1);
    var sps = url.split("&");
    for (let i = 0; i < sps.length; i++) {
        var item = sps[i];
        var cspt = item.split("=");
        if (cspt.length === 2) {
            obj[cspt[0]] = cspt[1];
        }
    }
    return obj;
}

//获取活动信息
function getActInfo(id, resp) {
    var url = "/act/" + id;
    $.ajax({
        url: url,
        type: "get",
        success: function (data) {
            codeCheck(data);
            resp(data);
        },
        error: function (data) {
            errorCheck(data);
        }
    })
}

function reload() {
    location.reload();
}

//获取api
function getActListByApi(curr, size, search, resp) {
    if (size == null)
        size = 10;
    var url = "/api/act/list?search=" + search + "&curr=" + curr + "&size=" + size;
    $.ajax({
        url: url,
        success: function (data) {
            codeCheck(data);
            resp(data);

        },
        error: function (data) {
            errorCheck(data);
        }
    })

}

//为活动添加一个表单项目
function saveActForm(actId, actForm, response) {
    var url = "/actForm/" + actId;
    $.ajax({
        url: url,
        type: "post",
        contentType: "application/json",
        data: JSON.stringify(actForm),
        success: function (data) {
            codeCheck(data)
            response(data);
        },
        error: codeCheck,
    })
}

// 获取活动表单列表
function getActFormList(actId, response) {
    var url = "/act/actFormlist/" + actId;
    $.ajax({
        url: url,
        type: "get",
        success: function (data) {
            codeCheck(data)
            response(data);
        },
        error: codeCheck,
    })
}

//获取参加的列表
function getMyPartakes(resp) {
    var url = "/partake/my";
    $.ajax({
        url: url,
        success: function (data) {
            codeCheck(data)
            resp(data);
        },
        error: errorCheck
    });
}

//获取个人参与活动的参与表/报名表
function getPartake(id, resp) {
    var url = "/partake/" + id;
    $.ajax({
        url: url,
        success: function (data) {
            codeCheck(data)
            resp(data);
        },
        error: errorCheck
    });
}

//参加活动
function joinAct(actId, resp) {
    var url = "/partake/join/" + actId;
    $.ajax({
        url: url,
        success: function (data) {
            codeCheck(data)
            resp(data);
        },
        error: errorCheck
    });
}

//检查返回数据
function codeCheck(data) {
    if (data.code !== 0) {
        if (data.msg != null && data.msg != "") {
            layui.layer.alert(data.msg);
        }
    }

}

//检查错误信息
function errorCheck(data) {
    console.log(data);
    if (data.responseJSON.error != null) {
        layui.layer.alert(data.responseJSON.error);
    }
    if (data.status === 410) {
        layui.layer.close();
        layui.layer.alert("未检测到您登陆系统,请前往登陆", function () {
            location.href = "/login.html";

        })
    }

}


var FORM_TYPE_SINGEL = 0;
var FORM_TYPE_MUTE = 1;
var FORM_TYPE_WRITE = 2;

//创建表单 dom对象
function createFormItemDoc(actForm) {
    var pre = $("<fieldset class='layui-elem-field'>"); //item dom容器
    var title = $("<h3 class='actForm-title'>");
    title = title.text(actForm.name);
    pre.append(title); // 添加标题
    pre.attr("id", actForm.id);
    pre.attr("type", actForm.type);

    var formItem = $("<div class='layui-form-item'>");
    var inputBlock = $("<div class='layui-input-block'>");
    formItem.append(inputBlock);

    switch (actForm.type) {
        case FORM_TYPE_MUTE:
            actForm.optionList.forEach(function (option) {
                var input = $(" <input type='checkbox' class='layui-input'/>");
                input.attr("name", actForm.id);
                input.attr("title", option.value);
                input.val(option.id)
                inputBlock.append(input);
            });
            break;
        case FORM_TYPE_SINGEL:
            actForm.optionList.forEach(function (option) {
                var input = $(" <input type='radio' class='layui-input'/>");
                input.attr("name", actForm.id);
                input.attr("title", option.value);
                input.val(option.id)
                inputBlock.append(input);
            });
            break;
        case  FORM_TYPE_WRITE:
            formItem.addClass("layui-form-text");
            inputBlock.append(" <textarea placeholder=\"请输入内容\" class=\"layui-textarea\" name='" + actForm.id + "' ></textarea>")
            break
    }
    pre.append(formItem);
    return pre;
}


//获取表单数据 基于layui.form
function getFormVal(filter) {
    filter = filter == null ? "" : filter;
    var data = layui.form.val(filter);


    var checkBoxes = {};
    $("input:checkbox").each(function () {
        checkBoxes[$(this).attr("name")] = [];
    });


    for (name in checkBoxes) {
        $("input:checkbox:checked[name=" + name + "]").each(function () {
            checkBoxes[name].push(this.value);
        })
    }

    for (name in checkBoxes) {
        data[name] = checkBoxes[name];
    }
    return data;
}


//提交表单
function submitActForm(partakeId, requestData, resp) {
    var url = "/partForm/partake/" + partakeId;
    $.ajax({
        url: url,
        contentType: "application/json",
        type: "post",
        data: JSON.stringify(requestData),
        success: function (data) {
            codeCheck(data);
            resp(data);
        },
        error: function (data) {
            errorCheck(data);
        }
    });
}


//发布活动
function releaseAct(actId, response) {
    var url = "/act/release/" + actId;
    $.ajax({
        url: url,
        success: function (data) {
            codeCheck(data);
            response(data);
        },
        error: errorCheck
    });
}


//生成参与活动的dom

function createPartDom(partake) {
    var pre = $("<li class='act-item'>");
    var title_dom = $("<h2>");
    title_dom.text(partake.act.name);
    title_dom.append("<span class='act-state'>" + PART_STATE[partake.state] + "</span>")
    pre.append(title_dom);

    var desc_dom = $("<p>");
    desc_dom.text(partake.act.describe);
    pre.append(desc_dom);

    var operate_dom = $("<div class='operate'>");
    partake.state === PART_STATE_ING ? operate_dom.append("<a class='layui-btn layui-btn-normal' href='/partake.html?partakeId=" + partake.id + "'>前往活动</a>") : ""
    pre.append(operate_dom);

    return pre;
}


//生成创建的活动 dom

function createActDom(act) {
    var pre = $("<li class='act-item'>");
    var title_dom = $("<h2>");
    title_dom.text(act.name);
    title_dom.append("<span class='act-state'>" + ACT_STATE[act.state] + "</span>")
    pre.append(title_dom);

    var desc_dom = $("<p>");
    desc_dom.text(act.describe);
    pre.append(desc_dom);

    var operate_dom = $("<div class='operate'>");

    operate_dom.append($("<a class='layui-btn layui-btn-primary' href='/dashboard.html?id=" + act.id + "'>前往仪表盘</a>"))
    pre.append(operate_dom);

    return pre;
}

//结束活动
function stopAct(actId, resp) {
    var url = "/act/stop/" + actId;
    $.ajax({
        url: url,
        success: function (data) {
            codeCheck(data);
            resp(data);
        },
        error: errorCheck,
    })
}


// 获取活动信息
function getActBaseInfo(actId, resp) {
    var url = "/act/baseInfo/" + actId;
    $.ajax({
        url: url,
        success: function (data) {
            codeCheck(data);
            resp(data);
        },
        error: errorCheck,
    })

}

function deleteAct(id, resp) {
    var url = "/act/" + id;
    $.ajax({
        url: url,
        type: "delete",
        success: function (data) {
            codeCheck(data);
            resp(data);
        },
        error: errorCheck
    })
}

//获取审核状态的参与
function getCheckParts(actId, resp) {
    var url = "/partake/check/" + actId;
    $.ajax({
        url: url,
        success: function (data) {
            codeCheck(data);
            resp(data);
        },
        error: errorCheck
    });
}

// part 通过
function partPass(id) {
    var url = "/partake/pass/" + id;
    $.ajax({
        url: url,
        success: function (data) {
            codeCheck(data);
            console.log(data)
            reload();
        }, error: errorCheck
    })
}

//part 驳回
function partReset(id) {
    var url = "/partake/reset/" + id;
    $.ajax({
        url: url,
        success: function (data) {
            codeCheck(data);
            reload();
        }, error: errorCheck
    })
}

//创建审核项 DOM
function createCheckItemDom(part) {
    var item = $("<dd>" +
        part.id +
        "</dd>");

    var a = $("<a class='layui-btn'  href='/partake.html?partakeId=" + part.id + "'>查阅</a>");
    var btn1 = $("<button class='layui-btn'>通过</button>");
    btn1.click(function () {
        partEvent(part.id, "pass");
    })
    var btn2 = $("<button class='layui-btn'>驳回</button>");
    btn2.click(function () {
        partEvent(part.id, "reset");
    });
    item.append(a);
    item.append(btn1);
    item.append(btn2);
    return item;
}

function partEvent(id, type) {
    switch (type) {
        case "pass":
            partPass(id);
            break
        case "reset":
            partReset(id);
            break;
    }
}

//获取用户输入的表单。
function getPartFormBypartake(id, resp) {
    var url = "/partForm/listByPart/" + id;
    $.ajax({
        url: url,
        success: function (data) {
            codeCheck(data);
            resp(data);

        },
        error: function (data) {
            errorCheck(data);

        }
    });
}

// 获取当前登录用户的信息
function getMyInfo(resp) {
    var url = "/user";
    $.ajax({
        url: url,
        success: function (data) {
            codeCheck(data);
            resp(data);
        },
        error: errorCheck,
    })
}

// 获得当前用户发布的活动
function getMyActListByState(state, page, resp) {
    var url = "/act/state/" + state;
    if (page.curr != null && page.size != null)
        url += "?curr=" + page.curr + "&size=" + page.size;
    else if (page.curr != null)
        url += "?curr=" + page.curr;

    $.ajax({
        url: url,
        success: function (data) {
            codeCheck(data);
            resp(data);
        },
        error: errorCheck,
    })

}

// 获得当前用户发布的活动
function getMyPartListByState(state, page, resp) {
    var url = "/partake/state/" + state;
    if (page.curr != null && page.size != null)
        url += "?curr=" + page.curr + "&size=" + page.size;
    else if (page.curr != null)
        url += "?curr=" + page.curr;

    $.ajax({
        url: url,
        success: function (data) {
            codeCheck(data);
            resp(data);
        },
        error: errorCheck,
    })

}