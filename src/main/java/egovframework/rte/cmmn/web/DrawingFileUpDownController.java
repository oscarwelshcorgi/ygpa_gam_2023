package egovframework.rte.cmmn.web;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import egovframework.com.cmm.SessionVO;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.FileVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.util.fileupload.multi.service.FileInfoVO;

@Controller("drawingFileUpDownController")
public class DrawingFileUpDownController {

	@Resource(name = "gamDrawingFileIdGnrService")
	EgovIdGnrService gamDrawingFileIdGnrService;

	@Resource(name = "multipartResolver")
	CommonsMultipartResolver multipartResolver;

    private static final Logger LOG = Logger.getLogger(DrawingFileUpDownController.class.getName());

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/upload/dwgUploadMulti.do")
	public @ResponseBody Map multipartProcess(final HttpServletRequest request, Model model)
			throws Exception {

		Map map = new HashMap();
		final long startTime = System.nanoTime();

		/*
		 * validate request type
		 */
		Assert.state(request instanceof MultipartHttpServletRequest,
				"request !instanceof MultipartHttpServletRequest");
		final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

		/*
		 * validate text input
		 */
		if(request.getParameter("type") != null) {
		Assert.state(request.getParameter("type").equals("genericFileMulti"),
				"type != genericFileMulti");
		}

		/*
		 * extract files
		 */
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		Assert.notNull(files, "files is null");
		Assert.state(files.size() > 0, "0 files exist");

		/*
		 * process files
		 */
		String uploadPath = EgovProperties.getProperty("drawing.fileStorePath");
		File saveFolder = new File(uploadPath);

		// 디렉토리 생성
		if (!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.mkdirs();
		}

		Iterator<Entry<String, MultipartFile>> itr = files.entrySet()
				.iterator();
		MultipartFile file;
		List fileInfoList = new ArrayList();
		String filePath;

		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();
//			System.out.println("[" + entry.getKey() + "]");

			file = entry.getValue();
			String[] tokens = file.getOriginalFilename().split("\\.(?=[^\\.]+$)");
			if (!"".equals(file.getOriginalFilename())) {
				FileInfoVO fileInfoVO = new FileInfoVO();
				fileInfoVO.setFileName(gamDrawingFileIdGnrService.getNextStringId()+"."+tokens[1]);
				fileInfoVO.setOrizinalFileName(file.getOriginalFilename());
				filePath = uploadPath + "\\" + fileInfoVO.getFileName();
				file.transferTo(new File(filePath));

				fileInfoVO.setFilePath("");
				fileInfoVO.setFileSize(file.getSize());
				fileInfoList.add(fileInfoVO);
			}
		}

		final long estimatedTime = System.nanoTime() - startTime;
//		System.out.println(estimatedTime + " " + getClass().getSimpleName());

		map.put("result", fileInfoList);
//		map.put("uploadPath", fileInfoList);

		return map;

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/upload/dwgUploadMultiIE.do")
	public @ResponseBody Map multipartIEProcess(final HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {

	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");

		Map map = new HashMap();
		final long startTime = System.nanoTime();

		/*
		 * validate request type
		 */
		Assert.state(request instanceof MultipartHttpServletRequest,
				"request !instanceof MultipartHttpServletRequest");
		final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

		/*
		 * validate text input
		 */
		if(request.getParameter("type") != null) {
		Assert.state(request.getParameter("type").equals("genericFileMulti"),
				"type != genericFileMulti");
		}

		/*
		 * extract files
		 */
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		Assert.notNull(files, "files is null");
		Assert.state(files.size() > 0, "0 files exist");

		/*
		 * process files
		 */
		String uploadPath = EgovProperties.getProperty("drawing.fileStorePath");
		File saveFolder = new File(uploadPath);

		// 디렉토리 생성
		if (!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.mkdirs();
		}

		Iterator<Entry<String, MultipartFile>> itr = files.entrySet()
				.iterator();
		MultipartFile file;
		List fileInfoList = new ArrayList();
		String filePath;

		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();
//			System.out.println("[" + entry.getKey() + "]");

			file = entry.getValue();
			String[] tokens = file.getOriginalFilename().split("\\.(?=[^\\.]+$)");
			if (!"".equals(file.getOriginalFilename())) {
				FileInfoVO fileInfoVO = new FileInfoVO();
				fileInfoVO.setFileName(gamDrawingFileIdGnrService.getNextStringId()+"."+tokens[1]);
				fileInfoVO.setOrizinalFileName(file.getOriginalFilename());
				filePath = uploadPath + "\\" + fileInfoVO.getFileName();
				file.transferTo(new File(filePath));

				fileInfoVO.setFilePath("");
				fileInfoVO.setFileSize(file.getSize());
				fileInfoList.add(fileInfoVO);
			}
		}

		final long estimatedTime = System.nanoTime() - startTime;
//		System.out.println(estimatedTime + " " + getClass().getSimpleName());

		map.put("result", fileInfoList);
//		map.put("uploadPath", fileInfoList);

		return map;

	}

