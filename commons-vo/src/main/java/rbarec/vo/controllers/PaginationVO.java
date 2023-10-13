package rbarec.vo.controllers;

import lombok.Data;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

/**
 * Objeto de Valor para Paginacion<br>
 * Tiene page y size
 * 
 * @author Ronald
 *
 */
@Log4j2
@Data
@Getter
public class PaginationVO {
	private int page;
	private int size;

	/**
	 * Nombre del campo en base o de campo JSON
	 */
	public static final String NOMBRE_CAMPO_PAGE = "page";
	/**
	 * Nombre del campo en base o de campo JSON
	 */
	public static final String NOMBRE_CAMPO_SIZE = "size";
	/**
	 * La pagina por defecto es 0
	 */
	public static final int DEFAULT_VALUE_PAGE = 0;
	/**
	 * El valor por defecto del numero de elementos por pagina es 5
	 */
	public static final int DEFAULT_VALUE_SIZE = 5;
	/**
	 * numero maximo de elementos por pagina
	 */
	public static final int MAX_EMELENT_PAGE = 50;

	/**
	 * Mensaje de Error
	 */
	public static final String PAGE_CONSTRAINS_TEXT = "El Campo page requiere un numero mayor a cero";
	/**
	 * Mensaje de Error
	 */
	public static final String SIZE_CONSTRAINS_TEXT = "El Campo size requiere un numero menor a 50";

	/**
	 * CONSTRUCTOR que valida, transforma o usa valores por defecto.<br>
	 * NO-NPE, nunca retorna NPE<br>
	 * @param strPageNumber
	 * @param strSizeNumber
	 */
	public PaginationVO(String strPageNumber, String strSizeNumber) {
		super();
		int inPage = DEFAULT_VALUE_PAGE;
		if (strPageNumber != null && !strPageNumber.isEmpty()) {
			try {
				inPage = Integer.parseInt(strPageNumber);
			} catch (Exception e) {
				log.error("Erro numero Pagina" + PAGE_CONSTRAINS_TEXT, e);
			}
		}

		int inSize = DEFAULT_VALUE_SIZE;
		if (strSizeNumber != null && !strSizeNumber.isEmpty()) {
			try {
				inSize = Integer.parseInt(strSizeNumber);
			} catch (Exception e) {
				log.error("Erro Tamaño Pagina" + SIZE_CONSTRAINS_TEXT, e);
			}
		}
		setValues(inPage, inSize);
	}

	private void setValues(int page, int size) {
		if (page < 0 || page > 50) {
			page = DEFAULT_VALUE_PAGE;
		}
		this.page = page;
		if (size < 1 || size > 50) {
			size = DEFAULT_VALUE_SIZE;
		}
		this.size = size;
	}

}
