package com.excilys.computerdatabase.restservice;

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
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.binding.dto.impl.ComputerDTOMapperImpl;
import com.excilys.computerdatabase.binding.dto.model.ComputerDTO;
import com.excilys.computerdatabase.service.impl.CompanyDBServiceImpl;
import com.excilys.computerdatabase.service.impl.ComputerDBServiceImpl;

@Service
@Path("/computer")
public class ComputerRS {
	@Autowired
	ComputerDBServiceImpl computerDBService;
	@Autowired
	CompanyDBServiceImpl companyDBService;
	ComputerDTOMapperImpl computerDTOMapper;

	@GET
	@Path("/{id: [0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public ComputerDTO findOne(@PathParam("id") long id) {
		if (computerDBService.exists(id)) {
			computerDTOMapper = new ComputerDTOMapperImpl();
			return computerDTOMapper.mapToDTO(computerDBService.findOne(id));
			}
		return null;}	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ComputerDTO> findAll() {
		computerDTOMapper = new ComputerDTOMapperImpl();
		return computerDTOMapper.mapToDTO(computerDBService.findAll());
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(ComputerDTO computer) {
		computerDTOMapper = new ComputerDTOMapperImpl();
		computerDBService.save(computerDTOMapper.mapFromDTO(computer));
		return Response.status(201).entity(computer).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(ComputerDTO computer) {
		computerDTOMapper = new ComputerDTOMapperImpl();
		computerDBService.save(computerDTOMapper.mapFromDTO(computer));
		return Response.status(201).entity(computer).build();
	}
	

	  @DELETE
	  @Path("/{id: [0-9]+}")
	  public Response remove(@PathParam("id") long id) {
		  Status status = null;
		  if (computerDBService.exists(id)) {
			  computerDBService.delete(id);
			   status = Status.NO_CONTENT;
		  }else{
			    status = Status.BAD_REQUEST;
		  }

	    return Response.status(status).build();
	  }
}
