package taxi.controller.car;

import java.io.IOException;
import java.util.NoSuchElementException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import taxi.controller.LoginController;
import taxi.lib.Injector;
import taxi.service.CarService;

public class DeleteCarController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("taxi");
    private static final Logger logger = LogManager.getLogger(LoginController.class);
    private final CarService carService = (CarService) injector.getInstance(CarService.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long carId = Long.parseLong(req.getParameter("id"));
        try {
            boolean result = carService.delete(carId);
            logger.info("Delete car by id=" + carId + " result:" + result);
        } catch (NoSuchElementException e) {
            logger.error("Can't delete car with id " + carId);
        }
        resp.sendRedirect(req.getContextPath() + "/cars");
    }
}
