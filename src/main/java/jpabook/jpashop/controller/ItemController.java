package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Item;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("items/new")
    public String createForm(Model model) {
        model.addAttribute("form", ItemForm.createEmpty());
        return "items/createItemForm";
    }

    @PostMapping("items/new")
    public String create(ItemForm itemForm) {
        Item item = Item.createItem(itemForm.getId()
                , itemForm.getName()
                , itemForm.getPrice()
                , itemForm.getStockQuantity());
        itemService.saveItem(item);
        return "redirect:/";
    }

    @GetMapping("items")
    public String list(Model model) {
        List<Item> items = itemService.findAllItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemService.findOneItems(itemId);
        ItemForm itemForm = ItemForm.createItemForm(item.getId(), item.getName()
                , item.getPrice(), item.getStockQuantity());
        model.addAttribute("form", itemForm);
        return "items/updateItemForm";
    }

    @PostMapping("items/{itemId}/edit")
    public String updateItem(@ModelAttribute("form") ItemForm itemForm) {
        Item item = Item.createItem(itemForm.getId(), itemForm.getName()
                , itemForm.getPrice(), itemForm.getStockQuantity());
        itemService.saveItem(item);
        return "redirect:/items";
    }

}
