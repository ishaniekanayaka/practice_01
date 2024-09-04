package lk.ijse.repo;

import lk.ijse.db.DBConnection;
import lk.ijse.model.CustomerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepo {

    public static List<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM customer";

        Connection connection = DBConnection.getDbConnection().getConnection();

        ResultSet resultSet = connection.createStatement().executeQuery(sql);

        List<CustomerDTO> cusList = new ArrayList<>();

        while (resultSet.next()) {
            cusList.add(new CustomerDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            ));
        }
        return cusList;
    }

    public static boolean save(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO customer(cusId, name, address) VALUES(?, ?, ?)";

        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, customer.getCustomerId());
        pstm.setString(2, customer.getCustomerName());
        pstm.setString(3, customer.getAddress());

        return pstm.executeUpdate() > 0;
    }

    public static boolean delete(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM customer WHERE cusId =?";

        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, customer.getCustomerId());

        return pstm.executeUpdate() > 0;
    }
}

