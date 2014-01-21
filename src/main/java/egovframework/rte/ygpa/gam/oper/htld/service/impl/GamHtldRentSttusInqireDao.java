package egovframework.rte.ygpa.gam.oper.htld.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentSttusInqireVO;

/**
 * @Class Name : GamHtldRentSttusInqireDao.java
 * @Description : 배후단지임대현황조회 (항만시설/배후단지/배후단지임대현황조회)
 * @Modification Information
 *
 * @author 도명호
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamHtldRentSttusInqireDao")
public class GamHtldRentSttusInqireDao extends EgovAbstractDAO {
	
	/**
	 * 배후단지임대현황을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대현황 목록
	 * @exception Exception
	 */
	public List selectGamHtldRentSttusInqireList(GamHtldRentSttusInqireVO searchVO) {
		 return list("gamHtldRentSttusInqireDao.selectGamHtldRentSttusInqireList_D", searchVO);
	}
	
	/**
	 * 배후단지임대현황 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대현황 총 갯수
	 * @exception Exception
	 */
	public int selectGamHtldRentSttusInqireListTotCnt(GamHtldRentSttusInqireVO searchVO) {
		return  (Integer)getSqlMapClientTemplate().queryForObject("gamHtldRentSttusInqireDao.selectGamHtldRentSttusInqireListTotCnt_S", searchVO);
	}

	/**
	 * 배후단지임대현황 정보 (자료수, 총면적, 총사용료 등)
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대현황 정보
	 * @exception Exception
	 */
	public GamHtldRentSttusInqireVO selectGamHtldRentSttusInqireInfo(GamHtldRentSttusInqireVO searchVO) throws Exception {
		return (GamHtldRentSttusInqireVO) selectByPk("gamHtldRentSttusInqireDao.selectGamHtldRentSttusInqireInfo_S", searchVO);
	}
}
