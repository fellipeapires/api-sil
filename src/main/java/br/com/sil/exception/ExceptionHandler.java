package br.com.sil.exception;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.sil.model.CodigoExceptionSql;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handlerDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
		String msgUser = null;
		String msgDev = null;

		if (ex.getCause().getCause() instanceof SQLException) {
			SQLException e = (SQLException) ex.getCause().getCause();
			if (e.getErrorCode() == CodigoExceptionSql.COD_UNIQUE_SQL.getCodigo()) {
				msgUser = getMessageExceptionUser(e, "Informacao ja cadastrada no sistema: ");
			} else {
				msgUser = "Erro ";
			}
			msgDev = e.getMessage();
		} else {
			msgUser = "Erro ";
			msgDev = ex.getMessage();
		}
		System.out.println(msgDev);
		List<Error> erros = Arrays.asList(new Error(msgUser, msgDev));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	public String getMessageExceptionUser(SQLException e, String msg) {
		int start = e.getMessage().indexOf("(") + 1;
		int end = e.getMessage().indexOf(")");
		return msg + e.getMessage().substring(start, end);
	}

	public ResponseEntity<Object> handleCustomRuntimeException(CustomRuntimeException ex, HttpHeaders headers,
			WebRequest request) {
		String msgUser = ex.getMessage();
		List<Error> erros = Arrays.asList(new Error(msgUser));
		return handleExceptionInternal(ex, erros, headers, HttpStatus.CONFLICT, request);
	}

	public static class Error {

		private String msgUser;
		private String msgDev;

		public Error(String msgUser, String msgDev) {
			super();
			this.msgUser = msgUser;
			this.msgDev = msgDev;
		}

		public Error(String msgUser) {
			super();
			this.msgUser = msgUser;
		}

		public String getMsgUser() {
			return msgUser;
		}

		public String getMsgDev() {
			return msgDev;
		}

	}
}
