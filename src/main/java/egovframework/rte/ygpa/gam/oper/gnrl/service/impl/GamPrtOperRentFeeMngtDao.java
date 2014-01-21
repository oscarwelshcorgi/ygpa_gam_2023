package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtOperRentFeeMngtVO;

/**
 * @Class Name : GamPrtOperRentFeeMngtDao.java
 * @Description : 항만시설사용료관리 (항만시설/일반부두/항만시설사용료관리)
 * @Modification Information
 *
 * @author 도명호
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamPrtOperRentFeeMngtDao")
public class GamPrtOperRentFeeMngtDao extends EgovAbstractDAO {
	
	/**
	 * 항만시설사용현황을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용현황 목록
	 * @exception Exception
	 */
	public List selectGamPrtOperRentFeeMngtList(GamPrtOperRentFeeMngtVO searchVO) {
		// TODO Auto-generated method stub
		 return list("gamPrtOperRentFeeMngtDao.selectGamPrtOperRentFeeMngtList_D", searchVO);
	}
	
	/**
	 * 항만시설사용현황 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용현황 총 갯수
	 * @exception Exception
	 */
	public int selectGamPrtOperRentFeeMngtListTotCnt(GamPrtOperRentFeeMngtVO searchVO) {
		return  (Integer)getSqlMapClientTemplate().queryForObject("gamPrtOperRentFeeMngtDao.selectGamPrtOperRentFeeMngtListTotCnt_S", searchVO);
	}

}
