/**
 * 
 */
package egovframework.rte.ygpa.gam.ctrt.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
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
	public List selectFcltyCtrtMngList(GamFcltyCtrtMngVO searchVO) {
		return gamFcltyCtrtMngDao.selectFcltyCtrtMngList(searchVO);
	}
	
	/**
	 * 계약정보목록 통계정보를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약정보목록 통계정보 VO
	 * @exception Exception
	 */
	public GamFcltyCtrtMngVO selectFcltyCtrtMngSum(GamFcltyCtrtMngVO searchVO) {
		return gamFcltyCtrtMngDao.selectFcltyCtrtMngSum(searchVO);
	}
	
	/**
	 * 계약정보를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약정보 VO
	 * @exception Exception
	 */
	public GamFcltyCtrtMngVO selectFcltyCtrtInfoDetail(GamFcltyCtrtMngVO searchVO) {
		return gamFcltyCtrtMngDao.selectFcltyCtrtInfoDetail(searchVO);
	}
	
	/**
	 * 계약정보를 등록한다.
	 * @param insertMap - 등록할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void insertFcltyCtrtInfoDetail(Map insertMap) {
		gamFcltyCtrtMngDao.insertFcltyCtrtInfoDetail(insertMap);
	}
	
	/**
	 * 계약정보를 수정한다.
	 * @param updateMap - 수정할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void updateFcltyCtrtInfoDetail(Map updateMap) {
		gamFcltyCtrtMngDao.updateFcltyCtrtInfoDetail(updateMap);
	}

	/**
	 * 계약정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void deleteFcltyCtrtInfoDetail(Map deleteMap) {
		gamFcltyCtrtMngDao.deleteFcltyCtrtInfoDetail(deleteMap);
	}
	
	
	/**
	 * 계약공동도급 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약공동도급 목록
	 * @exception Exception
	 */
	public List selectFcltyCtrtJoinContrList(GamFcltyCtrtMngVO searchVO) {
		return gamFcltyCtrtMngDao.selectFcltyCtrtJoinContrList(searchVO);
	}
	
	/**
	 * 계약공동도급 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약공동도급 목록 총개수
	 * @exception Exception
	 */
	public int selectFcltyCtrtJoinContrListCnt(GamFcltyCtrtMngVO searchVO) {
		return gamFcltyCtrtMngDao.selectFcltyCtrtJoinContrListCnt(searchVO);
	}
	
	
	/**
	 * 계약공동도급정보를 등록한다.
	 * @param insertMap - 등록할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void insertFcltyCtrtJoinContrDetail(Map insertMap) {
		gamFcltyCtrtMngDao.insertFcltyCtrtJoinContrDetail(insertMap);
	}
	
	/**
	 * 계약번호에 해당하는 계약공동도급정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void deleteFcltyCtrtJoinContrAll(Map deleteMap) {
		gamFcltyCtrtMngDao.deleteFcltyCtrtJoinContrAll(deleteMap);
	}


	/**
	 * 계약하도급 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약하도급 목록
	 * @exception Exception
	 */
	public List selectFcltyCtrtSubCtrtList(GamFcltyCtrtMngVO searchVO) {
		return gamFcltyCtrtMngDao.selectFcltyCtrtSubCtrtList(searchVO);
	}
	
	/**
	 * 계약하도급 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약하도급 목록 총개수
	 * @exception Exception
	 */
	public int selectFcltyCtrtSubCtrtListCnt(GamFcltyCtrtMngVO searchVO) {
		return gamFcltyCtrtMngDao.selectFcltyCtrtSubCtrtListCnt(searchVO);
	}
	
	
	/**
	 * 계약하도급정보를 등록한다.
	 * @param insertMap - 등록할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void insertFcltyCtrtSubCtrtDetail(Map insertMap) {
		gamFcltyCtrtMngDao.insertFcltyCtrtSubCtrtDetail(insertMap);
	}
	
	/**
	 * 계약번호에 해당하는 계약하도급정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void deleteFcltyCtrtSubCtrtAll(Map deleteMap) {
		gamFcltyCtrtMngDao.deleteFcltyCtrtSubCtrtAll(deleteMap);
	}

	/**
	 * 계약변경 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약변경 목록
	 * @exception Exception
	 */
	public List selectFcltyCtrtChangeList(GamFcltyCtrtMngVO searchVO) {
		return gamFcltyCtrtMngDao.selectFcltyCtrtChangeList(searchVO);
	}
	
	/**
	 * 계약변경 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약변경 목록 총개수
	 * @exception Exception
	 */
	public int selectFcltyCtrtChangeListCnt(GamFcltyCtrtMngVO searchVO) {
		return gamFcltyCtrtMngDao.selectFcltyCtrtChangeListCnt(searchVO);
	}
	
	
	/**
	 * 계약변경정보를 등록한다.
	 * @param insertMap - 등록할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void insertFcltyCtrtChangeDetail(Map insertMap) {
		gamFcltyCtrtMngDao.insertFcltyCtrtChangeDetail(insertMap);
	}
	
	/**
	 * 계약번호에 해당하는 계약변경정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void deleteFcltyCtrtChangeAll(Map deleteMap) {
		gamFcltyCtrtMngDao.deleteFcltyCtrtChangeAll(deleteMap);
	}
	

	/**
	 * 계약대금지급 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약대금지급 목록
	 * @exception Exception
	 */
	public List selectFcltyCtrtMoneyPymntList(GamFcltyCtrtMngVO searchVO) {
		return gamFcltyCtrtMngDao.selectFcltyCtrtMoneyPymntList(searchVO);
	}
	
	/**
	 * 계약대금지급 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약대금지급 목록 총개수
	 * @exception Exception
	 */
	public int selectFcltyCtrtMoneyPymntListCnt(GamFcltyCtrtMngVO searchVO) {
		return gamFcltyCtrtMngDao.selectFcltyCtrtMoneyPymntListCnt(searchVO);
	}
	
	
	/**
	 * 계약대금지급정보를 등록한다.
	 * @param insertMap - 등록할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void insertFcltyCtrtMoneyPymntDetail(Map insertMap) {
		gamFcltyCtrtMngDao.insertFcltyCtrtMoneyPymntDetail(insertMap);
	}
	
	/**
	 * 계약번호에 해당하는 계약대금지급정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void deleteFcltyCtrtMoneyPymntAll(Map deleteMap) {
		gamFcltyCtrtMngDao.deleteFcltyCtrtMoneyPymntAll(deleteMap);
	}
	
	/**
	 * 계약이행이월 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약이행이월 목록
	 * @exception Exception
	 */
	public List selectFcltyCtrtFulFillCaryFwdList(GamFcltyCtrtMngVO searchVO) {
		return gamFcltyCtrtMngDao.selectFcltyCtrtFulFillCaryFwdList(searchVO);
	}
	
	/**
	 * 계약이행이월 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약이행이월 목록 총개수
	 * @exception Exception
	 */
	public int selectFcltyCtrtFulFillCaryFwdListCnt(GamFcltyCtrtMngVO searchVO) {
		return gamFcltyCtrtMngDao.selectFcltyCtrtFulFillCaryFwdListCnt(searchVO);
	}
	
	
	/**
	 * 계약이행이월정보를 등록한다.
	 * @param insertMap - 등록할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void insertFcltyCtrtFulFillCaryFwdDetail(Map insertMap) {
		gamFcltyCtrtMngDao.insertFcltyCtrtFulFillCaryFwdDetail(insertMap);
	}
	
	/**
	 * 계약번호에 해당하는 계약이행이월정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void deleteFcltyCtrtFulFillCaryFwdAll(Map deleteMap) {
		gamFcltyCtrtMngDao.deleteFcltyCtrtFulFillCaryFwdAll(deleteMap);
	}
	
}
