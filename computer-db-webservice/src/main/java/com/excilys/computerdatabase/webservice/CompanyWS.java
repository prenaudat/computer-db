package com.excilys.computerdatabase.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.service.impl.CompanyDBServiceImpl;

@Component
@Path("/company")
@Produces(MediaType.APPLICATION_JSON)
public class CompanyWS {

	@GET
	public String get(){
		return "Request successful";
	}
}