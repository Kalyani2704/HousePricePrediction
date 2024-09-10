package org.house.predict.service;

import org.house.predict.model.AmenityModel;
import org.house.predict.repository.AmenityRepository;


public class AmenityService {
	
	AmenityRepository amRepo = new AmenityRepository();
	
/*********************************************************************************/
	public boolean isAddMinity(AmenityModel model) {
		return amRepo.isAddAmenity(model);
	}
/*********************************************************************************/
	public int getAmenityId(String amenityName) {
		return amRepo.getAmenityId(amenityName);
	}

	
	
	
}
