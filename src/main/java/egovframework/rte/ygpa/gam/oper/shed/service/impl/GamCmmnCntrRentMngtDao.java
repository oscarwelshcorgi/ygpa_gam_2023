package egovframework.rte.ygpa.gam.oper.shed.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentMngtVO;

/**
 * @Class Name : GamCmmnCntrRentMngtDao.java
 * @Description : 공컨장치장임대목록관리 (항만시설/공컨장치장/공컨장치장임대목록관리)
 * @Modification Information
 *
 * @author 도명호
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamCmmnCntrRentMngtDao")
public class GamCmmnCntrRentMngtDao extends EgovAbstractDAO {
	
	/**
	 * 컨테이너부두임대목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대 목록
	 * @exception Exception
	 */
	public List selectGamCmmnCntrRentMngtList(GamCmmnCntrRentMngtVO searchVO) {
		 return list("gamCmmnCntrRentMngtDao.selectGamCmmnCntrRentMngtList_D", searchVO);
	}
	
	/**
	 * 컨테이너부두임대목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록 총 갯수
	 * @exception Exception
	 */
	public int selectGamCmmnCntrRentMngtListTotCnt(GamCmmnCntrRentMngtVO searchVO) {
		return  (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrRentMngtDao.selectGamCmmnCntrRentMngtListTotCnt_S", searchVO);
	}

	/**
	 * 컨테이너부두임대목록 정보 (자료수, 총면적, 총사용료 등)
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록 정보
	 * @exception Exception
	 */
	public GamCmmnCntrRentMngtVO selectGamCmmnCntrRentMngtInfo(GamCmmnCntrRentMngtVO searchVO) throws Exception {
		return (GamCmmnCntrRentMngtVO) selectByPk("gamCmmnCntrRentMngtDao.selectGamCmmnCntrRentMngtInfo_S", searchVO);
	}
}
