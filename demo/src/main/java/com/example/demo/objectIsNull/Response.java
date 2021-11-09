package com.example.demo.objectIsNull;

import lombok.Data;

import java.io.Serializable;

@Data
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 2511274534390442425L;
    public static final int SUCCESS_CODE = 200;
    public static final int ERROR_CODE = 400;
    public static final int SYS_ERROR_CODE = 500;
    private boolean isSuccess = true;
    private int code;
    private String msg = "成功";
    private T result;

    public Response() {
    }

    public Response(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Response(boolean isSuccess, T result) {
        this.isSuccess = isSuccess;
        this.result = result;
    }

    public static <T> Response<T> errorResponse(String msg) {
        return errorResponse(400, msg);
    }

    public static <T> Response<T> errorResponse(int code, String msg) {
        Response<T> response = new Response(false);
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

    public static <T> Response<T> errorResponse(int code, String msg, T result) {
        Response<T> response = new Response(false, result);
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

    public static <T> Response errorResponse(String msg, T result) {
        Response<T> response = new Response(false, result);
        response.setCode(400);
        response.setMsg(msg);
        return response;
    }

    public static Response successResponse() {
        return new Response(true);
    }

    public static <T> Response<T> successResponse(String msg, T result) {
        return successResponse(200, msg, result);
    }

    public static <T> Response<T> successResponse(int code, String msg, T result) {
        Response<T> response = new Response(true, result);
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

    @Override
    public String toString() {
        return "Response(isSuccess=" + this.isSuccess + ",code=" + this.code + ",msg=" + this.msg + ");";
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public T getResult() {
        return this.result;
    }

    public void setSuccess(final boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }

    public void setResult(final T result) {
        this.result = result;
    }

//    @Override
//    public boolean equals(final Object o) {
//        if (o == this) {
//            return true;
//        } else if (!(o instanceof Response)) {
//            return false;
//        } else {
//            Response<?> other = (Response)o;
//            if (!other.canEqual(this)) {
//                return false;
//            } else if (this.isSuccess() != other.isSuccess()) {
//                return false;
//            } else if (this.getCode() != other.getCode()) {
//                return false;
//            } else {
//                label40: {
//                    Object this$msg = this.getMsg();
//                    Object other$msg = other.getMsg();
//                    if (this$msg == null) {
//                        if (other$msg == null) {
//                            break label40;
//                        }
//                    } else if (this$msg.equals(other$msg)) {
//                        break label40;
//                    }
//
//                    return false;
//                }
//
//                Object this$result = this.getResult();
//                Object other$result = other.getResult();
//                if (this$result == null) {
//                    if (other$result != null) {
//                        return false;
//                    }
//                } else if (!this$result.equals(other$result)) {
//                    return false;
//                }
//
//                return true;
//            }
//        }
//    }
//
//    protected boolean canEqual(final Object other) {
//        return other instanceof Response;
//    }
//
//    @Override
//    public int hashCode() {
//        int PRIME = true;
//        int result = 1;
//        int result = result * 59 + (this.isSuccess() ? 79 : 97);
//        result = result * 59 + this.getCode();
//        Object $msg = this.getMsg();
//        result = result * 59 + ($msg == null ? 43 : $msg.hashCode());
//        Object $result = this.getResult();
//        result = result * 59 + ($result == null ? 43 : $result.hashCode());
//        return result;
//    }
}
