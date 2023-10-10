package rbarec.tododomain.sampledata;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rbarec.tododomain.Person;
import rbarec.tododomain.identificationTypeEnum;

public class SamplePerson110 {
	
	public static final String FORMATO_FECHA_XSD = "yyyy-MM-dd";

	

	public static Person getPerson() throws Exception {
		return Person.builder().name("Ronald").surname("Barrera")//
				.identification("0922607715")//
				.identificationType(identificationTypeEnum.CEDULA)//
				.gender("Masculino")//
				.civilStatus("casado")//
				.direccion("Mucho Lote 2")
				.dateBirth(toDate("1983-07-03"))
				.build();
	}

	public static List<Person> getPersonList() throws Exception {
		Person p = Person.builder().name("Ronald").surname("Barrera")//
				.identification("0922607715")//
				.identificationType(identificationTypeEnum.CEDULA)//
				.gender("Masculino").civilStatus("casado").direccion("Mucho Lote 2")
				.dateBirth(toDate("1983-07-03")).build();
		
		Person p2 = Person.builder().name("Monica").surname("Carrera")//
				.identification("1202607715")//
				.identificationType(identificationTypeEnum.CEDULA)//
				.gender("Femenino").civilStatus("divorciado").direccion("Huancavilca pase3 v5")
				.dateBirth(toDate("1984-09-10")).build();
		
		Person p3 = Person.builder().name("Ronald Israel").surname("Barrera Espinoza")//
				.identification("0977195546")//
				.identificationType(identificationTypeEnum.CEDULA)//
				.gender("Masculino").civilStatus("soltero").direccion("Mucho Lote 2")
				.dateBirth(toDate("2016-06-10")).build();
		
		Person p4 = Person.builder().name("Lionel").surname("Messi")//
				.identification("0910101010")//
				.identificationType(identificationTypeEnum.PASSPORT)//
				.gender("Masculino").civilStatus("casado").direccion("Miami")
				.dateBirth(toDate("1986-01-01")).build();
		
		Person p5 = Person.builder().name("Christiano").surname("Ronaldo")//
				.identification("0907070707")//
				.identificationType(identificationTypeEnum.PASSPORT)//
				.gender("Masculino").civilStatus("casado").direccion("Portugal")
				.dateBirth(toDate("1984-07-03")).build();
		List<Person> lista = new ArrayList<>();
		lista.add(p);
		lista.add(p2);
		lista.add(p3);
		lista.add(p4);
		lista.add(p5);
		return lista;
	}

	
	
	public static synchronized Date toDate(String fecha) throws Exception {
			if (fecha == null ) {
				throw new Exception("ERROR_PARAMETROS", null);
			}
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat(SamplePerson110.FORMATO_FECHA_XSD);
			Date fechaEnviar = null;
			try {
				fechaEnviar = formatoDelTexto.parse(fecha);
				return fechaEnviar;
			} catch (ParseException ex) {
				ex.printStackTrace();
				return null;
			}
		}
}
