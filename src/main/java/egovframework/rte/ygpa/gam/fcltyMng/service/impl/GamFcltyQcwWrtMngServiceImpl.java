/**
 *
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyQcHwpBaseReportInterface;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyQcwWrtMngService;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyQcwWrtMngVO;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamQcMngAtchFileMngVO;

/**
 *
 * @author 김종민
 * @since 2014. 11. 24.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 11. 24.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamFcltyQcwWrtMngService")
public class GamFcltyQcwWrtMngServiceImpl extends AbstractServiceImpl implements GamFcltyQcwWrtMngService{
	@Resource(name="gamFcltyQcwWrtMngDao")
	GamFcltyQcwWrtMngDao gamFcltyQcwWrtMngDao;

	/**
	 * 점검관리내역 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectQcMngDtlsList(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return gamFcltyQcwWrtMngDao.selectQcMngDtlsList(searchVO);
	}

	/**
	 * 점검관리내역 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectQcMngDtlsListTotCnt(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return gamFcltyQcwWrtMngDao.selectQcMngDtlsListTotCnt(searchVO);
	}

	/**
	 * 점검관리내역 데이터 조회
	 * @param vo
	 * @return EgovMap
	 * @throws Exception
	 */
	public EgovMap selectQcMngDtlsDetail(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return gamFcltyQcwWrtMngDao.selectQcMngDtlsDetail(searchVO);
	}

	/**
	 * 점검관리내역 데이터 삽입
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public void insertQcMngDtls(Map<String, String> detailForm, List<HashMap<String, String>> qcObjList,  List<HashMap<String, String>> qcResultList) throws Exception {
		detailForm.put("qcMngSeq", gamFcltyQcwWrtMngDao.selectMaxQcMngSeq(detailForm));

		gamFcltyQcwWrtMngDao.insertQcMngDtls(detailForm);

		//점검대상물 전체 삭제 후 추가
		gamFcltyQcwWrtMngDao.deleteQcMngObjFcltsList(detailForm);
		for(HashMap<String, String> qcObjItem : qcObjList) {
			qcObjItem.put("qcMngSeq", detailForm.get("qcMngSeq"));
			qcObjItem.put("regUsr", detailForm.get("regUsr"));
			gamFcltyQcwWrtMngDao.insertQcMngObjFclts(qcObjItem);
		}

		//점검결과항목 전체 삭제 후 추가
		gamFcltyQcwWrtMngDao.deleteQcMngResultItemList(detailForm);
		for(HashMap<String, String> qcResultItem : qcResultList) {
			qcResultItem.put("qcMngSeq", detailForm.get("qcMngSeq"));
			qcResultItem.put("regUsr", detailForm.get("regUsr"));
			gamFcltyQcwWrtMngDao.insertQcMngResultItem(qcResultItem);
		}

	}

	/**
	 * 점검관리내역 데이터 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public void updateQcMngDtls(Map<String, String> detailForm, List<HashMap<String, String>> qcObjList,  List<HashMap<String, String>> qcResultList) throws Exception {
		gamFcltyQcwWrtMngDao.updateQcMngDtls(detailForm);

		//점검대상물 전체 삭제 후 추가
		gamFcltyQcwWrtMngDao.deleteQcMngObjFcltsList(detailForm);
		for(HashMap<String, String> qcObjItem : qcObjList) {
			qcObjItem.put("regUsr", detailForm.get("updUsr"));
			gamFcltyQcwWrtMngDao.insertQcMngObjFclts(qcObjItem);
		}

		//점검결과항목 전체 삭제 후 추가
		gamFcltyQcwWrtMngDao.deleteQcMngResultItemList(detailForm);
		for(HashMap<String, String> qcResultItem : qcResultList) {
			qcResultItem.put("regUsr", detailForm.get("updUsr"));
			gamFcltyQcwWrtMngDao.insertQcMngResultItem(qcResultItem);
		}

	}

	/**
	 * 선택된 점검관리내역 데이터 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public void deleteSelectedQcMngDtls(List<HashMap<String,String>> deleteList) throws Exception {
		for(int i=0; i<deleteList.size(); i++) {
			Map<?, ?> deleteItem = deleteList.get(i);
			deleteQcMngDtls(deleteItem);
		}
	}
	
	/**
	 * 점검관리내역 데이터 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public void deleteQcMngDtls(Map<?, ?> vo) throws Exception {
		gamFcltyQcwWrtMngDao.deleteQcMngAtchFileList(vo);
		gamFcltyQcwWrtMngDao.deleteQcMngResultItemList(vo);
		gamFcltyQcwWrtMngDao.deleteQcMngObjFcltsList(vo);
		gamFcltyQcwWrtMngDao.deleteQcMngDtls(vo);
	}


	/**
	 * 점검관리대상시설물 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectQcMngObjFcltsList(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return gamFcltyQcwWrtMngDao.selectQcMngObjFcltsList(searchVO);
	}

	/**
	 * 점검관리결과항목 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectQcMngResultItemList(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return gamFcltyQcwWrtMngDao.selectQcMngResultItemList(searchVO);
	}

	/**
	 * 점검관리결과항목 목록 개수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectQcMngResultItemListTotCnt(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return gamFcltyQcwWrtMngDao.selectQcMngResultItemListTotCnt(searchVO);
	}

	/**
	 * 기계 점검관리결과항목 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public List<?> selectMechQcMngResultItemList(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return gamFcltyQcwWrtMngDao.selectMechQcMngResultItemList(searchVO);
	}


	/**
	 * 관리그룹 데이터 조회
	 * @param vo
	 * @return EgovMap
	 * @throws Exception
	 */
	public EgovMap selectFcltsMngGroupInfo(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return gamFcltyQcwWrtMngDao.selectFcltsMngGroupInfo(searchVO);
	}



	@Override
	public List<?> selectFcltyQcwWrtMngQcMngAtchFileList(GamQcMngAtchFileMngVO searchVO) throws Exception {
		return gamFcltyQcwWrtMngDao.selectFcltyQcwWrtMngQcMngAtchFileList(searchVO);
	}

	@Override
	public void insertFcltyQcwWrtMngQcMngAtchFile(GamQcMngAtchFileMngVO gamQcMngAtchFileMngVO) throws Exception {
		gamFcltyQcwWrtMngDao.insertFcltyQcwWrtMngQcMngAtchFile(gamQcMngAtchFileMngVO);
	}

	@Override
	public void updateFcltyQcwWrtMngQcMngAtchFile(GamQcMngAtchFileMngVO gamQcMngAtchFileMngVO) throws Exception {
		gamFcltyQcwWrtMngDao.updateFcltyQcwWrtMngQcMngAtchFile(gamQcMngAtchFileMngVO);
	}

	@Override
	public void deleteFcltyQcwWrtMngQcMngAtchFile(GamQcMngAtchFileMngVO gamQcMngAtchFileMngVO) throws Exception {
		gamFcltyQcwWrtMngDao.deleteFcltyQcwWrtMngQcMngAtchFile(gamQcMngAtchFileMngVO);
	}

	@Override
	public void deleteFcltyQcwWrtMngQcMngAtchFileMulti(Map<?, ?> deleteVO) throws Exception {
		gamFcltyQcwWrtMngDao.deleteFcltyQcwWrtMngQcMngAtchFileMulti(deleteVO);
	}

	@Override
	public void deleteFcltyQcwWrtMngAllQcMngAtchFile(GamQcMngAtchFileMngVO gamQcMngAtchFileMngVO) throws Exception {
		gamFcltyQcwWrtMngDao.deleteFcltyQcwWrtMngAllQcMngAtchFile(gamQcMngAtchFileMngVO);
	}

	@Override
	public EgovMap selectFcltyQcwWrtMngQcMngAtchFilePk(GamQcMngAtchFileMngVO gamQcMngAtchFileMngVO) throws Exception {
		return gamFcltyQcwWrtMngDao.selectFcltyQcwWrtMngQcMngAtchFilePk(gamQcMngAtchFileMngVO);
	}

	@Override
	public String selectFcltyQcwWrtMngQcMngAtchFileNewSeq(GamQcMngAtchFileMngVO gamQcMngAtchFileMngVO) throws Exception {
		return gamFcltyQcwWrtMngDao.selectFcltyQcwWrtMngQcMngAtchFileNewSeq(gamQcMngAtchFileMngVO);
	}

	/**
	 * 선택된 안전 점검 첨부파일 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int selectQcMngAtchPictureFileListTotCnt(List<HashMap<String,String>> reportList) throws Exception {
		int result = 0;
		for(int i=0; i<reportList.size(); i++) {
			Map<String, String> reportItem = reportList.get(i);
			GamFcltyQcwWrtMngVO searchVO = new GamFcltyQcwWrtMngVO();
			searchVO.setsFcltsMngGroupNo(reportItem.get("fcltsMngGroupNo"));
			searchVO.setsFcltsJobSe(reportItem.get("fcltsJobSe"));
			searchVO.setsQcMngSeq(reportItem.get("qcMngSeq"));
			result += gamFcltyQcwWrtMngDao.selectQcMngAtchPictureFileListTotCnt(searchVO);
		}
		return result;
	}

	/**
	 * 선택된 안전 점검 결과 한글 문서 다운로드 - 김종민 추가 작업 2015.11.6
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String selectSafetyQcReportListHWPML(List<HashMap<String,String>> reportList) throws Exception {
		StringBuilder result = new StringBuilder();
		Map<String, Integer> imageIndexes = new HashMap<String, Integer>(); //이미지 파일명과 id구성을 위한 맵

		//HWPML용 인스턴스 생성
		GamFcltyQcHwpSafeResultReport report = new GamFcltyQcHwpSafeResultReport();
    	  	
    	//HWPML Start Element 부분
		result.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>\n");
		result.append("<HWPML Style=\"embed\" SubVersion=\"7.0.0.0\" Version=\"2.7\">\n");
		
    	//사진 이미지 파일 정보 구성
		for(int i=0; i<reportList.size(); i++) {
			Map<String, String> reportItem = reportList.get(i);
			GamFcltyQcwWrtMngVO searchVO = new GamFcltyQcwWrtMngVO();
			searchVO.setsFcltsMngGroupNo(reportItem.get("fcltsMngGroupNo"));
			searchVO.setsFcltsJobSe(reportItem.get("fcltsJobSe"));
			searchVO.setsQcMngSeq(reportItem.get("qcMngSeq"));
			
			List<EgovMap> fileList =  (List<EgovMap>) gamFcltyQcwWrtMngDao.selectQcMngAtchPictureFileList(searchVO);

	    	for(int j=0; j<fileList.size(); j++) {
	    		EgovMap item = (EgovMap) fileList.get(j);
	        	String fileName = (String) item.get("atchFileNmPhysicl");
	        	if((fileName != null) && (fileName.length() > 0)) {
	        		fileName = EgovProperties.getProperty("qcAttach.fileStorePath") + fileName; 
	            	File file = new File(fileName);
	            	if(file.exists()) {
	            		imageIndexes.put((String) item.get("atchFileNmPhysicl"), 0);
	            	}    
	        	}
	    	}
		}
		
    	//Head Element 구성 처리
		result.append(report.getReportHeader(imageIndexes));
		
		//Body Element 구성 처리
		result.append("<BODY><SECTION Id=\"0\">\n");
		
		for(int i=0; i<reportList.size(); i++) {
			Map<String, String> reportItem = reportList.get(i);
			GamFcltyQcwWrtMngVO searchVO = new GamFcltyQcwWrtMngVO();
			searchVO.setsFcltsMngGroupNo(reportItem.get("fcltsMngGroupNo"));
			searchVO.setsFcltsJobSe(reportItem.get("fcltsJobSe"));
			searchVO.setsQcMngSeq(reportItem.get("qcMngSeq"));
			
			EgovMap qcDetailData = gamFcltyQcwWrtMngDao.selectQcMngDtlsDetail(searchVO);
			List<EgovMap> fileList =  (List<EgovMap>) gamFcltyQcwWrtMngDao.selectQcMngAtchPictureFileList(searchVO);

			if((qcDetailData != null) && (fileList.size() > 0)) {
				result.append(report.getReportBody(qcDetailData, imageIndexes, fileList, i==0));
			}
		}
		
		result.append("</SECTION></BODY>\n");

		//Tail Element 구성 처리
		result.append(report.getReportTail(imageIndexes));
		result.append("</HWPML>");

		return result.toString();
	}
	
	/**
	 * 안전 점검 결과 한글 문서 다운로드 - 김종민 추가 작업 2015.11.2
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String selectSafetyQcReportHWPML(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		StringBuilder result = new StringBuilder();
    	Map<String, Integer> imageIndexes = new HashMap<String, Integer>(); //이미지 파일명과 id구성을 위한 맵
    	
    	//HWPML용 인스턴스 생성
    	GamFcltyQcHwpSafeResultReport report = new GamFcltyQcHwpSafeResultReport();
    	  	
    	//HWPML Start Element 부분
		result.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>\n");
		result.append("<HWPML Style=\"embed\" SubVersion=\"7.0.0.0\" Version=\"2.7\">\n");
    	
		//점검내역조회
		EgovMap qcDetailData = gamFcltyQcwWrtMngDao.selectQcMngDtlsDetail(searchVO);

		//점검사진목록조회
		List<EgovMap> fileList =  (List<EgovMap>) gamFcltyQcwWrtMngDao.selectQcMngAtchPictureFileList(searchVO);
		
    	//사진 이미지 파일 정보 구성
    	for(int i=0; i<fileList.size(); i++) {
    		EgovMap item = (EgovMap) fileList.get(i);
        	String fileName = (String) item.get("atchFileNmPhysicl");
        	if((fileName != null) && (fileName.length() > 0)) {
        		fileName = EgovProperties.getProperty("qcAttach.fileStorePath") + fileName; 
            	File file = new File(fileName);
            	if(file.exists()) {
            		imageIndexes.put((String) item.get("atchFileNmPhysicl"), 0);
            	}    
        	}
    	}
		
    	//Head Element 구성 처리
		result.append(report.getReportHeader(imageIndexes));
		
		//Body Element 구성 처리
		result.append("<BODY><SECTION Id=\"0\">\n");
		if(qcDetailData != null) {
			result.append(report.getReportBody(qcDetailData, imageIndexes, fileList, true));
		}
		result.append("</SECTION></BODY>\n");

		//Tail Element 구성 처리
		result.append(report.getReportTail(imageIndexes));
		result.append("</HWPML>");

		return result.toString();
	}
		
	/**
	 * 시설물 점검표 한글 문서 다운로드 - 김종민 추가 작업 2015.10.28
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public String selectQcMngResultListReportHWPML(GamFcltyQcwWrtMngVO searchVO) throws Exception {		
		List<HashMap<String, String>> reportList = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> report = new HashMap<String, String>();
		report.put("fcltsMngGroupNo", searchVO.getsFcltsMngGroupNo());
		report.put("fcltsJobSe", searchVO.getsFcltsJobSe());
		report.put("qcMngSeq", searchVO.getsQcMngSeq());
		reportList.add(report);
		return selectSelectedReportListHWPML(reportList);
	}
		
	/**
	 * 선택된 시설물점검표 리스트 한글 문서 다운로드 - 김종민 추가 작업 2016.01.12
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String selectSelectedReportListHWPML(List<HashMap<String,String>> reportList) throws Exception {
		List<GamFcltyQcHwpMngResultInfo> qcMngResultInfoList = new ArrayList<GamFcltyQcHwpMngResultInfo>();
		Map<String, Integer> fileIndexInfo = new HashMap<String, Integer>();
		String fcltsJobSe = null;
		String mechFcltsSe = null;
		
		//리포트 정보 생성
		for(int i=0; i<reportList.size(); i++) {
			EgovMap mngGroupInfo = null, chargerInfo1 = null, chargerInfo2 = null;
			List<EgovMap> qcResultItemList = null;
			// 점검내역 조회
			EgovMap qcDetailData = gamFcltyQcwWrtMngDao.selectHwpQcMngDtlsDetail(reportList.get(i));
			if(qcDetailData != null) {
				fcltsJobSe = (String)qcDetailData.get("fcltsJobSe");
				// 점검 결과 항목 리스트 조회
				if(fcltsJobSe.equals("M")) {
					//기계
					mechFcltsSe = (String)qcDetailData.get("fcltsJobSe");
					String mechCdStartChar = (mechFcltsSe.equals("1")) ? "M02" : "M01";
					qcDetailData.put("mechCdStartChar", mechCdStartChar);
					qcResultItemList = (List<EgovMap>) gamFcltyQcwWrtMngDao.selectHwpMechQcMngResultItemList(qcDetailData);
				} else if(fcltsJobSe.equals("C")) {
					//토목
					qcResultItemList = (List<EgovMap>) gamFcltyQcwWrtMngDao.selectHwpQcMngCivilResultItemList(qcDetailData);
				} else {
					qcResultItemList = (List<EgovMap>) gamFcltyQcwWrtMngDao.selectHwpQcMngResultItemList(qcDetailData);
				}
				
				// 토목일 경우 시설물 그룹 정보 조회
				if(fcltsJobSe.equals("C")) {
					mngGroupInfo = gamFcltyQcwWrtMngDao.selectHwpFcltsMngGroupInfo(qcDetailData);
				}

				// 작성자를 가져와서 ,로 나누어서 시설물관리자 정보를 가져온다.
				String wrtUsr = (String) qcDetailData.get("wrtUsr");
				if(wrtUsr != null) wrtUsr = wrtUsr.replace(" ", "");				
				if((wrtUsr != null) && (wrtUsr.length() > 0)) {
					String wrtUsers[] = wrtUsr.split(",");
					int index = 0;
					for(String wrtUser : wrtUsers) {
						if(index < 2) {
							Map<String, String> chargerNm = new HashMap<String, String>();
							chargerNm.put("chargerNm", wrtUser);
							chargerNm.put("fcltsJobSe", fcltsJobSe);
							EgovMap chargerInfo = gamFcltyQcwWrtMngDao.selectChargerInfo(chargerNm);
							if(chargerInfo != null) {
								String fileName = (String) chargerInfo.get("signFileNmPhysicl");
						    	if((fileName != null) && (fileName.length() > 0)) {
						    		fileName = EgovProperties.getProperty("prtfclty.fileStorePath") + fileName;
						        	File file = new File(fileName);
						        	if(file.exists()) {
						        		chargerInfo.put("fileExists", new Boolean(true));
						        		fileIndexInfo.put((String) chargerInfo.get("signFileNmPhysicl"), 0);
						        	} else {
						        		chargerInfo.put("fileExists", new Boolean(false));
						        	}
						    	} else {
						    		chargerInfo.put("fileExists", new Boolean(false));
						    	}
							} else {
								chargerInfo = new EgovMap();
								chargerInfo.put("chargerNm", wrtUser);
								chargerInfo.put("fileExists", new Boolean(false));
							}
							if(index == 0) chargerInfo1 = chargerInfo;
							else chargerInfo2 = chargerInfo;
						}
						index++;
					}
				}
				qcMngResultInfoList.add(new GamFcltyQcHwpMngResultInfo(qcDetailData, qcResultItemList, mngGroupInfo, chargerInfo1, chargerInfo2));
			}
		}
		
		GamFcltyQcHwpBaseReportInterface report = null;
		
		if(fcltsJobSe.equals("C")) { //토목
			report = new GamFcltyQcHwpCivilListReport(qcMngResultInfoList, fileIndexInfo);
		} else if(fcltsJobSe.equals("A")) { //건축
			report = new GamFcltyQcHwpArchListReport(qcMngResultInfoList, fileIndexInfo);
		} else if(fcltsJobSe.equals("E")) { //전력
			report = new GamFcltyQcHwpElectyListReport(qcMngResultInfoList, fileIndexInfo); 
		} else if(fcltsJobSe.equals("I")) { //정보통신
			report = new GamFcltyQcHwpInfoCommListReport(qcMngResultInfoList, fileIndexInfo);
		} else if(fcltsJobSe.equals("M")) { //기계
			if(mechFcltsSe != null) {
				report = (mechFcltsSe.equals("1")) ? new GamFcltyQcHwpCraneMechListReport(qcMngResultInfoList, fileIndexInfo) : new GamFcltyQcHwpArchMechListReport(qcMngResultInfoList, fileIndexInfo);
			} 
		}
		
		String result = "";
		if(report != null)
			result = report.getHwpReport();
		
		return result; 
	}
	
}
