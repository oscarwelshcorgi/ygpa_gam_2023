package egovframework.rte.ygpa.gam.oper.shed.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrShedRentSttusInqireVO;

/**
 * @Class Name : GamCmmnCntrShedRentSttusInqireDao.java
 * @Description : 공컨장치장임대현황조회 (항만시설/공컨장치장/공컨장치장임대현황조회)
 * @Modification Information
 *
 * @author 도명호
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamCmmnCntrShedRentSttusInqireDao")
public class GamCmmnCntrShedRentSttusInqireDao extends EgovAbstractDAO {
	
	/**
	 * 컨테이너부두임대현황을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대현황 목록
	 * @exception Exception
	 */
	public List selectGamCmmnCntrShedRentSttusInqireList(GamCmmnCntrShedRentSttusInqireVO searchVO) {
		 return list("gamCmmnCntrShedRentSttusInqireDao.selectGamCmmnCntrShedRentSttusInqireList_D", searchVO);
	}
	
	/**
	 * 컨테이너부두임대현황 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대현황 총 갯수
	 * @exception Exception
	 */
	public int selectGamCmmnCntrShedRentSttusInqireListTotCnt(GamCmmnCntrShedRentSttusInqireVO searchVO) {
		return  (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrShedRentSttusInqireDao.selectGamCmmnCntrShedRentSttusInqireListTotCnt_S", searchVO);
	}

	/**
	 * 컨테이너부두임대현황 정보 (자료수, 총면적, 총사용료 등)
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대현황 정보
	 * @exception Exception
	 */
	public GamCmmnCntrShedRentSttusInqireVO selectGamCmmnCntrShedRentSttusInqireInfo(GamCmmnCntrShedRentSttusInqireVO searchVO) throws Exception {
		return (GamCmmnCntrShedRentSttusInqireVO) selectByPk("gamCmmnCntrShedRentSttusInqireDao.selectGamCmmnCntrShedRentSttusInqireInfo_S", searchVO);
	}
}
