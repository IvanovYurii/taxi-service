package taxi.controller.driver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import taxi.controller.LoginController;
import taxi.exception.DataProcessingException;
import taxi.lib.Injector;
import taxi.model.Driver;
import taxi.service.DriverService;

public class GetAllDriversController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("taxi");
    private static final Logger logger = LogManager.getLogger(LoginController.class);
    private final DriverService driverService = (DriverService) injector
            .getInstance(DriverService.class);
    private List<Driver> drivers = new ArrayList<>();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            drivers = driverService.getAll();
            logger.info("Get drivers list:");
            for (Driver current : drivers) {
                logger.info("name= " + current.getName() + "; licenseNumber="
                        + current.getLicenseNumber() + "; login="
                        + current.getLogin() + "; password=*****");
            }
        } catch (DataProcessingException e) {
            logger.error("Can't get a list of drivers.");
        }
        HttpSession session = req.getSession();
        Long driverId = (Long) session.getAttribute("driver_id");
        logger.info("User " + driverService.get(driverId).getLogin()
                + " visit All driver page");
        req.setAttribute("drivers", drivers);
        req.getRequestDispatcher("/WEB-INF/views/drivers/all.jsp").forward(req, resp);
    }
}
