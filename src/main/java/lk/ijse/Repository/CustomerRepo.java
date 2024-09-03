package lk.ijse.Repository;

import lk.ijse.db.DBConnection;
import lk.ijse.model.CustomerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerRepo {

   /* public static boolean save(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO customer(cusId, name, address) VALUES(?, ?, ?)";
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, customer.getCustomerId());
        pstm.setString(2, customer.getCustomerName());
        pstm.setString(3, customer.getAddress());

        return pstm.executeUpdate()>0;
    }*/
}
