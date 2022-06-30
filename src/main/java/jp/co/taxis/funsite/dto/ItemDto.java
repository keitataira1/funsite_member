package jp.co.taxis.funsite.dto;

import java.io.Serializable;

import jp.co.taxis.funsite.entity.ItemEntity;
import jp.co.taxis.funsite.entity.OrderDetailEntity;
import lombok.Data;

@Data
public class ItemDto implements Serializable {
	
	private ItemEntity itemEntity;
	
	private OrderDetailEntity orderDetailEntity;

}
