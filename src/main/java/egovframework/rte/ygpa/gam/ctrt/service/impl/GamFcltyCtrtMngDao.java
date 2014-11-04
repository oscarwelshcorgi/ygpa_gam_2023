/**
 * 
 */
package egovframework.rte.ygpa.gam.ctrt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
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

@Repository("gamFcltyCtrtMngDao")
public class GamFcltyCtrtMngDao extends YGPAAbstractDAO {

	/**
	 * 계약정보를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약정보 VO
	 * @exception Exception
	 */
	public GamFcltyCtrtMngVO selectFcltyCtrtInfoDetail(GamFcltyCtrtMngVO searchVO) {
		return (GamFcltyCtrtMngVO)selectByPk("gamFcltyCtrtMngDao.selectFcltyCtrtInfoDetail_D", searchVO);
	}
	
	/**
	 * 계약정보를 등록한다.
	 * @param insertMap - 등록할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void insertFcltyCtrtInfoDetail(Map insertMap) {
		insert("gamFcltyCtrtMngDao.insertFcltyCtrtInfoDetail", insertMap);
	}
	
	/**
	 * 계약정보를 수정한다.
	 * @param updateMap - 수정할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void updateFcltyCtrtInfoDetail(Map updateMap) {
		update("gamFcltyCtrtMngDao.updateFcltyCtrtInfoDetail", updateMap);
	}

	/**
	 * 계약정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void deleteFcltyCtrtInfoDetail(Map deleteMap) {
		delete("gamFcltyCtrtMngDao.deleteFcltyCtrtInfoDetail", deleteMap);
	}
	
	
	/**
	 * 계약공동도급 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약공동도급 목록
	 * @exception Exception
	 */
	public List selectFcltyCtrtJoinContrList(GamFcltyCtrtMngVO searchVO) {
		return list("gamFcltyCtrtMngDao.selectFcltyCtrtJoinContrList", searchVO);
	}
	
	/**
	 * 계약공동도급 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약공동도급 목록 총개수
	 * @exception Exception
	 */
	public int selectFcltyCtrtJoinContrListCnt(GamFcltyCtrtMngVO searchVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyCtrtMngDao.selectFcltyCtrtJoinContrListCnt", searchVO);
	}
	
	
	/**
	 * 계약공동도급정보를 등록한다.
	 * @param insertMap - 등록할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void insertFcltyCtrtJoinContrDetail(Map insertMap) {
		insert("gamFcltyCtrtMngDao.insertFcltyCtrtJoinContrDetail", insertMap);
	}
	
	/**
	 * 계약번호에 해당하는 계약공동도급정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void deleteFcltyCtrtJoinContrAll(Map deleteMap) {
		delete("gamFcltyCtrtMngDao.deleteFcltyCtrtJoinContrAll", deleteMap);
	}


	/**
	 * 계약하도급 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약하도급 목록
	 * @exception Exception
	 */
	public List selectFcltyCtrtSubCtrtList(GamFcltyCtrtMngVO searchVO) {
		return list("gamFcltyCtrtMngDao.selectFcltyCtrtSubCtrtList", searchVO);
	}
	
	/**
	 * 계약하도급 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약하도급 목록 총개수
	 * @exception Exception
	 */
	public int selectFcltyCtrtSubCtrtListCnt(GamFcltyCtrtMngVO searchVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyCtrtMngDao.selectFcltyCtrtSubCtrtListCnt", searchVO);
	}
	
	
	/**
	 * 계약하도급정보를 등록한다.
	 * @param insertMap - 등록할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void insertFcltyCtrtSubCtrtDetail(Map insertMap) {
		insert("gamFcltyCtrtMngDao.insertFcltyCtrtSubCtrtDetail", insertMap);
	}
	
	/**
	 * 계약번호에 해당하는 계약하도급정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void deleteFcltyCtrtSubCtrtAll(Map deleteMap) {
		delete("gamFcltyCtrtMngDao.deleteFcltyCtrtSubCtrtAll", deleteMap);
	}

	/**
	 * 계약변경 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약변경 목록
	 * @exception Exception
	 */
	public List selectFcltyCtrtChangeList(GamFcltyCtrtMngVO searchVO) {
		return list("gamFcltyCtrtMngDao.selectFcltyCtrtChangeList", searchVO);
	}
	
	/**
	 * 계약변경 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약변경 목록 총개수
	 * @exception Exception
	 */
	public int selectFcltyCtrtChangeListCnt(GamFcltyCtrtMngVO searchVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyCtrtMngDao.selectFcltyCtrtChangeListCnt", searchVO);
	}
	
	
	/**
	 * 계약변경정보를 등록한다.
	 * @param insertMap - 등록할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void insertFcltyCtrtChangeDetail(Map insertMap) {
		insert("gamFcltyCtrtMngDao.insertFcltyCtrtChangeDetail", insertMap);
	}
	
	/**
	 * 계약번호에 해당하는 계약변경정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void deleteFcltyCtrtChangeAll(Map deleteMap) {
		delete("gamFcltyCtrtMngDao.deleteFcltyCtrtChangeAll", deleteMap);
	}
	

	/**
	 * 계약대금지급 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약대금지급 목록
	 * @exception Exception
	 */
	public List selectFcltyCtrtMoneyPymntList(GamFcltyCtrtMngVO searchVO) {
		return list("gamFcltyCtrtMngDao.selectFcltyCtrtMoneyPymntList", searchVO);
	}
	
	/**
	 * 계약대금지급 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약대금지급 목록 총개수
	 * @exception Exception
	 */
	public int selectFcltyCtrtMoneyPymntListCnt(GamFcltyCtrtMngVO searchVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyCtrtMngDao.selectFcltyCtrtMoneyPymntListCnt", searchVO);
	}
	
	
	/**
	 * 계약대금지급정보를 등록한다.
	 * @param insertMap - 등록할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void insertFcltyCtrtMoneyPymntDetail(Map insertMap) {
		insert("gamFcltyCtrtMngDao.insertFcltyCtrtMoneyPymntDetail", insertMap);
	}
	
	/**
	 * 계약번호에 해당하는 계약대금지급정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void deleteFcltyCtrtMoneyPymntAll(Map deleteMap) {
		delete("gamFcltyCtrtMngDao.deleteFcltyCtrtMoneyPymntAll", deleteMap);
	}

	/**
	 * 계약이행이월 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약이행이월 목록
	 * @exception Exception
	 */
	public List selectFcltyCtrtFulFillCaryFwdList(GamFcltyCtrtMngVO searchVO) {
		return list("gamFcltyCtrtMngDao.selectFcltyCtrtFulFillCaryFwdList", searchVO);
	}
	
	/**
	 * 계약이행이월 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약이행이월 목록 총개수
	 * @exception Exception
	 */
	public int selectFcltyCtrtFulFillCaryFwdListCnt(GamFcltyCtrtMngVO searchVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyCtrtMngDao.selectFcltyCtrtFulFillCaryFwdListCnt", searchVO);
	}
	
	
	/**
	 * 계약이행이월정보를 등록한다.
	 * @param insertMap - 등록할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void insertFcltyCtrtFulFillCaryFwdDetail(Map insertMap) {
		insert("gamFcltyCtrtMngDao.insertFcltyCtrtFulFillCaryFwdDetail", insertMap);
	}
	
	/**
	 * 계약번호에 해당하는 계약이행이월정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void deleteFcltyCtrtFulFillCaryFwdAll(Map deleteMap) {
		delete("gamFcltyCtrtMngDao.deleteFcltyCtrtFulFillCaryFwdAll", deleteMap);
	}
}
