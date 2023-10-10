package rbarec.tododomain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
public class Person {
	private String id;
	private String identification;
	private identificationTypeEnum identificationType;
	private String name;
	private String surname;
	private String gender;
	private String civilStatus;
	private String direccion;
	private Date dateBirth;
	private String countryBirth;
}
