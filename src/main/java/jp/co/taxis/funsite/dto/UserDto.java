package jp.co.taxis.funsite.dto;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import jp.co.taxis.funsite.entity.MemberEntity;
import lombok.Data;

@Data
@Component
@SessionScope
public class UserDto implements Serializable {
	/**
	 * ログインユーザ情報
	 */
	private MemberEntity memberEntity;

}
