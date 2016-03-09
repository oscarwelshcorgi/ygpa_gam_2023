/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 
 * @author Jongmin
 * @since 2016. 2. 20.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   아래한글 출력을 위한 공통 메소드를 묶어놓은 최상위 클래스
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 2. 20.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamFcltyQcHwpBaseReport {
	private Map<String, Integer> fileIndexInfo;
	
	public GamFcltyQcHwpBaseReport(Map<String, Integer> fileIndexInfo) {
		this.fileIndexInfo = fileIndexInfo;
	}
			
	// 결과항목코드로 점검결과기호 얻기
	protected String getResultItemSymbol(List<EgovMap> qcResultItemList, String itemCd) {
		String result = "";
		if(qcResultItemList != null) {
			for(EgovMap qcResultItem : qcResultItemList) {
				if(itemCd.equals(qcResultItem.get("qcItemCd"))){
					if(qcResultItem.get("inspResultChk") != null) {
						String value = (String) qcResultItem.get("inspResultChk");
						if(value.equals("N")) {
							result = "○";
						} else if(value.equals("W")) {
							result = "△";
						} else if(value.equals("X")) {
							result = "×";
						} else {
							result = "";
						}
					}
					break;
				};
			}
		}
		return result;
	}
	
	// 결과항목코드로 점검결과이름 얻기
	protected String getResultItemName(List<EgovMap> qcResultItemList, String itemCd) {
		String result = "";
		if(qcResultItemList != null) {
			for(EgovMap qcResultItem : qcResultItemList) {
				if(itemCd.equals(qcResultItem.get("qcItemCd"))){
					result = (qcResultItem.get("qcItemNm") != null) ? (String) qcResultItem.get("qcItemNm") : "";
					break;
				};
			}
		}
		return result;
	}

	// 결과항목코드로 점검결과내용 얻기(토목일 경우에만 사용)
	protected String getResultItemContent(List<EgovMap> qcResultItemList, String itemCd) {
		String result = "";
		if(qcResultItemList != null) {
			for(EgovMap qcResultItem : qcResultItemList) {
				if(itemCd.equals(qcResultItem.get("qcItemCd"))){
					result = (qcResultItem.get("inspResultCn") != null) ? (String) qcResultItem.get("inspResultCn") : "";
					break;
				};
			}
		}
		return result;
	}
	
	// 파일명에 대한 index id 얻기
	protected int getBinId(String fileName) {
		int result = 0;
		Iterator<String> it = fileIndexInfo.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			if(key.equals(fileName)) {
				result = fileIndexInfo.get(key);
				break;
			}
		}
		return result;
	}
	
	// HEAD에 들어갈 파일 정보 엘리먼트 구성
	protected StringBuilder getBinItemListElement() {
		StringBuilder result = new StringBuilder();
		StringBuilder buffer = new StringBuilder();
		int count = 0;
		
		Iterator<String> it = fileIndexInfo.keySet().iterator();
		while(it.hasNext()) {
			count++;
			String fileName = it.next();
			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toUpperCase();
			buffer.append("<BINITEM BinData=\"" + count + "\" Format=\"" + fileExt + "\" Type=\"Embedding\"/>\n");
			fileIndexInfo.put(fileName, count);
		}
		
		if(count > 0) {
			result.append("<BINDATALIST Count=\"" + count + "\">\n" + buffer + "</BINDATALIST>\n");
		} else {
			result.append("");
		}
		return result;
	}
	
	// TAIL에 들어갈 파일 내용 앨리먼트 구성
	protected StringBuilder getBinDataListElement() throws Exception {
		StringBuilder result = new StringBuilder();
		StringBuilder buffer = new StringBuilder();
		int count = 0;

		Iterator<String> it = fileIndexInfo.keySet().iterator();
		while(it.hasNext()) {
			count++;
			String fileName = it.next();
			int index = fileIndexInfo.get(fileName);
;				String fullFileName = EgovProperties.getProperty("prtfclty.fileStorePath") + fileName;
			String encBase64 = fileToBase64(fullFileName);
			buffer.append("<BINDATA Encoding=\"Base64\" Id=\"" + index + "\" Size=\"" + encBase64.length() + "\">"  + encBase64 +  "</BINDATA>\n");
		}
		
		if(count > 0) {
			result.append("<BINDATASTORAGE>\n" + buffer + "</BINDATASTORAGE>\n");
		} else {
			result.append("");
		}
		return result;
	}
	
	//파일을  BASE64엔코딩 문자열로 변환
	protected String fileToBase64(String fileName) throws Exception {
		FileInputStream fis = new FileInputStream(fileName);
		long fileSize = fis.getChannel().size();
		byte[] fileData = new byte[(int) fileSize];
		fis.read(fileData);
		fis.close();
		return new String(Base64.encodeBase64(fileData));
	}
}
