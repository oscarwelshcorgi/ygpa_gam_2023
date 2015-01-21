/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
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

@Repository("gamFcltyQcwWrtMngDao")
public class GamFcltyQcwWrtMngDao extends YGPAAbstractDAO {
	/**
	 * 점검관리내역 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectQcMngDtlsList(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return list("gamFcltyQcwWrtMngDao.selectQcMngDtlsList_D", searchVO);
	}
	
	/**
	 * 점검관리내역 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectQcMngDtlsListTotCnt(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyQcwWrtMngDao.selectQcMngDtlsListTotCnt_S", searchVO);
	}
	
	/**
	 * 점검관리목록(정보통신) 인쇄 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectQcMngDtlsReportI(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return list("gamFcltyQcwWrtMngDao.selectQcMngDtlsReportI_D", searchVO);
	}
	
	/**
	 * 점검관리내역 데이터 조회
	 * @param vo
	 * @return EgovMap
	 * @throws Exception
	 */		
	public EgovMap selectQcMngDtlsDetail(Map<?, ?> searchVO) throws Exception {
		return (EgovMap) selectByPk("gamFcltyQcwWrtMngDao.selectQcMngDtlsDetail_S", searchVO);
	}

	/**
	 * 점검구분 이름 조회(인쇄화면에 사용)
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public String selectQcSeNm(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return (String)getSqlMapClientTemplate().queryForObject("gamFcltyQcwWrtMngDao.selectQcSeNm_S", searchVO);
	}
	
	/**
	 * 점검구분 이름 조회(인쇄화면에 사용)
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public String selectMaxQcMngSeq(Map<?, ?> vo) throws Exception {
		return (String)getSqlMapClientTemplate().queryForObject("gamFcltyQcwWrtMngDao.selectMaxQcMngSeq_S", vo);
	}

	/**
	 * 점검관리내역 데이터 삽입
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void insertQcMngDtls(Map<?, ?> vo) throws Exception {
		insert("gamFcltyQcwWrtMngDao.insertQcMngDtls_S", vo);
	}
	
	/**
	 * 점검관리내역 데이터 수정
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void updateQcMngDtls(Map<?, ?> vo) throws Exception {
		update("gamFcltyQcwWrtMngDao.updateQcMngDtls_S", vo);
	}
	
	/**
	 * 점검관리내역 데이터 삭제
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void deleteQcMngDtls(Map<?, ?> vo) throws Exception {
		delete("gamFcltyQcwWrtMngDao.deleteQcMngDtls_S", vo);
	}
		
	/**
	 * 점검관리대상시설물 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectQcMngObjFcltsList(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return list("gamFcltyQcwWrtMngDao.selectQcMngObjFcltsList_D", searchVO);
	}
	
	/**
	 * 점검관리대상시설물 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectQcMngObjFcltsListTotCnt(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyQcwWrtMngDao.selectQcMngObjFcltsListTotCnt_S", searchVO);
	}
	
	/**
	 * 점검관리대상시설물 데이터 병합 저장
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public void mergeQcMngObjFclts(Map<String, Object> mergeMap) throws Exception {
		this.merge(mergeMap, "gamFcltyQcwWrtMngDao.insertQcMngObjFclts_S", "gamFcltyQcwWrtMngDao.updateQcMngObjFclts_S", "gamFcltyQcwWrtMngDao.deleteQcMngObjFclts_S");
	}
	
	/**
	 * 점검관리대상시설물 목록 삭제
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void deleteQcMngObjFcltsList(Map<?, ?> vo) throws Exception {
		delete("gamFcltyQcwWrtMngDao.deleteQcMngObjFcltsList_S", vo);
	}
	
	/**
	 * 점검관리첨부파일 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectQcMngAtchFileList(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return list("gamFcltyQcwWrtMngDao.selectQcMngAtchFileList_D", searchVO);
	}
	
	/**
	 * 점검관리첨부파일 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectQcMngAtchFileListTotCnt(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyQcwWrtMngDao.selectQcMngAtchFileListTotCnt_S", searchVO);
	}
		
	/**
	 * 점검관리첨부파일 데이터 병합 저장
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public void mergeQcMngAtchFile(Map<String, Object> mergeMap) throws Exception {
		this.merge(mergeMap, "gamFcltyQcwWrtMngDao.insertQcMngAtchFile_S", "gamFcltyQcwWrtMngDao.updateQcMngAtchFile_S", "gamFcltyQcwWrtMngDao.deleteQcMngAtchFile_S");
	}
	
	/**
	 * 점검관리첨부파일 목록 삭제
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void deleteQcMngAtchFileList(Map<?, ?> vo) throws Exception {
		delete("gamFcltyQcwWrtMngDao.deleteQcMngAtchFileList_S", vo);
	}
	

	/**
	 * 점검관리결과항목 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectQcMngResultItemList(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return list("gamFcltyQcwWrtMngDao.selectQcMngResultItemList_D", searchVO);
	}
	
	/**
	 * 점검관리결과항목 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectQcMngResultItemListTotCnt(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyQcwWrtMngDao.selectQcMngResultItemListTotCnt_S", searchVO);
	}
		
	/**
	 * 점검관리결과항목 데이터 병합 저장
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public void mergeQcMngResultItem(Map<String, Object> mergeMap) throws Exception {
		this.merge(mergeMap, "gamFcltyQcwWrtMngDao.insertQcMngResultItem_S", "gamFcltyQcwWrtMngDao.updateQcMngResultItem_S", "gamFcltyQcwWrtMngDao.deleteQcMngResultItem_S");
	}
	
	/**
	 * 점검관리결과항목 목록 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */		
	public void deleteQcMngResultItemList(Map<?, ?> vo) throws Exception {
		delete("gamFcltyQcwWrtMngDao.deleteQcMngResultItemList_S", vo);
	}
}
