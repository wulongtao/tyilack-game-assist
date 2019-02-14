package com.tyilack.assist.controller;

import com.tyilack.assist.util.BaseException;
import com.tyilack.assist.util.RetResponse;
import com.tyilack.assist.util.RetResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author 小小黑
 *
 * springboot中错误信息封装成如下的格式：
 *
 * {
 *     "timestamp": "2018-08-31T09:25:29.108+0000",
 *     "status": 400,
 *     "error": "Bad Request",
 *     "errors": [
 *         {
 *             "codes": [
 *                 "NotBlank.botPubInfoVO.name",
 *                 "NotBlank.name",
 *                 "NotBlank.java.lang.String",
 *                 "NotBlank"
 *             ],
 *             "arguments": [
 *                 {
 *                     "codes": [
 *                         "botPubInfoVO.name",
 *                         "name"
 *                     ],
 *                     "arguments": null,
 *                     "defaultMessage": "name",
 *                     "code": "name"
 *                 }
 *             ],
 *             "defaultMessage": "用户名不能为空",
 *             "objectName": "botPubInfoVO",
 *             "field": "name",
 *             "rejectedValue": null,
 *             "bindingFailure": false,
 *             "code": "NotBlank"
 *         }
 *     ],
 *     "message": "Bad Request",
 *     "path": "/gbot/publish"
 * }
 */
@Slf4j
@RequestMapping("${server.error.path:/error}")
@RestController
public class ErrorController extends AbstractErrorController {
    private static final String ERROR_INFO_KEY = "errors";
    private static final String JSON_SUFFIX = ".json";
    @Autowired
    RetResponse retResponse;

    public ErrorController() {
        super(new DefaultErrorAttributes());
    }

    @RequestMapping
    public RetResult<Object> error(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = Collections.unmodifiableMap(getErrorAttributes(request, false));
        //获取异常，有可能为空
        Throwable cause = getCause(request);
        //Http请求状态码
        int status = (int) model.get("status");
        //错误信息
        String message = (String) model.get("message");
        //如果有errors，则取errors的信息，把message替换（数据校验出错的信息放在errors里面）
        if (model.containsKey(ERROR_INFO_KEY)) {
            List<FieldError> errorList = (List<FieldError>) model.get(ERROR_INFO_KEY);
            StringBuilder sb = new StringBuilder();
            int index = 0;
            for (FieldError item: errorList) {
                index++;
                sb.append(item.getField() + item.getDefaultMessage());
                if (index < errorList.size()) {
                    sb.append(",");
                }
            }
            message = sb.toString();
            status = -1;
        }
        //后台打印日志信息方便查错

        if (message.contains(":")) {
            String[] messageArr = message.split(":");
            message = messageArr[messageArr.length-1];
        }
        log.info("请求错误：status={},message={}", status, message);
        /**
         * 若是自定义异常，则返回异常中的状态码和信息
         */
        boolean isCustomException = cause instanceof BaseException;
        log.error("是否是自定义异常：{}", isCustomException);
        if (isCustomException) {
            BaseException baseException = (BaseException) cause;
            status = baseException.getCode();
            message = baseException.getMessage();
        }

        response.setStatus(200);
        return retResponse.makeRsp(status, message);
    }

    private Throwable getCause(HttpServletRequest request) {
        Throwable error = (Throwable) request.getAttribute("javax.servlet.error.exception");
        if (error != null) {
            //MVC 有可能会封装异常成ServletException，需要调用getCause获取真正的异常
            while (error instanceof ServletException && error.getCause() != null) {
                error = ((ServletException)error).getCause();
            }
        }
        return error;
    }

    @Override
    public String getErrorPath() {
        System.out.println("getErrorPath");
        return null;
    }
}
