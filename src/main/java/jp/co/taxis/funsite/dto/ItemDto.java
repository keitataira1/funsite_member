package jp.co.taxis.funsite.dto;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import jp.co.taxis.funsite.entity.ItemEntity;
import jp.co.taxis.funsite.entity.OrderDetailEntity;
import lombok.Data;

@Data
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ItemDto implements Serializable {
	/**
	 * ログインユーザ情報
	 */
	
	private ItemEntity itemEntity;
	
	private OrderDetailEntity orderDetailEntity;

}
