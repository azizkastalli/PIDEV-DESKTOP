/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.twilio.Twilio; 
import com.twilio.rest.api.v2010.account.Message; 
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber; 
 

/**
 *
 * @author azizkastalli
 */
public class SendSms {
    private final static String ACCOUNT_SID = "AC50ff5df46d0773982ed1d40f345ce8f7"; 
    private final static String AUTH_TOKEN = "a16dd0f9c1a70dc2de0b4289c6d4f6a5"; 
 
    public  void Send(String number,String sms) { 
       Twilio.init(ACCOUNT_SID, AUTH_TOKEN); 
         
        MessageCreator messageCreator;
        messageCreator = Message.creator( 
        ACCOUNT_SID,
        new PhoneNumber("+216"+number),
        new PhoneNumber("+13305744387"),
        sms);
    
         
        Message message = messageCreator.create(); 
     
  System.out.println("oooo"); 
    } 
}
