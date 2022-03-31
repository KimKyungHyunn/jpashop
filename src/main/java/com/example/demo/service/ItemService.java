package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.item.Book;
import com.example.demo.domain.item.Item;
import com.example.demo.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor

public class ItemService {	
	
	private final ItemRepository itemRepository;
	
	@Transactional
	public void saveItem(Item item) {
		itemRepository.save(item);
	}
	
	@Transactional
	//준영속 엔티티 변경 1: 변경감지기능 사용 (dirty checking)
	public void updateItem(Long itemId, Book param) {
		Item findItem = itemRepository.findOne(itemId);
		findItem.setPrice(param.getPrice());
		findItem.setName(param.getName());
		// 아래 set으로 값 넣기 반복
		// 이후 알아서 flush 날림 = 영속성 컨텍스트가 변경 사항을 다 찾아보고 변경 반영
		saveItem(findItem);
		//실무에서는 set을 쓰지말고 findItem.change(name, price, stockQuantity)와 같은 함수로 써서 엔티티내에서 추적이 쉽게 써야함
	}
	
	@Transactional
	//준영속 엔티티 변경 2: 병합 (merge) 사용
	public void updateItemByMerge(Long itemId, Book param) {
		Item findItem = itemRepository.findOne(itemId);
		saveItem(findItem);
		//merge의 주의 사항은 필드에 수정 값을 안넣고 변경하면 기존 속성의 값이 null로 변경
		//위험성이 있으므로 변경감지로 업데이트하는 것이 좋다
	}
	public List<Item> findItems(){
		return itemRepository.findAll();
	}
	
	public Item findOne(Long itemId) { 
		return itemRepository.findOne(itemId);
	}
}
