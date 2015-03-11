<%-- 
    Document   : index
    Created on : 2014-11-27, 16:28:29
    Author     : zhengkaiming
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" type="text/javascript"></script>   
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>DSC</title>
<style type="text/css">
body{
	margin:0 auto;
	width: 1000px;

	}
#header{

	height:93px;
	width: 1000px;
	margin:0 auto;
	padding:0;
	//background-color: rgb(84,147,200);
	}
#Title{
    width:300px;
    height: 93px;
	float:left;
	font-size: 25px;

	}
#Title h1{
	height:93px;
	margin-top: 15px;
}
#login{
	
	float:right;
	width:420px;
	margin: 0px;
	padding-left: 20px;
        padding-right: 0;
        padding-top:10px;
        
}
#login h3{
	float: left;
	margin-right: 5px;
        font-size: 10px;
        padding-right: 0px;
}
#login h3 log{
	float: left;
	margin-right: 5px;
        padding-top: 10px;
}
#topMenu{
	margin:0 0 10px 0;
	padding:0;
	height:1px;
	background-repeat:repeat-x;
	//background-color: rgb(218,218,218);
        background: #383838;
	border-radius: 6px;
	-webkit-border-radius: 6px;
	-moz-border-rfadius: 6px;
	-o-border-radius: 6px;
	}
#firstUl{
	margin:0 auto;
	list-style-type:none;
	padding:0;
	width:910px;
	}



#container{
	width:1000px;
	margin:0 ;
	padding:0;
	
	}

#leftContent{
	width:280px;
	float:left;
	}
#leftContent ul li{
    border-bottom: 1px solid #EEE;
    //text-transform:uppercase;	     
        }
#rightContent{
	float:left;
	width:650px;
	margin-left:10px;
	margin-top: 2px;
		}
                
.leftNav3{
	height:45px;
	border: 2px #d6d6cf solid;
	background-repeat:repeat-x;}

.leftNav3 h2{
    	font-size:1.2em;
	color:#FFF;
	padding:10px;
	background:#B81D22;
	text-transform:uppercase;
	font-family: 'ambleregular';
}
.P01 h2{
	height:18px;
	margin:3px 0 16px 0 ;
	padding-left:10px;
	background-repeat:no-repeat;
	background-position:left;
	color:black;
	font:18px/28px ;
	font-weight:bold;
	}	
.rightNav3{
	height:600px;
	width:100%;
	padding:0 3px;
	}
.rightNav3 h3{
         padding-right: 10px;
         font-family: 'ambleregular';
         padding-left: 6px;
        }

.afterNav{
	list-style-type:none;
	padding:0 0 0 0px;
	margin-top: 5px;
	border: 2px #d6d6cf solid;
        border-bottom: 1px solid #EEE;

	}
.afterNav h3{
        font-size:1.2em;
	color:#FFF;
	padding:10px;
	background:#B81D22;
	text-transform:uppercase;
	font-family: 'ambleregular';  
        margin: 0px;
        }
.afterNav li a{
	text-decoration:none;
          	display:block;
	font-size:0.8em;
	padding:8px 15px;
        color: #9C9C9C;
        font-family: 'ambleregular';
        margin:0 20px;
	}
.afterNav li:hover{
	background-color:#ededed;
	border-top:1px #f8f8f8 solid;
	border-left:1px #f8f8f8 solid;
	}
.afterNav2{
         list-style-type:none;
	padding:0px;
	margin-top: 5px;
	border: 2px #d6d6cf solid;   
        }
.afterNav2 h3{
       font-size:1.2em;
	color:#FFF;
	padding:10px;
	background:#B81D22;
	text-transform:uppercase;
	font-family: 'ambleregular';  
        margin: 0px;         
        }
.afterNav2 li{
        
	padding-left:52px;
	padding-top: 5px;
	background-repeat:no-repeat;
	background-position:38px center;
	}
        
.shopping {	
	margin-top: 5px;
	border: 2px #d6d6cf solid;
        padding-left:52px;
	padding-top: 5px;
	background-repeat:no-repeat;
	background-position:38px center;
        }
