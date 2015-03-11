/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


window.onload = function() {
    
    var mainMenu = document.getElementById("mainMenu");
    var li = mainMenu.getElementsByTagName("li");
    var input = mainMenu.getElementsByTagName("input");
    
    if (li.length === 7) {
        li[0].onclick = function() {
            document.getElementById("customerID").parentNode.style.display = "none";
            document.getElementById("customerID").value = "";
            $.post("./BestSaleperson",
                function(data){
                    $( ".Inventory_Record_TB_box" ).html(data);
                }
             );
        }
    
       
        li[1].onclick = function() {
            document.getElementById("customerID").parentNode.style.display = "none";
            document.getElementById("customerID").value = "";
            $.post("./BCustomer_Buying_Most",
                function(data){
                    $( ".Inventory_Record_TB_box" ).html(data);
                }
             );
        }



        li[2].onclick = function() {
            document.getElementById("customerID").parentNode.style.display = "none";
            document.getElementById("customerID").value = "";
            $.post("./SalesAndProfits",
                function(data){
                    $( ".Inventory_Record_TB_box" ).html(data);
                }
             );
        }


        li[3].onclick = function() {
            document.getElementById("customerID").parentNode.style.display = "none";
            document.getElementById("customerID").value = "";
            $.post("./Region_SalesVolume",
                function(data){
                    $( ".Inventory_Record_TB_box" ).html(data);
                }
             );
        }
        
        li[4].onclick = function() {
            document.getElementById("customerID").parentNode.style.display = "block";
             $( ".Inventory_Record_TB_box" ).html("");
        }
        
        li[6].onclick = function() {
            document.getElementById("customerID").parentNode.style.display = "none";
            document.getElementById("customerID").value = "";
            $.post("./ReturnProduct",
                function(data){
                    $( ".Inventory_Record_TB_box" ).html(data);
                }
             );
        }
        
        input[1].onclick = function() {
            var id = document.getElementById("customerID").value;
            if (id == "") {
                alert("please input a customer_ID");
                return;
            }
            var reg = new RegExp("^[0-9]*$");
            if(!reg.test(id)){
                alert("customer_ID must be number");
                retuen;
            }
            
            $.post("./CustomerTracking",
                {"id":id},
                function(data){
                    $( ".Inventory_Record_TB_box" ).html(data);
                }
             );
        }
        
    } else if (li.length === 4){
        li[0].onclick = function() {
            document.getElementById("customerID").parentNode.style.display = "none";
            document.getElementById("customerID").value = "";
            $.post("./BestSaleperson",
                function(data){
                    $( ".Inventory_Record_TB_box" ).html(data);
                }
             );
        }
        
        li[1].onclick = function() {
            document.getElementById("customerID").parentNode.style.display = "block";
             $( ".Inventory_Record_TB_box" ).html("");
        }
        
        li[3].onclick = function() {
            document.getElementById("customerID").parentNode.style.display = "none";
            document.getElementById("customerID").value = "";
            $.post("./ReturnProduct",
                function(data){
                    $( ".Inventory_Record_TB_box" ).html(data);
                }
             );
        }
        
        input[1].onclick = function() {
            var id = document.getElementById("customerID").value;
            
            $.post("./CustomerTracking",
                {"id":id},
                function(data){
                    $( ".Inventory_Record_TB_box" ).html(data);
                }
             );
        }
    } else {
        li[0].onclick = function() {
            document.getElementById("updateID").parentNode.style.display = "none";
            document.getElementById("deleteID").parentNode.style.display = "none";
            document.getElementById("updateID").value = "";
            document.getElementById("deleteID").value = "";
            document.getElementById("create").style.display = "block";
            document.getElementById("update").style.display = "none";
            document.getElementById("delete").style.display = "none";
            
        }
        
        li[1].onclick = function() {
            document.getElementById("updateID").parentNode.style.display = "block";
            document.getElementById("deleteID").parentNode.style.display = "none";
            //document.getElementById("updateID").value = "";
            document.getElementById("deleteID").value = "";
            document.getElementById("create").style.display = "none";
            document.getElementById("update").style.display = "block";
            document.getElementById("delete").style.display = "none";
            $( "#update" ).html("");
        }
        
        li[3].onclick = function() {
            document.getElementById("updateID").parentNode.style.display = "none";
            document.getElementById("deleteID").parentNode.style.display = "block";
            document.getElementById("updateID").value = "";
            //document.getElementById("deleteID").value = "";
            document.getElementById("create").style.display = "none";
            document.getElementById("update").style.display = "none";
            document.getElementById("delete").style.display = "block";
            $( "#delete" ).html("");
        }
        
        input[1].onclick = function() {
            var id = document.getElementById("updateID").value;
            if (id == "" ) {
                alert("please input product_ID!");
                return;
            }
            var reg = new RegExp("^[0-9]*$");
            if(!reg.test(id)){
                alert("customer_ID must be number");
                retuen;
            }
            $.post("./showProductInfo",
                {"id":id},
                function(data){
                    $( "#update" ).html(data);
                    $("#updateProduct").click(function(){
                        var productid=$("#productID1").val();
                        var name=$("#name1").val();
                        var price=$("#price1").val();
                        var kind=$("#kind1").val();
                        var brand=$("#brand1").val();
                        var description=$("#description1").val();                
                        if(productid=="" || name=="" || price=="" || kind=="" || brand=="" || description=="")
                            alert("Please finish all information!");
                        else
                        {
                            $.post(
                            "./UpdateProduct",
                            {"productid":productid,"name":name,"price":price,"kind":kind,"brand":brand,"description":description},
                             function(data){                    
                                if (data == "0") alert("fails to update infomation, please try again");
                             },
                            "text" );
                        }
                    });
                }
             );
        }
        
        input[3].onclick = function() {
            var id = document.getElementById("deleteID").value;
            if (id == "" ) {
                alert("please input product_ID!");
                return;
            }
            var reg = new RegExp("^[0-9]*$");
            if(!reg.test(id)){
                alert("customer_ID must be number");
                retuen;
            }
            $.post("./DeleteProduct",
                {"id":id},
                function(data){
                    if (data == "1") {
                        $( "#delete" ).html("Delete successfully!");
                    } else if (data == "0") {
                        $( "#delete" ).html("fails to delete product, please try again");
                    } else {
                        $( "#delete" ).html(data);
                    }
                }
             );
        }
    }
    
    
    
    
}

