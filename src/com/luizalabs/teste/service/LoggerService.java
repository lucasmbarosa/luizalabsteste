package com.luizalabs.teste.service;


/**
 * Classe de log
 */
public class LoggerService {
    public static void debug(String message) {
        System.out.println(message);
    }

    public static void debug(Exception ex) {
        debug("Error: ", ex);
        ex.printStackTrace();
    }
    
    public static void debug(String message, Exception ex){
        System.out.println(message + ": " + ex.getMessage());
    }

    public static void info(String message) {
        System.out.println(message);
    }

    public static void error(String message) {
        System.out.println(message);
    }
    
    public static void error(Exception ex) {
        error("Error: ", ex);
    }
    
    public static void error(String message, Exception ex){
        System.out.println(message + ": " + ex.toString().replace("'", "\"") + org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(ex).replace("'", "\""));
        ex.printStackTrace();
    }
}