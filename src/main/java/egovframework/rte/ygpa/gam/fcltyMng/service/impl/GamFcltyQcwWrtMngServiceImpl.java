/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyQcPrintVO;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyQcwWrtMngService;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyQcwWrtMngVO;

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
	public void insertQcMngDtls(Map<String, String> detailForm, List<HashMap<String, String>> qcObjList,  List<HashMap<String, String>> qcResultList, List<HashMap<String, String>> atchFileList) throws Exception {
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

		//첨부파일 전체 삭제 후 추가
		gamFcltyQcwWrtMngDao.deleteQcMngAtchFileList(detailForm);
		for(HashMap<String, String> atchFile : atchFileList) {
			atchFile.put("qcMngSeq", detailForm.get("qcMngSeq"));
			atchFile.put("regUsr", detailForm.get("regUsr"));
			gamFcltyQcwWrtMngDao.insertQcMngAtchFile(atchFile);
		}

	}
		
	/**
	 * 점검관리내역 데이터 수정
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void updateQcMngDtls(Map<String, String> detailForm, List<HashMap<String, String>> qcObjList,  List<HashMap<String, String>> qcResultList, List<HashMap<String, String>> atchFileList) throws Exception {
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

		//첨부파일 전체 삭제 후 추가
		gamFcltyQcwWrtMngDao.deleteQcMngAtchFileList(detailForm);
		for(HashMap<String, String> atchFile : atchFileList) {
			atchFile.put("regUsr", detailForm.get("updUsr"));
			gamFcltyQcwWrtMngDao.insertQcMngAtchFile(atchFile);
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
	 * 점검관리첨부파일 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectQcMngAtchFileList(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return gamFcltyQcwWrtMngDao.selectQcMngAtchFileList(searchVO);
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
	 * 일반용 점검관리결과항목 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectQcMngResultItemList(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return gamFcltyQcwWrtMngDao.selectQcMngResultItemList(searchVO);
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
	 * 건축 점검관리결과항목 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectArchQcMngResultItemList(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return gamFcltyQcwWrtMngDao.selectArchQcMngResultItemList(searchVO);
	}
	
	/**
	 * 출력물용 점검관리결과항목 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectPrintQcMngResultItemList(GamFcltyQcPrintVO searchVO) throws Exception {
		return gamFcltyQcwWrtMngDao.selectPrintQcMngResultItemList(searchVO);
	}
}
