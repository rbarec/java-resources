package rbarec.numberstoletters;
//package numberstoletters;
//
//import topnotaria.exception.CustomException;
//import topnotaria.formato.dto.CtpDataDTO;
//import topnotaria.formato.dto.StepDataHelper;
//import topnotaria.formato.dto.StepDataItem;
//import topnotaria.formato.entity.FormatoStepModel;
//
//public class TransformText_Acto123EXPIRADO {
//
//	public static CtpDataDTO getActo1(//
//			FormatoStepModel stepItemModel, //
//			StepDataItem datosDelPaso, //
//			StepDataHelper sesionDataHelper
//	) throws CustomException{
//		CtpDataDTO parrafo = new CtpDataDTO();
//		
//		
//		
//		String vende = sesionDataHelper.getStringNotNull_fromGlobals("COMPRADOR_VENTA"); //"EMPRESA Y TRASCENDENCIA S.A. EMITRAS";
//		String compra =sesionDataHelper.getStringNotNull_fromGlobals("VENDEDOR_VENTA");  // "FERNANDO PATRICIO ESPINOZA AÑAZCO";
//		String cuantia = sesionDataHelper.getStringNotNull_fromGlobals("CUANTIA_FORMA_PAGO");//= "US$115,000.00";
//		//--------------
//		String a0 = "VENTA QUE HACE " ;//LA COMPAÑÍA
//		String a1a = vende;
//		String a1b = " A FAVOR ";// DEL SEÑOR
//		String a1c = compra;
//		String a4 = ".-.-.-.-.-.-.-.-.-.-.-\r\n";
//		String a5 = "(CUANTÍA: ";
//		String a6 = cuantia;
//		String a7 = ".-.-.-.) \r\n";
//		parrafo.addmicroText(a0, false, false);
//		parrafo.addmicroText(a1a, true, false);
//		parrafo.addmicroText(a1b, false, false);
//		parrafo.addmicroText(a1c, true, false);
//		parrafo.addmicroText(a4, false, false);
//		parrafo.addmicroText(a5, false, false);
//		parrafo.addmicroText(a6, true, false);
//		parrafo.addmicroText(a7, false, false);
//		return parrafo;
//	}
// 
//	
//	//
//	//
//	public static CtpDataDTO getActo2(//
//			FormatoStepModel stepItemModel, //
//			StepDataItem datosDelPaso, //
//			StepDataHelper sesionDataHelper
//	)throws CustomException {
//		
//		String compra =sesionDataHelper.getStringNotNull_fromGlobals("VENDEDOR_VENTA");  // "FERNANDO PATRICIO ESPINOZA AÑAZCO";
//		
//		CtpDataDTO parrafo = new CtpDataDTO();
//		
//		String a1 = "HIPOTECA CON EL CARÁCTER DE ABIERTA, PROHIBICIÓN VOLUNTARIA DE ENAJENAR "
//				+ "   Y GRAVAR Y ANTICRESIS QUE CONSTITUYE ";
//		String a2 = compra;
//		String a3 = " A FAVOR DEL";
//		String a4 =" BANCO DEL PACÍFICO S.A ";
//		String a5 =".-.-.-.-.-.-.-.-.-.-.\r\n";
//		
//		parrafo.addmicroText(a1, false, false);
//		parrafo.addmicroText(a2, true, false);
//		parrafo.addmicroText(a3, false, false);
//		parrafo.addmicroText(a4, true, false);
//		parrafo.addmicroText(a5, false, false);
//		return parrafo;
//	}
//
//	public static CtpDataDTO getActo3(//
//			FormatoStepModel stepItemModel, //
//			StepDataItem datosDelPaso, //
//			StepDataHelper sesionDataHelper
//	) {
//		CtpDataDTO parrafo = new CtpDataDTO();
//		String a1 = "(CUANTÍA: ";
//		String a2 = "INDETERMINADA";
//		String a3 = ".-.-.-) ";
//		parrafo.addmicroText(a1, false, false);
//		parrafo.addmicroText(a2, true, false);
//		parrafo.addmicroText(a3, false, false);
//		return parrafo;
//	}
//
//	
//	
//	public static String getPrimeraParte(//
//			FormatoStepModel stepItemModel, //
//			StepDataItem datosDelPaso //
//	) {
//		return "";
//	}
//
//	public static String getSegundaParte(//
//			FormatoStepModel stepItemModel, //
//			StepDataItem datosDelPaso //
//	) {
//		return "";
//	}
//}
