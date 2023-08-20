package com.white.exchange.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xh
 * @create 2023-08-21  0:17
 */
public class WebUtil {

    public static void renderString(HttpServletResponse response, String string) {
        try
        {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
