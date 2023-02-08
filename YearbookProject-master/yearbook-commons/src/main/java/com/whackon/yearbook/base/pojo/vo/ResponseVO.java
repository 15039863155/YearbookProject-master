package com.whackon.yearbook.base.pojo.vo;

import com.whackon.yearbook.base.pojo.enums.ResponseCodeEnum;
import lombok.Data;
import org.springframework.validation.FieldError;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>系统响应视图信息</b>
 *
 * @author Arthur
 * @date 2022/9/22
 */
@Data
public class ResponseVO<E> implements Serializable {
	private static final long serialVersionUID = 5591529391729716407L;
	private Integer code;                       // 系统响应编码
	private String message;                     // 系统响应信息
	private E data;                             // 系统响应数据

	public ResponseVO(ResponseCodeEnum codeEnum, String message, E data) {
		this.code = codeEnum.getCode();
		this.message = message;
		this.data = data;
	}

	/**
	 * <b>获得系统业务处理和响应成功视图信息</b>
	 * @param message
	 * @return
	 */
	public static ResponseVO createSuccessResponseVO(String message) {
		return new ResponseVO(ResponseCodeEnum.RESPONSE_CODE_SUCCESS, message, new String(""));
	}

	/**
	 * <b>获得系统业务处理和响应成功视图信息</b>
	 * @param message
	 * @param data
	 * @return
	 */
	public static ResponseVO createSuccessResponseVO(String message, Object data) {
		return new ResponseVO(ResponseCodeEnum.RESPONSE_CODE_SUCCESS, message, data);
	}

	/**
	 * <b>获得用户未进行系统认证视图信息</b>
	 * @return
	 */
	public static ResponseVO createUnAuthResponseVO() {
		return new ResponseVO(ResponseCodeEnum.RESPONSE_CODE_UNAUTH, ResponseCodeEnum.RESPONSE_CODE_UNAUTH.getRemark(), new String(""));
	}

	/**
	 * <b>获得系统业务处理错误视图信息</b>
	 * @param errorMessage
	 * @return
	 */
	public static ResponseVO createFailureResponseVO(String errorMessage) {
		return new ResponseVO(ResponseCodeEnum.RESPONSE_CODE_FAILURE, errorMessage, new String(""));
	}

	public static ResponseVO createFailureResponseVO(List<FieldError> fieldErrorList) {
		List<String> errorMessagesList = new ArrayList<String>();
		// 使用 JDK 8 所提供的循环方式依次从 fieldErrorList 中提取数据，存入 errorMessagesList
		fieldErrorList.forEach(fieldError -> errorMessagesList.add(fieldError.getDefaultMessage()));
		return new ResponseVO(ResponseCodeEnum.RESPONSE_CODE_FAILURE, "校验失败", errorMessagesList);
	}

	/**
	 * <b>获得系统响应异常视图信息</b>
	 * @param e
	 * @return
	 */
	public static ResponseVO createExceptionResponse(Exception e) {
		return new ResponseVO(ResponseCodeEnum.RESPONSE_CODE_EXCEPTION, e.getMessage(), e);
	}
}
