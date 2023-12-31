/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

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

	// 결과코드로 점검결과기호 얻기
	protected String getResultItemSymbol(String resultChk) {
		String result = "";
		if(resultChk.equals("N")) {
			result = "○";
		} else if(resultChk.equals("W")) {
			result = "△";
		} else if(resultChk.equals("X")) {
			result = "×";
		} else {
			result = "";
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
	
	//점검상위항목 리스트 구하기
	protected List<HashMap<String, String>> getQcUpperItemList(List<EgovMap> qcResultItemList) {
		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String qcItemUpperCd = "";
		if(qcResultItemList != null) {
			for(EgovMap qcResultItem : qcResultItemList) {
				if(!qcItemUpperCd.equals((String)qcResultItem.get("qcItemUpperCd"))){
					qcItemUpperCd = (String)qcResultItem.get("qcItemUpperCd");
					String qcItemUpperNm = (String)qcResultItem.get("qcItemUpperNm");
					HashMap<String, String> item = new HashMap<String, String>();
					item.put("qcItemUpperCd", qcItemUpperCd);
					item.put("qcItemUpperNm", qcItemUpperNm);
					result.add(item);
				};
			}
		}
		return result;
	}
	
	//기계점검상위항목 분류 리스트 구하기
	protected List<HashMap<String, String>> getCraneMechQcUpperItemList(List<HashMap<String, String>> qcUpperItemList, String startCode) {
		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		if(qcUpperItemList != null) {
			for(HashMap<String, String> qcUpperItem : qcUpperItemList) {
				String qcItemUpperCd = (String)qcUpperItem.get("qcItemUpperCd");
				if(qcItemUpperCd.startsWith(startCode)) {
					result.add(qcUpperItem);
				}
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
		FileInputStream fis = null; 
		long fileSize = 0;
		byte[] fileData = null;
		try {
			fis = new FileInputStream(fileName);
			fileSize = fis.getChannel().size();
			fileData = new byte[(int) fileSize];
		} catch(IOException e) {
			Logger.getLogger(EgovProperties.class).debug("IGNORED: " + e.getMessage());
		} finally {
			fis.read(fileData);
			fis.close();
		}
		
		return new String(Base64.encodeBase64(fileData));
	}
	
	
	//기계항만부잔교 재배열 출력리스트 생성
	protected List<HashMap<String, String>> getFloatingPierPrintList(List<HashMap<String, String>> qcItemUpperList, List<EgovMap> qcResultItemList) {
		List<HashMap<String, String>> resultList = new ArrayList<HashMap<String,String>>();
		
		for(HashMap<String, String> itemUpper : qcItemUpperList) {
			HashMap<String, String> displayItem = new HashMap<String, String>();
			String itemUpperCd = itemUpper.get("qcItemUpperCd");
			String itemUpperNm = itemUpper.get("qcItemUpperNm");
			displayItem.put("qcItemUpperCd", itemUpperCd);
			displayItem.put("qcItemUpperNm", itemUpperNm);
			boolean displayFlag = false;
			for(EgovMap resultItem : qcResultItemList) {
				String resultItemUpperCd = (String) resultItem.get("qcItemUpperCd");
				if(resultItemUpperCd.equals(itemUpperCd)) {
					String resultItemNm = (String) resultItem.get("qcItemNm");
					String resultItemInspChk = (String) resultItem.get("inspResultChk");
					displayItem.put(resultItemNm, resultItemInspChk);
					if(!resultItemInspChk.equals("E")) {
						displayFlag = true;
					}
				}
			}
			if(displayFlag) resultList.add(displayItem);
		}
		return resultList;
	}
	
	//기계항만부잔교 출력리스트에서 점검결과 가져오기
	protected String getFloatingPierInspResultChk(List<HashMap<String, String>> printItemList, String qcItemUpperCd, String qcItemNm) {
		String result = "";
		for(HashMap<String, String> item : printItemList) {
			String itemUpperCd = item.get("qcItemUpperCd");
			if(itemUpperCd.equals(qcItemUpperCd)) {
				result = item.get(qcItemNm);
				break;
			}
		}
		return result;
	}
}
