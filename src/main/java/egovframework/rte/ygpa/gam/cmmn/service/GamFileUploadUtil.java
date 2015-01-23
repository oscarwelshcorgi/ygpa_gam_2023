/**
 *
 */
package egovframework.rte.ygpa.gam.cmmn.service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.rte.cmmn.web.GenericFileUpDownloadController;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.idgnr.impl.EgovTableIdGnrService;
import egovframework.rte.util.fileupload.multi.service.FileInfoVO;

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

public class GamFileUploadUtil {

    private static final Logger LOG = Logger.getLogger(GenericFileUpDownloadController.class.getName());

    private static long maxFileSize = 1024 * 1024 * 10;   //업로드 최대 사이즈 설정 (10M)

	/**
	 * 파일을 지정한 디렉토리에 업로드 한다.
	 * @param request
	 * @param uploadPath
	 * @param egovFileIdGnrService
	 * @return
	 * @throws Exception
	 */
	public static List<GamFileServiceVo> uploadFiles(HttpServletRequest request, String uploadPath, EgovIdGnrService egovFileIdGnrService)
			throws Exception {

		Assert.state(request instanceof MultipartHttpServletRequest,
				"request !instanceof MultipartHttpServletRequest");
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

		Assert.state(request.getParameter("type").equals("genericFileMulti"),
				"type != genericFileMulti");

		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		Assert.notNull(files, "files is null");
		Assert.state(files.size() > 0, "0 files exist");
		Assert.state(files.size() < maxFileSize, "file size too big");

		File saveFolder = new File(uploadPath);

		// 디렉토리 생성
		if (!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.mkdirs();
		}

		Iterator<Entry<String, MultipartFile>> itr = files.entrySet()
				.iterator();
		MultipartFile file;
		List<GamFileServiceVo> list = new ArrayList<GamFileServiceVo>();
		String filePath;

		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();

			file = entry.getValue();
			String[] tokens = file.getOriginalFilename().split("\\.(?=[^\\.]+$)");
			if (!"".equals(file.getOriginalFilename())
					|| !"exe".equalsIgnoreCase(tokens[1])) {	// 파일명이 없는 파일과 실행파일은 저장 할 수 없다.
				GamFileServiceVo fileInfoVO = new GamFileServiceVo();
				fileInfoVO.setPhyscalFileNm(egovFileIdGnrService.getNextStringId()+"."+tokens[1]);
				fileInfoVO.setLogicalFileNm(file.getOriginalFilename());
				fileInfoVO.setSize(file.getSize());
				filePath = uploadPath + fileInfoVO.getPhyscalFileNm();
				file.transferTo(new File(filePath));

				list.add(fileInfoVO);
			}
		}

		return list;

	}

    /**
     * 파일을 이미지로 다운로드 한다.
     * @param request
     * @param response
     * @throws Exception
     */
    public static void downloadImage(final HttpServletRequest request, HttpServletResponse response, String uploadPath, GamFileServiceVo gamFileServiceVo) throws Exception {

		File file = null;
		FileInputStream fis = null;

		BufferedInputStream in = null;
		ByteArrayOutputStream bStream = null;

		String[] tokens = gamFileServiceVo.getPhyscalFileNm().split("\\.(?=[^\\.]+$)");


		try {
		    file = new File(uploadPath, gamFileServiceVo.getPhyscalFileNm());
		    fis = new FileInputStream(file);

		    in = new BufferedInputStream(fis);
		    bStream = new ByteArrayOutputStream();

		    int imgByte;
		    while ((imgByte = in.read()) != -1) {
		    	bStream.write(imgByte);
		    }

			String type = "";

			if (tokens[1] != null && !"".equals(tokens[1])) {
			    if ("jpg".equalsIgnoreCase(tokens[1])) {
				type = "image/jpeg";
			    } else {
				type = "image/" + tokens[1].toLowerCase();
			    }
			    type = "image/" + tokens[1].toLowerCase();

			} else {
			    LOG.debug("Image fileType is null.");
			}

			response.setHeader("Content-Type", type);
			response.setContentLength(bStream.size());

			bStream.writeTo(response.getOutputStream());

			response.getOutputStream().flush();
			response.getOutputStream().close();

			// 2011.10.10 보안점검 후속조치 끝
		}
		catch(FileNotFoundException e) {
			LOG.error("file not found : "+ e.getMessage());
		}
		catch(IOException e) {
			LOG.error("I/O Error : "+ e.getMessage());
		}
		finally {
			if (bStream != null) {
				try {
					bStream.close();
				} catch (Exception ignore) {
					LOG.debug("IGNORE: " + ignore.getMessage());
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (Exception ignore) {
					LOG.debug("IGNORE: " + ignore.getMessage());
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception ignore) {
					LOG.debug("IGNORE: " + ignore.getMessage());
				}
			}
		}
    }

	/**
	 * 파일을 다운로드 한다.
	 * @param request
	 * @param response
	 * @param uploadPath
	 * @param gamFileServiceVo
	 * @throws Exception
	 */
	public static void downloadFile(HttpServletRequest request, HttpServletResponse response, String uploadPath, GamFileServiceVo gamFileServiceVo) throws Exception {

		File uFile = new File(uploadPath, gamFileServiceVo.getPhyscalFileNm());
		int fSize = (int) uFile.length();
		BufferedInputStream in = null;

		if (fSize > 0) {
			try {
				in = new BufferedInputStream(
						new FileInputStream(uFile));
				// String mimetype = servletContext.getMimeType(requestedFile);
				String mimetype = "text/html";
				String downName=null;

		       	 String browser = request.getHeader("User-Agent");
		    	 if(browser.contains("MSIE") || browser.contains("Trident") || browser.contains("Chrome")){
			    		 downName = URLEncoder.encode(gamFileServiceVo.getLogicalFileNm(),"UTF-8").replaceAll("\\+", "%20");
			    		 LOG.debug("$$$ FileDownload from Chrome or msie executed, filename = "+ downName);
		       	 } else {
			       		 downName = new String(gamFileServiceVo.getLogicalFileNm().getBytes("UTF-8"), "ISO-8859-1");
			    		 LOG.debug("$$$ FileDownload from Other Browser executed, filename = "+ downName);
		       	 }

				response.setBufferSize(fSize);
				response.setContentType(mimetype);
				response.setHeader("Content-Disposition", "attachment; filename=\""
						+ downName + "\"");
				response.setContentLength(fSize);

				FileCopyUtils.copy(in, response.getOutputStream());
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}
			catch(Exception e) {
				LOG.error("download file error : "+e.getMessage());
			}
			finally {
				if(in!=null) in.close();
			}
		} else {
			//setContentType을 프로젝트 환경에 맞추어 변경
			response.setContentType("application/x-msdownload");
			PrintWriter printwriter = response.getWriter();
			printwriter.println("<html>");
			printwriter.println("<br><br><br><h2>Could not get file name:<br>"
					+ gamFileServiceVo.getLogicalFileNm() + "</h2>");
			printwriter
					.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
			printwriter.println("<br><br><br>&copy; webAccess");
			printwriter.println("</html>");
			printwriter.flush();
			printwriter.close();
		}
	}

}
