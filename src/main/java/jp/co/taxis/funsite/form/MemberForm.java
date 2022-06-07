package jp.co.taxis.funsite.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberForm {

	@NotEmpty
	@Pattern(regexp = "[1-20]")
	private String mail;
	@NotEmpty
	@Pattern(regexp = "[1-10]")
	private String password;
	private String realName;
	private String memberName;
	private LocalDate birthday;
	private Integer postalCode;
	private String address;

}
