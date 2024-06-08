package com.example.controller;

import com.example.entities.Item;
import com.example.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @QueryMapping
    public List<Item> items() {
        return itemRepository.findAll();
    }

    @QueryMapping
    public Optional<Item> item(Long id) {
        return itemRepository.findById(id);
    }

    @MutationMapping
    public Item createItem(String name, String description) {
        Item item = new Item();
        item.setName(name);
        item.setDescription(description);
        return itemRepository.save(item);
    }

    @MutationMapping
    public Item updateItem(Long id, String name, String description) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        item.setName(name);
        item.setDescription(description);
        return itemRepository.save(item);
    }

    @MutationMapping
    public Item deleteItem(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        itemRepository.delete(item);
        return item;
    }
}
