package org.house.predict.repository;

import org.house.predict.config.DBHelper;
import org.house.predict.model.AreaSquareFeetModel;

public class AreaSquareFeetRepository extends DBHelper {
	
/**********************************************************************************/		
	public boolean isAddSquareFeet(AreaSquareFeetModel model) {
		try {
			this.stmt = conn.prepareStatement("insert into areasquarefeet values('0',?)");
			stmt.setInt(1,model.getSquareFeet());
			int value = stmt.executeUpdate();
			return value > 0 ? true : false;
		}
		catch(Exception ex) {
			System.out.println("IsAddAreaSqureFeet error is "+ex);
		}
		return false;
	}
/**********************************************************************************/
	public int getSquareFeetId(int areasqfeet) {
		try {
			stmt = conn.prepareStatement("select sqid from areasquarefeet ");
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			else {
				return 0;
			}			
		}
		catch(Exception ex){
			System.out.println("Error is "+ex);
			return -1;			
		}
	}
/**********************************************************************************/	
	
	
	
	
	
	
	
	
	
}
