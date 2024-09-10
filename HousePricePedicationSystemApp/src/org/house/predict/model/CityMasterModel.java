package org.house.predict.model;

public class CityMasterModel {
/*************************************************************/
	private int cityId;
	private String cityName;
/*************************************************************/
	public CityMasterModel() {

	}
	public CityMasterModel(int cityId, String name) {
		this.cityId = cityId;
		this.cityName = name;
	}
/***********************************************************/
	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
/***************************************************************/	
}
