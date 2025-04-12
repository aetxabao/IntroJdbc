package edu.masanz.controller;

import edu.masanz.dto.Item;
import edu.masanz.service.AuctionService;
import io.javalin.http.Context;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainController {
    private static final Logger logger = LogManager.getLogger(MainController.class);
    public static void iniciar(Context context) {
        logger.info("iniciar");
        context.req().getSession().invalidate();
        Map<String, Object> model = new HashMap<>();
        model.put("username", "");
        model.put("error", false);
        context.render("/templates/login.ftl", model);
    }

    public static void autenticar(Context context) {
        logger.info("autenticar");
        String username = null;
        String password = null;
        boolean authenticated = false;
        boolean isAdministrator = false;
        try {
            username = context.formParam("username").trim();
            password = context.formParam("password");
            // ...
            // de momemto no se comprueba la contraseña, sólo que no estén vacíos los campos
            if (username == null || password == null || username.length() == 0 || password.length() == 0) {
                authenticated = false;
            }else {
                isAdministrator = false;
                authenticated = true;
            }
        }catch (Exception e) {
        }
        Map<String, Object> model = new HashMap<>();
        if (!authenticated) {
            model.put("username", username);
            model.put("error", true);
            context.render("/templates/login.ftl", model);
        }else {
            context.sessionAttribute("username", username);
            context.sessionAttribute("isAdministrator", isAdministrator);
            context.req().changeSessionId();
            context.redirect("/menu");
        }
    }

    public static void mostrarMenu(Context context) {
        logger.info("mostrarMenu");
        String username = context.sessionAttribute("username");
        if (username == null) {
            context.redirect("/error");
            return;
        }
        boolean isAdministrator = context.sessionAttribute("isAdministrator");
        Map<String, Object> model = new HashMap<>();
        model.put("isAdministrator", isAdministrator);
        context.render("/templates/menu.ftl", model);
    }

    public static void mostrarCrearOferta(Context context) {
        logger.info("mostrarCrearOferta");
        String username = context.sessionAttribute("username");
        boolean error = false;
        Item item = new Item();
        Map<String, Object> model = new HashMap<>();
        model.put("username", username);
        model.put("error", error);
        model.put("item", item);
        context.render("/templates/offer.ftl", model);
    }

    public static void repasarOferta(Context context) {
        logger.info("repasarOferta");
        String sessionUsername = context.sessionAttribute("username");
        String username = "";
        String password = "";
        String nombre;
        String desc;
        int precioInicio;
        String urlImagen;
        boolean error = false;
        Item item = new Item();
        try {
            username = context.formParam("username");
            password = context.formParam("password");
            // ...
            nombre = context.formParam("nombre");
            desc = context.formParam("desc");
            precioInicio = Integer.parseInt(context.formParam("precioInicio"));
            urlImagen = context.formParam("urlImagen");
            item = new Item(nombre, desc, precioInicio, urlImagen, username);
            long id = AuctionService.createItem(item);
            if (id == 0) {
                error = true;
            }else {
                item.setId(id);
            }
        }catch (Exception e) {
            error = true;
            logger.error("Error: " + e.getMessage());
        }
        Map<String, Object> model = new HashMap<>();
        model.put("username", username);
        model.put("error", error);
        model.put("item", item);
        if (error) {
            context.render("/templates/offer.ftl", model);
        }else {
            context.render("/templates/check.ftl", model);
        }
    }

    public static void confirmarOferta(Context context) {
        logger.info("confirmarOferta");
        String sessionUsername = context.sessionAttribute("username");
        String username = sessionUsername;
        String password = "";
        long id = 0;
        String nombre;
        String desc;
        int precioInicio;
        String urlImagen;
        int estado = 0;
        boolean historico = false;
        boolean error = false;
        Item item = new Item();
        try {
            id = Long.parseLong(context.pathParam("id"));
            nombre = context.formParam("nombre");
            desc = context.formParam("desc");
            precioInicio = Integer.parseInt(context.formParam("precioInicio"));
            urlImagen = context.formParam("urlImagen");
            item = new Item(id, nombre, desc, precioInicio, urlImagen, username, estado, historico);
            if (!error) {
                error = !AuctionService.updateItem(item);
                logger.info("item: " + item);
                // {"id":6,"name":"Muñeco de Duke","desc":"Mascota de Java de 15 cm de alto hecha a punto rellena de algodón.",
                //  "price":20,"url":"https://i.postimg.cc/3RcGmk4s/duke.png","username":"Amaia","valid":true}
            }
        }catch (Exception e) {
            error = true;
            logger.error("Error: " + e.getMessage());
        }
        if (error) {
            Map<String, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("error", error);
            model.put("item", item);
            context.render("/templates/check.ftl", model);
        }else {
            context.redirect("/user/ver-ofertas");
        }
    }

    public static void mostrarOfertas(Context context) {
        logger.info("mostrarOfertas");
        String username = "";
        List<Item> items = AuctionService.getAllItems();
        Map<String, Object> model = new HashMap<>();
        model.put("items", items);
        context.render("/templates/offer-list.ftl", model);
    }
}
