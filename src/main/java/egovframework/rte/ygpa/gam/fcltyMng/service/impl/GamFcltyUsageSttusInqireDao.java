/**
 *
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyUsageSttusInqireVO;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyUseUnuseSttusInqireVO;




/**
*
* @author jckim
* @since 2014. 12. 8.
* @version 1.0
* @see
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일 		 수정자		 수정내용
*  -------		--------	---------------------------
*  2014. 12. 8.		jckim		최초 생성
*
* Copyright (C) 2013 by LFIT  All right reserved.
* </pre>
*/

@Repository("gamFcltyUsageSttusInqireDao")
public class GamFcltyUsageSttusInqireDao extends YGPAAbstractDAO{

    /**
     * GIS 항만시설 목록 조회
     * @param vo
     * @return List
     * @exception Exception
     */
	public List selectFcltyGisPrtFcltyCdList(GamFcltyUsageSttusInqireVO vo) throws Exception{
		return list("gamFcltyUsageSttusInqireDao.selectFcltyGisPrtFcltyCdList", vo);
	}

	public int selectFcltyGisPrtFcltyCdListTotCnt(GamFcltyUsageSttusInqireVO vo) {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyUsageSttusInqireDao.selectFcltyGisPrtFcltyCdListTotCnt", vo);
	}

	/**
     * 시설물 사용현황 목록 조회
     * @param vo
     * @return List
     * @exception Exception
     */
	@SuppressWarnings("unchecked")
	public List selectFcltyAssetsRentList(GamFcltyUsageSttusInqireVO vo) throws Exception{
		return list("gamFcltyUsageSttusInqireDao.selectFcltyAssetsRentList", vo);
	}

	public int selectFcltyAssetsRentListTotCnt(GamFcltyUsageSttusInqireVO vo) {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltyUsageSttusInqireDao.selectFcltyAssetsRentListTotCnt", vo);
	}

	/**
     * 점검 관리 내역
     * @param vo
     * @return List
     * @exception Exception
     */
	public List selectQcMngList(GamFcltyUsageSttusInqireVO vo){
		return list("gamFcltyUsageSttusInqireDao.selectQcMngList", vo);
	}

	

	/**
	 * 하자 보수 내역
	 * @param vo
	 * @return List
	 * @exception Exception
	 */
	public List selectFlawList(GamFcltyUsageSttusInqireVO vo){
		return list("gamFcltyUsageSttusInqireDao.selectFlawList", vo);
	}


	/**
	 * 유지 보수 내역
	 * @param vo
	 * @return List
	 * @exception Exception
	 */
	public List selectMntnRprDtlsList(GamFcltyUsageSttusInqireVO vo){
		return list("gamFcltyUsageSttusInqireDao.selectMntnRprDtlsList", vo);
	}


	// TODO Auto-generated method stub
	public EgovMap selectFcltsMngGroupNm(Map searchVO) {
		return (EgovMap)selectByPk("gamFcltyUseUnuseSttusInqireDao.selectFcltsMngGroupNm_S", searchVO);
	}	


}