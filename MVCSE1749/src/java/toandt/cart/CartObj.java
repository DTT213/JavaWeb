/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toandt.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Toan
 */
public class CartObj implements Serializable {

    private Map<String, Integer> items;

    public CartObj() {
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public boolean addItemToCart(String id, Integer quantity) {
        boolean result = false;
        //1. check data validation
        if (id == null) {
            return result;
        }
        if (id.trim().isEmpty()) {
            return result;
        }
        if (quantity <= 0) {
            return result;
        }
        //2. check existed item
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        //3. check existed item
        if (this.items.containsKey(id)) {
            int currentQuantity = this.items.get(id);
            quantity = quantity + currentQuantity;
        }//end item has existed
        //4. update cart item;
        this.items.put(id, quantity);
        result = true;

        return result;
    }

    public boolean removeItemFromCart(String id, Integer quantity) {
        boolean result = false;
        //1. check data validation
        if (id == null) {
            return result;
        }
        if (id.trim().isEmpty()) {
            return result;
        }
        if (quantity <= 0) {
            return result;
        }
        //2. check existed item
        if (this.items == null) { //<at_here>
            return result;
        }
        //3. check existed item
        if (!this.items.containsKey(id)) {
            return result;
        }
        //4. remove items
        int currentQuantity = this.items.get(id);
        if (currentQuantity >= quantity) {
            quantity = currentQuantity - quantity;
        } else {
            return result;
        }
        if (quantity == 0) {
            this.items.remove(id);
            if (this.items.isEmpty()) {
                this.items = null;// when this is empty >> destroy so that we don't need to check empty <here>
            }
        } else {
            this.items.put(id, quantity);
        }
        result = true;
        return result;
    }
}
