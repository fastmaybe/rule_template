package com.sf.saas.bsp.rule.core.dto.vo.base;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 01383484
 */
@Deprecated
@Slf4j
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVo<T> implements Serializable {

    private static final long serialVersionUID = -5840397588459679125L;
    public static final String DEFAULT_SUCCESS_CODE = "0";
    public static final String DEFAULT_ERROR_CODE = "-1";
    @ApiModelProperty("0:成功;其他:失败错误码")
    private String code;
    @ApiModelProperty("返回的结果")
    private T data; //NOSONAR
    @ApiModelProperty("错误信息，给开发者使用。（可选）")
    private String message;
    @ApiModelProperty("提示信息，终端用户使用。（可选）")
    private String info;
    @ApiModelProperty("请求id")
    private String requestId;
    @ApiModelProperty("响应时间")
    private Date respTime;

    ResultVo(){
        try{
            String trackId = MDC.get("traceId");
            if(StringUtils.isNotBlank(trackId)){
                this.setRequestId(trackId);
            }
        } catch (Exception e){
            log.error("cannot get trackId",e);
        }
    }


//    public static <T> ResultVo<T> success(T data) {
//        return restResult(data, CommonErrorEnum.SUCCESS);
//    }
//
//    public static ResultVo failed(IError error) {
//        return restResult(null, error);
//    }
//
//    public static ResultVo failed(String errorMsg) {
//        return restResult(null, "-1",errorMsg);
//    }
//
//    public static <T> ResultVo<T> restResult(T data, IError error) {
//        return restResult(data, error.getCode(), error.getMsg());
//    }

    private static <T> ResultVo<T> restResult(T data, String code, String msg) {
        ResultVo<T> apiResult = new ResultVo<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMessage(msg);
        apiResult.setRespTime(new Date());
        log.debug("ResultVo: {}", JSON.toJSONString(apiResult));
        return apiResult;
    }

//
//    public static <T> ResultVo<T> batchDefaultResult(T data, IError error,String requestId) {
//        return batchDefaultResult(data, error.getCode(), error.getMsg(),requestId);
//    }

    private static <T> ResultVo<T> batchDefaultResult(T data, String code, String msg,String requestId) {
        ResultVo<T> apiResult = new ResultVo<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMessage(msg);
        apiResult.setRespTime(new Date());
        apiResult.setRequestId(requestId);
        log.debug("ResultVo: {}", JSON.toJSONString(apiResult));
        return apiResult;
    }
}
