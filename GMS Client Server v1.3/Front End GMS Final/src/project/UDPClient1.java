package project;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.Scanner;

class UDPClient1  {

    public static byte[] sendData = new byte[1024];
    public static byte[] receiveData = new byte[1024];
    public static int option;
    public static String modifiedSentence;
    public static DatagramPacket sendPacket;
    public static DatagramPacket receivePacket;
    public static EquipmentFE equipmentFE;
    public static MembersFE membersFE;
    public static UsageFE usageFE;
    public static DatagramSocket clientSocket;
    public static InetAddress IPAddress;
    public static int Port;


    public UDPClient1() throws Exception{
        clientSocket = new DatagramSocket();
        IPAddress = InetAddress.getByName("localhost");
        Port = 232;

    }

    public static void main(String args[]) throws Exception
    {
        MainMenu m=new MainMenu();
        m.setVisible(true);
    }


    public static void addEquipment(int id, String name, String manufacturer, int cost) throws Exception{

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            DataOutputStream dataOut = new DataOutputStream(outputStream);

            dataOut.writeInt(1);

            sendData = outputStream.toByteArray();
            sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);
            clientSocket.send(sendPacket);

            Equipment sampleEquipment = new Equipment(id, name,manufacturer,cost);
            ObjectOutputStream os = new ObjectOutputStream(outputStream);
            os.writeObject(sampleEquipment);

            sendData = outputStream.toByteArray();
            sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);
            clientSocket.send(sendPacket);
//            System.out.println("Message sent from client");

            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            modifiedSentence = new String(receiveData,0,receivePacket.getLength());
//            System.out.print("From Server: "+modifiedSentence);
            JOptionPane.showMessageDialog(null,modifiedSentence);
    }

    public static void viewEquipment() throws Exception{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DataOutputStream dataOut = new DataOutputStream(outputStream);
        dataOut.writeInt(2);
        
        sendData = outputStream.toByteArray();
        sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);
        clientSocket.send(sendPacket);

        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        ByteArrayInputStream in = new ByteArrayInputStream(receiveData); //put after receiving data
        ObjectInputStream is = new ObjectInputStream(in);
        ArrayList<Equipment> equipments = (ArrayList<Equipment>) is.readObject();
        equipmentFE=new EquipmentFE(equipments);
        equipmentFE.setVisible(true);
    }

    public static void searchEquipment(int id) throws Exception{

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DataOutputStream dataOut = new DataOutputStream(outputStream);

        dataOut.writeInt(3);
        sendData = outputStream.toByteArray();
        sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);
        clientSocket.send(sendPacket);

        dataOut.writeInt(id);
        sendData = outputStream.toByteArray();
        sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);
        clientSocket.send(sendPacket);

        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        ByteArrayInputStream in = new ByteArrayInputStream(receiveData); //put after receiving data
        ObjectInputStream is = new ObjectInputStream(in);
        try{

            Equipment received = (Equipment) is.readObject();
            EquipmentDetails ed=new EquipmentDetails(received);
            ed.setVisible(true);
            equipmentFE.dispose();
//            System.out.println("ID\t Name\t Manufacturer\t Cost");
//            System.out.println(received.toString());
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null,"No Equipment Found");
            System.out.println("No Equipment of given ID");
        }
    }

    public static void viewMembers() throws Exception{

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DataOutputStream dataOut = new DataOutputStream(outputStream);
        dataOut.writeInt(5);

        sendData = outputStream.toByteArray();
        sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);
        clientSocket.send(sendPacket);

        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        ByteArrayInputStream in = new ByteArrayInputStream(receiveData); //put after receiving data
        ObjectInputStream is = new ObjectInputStream(in);
        ArrayList<Member> members = (ArrayList<Member>) is.readObject();
        membersFE=new MembersFE(members);
        membersFE.setVisible(true);
        }

        public static void addMember(int id, String name, int age, String contact) throws Exception{
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            DataOutputStream dataOut = new DataOutputStream(outputStream);
            dataOut.writeInt(4);

            sendData = outputStream.toByteArray();
            sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);
            clientSocket.send(sendPacket);

            Member member = new Member(id, name, age, contact);
            ObjectOutputStream os = new ObjectOutputStream(outputStream);
            os.writeObject(member);

            sendData = outputStream.toByteArray();
            sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);
            clientSocket.send(sendPacket);
            System.out.println("Message sent from client");

            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            modifiedSentence = new String(receiveData,0,receivePacket.getLength());
            System.out.print("From Server: "+modifiedSentence);
            JOptionPane.showMessageDialog(null,modifiedSentence);

        }

    public static void searchMember(int id) throws Exception{

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DataOutputStream dataOut = new DataOutputStream(outputStream);
        dataOut.writeInt(6);

        sendData = outputStream.toByteArray();
        sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);
        clientSocket.send(sendPacket);
        dataOut.flush();
        outputStream.flush();

        dataOut.writeInt(id);
        sendData = outputStream.toByteArray();
        sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);
        clientSocket.send(sendPacket);

        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        ByteArrayInputStream in = new ByteArrayInputStream(receiveData); //put after receiving data
        ObjectInputStream is = new ObjectInputStream(in);
        try{

            Member member = (Member) is.readObject();
            MemberDetails md=new MemberDetails(member);
            md.setVisible(true);
            membersFE.dispose();

        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null,"No Member Found");
        }

    }
	public static void viewUsage() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DataOutputStream dataOut = new DataOutputStream(outputStream);
        option = 8;
        dataOut.writeInt(option);

        sendData = outputStream.toByteArray();
        sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);
        clientSocket.send(sendPacket);

        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        ByteArrayInputStream in = new ByteArrayInputStream(receiveData); //put after receiving data
        ObjectInputStream is = new ObjectInputStream(in);
        ArrayList<Usage> usages = (ArrayList<Usage>) is.readObject();
        usageFE = new UsageFE(usages);
        usageFE.setVisible(true);
    }

    public static void addUsage(int equipmentId, int memberId) throws Exception{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DataOutputStream dataOut = new DataOutputStream(outputStream);

        dataOut.writeInt(7);

        sendData = outputStream.toByteArray();
        sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);
        clientSocket.send(sendPacket);

        Usage usage = new Usage(equipmentId,memberId);
        ObjectOutputStream os = new ObjectOutputStream(outputStream);
        os.writeObject(usage);

        sendData = outputStream.toByteArray();
        sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);
        clientSocket.send(sendPacket);
//        System.out.println("Message sent from client");
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        modifiedSentence = new String(receiveData,0,receivePacket.getLength());
//        System.out.print("From Server: "+modifiedSentence);
        JOptionPane.showMessageDialog(null,modifiedSentence);
    }

	public static void searchUsage(int Eqid, int id) throws Exception{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DataOutputStream dataOut = new DataOutputStream(outputStream);

        dataOut.writeInt(9);
        sendData = outputStream.toByteArray();
        sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);
        clientSocket.send(sendPacket);
        dataOut.flush();
        outputStream.flush();

        dataOut.writeInt(id);
        dataOut.writeInt(Eqid);
        sendData = outputStream.toByteArray();
        sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Port);
        clientSocket.send(sendPacket);

        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        ByteArrayInputStream in = new ByteArrayInputStream(receiveData); //put after receiving data
        ObjectInputStream is = new ObjectInputStream(in);
        try{
            Usage usage2 = (Usage) is.readObject();
            UsageDetails ud=new UsageDetails(usage2);
            ud.setVisible(true);
            usageFE.dispose();
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null,"No Usage Found");
        }
    }
}
