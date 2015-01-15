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
	 * 점검관리목록 인쇄 조회
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public List<?> selectQcMngDtlsReportI(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return gamFcltyQcwWrtMngDao.selectQcMngDtlsReportI(searchVO);
	}
	
	/**
	 * 점검구분 이름 조회(인쇄화면에 사용)
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public String selectQcSeNm(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return gamFcltyQcwWrtMngDao.selectQcSeNm(searchVO);
	}
	
	/**
	 * 점검관리내역 데이터 조회
	 * @param vo
	 * @return EgovMap
	 * @throws Exception
	 */		
	public EgovMap selectQcMngDtlsDetail(Map<?, ?> searchVO) throws Exception {
		return gamFcltyQcwWrtMngDao.selectQcMngDtlsDetail(searchVO);
	}

	/**
	 * 점검관리내역 데이터 삽입
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void insertQcMngDtls(Map<?, ?> vo) throws Exception {
		gamFcltyQcwWrtMngDao.insertQcMngDtls(vo);
	}
	
	/**
	 * 점검관리내역 데이터 수정
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void updateQcMngDtls(Map<?, ?> vo) throws Exception {
		gamFcltyQcwWrtMngDao.updateQcMngDtls(vo);
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
	 * 점검관리대상시설물 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectQcMngObjFcltsListTotCnt(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return gamFcltyQcwWrtMngDao.selectQcMngObjFcltsListTotCnt(searchVO);
	}
	
	/**
	 * 점검관리대상시설물 데이터 병합 저장
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void mergeQcMngObjFclts(Map<String, Object> mergeMap) throws Exception {
		gamFcltyQcwWrtMngDao.mergeQcMngObjFclts(mergeMap);
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
	 * 점검관리첨부파일 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectQcMngAtchFileListTotCnt(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return gamFcltyQcwWrtMngDao.selectQcMngAtchFileListTotCnt(searchVO);
	}
		
	/**
	 * 점검관리첨부파일 데이터 병합 저장
	 * @param vo
	 * @return 
	 * @throws Exception
	 */		
	public void mergeQcMngAtchFile(Map<String, Object> mergeMap) throws Exception {
		gamFcltyQcwWrtMngDao.mergeQcMngAtchFile(mergeMap);
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
	 * 점검관리결과항목 목록 총수 조회
	 * @param vo
	 * @return int
	 * @throws Exception
	 */		
	public int selectQcMngResultItemListTotCnt(GamFcltyQcwWrtMngVO searchVO) throws Exception {
		return gamFcltyQcwWrtMngDao.selectQcMngResultItemListTotCnt(searchVO);
	}
		
	/**
	 * 점검관리결과항목 데이터 병합 저장
	 * @param vo
	 * @return list
	 * @throws Exception
	 */		
	public void mergeQcMngResultItem(Map<String, Object> mergeMap) throws Exception {
		gamFcltyQcwWrtMngDao.mergeQcMngResultItem(mergeMap);
	}
	
}
