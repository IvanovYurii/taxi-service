package taxi.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taxi.dao.ManufacturerDao;
import taxi.exception.DataProcessingException;
import taxi.lib.Injector;
import taxi.model.Manufacturer;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ManufacturerServiceImplTest {
    private static final Injector injector = Injector.getInstance("taxi");
    private final ManufacturerService manufacturerService = (ManufacturerService)
            injector.getInstance(ManufacturerService.class);
    private final ManufacturerDao manufacturerDao = (ManufacturerDao)
            injector.getInstance(ManufacturerDao.class);
    private Manufacturer manufacturer;

    @BeforeEach
    void setUp() {
        manufacturer = new Manufacturer();
    }

    @Test
    public void test_Add_Manufacturer_Ok() {
        manufacturer.setCountry("Usa");
        manufacturer.setName("Cadillac");
        assertDoesNotThrow(() -> manufacturerService.create(manufacturer));
    }

    @Test
    public void test_Add_Manufacturer_Model_Is_Null_NotOk() {
        manufacturer.setCountry("Usa");
        assertThrows(DataProcessingException.class,
                () -> manufacturerService.create(manufacturer));
    }

    @Test
    public void test_Add_Manufacturer_Country_Is_Null_NotOk() {
        manufacturer.setName("Cadillac");
        assertThrows(DataProcessingException.class,
                () -> manufacturerService.create(manufacturer));
    }

    @Test
    public void test_Get_Manufacturer_Ok() {
        if (manufacturerDao.get(1L).isEmpty()) {
            manufacturer.setCountry("Usa");
            manufacturer.setName("Cadillac");
            manufacturerService.create(manufacturer);
        }
        assertNotNull(manufacturerService.get(1L));
    }

    @Test
    public void test_Get_Manufacturer_By_Invalid_ID_NotOk() {
        assertThrows(NoSuchElementException.class,
                () -> manufacturerService.get(100L));
    }

    @Test
    public void test_Update_Manufacturer_Ok() {
        if (manufacturerDao.get(1L).isEmpty()) {
            manufacturer.setCountry("Usa");
            manufacturer.setName("Cadillac");
            manufacturerService.create(manufacturer);
        }
        manufacturer = manufacturerService.get(1L);
        manufacturer.setCountry("USA");
        manufacturer.setName("Ford");
        manufacturerService.update(manufacturer);
        assertEquals(manufacturerService.get(1L).getName(), manufacturer.getName());
        assertEquals(manufacturerService.get(1L).getCountry(), manufacturer.getCountry());
    }

    @Test
    public void test_Delete_Manufacturer_Ok() {
        manufacturer.setCountry("USA");
        manufacturer.setName("Cadillac");
        manufacturerService.create(manufacturer);
        List<Manufacturer> manufacturers = manufacturerService.getAll();
        manufacturer = manufacturers.get(manufacturers.size() - 1);
        assertTrue(manufacturerService.delete(manufacturer.getId()));
    }

    @Test
    public void test_Delete_Manufacturer_By_Invalid_ID_NotOk() {
        assertFalse(manufacturerService.delete(100L));
    }
}