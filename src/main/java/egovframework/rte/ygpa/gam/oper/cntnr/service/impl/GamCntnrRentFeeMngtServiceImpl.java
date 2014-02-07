package egovframework.rte.ygpa.gam.oper.cntnr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrRentFeeMngtService;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrRentFeeMngtVO;

/**
 * @Class Name : GamCntnrRentFeeMngtServiceImpl.java
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
@Service("gamCntnrRentFeeMngtService")
public class GamCntnrRentFeeMngtServiceImpl  extends AbstractServiceImpl implements GamCntnrRentFeeMngtService {

	@Resource(name="gamCntnrRentFeeMngtDao")
    private GamCntnrRentFeeMngtDao gamCntnrRentFeeMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 컨테이너부두임대료고지관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCntnrRentFeeMngtList(GamCntnrRentFeeMngtVO searchVO) throws Exception {
        return gamCntnrRentFeeMngtDao.selectCntnrRentFeeMngtList(searchVO);
    }

    /**
	 * 컨테이너부두임대료고지관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCntnrRentFeeMngtListTotCnt(GamCntnrRentFeeMngtVO searchVO) throws Exception {
		return gamCntnrRentFeeMngtDao.selectCntnrRentFeeMngtListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 연체, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대료고지관리목록
	 * @exception Exception
	 */
    public GamCntnrRentFeeMngtVO selectCntnrRentFeeMngtSum(GamCntnrRentFeeMngtVO searchVO) throws Exception {
        return gamCntnrRentFeeMngtDao.selectCntnrRentFeeMngtSum(searchVO);
    }

    /**
	 * 컨테이너부두임대료고지관리정보를 수정한다.
	 * @param vo GamCntnrRentFeeMngtVO
	 * @exception Exception
	 */
	public void updateCntnrRentFeeMngt(GamCntnrRentFeeMngtVO vo) throws Exception {
		gamCntnrRentFeeMngtDao.updateCntnrRentFeeMngt(vo);
	}
	
	/**
	 * 컨테이너부두임대료고지관리 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대료고지관리정보
	 * @exception Exception
	 */
    public GamCntnrRentFeeMngtVO selectCntnrRentFeeMngtInfo(GamCntnrRentFeeMngtVO searchVO) throws Exception {
        return gamCntnrRentFeeMngtDao.selectCntnrRentFeeMngtInfo(searchVO);
    }
    
    /**
	 * 세입징수 등록건수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAnlrveLevCnt(GamCntnrRentFeeMngtVO searchVO) throws Exception {
		return gamCntnrRentFeeMngtDao.selectAnlrveLevCnt(searchVO);
	}
    
    /**
	 * 세입징수를 등록한다.
	 * @param vo GamCntnrRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertAnlrveLev(GamCntnrRentFeeMngtVO vo) throws Exception {
		gamCntnrRentFeeMngtDao.insertAnlrveLev(vo);
	}
	
	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamCntnrRentFeeMngtVO
	 * @exception Exception
	 */
	public void deleteCntnrRentFeeMngt(GamCntnrRentFeeMngtVO vo) throws Exception {
		gamCntnrRentFeeMngtDao.deleteCntnrRentFeeMngt(vo);
	}
	
}
