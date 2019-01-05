package com.p.interview.mgmt.resources;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
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
import com.p.interview.mgmt.pojo.QuestionDTO;
import com.p.interview.mgmt.pojo.QuestionJsonResponseForReport;
import com.p.interview.mgmt.rpc.InterviewRPC;

/**
 * The Class CategoryResource.
 */
@Path("report")
public class ReportResource {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(ReportResource.class.getName());

	private InterviewRPC objInterviewRPC = new InterviewRPC();

	/**
	 * Gets the all categories list.
	 *
	 * @return the all categories list
	 */
	@GET
	@Path("/allQuestions")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCategories(@Context HttpServletRequest serveletRequest) {

		logger.info(
				"Entered into com.p.interview.mgmt.resources.CategoryResource.getAllCategories(HttpServletRequest) method");
		String message = "successfully contacted the restful API server";
		logger.info("Information : " + message);
		logger.info(
				"com.p.interview.mgmt.resources.CategoryResource.getAllCategories(HttpServletRequest) method called");

		Vector<CategoryDTO> list = new Vector<>();
		List<QuestionJsonResponseForReport> listQuestionJsonResponseForReport = new ArrayList<QuestionJsonResponseForReport>();
		try {
			list = objInterviewRPC.fetchAllCategories();
			if (list != null) {
				for (CategoryDTO c : list) {
					if (c.getQuestions() != null) {
						for (QuestionDTO q : c.getQuestions()) {
							listQuestionJsonResponseForReport.add(new QuestionJsonResponseForReport(c, q));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(e).build();
		}

		return Response.status(HttpURLConnection.HTTP_OK).entity(listQuestionJsonResponseForReport).build();
	}

}
