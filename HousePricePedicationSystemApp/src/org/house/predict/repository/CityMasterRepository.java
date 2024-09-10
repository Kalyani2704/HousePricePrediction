package org.house.predict.repository;

/******************************************************************************************************/
import java.io.*;
import java.sql.*;
import java.util.*;
import org.house.predict.config.DBHelper;
import org.house.predict.config.PathHelper;
import org.house.predict.model.AreaMasterModel;
import org.house.predict.model.CityMasterModel;

/******************************************************************************************************/
public class CityMasterRepository extends DBHelper {
	private List<CityMasterModel> list = new ArrayList<CityMasterModel>();
	private int areaid=0;
    private List<Object[]> cityWiseAreaCountList;
    private LinkedHashMap<String,Integer> map;
    LinkedHashMap<String,ArrayList<String>> cityWiseNameAreaMap;
    
    
/******************************************************************************************************/
	public boolean isAddNewCity(CityMasterModel model) {
		try {

			stmt = conn.prepareStatement("insert into citymaster values('0',?)");
			stmt.setString(1, model.getCityName());
			int value = stmt.executeUpdate();
			return value > 0 ? true : false;

		} catch (Exception ex) {
			System.out.println("Error is" + ex);
			return false;
		}
	}

/******************************************************************************************************/
	public List<CityMasterModel> getAllCities() {
		try {
			stmt = conn.prepareStatement("select * from citymaster");
			rs = stmt.executeQuery();
			while (rs.next()) {
				CityMasterModel model = new CityMasterModel();
				model.setCityId(rs.getInt(1));
				model.setCityName(rs.getString(2));
				list.add(model);
			}
			return list.size() > 0 ? list : null;
		} catch (Exception ex) {
			System.out.println("Error is" + ex);
			return null;
		}
	}

/********************************************************************************************************/
	public boolean isBulkAddCities() {
		try {
			FileReader fr = new FileReader(PathHelper.path + "city.csv");
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			int value = 0;
			while ((line = br.readLine()) != null) {
				String data[] = line.split(",");
				stmt = conn.prepareStatement("insert into citymaster values('0',?)");
				stmt.setString(1, data[1]);
				value = stmt.executeUpdate();
			}
			return value > 0 ? true : false;
		} catch (Exception ex) {
			System.out.println("Error is" + ex);
			return false;
		}
	}

/********************************************************************************************************/
	public int getCity(String name) {
		try {
			stmt = conn.prepareStatement("select cityid from citymaster where cityname=?");
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			} 
			
			else
			
			{
				return -1;
			}
			
		} 
		
		catch (Exception ex) 
		
		{
			System.out.println("Error is" + ex);
			return -1;
		}
	}
	
/********************************************************************************************************/
         public int getAreaIdAutomatic() {
        	 try {
        		 stmt = conn.prepareStatement("select max(aid) from areamaster");
        		 rs = stmt.executeQuery();
        		 if(rs.next()) {
        			 this.areaid = rs.getInt(1);	
        		 }
        		++areaid;
        		
        		return areaid;
        	 }
        	 catch(Exception ex) {
        		 return 0;
        	 }
         }
         
/********************************************************************************************************/
         public boolean isAddArea(AreaMasterModel model1) {
        	 try {
         /*---------------------------With procedure---------------------------------------*/
        		CallableStatement cstmt = conn.prepareCall("{call savearea(?,?,?)}");      
        		 cstmt.setInt(1,model1.getAreaId());
        		 cstmt.setString(2,model1.getAreaName());
        		 cstmt.setInt(3,model1.getCityId());
        		 boolean b = cstmt.execute();
        		 return !b;
        /*--------------------------Without procedure-------------------------------------*/		 
        		/* stmt = conn.prepareStatement("insert into areamaster values(?,?)");
        		 stmt.setInt(1,model1.getAreaId());
        		 stmt.setString(2,model1.getAreaName());
        		int value= stmt.executeUpdate();
        		if(value>0) {
        			stmt = conn.prepareStatement("insert into cityareajoin values(?,?)");
        		}*/
        	 }
        	 catch(Exception ex) {
        		 System.out.println("Error is"+ex);
        		 return false;
        	 }
         }
 
