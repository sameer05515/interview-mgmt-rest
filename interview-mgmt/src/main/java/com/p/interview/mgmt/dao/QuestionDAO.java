/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.p.interview.mgmt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import com.p.interview.mgmt.exception.RestServiceException;
import com.p.interview.mgmt.pojo.QuestionDTO;

/**
 * 
 * @author PREMENDRA
 */
public class QuestionDAO extends AbstractDAO {

	public boolean keyExists(QuestionDTO objQuestionDTO) throws Exception {
		boolean keyExists = false;

		try {
			ResultSet rs = null;
			Connection con = getConnection();
			PreparedStatement ps = con
					.prepareStatement("select * from t_catg_ques where ques_id=? and linked_cat_id=? ");
			int j = 1;
			// where
			ps.setInt(j++, objQuestionDTO.getQuestionID());
			ps.setInt(j++, objQuestionDTO.getLinkedCatID());
			// ps.setString(2, txtStudName.getText());
			rs = ps.executeQuery();
			if (rs.next()) {
				keyExists = true;
			}
			closeConnection(con);
		} catch (Exception ex) {
			keyExists = false;
			ex.printStackTrace();
		}
		System.out.println("keyExists  " + keyExists);

		return keyExists;
	}

	public void saveDetails(QuestionDTO objQuestionDTO) throws Exception {
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement("insert into t_catg_ques(ques_id,linked_cat_id,ques) values (?,?,?)");
		// int id = Integer.parseInt(txtStudID.getText());
		int j = 1;
		int nextWish_srno = generateNextsrno(objQuestionDTO);
		objQuestionDTO.setQuestionID(nextWish_srno);
		ps.setInt(j++, objQuestionDTO.getQuestionID());
		ps.setInt(j++, objQuestionDTO.getLinkedCatID());
		ps.setString(j++, objQuestionDTO.getQuestion());
		ps.executeUpdate();
		System.out.println("save");
		closeConnection(con);
	}

	private int generateNextsrno(QuestionDTO objQuestionDTO) throws Exception {
		int nextWish_srno = 0;
		ResultSet rs = null;
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement("select max(ques_id) from t_catg_ques where linked_cat_id=? ");

		int j = 1;
		ps.setInt(j++, objQuestionDTO.getLinkedCatID());
		rs = ps.executeQuery();
		if (rs.next()) {
			nextWish_srno = rs.getInt(1) + 1;
		}
		closeConnection(con);
		return nextWish_srno;

	}

	public void updateDetails(QuestionDTO objQuestionDTO) throws Exception {
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(
				"update t_catg_ques set " + "ques=? ,linked_cat_id=? where ques_id=? and linked_cat_id=? ");
		// int id = Integer.parseInt(txtStudID.getText());
		int j = 1;
		ps.setString(j++, objQuestionDTO.getQuestion());
		ps.setInt(j++, objQuestionDTO.getLinkedCatID());
		// where
		ps.setInt(j++, objQuestionDTO.getQuestionID());
		ps.setInt(j++, objQuestionDTO.getLinkedCatID());
		ps.executeUpdate();
		System.out.println("update");
		closeConnection(con);
	}

	public QuestionDTO retrieve(QuestionDTO objQuestionDTO) throws Exception {
		try {
			ResultSet rs = null;
			Connection con = getConnection();
			PreparedStatement ps = con
					.prepareStatement("select * from t_catg_ques where ques_id=? and linked_cat_id=?");
			int j = 1;
			ps.setInt(j++, objQuestionDTO.getQuestionID());
			ps.setInt(j++, objQuestionDTO.getLinkedCatID());

			rs = ps.executeQuery();
			if (rs.next()) {
				// status = true;
				objQuestionDTO.setQuestionID(rs.getInt("ques_id"));
				objQuestionDTO.setLinkedCatID(rs.getInt("linked_cat_id"));
				objQuestionDTO.setQuestion(rs.getString("ques"));

				// System.out.println("wish_srno = " + rs.getInt("wish_srno")
				// + "\t wish_stmt = " + rs.getString("wish_stmt"));
			} else {
				throw new RestServiceException("404", "no question found for category id == "
						+ objQuestionDTO.getLinkedCatID() + " and question id == " + objQuestionDTO.getQuestionID());
			}
			closeConnection(con);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return objQuestionDTO;
	}

	public Vector<QuestionDTO> fetchAllByCategory(int linkedCategId) throws Exception {
		Vector<QuestionDTO> vecAllStudName = new Vector<QuestionDTO>();
		ResultSet rs = null;

		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(
				"select ques_id,linked_cat_id,ques from t_catg_ques where linked_cat_id=? order by ques_id");

		int j = 1;
		ps.setInt(j++, linkedCategId);
		rs = ps.executeQuery();
		while (rs.next()) {
			// status = true;
			QuestionDTO objQuestionDTO = new QuestionDTO();
			objQuestionDTO.setQuestionID(rs.getInt("ques_id"));
			objQuestionDTO.setLinkedCatID(rs.getInt("linked_cat_id"));
			objQuestionDTO.setQuestion(rs.getString("ques"));
			// String value = rs.getInt("wish_srno") + ":"
			// + rs.getString("wish_stmt");
			vecAllStudName.add(objQuestionDTO);

		}
		closeConnection(con);
		return vecAllStudName;
	}

	public String deleteQuestion(QuestionDTO objQuestionDTO) throws Exception {
		boolean isSuccess = false;
		String msg = "";
		PreparedStatement ps = null;

		Connection con = getConnection();

		msg = "";
		// con = DBUtil.getInstance().getConnection();
		String sql = "delete from t_catg_ques where ques_id=? and linked_cat_id=?";
		ps = con.prepareStatement(sql);
		int j = 1;
		ps.setInt(j++, objQuestionDTO.getQuestionID());
		ps.setInt(j++, objQuestionDTO.getLinkedCatID());

		isSuccess = ps.executeUpdate() > 0 ? true : false;

		if (isSuccess) {
			msg = "Question deleted from database ";
		} else {
			msg = "Unable to delete Question from database ";
		}

		closeConnection(con);

		return msg;
	}
}
