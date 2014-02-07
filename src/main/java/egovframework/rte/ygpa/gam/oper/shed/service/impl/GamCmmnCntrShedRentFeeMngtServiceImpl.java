package egovframework.rte.ygpa.gam.oper.shed.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrShedRentFeeMngtService;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrShedRentFeeMngtVO;

/**
 * @Class Name : GamCmmnCntrShedRentFeeMngtServiceImpl.java
 * @Description : 공컨장치장임대료관리  Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Service("gamCmmnCntrShedRentFeeMngtService")
public class GamCmmnCntrShedRentFeeMngtServiceImpl  extends AbstractServiceImpl implements GamCmmnCntrShedRentFeeMngtService {

	@Resource(name="gamCmmnCntrShedRentFeeMngtDao")
    private GamCmmnCntrShedRentFeeMngtDao gamCmmnCntrShedRentFeeMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 공컨장치장임대료고지관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCmmnCntrShedRentFeeMngtList(GamCmmnCntrShedRentFeeMngtVO searchVO) throws Exception {
        return gamCmmnCntrShedRentFeeMngtDao.selectCmmnCntrShedRentFeeMngtList(searchVO);
    }

    /**
	 * 공컨장치장임대료고지관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCmmnCntrShedRentFeeMngtListTotCnt(GamCmmnCntrShedRentFeeMngtVO searchVO) throws Exception {
		return gamCmmnCntrShedRentFeeMngtDao.selectCmmnCntrShedRentFeeMngtListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 연체, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대료고지관리목록
	 * @exception Exception
	 */
    public GamCmmnCntrShedRentFeeMngtVO selectCmmnCntrShedRentFeeMngtSum(GamCmmnCntrShedRentFeeMngtVO searchVO) throws Exception {
        return gamCmmnCntrShedRentFeeMngtDao.selectCmmnCntrShedRentFeeMngtSum(searchVO);
    }

    /**
	 * 공컨장치장임대료고지관리정보를 수정한다.
	 * @param vo GamCmmnCntrShedRentFeeMngtVO
	 * @exception Exception
	 */
	public void updateCmmnCntrShedRentFeeMngt(GamCmmnCntrShedRentFeeMngtVO vo) throws Exception {
		gamCmmnCntrShedRentFeeMngtDao.updateCmmnCntrShedRentFeeMngt(vo);
	}
	
	/**
	 * 공컨장치장임대료고지관리 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대료고지관리정보
	 * @exception Exception
	 */
    public GamCmmnCntrShedRentFeeMngtVO selectCmmnCntrShedRentFeeMngtInfo(GamCmmnCntrShedRentFeeMngtVO searchVO) throws Exception {
        return gamCmmnCntrShedRentFeeMngtDao.selectCmmnCntrShedRentFeeMngtInfo(searchVO);
    }
    
    /**
	 * 세입징수 등록건수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAnlrveLevCnt(GamCmmnCntrShedRentFeeMngtVO searchVO) throws Exception {
		return gamCmmnCntrShedRentFeeMngtDao.selectAnlrveLevCnt(searchVO);
	}
    
    /**
	 * 세입징수를 등록한다.
	 * @param vo GamCmmnCntrShedRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertAnlrveLev(GamCmmnCntrShedRentFeeMngtVO vo) throws Exception {
		gamCmmnCntrShedRentFeeMngtDao.insertAnlrveLev(vo);
	}
	
	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamCmmnCntrShedRentFeeMngtVO
	 * @exception Exception
	 */
	public void deleteCmmnCntrShedRentFeeMngt(GamCmmnCntrShedRentFeeMngtVO vo) throws Exception {
		gamCmmnCntrShedRentFeeMngtDao.deleteCmmnCntrShedRentFeeMngt(vo);
	}
	
}
