package com.sathya.restapiproject.data;
import jakarta.persistence.Entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	 @NotBlank(message = "Name cannot be empty")
	    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
	    private String name;

	    @NotBlank(message = "Gender cannot be empty")
	    @Pattern(regexp = "^(Male|Female|Other)$", message = "Gender must be Male, Female, or Other")
	    private String gender;

	    @Min(value = 3000, message = "Salary must be at least 3000")
	    @Max(value = 100000, message = "Salary cannot exceed 100000")
	    private double salary;

	    @NotBlank(message = "Email cannot be empty")
	    @Email(message = "Invalid email format")
	    private String email;

//	    @NotBlank(message = "Dept cannot be empty")
//	    @Size(min = 2, max = 50, message = "Dept name must be between 2 and 50 characters")
	    private String dept;

		

}
