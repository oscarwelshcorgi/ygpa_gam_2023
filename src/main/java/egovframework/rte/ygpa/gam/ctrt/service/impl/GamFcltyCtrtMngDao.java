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
	 * 계약정보목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약정보목록
	 * @exception Exception
	 */
	public List selectFcltyCtrtMngList(GamFcltyCtrtMngVO searchVO) throws Exception {
		return list("gamFcltyCtrtMngDao.selectFcltyCtrtMngList", searchVO);
	}
	
	/**
	 * 계약정보목록 통계정보를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약정보목록 통계정보 VO
	 * @exception Exception
	 */
	public GamFcltyCtrtMngVO selectFcltyCtrtMngSum(GamFcltyCtrtMngVO searchVO) throws Exception {
		return (GamFcltyCtrtMngVO)selectByPk("gamFcltyCtrtMngDao.selectFcltyCtrtMngSum", searchVO);
	}
	
	/**
	 * 계약정보를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약정보 VO
	 * @exception Exception
	 */
	public GamFcltyCtrtMngVO selectFcltyCtrtInfoDetail(GamFcltyCtrtMngVO searchVO) throws Exception {
		return (GamFcltyCtrtMngVO)selectByPk("gamFcltyCtrtMngDao.selectFcltyCtrtInfoDetail_D", searchVO);
	}
	
	/**
	 * 계약정보를 등록한다.
	 * @param insertMap - 등록할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void insertFcltyCtrtInfoDetail(Map insertMap) throws Exception {
		insert("gamFcltyCtrtMngDao.insertFcltyCtrtInfoDetail", insertMap);
	}
	
	/**
	 * 계약정보를 수정한다.
	 * @param updateMap - 수정할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void updateFcltyCtrtInfoDetail(Map updateMap) throws Exception {
		update("gamFcltyCtrtMngDao.updateFcltyCtrtInfoDetail", updateMap);
	}

	/**
	 * 계약정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void deleteFcltyCtrtInfoDetail(Map deleteMap) throws Exception {
		delete("gamFcltyCtrtMngDao.deleteFcltyCtrtInfoDetail", deleteMap);
	}
	
	
	/**
	 * 계약공동도급 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약공동도급 목록
	 * @exception Exception
	 */
	public List selectFcltyCtrtJoinContrList(GamFcltyCtrtMngVO searchVO) throws Exception {
		return list("gamFcltyCtrtMngDao.selectFcltyCtrtJoinContrList", searchVO);
	}
	
	/**
	 * 계약공동도급 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약공동도급 목록 총개수
	 * @exception Exception
	 */
	public int selectFcltyCtrtJoinContrListCnt(GamFcltyCtrtMngVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyCtrtMngDao.selectFcltyCtrtJoinContrListCnt", searchVO);
	}
	
	
	/**
	 * 계약공동도급정보를 등록한다.
	 * @param insertMap - 등록할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void insertFcltyCtrtJoinContrDetail(Map insertMap) throws Exception {
		insert("gamFcltyCtrtMngDao.insertFcltyCtrtJoinContrDetail", insertMap);
	}

	/**
	 * 계약공동도급정보를 병합저장한다.
	 * @param map - 병합저장할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public List mergeFcltyCtrtJoinContrDetail(Map map) throws Exception {
		return this.merge(map, "gamFcltyCtrtMngDao.insertFcltyCtrtJoinContrDetail", "gamFcltyCtrtMngDao.updateFcltyCtrtJoinContrDetail", "gamFcltyCtrtMngDao.deleteFcltyCtrtJoinContrDetail");
	}
	
	/**
	 * 계약번호에 해당하는 계약공동도급정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void deleteFcltyCtrtJoinContrAll(Map deleteMap) throws Exception {
		delete("gamFcltyCtrtMngDao.deleteFcltyCtrtJoinContrAll", deleteMap);
	}
	

	/**
	 * 계약하도급 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약하도급 목록
	 * @exception Exception
	 */
	public List selectFcltyCtrtSubCtrtList(GamFcltyCtrtMngVO searchVO) throws Exception {
		return list("gamFcltyCtrtMngDao.selectFcltyCtrtSubCtrtList", searchVO);
	}
	
	/**
	 * 계약하도급 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약하도급 목록 총개수
	 * @exception Exception
	 */
	public int selectFcltyCtrtSubCtrtListCnt(GamFcltyCtrtMngVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyCtrtMngDao.selectFcltyCtrtSubCtrtListCnt", searchVO);
	}
	
	
	/**
	 * 계약하도급정보를 등록한다.
	 * @param insertMap - 등록할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void insertFcltyCtrtSubCtrtDetail(Map insertMap) throws Exception {
		insert("gamFcltyCtrtMngDao.insertFcltyCtrtSubCtrtDetail", insertMap);
	}
	
	/**
	 * 계약하도급정보를 병합저장한다.
	 * @param map - 병합저장할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public List mergeFcltyCtrtSubCtrtDetail(Map map) throws Exception {
		return this.merge(map, "gamFcltyCtrtMngDao.insertFcltyCtrtSubCtrtDetail", "gamFcltyCtrtMngDao.updateFcltyCtrtSubCtrtDetail", "gamFcltyCtrtMngDao.deleteFcltyCtrtSubCtrtDetail");
	}

	/**
	 * 계약번호에 해당하는 계약하도급정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void deleteFcltyCtrtSubCtrtAll(Map deleteMap) throws Exception {
		delete("gamFcltyCtrtMngDao.deleteFcltyCtrtSubCtrtAll", deleteMap);
	}

	
	/**
	 * 계약변경 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약변경 목록
	 * @exception Exception
	 */
	public List selectFcltyCtrtChangeList(GamFcltyCtrtMngVO searchVO) throws Exception {
		return list("gamFcltyCtrtMngDao.selectFcltyCtrtChangeList", searchVO);
	}
	
	/**
	 * 계약변경 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약변경 목록 총개수
	 * @exception Exception
	 */
	public int selectFcltyCtrtChangeListCnt(GamFcltyCtrtMngVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyCtrtMngDao.selectFcltyCtrtChangeListCnt", searchVO);
	}
	
	
	/**
	 * 계약변경정보를 등록한다.
	 * @param insertMap - 등록할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void insertFcltyCtrtChangeDetail(Map insertMap) throws Exception {
		insert("gamFcltyCtrtMngDao.insertFcltyCtrtChangeDetail", insertMap);
	}
	
	/**
	 * 계약변경정보를 병합저장한다.
	 * @param map - 병합저장할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public List mergeFcltyCtrtChangeDetail(Map map) throws Exception {
		return this.merge(map, "gamFcltyCtrtMngDao.insertFcltyCtrtChangeDetail", "gamFcltyCtrtMngDao.updateFcltyCtrtChangeDetail", "gamFcltyCtrtMngDao.deleteFcltyCtrtChangeDetail");
	}

	/**
	 * 계약번호에 해당하는 계약변경정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void deleteFcltyCtrtChangeAll(Map deleteMap) throws Exception {
		delete("gamFcltyCtrtMngDao.deleteFcltyCtrtChangeAll", deleteMap);
	}
	

	/**
	 * 계약대금지급 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약대금지급 목록
	 * @exception Exception
	 */
	public List selectFcltyCtrtMoneyPymntList(GamFcltyCtrtMngVO searchVO) throws Exception {
		return list("gamFcltyCtrtMngDao.selectFcltyCtrtMoneyPymntList", searchVO);
	}
	
	/**
	 * 계약대금지급 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약대금지급 목록 총개수
	 * @exception Exception
	 */
	public int selectFcltyCtrtMoneyPymntListCnt(GamFcltyCtrtMngVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyCtrtMngDao.selectFcltyCtrtMoneyPymntListCnt", searchVO);
	}
	
	/**
	 * 계약대금지급정보를 등록한다.
	 * @param insertMap - 등록할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void insertFcltyCtrtMoneyPymntDetail(Map insertMap) throws Exception {
		insert("gamFcltyCtrtMngDao.insertFcltyCtrtMoneyPymntDetail", insertMap);
	}

	/**
	 * 계약대금지급정보를 병합저장한다.
	 * @param map - 병합저장할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public List mergeFcltyCtrtMoneyPymntDetail(Map map) throws Exception {
		return this.merge(map, "gamFcltyCtrtMngDao.insertFcltyCtrtMoneyPymntDetail", "gamFcltyCtrtMngDao.updateFcltyCtrtMoneyPymntDetail", "gamFcltyCtrtMngDao.deleteFcltyCtrtMoneyPymntDetail");
	}
	
	/**
	 * 계약번호에 해당하는 계약대금지급정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void deleteFcltyCtrtMoneyPymntAll(Map deleteMap) throws Exception {
		delete("gamFcltyCtrtMngDao.deleteFcltyCtrtMoneyPymntAll", deleteMap);
	}

	/**
	 * 계약이행이월 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약이행이월 목록
	 * @exception Exception
	 */
	public List selectFcltyCtrtFulFillCaryFwdList(GamFcltyCtrtMngVO searchVO) throws Exception {
		return list("gamFcltyCtrtMngDao.selectFcltyCtrtFulFillCaryFwdList", searchVO);
	}
	
	/**
	 * 계약이행이월 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약이행이월 목록 총개수
	 * @exception Exception
	 */
	public int selectFcltyCtrtFulFillCaryFwdListCnt(GamFcltyCtrtMngVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyCtrtMngDao.selectFcltyCtrtFulFillCaryFwdListCnt", searchVO);
	}
	
	
	/**
	 * 계약이행이월정보를 등록한다.
	 * @param insertMap - 등록할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void insertFcltyCtrtFulFillCaryFwdDetail(Map insertMap) throws Exception {
		insert("gamFcltyCtrtMngDao.insertFcltyCtrtFulFillCaryFwdDetail", insertMap);
	}
	
	/**
	 * 계약이행이월정보를 병합저장한다.
	 * @param map - 병합저장할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public List mergeFcltyCtrtFulFillCaryFwdDetail(Map map) throws Exception {
		return this.merge(map, "gamFcltyCtrtMngDao.insertFcltyCtrtFulFillCaryFwdDetail", "gamFcltyCtrtMngDao.updateFcltyCtrtFulFillCaryFwdDetail", "gamFcltyCtrtMngDao.deleteFcltyCtrtFulFillCaryFwdDetail");
	}

	/**
	 * 계약번호에 해당하는 계약이행이월정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void deleteFcltyCtrtFulFillCaryFwdAll(Map deleteMap) throws Exception {
		delete("gamFcltyCtrtMngDao.deleteFcltyCtrtFulFillCaryFwdAll", deleteMap);
	}
}
