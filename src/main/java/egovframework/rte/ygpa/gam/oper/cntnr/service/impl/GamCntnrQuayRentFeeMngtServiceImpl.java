package egovframework.rte.ygpa.gam.oper.cntnr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentFeeMngtService;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentFeeMngtVO;

/**
 * @Class Name : GamCntnrQuayRentFeeMngtServiceImpl.java
 * @Description : 컨테이너부두임대료관리  Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Service("gamCntnrQuayRentFeeMngtService")
public class GamCntnrQuayRentFeeMngtServiceImpl extends AbstractServiceImpl implements GamCntnrQuayRentFeeMngtService {

	@Resource(name="gamCntnrQuayRentFeeMngtDao")
    private GamCntnrQuayRentFeeMngtDao gamCntnrQuayRentFeeMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 컨테이너부두임대고지관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCntnrQuayRentFeeMngtList(GamCntnrQuayRentFeeMngtVO searchVO) throws Exception {
        return gamCntnrQuayRentFeeMngtDao.selectCntnrQuayRentFeeMngtList(searchVO);
    }

    /**
	 * 컨테이너부두임대고지관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCntnrQuayRentFeeMngtListTotCnt(GamCntnrQuayRentFeeMngtVO searchVO) throws Exception {
		return gamCntnrQuayRentFeeMngtDao.selectCntnrQuayRentFeeMngtListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 연체, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대고지관리목록
	 * @exception Exception
	 */
    public GamCntnrQuayRentFeeMngtVO selectCntnrQuayRentFeeMngtSum(GamCntnrQuayRentFeeMngtVO searchVO) throws Exception {
        return gamCntnrQuayRentFeeMngtDao.selectCntnrQuayRentFeeMngtSum(searchVO);
    }

    /**
	 * 컨테이너부두임대고지관리정보를 수정한다.
	 * @param vo GamCntnrQuayRentFeeMngtVO
	 * @exception Exception
	 */
	public void updateCntnrQuayRentFeeMngt(GamCntnrQuayRentFeeMngtVO vo) throws Exception {
		gamCntnrQuayRentFeeMngtDao.updateCntnrQuayRentFeeMngt(vo);
	}
	
	/**
	 * 컨테이너부두임대고지관리 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대고지관리정보
	 * @exception Exception
	 */
    public GamCntnrQuayRentFeeMngtVO selectCntnrQuayRentFeeMngtInfo(GamCntnrQuayRentFeeMngtVO searchVO) throws Exception {
        return gamCntnrQuayRentFeeMngtDao.selectCntnrQuayRentFeeMngtInfo(searchVO);
    }
    
    /**
	 * 세입징수 등록건수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAnlrveLevCnt(GamCntnrQuayRentFeeMngtVO searchVO) throws Exception {
		return gamCntnrQuayRentFeeMngtDao.selectAnlrveLevCnt(searchVO);
	}
    
    /**
	 * 세입징수를 등록한다.
	 * @param vo GamCntnrQuayRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertAnlrveLev(GamCntnrQuayRentFeeMngtVO vo) throws Exception {
		gamCntnrQuayRentFeeMngtDao.insertAnlrveLev(vo);
	}
	
	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamCntnrQuayRentFeeMngtVO
	 * @exception Exception
	 */
	public void deleteCntnrQuayRentFeeMngt(GamCntnrQuayRentFeeMngtVO vo) throws Exception {
		gamCntnrQuayRentFeeMngtDao.deleteCntnrQuayRentFeeMngt(vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamCntnrQuayRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertCntnrQuayRentFeeMngtLevReqest(GamCntnrQuayRentFeeMngtVO vo) throws Exception {
		gamCntnrQuayRentFeeMngtDao.insertCntnrQuayRentFeeMngtLevReqest(vo);
	}
	
}
