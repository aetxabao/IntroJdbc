package edu.masanz;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinFreemarker;

import edu.masanz.controller.AuditController;
import edu.masanz.controller.MainController;
import org.eclipse.jetty.server.session.DefaultSessionCache;
import org.eclipse.jetty.server.session.FileSessionDataStore;
import org.eclipse.jetty.server.session.SessionCache;
import org.eclipse.jetty.server.session.SessionHandler;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        Javalin app1 = Javalin.create(config -> {
            config.jetty.modifyServletContextHandler(handler -> handler.setSessionHandler(fileSessionHandler("session1")));
            config.staticFiles.add("/public");
            config.fileRenderer(new JavalinFreemarker());
        }).start(4444);

        setJavalinAppRoutes(app1);

    }

    private static void setJavalinAppRoutes(Javalin app) {

        app.after("/autenticar", AuditController::grabarLogin);
        app.before("/menu", AuditController::comprobarUser);
        app.before("/admin/*", AuditController::comprobarAdmin);
        app.before("/user/*", AuditController::comprobarUser);

        app.get("/", MainController::iniciar);
        app.get("/login", MainController::iniciar);

        app.get("/error", MainController::iniciar);
        app.get("/exit", MainController::iniciar);

        app.post("/autenticar", MainController::autenticar);
        app.get("/menu", MainController::mostrarMenu);

        app.get("/user/crear-oferta", MainController::mostrarCrearOferta);
        app.post("/user/repasar-oferta", MainController::repasarOferta);
        app.post("/user/confirmar-oferta/{id}", MainController::confirmarOferta);

        app.get("/user/ver-ofertas", MainController::mostrarOfertas);

        app.get("/user/pujar/{id}", MainController::mostrarMenu);

    }

    public static SessionHandler fileSessionHandler(String sessionName) {
        SessionHandler sessionHandler = new SessionHandler();
        SessionCache sessionCache = new DefaultSessionCache(sessionHandler);
        sessionCache.setSessionDataStore(fileSessionDataStore());
        sessionHandler.setSessionCache(sessionCache);
        sessionHandler.setHttpOnly(true);
        // make additional changes to your SessionHandler here
        sessionHandler.setSessionCookie(sessionName);// YO
        return sessionHandler;
    }

    private static FileSessionDataStore fileSessionDataStore() {
        FileSessionDataStore fileSessionDataStore = new FileSessionDataStore();
        File baseDir = new File(System.getProperty("java.io.tmpdir"));
        File storeDir = new File(baseDir, "javalin-session-store");
        //C:\Users\USUARIO\AppData\Local\Temp\javalin-session-store
        storeDir.mkdir();
        fileSessionDataStore.setStoreDir(storeDir);
        return fileSessionDataStore;
    }

}