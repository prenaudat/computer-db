package com.excilys.computerdatabase.restservice;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.binding.dto.impl.CompanyDTOMapperImpl;
import com.excilys.computerdatabase.binding.dto.model.CompanyDTO;
import com.excilys.computerdatabase.service.impl.CompanyDBServiceImpl;

@Component
@Path("/company")
@Produces(MediaType.APPLICATION_JSON)
public class CompanyRS {
	@Autowired
	CompanyDBServiceImpl companyDBService;
	CompanyDTOMapperImpl companyDTOMapper = new CompanyDTOMapperImpl();
	@GET
	@Path("{id}")
	public Response get(@PathParam("id") long id) {
		if (companyDBService.exists(id)) {
//			String result = null;
//			try {
////				result = new ObjectMapper().writeValueAsString(companyDBService.findOne(id));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return Response.ok(result, MediaType.APPLICATION_JSON).build();
			return Response.ok("No media found", MediaType.APPLICATION_JSON)
					.build();

		} else {
			return Response.ok("No media found", MediaType.APPLICATION_JSON)
					.build();
		}
	}

	@GET
	public Response findAll() {
		String result=null;
//		try {
//			result = new ObjectMapper().writeValueAsString(companyDTOMapper.mapToDTO(companyDBService.findAll()));
//		} catch (IOException e) {
//			//Logger.log("DATA IS CORRUPTED pls abort");
//		}
		return Response.ok(result, MediaType.APPLICATION_JSON).build();
	}
}