.shopping a{
            text-decoration: none;
            font-size: 20px;
	    color:#B81D22; 
        }
.afterNav1{
    list-style-type:none;
	padding:20px 0 0 2px;
	margin-top: 5px;

}

.afterNav1 li a{
	text-decoration:none;
	font:20px/25px ;
	color:#000; 
	padding-top: 20px;
	margin:3px 0 16px 0 ;
	padding-left:55px;
	}
        
#msgDiv {
    z-index:10001;
    width:500px;
    height:400px;
    background:white;
    border:#336699 1px solid;
    position:absolute;
    left:50%;
    top:20%;
    font-size:15px;
    margin-left:-225px;
    display: none;
}
#bgDiv {
    display: none;
    position: absolute;
    top: 0px;
    left: 0px;
    right:0px;
    //background-color: white;
    filter:progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=25,finishOpacity=75)
}
.pname{
    width:50px;
}


#item tr td{
    padding-right: 10px;
    text-align: center;
    border-bottom: 1px solid #EEE;
    border-top: 1px solid #EEE;
}
#item2{
    margin-right: auto;
    margin-left:  auto;
    
}
#item2 tr td{
    padding-right: 10px;
    text-align: center;
    //border: 1px solid #EEE;
}
#buy input{
   
    margin-left: auto;
    margin-right: auto;
    font-size: 15px;
    

}

#buy{
     padding-left: 140px;
     margin-left: auto;
    margin-right: auto;
}

＃item table{
     border: 2px #d6d6cf solid;
}
#item tr{
    width: 500px;
    
   
}

#item tr td{
    width:90px;
    height: 30px;
    //border-bottom: 2px #d6d6cf solid;
    
}
#div_cart table tr{
   width: 500px; 
}
#div_cart table tr td{
    width:90px;
    height: 30px; 
   // border: 1px solid #EEE;
}

#needInput{
        margin-left: auto;
    margin-right: auto;
    padding-top: 5px;
    padding-left: 0px;
}
#sum{
    padding-left: 6px;
    padding-top: 10px;
}
#needInput input{
        margin-left: auto;
    margin-right: auto;
    //margin-top: 0px;
    padding-top: 0px;
    padding-left: 0px;
}
</style>

<script LANGUAGE="javascript"> 
function openwin(){ 

  var bgObj=document.getElementById("bgDiv");
  bgObj.style.width = document.body.offsetWidth + "px";
  bgObj.style.height = screen.height + "px";


  var msgObj=document.getElementById("msgDiv");
  msgObj.style.marginTop = -75 +  document.documentElement.scrollTop + "px";

  var sum=0;
  var id=null;
   $("#cart").children().children().each(function(index){
           

            var price=$(this).children().eq(3).text();
            var amount=$(this).children().eq(2).text();
            id=$(this).children().eq(0).text();
            //array.data[index]={"id":id,"amount":amount,"salesperson_id":salesperson_id,"customer_ID":customer_ID};
            sum=parseFloat(sum) + parseFloat(amount)*parseFloat(price);
            //alert(sum);
            
        });
        if($("#sum").children().length>0)
        {
          
        $("#sum").children().children().eq(1).text(""+sum+"");
                                
        }else{
        $("#sum").append("<tr id=\""+sum+"\"><td name='data'>Total:</td><td name='data'>"+sum+"</td></tr>");}
    
  document.getElementById("msgShut").onclick = function(){
  bgObj.style.display = msgObj.style.display = "none";
  };
  msgObj.style.display = bgObj.style.display = "block";
  //msgDetail.innerHTML="<p align=center>1111</p><p align=center><A href=http://www.jscode.cn><FONT color=#0000ff>111</FONT></A></p>"
}

</script>

