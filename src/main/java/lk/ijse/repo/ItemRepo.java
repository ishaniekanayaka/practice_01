package lk.ijse.repo;

import lk.ijse.db.DBConnection;
import lk.ijse.model.CustomerDTO;
import lk.ijse.model.ItemDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemRepo {
    public static List<ItemDTO> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM item";

        Connection connection = DBConnection.getDbConnection().getConnection();

        ResultSet resultSet = connection.createStatement().executeQuery(sql);

        List<ItemDTO> cusList = new ArrayList<>();

        while (resultSet.next()) {
            cusList.add(new ItemDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4)
            ));
        }
        return cusList;
    }

    public static boolean save(ItemDTO item) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO item(code, description, unitPrice, qty) VALUES(?, ?, ?,?)";

        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,item.getCode());
        pstm.setString(2, item.getDescription());
        pstm.setInt(3, item.getUnitPrice());
        pstm.setInt(4, item.getQty());

        return pstm.executeUpdate() > 0;
    }

    public static  boolean delete(ItemDTO item) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM item WHERE code=?";

        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, item.getCode());

        return pstm.executeUpdate() > 0;
    }
}
