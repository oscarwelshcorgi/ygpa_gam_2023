package egovframework.rte.ygpa.gam.oper.center.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeeMngtVO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentFeeMngtService;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentFeeMngtVO;

/**
 * @Class Name : GamMarineCenterRentFeeMngtServiceImpl.java
 * @Description : 마린센터임대료관리 Business Implement class
 * @Modification Information
 *
 * @author heroin
 * @since 2014-01-10
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

	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamMarineCenterRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertAssetRentLevReqest(GamMarineCenterRentFeeMngtVO vo) throws Exception {
		gamMarineCenterRentFeeMngtDao.insertAssetRentLevReqest(vo);
	}

	@Override
	public List selectNpticPrintInfo(Map searchVO) throws Exception {
		return gamMarineCenterRentFeeMngtDao.selectNpticPrintInfo(searchVO);
	}

	@Override
	public List selectTaxNtcPrintInfo(Map searchVO) throws Exception {
		return gamMarineCenterRentFeeMngtDao.selectTaxNtcPrintInfo(searchVO);
	}

	@Override
	public void updateAssetRentFeeMngtListDetail(GamMarineCenterRentFeeMngtVO vo)
			throws Exception {
		gamMarineCenterRentFeeMngtDao.updateAssetRentFeeMngtListDetail(vo);
	}

	@Override
	public List selectAssetRentFeeDetailList(GamMarineCenterRentFeeMngtVO searchVO) {
		return gamMarineCenterRentFeeMngtDao.selectAssetRentFeeDetailList(searchVO);
	}

	@Override
	public Map selectAssetRentFeeDetailMstPk(GamMarineCenterRentFeeMngtVO searchVO) {
		return gamMarineCenterRentFeeMngtDao.selectAssetRentFeeDetailMstPk(searchVO);
	}

	@Override
	public Map selectAssetRentFeeDetailSumPk(GamMarineCenterRentFeeMngtVO searchVO) {
		return gamMarineCenterRentFeeMngtDao.selectAssetRentFeeDetailSumPk(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeeMngtService#selectMngFeeList(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeeMngtVO)
	 */
	@Override
	public Map selectAssetRentMngFeeData(GamMarineCenterRentFeeMngtVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return gamMarineCenterRentFeeMngtDao.selectAssetRentMngFeeData(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeeMngtService#selectAssetRentMngFeeVal(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeeMngtVO)
	 */
	@Override
	public Map selectAssetRentMngFeeVal(GamMarineCenterRentFeeMngtVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return gamMarineCenterRentFeeMngtDao.selectAssetRentMngFeeVal(searchVO);
	}

	public void updateAssetRentMngFee(GamMarineCenterRentFeeMngtVO vo) throws Exception {
		gamMarineCenterRentFeeMngtDao.deleteAssetRentMngFee(vo);
		gamMarineCenterRentFeeMngtDao.insertAssetRentMngFee(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentFeeMngtService#selectAssetRentMngFeeInfo(egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentFeeMngtVO)
	 */
	@Override
	public Map selectAssetRentMngFeeInfo(GamMarineCenterRentFeeMngtVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return gamMarineCenterRentFeeMngtDao.selectAssetRentMngFeeInfo(searchVO);
	}
}
