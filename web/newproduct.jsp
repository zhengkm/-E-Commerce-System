<%-- 
    Document   : newproduct
    Created on : 2014-11-28, 18:15:37
    Author     : Kehao Xu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="./js/ForDataAggregation.js"></script>
        <title>JSP Page</title>
        <style type="text/css">
 body{
	margin:0;
	padding:0;
	font-family:Arial, Helvetica, sans-serif;
	font-size:12px;
	}
ul{
	list-style-type:none;
	margin:0;
	padding:0;
	}
#container{
	width:765px;
	margin:10px auto;
	position:relative;
	}
#h1{
	border-bottom:2px #DDD solid;
	border-top:6px #DDD solid;
        height:80px;
	margin-top: 0px;
        margin-bottom: 0px;
        font-size: 40px;
        color: #878484;
	/*background-image:url('./images/logo.png');*/
	/*background-repeat:no-repeat;*/
	}
#h1 p {
        font-size: 20px;
        margin: 0px;
        padding-left:3px; 
}
#showDataDiv{
	width:510px;
	/*height:500px;*/
	/*background-image:url('Banner.png');*/
	margin:5px 0 0 0;
	float:right;
	/*border-top: 1px solid #C4C4C4;*/
	/*border-bottom: 1px solid #C4C4C4;*/
	}
#topMenu{
	position:absolute;
	right:0;
	top:6px;
	}
#topMenu li{
	float:left;
	padding:20px 10px 0;
	border-left:1px #ddd solid;
	}
#topMenu li#first{
	border:none;
	}
#topMenu li a{
	color:gray;
	text-decoration:none;
	}
#topMenu li a:hover{
	text-decoration:underline overline;
	}
#narrow{
	width:235px;
	float:left;
	padding:10px;
	}
#narrow #mainMenu{
	margin:0 40px 10px 0px;
	font-size:12px;
	line-height:20px;}
#narrow #mainMenu li{
	border-bottom:1px #DDD solid;
	}
#narrow #mainMenu li a{
	display:block;
	color:#555;
	text-decoration:none;
	font-weight:bold;
	padding:3px 0; 
	padding-left:50px;
	background-image:url('./images/bullet-green.gif');
	background-repeat:no-repeat;
	background-position:left center;
	}
#narrow #mainMenu li a:hover{
	background-image:url('./images/bullet-red.gif');
	}
#narrow #mainMenu li.last{
	border-bottom:1px white solid;
	}
#narrow form{
	background-image:url('./images/search-background.gif');
	text-align:center;
	padding-top:11px;
	padding-bottom:0px;
	height:36px;
	margin:10px 0;
	}
#narrow #news h3{
	margin:10px 0;
	font-size:15px;
	}
#narrow #news p{
	margin:0;
	}
#narrow .newsTitle{
	color:#47C;
	font-size:12px;
	font-weight:bold;
	background-image:url('./images/arrow.gif');
	background-repeat:no-repeat;
	background-position:left;
	margin:10px 0 0 -10px;
	padding-left:10px;
	}
#narrow .newsDate{
	color:#777;
	font-weight:bold;
	}
#narrow .newsContent{
	font-size:11px;
	color:#777;
	}
.contentBox{
	width:245px;
	float:left;
	padding:0 5px;
	}
.orange h3{
	background-color:orange;
	}
.green h3{
	background-color:green;
	}
.contentBox h3{
	font-size:15px;
	color:white;
	margin:5px -2px 5px -5px;
	line-height:1.5;
	padding-left:5px;
	}
.contentBox ul{
	margin-left:2em;
	font-weight:bold;
	color:#666;
	list-style-type:circle;
	}
.floatLeft{
	float:left;
	margin-right:5px;
	}
#footer{
	clear:both;
	}
#footer ul{
	margin-top:15px;
	height:30px;
	border-top:10px #ddd solid;
	border-bottom:10px #ddd solid;
	}
#footer ul li{
	width:254px;
	float:left;
	height:30px;
	background-color:#ddd;
	text-align:center;
	line-height:30px;
	border-left:1px #bbb solid;
	}
#footer .first{
	border:none;
	width:255px;
	}

