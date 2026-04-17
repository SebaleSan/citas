package com.semana2.citas.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {

		Map<String, String> errores = new HashMap<>();

		for (FieldError error : ex.getBindingResult().getFieldErrors()) {

			errores.put(error.getField(), error.getDefaultMessage());
		}

		Map<String, Object> response = new HashMap<>();
		response.put("timestamp", LocalDateTime.now());
		response.put("status", HttpStatus.BAD_REQUEST.value());
		response.put("errores", errores);

		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleGeneralError(Exception ex) {

		Map<String, Object> response = new HashMap<>();
		response.put("timestamp", LocalDateTime.now());
		response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.put("mensaje", "Error interno del servidor");
		response.put("detalle", ex.getMessage());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	 @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleJsonParseError(HttpMessageNotReadableException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("mensaje", "Formato de fecha inválido");
        error.put("detalle", "La fecha debe enviarse en formato dd-MM-yyyy");
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<Map<String, String>> handleDateTimeParseError(DateTimeParseException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("mensaje", "Formato de fecha inválido");
        error.put("detalle", "La fecha debe enviarse en formato dd-MM-yyyy");
        return ResponseEntity.badRequest().body(error);
    }
}
