package egovframework.rte.ygpa.gam.oper.cntnr.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentfeeInqireVO;

/**
 * @Class Name : GamCntnrQuayRentfeeInqireDao.java
 * @Description : 컨테이너부두임대료조회 (항만시설/컨테이너부두/컨테이너부두임대료조회)
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamCntnrQuayRentfeeInqireDao")
public class GamCntnrQuayRentfeeInqireDao extends EgovAbstractDAO {
	
	/**
	 * 컨테이너부두임대료관리를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대료관리 목록
	 * @exception Exception
	 */
	public List selectGamCntnrQuayRentfeeInqireList(GamCntnrQuayRentfeeInqireVO searchVO) {
		 return list("gamCntnrQuayRentfeeInqireDao.selectGamCntnrQuayRentfeeInqireList_D", searchVO);
	}
	
	/**
	 * 컨테이너부두임대료관리 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대료관리 총 갯수
	 * @exception Exception
	 */
	public int selectGamCntnrQuayRentfeeInqireListTotCnt(GamCntnrQuayRentfeeInqireVO searchVO) {
		return  (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrQuayRentfeeInqireDao.selectGamCntnrQuayRentfeeInqireListTotCnt_S", searchVO);
	}

	/**
	 * 컨테이너부두임대료관리 정보 (자료수, 총면적, 총사용료 등)
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대료관리 정보
	 * @exception Exception
	 */
	public GamCntnrQuayRentfeeInqireVO selectGamCntnrQuayRentfeeInqireInfo(GamCntnrQuayRentfeeInqireVO searchVO) throws Exception {
		return (GamCntnrQuayRentfeeInqireVO) selectByPk("gamCntnrQuayRentfeeInqireDao.selectGamCntnrQuayRentfeeInqireInfo_S", searchVO);
	}
}
