
package com.japp.classes;

public class Cart {
    
    private double cartTotal;
    private Object[] cartItems;
    private double[] calValues;
    private double qty;
    
    public Cart(double cartTotal, Object[] cartItems, double[] calValues, double qty){
        this.cartTotal = cartTotal;
        this.cartItems = cartItems;
        this.calValues = calValues;
        this.qty = qty;
    }
 
    public Object[] addToCart() {
        String itemStringid = this.cartItems[0].toString();
        int itemID = Integer.parseInt(itemStringid);
        String itemName = this.cartItems[1].toString();
        String itemStringUnitPrice = this.cartItems[2].toString();
        double itemUnitPrice = Double.parseDouble(itemStringUnitPrice);
        double itemTotal = itemUnitPrice * this.qty;

        Object[] cartData = new Object[] {itemID, itemName, itemUnitPrice, this.qty, itemTotal};
        return cartData;
    }
    
    public boolean removeFromCart(){
        boolean result = true;
        return result;
    }

    public String calCartTotal() {
        double total = 0;
        for(int i = 0; i<this.calValues.length; i++){
            total = total + this.calValues[i];
        }
        String cartTotal = String.valueOf(total);
        return cartTotal;
    }
 
}
