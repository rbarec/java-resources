package rbarec.numberstoletters.service.test;

import java.util.Properties;

import rbarec.numberstoletters.domain.PropertiesBuilder;
import rbarec.numberstoletters.domain.PropertiesEnum;
import rbarec.numberstoletters.dto.TransformaOracionResponse;
import rbarec.numberstoletters.service.TransformarTextoSimple;
/**
 * Test for TransformarTextoSimple
 * @author Ronald
 *
 */
public class TransformarTextoSimpleTest {
	
	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		Properties props = PropertiesBuilder.builder().put(PropertiesEnum.VERBOSE, true).build();
		
		final String texto = "siendo $ 567,88"//
				+ " la $33.44 1 de 9 de 2023, suma USD $ 199.456,96. Registro de"//
				+ " $ 171.456,96. una en vir	tud  celular 0960590924";
		System.out.println(texto);
		
		TransformarTextoSimple to = new TransformarTextoSimple();
		TransformaOracionResponse x = to.transformarTextoSimple(texto, props);

		System.out.println("RESULTADO");
		System.out.println(x.getTextoResultado());
	}

}
