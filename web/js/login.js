/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.onload = function() {
    var url = window.location.href;
    var start = url.indexOf("uid");
    var substr = url.substr(start);
    
    var flag = substr.indexOf("&");
    
    var uid = substr.substring(4,flag); // uid=
    var job_title = substr.substr(flag+11);// &job_title=
    
    var message = document.getElementById("message");
    message.innerHTML = "<p>Welcome, " + job_title + ", " + uid + "</p>";
    
    var showSalesData = document.getElementById("first");

    showSalesData.firstElementChild.href = "./EnterDataAggregation?title=" + job_title;
    
    
    
}

