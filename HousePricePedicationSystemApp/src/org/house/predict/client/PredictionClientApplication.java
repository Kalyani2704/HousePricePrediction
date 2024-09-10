package org.house.predict.client;

/***********************************************************************************************/
import static java.lang.System.*;
import java.util.*;

import org.house.predict.model.AmenityModel;
import org.house.predict.model.AreaMasterModel;
import org.house.predict.model.AreaSquareFeetModel;
import org.house.predict.model.CityMasterModel;
import org.house.predict.model.DealModel;
import org.house.predict.model.PropertyModel;
import org.house.predict.service.AmenityService;
import org.house.predict.service.AreaSquareFeetService;
import org.house.predict.service.CityMasterService;
import org.house.predict.service.PropertyService;

/***********************************************************************************************/
public class PredictionClientApplication {

	public static void main(String[] args) {
		
		CityMasterService cms = new CityMasterService();
		AreaSquareFeetService areasq = new AreaSquareFeetService();
		AreaMasterModel model1 = new AreaMasterModel();
		AmenityService amService = new AmenityService();
		//PropertyService propService = new PropertyService();
		PropertyService propService = new PropertyService();
		//PropertyModel propModel = new PropertyModel();
		CityMasterModel model = new CityMasterModel();
		
		Scanner sc = new Scanner(System.in);
		int choice;
		do {			
			System.out.println("1:Add New City");
			System.out.println("2:View All City");
			System.out.println("3:Add New Bulk Cities");
			System.out.println("4:Add New Area");
			System.out.println("5:City Wise Area Counting");
			System.out.println("6:City Wise Area Name");
			System.out.println("7:Add Square Feet of Area");
			System.out.println("8:Add New Aminities");
			System.out.println("9:Add new properties ");
//			System.out.println("10: Find area wise property count");
			System.out.println("________________________________");
			System.out.println("---Enter your choice---");
			choice = sc.nextInt();
			System.out.println("________________________________");
			switch (choice) {
/******************************************************************************************************/
			case 1:

				System.out.println("Enter city name");
				sc.nextLine();
				String cityName = sc.nextLine();
				
				//CityMasterModel model = new CityMasterModel();
				model.setCityName(cityName);
				boolean b = cms.isAddCity(model);
				if (b) {
					System.out.println("New city added in Database table");
				} else {
					System.out.println("City not added");
				}
				break;
/******************************************************************************************************/
			case 2:

				List<CityMasterModel> list = cms.getAllCities();
				if (list != null) {
					list.forEach((m) -> System.out.println(m.getCityId() + "\t" + m.getCityName()));

				} else {
					System.err.println("There is no city present");
				}
				break;
/*******************************************************************************************************/
			case 3:
				b = cms.isBulkAddCities();
				if (b) {
					System.out.println("City Added Successfully....");
				} else {
					System.out.println("Some problem is there");
				}
				break;
/*******************************************************************************************************/
			case 4:
				sc.nextLine();
				System.out.println("Enter city name");
				cityName = sc.nextLine();
				int cityId = cms.getCityId(cityName);
				if (cityId != -1) {
					System.out.println("Enter area name");
					String areaName = sc.nextLine();
					int areaid = cms.getAreaIdAutomatic();
					
					//AreaMasterModel model1 = new AreaMasterModel();
					model1.setCityId(cityId);
					model1.setAreaName(areaName);
					model1.setAreaId(areaid);
					b = cms.isAddArea(model1);
					if(b) {
						System.out.println("Area added successfully.....");
					}
					else {
						System.out.println("Area not added......");
					}
				} else {
					System.out.println("City not present in Database");
					System.out.println("Do you want to Add this city in Database");
					String msg = sc.next();
					if (msg.equals("yes")) {
						cityName = sc.nextLine();
						model = new CityMasterModel();
						model.setCityName(cityName);
						b = cms.isAddCity(model);
						if (b) {
							System.out.println("New city added in Database table");
						} else {
							System.out.println("City not added");
						}

					} else {
						System.out.println("Thank you....");
					}
				}
				break;
				
/*******************************************************************************************************/
			case 5:
				LinkedHashMap<String,Integer> map = cms.getCityWiseCount();
				Set<Map.Entry<String, Integer>> s = map.entrySet();
				for(Map.Entry<String,Integer> m:s) {
					System.out.println(m.getKey()+"\t"+ m.getValue());
				}
				break;
/*******************************************************************************************************/
			case 6:
				 LinkedHashMap<String,ArrayList<String>> areaNames = cms.getCityWiseAreaNames();
				 Set<Map.Entry<String,ArrayList<String>>> set = areaNames.entrySet();
				 for(Map.Entry<String,ArrayList<String>> m:set) {
					 ArrayList <String>values = m.getValue(); //area name
					 if(values.size()>0) {
						 System.out.println("City Name --> "+m.getKey()); // City Name...
				     	 System.out.println("------------------------");
				     	 System.out.println("Area Name:-");
					 for(String areaName:values) {
						 System.out.println("\t"+areaName);
					    }
					 System.out.println("\n"); 
					 }					
				 }
				 break;
/*****************************************************************************************************/				 
			case 7:
				System.out.println("Enter value in square feet");
				int sqFeet = sc.nextInt();
				AreaSquareFeetModel areaFeetModel = new AreaSquareFeetModel();
				areaFeetModel.setSquareFeet(sqFeet);
				
				//AreaSquareFeetService areasq = new AreaSquareFeetService();
				b = areasq.isAddSquaerFeet(areaFeetModel);
				if(b) {
					System.out.println("Square feet added in database table");
				}
				else {
					System.out.println("some problem occurs....");
				}
				break;
/*****************************************************************************************************/				 
            case 8:
				sc.nextLine();
				System.out.println("Enter Amenity name");
				String name = sc.nextLine();
				AmenityModel amodel = new AmenityModel();
				amodel.setName(name);
				
				//AmenityService amService = new AmenityService();
				b= amService.isAddMinity(amodel);
				if(b) {
					System.out.println("New Amenitis Added in database");
				}
				else {
					System.out.println("No record added");
				}
				
				break;
/*****************************************************************************************************/		 
            case 9:
				sc.nextLine();
				System.out.println("Enter City Name");
				cityName = sc.next();// pune
				
				System.out.println("Enter Area Name");
				String areaName = sc.next();   //karvenar 
				
				System.out.println("Enter Address of the property");
				String propertyAddress = sc.next();   
				
				System.out.println("Land Area");
				int landArea = sc.nextInt();
				
				
				System.out.println("Enter the nbed and nbath rooms ");
				int nbed = sc.nextInt();
				
				int nbath = sc.nextInt();
				
				cityId = cms.getCityId(cityName); // pune   // data base  fech  table city id by = 1
				
				AreaMasterModel m = new AreaMasterModel();
				
				m.setCityName(cityName); // pune
				m.setAreaName(areaName);   // karVenagar
				
				int areaId = cms.getAreaId(m);
				
				System.out.println("===================>"+areaId);	
				
				
//				sqFeet = sc.nextInt();	
				
				int sqid = areasq.getSquareFeetId(landArea);
				
				

				System.err.println("===================>"+sqid);	
				
				 if(sqid == -1) {
					 System.out.println("There is some error");
				 }
				 else if(sqid == 1) {
					 System.out.println("square area is not present.\n Do you want to add new area");
				 }
				 
				List<AmenityModel> amenityList = new ArrayList<AmenityModel>();
				String str="";
				do {
					sc.nextLine();
					
					
					System.out.println("Enter Amenity Name");
					String amname = sc.nextLine();
					AmenityModel amModel = new AmenityModel();
					int amId = new AmenityService().getAmenityId(amname);
					amModel.setName(amname);
					amModel.setId(amId);
					
					amenityList.add(amModel);
					System.out.println("Do You Want To Add More Amenities");
					str = sc.nextLine();					
				}while(str.equals("yes"));
				
				PropertyModel propModel = new PropertyModel();
				
				propModel.setAreaModel(m);
				propModel.setName(propertyAddress);
				propModel.setNbath(nbath);
				propModel.setNbed(nbed);
				areaFeetModel = new AreaSquareFeetModel();
				areaFeetModel.setSquareFeet(landArea);
				areaFeetModel.setId(sqid);
				propModel.setSqModel(areaFeetModel);
				propModel.setList(amenityList);
				
				System.out.println("Enter the price and date of registry");
				int price = sc.nextInt();
			/*--dont ask date --*/
//				sc.nextLine();
//				String rDate = sc.nextLine();
				DealModel dModel = new DealModel();
				dModel.setPrice(price);
				//dModel.setDate(rDate);
				propModel.setDealModel(dModel);
				
				b= propService.isAddNewProperty(propModel);	
				if(b) {
					System.out.println("New property added successfully........");
				}
				else {
					System.out.println("Not property added");
				}
				
			
				break;
/*****************************************************************************************************/	 
				
				
			default:
				System.err.println("Wrong Choice");
				System.err.println("------------");
			}

		} while (choice>0);
    sc.close();
	}

	
}
