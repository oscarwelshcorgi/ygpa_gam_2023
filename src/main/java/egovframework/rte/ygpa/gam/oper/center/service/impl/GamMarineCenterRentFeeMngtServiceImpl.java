package egovframework.rte.ygpa.gam.oper.center.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentFeeMngtService;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentFeeMngtVO;

/**
 * @Class Name : GamMarineCenterRentFeeMngtServiceImpl.java
 * @Description : 마린센터임대료관리 Business Implement class
 * @Modification Information
 *
 * @author heroin
 * @since 2014-02-11
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamMarineCenterRentFeeMngtService")

public class GamMarineCenterRentFeeMngtServiceImpl  extends AbstractServiceImpl implements GamMarineCenterRentFeeMngtService {

	@Resource(name="gamMarineCenterRentFeeMngtDao")
    private GamMarineCenterRentFeeMngtDao gamMarineCenterRentFeeMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 마린센터임대료관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectMarineCenterRentFeeList(GamMarineCenterRentFeeMngtVO searchVO) throws Exception {
        return gamMarineCenterRentFeeMngtDao.selectMarineCenterRentFeeList(searchVO);
    }

    /**
	 * 마린센터임대료관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectMarineCenterRentFeeListTotCnt(GamMarineCenterRentFeeMngtVO searchVO) throws Exception {
		return gamMarineCenterRentFeeMngtDao.selectMarineCenterRentFeeListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 연체, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대료관리목록
	 * @exception Exception
	 */
    public GamMarineCenterRentFeeMngtVO selectMarineCenterRentFeeSum(GamMarineCenterRentFeeMngtVO searchVO) throws Exception {
        return gamMarineCenterRentFeeMngtDao.selectMarineCenterRentFeeSum(searchVO);
    }

    /**
	 * 마린센터임대료관리정보를 수정한다.
	 * @param vo GamMarineCenterRentFeeMngtVO
	 * @exception Exception
	 */
	public void updateMarineCenterRentFee(GamMarineCenterRentFeeMngtVO vo) throws Exception {
		gamMarineCenterRentFeeMngtDao.updateMarineCenterRentFee(vo);
	}
	
	/**
	 * 마린센터임대료관리 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대료관리정보
	 * @exception Exception
	 */
    public GamMarineCenterRentFeeMngtVO selectMarineCenterRentFeeInfo(GamMarineCenterRentFeeMngtVO searchVO) throws Exception {
        return gamMarineCenterRentFeeMngtDao.selectMarineCenterRentFeeInfo(searchVO);
    }
    
    /**
	 * 세입징수 등록건수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAnlrveLevCnt(GamMarineCenterRentFeeMngtVO searchVO) throws Exception {
		return gamMarineCenterRentFeeMngtDao.selectAnlrveLevCnt(searchVO);
	}
    
    /**
	 * 세입징수를 등록한다.
	 * @param vo GamMarineCenterRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertAnlrveLev(GamMarineCenterRentFeeMngtVO vo) throws Exception {
		gamMarineCenterRentFeeMngtDao.insertAnlrveLev(vo);
	}
	
	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamMarineCenterRentFeeMngtVO
	 * @exception Exception
	 */
	public void deleteMarineCenterRentFee(GamMarineCenterRentFeeMngtVO vo) throws Exception {
		gamMarineCenterRentFeeMngtDao.deleteMarineCenterRentFee(vo);
	}
	
}
