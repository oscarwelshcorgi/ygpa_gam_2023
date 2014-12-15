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
	public List<?> selectQcSttusDtlsList(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return list("gamFcltyQcSttusInqireDao.selectQcSttusDtlsList_D", searchVO);
	}
	
	/**
	 * 점검관리내역 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectQcSttusDtlsListTotCnt(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyQcSttusInqireDao.selectQcSttusDtlsListTotCnt_S", searchVO);
	}
	
	/**
	 * 점검관리내역 데이터 조회
	 * @param vo
	 * @return EgovMap
	 * @throws Exception
	 */		
	public EgovMap selectQcSttusDtlsDetail(Map<?, ?> searchVO) throws Exception {
		return (EgovMap) selectByPk("gamFcltyQcSttusInqireDao.selectQcSttusDtlsDetail_S", searchVO);
	}
			
	/**
	 * 점검관리대상시설물 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectQcSttusObjFcltsList(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return list("gamFcltyQcSttusInqireDao.selectQcSttusObjFcltsList_D", searchVO);
	}
	
	/**
	 * 점검관리대상시설물 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectQcSttusObjFcltsListTotCnt(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyQcSttusInqireDao.selectQcSttusObjFcltsListTotCnt_S", searchVO);
	}
	
	/**
	 * 점검관리첨부파일 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectQcSttusAtchFileList(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return list("gamFcltyQcSttusInqireDao.selectQcSttusAtchFileList_D", searchVO);
	}
	
	/**
	 * 점검관리첨부파일 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectQcSttusAtchFileListTotCnt(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyQcSttusInqireDao.selectQcSttusAtchFileListTotCnt_S", searchVO);
	}

	/**
	 * 점검관리결과항목 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectQcSttusResultItemList(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return list("gamFcltyQcSttusInqireDao.selectQcSttusResultItemList_D", searchVO);
	}
	
	/**
	 * 점검관리결과항목 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectQcSttusResultItemListTotCnt(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyQcSttusInqireDao.selectQcSttusResultItemListTotCnt_S", searchVO);
	}
}
