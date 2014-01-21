package egovframework.rte.ygpa.gam.oper.train.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentSttusInqireService;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentSttusInqireVO;

/**
 * @Class Name : GamTrainPortRentSttusInqireServiceImpl.java
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
@Service("gamTrainPortRentSttusInqireService")
public class GamTrainPortRentSttusInqireImpl implements GamTrainPortRentSttusInqireService {
	
    @Resource(name="gamTrainPortRentSttusInqireDao")
    private GamTrainPortRentSttusInqireDao gamTrainPortRentSttusInqireDao;

    /**
	 * 컨테이너부두임대현황을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대현황 목록
	 * @exception Exception
	 */
    public List selectGamTrainPortRentSttusInqireList(GamTrainPortRentSttusInqireVO searchVO) throws Exception {
        return gamTrainPortRentSttusInqireDao.selectGamTrainPortRentSttusInqireList(searchVO);
    }

    /**
	 * 컨테이너부두임대현황 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대현황 총 갯수
	 * @exception Exception
	 */
    public int selectGamTrainPortRentSttusInqireListTotCnt(GamTrainPortRentSttusInqireVO searchVO) {
		return gamTrainPortRentSttusInqireDao.selectGamTrainPortRentSttusInqireListTotCnt(searchVO);
	}
    
    /**
	 * 컨테이너부두임대현황 정보 (자료수, 총면적, 총사용료 등)
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대현황 정보
	 * @exception Exception
	 */
    public GamTrainPortRentSttusInqireVO selectGamTrainPortRentSttusInqireInfo(GamTrainPortRentSttusInqireVO searchVO) throws Exception {
        return gamTrainPortRentSttusInqireDao.selectGamTrainPortRentSttusInqireInfo(searchVO);
    }
}
