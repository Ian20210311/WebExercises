package controller.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;


import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.jsoninfo.course;

public class allutil {
	
	public String readJson(String file) throws IOException
	{
		//String filePath = "https://cloud.hakka.gov.tw/Pub/Opendata/DTST20200800001.json";
		
		URL url = new URL(file);//your url i.e fetch data from .
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setRequestProperty("Accept", "application/json");
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP Error code : "
                    + conn.getResponseCode());
        }
        //System.out.println(Charset.defaultCharset());
        
        InputStreamReader in = new InputStreamReader(conn.getInputStream(),"UTF-8");
        BufferedReader br = new BufferedReader(in);
        String output="";
        String rtnStr="";
        while ((output = br.readLine()) != null) {
            //System.out.println(output);
            rtnStr += output ;
        }
        
        return rtnStr ;
        
        /*
        InputStream InputStream = conn.getInputStream();
        byte[] bytes = new byte[1024];
        String content = "";
        while (InputStream.read(bytes) > 0) {
            content += new String(bytes,"UTF-8");
        }
        conn.disconnect();
        System.out.println(content);
		return content;
		*/
	}
	
	//inputStream to byte[]
	public byte[] readlocalJSON(InputStream in)
	{
		byte[] targetArray = null;
		try
		{
			int intbytes = in.available();
	        targetArray = new byte[intbytes];
	        in.read(targetArray);
	        /*
			byte[] bytes = new byte[1024];
	        while (in.read(bytes) > 0) {
	            content += new String(bytes,"UTF-8");
	        }*/
	        //System.out.println(content);
		}
		catch(IOException io)
		{
			io.printStackTrace();
		}
		finally
		{
			return targetArray;
		}
		
		
	}
	
	public Type fromJsonObj(String jsStr, Object class1)
	{
		Type objs = null;

		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();
		objs = (Type) gson.fromJson(jsStr, class1.getClass());
		
		return objs;
	}
	
	//取得current formate time
	public static Timestamp getTimeStamp() {
		// Date utilDate = new java.util.Date();
		// Timestamp sqlTS = new java.sql.Timestamp(utilDate.getTime());
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date current = new Date();
		String getCurrent = sdf.format(current);
		// Timestamp sqlTS = new java.sql.Timestamp(System.currentTimeMillis() );
		return Timestamp.valueOf(getCurrent);
	}
	
	//產生1-10 random num
	public static String get10random()
	{
		return String.valueOf((int)(Math.random()*10)) ;
	}
	
	//設定DB 密碼 規則(mapping "student_info" table, "pwdHash" column)
	public static String getPwdHash(String seed, String pwd)
	{
		return seed + pwd.hashCode() ;
	}

}
