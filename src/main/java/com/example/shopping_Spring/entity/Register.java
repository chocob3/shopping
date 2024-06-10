package com.example.shopping_Spring.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Register implements Serializable,Comparable<Register>{
	@Id
	@NotBlank
	private String id;
	@NotBlank
	private String password;
	@NotBlank
	private String mail;
	private Timestamp inserteddate;
	private String modifiedby;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Register register = (Register) obj;
        return Objects.equals(id, register.id);
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public int compareTo(Register r) {
		return this.inserteddate.compareTo(r.inserteddate);
	
	}
	@Override
	public String toString() {
		return "Register{" +
                "id=" + id +
                ", mail=" + mail +
                ", inserteddate='" + inserteddate + '\'' +
                ", modifyby" + modifiedby +
                '}';
	}


}
