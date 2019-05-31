package com.example.springrediscacheexample.cache;

import com.example.springrediscacheexample.model.Item;
import com.example.springrediscacheexample.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class ItemCache {

    @Autowired
    ItemRepository itemRepo;

    @Cacheable(value="itemCache", key="#p0")
    public Item getItem(int id){
        System.out.println("In getItem cache Component..");
        Item item = null;
        try{
            item = itemRepo.getItem(id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return item;
    }
    @CacheEvict(value="itemCache",key = "#p0")
    public void deleteItem(int id){
        System.out.println("In deleteItem cache Component..");
        itemRepo.deleteItem(id);
    }

    @Cacheable(value="itemCache",key = "#p0")
    public void addItem(Item item){
        System.out.println("In addItem cache component..");
        itemRepo.addItem(item);
    }

    @Cacheable(value="itemCache",key = "#p0",condition = "#result != null")
    public void updateItem(Item item){
        System.out.println("In UpdateItem cache Component..");
        itemRepo.updateItem(item);
    }


}
