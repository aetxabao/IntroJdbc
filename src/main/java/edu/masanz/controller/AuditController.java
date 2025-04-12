package edu.masanz.controller;

import io.javalin.http.Context;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuditController {
    private static final Logger logger = LogManager.getLogger(AuditController.class);

    public static void comprobarAdmin(Context context) {
        String username = context.sessionAttribute("username");
        if (username == null) {
            context.redirect("/error");
            return;
        }
        boolean isAdministrator = context.sessionAttribute("isAdministrator");
        if (!isAdministrator) {
            context.redirect("/error");
            return;
        }
    }

    public static void comprobarUser(Context context) {
        String username = context.sessionAttribute("username");
        if (username == null) {
            context.redirect("/error");
            return;
        }
    }

    public static void grabarLogin(Context context) {
        String username = context.formParam("username");
        logger.info("Usuario {} ha iniciado sesión", username);
    }

}
