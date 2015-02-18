package com.excilys.computerdatabase.webservice;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.binding.dto.model.ComputerDTO;
import com.excilys.computerdatabase.core.model.Company;
import com.excilys.computerdatabase.core.model.Computer;
import com.excilys.computerdatabase.service.impl.CompanyDBServiceImpl;
import com.excilys.computerdatabase.service.impl.ComputerDBServiceImpl;

@Service
@Path("/computer")
@Produces(MediaType.APPLICATION_JSON)
public class ComputerRS {
	@Autowired
	ComputerDBServiceImpl computerDBService;
	@Autowired
	CompanyDBServiceImpl companyDBService;
	@GET
	@Path("{id}")
	public Response get(@PathParam("id") long id) {
		if (computerDBService.exists(id)) {
			System.out.println("computer at" + id + "is "
					+ computerDBService.findOne(id));
			Computer result = computerDBService.findOne(id);
			String json = new StringBuilder("{\"id\":")
				.append(result.getId())
				.append(",\"name\":\"")
				.append(result.getName())
				.append("\",\"introduced\":\"")
				.append(result.getIntroduced())
				.append("\",\"discontinued\":\"")
				.append(result.getDiscontinued())
				.append("\",\"companyId\":\"")
				.append(result.getCompanyId())
				.append("\"}").toString();
			return Response.ok(json, MediaType.APPLICATION_JSON).build();
		} else {
			return Response.status(404).build();
		}
	}

}
