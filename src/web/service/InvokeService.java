package web.service;

import java.util.List;

public interface InvokeService {

    public String get(String baseUrl, List<String> params);

    public String post(String baseUrl, Object data);

    public String put(String baseUrl, Object data, Integer id);

}
