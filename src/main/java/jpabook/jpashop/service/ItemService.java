package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    /**
     * 상품 등록
     */
    @Transactional
    public void saveItem(SaveItemDto dto) {
        Item item = Item.createItem(dto.getId(), dto.getName(), dto.getPrice(), dto.getStockQuantity());
        itemRepository.save(item);
    }

    /**
     * 책 등록
     */
    @Transactional
    public Long saveBook(SaveBookDto dto) {
        Book book = new Book(dto.getId(), null, dto.getName(), dto.getPrice(), dto.getStockQuantity(),
                dto.getAuthor(), dto.getIsbn());
        itemRepository.save(book);
        return book.getId();
    }

    /**
     * 상품 조회
     */
    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    public Item findOneItems(Long findId) {
        return itemRepository.findOne(findId);
    }

    /**
     * 책 조회
     */
    public Book findOneBook(Long id) {return itemRepository.findOneBook(id); }
}
