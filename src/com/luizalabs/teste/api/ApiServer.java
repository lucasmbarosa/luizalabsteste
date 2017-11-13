package com.luizalabs.teste.api;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.luizalabs.teste.service.LoggerService;

/**
 * Classe para iniciar a api
 */
public class ApiServer {
    /**
     * Porta que a api estará ouvindo
     */
    public static final int PORT = 8080;

    /**
     * URL que a api estará ouvindo
     */
    public static String BASE_URI = "";

    /**
     * Inicia HTTP server.
     * 
     * @return Grizzly HTTP server.
     * @throws IOException
     */
    public static HttpServer startServer() throws IOException {
        final ResourceConfig rc = new ResourceConfig().packages("com.luizalabs.teste.api");
        rc.register(JacksonFeature.class);
        String ip = getIp();
        BASE_URI = String.format("http://%s:%s/api/", ip, PORT);
        HttpServer httpServer = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc, false);
                
        httpServer.getServerConfiguration().addHttpHandler(new HttpHandler() {
            @Override
            public void service(Request request, Response response) throws Exception {
                response.getWriter().write("API");
            }
        }, "/dynamic");

        try {
            httpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("Hit ENTER to close...");
            System.in.read();
            System.exit(0);
        }

        return httpServer;
    }

    /**
     * Método main.
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        String ip = getIp();

        System.out.println(String.format("Teste Luiza Labs API iniciado\nDisponível em " + "%s\n", BASE_URI));
        if (ip.equals("localhost")) {
            System.out.println("Host = 'localhost' --> A api não está disponível para outros devices (localhost)");
        }
        System.in.read();
        server.shutdownNow();
    }

    
    /**
     * Método para retornar o ip da máquina servidor (local)s.
     * 
     * @param args
     * @throws IOException
     */
    private static String getIp() {
        String ip = "localhost";
        String apiHost = "";
        if ((apiHost.equals(""))) {
            try {
                InetAddress addr = InetAddress.getLocalHost();
                ip = addr.getHostAddress();
            } catch (UnknownHostException e) {
                LoggerService.debug(e);
            }
        } else {
            ip = apiHost;
        }

        return ip;
    }
}