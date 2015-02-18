package com.excilys.computerdatabase.restservice;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.binding.dto.impl.CompanyDTOMapperImpl;
import com.excilys.computerdatabase.binding.dto.model.CompanyDTO;
import com.excilys.computerdatabase.core.model.Company;
import com.excilys.computerdatabase.service.impl.CompanyDBServiceImpl;

/**
 *  *<h1>Company Rest Service : </h1> Access computer information at:<br>
 *		GET /company : list of companies<br>
 *		GET /company/id : company with corresponding id<br>
 *		DELETE /company/id : delete company with corersponding id<br>
 * @author excilys
 *
 */
@Component
@Path("/company")
@Produces(MediaType.APPLICATION_JSON)
public class CompanyRS {
	@Autowired
	CompanyDBServiceImpl companyDBService;
	CompanyDTOMapperImpl companyDTOMapper = new CompanyDTOMapperImpl();
	
	/** 
	 * Get a company by id
	 * @param id : corresponding id
	 * @return company at corresponding id
	 */
	@GET
	@Path("/{id: [0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Company get(@PathParam("id") long id) {
		if (companyDBService.exists(id)) {
			return companyDBService.findOne(id);

		} else {
			return null;
		}
	}

	/**
	 * Returns a list of all companies
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Company> findAll() {
		return companyDBService.findAll();
	}
	
	/**
	 * Delete a company && all its computers as well.
	 * @param id id of company.
	 * @return response status
	 */
	@DELETE
	@Path("/{id: [0-9]+}")
	public Response remove(@PathParam("id") long id) {
		Status status = null;
		if (companyDBService.exists(id)) {
			companyDBService.delete(id);
			status = Status.NO_CONTENT;
		} else {
			status = Status.BAD_REQUEST;
		}
		return Response.status(status).build();
	}

}