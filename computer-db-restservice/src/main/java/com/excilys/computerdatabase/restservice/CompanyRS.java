package com.excilys.computerdatabase.restservice;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.binding.dto.impl.CompanyDTOMapperImpl;
import com.excilys.computerdatabase.core.model.Company;
import com.excilys.computerdatabase.service.impl.CompanyDBServiceImpl;

/**
 * <h1>Company Rest Service :</h1> Access computer information at:<br>
 * GET /company : list of companies<br>
 * GET /company/id : company with corresponding id<br>
 * DELETE /company/id : delete company with corresponding id<br>
 * 
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
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyRS.class);

	/**
	 * Get a company by id
	 * 
	 * @param id
	 *            : corresponding id
	 * @return company at corresponding id
	 */
	@GET
	@Path("/{id: [0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Company get(@PathParam("id") long id) {
		if (companyDBService.exists(id)) {
			LOGGER.debug("Queried Company for id {} successfully", id);
			return companyDBService.findOne(id);
		}
		return null;
	}

	/**
	 * Returns a list of all companies
	 * 
	 * @return List of companies
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Company> findAll() {
		LOGGER.debug("Queried Company for all companies");
		return companyDBService.findAll();
	}

	/**
	 * Delete a company && all its computers as well.
	 * 
	 * @param id
	 *            id of company.
	 * @return response status
	 */
	@DELETE
	@Path("/{id: [0-9]+}")
	public Response remove(@PathParam("id") long id) {
		Status status = null;
		if (companyDBService.exists(id)) {
			companyDBService.delete(id);
			LOGGER.debug("Company deleted successfully : {}", id);
			status = Status.NO_CONTENT;
		} else {
			LOGGER.debug("There was no company to be deleted at id : {}",id);
			status = Status.BAD_REQUEST;
		}
		return Response.status(status).build();
	}

}