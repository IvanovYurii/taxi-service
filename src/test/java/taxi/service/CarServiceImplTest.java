package taxi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taxi.dao.CarDao;
import taxi.dao.DriverDao;
import taxi.exception.DataProcessingException;
import taxi.lib.Injector;
import taxi.model.Car;
import taxi.model.Driver;
import taxi.model.Manufacturer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CarServiceImplTest {
    private static final Injector injector = Injector.getInstance("taxi");
    private final CarService carService = (CarService)
            injector.getInstance(CarService.class);
    private final CarDao carDao = (CarDao)
            injector.getInstance(CarDao.class);
    private final DriverDao driverDao = (DriverDao)
            injector.getInstance(DriverDao.class);
    private final DriverService driverService = (DriverService)
            injector.getInstance(DriverService.class);
    private Manufacturer manufacturer;

    @BeforeEach
    void setUp() {
        manufacturer = new Manufacturer();
    }

    @Test
    public void test_Create_Car_Ok() {
        manufacturer.setId(1L);
        Car car = new Car("Model", manufacturer);
        Car createdCar = carService.create(car);
        assertNotNull(createdCar.getId());
    }

    @Test
    public void test_Create_Car_By_Manufacturer_Is_Null_NotOk() {
        Car car = new Car("Model", manufacturer);
        assertThrows(NullPointerException.class,
                () -> carService.create(car));
    }

    @Test
    public void test_Create_Car_By_Manufacturer_Invalid_ID_NotOk() {
        manufacturer.setId(100L);
        Car car = new Car("Model", manufacturer);
        assertThrows(DataProcessingException.class,
                () -> carService.create(car));
    }

    @Test
    public void test_Get_Car_By_ID_Ok() {
        Long carId = 1L;
        Car car = carService.get(carId);
        assertEquals(carId, car.getId());
    }

    @Test
    public void test_Get_Car_By_Invalid_ID_NotOk() {
        Long carId = 100L;
        assertThrows(NoSuchElementException.class,
                () -> carService.get(carId));
    }

    @Test
    public void test_Update_Car_By_ID_Ok() {
        manufacturer.setId(1L);
        Car car = new Car("UpdatedModel", manufacturer);
        car.setId(1L);
        carService.update(car);
        Car updatedCar = carService.get(car.getId());
        assertEquals("UpdatedModel", updatedCar.getModel());
    }

    @Test
    public void test_Update_Car_By_Invalid_Car_ID_NotOk() {
        manufacturer.setId(1L);
        Car car = new Car("UpdatedModel", manufacturer);
        car.setId(100L);
        carService.update(car);
        assertThrows(NoSuchElementException.class,
                () -> carService.get(car.getId()));
    }

    @Test
    public void test_Update_Car_By_Manufacturer_ID_Invalid_NotOk() {
        manufacturer.setId(100L);
        Car car = new Car("UpdatedModel", manufacturer);
        car.setId(1L);
        assertThrows(DataProcessingException.class,
                () -> carService.update(car));
    }

    @Test
    public void test_Delete_Car_By_ID_Ok() {
        manufacturer.setId(1L);
        Car car = new Car("Model", manufacturer);
        carService.create(car);
        List<Car> cars = carService.getAll();
        car = cars.get(cars.size() - 1);
        assertTrue(carService.delete(car.getId()));
    }

    @Test
    public void test_Delete_Car_By_Invalid_ID_NotOk() {
        Car car = new Car("Model", new Manufacturer("Toyota", "Japan"));
        car.setId(300L);
        assertThrows(NoSuchElementException.class,
                () -> carService.get(car.getId()));
    }


    @Test
    public void test_Add_Driver_To_Car_By_ID_Ok() {
        manufacturer.setId(1L);
        Car car = new Car("Model", manufacturer);
        if (carDao.get(1L).isEmpty()) {
            carService.create(car);
        }
        car = carService.get(1L);
        List<Driver> drivers = new ArrayList<>();
        car.getDrivers().clear();
        carService.update(car);
        drivers.add(0, new Driver("Misha", "123432", "Misha_1", "1234"));
        if (driverDao.get(1L).isEmpty()) {
            driverDao.create(drivers.get(0));
        }
        Driver driver = driverService.get(1L);
        carService.addDriverToCar(driver, car);
        assertTrue(carService.get(1L).getDrivers().contains(driverService.get(1L)));
    }

    @Test
    public void test_Add_Driver_To_Car_By_Invalid_Car_ID_NotOk() {
        Long carId = 100L;
        List<Driver> drivers = new ArrayList<>();
        drivers.add(0, new Driver("Misha", "123432", "Misha_1", "1234"));
        if (driverDao.get(1L).isEmpty()) {
            driverService.create(drivers.get(0));
        }
        Driver driver = driverService.get(1L);
        assertThrows(NoSuchElementException.class,
                () -> carService.addDriverToCar(driver, carService.get(carId)));
    }

    @Test
    public void test_Add_Driver_To_Car_By_Invalid_Driver_ID_NotOk() {
        manufacturer.setId(1L);
        Car car = new Car("Model", manufacturer);
        if (carDao.get(1L).isEmpty()) {
            carService.create(car);
        }
        assertThrows(NoSuchElementException.class,
                () -> carService.addDriverToCar(driverService.get(100L), carService.get(1L)));
    }
}