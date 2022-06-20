package jp.co.taxis.funsite.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberForm {

	@NotEmpty
	@Pattern(regexp = ".{1,20}")
	private String mail;

	@NotEmpty
	@Pattern(regexp = ".{1,10}")
	private String password;

	@NotEmpty
	@Pattern(regexp = ".{1,10}")
	private String realName;

	@NotEmpty
	@Pattern(regexp = ".{1,10}")
	private String memberName;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate birthday;

	@Size(max = 10)
	private String postalCode;

	@Size(max = 50)
	private String address;

}
