package egovframework.rte.ygpa.gam.oper.train.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentSttusInqireVO;

/**
 * @Class Name : GamTrainPortRentSttusInqireDao.java
 * @Description : 철송장임대현황조회 (항만시설/철송장/철송장임대현황조회)
 * @Modification Information
 *
 * @author 도명호
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamTrainPortRentSttusInqireDao")
public class GamTrainPortRentSttusInqireDao extends EgovAbstractDAO {
	
	/**
	 * 컨테이너부두임대현황을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대현황 목록
	 * @exception Exception
	 */
	public List selectGamTrainPortRentSttusInqireList(GamTrainPortRentSttusInqireVO searchVO) {
		 return list("gamTrainPortRentSttusInqireDao.selectGamTrainPortRentSttusInqireList_D", searchVO);
	}
	
	/**
	 * 컨테이너부두임대현황 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대현황 총 갯수
	 * @exception Exception
	 */
	public int selectGamTrainPortRentSttusInqireListTotCnt(GamTrainPortRentSttusInqireVO searchVO) {
		return  (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortRentSttusInqireDao.selectGamTrainPortRentSttusInqireListTotCnt_S", searchVO);
	}

	/**
	 * 컨테이너부두임대현황 정보 (자료수, 총면적, 총사용료 등)
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대현황 정보
	 * @exception Exception
	 */
	public GamTrainPortRentSttusInqireVO selectGamTrainPortRentSttusInqireInfo(GamTrainPortRentSttusInqireVO searchVO) throws Exception {
		return (GamTrainPortRentSttusInqireVO) selectByPk("gamTrainPortRentSttusInqireDao.selectGamTrainPortRentSttusInqireInfo_S", searchVO);
	}
}
