package jp.co.taxis.funsite.form;

import lombok.Data;

@Data
public class TicketForm {
	
	private Integer id;
	private Integer quantity;//チケット枚数
	private Integer itemId;
}
