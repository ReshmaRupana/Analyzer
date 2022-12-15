/**
 * 
 */
package com.disys.analyzer.security.service.util;

import java.security.SecureRandom;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * @author Sajid
 * 
 */
public class PasswordBasedEncryption {
	/*
	 * This is used to generate passwords CustomPasswordEncoder is used.
	 */
	private static PasswordBasedEncryption instance = null;

    public static final byte[] SALT =
    {
        (byte)0xff, (byte)0x92, (byte)0x25, (byte)0x3c,
        (byte)0x7e, (byte)0xb1, (byte)0xe4, (byte)0xa9
    };

    public static final int ITERATION = 2765;

    public static final String[] passwds = { "corn", "camping", "trueman", "fruits",
    	"mocking", "soccer", "children", "playground", "wonderful", "magician", "delta","washingto","metro","crocs"
    };
    
    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static SecureRandom rnd = new SecureRandom();

    protected PasswordBasedEncryption ()
    {
    }

    public static synchronized PasswordBasedEncryption getInstance ()
    {
        if(instance == null)
        {
            instance = new PasswordBasedEncryption();
        }

        return instance;
    }
    
    public String decrypt (String s)
    {
    	PBEKeySpec       pbeKeySpec              = null;
    	PBEParameterSpec pbeParamSpec            = null;
    	SecretKeyFactory keyFac                  = null;
        String           decryptedPasswordString = null;
        char[]           password                = s.toCharArray();

    	try
    	{
            //Create PBE parameter set
            pbeParamSpec = new PBEParameterSpec(PasswordBasedEncryption.SALT, PasswordBasedEncryption.ITERATION);
            pbeKeySpec   = new PBEKeySpec(password);
            keyFac       = SecretKeyFactory.getInstance("PBEWithMD5AndDES");

            SecretKey pbeKey  = keyFac.generateSecret(pbeKeySpec);

            // Create PBE Cipher
            Cipher pbeCipher  = Cipher.getInstance("PBEWithMD5AndDES");

            // Initialize PBE Cipher with key and parameters
            pbeCipher.init(Cipher.DECRYPT_MODE, pbeKey, pbeParamSpec);

            // Our cleartext
            byte[] cleartext  = createByteArray (password);

            // Encrypt the cleartext
            byte[] ciphertext = pbeCipher.doFinal(cleartext);

            decryptedPasswordString = new String (ciphertext);

            clearCharArray (password);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return decryptedPasswordString;
    }

    public String encrypt (String s)
    {
    	PBEKeySpec       pbeKeySpec              = null;
    	PBEParameterSpec pbeParamSpec            = null;
    	SecretKeyFactory keyFac                  = null;
        String           encryptedPasswordString = null;
        char[]           password                = s.toCharArray();

    	try
    	{
            //Create PBE parameter set
            pbeParamSpec = new PBEParameterSpec(PasswordBasedEncryption.SALT, PasswordBasedEncryption.ITERATION);
            pbeKeySpec   = new PBEKeySpec(password);
            keyFac       = SecretKeyFactory.getInstance("PBEWithMD5AndDES");

            SecretKey pbeKey  = keyFac.generateSecret(pbeKeySpec);

            // Create PBE Cipher
            Cipher pbeCipher  = Cipher.getInstance("PBEWithMD5AndDES");

            // Initialize PBE Cipher with key and parameters
            pbeCipher.init(Cipher.ENCRYPT_MODE, pbeKey, pbeParamSpec);

            // Our cleartext
            byte[] cleartext  = createByteArray (password);

            // Encrypt the cleartext
            byte[] ciphertext = pbeCipher.doFinal(cleartext);

            encryptedPasswordString = new String (ciphertext);

            clearCharArray (password);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        //System.out.println("s = " + s);
        //System.out.println("encryptedPasswordString = " + encryptedPasswordString);
        return encryptedPasswordString;
    }

    public String generateRandomPassword()
    {
    	String password = randomString(10);
    	return password;
    }

    String randomString( int len ){
 	   StringBuilder sb = new StringBuilder( len );
 	   for( int i = 0; i < len; i++ ) 
 	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
 	   return sb.toString();
 	}

    // Generate a userID with the role, update db and notify user

    public String createGenericUser(String userID, String roleID)
    {
      Connection con = null;
      CallableStatement callStmt = null;
      ResultSet rs = null;
      String result = "0";
      String randomPassword = "";
      String randomEncryptedPassword = "";

      try
      {

        con = DBConnection.getConnection();
        if(con != null)
        {
          String query = null;
          randomPassword = generateRandomPassword();
          randomEncryptedPassword = encrypt(randomPassword);

          query = "{call dob.spCreateGenericUser('" + userID + "','"
            + roleID + "','" + randomPassword + "','"   + randomEncryptedPassword + "')}";

          System.out.println("Executing Query in createGenericUser -- :  " + query);

          callStmt = con.prepareCall(query);
          rs = callStmt.executeQuery();

          if (rs != null) {
            if (rs.next())
            {
                result = rs.getString(1);
            }

          }

          con.close();
          callStmt.close();
          if (rs != null) {
              rs.close();
          }
          con = null;
          callStmt = null;
          rs = null;

        }else
        {
          throw new Exception("Connection is null");
        }
      }catch (Exception ex) {
        System.out.println("Exception in createGenericUser method of PasswordBasedEncryption");
        ex.printStackTrace();
        return "System Error";
      }finally
      {
        DBConnection.closeConnection(con, callStmt, rs);
      }

      return result;

  }

    public void encryptDBField ()
    {
        Connection con         = null;
        Statement  s           = null;
        Statement s2           = null;
        ResultSet  rs          = null;
        String updateStatement = null;

        try
        {
            Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");

            String url = "jdbc:microsoft:sqlserver://192.168.1.11:1893";

            con = DriverManager.getConnection(url, "wireless", "wireless");

            s   = con.createStatement();
            s2  = con.createStatement();

            rs  = s.executeQuery("select user_id,password from users");

            while (rs.next())
            {
            	System.out.println(rs.getString("password"));
                System.out.println(PasswordBasedEncryption.getInstance().encrypt(rs.getString("password")));
            	System.out.println();

                updateStatement = "UPDATE users " +
                                  "SET    password = '" + PasswordBasedEncryption.getInstance().encrypt(rs.getString("password")) + "'" +
                                  "WHERE  user_id = '" + rs.getString("user_id") + "'";

                s2.executeUpdate(updateStatement);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (rs != null)
            {
                try
                {
                    rs.close();
                }
                catch (Exception e)
                {
                }
            }

            if (s != null)
            {
                try
                {
            	      s.close();
                }
                catch (Exception e)
                {
                }
            }

            if (s2 != null)
            {
                try
                {
            	      s2.close();
                }
                catch (Exception e)
                {
                }
            }

            if (con != null)
            {
                try
                {
            	      con.close();
                }
                catch (Exception e)
                {
                }
            }
        }
    }

    private char[] clearCharArray (char[] c)
    {
    	Arrays.fill(c, ' ');

        return c;
    }

    private byte[] createByteArray (char[] c)
    {
    	byte[] b = new byte[c.length];

    	for (int i = 0; i < c.length; i++)
    	{
    	    b[i] = (byte)c[i];
        }

        return b;
    }
    
    private int getRandom(int mod) {
        Random rand = new Random();
        return Math.abs(rand.nextInt()) % mod + 1;
    }


    private int doRawRandomNumber2() {

        int rawRandomNumber = 0;

        for (int i = 0; i < 20; i++) {
            rawRandomNumber = getRandom(10);
        }

        return rawRandomNumber;
    }
    
    public String getRandomPassword()
    {
    	return passwds[doRawRandomNumber2()];
    }

    public static void main (String [] args)
    {
    	//PasswordBasedEncryption.getInstance().encryptDBField();
      System.out.println(PasswordBasedEncryption.getInstance().encrypt("wonders"));
    }
}
