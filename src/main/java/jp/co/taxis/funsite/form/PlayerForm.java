package jp.co.taxis.funsite.form;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class PlayerForm {
	
	private Integer id;
	@NotNull()
	@Pattern(regexp=".{1,20}")
	private String name;
	private LocalDate birthday;
	private String position;
	

}
