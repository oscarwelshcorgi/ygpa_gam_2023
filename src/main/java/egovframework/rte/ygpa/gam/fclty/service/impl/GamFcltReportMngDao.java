/**
 * 
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltReportMngVO;

/**
 * 
 * @author HNJ
 * @since 2015. 2. 6.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 2. 6.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamFcltReportMngDao")
public class GamFcltReportMngDao extends YGPAAbstractDAO {
	
	
	/**
	 * 시설물관리대장인쇄
	 * @param GamFcltReportMngVO
	 * @return Map
	 * @throws Exception
	 */	
	public EgovMap selectFcltReportMng(GamFcltReportMngVO searchVO) throws Exception {
		return (EgovMap)selectByPk("gamFcltReportMngDao.selectFcltReportMng_S", searchVO);
	}
	
	
	/**
	 * 안전점검 및 정밀안전진단계획 인쇄
	 * @param GamFcltReportMngVO
	 * @return Map
	 * @throws Exception
	 */
	public List<?> selectFcleQcMngList(GamFcltReportMngVO vo) throws Exception{
		return list("gamFcltReportMngDao.selectFcleQcMngList_D", vo);
	}
	
	
	/**
	 * 안전점검 및 정밀안전진단계획총갯수
	 * @param GamFcltReportMngVO
	 * @return Map
	 * @throws Exception
	 */
	public int selectFcleQcMngListTotalCount(GamFcltReportMngVO vo) throws Exception{
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltReportMngDao.selectFcleQcMngListTotalCount_S", vo);
	}
	
	
	/**
	 * 보수.보강계획 리스트 인쇄
	 * @param GamFcltReportMngVO
	 * @return Map
	 * @throws Exception
	 */
	public List<?> selectFcleMntnRprMngList(GamFcltReportMngVO vo) throws Exception{
		return list("gamFcltReportMngDao.selectFcleMntnRprMngList_D", vo);
	}
	
	
	/**
	 * 보수.보강계획 리스트 총갯수
	 * @param GamFcltReportMngVO
	 * @return Map
	 * @throws Exception
	 */
	public int selectFcleMntnRprMngListTotalCount(GamFcltReportMngVO vo) throws Exception{
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltReportMngDao.selectFcleMntnRprMngListTotalCount_S", vo);
	}

}
