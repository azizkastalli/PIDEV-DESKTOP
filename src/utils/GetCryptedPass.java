/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author USER
 */
public class GetCryptedPass {
    public static String getCryptedPass(String plainPassword){
        String pw_hash = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        String myPass = pw_hash;
          char[] myPassChars = myPass.toCharArray();
        myPassChars[2] = 'y';
        myPass = String.valueOf(myPassChars);
        return myPass;
    }
}
