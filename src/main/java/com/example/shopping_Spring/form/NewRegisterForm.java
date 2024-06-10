package com.example.shopping_Spring.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewRegisterForm {
	@NotBlank(message="idを入力してください")
	private String id;
	@NotBlank(message="passwordを入力してください")
	private String password;
	@NotBlank(message="password(２回目)を入力してください")
	private String password2;
	@NotBlank@NotEmpty(message="mailを入力してください")
	private String mail;
	

}
