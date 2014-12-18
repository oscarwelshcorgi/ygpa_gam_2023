/**
 * 
 */
package egovframework.rte.ygpa.gam.ctrt.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngService;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngVO;

/**
 * 
 * @author 김종민
 * @since 2014. 10. 29.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 29.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Service("gamFcltyCtrtMngService")
public class GamFcltyCtrtMngServiceImpl extends AbstractServiceImpl implements GamFcltyCtrtMngService {
	@Resource(name="gamFcltyCtrtMngDao")
	private GamFcltyCtrtMngDao gamFcltyCtrtMngDao;
	
	/**
	 * 계약정보목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약정보목록
	 * @exception Exception
	 */
	public List<?> selectFcltyCtrtMngList(GamFcltyCtrtMngVO searchVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtMngList(searchVO);
	}
	
	/**
	 * 계약정보목록 통계정보를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약정보목록 통계정보 VO
	 * @exception Exception
	 */
	public GamFcltyCtrtMngVO selectFcltyCtrtMngTotSum(GamFcltyCtrtMngVO searchVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtMngTotSum(searchVO);
	}
	
	/**
	 * 계약정보를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약정보 VO
	 * @exception Exception
	 */
	public EgovMap selectFcltyCtrtInfoDetail(GamFcltyCtrtMngVO searchVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtInfoDetail(searchVO);
	}
	
	/**
	 * 계약정보를 등록한다.
	 * @param insertMap - 등록할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void insertFcltyCtrtInfoDetail(Map<?, ?> insertMap) throws Exception {
		gamFcltyCtrtMngDao.insertFcltyCtrtInfoDetail(insertMap);
	}
	
	/**
	 * 계약정보를 수정한다.
	 * @param updateMap - 수정할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void updateFcltyCtrtInfoDetail(Map<?, ?> updateMap) throws Exception {
		gamFcltyCtrtMngDao.updateFcltyCtrtInfoDetail(updateMap);
	}

	/**
	 * 계약정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void deleteFcltyCtrtInfoDetail(Map<?, ?> deleteMap) throws Exception {
		gamFcltyCtrtMngDao.deleteFcltyCtrtInfoDetail(deleteMap);
	}
	
	
	/**
	 * 계약공동도급 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약공동도급 목록
	 * @exception Exception
	 */
	public List<?> selectFcltyCtrtJoinContrList(GamFcltyCtrtMngVO searchVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtJoinContrList(searchVO);
	}
	
	/**
	 * 계약공동도급 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약공동도급 목록 총개수
	 * @exception Exception
	 */
	public int selectFcltyCtrtJoinContrListTotCnt(GamFcltyCtrtMngVO searchVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtJoinContrListTotCnt(searchVO);
	}
	
	
	/**
	 * 계약공동도급정보를 병합저장한다.
	 * @param map - 병합저장할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void mergeFcltyCtrtJoinContrDetail(Map<String, Object> map) throws Exception {
		gamFcltyCtrtMngDao.mergeFcltyCtrtJoinContrDetail(map);
	}
	
	/**
	 * 계약번호에 해당하는 계약공동도급정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void deleteFcltyCtrtJoinContrList(Map<?, ?> deleteMap) throws Exception {
		gamFcltyCtrtMngDao.deleteFcltyCtrtJoinContrList(deleteMap);
	}


	/**
	 * 계약하도급 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약하도급 목록
	 * @exception Exception
	 */
	public List<?> selectFcltyCtrtSubCtrtList(GamFcltyCtrtMngVO searchVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtSubCtrtList(searchVO);
	}
	
	/**
	 * 계약하도급 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약하도급 목록 총개수
	 * @exception Exception
	 */
	public int selectFcltyCtrtSubCtrtListTotCnt(GamFcltyCtrtMngVO searchVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtSubCtrtListTotCnt(searchVO);
	}
	
	/**
	 * 계약하도급정보를 병합저장한다.
	 * @param map - 병합저장할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void mergeFcltyCtrtSubCtrtDetail(Map<String, Object> map) throws Exception {
		gamFcltyCtrtMngDao.mergeFcltyCtrtSubCtrtDetail(map);
	}
	
	/**
	 * 계약번호에 해당하는 계약하도급정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void deleteFcltyCtrtSubCtrtList(Map<?, ?> deleteMap) throws Exception {
		gamFcltyCtrtMngDao.deleteFcltyCtrtSubCtrtList(deleteMap);
	}

	/**
	 * 계약변경 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약변경 목록
	 * @exception Exception
	 */
	public List<?> selectFcltyCtrtChangeList(GamFcltyCtrtMngVO searchVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtChangeList(searchVO);
	}
	
	/**
	 * 계약변경 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약변경 목록 총개수
	 * @exception Exception
	 */
	public int selectFcltyCtrtChangeListTotCnt(GamFcltyCtrtMngVO searchVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtChangeListTotCnt(searchVO);
	}
	
	/**
	 * 계약변경정보를 병합저장한다.
	 * @param map - 병합저장할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void mergeFcltyCtrtChangeDetail(Map<String, Object> map) throws Exception {
		gamFcltyCtrtMngDao.mergeFcltyCtrtChangeDetail(map);
	}
	
	/**
	 * 계약번호에 해당하는 계약변경정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void deleteFcltyCtrtChangeList(Map<?, ?> deleteMap) throws Exception {
		gamFcltyCtrtMngDao.deleteFcltyCtrtChangeList(deleteMap);
	}
	

	/**
	 * 계약대금지급 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약대금지급 목록
	 * @exception Exception
	 */
	public List<?> selectFcltyCtrtMoneyPymntList(GamFcltyCtrtMngVO searchVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtMoneyPymntList(searchVO);
	}
	
	/**
	 * 계약대금지급 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약대금지급 목록 총개수
	 * @exception Exception
	 */
	public int selectFcltyCtrtMoneyPymntListTotCnt(GamFcltyCtrtMngVO searchVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtMoneyPymntListTotCnt(searchVO);
	}
	
	
	/**
	 * 계약대금지급정보를 병합저장한다.
	 * @param map - 병합저장할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void mergeFcltyCtrtMoneyPymntDetail(Map<String, Object> map) throws Exception {
		gamFcltyCtrtMngDao.mergeFcltyCtrtMoneyPymntDetail(map);
	}

	/**
	 * 계약번호에 해당하는 계약대금지급정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void deleteFcltyCtrtMoneyPymntList(Map<?, ?> deleteMap) throws Exception {
		gamFcltyCtrtMngDao.deleteFcltyCtrtMoneyPymntList(deleteMap);
	}
	
	/**
	 * 계약이행이월 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약이행이월 목록
	 * @exception Exception
	 */
	public List<?> selectFcltyCtrtFulFillCaryFwdList(GamFcltyCtrtMngVO searchVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtFulFillCaryFwdList(searchVO);
	}
	
	/**
	 * 계약이행이월 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약이행이월 목록 총개수
	 * @exception Exception
	 */
	public int selectFcltyCtrtFulFillCaryFwdListTotCnt(GamFcltyCtrtMngVO searchVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtFulFillCaryFwdListTotCnt(searchVO);
	}
	
	/**
	 * 계약이행이월정보를 병합저장한다.
	 * @param map - 병합저장할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void mergeFcltyCtrtFulFillCaryFwdDetail(Map<String, Object> map) throws Exception {
		gamFcltyCtrtMngDao.mergeFcltyCtrtFulFillCaryFwdDetail(map);
	}
	
	/**
	 * 계약번호에 해당하는 계약이행이월정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void deleteFcltyCtrtFulFillCaryFwdList(Map<?, ?> deleteMap) throws Exception {
		gamFcltyCtrtMngDao.deleteFcltyCtrtFulFillCaryFwdList(deleteMap);
	}
	

	/**
	 * 계약낙찰정보 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약낙찰정보 목록
	 * @exception Exception
	 */
	public List<?> selectFcltyCtrtScsbidInfoList(GamFcltyCtrtMngVO searchVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtScsbidInfoList(searchVO);
	}
	
	/**
	 * 계약낙찰정보 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약낙찰정보 목록 총개수
	 * @exception Exception
	 */
	public int selectFcltyCtrtScsbidInfoListTotCnt(GamFcltyCtrtMngVO searchVO) throws Exception {
		return gamFcltyCtrtMngDao.selectFcltyCtrtScsbidInfoListTotCnt(searchVO);
	}
	
	/**
	 * 계약낙찰정보를 병합저장한다.
	 * @param map - 병합저장할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void mergeFcltyCtrtScsbidInfoDetail(Map<String, Object> map) throws Exception {
		gamFcltyCtrtMngDao.mergeFcltyCtrtScsbidInfoDetail(map);
	}
	
	/**
	 * 계약번호에 해당하는 계약낙찰정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void deleteFcltyCtrtScsbidInfoList(Map<?, ?> deleteMap) throws Exception {
		gamFcltyCtrtMngDao.deleteFcltyCtrtScsbidInfoList(deleteMap);
	}
}
