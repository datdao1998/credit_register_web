package web.service.impl;

import com.google.gson.Gson;
import web.service.InvokeService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class InvokeServiceImpl implements InvokeService {

    private Gson gson = new Gson();

    @Override
    public String get(String baseUrl, List<String> params) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(baseUrl);
        if (params.size() > 0) urlBuilder.append("?");
        for (String param : params) {
            int i = params.indexOf(param);
            if (params.indexOf(param) == params.size() - 1 ) {
                urlBuilder.append(param);
            }
            else {
                urlBuilder.append(param);
                urlBuilder.append("&");
            }
        }

        HttpURLConnection conn=null;
        BufferedReader reader=null;
        StringBuilder responseBuilder = new StringBuilder();
        try{
            URL url = new URL(urlBuilder.toString().replaceAll(" ", "%20"));
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : "
                        + conn.getResponseCode());
            }

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String output = null;
            while ((output = reader.readLine()) != null)
                responseBuilder.append(output);
        }catch(Exception e) {
            e.printStackTrace();
        }
        finally
        {
            if(reader!=null)
            {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(conn!=null)
            {
                conn.disconnect();
            }
        }
        return responseBuilder.toString();
    }

    @Override
    public String post(String baseUrl, Object data) {
        HttpURLConnection conn=null;
        BufferedReader reader=null;
        StringBuilder responseBuilder = new StringBuilder();
        try{
            URL url = new URL(baseUrl);
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            os.write(gson.toJson(data).getBytes());
            os.flush();
            os.close();
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("HTTP POST Request Failed with Error code : "
                        + conn.getResponseCode());
            }

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String output = null;
            while ((output = reader.readLine()) != null)
                responseBuilder.append(output);
        }catch(Exception e) {
            e.printStackTrace();
        }
        finally
        {
            if(reader!=null)
            {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(conn!=null)
            {
                conn.disconnect();
            }
        }
        return responseBuilder.toString();
    }

    @Override
    public String put(String baseUrl, Object data, Integer id) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(baseUrl);
        urlBuilder.append(String.valueOf(id));

        HttpURLConnection conn=null;
        BufferedReader reader=null;
        StringBuilder responseBuilder = new StringBuilder();
        try{
            URL url = new URL(urlBuilder.toString());
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            os.write(gson.toJson(data).getBytes());
            os.flush();
            os.close();
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : "
                        + conn.getResponseCode());
            }

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String output = null;
            while ((output = reader.readLine()) != null)
                responseBuilder.append(output);
        }catch(Exception e) {
            e.printStackTrace();
        }
        finally
        {
            if(reader!=null)
            {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(conn!=null)
            {
                conn.disconnect();
            }
        }
        return responseBuilder.toString();
    }

}
