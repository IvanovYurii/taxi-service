package taxi.controller.car;

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
import taxi.model.Car;
import taxi.service.CarService;
import taxi.service.DriverService;

public class GetAllCarsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("taxi");
    private static final Logger logger = LogManager.getLogger(LoginController.class);
    private final CarService carService = (CarService) injector.getInstance(CarService.class);
    private final DriverService driverService = (DriverService) injector
            .getInstance(DriverService.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Car> cars = new ArrayList<>();
        try {
            cars = carService.getAll();
            logger.info("Get cars list:");
            for (Car current : cars) {
                logger.info("model= " + current.getModel() + "; manufacturer: name="
                        + current.getManufacturer().getName() + "; country="
                        + current.getManufacturer().getCountry());
            }
        } catch (DataProcessingException e) {
            logger.error("Can't get a list of cars.");
        }
        HttpSession session = req.getSession();
        Long driverId = (Long) session.getAttribute("driver_id");
        logger.info("User " + driverService.get(driverId).getLogin() + " visit All car page");
        req.setAttribute("cars", cars);
        req.getRequestDispatcher("/WEB-INF/views/cars/all.jsp").forward(req, resp);
    }
}
