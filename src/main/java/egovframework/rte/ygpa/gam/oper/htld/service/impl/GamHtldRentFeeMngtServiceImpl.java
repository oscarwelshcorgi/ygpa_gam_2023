package egovframework.rte.ygpa.gam.oper.htld.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFFeeMngtService;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFFeeMngtVO;

/**
 * @Class Name : GamHtldRentFFeeMngtServiceImpl.java
 * @Description : 배후단지임대료관리  Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Service("gamHtldRentFFeeMngtService")
public class GamHtldRentFFeeMngtServiceImpl  extends AbstractServiceImpl implements GamHtldRentFFeeMngtService {

	@Resource(name="gamHtldRentFFeeMngtDao")
    private GamHtldRentFFeeMngtDao gamHtldRentFFeeMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 배후단지임대료고지관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectHtldRentFFeeMngtList(GamHtldRentFFeeMngtVO searchVO) throws Exception {
        return gamHtldRentFFeeMngtDao.selectHtldRentFFeeMngtList(searchVO);
    }

    /**
	 * 배후단지임대료고지관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectHtldRentFFeeMngtListTotCnt(GamHtldRentFFeeMngtVO searchVO) throws Exception {
		return gamHtldRentFFeeMngtDao.selectHtldRentFFeeMngtListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 연체, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대료고지관리목록
	 * @exception Exception
	 */
    public GamHtldRentFFeeMngtVO selectHtldRentFFeeMngtSum(GamHtldRentFFeeMngtVO searchVO) throws Exception {
        return gamHtldRentFFeeMngtDao.selectHtldRentFFeeMngtSum(searchVO);
    }

    /**
	 * 배후단지임대료고지관리정보를 수정한다.
	 * @param vo GamHtldRentFFeeMngtVO
	 * @exception Exception
	 */
	public void updateHtldRentFFeeMngt(GamHtldRentFFeeMngtVO vo) throws Exception {
		gamHtldRentFFeeMngtDao.updateHtldRentFFeeMngt(vo);
	}
	
	/**
	 * 배후단지임대료고지관리 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대료고지관리정보
	 * @exception Exception
	 */
    public GamHtldRentFFeeMngtVO selectHtldRentFFeeMngtInfo(GamHtldRentFFeeMngtVO searchVO) throws Exception {
        return gamHtldRentFFeeMngtDao.selectHtldRentFFeeMngtInfo(searchVO);
    }
    
    /**
	 * 세입징수 등록건수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAnlrveLevCnt(GamHtldRentFFeeMngtVO searchVO) throws Exception {
		return gamHtldRentFFeeMngtDao.selectAnlrveLevCnt(searchVO);
	}
    
    /**
	 * 세입징수를 등록한다.
	 * @param vo GamHtldRentFFeeMngtVO
	 * @exception Exception
	 */
	public void insertAnlrveLev(GamHtldRentFFeeMngtVO vo) throws Exception {
		gamHtldRentFFeeMngtDao.insertAnlrveLev(vo);
	}
	
	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamHtldRentFFeeMngtVO
	 * @exception Exception
	 */
	public void deleteHtldRentFFeeMngt(GamHtldRentFFeeMngtVO vo) throws Exception {
		gamHtldRentFFeeMngtDao.deleteHtldRentFFeeMngt(vo);
	}
	
}
