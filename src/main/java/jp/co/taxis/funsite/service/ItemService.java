package jp.co.taxis.funsite.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.taxis.funsite.entity.ItemEntity;
import jp.co.taxis.funsite.repository.ItemRepository;

@Transactional
@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	public List<ItemEntity> selectAll() {
		List<ItemEntity> itemList=itemRepository.findAll();
		return itemList;
	}
	
	public ItemEntity selectById(Integer id) {
		ItemEntity item = itemRepository.findById(id).orElse(null);
		return item;
	}
	
	public ItemEntity itemInsert(ItemEntity item) {
		ItemEntity resultItem=itemRepository.save(item);
		return resultItem;
	}


	public List<ItemEntity> selectLikeName(String searchWord) {
		List<ItemEntity> searchList = itemRepository.selectLikeName("%"+searchWord+"%");
		return searchList;
	}
}
