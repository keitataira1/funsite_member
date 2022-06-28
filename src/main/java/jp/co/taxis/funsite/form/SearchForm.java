package jp.co.taxis.funsite.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SearchForm {
	@NotEmpty
	@Size(max = 20)
	private String name;
}
