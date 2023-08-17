package taxi.controller.manufacturer;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import taxi.controller.LoginController;
import taxi.exception.DataProcessingException;
import taxi.lib.Injector;
import taxi.service.ManufacturerService;

public class DeleteManufacturerController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("taxi");
    private static final Logger logger = LogManager.getLogger(LoginController.class);
    private final ManufacturerService manufacturerService = (ManufacturerService) injector
            .getInstance(ManufacturerService.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long manufacturerId = Long.parseLong(req.getParameter("id"));
        try {
            boolean result = manufacturerService.delete(manufacturerId);
            logger.info("Delete manufacturer by id=" + manufacturerService.get(manufacturerId)
                    .getId() + " result:" + result);
        } catch (DataProcessingException e) {
            logger.error("Can't delete manufacturer with id " + manufacturerId);
        }
        resp.sendRedirect(req.getContextPath() + "/manufacturers");
    }
}
