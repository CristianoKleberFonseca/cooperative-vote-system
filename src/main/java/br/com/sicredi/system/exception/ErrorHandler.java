package br.com.sicredi.system.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.sicredi.system.model.dto.error.ErrorDetailDto;
import br.com.sicredi.system.model.dto.error.ErrorDto;

@ControllerAdvice
public class ErrorHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public @ResponseBody ErrorDto handleNotFoundException(HttpServletRequest httpServletRequest, Exception exception) {
		NotFoundException notFoundException = (NotFoundException) exception;
		ErrorDto errorDto = new ErrorDto(HttpStatus.NOT_FOUND.value(), exception.getMessage(),
				notFoundException.getDetails());

		return errorDto;
	}

	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(BusinessException.class)
	public @ResponseBody ErrorDto handleBusinessException(HttpServletRequest httpServletRequest, Exception exception) {
		BusinessException businessException = (BusinessException) exception;
		ErrorDto errorDto = new ErrorDto(HttpStatus.UNPROCESSABLE_ENTITY.value(), exception.getMessage(), businessException.getDetails());

		return errorDto;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BadRequestException.class)
	public @ResponseBody ErrorDto handleBadRequest(HttpServletRequest httpServletRequest, Exception exception) {
		BadRequestException badRequestException = (BadRequestException) exception;
		ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), badRequestException.getDetails());

		return errorDto;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public @ResponseBody ErrorDto handleConstraintViolationException(HttpServletRequest httpServletRequest, Exception exception) {
		ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), buildItems(((ConstraintViolationException) exception).getConstraintViolations())); 
		return errorDto;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
	public @ResponseBody ErrorDto handleIlegalArgument2(HttpServletRequest httpServletRequest, Exception exception) {
		ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), null);
		return errorDto;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(JsonMappingException.class)
	public @ResponseBody ErrorDto handleJsonMappingException(HttpServletRequest httpServletRequest, Exception exception) {
		ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage()); 
		return errorDto;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public @ResponseBody ErrorDto handleBadRequestMissingServletRequestParameterException(HttpServletRequest httpServletRequest, Exception exception) {
		ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), null);
		return errorDto;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public @ResponseBody ErrorDto handleBadRequest2(HttpServletRequest httpServletRequest, Exception exception) {
		MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) exception;
		ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), buildItems((methodArgumentNotValidException).getBindingResult())); 
		return errorDto;
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public @ResponseBody ErrorDto handleInternalError(HttpServletRequest httpServletRequest, Exception exception) {
		ErrorDto errorDto = new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage(), this.buildItems(exception));
		return errorDto;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(InternalError.class)
	public @ResponseBody ErrorDto handleInternalErrorImpl(HttpServletRequest httpServletRequest, Exception ex) {
		ErrorDto errorDto = new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null);
		return errorDto;
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NullPointerException.class)
	public @ResponseBody ErrorDto handleNullPointer(HttpServletRequest httpServletRequest, Exception exception) {
		ErrorDto errorDto = new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), "java.lang.NullPointerException", null);
		return errorDto;
	}

	private List<ErrorDetailDto> buildItems(final Exception ex) {

		final List<ErrorDetailDto> details = new ArrayList<ErrorDetailDto>();

		details.add(new ErrorDetailDto(ex.getCause() != null ? ex.getCause().toString() : null, ex.getMessage()));

		return details;

	}

	private List<ErrorDetailDto> buildItems(final Set<ConstraintViolation<?>> errors) {

		if (errors.isEmpty()) {
			return null;
		}

		final List<ErrorDetailDto> details = new ArrayList<ErrorDetailDto>();

		errors.stream().forEach(error -> {

			String message = null;

			try {
				message = error.getMessageTemplate().toString().replace("{", "").replace("}", "");
			} catch (Exception e) {
				message = error.getMessage();
			} finally {
				details.add(new ErrorDetailDto(error.getPropertyPath().toString(), message));
			}

		});

		return details;

	}
	
	private List<ErrorDetailDto> buildItems(final BindingResult bindingResult) {

		if (bindingResult.getFieldErrors().isEmpty())
			return null;

		final List<ErrorDetailDto> details = new ArrayList<ErrorDetailDto>();

		bindingResult.getFieldErrors().stream().forEach(error -> {
			details.add(new ErrorDetailDto(error.getField(), error.getDefaultMessage()));
		});

		return details;

	}
}
