package com.excilys.computerdatabase.restservice;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.binding.dto.impl.ComputerDTOMapperImpl;
import com.excilys.computerdatabase.binding.dto.model.ComputerDTO;
import com.excilys.computerdatabase.core.common.RequestPage;
import com.excilys.computerdatabase.core.model.Computer;
import com.excilys.computerdatabase.service.impl.CompanyDBServiceImpl;
import com.excilys.computerdatabase.service.impl.ComputerDBServiceImpl;

/**
 * <h1>Computer Rest Service :</h1> Access computer information at:<br>
 * GET /computer : list of computers<br>
 * GET /computer/id : computer with corresponding id<br>
 * GET /computer/page/id : returns page with page number corresponding to id.
 * 0-based<br>
 * POST /computer : add a computer (requires Content-Type: json/application)<br>
 * PUT /computer : update a computer<br>
 * DELETE /computer/id<br>
 * 
 * @author excilys
 */
@Service
@Path("/computer")
public class ComputerRS {
	@Autowired
	ComputerDBServiceImpl computerDBService;
	@Autowired
	CompanyDBServiceImpl companyDBService;
	ComputerDTOMapperImpl computerDTOMapper;
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerRS.class);

	/**
	 * Finds a computer with corresponding ID path variable.
	 * 
	 * @param id id corresponding to a computer in database.
	 * @return Computer with corresponding id or null if not exists
	 */
	@GET
	@Path("/{id: [0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public ComputerDTO findOne(@PathParam("id") long id) {
		if (computerDBService.exists(id)) {
			computerDTOMapper = new ComputerDTOMapperImpl();
			LOGGER.info("Computer found successfully at ID : {} ", id);
			return computerDTOMapper.mapToDTO(computerDBService.findOne(id));
		}
		LOGGER.info("No computer found at ID : {} ", id);
		return null;
	}

	/**
	 * Returns list of all computers in database.
	 * 
	 * @return List of ComputerDTO  of all computers in database
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ComputerDTO> findAll() {
		LOGGER.info("All computers queried");
		computerDTOMapper = new ComputerDTOMapperImpl();
		return computerDTOMapper.mapToDTO(computerDBService.findAll());
	}

	/**
	 * Post a computer to be saved.
	 * 
	 * @param computer extracted from JSON
	 * @return Response object
	 * @throws IOException 
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(ComputerDTO computer) {

		LOGGER.info("Saved computer {}", computer);
		computerDTOMapper = new ComputerDTOMapperImpl();
		computerDTOMapper.mapFromDTO(computer);
		computerDBService.save(computerDTOMapper.mapFromDTO(computer));
		return Response.status(201).entity(computer).build();
	}

	/**
	 * PUT a computer to be upserted (updated or inserted)
	 * 
	 * @param computer ComputerDTO extracted from JSON
	 * @return Response object
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(ComputerDTO computer) {
		LOGGER.info("Updated ComputerDTO : {} ", computer);
		computerDTOMapper = new ComputerDTOMapperImpl();
		computerDBService.save(computerDTOMapper.mapFromDTO(computer));
		return Response.status(201).entity(computer).build();
	}

	/**
	 * Remove computer by id;
	 * 
	 * @param id  Id of computer to be deleted
	 * @return Response object
	 */
	@DELETE
	@Path("/{id: [0-9]+}")
	public Response remove(@PathParam("id") long id) {
		Status status = null;
		if (computerDBService.exists(id)) {
			LOGGER.info("Deleted computer at id : {}", id);
			computerDBService.delete(id);
			status = Status.NO_CONTENT;
		} else {
			LOGGER.error("Failed to delete computer (Didn't exist) at id : {}", id);
			status = Status.BAD_REQUEST;
		}

		return Response.status(status).build();
	}

	/**
	 * Returns page at index 'id'
	 * 
	 * @param page page number
	 * @return PageImpl of ComputerDTO
	 */
	@GET
	@Path("/page/{page: [0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Page<ComputerDTO> getPage(@PathParam("page") int page) {
		computerDTOMapper = new ComputerDTOMapperImpl();
		Pageable pageable = new RequestPage(page, 10);
		Page<Computer> retrievedPage = computerDBService.retrievePage(pageable,
				"");
		LOGGER.info("Retrieved page number {}", page);
		return new PageImpl<ComputerDTO>(
				computerDTOMapper.mapToDTO(retrievedPage.getContent()),
				pageable, retrievedPage.getTotalElements());
	}
}
