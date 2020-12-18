package com.example.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.models.Item;

public interface ItemRepository extends CrudRepository<Item, Long>{

}
