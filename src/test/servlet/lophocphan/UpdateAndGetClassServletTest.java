package test.servlet.lophocphan;


import api.servlet.AbstractServlet;
import api.servlet.lophocphan.UpdateAndGetClassServlet;
import common.exception.FPException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

import static org.mockito.Mockito.*;


public class UpdateAndGetClassServletTest {

    @Test
    public void testDoGet()  throws Exception{
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        UpdateAndGetClassServlet servlet = new UpdateAndGetClassServlet();
    }
}