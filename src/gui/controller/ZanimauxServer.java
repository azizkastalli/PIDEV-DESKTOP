/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import static gui.controller.LoginController.loggduser;
import entites.Participantsencheres;
import entites.QuartzJob;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import services.ServiceParticipantEncheres;

/**
 *
 * @author azizkastalli
 */


public class ZanimauxServer  
{
   HashMap <PrintWriter,Integer> clientOutputStreams;
   ArrayList<String> users;
   
   public ZanimauxServer()  {}                     

   public class ClientHandler implements Runnable	
   {
       BufferedReader reader;
       Socket sock;
       PrintWriter client;
       
       public ClientHandler(Socket clientSocket, PrintWriter user) 
       {
            client = user;
            try 
            {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
            }
            catch (Exception ex) 
            {
                System.out.println("Unexpected error... ");
            }

       }

       @Override
       public void run() 
       {
            String message, connect = "Connect", disconnect = "Disconnect", chat = "Chat" ;
            String[] data;
            
            try 
            {
                while ((message = reader.readLine()) != null) 
                {
                    System.out.println("Received: " + message );
                    data = message.split(":");
                    for (String token:data) 
                    {
                        System.out.println(token + "\n");
                    }

                    if (data[2].equals(connect)) 
                    {
                        tellAll(data[0] + ":" + data[1] + ":" + chat);
                        userAdd(data[0]);
                    } 
                    else if (data[2].equals(disconnect)) 
                    {
                        tellAll(data[0] + ":has disconnected." + ":" + chat);
                        userRemove(data[0]);
                    } 
                    else if (data[2].equals(chat)) 
                    {
                        tellAll(message);
                    } 
                    else 
                    {
                        System.out.println("No Conditions were met. ");
                    }
                } 
             } 
             catch (Exception ex) 
             {
                System.out.println("Lost a connection. ");
                ex.printStackTrace();
                clientOutputStreams.remove(client);
             } 
	} 
    }


    private void b_endActionPerformed() {                                      
        try 
        {
            Thread.sleep(5000);                 //5000 milliseconds is five second.
        } 
        catch(InterruptedException ex) {Thread.currentThread().interrupt();}
        
        tellAll("Server:is stopping and all users will be disconnected.\n:Chat");
        System.out.println("Server stopping... ");
        
    }                                     

    private void startServer() {                                        
       try {                                        
           
           ServiceParticipantEncheres Participants = new ServiceParticipantEncheres();
           ArrayList<Participantsencheres> ListeParticipants = new ArrayList<>();
            Scheduler sc = StdSchedulerFactory.getDefaultScheduler();
            sc.start();
           try {
               ListeParticipants= (ArrayList<Participantsencheres>) Participants.SelectAll();
            } catch (SQLException ex) {
               Logger.getLogger(ZanimauxServer.class.getName()).log(Level.SEVERE, null, ex);
           }
           
           Timestamp triggerStartTime;
           String Triggername = "a";
          
           for(Participantsencheres P : ListeParticipants)
           {
            Triggername +="a"; 
            
            JobDataMap  Jobdata = new JobDataMap();
            JobDetail job = JobBuilder
                            .newJob(QuartzJob.class)
                            .setJobData(Jobdata)
                            .usingJobData("number",P.getNum())
                            .build();
            triggerStartTime = P.getDebut_session();
            triggerStartTime.setMinutes(P.getDebut_session().getMinutes()-30);
            Trigger t1 = TriggerBuilder.newTrigger().withIdentity(Triggername).startAt(triggerStartTime).build();
            sc.scheduleJob(job,t1);
           }
           
           
           System.out.println("Server started... ");
       } catch (SchedulerException ex) {
           Logger.getLogger(ZanimauxServer.class.getName()).log(Level.SEVERE, null, ex);
       }
            Thread starter = new Thread(new ServerStart());
            starter.start();
    }                                       

    private void b_usersActionPerformed() {                                        
        System.out.println(" Online users : ");
        for (String current_user : users)
        {
          System.out.println(current_user);
        }    
        
    }                                       

    private void b_clearActionPerformed() {                                        
       // ta_chat.setText("");
    }                                       

    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() {
              ZanimauxServer s =  new ZanimauxServer();
              s.startServer();
            }
        });
    }
    
    public class ServerStart implements Runnable 
    {       
        @Override
        public void run() 
        {
            clientOutputStreams = new HashMap<>();
            users = new ArrayList();  
            try 
            {
                ServerSocket serverSock = new ServerSocket(2222);
                
                while (true) 
                {
				Socket clientSock = serverSock.accept();                          
				PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
				clientOutputStreams.put(writer,1);
				Thread listener = new Thread(new ClientHandler(clientSock, writer));
				listener.start();
				System.out.println("Got a connection. ");
                }
            }
            catch (IOException ex)
            {
                System.out.println("Error making a connection. \n");
            }
        }
    }
    
    public void userAdd (String data) 
    {
        String message, add = ": :Connect", done = "Server: :Done", name = data;
        System.out.println("Before " + name + " added. ");
        users.add(name);
        System.out.println("After " + name + " added. ");
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token:tempList) 
        {
            message = (token + add);
            tellAll(message);
        }
        tellAll(done);
    }
    
    public void userRemove (String data) 
    {
        String message, add = ": :Connect", done = "Server: :Done", name = data;
        users.remove(name);
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token:tempList) 
        {
            message = (token + add);
            tellAll(message);
        }
        tellAll(done);
    }
 
  
    
    public void tellAll(String message) 
    {
        for (Map.Entry mapentry : clientOutputStreams.entrySet()) 
          {
                try 
            {
                PrintWriter writer = (PrintWriter) mapentry.getKey() ;
		writer.println(message);
		System.out.println("Sending: " + message );
                writer.flush();
            } 
            catch (Exception ex) {System.out.println("Error telling everyone. ");}  
          } 
    }
                    
}

