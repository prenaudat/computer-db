package com.excilys.computerdatabase.restinterface;

import java.util.List;

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

import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.binding.common.resources.PageResponse;
import com.excilys.computerdatabase.binding.dto.model.ComputerDTO;

/**
 * Computer RestService interface
 * @author excilys
 *
 */
@Service
public interface IComputerRestService {
	@GET
	@Path("/{id: [0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public ComputerDTO findOne(@PathParam("id") long id);

	/**
	 * Returns list of all computers in database.
	 * 
	 * @return List<\ComputerDTO\> of all computers in database
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ComputerDTO> findAll();

	/**
	 * Post a computer to be saved.
	 * 
	 * @param computer
	 *            extracted from JSON
	 * @return Response object
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(ComputerDTO computer);

	/**
	 * PUT a computer to be upserted (updated or inserted)
	 * 
	 * @param computer
	 *            ComputerDTO extracted from JSON
	 * @return Response object
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(ComputerDTO computer);

	/**
	 * Remove computer by id;
	 * 
	 * @param id
	 *            Id of computer to be deleted
	 * @return Response object
	 */
	@DELETE
	@Path("/{id: [0-9]+}")
	public Response remove(@PathParam("id") long id);

	/**
	 * Returns page at index 'id'
	 * 
	 * @param page
	 *            page number
	 * @return PageImpl<\ComputerDTO\>
	 */
	@GET
	@Path("/page/{page: [0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public PageResponse<ComputerDTO> getPage(@PathParam("page") int page);

}
