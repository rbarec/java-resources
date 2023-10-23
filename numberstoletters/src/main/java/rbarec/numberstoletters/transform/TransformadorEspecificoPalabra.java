package rbarec.numberstoletters.transform;

import rbarec.numberstoletters.domain.Palabra;
import rbarec.numberstoletters.dto.TransformaLetraResponse;

/**
 * Si desea transformar un texto especial, puedo crear un transformador
 * especifico.
 * 
 * @author Ronald
 *
 */
public interface TransformadorEspecificoPalabra {

	/**
	 * validar si un texto se corresponde con un tipo de texto.
	 * 
	 * @param analisis
	 * @return
	 */
	public boolean validarNaturaleza(Palabra analisis);

	/**
	 * TRasnformador de texto a otro.
	 * 
	 * @param entrada
	 * @return
	 */
	public TransformaLetraResponse transformar(String entrada);
}
