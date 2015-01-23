/**
 *
 */
package egovframework.rte.ygpa.gam.cmmn.service;

import java.io.Serializable;

/**
 *
 * @author EUNSUNGJ
 * @since 2015. 1. 22.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 1. 22.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@SuppressWarnings("serial")
public class GamFileServiceVo implements Serializable  {

	public static int TYPE_ALL_FILE = 0;
	public static int TYPE_IMAGE_FILE = 1;
	public static int TYPE_DWG_FILE = 2;
	public static int TYPE_DOC_FILE = 3;

    /** 파일명 */
    private String logicalFileNm = "";
    /** ContextType */
    private String contentType = "";
    /** 하위 디렉토리 지정 */
    private String serverSubPath = "";
    /** 물리적 파일명 */
    private String physcalFileNm = "";
    /** 파일 사이즈 */
    private long size = 0L;

	/**
	 * @return the fileName
	 */
	public String getLogicalFileNm() {
		return logicalFileNm;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setLogicalFileNm(String fileName) {
		this.logicalFileNm = fileName;
	}
	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}
	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	/**
	 * @return the serverSubPath
	 */
	public String getServerSubPath() {
		return serverSubPath;
	}
	/**
	 * @param serverSubPath the serverSubPath to set
	 */
	public void setServerSubPath(String serverSubPath) {
		this.serverSubPath = serverSubPath;
	}
	/**
	 * @return the physicalName
	 */
	public String getPhyscalFileNm() {
		return physcalFileNm;
	}
	/**
	 * @param physicalName the physicalName to set
	 */
	public void setPhyscalFileNm(String physicalName) {
		this.physcalFileNm = physicalName;
	}
	/**
	 * @return the size
	 */
	public long getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(long size) {
		this.size = size;
	}


}
