package taxi.controller.manufacturer;

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
import taxi.model.Manufacturer;
import taxi.service.DriverService;
import taxi.service.ManufacturerService;

public class GetAllManufacturersController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("taxi");
    private static final Logger logger = LogManager.getLogger(LoginController.class);
    private final ManufacturerService manufacturerService = (ManufacturerService) injector
            .getInstance(ManufacturerService.class);
    private final DriverService driverService = (DriverService) injector
            .getInstance(DriverService.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Manufacturer> manufacturers = new ArrayList<>();
        try {
            manufacturers = manufacturerService.getAll();
            logger.info("Get maufacturer list:");
            for (Manufacturer current : manufacturers) {
                logger.info("name= " + current.getName() + "; country="
                        + current.getCountry());
            }
        } catch (DataProcessingException e) {
            logger.error("Can't get a list of manufacturers.");
        }
        HttpSession session = req.getSession();
        Long driverId = (Long) session.getAttribute("driver_id");
        logger.info("User " + driverService.get(driverId).getLogin()
                + " visit All manufacturer page");
        req.setAttribute("manufacturers", manufacturers);
        req.getRequestDispatcher("/WEB-INF/views/manufacturers/all.jsp").forward(req, resp);
    }
}
