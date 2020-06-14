package web.servlet;

import com.google.gson.Gson;
import web.service.InvokeService;
import web.service.impl.InvokeServiceImpl;

import javax.servlet.http.HttpServlet;

public abstract class AbstractServlet extends HttpServlet {

    protected Gson gson = new Gson();
    protected InvokeService invokeService = new InvokeServiceImpl();

}
