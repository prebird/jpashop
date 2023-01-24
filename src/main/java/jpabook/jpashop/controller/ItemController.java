package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Item;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new ItemForm());
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
}
