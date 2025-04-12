package edu.masanz.service;

import edu.masanz.dao.Dao;
import edu.masanz.dto.Item;

import java.util.List;

public class Service {

    private static Dao dao = new Dao();

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
