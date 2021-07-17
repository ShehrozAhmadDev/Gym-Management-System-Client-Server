package project;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

public class Usage implements Serializable {

    private Equipment equipment;
    private Member member;

    public Usage(Equipment equipment, Member member){
       this.equipment=new Equipment(equipment);
       this.member=new Member(member);

    }

    // Constructor
    public Usage(int equipmentId, int memberId){
        equipment = new Equipment(equipmentId);
        member = new Member(memberId);
    }

    // Getters and Setters
    public Equipment getEquipment() {
        return new Equipment(equipment);
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = new Equipment(equipment);
    }

    public Member getMember() {
        return new Member(member);
    }

    public void setMemberId(Member member) {
        this.member = new Member(member);
    }

    public String toString(){
        return member.toString()+"\t "+ equipment.toString();
    }

    // add getDetails function to Member and Equipment to get their data from db using ID
    public Exception addUsage() {
        try{
            Connection con= DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:orcl","shehroz","admin");

            String usageSql="INSERT into USAGE(EQUIPMENT_ID, MEMBER_ID) values(?, ?)";
            PreparedStatement query=con.prepareStatement(usageSql);
            query.setInt(1, this.equipment.getId());
            query.setInt(2, this.member.getId());

            int upd=query.executeUpdate();
            if (upd==1) {
                System.out.println("Equipment Usage Inserted Successfully");
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

    public static Usage searchUsage(int equipmentId, int memberId) {
        Usage searched = null;
        try{
            Connection con= DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:orcl","shehroz","admin");

            String usageSql="SELECT * FROM EQUIPMENT natural join MEMBER natural join USAGE WHERE EQUIPMENT_ID="+equipmentId+" and MEMBER_ID="+memberId;
            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery(usageSql);
            while(rs.next()){

                Equipment equipmentData=new Equipment(rs.getInt("EQUIPMENT_ID"),rs.getString("EQUIPMENT_NAME"),rs.getString("MANUFACTURER"),rs.getInt("COST"));
                Member memberData=new Member(rs.getInt("MEMBER_ID"),rs.getString("MEMBER_NAME"),rs.getInt("AGE"),rs.getString("CONTACT"));
                searched = new Usage(equipmentData,memberData);
            }
            con.close();
        }
        catch(Exception e1){
            System.out.println(e1);
            return null;
        }
        return searched;
    }

    public static ArrayList<Usage> viewAllUsage() {
        ArrayList<Usage> allUsageData=new ArrayList<Usage>();
        try {
            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:orcl","shehroz","admin");

            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("SELECT * from EQUIPMENT natural join MEMBER natural join USAGE");

            while(rs.next()) {
                Equipment equipmentData=new Equipment(rs.getInt("EQUIPMENT_ID"),rs.getString("EQUIPMENT_NAME"),rs.getString("MANUFACTURER"),rs.getInt("COST"));
                Member memberData=new Member(rs.getInt("MEMBER_ID"),rs.getString("MEMBER_NAME"),rs.getInt("AGE"),rs.getString("CONTACT"));
                Usage result=new Usage(equipmentData, memberData);
                allUsageData.add(result);
            }
        }catch(Exception e){ System.out.println(e);}
        return allUsageData;
    }


}

