package egovframework.rte.util.fileupload.multi.service;
/**
 * @file Name : FileInfoVO.java
 * @Description : FileInfoVO class
 * @Modification Information
 * @
 * @  수정일         수정자                   수정내용
 * @ -------    --------    ---------------------------
 * @ 2009.03.05      이동도          최초 생성
 *
 *  @author 실행환경 개발팀 이동도
 *  @since 2009.03.05
 *  @version 1.0
 *  @see
 *
 *  Copyright (C) 2009 by MOPAS  All right reserved.
 */
import java.io.Serializable;

public class FileInfoVO implements Serializable {

	private static final long serialVersionUID = -2334475343466662506L;

	private String filePath;

	private String fileName;

	private String orizinalFileName;

	private long fileSize;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getOrizinalFileName() {
		return orizinalFileName;
	}

	public void setOrizinalFileName(String orizinalFileName) {
		this.orizinalFileName = orizinalFileName;
	}

}
