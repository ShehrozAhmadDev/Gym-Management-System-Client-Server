package project;
import java.sql.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Equipment implements Serializable {

    private int id;
    private String name;
    private String manufacturer;
    private int cost;

    // Constructor
    public Equipment(int id, String name, String manufacturer, int cost){
        this.id=id;
        this.name=name;
        this.manufacturer=manufacturer;
        this.cost=cost;
    }

    public  Equipment(int id){
        this.id = id;
    }
    public Equipment(Equipment equipment){
        this.id= equipment.getId();
        this.name=equipment.getName();
        this.manufacturer=equipment.getManufacturer();
        this.cost=equipment.getCost();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    // Method for adding equipment

    public Exception addEquipment() {
		try{
            Connection con=DriverManager.getConnection(
            "jdbc:oracle:thin:@localhost:1521:orcl","shehroz","admin");
            
            String equipmentSql="INSERT into EQUIPMENT(EQUIPMENT_ID, EQUIPMENT_NAME, MANUFACTURER, COST) values(?, ?, ?, ?)";
            PreparedStatement query=con.prepareStatement(equipmentSql);
            query.setInt(1, this.id);
            query.setString(2, this.name);
            query.setString(3, this.manufacturer);
            query.setInt(4, this.cost);
	      
            int upd=query.executeUpdate();
            if (upd==1) {
            	System.out.println("Equipment Inserted Successfully");
            }
            else {
            	System.out.println("Error Occurred");
            }      
            con.close();   
        }
	 	catch(Exception e1){ 
	 		System.out.println(e1);
	 		return e1;
	 	}
        return null;
    }

	// Method for viewing all equipment	
	public static ArrayList<Equipment> viewAll() {
		ArrayList<Equipment> allEquipment=new ArrayList<Equipment>();
        try {
			Connection con=DriverManager.getConnection(
		            "jdbc:oracle:thin:@localhost:1521:orcl","shehroz","admin");
			 
			Statement stmt=con.createStatement();
	   
	        ResultSet rs=stmt.executeQuery("SELECT * from EQUIPMENT order by EQUIPMENT_ID");
	          
	        while(rs.next()) {
               Equipment result=new Equipment(rs.getInt("EQUIPMENT_ID"),rs.getString("EQUIPMENT_NAME"),rs.getString("MANUFACTURER"),rs.getInt("COST"));
               allEquipment.add(result);
            }
		}catch(Exception e){ System.out.println(e);}
        return allEquipment;
	}

	// Method for searching equipment using id
	public static Equipment searchEquipment(int id) {
		Equipment result=null;
		try {
			
			Connection con=DriverManager.getConnection(
		            "jdbc:oracle:thin:@localhost:1521:orcl","shehroz","admin");
			 
			Statement stmt=con.createStatement();
	   
	        ResultSet rs=stmt.executeQuery("SELECT * from EQUIPMENT where EQUIPMENT_ID="+id);

	        while(rs.next()) {
	        	result=new Equipment(rs.getInt("EQUIPMENT_ID"),rs.getString("EQUIPMENT_NAME"),rs.getString("MANUFACTURER"),rs.getInt("COST"));     	
	        }
		}catch(Exception e){ 
			System.out.println(e);
			}
		return result;
	}

    public String toString(){
        return this.getId()+"\t"+this.getName()+"\t"+this.getManufacturer()+"\t"+this.getCost();
    }
}
