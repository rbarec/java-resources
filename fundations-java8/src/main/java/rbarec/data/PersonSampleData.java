package rbarec.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class PersonSampleData {

	public static List<Person> getPersonList() {
		List<Person> lista = new ArrayList<>();
		lista.add(Person.builder().gender(GenderEnum.FEMENINNO).id(1L).names("Thayra").surnames("Bar")//
				.identification("089776").PersonType(PersonTypeEnum.NATURAL)//
				.birthDate(fecha("2012-01-01")).edad(12).altura(1.45).equiposFav( Arrays.asList("Barcelona", "Ecuador"))
				.build());
		lista.add(Person.builder().gender(GenderEnum.MASCULINO).id(11L).names("Ronald").surnames("Barrera Espinoza")//
				.identification("09220670032").PersonType(PersonTypeEnum.NATURAL)//
				.birthDate(fecha("2016-06-10")).edad(7).altura(1.15).equiposFav( Arrays.asList("Barcelona", "Miami"))
				.build());
		lista.add(Person.builder().gender(GenderEnum.MASCULINO).id(23L).names("Fred").surnames("Barrera Espinoza")//
				.identification("089776").PersonType(PersonTypeEnum.NATURAL)//
				.birthDate(fecha("2015-01-05")).edad(8).altura(1.25).equiposFav( Arrays.asList("Barcelona", "Miami"))
				.build());
		lista.add(Person.builder().gender(GenderEnum.MASCULINO).id(33L).names("Ronald Belisario").surnames("Barrera Larrea")//
				.identification("0922067715").PersonType(PersonTypeEnum.NATURAL)//
				.birthDate(fecha("1983-07-03")).edad(40).altura(1.80).equiposFav( Arrays.asList("City", "Bayer", "Miami"))
				.build());
		return lista;
	}
	
	
	private static Date fecha(String fecha)  {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.parse(fecha);
		} catch (ParseException e) {
			return new Date();
		}
	}
	
}
