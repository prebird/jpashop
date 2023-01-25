package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemServiceTest {

    @Autowired ItemService itemService;

    @Test
    void 책_등록_테스트() {
        SaveBookDto saveBookDto = new SaveBookDto(null, "JPA책", 1000, 12,
                "김영한", "1122234");

        Long id = itemService.saveBook(saveBookDto);

        Book findBook = itemService.findOneBook(id);
        assertThat(findBook.getName()).isEqualTo(saveBookDto.getName()).withFailMessage("name 에러");
        assertThat(findBook.getAuthor()).isEqualTo(saveBookDto.getAuthor()).withFailMessage("author 에러");
        assertThat(findBook.getIsbn()).isEqualTo(saveBookDto.getIsbn()).withFailMessage("isbn 에러");
    }

}
