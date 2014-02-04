package egovframework.rte.ygpa.gam.asset.rent.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeeMngtService;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeeMngtVO;

/**
 * @Class Name : GamAssetRentFeeMngtServiceImpl.java
 * @Description : 자산임대료고지관리 Business Implement class
 * @Modification Information
 *
 * @author heroin
 * @since 2014-01-10
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamAssetRentFeeMngtService")

public class GamAssetRentFeeMngtServiceImpl  extends AbstractServiceImpl implements GamAssetRentFeeMngtService {

	@Resource(name="gamAssetRentFeeMngtDao")
    private GamAssetRentFeeMngtDao gamAssetRentFeeMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 자산임대료고지관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectAssetRentFeeList(GamAssetRentFeeMngtVO searchVO) throws Exception {
        return gamAssetRentFeeMngtDao.selectAssetRentFeeList(searchVO);
    }

    /**
	 * 자산임대료고지관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetRentFeeListTotCnt(GamAssetRentFeeMngtVO searchVO) throws Exception {
		return gamAssetRentFeeMngtDao.selectAssetRentFeeListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 연체, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대료고지관리목록
	 * @exception Exception
	 */
    public GamAssetRentFeeMngtVO selectAssetRentFeeSum(GamAssetRentFeeMngtVO searchVO) throws Exception {
        return gamAssetRentFeeMngtDao.selectAssetRentFeeSum(searchVO);
    }

    /**
	 * 자산임대료고지관리정보를 수정한다.
	 * @param vo GamAssetRentFeeMngtVO
	 * @exception Exception
	 */
	public void updateAssetRentFee(GamAssetRentFeeMngtVO vo) throws Exception {
		gamAssetRentFeeMngtDao.updateAssetRentFee(vo);
	}
	
	/**
	 * 자산임대료고지관리 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대료고지관리정보
	 * @exception Exception
	 */
    public GamAssetRentFeeMngtVO selectAssetRentFeeInfo(GamAssetRentFeeMngtVO searchVO) throws Exception {
        return gamAssetRentFeeMngtDao.selectAssetRentFeeInfo(searchVO);
    }
    
    /**
	 * 세입징수 등록건수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAnlrveLevCnt(GamAssetRentFeeMngtVO searchVO) throws Exception {
		return gamAssetRentFeeMngtDao.selectAnlrveLevCnt(searchVO);
	}
    
    /**
	 * 세입징수를 등록한다.
	 * @param vo GamAssetRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertAnlrveLev(GamAssetRentFeeMngtVO vo) throws Exception {
		gamAssetRentFeeMngtDao.insertAnlrveLev(vo);
	}
	
	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamAssetRentFeeMngtVO
	 * @exception Exception
	 */
	public void deleteAssetRentFee(GamAssetRentFeeMngtVO vo) throws Exception {
		gamAssetRentFeeMngtDao.deleteAssetRentFee(vo);
	}
	
}
