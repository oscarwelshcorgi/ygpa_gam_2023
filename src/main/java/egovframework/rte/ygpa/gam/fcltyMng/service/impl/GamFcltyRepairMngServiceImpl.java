/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyRepairMngService;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyRepairMngVO;

/**
 * 
 * @author HNJ
 * @since 2014. 12. 01.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 01.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamFcltyRepairMngService")
public class GamFcltyRepairMngServiceImpl extends AbstractServiceImpl implements GamFcltyRepairMngService {
	
	@Resource(name="gamFcltyRepairMngDao")
	private GamFcltyRepairMngDao gamFcltyRepairMngDao;

	/**
	 * 하자보수 내역 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectFcltyRepairMngList(GamFcltyRepairMngVO vo) throws Exception {
		return (List<?>)gamFcltyRepairMngDao.selectFcltyRepairMngList(vo);
	}

	
	/**
	 * 하자보수 내역 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public GamFcltyRepairMngVO selectFcltyRepairMngListTotCnt(GamFcltyRepairMngVO vo) throws Exception {
		return gamFcltyRepairMngDao.selectFcltyRepairMngListTotCnt(vo);
	}
	
	
	/**
	 * 하자보수상세내역 조회
	 * @param searchVO
	 * @return map
	 * @throws Exception
	 */
	public EgovMap selectFcltyRepairMngDetail(GamFcltyRepairMngVO vo) throws Exception{
		return gamFcltyRepairMngDao.selectFcltyRepairMngDetail(vo);
	}
	
	
	
	/**
	 * 하자보수 대상시설물 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectFlawRprObjFcltsFList(GamFcltyRepairMngVO vo) throws Exception {
		return (List<?>)gamFcltyRepairMngDao.selectFlawRprObjFcltsFList(vo);
	}
	
	
	/**
	 * 하자보수 대상시설물 총갯수
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectFlawRprObjFcltsFListTotCnt(GamFcltyRepairMngVO vo) throws Exception {
		return gamFcltyRepairMngDao.selectFlawRprObjFcltsFListTotCnt(vo);
	}

	
	/**
	 * 하자보수 첨부파일 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectFcltyRepairFileList(GamFcltyRepairMngVO vo) throws Exception {
		return (List<?>)gamFcltyRepairMngDao.selectFcltyRepairFileList(vo);
	}
	
	
	/**
	 * 하자보수내역 입력
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void insertFcltyRepairMng(Map insertRprData, List insertObjList, List insertFileList) throws Exception{
		String fcltsMngGroupNo = (String) insertRprData.get("fcltsMngGroupNo");
		String fcltsJobSe = (String) insertRprData.get("fcltsJobSe");
		int flawRprSeq = gamFcltyRepairMngDao.selectNextMntnRprSeq(insertRprData);
		Map insertObj = null;
		Map insertFile = null;
		
		insertRprData.put("flawRprSeq",flawRprSeq);
		gamFcltyRepairMngDao.insertFcltyRepairMng(insertRprData);
		
		// 하자보수 대상시설물 입력처리
		for(int i=0;i<insertObjList.size();i++){
			insertObj = (Map) insertObjList.get(i);
			insertObj.put("fcltsMngGroupNo",fcltsMngGroupNo);
			insertObj.put("fcltsJobSe",fcltsJobSe);
			insertObj.put("flawRprSeq",flawRprSeq);
			insertObj.put("regUsr", insertRprData.get("regUsr"));
			gamFcltyRepairMngDao.insertFlawRprObjFcltsF(insertObj);
		}

		// 하자보수 첨부파일 입력처리
		for(int i=0;i<insertFileList.size();i++){
			insertFile = (Map) insertFileList.get(i);
			insertFile.put("fcltsMngGroupNo",fcltsMngGroupNo);
			insertFile.put("fcltsJobSe",fcltsJobSe);
			insertFile.put("flawRprSeq",flawRprSeq);
			insertFile.put("regUsr", insertRprData.get("regUsr"));
			gamFcltyRepairMngDao.insertFcltyRepairFile(insertFile);
		}
	}
	
	
	/**
	 * 하자보수내역 수정
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateFcltyRepairMng(Map updateRprData, Map updateObj, List updateFileList,List deleteRepairFileList) throws Exception{
		String fcltsMngGroupNo = (String) updateRprData.get("fcltsMngGroupNo");
		String fcltsJobSe = (String) updateRprData.get("fcltsJobSe");
		String flawRprSeq = (String) updateRprData.get("flawRprSeq");

		Map insertFile = null;

		gamFcltyRepairMngDao.updateFcltyRepairMng(updateRprData);
		
		// 하자보수 대상시설물 입력처리
		gamFcltyRepairMngDao.mergeFlawRprObjFcltsF(updateObj);
		
		

		for(int i=0;i<updateFileList.size();i++){
			insertFile = (Map) updateFileList.get(i);
			insertFile.put("fcltsMngGroupNo",fcltsMngGroupNo);
			insertFile.put("fcltsJobSe",fcltsJobSe);
			insertFile.put("flawRprSeq",flawRprSeq);
			insertFile.put("regUsr", updateRprData.get("regUsr"));
			gamFcltyRepairMngDao.insertFcltyRepairFile(insertFile);
		}
	}
	
	public void deleteFcltyRepairMngList(GamFcltyRepairMngVO deleteFileVO) throws Exception{
	gamFcltyRepairMngDao.deleteFcltyRepairFile(deleteFileVO);
	}
	/**
	 * 하자보수내역 삭제
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public void deleteFcltyRepairMng(Map<?,?> vo) throws Exception{
		gamFcltyRepairMngDao.deleteFcltyRepairMng(vo);
		gamFcltyRepairMngDao.deleteFlawRprObjFcltsF(vo);
	//	gamFcltyRepairMngDao.deleteFcltyRepairFile(vo);
	}

	
	/**
	 * 하자검사조서인쇄
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public EgovMap selectFcltyRepairCheckReport(GamFcltyRepairMngVO vo) throws Exception{
		return (EgovMap)gamFcltyRepairMngDao.selectFcltyRepairCheckReport(vo);
	}
	
	
	/**
	 * 하자검사관리대장인쇄
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public EgovMap selectFcltyRepairCheckMng(GamFcltyRepairMngVO vo) throws Exception{
		return (EgovMap)gamFcltyRepairMngDao.selectFcltyRepairCheckMng(vo);
	}
	
	
	/**
	 *  계약당 하자보증 내역 인쇄
	 * @param  String
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectFcltyRepairMngListPerCtrt(String vo) throws Exception {
		return (List<?>)gamFcltyRepairMngDao.selectFcltyRepairMngListPerCtrt(vo);
	}
	
	
	/**
	 * 계약당 계약당 하자보증 내역 총갯수
	 * @param String
	 * @return int
	 * @throws Exception
	 */
	public int selectFcltyRepairMngListPerCtrtTotalCnt(String vo) throws Exception {
		return gamFcltyRepairMngDao.selectFcltyRepairMngListPerCtrtTotalCnt(vo);
	}


	public EgovMap selectFcltyRepairCheckReportCharger(GamFcltyRepairMngVO GamFcltyRepairMngVO) throws Exception{
		return (EgovMap)gamFcltyRepairMngDao.selectFcltyRepairCheckReportCharger(GamFcltyRepairMngVO);
	}

	/**
	 * 선택된 하자 검수 조서 아래한글 XML 문자열 서비스
	 * @param String
	 * @return int
	 * @throws Exception
	 */
	
	@SuppressWarnings({ "unused", "unchecked" })
	public String selectFcltyRepairCheckReportListHWPML(List<HashMap<String,String>> reportList) throws Exception {
    	StringBuilder result =  new StringBuilder(); //HWPML 처리 문자열 버퍼
    	Map<String, Integer> imageIndexes = new HashMap<String, Integer>(); //이미지 파일명과 id구성을 위한 맵
    	Map<String, Boolean> signImageYN = new HashMap<String, Boolean>(); //이미지 파일명과 도장이미지인지(true) 아닌지(false)
    	
    	//HWPML용 인스턴스 생성
    	MakeFcltyRepairCheckReportHWPML reportHWP = new MakeFcltyRepairCheckReportHWPML();
    	
    	//HWPML Start Element 부분
		result.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>\n");
		result.append("<HWPML Style=\"embed\" SubVersion=\"7.0.0.0\" Version=\"2.7\">\n");
		
		for(int i=0; i<reportList.size(); i++) {
			Map<String, String> reportItem = reportList.get(i);
			
			GamFcltyRepairMngVO searchVO = new GamFcltyRepairMngVO();
			searchVO.setFcltsMngGroupNo(reportItem.get("fcltsMngGroupNo"));
			searchVO.setFlawExamUsr(reportItem.get("flawExamUsr"));
			searchVO.setFcltsJobSe(reportItem.get("fcltsJobSe"));
			searchVO.setFlawRprSeq(reportItem.get("flawRprSeq"));

	    	EgovMap[] chargers = null;
			if(searchVO.getFlawExamUsr() == null) {
				searchVO.setFlawExamUsr("");
			} 
			String[] users = searchVO.getFlawExamUsr().split(",");
			if(users.length > 0) {
				chargers = new EgovMap[users.length];
				for(int j=0; j<users.length; j++) {
					searchVO.setFlawExamUsr(users[j].replace(" ", ""));
					chargers[j] = gamFcltyRepairMngDao.selectFcltyRepairCheckReportCharger(searchVO);
				}
				for(int j=0; j<chargers.length; j++) {
					EgovMap charger = chargers[j];
			    	//검사자 도장 이미지 파일 정보 구성
			    	if(charger != null) {
				    	String fileName = (String) charger.get("signFileNmPhysicl");
				    	if((fileName != null) && (fileName.length() > 0)) {
				    		fileName = EgovProperties.getProperty("prtfclty.fileStorePath") + fileName;  //경로 주의 : 도장이미지와 하자사진이미지의 경로가 틀림
				        	File file = new File(fileName);
				        	if(file.exists()) {
				        		imageIndexes.put((String) charger.get("signFileNmPhysicl"), 0);
				        		signImageYN.put((String) charger.get("signFileNmPhysicl"), true);	        		
				        	}    
				    	}
			    	}
				}
			}
			
	    	//하자검사조서
	    	EgovMap report = gamFcltyRepairMngDao.selectFcltyRepairCheckReport(searchVO);
	    	//첨부파일이미지
	    	List fileList = gamFcltyRepairMngDao.selectFcltyRepairPictureFileList(searchVO);
	    		    	
	    	//사진 이미지 파일 정보 구성
	    	for(int j=0; j<fileList.size(); j++) {
	    		EgovMap item = (EgovMap) fileList.get(j);
	        	String fileName = (String) item.get("atchFileNmPhysicl");
	        	if((fileName != null) && (fileName.length() > 0)) {
	        		fileName = EgovProperties.getProperty("repairAttach.fileStorePath") + fileName; //경로 주의 : 도장이미지와 하자사진이미지의 경로가 틀림
	            	File file = new File(fileName);
	            	if(file.exists()) {
	            		imageIndexes.put((String) item.get("atchFileNmPhysicl"), 0);
	            		signImageYN.put((String) item.get("atchFileNmPhysicl"), false);
	            	}    
	        	}
	    	}
		}
		
    	//Head Element 구성 처리
		result.append(reportHWP.getXmlFcltyRepairCheckReportHead(imageIndexes));
		
		//Body Element 구성 처리
		result.append("<BODY><SECTION Id=\"0\">\n");

		for(int i=0; i<reportList.size(); i++) {
			Map<String, String> reportItem = reportList.get(i);
			
			GamFcltyRepairMngVO searchVO = new GamFcltyRepairMngVO();
			searchVO.setFcltsMngGroupNo(reportItem.get("fcltsMngGroupNo"));
			searchVO.setFlawExamUsr(reportItem.get("flawExamUsr"));
			searchVO.setFcltsJobSe(reportItem.get("fcltsJobSe"));
			searchVO.setFlawRprSeq(reportItem.get("flawRprSeq"));
					
	    	EgovMap[] chargers = null;
			if(searchVO.getFlawExamUsr() == null) {
				searchVO.setFlawExamUsr("");
			} 
			String[] users = searchVO.getFlawExamUsr().split(",");
			if(users.length > 0) {
				chargers = new EgovMap[users.length];
				for(int j=0; j<users.length; j++) {
					searchVO.setFlawExamUsr(users[j].replace(" ", ""));
					chargers[j] = gamFcltyRepairMngDao.selectFcltyRepairCheckReportCharger(searchVO);
				}
			}

			//하자검사조서
	    	EgovMap report = gamFcltyRepairMngDao.selectFcltyRepairCheckReport(searchVO);
	    	//첨부파일이미지
	    	List fileList = gamFcltyRepairMngDao.selectFcltyRepairPictureFileList(searchVO);
		
			if(report != null) {
				//하자검사조서 처리
				result.append(reportHWP.getXmlFcltyRepairCheckReportBody(imageIndexes, chargers, report));
				String flawEnnc = (String) report.get("flawEnnc");
				if(flawEnnc != null) {
					if(flawEnnc.equals("Y")) {
						//하자가 있으면 하지 내용과 사진 부분 처리
						result.append(reportHWP.getXmlFcltyRepairCheckReportList(imageIndexes, report, fileList));
					}
				}
			}
		}
		
		result.append("</SECTION></BODY>\n");
		
		//Tail Element 구성 처리
		result.append(reportHWP.getXmlFcltyRepairCheckReportTail(imageIndexes, signImageYN));
		result.append("</HWPML>");
    	
		return result.toString();
	}

	/**
	 * 하자 검수 조서 아래한글 XML 문자열 서비스
	 * @param String
	 * @return int
	 * @throws Exception
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	public String selectFcltyRepairCheckReportHWPML(GamFcltyRepairMngVO searchVO) throws Exception {
    	StringBuilder result =  new StringBuilder(); //HWPML 처리 문자열 버퍼
    	Map<String, Integer> imageIndexes = new HashMap<String, Integer>(); //이미지 파일명과 id구성을 위한 맵
    	Map<String, Boolean> signImageYN = new HashMap<String, Boolean>(); //이미지 파일명과 도장이미지인지(true) 아닌지(false)
    	
    	//HWPML용 인스턴스 생성
    	MakeFcltyRepairCheckReportHWPML reportHWP = new MakeFcltyRepairCheckReportHWPML();
    	
    	//HWPML Start Element 부분
		result.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>\n");
		result.append("<HWPML Style=\"embed\" SubVersion=\"7.0.0.0\" Version=\"2.7\">\n");
		
    	EgovMap[] chargers = null;
		if(searchVO.getFlawExamUsr() == null) {
			searchVO.setFlawExamUsr("");
		} 
		String[] users = searchVO.getFlawExamUsr().split(",");
		if(users.length > 0) {
			chargers = new EgovMap[users.length];
			for(int i=0; i<users.length; i++) {
				searchVO.setFlawExamUsr(users[i].replace(" ", ""));
				chargers[i] = gamFcltyRepairMngDao.selectFcltyRepairCheckReportCharger(searchVO);
			}
			for(int i=0; i<chargers.length; i++) {
				EgovMap charger = chargers[i];
		    	//검사자 도장 이미지 파일 정보 구성
		    	if(charger != null) {
			    	String fileName = (String) charger.get("signFileNmPhysicl");
			    	if((fileName != null) && (fileName.length() > 0)) {
			    		fileName = EgovProperties.getProperty("prtfclty.fileStorePath") + fileName;  //경로 주의 : 도장이미지와 하자사진이미지의 경로가 틀림
			        	File file = new File(fileName);
			        	if(file.exists()) {
			        		imageIndexes.put((String) charger.get("signFileNmPhysicl"), 0);
			        		signImageYN.put((String) charger.get("signFileNmPhysicl"), true);	        		
			        	}    
			    	}
		    	}
			}
		}
		
		//하자검사조서
		EgovMap report = gamFcltyRepairMngDao.selectFcltyRepairCheckReport(searchVO);
		//첨부파일이미지
		List fileList = gamFcltyRepairMngDao.selectFcltyRepairPictureFileList(searchVO);
    	
 
    	//사진 이미지 파일 정보 구성
    	for(int i=0; i<fileList.size(); i++) {
    		EgovMap item = (EgovMap) fileList.get(i);
        	String fileName = (String) item.get("atchFileNmPhysicl");
        	if((fileName != null) && (fileName.length() > 0)) {
        		fileName = EgovProperties.getProperty("repairAttach.fileStorePath") + fileName; //경로 주의 : 도장이미지와 하자사진이미지의 경로가 틀림
            	File file = new File(fileName);
            	if(file.exists()) {
            		imageIndexes.put((String) item.get("atchFileNmPhysicl"), 0);
            		signImageYN.put((String) item.get("atchFileNmPhysicl"), false);
            	}    
        	}
    	}
    	
    	//Head Element 구성 처리
		result.append(reportHWP.getXmlFcltyRepairCheckReportHead(imageIndexes));
		
		//Body Element 구성 처리
		result.append("<BODY><SECTION Id=\"0\">\n");
		
		if(report != null) {
			//하자검사조서 처리
			result.append(reportHWP.getXmlFcltyRepairCheckReportBody(imageIndexes, chargers, report));
			String flawEnnc = (String) report.get("flawEnnc");
			if(flawEnnc != null) {
				if(flawEnnc.equals("Y")) {
					//하자가 있으면 하지 내용과 사진 부분 처리
					result.append(reportHWP.getXmlFcltyRepairCheckReportList(imageIndexes, report, fileList));
				}
			}
		}
		
		result.append("</SECTION></BODY>\n");
		
		//Tail Element 구성 처리
		result.append(reportHWP.getXmlFcltyRepairCheckReportTail(imageIndexes, signImageYN));
		result.append("</HWPML>");
    	
		return result.toString();
	}

	/*******
	 * 여기서 부터는 HWPML의 엘리먼트를 구성하기 위한 INNER CLASS
	 */
	
	class MakeFcltyRepairCheckReportHWPML {
		protected int instanceId = 2038414160;
		protected int zOrder = 0;
	
		/** InstId속성와 ZOrder 쓰는 엘리먼트들의 값을 변경시킨다.*/
		protected int getInstanceId() {
			return instanceId++;
		}
		protected int getZOrder() {
			return zOrder++;
		}
		
		/**파일을  BASE64엔코딩 문자열로 변환시킨다 */
		protected String fileToBase64(String fileName) throws Exception {
			FileInputStream fis = new FileInputStream(fileName);
			long fileSize = fis.getChannel().size();
			byte[] fileData = new byte[(int) fileSize];
			fis.read(fileData);
			fis.close();
			return new String(Base64.encodeBase64(fileData));
		}
		
		/** 이미지 파일명의 id를 검색한다. */
		protected int getImageId(Map<String, Integer> imageIndexes, String fileName) {
			Iterator<String> it = imageIndexes.keySet().iterator();
			while(it.hasNext()) {
				if(it.next().equals(fileName)) {
					return imageIndexes.get(fileName);
				}
			}
			return 0;
		}
		
		/** 하자검사원 직급과 이름을 공백에 맞추어 리턴*/
		protected String getChargerNmString(EgovMap charger) {
			String chargerNm = "", chargerOfcPos = ""; 
			if(charger != null) {
				chargerNm = (charger.get("chargerNm") != null) ? (String)charger.get("chargerNm") : ""; //검사자  이름
				chargerOfcPos = (charger.get("chargerOfcPos") != null) ? (String)charger.get("chargerOfcPos") : ""; //검사자 직급
				// 이름 사이에 공백넣기 루틴
				switch(chargerNm.length()) {
				case 0 :
					chargerNm = "        ";
					break;
				case 1 : 
					chargerNm = "   " + chargerNm + "   ";
					break;
				case 2 : 
					chargerNm = chargerNm.substring(0, 1) + "    " + chargerNm.substring(1, 2);
					break;
				case 3 : 
					chargerNm = chargerNm.substring(0, 1) + " " + chargerNm.substring(1, 2) + " " + chargerNm.substring(2, 3);
					break;
				}
			}
			return chargerOfcPos + " " + chargerNm;
		}
		
		/**HWPML 용 하자검사조서 HEAD 엘리먼트 구성을 문자열로 가져온다.*/
		public StringBuilder getXmlFcltyRepairCheckReportHead(Map<String, Integer> imageIndexes) {
			StringBuilder sb = new StringBuilder();
			Iterator<String> it = imageIndexes.keySet().iterator();
			int id = 0, count = imageIndexes.keySet().size();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM일 dd일 HH시 mm분 ss초", Locale.KOREA);
			String today = formatter.format(new Date());
			
			sb.append("<HEAD SecCnt=\"1\">\n");
			sb.append("<DOCSUMMARY><AUTHOR>YGPA GIS Assets Management System</AUTHOR><DATE>" + today + "</DATE></DOCSUMMARY>\n");
			sb.append("<DOCSETTING><BEGINNUMBER Endnote=\"1\" Equation=\"1\" Footnote=\"1\" Page=\"1\" Picture=\"1\" Table=\"1\"/><CARETPOS List=\"1\" Para=\"0\" Pos=\"0\"/></DOCSETTING>\n");
			sb.append("<MAPPINGTABLE>\n");
			if(count > 0) {
				sb.append("<BINDATALIST Count=\""+count+"\">\n");
				while(it.hasNext()) {
					String fileName = it.next();
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
					id++;
					imageIndexes.put(fileName, id);
					sb.append("<BINITEM BinData=\"" + id + "\" Format=\"" + fileExt + "\" Type=\"Embedding\"/>\n");
				}
				sb.append("</BINDATALIST>\n");
			}
			sb.append("<FACENAMELIST>\n");
			sb.append("<FONTFACE Count=\"3\" Lang=\"Hangul\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"휴먼명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE>\n");
			sb.append("<FONTFACE Count=\"3\" Lang=\"Latin\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"HCI Poppy\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE>\n");
			sb.append("<FONTFACE Count=\"3\" Lang=\"Hanja\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE>\n");
			sb.append("<FONTFACE Count=\"3\" Lang=\"Japanese\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE>\n");
			sb.append("<FONTFACE Count=\"3\" Lang=\"Other\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE>\n");
			sb.append("<FONTFACE Count=\"3\" Lang=\"Symbol\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE>\n");
			sb.append("<FONTFACE Count=\"3\" Lang=\"User\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE>\n");
			sb.append("</FACENAMELIST>\n");

			sb.append("<BORDERFILLLIST Count=\"19\">\n");
			sb.append("<BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"1\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"None\" Width=\"0.1mm\"/><RIGHTBORDER Type=\"None\" Width=\"0.1mm\"/><TOPBORDER Type=\"None\" Width=\"0.1mm\"/><BOTTOMBORDER Type=\"None\" Width=\"0.1mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.1mm\"/><FILLBRUSH><WINDOWBRUSH Alpha=\"0\" FaceColor=\"4294967295\" HatchColor=\"4278190080\"/></FILLBRUSH></BORDERFILL>\n");
			sb.append("<BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"2\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL>\n");
			sb.append("<BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"3\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.4mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.4mm\"/></BORDERFILL>\n");
			sb.append("<BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"4\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.4mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.4mm\"/></BORDERFILL>\n");
			sb.append("<BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"5\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.4mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.4mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL>\n");
			sb.append("<BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"6\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.4mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.4mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.4mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.4mm\"/></BORDERFILL>\n");
			sb.append("<BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"7\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.4mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.4mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><FILLBRUSH><GRADATION Alpha=\"0\" Angle=\"0\" CenterX=\"50\" CenterY=\"50\" ColorNum=\"2\" Step=\"50\" StepCenter=\"50\" Type=\"Linear\"><COLOR Value=\"16777215\"/><COLOR Value=\"12632256\"/></GRADATION></FILLBRUSH></BORDERFILL>\n");
			sb.append("<BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"8\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.4mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.4mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><FILLBRUSH><GRADATION Alpha=\"0\" Angle=\"0\" CenterX=\"50\" CenterY=\"50\" ColorNum=\"2\" Step=\"50\" StepCenter=\"50\" Type=\"Linear\"><COLOR Value=\"16777215\"/><COLOR Value=\"12632256\"/></GRADATION></FILLBRUSH></BORDERFILL>\n");
			sb.append("<BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"9\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.4mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.4mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.4mm\"/></BORDERFILL>\n");
			sb.append("<BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"10\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.4mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.1mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.4mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL>\n");
			sb.append("<BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"11\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.1mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.4mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.4mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL>\n");
			sb.append("<BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"12\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.4mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.1mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL>\n");
			sb.append("<BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"13\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.1mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.4mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL>\n");
			sb.append("<BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"14\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.4mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.1mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.4mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL>\n");
			sb.append("<BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"15\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.1mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.4mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.4mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL>\n");
			sb.append("<BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"16\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.4mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.1mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL>\n");
			sb.append("<BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"17\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.1mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.4mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL>\n");
			sb.append("<BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"18\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.4mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.1mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.4mm\"/></BORDERFILL>\n");
			sb.append("<BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"19\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.1mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.4mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.4mm\"/></BORDERFILL>\n");
			sb.append("</BORDERFILLLIST>\n");

			sb.append("<CHARSHAPELIST Count=\"20\">\n");
			sb.append("<CHARSHAPE BorderFillId=\"0\" Height=\"1200\" Id=\"0\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"2\" Hanja=\"2\" Japanese=\"2\" Latin=\"2\" Other=\"2\" Symbol=\"2\" User=\"2\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE>\n");
			sb.append("<CHARSHAPE BorderFillId=\"0\" Height=\"1200\" Id=\"1\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"2\" Hanja=\"2\" Japanese=\"2\" Latin=\"2\" Other=\"2\" Symbol=\"2\" User=\"2\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"-6\" Hanja=\"-6\" Japanese=\"-6\" Latin=\"-6\" Other=\"-6\" Symbol=\"-6\" User=\"-6\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE>\n");
			sb.append("<CHARSHAPE BorderFillId=\"0\" Height=\"1100\" Id=\"2\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"2\" Hanja=\"2\" Japanese=\"2\" Latin=\"2\" Other=\"2\" Symbol=\"2\" User=\"2\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE>\n");
			sb.append("<CHARSHAPE BorderFillId=\"0\" Height=\"1000\" Id=\"3\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE>\n");
			sb.append("<CHARSHAPE BorderFillId=\"0\" Height=\"1000\" Id=\"4\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE>\n");
			sb.append("<CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"5\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE>\n");
			sb.append("<CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"6\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"95\" Hanja=\"95\" Japanese=\"95\" Latin=\"95\" Other=\"95\" Symbol=\"95\" User=\"95\"/><CHARSPACING Hangul=\"-5\" Hanja=\"-5\" Japanese=\"-5\" Latin=\"-5\" Other=\"-5\" Symbol=\"-5\" User=\"-5\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE>\n");
			sb.append("<CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"7\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"95\" Hanja=\"95\" Japanese=\"95\" Latin=\"95\" Other=\"95\" Symbol=\"95\" User=\"95\"/><CHARSPACING Hangul=\"-5\" Hanja=\"-5\" Japanese=\"-5\" Latin=\"-5\" Other=\"-5\" Symbol=\"-5\" User=\"-5\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE>\n");
			sb.append("<CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"8\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE>\n");
			sb.append("<CHARSHAPE BorderFillId=\"0\" Height=\"2000\" Id=\"9\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE>\n");
			sb.append("<CHARSHAPE BorderFillId=\"0\" Height=\"2000\" Id=\"10\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><BOLD/><UNDERLINE Color=\"0\" Shape=\"Solid\" Type=\"Bottom\"/></CHARSHAPE>\n");
			sb.append("<CHARSHAPE BorderFillId=\"0\" Height=\"1600\" Id=\"11\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE>\n");
			sb.append("<CHARSHAPE BorderFillId=\"0\" Height=\"1500\" Id=\"12\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE>\n");
			sb.append("<CHARSHAPE BorderFillId=\"0\" Height=\"1500\" Id=\"13\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><BOLD/></CHARSHAPE>\n");
			sb.append("<CHARSHAPE BorderFillId=\"0\" Height=\"1500\" Id=\"14\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"-15\" Hanja=\"-15\" Japanese=\"-15\" Latin=\"-15\" Other=\"-15\" Symbol=\"-15\" User=\"-15\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE>\n");
			sb.append("<CHARSHAPE BorderFillId=\"0\" Height=\"1500\" Id=\"15\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"-13\" Hanja=\"-13\" Japanese=\"-13\" Latin=\"-13\" Other=\"-13\" Symbol=\"-13\" User=\"-13\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE>\n");
			sb.append("<CHARSHAPE BorderFillId=\"0\" Height=\"1500\" Id=\"16\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"-13\" Hanja=\"-13\" Japanese=\"-13\" Latin=\"-13\" Other=\"-13\" Symbol=\"-13\" User=\"-13\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><UNDERLINE Color=\"0\" Shape=\"Solid\" Type=\"Bottom\"/></CHARSHAPE>\n");
			sb.append("<CHARSHAPE BorderFillId=\"0\" Height=\"1500\" Id=\"17\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"-1\" Hanja=\"-1\" Japanese=\"-1\" Latin=\"-1\" Other=\"-1\" Symbol=\"-1\" User=\"-1\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1500\" Id=\"18\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"-5\" Hanja=\"-5\" Japanese=\"-5\" Latin=\"-5\" Other=\"-5\" Symbol=\"-5\" User=\"-5\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"2000\" Id=\"19\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><BOLD/></CHARSHAPE>\n");
			sb.append("</CHARSHAPELIST>\n");

			sb.append("<TABDEFLIST Count=\"3\">\n");
			sb.append("<TABDEF AutoTabLeft=\"false\" AutoTabRight=\"false\" Id=\"0\"/>\n");
			sb.append("<TABDEF AutoTabLeft=\"true\" AutoTabRight=\"false\" Id=\"1\"/>\n");
			sb.append("<TABDEF AutoTabLeft=\"false\" AutoTabRight=\"true\" Id=\"2\"/>\n");
			sb.append("</TABDEFLIST>\n");

			sb.append("<NUMBERINGLIST Count=\"1\">\n");
			sb.append("<NUMBERING Id=\"1\" Start=\"0\"><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"1\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^1.</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"2\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^2.</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"3\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^3)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"4\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^4)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"5\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">(^5)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"6\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">(^6)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"7\" NumFormat=\"CircledDigit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^7</PARAHEAD></NUMBERING>\n");
			sb.append("</NUMBERINGLIST>\n");
			
			sb.append("<PARASHAPELIST Count=\"15\">\n");
			sb.append("<PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"0\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"true\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"1\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE>\n");
			sb.append("<PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"1\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE>\n");
			sb.append("<PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"2\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"-4416\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE>\n");
			sb.append("<PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"3\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"100\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE>\n");
			sb.append("<PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"4\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"1\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"-2620\" Left=\"0\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"1\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE>\n");
			sb.append("<PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"5\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"1\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE>\n");
			sb.append("<PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"6\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"3000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"1\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE>\n");
			sb.append("<PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"7\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"2000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"1\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE>\n");
			sb.append("<PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"8\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"1\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"4000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"1\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE>\n");
			sb.append("<PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"9\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"2\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"6000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"1\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE>\n");
			sb.append("<PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"10\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"3\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"8000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"1\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE>\n");
			sb.append("<PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"11\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"4\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"10000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"1\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE>\n");
			sb.append("<PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"12\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"5\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"12000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"1\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE>\n");
			sb.append("<PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"13\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"6\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"14000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"1\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE>\n");
			sb.append("<PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"14\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"2\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"150\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"1\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE>\n");
			sb.append("</PARASHAPELIST>\n");
	
			sb.append("<STYLELIST Count=\"14\">\n");
			sb.append("<STYLE CharShape=\"4\" EngName=\"Normal\" Id=\"0\" LangId=\"1042\" LockForm=\"0\" Name=\"바탕글\" NextStyle=\"0\" ParaShape=\"5\" Type=\"Para\"/><STYLE CharShape=\"4\" EngName=\"Body\" Id=\"1\" LangId=\"1042\" LockForm=\"0\" Name=\"본문\" NextStyle=\"1\" ParaShape=\"6\" Type=\"Para\"/><STYLE CharShape=\"4\" EngName=\"Outline 1\" Id=\"2\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 1\" NextStyle=\"2\" ParaShape=\"7\" Type=\"Para\"/><STYLE CharShape=\"4\" EngName=\"Outline 2\" Id=\"3\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 2\" NextStyle=\"3\" ParaShape=\"8\" Type=\"Para\"/><STYLE CharShape=\"4\" EngName=\"Outline 3\" Id=\"4\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 3\" NextStyle=\"4\" ParaShape=\"9\" Type=\"Para\"/><STYLE CharShape=\"4\" EngName=\"Outline 4\" Id=\"5\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 4\" NextStyle=\"5\" ParaShape=\"10\" Type=\"Para\"/><STYLE CharShape=\"4\" EngName=\"Outline 5\" Id=\"6\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 5\" NextStyle=\"6\" ParaShape=\"11\" Type=\"Para\"/><STYLE CharShape=\"4\" EngName=\"Outline 6\" Id=\"7\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 6\" NextStyle=\"7\" ParaShape=\"12\" Type=\"Para\"/><STYLE CharShape=\"4\" EngName=\"Outline 7\" Id=\"8\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 7\" NextStyle=\"8\" ParaShape=\"13\" Type=\"Para\"/><STYLE CharShape=\"3\" EngName=\"Page Number\" Id=\"9\" LangId=\"1042\" LockForm=\"0\" Name=\"쪽 번호\" NextStyle=\"9\" ParaShape=\"5\" Type=\"Para\"/><STYLE CharShape=\"5\" EngName=\"Header\" Id=\"10\" LangId=\"1042\" LockForm=\"0\" Name=\"머리말\" NextStyle=\"10\" ParaShape=\"14\" Type=\"Para\"/><STYLE CharShape=\"6\" EngName=\"Footnote\" Id=\"11\" LangId=\"1042\" LockForm=\"0\" Name=\"각주\" NextStyle=\"11\" ParaShape=\"4\" Type=\"Para\"/><STYLE CharShape=\"6\" EngName=\"Endnote\" Id=\"12\" LangId=\"1042\" LockForm=\"0\" Name=\"미주\" NextStyle=\"12\" ParaShape=\"4\" Type=\"Para\"/><STYLE CharShape=\"7\" EngName=\"Memo\" Id=\"13\" LangId=\"1042\" LockForm=\"0\" Name=\"메모\" NextStyle=\"13\" ParaShape=\"0\" Type=\"Para\"/>\n");
			sb.append("</STYLELIST>\n");

			sb.append("</MAPPINGTABLE>\n");
			sb.append("</HEAD>\n");			
			return sb;
		}
		
		/**HWPML 용 하자검사조서 검사조서 엘리먼트를 문자열로 가져온다.*/
		public StringBuilder getXmlFcltyRepairCheckReportBody(Map<String, Integer> imageIndexes, EgovMap[] chargers, EgovMap report) {
			StringBuilder sb = new StringBuilder();
			String castFlawEnnc = (String)report.get("castFlawEnnc"); //하자있음 또는 하자없음
			String ctrtAmt = (String)report.get("ctrtAmt"); //계약금액
			ctrtAmt = ctrtAmt.replace(" ", "");
			String flawExamDt = (report.get("flawExamDt") != null) ? (String)report.get("flawExamDt") : "     년   월   일"; //하자검사일
			String ctrtDt = (report.get("ctrtDt") != null) ? (String)report.get("ctrtDt") : "     년   월   일";  //계약일
			String flawRprNm = (report.get("flawRprNm") != null) ? (String)report.get("flawRprNm") : ""; //공사명
			String flawRprEntrpsNm = (report.get("flawRprEntrpsNm") != null) ? (String)report.get("flawRprEntrpsNm") : ""; //도급업체명
			String bldDt = (report.get("bldDt") != null) ? (String)report.get("bldDt") : null; //도급업체명
			
			sb.append("<P ColumnBreak=\"false\" PageBreak=\"false\" ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"8\">\n");
			sb.append("<SECDEF CharGrid=\"0\" FirstBorder=\"false\" FirstFill=\"false\" LineGrid=\"0\" OutlineShape=\"1\" SpaceColumns=\"1134\" TabStop=\"8000\" TextDirection=\"0\" TextVerticalWidthHead=\"0\">\n");
			sb.append("<STARTNUMBER Equation=\"0\" Figure=\"0\" Page=\"0\" PageStartsOn=\"Both\" Table=\"0\"/>\n");
			sb.append("<HIDE Border=\"false\" EmptyLine=\"false\" Fill=\"false\" Footer=\"false\" Header=\"false\" MasterPage=\"false\" PageNumPos=\"false\"/>\n");
			sb.append("<PAGEDEF GutterType=\"LeftOnly\" Height=\"84188\" Landscape=\"0\" Width=\"59528\">\n");
			sb.append("<PAGEMARGIN Bottom=\"4252\" Footer=\"2835\" Gutter=\"0\" Header=\"2835\" Left=\"7087\" Right=\"7087\" Top=\"5668\"/>\n");
			sb.append("</PAGEDEF>\n");
			sb.append("<FOOTNOTESHAPE>\n");
			sb.append("<AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/>\n");
			sb.append("<NOTELINE Length=\"5cm\" Type=\"Solid\" Width=\"0.12mm\"/>\n");
			sb.append("<NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"283\"/>\n");
			sb.append("<NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/>\n");
			sb.append("<NOTEPLACEMENT BeneathText=\"false\" Place=\"EachColumn\"/>\n");
			sb.append("</FOOTNOTESHAPE>\n");
			sb.append("<ENDNOTESHAPE>\n");
			sb.append("<AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/>\n");
			sb.append("<NOTELINE Length=\"14692344\" Type=\"Solid\" Width=\"0.12mm\"/>\n");
			sb.append("<NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"0\"/>\n");
			sb.append("<NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/>\n");
			sb.append("<NOTEPLACEMENT BeneathText=\"false\" Place=\"EndOfDocument\"/>\n");
			sb.append("</ENDNOTESHAPE>\n");
			sb.append("<PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Both\">\n");
			sb.append("<PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/>\n");
			sb.append("</PAGEBORDERFILL>\n");
			sb.append("<PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Even\">\n");
			sb.append("<PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/>\n");
			sb.append("</PAGEBORDERFILL>\n");
			sb.append("<PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Odd\">\n");
			sb.append("<PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/>\n");
			sb.append("</PAGEBORDERFILL>\n");
			sb.append("</SECDEF>\n");
			
			sb.append("<COLDEF Count=\"1\" Layout=\"Left\" SameGap=\"0\" SameSize=\"true\" Type=\"Newspaper\"/>\n");
			
			sb.append("<TABLE BorderFill=\"2\" CellSpacing=\"0\" ColCount=\"1\" PageBreak=\"Cell\" RepeatHeader=\"true\" RowCount=\"1\">\n");
			sb.append("<SHAPEOBJECT InstId=\"" + getInstanceId() + "\" Lock=\"false\" NumberingType=\"Table\" ZOrder=\"" + getZOrder() + "\">\n");
			sb.append("<SIZE Height=\"67462\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"44788\" WidthRelTo=\"Absolute\"/>\n");
			sb.append("<POSITION AffectLSpacing=\"false\" AllowOverlap=\"false\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"0\" HorzRelTo=\"Para\" TreatAsChar=\"true\" VertAlign=\"Top\" VertOffset=\"0\" VertRelTo=\"Para\"/>\n");
			sb.append("<OUTSIDEMARGIN Bottom=\"141\" Left=\"141\" Right=\"141\" Top=\"141\"/>\n");
			sb.append("</SHAPEOBJECT>\n");
			sb.append("<INSIDEMARGIN Bottom=\"141\" Left=\"141\" Right=\"141\" Top=\"141\"/>\n");
			sb.append("<ROW>\n");
			sb.append("<CELL BorderFill=\"6\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"66089\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"44788\">\n");
			sb.append("<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Top\">\n");
			sb.append("<P ParaShape=\"5\" Style=\"0\"><TEXT CharShape=\"9\"/></P>\n");
			sb.append("<P ParaShape=\"5\" Style=\"0\"><TEXT CharShape=\"9\"/></P>\n");
			sb.append("<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>하 자 검 사 조 서</CHAR></TEXT></P>\n");
			sb.append("<P ParaShape=\"5\" Style=\"0\"><TEXT CharShape=\"11\"/></P>\n");
			sb.append("<P ParaShape=\"5\" Style=\"0\"><TEXT CharShape=\"11\"/></P>\n");
			sb.append("<P ParaShape=\"5\" Style=\"0\"><TEXT CharShape=\"11\"><CHAR> </CHAR></TEXT><TEXT CharShape=\"12\"><CHAR> </CHAR></TEXT><TEXT CharShape=\"13\"><CHAR>공사명 : " + flawRprNm + "</CHAR></TEXT></P>\n");
			sb.append("<P ParaShape=\"5\" Style=\"0\"><TEXT CharShape=\"13\"/></P>\n");
			if(bldDt != null) {
				sb.append("<P ParaShape=\"5\" Style=\"0\"><TEXT CharShape=\"13\"><CHAR>                 </CHAR></TEXT><TEXT CharShape=\"12\"><CHAR>" + bldDt + " 준공</CHAR></TEXT></P>\n");
			} else {
				sb.append("<P ParaShape=\"5\" Style=\"0\"><TEXT CharShape=\"13\"><CHAR>                 </CHAR></TEXT><TEXT CharShape=\"12\"><CHAR>20  년   월   일 준공</CHAR></TEXT></P>\n");
			}
			sb.append("<P ParaShape=\"5\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>                 " + ctrtDt + " " + flawRprEntrpsNm + "</CHAR></TEXT></P>\n");
			sb.append("<P ParaShape=\"5\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>                 도급액 :  " + ctrtAmt + "원</CHAR></TEXT></P>\n");
			sb.append("<P ParaShape=\"5\" Style=\"0\"><TEXT CharShape=\"12\"/></P>\n");
			sb.append("<P ParaShape=\"5\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>     </CHAR></TEXT><TEXT CharShape=\"14\"><CHAR>위 공사의 하자검사의 명을 받아  " + flawExamDt + " 검사한 결과</CHAR></TEXT></P>\n");
			sb.append("<P ParaShape=\"5\" Style=\"0\"><TEXT CharShape=\"15\"><CHAR>      </CHAR></TEXT><TEXT CharShape=\"16\"><CHAR>" + castFlawEnnc + "</CHAR></TEXT><TEXT CharShape=\"15\"><CHAR>을 확인함</CHAR></TEXT></P>\n");
			sb.append("<P ParaShape=\"5\" Style=\"0\"><TEXT CharShape=\"12\"/></P>\n");
			sb.append("<P ParaShape=\"5\" Style=\"0\"><TEXT CharShape=\"12\"/></P>\n");
			sb.append("<P ParaShape=\"5\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>                                       " + flawExamDt +"</CHAR></TEXT></P>\n");
			sb.append("<P ParaShape=\"5\" Style=\"0\"><TEXT CharShape=\"12\">\n");
			if(chargers.length > 0) {
				if(chargers[0] != null) {
					String signImageFileNm = (String) chargers[0].get("signFileNmPhysicl");
					int imageId = getImageId(imageIndexes, signImageFileNm);
					if(imageId > 0) {
						sb.append("<PICTURE Reverse=\"false\">\n");
						sb.append("<SHAPEOBJECT InstId=\"" + getInstanceId() + "\" Lock=\"false\" NumberingType=\"Figure\" TextWrap=\"BehindText\" ZOrder=\"" + getZOrder() + "\">\n");
						sb.append("<SIZE Height=\"4500\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"3840\" WidthRelTo=\"Absolute\"/>\n");
						sb.append("<POSITION AffectLSpacing=\"false\" AllowOverlap=\"true\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"37600\" HorzRelTo=\"Para\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"2123\" VertRelTo=\"Para\"/>\n");
						sb.append("<OUTSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/>\n");
						sb.append("</SHAPEOBJECT>\n");
						sb.append("<SHAPECOMPONENT GroupLevel=\"0\" HorzFlip=\"false\" InstID=\"" + getInstanceId() + "\" OriHeight=\"4500\" OriWidth=\"3840\" VertFlip=\"false\" XPos=\"0\" YPos=\"0\">\n");
						sb.append("<ROTATIONINFO Angle=\"0\" CenterX=\"1920\" CenterY=\"2250\"/>\n");
						sb.append("<RENDERINGINFO>\n");
						sb.append("<TRANSMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/>\n");
						sb.append("<SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/>\n");
						sb.append("<ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/>\n");
						sb.append("</RENDERINGINFO>\n");
						sb.append("</SHAPECOMPONENT>\n");
						sb.append("<IMAGERECT X0=\"0\" X1=\"3840\" X2=\"3840\" X3=\"0\" Y0=\"0\" Y1=\"0\" Y2=\"4500\" Y3=\"4500\"/>\n");
						sb.append("<IMAGECLIP Bottom=\"4500\" Left=\"0\" Right=\"3840\" Top=\"0\"/>\n");
						sb.append("<INSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/>\n");
						sb.append("<IMAGE Alpha=\"0\" BinItem=\"" + imageId + "\" Bright=\"0\" Contrast=\"0\" Effect=\"RealPic\"/>\n");
						sb.append("</PICTURE>\n");
					}
				}
			}
			sb.append("<CHAR/>\n");
			sb.append("</TEXT></P>\n");
			sb.append("<P ParaShape=\"5\" Style=\"0\"><TEXT CharShape=\"12\"/></P>\n");
			sb.append("<P ParaShape=\"5\" Style=\"0\"><TEXT CharShape=\"12\">\n");
			if(chargers.length > 1) {
				if(chargers[1] != null) {
					String signImageFileNm = (String) chargers[1].get("signFileNmPhysicl");
					int imageId = getImageId(imageIndexes, signImageFileNm);
					if(imageId > 0) {
						sb.append("<PICTURE Reverse=\"false\">\n");
						sb.append("<SHAPEOBJECT InstId=\"" + getInstanceId() + "\" Lock=\"false\" NumberingType=\"Figure\" TextWrap=\"BehindText\" ZOrder=\"" + getZOrder() + "\">\n");
						sb.append("<SIZE Height=\"4500\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"3840\" WidthRelTo=\"Absolute\"/>\n");
						sb.append("<POSITION AffectLSpacing=\"false\" AllowOverlap=\"false\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"37600\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"1650\" VertRelTo=\"Para\"/>\n");
						sb.append("<OUTSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/>\n");
						sb.append("</SHAPEOBJECT>\n");
						sb.append("<SHAPECOMPONENT GroupLevel=\"0\" HorzFlip=\"false\" InstID=\"" + getInstanceId() + "\" OriHeight=\"4500\" OriWidth=\"3840\" VertFlip=\"false\" XPos=\"0\" YPos=\"0\">\n");
						sb.append("<ROTATIONINFO Angle=\"0\" CenterX=\"1920\" CenterY=\"2250\"/>\n");
						sb.append("<RENDERINGINFO>\n");
						sb.append("<TRANSMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/>\n");
						sb.append("<SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/>\n");
						sb.append("<ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/>\n");
						sb.append("</RENDERINGINFO>\n");
						sb.append("</SHAPECOMPONENT>\n");
						sb.append("<IMAGERECT X0=\"0\" X1=\"3840\" X2=\"3840\" X3=\"0\" Y0=\"0\" Y1=\"0\" Y2=\"4500\" Y3=\"4500\"/>\n");
						sb.append("<IMAGECLIP Bottom=\"4500\" Left=\"0\" Right=\"3840\" Top=\"0\"/>\n");
						sb.append("<INSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/>\n");
						sb.append("<IMAGE Alpha=\"0\" BinItem=\"" + imageId + "\" Bright=\"0\" Contrast=\"0\" Effect=\"RealPic\"/>\n");
						sb.append("</PICTURE>\n");
					}
				}
			}
			if(chargers.length > 0) {
				if(chargers[0] != null) {
					sb.append("<CHAR>                        하자검사원 : " + getChargerNmString(chargers[0]) + "  (인)  </CHAR>\n");
				} else {
					sb.append("<CHAR/>");
				}
			} 
			sb.append("</TEXT></P>\n");
			if(chargers.length > 1) {
				sb.append("<P ParaShape=\"5\" Style=\"0\"><TEXT CharShape=\"12\">\n");
				if(chargers[1] != null) {
					sb.append("<CHAR>                                     " + getChargerNmString(chargers[1]) + "  (인)  </CHAR>\n");
				} else {
					sb.append("<CHAR/>");
				}
				sb.append("</TEXT></P>\n");
			}
			sb.append("<P ParaShape=\"3\" Style=\"0\"><TEXT CharShape=\"12\"/></P>\n");
			sb.append("<P ParaShape=\"5\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>               </CHAR></TEXT><TEXT CharShape=\"17\"><CHAR>                      </CHAR></TEXT><TEXT CharShape=\"12\"/></P>\n");
			sb.append("<P ParaShape=\"5\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>                         </CHAR></TEXT><TEXT CharShape=\"18\"><CHAR>         </CHAR></TEXT><TEXT CharShape=\"12\"><CHAR>   </CHAR></TEXT></P>\n");
			sb.append("<P ParaShape=\"5\" Style=\"0\"><TEXT CharShape=\"12\"/></P>\n");
			sb.append("<P ParaShape=\"5\" Style=\"0\"><TEXT CharShape=\"12\"/></P>\n");
			sb.append("<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"19\"><CHAR>여수광양항만공사사장 귀하</CHAR></TEXT></P>\n");
			sb.append("<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"19\"/></P>\n");
			sb.append("</PARALIST>\n");
			sb.append("</CELL>\n");
			sb.append("</ROW>\n");
			sb.append("</TABLE>\n");
			sb.append("<CHAR/>\n");
			sb.append("</TEXT></P>\n");

			return sb;
		}
		
		/**HWPML 용 하자검사조서 하자 내용과 비고 사진 리스트 엘리먼트를 문자열로 가져온다.*/
		@SuppressWarnings({ "rawtypes" })
		public StringBuilder getXmlFcltyRepairCheckReportList(Map<String, Integer> imageIndexes, EgovMap report, List fileList) {
			StringBuilder sb = new StringBuilder();
			String flawRprNm = (report.get("flawRprNm") != null) ? (String)report.get("flawRprNm") : ""; //공사명
			String flawRprContents = (report.get("flawRprContents") != null) ? (String)report.get("flawRprContents") : ""; //하자 내용
			String flawRprRm = (report.get("rm") != null) ? (String)report.get("rm") : ""; // 비고
			String[] contents = flawRprContents.split("\n");
			String[] rm = flawRprRm.split("\n");
			
			sb.append("<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>하    자    내    용</CHAR></TEXT></P>\n");
			sb.append("<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>(" + flawRprNm + ")</CHAR></TEXT></P>\n");
			sb.append("<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"8\"/></P>\n");
			sb.append("<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"8\">\n");
			sb.append("<TABLE BorderFill=\"2\" CellSpacing=\"0\" ColCount=\"2\" PageBreak=\"Cell\" RepeatHeader=\"true\" RowCount=\"2\">\n");
			sb.append("<SHAPEOBJECT InstId=\"" + getInstanceId() + "\" Lock=\"false\" NumberingType=\"Table\" ZOrder=\"" + getZOrder() + "\">\n");
			sb.append("<SIZE Height=\"11049\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"45034\" WidthRelTo=\"Absolute\"/>\n");
			sb.append("<POSITION AffectLSpacing=\"false\" AllowOverlap=\"false\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"0\" HorzRelTo=\"Para\" TreatAsChar=\"true\" VertAlign=\"Top\" VertOffset=\"0\" VertRelTo=\"Para\"/>\n");
			sb.append("<OUTSIDEMARGIN Bottom=\"141\" Left=\"141\" Right=\"141\" Top=\"141\"/>\n");
			sb.append("</SHAPEOBJECT>\n");
			sb.append("<INSIDEMARGIN Bottom=\"141\" Left=\"141\" Right=\"141\" Top=\"141\"/>\n");
			sb.append("<ROW>\n");
			sb.append("<CELL BorderFill=\"7\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3402\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"30724\">\n");
			sb.append("<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"0\"><CHAR>하자내용</CHAR></TEXT></P>\n");
			sb.append("</PARALIST>\n");
			sb.append("</CELL>\n");
			sb.append("<CELL BorderFill=\"8\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3402\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"14310\">\n");
			sb.append("<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"0\"><CHAR>비 고</CHAR></TEXT></P>\n");
			sb.append("</PARALIST>\n");
			sb.append("</CELL>\n");
			sb.append("</ROW>\n");
			sb.append("<ROW>\n");
			sb.append("<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7647\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"30724\">\n");
			sb.append("<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			if(contents != null) {
				if(contents.length > 0) {
					for(int i=0; i<contents.length; i++) {
						sb.append("<P ParaShape=\"2\" Style=\"0\"><TEXT CharShape=\"1\"><CHAR>" + contents[i] + "</CHAR></TEXT></P>\n");
					}
				} else {
					sb.append("<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"1\"><CHAR/></TEXT></P>\n");
				}
			} else {
				sb.append("<P ParaShape=\"2\" Style=\"0\"><TEXT CharShape=\"1\"><CHAR/></TEXT></P>\n");
			}
			sb.append("</PARALIST>\n");
			sb.append("</CELL>\n");
			sb.append("<CELL BorderFill=\"4\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7647\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"14310\">\n");
			sb.append("<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			if(rm != null) {
				if(rm.length > 0) {
					for(int i=0; i<rm.length; i++) {
			        	sb.append("<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"1\"><CHAR>" + rm[i] + "</CHAR></TEXT></P>\n");
			    	}
				} else {
					sb.append("<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"1\"><CHAR/></TEXT></P>\n");
				}
			} else {
				sb.append("<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"1\"><CHAR/></TEXT></P>\n");
			}
			sb.append("</PARALIST>\n");
			sb.append("</CELL>\n");
			sb.append("</ROW>\n");
			sb.append("</TABLE>\n");
			sb.append("<CHAR/>\n");
			sb.append("</TEXT></P>\n");				
			
			sb.append("<P ParaShape=\"5\" Style=\"0\"><TEXT CharShape=\"8\"/></P>\n");
			sb.append("<P ParaShape=\"5\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>○ 사진대지</CHAR></TEXT></P>\n");
			sb.append("<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"8\">\n");

			int rowCount = fileList.size();
			if((rowCount % 2) == 1) {
				rowCount++; //홀수개의 ROW가 되면 짝수로 바꿔준다.
			}
			if(rowCount > 0) {
				//레코드 카운트를 짝수로 맞춘다. 이유는 2개의 데이터당 2개의 행에 출력하기 때문에 홀수의 레코드는 빈칸을 출력하기 위해 짝수로 맞춘다.			
				sb.append("<TABLE BorderFill=\"2\" CellSpacing=\"0\" ColCount=\"2\" PageBreak=\"Table\" RepeatHeader=\"true\" RowCount=\"" + rowCount + "\">\n");
				sb.append("<SHAPEOBJECT InstId=\"" + getInstanceId() + "\" Lock=\"false\" NumberingType=\"Table\" TextWrap=\"TopAndBottom\" ZOrder=\"" + getZOrder() + "\">\n");
				sb.append("<SIZE Height=\"39628\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"45024\" WidthRelTo=\"Absolute\"/>\n");
				sb.append("<POSITION AffectLSpacing=\"false\" AllowOverlap=\"false\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"0\" HorzRelTo=\"Para\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"0\" VertRelTo=\"Para\"/>\n");
				sb.append("<OUTSIDEMARGIN Bottom=\"141\" Left=\"141\" Right=\"141\" Top=\"141\"/>\n");
				sb.append("</SHAPEOBJECT>\n");
				sb.append("<INSIDEMARGIN Bottom=\"141\" Left=\"141\" Right=\"141\" Top=\"141\"/>\n");
				//루프로 돌릴 때 한 루프당 2개의 데이터를 2개의 행에 표현하기 때문에 루트는 데이터 갯수의 반만 돌린다. 
				int loopEnd = rowCount / 2;
				int rowAddr = 0; //각 cell element의 rowAddr속성을 변경시켜주기 위한 변수
				
				int listIndex = 0; //루프 내에서 사용할 실제 이미지 리스트의 인덱스 
				int listSize = fileList.size(); // 이미지 리스트 개수
	
				int leftBorderFillId = 0, rightBorderFillId = 0; //테두리를 변경시키기 위한 변수
	
				for(int i=0; i<loopEnd; i++) {
					String leftFileName = "", rightFileName = "";
					String leftFileSj = "", rightFileSj = "";
					int leftImageId = 0, rightImageId = 0;
					EgovMap record = (EgovMap) fileList.get(listIndex);
					leftFileName = (String) record.get("atchFileNmPhysicl");
					leftFileSj = (String) record.get("atchFileSj");
					leftImageId = getImageId(imageIndexes, leftFileName);
					listIndex++;
					if(listIndex < listSize) {
						record = (EgovMap) fileList.get(listIndex);
						rightFileName = (String) record.get("atchFileNmPhysicl");
						rightFileSj = (String) record.get("atchFileSj");
						rightImageId = getImageId(imageIndexes, rightFileName);
					}
					listIndex++;
					
					//테두리 처리
					leftBorderFillId = (rowAddr == 0) ? 14 : ((rowAddr < (rowCount-1)) ? 16 : 18); 
					rightBorderFillId = (rowAddr == 0) ? 15 : ((rowAddr < (rowCount-1)) ? 17 : 19); 
					sb.append("<ROW>\n");
					sb.append("<CELL BorderFill=\"" + leftBorderFillId + "\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"15486\" Protect=\"false\" RowAddr=\"" + rowAddr + "\" RowSpan=\"1\" Width=\"22512\">\n");
					sb.append(getXmlFcltyRepairCheckReportListPicture(leftImageId)); //사진
					sb.append("</CELL>\n");
					sb.append("<CELL BorderFill=\"" + rightBorderFillId + "\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"15486\" Protect=\"false\" RowAddr=\"" + rowAddr + "\" RowSpan=\"1\" Width=\"22512\">\n");
					sb.append(getXmlFcltyRepairCheckReportListPicture(rightImageId)); //사진
					sb.append("</CELL>\n");
					sb.append("</ROW>\n");
					rowAddr++;
					//테두리 처리
					leftBorderFillId = (rowAddr == 0) ? 14 : ((rowAddr < (rowCount-1)) ? 16 : 18); 
					rightBorderFillId = (rowAddr == 0) ? 15 : ((rowAddr < (rowCount-1)) ? 17 : 19); 
					sb.append("<ROW>\n");
					sb.append("<CELL BorderFill=\"" + leftBorderFillId + "\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"1948\" Protect=\"false\" RowAddr=\"" + rowAddr + "\" RowSpan=\"1\" Width=\"22512\">\n");
					sb.append("<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
					if(leftFileSj != null) {
						sb.append("<P ParaShape=\"5\" Style=\"0\"><TEXT CharShape=\"2\"><CHAR>" + leftFileSj + "</CHAR></TEXT></P>\n");
					} else {
						sb.append("<P ParaShape=\"5\" Style=\"0\"><TEXT CharShape=\"2\"><CHAR/></TEXT></P>\n");				
					}
					sb.append("</PARALIST>\n");
					sb.append("</CELL>\n");
					sb.append("<CELL BorderFill=\"" + rightBorderFillId + "\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"1948\" Protect=\"false\" RowAddr=\"" + rowAddr + "\" RowSpan=\"1\" Width=\"22512\">\n");
					sb.append("<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
					if(rightFileSj != null) {
						sb.append("<P ParaShape=\"5\" Style=\"0\"><TEXT CharShape=\"2\"><CHAR>" + rightFileSj + "</CHAR></TEXT></P>\n");
					} else {
						sb.append("<P ParaShape=\"5\" Style=\"0\"><TEXT CharShape=\"2\"><CHAR/></TEXT></P>\n");				
					}
					sb.append("</PARALIST>\n");
					sb.append("</CELL>\n");
					sb.append("</ROW>\n");
					rowAddr++;
				}				
				sb.append("</TABLE>\n");
			} else {
				sb.append("<CHAR />\n");
			}
			sb.append("</TEXT></P>\n");						
			return sb;
		}
	
		/**HWPML 용 하자검사조서 사진 엘리먼트 또는 빈 공백 엘리먼트(id가 0인 경우)를 xml 문자열로 가져온다.*/
		public StringBuilder getXmlFcltyRepairCheckReportListPicture(int id) {
			StringBuilder sb = new StringBuilder();
			if(id > 0) {
				sb.append("<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
				sb.append("<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"4\">\n");
				sb.append("<PICTURE Reverse=\"false\">\n");
				sb.append("<SHAPEOBJECT InstId=\"" + getInstanceId() + "\" Lock=\"false\" NumberingType=\"Figure\" ZOrder=\"" + getZOrder() + "\"><SIZE Height=\"12756\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"19843\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"false\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"0\" HorzRelTo=\"Para\" TreatAsChar=\"true\" VertAlign=\"Top\" VertOffset=\"0\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/></SHAPEOBJECT>\n");
				sb.append("<SHAPECOMPONENT CurHeight=\"12755\" CurWidth=\"19843\" GroupLevel=\"0\" HorzFlip=\"false\" InstID=\"" + getInstanceId() + "\" OriHeight=\"57600\" OriWidth=\"76800\" VertFlip=\"false\" XPos=\"0\" YPos=\"0\"><ROTATIONINFO Angle=\"0\" CenterX=\"9921\" CenterY=\"6378\"/><RENDERINGINFO><TRANSMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"0.25837\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"0.22146\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/></RENDERINGINFO></SHAPECOMPONENT>\n");
				sb.append("<IMAGERECT X0=\"0\" X1=\"76800\" X2=\"76800\" X3=\"0\" Y0=\"0\" Y1=\"0\" Y2=\"57600\" Y3=\"57600\"/>\n");
				sb.append("<IMAGECLIP Bottom=\"57600\" Left=\"0\" Right=\"76800\" Top=\"0\"/>\n");
				sb.append("<INSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/>\n");
				sb.append("<IMAGE Alpha=\"0\" BinItem=\""+ id + "\" Bright=\"0\" Contrast=\"0\" Effect=\"RealPic\"/>\n");
				sb.append("</PICTURE>\n");
				sb.append("<CHAR/>\n");
				sb.append("</TEXT></P>\n");
				sb.append("</PARALIST>\n");				
			}
			else {
				sb.append("<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
				sb.append("<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"0\">\n");
				sb.append("<CHAR/>\n");
				sb.append("</TEXT></P>\n");
				sb.append("</PARALIST>\n");
			}
			return sb;
		}
		
		/**HWPML 용 하자검사조서 TAIL 엘리먼트를 문자열로 가져온다.
		 * @throws Exception */
		public StringBuilder getXmlFcltyRepairCheckReportTail(Map<String, Integer> imageIndexes, Map<String, Boolean> signImageYN) throws Exception {
			StringBuilder sb = new StringBuilder();
			Iterator<String> it = imageIndexes.keySet().iterator();
			int count = imageIndexes.keySet().size();
			sb.append("<TAIL>\n");
			if(count > 0) {
				sb.append("<BINDATASTORAGE>\n");
				while(it.hasNext()) {
					String fileName = it.next();
					int id = imageIndexes.get(fileName);
					fileName = (signImageYN.get(fileName)) ? EgovProperties.getProperty("prtfclty.fileStorePath") + fileName : 
						EgovProperties.getProperty("repairAttach.fileStorePath") + fileName; // 도장이미지와 하자사진이미지의 경로가 다름.
	 				String base64Data = fileToBase64(fileName);
					int dataSize = base64Data.length();
					sb.append("<BINDATA Encoding=\"Base64\" Id=\"" + id + "\" Size=\"" + dataSize + "\">");
					sb.append(base64Data);
					sb.append("</BINDATA>\n");
				}
				sb.append("</BINDATASTORAGE>\n");
			}
			sb.append("<SCRIPTCODE Type=\"JScrip\" Version=\"1.0\"><SCRIPTHEADER>var Documents = XHwpDocuments;\n");
			sb.append("var Document = Documents.Active_XHwpDocument;\n");
			sb.append("</SCRIPTHEADER><SCRIPTSOURCE>function OnDocument_New() { }\n");
			sb.append("</SCRIPTSOURCE></SCRIPTCODE>\n");
			sb.append("</TAIL>\n");
			return sb;
		}
	}
}
