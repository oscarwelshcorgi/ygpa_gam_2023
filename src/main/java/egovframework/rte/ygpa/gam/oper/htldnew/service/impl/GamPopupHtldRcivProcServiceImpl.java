/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentNticDefaultVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamPopupHtldRcivProcService;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamPopupHtldRcivProcVO;

/**
 * 
 * @author Jongmin
 * @since 2016. 6. 15.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 6. 15.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamPopupHtldRcivProcService")
public class GamPopupHtldRcivProcServiceImpl extends AbstractServiceImpl implements GamPopupHtldRcivProcService {

	@Resource(name="gamPopupHtldRcivProcDao")
    private GamPopupHtldRcivProcDao gamPopupHtldRcivProcDao;

	/**
	 * 수납처리정보 조회
	 * @param GamHtldRentNticDefaultVO
	 * @return Map 
	 * @exception Exception
	 */
	public Map<?, ?> selectHtldNticDtlsRcivInfo(GamHtldRentNticDefaultVO searchVO) throws Exception {
		return gamPopupHtldRcivProcDao.selectHtldNticDtlsRcivInfo(searchVO);
	}
	
	/**
	 * 수납정보 수정
	 * @param GamPopupHtldRcivProcVO
	 * @return 
	 * @exception Exception
	 */
	public void updateHtldRcivInfo(GamPopupHtldRcivProcVO vo) throws Exception {
		vo.setRcvdTp(vo.getRcivSe());
		vo.setRcvdDt(vo.getRcivDt());
		gamPopupHtldRcivProcDao.updateNticDtlsRcivInfo(vo);
		gamPopupHtldRcivProcDao.updateRevCollRcivInfo(vo);
	}

}
