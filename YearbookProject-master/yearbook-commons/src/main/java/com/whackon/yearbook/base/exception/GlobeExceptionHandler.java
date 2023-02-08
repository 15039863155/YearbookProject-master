package com.whackon.yearbook.base.exception;

import com.whackon.yearbook.base.pojo.vo.ResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

/**
 * <b>系统全局异常信息处理</b>
 * <p>
 *     系统所有控制层类所抛出的异常，最终都会交给该类对象进行统一处理
 * </p>
 *
 * @author Arthur
 * @date 2022/9/22
 */
@RestControllerAdvice("globeExceptionHandler")
public class GlobeExceptionHandler {
	// 创建系统日志对象 Logger
	private Logger logger = LoggerFactory.getLogger(GlobeExceptionHandler.class);

	/**
	 * <b>处理所有控制层所抛出的异常</b>
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(Exception.class)
	public ResponseVO handldExeption(Exception e) throws Exception {
		// 将错误信息记录到日志中
		logger.error(e.getMessage() + " : " + new Date(), e);
		// 向前端返回系统异常视图信息
		return ResponseVO.createExceptionResponse(e);
	}
}
