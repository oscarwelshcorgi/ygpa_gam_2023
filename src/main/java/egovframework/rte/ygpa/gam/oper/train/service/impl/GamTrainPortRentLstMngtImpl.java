package egovframework.rte.ygpa.gam.oper.train.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentLstMngtService;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentLstMngtVO;

/**
 * @Class Name : GamTrainPortRentLstMngtImpl.java
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
@Service("gamTrainPortRentLstMngtService")
public class GamTrainPortRentLstMngtImpl implements GamTrainPortRentLstMngtService {
	
    @Resource(name="gamTrainPortRentLstMngtDao")
    private GamTrainPortRentLstMngtDao gamTrainPortRentLstMngtDao;

    /**
	 * 컨테이너부두임대목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대 목록
	 * @exception Exception
	 */
    public List selectGamTrainPortRentLstMngtList(GamTrainPortRentLstMngtVO searchVO) throws Exception {
        return gamTrainPortRentLstMngtDao.selectGamTrainPortRentLstMngtList(searchVO);
    }

    /**
	 * 컨테이너부두임대목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록 총 갯수
	 * @exception Exception
	 */
    public int selectGamTrainPortRentLstMngtListTotCnt(GamTrainPortRentLstMngtVO searchVO) {
		return gamTrainPortRentLstMngtDao.selectGamTrainPortRentLstMngtListTotCnt(searchVO);
	}
    
    /**
	 * 컨테이너부두임대목록 정보 (자료수, 총면적, 총사용료 등)
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록 정보
	 * @exception Exception
	 */
    public GamTrainPortRentLstMngtVO selectGamTrainPortRentLstMngtInfo(GamTrainPortRentLstMngtVO searchVO) throws Exception {
        return gamTrainPortRentLstMngtDao.selectGamTrainPortRentLstMngtInfo(searchVO);
    }
}
