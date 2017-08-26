/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.p.interview.mgmt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import com.p.interview.mgmt.pojo.CategoryDTO;



/**
 * 
 * @author PREMENDRA
 */
public class CategoryDAO extends AbstractDAO {

	public boolean keyExists(CategoryDTO objCategoryDTO) throws Exception {
		boolean keyExists = false;

		try {
			ResultSet rs = null;
			Connection con=getConnection();
			PreparedStatement ps = con
					.prepareStatement("select * from t_category where cat_id=?");

			ps.setInt(1, objCategoryDTO.getCatID());

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

//		closeConnection(con);
		return keyExists;
	}

	public void save(CategoryDTO objCategoryDTO) throws Exception {
		Connection con=getConnection();
		PreparedStatement ps = con
				.prepareStatement("insert into t_category values (?,?)");

		int j = 1;
		int nextWish_srno = generateNextsrno();
		objCategoryDTO.setCatID(nextWish_srno);
		ps.setInt(j++, objCategoryDTO.getCatID());
		ps.setString(j++, objCategoryDTO.getCatgoryName());
		ps.executeUpdate();
		System.out.println("save");
		closeConnection(con);
	}

	private int generateNextsrno() throws Exception {
		int nextWish_srno = 0;
		ResultSet rs = null;
		Connection con=getConnection();
		PreparedStatement ps = con
				.prepareStatement("select max(cat_id) from t_category");

		rs = ps.executeQuery();
		if (rs.next()) {
			nextWish_srno = rs.getInt(1) + 1;
		}
		closeConnection(con);
		return nextWish_srno;

	}

	public void update(CategoryDTO objCategoryDTO) throws Exception {
		Connection con=getConnection();
		PreparedStatement ps = con.prepareStatement("update t_category set "
				+ "cat_name=?  where cat_id=?");

		int j = 1;
		ps.setString(j++, objCategoryDTO.getCatgoryName());

		// where
		ps.setInt(j++, objCategoryDTO.getCatID());
		ps.executeUpdate();
		System.out.println("update");
		closeConnection(con);
	}

	public void retrieve(CategoryDTO objCategoryDTO) {
		try {
			ResultSet rs = null;
			Connection con=getConnection();
			PreparedStatement ps = con
					.prepareStatement("select * from t_category where cat_id=?");
			int j = 1;
			ps.setInt(j++, objCategoryDTO.getCatID());

			rs = ps.executeQuery();
			while (rs.next()) {
				// status = true;
				objCategoryDTO.setCatID(rs.getInt("cat_id"));
				objCategoryDTO.setCatgoryName(rs.getString("cat_name"));

				// System.out.println("wish_srno = " + rs.getInt("wish_srno")
				// + "\t  wish_stmt  = " + rs.getString("wish_stmt"));
			}
			closeConnection(con);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Vector<CategoryDTO> fetchAll() throws Exception {
		Vector<CategoryDTO> vecAllStudName = new Vector<CategoryDTO>();
		ResultSet rs = null;

		Connection con=getConnection();
		PreparedStatement ps = con
				.prepareStatement("select cat_id,cat_name from t_category order by cat_id");

		rs = ps.executeQuery();
		while (rs.next()) {
			// status = true;
			CategoryDTO objCategoryDTO = new CategoryDTO();
			objCategoryDTO.setCatID(rs.getInt("cat_id"));
			objCategoryDTO.setCatgoryName(rs.getString("cat_name"));
			vecAllStudName.add(objCategoryDTO);
		}
		closeConnection(con);
		return vecAllStudName;
	}

	public String deleteCategory(int catId) throws Exception {
		boolean isSuccess = false;
		String msg = "";
		PreparedStatement ps = null;
		
		Connection con=getConnection();

		msg = "";
		// con = DBUtil.getInstance().getConnection();
		String sql = "delete from t_category where cat_id=?";
		ps = con.prepareStatement(sql);
		int j = 1;
		ps.setInt(j++, catId);

		isSuccess = ps.executeUpdate() > 0 ? true : false;

		if (isSuccess) {
			msg = "category deleted from database ";
		} else {
			msg = "Unable to delete category from database ";
		}

		closeConnection(con);
		return msg;
	}
}
