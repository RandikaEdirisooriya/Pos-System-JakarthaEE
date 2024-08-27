package lk.ijse.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class CORSFilter extends HttpFilter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        var origin = request.getHeader("Origin");
        var config=getServletContext().getInitParameter("origin");
        if(origin.contains(config)){
            response.setHeader("Access-Control-Allow-Origin", origin);

            // Allow specific HTTP methods
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT,PATCH, DELETE, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type");

            /*meken wenne headers tikata allows denwa*/
            response.setHeader("Access-Control-Expose-Headers", "Content-Type");

            /* me deke wensa Expose thiyenne browser eke thiyana js engine ekt allow karanwa access karannawa*/
        }
        chain.doFilter(req, res);
    }
}
