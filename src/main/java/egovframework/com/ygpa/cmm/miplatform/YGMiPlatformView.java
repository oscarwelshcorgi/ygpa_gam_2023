package egovframework.com.ygpa.cmm.miplatform;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.tobesoft.platform.PlatformConstants;
import com.tobesoft.platform.PlatformResponse;

import egovframework.com.ygpa.cmm.YGProperties;

public class YGMiPlatformView extends AbstractView {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(YGMiPlatformView.class);


    final char FILE_SEPARATOR     = File.separatorChar;
    
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("renderMergedOutputModel(Map, HttpServletRequest, HttpServletResponse) - start"); //$NON-NLS-1$
        }

        //        if (logger.isDebugEnabled()) {
//            logger.debug("renderMergedOutputModel(Map, HttpServletRequest, HttpServletResponse) - start"); //$NON-NLS-1$
//        }
        if ( model.get("ajaxXml") != null ){
            PrintWriter writer = null;
            try {
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                response.setCharacterEncoding("UTF-8");

                writer = response.getWriter();
                writer.write((String) model.get("ajaxXml"));
                
            } finally {

                writer.close();
            }
        }
        if ( model.get("miplatformData") != null ){
            Object data = model.get("miplatformData");
            if ( data.getClass() ==  YGMiPlatformData.class) {

//                if (logger.isDebugEnabled()) {
//                    logger.debug("renderMergedOutputModel(Map, HttpServletRequest, HttpServletResponse) - miplatformData"); //$NON-NLS-1$
//                }               
            	YGMiPlatformData platformData = (YGMiPlatformData) data;
                
//                platformData.getPlatformData().printData();
                
                new PlatformResponse(response, PlatformConstants.CHARSET_UTF8).sendData(platformData.getPlatformData());
            
            }
        }
        
        if ( model.get("miplatformForm") != null ){
            Object form = model.get("miplatformForm");
            if ( form.getClass() ==  YGMiPlatformForm.class) {
                
//                if (logger.isDebugEnabled()) {
//                    logger.debug("renderMergedOutputModel(Map, HttpServletRequest, HttpServletResponse) - miplatformForm"); //$NON-NLS-1$
//                }
                
            	YGMiPlatformForm platformForm = (YGMiPlatformForm) form;
                
    //          PrintWriter writer = null;
    //          try{
    //              response.setContentType("text/xml");
    //                response.setHeader("Cache-Control", "no-cache");
    //                response.setCharacterEncoding("UTF-8");
    //                
    //                writer = response.getWriter();
    //                writer.write((platformForm.); //파일 자체도 가능한지 체크 pdk ship
    //          }finally{
    //              writer.close();
    //          }
                
    //          BufferedInputStream in = new BufferedInputStream(new FileInputStream(platformForm.getUrl()));
                
                //response 방식
                /*
                
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(temp));
                FileCopyUtils.copy(in, response.getOutputStream());
                in.close();
                response.getOutputStream().flush();
                response.getOutputStream().close();
                */
                // download 방식
                
                
                
                
                File uFile = platformForm.getFile();
                int fSize = (int)uFile.length();
                if (logger.isDebugEnabled()) {
                    logger.debug("renderMergedOutputModel(Map, HttpServletRequest, HttpServletResponse) - center filename = " + uFile.getName() + " fSize=" + fSize); //$NON-NLS-1$
                }
                if (fSize > 0) {
                    BufferedInputStream in2 = new BufferedInputStream(new FileInputStream(uFile));
    
                    String mimetype = "application/x-msdownload";
                    //response.setBufferSize(fSize);
                    response.setContentType(mimetype);
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + platformForm.getFile().getName() + "\"");
                    response.setContentLength(fSize);
    
                    FileCopyUtils.copy(in2, response.getOutputStream());
                    in2.close();
                    response.getOutputStream().flush();
                    response.getOutputStream().close(); 
                }else{
                    try{
                        String fileNotFound = "/ygpa/cmm/sec/fileNotFound.xml";
                        String miplatformPath = YGProperties.getProperty("YGPAAM.ygam.miplatformPath");
                        String fileNotFoundValue = (miplatformPath + fileNotFound).replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
                        File nFile = new File (fileNotFoundValue);
                        int nSize = (int)nFile.length();
                        
                        
                        BufferedInputStream in2 = new BufferedInputStream(new FileInputStream(nFile));
                        
                        String mimetype = "application/x-msdownload";
                        //response.setBufferSize(fSize);
                        response.setContentType(mimetype);
                        response.setHeader("Content-Disposition", "attachment; filename=\"" + nFile.getName() + "\"");
                        response.setContentLength(nSize);
        
                        FileCopyUtils.copy(in2, response.getOutputStream());
                        in2.close();
                        response.getOutputStream().flush();
                        response.getOutputStream().close();      
                    }catch(Exception e){
                        logger.warn("renderMergedOutputModel(miplatformForm)- exception ignored", e.toString()); //$NON-NLS-1$
                        
                    }
                }
                
                // response head 방식
                
                // response content 방식 - xml
            }
        }
        
        
//        if (logger.isDebugEnabled()) {
//            logger.debug("renderMergedOutputModel(Map, HttpServletRequest, HttpServletResponse) - end"); //$NON-NLS-1$
//        }

        if (logger.isDebugEnabled()) {
            logger.debug("renderMergedOutputModel(Map, HttpServletRequest, HttpServletResponse) - end"); //$NON-NLS-1$
        }
	}

}