<script type="text/javascript">
       function getData(){
       var array={"data":[]};
       var customer_ID=$("#customer_ID").val();
       //var BuyDate=$("#BuyDate").val();
       var salesperson_id=$("#salesperson_id").val();
       //var sum = 0;
       $("#cart").children().children().each(function(index){
           

            var id=$(this).children().eq(0).text();
            var amount=$(this).children().eq(2).text();
            array.data[index]={"id":id,"amount":amount,"salesperson_id":salesperson_id,"customer_ID":customer_ID};
            //sum=parseInt(sum) + parseInt(amount);
        //alert(sum);
            
        });
        
       // $("#sum").append("<h3>Total:"+sum+"</h3>");
        var jstring=JSON.stringify(array);
                   $.post(
                    "./Servlet",
            {"data":jstring},
                     function(data){  
                         if(data==0){
                             alert("you should input right information!");
                             return false;
                         }
                         window.location.href="sucess.jsp";
                                 //$("#cart").remove();
                         //alert("sucess");
                     },
                    "text" );
       
                    
    };
</script>
<script type="text/javascript">
 
         function FindandDisplay()
        {
            var Name=$("#Name").val();
            var Brand=$("#Brand").val();
            var Price=$("#Price").val();
             var Kind=$("#Kind").val();
             var sort=$("#sort").val();
            if(Name==""){Name="%";};
            if(Brand==""){Brand="%";};
            if(Kind==""){Kind="%";};
            
            
            $.post(
                    "./FindItem",
                    {"Name":Name,"Brand":Brand,"Price":Price,"Kind":Kind,"sort":sort},
                     function(data){                    
                         $("#tableofitem").html(data);  
                         eventbind();
                     },
                    "text" );
        };
        

        </script>


<script type="text/javascript">
           function eventbind()
            {
                $("#item").find("button").bind("click",function()
                {
                    var name=$(this).parent().parent().children().eq(0).text(); 
                    var price=$(this).parent().parent().children().eq(2).text(); 
                    var quantity=$(this).parent().parent().children().eq(6).children().val();
                    var max=$(this).parent().parent().children().eq(1).text();
                    var id=$(this).parent().parent().attr("class");
                    
                    //alert(max);
                    if (parseInt(quantity)>parseInt(max))
                    {
                        alert("You buy too much item!");
                        $(this).parent().parent().children().eq(6).children().val(max);
                    }
                    else if(parseInt(max)==0)
                    {
                        alert("this item do not have inventory now, sorry!");
                        $(this).parent().parent().children().eq(6).children().val(max);

                    }
                    else if(parseInt(quantity)==0)
                    {
                        alert("please input quantity you want");
                    }
                    else
                    {
                        $(this).parent().parent().children().eq(1).text(parseInt($(this).parent().parent().children().eq(1).text())-parseInt(quantity));
                        if($("#"+id).length>0)
                        {

                                $("#"+id).children().eq(2).text(parseInt($("#"+id).children().eq(2).text())+parseInt(quantity));
                                alert("Successful purchase "+name+" for "+quantity+" again !");
                                //$(this).parent().parent().children().eq(1).text();
                                
                            
                        }
                        else
                        {    
                            $("#cart").append("<tr id=\""+id+"\"><td name='data'>"+id+"</td><td>"+name+"</td><td>"+quantity+"</td><td>"+price+"</td><td><button class=\"delete\">delete</button></td></tr>");
                            //$(this).parent().parent().children().eq(1).children().text(max-parseInt($("#"+id).children().eq(1).text()));
                                
                            alert("Successful purchase "+name+" for "+quantity);
                            binddelete(id);
                        }
                    }
                   
                });
            };
       </script>

                            
<script type="text/javascript">
           function binddelete(id)
           {
               $("#"+id).find("button").click(function()
                {
                    $(this).parent().parent().remove();
                    alert("successful delete the items.");
                });
           };
       </script>
