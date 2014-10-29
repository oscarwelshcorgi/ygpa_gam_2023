/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.mngFee.service.GamCarMngVo;
import egovframework.rte.ygpa.gam.mngFee.service.GamCarRefuelSttusMngVo;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyEntrpsRentFeeSttusInqireVO;

/**
 *
 * @author Lee
 * @since 2014. 10. 22.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 22.		Lee		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamCarRefuelSttusMngDao")
public class GamCarRefuelSttusMngDao extends YGPAAbstractDAO{


	/**
	 * @param gamCarRefuelSttusMngVo
	 */
	public void InsertCarRefuelSttusMngList(GamCarRefuelSttusMngVo gamCarRefuelSttusMngVo) {
		insert("gamCarRefuelSttusMngDao.InsertCarRefuelSttusMngList_S", gamCarRefuelSttusMngVo);
	}

	/**
	 * @param gamCarRefuelSttusMngVo
	 */
	public void DeleteCarRefuelSttusMngList(GamCarRefuelSttusMngVo gamCarRefuelSttusMngVo) {
		delete("gamCarRefuelSttusMngDao.DeleteCarRefuelSttusMngList_S", gamCarRefuelSttusMngVo);
	}

	/**
	 * @param gamCarRefuelSttusMngVo
	 * @return
	 */
	public int selectCarRefuelSttusMngListTotCnt(GamCarRefuelSttusMngVo gamCarRefuelSttusMngVo) {
		// TODO Auto-generated method stub
		return (Integer)getSqlMapClientTemplate().queryForObject("gamCarRefuelSttusMngDao.selectCarRefuelSttusMngListTotCnt_S", gamCarRefuelSttusMngVo);
	}

	/**
	 * @param gamCarRefuelSttusMngVo
	 * @return
	 */
	public List selectCarRefuelSttusMngList(GamCarRefuelSttusMngVo gamCarRefuelSttusMngVo) {
		// TODO Auto-generated method stub
		return list("gamCarRefuelSttusMngDao.selectCarRefuelSttusMngList_D", gamCarRefuelSttusMngVo);

	}


}