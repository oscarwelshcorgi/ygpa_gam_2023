package egovframework.rte.ygpa.gam.cmmn.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.service.EgovProperties;

/**
 * 용자 관리 컨트롤러
 * @author EUNSUNGJ
 * @since 2015. 1. 8.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 1. 8.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Controller
public class GamFileDownloadController {

	/**
	 * 서버 파일에 대하여 다운로드를 처리한다.
	 *
	 * @param response
	 * @param streFileNm 파일저장 경로가 포함된 형태
	 * @param orignFileNm
	 * @throws Exception
	 */
	@RequestMapping(value = "/downloadFile.do")
	public void downloadFile(@RequestParam(value = "requestedFile") String requestedFile,HttpServletResponse response) throws Exception {
			
		 response.setContentType("application/x-msdownload");
	
	
		String filename = "test.hwp";
		String path = EgovProperties.getPathProperty("fileStorePath");
		
		response.setHeader("Content-Disposition","attachment; filename="+filename);
		File file = new File (path+filename);
		byte[] bytestream = new byte[(int)file.length()];
		
		FileInputStream filestream = new FileInputStream(file);
		int i = 0, j = 0;
		
		while((i = filestream.read()) != -1) {
		bytestream[j] = (byte)i;
		j++;
		}
		// 응답 스트림 객체를 생성한다.
		OutputStream outStream = response.getOutputStream();
		// 응답 스트림에 파일 바이트 배열을 쓴다.
		outStream.write(bytestream);
		outStream.close();
		
		
		/*		String downFileName = streFileNm;
		String orgFileName = orignFileNm;

		File file = new File(downFileName);
		
		if (!file.exists()) {
			throw new FileNotFoundException(downFileName);
		}

		if (!file.isFile()) {
			throw new FileNotFoundException(downFileName);
		}

		int fSize = (int) file.length();
		if (fSize > 0) {
			BufferedInputStream in = null;

			try {
				in = new BufferedInputStream(new FileInputStream(file));

				String mimetype = "application/x-msdownload";

				//response.setBufferSize(fSize);
				response.setContentType(mimetype);
				response.setHeader("Content-Disposition:", "attachment; filename=" + orgFileName);
				response.setContentLength(fSize);
				//response.setHeader("Content-Transfer-Encoding","binary");
				//response.setHeader("Pragma","no-cache");
				//response.setHeader("Expires","0");
				FileCopyUtils.copy(in, response.getOutputStream());
			} finally {
				close(in);
			}
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
    }
	
	public static void close(Closeable  ... resources) {
		for (Closeable resource : resources) {
			if (resource != null) {
				try {
					resource.close();
				} catch (Exception e) {
					e.getMessage();
				}
			}
		}
	}*/
	}
}