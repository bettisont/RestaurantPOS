/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilityClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import restaurantpossystem.ManagerGUIController;

/**
 *
 * @author timbettison
 */
public class DatabaseManager {

    private static final String userName = "root";
    private static final String password = "root";
    private static final String conn = "jdbc:mysql://localhost:8889/restaurantManagementSystem";
    private static Connection connection;

    public DatabaseManager() {
        try {
            /**
             * setup the db connection
             */
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/restaurantManagementSystem", userName, password);
        } catch (SQLException ex) {
            Logger.getLogger(ManagerGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return an ObservableList of MenuItems this will eventually be linked to
     * a database an ObservableList is very similar to an ArrayList
     */
    // TODO - alter the functionality of this to retrieve menuItems from the database
    public ObservableList<MenuItem> getMenuItems() {
        ObservableList<MenuItem> menuItems = FXCollections.observableArrayList();

        return menuItems;
    }

    public void addMenuItemToDatabase(MenuItem menuItem) throws SQLException {
        String category = menuItem.getCategory();
        String name = menuItem.getName();
        String description = menuItem.getDescription();
        float price = menuItem.getPrice();
        int allergen = menuItem.getAllergen();
        int allergenForDb = 1;
        if (!(allergen == 1)) {
            allergenForDb = 0;
        }
        String prepTime = menuItem.getTimeToPrepare();
        String sql = "INSERT INTO `menuItems` (`ID`, `category`, `name`, `description`, `price`, `allergen`, `prepTime`) VALUES (NULL," + "'" + category + "'" + "," + "'" + name + "'" + "," + "'" + description + "'" + "," + "'" + price + "'" + "," + "'" + allergenForDb + "'" + "," + "'" + prepTime + "'" + ")";
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    public ObservableList<MenuItem> getAllMenuItems() throws SQLException {
        ObservableList<String> listOfMenuItems = FXCollections.observableArrayList();
        String sql = "SELECT * FROM `menuItems`";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            listOfMenuItems.add(rs.getString("category") + ", " + rs.getString("name") + ", " + rs.getString("description") + ", " + rs.getString("price") + ", " + rs.getString("allergen") + ", " + rs.getString("prepTime"));
        }
        return convertStringsToMenuItems(listOfMenuItems);
    }

    public MenuItem getMenuItem(int id) throws SQLException {
        ObservableList<String> listOfMenuItems = FXCollections.observableArrayList();
        String sql = "SELECT * FROM menuItems WHERE ID = " + id;
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            listOfMenuItems.add(rs.getString("category") + ", " + rs.getString("name") + ", " + rs.getString("description") + ", " + rs.getString("price") + ", " + rs.getString("allergen") + ", " + rs.getString("prepTime"));
        }
        return convertStringsToMenuItems(listOfMenuItems).get(0);
    }

    private ObservableList<MenuItem> convertStringsToMenuItems(ObservableList<String> menuItems) {
        ObservableList<MenuItem> menuItemObjects = FXCollections.observableArrayList();
        menuItems.stream().map((menuItem) -> menuItem.split(",")).map((split) -> {
            String name = split[1].trim();
            String category = split[0].trim();
            String description = split[2].trim();
            float price = Float.parseFloat(split[3].trim());
            int allergen = Integer.parseInt(split[4].trim());
            String timeToPrepare = split[5].trim();
            MenuItem thisMenuItem = new MenuItem(name, category, description, price, allergen, timeToPrepare);
            return thisMenuItem;
        }).forEachOrdered((thisMenuItem) -> {
            menuItemObjects.add(thisMenuItem);
        });
        return menuItemObjects;
    }

    public ObservableList<MenuItem> getSpecifiedFoodCategory(String category) throws SQLException {
        ObservableList<String> listOfMenuItems = FXCollections.observableArrayList();
        String sql = "select * from `menuItems` where category = '" + category + "'";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            listOfMenuItems.add(rs.getString("category") + ", " + rs.getString("name") + ", " + rs.getString("description") + ", " + rs.getString("price") + ", " + rs.getString("allergen") + ", " + rs.getString("prepTime"));
        }
        return convertStringsToMenuItems(listOfMenuItems);
    }

//     *
//     * @param tableNumber
//     * @return a Boolean indicating whether the table is occupied (i.e. has an order in progress, etc)
//     * @throws SQLException
//     */
    public Boolean isTableOccupied(int tableNumber) throws SQLException {
        String sql = "select * from tables where tableNumber =" + tableNumber;
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public void insertTableToDb(String tableNumber) throws SQLException {
        String sql = "INSERT INTO `tables` (`ID`, `tableNumber`) VALUES (NULL, " + "'" + tableNumber + "'" + ")";
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    public String getTableID(String tableNumber) throws SQLException, Exception {
        String sql = "SELECT ID FROM tables WHERE tableNumber = " + tableNumber;
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return String.valueOf(rs.getInt("id"));
        }
        return "";
    }

    public static String getTableNumber(int tableID) throws SQLException, Exception {
        String sql = "SELECT tableNumber FROM tables WHERE ID = " + tableID;
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return String.valueOf(rs.getInt("tableNumber"));
        }
        return "";
    }

    public String getMenuItemID(MenuItem menuItem) throws SQLException, Exception {
        String sql = "SELECT id FROM menuItems WHERE name = " + "'" + menuItem.getName().trim() + "'" + " AND description = " + "'" + menuItem.getDescription().trim() + "'" + "";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return String.valueOf(rs.getInt("id"));
        }
        throw new Exception("menu item not found");
    }

