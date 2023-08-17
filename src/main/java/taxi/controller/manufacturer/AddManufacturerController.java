package taxi.controller.manufacturer;

import java.io.IOException;
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

public class AddManufacturerController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("taxi");
    private static final Logger logger = LogManager.getLogger(LoginController.class);
    private final ManufacturerService manufacturerService = (ManufacturerService) injector
            .getInstance(ManufacturerService.class);
    private final DriverService driverService = (DriverService) injector
            .getInstance(DriverService.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long driverId = (Long) session.getAttribute("driver_id");
        logger.info("User " + driverService.get(driverId).getLogin()
                + " visit Add manufacturer page");
        req.getRequestDispatcher("/WEB-INF/views/manufacturers/add.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String country = req.getParameter("country");
        Manufacturer manufacturer = new Manufacturer(name, country);
        try {
            manufacturerService.create(manufacturer);
            logger.info("Manufacturer with Param: name= " + name
                    + "; country=" + country + " was created");
        } catch (DataProcessingException e) {
            logger.error("Can't create manufacturer with Param: name= "
                    + name + "; country=" + country);
        }
        manufacturerService.create(manufacturer);
        resp.sendRedirect(req.getContextPath() + "/manufacturers/add");
    }
}
