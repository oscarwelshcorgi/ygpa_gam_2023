package egovframework.com.ygpa.cmm.miplatform;

import java.io.File;

import org.apache.log4j.Logger;

import egovframework.com.ygpa.cmm.YGProperties;

public class YGMiPlatformForm {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(YGMiPlatformForm.class);

    protected String url; 
    protected File file;
    
    
    final char FILE_SEPARATOR     = File.separatorChar;
    
    public File getFile() {
        return file;
    }



    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    /**
     * String url 기반 마이플랫폼 XML 화면 호출 
     * @param url
     */
    public YGMiPlatformForm(String url) {
//        if (logger.isDebugEnabled()) {
//            logger.debug("ISMiPlatformForm(String) - start"); //$NON-NLS-1$
//        }

        this.url = url;
       
        String miplatformPath = YGProperties.getProperty("YGPA.ygam.miplatformPath");

        String fileSrcValue = (miplatformPath + url).replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
        

        
            this.file =  new File (fileSrcValue);
            
            if (logger.isDebugEnabled()) {
                logger.debug("YGMiPlatformForm(String) - center pdfFileSrcValue="+fileSrcValue + " this.file=" + this.file.length() +" this.file.getName()=" + this.file.getName()); //$NON-NLS-1$
            }            
//        if (logger.isDebugEnabled()) {
//            logger.debug("ISMiPlatformForm(String) - end"); //$NON-NLS-1$
//        }
    }
    
//    public String getXmlForm(){
//        
//        
//        
//        return 
//    }
}
