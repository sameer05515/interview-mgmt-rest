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
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.p.interview.mgmt.pojo.AnswerDTO;
import com.p.interview.mgmt.pojo.CategoryDTO;
import com.p.interview.mgmt.pojo.QuestionDTO;
import com.p.interview.mgmt.rpc.InterviewRPC;

/**
 * The Class TopicResource.
 */
@Path("categories/{linkedCategoryID}/questions/{linkedQuestionID}/answers")
public class AnswerResource {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(AnswerResource.class.getName());

	private InterviewRPC objInterviewRPC = new InterviewRPC();

	/**
	 * Gets the all topics list.
	 *
	 * @return the all topics list
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(@Context HttpServletRequest serveletRequest,
			@PathParam("linkedCategoryID") int linkedCategoryID, @PathParam("linkedQuestionID") int linkedQuestionID) {

		logger.info("Entered into getCoachingList method");

		logger.info("get all category method called");

		String message = "successfully contacted the restful API server";
		Vector<AnswerDTO> list = new Vector<>();

		QuestionDTO objQuestionDTO = new QuestionDTO();
		objQuestionDTO.setQuestionID(linkedQuestionID);
		objQuestionDTO.setLinkedCatID(linkedCategoryID);

		try {

			list = objInterviewRPC.fetchAllAnswersByQuestion(objQuestionDTO);

			logger.info("Information : " + message + list);

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
	public Response get(@PathParam("id") int id, @PathParam("linkedCategoryID") int linkedCategoryID,
			@PathParam("linkedQuestionID") int linkedQuestionID) {

		logger.info("Entered into getCoachingList method");
		String message = "successfully contacted the restful API server";
		logger.info("Information : " + message);

		logger.info("get category method for id " + id + " called");

		AnswerDTO objQuestionDTO = new AnswerDTO();
		objQuestionDTO.setLinkedQuesID(linkedQuestionID);
		objQuestionDTO.setLinkedCatID(linkedCategoryID);
		objQuestionDTO.setAnsID(id);
		

		try {
			objQuestionDTO = objInterviewRPC.retrieveAnswer(objQuestionDTO);
			return Response.status(HttpURLConnection.HTTP_OK).entity(objQuestionDTO).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity("{\"status\":\"" + HttpURLConnection.HTTP_NOT_FOUND
					+ "\", \"message\": \" no answer found for given id :  " + id + "\"}")
					.build();
		}

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
	public Response delete(@PathParam("id") int id, @PathParam("linkedCategoryID") int linkedCategoryID,
			@PathParam("linkedQuestionID") int linkedQuestionID) {

		logger.info("Entered into getCoachingList method");
		String message = "successfully contacted the restful API server";

		logger.info("delete answer method for id " + id + " called");
		
		String me = null;
		
		AnswerDTO objQuestionDTO = new AnswerDTO();
		objQuestionDTO.setLinkedQuesID(linkedQuestionID);
		objQuestionDTO.setLinkedCatID(linkedCategoryID);
		objQuestionDTO.setAnsID(id);
		
		try {
			me = objInterviewRPC.deleteAnswer(objQuestionDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Information : " + message);
		if (me != null) {
			return Response.status(HttpURLConnection.HTTP_OK).entity(me).build();
		} else {
			return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity("{\"status\":\"" + HttpURLConnection.HTTP_NOT_FOUND
					+ "\", \"message\": \" no answer found for given id :  " + id + "\"}")
					.build();
		}

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(AnswerDTO objAnswerDTO, @PathParam("linkedCategoryID") int linkedCategoryID,
			@PathParam("linkedQuestionID") int linkedQuestionID) {

		logger.info("Entered into getCoachingList method");

		// logger.info("person.getFirstName()" + topic.getTitle() +
		// "person.getLastName()" + topic.getDescription()
		// + "topic.isPersonal()" + topic.isPersonal());

		String message = "successfully contacted the restful API server";

		logger.info("Information : " + message);

		logger.info("save category method called");

		/*
		 * TODO Validation of the topic object came , and if any assertion is
		 * failing, error response code should be returned to client
		 */
		
		objAnswerDTO.setLinkedCatID(linkedCategoryID);
		objAnswerDTO.setLinkedQuesID(linkedQuestionID);
		
		try {
			// topic.setDateCreated(new Date());
			// topic.setDateLastModified(new Date());
			int c = 1;
			objInterviewRPC.saveAnswer(objAnswerDTO);
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
	public Response update(AnswerDTO objAnswerDTO, @PathParam("linkedCategoryID") int linkedCategoryID,
			@PathParam("linkedQuestionID") int linkedQuestionID) {

		logger.info("Entered into getCoachingList method");

		// logger.info("person.getFirstName()" + topic.getTitle() +
		// "person.getLastName()" + topic.getDescription()
		// + "topic.isPersonal()" + topic.isPersonal());

		String message = "successfully contacted the restful API server";
		logger.info("Information : " + message);

		logger.info("update category method called");

		/*
		 * TODO Validation of the topic object came , and if any assertion is
		 * failing, error response code should be returned to client
		 */
		objAnswerDTO.setLinkedCatID(linkedCategoryID);
		objAnswerDTO.setLinkedQuesID(linkedQuestionID);

		try {
			// topic.setDateLastModified(new Date());
			boolean b = true;
			objInterviewRPC.updateAnswer(objAnswerDTO);

			return Response.status(HttpURLConnection.HTTP_OK)
					.entity("{\"status\":\""
							+ ((b && true) ? HttpURLConnection.HTTP_OK : HttpURLConnection.HTTP_INTERNAL_ERROR)
							+ "\", \"message\": \"" + ((b && true) ? "Successfully " : "Unsuccessfully ")
							+ "updated group " + objAnswerDTO.getAnsID() + "\"}")
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
