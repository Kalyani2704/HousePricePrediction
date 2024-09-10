package org.house.predict.repository;

import java.util.*;

import org.house.predict.config.DBHelper;
import org.house.predict.model.AmenityModel;
import org.house.predict.model.DealModel;
import org.house.predict.model.PropertyModel;

public class PropertyRepository extends DBHelper {

/**********************************************************************************************/
	/*--auto increment--*/
	int pid;
	public int getPropIdAuto() {
		try {
			stmt = conn.prepareStatement("select max(pid) from propertymaster");
			rs = stmt.executeQuery();
			if(rs.next()) {
				pid =  rs.getInt(1);				
			}
		return ++pid;
		}
		catch(Exception ex) {
			System.out.println("Exception "+ex);
		}
		return 0;
	}
	
	
	
	
/**********************************************************************************************/
	public boolean isAddNewProperty(PropertyModel model) {
		
		pid = this.getPropIdAuto();
		String propertyName = model.getName();
		int sqid = model.getSqModel().getId();
		int areaId = model.getAreaModel().getAreaId();
		int cityId = model.getAreaModel().getCityId();
		int nbed = model.getNbed();
		int nbath = model.getNbath();
		
     /*---------get property master data------*/
		System.out.println("property master");
		System.out.println("Id\tName\tSquare feet\tArea id\t\tCity id\tNbed\tNbath");
		System.out.println((pid+1)+"\t"+propertyName+"\t"+sqid+"\t\t"+areaId+"\t\t"+cityId+"\t"+nbed+"\t"+nbath+"\t");
		
		try {
			stmt = conn.prepareStatement("insert into propertymaster values(?,?,?,?,?,?,?)");
			stmt.setInt(1, pid);
			stmt.setString(2,propertyName);
			stmt.setInt(3, sqid);
			stmt.setInt(4, areaId);
			stmt.setInt(5, cityId);
			stmt.setInt(6, nbed);
			stmt.setInt(7, nbath);
			
			int value = stmt.executeUpdate();
			if(value>0) {
				
				/*------------get amenities data----------*/
				List<AmenityModel> list = model.getList();
				System.out.println("Amenities");
				int count=0;
				for(AmenityModel m:list) {
					stmt = conn.prepareStatement("insert into propertyamenityjoin values(?,?)");
					stmt.setInt(1, pid);
					stmt.setInt(2, m.getId());
					value = stmt.executeUpdate();
				}	
				 /*--------get property and price relation------*/
				
				DealModel dealModel = model.getDealModel();
				Date d = dealModel.getDate();
				int price = dealModel.getPrice();
				stmt = conn.prepareStatement("insert into propertyhistoricalprices values('0',?,?,(select curDate())");
				stmt.setInt(1,pid);
				stmt.setInt(2, price);
//				stmt.setDate(3, new java.sql.Date(5));
				
				value = stmt.executeUpdate();
				if(value>0) {
					System.out.println("Property added success...");
					return true;
					}
				else {
					System.out.println("Property not added");
					return false;
				}
				
			}
			else {
				System.out.println("Exception is there...");
				return false;
			}
		}
		catch(Exception ex) {
			System.out.println("Exception "+ex);
			return false;
		}   
//		System.out.println("property and price relationship");
//		DealModel dealModel = model.getDealModel();
//		System.out.println((dealModel.getTransId()+1)+"\t"+dealModel.getPrice()+"\t"+dealModel.getDate());
//		
		
	}
/**********************************************************************************************/
	
	
	
}
