package taxi.controller.car;

import java.io.IOException;
import java.util.NoSuchElementException;
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
import taxi.model.Manufacturer;
import taxi.service.CarService;
import taxi.service.DriverService;
import taxi.service.ManufacturerService;

public class AddCarController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("taxi");
    private static final Logger logger = LogManager.getLogger(LoginController.class);
    private final CarService carService = (CarService) injector.getInstance(CarService.class);
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
                + " visit Add driver page");
        req.getRequestDispatcher("/WEB-INF/views/cars/add.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String model = req.getParameter("model");
        long manufacturerId = Long.parseLong(req.getParameter("manufacturer_id"));
        try {
            Manufacturer manufacturer = manufacturerService.get(manufacturerId);
            Car car = new Car(model, manufacturer);
            carService.create(car);
            resp.sendRedirect(req.getContextPath() + "/cars/add");
            logger.info("Car with Param: model= " + model + "; manufacturer: name="
                    + car.getManufacturer().getName() + "; country="
                    + car.getManufacturer().getCountry() + " was created");
        } catch (NoSuchElementException | DataProcessingException e) {
            logger.error("Can't not add manufacturer ID=" + manufacturerId + " to car");
            req.setAttribute("errorMsg", "Can't not add manufacturer ID=" + manufacturerId
                    + " to car");
            req.getRequestDispatcher("/WEB-INF/views/cars/drivers/add.jsp").forward(req, resp);
        }
    }
}
