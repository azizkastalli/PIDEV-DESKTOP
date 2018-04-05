/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.twilio.Twilio; 
import com.twilio.rest.api.v2010.account.Message; 
import com.twilio.rest.api.v2010.account.MessageCreator;
//import com.twilio.rest.api.v2010.account.MessageCreator; 
import com.twilio.type.PhoneNumber; 
 
import java.math.BigDecimal; 
import java.net.URI; 
import java.util.ArrayList; 
import java.util.List; 
/**
 *
 * @author azizkastalli
 */
public class SendSms {
         private final static String ACCOUNT_SID = "AC50ff5df46d0773982ed1d40f345ce8f7"; 
    private final static String AUTH_TOKEN = "a16dd0f9c1a70dc2de0b4289c6d4f6a5"; 
 
    public static void main(String[] args) { 
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN); 
         
        MessageCreator messageCreator;
        messageCreator = Message.creator( 
        ACCOUNT_SID,
        new PhoneNumber("+21620435370"),
        new PhoneNumber("+13305744387"),
        "salut frer");
    
         
        Message message = messageCreator.create(); 
        System.out.println(message.getSid()); 
    } 
}
