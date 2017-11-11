package com.luizalabs.teste.api;

import java.util.ArrayList;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.luizalabs.teste.common.PayLoadIn;
import com.luizalabs.teste.common.PayLoadOut;
import com.luizalabs.teste.service.LoggerService;
import com.luizalabs.teste.service.PayLoadInService;

@Path("/main")
public class MainController {
	
	/**
     * Método para filtrar,agrupar e ordenar a entrada 
     * 
     * @return ArrayList<PayLoadOut>
     * @throws Exception
     */
	@POST
    @Path("/organize")
    @Produces("application/json")
    public ArrayList<PayLoadOut> organize(PayLoadIn payLoadIn) {	    
        try {
			return new PayLoadInService().organize(payLoadIn);
		} catch (Exception e) {
			LoggerService.error(e.getMessage());
			return null;
		}
    }
	
}