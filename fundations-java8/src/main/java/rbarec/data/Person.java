package rbarec.data;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person {

	private Long id;
	private PersonTypeEnum PersonType;
	private String identification;
	private String names;
	private String surnames;
	private Date birthDate;
	private Integer edad;
	private Double  altura;
	private List<String> equiposFav;
	private GenderEnum gender;
}
