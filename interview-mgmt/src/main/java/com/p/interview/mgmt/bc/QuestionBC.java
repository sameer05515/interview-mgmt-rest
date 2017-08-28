/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.p.interview.mgmt.bc;

import java.util.Vector;

import com.p.interview.mgmt.dao.QuestionDAO;
import com.p.interview.mgmt.pojo.AnswerDTO;
import com.p.interview.mgmt.pojo.QuestionDTO;

/**
 * 
 * @author PREMENDRA
 */
public class QuestionBC extends AbstractBC {
	QuestionDAO objQuestionDAO;

	AnswerBC objAnswerBC = new AnswerBC();

	public QuestionBC() {
		objQuestionDAO = new QuestionDAO();
	}

	public boolean keyExists(QuestionDTO objQuestionDTO) throws Exception {
		return objQuestionDAO.keyExists(objQuestionDTO);
	}

	public void save(QuestionDTO objQuestionDTO) throws Exception {
		objQuestionDAO.saveDetails(objQuestionDTO);
	}

	public void update(QuestionDTO objQuestionDTO) throws Exception {
		objQuestionDAO.updateDetails(objQuestionDTO);
	}

	public QuestionDTO retrieve(QuestionDTO objQuestionDTO) throws Exception {

		QuestionDTO questionDTO = objQuestionDAO.retrieve(objQuestionDTO);

		Vector<AnswerDTO> objAnswerDTOs = objAnswerBC.fetchAllByQuestion(questionDTO);
		questionDTO.setAnswers(objAnswerDTOs);

		return questionDTO;
	}

	public Vector<QuestionDTO> fetchAllByCategory(int linkedCategId) throws Exception {
		Vector<QuestionDTO> objQuestionDTOs = objQuestionDAO.fetchAllByCategory(linkedCategId);

		for (QuestionDTO questionDTO : objQuestionDTOs) {
			Vector<AnswerDTO> objAnswerDTOs = objAnswerBC.fetchAllByQuestion(questionDTO);
			questionDTO.setAnswers(objAnswerDTOs);
		}
		return objQuestionDTOs;
	}

	public String deleteQuestion(QuestionDTO objQuestionDTO) throws Exception {
		String msg = "";
		AnswerBC objAnswerBC = new AnswerBC();
		Vector<AnswerDTO> objAnswerDTOs = objAnswerBC.fetchAllByQuestion(objQuestionDTO);
		for (AnswerDTO objAnswerDTO : objAnswerDTOs) {
			msg += "\n" + objAnswerBC.deleteAnswer(objAnswerDTO);
		}
		msg += objQuestionDAO.deleteQuestion(objQuestionDTO);
		return msg;
	}
}
