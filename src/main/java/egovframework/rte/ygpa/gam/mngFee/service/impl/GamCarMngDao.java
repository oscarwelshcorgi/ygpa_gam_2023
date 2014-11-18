/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.mngFee.service.GamCarMngVo;
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
@Repository("gamCarMngDao")
public class GamCarMngDao extends YGPAAbstractDAO{

	public List selectCarMngList(GamCarMngVo searchVO) throws Exception{
		return list("gamCarMngDao.selectCarMngList_D", searchVO);
	}

	public int selectCarMngListTotCnt(GamCarMngVo searchVO) throws Exception {
       	return (Integer)getSqlMapClientTemplate().queryForObject("gamCarMngDao.selectCarMngListTotCnt_S", searchVO);
       }

	public void insertCarMng(GamCarMngVo gamCarMngVo) {
		insert("gamCarMngDao.insertCarMng_S", gamCarMngVo);
	}

	public void updateCarMng(GamCarMngVo gamCarMngVo) {
		insert("gamCarMngDao.updateCarMng_S", gamCarMngVo);
	}

	public void deleteCarMng(GamCarMngVo gamCarMngVo ) throws Exception {
		delete("gamCarMngDao.deleteCarMng_S", gamCarMngVo);
	}
}
