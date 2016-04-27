package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeeMngtVO;
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
	 * 투자비보전 상계처리한다. 2017.12.17 김종민
	 * @param vo
	 * @throws Exception
	 */
	public void updatePrtFcltyRentFeeSaveSingle(GamPrtFcltyRentFeeMngtVO vo) throws Exception {
		gamPrtFcltyRentFeeMngtDao.updatePrtFcltyRentFeeSaveSingle(vo);
	}


	/**
	 * 투자비보전 상계취소한다. 2017.12.17 김종민
	 * @param vo
	 * @throws Exception
	 */
	public void cancelPrtFcltyRentFeeSaveSingle(GamPrtFcltyRentFeeMngtVO vo) throws Exception {
		gamPrtFcltyRentFeeMngtDao.cancelPrtFcltyRentFeeSaveSingle(vo);
	}

	/**
	 * 사용료를 변경한다.
	 * @param vo
	 * @throws Exception
	 */
	public void updatePrtFcltyRentFee(GamPrtFcltyRentFeeMngtVO vo) throws Exception {
		BigDecimal nticAmt=new BigDecimal(vo.getFee());
		nticAmt=nticAmt.add(new BigDecimal(vo.getIntrAmnt()));
		nticAmt=nticAmt.add(new BigDecimal(vo.getVat()));
		vo.setNticAmt(nticAmt.toString());

		gamPrtFcltyRentFeeMngtDao.updatePrtFcltyRentFee(vo);
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

	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamPrtFcltyRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertPrtFcltyRentFeeMngtLevReqest(GamPrtFcltyRentFeeMngtVO vo) throws Exception {
		gamPrtFcltyRentFeeMngtDao.insertPrtFcltyRentFeeMngtLevReqest(vo);
	}

	@Override
	public List selectNpticPrintInfo(GamPrtFcltyRentFeeMngtVO searchVO) throws Exception {
		return gamPrtFcltyRentFeeMngtDao.selectNpticPrintInfo(searchVO);
	}

	@Override
	public List selectTaxNtcPrintInfo(Map searchVO) throws Exception {
		return gamPrtFcltyRentFeeMngtDao.selectTaxNtcPrintInfo(searchVO);
	}

	@Override
	public void updateAssetRentFeeMngtListDetail(GamPrtFcltyRentFeeMngtVO vo)
			throws Exception {
		gamPrtFcltyRentFeeMngtDao.updateAssetRentFeeMngtListDetail(vo);
	}

	@Override
	public List selectAssetRentFeeDetailList(GamPrtFcltyRentFeeMngtVO searchVO) {
		return gamPrtFcltyRentFeeMngtDao.selectAssetRentFeeDetailList(searchVO);
	}

	@Override
	public Map selectAssetRentFeeDetailMstPk(GamPrtFcltyRentFeeMngtVO searchVO) {
		return gamPrtFcltyRentFeeMngtDao.selectAssetRentFeeDetailMstPk(searchVO);
	}

	@Override
	public Map selectAssetRentFeeDetailSumPk(GamPrtFcltyRentFeeMngtVO searchVO) {
		return gamPrtFcltyRentFeeMngtDao.selectAssetRentFeeDetailSumPk(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeeMngtService#selectNpticPrintInfo2(java.util.Map)
	 */
	@Override
	public List selectNpticPrintInfo2(Map searchVO) throws Exception {
		return gamPrtFcltyRentFeeMngtDao.selectNpticPrintInfo2(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamNticRequestMngtService#updateNticPrintYn(java.util.Map)
	 */
//	@Override
//	public void updateNticPrintState(GamPrtFcltyRentFeeMngtVO vo) throws Exception {
//		Map map = gamPrtFcltyRentFeeMngtDao.selectNpticPrintInfo(vo);
//		if(vo.getNhtPrintYn().equals(map.get("nhtPrintYn"))) {
//			log.debug("이미 처리 된 자료 입니다.");
//			return;
//		}
//		gamPrtFcltyRentFeeMngtDao.updateLevReqestNhtPrintYn(vo);
//		if(map.get("arrrgNo")!=null && "".equals(map.get("arrrgNo"))) {
//			// 연체 고지
//			gamPrtFcltyRentFeeMngtDao.updateUnpaidBillPrintYn(map);
//		}
//		gamPrtFcltyRentFeeMngtDao.updateRevCollBillPrintYn(map);
//
//		// 2014-08-13 전자고지 출력
//		if("Y"==vo.getNhtPrintYn()) {
//	    	egiroPrint(map);
//		}
//		else {
//			egiroPrintCancel(map);
//		}
//	}
}
