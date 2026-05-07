package eu.iamhelmi.baru.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Person {
	private String email;
	private String fullname;
	private String memberSince;
	private Boolean active;
}
