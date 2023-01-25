package jpabook.jpashop.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class SaveItemDto {
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
}
