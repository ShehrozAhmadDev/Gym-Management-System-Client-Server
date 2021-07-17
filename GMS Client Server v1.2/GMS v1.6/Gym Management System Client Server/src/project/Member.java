package project;
import java.sql.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Member implements Serializable {

    private int id;
    private String name;
    private int age;
    private String contact;

    // Constructor
    public Member(int id, String name, int age, String contact){
        this.id=id;
        this.name=name;
        this.age=age;
        this.contact=contact;
    }

    public Member(int id){
        this.id = id;
    }

    public Member(Member member){
        this.id= member.getId();
        this.name=member.getName();
        this.age=member.getAge();
        this.contact=member.getContact();
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String toString(){
        return this.id+"\t"+this.name+"\t"+this.age+"\t"+this.contact;
    }


    public Exception addMember() {
        try{
            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:orcl","shehroz","admin");

            String memberSql="INSERT into MEMBER(MEMBER_ID, MEMBER_NAME, AGE, CONTACT) values(?, ?, ?, ?)";
            PreparedStatement query=con.prepareStatement(memberSql);
            query.setInt(1, this.id);
            query.setString(2, this.name);
            query.setInt(3, this.age);
            query.setString(4, this.contact);

            int upd=query.executeUpdate();
            if (upd==1) {
                System.out.println("Member Inserted Successfully");
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

    // Method for viewing all members
    public static ArrayList<Member> viewAll() {
        ArrayList<Member> allMembers=new ArrayList<Member>();
        try {
            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:orcl","shehroz","admin");

            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("SELECT * from MEMBER order by MEMBER_ID");

            while(rs.next()) {
                Member result=new Member(rs.getInt("MEMBER_ID"),rs.getString("MEMBER_NAME"),rs.getInt("Age"),rs.getString("CONTACT"));
                allMembers.add(result);
            }
        }catch(Exception e){ System.out.println(e);}
        return allMembers;
    }

    // Method for searching Member using id
    public static Member searchMember(int id) {
        Member result=null;
        try {

            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:orcl","shehroz","admin");

            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("SELECT * from MEMBER where MEMBER_ID="+id);

            while(rs.next()) {
                result=new Member(rs.getInt("MEMBER_ID"),rs.getString("MEMBER_NAME"),rs.getInt("AGE"),rs.getString("CONTACT"));
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return result;
    }
}
