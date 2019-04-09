var formatJson = function (json, options) {
    var reg = null,
        formatted = '',
        pad = 0,
        PADDING = '    '; // one can also use '\t' or a different number of spaces

    // optional settings
    options = options || {};
    // remove newline where '{' or '[' follows ':'
    options.newlineAfterColonIfBeforeBraceOrBracket = (options.newlineAfterColonIfBeforeBraceOrBracket === true) ? true : false;
    // use a space after a colon
    options.spaceAfterColon = (options.spaceAfterColon === false) ? false : true;

    // begin formatting...
    if (typeof json !== 'string') {
        // make sure we start with the JSON as a string
        json = JSON.stringify(json);
    } else {
        // is already a string, so parse and re-stringify in order to remove extra whitespace
        json = JSON.parse(json);
        json = JSON.stringify(json);
    }

    // add newline before and after curly braces
    reg = /([\{\}])/g;
    json = json.replace(reg, '\r\n$1\r\n');

    // add newline before and after square brackets
    reg = /([\[\]])/g;
    json = json.replace(reg, '\r\n$1\r\n');

    // add newline after comma
    reg = /(\,)/g;
    json = json.replace(reg, '$1\r\n');

    // remove multiple newlines
    reg = /(\r\n\r\n)/g;
    json = json.replace(reg, '\r\n');

    // remove newlines before commas
    reg = /\r\n\,/g;
    json = json.replace(reg, ',');

    // optional formatting...
    if (!options.newlineAfterColonIfBeforeBraceOrBracket) {
        reg = /\:\r\n\{/g;
        json = json.replace(reg, ':{');
        reg = /\:\r\n\[/g;
        json = json.replace(reg, ':[');
    }
    if (options.spaceAfterColon) {
        reg = /\:/g;
        json = json.replace(reg, ':');
    }

    $.each(json.split('\r\n'), function (index, node) {
        var i = 0,
            indent = 0,
            padding = '';

        if (node.match(/\{$/) || node.match(/\[$/)) {
            indent = 1;
        } else if (node.match(/\}/) || node.match(/\]/)) {
            if (pad !== 0) {
                pad -= 1;
            }
        } else {
            indent = 0;
        }

        for (i = 0; i < pad; i++) {
            padding += PADDING;
        }

        formatted += padding + node + '\r\n';
        pad += indent;
    });

    return formatted;
};

/**
 * 图片放大
 * @param imgesOb
 * @param titlese
 * @param divid
 */
function openImgesDiv(imgesOb, titlese, divid) {
    var renderDiv = null;
    if (!divid) {
        renderDiv = $(document.body);
    } else {
        renderDiv = $("#" + divid);
    }
    var imgesUrlPath = imgesOb.src;
    renderDiv.append("<div  onclick='closeImgesDivWindow();' id='tranDiv'><div id='tranDivBack'></div><div align='center' id='infoDiv'></div> </div>");
    $("#infoDiv").html("" + "<img src='" + imgesUrlPath + "'  title='" + (titlese || "") + "'/>");
    $("#infoDiv").attr("style", "position:absolute;left:0px; top:0px;z-Index:2147483647;width:100%;height:100%;");
    $("#tranDivBack").attr("style", "position:absolute;left:0px; top:0px; height:100vh; width:" + document.body.clientWidth + "px;background-color:#E4E4E4;opacity:0.2;z-Index:1147483647;");
    $("#tranDiv").attr("style", "position:absolute;top:0px;left:0px;margin:0 auto;text-align:center;z-Index:2147483647;height:100vh;width:" + document.body.clientWidth + "px;");
    $("#tranDiv").fadeIn("slow");
}

function closeImgesDivWindow() {
    $("#tranDiv").fadeOut("slow");
}

/** * 包括cookie的读取(get)、添加(set)、删除(dek)操作 */
function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toGMTString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i].trim();
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function delCookie(cname) {
    document.cookie = cname + "=";
}
