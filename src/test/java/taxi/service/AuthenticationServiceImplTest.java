package taxi.service;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import taxi.dao.DriverDao;
import taxi.exception.AuthenticationException;
import taxi.lib.Injector;
import taxi.model.Driver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthenticationServiceImplTest {
    private static final Injector injector = Injector.getInstance("taxi");
    private final DriverService driverService = (DriverService) injector
            .getInstance(DriverService.class);
    private final DriverDao driverDao = (DriverDao) injector
            .getInstance(DriverDao.class);
    private final AuthenticationService authenticationService = (AuthenticationService) injector
            .getInstance(AuthenticationService.class);
    private Driver driver;

    @Test
    public void test_Driver_Login_Ok() throws AuthenticationException {
        Driver driver = new Driver();
        if (driverDao.get(1L).isEmpty()) {
            driver.setName("Vik");
            driver.setLicenseNumber("785674");
            driver.setLogin("Vik_1");
            driver.setPassword("1234");
            driverService.create(driver);
        }
        driver = driverService.get(1L);
        driver = authenticationService.login(driver.getLogin(), driver.getPassword());
        assertNotNull(driver);
        assertTrue(driver.getId() > 0);
    }

    @Test
    public void test_Driver_Login_Password_Match_Ok() throws AuthenticationException {
        Driver driver = new Driver();
        driver.setName("Vik");
        driver.setLicenseNumber("785674");
        driver.setLogin("Vik_1");
        driver.setPassword("1234");
        driverService.create(driver);
        Optional<Driver> driverdriverFromDb = driverService.findByLogin(driver.getLogin());
        Driver actualDriver = authenticationService.login(driverdriverFromDb.get().getLogin(),
                driverdriverFromDb.get().getPassword());
        assertNotNull(actualDriver);
        assertTrue(actualDriver.getId() > 0);
        assertEquals(driver.getLogin(), actualDriver.getLogin());
        assertEquals(driver.getPassword(), actualDriver.getPassword());
    }

    @Test
    public void test_Login_By_Driver_Login_IsNull_NotOk() {
        String login = null;
        Optional<Driver> driver = driverService.findByLogin(login);
        assertFalse(driver.isPresent());
    }

    @Test
    public void test_Login_By_Driver_Login_IsEmpty_NotOk() {
        String login = "";
        Optional<Driver> driver = driverService.findByLogin(login);
        assertFalse(driver.isPresent());
    }

    @Test
    public void test_Login_By_Driver_Login_Invalid_NotOk() {
        String login = "test";
        Optional<Driver> driver = driverService.findByLogin(login);
        assertFalse(driver.isPresent());
    }

    @Test
    public void test_Login_By_Driver_Password_IsNull_NotOk() {
        String login = "Vik_1";
        String password = null;
        assertThrows(AuthenticationException.class,
                () -> authenticationService.login(login, password));
    }

    @Test
    public void test_Login_By_Driver_Password_IsEmpty_NotOk() {
        String login = "Vik_1";
        String password = "";
        assertThrows(AuthenticationException.class,
                () -> authenticationService.login(login, password));
    }

    @Test
    public void test_Login_By_Driver_Password_Invalid_NotOk() {
        String login = "Vik_1";
        String password = "1";
        assertThrows(AuthenticationException.class,
                () -> authenticationService.login(login, password));
    }
}