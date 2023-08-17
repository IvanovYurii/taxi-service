package taxi.controller.driver;

import java.io.IOException;
import java.util.NoSuchElementException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import taxi.controller.LoginController;
import taxi.lib.Injector;
import taxi.service.DriverService;

public class DeleteDriverController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("taxi");
    private static final Logger logger = LogManager.getLogger(LoginController.class);
    private final DriverService driverService = (DriverService) injector
            .getInstance(DriverService.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long driverID = Long.parseLong(req.getParameter("id"));
        try {
            boolean result = driverService.delete(driverID);
            logger.info("Delete driver by id=" + driverService.get(driverID).getId()
                    + " result:" + result);
        } catch (NoSuchElementException e) {
            logger.error("Can't delete driver with id " + driverID);
        }
        resp.sendRedirect(req.getContextPath() + "/drivers");
    }
}
