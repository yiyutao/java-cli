package net.yiyutao.exception;

import com.alibaba.fastjson.JSONObject;
import net.yiyutao.common.DataResult;
import net.yiyutao.common.StatusCode;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @author: masterYI
 * @date: 2017/11/9
 * @time: 16:11
 * Description:参数校验全局异常处理
 */
@RestControllerAdvice
public class ConstraintViolationExceptionControllerAdvice {

    @ExceptionHandler(value = ConstraintViolationException.class)
    public DataResult constraintViolationException(ConstraintViolationException e) {
        DataResult result = new DataResult();
        JSONObject jsonObject = new JSONObject();
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            String message = constraintViolation.getMessage();
            PathImpl propertyPath = (PathImpl) constraintViolation.getPropertyPath();
            String property = propertyPath.getLeafNode().getName();
            jsonObject.put(property, message);
        }
        result.setStatusCode(StatusCode.LackParam);
        result.setData(jsonObject);
        return result;
    }


}
