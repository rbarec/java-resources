package automation.jud1.api;

import java.util.UUID;

import org.apache.logging.log4j.CloseableThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import automation.appbisscontrol.ScrappingLimitControl;
import automation.appbisscontrol.ScrappingLimitControlException;
import automation.base.CommonDataTx;
import automation.jud1.JudicaturaService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import topnotaria.cero.GenericController;
import topnotaria.exception.CustomException;
import topnotaria.msj.GenericResponse;
import topnotaria.vo.LoggerVO;

@Log4j2
@CrossOrigin("*")
@RestController
@RequestMapping("")
public class JudicaturaAPIController extends GenericController {

	@Autowired
	JudicaturaService automationService;

	@Autowired
	ScrappingLimitControl scrappingLimitControl;

	// Ejecutar Judicatura por Grupo o Tramite, notes = se necesita el tipo=
	// grupo|tramite
	// y el id del objeto es idTRamite o idGRupo. hay validaciones sobre los datos
	@RequestMapping(value = "/api/v1/automation/judicatura/1", method = RequestMethod.POST, //
			consumes = "application/json", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<GenericResponse<ResponseJudicatura>> ejecutaJudicatura( //
			@RequestBody RequestJudicatura objRequest, //
			HttpServletRequest httpRequest //
	) {
		long startTransactionTime = System.currentTimeMillis();
		HttpHeaders headers = new HttpHeaders();
		CommonDataTx commons = new CommonDataTx(//
				"TEST-USER-01", //
				captureHeadersDebug(httpRequest), "1.0", null, captureHeadersCache(httpRequest));
		LoggerVO.addDataContext(UUID.randomUUID().toString(), null);
		GenericResponse<ResponseJudicatura> response = new GenericResponse<>();
		try {
			//System.out.println(" PROCESOS====================== \n\n" + scrappingLimitControl.getAllTagsProcess());
			scrappingLimitControl.beginTramiteIfNotExist(objRequest.getIdObjeto(), commons.getUserSession());
			ValidatorJudicatura.validate(objRequest);
			ResponseJudicatura r = automationService.ejecutaJudicatura(objRequest, commons);
			response.setResult(r);
			// _______________________________________________________
			response.setLogs(commons.getLogs());
			response.setInfoFrom_DefaultExito();
			return new ResponseEntity<>(response, headers, HttpStatus.OK);
			//
		} catch (ScrappingLimitControlException e) {
			e.printStackTrace();
			commons.putLogError("Scrappign ya en ejecucion. " + e.getExceptionMessage());
			response.setStatus("ERROR");
			response.setMessage("Scrappign ya en ejecucion. " + e.getExceptionMessage());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (CustomException e) {
			e.printStackTrace();
			commons.putLogError(e.getMessage());
			response.setLogs(commons.getLogs());
			response.setInfoFrom_CustomException(e, 200L);
			return new ResponseEntity<>(response, e.getHttpStatus());
		} catch (Exception e) {
			e.printStackTrace();
			commons.putLogError(e.getMessage());
			response.setLogs(commons.getLogs());
			response.setInfoFrom_Exception(e, 500L);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			System.out.println(" PROCESOS_RETIRADO ********* "+objRequest.getIdObjeto()+" \n\n");
			scrappingLimitControl.endTramite(objRequest.getIdObjeto());
			CloseableThreadContext.put("transactionTime", (System.currentTimeMillis() - startTransactionTime) + "Ms");
			LoggerVO.clearLog();
		}
	}

}
