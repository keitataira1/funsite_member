package jp.co.taxis.funsite.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import jp.co.taxis.funsite.entity.MemberEntity;
import lombok.Data;

@Data
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserDto implements Serializable {
	/**
	 * ログインユーザ情報
	 */
	private MemberEntity memberEntity;

	/**
	 * 商品情報
	 */
	private List<ItemDto> itemDtoList = new ArrayList<ItemDto>();
	
}
