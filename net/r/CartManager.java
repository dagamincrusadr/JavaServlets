package net.r;

import java.util.HashMap;
import java.util.Map;

public class CartManager {

    private Map<String, Map<CartItem, Integer>> userCarts;

    public CartManager() {
        userCarts = new HashMap<>();
    }

    public CartManager(Map<String, Map<CartItem, Integer>> userCarts) {
        this.userCarts = userCarts;

    }

    public Map<String, Map<CartItem, Integer>> getUserCarts() {
        return userCarts;
    }

    public Map<CartItem, Integer> getUserCart(String email) {
        return userCarts.get(email);

    }

    public void addToCart(String email, CartItem item) {
        if(userCarts.containsKey(email)) {
            if(userCarts.get(email).containsKey(item)) {
                Map<CartItem, Integer> userCart = userCarts.get(email);
                int itemCount = 1;
                if(userCart.containsKey(item)) itemCount += userCart.get(item);
                userCart.put(item, itemCount);
                userCarts.put(email, userCart);
            }
        }

    }
    
}
