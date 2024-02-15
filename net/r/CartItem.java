package net.r;

import java.io.Serializable;

public class CartItem implements Serializable{


    private static final long serialVersionUID = 50l;

    private String imgAddress;
    private String name;
    private int price;

    public CartItem(String imgAddress, String name, int price) {
        this.imgAddress = imgAddress;
        this.name = name;
        this.price = price;
    }

    public String getImgAddress() {
        return imgAddress;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        CartItem otherCartItem = (CartItem) obj;

        if(otherCartItem.getName().equals(name)) return true;
        else return false;
    }

    
    public boolean hashCode(Object obj) {
        
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        CartItem otherCartItem = (CartItem) obj;

        if(otherCartItem.getName().hashCode() == name.hashCode()) return true;
        else return false;
    }

}