	@RequestMapping(value = "/download/dwgDownloadFile.do")
	public void downloadFile(
			@RequestParam(value = "requestedFile") String requestedFile,
			@RequestParam(value = "downloadFileName") String downloadFileName,
			HttpServletResponse response) throws Exception {

		String uploadPath = EgovProperties.getProperty("drawing.fileStorePath");

		File uFile = new File(uploadPath, requestedFile);
		int fSize = (int) uFile.length();
		BufferedInputStream in=null;

		if (fSize > 0) {
			try {
				in = new BufferedInputStream(
						new FileInputStream(uFile));
				// String mimetype = servletContext.getMimeType(requestedFile);
				String mimetype = "text/html";

				response.setBufferSize(fSize);
				response.setContentType(mimetype);
				response.setHeader("Content-Disposition", "attachment; filename=\""
						+ downloadFileName + "\"");
				response.setContentLength(fSize);

				FileCopyUtils.copy(in, response.getOutputStream());
				response.getOutputStream().flush();
				response.getOutputStream().close();
			} catch (IOException i){
				Logger.getLogger(EgovProperties.class).debug("IGNORED: " + i.getMessage());
			}
			catch(Exception e) {
				LOG.error("drawing download error : "+e.getMessage());
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
					+ requestedFile + "</h2>");
			printwriter
					.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
			printwriter.println("<br><br><br>&copy; webAccess");
			printwriter.println("</html>");
			printwriter.flush();
			printwriter.close();
		}
	}

	/*
     * 이미지 파일에 대한 미리보기 기능을 제공한다.
     *
     * @param atchFileId
     * @param fileSn
     * @param sessionVO
     * @param model
     * @param response
     * @throws Exception
     */
/*    @RequestMapping("/cmm/getDwgImage.do")
    public void getImage(final HttpServletRequest request, HttpServletResponse response) throws Exception {

		//@RequestParam("atchFileId") String atchFileId,
		//@RequestParam("fileSn") String fileSn,
		String physicalFileNm = (String)request.getParameter("physicalFileNm");
//		String fileSn = (String)commandMap.get("fileSn");
		String uploadPath = EgovProperties.getProperty("drawing.fileStorePath");
		String[] tokens = physicalFileNm.split("\\.(?=[^\\.]+$)");

		FileInfoVO vo = new FileInfoVO();

		vo.setFileName(physicalFileNm);

		File file = null;
		FileInputStream fis = null;

		BufferedInputStream in = null;
		ByteArrayOutputStream bStream = null;

		try {
		    file = new File(uploadPath, vo.getFileName());
		    fis = new FileInputStream(file);

		    in = new BufferedInputStream(fis);
		    bStream = new ByteArrayOutputStream();

		    int imgByte;
		    while ((imgByte = in.read()) != -1) {
			bStream.write(imgByte);
		    }

			String type = "";

			if (tokens[1] != null && !"".equals(tokens[1])) {
			    if ("jpg".equals(tokens[1].toLowerCase())) {
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
		} finally {
			if (bStream != null) {
				try {
					bStream.close();
				} catch (Exception ignore) {
					//System.out.println("IGNORE: " + ignore);
					LOG.debug("IGNORE: " + ignore.getMessage());
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (Exception ignore) {
					//System.out.println("IGNORE: " + ignore);
					LOG.debug("IGNORE: " + ignore.getMessage());
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception ignore) {
					//System.out.println("IGNORE: " + ignore);
					LOG.debug("IGNORE: " + ignore.getMessage());
				}
			}
		}
    }*/

}

