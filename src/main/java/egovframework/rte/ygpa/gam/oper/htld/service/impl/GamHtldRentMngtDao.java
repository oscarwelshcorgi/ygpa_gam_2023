package egovframework.rte.ygpa.gam.oper.htld.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtVO;

/**
 * @Class Name : GamHtldRentMngtDao.java
 * @Description : 배후단지임대목록관리 (항만시설/배후단지/배후단지임대목록관리)
 * @Modification Information
 *
 * @author 도명호
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamHtldRentMngtDao")
public class GamHtldRentMngtDao extends EgovAbstractDAO {
	
	/**
	 * 배후단지임대목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대 목록
	 * @exception Exception
	 */
	public List selectGamHtldRentMngtList(GamHtldRentMngtVO searchVO) {
		 return list("gamHtldRentMngtDao.selectGamHtldRentMngtList_D", searchVO);
	}
	
	/**
	 * 배후단지임대목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록 총 갯수
	 * @exception Exception
	 */
	public int selectGamHtldRentMngtListTotCnt(GamHtldRentMngtVO searchVO) {
		return  (Integer)getSqlMapClientTemplate().queryForObject("gamHtldRentMngtDao.selectGamHtldRentMngtListTotCnt_S", searchVO);
	}

	/**
	 * 배후단지임대목록 정보 (자료수, 총면적, 총사용료 등)
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록 정보
	 * @exception Exception
	 */
	public GamHtldRentMngtVO selectGamHtldRentMngtInfo(GamHtldRentMngtVO searchVO) throws Exception {
		return (GamHtldRentMngtVO) selectByPk("gamHtldRentMngtDao.selectGamHtldRentMngtInfo_S", searchVO);
	}
}
