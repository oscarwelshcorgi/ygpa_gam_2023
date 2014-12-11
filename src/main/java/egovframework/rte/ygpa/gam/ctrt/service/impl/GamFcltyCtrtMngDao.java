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
		return list("gamFcltyCtrtMngDao.selectFcltyCtrtMngList_D", searchVO);
	}
	
	/**
	 * 계약정보목록 통계정보를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약정보목록 통계정보 VO
	 * @exception Exception
	 */
	public GamFcltyCtrtMngVO selectFcltyCtrtMngTotSum(GamFcltyCtrtMngVO searchVO) throws Exception {
		return (GamFcltyCtrtMngVO)selectByPk("gamFcltyCtrtMngDao.selectFcltyCtrtMngTotSum_S", searchVO);
	}
	
	/**
	 * 계약정보를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약정보 VO
	 * @exception Exception
	 */
	public GamFcltyCtrtMngVO selectFcltyCtrtInfoDetail(GamFcltyCtrtMngVO searchVO) throws Exception {
		return (GamFcltyCtrtMngVO)selectByPk("gamFcltyCtrtMngDao.selectFcltyCtrtInfoDetail_S", searchVO);
	}
	
	/**
	 * 계약정보를 등록한다.
	 * @param insertMap - 등록할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void insertFcltyCtrtInfoDetail(Map insertMap) throws Exception {
		insert("gamFcltyCtrtMngDao.insertFcltyCtrtInfoDetail_S", insertMap);
	}
	
	/**
	 * 계약정보를 수정한다.
	 * @param updateMap - 수정할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void updateFcltyCtrtInfoDetail(Map updateMap) throws Exception {
		update("gamFcltyCtrtMngDao.updateFcltyCtrtInfoDetail_S", updateMap);
	}

	/**
	 * 계약정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void deleteFcltyCtrtInfoDetail(Map deleteMap) throws Exception {
		delete("gamFcltyCtrtMngDao.deleteFcltyCtrtInfoDetail_S", deleteMap);
	}
	
	
	/**
	 * 계약공동도급 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약공동도급 목록
	 * @exception Exception
	 */
	public List selectFcltyCtrtJoinContrList(GamFcltyCtrtMngVO searchVO) throws Exception {
		return list("gamFcltyCtrtMngDao.selectFcltyCtrtJoinContrList_D", searchVO);
	}
	
	/**
	 * 계약공동도급 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약공동도급 목록 총개수
	 * @exception Exception
	 */
	public int selectFcltyCtrtJoinContrListTotCnt(GamFcltyCtrtMngVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyCtrtMngDao.selectFcltyCtrtJoinContrListTotCnt_S", searchVO);
	}
		
	/**
	 * 계약공동도급정보를 병합저장한다.
	 * @param map - 병합저장할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public List mergeFcltyCtrtJoinContrDetail(Map map) throws Exception {
		return this.merge(map, "gamFcltyCtrtMngDao.insertFcltyCtrtJoinContrDetail_S", "gamFcltyCtrtMngDao.updateFcltyCtrtJoinContrDetail_S", "gamFcltyCtrtMngDao.deleteFcltyCtrtJoinContrDetail_S");
	}
	
	/**
	 * 계약번호에 해당하는 계약공동도급정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void deleteFcltyCtrtJoinContrList(Map deleteMap) throws Exception {
		delete("gamFcltyCtrtMngDao.deleteFcltyCtrtJoinContrList_S", deleteMap);
	}
	

	/**
	 * 계약하도급 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약하도급 목록
	 * @exception Exception
	 */
	public List selectFcltyCtrtSubCtrtList(GamFcltyCtrtMngVO searchVO) throws Exception {
		return list("gamFcltyCtrtMngDao.selectFcltyCtrtSubCtrtList_D", searchVO);
	}
	
	/**
	 * 계약하도급 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약하도급 목록 총개수
	 * @exception Exception
	 */
	public int selectFcltyCtrtSubCtrtListTotCnt(GamFcltyCtrtMngVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyCtrtMngDao.selectFcltyCtrtSubCtrtListTotCnt_S", searchVO);
	}
	
	/**
	 * 계약하도급정보를 병합저장한다.
	 * @param map - 병합저장할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public List mergeFcltyCtrtSubCtrtDetail(Map map) throws Exception {
		return this.merge(map, "gamFcltyCtrtMngDao.insertFcltyCtrtSubCtrtDetail_S", "gamFcltyCtrtMngDao.updateFcltyCtrtSubCtrtDetail_S", "gamFcltyCtrtMngDao.deleteFcltyCtrtSubCtrtDetail_S");
	}

	/**
	 * 계약번호에 해당하는 계약하도급정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void deleteFcltyCtrtSubCtrtList(Map deleteMap) throws Exception {
		delete("gamFcltyCtrtMngDao.deleteFcltyCtrtSubCtrtList_S", deleteMap);
	}

	
	/**
	 * 계약변경 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약변경 목록
	 * @exception Exception
	 */
	public List selectFcltyCtrtChangeList(GamFcltyCtrtMngVO searchVO) throws Exception {
		return list("gamFcltyCtrtMngDao.selectFcltyCtrtChangeList_D", searchVO);
	}
	
	/**
	 * 계약변경 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약변경 목록 총개수
	 * @exception Exception
	 */
	public int selectFcltyCtrtChangeListTotCnt(GamFcltyCtrtMngVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyCtrtMngDao.selectFcltyCtrtChangeListTotCnt_S", searchVO);
	}
	
	
	/**
	 * 계약변경정보를 병합저장한다.
	 * @param map - 병합저장할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public List mergeFcltyCtrtChangeDetail(Map map) throws Exception {
		return this.merge(map, "gamFcltyCtrtMngDao.insertFcltyCtrtChangeDetail_S", "gamFcltyCtrtMngDao.updateFcltyCtrtChangeDetail_S", "gamFcltyCtrtMngDao.deleteFcltyCtrtChangeDetail_S");
	}

	/**
	 * 계약번호에 해당하는 계약변경정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void deleteFcltyCtrtChangeList(Map deleteMap) throws Exception {
		delete("gamFcltyCtrtMngDao.deleteFcltyCtrtChangeList_S", deleteMap);
	}
	

	/**
	 * 계약대금지급 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약대금지급 목록
	 * @exception Exception
	 */
	public List selectFcltyCtrtMoneyPymntList(GamFcltyCtrtMngVO searchVO) throws Exception {
		return list("gamFcltyCtrtMngDao.selectFcltyCtrtMoneyPymntList_D", searchVO);
	}
	
	/**
	 * 계약대금지급 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약대금지급 목록 총개수
	 * @exception Exception
	 */
	public int selectFcltyCtrtMoneyPymntListTotCnt(GamFcltyCtrtMngVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyCtrtMngDao.selectFcltyCtrtMoneyPymntListTotCnt_S", searchVO);
	}
	
	/**
	 * 계약대금지급정보를 병합저장한다.
	 * @param map - 병합저장할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public List mergeFcltyCtrtMoneyPymntDetail(Map map) throws Exception {
		return this.merge(map, "gamFcltyCtrtMngDao.insertFcltyCtrtMoneyPymntDetail_S", "gamFcltyCtrtMngDao.updateFcltyCtrtMoneyPymntDetail_S", "gamFcltyCtrtMngDao.deleteFcltyCtrtMoneyPymntDetail_S");
	}
	
	/**
	 * 계약번호에 해당하는 계약대금지급정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void deleteFcltyCtrtMoneyPymntList(Map deleteMap) throws Exception {
		delete("gamFcltyCtrtMngDao.deleteFcltyCtrtMoneyPymntList_S", deleteMap);
	}

	/**
	 * 계약이행이월 목록을 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약이행이월 목록
	 * @exception Exception
	 */
	public List selectFcltyCtrtFulFillCaryFwdList(GamFcltyCtrtMngVO searchVO) throws Exception {
		return list("gamFcltyCtrtMngDao.selectFcltyCtrtFulFillCaryFwdList_D", searchVO);
	}
	
	/**
	 * 계약이행이월 목록의 총개수를 가져온다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 계약이행이월 목록 총개수
	 * @exception Exception
	 */
	public int selectFcltyCtrtFulFillCaryFwdListTotCnt(GamFcltyCtrtMngVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyCtrtMngDao.selectFcltyCtrtFulFillCaryFwdListTotCnt_S", searchVO);
	}
	
	/**
	 * 계약이행이월정보를 병합저장한다.
	 * @param map - 병합저장할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public List mergeFcltyCtrtFulFillCaryFwdDetail(Map map) throws Exception {
		return this.merge(map, "gamFcltyCtrtMngDao.insertFcltyCtrtFulFillCaryFwdDetail_S", "gamFcltyCtrtMngDao.updateFcltyCtrtFulFillCaryFwdDetail_S", "gamFcltyCtrtMngDao.deleteFcltyCtrtFulFillCaryFwdDetail_S");
	}

	/**
	 * 계약번호에 해당하는 계약이행이월정보를 삭제한다.
	 * @param deleteMap - 삭제할 정보가 담긴 Map
	 * @return 
	 * @exception Exception
	 */
	public void deleteFcltyCtrtFulFillCaryFwdList(Map deleteMap) throws Exception {
		delete("gamFcltyCtrtMngDao.deleteFcltyCtrtFulFillCaryFwdList_S", deleteMap);
	}
}
