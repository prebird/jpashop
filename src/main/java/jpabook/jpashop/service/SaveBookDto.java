package jpabook.jpashop.service;

import lombok.AllArgsConstructor;

public class SaveBookDto extends SaveItemDto {

    // Setter를 만들지 않기 위해서 + 기본생성자를 만들지 않기 위해서
    // lombok을 사용하지 않고 직접 생성자를 만들었다.
    // 기본생성자를 만들면, setter가 있어야 ModelAttribute로 바인딩이 되기 때문 -> no 기본생성자
    public SaveBookDto(Long id, String name, int price, int stockQuantity, String author, String isbn) {
        super(id, name, price, stockQuantity);
        this.author = author;
        this.isbn = isbn;
    }
    private String author;
    private String isbn;
}
