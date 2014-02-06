package egovframework.rte.ygpa.gam.oper.train.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentfeeInqireVO;

/**
 * @Class Name : GamTrainPortRentfeeInqireDao.java
 * @Description : 철송장임대료조회 (항만시설/철송장/철송장임대료조회)
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamTrainPortRentfeeInqireDao")
public class GamTrainPortRentfeeInqireDao extends EgovAbstractDAO {
	
	/**
	 * 컨테이너부두임대료관리를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대료관리 목록
	 * @exception Exception
	 */
	public List selectGamTrainPortRentfeeInqireList(GamTrainPortRentfeeInqireVO searchVO) {
		 return list("gamTrainPortRentfeeInqireDao.selectGamTrainPortRentfeeInqireList_D", searchVO);
	}
	
	/**
	 * 컨테이너부두임대료관리 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대료관리 총 갯수
	 * @exception Exception
	 */
	public int selectGamTrainPortRentfeeInqireListTotCnt(GamTrainPortRentfeeInqireVO searchVO) {
		return  (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortRentfeeInqireDao.selectGamTrainPortRentfeeInqireListTotCnt_S", searchVO);
	}

	/**
	 * 컨테이너부두임대료관리 정보 (자료수, 총면적, 총사용료 등)
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대료관리 정보
	 * @exception Exception
	 */
	public GamTrainPortRentfeeInqireVO selectGamTrainPortRentfeeInqireInfo(GamTrainPortRentfeeInqireVO searchVO) throws Exception {
		return (GamTrainPortRentfeeInqireVO) selectByPk("gamTrainPortRentfeeInqireDao.selectGamTrainPortRentfeeInqireInfo_S", searchVO);
	}
}
