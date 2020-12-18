package com.example.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.beans.CreateItemBean;
import com.example.beans.GetItemIdBean;
import com.example.beans.UpdateItemBean;
import com.example.models.Item;
import com.example.models.Lista;
import com.example.repositories.ItemRepository;
import com.example.repositories.ListaRepository;

@RestController
public class MainController {
	
	@Autowired private ListaRepository listaRepository;
	@Autowired private ItemRepository itemRepository;
	
	@CrossOrigin
	@PostMapping("/create/lista/{name}")
	public Lista createLista(@PathVariable("name") String name) {
		Lista lista = new Lista();
		lista.setName(name);
		return listaRepository.save(lista);
	}
	
	@CrossOrigin
	@PostMapping("/find/lista")
	public ArrayList<Lista> getLista() {
		return (ArrayList<Lista>) listaRepository.findAll();
	}
	
	@CrossOrigin
	@PostMapping("/create/item")
	public Item createItem(@RequestBody CreateItemBean itemBean) {
		Optional<Lista> opLista = listaRepository.findById(itemBean.getLista_id());
		Lista lista = opLista.get();
		
		if(lista.getId() != null) {
			List<Item> arrList = lista.getItems();
			
			Item item = new Item();
			item.setTarefa(itemBean.getTarefa());
			item.setLista(lista);
			
			arrList.add(itemRepository.save(item));
			
			listaRepository.save(lista);
			
			return item;
		}
		return new Item();
	}
	
	@CrossOrigin
	@PostMapping("/delete/item")
	public void deleteItem(@RequestBody GetItemIdBean itemIdBean) {
		itemRepository.deleteById(itemIdBean.getId());
	}
	
	@CrossOrigin
	@PostMapping("/update/item")
	public void changeItemToFinished(@RequestBody UpdateItemBean itemBean) {
		Optional<Item> opItem = itemRepository.findById(itemBean.getId());
		Item item = opItem.get();
		
		if(item.getId() != null) {
			item.setConcluida(itemBean.getConcluida());
			itemRepository.save(item);	
		}
	}
}
