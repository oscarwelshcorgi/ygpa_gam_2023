package egovframework.rte.ygpa.gam.oper.train.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentFeeMngtService;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentFeeMngtVO;

/**
 * @Class Name : GamTrainPortRentFeeMngtServiceImpl.java
 * @Description : 철송장임대료관리  Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Service("gamTrainPortRentFeeMngtService")
public class GamTrainPortRentFeeMngtServiceImpl extends AbstractServiceImpl implements GamTrainPortRentFeeMngtService {

	@Resource(name="gamTrainPortRentFeeMngtDao")
    private GamTrainPortRentFeeMngtDao gamTrainPortRentFeeMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 철송장임대고지관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectTrainPortRentFeeMngtList(GamTrainPortRentFeeMngtVO searchVO) throws Exception {
        return gamTrainPortRentFeeMngtDao.selectTrainPortRentFeeMngtList(searchVO);
    }

    /**
	 * 철송장임대고지관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectTrainPortRentFeeMngtListTotCnt(GamTrainPortRentFeeMngtVO searchVO) throws Exception {
		return gamTrainPortRentFeeMngtDao.selectTrainPortRentFeeMngtListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 연체, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대고지관리목록
	 * @exception Exception
	 */
    public GamTrainPortRentFeeMngtVO selectTrainPortRentFeeMngtSum(GamTrainPortRentFeeMngtVO searchVO) throws Exception {
        return gamTrainPortRentFeeMngtDao.selectTrainPortRentFeeMngtSum(searchVO);
    }

    /**
	 * 철송장임대고지관리정보를 수정한다.
	 * @param vo GamTrainPortRentFeeMngtVO
	 * @exception Exception
	 */
	public void updateTrainPortRentFeeMngt(GamTrainPortRentFeeMngtVO vo) throws Exception {
		gamTrainPortRentFeeMngtDao.updateTrainPortRentFeeMngt(vo);
	}
	
	/**
	 * 철송장임대고지관리 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대고지관리정보
	 * @exception Exception
	 */
    public GamTrainPortRentFeeMngtVO selectTrainPortRentFeeMngtInfo(GamTrainPortRentFeeMngtVO searchVO) throws Exception {
        return gamTrainPortRentFeeMngtDao.selectTrainPortRentFeeMngtInfo(searchVO);
    }
    
    /**
	 * 세입징수 등록건수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAnlrveLevCnt(GamTrainPortRentFeeMngtVO searchVO) throws Exception {
		return gamTrainPortRentFeeMngtDao.selectAnlrveLevCnt(searchVO);
	}
    
    /**
	 * 세입징수를 등록한다.
	 * @param vo GamTrainPortRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertAnlrveLev(GamTrainPortRentFeeMngtVO vo) throws Exception {
		gamTrainPortRentFeeMngtDao.insertAnlrveLev(vo);
	}
	
	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamTrainPortRentFeeMngtVO
	 * @exception Exception
	 */
	public void deleteTrainPortRentFeeMngt(GamTrainPortRentFeeMngtVO vo) throws Exception {
		gamTrainPortRentFeeMngtDao.deleteTrainPortRentFeeMngt(vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamTrainPortRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertTrainPortRentFeeMngtLevReqest(GamTrainPortRentFeeMngtVO vo) throws Exception {
		gamTrainPortRentFeeMngtDao.insertTrainPortRentFeeMngtLevReqest(vo);
	}
	
}
