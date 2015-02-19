package com.excilys.computerdatabase.restinterface;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.binding.dto.model.CompanyDTO;
@Service
public interface ICompanyRestService {
	  @POST
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  CompanyDTO create(CompanyDTO companyDTO);

	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  List<CompanyDTO> findAll();

	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  @Path("/{id: [0-9]+}")
	  CompanyDTO findById(@PathParam("id") long id);

	  @DELETE
	  @Path("/{id: [0-9]+}")
	  Response remove(@PathParam("id") long id);

}
