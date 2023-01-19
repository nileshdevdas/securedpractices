package com.vinsys.securitylabs.captchasolution;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class CaptchaService {
	// Returns true if given two strings are same
    public boolean checkCaptcha(String captcha, String user_captcha)
    {
        return captcha.equals(user_captcha);
    }
     
    // Generates a CAPTCHA of given length
   public String generateCaptcha(int n)
    {
        //to generate random integers in the range [0-61]
        Random rand = new Random(62);
         
        // Characters to be included
        String chrs = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
      
        // Generate n characters from above set and
        // add these characters to captcha.
        String captcha = "";
        while (n-->0){
            int index = (int)(Math.random()*62);
            captcha+=chrs.charAt(index);
        }
           
        return captcha;
    }
	
	
}
