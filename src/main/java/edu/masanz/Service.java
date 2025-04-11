package edu.masanz;

import java.util.ArrayList;
import java.util.List;

public class Service {

    private static Dao dao = new Dao();

    public static List<Item> getAllItems() {
        // TODO: getAllItems
        return new ArrayList<>();
    }

    public static Item getItemById(long idItem) {
        // TODO: getItemById
        return new Item();
    }
    public static boolean existsItem(long idItem) {
        // TODO: existsItem
        return false;
    }

    public static long createItem(Item item) {
        // TODO: createItem
        return 0L;
    }

    public static boolean updateItem(Item item) {
        // TODO: updateItem
        return false;
    }

    public static boolean deleteItem(long idItem) {
        // TODO: deleteItem
        return false;
    }

    public static void endService() {
        // TODO: endService
    }

}
