package jp.co.taxis.funsite.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchForm {
	@NotEmpty
	@Size(max = 20)
	private String name;
	
	@NotEmpty
	@Pattern(regexp = ".{1,50}")
	private String searchWord;
}