    public void insertOrderToDb(String tableID, String menuItemID, String orderNotes) throws SQLException {
        String ID = "0";
        String status = "waiting";
        String sql = "INSERT INTO `orders` (`ID`, `tableID`, `menuItemID`, `status`, `notes`) VALUES (" + "'" + ID + "'" + "," + "'" + tableID + "'" + "," + "'" + menuItemID + "'" + "," + "'" + status + "'" + "," + "'" + orderNotes + "'" + ")";
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList();
        String sql = "SELECT * FROM orders";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            orders.add(new Order(rs.getString("status"), rs.getInt("tableid"), rs.getInt("menuitemid"), rs.getInt("id"), rs.getString("notes")));
        }
        return orders;
    }

    public List<Order> getOrdersForSpecificTable(Table table) throws Exception {
        List<Order> orders = new ArrayList();
        String thisTableId = getTableID(String.valueOf(table.getTableNumber()));
        String sql = "SELECT * FROM orders WHERE tableID =" + thisTableId;
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            orders.add(new Order(rs.getString("status"), rs.getInt("tableid"), rs.getInt("menuitemid"), rs.getInt("id"), rs.getString("notes")));
        }
        return orders;
    }

    public void setOrderStatusToInProgress(int OrderId) throws SQLException {
        String sql = "UPDATE orders SET status = 'in progress' WHERE id = " + OrderId;
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    public void setOrderCompletionTime(int orderId, int preperationTime) throws SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        sdf.format(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, preperationTime);
        String completionTime = sdf.format(cal.getTime());
        String sql = "UPDATE `orders` SET `completionTime` = '" + completionTime + "' WHERE `orders`.`ID` = '" + orderId + "'";
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    public String getOrderCompletionTime(int orderId) throws SQLException, Exception {
        String sql = "SELECT `completionTime` FROM `orders` WHERE `ID` =  '" + orderId + "'";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return String.valueOf(rs.getTime("completionTime"));
        }
        throw new Exception("order not found");
    }

    public void setOrderStatusToComplete(int OrderId) throws SQLException {
        String sql = "UPDATE orders SET status = 'complete' WHERE id = " + OrderId;
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    public void removeTableFromDB(Table table) throws Exception {
        if (isTableOccupied(table.getTableNumber())) {
            String tableID = getTableID(String.valueOf(table.getTableNumber()));
            String tableNumber = String.valueOf(table.getTableNumber());
            String sql = "DELETE FROM tables WHERE ID = " + tableID + " AND tableNumber = " + tableNumber;
            Statement statement = connection.createStatement();
            statement.execute(sql);
        }
    }

}
