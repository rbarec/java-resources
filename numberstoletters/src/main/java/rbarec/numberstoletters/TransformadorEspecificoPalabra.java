package rbarec.numberstoletters;

import rbarec.numberstoletters.dto.AnalisisPalabraDTO;
import rbarec.numberstoletters.dto.TransformaLetraResponse;

public interface TransformadorEspecificoPalabra {

	public boolean validarNaturaleza(AnalisisPalabraDTO analisis);
	
	public TransformaLetraResponse transformar(String entrada);
}
