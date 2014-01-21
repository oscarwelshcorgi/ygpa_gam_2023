package egovframework.rte.ygpa.gam.oper.train.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentLstMngtVO;

/**
 * @Class Name : GamTrainPortRentLstMngtDao.java
 * @Description : 철송장임대목록관리 (항만시설/철송장/철송장임대목록관리)
 * @Modification Information
 *
 * @author 도명호
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamTrainPortRentLstMngtDao")
public class GamTrainPortRentLstMngtDao extends EgovAbstractDAO {
	
	/**
	 * 컨테이너부두임대목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대 목록
	 * @exception Exception
	 */
	public List selectGamTrainPortRentLstMngtList(GamTrainPortRentLstMngtVO searchVO) {
		 return list("gamTrainPortRentLstMngtDao.selectGamTrainPortRentLstMngtList_D", searchVO);
	}
	
	/**
	 * 컨테이너부두임대목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록 총 갯수
	 * @exception Exception
	 */
	public int selectGamTrainPortRentLstMngtListTotCnt(GamTrainPortRentLstMngtVO searchVO) {
		return  (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortRentLstMngtDao.selectGamTrainPortRentLstMngtListTotCnt_S", searchVO);
	}

	/**
	 * 컨테이너부두임대목록 정보 (자료수, 총면적, 총사용료 등)
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록 정보
	 * @exception Exception
	 */
	public GamTrainPortRentLstMngtVO selectGamTrainPortRentLstMngtInfo(GamTrainPortRentLstMngtVO searchVO) throws Exception {
		return (GamTrainPortRentLstMngtVO) selectByPk("gamTrainPortRentLstMngtDao.selectGamTrainPortRentLstMngtInfo_S", searchVO);
	}
}
