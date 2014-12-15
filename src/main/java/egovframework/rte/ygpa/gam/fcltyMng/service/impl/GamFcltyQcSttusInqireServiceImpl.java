/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyQcSttusInqireService;
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
	public List<?> selectQcSttusDtlsList(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return gamFcltyQcSttusInqireDao.selectQcSttusDtlsList(searchVO);
	}
	
	/**
	 * 점검관리내역 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectQcSttusDtlsListTotCnt(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return gamFcltyQcSttusInqireDao.selectQcSttusDtlsListTotCnt(searchVO);
	}
	
	/**
	 * 점검관리내역 데이터 조회
	 * @param vo
	 * @return EgovMap
	 * @throws Exception
	 */		
	public EgovMap selectQcSttusDtlsDetail(Map<?, ?> searchVO) throws Exception {
		return gamFcltyQcSttusInqireDao.selectQcSttusDtlsDetail(searchVO);
	}

	/**
	 * 점검관리대상시설물 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectQcSttusObjFcltsList(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return gamFcltyQcSttusInqireDao.selectQcSttusObjFcltsList(searchVO);
	}
	
	/**
	 * 점검관리대상시설물 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectQcSttusObjFcltsListTotCnt(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return gamFcltyQcSttusInqireDao.selectQcSttusObjFcltsListTotCnt(searchVO);
	}
	
	/**
	 * 점검관리첨부파일 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectQcSttusAtchFileList(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return gamFcltyQcSttusInqireDao.selectQcSttusAtchFileList(searchVO);
	}
	
	/**
	 * 점검관리첨부파일 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectQcSttusAtchFileListTotCnt(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return gamFcltyQcSttusInqireDao.selectQcSttusAtchFileListTotCnt(searchVO);
	}
		
	/**
	 * 점검관리결과항목 목록 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectQcSttusResultItemList(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return gamFcltyQcSttusInqireDao.selectQcSttusResultItemList(searchVO);
	}
	
	/**
	 * 점검관리결과항목 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectQcSttusResultItemListTotCnt(GamFcltyQcSttusInqireVO searchVO) throws Exception {
		return gamFcltyQcSttusInqireDao.selectQcSttusResultItemListTotCnt(searchVO);
	}
}
