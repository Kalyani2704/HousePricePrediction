package org.house.predict.repository;

import org.house.predict.config.DBHelper;
import org.house.predict.model.AmenityModel;

public class AmenityRepository extends DBHelper{
	
/**********************************************************************************/
	public boolean isAddAmenity(AmenityModel model) {
		try {
			stmt = conn.prepareStatement("insert into aminitymaster values('0',?)");
			stmt.setString(1,model.getName());
			return stmt.executeUpdate() > 0 ? true : false;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return false;
		}		
	}
	
/**********************************************************************************/
	public int getAmenityId(String name) {
		try {
			stmt = conn.prepareStatement("select amid from aminitymaster where amenity=?");
			stmt.setString(1,name);
			rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			else {
				return 0;
			}
			
		} 
		catch(Exception ex) {
			System.out.println("Exceotion of"+ex);
		}
		return 0;
		
	}
}
