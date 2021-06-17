package info.library.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceUtil {
  public Connection getConnect() {

      try {
          Class.forName("com.mysql.jdbc.Driver").newInstance();
      } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
          e.printStackTrace();
      }


      Connection connect = null;
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "Alexander", "!Ab1540340");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connect;
    }

    public void closeConnect(Connection connect) {
        if (connect != null) {
            try {
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}