package edu.masanz.service;

import edu.masanz.dao.Dao;
import edu.masanz.dto.Item;

import java.util.List;

public class AuctionService {

    private static Dao dao = new Dao();

    public static boolean authenticate(String username, String password) {
        // TODO: authenticate
        return !(username == null || password == null || username.length() == 0 || password.length() == 0);
    }

    public static boolean isAdministrator(String username) {
        // TODO: isAdministrator
        return false;
    }

    public static List<Item> getAllItems() {
        return dao.getAllItems();
    }

    public static Item getItemById(long idItem) {
        return dao.getItemById(idItem);
    }
    public static boolean existsItem(long idItem) {
        return dao.existsItem(idItem);
    }

    public static long createItem(Item item) {
        if (item != null) {
            return dao.addItem(item);
        }
        return 0;
    }

    public static boolean updateItem(Item item) {
        if (item != null) {
            dao.updateItem(item);
            return true;
        }
        return false;
    }

    public static boolean deleteItem(long idItem) {
        if (existsItem(idItem)) {
            dao.deleteItem(idItem);
            return true;
        }
        return false;
    }

    public static void endService() {
        dao.cerrarConexion();
    }

}
