package vn.io.nghlong3004.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseUtil {
  private static Logger logger = LoggerFactory.getLogger(DatabaseUtil.class);

  private final String url;
  private final String username;
  private final String password;
  private final String driverClassName;

  public DatabaseUtil(Property property) {
    logger.info("Loading DatabaseUtil...");
    url = property.getDatasourceUrl();
    username = property.getDatasourceUsername();
    password = property.getDatasourcePassword();
    driverClassName = property.getDatasourceDriverClassName();
  }

  private void loadDriver() throws ClassNotFoundException {
    logger.info("Load driver");
    Class.forName(driverClassName);
  }

  private Connection createConnection() throws SQLException {
    logger.info("Create connection database");
    Connection connection = null;
    connection = DriverManager.getConnection(url, username, password);
    if (connection == null) {
      throw new SQLException();
    }
    return connection;
  }

  private PreparedStatement statement(String query, Object... objects) throws SQLException {
    logger.info("Statement");
    PreparedStatement preparedStatement = null;
    preparedStatement = createConnection().prepareStatement(query);

    if (preparedStatement == null) {
      throw new SQLException();
    }

    for (int i = 0; i < objects.length; ++i) {
      preparedStatement.setObject(i + 1, objects[i]);
    }

    return preparedStatement;
  }

  public List<Object> execute(String query, Object... objects) {
    List<Object> result = null;
    try {
      loadDriver();
      Connection connection = createConnection();
      PreparedStatement preparedStatement = statement(query, objects);
      if (query.startsWith("SELECT")) {
        logger.info("This is SELECT SQL query");
        logger.info("Execute query");
        ResultSet resultSet = preparedStatement.executeQuery();
        logger.info("Get meta data");
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        logger.info("Get colums name");
        List<String> columnNames = getColumnsName(resultSetMetaData);
        logger.info("Get result");
        result = getResult(columnNames, resultSet);

        resultSet.close();
      } else {
        logger.info("This is not SELECT SQL query");
        logger.info("Execute query");
        preparedStatement.executeUpdate();
      }

      preparedStatement.close();
      connection.close();
    } catch (ClassNotFoundException e) {
      logger.debug("Class not found: " + e.getMessage());
    } catch (SQLException e) {
      logger.debug("SQL Message error: " + e.getMessage());
    }


    return result;
  }

  private List<Object> getResult(List<String> columnNames, ResultSet resultSet)
      throws SQLException {
    List<Object> result = new ArrayList<Object>();
    result.add(columnNames);
    while (resultSet.next()) {
      List<Object> row = new ArrayList<Object>();
      for (String column : columnNames) {
        row.add(resultSet.getObject(column));
      }
      result.add(row);
    }
    return result;
  }

  private List<String> getColumnsName(ResultSetMetaData resultSetMetaData) throws SQLException {
    List<String> columnNames = new ArrayList<String>();
    int columns = resultSetMetaData.getColumnCount();
    for (int i = 0; i < columns; ++i) {
      columnNames.add(Helper.snakeCaseToCamelCase(resultSetMetaData.getColumnName(i)));
    }
    return columnNames;
  }

}
