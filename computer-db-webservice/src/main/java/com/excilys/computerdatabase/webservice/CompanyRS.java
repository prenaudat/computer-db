package com.excilys.computerdatabase.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.core.model.Company;
import com.excilys.computerdatabase.service.impl.CompanyDBServiceImpl;

@Component
@Path("/company")
@Produces(MediaType.APPLICATION_JSON)
public class CompanyRS {
	@Autowired
	CompanyDBServiceImpl companyDBService;

	@GET
	@Path("{id}")
	public Response get(@PathParam("id")long id) {
		if(companyDBService.exists(id)){
			System.out.println("computer at"+ id + "is "+companyDBService.findOne(id) );
		Company result = companyDBService.findOne(id);
		String json = new StringBuilder("{\"id\":").append(result.getId()).append(",\"name\":\"").append(result.getName()).append("\"}").toString();
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
		}else{
			return Response.ok("No media found", MediaType.APPLICATION_JSON).build();
		}
	}
//	@GET
//	public Response findAll()
}