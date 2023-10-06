package rbarec.springboot3zero.appcontext;

import java.util.ArrayList;
import java.util.List;

public class PersonaSampleData {

	public static List<Persona> getPersonaList(){
		List<Persona> lista = new ArrayList<>();
		lista.add(Persona.builder().nombres("Miley").apellidos("Carrera").build());
		lista.add(Persona.builder().nombres("Jan").apellidos("Taylor").build());
		return lista;
	}
}
