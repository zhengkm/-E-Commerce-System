/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

public class Products {

		private String product_ID;			   
		private String name;       
		private int inventory_amount;  
                private float price;
		private String kind;     
		private String brand; 
                private String description;
                
		public Products(){
			
		}
		public Products(String product_ID,String name,int inventory_amount,float price,String kind,String brand, String description){
			this.product_ID=product_ID;
			this.name=name;
			this.inventory_amount=inventory_amount;
                        this.price = price;
			this.kind=kind;
			this.brand=brand;
		}
		public String getProductID(){
			return product_ID;
		}
		public void setProductID(String product_ID){
			this.product_ID=product_ID;
		}
		
		public String getName(){
			return name;
		}
		public void setName(String name){
			this.name=name;
		}
		
		public int getAmount(){
			return inventory_amount;
		}
		public void setAmount(int inventory_amount){
			this.inventory_amount=inventory_amount;
		}
		
                public float getPrice(){
			return price;
		}
		public void setPrice(float price){
			this.price=price;
		}
		public String getKind(){
			return kind;
		}
		public void setKind(String kind){
			this.kind=kind;
		}
		public String getBrand(){
			return brand;
		}
		public void setBrand(String brand){
			this.brand=brand;
		}
                public String getDescription(){
			return description;
		}
		public void setDescription(String description){
			this.description=description;
		}
}