<script type="text/javascript">
           function SearchByKind(kind)
           {
                var Name="%";
                var Brand="%";
                var Kind=kind;
                var Price="0";
                var sort="asc";
            
            $.post(
                    "./FindItem",
                    {"Name":Name,"Brand":Brand,"Price":Price,"Kind":Kind,"sort":sort},
                     function(data){                    
                         $("#tableofitem").html(data);  
                         eventbind();
                         
                     },
                    "text" );
           }
       </script>

  <script type="text/javascript">
         $(document).ready(function()
        {
            $("#log").click(function()
            {
                var username=$("#username").val();
                var password=$("#password").val();
                if(username=="" || password=="")
                    alert("Please finish all information!");
                else
                {
                    $.post(
                            
                    "./Login",
                    {"username":username,"password":password},
                     function(data){  
                         if(data!=="false")
                        {
                            //alert(data);
                            window.location.href = "DSC.html?uid=" + $("#username").val() + "&job_title="+data;
                         }
                         else
                            alert("Username or Password wrong! Please check your enter");
                     },
                    "text" );
                }
            });
                    
            
        });
    </script>
</head>

<body>
  <div id="header">
    <div id="Title">
      <h1><span>DSC.com</span></h1>
    </div>
    <div id="login">	
    	<h3>Name <input id="username" type="text"/></h3>
    	<h3>Password <input id="password" type="password"/></h3>
    	<h3 ><button id="log">LOG IN</button></h3>
    </div>
  </div>

  <div id="topMenu">
    <ul id="firstUl">
      <li id="first"><a href="#"></a></li>
    </ul>
  </div>

  <div id="container">
  	<div id="leftContent">
         <ul class="afterNav">
             <h3>Categories</h3>
             <li><a href="#" onclick="SearchByKind('shoe')">
                     Shoe
                 </a> 
             </li>
        <li><a href="#" onclick="SearchByKind('computer')">
                Computer
            </a>
        </li>
        <li><a href="#" onclick="SearchByKind('TV')">
                TV
            </a>
        </li>
        <li><a href="#" onclick="SearchByKind('phone')">
                Phone
            </a>
        </li>
        </ul>
        <ul class="afterNav2">
            <h3>Search</h3>
            <li >Name:  <input id="Name" type="text" /></li>
            <li>Brand: <input id="Brand" type="text"/></li>
            <li>kind: <input id="Kind" type="text" /></li>
            <li>Price : 
                <select id="Price">
                    <option value="0">None</option>
                    <option value="1">0-499</option>
                    <option value="2">500-999</option>
                    <option value="3">1000-1499</option>
                    <option value="4">1500-1999</option>
                    <option value="5">2000-upper</option>
                </select>
            </li>
            <li >SortByPrice:
                <select id="sort">
                    <option value="asc" selected="selected">Ascend</option>
                    <option value="desc">Descend</option>
                </select>
            </li>
            <li><button id="submit" onclick="FindandDisplay()">Search</button></li>
        </ul>
        <div class="shopping" ><a href="#"onclick="openwin()">Shopping Cart</a>
        </div>
    </div>

    <div id="rightContent">
    	<div class="rightNav3 P01">
         <h2>Product Information</h2>
         <div id="tableofitem">
        </div> 
         <div id="msgDiv">
         <div id="div_cart">
          <div id="item2">
           <table>
           <tr>
            <td>Product_ID</td>
            <td>Name</td>
            <td>Amount</td>
            <td>Price</td>
           </tr>
            <table id="cart">
            </table>
            
           </table>
              
         </div>
             <div id="sum"></div>
             <div id="needInput">
                 
                 <table>
                 <tr>
                 <td>customerID:</td>
                 <td><input type="text" id="customer_ID" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " ></input></td>
                 
                 </tr>
                 <tr>
                 <td>salesPersonID:</td>
                 <td><input type="text" id="salesperson_id"onkeyup="this.value=this.value.replace(/[^\d]/g,'') " ></input></td>
                 
                 </tr>
                 </table>
               
             </div>
            <div id="buy">
             <input type="submit" onClick="getData()"value="buy"/>
              <input type="submit" id="msgShut" value="close"/>
            </div>
        </div>
        </div>
       <div id="bgDiv">
<!--       </div>

          <div id="div_cart">
            <table id="carts">
            </table> 
             
        </div>-->
         
        </div>
    </div>
  </div>

</body>
</html>