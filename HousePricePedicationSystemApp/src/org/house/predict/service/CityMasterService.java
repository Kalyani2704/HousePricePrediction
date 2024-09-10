package org.house.predict.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
/*****************************************************************************************************/
import java.util.List;
import org.house.predict.model.AreaMasterModel;
import org.house.predict.model.CityMasterModel;
import org.house.predict.repository.CityMasterRepository;

/*****************************************************************************************************/
    public class CityMasterService {
	     CityMasterRepository cityrepo= new CityMasterRepository();
	
/*****************************************************************************************************/	
	public boolean isAddCity(CityMasterModel model) {
		boolean b=cityrepo.isAddNewCity(model);
		return b;
	}
	
/****************************************************************************************************/	
	public List<CityMasterModel> getAllCities(){
         //List<CityMasterModel> list = cityrepo.getAllCities();
         //return list;
		return cityrepo.getAllCities();
	}
	
/****************************************************************************************************/	
	public boolean isBulkAddCities() {
		
		return cityrepo.isBulkAddCities();
	}
	
/****************************************************************************************************/
	public int getCityId(String cityName) {
		
		return cityrepo.getCity(cityName);
	}
	
/***************************************************************************************************/
	public int getAreaIdAutomatic() {
		return cityrepo.getAreaIdAutomatic();
	}
	
/***************************************************************************************************/
	public boolean isAddArea(AreaMasterModel model) {
		return cityrepo.isAddArea(model);	
	}
	
/***************************************************************************************************/
	 public LinkedHashMap<String,Integer> getCityWiseCount(){
		 return this.cityrepo.getCityWiseCount();
	 }

/***************************************************************************************************/
	 public LinkedHashMap<String,ArrayList<String>> getCityWiseAreaNames(){ 
		 return this.cityrepo.getCityWiseAreaNames();
	 }
	 
/***************************************************************************************************/
	 public int getAreaId(AreaMasterModel model) {
		 return cityrepo.getAreaIdByName(model);
	 }
	
	
}
