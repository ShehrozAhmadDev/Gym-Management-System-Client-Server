package project;
import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.ArrayList;

class UDPServer {
    public static void main(String args[]) throws Exception
    {
        DatagramSocket serverSocket = new DatagramSocket(132);
        String[] names= {"Saleh", "Shehroz"};
        byte[] receiveData = new byte[1024];
        byte[] sendData  = new byte[1024];
        System.out.println("SERVER HAS STARTED: ");

        while(true)
        {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            String sentence= new String(receiveData,0,receivePacket.getLength());
//            System.out.println(sentence);
            String newSentence;

            if(login(sentence, names)){
                newSentence = "Connection Successfull";
                sendData = newSentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,port);
                serverSocket.send(sendPacket);
                System.out.println("Login Confirmation Sent");

                do{
                    //receive chosen option
                    receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    serverSocket.receive(receivePacket);
                    receiveData = receivePacket.getData();
                    sentence= new String(receiveData,0,receivePacket.getLength());
//                    System.out.println(sentence);

                    switch(sentence){
                        case "1":
                            //receive equipment data
                            receivePacket = new DatagramPacket(receiveData, receiveData.length);
                            serverSocket.receive(receivePacket);
                            String name = new String(receiveData,0,receivePacket.getLength());
                            System.out.println(name);

                            receivePacket = new DatagramPacket(receiveData, receiveData.length);
                            serverSocket.receive(receivePacket);
                            String manufactur = new String(receiveData,0,receivePacket.getLength());
                            System.out.println(manufactur);

                            receivePacket = new DatagramPacket(receiveData, receiveData.length);
                            serverSocket.receive(receivePacket);

//                            System.out.println("Value of Cost without Integer.parseInt Line 56: "+new String(receiveData,0,receivePacket.getLength()));
                            int cost = Integer.parseInt(new String(receiveData,0,receivePacket.getLength()));

                            receivePacket = new DatagramPacket(receiveData, receiveData.length);
                            serverSocket.receive(receivePacket);
//                            System.out.println("Value of id without Integer.parseInt Line 61: "+new String(receiveData,0,receivePacket.getLength());

                            int id = Integer.parseInt(new String(receiveData,0,receivePacket.getLength()));
                            System.out.println(cost);
                            System.out.println(id);

//                            equipment object, call Add
                            Equipment eq = new Equipment(id,name,manufactur,cost);
                            try{
                                Exception e=eq.addEquipment();
                                if (e==null){
                                    newSentence = "Equipment Added";
                                }
                                else {
                                    newSentence="Equipment already exists with this id\n"+e;
                                }
                            }
                            catch (Exception e1){
                                System.out.println(e1);
                                newSentence=e1+"";
                            }
                            break;
                        case "2":
                            //call viewAll func
                            System.out.println("View Function");
                            ArrayList<Equipment> all = Equipment.viewAll();
                            UDPServer.print(all);
                                //send this to client and print it all
                            newSentence = equipmentString(all);
                            break;
                        case "3":
                            //call search by id
                            receivePacket = new DatagramPacket(receiveData, receiveData.length);
                            serverSocket.receive(receivePacket);
                            System.out.println(new String(receiveData,0,receivePacket.getLength()));
                            id = Integer.parseInt(new String(receiveData,0,receivePacket.getLength()));
                            try {
                                Equipment ans = Equipment.searchEquipment(id);
                                newSentence = ans.getId() +"\t"+ans.getName() +"\t"+ ans.getManufacturer() +"\t"+ ans.getCost();

                            }catch (Exception e){
                                System.out.println(e);
                                newSentence="No Equipment Found of certain ID\n"+e+"";
                            }
                            break;
                        default:
                            //invalid no.
                    }
                    sendData = newSentence.getBytes();
                    sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,port);
                    serverSocket.send(sendPacket);
                }while(!sentence.equals("0"));
            }
            else{
                newSentence="Connection failed";
                sendData = newSentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,
                        port);

                serverSocket.send(sendPacket);
            }
        }
    }
    public static boolean login(String sentence,String[] names){
        for(int i=0;i<names.length;i++)
            if(sentence.contains(names[i]))
                return true;
        return false;
    }

    public static String equipmentString(ArrayList<Equipment> all){
        String ans = "";
        for(int i=0;i<all.size();i++){
            ans += "\n"+all.get(i).getId()+"\t"+all.get(i).getName()+"\t"+all.get(i).getManufacturer()+"\t"+all.get(i).getCost();
        }
        return ans;
    }
    public static void print(ArrayList<Equipment> e1){
        for(int i=0;i< e1.size();i++){
            System.out.println(e1.get(i).getId());
            System.out.println(e1.get(i).getName());
            System.out.println(e1.get(i).getCost());
            System.out.println(e1.get(i).getManufacturer());
            System.out.println("\n");
    }
}
}



