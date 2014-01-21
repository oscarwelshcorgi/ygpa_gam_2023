package egovframework.rte.ygpa.gam.oper.cntnr.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrRentMngtVO;

/**
 * @Class Name : GamCntnrRentMngtDao.java
 * @Description : 컨테이너부두임대목록관리 (항만시설/컨테이너부두/컨테이너부두임대목록관리)
 * @Modification Information
 *
 * @author 도명호
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamCntnrRentMngtDao")
public class GamCntnrRentMngtDao extends EgovAbstractDAO {
	
	/**
	 * 컨테이너부두임대목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대 목록
	 * @exception Exception
	 */
	public List selectGamCntnrRentMngtList(GamCntnrRentMngtVO searchVO) {
		 return list("gamCntnrRentMngtDao.selectGamCntnrRentMngtList_D", searchVO);
	}
	
	/**
	 * 컨테이너부두임대목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록 총 갯수
	 * @exception Exception
	 */
	public int selectGamCntnrRentMngtListTotCnt(GamCntnrRentMngtVO searchVO) {
		return  (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrRentMngtDao.selectGamCntnrRentMngtListTotCnt_S", searchVO);
	}

	/**
	 * 컨테이너부두임대목록 정보 (자료수, 총면적, 총사용료 등)
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록 정보
	 * @exception Exception
	 */
	public GamCntnrRentMngtVO selectGamCntnrRentMngtInfo(GamCntnrRentMngtVO searchVO) throws Exception {
		return (GamCntnrRentMngtVO) selectByPk("gamCntnrRentMngtDao.selectGamCntnrRentMngtInfo_S", searchVO);
	}
}
