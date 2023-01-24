package jpabook.jpashop.service;

import jpabook.jpashop.domain.Item;
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
     * 상품 조회
     */
    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    public Item findOneItems(Long findId) {
        return itemRepository.findOne(findId);
    }
}
