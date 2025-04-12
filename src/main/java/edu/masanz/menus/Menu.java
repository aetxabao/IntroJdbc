package edu.masanz.menus;


import edu.masanz.dto.Item;
import edu.masanz.service.AuctionService;

import java.util.Scanner;

public class Menu {

    public static final int LONG_TITULO = 85;
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        mostrarTitulo("EJEMPLO ACCESO A BBDD");
        mostrarMenu();
        int opc = leerOpcion("Opción", 0, 5);
        while (opc != 0) {
            switch (opc) {
                case 1:
                    showAllItems();
                    break;
                case 2:
                    showItem();
                    break;
                case 3:
                    createItem();
                    break;
                case 4:
                    updateItem();
                    break;
                case 5:
                    deleteItem();
                    break;
                default:
            }
            mostrarMenu();
            opc = leerOpcion("Opción", 0, 5);
        }
        exit();
    }

    private void mostrarTitulo(String titulo) {
        int n = LONG_TITULO, m1, m2;
        m1 = n - titulo.length();
        m2 = m1 / 2 - 1;
        m1 = m1 % 2 == 0? m2 : m2+1;
        System.out.println("*".repeat(n));
        System.out.println("*" + " ".repeat(m1) + titulo + " ".repeat(m2) + "*");
        System.out.println("*".repeat(n));
    }

    public void mostrarMenu() {
        System.out.println();
        System.out.println("1. Mostrar todos los items");
        System.out.println("2. Buscar un item por id");
        System.out.println("3. Crear item");
        System.out.println("4. Actualizar item");
        System.out.println("5. Eliminar item");
        System.out.println("0. Exit");
    }

    public int leerOpcion(String txt, int min, int max) {
        int opc = -1;
        if (max == Integer.MAX_VALUE) {
            System.out.printf("%s: ", txt);
        } else {
            System.out.printf("%s [%d,%d]: ", txt, min, max);
        }
        while(opc<min || opc>max) {
            try {
                opc = Integer.parseInt(scanner.nextLine().trim());
            }catch (Exception e) { }
            System.out.println();
        }
        return opc;
    }

    private void showAllItems() {
        String s = "Lista de todos los items:";
        System.out.println(s);
        System.out.println("=".repeat(s.length()));
        System.out.println();
        for (Item item : AuctionService.getAllItems()) {
            System.out.println(item);
        }
    }

    private void showItem() {
        long idItem = pedirIdItem("Ingrese el id del item a buscar: ");
        System.out.println();
        Item item = AuctionService.getItemById(idItem);
        if (item != null) {
            System.out.println("Item encontrado:");
            System.out.println(item);
        } else {
            System.out.println("No se encontró el item con id " + idItem);
        }
    }

    private void createItem() {
        Item item = pedirItem("Ingrese el item a crear: ", false);
        if (item != null) {
            long id = AuctionService.createItem(item);
            item.setId(id);
            System.out.println("Item creado: " + item);
        } else {
            System.out.println("No se pudo crear el item.");
        }
    }

    private void updateItem() {
        Item item = pedirItem("Ingrese el item a modificar: ", true);
        if (item != null && AuctionService.updateItem(item)) {
            System.out.printf("Item %d actualizado", item.getId());
        } else {
            System.out.println("No se pudo actualizar el item.");
        }
    }

    private void deleteItem() {
        long idItem = pedirIdItem("Ingrese el id del item a eliminar: ");
        if (AuctionService.deleteItem(idItem)) {
            System.out.printf("Item %d eliminado", idItem);
        } else {
            System.out.println("No se pudo eliminar el item.");
        }
    }

    private long pedirIdItem(String msg) {
        System.out.print(msg);
        while(true) {
            try {
                long id = Long.parseLong(scanner.nextLine());
                return id;
            }catch (Exception e) { }
        }
    }

    private Item pedirItem(String msg, boolean itemExists ) {
        long id = 0;
        String nombre = "";
        String desc = "";
        int precioInicio = 0;
        String urlImagen = "";
        String nombreUsuario = "";
        int estado = 0;//pendiente
        boolean historico = false;
        System.out.println(msg);
        if (itemExists) {
            id = pedirIdItem("Id del item: ");
            if (!AuctionService.existsItem(id)) {
                System.out.println("No se encontró el item con id " + id);
                return null;
            }
            Item item = AuctionService.getItemById(id);
            if (item != null) {
                nombre = item.getNombre();
                desc = item.getDesc();
                precioInicio = item.getPrecioInicio();
                urlImagen = item.getUrlImagen();
                nombreUsuario = item.getNombreUsuario();
                estado = item.getEstado();
                historico = item.isHistorico();
            }
        }
        String s = "";
        try {
            System.out.printf("Nombre (%s): ", nombre);
            s = scanner.nextLine();
            if (!s.isEmpty()) { nombre = s; }
            System.out.printf("Descripción (%s): ", desc);
            s = scanner.nextLine();
            if (!s.isEmpty()) { desc = s; }
            System.out.printf("Precio (%d): ", precioInicio);
            s = scanner.nextLine();
            if (!s.isEmpty()) { precioInicio = Integer.parseInt(s); }
            System.out.printf("URL (%s): ", urlImagen);
            s = scanner.nextLine();
            if (!s.isEmpty()) { urlImagen = s; }
            System.out.printf("Usuario (%s): ", nombreUsuario);
            s = scanner.nextLine();
            if (!s.isEmpty()) { nombreUsuario = s; }
        }catch (Exception e) {
            System.out.println("Error al leer los datos del item");
            estado = -2; // cancelado
        }
        return new Item(id, nombre, desc, precioInicio, urlImagen, nombreUsuario, estado, historico);
    }

    private void exit() {
        AuctionService.endService();
    }

}
