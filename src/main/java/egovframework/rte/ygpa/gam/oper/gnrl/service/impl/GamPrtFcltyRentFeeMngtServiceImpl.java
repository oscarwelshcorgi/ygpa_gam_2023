package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeeMngtService;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeeMngtVO;

/**
 * @Class Name : GamPrtFcltyRentFeeMngtServiceImpl.java
 * @Description : 항만시설사용료관리  Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Service("gamPrtFcltyRentFeeMngtService")
public class GamPrtFcltyRentFeeMngtServiceImpl  extends AbstractServiceImpl implements GamPrtFcltyRentFeeMngtService {

	@Resource(name="gamPrtFcltyRentFeeMngtDao")
    private GamPrtFcltyRentFeeMngtDao gamPrtFcltyRentFeeMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 자산임대료고지관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyRentFeeMngtList(GamPrtFcltyRentFeeMngtVO searchVO) throws Exception {
        return gamPrtFcltyRentFeeMngtDao.selectPrtFcltyRentFeeMngtList(searchVO);
    }

    /**
	 * 자산임대료고지관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyRentFeeMngtListTotCnt(GamPrtFcltyRentFeeMngtVO searchVO) throws Exception {
		return gamPrtFcltyRentFeeMngtDao.selectPrtFcltyRentFeeMngtListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 연체, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대료고지관리목록
	 * @exception Exception
	 */
    public GamPrtFcltyRentFeeMngtVO selectPrtFcltyRentFeeMngtSum(GamPrtFcltyRentFeeMngtVO searchVO) throws Exception {
        return gamPrtFcltyRentFeeMngtDao.selectPrtFcltyRentFeeMngtSum(searchVO);
    }

    /**
	 * 자산임대료고지관리정보를 수정한다.
	 * @param vo GamPrtFcltyRentFeeMngtVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentFeeMngt(GamPrtFcltyRentFeeMngtVO vo) throws Exception {
		gamPrtFcltyRentFeeMngtDao.updatePrtFcltyRentFeeMngt(vo);
	}
	
	/**
	 * 자산임대료고지관리 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대료고지관리정보
	 * @exception Exception
	 */
    public GamPrtFcltyRentFeeMngtVO selectPrtFcltyRentFeeMngtInfo(GamPrtFcltyRentFeeMngtVO searchVO) throws Exception {
        return gamPrtFcltyRentFeeMngtDao.selectPrtFcltyRentFeeMngtInfo(searchVO);
    }
    
    /**
	 * 세입징수 등록건수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAnlrveLevCnt(GamPrtFcltyRentFeeMngtVO searchVO) throws Exception {
		return gamPrtFcltyRentFeeMngtDao.selectAnlrveLevCnt(searchVO);
	}
    
    /**
	 * 세입징수를 등록한다.
	 * @param vo GamPrtFcltyRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertAnlrveLev(GamPrtFcltyRentFeeMngtVO vo) throws Exception {
		gamPrtFcltyRentFeeMngtDao.insertAnlrveLev(vo);
	}
	
	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamPrtFcltyRentFeeMngtVO
	 * @exception Exception
	 */
	public void deletePrtFcltyRentFeeMngt(GamPrtFcltyRentFeeMngtVO vo) throws Exception {
		gamPrtFcltyRentFeeMngtDao.deletePrtFcltyRentFeeMngt(vo);
	}
	
}
