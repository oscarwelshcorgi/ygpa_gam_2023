/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyQcSttusInqireVO;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyQcSttusInqireVO;

/**
 * 
 * @author 김종민
 * @since 2014. 12. 15.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 15.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamFcltyQcSttusInqireDao")
public class GamFcltyQcSttusInqireDao extends YGPAAbstractDAO {
	/**
	 * 점검관리내역 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectQcMngDtlsList(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return list("gamFcltyQcSttusInqireDao.selectQcMngDtlsList_D", searchVO);
	}
	
	/**
	 * 점검관리내역 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectQcMngDtlsListTotCnt(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyQcSttusInqireDao.selectQcMngDtlsListTotCnt_S", searchVO);
	}
	
	/**
	 * 점검관리목록(정보통신) 인쇄 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectQcMngDtlsReportI(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return list("gamFcltyQcSttusInqireDao.selectQcMngDtlsReportI_D", searchVO);
	}
	
	/**
	 * 점검관리내역 데이터 조회
	 * @param vo
	 * @return EgovMap
	 * @throws Exception
	 */		
	public EgovMap selectQcMngDtlsDetail(Map<?, ?> searchVO) throws Exception {
		return (EgovMap) selectByPk("gamFcltyQcSttusInqireDao.selectQcMngDtlsDetail_S", searchVO);
	}

	/**
	 * 점검구분 이름 조회(인쇄화면에 사용)
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public String selectQcSeNm(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return (String)getSqlMapClientTemplate().queryForObject("gamFcltyQcSttusInqireDao.selectQcSeNm_S", searchVO);
	}
	
	/**
	 * 점검관리대상시설물 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectQcMngObjFcltsList(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return list("gamFcltyQcSttusInqireDao.selectQcMngObjFcltsList_D", searchVO);
	}
	
	/**
	 * 점검관리대상시설물 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectQcMngObjFcltsListTotCnt(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyQcSttusInqireDao.selectQcMngObjFcltsListTotCnt_S", searchVO);
	}
	
	/**
	 * 점검관리첨부파일 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectQcMngAtchFileList(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return list("gamFcltyQcSttusInqireDao.selectQcMngAtchFileList_D", searchVO);
	}
	
	/**
	 * 점검관리첨부파일 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectQcMngAtchFileListTotCnt(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyQcSttusInqireDao.selectQcMngAtchFileListTotCnt_S", searchVO);
	}
		
	/**
	 * 점검관리결과항목 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectQcMngResultItemList(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return list("gamFcltyQcSttusInqireDao.selectQcMngResultItemList_D", searchVO);
	}
	
	/**
	 * 점검관리결과항목 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectQcMngResultItemListTotCnt(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyQcSttusInqireDao.selectQcMngResultItemListTotCnt_S", searchVO);
	}
		
}
