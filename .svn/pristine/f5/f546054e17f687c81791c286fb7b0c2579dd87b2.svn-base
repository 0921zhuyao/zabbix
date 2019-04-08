package com.jd.common.utils;

import java.net.URLDecoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Hex;

public class DesUtil {

	 private static final String DES_ALGORITHM = "DES";

	    /**
	     * DES加密
	     * 
	     * @param plainData 原始字符串
	     * @param secretKey 加密密钥
	     * @return 加密后的字符串
	     * @throws Exception
	     */
	    public static String encryption(String plainData, String secretKey) throws Exception {

	        Cipher cipher = null;
	        try {
	        	cipher = Cipher.getInstance(DES_ALGORITHM);
	        	// DES算法要求有一个可信任的随机数源
	            SecureRandom sr = new SecureRandom();
	            cipher.init(Cipher.ENCRYPT_MODE, generateKey(secretKey),sr);

	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        } catch (NoSuchPaddingException e) {
	            e.printStackTrace();
	        } catch (InvalidKeyException e) {

	        }

	        try {
	            // 为了防止解密时报javax.crypto.IllegalBlockSizeException: Input length must
	            // be multiple of 8 when decrypting with padded cipher异常，
	            // 不能把加密后的字节数组直接转换成字符串
	        	
					byte[] buf = cipher.doFinal(plainData.getBytes());
					return Hex.encodeHexString(buf);
					//return str2HexStr(new String(buf));
	        	
	        	

	        } catch (IllegalBlockSizeException e) {
	            e.printStackTrace();
	            throw new Exception("IllegalBlockSizeException", e);
	        } catch (BadPaddingException e) {
	            e.printStackTrace();
	            throw new Exception("BadPaddingException", e);
	        }

	    }


	    /**
	     * DES解密
	     * @param secretData 密码字符串
	     * @param secretKey 解密密钥
	     * @return 原始字符串
	     * @throws Exception
	     */
	    public static String decryption(String secretData, String secretKey) throws Exception {

	        Cipher cipher = null;
	        try {
	            //
	            cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");//  DES/ECB/NoPadding 
	            cipher.init(Cipher.DECRYPT_MODE, generateKey(secretKey));
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	            throw new Exception("NoSuchAlgorithmException", e);
	        } catch (NoSuchPaddingException e) {
	            e.printStackTrace();
	            throw new Exception("NoSuchPaddingException", e);
	        } catch (InvalidKeyException e) {
	            e.printStackTrace();
	            throw new Exception("InvalidKeyException", e);

	        }

	        try {
	            byte[] buf = cipher.doFinal(hexStr2Bytes(secretData));

	            return new String(buf,"utf-8");

	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new Exception("IllegalBlockSizeException", e);
	        }
	    }
	    public static byte[] hexStr2Bytes(String src){  
	        /*对输入值进行规范化整理*/  
	    	  src = src.trim().replace(" ", "").toUpperCase(Locale.US);
	          //处理值初始化
	          int m=0,n=0;
	          int iLen=src.length()/2; //计算长度
	          byte[] ret = new byte[iLen]; //分配存储空间
	          
	          for (int i = 0; i < iLen; i++){
	              m=i*2+1;
	              n=m+1;
	              ret[i] = (byte)(Integer.decode("0x"+ src.substring(i*2, m) + src.substring(m,n)) & 0xFF);
	          }
	          return ret;
	    }  

