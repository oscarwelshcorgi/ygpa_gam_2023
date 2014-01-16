package egovframework.rte.ygpa.gam.asset.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.ygpa.gam.asset.service.GamAssetRentDetailVO;
import egovframework.rte.ygpa.gam.asset.service.GamAssetRentService;
import egovframework.rte.ygpa.gam.asset.service.GamAssetRentVO;

/**
 * @Class Name : ErpAssetCdServiceImpl.java
 * @Description : ErpAssetCd Business Implement class
 * @Modification Information
 *
 * @author Dev
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Service("gamAssetRentService")

public class GamAssetRentServiceImpl implements GamAssetRentService {
	
	@Resource(name="gamAssetRentDao")
    private GamAssetRentDao gamAssetRentDao;

	/**
	 * 자산임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectAssetRentList(GamAssetRentVO searchVO) throws Exception {
        return gamAssetRentDao.selectAssetRentList(searchVO);
    }

    /**
	 * 자산임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetRentListTotCnt(GamAssetRentVO searchVO) throws Exception {
		return gamAssetRentDao.selectAssetRentListTotCnt(searchVO);
	}
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetRentVO selectAssetRentSum(GamAssetRentVO searchVO) throws Exception {
        return gamAssetRentDao.selectAssetRentSum(searchVO);
    }
    
    /**
	 * 자산임대 최초 신청을 등록한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void insertAssetRentFirst(GamAssetRentVO vo) throws Exception {
		gamAssetRentDao.insertAssetRentFirst(vo);
	}
	
    /**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetRentVO selectAssetRentMaxNo(GamAssetRentVO searchVO) throws Exception {
        return gamAssetRentDao.selectAssetRentMaxNo(searchVO);
    }
    
    /**
	 * 자산임대 연장 신청을 등록한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void insertAssetRentRenew(GamAssetRentVO vo) throws Exception {
		gamAssetRentDao.insertAssetRentRenew(vo);
	}
	
	/**
	 * 자산임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectAssetRentDetailList(GamAssetRentDetailVO vo) throws Exception {
        return gamAssetRentDao.selectAssetRentDetailList(vo);
    }

    /**
	 * 자산임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetRentDetailListTotCnt(GamAssetRentDetailVO vo) throws Exception {
		return gamAssetRentDao.selectAssetRentDetailListTotCnt(vo);
	}
	
	

}
