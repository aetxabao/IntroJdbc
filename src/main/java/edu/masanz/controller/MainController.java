package edu.masanz.controller;

import edu.masanz.service.AuctionService;
import io.javalin.http.Context;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.masanz.dto.Item;

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
            authenticated = AuctionService.authenticate(username, password);
            if (authenticated) {
                isAdministrator = AuctionService.isAdministrator(username);
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
        boolean isAdministrator = context.sessionAttribute("isAdministrator");
        Map<String, Object> model = new HashMap<>();
        model.put("isAdministrator", isAdministrator);
        context.render("/templates/menu.ftl", model);
    }

    public static void mostrarCrearOferta(Context context) {
        logger.info("mostrarCrearOferta");
        // TODO: mostrarCrearOferta
        context.redirect("/menu");
    }

    public static void repasarOferta(Context context) {
        logger.info("repasarOferta");
        // TODO: repasarOferta
        context.redirect("/menu");
    }

    public static void confirmarOferta(Context context) {
        logger.info("confirmarOferta");
        // TODO: confirmarOferta
        context.redirect("/menu");
    }

    public static void mostrarOfertas(Context context) {
        logger.info("mostrarOfertas");
        // TODO: mostrarOfertas
        context.redirect("/menu");
    }
}
