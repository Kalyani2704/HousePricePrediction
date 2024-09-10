package org.house.predict.service;

import org.house.predict.model.AreaSquareFeetModel;
import org.house.predict.repository.AreaSquareFeetRepository;

public class AreaSquareFeetService {
	
	  AreaSquareFeetRepository areasq = new AreaSquareFeetRepository();
	
/********************************************************************************/	
	public boolean isAddSquaerFeet(AreaSquareFeetModel model) {
		return areasq.isAddSquareFeet(model);
	}
	
/********************************************************************************/
	public int getSquareFeetId(int sqFeet) {
		return areasq.getSquareFeetId(sqFeet);
	}
	
/********************************************************************************/	
	
	
	
}
