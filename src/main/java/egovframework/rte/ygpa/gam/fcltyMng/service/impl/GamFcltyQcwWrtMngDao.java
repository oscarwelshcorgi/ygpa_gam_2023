/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyQcPrintVO;
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
	 * 점검관리내역 데이터 조회
	 * @param vo
	 * @return EgovMap
	 * @throws Exception
	 */		
	public EgovMap selectQcMngDtlsDetail(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamFcltyQcwWrtMngDao.selectQcMngDtlsDetail_S", searchVO);
	}

	/**
	 * 점검관리순번 가져오기
	 * @param vo
	 * @return String
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
	 * 점검관리첨부파일 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectQcMngAtchFileList(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return list("gamFcltyQcwWrtMngDao.selectQcMngAtchFileList_D", searchVO);
	}
	
	/**
	 * 점검관리첨부파일 데이터 삽입
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public void insertQcMngAtchFile(Map<?, ?> vo) throws Exception {
		insert("gamFcltyQcwWrtMngDao.insertQcMngAtchFile_S", vo);
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
	 * 점검관리대상시설물 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectQcMngObjFcltsList(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return list("gamFcltyQcwWrtMngDao.selectQcMngObjFcltsList_D", searchVO);
	}

	/**
	 * 점검관리대상시설물 삽입
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public void insertQcMngObjFclts(Map<?, ?> vo) throws Exception {
		insert("gamFcltyQcwWrtMngDao.insertQcMngObjFclts_S", vo);
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
	 * 일반적 점검관리결과항목 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectQcMngResultItemList(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return list("gamFcltyQcwWrtMngDao.selectQcMngResultItemList_D", searchVO);
	}
	
	/**
	 * 기계 점검관리결과항목 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectMechQcMngResultItemList(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return list("gamFcltyQcwWrtMngDao.selectMechQcMngResultItemList_D", searchVO);
	}

	/**
	 * 건축 점검관리결과항목 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectArchQcMngResultItemList(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return list("gamFcltyQcwWrtMngDao.selectArchQcMngResultItemList_D", searchVO);
	}

	/**
	 * 점검관리결과항목 삽입
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public void insertQcMngResultItem(Map<?, ?> vo) throws Exception {
		insert("gamFcltyQcwWrtMngDao.insertQcMngResultItem_S", vo);
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


	/**
	 * 출력물용 점검관리결과항목 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectPrintQcMngResultItemList(GamFcltyQcPrintVO searchVO) throws Exception {
		return list("gamFcltyQcwWrtMngDao.selectPrintQcMngResultItemList_D", searchVO);
	}

}
