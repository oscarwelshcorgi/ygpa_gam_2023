package egovframework.com.ygpa.cmm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import egovframework.com.cmm.service.impl.EgovFileMngServiceImpl;

public class YGProperties {

	//프로퍼티값 로드시 에러발생하면 반환되는 에러문자열
	public static String ERR_CODE =" EXCEPTION OCCURRED";
	public static String ERR_CODE_FNFE =" EXCEPTION(FNFE) OCCURRED";
	public static String ERR_CODE_IOE =" EXCEPTION(IOE) OCCURRED";

	//파일구분자
    static final char FILE_SEPARATOR     = File.separatorChar;

    public static final Logger LOGGER = Logger.getLogger(EgovFileMngServiceImpl.class.getName());

	//프로퍼티 파일의 물리적 위치
    public  static String GLOBALS_PROPERTIES_FILE
        //= "D:/project/egov/workspace/egovcmm/target/classes/egovframework/cmm/utl/globals.properties";
        = System.getProperty("user.home") + System.getProperty("file.separator") + "egovProps"
        + System.getProperty("file.separator") + "ygpa.properties";

	/**
	 * 인자로 주어진 문자열을 Key값으로 하는 프로퍼티 값을 반환한다(Globals.java 전용)
	 * @param keyName String
	 * @return String
	 */
	public static String getProperty(String keyName){
		String value = ERR_CODE;
		value="99";
		LOGGER.debug(GLOBALS_PROPERTIES_FILE + " : " + keyName);
		FileInputStream fis = null;
		try{
			Properties props = new Properties();
			fis  = new FileInputStream(GLOBALS_PROPERTIES_FILE);
			props.load(new java.io.BufferedInputStream(fis));
			value = props.getProperty(keyName).trim();
		}catch(FileNotFoundException e){
			LOGGER.error("File not found Error : "+ e.getMessage());
		}catch(IOException e){
			LOGGER.error("I/O Error : "+ e.getMessage());
		}catch(Exception e){
			LOGGER.error("Unexpected Error : "+ e.getMessage());
		}finally{
			try {
				if (fis != null) fis.close();
			} catch (IOException i) {

			} catch (Exception e){
				
			} 

		}
		return value;
	}

	/**
	 * 주어진 파일에서 인자로 주어진 문자열을 Key값으로 하는 프로퍼티 값을 반환한다
	 * @param fileName String
	 * @param key String
	 * @return String
	 */
	public static String getProperty(String fileName, String key){
		FileInputStream fis = null;
		try{
			java.util.Properties props = new java.util.Properties();
			fis  = new FileInputStream(fileName);
			props.load(new java.io.BufferedInputStream(fis));
			fis.close();

			String value = props.getProperty(key);
			return value;
		}catch(java.io.FileNotFoundException fne){
			return ERR_CODE_FNFE;
		}catch(java.io.IOException ioe){
			return ERR_CODE_IOE;
		}finally{
			try {
				if (fis != null) fis.close();
			} catch (IOException i) {

			} catch (Exception e){
				
			} 
		}
	}

	/**
	 * 주어진 프로파일의 내용을 파싱하여 (key-value) 형태의 구조체 배열을 반환한다.
	 * @param property String
	 * @return ArrayList
	 */
	public static ArrayList loadPropertyFile(String property){

		// key - value 형태로 된 배열 결과
		ArrayList keyList = new ArrayList();

		String src = property.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		FileInputStream fis = null;
		try
		{

			File srcFile = new File(src);
			if (srcFile.exists()) {

				java.util.Properties props = new java.util.Properties();
				fis  = new FileInputStream(src);
				props.load(new java.io.BufferedInputStream(fis));
				fis.close();

				int i = 0;
				Enumeration plist = props.propertyNames();
				if (plist != null) {
					while (plist.hasMoreElements()) {
						Map map = new HashMap();
						String key = (String)plist.nextElement();
						map.put(key, props.getProperty(key));
						keyList.add(map);
					}
				}
			}
		}
		catch(FileNotFoundException e) {
			LOGGER.error("file not found : "+e.getMessage());
		}
		catch(IOException e) {
			LOGGER.error("io error : "+ e.getMessage());
		}
		catch (Exception e){
			LOGGER.error("Unexpected Error : "+ e.getMessage());
		} finally {
			try {
				if (fis != null) fis.close();
			} catch(IOException i){ 
			
			} catch (Exception e) {
				LOGGER.error("file close error : "+ e.getMessage());
			}
		}

		return keyList;
	}
}