	    /**
	     * 获得秘密密钥
	     * 
	     * @param secretKey
	     * @return
	     * @throws NoSuchAlgorithmException
	     * @throws InvalidKeySpecException
	     * @throws InvalidKeyException
	     */
	    private static SecretKey generateKey(String secretKey)
	            throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException {
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES_ALGORITHM);
	        DESKeySpec keySpec = new DESKeySpec(secretKey.getBytes());
	        keyFactory.generateSecret(keySpec);
	        return keyFactory.generateSecret(keySpec);
	    }
	    /**  
	     * 字符串转换成十六进制字符串 
	     * @param str String 待转换的ASCII字符串 
	     * @return String 每个Byte之间空格分隔，如: [61 6C 6B] 
	     */    
	    private final static char[] mChars = "0123456789ABCDEF".toCharArray();
	    public static String str2HexStr(String str){    
	        StringBuilder sb = new StringBuilder();  
	        byte[] bs = str.getBytes();    

	        for (int i = 0; i < bs.length; i++){    
	            sb.append(mChars[(bs[i] & 0xFF) >> 4]);    
	            sb.append(mChars[bs[i] & 0x0F]);  
	            //sb.append(' ');  
	        }    
	        return sb.toString().trim();    
	    }  
	    static  class Base64Utils {

	        static private char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/="
	                .toCharArray();
	        static private byte[] codes = new byte[256];

	        static {
	            for (int i = 0; i < 256; i++)
	                codes[i] = -1;
	            for (int i = 'A'; i <= 'Z'; i++)
	                codes[i] = (byte) (i - 'A');
	            for (int i = 'a'; i <= 'z'; i++)
	                codes[i] = (byte) (26 + i - 'a');
	            for (int i = '0'; i <= '9'; i++)
	                codes[i] = (byte) (52 + i - '0');
	            codes['+'] = 62;
	            codes['/'] = 63;
	        }

	        /**
	         * 将原始数据编码为base64编码
	         */
	        static  String encode(byte[] data) {
	            char[] out = new char[((data.length + 2) / 3) * 4];
	            for (int i = 0, index = 0; i < data.length; i += 3, index += 4) {
	                boolean quad = false;
	                boolean trip = false;
	                int val = (0xFF & (int) data[i]);
	                val <<= 8;
	                if ((i + 1) < data.length) {
	                    val |= (0xFF & (int) data[i + 1]);
	                    trip = true;
	                }
	                val <<= 8;
	                if ((i + 2) < data.length) {
	                    val |= (0xFF & (int) data[i + 2]);
	                    quad = true;
	                }
	                out[index + 3] = alphabet[(quad ? (val & 0x3F) : 64)];
	                val >>= 6;
	                out[index + 2] = alphabet[(trip ? (val & 0x3F) : 64)];
	                val >>= 6;
	                out[index + 1] = alphabet[val & 0x3F];
	                val >>= 6;
	                out[index + 0] = alphabet[val & 0x3F];
	            }

	            return new String(out);
	        }

	        /**
	         * 将base64编码的数据解码成原始数据
	         */
	        static  byte[] decode(char[] data) {
	            int len = ((data.length + 3) / 4) * 3;
	            if (data.length > 0 && data[data.length - 1] == '=')
	                --len;
	            if (data.length > 1 && data[data.length - 2] == '=')
	                --len;
	            byte[] out = new byte[len];
	            int shift = 0;
	            int accum = 0;
	            int index = 0;
	            for (int ix = 0; ix < data.length; ix++) {
	                int value = codes[data[ix] & 0xFF];
	                if (value >= 0) {
	                    accum <<= 6;
	                    shift += 6;
	                    accum |= value;
	                    if (shift >= 8) {
	                        shift -= 8;
	                        out[index++] = (byte) ((accum >> shift) & 0xff);
	                    }
	                }
	            }
	            if (index != out.length)
	                throw new Error("miscalculated data length!");
	            return out;
	        }
	    }
	
	    /**   
	     * 十六进制转换字符串  
	     * @param String str Byte字符串(Byte之间无分隔符 如:[616C6B])  
	     * @return String 对应的字符串  
	     */      
	    public static String hexStr2Str(String hexStr)    
	    {      
	        String str = "0123456789ABCDEF";      
	        char[] hexs = hexStr.toCharArray();      
	        byte[] bytes = new byte[hexStr.length() / 2];      
	        int n;      
	      
	        for (int i = 0; i < bytes.length; i++)    
	        {      
	            n = str.indexOf(hexs[2 * i]) * 16;      
	            n += str.indexOf(hexs[2 * i + 1]);      
	            bytes[i] = (byte) (n & 0xff);      
	        }      
	        return new String(bytes);      
	    }    
	    
	    
	    
	    
	    public static String getKey(String timestamp){
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	        return "wttp"+(dateFormat.format(new Date()))+timestamp;
	    }
	    
	    
	    public static String decrypt(String message,String key) throws Exception {     
	          
	        byte[] bytesrc =convertHexString(message);        
	        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");         
	        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));        
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");        
	        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);        
	        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));     
	        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);           
	        byte[] retByte = cipher.doFinal(bytesrc);          
	        return new String(retByte);      
    	}  
	    
	    
	    public static byte[] convertHexString(String ss)      
	    {      
	    byte digest[] = new byte[ss.length() / 2];      
	    for(int i = 0; i < digest.length; i++)      
	    {      
	    String byteString = ss.substring(2 * i, 2 * i + 2);      
	    int byteValue = Integer.parseInt(byteString, 16);      
	    digest[i] = (byte)byteValue;      
	    }      
	    return digest;      
	    }     
    	public static void main(String[] args) throws Exception{  
    	    
    	    
//    	    Map<String, String> map = new HashMap<String,String>();  
//            map.put("address", "6970地址啦啦");  
//            map.put("monitoredId", "680427");  
//            map.put("birthday", "1990-08-02"); 
//            map.put("monitorId", "680427"); 
//            map.put("type", ""); 
//            map.put("sex", "0"); 
//            map.put("nickName", "6970测试机"); 
//            map.put("stimestamp", "1506042952"); 
//    	    
//            JSONObject json = JSONObject.fromObject(map); 
            
    		String decStr = "fecfd82a80b18c2b3ecd691ede3098ed355d7ac73fc668148cbacf75947f74db25e2298d1756b89c9d9767acd0d5a5987077d17b3215e5d5cc538e16209e5e214fff797e695644937cb6afd60b1ac537";
    	    
    	    
    	   // String decStr =  DESUtil.encryption("{\"timestamp\":\"123\",\"userNo\":\"123123\",\"sendNo\":\"02552360029\",\"content\":\"12312\"}", "wttpwttp");
    		   
    		 System.out.println("加密字符串為:"+decStr);
    		
    	     System.out.println("解密字符串為:"+ URLDecoder.decode (DesUtil.decrypt(decStr, "wttpwttp")));
    	 
    	}
    	
	
	   
}