/********************************************************************************************************/
         
	/*------------------------Using List------------------------------------*/
         
        /* public List<Object[]> getCityWiseCount(){     
         try {
        	 this.cityWiseAreaCountList = new ArrayList<Object[]>();
        	 stmt = conn.prepareStatement("select cm.cityname,count(crj.cityid) from citymaster cm inner join cityareajoin crj on cm.cityid=crj.cityid inner join areamaster am on am.aid=crj.aid group by cm.cityname");
        	 rs = stmt.executeQuery();
        	 while(rs.next()) {
        		 Object obj[] = new Object[] {rs.getString(1),rs.getInt(1)};
        		 this.cityWiseAreaCountList.add(obj);       		 
        	 }
        	 return this.cityWiseAreaCountList;
         }
	     catch(Exception ex) {
	    	 System.out.println("Error is"+ex);
	    	 return null;
	     } 
	  }*/
         
     /*--------------------------Using Linked HashMap--------------------------*/
         public LinkedHashMap<String,Integer> getCityWiseCount(){     
         try {
        	 map = new LinkedHashMap<String, Integer>();
        	 this.cityWiseAreaCountList = new ArrayList<Object[]>();
        	 stmt = conn.prepareStatement("select cm.cityname,count(crj.cityid) from citymaster cm inner join cityareajoin crj on cm.cityid=crj.cityid inner join areamaster am on am.aid=crj.aid group by cm.cityname");
        	 rs = stmt.executeQuery();
        	 while(rs.next()) {
        		map.put(rs.getString(1),rs.getInt(2));  // key&value
        	 }
        	 return map;
         }
	     catch(Exception ex) {
	    	 System.out.println("Error is"+ex);
	    	 return null;
	     } 
	  }
         
         
/********************************************************************************************************/
         public LinkedHashMap<String,ArrayList<String>> getCityWiseAreaNames(){
        	 
        	 try {
        		 this.cityWiseNameAreaMap = new LinkedHashMap<String,ArrayList<String>>();
        		 stmt=conn.prepareStatement(" select cityname from citymaster");
        		 rs=stmt.executeQuery();
        		 while(rs.next()) {
        			 String cityName = rs.getString(1);
        			 PreparedStatement stmt1 = conn.prepareStatement("select am.areaname from areamaster am inner join"
        			 		+ " cityareajoin caj on am.aid=caj.aid inner join citymaster cm on caj.cityid=cm.cityid where cm.cityname=?");
        			 stmt1.setString(1,cityName);
        			 ResultSet rs1 = stmt1.executeQuery();
        			 ArrayList <String>areaName = new ArrayList();
        			 while(rs1.next()) {
        				 areaName.add(rs1.getString(1));
        			 }
        			 this.cityWiseNameAreaMap.put(cityName,areaName);
        		 }
        		 return this.cityWiseNameAreaMap;
        	 }
        	 catch(Exception ex) {
        		 System.out.println("error is"+ex);
        		 return null;
        	 }
         }
         
/********************************************************************************************************/        
      public int getAreaIdByName(AreaMasterModel model) {
    	  try {
    		  stmt = conn.prepareStatement("select am.aid from areamaster am inner join cityareajoin caj on am.aid=caj.aid inner join "
    		  		+ "citymaster cm on cm.cityid=caj.cityid where am.areaname=? and cm.cityname=?");
    		 
    		  
    		  stmt.setString(1,model.getAreaName());  // karvenagr
    		  
    		  
    		  
    		  stmt.setString(2,model.getCityName());// pune
    		  
    		  
    		  rs = stmt.executeQuery();
    		  
    		  if(rs.next())
    		  {
    			 return rs.getInt(1); 
    			 
    		  }
    		  else {
    			  return 0;
    		  }
    	  }
    	  catch(Exception ex) {
    		  System.out.println("error is"+ex);
    		  return -1;
    	  }
      }
         
/***********************************************************************************************************/         
         
         
         
         
         
         
         
         
         
         
}






















