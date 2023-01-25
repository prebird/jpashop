package jpabook.jpashop.controller;

import lombok.*;

@Getter
@AllArgsConstructor
public class ItemForm {

    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    public static ItemForm createItemForm(Long id, String name, int price, int stockQuantity) {
        ItemForm itemForm = new ItemForm(id, name, price, stockQuantity);
        return itemForm;
    }

    public static ItemForm createEmpty() {
        ItemForm itemForm = new ItemForm(null, null, 0, 0);
        return itemForm;
    }
}
