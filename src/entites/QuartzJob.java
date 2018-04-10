/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import utils.SendSms;

/**
 *
 * @author azizkastalli
 */
public class QuartzJob implements Job {
    
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        //get jobdata
        JobDataMap jb=  jec.getMergedJobDataMap();
        SendSms SmS = new SendSms();
        String sms="votre session d'encheres commance dans 30 minutes !";
        SmS.Send("20435370", sms);
    }
    
}
