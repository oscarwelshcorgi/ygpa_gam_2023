package egovframework.rte.ygpa.gam.oper.htld.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeeMngtVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeeMngtService;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeeMngtVO;

/**
 * @Class Name : GamHtldRentFeeMngtServiceImpl.java
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
@Service("gamHtldRentFeeMngtService")
public class GamHtldRentFeeMngtServiceImpl extends AbstractServiceImpl implements GamHtldRentFeeMngtService {

	@Resource(name="gamHtldRentFeeMngtDao")
    private GamHtldRentFeeMngtDao gamHtldRentFeeMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 배후단지임대고지관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectHtldRentFeeMngtList(GamHtldRentFeeMngtVO searchVO) throws Exception {
        return gamHtldRentFeeMngtDao.selectHtldRentFeeMngtList(searchVO);
    }

    /**
	 * 배후단지임대고지관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectHtldRentFeeMngtListTotCnt(GamHtldRentFeeMngtVO searchVO) throws Exception {
		return gamHtldRentFeeMngtDao.selectHtldRentFeeMngtListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 연체, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대고지관리목록
	 * @exception Exception
	 */
    public GamHtldRentFeeMngtVO selectHtldRentFeeMngtSum(GamHtldRentFeeMngtVO searchVO) throws Exception {
        return gamHtldRentFeeMngtDao.selectHtldRentFeeMngtSum(searchVO);
    }

    /**
	 * 배후단지임대고지관리정보를 수정한다.
	 * @param vo GamHtldRentFeeMngtVO
	 * @exception Exception
	 */
	public void updateHtldRentFeeMngt(GamHtldRentFeeMngtVO vo) throws Exception {
		gamHtldRentFeeMngtDao.updateHtldRentFeeMngt(vo);
	}
	
	/**
	 * 배후단지임대고지관리 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대고지관리정보
	 * @exception Exception
	 */
    public GamHtldRentFeeMngtVO selectHtldRentFeeMngtInfo(GamHtldRentFeeMngtVO searchVO) throws Exception {
        return gamHtldRentFeeMngtDao.selectHtldRentFeeMngtInfo(searchVO);
    }
    
    /**
	 * 세입징수 등록건수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAnlrveLevCnt(GamHtldRentFeeMngtVO searchVO) throws Exception {
		return gamHtldRentFeeMngtDao.selectAnlrveLevCnt(searchVO);
	}
    
    /**
	 * 세입징수를 등록한다.
	 * @param vo GamHtldRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertAnlrveLev(GamHtldRentFeeMngtVO vo) throws Exception {
		gamHtldRentFeeMngtDao.insertAnlrveLev(vo);
	}
	
	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamHtldRentFeeMngtVO
	 * @exception Exception
	 */
	public void deleteHtldRentFeeMngt(GamHtldRentFeeMngtVO vo) throws Exception {
		gamHtldRentFeeMngtDao.deleteHtldRentFeeMngt(vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamHtldRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertHtldRentFeeMngtLevReqest(GamHtldRentFeeMngtVO vo) throws Exception {
		gamHtldRentFeeMngtDao.insertHtldRentFeeMngtLevReqest(vo);
	}
	
	
	@Override
	public List selectNpticPrintInfo(Map searchVO) throws Exception {
		return gamHtldRentFeeMngtDao.selectNpticPrintInfo(searchVO);
	}
	
	@Override
	public List selectTaxNtcPrintInfo(Map searchVO) throws Exception {
		return gamHtldRentFeeMngtDao.selectTaxNtcPrintInfo(searchVO);
	}
	
	@Override
	public void updateAssetRentFeeMngtListDetail(GamHtldRentFeeMngtVO vo)
			throws Exception {
		gamHtldRentFeeMngtDao.updateAssetRentFeeMngtListDetail(vo);
	}
	
	@Override
	public List selectAssetRentFeeDetailList(GamHtldRentFeeMngtVO searchVO) {
		return gamHtldRentFeeMngtDao.selectAssetRentFeeDetailList(searchVO);
	}

	@Override
	public Map selectAssetRentFeeDetailMstPk(GamHtldRentFeeMngtVO searchVO) {
		return gamHtldRentFeeMngtDao.selectAssetRentFeeDetailMstPk(searchVO);
	}

	@Override
	public Map selectAssetRentFeeDetailSumPk(GamHtldRentFeeMngtVO searchVO) {
		return gamHtldRentFeeMngtDao.selectAssetRentFeeDetailSumPk(searchVO);
	}
}
