package api.servlet;

import com.google.gson.Gson;
import common.constant.FPErrorCode;
import common.constant.FPMessage;
import common.dto.response.FPResponse;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public abstract class AbstractServlet extends HttpServlet {

    protected Gson gson = new Gson();

    protected <R> String toResponse(Optional<R> optional) {
        if (!optional.isPresent()) {
            FPResponse<R> fpResponse = new FPResponse<R>();
            fpResponse.setCode(FPErrorCode.SUCCESS);
            fpResponse.setMessage(FPMessage.SUCCESS);
            fpResponse.setData(null);
            return gson.toJson(fpResponse);
        }
        else {
            return toResponse(optional.get());
        }
    }

    protected <R> String toResponse(R r) {
        FPResponse<R> fpResponse = new FPResponse<R>();
        fpResponse.setCode(FPErrorCode.SUCCESS);
        fpResponse.setMessage(FPMessage.SUCCESS);
        fpResponse.setData(r);
        return gson.toJson(fpResponse);
    }

    protected <R> String toResponse(List<R> list) {
        FPResponse<List<R>> fpResponse = new FPResponse<>();
        fpResponse.setCode(FPErrorCode.SUCCESS);
        fpResponse.setMessage(FPMessage.SUCCESS);
        fpResponse.setData(list);
        return gson.toJson(fpResponse);
    }

    protected String toErrorResponse(String code, String message) {
        FPResponse<?> fpResponse = new FPResponse<>();
        fpResponse.setCode(code);
        fpResponse.setMessage(message);
        fpResponse.setData(null);
        return gson.toJson(fpResponse);
    }

    protected String readRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder strBuf = new StringBuilder();
        BufferedReader reader = request.getReader();
        String output = null;
        while ((output = reader.readLine()) != null)
            strBuf.append(output);
        return strBuf.toString();
    }

    protected String getPath(HttpServletRequest request) {
        return request.getPathInfo().substring(1);
    }
}
