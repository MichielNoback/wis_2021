package nl.bioinf.wis2021.servlets;

import nl.bioinf.wis2021.config.WebConfig; //change to your situation!
import nl.bioinf.wis2021.model.User;
import org.thymeleaf.context.WebContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
@WebServlet(name = "WelcomeServlet",
        urlPatterns = {"/welcome", "/home", "/index.html"})
public class WelcomeServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("Initializing Thymeleaf template engine");
        final ServletContext servletContext = this.getServletContext();
        WebConfig.createTemplateEngine(servletContext);
    }

    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{

        process(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        final String remoteAddr = request.getRemoteAddr();
        System.out.println("remoteAddr = " + remoteAddr);
        process(request, response);
    }

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        //this step is optional; standard settings also suffice
        final String role = request.getParameter("role");
        System.out.println("role = " + role);

        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(300);

        WebConfig.configureResponse(response);
        WebContext ctx = new WebContext(
                request,
                response,
                request.getServletContext(),
                request.getLocale());
        ctx.setVariable("currentDate", new Date());
        ctx.setVariable("user", new User("Nihil", "Nobrain", 50));
        WebConfig.createTemplateEngine(getServletContext()).
                process("welcome", ctx, response.getWriter());
    }
}