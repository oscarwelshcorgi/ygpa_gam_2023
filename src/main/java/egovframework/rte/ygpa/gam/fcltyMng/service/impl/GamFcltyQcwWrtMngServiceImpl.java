/**
 *
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	public List selectFcltyQcwWrtMngQcMngAtchFileList(GamQcMngAtchFileMngVO searchVO) throws Exception {
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
	 * 선택된 안전 점검 결과 한글 문서 다운로드 - 김종민 추가 작업 2015.11.6
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	public String selectSafetyQcReportListHWPML(List<HashMap<String,String>> reportList) throws Exception {
		StringBuilder result = new StringBuilder();
		Map<String, Integer> imageIndexes = new HashMap<String, Integer>(); //이미지 파일명과 id구성을 위한 맵

		//HWPML용 인스턴스 생성
    	MakeSafeQcResultHwpReport report = new MakeSafeQcResultHwpReport();
    	  	
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

			if(qcDetailData != null) {
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
    	MakeSafeQcResultHwpReport report = new MakeSafeQcResultHwpReport();
    	  	
    	//HWPML Start Element 부분
		result.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>\n");
		result.append("<HWPML Style=\"embed\" SubVersion=\"7.0.0.0\" Version=\"2.7\">\n");
    	
		//점검내역조회
		EgovMap qcDetailData = gamFcltyQcwWrtMngDao.selectQcMngDtlsDetail(searchVO);

		//점검사진목록조회
		List<EgovMap> fileList =  (List<EgovMap>) gamFcltyQcwWrtMngDao.selectQcMngAtchFileList(searchVO);
		
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
	
	
	class MakeSafeQcResultHwpReport {
		
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
		
		/**HWPML 용 안전점검결과 HEAD 엘리먼트를 문자열로 가져온다.
		 * @throws Exception */
		public StringBuilder getReportHeader(Map<String, Integer> imageIndexes) {
			StringBuilder sb = new StringBuilder();
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM일 dd일 HH시 mm분 ss초", Locale.KOREA);
			String today = formatter.format(new Date());
			Iterator<String> it = imageIndexes.keySet().iterator();
			int id = 0, count = imageIndexes.keySet().size();
			
			sb.append("<HEAD SecCnt=\"1\">\n");
			sb.append("	<DOCSUMMARY><AUTHOR>YGPA GIS Assets Management System</AUTHOR><DATE>" + today + "</DATE></DOCSUMMARY>\n");
			sb.append("	<DOCSETTING><BEGINNUMBER Endnote=\"1\" Equation=\"1\" Footnote=\"1\" Page=\"1\" Picture=\"1\" Table=\"1\"/><CARETPOS List=\"0\" Para=\"0\" Pos=\"16\"/></DOCSETTING>\n");
			sb.append("	<MAPPINGTABLE>\n");
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
			sb.append("		<FACENAMELIST><FONTFACE Count=\"4\" Lang=\"Hangul\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"휴먼명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT><FONT Id=\"3\" Name=\"한양중고딕\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"4\" Lang=\"Latin\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양중고딕\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT><FONT Id=\"3\" Name=\"HCI Poppy\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"4\" Lang=\"Hanja\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT><FONT Id=\"3\" Name=\"한양중고딕\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"4\" Lang=\"Japanese\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT><FONT Id=\"3\" Name=\"한양중고딕\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Other\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"4\" Lang=\"Symbol\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT><FONT Id=\"3\" Name=\"한양중고딕\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"User\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE></FACENAMELIST>\n");
			sb.append("		<BORDERFILLLIST Count=\"5\"><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"1\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.1mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"2\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"None\" Width=\"0.1mm\"/><RIGHTBORDER Type=\"None\" Width=\"0.1mm\"/><TOPBORDER Type=\"None\" Width=\"0.1mm\"/><BOTTOMBORDER Type=\"None\" Width=\"0.1mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.1mm\"/><FILLBRUSH><WINDOWBRUSH Alpha=\"0\" FaceColor=\"4294967295\" HatchColor=\"4278190080\"/></FILLBRUSH></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"3\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.1mm\"/><FILLBRUSH><WINDOWBRUSH Alpha=\"0\" FaceColor=\"14079702\" HatchColor=\"0\"/></FILLBRUSH></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"4\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Dot\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.1mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"5\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Dot\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.1mm\"/></BORDERFILL></BORDERFILLLIST>\n");
			sb.append("		<CHARSHAPELIST Count=\"12\"><CHARSHAPE BorderFillId=\"0\" Height=\"1000\" Id=\"0\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1000\" Id=\"1\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"2\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"3\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"95\" Hanja=\"95\" Japanese=\"95\" Latin=\"95\" Other=\"95\" Symbol=\"95\" User=\"95\"/><CHARSPACING Hangul=\"-5\" Hanja=\"-5\" Japanese=\"-5\" Latin=\"-5\" Other=\"-5\" Symbol=\"-5\" User=\"-5\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"4\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"95\" Hanja=\"95\" Japanese=\"95\" Latin=\"95\" Other=\"95\" Symbol=\"95\" User=\"95\"/><CHARSPACING Hangul=\"-5\" Hanja=\"-5\" Japanese=\"-5\" Latin=\"-5\" Other=\"-5\" Symbol=\"-5\" User=\"-5\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1500\" Id=\"5\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"3\" Hanja=\"3\" Japanese=\"3\" Latin=\"2\" Other=\"2\" Symbol=\"3\" User=\"2\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><BOLD/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1300\" Id=\"6\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"2\" Hanja=\"2\" Japanese=\"2\" Latin=\"3\" Other=\"2\" Symbol=\"2\" User=\"2\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1300\" Id=\"7\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"2\" Hanja=\"2\" Japanese=\"2\" Latin=\"3\" Other=\"2\" Symbol=\"2\" User=\"2\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><BOLD/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1100\" Id=\"8\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"2\" Hanja=\"2\" Japanese=\"2\" Latin=\"3\" Other=\"2\" Symbol=\"2\" User=\"2\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><BOLD/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1100\" Id=\"9\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"2\" Hanja=\"2\" Japanese=\"2\" Latin=\"3\" Other=\"2\" Symbol=\"2\" User=\"2\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"600\" Id=\"10\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"2\" Hanja=\"2\" Japanese=\"2\" Latin=\"3\" Other=\"2\" Symbol=\"2\" User=\"2\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1200\" Id=\"11\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"2\" Hanja=\"2\" Japanese=\"2\" Latin=\"3\" Other=\"2\" Symbol=\"2\" User=\"2\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE></CHARSHAPELIST>\n");
			sb.append("		<TABDEFLIST Count=\"3\"><TABDEF AutoTabLeft=\"false\" AutoTabRight=\"false\" Id=\"0\"/><TABDEF AutoTabLeft=\"true\" AutoTabRight=\"false\" Id=\"1\"/><TABDEF AutoTabLeft=\"false\" AutoTabRight=\"true\" Id=\"2\"/></TABDEFLIST>\n");
			sb.append("		<NUMBERINGLIST Count=\"1\"><NUMBERING Id=\"1\" Start=\"0\"><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"1\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^1.</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"2\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^2.</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"3\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^3)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"4\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^4)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"5\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">(^5)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"6\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">(^6)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"7\" NumFormat=\"CircledDigit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^7</PARAHEAD></NUMBERING></NUMBERINGLIST>\n");
			sb.append("		<PARASHAPELIST Count=\"15\"><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"0\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"true\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"1\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"1\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"-2620\" Left=\"0\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"2\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"3\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"3000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"4\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"2000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"5\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"1\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"4000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"6\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"2\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"6000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"7\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"3\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"8000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"8\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"4\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"10000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"9\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"5\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"12000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"10\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"6\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"14000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"11\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"2\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"150\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Right\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"12\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"13\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Left\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"14\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE></PARASHAPELIST>\n");
			sb.append("		<STYLELIST Count=\"14\"><STYLE CharShape=\"1\" EngName=\"Normal\" Id=\"0\" LangId=\"1042\" LockForm=\"0\" Name=\"바탕글\" NextStyle=\"0\" ParaShape=\"2\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Body\" Id=\"1\" LangId=\"1042\" LockForm=\"0\" Name=\"본문\" NextStyle=\"1\" ParaShape=\"3\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 1\" Id=\"2\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 1\" NextStyle=\"2\" ParaShape=\"4\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 2\" Id=\"3\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 2\" NextStyle=\"3\" ParaShape=\"5\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 3\" Id=\"4\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 3\" NextStyle=\"4\" ParaShape=\"6\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 4\" Id=\"5\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 4\" NextStyle=\"5\" ParaShape=\"7\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 5\" Id=\"6\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 5\" NextStyle=\"6\" ParaShape=\"8\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 6\" Id=\"7\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 6\" NextStyle=\"7\" ParaShape=\"9\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 7\" Id=\"8\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 7\" NextStyle=\"8\" ParaShape=\"10\" Type=\"Para\"/><STYLE CharShape=\"0\" EngName=\"Page Number\" Id=\"9\" LangId=\"1042\" LockForm=\"0\" Name=\"쪽 번호\" NextStyle=\"9\" ParaShape=\"2\" Type=\"Para\"/><STYLE CharShape=\"2\" EngName=\"Header\" Id=\"10\" LangId=\"1042\" LockForm=\"0\" Name=\"머리말\" NextStyle=\"10\" ParaShape=\"11\" Type=\"Para\"/><STYLE CharShape=\"3\" EngName=\"Footnote\" Id=\"11\" LangId=\"1042\" LockForm=\"0\" Name=\"각주\" NextStyle=\"11\" ParaShape=\"1\" Type=\"Para\"/><STYLE CharShape=\"3\" EngName=\"Endnote\" Id=\"12\" LangId=\"1042\" LockForm=\"0\" Name=\"미주\" NextStyle=\"12\" ParaShape=\"1\" Type=\"Para\"/><STYLE CharShape=\"4\" EngName=\"Memo\" Id=\"13\" LangId=\"1042\" LockForm=\"0\" Name=\"메모\" NextStyle=\"13\" ParaShape=\"0\" Type=\"Para\"/></STYLELIST>\n");
			sb.append("	</MAPPINGTABLE>\n");
			sb.append("</HEAD>\n");
			
			return sb;
		}
		
		/**HWPML 용 안전점검결과 BODY 엘리먼트를 문자열로 가져온다.
		 * @throws Exception */
		@SuppressWarnings("rawtypes")
		public StringBuilder getReportBody(EgovMap detailData, Map<String, Integer> imageIndexes,  List fileList, boolean firstPage) {
			StringBuilder sb = new StringBuilder();
			String mngGroupNm = (detailData.get("fcltsMngGroupNm") != null) ? (String)detailData.get("fcltsMngGroupNm") : ""; //시설물 관리그룹명
			String qcActionCn = (detailData.get("actionCn") != null) ? (String)detailData.get("actionCn") : ""; //점검내용
			String qcRm = (detailData.get("rm") != null) ? (String)detailData.get("rm") : ""; // 비고
			String[] contents = qcActionCn.split("\n");
			String[] rm = qcRm.split("\n");
			if(firstPage) {
				sb.append("	<P ColumnBreak=\"false\" PageBreak=\"false\" ParaShape=\"2\" Style=\"0\"><TEXT CharShape=\"5\">\n");
				sb.append("		<SECDEF CharGrid=\"0\" FirstBorder=\"false\" FirstFill=\"false\" LineGrid=\"0\" OutlineShape=\"1\" SpaceColumns=\"1134\" TabStop=\"8000\" TextDirection=\"0\" TextVerticalWidthHead=\"0\"><STARTNUMBER Equation=\"0\" Figure=\"0\" Page=\"0\" PageStartsOn=\"Both\" Table=\"0\"/><HIDE Border=\"false\" EmptyLine=\"false\" Fill=\"false\" Footer=\"false\" Header=\"false\" MasterPage=\"false\" PageNumPos=\"false\"/><PAGEDEF GutterType=\"LeftOnly\" Height=\"84188\" Landscape=\"0\" Width=\"59528\"><PAGEMARGIN Bottom=\"4252\" Footer=\"4252\" Gutter=\"0\" Header=\"4252\" Left=\"5669\" Right=\"5669\" Top=\"2835\"/></PAGEDEF><FOOTNOTESHAPE><AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/><NOTELINE Length=\"5cm\" Type=\"Solid\" Width=\"0.12mm\"/><NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"283\"/><NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/><NOTEPLACEMENT BeneathText=\"false\" Place=\"EachColumn\"/></FOOTNOTESHAPE><ENDNOTESHAPE><AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/><NOTELINE Length=\"14692344\" Type=\"Solid\" Width=\"0.12mm\"/><NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"0\"/><NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/><NOTEPLACEMENT BeneathText=\"false\" Place=\"EndOfDocument\"/></ENDNOTESHAPE><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Both\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Even\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Odd\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL></SECDEF>\n");
				sb.append("		<COLDEF Count=\"1\" Layout=\"Left\" SameGap=\"0\" SameSize=\"true\" Type=\"Newspaper\"/>\n");
				sb.append("		<CHAR>□  " + mngGroupNm + "</CHAR>\n");
				sb.append("	</TEXT></P>\n");
			} else {
				sb.append("	<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"2\" Style=\"0\"><TEXT CharShape=\"5\">\n");
				sb.append("		<CHAR>□  " + mngGroupNm + "</CHAR>\n");
				sb.append("	</TEXT></P>\n");
			}
			sb.append("	<P ParaShape=\"2\" Style=\"0\"><TEXT CharShape=\"6\"/></P>\n");
			sb.append("	<P ParaShape=\"2\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>  ○ 안전점검 내용</CHAR></TEXT><TEXT CharShape=\"6\"/></P>\n");
			sb.append("	<P ParaShape=\"12\" Style=\"0\"><TEXT CharShape=\"6\">\n");
			
			sb.append("		<TABLE BorderFill=\"1\" CellSpacing=\"0\" ColCount=\"2\" PageBreak=\"Cell\" RepeatHeader=\"true\" RowCount=\"2\">\n");
			sb.append("			<SHAPEOBJECT InstId=\"" + getInstanceId() + "\" Lock=\"false\" NumberingType=\"Table\" ZOrder=\"" + getZOrder() + "\"><SIZE Height=\"11250\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"46557\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"false\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"0\" HorzRelTo=\"Para\" TreatAsChar=\"true\" VertAlign=\"Top\" VertOffset=\"0\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"283\" Left=\"283\" Right=\"283\" Top=\"283\"/></SHAPEOBJECT>\n");
			sb.append("			<INSIDEMARGIN Bottom=\"141\" Left=\"510\" Right=\"510\" Top=\"141\"/>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2797\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"25967\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"13\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>점 검 내 용</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2797\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"20590\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"13\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>비 고</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"1\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3142\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"25967\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			if(contents != null) {
				if(contents.length > 0) {
					for(int i=0; i<contents.length; i++) {
							sb.append("<P ParaShape=\"2\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>" + contents[i] + "</CHAR></TEXT></P>\n");
					}
				} else {
					sb.append("<P ParaShape=\"2\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR/></TEXT></P>\n");
				}
			} else {
				sb.append("<P ParaShape=\"2\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR/></TEXT></P>\n");
			}	
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"1\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3142\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"20590\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			if(rm != null) {
				if(rm.length > 0) {
					for(int i=0; i<rm.length; i++) {
							sb.append("<P ParaShape=\"13\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>" + rm[i] + "</CHAR></TEXT></P>\n");
			    	}
				} else {
					sb.append("<P ParaShape=\"13\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR/></TEXT></P>\n");
				}
			} else {
				sb.append("<P ParaShape=\"13\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR/></TEXT></P>\n");
			}
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("		</TABLE>\n");
			sb.append("		<CHAR/>\n");
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ParaShape=\"2\" Style=\"0\"><TEXT CharShape=\"10\"/></P>\n");
			sb.append("	<P ParaShape=\"2\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>  ○ 사진대지</CHAR></TEXT><TEXT CharShape=\"6\"/></P>\n");
			sb.append("	<P ParaShape=\"12\" Style=\"0\"><TEXT CharShape=\"6\">\n");
			
			int rowCount = fileList.size();
			if((rowCount % 2) == 1) {
				rowCount++; //홀수개의 ROW가 되면 짝수로 바꿔준다.
			}
			if(rowCount > 0) {
				//레코드 카운트를 짝수로 맞춘다. 이유는 2개의 데이터당 2개의 행에 출력하기 때문에 홀수의 레코드는 빈칸을 출력하기 위해 짝수로 맞춘다.			
				sb.append("		<TABLE BorderFill=\"1\" CellSpacing=\"0\" ColCount=\"2\" PageBreak=\"Table\" RepeatHeader=\"true\" RowCount=\"" + rowCount + "\">\n");
				sb.append("			<SHAPEOBJECT InstId=\"" + getInstanceId() + "\" Lock=\"false\" NumberingType=\"Table\" TextWrap=\"TopAndBottom\" ZOrder=\"" + getZOrder() + "\"><SIZE Height=\"45578\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"46490\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"false\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"1134\" HorzRelTo=\"Para\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"0\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"283\" Left=\"283\" Right=\"283\" Top=\"283\"/></SHAPEOBJECT>\n");
				sb.append("			<INSIDEMARGIN Bottom=\"141\" Left=\"510\" Right=\"510\" Top=\"141\"/>\n");
				//루프로 돌릴 때 한 루프당 2개의 데이터를 2개의 행에 표현하기 때문에 루트는 데이터 갯수의 반만 돌린다. 
				int loopEnd = rowCount / 2;
				int rowAddr = 0; //각 cell element의 rowAddr속성을 변경시켜주기 위한 변수
				
				int listIndex = 0; //루프 내에서 사용할 실제 이미지 리스트의 인덱스 
				int listSize = fileList.size(); // 이미지 리스트 개수
	
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
					sb.append("			<ROW>\n");
					sb.append("				<CELL BorderFill=\"1\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"18435\" Protect=\"false\" RowAddr=\"" + rowAddr + "\" RowSpan=\"1\" Width=\"23245\">\n");
					sb.append(getReportListPicture(leftImageId));
					sb.append("				</CELL>\n");
					sb.append("				<CELL BorderFill=\"1\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"18435\" Protect=\"false\" RowAddr=\"" + rowAddr + "\" RowSpan=\"1\" Width=\"23245\">\n");
					sb.append(getReportListPicture(rightImageId));
					sb.append("				</CELL>\n");
					sb.append("			</ROW>\n");
					rowAddr++;
					sb.append("			<ROW>\n");
					sb.append("				<CELL BorderFill=\"1\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2131\" Protect=\"false\" RowAddr=\"" + rowAddr + "\" RowSpan=\"1\" Width=\"23245\">\n");
					sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
					if(leftFileSj != null) {
						sb.append("						<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"11\"><CHAR>" + leftFileSj + "</CHAR></TEXT></P>\n");
					} else {
						sb.append("						<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"11\"><CHAR/></TEXT></P>\n");
					}
					sb.append("					</PARALIST>\n");
					sb.append("				</CELL>\n");
					sb.append("				<CELL BorderFill=\"1\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2131\" Protect=\"false\" RowAddr=\"" + rowAddr + "\" RowSpan=\"1\" Width=\"23245\">\n");
					sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
					if(rightFileSj != null) {
						sb.append("						<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"11\"><CHAR>" + rightFileSj + "</CHAR></TEXT></P>\n");
					} else {
						sb.append("						<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"11\"><CHAR/></TEXT></P>\n");
					}
					sb.append("					</PARALIST>\n");
					sb.append("				</CELL>\n");
					sb.append("			</ROW>\n");
					rowAddr++;
				}
				sb.append("		</TABLE>\n");
			}
			sb.append("		<CHAR/>\n");
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ParaShape=\"0\" Style=\"0\"><TEXT CharShape=\"1\"/></P>\n");
						
			return sb;
		}
		
		/**HWPML 용 안전점검결과 사진 엘리먼트 또는 빈 공백 엘리먼트(id가 0인 경우)를 xml 문자열로 가져온다.*/
		public StringBuilder getReportListPicture(int id) {
			StringBuilder sb = new StringBuilder();
			if(id > 0) {
				sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
				sb.append("						<P ParaShape=\"13\" Style=\"0\"><TEXT CharShape=\"1\">\n");
				sb.append("							<PICTURE Reverse=\"false\">\n");
				sb.append("								<SHAPEOBJECT InstId=\"" + getInstanceId() + "\" Lock=\"false\" NumberingType=\"Figure\" ZOrder=\"" + getZOrder() + "\"><SIZE Height=\"14944\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"22561\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"false\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"0\" HorzRelTo=\"Para\" TreatAsChar=\"true\" VertAlign=\"Top\" VertOffset=\"0\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/></SHAPEOBJECT>\n");
				sb.append("								<SHAPECOMPONENT CurHeight=\"14944\" CurWidth=\"22560\" GroupLevel=\"0\" HorzFlip=\"false\" InstID=\"" + getInstanceId() + "\" OriHeight=\"57600\" OriWidth=\"76800\" VertFlip=\"false\" XPos=\"0\" YPos=\"0\"><ROTATIONINFO Angle=\"0\" CenterX=\"11280\" CenterY=\"7472\"/><RENDERINGINFO><TRANSMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"0.29376\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"0.25944\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/></RENDERINGINFO></SHAPECOMPONENT>\n");
				sb.append("								<IMAGERECT X0=\"0\" X1=\"76800\" X2=\"76800\" X3=\"0\" Y0=\"0\" Y1=\"0\" Y2=\"57600\" Y3=\"57600\"/>\n");
				sb.append("								<IMAGECLIP Bottom=\"57600\" Left=\"0\" Right=\"76800\" Top=\"0\"/>\n");
				sb.append("								<INSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/>\n");
				sb.append("								<IMAGE Alpha=\"0\" BinItem=\""+ id + "\" Bright=\"0\" Contrast=\"0\" Effect=\"RealPic\"/>\n");
				sb.append("							</PICTURE>\n");
				sb.append("							<CHAR/>\n");
				sb.append("						</TEXT></P>\n");
				sb.append("					</PARALIST>\n");
			} else {
				sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
				sb.append("						<P ParaShape=\"13\" Style=\"0\"><TEXT CharShape=\"1\">\n");
				sb.append("							<CHAR/>\n");
				sb.append("						</TEXT></P>\n");
				sb.append("					</PARALIST>\n");
			}
			return sb;
		}
		
		/**HWPML 용 안전점검결과 TAIL 엘리먼트를 문자열로 가져온다.
		 * @throws Exception */
		public StringBuilder getReportTail(Map<String, Integer> imageIndexes) throws Exception {
			StringBuilder sb = new StringBuilder();
			Iterator<String> it = imageIndexes.keySet().iterator();
			int count = imageIndexes.keySet().size();
			sb.append("<TAIL>\n");
			if(count > 0) {
				sb.append("<BINDATASTORAGE>\n");
				while(it.hasNext()) {
					String fileName = it.next();
					int id = imageIndexes.get(fileName);
					fileName = EgovProperties.getProperty("qcAttach.fileStorePath") + fileName;
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
	
	/**
	 * 시설물 점검표 한글 문서 다운로드 - 김종민 추가 작업 2015.10.28
	 * @param vo
	 * @return list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String selectQcMngResultListReportHWPML(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		String result = null;
		List<EgovMap> qcResultItemList = null;
		EgovMap mngGroupInfo = null;
		List<EgovMap> qcChargerList = new ArrayList<EgovMap>();
		
		//점검내역조회
		EgovMap qcDetailData = gamFcltyQcwWrtMngDao.selectQcMngDtlsDetail(searchVO);
	
		if(qcDetailData != null) {
			String fcltsJobSe = (String)qcDetailData.get("fcltsJobSe");
			
			// 점검 결과 항목 리스트 조회
			qcResultItemList = (fcltsJobSe.equals("M")) ? (List<EgovMap>) gamFcltyQcwWrtMngDao.selectMechQcMngResultItemList(searchVO) : 
						(List<EgovMap>) gamFcltyQcwWrtMngDao.selectQcMngResultItemList(searchVO);
			
			// 토목일 경우 시설물 그룹 정보 조회
			if(fcltsJobSe.equals("C")) {
				mngGroupInfo = gamFcltyQcwWrtMngDao.selectFcltsMngGroupInfo(searchVO);
			}
			
			// 작성자를 가져와서 ,로 나누어서 시설물관리자 정보를 가져온다.
			String wrtUsr = (String) qcDetailData.get("wrtUsr");
			if(wrtUsr != null) {
				String qcUsers[] = wrtUsr.split(",");
				for(String qcUser : qcUsers) {
					Map<String, String> chargerNm = new HashMap<String, String>();
					chargerNm.put("chargerNm", qcUser.replace(" ", ""));
					EgovMap chargerInfo = gamFcltyQcwWrtMngDao.selectChargerInfo(chargerNm);
					if(chargerInfo  != null) {
						qcChargerList.add(chargerInfo);
					}
				}
			}
		}
		
		MakeQcMngResultItemsHwpReport report = new MakeQcMngResultItemsHwpReport(qcDetailData, qcResultItemList, qcChargerList,  mngGroupInfo);
		
		result = report.getHwpReport();;
		
		return result;
	}
	
	
	
	/** 시설물 점검표 HML처리 INNER CLASS */
	class MakeQcMngResultItemsHwpReport {
		private EgovMap qcDetailData = null;
		private List<EgovMap> qcResultItemList = null;
		private List<EgovMap> qcChargerList = null;
		private EgovMap mngGroupInfo = null;
		private Map<String, Object> chargerInfo1 = null;
		private Map<String, Object> chargerInfo2 = null;
		private String fcltsMngGroupNm = null;
		private String docDate = null;
		
		public MakeQcMngResultItemsHwpReport(EgovMap qcDetailData, List<EgovMap> qcResultItemList, List<EgovMap> qcChargerList, EgovMap mngGroupInfo) {
			this.qcDetailData = qcDetailData;
			this.qcResultItemList = qcResultItemList;
			this.qcChargerList = qcChargerList;
			this.mngGroupInfo = mngGroupInfo;
		}
		
		public String getHwpReport() throws Exception{
			String result = null;
			if(qcDetailData != null) {
				initData();
				String fcltsJobSe = (String)qcDetailData.get("fcltsJobSe");
				if(fcltsJobSe.equals("C")) {
					result = getCivilHwpReport(); //토목
				} else if(fcltsJobSe.equals("A")) {
					result = getArchHwpReport(); //건축
				} else if(fcltsJobSe.equals("E")) {
					result = getElectyHwpReport(); //전력
				} else if(fcltsJobSe.equals("I")) {
					result = getInfoCommHwpReport(); //정보통신
				} else if(fcltsJobSe.equals("M")) {
					//기계
					String mechFcltsSe = (String)qcDetailData.get("mechFcltsSe");
					if(mechFcltsSe != null) {
						if(mechFcltsSe.equals("1")) {
							result = getCraneMechHwpReport(); //하역장비
						} else if(mechFcltsSe.equals("3")) {
							result = getArchMechHwpReport(); //건설설비기계
						} else {
							result = getTempHwpReport(); //빈문서
						}
					} else {
						result = getTempHwpReport(); //빈문서
					}
				} else {
					result = getTempHwpReport(); //빈문서
				}
			}
			else {
				result = getTempHwpReport(); //빈문서
			}
			return result;
		}
		
		// 데이터 초기화
		protected void initData() throws Exception {
			fcltsMngGroupNm = (String) qcDetailData.get("fcltsMngGroupNm");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM일 dd일 HH시 mm분 ss초", Locale.KOREA);
			docDate = formatter.format(new Date());
			
			// 점검자가 2명 이하만 데이터를 등록한다.
			if(qcChargerList != null) {
				for(int i=0; i<qcChargerList.size(); i++) {
					EgovMap chargerItem = qcChargerList.get(i);
					if(i==0) {
						chargerInfo1 = new HashMap<String, Object>();
						fillChargerInfo(chargerItem, chargerInfo1);
					}
					if(i==1) {
						chargerInfo2 = new HashMap<String, Object>();
						fillChargerInfo(chargerItem, chargerInfo2);
					}
				}
			}
		}

		// 점검자 정보와 도장이미지 정보를 채움
		protected void fillChargerInfo(EgovMap chargerItem, Map<String, Object> chargerInfo) throws Exception {
			//chargerInfo = new HashMap<String, Object>();
			chargerInfo.put("chargerNm", (chargerItem.get("chargerNm") != null) ? chargerItem.get("chargerNm") : "");
			chargerInfo.put("signFileNm", (chargerItem.get("signFileNmPhysicl") != null) ? chargerItem.get("signFileNmPhysicl") : null);
			if(chargerInfo.get("signFileNm") != null) {
				String fileNm = EgovProperties.getProperty("prtfclty.fileStorePath") + (String) chargerInfo.get("signFileNm");
				chargerInfo.put("fileExt", fileNm.substring(fileNm.lastIndexOf(".") + 1, fileNm.length()));
				File file = new File(fileNm);
				if(file.exists()) {
					chargerInfo.put("fileExists", new Boolean(true));
					chargerInfo.put("binData", fileToBase64(fileNm));
					chargerInfo.put("size", ((String)chargerInfo.get("binData")).length());
				} else {
					chargerInfo.put("fileExists", new Boolean(false));
					chargerInfo.put("binData", null);
					chargerInfo.put("size", 0);
				}
			} else {
				chargerInfo.put("fileExt", null);
				chargerInfo.put("fileExists", new Boolean(false));
				chargerInfo.put("binData", null);
				chargerInfo.put("size", 0);
			}
		}
				
		// 결과항목코드로 점검결과기호 얻기
		protected String getResultItemSymbol(String itemCd) {
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
		
		// 결과항목코드로 점검결과내용 얻기(토목일 경우에만 사용)
		protected String getResultItemContent(String itemCd) {
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
		
		// 점검결과내용이 있는지 없는지 체크(토목일 경우에만 사용)
		protected boolean isValidResultItemContent(String itemCd) {
			String value = getResultItemContent(itemCd).replace(" ", "");
			return !value.equals("");
		}
		
		// 토목 하위항목 데이터유무를 체크하여 전체 rowCount 구하기
		protected int getCivilRowCount() {
			int result = 5;
			if(isValidResultItemContent("C01010000") || isValidResultItemContent("C01020000") || isValidResultItemContent("C01030000") || isValidResultItemContent("C01040000") ){
				result++;
			}
			if(isValidResultItemContent("C02010000") || isValidResultItemContent("C02020000") || isValidResultItemContent("C02030000") 
					|| isValidResultItemContent("C02040000") || isValidResultItemContent("C02050000") || isValidResultItemContent("C02060000") ){
				result++;
			}
			if(isValidResultItemContent("C03010000") || isValidResultItemContent("C03020000") || isValidResultItemContent("C03030000") 
					|| isValidResultItemContent("C03040000") || isValidResultItemContent("C03050000") ){
				result++;
			}
			if(isValidResultItemContent("C04010000") || isValidResultItemContent("C04020000") ){
				result++;
			}
			if(isValidResultItemContent("C05010000")){
				result++;
			}
			return result;
		}
		
		// HEAD에 들어갈 파일 정보 엘리먼트 구성
		protected String getBinItemListElement() {
			String result = "";
			int count = 0;
			if(chargerInfo1 != null) {
				if((Boolean)chargerInfo1.get("fileExists")) {
					count++;
					result += "<BINITEM BinData=\"" + count + "\" Format=\"" + (String)chargerInfo1.get("fileExt") + "\" Type=\"Embedding\"/>\n";
					chargerInfo1.put("binId", count);
				}
			}
			if(chargerInfo2 != null) {
				if((Boolean)chargerInfo2.get("fileExists")) {
					count++;
					result += "<BINITEM BinData=\"" + count + "\" Format=\"" + (String)chargerInfo2.get("fileExt") + "\" Type=\"Embedding\"/>\n";
					chargerInfo2.put("binId", count);
				}
			}
			if(count > 0) {
				result = "<BINDATALIST Count=\"" + count + "\">\n" + result + "</BINDATALIST>\n";
			}
			return result;
		}
		
		// TAIL에 들어갈 파일 내용 앨리먼트 구성
		protected String getBinDataListElement() {
			String result = "";
			int count = 0;
			if(chargerInfo1 != null) {
				if((Boolean)chargerInfo1.get("fileExists")) {
					count++;
					result += "<BINDATA Encoding=\"Base64\" Id=\"" + (Integer)chargerInfo1.get("binId") + "\" Size=\"" + (Integer)chargerInfo1.get("size") + "\">" 
								+ (String)chargerInfo1.get("binData") +  "</BINDATA>\n";
				}
			}
			if(chargerInfo2 != null) {
				if((Boolean)chargerInfo2.get("fileExists")) {
					count++;
					result += "<BINDATA Encoding=\"Base64\" Id=\"" + (Integer)chargerInfo2.get("binId") + "\" Size=\"" + (Integer)chargerInfo2.get("size") + "\">" 
								+ (String)chargerInfo2.get("binData") +  "</BINDATA>\n";
				}
			}
			if(count > 0) {
				result = "<BINDATASTORAGE>\n" + result + "</BINDATASTORAGE>\n";
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
		
		/**토목 시설물 점검표*/  
		protected String getCivilHwpReport() {
			StringBuilder sb = new StringBuilder();
			
			String prtAtCodeNm = (mngGroupInfo.get("prtAtCodeNm") != null) ? (String)mngGroupInfo.get("prtAtCodeNm") + "항" : "      ";
			String fcltsMngGroupNm = (mngGroupInfo.get("fcltsMngGroupNm") != null) ? (String) mngGroupInfo.get("fcltsMngGroupNm") : "";
			String loc = (mngGroupInfo.get("loc") != null) ? (String) mngGroupInfo.get("loc") : "";
			String fcltsGbnNm = ((mngGroupInfo.get("fcltsGbnNm") != null) ? (String)mngGroupInfo.get("fcltsGbnNm") : "-") + " / -";
			String bldYear = (mngGroupInfo.get("bldYear") != null) ? (String) mngGroupInfo.get("bldYear") + " 년" : "";
			String cnstrtr = (mngGroupInfo.get("cnstrtr") != null) ? (String) mngGroupInfo.get("cnstrtr") : "";
			String flawEndDt = (mngGroupInfo.get("flawEndDt") != null) ? (String) mngGroupInfo.get("flawEndDtYear") + " 년 " + (String) mngGroupInfo.get("flawEndDtMonth") + " 월 " + (String) mngGroupInfo.get("flawEndDtDay") + " 일"  : "";

			int rowCount = getCivilRowCount();
			int rowAddr = 4;
			
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>\n");
			sb.append("<HWPML Style=\"embed\" SubVersion=\"7.0.0.0\" Version=\"2.7\">\n");
			sb.append("<HEAD SecCnt=\"1\">\n");
			sb.append("	<DOCSUMMARY><AUTHOR>YGPA GIS Assets Management System</AUTHOR><DATE>" + docDate + "</DATE></DOCSUMMARY>\n");
			sb.append("	<DOCSETTING><BEGINNUMBER Endnote=\"1\" Equation=\"1\" Footnote=\"1\" Page=\"1\" Picture=\"1\" Table=\"1\"/><CARETPOS List=\"31\" Para=\"0\" Pos=\"5\"/></DOCSETTING>\n");
			sb.append("	<MAPPINGTABLE>\n");
			sb.append(getBinItemListElement());
			sb.append("		<FACENAMELIST><FONTFACE Count=\"5\" Lang=\"Hangul\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"휴먼옛체\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"4\" StrokeVariation=\"1\" Weight=\"5\" XHeight=\"1\"/></FONT><FONT Id=\"3\" Name=\"휴먼명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT><FONT Id=\"4\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"5\" Lang=\"Latin\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT><FONT Id=\"3\" Name=\"한양궁서\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"4\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT><FONT Id=\"4\" Name=\"HCI Poppy\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Hanja\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Japanese\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Other\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Symbol\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"User\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE></FACENAMELIST>\n");
			sb.append("		<BORDERFILLLIST Count=\"4\"><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"1\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"2\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"3\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/><FILLBRUSH><WINDOWBRUSH Alpha=\"0\" FaceColor=\"15066597\" HatchColor=\"0\"/></FILLBRUSH></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"4\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"None\" Width=\"0.1mm\"/><RIGHTBORDER Type=\"None\" Width=\"0.1mm\"/><TOPBORDER Type=\"None\" Width=\"0.1mm\"/><BOTTOMBORDER Type=\"None\" Width=\"0.1mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.1mm\"/><FILLBRUSH><WINDOWBRUSH Alpha=\"0\" FaceColor=\"4294967295\" HatchColor=\"0\"/></FILLBRUSH></BORDERFILL></BORDERFILLLIST>\n");
			sb.append("		<CHARSHAPELIST Count=\"12\"><CHARSHAPE BorderFillId=\"0\" Height=\"1000\" Id=\"0\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"4\" Hanja=\"2\" Japanese=\"2\" Latin=\"2\" Other=\"2\" Symbol=\"2\" User=\"2\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"2000\" Id=\"1\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"2\" Hanja=\"2\" Japanese=\"2\" Latin=\"3\" Other=\"2\" Symbol=\"2\" User=\"2\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"2000\" Id=\"2\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"2\" Hanja=\"2\" Japanese=\"2\" Latin=\"3\" Other=\"2\" Symbol=\"2\" User=\"2\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><BOLD/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1000\" Id=\"3\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1000\" Id=\"4\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"5\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"6\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"95\" Hanja=\"95\" Japanese=\"95\" Latin=\"95\" Other=\"95\" Symbol=\"95\" User=\"95\"/><CHARSPACING Hangul=\"-5\" Hanja=\"-5\" Japanese=\"-5\" Latin=\"-5\" Other=\"-5\" Symbol=\"-5\" User=\"-5\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"7\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"95\" Hanja=\"95\" Japanese=\"95\" Latin=\"95\" Other=\"95\" Symbol=\"95\" User=\"95\"/><CHARSPACING Hangul=\"-5\" Hanja=\"-5\" Japanese=\"-5\" Latin=\"-5\" Other=\"-5\" Symbol=\"-5\" User=\"-5\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1300\" Id=\"8\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"3\" Hanja=\"1\" Japanese=\"1\" Latin=\"4\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"-10\" Hanja=\"-10\" Japanese=\"-10\" Latin=\"-10\" Other=\"-10\" Symbol=\"-10\" User=\"-10\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1300\" Id=\"9\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"3\" Hanja=\"1\" Japanese=\"1\" Latin=\"4\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"-10\" Hanja=\"-10\" Japanese=\"-10\" Latin=\"-10\" Other=\"-10\" Symbol=\"-10\" User=\"-10\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><BOLD/></CHARSHAPE><CHARSHAPE BorderFillId=\"4\" Height=\"1300\" Id=\"10\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"3\" Hanja=\"1\" Japanese=\"1\" Latin=\"4\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"-10\" Hanja=\"-10\" Japanese=\"-10\" Latin=\"-10\" Other=\"-10\" Symbol=\"-10\" User=\"-10\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1300\" Id=\"11\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE></CHARSHAPELIST>\n");
			sb.append("		<TABDEFLIST Count=\"3\"><TABDEF AutoTabLeft=\"false\" AutoTabRight=\"false\" Id=\"0\"/><TABDEF AutoTabLeft=\"true\" AutoTabRight=\"false\" Id=\"1\"/><TABDEF AutoTabLeft=\"false\" AutoTabRight=\"true\" Id=\"2\"/></TABDEFLIST>\n");
			sb.append("		<NUMBERINGLIST Count=\"1\"><NUMBERING Id=\"1\" Start=\"0\"><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"1\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^1.</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"2\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^2.</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"3\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^3)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"4\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^4)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"5\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">(^5)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"6\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">(^6)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"7\" NumFormat=\"CircledDigit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^7</PARAHEAD></NUMBERING></NUMBERINGLIST>\n");
			sb.append("		<PARASHAPELIST Count=\"16\"><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"0\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"1\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"2\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"true\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"4\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"3\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"1\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"-2620\" Left=\"0\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"4\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"4\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"4\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"5\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"3000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"4\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"6\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"2000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"4\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"7\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"1\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"4000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"4\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"8\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"2\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"6000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"4\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"9\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"3\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"8000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"4\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"10\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"4\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"10000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"4\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"11\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"5\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"12000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"4\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"12\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"6\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"14000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"4\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"13\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"2\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"150\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"4\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"14\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"15\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"100\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE></PARASHAPELIST>\n");
			sb.append("		<STYLELIST Count=\"15\"><STYLE CharShape=\"4\" EngName=\"Normal\" Id=\"0\" LangId=\"1042\" LockForm=\"0\" Name=\"바탕글\" NextStyle=\"0\" ParaShape=\"4\" Type=\"Para\"/><STYLE CharShape=\"4\" EngName=\"Body\" Id=\"1\" LangId=\"1042\" LockForm=\"0\" Name=\"본문\" NextStyle=\"1\" ParaShape=\"5\" Type=\"Para\"/><STYLE CharShape=\"4\" EngName=\"Outline 1\" Id=\"2\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 1\" NextStyle=\"2\" ParaShape=\"6\" Type=\"Para\"/><STYLE CharShape=\"4\" EngName=\"Outline 2\" Id=\"3\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 2\" NextStyle=\"3\" ParaShape=\"7\" Type=\"Para\"/><STYLE CharShape=\"4\" EngName=\"Outline 3\" Id=\"4\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 3\" NextStyle=\"4\" ParaShape=\"8\" Type=\"Para\"/><STYLE CharShape=\"4\" EngName=\"Outline 4\" Id=\"5\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 4\" NextStyle=\"5\" ParaShape=\"9\" Type=\"Para\"/><STYLE CharShape=\"4\" EngName=\"Outline 5\" Id=\"6\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 5\" NextStyle=\"6\" ParaShape=\"10\" Type=\"Para\"/><STYLE CharShape=\"4\" EngName=\"Outline 6\" Id=\"7\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 6\" NextStyle=\"7\" ParaShape=\"11\" Type=\"Para\"/><STYLE CharShape=\"4\" EngName=\"Outline 7\" Id=\"8\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 7\" NextStyle=\"8\" ParaShape=\"12\" Type=\"Para\"/><STYLE CharShape=\"3\" EngName=\"Page Number\" Id=\"9\" LangId=\"1042\" LockForm=\"0\" Name=\"쪽 번호\" NextStyle=\"9\" ParaShape=\"4\" Type=\"Para\"/><STYLE CharShape=\"5\" EngName=\"Header\" Id=\"10\" LangId=\"1042\" LockForm=\"0\" Name=\"머리말\" NextStyle=\"10\" ParaShape=\"13\" Type=\"Para\"/><STYLE CharShape=\"6\" EngName=\"Footnote\" Id=\"11\" LangId=\"1042\" LockForm=\"0\" Name=\"각주\" NextStyle=\"11\" ParaShape=\"3\" Type=\"Para\"/><STYLE CharShape=\"6\" EngName=\"Endnote\" Id=\"12\" LangId=\"1042\" LockForm=\"0\" Name=\"미주\" NextStyle=\"12\" ParaShape=\"3\" Type=\"Para\"/><STYLE CharShape=\"7\" EngName=\"Memo\" Id=\"13\" LangId=\"1042\" LockForm=\"0\" Name=\"메모\" NextStyle=\"13\" ParaShape=\"2\" Type=\"Para\"/><STYLE CharShape=\"1\" Id=\"14\" LangId=\"1042\" LockForm=\"0\" Name=\"중간제목(옛체20)\" NextStyle=\"14\" ParaShape=\"1\" Type=\"Para\"/></STYLELIST>\n");
			sb.append("	</MAPPINGTABLE>\n");
			sb.append("</HEAD>\n");
			sb.append("<BODY><SECTION Id=\"0\">\n");
			sb.append("	<P ColumnBreak=\"false\" PageBreak=\"false\" ParaShape=\"1\" Style=\"14\"><TEXT CharShape=\"2\">\n");
			sb.append("		<SECDEF CharGrid=\"0\" FirstBorder=\"false\" FirstFill=\"false\" LineGrid=\"0\" OutlineShape=\"1\" SpaceColumns=\"1134\" TabStop=\"8000\" TextDirection=\"0\" TextVerticalWidthHead=\"0\"><STARTNUMBER Equation=\"0\" Figure=\"0\" Page=\"0\" PageStartsOn=\"Both\" Table=\"0\"/><HIDE Border=\"false\" EmptyLine=\"false\" Fill=\"false\" Footer=\"false\" Header=\"false\" MasterPage=\"false\" PageNumPos=\"false\"/><PAGEDEF GutterType=\"LeftOnly\" Height=\"84188\" Landscape=\"0\" Width=\"59528\"><PAGEMARGIN Bottom=\"0\" Footer=\"4252\" Gutter=\"0\" Header=\"7087\" Left=\"4252\" Right=\"4110\" Top=\"0\"/></PAGEDEF><FOOTNOTESHAPE><AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/><NOTELINE Length=\"5cm\" Type=\"Solid\" Width=\"0.12mm\"/><NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"283\"/><NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/><NOTEPLACEMENT BeneathText=\"false\" Place=\"EachColumn\"/></FOOTNOTESHAPE><ENDNOTESHAPE><AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/><NOTELINE Length=\"14692344\" Type=\"Solid\" Width=\"0.12mm\"/><NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"0\"/><NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/><NOTEPLACEMENT BeneathText=\"false\" Place=\"EndOfDocument\"/></ENDNOTESHAPE><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Both\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Even\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Odd\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL></SECDEF>\n");
			sb.append("		<COLDEF Count=\"1\" Layout=\"Left\" SameGap=\"0\" SameSize=\"true\" Type=\"Newspaper\"/>\n");
			sb.append("		<CHAR>토목 시설물 점검표</CHAR></TEXT><TEXT CharShape=\"0\"><CHAR/>\n");
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ParaShape=\"0\" Style=\"0\"><TEXT CharShape=\"8\">\n");
			if(chargerInfo1 != null) {
				if((Boolean)chargerInfo1.get("fileExists")) {
					sb.append("		<PICTURE Reverse=\"false\">\n");
					sb.append("			<SHAPEOBJECT InstId=\"2058515283\" Lock=\"false\" NumberingType=\"Figure\" TextWrap=\"BehindText\" ZOrder=\"1\"><SIZE Height=\"4500\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"3840\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"true\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"44040\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"1060\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/></SHAPEOBJECT>\n");
					sb.append("			<SHAPECOMPONENT GroupLevel=\"0\" HorzFlip=\"false\" InstID=\"984773460\" OriHeight=\"4500\" OriWidth=\"3840\" VertFlip=\"false\" XPos=\"0\" YPos=\"0\"><ROTATIONINFO Angle=\"0\" CenterX=\"1920\" CenterY=\"2250\"/><RENDERINGINFO><TRANSMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/></RENDERINGINFO></SHAPECOMPONENT>\n");
					sb.append("			<IMAGERECT X0=\"0\" X1=\"3840\" X2=\"3840\" X3=\"0\" Y0=\"0\" Y1=\"0\" Y2=\"4500\" Y3=\"4500\"/>\n");
					sb.append("			<IMAGECLIP Bottom=\"4500\" Left=\"0\" Right=\"3840\" Top=\"0\"/>\n");
					sb.append("			<INSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/>\n");
					sb.append("			<IMAGE Alpha=\"0\" BinItem=\"" + (Integer)chargerInfo1.get("binId") + "\" Bright=\"0\" Contrast=\"0\" Effect=\"RealPic\"/>\n");
					sb.append("		</PICTURE>\n");
				}
			}
			sb.append("		<CHAR/>\n");
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ParaShape=\"0\" Style=\"0\"><TEXT CharShape=\"8\">\n");
			//sb.append("		<CHAR>    항  명 :<TAB/>" + prtAtCodeNm + "<TAB/><TAB/><TAB/>       <TAB/>     점검일 :  " + ((qcDetailData.get("wrtDtHwp") != null) ? (String) qcDetailData.get("wrtDtHwp") : "" ) + "</CHAR>\n");
			sb.append("		<CHAR>            <TAB/><TAB/><TAB/><TAB/>       <TAB/>     점검일 :  " + ((qcDetailData.get("wrtDtHwp") != null) ? (String) qcDetailData.get("wrtDtHwp") : "" ) + "</CHAR>\n");
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ParaShape=\"0\" Style=\"0\"><TEXT CharShape=\"8\">\n");
			if(chargerInfo2 != null) {
				if((Boolean)chargerInfo2.get("fileExists")) {
					sb.append("		<PICTURE Reverse=\"false\">\n");
					sb.append("			<SHAPEOBJECT InstId=\"2058515287\" Lock=\"false\" NumberingType=\"Figure\" TextWrap=\"BehindText\" ZOrder=\"3\"><SIZE Height=\"4500\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"3840\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"true\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"44160\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"1180\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/></SHAPEOBJECT>\n");
					sb.append("			<SHAPECOMPONENT GroupLevel=\"0\" HorzFlip=\"false\" InstID=\"984773464\" OriHeight=\"4500\" OriWidth=\"3840\" VertFlip=\"false\" XPos=\"0\" YPos=\"0\"><ROTATIONINFO Angle=\"0\" CenterX=\"1920\" CenterY=\"2250\"/><RENDERINGINFO><TRANSMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/></RENDERINGINFO></SHAPECOMPONENT>\n");
					sb.append("			<IMAGERECT X0=\"0\" X1=\"3840\" X2=\"3840\" X3=\"0\" Y0=\"0\" Y1=\"0\" Y2=\"4500\" Y3=\"4500\"/>\n");
					sb.append("			<IMAGECLIP Bottom=\"4500\" Left=\"0\" Right=\"3840\" Top=\"0\"/>\n");
					sb.append("			<INSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/>\n");
					sb.append("			<IMAGE Alpha=\"0\" BinItem=\"" + (Integer)chargerInfo2.get("binId") + "\" Bright=\"0\" Contrast=\"0\" Effect=\"RealPic\"/>\n");
					sb.append("		</PICTURE>\n");
				}
			}
			if(chargerInfo1 != null) {			
				sb.append("		<CHAR><TAB/><TAB/><TAB/><TAB/><TAB/><TAB/><TAB/>     점검자 :<TAB/>"  + ((chargerInfo1.get("chargerNm") != null) ? (String)chargerInfo1.get("chargerNm") :  "      ") +  "         (인)</CHAR>\n");
			} else {
				sb.append("		<CHAR><TAB/><TAB/><TAB/><TAB/><TAB/><TAB/><TAB/>     점검자 :<TAB/>               (인)</CHAR>\n");
			}
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ParaShape=\"0\" Style=\"0\"><TEXT CharShape=\"11\">\n");
			if(chargerInfo2 != null) {						
				sb.append("		<CHAR><TAB/><TAB/><TAB/><TAB/><TAB/><TAB/><TAB/><TAB/><TAB/></CHAR></TEXT><TEXT CharShape=\"8\"><CHAR>"  + ((chargerInfo2.get("chargerNm") != null) ? (String)chargerInfo2.get("chargerNm") :  "      ") +  "         (인)</CHAR>\n");
			} else {
				sb.append("		<CHAR/>\n");
			}
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"8\">\n");
			sb.append("		<TABLE BorderFill=\"1\" CellSpacing=\"0\" ColCount=\"6\" PageBreak=\"None\" RepeatHeader=\"true\" RowCount=\"" + rowCount + "\">\n");
			sb.append("			<SHAPEOBJECT InstId=\"2058515282\" Lock=\"false\" NumberingType=\"Table\" ZOrder=\"0\"><SIZE Height=\"60400\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"48208\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"false\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"0\" HorzRelTo=\"Column\" TreatAsChar=\"true\" VertAlign=\"Top\" VertOffset=\"0\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/></SHAPEOBJECT>\n");
			sb.append("			<INSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3284\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"9212\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>시 설 명</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"2\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3284\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"14040\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + fcltsMngGroupNm + "</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"2\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3284\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"9496\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>시설물소재지</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"2\" ColAddr=\"4\" ColSpan=\"2\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3284\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"15460\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + loc + "</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3284\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"9212\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>시설개요</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"2\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3284\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"14040\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR></CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"3\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3284\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"12336\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>종별 / 상태등급</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"2\" ColAddr=\"5\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3284\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"12620\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + fcltsGbnNm + "</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3284\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"9212\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>준공년도</CHAR></TEXT><TEXT CharShape=\"4\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"2\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3284\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"14040\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + bldYear + "</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"3\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3284\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"12336\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>하 자 만 료 일</CHAR></TEXT><TEXT CharShape=\"4\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"2\" ColAddr=\"5\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3284\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"12620\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + flawEndDt + "</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3284\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"9212\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>시설규모</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"2\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3284\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"14040\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR></CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"3\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3284\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"12336\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>시  공  회  사</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"2\" ColAddr=\"5\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3284\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"12620\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + cnstrtr + "</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4584\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"9212\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>점검항목</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"2\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4584\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"20349\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>점 검 세 부 항 목</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"3\" ColSpan=\"3\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4584\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"18647\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>점  검  내  용</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("			</ROW>\n");
			
			if(isValidResultItemContent("C01010000") || isValidResultItemContent("C01020000") || isValidResultItemContent("C01030000") || isValidResultItemContent("C01040000") ){
				rowAddr++;
				sb.append("			<ROW>\n");
				sb.append("				<CELL BorderFill=\"2\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"8952\" Protect=\"false\" RowAddr=\"" + rowAddr + "\" RowSpan=\"1\" Width=\"9212\">\n");
				sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
				sb.append("						<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>구조물 현황</CHAR></TEXT></P>\n");
				sb.append("					</PARALIST>\n");
				sb.append("				</CELL>\n");
				sb.append("				<CELL BorderFill=\"2\" ColAddr=\"1\" ColSpan=\"2\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"8952\" Protect=\"false\" RowAddr=\"" + rowAddr + "\" RowSpan=\"1\" Width=\"20349\">\n");
				sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
				if(isValidResultItemContent("C01010000")) {
					sb.append("						<P ParaShape=\"0\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>ㅇ 시설물 용도 변경여부</CHAR></TEXT></P>\n");
				}
				if(isValidResultItemContent("C01020000")) {
					sb.append("						<P ParaShape=\"0\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>ㅇ 부두의 법선 변위여부</CHAR></TEXT></P>\n");
				}
				if(isValidResultItemContent("C01030000")) {
					sb.append("						<P ParaShape=\"0\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>ㅇ 안벽법면의 수직도</CHAR></TEXT></P>\n");
				}
				if(isValidResultItemContent("C01040000")) {
					sb.append("						<P ParaShape=\"0\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>ㅇ 교량 변위여부</CHAR></TEXT></P>\n");
				}
				sb.append("					</PARALIST>\n");
				sb.append("				</CELL>\n");
				sb.append("				<CELL BorderFill=\"2\" ColAddr=\"3\" ColSpan=\"3\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"8952\" Protect=\"false\" RowAddr=\"" + rowAddr + "\" RowSpan=\"1\" Width=\"18647\">\n");
				sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
				if(isValidResultItemContent("C01010000")) {
					sb.append("						<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>" + getResultItemContent("C01010000") + "</CHAR></TEXT></P>\n");
				}
				if(isValidResultItemContent("C01020000")) {
					sb.append("						<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>" + getResultItemContent("C01020000") + "</CHAR></TEXT></P>\n");
				}
				if(isValidResultItemContent("C01030000")) {
					sb.append("						<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>" + getResultItemContent("C01030000") + "</CHAR></TEXT></P>\n");
				}
				if(isValidResultItemContent("C01040000")) {
					sb.append("						<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>" + getResultItemContent("C01040000") + "</CHAR></TEXT></P>\n");
				}
				sb.append("					</PARALIST>\n");
				sb.append("				</CELL>\n");
				sb.append("			</ROW>\n");
			}
			
			if(isValidResultItemContent("C02010000") || isValidResultItemContent("C02020000") || isValidResultItemContent("C02030000") 
					|| isValidResultItemContent("C02040000") || isValidResultItemContent("C02050000") || isValidResultItemContent("C02060000") ){
				rowAddr++;
				sb.append("			<ROW>\n");
				sb.append("				<CELL BorderFill=\"2\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"13112\" Protect=\"false\" RowAddr=\"" + rowAddr + "\" RowSpan=\"1\" Width=\"9212\">\n");
				sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
				sb.append("						<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>부대시설물</CHAR></TEXT></P>\n");
				sb.append("					</PARALIST>\n");
				sb.append("				</CELL>\n");
				sb.append("				<CELL BorderFill=\"2\" ColAddr=\"1\" ColSpan=\"2\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"13112\" Protect=\"false\" RowAddr=\"" + rowAddr + "\" RowSpan=\"1\" Width=\"20349\">\n");
				sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
				if(isValidResultItemContent("C02010000")) {
					sb.append("						<P ParaShape=\"0\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>ㅇ 방충재 파손, 마모상태</CHAR></TEXT></P>\n");
				}
				if(isValidResultItemContent("C02020000")) {
					sb.append("						<P ParaShape=\"0\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>ㅇ 계선주, 차막이 등 상태</CHAR></TEXT></P>\n");
				}
				if(isValidResultItemContent("C02030000")) {
					sb.append("						<P ParaShape=\"0\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>ㅇ 타이다운 등 관리상태</CHAR></TEXT></P>\n");
				}
				if(isValidResultItemContent("C02040000")) {
					sb.append("						<P ParaShape=\"0\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>ㅇ 기타 여건번화</CHAR></TEXT></P>\n");
				}
				if(isValidResultItemContent("C02050000")) {
					sb.append("						<P ParaShape=\"0\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>ㅇ 난간 및 보도상태</CHAR></TEXT></P>\n");
				}
				if(isValidResultItemContent("C02060000")) {
					sb.append("						<P ParaShape=\"0\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>ㅇ 교좌장치 상태</CHAR></TEXT></P>\n");
				}
				sb.append("					</PARALIST>\n");
				sb.append("				</CELL>\n");
				sb.append("				<CELL BorderFill=\"2\" ColAddr=\"3\" ColSpan=\"3\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"13112\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"18647\">\n");
				sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
				if(isValidResultItemContent("C02010000")) {
					sb.append("						<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>" + getResultItemContent("C02010000") + "</CHAR></TEXT></P>\n");
				}
				if(isValidResultItemContent("C02020000")) {
					sb.append("						<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>" + getResultItemContent("C02020000") + "</CHAR></TEXT></P>\n");
				}
				if(isValidResultItemContent("C02030000")) {
					sb.append("						<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>" + getResultItemContent("C02030000") + "</CHAR></TEXT></P>\n");
				}
				if(isValidResultItemContent("C02040000")) {
					sb.append("						<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>" + getResultItemContent("C02040000") + "</CHAR></TEXT></P>\n");
				}
				if(isValidResultItemContent("C02050000")) {
					sb.append("						<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>" + getResultItemContent("C02050000") + "</CHAR></TEXT></P>\n");
				}
				if(isValidResultItemContent("C02060000")) {
					sb.append("						<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>" + getResultItemContent("C02060000") + "</CHAR></TEXT></P>\n");
				}
				sb.append("					</PARALIST>\n");
				sb.append("				</CELL>\n");
				sb.append("			</ROW>\n");
			}

			if(isValidResultItemContent("C03010000") || isValidResultItemContent("C03020000") || isValidResultItemContent("C03030000") 
					|| isValidResultItemContent("C03040000") || isValidResultItemContent("C03050000") ){
				rowAddr++;
				sb.append("			<ROW>\n");
				sb.append("				<CELL BorderFill=\"2\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"13112\" Protect=\"false\" RowAddr=\"" + rowAddr + "\" RowSpan=\"1\" Width=\"9212\">\n");
				sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
				sb.append("						<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>피해 또는 노후 현황</CHAR></TEXT></P>\n");
				sb.append("					</PARALIST>\n");
				sb.append("				</CELL>\n");
				sb.append("				<CELL BorderFill=\"2\" ColAddr=\"1\" ColSpan=\"2\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"13112\" Protect=\"false\" RowAddr=\"" + rowAddr + "\" RowSpan=\"1\" Width=\"20349\">\n");
				sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
				if(isValidResultItemContent("C03010000")) {
					sb.append("						<P ParaShape=\"0\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>ㅇ 시설물의 상태(균열, 침하, 활동, 융기, 함몰, 전도 등)</CHAR></TEXT></P>\n");
				}
				if(isValidResultItemContent("C03020000")) {
					sb.append("						<P ParaShape=\"0\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>ㅇ 야적장 포장의 파손여부</CHAR></TEXT></P>\n");
				}
				if(isValidResultItemContent("C03030000")) {
					sb.append("						<P ParaShape=\"0\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>ㅇ 에프런 상태 및 기타사항</CHAR></TEXT></P>\n");
				}
				if(isValidResultItemContent("C03040000")) {
					sb.append("						<P ParaShape=\"0\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>ㅇ 포장의 파손여부</CHAR></TEXT></P>\n");
				}
				if(isValidResultItemContent("C03050000")) {
					sb.append("						<P ParaShape=\"0\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>ㅇ 균열, 백태 및 철근노출, 부식</CHAR></TEXT></P>\n");
				}
				sb.append("					</PARALIST>\n");
				sb.append("				</CELL>\n");
				sb.append("				<CELL BorderFill=\"2\" ColAddr=\"3\" ColSpan=\"3\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"13112\" Protect=\"false\" RowAddr=\"" + rowAddr + "\" RowSpan=\"1\" Width=\"18647\">\n");
				sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
				if(isValidResultItemContent("C03010000")) {
					sb.append("						<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>" + getResultItemContent("C03010000") + "</CHAR></TEXT></P>\n");
					sb.append("						<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR/></TEXT></P>\n");
				}
				if(isValidResultItemContent("C03020000")) {
					sb.append("						<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>" + getResultItemContent("C03020000") + "</CHAR></TEXT></P>\n");
				}
				if(isValidResultItemContent("C03030000")) {
					sb.append("						<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>" + getResultItemContent("C03030000") + "</CHAR></TEXT></P>\n");
				}
				if(isValidResultItemContent("C03040000")) {
					sb.append("						<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>" + getResultItemContent("C03040000") + "</CHAR></TEXT></P>\n");
				}
				if(isValidResultItemContent("C03050000")) {
					sb.append("						<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>" + getResultItemContent("C03050000") + "</CHAR></TEXT></P>\n");
				}
				sb.append("					</PARALIST>\n");
				sb.append("				</CELL>\n");
				sb.append("			</ROW>\n");
			}
			
			if(isValidResultItemContent("C04010000") || isValidResultItemContent("C04020000") ){
				rowAddr++;
				sb.append("			<ROW>\n");
				sb.append("				<CELL BorderFill=\"2\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4792\" Protect=\"false\" RowAddr=\"" + rowAddr + "\" RowSpan=\"1\" Width=\"9212\">\n");
				sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
				sb.append("						<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>시설물 운영</CHAR></TEXT></P>\n");
				sb.append("						<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>및 관리</CHAR></TEXT></P>\n");
				sb.append("					</PARALIST>\n");
				sb.append("				</CELL>\n");
				sb.append("				<CELL BorderFill=\"2\" ColAddr=\"1\" ColSpan=\"2\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4792\" Protect=\"false\" RowAddr=\"" + rowAddr + "\" RowSpan=\"1\" Width=\"20349\">\n");
				sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
				if(isValidResultItemContent("C04010000")) {
					sb.append("						<P ParaShape=\"0\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>ㅇ 야적화물의 과적여부</CHAR></TEXT></P>\n");
				}
				if(isValidResultItemContent("C04020000")) {
					sb.append("						<P ParaShape=\"0\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>ㅇ 사용장비의 적정성 여부등</CHAR></TEXT></P>\n");
				}
				sb.append("					</PARALIST>\n");
				sb.append("				</CELL>\n");
				sb.append("				<CELL BorderFill=\"2\" ColAddr=\"3\" ColSpan=\"3\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4792\" Protect=\"false\" RowAddr=\"" + rowAddr + "\" RowSpan=\"1\" Width=\"18647\">\n");
				sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
				if(isValidResultItemContent("C04010000")) {
					sb.append("						<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>" + getResultItemContent("C04010000") + "</CHAR></TEXT></P>\n");
				}
				if(isValidResultItemContent("C04020000")) {
					sb.append("						<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>" + getResultItemContent("C04020000") + "</CHAR></TEXT></P>\n");
				}
				sb.append("					</PARALIST>\n");
				sb.append("				</CELL>\n");
				sb.append("			</ROW>\n");
			}
			
			if(isValidResultItemContent("C05010000")){
				rowAddr++;
				sb.append("			<ROW>\n");
				sb.append("				<CELL BorderFill=\"2\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2712\" Protect=\"false\" RowAddr=\"" + rowAddr + "\" RowSpan=\"1\" Width=\"9212\">\n");
				sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
				sb.append("						<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>기 타</CHAR></TEXT></P>\n");
				sb.append("					</PARALIST>\n");
				sb.append("				</CELL>\n");
				sb.append("				<CELL BorderFill=\"2\" ColAddr=\"1\" ColSpan=\"2\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2712\" Protect=\"false\" RowAddr=\"" + rowAddr + "\" RowSpan=\"1\" Width=\"20349\">\n");
				sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
				if(isValidResultItemContent("C05010000")) {
					sb.append("						<P ParaShape=\"0\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>ㅇ 배수시설 상태 등 </CHAR></TEXT></P>\n");
				}
				sb.append("					</PARALIST>\n");
				sb.append("				</CELL>\n");
				sb.append("				<CELL BorderFill=\"2\" ColAddr=\"3\" ColSpan=\"3\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2712\" Protect=\"false\" RowAddr=\"" + rowAddr + "\" RowSpan=\"1\" Width=\"18647\">\n");
				sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
				if(isValidResultItemContent("C05010000")) {
					sb.append("						<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>" + getResultItemContent("C05010000") + "</CHAR></TEXT></P>\n");
				}
				sb.append("					</PARALIST>\n");
				sb.append("				</CELL>\n");
				sb.append("			</ROW>\n");
			}
			sb.append("		</TABLE>\n");
			sb.append("		<CHAR/>\n");
			sb.append("	</TEXT></P>\n");
			sb.append("</SECTION></BODY>\n");
			sb.append("<TAIL>\n");
			sb.append(getBinDataListElement());
			sb.append("<SCRIPTCODE Type=\"JScript\" Version=\"1.0\"><SCRIPTHEADER>var Documents = XHwpDocuments;\n");
			sb.append("var Document = Documents.Active_XHwpDocument;\n");
			sb.append("</SCRIPTHEADER><SCRIPTSOURCE>function OnDocument_New() {} \n");
			sb.append("</SCRIPTSOURCE></SCRIPTCODE></TAIL></HWPML>");
			return sb.toString();
		}
		
		/**전력 시설물 점검표*/  
		protected String getElectyHwpReport() {
			StringBuilder sb = new StringBuilder();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>\n");
			sb.append("<HWPML Style=\"embed\" SubVersion=\"7.0.0.0\" Version=\"2.7\">\n");
			sb.append("<HEAD SecCnt=\"1\">\n");
			sb.append("	<DOCSUMMARY><TITLE>전력 시설물 점검표</TITLE><AUTHOR>YGPA GIS Assets Management System</AUTHOR><DATE>" + docDate + "</DATE></DOCSUMMARY>\n");
			sb.append("	<DOCSETTING><BEGINNUMBER Endnote=\"1\" Equation=\"1\" Footnote=\"1\" Page=\"1\" Picture=\"1\" Table=\"1\"/><CARETPOS List=\"0\" Para=\"0\" Pos=\"29\"/></DOCSETTING>\n");
			sb.append("	<MAPPINGTABLE>\n");
			sb.append(getBinItemListElement());
			sb.append("		<FACENAMELIST><FONTFACE Count=\"4\" Lang=\"Hangul\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"휴먼옛체\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"4\" StrokeVariation=\"1\" Weight=\"5\" XHeight=\"1\"/></FONT><FONT Id=\"3\" Name=\"휴먼명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"4\" Lang=\"Latin\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양궁서\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"4\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT><FONT Id=\"3\" Name=\"HCI Poppy\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Hanja\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Japanese\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Other\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Symbol\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"User\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE></FACENAMELIST>\n");
			sb.append("		<BORDERFILLLIST Count=\"5\"><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"1\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"2\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"None\" Width=\"0.1mm\"/><RIGHTBORDER Type=\"None\" Width=\"0.1mm\"/><TOPBORDER Type=\"None\" Width=\"0.1mm\"/><BOTTOMBORDER Type=\"None\" Width=\"0.1mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.1mm\"/><FILLBRUSH><WINDOWBRUSH Alpha=\"0\" FaceColor=\"4294967295\" HatchColor=\"0\"/></FILLBRUSH></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"3\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.4mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.4mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.4mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"4\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.4mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.4mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"5\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.4mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.4mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.4mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL></BORDERFILLLIST>\n");
			sb.append("		<CHARSHAPELIST Count=\"15\"><CHARSHAPE BorderFillId=\"0\" Height=\"2000\" Id=\"0\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"2\" Hanja=\"2\" Japanese=\"2\" Latin=\"2\" Other=\"2\" Symbol=\"2\" User=\"2\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"2000\" Id=\"1\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"2\" Hanja=\"2\" Japanese=\"2\" Latin=\"2\" Other=\"2\" Symbol=\"2\" User=\"2\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><BOLD/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1000\" Id=\"2\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1000\" Id=\"3\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"4\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"5\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"95\" Hanja=\"95\" Japanese=\"95\" Latin=\"95\" Other=\"95\" Symbol=\"95\" User=\"95\"/><CHARSPACING Hangul=\"-5\" Hanja=\"-5\" Japanese=\"-5\" Latin=\"-5\" Other=\"-5\" Symbol=\"-5\" User=\"-5\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"6\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"95\" Hanja=\"95\" Japanese=\"95\" Latin=\"95\" Other=\"95\" Symbol=\"95\" User=\"95\"/><CHARSPACING Hangul=\"-5\" Hanja=\"-5\" Japanese=\"-5\" Latin=\"-5\" Other=\"-5\" Symbol=\"-5\" User=\"-5\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"7\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"8\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"3\" Hanja=\"1\" Japanese=\"1\" Latin=\"3\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1200\" Id=\"9\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"3\" Hanja=\"1\" Japanese=\"1\" Latin=\"3\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"10\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"3\" Hanja=\"1\" Japanese=\"1\" Latin=\"3\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"95\" Hanja=\"95\" Japanese=\"95\" Latin=\"95\" Other=\"95\" Symbol=\"95\" User=\"95\"/><CHARSPACING Hangul=\"-10\" Hanja=\"-10\" Japanese=\"-10\" Latin=\"-10\" Other=\"-10\" Symbol=\"-10\" User=\"-10\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1300\" Id=\"11\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"3\" Hanja=\"1\" Japanese=\"1\" Latin=\"3\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"12\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"3\" Hanja=\"1\" Japanese=\"1\" Latin=\"3\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"95\" Hanja=\"95\" Japanese=\"95\" Latin=\"95\" Other=\"95\" Symbol=\"95\" User=\"95\"/><CHARSPACING Hangul=\"-5\" Hanja=\"-5\" Japanese=\"-5\" Latin=\"-5\" Other=\"-5\" Symbol=\"-5\" User=\"-5\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"13\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"3\" Hanja=\"1\" Japanese=\"1\" Latin=\"3\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><BOLD/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"14\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><BOLD/></CHARSHAPE></CHARSHAPELIST>\n");
			sb.append("		<TABDEFLIST Count=\"3\"><TABDEF AutoTabLeft=\"false\" AutoTabRight=\"false\" Id=\"0\"/><TABDEF AutoTabLeft=\"true\" AutoTabRight=\"false\" Id=\"1\"/><TABDEF AutoTabLeft=\"false\" AutoTabRight=\"true\" Id=\"2\"/></TABDEFLIST>\n");
			sb.append("		<NUMBERINGLIST Count=\"1\"><NUMBERING Id=\"1\" Start=\"0\"><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"1\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^1.</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"2\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^2.</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"3\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^3)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"4\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^4)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"5\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">(^5)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"6\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">(^6)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"7\" NumFormat=\"CircledDigit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^7</PARAHEAD></NUMBERING></NUMBERINGLIST>\n");
			sb.append("		<PARASHAPELIST Count=\"25\"><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"0\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"1\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"true\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"2\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"1\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"-2620\" Left=\"0\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"3\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"4\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"3000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"5\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"2000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"6\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"1\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"4000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"7\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"2\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"6000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"8\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"3\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"8000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"9\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"4\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"10000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"10\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"5\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"12000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"11\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"6\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"14000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"12\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"2\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"150\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"13\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"100\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"14\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"15\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"200\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Right\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"16\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"3000\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"17\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"18\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"1000\" LineSpacing=\"100\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"1000\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"19\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"1000\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"1000\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"20\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"1000\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"1000\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Right\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"21\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"4000\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"22\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"-1776\" Left=\"1000\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"1000\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Right\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"23\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"24\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE></PARASHAPELIST>\n");
			sb.append("		<STYLELIST Count=\"15\"><STYLE CharShape=\"3\" EngName=\"Normal\" Id=\"0\" LangId=\"1042\" LockForm=\"0\" Name=\"바탕글\" NextStyle=\"0\" ParaShape=\"3\" Type=\"Para\"/><STYLE CharShape=\"3\" EngName=\"Body\" Id=\"1\" LangId=\"1042\" LockForm=\"0\" Name=\"본문\" NextStyle=\"1\" ParaShape=\"4\" Type=\"Para\"/><STYLE CharShape=\"3\" EngName=\"Outline 1\" Id=\"2\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 1\" NextStyle=\"2\" ParaShape=\"5\" Type=\"Para\"/><STYLE CharShape=\"3\" EngName=\"Outline 2\" Id=\"3\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 2\" NextStyle=\"3\" ParaShape=\"6\" Type=\"Para\"/><STYLE CharShape=\"3\" EngName=\"Outline 3\" Id=\"4\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 3\" NextStyle=\"4\" ParaShape=\"7\" Type=\"Para\"/><STYLE CharShape=\"3\" EngName=\"Outline 4\" Id=\"5\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 4\" NextStyle=\"5\" ParaShape=\"8\" Type=\"Para\"/><STYLE CharShape=\"3\" EngName=\"Outline 5\" Id=\"6\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 5\" NextStyle=\"6\" ParaShape=\"9\" Type=\"Para\"/><STYLE CharShape=\"3\" EngName=\"Outline 6\" Id=\"7\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 6\" NextStyle=\"7\" ParaShape=\"10\" Type=\"Para\"/><STYLE CharShape=\"3\" EngName=\"Outline 7\" Id=\"8\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 7\" NextStyle=\"8\" ParaShape=\"11\" Type=\"Para\"/><STYLE CharShape=\"2\" EngName=\"Page Number\" Id=\"9\" LangId=\"1042\" LockForm=\"0\" Name=\"쪽 번호\" NextStyle=\"9\" ParaShape=\"3\" Type=\"Para\"/><STYLE CharShape=\"4\" EngName=\"Header\" Id=\"10\" LangId=\"1042\" LockForm=\"0\" Name=\"머리말\" NextStyle=\"10\" ParaShape=\"12\" Type=\"Para\"/><STYLE CharShape=\"5\" EngName=\"Footnote\" Id=\"11\" LangId=\"1042\" LockForm=\"0\" Name=\"각주\" NextStyle=\"11\" ParaShape=\"2\" Type=\"Para\"/><STYLE CharShape=\"5\" EngName=\"Endnote\" Id=\"12\" LangId=\"1042\" LockForm=\"0\" Name=\"미주\" NextStyle=\"12\" ParaShape=\"2\" Type=\"Para\"/><STYLE CharShape=\"6\" EngName=\"Memo\" Id=\"13\" LangId=\"1042\" LockForm=\"0\" Name=\"메모\" NextStyle=\"13\" ParaShape=\"1\" Type=\"Para\"/><STYLE CharShape=\"0\" Id=\"14\" LangId=\"1042\" LockForm=\"0\" Name=\"중간제목(옛체20)\" NextStyle=\"14\" ParaShape=\"0\" Type=\"Para\"/></STYLELIST>\n");
			sb.append("	</MAPPINGTABLE>\n");
			sb.append("</HEAD>\n");
			sb.append("<BODY><SECTION Id=\"0\">\n");
			sb.append("	<P ColumnBreak=\"false\" PageBreak=\"false\" ParaShape=\"0\" Style=\"14\"><TEXT CharShape=\"1\">\n");
			sb.append("		<SECDEF CharGrid=\"0\" FirstBorder=\"false\" FirstFill=\"false\" LineGrid=\"0\" OutlineShape=\"1\" SpaceColumns=\"1134\" TabStop=\"8000\" TextDirection=\"0\" TextVerticalWidthHead=\"0\"><STARTNUMBER Equation=\"0\" Figure=\"0\" Page=\"0\" PageStartsOn=\"Both\" Table=\"0\"/><HIDE Border=\"false\" EmptyLine=\"false\" Fill=\"false\" Footer=\"false\" Header=\"false\" MasterPage=\"false\" PageNumPos=\"false\"/><PAGEDEF GutterType=\"LeftOnly\" Height=\"84188\" Landscape=\"0\" Width=\"59528\"><PAGEMARGIN Bottom=\"0\" Footer=\"4252\" Gutter=\"0\" Header=\"4252\" Left=\"4252\" Right=\"4110\" Top=\"0\"/></PAGEDEF><FOOTNOTESHAPE><AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/><NOTELINE Length=\"5cm\" Type=\"Solid\" Width=\"0.12mm\"/><NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"283\"/><NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/><NOTEPLACEMENT BeneathText=\"false\" Place=\"EachColumn\"/></FOOTNOTESHAPE><ENDNOTESHAPE><AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/><NOTELINE Length=\"14692344\" Type=\"Solid\" Width=\"0.12mm\"/><NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"0\"/><NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/><NOTEPLACEMENT BeneathText=\"false\" Place=\"EndOfDocument\"/></ENDNOTESHAPE><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Both\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Even\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Odd\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL></SECDEF>\n");
			sb.append("		<COLDEF Count=\"1\" Layout=\"Left\" SameGap=\"0\" SameSize=\"true\" Type=\"Newspaper\"/>\n");
			sb.append("		<CHAR>전 력 시 설 점 검 표</CHAR>\n");
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ParaShape=\"13\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>   ○ 시설명 : </CHAR></TEXT><TEXT CharShape=\"13\"><CHAR>" + fcltsMngGroupNm + "</CHAR></TEXT></P>\n");
			sb.append("	<P ParaShape=\"16\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>※ 정상 : ○  요주의 : △  불량 : ×</CHAR></TEXT></P>\n");
			sb.append("	<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"8\">\n");
			sb.append("		<TABLE BorderFill=\"1\" CellSpacing=\"0\" ColCount=\"4\" PageBreak=\"None\" RepeatHeader=\"true\" RowCount=\"8\">\n");
			sb.append("			<SHAPEOBJECT InstId=\"2052475343\" Lock=\"false\" NumberingType=\"Table\" ZOrder=\"1\"><SIZE Height=\"57908\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"49303\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"false\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"0\" HorzRelTo=\"Column\" TreatAsChar=\"true\" VertAlign=\"Top\" VertOffset=\"0\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/></SHAPEOBJECT>\n");
			sb.append("			<INSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"1964\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"8140\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>시 설 명</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"1964\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"24896\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>점 검 사 항</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"1964\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"8703\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>결    과</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"1964\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"7564\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>비 고</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"11632\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"8140\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>변 압 기</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"11632\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"24896\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>1. 1, 2차 탭 절환의 적정여부</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>2. 1, 2차 부싱의 케이블 접속상태</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>3. 보호 계전기의 작동상태</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>4. 절연유 내전압의 적정여부</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>5. 절연 저항의 적합여부</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>6. 외함의 부식상태</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"11632\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"8703\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E01010000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E01020000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E01030000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E01040000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E01050000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E01060000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"11632\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"7564\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"6172\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"8140\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>차단기류</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"6172\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"24896\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>1. 단자의 접속상태</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>2. 절연 저항의 적합여부</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>3. 차단 속도의 적합여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"6172\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"8703\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E02010000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E02020000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E02030000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"6172\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"7564\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"6172\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"8140\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>단 로 기</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"6172\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"24896\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>1. 기구의 접속상태</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>2. 접촉부, 조작부의 작동상태</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>3. 절연저항의 적합여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"6172\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"8703\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E03010000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E03020000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E03030000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"6172\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"7564\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"11632\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"8140\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>수배전반</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"11632\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"24896\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>1. 보호 계전기의 작동상태</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>2. 단자 접속상태</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>3. 모선의 이상유무</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>4. 절연 저항의 적합여부</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>5. 접지 저항의 접합여부</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>6. 외함의 부식상태</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"11632\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"8703\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E04010000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E04020000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E04030000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E04040000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E04050000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E04060000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"11632\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"7564\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7992\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"8140\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>계 기 용</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>변 성 기</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7992\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"24896\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>1. 단자 접속상태</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>2. 절연 저항의 적합여부</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>3. 접지 저항의 적합여부</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>4. 외함의 부식상태</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7992\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"8703\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E05010000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E05020000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E05030000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E05040000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7992\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"7564\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7992\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"8140\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>계 기 용</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>변 압 기,</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>변 류 기</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7992\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"24896\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>1. 단자 접속상태</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>2. 절연저항의 적합여부</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>3. 접지 저항의 적합여부</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>4. 외함의 부식상태</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7992\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"8703\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E06010000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E06020000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E06030000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E06040000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7992\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"7564\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4352\" Protect=\"false\" RowAddr=\"7\" RowSpan=\"1\" Width=\"8140\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>피 뢰 기</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4352\" Protect=\"false\" RowAddr=\"7\" RowSpan=\"1\" Width=\"24896\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>1. 기구의 외관상태</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>2. 접지 저항의 적합여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4352\" Protect=\"false\" RowAddr=\"7\" RowSpan=\"1\" Width=\"8703\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E07010000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E07010000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4352\" Protect=\"false\" RowAddr=\"7\" RowSpan=\"1\" Width=\"7564\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("		</TABLE>\n");
			sb.append("		<CHAR/>\n");
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"8\"/></P>\n");
			sb.append("	<P ParaShape=\"21\" Style=\"0\"><TEXT CharShape=\"11\"><CHAR>※ 정상 : ○  요주의 : △  불량 : ×</CHAR></TEXT></P>\n");
			sb.append("	<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"8\">\n");
			sb.append("		<TABLE BorderFill=\"1\" CellSpacing=\"0\" ColCount=\"4\" PageBreak=\"None\" RepeatHeader=\"true\" RowCount=\"8\">\n");
			sb.append("			<SHAPEOBJECT InstId=\"2052475344\" Lock=\"false\" NumberingType=\"Table\" ZOrder=\"2\"><SIZE Height=\"59844\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"49582\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"false\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"0\" HorzRelTo=\"Column\" TreatAsChar=\"true\" VertAlign=\"Top\" VertOffset=\"0\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/></SHAPEOBJECT>\n");
			sb.append("			<INSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"7572\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>시 설 명</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"24896\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>점 검 사 항</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"9834\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>결    과</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"7280\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>비 고</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7708\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"7572\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>진 상 용</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>콘 덴 서</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7708\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"24896\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>1. 기구의 외관상태</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>2. 단자 접속상태</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>3. 절연 저항의 적합여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>4. 접지저항의 적합여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7708\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"9834\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E08010000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E08020000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E08030000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E08040000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7708\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"7280\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7708\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"7572\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>직렬리액터</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7708\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"24896\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>1. 기구의 외관상태</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>2. 단자 접속상태</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>3. 절연 저항의 적합여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>4. 접지 저항의 적합여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7708\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"9834\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E09010000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E09020000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E09030000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E09040000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7708\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"7280\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5888\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"7572\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>정류기반</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5888\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"24896\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>1. 온도상승의 적정여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>2. 각종 계전기 및 차단기의 작동상태</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>3. 외함 부식상태</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5888\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"9834\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E10010000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E10020000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E10030000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5888\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"7280\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"9528\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"7572\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>축 전 기</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"9528\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"24896\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>1. 전해액 비중의 적합여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>2. 액면의 적합여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>3. 단자의 풀림상태</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>4. 밧데리의 설치상태</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>5. 전해액 온도의 적합여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"9528\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"9834\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E11010000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E11020000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E11030000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E11040000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E11050000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"9528\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"7280\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7708\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"7572\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>중앙감시</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>설    비</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7708\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"24896\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>1. ANALOG 입․출력의 오차</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>2. </CHAR></TEXT><TEXT CharShape=\"12\"><CHAR>DIGITAL 입․출력의 오차</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>3. FLOPY 및 HARD DISK의 오손상태</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>4. PANEL의 부식상태</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7708\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"9834\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E12010000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E12020000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E12030000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E12040000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7708\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"7280\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"11348\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"7572\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>옥 내 외</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>조명설비</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"11348\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"24896\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>1. 등기구의 변색, 변형여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"22\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>2. 조명탑, 조명폴, 조작반, 안정기함의 변형 및 부식상태</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>3. 배광 및 조도의 적정여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>4. 절연 저항의 적합여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>5. 접지 저항의 적합여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"11348\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"9834\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E13010000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E13020000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E13030000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E13040000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E13050000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"11348\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"7280\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7708\" Protect=\"false\" RowAddr=\"7\" RowSpan=\"1\" Width=\"7572\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>동력설비</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7708\" Protect=\"false\" RowAddr=\"7\" RowSpan=\"1\" Width=\"24896\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>1. 차단기 및 스위치의 작동상태</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>2. 조작반 외함의 부식상태</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>3. 모터 절연저항의 적합여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>4. 각 기기의 접지저항의 적합여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7708\" Protect=\"false\" RowAddr=\"7\" RowSpan=\"1\" Width=\"9834\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E14010000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E14020000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E14030000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E14040000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7708\" Protect=\"false\" RowAddr=\"7\" RowSpan=\"1\" Width=\"7280\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("		</TABLE>\n");
			sb.append("		<CHAR/>\n");
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"3\"/></P>\n");
			sb.append("	<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"21\" Style=\"0\"><TEXT CharShape=\"11\"><CHAR>※ 정상 : ○  요주의 : △  불량 : ×</CHAR></TEXT></P>\n");
			sb.append("	<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"8\">\n");
			sb.append("		<TABLE BorderFill=\"1\" CellSpacing=\"0\" ColCount=\"4\" PageBreak=\"None\" RepeatHeader=\"true\" RowCount=\"7\">\n");
			sb.append("			<SHAPEOBJECT InstId=\"2052475345\" Lock=\"false\" NumberingType=\"Table\" ZOrder=\"3\"><SIZE Height=\"57260\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"49299\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"false\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"0\" HorzRelTo=\"Column\" TreatAsChar=\"true\" VertAlign=\"Top\" VertOffset=\"0\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/></SHAPEOBJECT>\n");
			sb.append("			<INSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"1680\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"7572\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>시 설 명</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"1680\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"24896\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>점 검 사 항</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"1680\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"9551\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>결    과</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"1680\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"7280\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>비 고</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7140\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"7572\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>옥내배선</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7140\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"24896\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"22\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>1. 단자의 조임 및 단자상호간의 혼촉여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>2. 전선류의 손상상태</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>3. 절연저항의 적합여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>4. 전선의 과열여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7140\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"9551\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E15010000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E15020000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E15030000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E15040000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7140\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"7280\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5320\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"7572\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>콘 센 트,</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>전기기계</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>기    구</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5320\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"24896\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>1. 기구의 손상여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>2. 절연저항의 적합여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>3. 사용전압 및 적합한 콘센트의 사용여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5320\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"9551\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E16010000") + "</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E16020000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E16030000") + "</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5320\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"7280\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7140\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"7572\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>자동화재</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>경보시설</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7140\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"24896\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>1. 수신기 및 발신기의 취부상태</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>2. 유도등의 취부상태</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>3. 수신기 및 발신기 외함의 부식상태</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>4. 각 회로별 절연저항의 적합여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7140\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"9551\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E17010000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E17020000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E17030000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E17040000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7140\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"7280\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"10780\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"7572\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>전력케이블</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"10780\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"24896\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>1. 단말설비(케이블 햇드)의 상태</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>2. 직선 접속부분의 이상여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>3. 핸드홀, 맨홀의 이물질 또는 침수상태</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>4. 각종 닥트의 침하여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>5. 절연저항의 적합여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>6. 이상 발열여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"10780\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"9551\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E18010000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E18020000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E18030000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E18040000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E18050000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E18060000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"10780\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"7280\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"8960\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"7572\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>가공전선로</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"8960\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"24896\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>1. 전주 및 지선의 손상여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>2. 지선의 이완정도 또는 부식상태</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>3. 전선 지지상태와 적합여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>4. 전선과 타물질과의 이격 거리상태</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>5. 접지 보호시설의 이상유무</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"8960\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"9551\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E19010000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E19020000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E19030000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E19040000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E19050000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"8960\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"7280\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"16240\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"7572\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>발 전 기</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"16240\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"24896\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>1. 운전시 소음, 진동의 이상유무</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>2. 절연 저항의 적합여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>3. 접지 저항의 적합여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>4. 펌프류의 작동상태</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>5. 시동장치의 정상 작동여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>6. 연료탱크의 누유상태</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>7. 냉각수 탱크의 청소상태</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>8. 냉각수 공급 파이프의 누수상태</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>9. 각종 판넬의 부식상태</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"16240\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"9551\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E20010000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E20020000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E20030000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E20040000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E20050000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E20060000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E20070000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E20080000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("E20090000") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"16240\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"7280\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("		</TABLE>\n");
			sb.append("		<CHAR/>\n");
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\"/></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"7\">\n");
			if(chargerInfo1 != null) {
				if((Boolean)chargerInfo1.get("fileExists")) {			
					sb.append("		<PICTURE Reverse=\"false\">\n");
					sb.append("			<SHAPEOBJECT InstId=\"2052634208\" Lock=\"false\" NumberingType=\"Figure\" TextWrap=\"BehindText\" ZOrder=\"0\"><SIZE Height=\"4500\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"3840\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"true\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"38100\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"480\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/></SHAPEOBJECT>\n");
					sb.append("			<SHAPECOMPONENT GroupLevel=\"0\" HorzFlip=\"false\" InstID=\"978892385\" OriHeight=\"4500\" OriWidth=\"3840\" VertFlip=\"false\" XPos=\"0\" YPos=\"0\"><ROTATIONINFO Angle=\"0\" CenterX=\"1920\" CenterY=\"2250\"/><RENDERINGINFO><TRANSMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/></RENDERINGINFO></SHAPECOMPONENT>\n");
					sb.append("			<IMAGERECT X0=\"0\" X1=\"3840\" X2=\"3840\" X3=\"0\" Y0=\"0\" Y1=\"0\" Y2=\"4500\" Y3=\"4500\"/>\n");
					sb.append("			<IMAGECLIP Bottom=\"4500\" Left=\"0\" Right=\"3840\" Top=\"0\"/>\n");
					sb.append("			<INSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/>\n");
					sb.append("			<IMAGE Alpha=\"0\" BinItem=\"" + (Integer)chargerInfo1.get("binId") +  "\" Bright=\"0\" Contrast=\"0\" Effect=\"RealPic\"/>\n");
					sb.append("		</PICTURE>\n");
				}
			}
			sb.append("		<CHAR>                                  점검일자 :  </CHAR></TEXT><TEXT CharShape=\"14\"><CHAR>"  + ((qcDetailData.get("wrtDtHwp") != null) ? (String) qcDetailData.get("wrtDtHwp") : "" ) + "</CHAR>\n");
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"7\">\n");
			if(chargerInfo2 != null) {
				if((Boolean)chargerInfo2.get("fileExists")) {			
					sb.append("		<PICTURE Reverse=\"false\">\n");
					sb.append("			<SHAPEOBJECT InstId=\"2055984741\" Lock=\"false\" NumberingType=\"Figure\" TextWrap=\"BehindText\" ZOrder=\"4\"><SIZE Height=\"4500\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"3840\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"true\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"38220\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"2020\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/></SHAPEOBJECT>\n");
					sb.append("			<SHAPECOMPONENT GroupLevel=\"0\" HorzFlip=\"false\" InstID=\"982242918\" OriHeight=\"4500\" OriWidth=\"3840\" VertFlip=\"false\" XPos=\"0\" YPos=\"0\"><ROTATIONINFO Angle=\"0\" CenterX=\"1920\" CenterY=\"2250\"/><RENDERINGINFO><TRANSMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/></RENDERINGINFO></SHAPECOMPONENT>\n");
					sb.append("			<IMAGERECT X0=\"0\" X1=\"3840\" X2=\"3840\" X3=\"0\" Y0=\"0\" Y1=\"0\" Y2=\"4500\" Y3=\"4500\"/>\n");
					sb.append("			<IMAGECLIP Bottom=\"4500\" Left=\"0\" Right=\"3840\" Top=\"0\"/>\n");
					sb.append("			<INSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/>\n");
					sb.append("			<IMAGE Alpha=\"0\" BinItem=\"" + (Integer)chargerInfo2.get("binId") + "\" Bright=\"0\" Contrast=\"0\" Effect=\"RealPic\"/>\n");
					sb.append("		</PICTURE>\n");
				}
			}
			if(chargerInfo1 != null) {
				sb.append("		<CHAR>                                  점 검 자 :  " + ((chargerInfo1.get("chargerNm") != null) ? (String)chargerInfo1.get("chargerNm") :  "      ") + "    (인)</CHAR>\n");
			} else {
				sb.append("		<CHAR>                                  점 검 자 :            (인)</CHAR>\n");
			}
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"7\">\n");
			if(chargerInfo2 != null) {
				sb.append("		<CHAR>                                              " + ((chargerInfo2.get("chargerNm") != null) ? (String)chargerInfo2.get("chargerNm") :  "      ") + "    (인)</CHAR>\n");
			} else {
				sb.append("		<CHAR/>");
			}
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"7\"/></P>\n");
			sb.append("</SECTION></BODY>\n");
			sb.append("<TAIL>\n");
			sb.append(getBinDataListElement());
			sb.append("<SCRIPTCODE Type=\"JScript\" Version=\"1.0\"><SCRIPTHEADER>var Documents = XHwpDocuments;\n");
			sb.append("var Document = Documents.Active_XHwpDocument;\n");
			sb.append("</SCRIPTHEADER><SCRIPTSOURCE>function OnDocument_New() {} \n");
			sb.append("</SCRIPTSOURCE></SCRIPTCODE></TAIL></HWPML>");
			return sb.toString();
		}

		/**건축 시설물 점검표*/  
		protected String getArchHwpReport() {
			StringBuilder sb = new StringBuilder();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>\n");
			sb.append("<HWPML Style=\"embed\" SubVersion=\"7.0.0.0\" Version=\"2.7\">\n");
			sb.append("<HEAD SecCnt=\"1\">\n");
			sb.append("	<DOCSUMMARY><TITLE>건축 시설물 점검표</TITLE><AUTHOR>YGPA GIS Assets Management System</AUTHOR><DATE>" + docDate + "</DATE></DOCSUMMARY>\n");
			sb.append("	<DOCSETTING><BEGINNUMBER Endnote=\"1\" Equation=\"1\" Footnote=\"1\" Page=\"1\" Picture=\"1\" Table=\"1\"/><CARETPOS List=\"0\" Para=\"0\" Pos=\"29\"/></DOCSETTING>\n");
			sb.append("	<MAPPINGTABLE>\n");
			sb.append(getBinItemListElement());
			sb.append("		<FACENAMELIST><FONTFACE Count=\"3\" Lang=\"Hangul\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"휴먼옛체\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"4\" StrokeVariation=\"1\" Weight=\"5\" XHeight=\"1\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Latin\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양궁서\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"4\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Hanja\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Japanese\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Other\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Symbol\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"User\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE></FACENAMELIST>\n");
			sb.append("		<BORDERFILLLIST Count=\"3\"><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"1\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"2\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"None\" Width=\"0.1mm\"/><RIGHTBORDER Type=\"None\" Width=\"0.1mm\"/><TOPBORDER Type=\"None\" Width=\"0.1mm\"/><BOTTOMBORDER Type=\"None\" Width=\"0.1mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.1mm\"/><FILLBRUSH><WINDOWBRUSH Alpha=\"0\" FaceColor=\"4294967295\" HatchColor=\"4278190080\"/></FILLBRUSH></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"3\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL></BORDERFILLLIST>\n");
			sb.append("		<CHARSHAPELIST Count=\"13\"><CHARSHAPE BorderFillId=\"0\" Height=\"1000\" Id=\"0\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1000\" Id=\"1\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"2\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"3\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"95\" Hanja=\"95\" Japanese=\"95\" Latin=\"95\" Other=\"95\" Symbol=\"95\" User=\"95\"/><CHARSPACING Hangul=\"-5\" Hanja=\"-5\" Japanese=\"-5\" Latin=\"-5\" Other=\"-5\" Symbol=\"-5\" User=\"-5\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"4\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"95\" Hanja=\"95\" Japanese=\"95\" Latin=\"95\" Other=\"95\" Symbol=\"95\" User=\"95\"/><CHARSPACING Hangul=\"-5\" Hanja=\"-5\" Japanese=\"-5\" Latin=\"-5\" Other=\"-5\" Symbol=\"-5\" User=\"-5\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"2000\" Id=\"5\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"2\" Hanja=\"2\" Japanese=\"2\" Latin=\"2\" Other=\"2\" Symbol=\"2\" User=\"2\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"2000\" Id=\"6\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"2\" Hanja=\"2\" Japanese=\"2\" Latin=\"2\" Other=\"2\" Symbol=\"2\" User=\"2\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><BOLD/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"7\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1200\" Id=\"8\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"9\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"90\" Hanja=\"90\" Japanese=\"90\" Latin=\"90\" Other=\"90\" Symbol=\"90\" User=\"90\"/><CHARSPACING Hangul=\"-10\" Hanja=\"-10\" Japanese=\"-10\" Latin=\"-10\" Other=\"-10\" Symbol=\"-10\" User=\"-10\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"10\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"90\" Hanja=\"90\" Japanese=\"90\" Latin=\"90\" Other=\"90\" Symbol=\"90\" User=\"90\"/><CHARSPACING Hangul=\"-15\" Hanja=\"-15\" Japanese=\"-15\" Latin=\"-15\" Other=\"-15\" Symbol=\"-15\" User=\"-15\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"11\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><BOLD/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"12\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"90\" Hanja=\"90\" Japanese=\"90\" Latin=\"90\" Other=\"90\" Symbol=\"90\" User=\"90\"/><CHARSPACING Hangul=\"-14\" Hanja=\"-14\" Japanese=\"-14\" Latin=\"-14\" Other=\"-14\" Symbol=\"-14\" User=\"-14\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE></CHARSHAPELIST>\n");
			sb.append("		<TABDEFLIST Count=\"3\"><TABDEF AutoTabLeft=\"false\" AutoTabRight=\"false\" Id=\"0\"/><TABDEF AutoTabLeft=\"true\" AutoTabRight=\"false\" Id=\"1\"/><TABDEF AutoTabLeft=\"false\" AutoTabRight=\"true\" Id=\"2\"/></TABDEFLIST>\n");
			sb.append("		<NUMBERINGLIST Count=\"1\"><NUMBERING Id=\"1\" Start=\"0\"><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"1\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^1.</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"2\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^2.</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"3\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^3)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"4\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^4)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"5\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">(^5)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"6\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">(^6)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"7\" NumFormat=\"CircledDigit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^7</PARAHEAD></NUMBERING></NUMBERINGLIST>\n");
			sb.append("		<PARASHAPELIST Count=\"20\"><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"0\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"1\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"100\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"2\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"3\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"true\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"4\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"1\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"-2620\" Left=\"0\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"5\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"3000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"6\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"2000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"7\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"1\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"4000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"8\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"2\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"6000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"9\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"3\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"8000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"10\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"4\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"10000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"11\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"5\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"12000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"12\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"6\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"14000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"13\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"2\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"150\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Right\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"14\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"150\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"2000\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"15\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"150\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"16\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"17\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"110\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"18\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"110\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"19\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"110\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE></PARASHAPELIST>\n");
			sb.append("		<STYLELIST Count=\"15\"><STYLE CharShape=\"1\" EngName=\"Normal\" Id=\"0\" LangId=\"1042\" LockForm=\"0\" Name=\"바탕글\" NextStyle=\"0\" ParaShape=\"2\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Body\" Id=\"1\" LangId=\"1042\" LockForm=\"0\" Name=\"본문\" NextStyle=\"1\" ParaShape=\"5\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 1\" Id=\"2\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 1\" NextStyle=\"2\" ParaShape=\"6\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 2\" Id=\"3\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 2\" NextStyle=\"3\" ParaShape=\"7\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 3\" Id=\"4\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 3\" NextStyle=\"4\" ParaShape=\"8\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 4\" Id=\"5\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 4\" NextStyle=\"5\" ParaShape=\"9\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 5\" Id=\"6\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 5\" NextStyle=\"6\" ParaShape=\"10\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 6\" Id=\"7\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 6\" NextStyle=\"7\" ParaShape=\"11\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 7\" Id=\"8\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 7\" NextStyle=\"8\" ParaShape=\"12\" Type=\"Para\"/><STYLE CharShape=\"0\" EngName=\"Page Number\" Id=\"9\" LangId=\"1042\" LockForm=\"0\" Name=\"쪽 번호\" NextStyle=\"9\" ParaShape=\"2\" Type=\"Para\"/><STYLE CharShape=\"2\" EngName=\"Header\" Id=\"10\" LangId=\"1042\" LockForm=\"0\" Name=\"머리말\" NextStyle=\"10\" ParaShape=\"13\" Type=\"Para\"/><STYLE CharShape=\"3\" EngName=\"Footnote\" Id=\"11\" LangId=\"1042\" LockForm=\"0\" Name=\"각주\" NextStyle=\"11\" ParaShape=\"4\" Type=\"Para\"/><STYLE CharShape=\"3\" EngName=\"Endnote\" Id=\"12\" LangId=\"1042\" LockForm=\"0\" Name=\"미주\" NextStyle=\"12\" ParaShape=\"4\" Type=\"Para\"/><STYLE CharShape=\"4\" EngName=\"Memo\" Id=\"13\" LangId=\"1042\" LockForm=\"0\" Name=\"메모\" NextStyle=\"13\" ParaShape=\"3\" Type=\"Para\"/><STYLE CharShape=\"5\" Id=\"14\" LangId=\"1042\" LockForm=\"0\" Name=\"중간제목(옛체20)\" NextStyle=\"14\" ParaShape=\"0\" Type=\"Para\"/></STYLELIST>\n");
			sb.append("	</MAPPINGTABLE>\n");
			sb.append("</HEAD>\n");
			sb.append("<BODY><SECTION Id=\"0\">\n");
			sb.append("	<P ColumnBreak=\"false\" PageBreak=\"false\" ParaShape=\"0\" Style=\"14\"><TEXT CharShape=\"6\">\n");
			sb.append("		<SECDEF CharGrid=\"0\" FirstBorder=\"false\" FirstFill=\"false\" LineGrid=\"0\" OutlineShape=\"1\" SpaceColumns=\"1134\" TabStop=\"8000\" TextDirection=\"0\" TextVerticalWidthHead=\"0\"><STARTNUMBER Equation=\"0\" Figure=\"0\" Page=\"0\" PageStartsOn=\"Both\" Table=\"0\"/><HIDE Border=\"false\" EmptyLine=\"false\" Fill=\"false\" Footer=\"false\" Header=\"false\" MasterPage=\"false\" PageNumPos=\"false\"/><PAGEDEF GutterType=\"LeftOnly\" Height=\"84188\" Landscape=\"0\" Width=\"59528\"><PAGEMARGIN Bottom=\"0\" Footer=\"4252\" Gutter=\"0\" Header=\"4252\" Left=\"4252\" Right=\"4110\" Top=\"0\"/></PAGEDEF><FOOTNOTESHAPE><AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/><NOTELINE Length=\"5cm\" Type=\"Solid\" Width=\"0.12mm\"/><NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"283\"/><NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/><NOTEPLACEMENT BeneathText=\"false\" Place=\"EachColumn\"/></FOOTNOTESHAPE><ENDNOTESHAPE><AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/><NOTELINE Length=\"14692344\" Type=\"Solid\" Width=\"0.12mm\"/><NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"0\"/><NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/><NOTEPLACEMENT BeneathText=\"false\" Place=\"EndOfDocument\"/></ENDNOTESHAPE><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Both\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Even\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Odd\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL></SECDEF>\n");
			sb.append("		<COLDEF Count=\"1\" Layout=\"Left\" SameGap=\"0\" SameSize=\"true\" Type=\"Newspaper\"/>\n");
			sb.append("		<CHAR>건 축 시 설 점 검 표</CHAR>\n");
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ParaShape=\"16\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>○ 시설명 : </CHAR></TEXT><TEXT CharShape=\"11\"><CHAR>" + fcltsMngGroupNm + "</CHAR></TEXT></P>\n");
			sb.append("	<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>※ 정상 : ○ 요주의 : △ 불량 : ×</CHAR></TEXT></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"7\">\n");
			sb.append("		<TABLE BorderFill=\"1\" CellSpacing=\"0\" ColCount=\"4\" PageBreak=\"None\" RepeatHeader=\"true\" RowCount=\"9\">\n");
			sb.append("			<SHAPEOBJECT InstId=\"2052493049\" Lock=\"false\" NumberingType=\"Table\" ZOrder=\"1\"><SIZE Height=\"52080\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"50450\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"false\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"0\" HorzRelTo=\"Column\" TreatAsChar=\"true\" VertAlign=\"Top\" VertOffset=\"0\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/></SHAPEOBJECT>\n");
			sb.append("			<INSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"1680\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"5444\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>구    분</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"1680\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"30720\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>점    검    사    항</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"1680\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"7143\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>결 과</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"1680\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"7143\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>비 고</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3220\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"5444\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>기    초</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3220\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"30720\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 구조체 침하</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 구조체 전도</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3220\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"7143\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("A01010000") + "</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("A01020000") + "</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3220\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"7143\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4760\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"5444\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>바    닥</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4760\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"30720\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 바닥판 콘크리트 침하</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 콘크리트 파손 및 균열</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 바닥마감재(도자기타일, 아스타일 등) 파손 및 탈락</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4760\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"7143\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("A02010000") + "</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("A02020000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("A02030000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4760\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"7143\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"10920\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"5444\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>벽    체</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"10920\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"30720\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 조적벽체 균열</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 모르터 균열</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 마감도장 노후</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 경량칸막이 노후 및 파손</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 창틀 및 벽체누수</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 내장 및 외장타일 파손, 탈락</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 외벽 백화현상</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"10920\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"7143\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("A03010000") + "</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("A03020000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("A03030000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("A03040000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("A03050000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("A03060000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("A03070000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"10920\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"7143\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3220\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"5444\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>기    둥</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3220\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"30720\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 기둥 콘크리트 균열</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 구조물 전도</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3220\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"7143\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("A04010000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("A04020000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3220\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"7143\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"6300\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"5444\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>천    정</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"6300\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"30720\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 경량철골 천장틀 노후, 파손</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 천정재 탈락, 노후 및 파손</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 천정 흡음재(질석스프레이 등) 부식 및 탈락</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 제치장콘크리트 및 모르터위 도색 노후</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"6300\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"7143\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("A05010000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("A05020000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("A05030000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("A05040000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"6300\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"7143\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"10920\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"5444\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>지    붕</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"10920\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"30720\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 옥상층 모체(콘크리트) 균열 및 노후</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ </CHAR></TEXT><TEXT CharShape=\"10\"><CHAR>옥상방수층(아스팔트, 시멘트 액체, 쉬트등) 균열 및 파손</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 철골트러스</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR> - 접합부(볼트조임부, 리벳조임부, 용접부) 이격 및 파손</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 형태변형(비틀림, 휨, 처짐)</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 지붕마감재(슬레이트, 석면수지피복강판) 노후 및 탈락</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 도색노후</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"10920\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"7143\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("A06010000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("A06020000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"/></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("A06030100") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("A06040000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("A06050000") + "</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("A06060000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"10920\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"7143\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7840\" Protect=\"false\" RowAddr=\"7\" RowSpan=\"1\" Width=\"5444\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>창    호</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7840\" Protect=\"false\" RowAddr=\"7\" RowSpan=\"1\" Width=\"30720\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ </CHAR></TEXT><TEXT CharShape=\"12\"><CHAR>철재(알루미늄 포함) 및 목재창호 후레임 비틀림 및 파손</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 철재창호 부식 및 파손</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 목재창호 부식 및 비틀림</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 창호 여닫이 및 비틀림</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 유리파손</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7840\" Protect=\"false\" RowAddr=\"7\" RowSpan=\"1\" Width=\"7143\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("A07010000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("A07020000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("A07030000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("A07040000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("A07050000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7840\" Protect=\"false\" RowAddr=\"7\" RowSpan=\"1\" Width=\"7143\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3220\" Protect=\"false\" RowAddr=\"8\" RowSpan=\"1\" Width=\"5444\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>지 하 실</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3220\" Protect=\"false\" RowAddr=\"8\" RowSpan=\"1\" Width=\"30720\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 모체(콘크리트 옹벽 및 바닥판) 균열 누수</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 흡음재(질적스프레이 등) 부식 및 탈락</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3220\" Protect=\"false\" RowAddr=\"8\" RowSpan=\"1\" Width=\"7143\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("A08010000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("A08020000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3220\" Protect=\"false\" RowAddr=\"8\" RowSpan=\"1\" Width=\"7143\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("		</TABLE>\n");
			sb.append("		<CHAR/>\n");
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"7\"/></P>\n");
			sb.append("	<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"7\">\n");
			if(chargerInfo1 != null) {
				if((Boolean)chargerInfo1.get("fileExists")) {
					sb.append("		<PICTURE Reverse=\"false\">\n");
					sb.append("			<SHAPEOBJECT InstId=\"2052618215\" Lock=\"false\" NumberingType=\"Figure\" TextWrap=\"BehindText\" ZOrder=\"0\"><SIZE Height=\"4500\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"3840\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"true\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"38640\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"500\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/></SHAPEOBJECT>\n");
					sb.append("			<SHAPECOMPONENT GroupLevel=\"0\" HorzFlip=\"false\" InstID=\"978876392\" OriHeight=\"4500\" OriWidth=\"3840\" VertFlip=\"false\" XPos=\"0\" YPos=\"0\"><ROTATIONINFO Angle=\"0\" CenterX=\"1920\" CenterY=\"2250\"/><RENDERINGINFO><TRANSMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/></RENDERINGINFO></SHAPECOMPONENT>\n");
					sb.append("			<IMAGERECT X0=\"0\" X1=\"3840\" X2=\"3840\" X3=\"0\" Y0=\"0\" Y1=\"0\" Y2=\"4500\" Y3=\"4500\"/>\n");
					sb.append("			<IMAGECLIP Bottom=\"4500\" Left=\"0\" Right=\"3840\" Top=\"0\"/>\n");
					sb.append("			<INSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/>\n");
					sb.append("			<IMAGE Alpha=\"0\" BinItem=\"" + (Integer)chargerInfo1.get("binId") + "\" Bright=\"0\" Contrast=\"0\" Effect=\"RealPic\"/>\n");
					sb.append("		</PICTURE>\n");
				}
			}
			sb.append("		<CHAR>                                  점검일자 :  </CHAR></TEXT><TEXT CharShape=\"11\"><CHAR>" + ((qcDetailData.get("wrtDtHwp") != null) ? (String) qcDetailData.get("wrtDtHwp") : "" ) + "</CHAR>\n");
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"7\"/></P>\n");
			if(chargerInfo1 != null) {
				sb.append("	<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>                                  점 검 자 :  " + ((chargerInfo1.get("chargerNm") != null) ? (String)chargerInfo1.get("chargerNm") :  "      ") + "     (인)</CHAR></TEXT></P>\n");
			} else {
				sb.append("	<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>                                  점 검 자 :             (인)</CHAR></TEXT></P>\n");
			}
			sb.append("	<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"7\">\n");
			if(chargerInfo2 != null) {
				if((Boolean)chargerInfo2.get("fileExists")) {
					sb.append("		<PICTURE Reverse=\"false\">\n");
					sb.append("			<SHAPEOBJECT InstId=\"2055984737\" Lock=\"false\" NumberingType=\"Figure\" TextWrap=\"BehindText\" ZOrder=\"2\"><SIZE Height=\"4500\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"3840\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"true\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"38640\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"740\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/></SHAPEOBJECT>\n");
					sb.append("			<SHAPECOMPONENT GroupLevel=\"0\" HorzFlip=\"false\" InstID=\"982242914\" OriHeight=\"4500\" OriWidth=\"3840\" VertFlip=\"false\" XPos=\"0\" YPos=\"0\"><ROTATIONINFO Angle=\"0\" CenterX=\"1920\" CenterY=\"2250\"/><RENDERINGINFO><TRANSMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/></RENDERINGINFO></SHAPECOMPONENT>\n");
					sb.append("			<IMAGERECT X0=\"0\" X1=\"3840\" X2=\"3840\" X3=\"0\" Y0=\"0\" Y1=\"0\" Y2=\"4500\" Y3=\"4500\"/>\n");
					sb.append("			<IMAGECLIP Bottom=\"4500\" Left=\"0\" Right=\"3840\" Top=\"0\"/>\n");
					sb.append("			<INSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/>\n");
					sb.append("			<IMAGE Alpha=\"0\" BinItem=\"" + (Integer)chargerInfo2.get("binId") + "\" Bright=\"0\" Contrast=\"0\" Effect=\"RealPic\"/>\n");
					sb.append("		</PICTURE>\n");
				}
			}
			sb.append("		<CHAR/>\n");
			sb.append("	</TEXT></P>\n");
			if(chargerInfo2 != null) {
				sb.append("	<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>                                              " + ((chargerInfo2.get("chargerNm") != null) ? (String)chargerInfo2.get("chargerNm") :  "      ") + "     (인)</CHAR></TEXT></P>\n");
			} else {
				sb.append("	<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR/></TEXT></P>\n");
			}
			sb.append("</SECTION></BODY>\n");
			sb.append("<TAIL>\n");
			sb.append(getBinDataListElement());
			sb.append("<SCRIPTCODE Type=\"JScript\" Version=\"1.0\"><SCRIPTHEADER>var Documents = XHwpDocuments;\n");
			sb.append("var Document = Documents.Active_XHwpDocument;\n");
			sb.append("</SCRIPTHEADER><SCRIPTSOURCE>function OnDocument_New() {}\n");
			sb.append("</SCRIPTSOURCE></SCRIPTCODE></TAIL></HWPML>\n");
			return sb.toString();
		}
		
		/**기계(건축설비) 시설물 점검표*/
		protected String getArchMechHwpReport() {
			StringBuilder sb = new StringBuilder();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>\n");
			sb.append("<HWPML Style=\"embed\" SubVersion=\"7.0.0.0\" Version=\"2.7\">\n");
			sb.append("<HEAD SecCnt=\"1\">\n");
			sb.append("	<DOCSUMMARY><TITLE>기계설비 시설물 점검표</TITLE><AUTHOR>YGPA GIS Assets Management System</AUTHOR><DATE>" + docDate + "</DATE></DOCSUMMARY>\n");
			sb.append("	<DOCSETTING><BEGINNUMBER Endnote=\"1\" Equation=\"1\" Footnote=\"1\" Page=\"1\" Picture=\"1\" Table=\"1\"/><CARETPOS List=\"0\" Para=\"0\" Pos=\"29\"/></DOCSETTING>\n");
			sb.append("	<MAPPINGTABLE>\n");
			sb.append(getBinItemListElement());
			sb.append("		<FACENAMELIST><FONTFACE Count=\"3\" Lang=\"Hangul\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"휴먼옛체\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"4\" StrokeVariation=\"1\" Weight=\"5\" XHeight=\"1\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Latin\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양궁서\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"4\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Hanja\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Japanese\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Other\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Symbol\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"User\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE></FACENAMELIST>\n");
			sb.append("		<BORDERFILLLIST Count=\"3\"><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"1\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"2\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"None\" Width=\"0.1mm\"/><RIGHTBORDER Type=\"None\" Width=\"0.1mm\"/><TOPBORDER Type=\"None\" Width=\"0.1mm\"/><BOTTOMBORDER Type=\"None\" Width=\"0.1mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.1mm\"/><FILLBRUSH><WINDOWBRUSH Alpha=\"0\" FaceColor=\"4294967295\" HatchColor=\"4278190080\"/></FILLBRUSH></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"3\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL></BORDERFILLLIST>\n");
			sb.append("		<CHARSHAPELIST Count=\"12\"><CHARSHAPE BorderFillId=\"0\" Height=\"1000\" Id=\"0\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1000\" Id=\"1\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"2\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"3\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"95\" Hanja=\"95\" Japanese=\"95\" Latin=\"95\" Other=\"95\" Symbol=\"95\" User=\"95\"/><CHARSPACING Hangul=\"-5\" Hanja=\"-5\" Japanese=\"-5\" Latin=\"-5\" Other=\"-5\" Symbol=\"-5\" User=\"-5\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"4\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"95\" Hanja=\"95\" Japanese=\"95\" Latin=\"95\" Other=\"95\" Symbol=\"95\" User=\"95\"/><CHARSPACING Hangul=\"-5\" Hanja=\"-5\" Japanese=\"-5\" Latin=\"-5\" Other=\"-5\" Symbol=\"-5\" User=\"-5\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"2000\" Id=\"5\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"2\" Hanja=\"2\" Japanese=\"2\" Latin=\"2\" Other=\"2\" Symbol=\"2\" User=\"2\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"2000\" Id=\"6\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"2\" Hanja=\"2\" Japanese=\"2\" Latin=\"2\" Other=\"2\" Symbol=\"2\" User=\"2\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><BOLD/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"7\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1200\" Id=\"8\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"9\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"90\" Hanja=\"90\" Japanese=\"90\" Latin=\"90\" Other=\"90\" Symbol=\"90\" User=\"90\"/><CHARSPACING Hangul=\"-10\" Hanja=\"-10\" Japanese=\"-10\" Latin=\"-10\" Other=\"-10\" Symbol=\"-10\" User=\"-10\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"10\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"90\" Hanja=\"90\" Japanese=\"90\" Latin=\"90\" Other=\"90\" Symbol=\"90\" User=\"90\"/><CHARSPACING Hangul=\"-15\" Hanja=\"-15\" Japanese=\"-15\" Latin=\"-15\" Other=\"-15\" Symbol=\"-15\" User=\"-15\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"11\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><BOLD/></CHARSHAPE></CHARSHAPELIST>\n");
			sb.append("		<TABDEFLIST Count=\"3\"><TABDEF AutoTabLeft=\"false\" AutoTabRight=\"false\" Id=\"0\"/><TABDEF AutoTabLeft=\"true\" AutoTabRight=\"false\" Id=\"1\"/><TABDEF AutoTabLeft=\"false\" AutoTabRight=\"true\" Id=\"2\"/></TABDEFLIST>\n");
			sb.append("		<NUMBERINGLIST Count=\"1\"><NUMBERING Id=\"1\" Start=\"0\"><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"1\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^1.</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"2\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^2.</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"3\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^3)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"4\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^4)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"5\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">(^5)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"6\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">(^6)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"7\" NumFormat=\"CircledDigit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^7</PARAHEAD></NUMBERING></NUMBERINGLIST>\n");
			sb.append("		<PARASHAPELIST Count=\"20\"><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"0\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"1\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"100\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"2\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"3\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"true\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"4\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"1\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"-2620\" Left=\"0\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"5\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"3000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"6\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"2000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"7\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"1\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"4000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"8\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"2\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"6000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"9\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"3\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"8000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"10\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"4\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"10000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"11\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"5\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"12000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"12\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"6\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"14000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"13\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"2\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"150\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Right\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"14\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"150\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"2000\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"15\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"150\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"16\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"17\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"200\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"18\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"150\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"19\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"150\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE></PARASHAPELIST>\n");
			sb.append("		<STYLELIST Count=\"15\"><STYLE CharShape=\"1\" EngName=\"Normal\" Id=\"0\" LangId=\"1042\" LockForm=\"0\" Name=\"바탕글\" NextStyle=\"0\" ParaShape=\"2\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Body\" Id=\"1\" LangId=\"1042\" LockForm=\"0\" Name=\"본문\" NextStyle=\"1\" ParaShape=\"5\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 1\" Id=\"2\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 1\" NextStyle=\"2\" ParaShape=\"6\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 2\" Id=\"3\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 2\" NextStyle=\"3\" ParaShape=\"7\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 3\" Id=\"4\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 3\" NextStyle=\"4\" ParaShape=\"8\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 4\" Id=\"5\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 4\" NextStyle=\"5\" ParaShape=\"9\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 5\" Id=\"6\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 5\" NextStyle=\"6\" ParaShape=\"10\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 6\" Id=\"7\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 6\" NextStyle=\"7\" ParaShape=\"11\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 7\" Id=\"8\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 7\" NextStyle=\"8\" ParaShape=\"12\" Type=\"Para\"/><STYLE CharShape=\"0\" EngName=\"Page Number\" Id=\"9\" LangId=\"1042\" LockForm=\"0\" Name=\"쪽 번호\" NextStyle=\"9\" ParaShape=\"2\" Type=\"Para\"/><STYLE CharShape=\"2\" EngName=\"Header\" Id=\"10\" LangId=\"1042\" LockForm=\"0\" Name=\"머리말\" NextStyle=\"10\" ParaShape=\"13\" Type=\"Para\"/><STYLE CharShape=\"3\" EngName=\"Footnote\" Id=\"11\" LangId=\"1042\" LockForm=\"0\" Name=\"각주\" NextStyle=\"11\" ParaShape=\"4\" Type=\"Para\"/><STYLE CharShape=\"3\" EngName=\"Endnote\" Id=\"12\" LangId=\"1042\" LockForm=\"0\" Name=\"미주\" NextStyle=\"12\" ParaShape=\"4\" Type=\"Para\"/><STYLE CharShape=\"4\" EngName=\"Memo\" Id=\"13\" LangId=\"1042\" LockForm=\"0\" Name=\"메모\" NextStyle=\"13\" ParaShape=\"3\" Type=\"Para\"/><STYLE CharShape=\"5\" Id=\"14\" LangId=\"1042\" LockForm=\"0\" Name=\"중간제목(옛체20)\" NextStyle=\"14\" ParaShape=\"0\" Type=\"Para\"/></STYLELIST>\n");
			sb.append("	</MAPPINGTABLE>\n");
			sb.append("</HEAD>\n");
			sb.append("<BODY><SECTION Id=\"0\">\n");
			sb.append("	<P ColumnBreak=\"false\" PageBreak=\"false\" ParaShape=\"0\" Style=\"14\"><TEXT CharShape=\"6\">\n");
			sb.append("		<SECDEF CharGrid=\"0\" FirstBorder=\"false\" FirstFill=\"false\" LineGrid=\"0\" OutlineShape=\"1\" SpaceColumns=\"1134\" TabStop=\"8000\" TextDirection=\"0\" TextVerticalWidthHead=\"0\"><STARTNUMBER Equation=\"0\" Figure=\"0\" Page=\"0\" PageStartsOn=\"Both\" Table=\"0\"/><HIDE Border=\"false\" EmptyLine=\"false\" Fill=\"false\" Footer=\"false\" Header=\"false\" MasterPage=\"false\" PageNumPos=\"false\"/><PAGEDEF GutterType=\"LeftOnly\" Height=\"84188\" Landscape=\"0\" Width=\"59528\"><PAGEMARGIN Bottom=\"0\" Footer=\"4252\" Gutter=\"0\" Header=\"4252\" Left=\"4252\" Right=\"4110\" Top=\"0\"/></PAGEDEF><FOOTNOTESHAPE><AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/><NOTELINE Length=\"5cm\" Type=\"Solid\" Width=\"0.12mm\"/><NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"283\"/><NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/><NOTEPLACEMENT BeneathText=\"false\" Place=\"EachColumn\"/></FOOTNOTESHAPE><ENDNOTESHAPE><AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/><NOTELINE Length=\"14692344\" Type=\"Solid\" Width=\"0.12mm\"/><NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"0\"/><NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/><NOTEPLACEMENT BeneathText=\"false\" Place=\"EndOfDocument\"/></ENDNOTESHAPE><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Both\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Even\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Odd\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL></SECDEF>\n");
			sb.append("		<COLDEF Count=\"1\" Layout=\"Left\" SameGap=\"0\" SameSize=\"true\" Type=\"Newspaper\"/>\n");
			sb.append("		<CHAR>기 계 설 비 점 검 표</CHAR>\n");
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"7\"/></P>\n");
			sb.append("	<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>○ 시설명 : </CHAR></TEXT><TEXT CharShape=\"11\"><CHAR>" + fcltsMngGroupNm + "</CHAR></TEXT></P>\n");
			sb.append("	<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"7\"/></P>\n");
			sb.append("	<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>※ 정상 : ○ 요주의 : △ 불량 : ×</CHAR></TEXT></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"7\">\n");
			sb.append("		<TABLE BorderFill=\"1\" CellSpacing=\"0\" ColCount=\"4\" PageBreak=\"None\" RepeatHeader=\"true\" RowCount=\"7\">\n");
			sb.append("			<SHAPEOBJECT InstId=\"2052475346\" Lock=\"false\" NumberingType=\"Table\" ZOrder=\"0\"><SIZE Height=\"43380\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"49884\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"false\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"0\" HorzRelTo=\"Column\" TreatAsChar=\"true\" VertAlign=\"Top\" VertOffset=\"0\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/></SHAPEOBJECT>\n");
			sb.append("			<INSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3384\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"8284\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>구    분</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3384\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"25040\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>점    검    내    용</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3384\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"9131\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>점 검 결 과</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3384\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"7429\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>비  고</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"11216\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"8284\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>기계실</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"11216\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"25040\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 각종 배관누수 및 변형여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 각종 열기기 정상작동 여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 각종 Pump 정상작동 여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 각종물탱 누수 및 변형 여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 각종 배수 맨홀 배수상태</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"11216\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"9131\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("M01010100") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("M01010200") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("M01010300") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("M01010400") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("M01010500") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"11216\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"7429\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2816\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"8284\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>위생설비</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2816\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"25040\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 위생기구 적정관리여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2816\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"9131\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("M01020100") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2816\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"7429\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2816\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"8284\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>냉․난방시설</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2816\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"25040\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 팬코일 및 라디에타등 적정관리 여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2816\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"9131\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("M01030100") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2816\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"7429\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"9116\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"8284\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>소방시설</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"9116\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"25040\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 각종 배관 누수 및 변형 여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 각종 Pump 및 압력탱크 정상작동 여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ </CHAR></TEXT><TEXT CharShape=\"10\"><CHAR>소화전등 청소상태 및 소방호스 적정관리여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 소화기 배치 및 위치표시 여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"9116\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"9131\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("M01040100") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("M01040200") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("M01040300") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("M01040400") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"9116\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"7429\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4916\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"8284\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>유류저장시설</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>(건축물 관계)</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4916\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"25040\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 주유시설 주위 기름오염 여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 안전수칙 및 게시판 적정관리 여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4916\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"9131\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("M01050100") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("M01050200") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4916\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"7429\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"9116\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"8284\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>기타</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"9116\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"25040\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 소방시설 정기점검 기록 관리여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 관리대상 열기기 정기점검 여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 위험물 취급자에 의한 취급 여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 시설물 정기점검 기록 관리 여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"9116\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"9131\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("M01060100") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("M01060200") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("M01060300") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol("M01060400") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
			sb.append("				</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"9116\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"7429\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("		</TABLE>\n");
			sb.append("		<CHAR/>\n");
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"1\"/></P>\n");
			sb.append("	<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>                          </CHAR></TEXT></P>\n");
			sb.append("	<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"7\"/></P>\n");
			sb.append("	<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"7\">\n");
			if(chargerInfo1 != null) {
				if((Boolean)chargerInfo1.get("fileExists")) {
					sb.append("		<PICTURE Reverse=\"false\">\n");
					sb.append("			<SHAPEOBJECT InstId=\"2052618217\" Lock=\"false\" NumberingType=\"Figure\" TextWrap=\"BehindText\" ZOrder=\"1\"><SIZE Height=\"4500\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"3840\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"true\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"42720\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"400\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/></SHAPEOBJECT>\n");
					sb.append("			<SHAPECOMPONENT GroupLevel=\"0\" HorzFlip=\"false\" InstID=\"978876394\" OriHeight=\"4500\" OriWidth=\"3840\" VertFlip=\"false\" XPos=\"0\" YPos=\"0\"><ROTATIONINFO Angle=\"0\" CenterX=\"1920\" CenterY=\"2250\"/><RENDERINGINFO><TRANSMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/></RENDERINGINFO></SHAPECOMPONENT>\n");
					sb.append("			<IMAGERECT X0=\"0\" X1=\"3840\" X2=\"3840\" X3=\"0\" Y0=\"0\" Y1=\"0\" Y2=\"4500\" Y3=\"4500\"/>\n");
					sb.append("			<IMAGECLIP Bottom=\"4500\" Left=\"0\" Right=\"3840\" Top=\"0\"/>\n");
					sb.append("			<INSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/>\n");
					sb.append("			<IMAGE Alpha=\"0\" BinItem=\"" + (Integer)chargerInfo1.get("binId") + "\" Bright=\"0\" Contrast=\"0\" Effect=\"RealPic\"/>\n");
					sb.append("		</PICTURE>\n");
				}
			}
			sb.append("		<CHAR>                                         점검일자 : </CHAR></TEXT><TEXT CharShape=\"11\"><CHAR>" + ((qcDetailData.get("wrtDtHwp") != null) ? (String) qcDetailData.get("wrtDtHwp") : "" ) + "</CHAR>\n");
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ParaShape=\"16\" Style=\"0\"><TEXT CharShape=\"7\">\n");
			if(chargerInfo2 != null) {
				if((Boolean)chargerInfo2.get("fileExists")) {
					sb.append("		<PICTURE Reverse=\"false\">\n");
					sb.append("			<SHAPEOBJECT InstId=\"2055984739\" Lock=\"false\" NumberingType=\"Figure\" TextWrap=\"BehindText\" ZOrder=\"2\"><SIZE Height=\"4500\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"3840\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"true\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"42900\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"1800\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/></SHAPEOBJECT>\n");
					sb.append("			<SHAPECOMPONENT GroupLevel=\"0\" HorzFlip=\"false\" InstID=\"982242916\" OriHeight=\"4500\" OriWidth=\"3840\" VertFlip=\"false\" XPos=\"0\" YPos=\"0\"><ROTATIONINFO Angle=\"0\" CenterX=\"1920\" CenterY=\"2250\"/><RENDERINGINFO><TRANSMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/></RENDERINGINFO></SHAPECOMPONENT>\n");
					sb.append("			<IMAGERECT X0=\"0\" X1=\"3840\" X2=\"3840\" X3=\"0\" Y0=\"0\" Y1=\"0\" Y2=\"4500\" Y3=\"4500\"/>\n");
					sb.append("			<IMAGECLIP Bottom=\"4500\" Left=\"0\" Right=\"3840\" Top=\"0\"/>\n");
					sb.append("			<INSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/>\n");
					sb.append("			<IMAGE Alpha=\"0\" BinItem=\"" + (Integer)chargerInfo2.get("binId") + "\" Bright=\"0\" Contrast=\"0\" Effect=\"RealPic\"/>\n");
					sb.append("		</PICTURE>\n");
				}
			}
			if(chargerInfo1 != null) {
				sb.append("		<CHAR>                                         점 검 자 : " + ((chargerInfo1.get("chargerNm") != null) ? (String)chargerInfo1.get("chargerNm") :  "      ") + "    (인)</CHAR>\n");
			} else {
				sb.append("		<CHAR>                                         점 검 자 :           (인)</CHAR>\n");
			}
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ParaShape=\"16\" Style=\"0\"><TEXT CharShape=\"7\">\n");
			if(chargerInfo2 != null) {
				sb.append("		<CHAR>                                                    " + ((chargerInfo2.get("chargerNm") != null) ? (String)chargerInfo2.get("chargerNm") :  "      ") + "    (인) </CHAR>\n");
			} else {
				sb.append("		<CHAR/>\n");
			}
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ParaShape=\"3\" Style=\"0\"><TEXT CharShape=\"1\"/></P>\n");
			sb.append("</SECTION></BODY>\n");
			sb.append("<TAIL>\n");
			sb.append(getBinDataListElement());
			sb.append("<SCRIPTCODE Type=\"JScript\" Version=\"1.0\"><SCRIPTHEADER>var Documents = XHwpDocuments;\n");
			sb.append("var Document = Documents.Active_XHwpDocument;\n");
			sb.append("</SCRIPTHEADER><SCRIPTSOURCE>function OnDocument_New() {}\n");
			sb.append("</SCRIPTSOURCE></SCRIPTCODE></TAIL></HWPML>");			
			return sb.toString();
		}
		
		/**하역장비(기계) 시설물 점검표*/
		protected String getCraneMechHwpReport() {
			StringBuilder sb = new StringBuilder();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>\n");
			sb.append("<HWPML Style=\"embed\" SubVersion=\"7.0.0.0\" Version=\"2.7\">\n");
			sb.append("<HEAD SecCnt=\"1\">\n");
			sb.append("	<DOCSUMMARY><TITLE>항만하역장비 시설물 점검표</TITLE><AUTHOR>YGPA GIS Assets Management System</AUTHOR><DATE>" + docDate + "</DATE></DOCSUMMARY>\n");
			sb.append("	<DOCSETTING><BEGINNUMBER Endnote=\"1\" Equation=\"1\" Footnote=\"1\" Page=\"1\" Picture=\"1\" Table=\"1\"/><CARETPOS List=\"0\" Para=\"0\" Pos=\"30\"/></DOCSETTING>\n");
			sb.append("	<MAPPINGTABLE>\n");
			sb.append(getBinItemListElement());
			sb.append("		<FACENAMELIST><FONTFACE Count=\"3\" Lang=\"Hangul\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"휴먼옛체\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"4\" StrokeVariation=\"1\" Weight=\"5\" XHeight=\"1\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Latin\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양궁서\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"4\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Hanja\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Japanese\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Other\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Symbol\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"User\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE></FACENAMELIST>\n");
			sb.append("		<BORDERFILLLIST Count=\"22\"><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"1\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"2\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"None\" Width=\"0.1mm\"/><RIGHTBORDER Type=\"None\" Width=\"0.1mm\"/><TOPBORDER Type=\"None\" Width=\"0.1mm\"/><BOTTOMBORDER Type=\"None\" Width=\"0.1mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.1mm\"/><FILLBRUSH><WINDOWBRUSH Alpha=\"0\" FaceColor=\"4294967295\" HatchColor=\"0\"/></FILLBRUSH></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"3\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"4\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.4mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"5\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.4mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"6\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.4mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"7\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.4mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.4mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"8\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"DoubleSlim\" Width=\"0.5mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"9\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.4mm\"/><TOPBORDER Type=\"DoubleSlim\" Width=\"0.5mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"10\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.4mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.4mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"11\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.4mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"DoubleSlim\" Width=\"0.5mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"12\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"None\" Width=\"0.1mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"13\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"DoubleSlim\" Width=\"0.5mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"14\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.4mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.4mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"15\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.4mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.4mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"16\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"DoubleSlim\" Width=\"0.5mm\"/><BOTTOMBORDER Type=\"None\" Width=\"0.1mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"17\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.4mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"DoubleSlim\" Width=\"0.5mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"18\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.4mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"DoubleSlim\" Width=\"0.5mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"19\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.4mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"DoubleSlim\" Width=\"0.5mm\"/><BOTTOMBORDER Type=\"None\" Width=\"0.1mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"20\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.4mm\"/><TOPBORDER Type=\"DoubleSlim\" Width=\"0.5mm\"/><BOTTOMBORDER Type=\"None\" Width=\"0.1mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"21\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.4mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"None\" Width=\"0.1mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"22\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.4mm\"/><TOPBORDER Type=\"None\" Width=\"0.1mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL></BORDERFILLLIST>\n");
			sb.append("		<CHARSHAPELIST Count=\"16\"><CHARSHAPE BorderFillId=\"0\" Height=\"2000\" Id=\"0\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"2\" Hanja=\"2\" Japanese=\"2\" Latin=\"2\" Other=\"2\" Symbol=\"2\" User=\"2\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"2000\" Id=\"1\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"2\" Hanja=\"2\" Japanese=\"2\" Latin=\"2\" Other=\"2\" Symbol=\"2\" User=\"2\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><BOLD/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1000\" Id=\"2\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1000\" Id=\"3\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"4\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"5\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"95\" Hanja=\"95\" Japanese=\"95\" Latin=\"95\" Other=\"95\" Symbol=\"95\" User=\"95\"/><CHARSPACING Hangul=\"-5\" Hanja=\"-5\" Japanese=\"-5\" Latin=\"-5\" Other=\"-5\" Symbol=\"-5\" User=\"-5\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"6\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"95\" Hanja=\"95\" Japanese=\"95\" Latin=\"95\" Other=\"95\" Symbol=\"95\" User=\"95\"/><CHARSPACING Hangul=\"-5\" Hanja=\"-5\" Japanese=\"-5\" Latin=\"-5\" Other=\"-5\" Symbol=\"-5\" User=\"-5\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"7\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><BOLD/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"8\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1200\" Id=\"9\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"10\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"90\" Hanja=\"90\" Japanese=\"90\" Latin=\"90\" Other=\"90\" Symbol=\"90\" User=\"90\"/><CHARSPACING Hangul=\"-10\" Hanja=\"-10\" Japanese=\"-10\" Latin=\"-10\" Other=\"-10\" Symbol=\"-10\" User=\"-10\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"11\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"90\" Hanja=\"90\" Japanese=\"90\" Latin=\"90\" Other=\"90\" Symbol=\"90\" User=\"90\"/><CHARSPACING Hangul=\"-10\" Hanja=\"-10\" Japanese=\"-10\" Latin=\"-10\" Other=\"-10\" Symbol=\"-10\" User=\"-10\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><BOLD/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"12\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"90\" Hanja=\"90\" Japanese=\"90\" Latin=\"90\" Other=\"90\" Symbol=\"90\" User=\"90\"/><CHARSPACING Hangul=\"-15\" Hanja=\"-15\" Japanese=\"-15\" Latin=\"-15\" Other=\"-15\" Symbol=\"-15\" User=\"-15\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"13\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"90\" Hanja=\"90\" Japanese=\"90\" Latin=\"90\" Other=\"90\" Symbol=\"90\" User=\"90\"/><CHARSPACING Hangul=\"-19\" Hanja=\"-19\" Japanese=\"-19\" Latin=\"-19\" Other=\"-19\" Symbol=\"-19\" User=\"-19\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"14\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"90\" Hanja=\"90\" Japanese=\"90\" Latin=\"90\" Other=\"90\" Symbol=\"90\" User=\"90\"/><CHARSPACING Hangul=\"-14\" Hanja=\"-14\" Japanese=\"-14\" Latin=\"-14\" Other=\"-14\" Symbol=\"-14\" User=\"-14\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"15\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"90\" Hanja=\"90\" Japanese=\"90\" Latin=\"90\" Other=\"90\" Symbol=\"90\" User=\"90\"/><CHARSPACING Hangul=\"-23\" Hanja=\"-23\" Japanese=\"-23\" Latin=\"-23\" Other=\"-23\" Symbol=\"-23\" User=\"-23\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE></CHARSHAPELIST>\n");
			sb.append("		<TABDEFLIST Count=\"3\"><TABDEF AutoTabLeft=\"false\" AutoTabRight=\"false\" Id=\"0\"/><TABDEF AutoTabLeft=\"true\" AutoTabRight=\"false\" Id=\"1\"/><TABDEF AutoTabLeft=\"false\" AutoTabRight=\"true\" Id=\"2\"/></TABDEFLIST>\n");
			sb.append("		<NUMBERINGLIST Count=\"1\"><NUMBERING Id=\"1\" Start=\"0\"><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"1\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^1.</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"2\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^2.</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"3\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^3)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"4\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^4)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"5\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">(^5)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"6\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">(^6)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"7\" NumFormat=\"CircledDigit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^7</PARAHEAD></NUMBERING></NUMBERINGLIST>\n");
			sb.append("		<PARASHAPELIST Count=\"25\"><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"0\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"1\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"2\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"true\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"3\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"1\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"-2620\" Left=\"0\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"4\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"5\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"3000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"6\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"2000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"7\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"1\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"4000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"8\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"2\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"6000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"9\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"3\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"8000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"10\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"4\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"10000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"11\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"5\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"12000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"12\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"6\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"14000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"13\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"2\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"150\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"14\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"200\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"15\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"150\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Right\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"16\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"150\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"2000\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"17\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"18\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"19\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"-3560\" Left=\"0\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"20\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"-3616\" Left=\"0\" LineSpacing=\"150\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"21\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"-4008\" Left=\"0\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"22\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"-4112\" Left=\"0\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"23\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"24\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"150\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE></PARASHAPELIST>\n");
			sb.append("		<STYLELIST Count=\"15\"><STYLE CharShape=\"3\" EngName=\"Normal\" Id=\"0\" LangId=\"1042\" LockForm=\"0\" Name=\"바탕글\" NextStyle=\"0\" ParaShape=\"4\" Type=\"Para\"/><STYLE CharShape=\"3\" EngName=\"Body\" Id=\"1\" LangId=\"1042\" LockForm=\"0\" Name=\"본문\" NextStyle=\"1\" ParaShape=\"5\" Type=\"Para\"/><STYLE CharShape=\"3\" EngName=\"Outline 1\" Id=\"2\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 1\" NextStyle=\"2\" ParaShape=\"6\" Type=\"Para\"/><STYLE CharShape=\"3\" EngName=\"Outline 2\" Id=\"3\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 2\" NextStyle=\"3\" ParaShape=\"7\" Type=\"Para\"/><STYLE CharShape=\"3\" EngName=\"Outline 3\" Id=\"4\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 3\" NextStyle=\"4\" ParaShape=\"8\" Type=\"Para\"/><STYLE CharShape=\"3\" EngName=\"Outline 4\" Id=\"5\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 4\" NextStyle=\"5\" ParaShape=\"9\" Type=\"Para\"/><STYLE CharShape=\"3\" EngName=\"Outline 5\" Id=\"6\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 5\" NextStyle=\"6\" ParaShape=\"10\" Type=\"Para\"/><STYLE CharShape=\"3\" EngName=\"Outline 6\" Id=\"7\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 6\" NextStyle=\"7\" ParaShape=\"11\" Type=\"Para\"/><STYLE CharShape=\"3\" EngName=\"Outline 7\" Id=\"8\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 7\" NextStyle=\"8\" ParaShape=\"12\" Type=\"Para\"/><STYLE CharShape=\"2\" EngName=\"Page Number\" Id=\"9\" LangId=\"1042\" LockForm=\"0\" Name=\"쪽 번호\" NextStyle=\"9\" ParaShape=\"4\" Type=\"Para\"/><STYLE CharShape=\"4\" EngName=\"Header\" Id=\"10\" LangId=\"1042\" LockForm=\"0\" Name=\"머리말\" NextStyle=\"10\" ParaShape=\"13\" Type=\"Para\"/><STYLE CharShape=\"5\" EngName=\"Footnote\" Id=\"11\" LangId=\"1042\" LockForm=\"0\" Name=\"각주\" NextStyle=\"11\" ParaShape=\"3\" Type=\"Para\"/><STYLE CharShape=\"5\" EngName=\"Endnote\" Id=\"12\" LangId=\"1042\" LockForm=\"0\" Name=\"미주\" NextStyle=\"12\" ParaShape=\"3\" Type=\"Para\"/><STYLE CharShape=\"6\" EngName=\"Memo\" Id=\"13\" LangId=\"1042\" LockForm=\"0\" Name=\"메모\" NextStyle=\"13\" ParaShape=\"2\" Type=\"Para\"/><STYLE CharShape=\"0\" Id=\"14\" LangId=\"1042\" LockForm=\"0\" Name=\"중간제목(옛체20)\" NextStyle=\"14\" ParaShape=\"1\" Type=\"Para\"/></STYLELIST>\n");
			sb.append("	</MAPPINGTABLE>\n");
			sb.append("</HEAD>\n");
			sb.append("<BODY><SECTION Id=\"0\">\n");
			sb.append("	<P ColumnBreak=\"false\" PageBreak=\"false\" ParaShape=\"1\" Style=\"14\"><TEXT CharShape=\"1\">\n");
			sb.append("		<SECDEF CharGrid=\"0\" FirstBorder=\"false\" FirstFill=\"false\" LineGrid=\"0\" OutlineShape=\"1\" SpaceColumns=\"1134\" TabStop=\"8000\" TextDirection=\"0\" TextVerticalWidthHead=\"0\"><STARTNUMBER Equation=\"0\" Figure=\"0\" Page=\"0\" PageStartsOn=\"Both\" Table=\"0\"/><HIDE Border=\"false\" EmptyLine=\"false\" Fill=\"false\" Footer=\"false\" Header=\"false\" MasterPage=\"false\" PageNumPos=\"false\"/><PAGEDEF GutterType=\"LeftOnly\" Height=\"84188\" Landscape=\"0\" Width=\"59528\"><PAGEMARGIN Bottom=\"0\" Footer=\"4252\" Gutter=\"0\" Header=\"4252\" Left=\"4252\" Right=\"4110\" Top=\"0\"/></PAGEDEF><FOOTNOTESHAPE><AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/><NOTELINE Length=\"5cm\" Type=\"Solid\" Width=\"0.12mm\"/><NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"283\"/><NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/><NOTEPLACEMENT BeneathText=\"false\" Place=\"EachColumn\"/></FOOTNOTESHAPE><ENDNOTESHAPE><AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/><NOTELINE Length=\"14692344\" Type=\"Solid\" Width=\"0.12mm\"/><NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"0\"/><NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/><NOTEPLACEMENT BeneathText=\"false\" Place=\"EndOfDocument\"/></ENDNOTESHAPE><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Both\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Even\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Odd\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL></SECDEF>\n");
			sb.append("		<COLDEF Count=\"1\" Layout=\"Left\" SameGap=\"0\" SameSize=\"true\" Type=\"Newspaper\"/>\n");
			sb.append("		<CHAR>항만 하역장비 안전 점검표</CHAR>\n");
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ParaShape=\"1\" Style=\"14\"><TEXT CharShape=\"1\"/></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR> </CHAR></TEXT><TEXT CharShape=\"8\"><CHAR>○ 시설명 : </CHAR></TEXT><TEXT CharShape=\"7\"><CHAR>" + fcltsMngGroupNm + "</CHAR></TEXT></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"8\"/></P><P ParaShape=\"16\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>※ 정상 ○ 요주의 : △ 불량 : ×</CHAR></TEXT></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"8\">\n");
			sb.append("		<TABLE BorderFill=\"1\" CellSpacing=\"0\" ColCount=\"4\" PageBreak=\"None\" RepeatHeader=\"true\" RowCount=\"14\">\n");
			sb.append("			<SHAPEOBJECT InstId=\"2052475347\" Lock=\"false\" NumberingType=\"Table\" ZOrder=\"0\"><SIZE Height=\"56952\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"50450\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"false\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"0\" HorzRelTo=\"Column\" TreatAsChar=\"true\" VertAlign=\"Top\" VertOffset=\"0\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/></SHAPEOBJECT>\n");
			sb.append("			<INSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"14\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>장   비   명</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"15\" ColAddr=\"1\" ColSpan=\"3\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"38474\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>컨  테  이  너  크  레  인</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"17\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>점 검 개 소</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"13\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"22768\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>점검사항</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"13\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"8845\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>점검결과</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"18\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"6861\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>비  고</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"19\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"11\"><CHAR>(기 계 부)</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"16\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"22768\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"12\"/></P></PARALIST></CELL>\n");
			sb.append("				<CELL BorderFill=\"16\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"8845\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("				<CELL BorderFill=\"20\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"6861\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"21\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>1. 주행장치</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"12\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"22768\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>○ 갠트리 트럭의 각부 변형 및 손상 여부</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>○ 휠의 상태</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"12\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"8845\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02010101") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02010102") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"22\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"6861\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5888\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>2. 구 조 부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5888\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"22768\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>○ 구조부재의 변형여부</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>○ 구조부재의 외부부식 및 도장상태</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>○ 트로리 레일 마모 및 용접상태</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5888\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"8845\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02010201") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02010202") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02010203") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5888\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"6861\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>3. 주 권상장치</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"22768\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>○ 주 권상용 로우프드럼의 손상 여부</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>○ </CHAR></TEXT><TEXT CharShape=\"13\"><CHAR>주 권상 시이브 플랜지의 파손 및 변형여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"8845\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02010301") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02010302") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"6861\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"9528\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>4. 트로리 드라이브</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"9528\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"22768\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>○ TROLLEY FRAME의 변형 및 부식여부</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>○ TROLLEY WHEEL 상태</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>○ ROPE CLAMPING 상태</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>○ SHEAVE FRANGE 마모 및 손상여부</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>○ 로우프 드럼의 손상여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"9528\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"8845\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02010401") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02010402") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02010403") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02010404") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02010405") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"9528\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"6861\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5888\" Protect=\"false\" RowAddr=\"7\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>5. 스프레다 장치</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5888\" Protect=\"false\" RowAddr=\"7\" RowSpan=\"1\" Width=\"22768\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>○ 신축 프레임의 변형여부</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>○ 트위스트록, 플리퍼 변형여부</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>○ 헤드블록 프레임의 변형여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5888\" Protect=\"false\" RowAddr=\"7\" RowSpan=\"1\" Width=\"8845\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02010501") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02010502") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02010503") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5888\" Protect=\"false\" RowAddr=\"7\" RowSpan=\"1\" Width=\"6861\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5888\" Protect=\"false\" RowAddr=\"8\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>6. 운 전 실</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5888\" Protect=\"false\" RowAddr=\"8\" RowSpan=\"1\" Width=\"22768\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>○ 운전실과 몸체간의 고착부 균열 및 볼트 탈락여부</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>○ 소화기 비치 및 정기점검 여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5888\" Protect=\"false\" RowAddr=\"8\" RowSpan=\"1\" Width=\"8845\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02010601") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"/></P>");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02010602") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5888\" Protect=\"false\" RowAddr=\"8\" RowSpan=\"1\" Width=\"6861\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"9\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>7. 기 계 실</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"9\" RowSpan=\"1\" Width=\"22768\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>○ 소화기 비치 및 정기점검 여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"9\" RowSpan=\"1\" Width=\"8845\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02010701") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"9\" RowSpan=\"1\" Width=\"6861\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"10\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>8. 기    타</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"10\" RowSpan=\"1\" Width=\"22768\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>○ 장비점검 이행상태 여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"10\" RowSpan=\"1\" Width=\"8845\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02010801") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"10\" RowSpan=\"1\" Width=\"6861\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"11\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"11\"><CHAR>(전 기 부)</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"11\" RowSpan=\"1\" Width=\"22768\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"12\"/></P></PARALIST></CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"11\" RowSpan=\"1\" Width=\"8845\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"11\" RowSpan=\"1\" Width=\"6861\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\"12\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>1. 직류전동기</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\"12\" RowSpan=\"1\" Width=\"22768\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>○ 각부 이상진동 및 이상음 발생여부</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>○ 본체의 고정볼트, 너트 이완여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\"12\" RowSpan=\"1\" Width=\"8845\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02020101") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02020102") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\"12\" RowSpan=\"1\" Width=\"6861\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\"13\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>2. </CHAR></TEXT><TEXT CharShape=\"14\"><CHAR>수배전반 및 제어반</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\"13\" RowSpan=\"1\" Width=\"22768\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>○ 과전류 개폐기의 정상 작동여부</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>○ 반내 배선 결속상태</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\"13\" RowSpan=\"1\" Width=\"8845\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02020201") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02020202") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\"13\" RowSpan=\"1\" Width=\"6861\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("		</TABLE>\n");
			sb.append("		<CHAR/>\n");
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\"/></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\"/></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\">\n");
			sb.append("		<TABLE BorderFill=\"1\" CellSpacing=\"0\" ColCount=\"4\" PageBreak=\"None\" RepeatHeader=\"true\" RowCount=\"6\">\n");
			sb.append("			<SHAPEOBJECT InstId=\"2052475348\" Lock=\"false\" NumberingType=\"Table\" ZOrder=\"1\"><SIZE Height=\"28720\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"50167\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"false\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"0\" HorzRelTo=\"Column\" TreatAsChar=\"true\" VertAlign=\"Top\" VertOffset=\"0\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/></SHAPEOBJECT>\n");
			sb.append("			<INSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"14\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2816\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>장   비   명</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"15\" ColAddr=\"1\" ColSpan=\"3\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2816\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"38191\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>컨  테  이  너  크  레  인</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"17\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3099\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>점 검 개 소</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"13\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3099\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"22768\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>점    검    사    항</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"13\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3099\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"8279\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>점검결과</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"18\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3099\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"7144\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>비  고</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"11\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5489\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>3. 케이블</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"8\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5489\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"22768\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>○ 주행케이블 파손, 오손 및 변형여부</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>○ </CHAR></TEXT><TEXT CharShape=\"15\"><CHAR>FESTOON CABLE 파손, 오손 및 변형여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"8\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5489\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"8279\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02020301") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02020302") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"9\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5489\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"7144\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5772\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>4. 운 전 실</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5772\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"22768\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>○ 기기의 부착상태</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>○ 경고장치 정상 작동여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5772\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"8279\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02020401") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02020402") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5772\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"7144\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5772\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>5. 각종 SWITCH</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5772\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"22768\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>○ 각 리미트 스위치의 정상 작동여부</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>○ 각 스위치의 고정상태 및 방수상태</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5772\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"8279\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02020501") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02020502") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5772\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"7144\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"10\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5772\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>6. 조 명 등</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"6\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5772\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"22768\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>○ 항공장애등 및 WALK WAY등의 정상작동여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"6\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5772\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"8279\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02020601") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"7\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5772\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"7144\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("		</TABLE>\n");
			sb.append("		<CHAR/>\n");
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\"/></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\"/></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\"/></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\"/></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\"/></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\"/></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\"/></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\"/></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\"/></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\"/></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\"/></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\"/></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\"/></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\"/></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\"/></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\"/></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\"/></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\"/></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\"/></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\"/></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\"/></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\"/></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\"/></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\"/></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\"/></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\"/></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\">\n");
			sb.append("		<TABLE BorderFill=\"1\" CellSpacing=\"0\" ColCount=\"4\" PageBreak=\"None\" RepeatHeader=\"true\" RowCount=\"15\">\n");
			sb.append("			<SHAPEOBJECT InstId=\"2052475349\" Lock=\"false\" NumberingType=\"Table\" ZOrder=\"2\"><SIZE Height=\"57664\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"50167\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"false\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"0\" HorzRelTo=\"Column\" TreatAsChar=\"true\" VertAlign=\"Top\" VertOffset=\"0\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/></SHAPEOBJECT>\n");
			sb.append("			<INSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"14\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2532\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>장   비   명</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"15\" ColAddr=\"1\" ColSpan=\"3\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2532\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"38191\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>트  란  스  퍼  크  레  인</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"17\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2532\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>점 검 개 소</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"13\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2532\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"22768\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>점    검    사    항</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"13\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2532\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"8279\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>점검결과</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"18\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2532\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"7144\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>비  고</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"11\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2532\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"11\"><CHAR>(기 계 부)</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"8\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2532\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"22768\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("				<CELL BorderFill=\"8\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2532\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"8279\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("				<CELL BorderFill=\"9\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2532\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"7144\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>1. 주행장치</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"22768\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>○ 갠트리 트럭의 각부 변형 및 손상 여부</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>○ 휠의 상태</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"8279\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02030101") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02030102") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"7144\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>2. 주 권상장치</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"22768\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>○ 주 권상 로우프드럼의 손상여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"8279\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02030201") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"7144\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5888\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>3. 트로리 드라이브</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5888\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"22768\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>○ 프레임의 부식 및 변형여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>○ 시이브 플랜지의 파손 및 변형여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>○ 트로리 레일의 마모 및 용접상태</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5888\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"8279\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02030301") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02030302") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02030303") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5888\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"7144\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5888\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>4. 스프레다 장치</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5888\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"22768\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>○ 신축 프레임 변형여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>○ 플리퍼 및 트위스트로크의 변형여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>○ 헤드블록 프레임의 변형여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5888\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"8279\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02030401") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02030402") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02030403") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5888\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"7144\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5888\" Protect=\"false\" RowAddr=\"7\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>5. 운 전 실</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5888\" Protect=\"false\" RowAddr=\"7\" RowSpan=\"1\" Width=\"22768\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"21\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>○ 운전실과 몸체간의 고착부의 균열 및 보울트의 탈락여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"21\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>○ 소화기 비치 및 정기점검 여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5888\" Protect=\"false\" RowAddr=\"7\" RowSpan=\"1\" Width=\"8279\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02030501") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02030502") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5888\" Protect=\"false\" RowAddr=\"7\" RowSpan=\"1\" Width=\"7144\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"8\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>6. 기    타</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"8\" RowSpan=\"1\" Width=\"22768\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>○ 장비점검 이행상태 여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"8\" RowSpan=\"1\" Width=\"8279\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02030601") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"8\" RowSpan=\"1\" Width=\"7144\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"9\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"11\"><CHAR>(전 기 부)</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"9\" RowSpan=\"1\" Width=\"22768\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"9\" RowSpan=\"1\" Width=\"8279\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"9\" RowSpan=\"1\" Width=\"7144\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\"10\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>1. 직류전동기</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\"10\" RowSpan=\"1\" Width=\"22768\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>○ 각부 이상진동 및 이상음 발생여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>○ 본체의 고정볼트 및 너트 이완여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\"10\" RowSpan=\"1\" Width=\"8279\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02040101") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02040102") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\"10\" RowSpan=\"1\" Width=\"7144\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\"11\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>2. 배전반</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\"11\" RowSpan=\"1\" Width=\"22768\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>○ 반내 배선 결속상태</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>○ 각 스위치의 작동상태</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\"11\" RowSpan=\"1\" Width=\"8279\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02040201") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02040202") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\"11\" RowSpan=\"1\" Width=\"7144\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5888\" Protect=\"false\" RowAddr=\"12\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>3. CABLE</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5888\" Protect=\"false\" RowAddr=\"12\" RowSpan=\"1\" Width=\"22768\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"22\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>○ FESTOON CABLE의 파손, 오손 및 변형여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"22\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>○ CABLE BAND의 부착상태</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5888\" Protect=\"false\" RowAddr=\"12\" RowSpan=\"1\" Width=\"8279\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02040301") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02040302") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"5888\" Protect=\"false\" RowAddr=\"12\" RowSpan=\"1\" Width=\"7144\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\"13\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>4. 운 전 실</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\"13\" RowSpan=\"1\" Width=\"22768\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>○ 실내 각 기기의 부착상태 및 배선상태</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>○ 경고장치의 정상 작동여부</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\"13\" RowSpan=\"1\" Width=\"8279\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02040401") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02040402") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\"13\" RowSpan=\"1\" Width=\"7144\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"10\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\"14\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>5. 각종 스위치</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"6\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\"14\" RowSpan=\"1\" Width=\"22768\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>○ 각 리미트 스위치 정상 작동여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>○ 스위치의 고정상태 및 방수상태</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"6\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\"14\" RowSpan=\"1\" Width=\"8279\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02040501") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>" + getResultItemSymbol("M02040502") + "</CHAR></TEXT><TEXT CharShape=\"8\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"7\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\"14\" RowSpan=\"1\" Width=\"7144\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("		</TABLE>\n");
			sb.append("		<CHAR/>\n");
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\"/></P>\n");
			sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"3\"/></P>\n");
			sb.append("	<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"8\">\n");
			if(chargerInfo1 != null) {
				if((Boolean)chargerInfo1.get("fileExists")) {
					sb.append("		<PICTURE Reverse=\"false\">\n");
					sb.append("			<SHAPEOBJECT InstId=\"2052634211\" Lock=\"false\" NumberingType=\"Figure\" TextWrap=\"BehindText\" ZOrder=\"3\"><SIZE Height=\"4500\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"3840\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"true\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"41940\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"480\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/></SHAPEOBJECT>\n");
					sb.append("			<SHAPECOMPONENT GroupLevel=\"0\" HorzFlip=\"false\" InstID=\"978892388\" OriHeight=\"4500\" OriWidth=\"3840\" VertFlip=\"false\" XPos=\"0\" YPos=\"0\"><ROTATIONINFO Angle=\"0\" CenterX=\"1920\" CenterY=\"2250\"/><RENDERINGINFO><TRANSMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/></RENDERINGINFO></SHAPECOMPONENT>\n");
					sb.append("			<IMAGERECT X0=\"0\" X1=\"3840\" X2=\"3840\" X3=\"0\" Y0=\"0\" Y1=\"0\" Y2=\"4500\" Y3=\"4500\"/>\n");
					sb.append("			<IMAGECLIP Bottom=\"4500\" Left=\"0\" Right=\"3840\" Top=\"0\"/>\n");
					sb.append("			<INSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/>\n");
					sb.append("			<IMAGE Alpha=\"0\" BinItem=\"" + (Integer)chargerInfo1.get("binId") + "\" Bright=\"0\" Contrast=\"0\" Effect=\"RealPic\"/>\n");
					sb.append("		</PICTURE>\n");
				}
			}
			sb.append("		<CHAR>                                        점검일자 : </CHAR></TEXT><TEXT CharShape=\"7\"><CHAR>"+ ((qcDetailData.get("wrtDtHwp") != null) ? (String) qcDetailData.get("wrtDtHwp") : "" ) + "</CHAR>\n");
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ParaShape=\"0\" Style=\"0\"><TEXT CharShape=\"8\">\n");
			if(chargerInfo2 != null) {
				if((Boolean)chargerInfo2.get("fileExists")) {
					sb.append("		<PICTURE Reverse=\"false\">\n");
					sb.append("			<SHAPEOBJECT InstId=\"2056047213\" Lock=\"false\" NumberingType=\"Figure\" TextWrap=\"BehindText\" ZOrder=\"4\"><SIZE Height=\"4500\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"3840\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"true\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"42060\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"1880\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/></SHAPEOBJECT>\n");
					sb.append("			<SHAPECOMPONENT GroupLevel=\"0\" HorzFlip=\"false\" InstID=\"982305390\" OriHeight=\"4500\" OriWidth=\"3840\" VertFlip=\"false\" XPos=\"0\" YPos=\"0\"><ROTATIONINFO Angle=\"0\" CenterX=\"1920\" CenterY=\"2250\"/><RENDERINGINFO><TRANSMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/></RENDERINGINFO></SHAPECOMPONENT>\n");
					sb.append("			<IMAGERECT X0=\"0\" X1=\"3840\" X2=\"3840\" X3=\"0\" Y0=\"0\" Y1=\"0\" Y2=\"4500\" Y3=\"4500\"/>\n");
					sb.append("			<IMAGECLIP Bottom=\"4500\" Left=\"0\" Right=\"3840\" Top=\"0\"/>\n");
					sb.append("			<INSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/>\n");
					sb.append("			<IMAGE Alpha=\"0\" BinItem=\"" + (Integer)chargerInfo2.get("binId") + "\" Bright=\"0\" Contrast=\"0\" Effect=\"RealPic\"/>\n");
					sb.append("		</PICTURE>\n");
				}
			}
			if(chargerInfo1 != null) {
				sb.append("		<CHAR>                                        점 검 자 : " + ((chargerInfo1.get("chargerNm") != null) ? (String)chargerInfo1.get("chargerNm") :  "      ") + "    (인)</CHAR>\n");
			} else {
				sb.append("		<CHAR>                                        점 검 자 :           (인)</CHAR>\n");
			}
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ParaShape=\"0\" Style=\"0\"><TEXT CharShape=\"8\">\n");
			if(chargerInfo2 != null) {
				sb.append("		<CHAR>                                                   " + ((chargerInfo2.get("chargerNm") != null) ? (String)chargerInfo2.get("chargerNm") :  "      ") + "    (인) </CHAR>\n");
			} else {
				sb.append("		<CHAR/>\n");
			}
			sb.append("	</TEXT></P>\n");
			sb.append("</SECTION></BODY>\n");
			sb.append("<TAIL>\n");
			sb.append(getBinDataListElement());
			sb.append("<SCRIPTCODE Type=\"JScript\" Version=\"1.0\"><SCRIPTHEADER>var Documents = XHwpDocuments;\n");
			sb.append("var Document = Documents.Active_XHwpDocument;\n");
			sb.append("</SCRIPTHEADER><SCRIPTSOURCE>function OnDocument_New() {}\n");
			sb.append("</SCRIPTSOURCE></SCRIPTCODE></TAIL></HWPML>");			
			return sb.toString();
		}
		
		/**정보통신 시설물 점검표*/
		protected String getInfoCommHwpReport() {
			StringBuilder sb = new StringBuilder();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>\n");
			sb.append("<HWPML Style=\"embed\" SubVersion=\"7.0.0.0\" Version=\"2.7\">\n");
			sb.append("<HEAD SecCnt=\"1\">\n");
			sb.append("	<DOCSUMMARY><TITLE>정보통신 시설물 점검표</TITLE><AUTHOR>YGPA GIS Assets Management System</AUTHOR><DATE>" + docDate + "</DATE></DOCSUMMARY>\n");
			sb.append("	<DOCSETTING><BEGINNUMBER Endnote=\"1\" Equation=\"1\" Footnote=\"1\" Page=\"1\" Picture=\"1\" Table=\"1\"/><CARETPOS List=\"0\" Para=\"0\" Pos=\"33\"/></DOCSETTING>\n");
			sb.append("	<MAPPINGTABLE>\n");
			sb.append(getBinItemListElement());
			sb.append("		<FACENAMELIST><FONTFACE Count=\"4\" Lang=\"Hangul\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"휴먼명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT><FONT Id=\"3\" Name=\"휴먼옛체\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"4\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"4\" Lang=\"Latin\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양궁서\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"4\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT><FONT Id=\"3\" Name=\"HCI Poppy\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Hanja\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Japanese\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Other\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Symbol\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"User\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE></FACENAMELIST>\n");
			sb.append("		<BORDERFILLLIST Count=\"5\"><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"1\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"2\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"None\" Width=\"0.1mm\"/><RIGHTBORDER Type=\"None\" Width=\"0.1mm\"/><TOPBORDER Type=\"None\" Width=\"0.1mm\"/><BOTTOMBORDER Type=\"None\" Width=\"0.1mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.1mm\"/><FILLBRUSH><WINDOWBRUSH Alpha=\"0\" FaceColor=\"4294967295\" HatchColor=\"4278190080\"/></FILLBRUSH></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"3\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.4mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.4mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.4mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"4\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.4mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.4mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"5\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.4mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.4mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.4mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL></BORDERFILLLIST>\n");
			sb.append("		<CHARSHAPELIST Count=\"12\"><CHARSHAPE BorderFillId=\"0\" Height=\"1000\" Id=\"0\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1000\" Id=\"1\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"2\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"3\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"95\" Hanja=\"95\" Japanese=\"95\" Latin=\"95\" Other=\"95\" Symbol=\"95\" User=\"95\"/><CHARSPACING Hangul=\"-5\" Hanja=\"-5\" Japanese=\"-5\" Latin=\"-5\" Other=\"-5\" Symbol=\"-5\" User=\"-5\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"4\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"95\" Hanja=\"95\" Japanese=\"95\" Latin=\"95\" Other=\"95\" Symbol=\"95\" User=\"95\"/><CHARSPACING Hangul=\"-5\" Hanja=\"-5\" Japanese=\"-5\" Latin=\"-5\" Other=\"-5\" Symbol=\"-5\" User=\"-5\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"5\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"6\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><BOLD/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"7\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"2\" Hanja=\"1\" Japanese=\"1\" Latin=\"3\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1200\" Id=\"8\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"2\" Hanja=\"1\" Japanese=\"1\" Latin=\"3\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"9\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"2\" Hanja=\"1\" Japanese=\"1\" Latin=\"3\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"95\" Hanja=\"95\" Japanese=\"95\" Latin=\"95\" Other=\"95\" Symbol=\"95\" User=\"95\"/><CHARSPACING Hangul=\"-10\" Hanja=\"-10\" Japanese=\"-10\" Latin=\"-10\" Other=\"-10\" Symbol=\"-10\" User=\"-10\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"2000\" Id=\"10\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"3\" Hanja=\"2\" Japanese=\"2\" Latin=\"2\" Other=\"2\" Symbol=\"2\" User=\"2\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"2000\" Id=\"11\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"3\" Hanja=\"2\" Japanese=\"2\" Latin=\"2\" Other=\"2\" Symbol=\"2\" User=\"2\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><BOLD/></CHARSHAPE></CHARSHAPELIST>\n");
			sb.append("		<TABDEFLIST Count=\"3\"><TABDEF AutoTabLeft=\"false\" AutoTabRight=\"false\" Id=\"0\"/><TABDEF AutoTabLeft=\"true\" AutoTabRight=\"false\" Id=\"1\"/><TABDEF AutoTabLeft=\"false\" AutoTabRight=\"true\" Id=\"2\"/></TABDEFLIST>\n");
			sb.append("		<NUMBERINGLIST Count=\"1\"><NUMBERING Id=\"1\" Start=\"0\"><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"1\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^1.</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"2\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^2.</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"3\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^3)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"4\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^4)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"5\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">(^5)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"6\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">(^6)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"7\" NumFormat=\"CircledDigit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^7</PARAHEAD></NUMBERING></NUMBERINGLIST>\n");
			sb.append("		<PARASHAPELIST Count=\"20\"><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"0\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"1\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"100\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"2\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"3\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"true\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"4\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"1\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"-2620\" Left=\"0\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"5\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"3000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"6\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"2000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"7\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"1\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"4000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"8\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"2\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"6000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"9\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"3\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"8000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"10\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"4\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"10000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"11\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"5\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"12000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"12\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"6\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"14000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"13\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"2\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"150\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"14\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"15\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"1000\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"1000\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Right\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"16\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"3000\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"17\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"1000\" LineSpacing=\"100\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"1000\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"18\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"1000\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"1000\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"19\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"50\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE></PARASHAPELIST>\n");
			sb.append("		<STYLELIST Count=\"15\"><STYLE CharShape=\"1\" EngName=\"Normal\" Id=\"0\" LangId=\"1042\" LockForm=\"0\" Name=\"바탕글\" NextStyle=\"0\" ParaShape=\"2\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Body\" Id=\"1\" LangId=\"1042\" LockForm=\"0\" Name=\"본문\" NextStyle=\"1\" ParaShape=\"5\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 1\" Id=\"2\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 1\" NextStyle=\"2\" ParaShape=\"6\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 2\" Id=\"3\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 2\" NextStyle=\"3\" ParaShape=\"7\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 3\" Id=\"4\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 3\" NextStyle=\"4\" ParaShape=\"8\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 4\" Id=\"5\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 4\" NextStyle=\"5\" ParaShape=\"9\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 5\" Id=\"6\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 5\" NextStyle=\"6\" ParaShape=\"10\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 6\" Id=\"7\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 6\" NextStyle=\"7\" ParaShape=\"11\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 7\" Id=\"8\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 7\" NextStyle=\"8\" ParaShape=\"12\" Type=\"Para\"/><STYLE CharShape=\"0\" EngName=\"Page Number\" Id=\"9\" LangId=\"1042\" LockForm=\"0\" Name=\"쪽 번호\" NextStyle=\"9\" ParaShape=\"2\" Type=\"Para\"/><STYLE CharShape=\"2\" EngName=\"Header\" Id=\"10\" LangId=\"1042\" LockForm=\"0\" Name=\"머리말\" NextStyle=\"10\" ParaShape=\"13\" Type=\"Para\"/><STYLE CharShape=\"3\" EngName=\"Footnote\" Id=\"11\" LangId=\"1042\" LockForm=\"0\" Name=\"각주\" NextStyle=\"11\" ParaShape=\"4\" Type=\"Para\"/><STYLE CharShape=\"3\" EngName=\"Endnote\" Id=\"12\" LangId=\"1042\" LockForm=\"0\" Name=\"미주\" NextStyle=\"12\" ParaShape=\"4\" Type=\"Para\"/><STYLE CharShape=\"4\" EngName=\"Memo\" Id=\"13\" LangId=\"1042\" LockForm=\"0\" Name=\"메모\" NextStyle=\"13\" ParaShape=\"3\" Type=\"Para\"/><STYLE CharShape=\"10\" Id=\"14\" LangId=\"1042\" LockForm=\"0\" Name=\"중간제목(옛체20)\" NextStyle=\"14\" ParaShape=\"0\" Type=\"Para\"/></STYLELIST>\n");
			sb.append("	</MAPPINGTABLE>\n");
			sb.append("</HEAD>\n");
			sb.append("<BODY><SECTION Id=\"0\">\n");
			sb.append("	<P ColumnBreak=\"false\" PageBreak=\"false\" ParaShape=\"0\" Style=\"14\"><TEXT CharShape=\"11\">\n");
			sb.append("		<SECDEF CharGrid=\"0\" FirstBorder=\"false\" FirstFill=\"false\" LineGrid=\"0\" OutlineShape=\"1\" SpaceColumns=\"1134\" TabStop=\"8000\" TextDirection=\"0\" TextVerticalWidthHead=\"0\"><STARTNUMBER Equation=\"0\" Figure=\"0\" Page=\"0\" PageStartsOn=\"Both\" Table=\"0\"/><HIDE Border=\"false\" EmptyLine=\"false\" Fill=\"false\" Footer=\"false\" Header=\"false\" MasterPage=\"false\" PageNumPos=\"false\"/><PAGEDEF GutterType=\"LeftOnly\" Height=\"84188\" Landscape=\"0\" Width=\"59528\"><PAGEMARGIN Bottom=\"0\" Footer=\"4252\" Gutter=\"0\" Header=\"2835\" Left=\"4252\" Right=\"4252\" Top=\"4252\"/></PAGEDEF><FOOTNOTESHAPE><AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/><NOTELINE Length=\"5cm\" Type=\"Solid\" Width=\"0.12mm\"/><NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"283\"/><NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/><NOTEPLACEMENT BeneathText=\"false\" Place=\"EachColumn\"/></FOOTNOTESHAPE><ENDNOTESHAPE><AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/><NOTELINE Length=\"14692344\" Type=\"Solid\" Width=\"0.12mm\"/><NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"0\"/><NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/><NOTEPLACEMENT BeneathText=\"false\" Place=\"EndOfDocument\"/></ENDNOTESHAPE><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Both\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Even\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Odd\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL></SECDEF>\n");
			sb.append("		<COLDEF Count=\"1\" Layout=\"Left\" SameGap=\"0\" SameSize=\"true\" Type=\"Newspaper\"/>\n");
			sb.append("		<CHAR>정 보 통 신 시 설 점 검 표</CHAR>\n");
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ParaShape=\"0\" Style=\"14\"><TEXT CharShape=\"11\"/></P>\n");
			sb.append("	<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>   ○ 시설명 : " + fcltsMngGroupNm + "</CHAR></TEXT></P>\n");
			sb.append("	<P ParaShape=\"16\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>※ 정상 : ○  요주의 : △  불량 : ×</CHAR></TEXT></P>\n");
			sb.append("	<P ParaShape=\"0\" Style=\"0\"><TEXT CharShape=\"7\">\n");
			sb.append("		<TABLE BorderFill=\"1\" CellSpacing=\"0\" ColCount=\"4\" PageBreak=\"None\" RepeatHeader=\"true\" RowCount=\"5\">\n");
			sb.append("			<SHAPEOBJECT InstId=\"2055715170\" Lock=\"false\" NumberingType=\"Table\" ZOrder=\"2\"><SIZE Height=\"52364\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"47888\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"false\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"0\" HorzRelTo=\"Column\" TreatAsChar=\"true\" VertAlign=\"Top\" VertOffset=\"0\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/></SHAPEOBJECT>\n");
			sb.append("			<INSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"1964\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"8140\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>시 설 명</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"1964\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"24896\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>점 검 사 항</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"1964\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"8703\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>결    과</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"1964\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"6149\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>비 고</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"11632\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"8140\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>통합배선</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>설비</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"11632\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"24896\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"/></P>\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>1. 단자의 접속상태</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>2. 접촉부, 조작부의 작동상태</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>3. 접지저항의 적합여부</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>4. 설비의 외관상태</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>5. 국선의 이상유무</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>6. 단자함 외관의 이상유무</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"11632\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"8703\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>" + getResultItemSymbol("I01010000") + "</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>" + getResultItemSymbol("I01020000") + "</CHAR></TEXT><TEXT CharShape=\"9\"/></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>" + getResultItemSymbol("I01030000") + "</CHAR></TEXT><TEXT CharShape=\"9\"/></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>" + getResultItemSymbol("I01040000") + "</CHAR></TEXT><TEXT CharShape=\"9\"/></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>" + getResultItemSymbol("I01050000") + "</CHAR></TEXT><TEXT CharShape=\"9\"/></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>" + getResultItemSymbol("I01060000") + "</CHAR></TEXT><TEXT CharShape=\"9\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"11632\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"6149\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"6172\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"8140\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>방송설비</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"6172\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"24896\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"/></P>\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>1. 단자의 접속상태</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>2. 구분별 지역별 방송상태</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>3. Main Amp의 출력상태</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>4. 개별 스피커의 동작상태</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"6172\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"8703\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>" + getResultItemSymbol("I02010000") + "</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>" + getResultItemSymbol("I02020000") + "</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>" + getResultItemSymbol("I02030000") + "</CHAR></TEXT><TEXT CharShape=\"9\"/></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>" + getResultItemSymbol("I02040000") + "</CHAR></TEXT><TEXT CharShape=\"9\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"6172\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"6149\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"6172\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"8140\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>TV공시청</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>설비</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"6172\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"24896\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"/></P>\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>1. 단자의 접속상태</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>2. Head End 접속상태</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>3. 절연저항의 적합여부</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"6172\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"8703\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"/></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>" + getResultItemSymbol("I03010000") + "</CHAR></TEXT><TEXT CharShape=\"9\"/></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>" + getResultItemSymbol("I03020000") + "</CHAR></TEXT><TEXT CharShape=\"9\"/></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>" + getResultItemSymbol("I03030000") + "</CHAR></TEXT><TEXT CharShape=\"9\"/></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"6172\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"6149\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"11632\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"8140\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>외곽울타리방호설비</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"11632\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"24896\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"/></P>\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>1. 센서의 감지동작상태</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>2. 각 CCTV 카메라의 동작상태</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>3. CCTV 카메라 컨트롤의 동작상태</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>4. 각 모니터의 영상재생상태</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>5. 영상 녹화장치의 동작상태</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>6. Alarm의 이상유무</CHAR></TEXT></P>\n");
			sb.append("						<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>7. 센서와 CCTV의 연동상태</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"4\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"11632\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"8703\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"/></P>\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>     " + getResultItemSymbol("I04010000") + "</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>     " + getResultItemSymbol("I04020000") + "</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>     " + getResultItemSymbol("I04030000") + "</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>     " + getResultItemSymbol("I04040000") + "</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>     " + getResultItemSymbol("I04050000") + "</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>     " + getResultItemSymbol("I04060000") + "</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>     " + getResultItemSymbol("I04070000") + "</CHAR></TEXT></P>\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"11632\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"6149\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\"/></P></PARALIST></CELL>\n");
			sb.append("			</ROW>\n");
			sb.append("		</TABLE>\n");
			sb.append("		<CHAR/>\n");
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"/></P>\n");
			sb.append("	<P ParaShape=\"2\" Style=\"0\"><TEXT CharShape=\"5\">\n");
			if(chargerInfo1 != null) {
				if((Boolean)chargerInfo1.get("fileExists")) {
					sb.append("		<PICTURE Reverse=\"false\">\n");
					sb.append("			<SHAPEOBJECT InstId=\"2056031443\" Lock=\"false\" NumberingType=\"Figure\" TextWrap=\"BehindText\" ZOrder=\"3\"><SIZE Height=\"4500\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"3840\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"true\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"42600\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"266\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/></SHAPEOBJECT>\n");
					sb.append("			<SHAPECOMPONENT GroupLevel=\"0\" HorzFlip=\"false\" InstID=\"982289620\" OriHeight=\"4500\" OriWidth=\"3840\" VertFlip=\"false\" XPos=\"0\" YPos=\"0\"><ROTATIONINFO Angle=\"0\" CenterX=\"1920\" CenterY=\"2250\"/><RENDERINGINFO><TRANSMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/></RENDERINGINFO></SHAPECOMPONENT>\n");
					sb.append("			<IMAGERECT X0=\"0\" X1=\"3840\" X2=\"3840\" X3=\"0\" Y0=\"0\" Y1=\"0\" Y2=\"4500\" Y3=\"4500\"/>\n");
					sb.append("			<IMAGECLIP Bottom=\"4500\" Left=\"0\" Right=\"3840\" Top=\"0\"/>\n");
					sb.append("			<INSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/>\n");
					sb.append("			<IMAGE Alpha=\"0\" BinItem=\"" + (Integer)chargerInfo1.get("binId") + "\" Bright=\"0\" Contrast=\"0\" Effect=\"RealPic\"/>\n");
					sb.append("		</PICTURE>\n");
				}
			}
			sb.append("		<CHAR>                                         점검일자 : </CHAR></TEXT><TEXT CharShape=\"6\"><CHAR>" + ((qcDetailData.get("wrtDtHwp") != null) ? (String) qcDetailData.get("wrtDtHwp") : "" ) + "</CHAR>\n");
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"5\">\n");
			if(chargerInfo2 != null) {
				if((Boolean)chargerInfo2.get("fileExists")) {
					sb.append("		<PICTURE Reverse=\"false\">\n");
					sb.append("			<SHAPEOBJECT InstId=\"2056031445\" Lock=\"false\" NumberingType=\"Figure\" TextWrap=\"BehindText\" ZOrder=\"4\"><SIZE Height=\"4500\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"3840\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"true\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"42780\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"2204\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/></SHAPEOBJECT>\n");
					sb.append("			<SHAPECOMPONENT GroupLevel=\"0\" HorzFlip=\"false\" InstID=\"982289622\" OriHeight=\"4500\" OriWidth=\"3840\" VertFlip=\"false\" XPos=\"0\" YPos=\"0\"><ROTATIONINFO Angle=\"0\" CenterX=\"1920\" CenterY=\"2250\"/><RENDERINGINFO><TRANSMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/></RENDERINGINFO></SHAPECOMPONENT>\n");
					sb.append("			<IMAGERECT X0=\"0\" X1=\"3840\" X2=\"3840\" X3=\"0\" Y0=\"0\" Y1=\"0\" Y2=\"4500\" Y3=\"4500\"/>\n");
					sb.append("			<IMAGECLIP Bottom=\"4500\" Left=\"0\" Right=\"3840\" Top=\"0\"/>\n");
					sb.append("			<INSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/>\n");
					sb.append("			<IMAGE Alpha=\"0\" BinItem=\"" + (Integer)chargerInfo2.get("binId") + "\" Bright=\"0\" Contrast=\"0\" Effect=\"RealPic\"/>\n");
					sb.append("		</PICTURE>\n");
				}
			}
			if(chargerInfo1 != null) {
				sb.append("		<CHAR>                                         점 검 자 : " + ((chargerInfo1.get("chargerNm") != null) ? (String)chargerInfo1.get("chargerNm") :  "      ") + "    (인)</CHAR>\n");
			} else {
				sb.append("		<CHAR>                                         점 검 자 :           (인)</CHAR>\n");
			}
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"5\">\n");
			if(chargerInfo2 != null) {			
				sb.append("		<CHAR>                                                    " + ((chargerInfo2.get("chargerNm") != null) ? (String)chargerInfo2.get("chargerNm") :  "      ") + "    (인)</CHAR>\n");
			} else {
				sb.append("		<CHAR/>\n");
			}
			sb.append("	</TEXT></P>\n");
			sb.append("</SECTION></BODY>\n");
			sb.append("<TAIL>\n");
			sb.append(getBinDataListElement());
			sb.append("<SCRIPTCODE Type=\"JScript\" Version=\"1.0\"><SCRIPTHEADER>var Documents = XHwpDocuments;\n");
			sb.append("var Document = Documents.Active_XHwpDocument;\n");
			sb.append("</SCRIPTHEADER><SCRIPTSOURCE>function OnDocument_New() {}\n");
			sb.append("</SCRIPTSOURCE></SCRIPTCODE></TAIL></HWPML>");			
			return sb.toString();
		}
		
		/**빈문서*/
		protected String getTempHwpReport() {
			StringBuilder sb = new StringBuilder();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?><HWPML Style=\"embed\" SubVersion=\"7.0.0.0\" Version=\"2.7\"><HEAD SecCnt=\"1\"><DOCSUMMARY><DATE>2015년 10월 28일 수요일 오후 3:25:52</DATE></DOCSUMMARY><DOCSETTING><BEGINNUMBER Endnote=\"1\" Equation=\"1\" Footnote=\"1\" Page=\"1\" Picture=\"1\" Table=\"1\"/><CARETPOS List=\"0\" Para=\"0\" Pos=\"16\"/></DOCSETTING><MAPPINGTABLE><FACENAMELIST><FONTFACE Count=\"2\" Lang=\"Hangul\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT></FONTFACE><FONTFACE Count=\"2\" Lang=\"Latin\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT></FONTFACE><FONTFACE Count=\"2\" Lang=\"Hanja\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT></FONTFACE><FONTFACE Count=\"2\" Lang=\"Japanese\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT></FONTFACE><FONTFACE Count=\"2\" Lang=\"Other\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT></FONTFACE><FONTFACE Count=\"2\" Lang=\"Symbol\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT></FONTFACE><FONTFACE Count=\"2\" Lang=\"User\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT></FONTFACE></FACENAMELIST><BORDERFILLLIST Count=\"1\"><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"1\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"None\" Width=\"0.1mm\"/><RIGHTBORDER Type=\"None\" Width=\"0.1mm\"/><TOPBORDER Type=\"None\" Width=\"0.1mm\"/><BOTTOMBORDER Type=\"None\" Width=\"0.1mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.1mm\"/><FILLBRUSH><WINDOWBRUSH Alpha=\"0\" FaceColor=\"4294967295\" HatchColor=\"0\"/></FILLBRUSH></BORDERFILL></BORDERFILLLIST><CHARSHAPELIST Count=\"5\"><CHARSHAPE BorderFillId=\"0\" Height=\"1000\" Id=\"0\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1000\" Id=\"1\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"2\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"3\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"95\" Hanja=\"95\" Japanese=\"95\" Latin=\"95\" Other=\"95\" Symbol=\"95\" User=\"95\"/><CHARSPACING Hangul=\"-5\" Hanja=\"-5\" Japanese=\"-5\" Latin=\"-5\" Other=\"-5\" Symbol=\"-5\" User=\"-5\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"4\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"95\" Hanja=\"95\" Japanese=\"95\" Latin=\"95\" Other=\"95\" Symbol=\"95\" User=\"95\"/><CHARSPACING Hangul=\"-5\" Hanja=\"-5\" Japanese=\"-5\" Latin=\"-5\" Other=\"-5\" Symbol=\"-5\" User=\"-5\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE></CHARSHAPELIST><TABDEFLIST Count=\"3\"><TABDEF AutoTabLeft=\"false\" AutoTabRight=\"false\" Id=\"0\"/><TABDEF AutoTabLeft=\"true\" AutoTabRight=\"false\" Id=\"1\"/><TABDEF AutoTabLeft=\"false\" AutoTabRight=\"true\" Id=\"2\"/></TABDEFLIST><NUMBERINGLIST Count=\"1\"><NUMBERING Id=\"1\" Start=\"0\"><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"1\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^1.</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"2\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^2.</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"3\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^3)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"4\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^4)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"5\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">(^5)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"6\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">(^6)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"7\" NumFormat=\"CircledDigit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^7</PARAHEAD></NUMBERING></NUMBERINGLIST><PARASHAPELIST Count=\"12\"><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"0\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"1\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"-2620\" Left=\"0\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"1\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"1\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"true\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"1\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"2\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"1\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"3\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"3000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"1\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"4\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"2000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"1\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"5\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"1\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"4000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"1\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"6\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"2\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"6000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"1\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"7\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"3\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"8000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"1\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"8\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"4\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"10000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"1\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"9\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"5\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"12000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"1\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"10\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"6\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"14000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"1\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"11\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"2\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"150\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"1\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE></PARASHAPELIST><STYLELIST Count=\"14\"><STYLE CharShape=\"1\" EngName=\"Normal\" Id=\"0\" LangId=\"1042\" LockForm=\"0\" Name=\"바탕글\" NextStyle=\"0\" ParaShape=\"2\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Body\" Id=\"1\" LangId=\"1042\" LockForm=\"0\" Name=\"본문\" NextStyle=\"1\" ParaShape=\"3\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 1\" Id=\"2\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 1\" NextStyle=\"2\" ParaShape=\"4\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 2\" Id=\"3\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 2\" NextStyle=\"3\" ParaShape=\"5\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 3\" Id=\"4\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 3\" NextStyle=\"4\" ParaShape=\"6\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 4\" Id=\"5\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 4\" NextStyle=\"5\" ParaShape=\"7\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 5\" Id=\"6\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 5\" NextStyle=\"6\" ParaShape=\"8\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 6\" Id=\"7\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 6\" NextStyle=\"7\" ParaShape=\"9\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 7\" Id=\"8\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 7\" NextStyle=\"8\" ParaShape=\"10\" Type=\"Para\"/><STYLE CharShape=\"0\" EngName=\"Page Number\" Id=\"9\" LangId=\"1042\" LockForm=\"0\" Name=\"쪽 번호\" NextStyle=\"9\" ParaShape=\"2\" Type=\"Para\"/><STYLE CharShape=\"2\" EngName=\"Header\" Id=\"10\" LangId=\"1042\" LockForm=\"0\" Name=\"머리말\" NextStyle=\"10\" ParaShape=\"11\" Type=\"Para\"/><STYLE CharShape=\"3\" EngName=\"Footnote\" Id=\"11\" LangId=\"1042\" LockForm=\"0\" Name=\"각주\" NextStyle=\"11\" ParaShape=\"0\" Type=\"Para\"/><STYLE CharShape=\"3\" EngName=\"Endnote\" Id=\"12\" LangId=\"1042\" LockForm=\"0\" Name=\"미주\" NextStyle=\"12\" ParaShape=\"0\" Type=\"Para\"/><STYLE CharShape=\"4\" EngName=\"Memo\" Id=\"13\" LangId=\"1042\" LockForm=\"0\" Name=\"메모\" NextStyle=\"13\" ParaShape=\"1\" Type=\"Para\"/></STYLELIST></MAPPINGTABLE></HEAD><BODY><SECTION Id=\"0\"><P ColumnBreak=\"false\" PageBreak=\"false\" ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"1\"><SECDEF CharGrid=\"0\" FirstBorder=\"false\" FirstFill=\"false\" LineGrid=\"0\" OutlineShape=\"1\" SpaceColumns=\"1134\" TabStop=\"8000\" TextDirection=\"0\" TextVerticalWidthHead=\"0\"><STARTNUMBER Equation=\"0\" Figure=\"0\" Page=\"0\" PageStartsOn=\"Both\" Table=\"0\"/><HIDE Border=\"false\" EmptyLine=\"false\" Fill=\"false\" Footer=\"false\" Header=\"false\" MasterPage=\"false\" PageNumPos=\"false\"/><PAGEDEF GutterType=\"LeftOnly\" Height=\"84188\" Landscape=\"0\" Width=\"59528\"><PAGEMARGIN Bottom=\"4252\" Footer=\"4252\" Gutter=\"0\" Header=\"4252\" Left=\"8504\" Right=\"8504\" Top=\"5668\"/></PAGEDEF><FOOTNOTESHAPE><AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/><NOTELINE Length=\"5cm\" Type=\"Solid\" Width=\"0.12mm\"/><NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"283\"/><NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/><NOTEPLACEMENT BeneathText=\"false\" Place=\"EachColumn\"/></FOOTNOTESHAPE><ENDNOTESHAPE><AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/><NOTELINE Length=\"14692344\" Type=\"Solid\" Width=\"0.12mm\"/><NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"0\"/><NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/><NOTEPLACEMENT BeneathText=\"false\" Place=\"EndOfDocument\"/></ENDNOTESHAPE><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Both\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Even\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Odd\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL></SECDEF><COLDEF Count=\"1\" Layout=\"Left\" SameGap=\"0\" SameSize=\"true\" Type=\"Newspaper\"/><CHAR/></TEXT></P></SECTION></BODY><TAIL>\n");
			sb.append("<SCRIPTCODE Type=\"JScript\" Version=\"1.0\"><SCRIPTHEADER>var Documents = XHwpDocuments;\n");
			sb.append("var Document = Documents.Active_XHwpDocument;\n");
			sb.append("</SCRIPTHEADER><SCRIPTSOURCE>function OnDocument_New() {}\n");
			sb.append("</SCRIPTSOURCE></SCRIPTCODE></TAIL></HWPML>");			
			return sb.toString();
		}
 	}
}
