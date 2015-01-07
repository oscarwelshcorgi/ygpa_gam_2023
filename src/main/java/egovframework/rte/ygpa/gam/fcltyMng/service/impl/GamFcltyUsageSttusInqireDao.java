/**
 *
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;

import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyUsageSttusInqireVO;




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
     * @param vo ComDefaultVO
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
     * @param vo ComDefaultVO
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
     * @param vo ComDefaultVO
     * @return List
     * @exception Exception
     */
	public List selectQcMngList(GamFcltyUsageSttusInqireVO vo){
		return list("gamFcltyUsageSttusInqireDao.selectQcMngList", vo);
	}

	/**
     * 점검 관리 대상 시설물
     * @param vo ComDefaultVO
     * @return List
     * @exception Exception
     */
	public List selectQcMngObjFcltsList(GamFcltyUsageSttusInqireVO vo){
		return list("gamFcltyUsageSttusInqireDao.selectQcMngObjFcltsList", vo);
	}
	/**
     * 점검 관리 결과 항목
     * @param vo ComDefaultVO
     * @return List
     * @exception Exception
     */
	public List selectQcMngResultItemList(GamFcltyUsageSttusInqireVO vo){
		return list("gamFcltyUsageSttusInqireDao.selectQcMngResultItemList", vo);
	}
}