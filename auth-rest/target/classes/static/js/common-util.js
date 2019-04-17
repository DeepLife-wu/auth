/**
 * 获取指定参数值
 * @param value
 * @returns
 */
function GetQueryString(value) {
     var reg = new RegExp("(^|&)"+ value +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null) return  unescape(r[2]); return null;
}

