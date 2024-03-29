package rbarec.numberstoletters.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rbarec.numberstoletters.domain.Palabra;

/**
 * Texto Entrada Texto Salida y Conteo Errores.
 * 
 * @author Ronald
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransformaOracionResponse implements Serializable {
	
	
	private static final long serialVersionUID = 17223222180L;

	/**
	 * 
	 */
	private final boolean EXPORT_ESTRATEGIA = false;

	/**
	 * 
	 */
	private String texto;
	/**
	 * 
	 */
	private List<String> arrPalabras;
	/**
	 * 
	 */
	private Integer conteoErrores = 0;
	/**
	 * 
	 */
	List<String> arrResultadoTrans = new ArrayList<>();
	/**
	 * 
	 */
	Map<Integer, Palabra> mapAnalisis = new HashMap<>();
	/**
	 * 
	 */
	Map<String, String> maplogWords = new HashMap<>();

	/*
	 * 
	 */
	public int size() {
		return arrPalabras.size();
	}

	/**
	 * 
	 */
	public void incrementaErrores() {
		if (conteoErrores == null)
			conteoErrores = 1;
		conteoErrores = conteoErrores++;
	}

	/**
	 * 
	 * @return
	 */
	public String getTextoResultado() {
		String aaa = "";
		String bbb = "";
		for (Map.Entry<Integer, Palabra> entry : getMapAnalisis().entrySet()) {
			Palabra val = entry.getValue();
			if (EXPORT_ESTRATEGIA) {
				aaa += val.getPalabraTransformada() + " -" + val.getEstrategia() + "-";
			} else {
				aaa += val.getPalabraTransformada() + " ";
			}

			bbb += "(" + entry.getKey() + ")" + val.getPalabraTransformada();
		}
		System.out.println(bbb);
		return aaa;
	}
}
