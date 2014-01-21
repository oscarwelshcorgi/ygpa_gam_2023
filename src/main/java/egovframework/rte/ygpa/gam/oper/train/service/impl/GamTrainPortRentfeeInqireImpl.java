package egovframework.rte.ygpa.gam.oper.train.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentfeeInqireService;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentfeeInqireVO;

/**
 * @Class Name : GamTrainPortRentfeeInqireServiceImpl.java
 * @Description : 철송장임대료조회 (항만시설/철송장/철송장임대료조회)
 * @Modification Information
 *
 * @author 도명호
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Service("gamTrainPortRentfeeInqireService")
public class GamTrainPortRentfeeInqireImpl implements GamTrainPortRentfeeInqireService {
	
    @Resource(name="gamTrainPortRentfeeInqireDao")
    private GamTrainPortRentfeeInqireDao gamTrainPortRentfeeInqireDao;

    /**
	 * 컨테이너부두임대료관리를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대료관리 목록
	 * @exception Exception
	 */
    public List selectGamTrainPortRentfeeInqireList(GamTrainPortRentfeeInqireVO searchVO) throws Exception {
        return gamTrainPortRentfeeInqireDao.selectGamTrainPortRentfeeInqireList(searchVO);
    }

    /**
	 * 컨테이너부두임대료관리 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대료관리 총 갯수
	 * @exception Exception
	 */
    public int selectGamTrainPortRentfeeInqireListTotCnt(GamTrainPortRentfeeInqireVO searchVO) {
		return gamTrainPortRentfeeInqireDao.selectGamTrainPortRentfeeInqireListTotCnt(searchVO);
	}
    
    /**
	 * 컨테이너부두임대료관리 정보 (자료수, 총면적, 총사용료 등)
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대료관리정보
	 * @exception Exception
	 */
    public GamTrainPortRentfeeInqireVO selectGamTrainPortRentfeeInqireInfo(GamTrainPortRentfeeInqireVO searchVO) throws Exception {
        return gamTrainPortRentfeeInqireDao.selectGamTrainPortRentfeeInqireInfo(searchVO);
    }
}