/*showDataTable*/
.Inventory_Record_TB_box {
	width:  510px;
	/*height: 200px;*/
	margin-top: 10px;
	font: 12px "微软雅黑",Arial,"Helvetica Neue","Liberation Sans",FreeSans,sans-serif;
	}
.Inventory_Record_thead {
	/*position: absolute;*/
	/*border-top: 2px solid #C4C4C4;*/
	left: 0px;
	top: 0px;
	width: 510px;
	height: 25px;
}
.Inventory_Record_thead ul {
	width: 510px;
	height: 25px;
	list-style: outside none none;

}
.Inventory_Record_thead ul li {
	float: left;
	height: 25px;
	/*width: 125px;*/
	line-height: 25px;
	text-align: center;
	border-right: 1px solid #C4C4C4;
	background-color: #D2D2D2;
}
.Inventory_Record_TB {
	height: 400px;
	color: #797979;
	font-size: 12px;
	overflow-y: scroll; 
}
.Inventory_Record_TB table tr td {
	padding: 4px 0px;
	text-align: center;
	border-right: 1px solid #D9D9D9;
/*	width: 25px;
*/}
.even {
	background-color: #FFF;
}
.odd {
	background-color: #F1F1F1;
}
.len6 {
	width: 125px;
}
.len8 {
	width: 250px;
}
.len1 {
    width:50px;
    margin-left: 50px;
}
.len2 {
    width: 100px;
}
ul {
	list-style-type:none;
	margin-top:10px;
	padding:0px;
	}
        ul li {
           margin:0px;
           padding:10px; 
        }
        </style>
    </head>
    <body>
       <div id="container">
      <div id="h1"><span>DSC.com</span><p>Data Center</p>
    </div>
    <div id="narrow">
       <ul id="mainMenu">
            <li><a href="#">Create a new product</a></li>
            <li><a href="#">Update a product information</a></li>
            <li style="display:none;">
                <input id="updateID" class="len1" type="text" value="" />
                <input type="button" value="submit" />   
            </li>
            <li><a class="last" href="#">Delete a product</a></li>
            <li style="display:none;">
                <input id="deleteID" class="len1" type="text" value="" />
                <input type="button" value="submit" />   
            </li>
        </ul>
     </div>
           <div id="showDataDiv">
           <ul id="create">
            <li>product_ID:&nbsp;<input id="productID" type="text"/></li>
            <li>name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="name" type="text"/></li>
            <li>price:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="price" type="text"/></li>
            <li>kind:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="kind" type="text"/></li>
            <li>brand:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="brand" type="text"/></li>
            <li>description:   <p></p><textarea rows="10" cols="30" id="description" ></textarea></li>            
            <li><button id="store">Create new product !</button></li>
        </ul>
               <ul id="update"></ul>
               <ul id="delete"></ul>
        </div>
    
    <script type="text/javascript">
        $(document).ready(function()
        {
            $("#store").click(function()
            {
                var productid=$("#productID").val();
                var name=$("#name").val();
                var price=$("#price").val();
                var kind=$("#kind").val();
                var brand=$("#brand").val();
                var description=$("#description").val();                
                if(productid=="" || name=="" || price=="" || kind=="" || brand=="" || description=="")
                    alert("Please finish all information!");
                else if(isNaN(parseInt(productid.length))||productid.length!=7)
                    alert("Please enter right product_ID!");
                else if(isNaN(parseInt(price)) || parseInt(price)<0 )
                    alert("Please enter right price!");
                else if(kind.length>10)
                    alert("Please enter right kind!");
                else if(brand.length>20)
                    alert("Please enter right brand!");
                else if(name.length>20)
                    alert("Please enter right name!");
                else
                {
                    $.post(
                    "./NewProduct",
                    {"productid":productid,"name":name,"price":price,"kind":kind,"brand":brand,"description":description},
                     function(data){                    
                        alert(data);
                        $("#productID").val("");
                        $("#name").val("");
                        $("#price").val("");
                        $("#kind").val("");
                        $("#brand").val("");
                        $("#description").val(""); 
                     },
                    "text" );
                }
            });
                    
            
        });
    </script> 
    </body>
</html>
