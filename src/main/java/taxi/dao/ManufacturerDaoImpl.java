package taxi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import taxi.controller.LoginController;
import taxi.exception.DataProcessingException;
import taxi.lib.Dao;
import taxi.model.Manufacturer;
import taxi.util.ConnectionUtil;

@Dao
public class ManufacturerDaoImpl implements ManufacturerDao {
    private static final Logger logger = LogManager.getLogger(LoginController.class);

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        logger.info("Manufacturer method create with Param: name= " + manufacturer.getName()
                + "; country=" + manufacturer.getCountry() + " was called");
        String query = "INSERT INTO manufacturers (name, country) VALUES (?,?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement
                        = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setUpdate(statement, manufacturer).executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                manufacturer.setId(resultSet.getObject(1, Long.class));
            }
            return manufacturer;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create manufacturer " + manufacturer, e);
        }
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        logger.info("Manufacturer method get by ID with Param: ID=" + id + " was called");
        String query = "SELECT * FROM manufacturers WHERE id = ? AND is_deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Manufacturer manufacturer = null;
            if (resultSet.next()) {
                manufacturer = parseManufacturerFromResultSet(resultSet);
            }
            return Optional.ofNullable(manufacturer);
        } catch (SQLException e) {

            throw new DataProcessingException("Can't get manufacturer by id " + id, e);
        }
    }

    @Override
    public List<Manufacturer> getAll() {
        logger.info("Manufacturer method get All was called");
        String query = "SELECT * FROM manufacturers WHERE is_deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            List<Manufacturer> manufacturers = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                manufacturers.add(parseManufacturerFromResultSet(resultSet));
            }
            return manufacturers;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get a list of manufacturers.", e);
        }
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        logger.info("Manufacturer method update with Param: name= " + manufacturer.getName()
                + "; country=" + manufacturer.getCountry() + " was called");
        String query = "UPDATE manufacturers SET name = ?, country = ?"
                + " WHERE id = ? AND is_deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement
                        = setUpdate(connection.prepareStatement(query), manufacturer)) {
            statement.setLong(3, manufacturer.getId());
            statement.executeUpdate();
            return manufacturer;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update a manufacturer "
                    + manufacturer, e);
        }
    }

    @Override
    public boolean delete(Long id) {
        logger.info("Manufacturer method delete with Param: ID= " + id + " was called");
        String query = "UPDATE manufacturers SET is_deleted = TRUE WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete a manufacturer by id " + id, e);
        }
    }

    private Manufacturer parseManufacturerFromResultSet(ResultSet resultSet) throws SQLException {
        logger.info("Manufacturer method parseManufacturerFromResultSet was called");
        Long id = resultSet.getObject("id", Long.class);
        String name = resultSet.getString("name");
        String country = resultSet.getString("country");
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(id);
        manufacturer.setName(name);
        manufacturer.setCountry(country);
        logger.info("Method parseManufacturerFromResultSet return Manufacturer with Param: name= "
                + manufacturer.getName() + "; country=" + manufacturer.getCountry());
        return manufacturer;
    }

    private PreparedStatement setUpdate(PreparedStatement statement,
                                        Manufacturer manufacturer) throws SQLException {
        logger.info("Method setUpdate with Param: name= " + manufacturer.getName()
                + "; country=" + manufacturer.getCountry() + " was called");
        statement.setString(1, manufacturer.getName());
        statement.setString(2, manufacturer.getCountry());
        return statement;
    }
}
