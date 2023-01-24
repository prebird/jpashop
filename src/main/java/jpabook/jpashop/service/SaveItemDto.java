package jpabook.jpashop.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SaveItemDto {
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
}
