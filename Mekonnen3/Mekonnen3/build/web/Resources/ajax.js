var httpRequest = false;
//var token = document.getElementById("id").value;
//var url = 'EventServlet?token=' + token;
window.onload = hrObject;
function hrObject() {

    if (window.XMLHttpRequest) {
        httpRequest = new XMLHttpRequest();
    } else {
        if (window.ActiveXObject) {
            try {
                httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {
            }
        }
    }
    getData();
}
function getData() {
    var token = document.getElementById("id").value;
    var url = 'EventServlet?token=' + token;
    httpRequest.open("GET", url, true);
    httpRequest.onreadystatechange = showData;
    httpRequest.send();
    setTimeout("getData()", 10000);
}
function showData() {
    if (this.readyState == 4 && this.status == 200) {
        // alert(this.responseText);
        my_json_obj = JSON.parse(this.responseText);
        document.getElementById("id").value = my_json_obj.id;
        document.getElementById("event").innerHTML = my_json_obj.event;
    } else {
        //alert("Error with loading datan" + httpRequest.status + ":" + httpRequest.s);
    }
}



