/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyQcSttusInqireService;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyQcSttusInqireVO;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyQcwWrtMngVO;

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
@Service("gamFcltyQcSttusInqireService")
public class GamFcltyQcSttusInqireServiceImpl extends AbstractServiceImpl implements GamFcltyQcSttusInqireService{
	@Resource(name="gamFcltyQcSttusInqireDao")
	GamFcltyQcSttusInqireDao gamFcltyQcSttusInqireDao;
	
	/**
	 * 점검관리내역 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectQcMngDtlsList(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return gamFcltyQcSttusInqireDao.selectQcMngDtlsList(searchVO);
	}
	
	/**
	 * 점검관리내역 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectQcMngDtlsListTotCnt(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return gamFcltyQcSttusInqireDao.selectQcMngDtlsListTotCnt(searchVO);
	}
	
	/**
	 * 점검관리내역 데이터 조회
	 * @param vo
	 * @return EgovMap
	 * @throws Exception
	 */		
	public EgovMap selectQcMngDtlsDetail(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return gamFcltyQcSttusInqireDao.selectQcMngDtlsDetail(searchVO);
	}

	/**
	 * 점검관리첨부파일 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectQcMngAtchFileList(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return gamFcltyQcSttusInqireDao.selectQcMngAtchFileList(searchVO);
	}
	
	/**
	 * 점검관리대상시설물 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectQcMngObjFcltsList(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return gamFcltyQcSttusInqireDao.selectQcMngObjFcltsList(searchVO);
	}
		
	/**
	 * 점검관리결과항목 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectQcMngResultItemList(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return gamFcltyQcSttusInqireDao.selectQcMngResultItemList(searchVO);
	}

	/**
	 * 점검관리결과항목 목록 개수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectQcMngResultItemListTotCnt(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return gamFcltyQcSttusInqireDao.selectQcMngResultItemListTotCnt(searchVO);
	}

	/**
	 * 기계 점검관리결과항목 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectMechQcMngResultItemList(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return gamFcltyQcSttusInqireDao.selectMechQcMngResultItemList(searchVO);
	}
	
}
