package taxi.service;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taxi.dao.DriverDao;
import taxi.exception.DataProcessingException;
import taxi.lib.Injector;
import taxi.model.Driver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DriverServiceImplTest {
    private static final Injector injector = Injector.getInstance("taxi");
    private final DriverService driverService = (DriverService)
            injector.getInstance(DriverService.class);
    private final DriverDao driverDao = (DriverDao)
            injector.getInstance(DriverDao.class);
    private Driver driver;

    @BeforeEach
    void setUp() {
        driver = new Driver();
    }

    @Test
    public void test_Add_Driver_Ok() {
        driver.setName("Lena");
        driver.setLicenseNumber("123456");
        driver.setLogin("Lena_1");
        driver.setPassword("1234");
        driverService.create(driver);
        Driver driverFromDb = driverService.findByLogin(driver.getLogin()).orElseThrow();
        assertEquals(driver.getName(), driverFromDb.getName());
        assertEquals(driver.getLicenseNumber(), driverFromDb.getLicenseNumber());
        assertEquals(driver.getLogin(), driverFromDb.getLogin());
        assertEquals(driver.getPassword(), driverFromDb.getPassword());
    }

    @Test
    public void test_Add_Driver_Name_Is_Null_NotOk() {
        driver.setLicenseNumber("123456");
        driver.setLogin("Lena_1");
        driver.setPassword("1234");
        assertThrows(DataProcessingException.class,
                () -> driverService.create(driver));
    }

    @Test
    public void test_Add_Driver_License_Number_Is_Null_NotOk() {
        driver.setName("Lena");
        driver.setLogin("Lena_1");
        driver.setPassword("1234");
        assertThrows(DataProcessingException.class,
                () -> driverService.create(driver));
    }

    @Test
    public void test_Add_Driver_License_Is_Null_NotOk() {
        driver.setName("Lena");
        driver.setLicenseNumber("123456");
        driver.setPassword("1234");
        assertThrows(DataProcessingException.class,
                () -> driverService.create(driver));
    }

    @Test
    public void test_Add_Driver_Password_Is_Null_NotOk() {
        driver.setName("Lena");
        driver.setLicenseNumber("123456");
        driver.setLogin("Lena_1");
        assertThrows(DataProcessingException.class,
                () -> driverService.create(driver));
    }

    @Test
    public void test_Get_Driver_Ok() {
        if (driverDao.get(1L).isEmpty()) {
            driver.setName("Lena");
            driver.setLicenseNumber("123456");
            driver.setLogin("Vik_1");
            driver.setPassword("1234");
            driverService.create(driver);
        }
        assertNotNull(driverService.get(1L));
    }

    @Test
    public void test_Get_Driver_By_Invalid_ID_NotOk() {
        assertThrows(NoSuchElementException.class,
                () -> driverService.get(100L));
    }

    @Test
    public void test_Update_Driver_Ok() {
        if (driverDao.get(1L).isEmpty()) {
            driver.setName("Lena");
            driver.setLicenseNumber("123456");
            driver.setLogin("Lena_1");
            driver.setPassword("1234");
            driverService.create(driver);
        }
        driver = driverService.get(1L);
        driver.setName("LENA");
        driver.setLicenseNumber("654321");
        driver.setLogin("1_Lena");
        driver.setPassword("4321");
        driverService.update(driver);
        assertEquals(driverService.get(1L).getName(), driver.getName());
        assertEquals(driverService.get(1L).getLicenseNumber(), driver.getLicenseNumber());
        assertEquals(driverService.get(1L).getLogin(), driver.getLogin());
        assertEquals(driverService.get(1L).getPassword(), driver.getPassword());
    }

    @Test
    public void test_Update_Driver_Name_Is_Null_NotOk() {
        if (driverDao.get(1L).isEmpty()) {
            driver.setName("Lena");
            driver.setLicenseNumber("123456");
            driver.setLogin("Lena_1");
            driver.setPassword("1234");
            driverService.create(driver);
        }
        driver = driverService.get(1L);
        driver.setName(null);
        driver.setLicenseNumber("654321");
        driver.setLogin("1_Lena");
        driver.setPassword("4321");
        assertThrows(DataProcessingException.class,
                () -> driverService.update(driver));
    }

    @Test
    public void test_Update_Driver_License_Number_Is_Null_NotOk() {
        if (driverDao.get(1L).isEmpty()) {
            driver.setName("Lena");
            driver.setLicenseNumber("123456");
            driver.setLogin("Lena_1");
            driver.setPassword("1234");
            driverService.create(driver);
        }
        driver = driverService.get(1L);
        driver.setName("Lena");
        driver.setLicenseNumber(null);
        driver.setLogin("1_Lena");
        driver.setPassword("4321");
        assertThrows(DataProcessingException.class,
                () -> driverService.update(driver));
    }

    @Test
    public void test_Update_Driver_Login_Is_Null_NotOk() {
        if (driverDao.get(1L).isEmpty()) {
            driver.setName("Lena");
            driver.setLicenseNumber("123456");
            driver.setLogin("Lena_1");
            driver.setPassword("1234");
            driverService.create(driver);
        }
        driver = driverService.get(1L);
        driver.setName("Lena");
        driver.setLicenseNumber("123456");
        driver.setLogin(null);
        driver.setPassword("4321");
        assertThrows(DataProcessingException.class,
                () -> driverService.update(driver));
    }

    @Test
    public void test_Update_Driver_Password_Is_Null_NotOk() {
        if (driverDao.get(1L).isEmpty()) {
            driver.setName("Lena");
            driver.setLicenseNumber("123456");
            driver.setLogin("Lena_1");
            driver.setPassword("1234");
            driverService.create(driver);
        }
        driver = driverService.get(1L);
        driver.setName("Lena");
        driver.setLicenseNumber("123456");
        driver.setLogin("Lena_1");
        driver.setPassword(null);
        assertThrows(DataProcessingException.class,
                () -> driverService.update(driver));
    }

    @Test
    public void test_Delete_Driver_By_ID_Ok() {
        driver.setName("Lena");
        driver.setLicenseNumber("123456");
        driver.setLogin("Lena_1");
        driver.setPassword("1234");
        driverService.create(driver);
        Driver driverFromDb = driverService.findByLogin(driver.getLogin()).orElseThrow();
        assertTrue(driverService.delete(driverFromDb.getId()));
    }

    @Test
    public void test_Delete_Driver_By_Invalid_ID_NotOk() {
        assertFalse(driverService.delete(100L));
    }
}
