import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;

@WebListener()
public class SessionListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    public SessionListener() {
    }
    public void contextInitialized(ServletContextEvent sce) {
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }

    public void sessionCreated(HttpSessionEvent event) {
        ServletContext context = event.getSession().getServletContext();
        Object Temp = context.getAttribute("number");
        if(Temp==null)
            context.setAttribute("number",0);
        else {
            int num =  (int) Temp;
            context.setAttribute("number", num + 1);
            System.out.println(num);
        }
    }

    public void sessionDestroyed(HttpSessionEvent event) {
        ServletContext context = event.getSession().getServletContext();
        int num = (int)context.getAttribute("number");
        context.setAttribute("number", num-1);
    }

    public void attributeAdded(HttpSessionBindingEvent sbe) {
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
    }
}