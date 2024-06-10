package com.example.shopping_Spring.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterForm {
	@NotBlank
	private String id;
	@NotBlank
	private String password;
	

}
