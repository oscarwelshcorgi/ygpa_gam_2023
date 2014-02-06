package egovframework.rte.ygpa.gam.oper.htld.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentfeeMngtVO;

/**
 * @Class Name : GamHtldRentfeeMngtDao.java
 * @Description : 배후단지임대료관리 (항만시설/배후단지/배후단지임대료관리)
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamHtldRentfeeMngtDao")
public class GamHtldRentfeeMngtDao extends EgovAbstractDAO {
	
	/**
	 * 배후단지임대료관리를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대료관리 목록
	 * @exception Exception
	 */
	public List selectGamHtldRentfeeMngtList(GamHtldRentfeeMngtVO searchVO) {
		 return list("gamHtldRentfeeMngtDao.selectGamHtldRentfeeMngtList_D", searchVO);
	}
	
	/**
	 * 배후단지임대료관리 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대료관리 총 갯수
	 * @exception Exception
	 */
	public int selectGamHtldRentfeeMngtListTotCnt(GamHtldRentfeeMngtVO searchVO) {
		return  (Integer)getSqlMapClientTemplate().queryForObject("gamHtldRentfeeMngtDao.selectGamHtldRentfeeMngtListTotCnt_S", searchVO);
	}

	/**
	 * 배후단지임대료관리 정보 (자료수, 총면적, 총사용료 등)
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대료관리 정보
	 * @exception Exception
	 */
	public GamHtldRentfeeMngtVO selectGamHtldRentfeeMngtInfo(GamHtldRentfeeMngtVO searchVO) throws Exception {
		return (GamHtldRentfeeMngtVO) selectByPk("gamHtldRentfeeMngtDao.selectGamHtldRentfeeMngtInfo_S", searchVO);
	}
}
