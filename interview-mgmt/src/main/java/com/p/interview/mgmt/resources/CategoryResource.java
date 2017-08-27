package com.p.interview.mgmt.resources;

import java.net.HttpURLConnection;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import com.p.interview.mgmt.exception.RestServiceException;
import com.p.interview.mgmt.pojo.CategoryDTO;
import com.p.interview.mgmt.rpc.InterviewRPC;


/**
 * The Class TopicResource.
 */
@Path("categories")
public class CategoryResource {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(CategoryResource.class.getName());
	
	private InterviewRPC objInterviewRPC=new InterviewRPC(); 

	/**
	 * Gets the all topics list.
	 *
	 * @return the all topics list
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(@Context HttpServletRequest serveletRequest) {

		logger.info("Entered into getAll Categories method");
		
		logger.info("get all category method called");
		
		
		String message = "successfully contacted the restful API server";
		
		Vector<CategoryDTO> list=new Vector<>();
		try {
			list=objInterviewRPC.fetchAllCategories();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(e).build();
		}
		
		return Response.status(HttpURLConnection.HTTP_OK).entity(list).build(); 
	}

	/**
	 * Gets the topic for given id from list.
	 *
	 * @param id
	 * 
	 * @return the topic from list for given id
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(/*@PathParam("id") int id*/@Context UriInfo ui) {
		
		MultivaluedMap<String, String> pathParams = ui.getPathParameters();
		String idstr =pathParams.getFirst("id");
		int id=Integer.parseInt(idstr);

		logger.info("Entered into getCoachingList method");
		String message = "successfully contacted the restful API server";
		logger.info("Information : " + message);
		
		logger.info("get category method for id "
				+ id
				+ " called");
		
		CategoryDTO objCategoryDTO=new CategoryDTO();
		objCategoryDTO.setCatID(id);
		
		try {
			objCategoryDTO=objInterviewRPC.retrieveCategory(objCategoryDTO);
		} catch (RestServiceException e) {
			e.printStackTrace();
			return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity("no topic found for given id : " + id)
					.build();
		}catch (Exception e) {
			e.printStackTrace();
		}


		return Response.status(HttpURLConnection.HTTP_OK).entity(objCategoryDTO).build(); 

	}

	/**
	 * Deletes the topic for given id from list.
	 *
	 * @param id
	 * 
	 * @return the topic from list for given id
	 */
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") int id) {

		logger.info("Entered into getCoachingList method");
		String message = "successfully contacted the restful API server";
		
		logger.info("delete category method for id "
				+ id
				+ " called");
		
		String me=null;
		try {
			me=objInterviewRPC.deleteCategory(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Information : " + message);
		if (me != null) {
			return Response.status(HttpURLConnection.HTTP_OK).entity(me).build();
		} else {
			return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity("no topic found for given id : " + id)
					.build();
		}

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(CategoryDTO topic) {

		logger.info("Entered into getCoachingList method");

		String message = "successfully contacted the restful API server";		
		logger.info("Information : " + message);
		
		logger.info("save category method called");

		/*
		 * TODO Validation of the topic object came , and if any assertion is
		 * failing, error response code should be returned to client
		 */
		try {
//			topic.setDateCreated(new Date());
//			topic.setDateLastModified(new Date());
			int c = 1;
			objInterviewRPC.saveCategory(topic);
			return Response.status(HttpURLConnection.HTTP_OK).entity("{\"status\":\"" + HttpURLConnection.HTTP_OK
					+ "\", \"message\": \" Successfully created new topic : " + c + "\"}").build();
		} catch (Exception e) {

			/*
			 * TODO Error response code must be centralised, or if possible use
			 * SpringREST instead of Jersey framework
			 */
			e.printStackTrace();
			logger.info(e);

			return Response.status(HttpURLConnection.HTTP_NOT_FOUND)
					.entity("{\"status\":\"" + HttpURLConnection.HTTP_NOT_FOUND
							+ "\", \"message\": \" Error while creating new topic : " + e + "\"}")
					.build();
		}	
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(CategoryDTO topic) {

		logger.info("Entered into getCoachingList method");

//		logger.info("person.getFirstName()" + topic.getTitle() + "person.getLastName()" + topic.getDescription()
//				+ "topic.isPersonal()" + topic.isPersonal());

		String message = "successfully contacted the restful API server";
		logger.info("Information : " + message);
		
		logger.info("update category method called");

		/*
		 * TODO Validation of the topic object came , and if any assertion is
		 * failing, error response code should be returned to client
		 */

		try {
//			topic.setDateLastModified(new Date());
			boolean b = true;
			objInterviewRPC.updateCategory(topic);

			return Response.status(HttpURLConnection.HTTP_OK)
					.entity("{\"status\":\""
							+ ((b && true) ? HttpURLConnection.HTTP_OK : HttpURLConnection.HTTP_INTERNAL_ERROR)
							+ "\", \"message\": \"" + ((b && true) ? "Successfully " : "Unsuccessfully ")
							+ "updated group " + topic.getCatID() + "\"}")
					.build();

		} catch (Exception e) {

			e.printStackTrace();
			return Response.status(HttpURLConnection.HTTP_NOT_FOUND)
					.entity("{\"status\":\"" + HttpURLConnection.HTTP_NOT_FOUND
							+ "\", \"message\": \" Error while creating new topic : " + e + "\"}")
					.build();
		}	
		
	}

}
