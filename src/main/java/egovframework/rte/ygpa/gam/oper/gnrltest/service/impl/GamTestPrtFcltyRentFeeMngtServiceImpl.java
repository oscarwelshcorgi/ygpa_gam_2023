package egovframework.rte.ygpa.gam.oper.gnrltest.service.impl;

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
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyRentFeeMngtService;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyRentFeeMngtVO;

/**
 * @Class Name : GamTestPrtFcltyRentFeeMngtServiceImpl.java
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
@Service("gamTestPrtFcltyRentFeeMngtService")
public class GamTestPrtFcltyRentFeeMngtServiceImpl  extends AbstractServiceImpl implements GamTestPrtFcltyRentFeeMngtService {

	@Resource(name="gamTestPrtFcltyRentFeeMngtDao")
    private GamTestPrtFcltyRentFeeMngtDao gamTestPrtFcltyRentFeeMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 자산임대료고지관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyRentFeeMngtList(GamTestPrtFcltyRentFeeMngtVO searchVO) throws Exception {
        return gamTestPrtFcltyRentFeeMngtDao.selectPrtFcltyRentFeeMngtList(searchVO);
    }

    /**
	 * 자산임대료고지관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyRentFeeMngtListTotCnt(GamTestPrtFcltyRentFeeMngtVO searchVO) throws Exception {
		return gamTestPrtFcltyRentFeeMngtDao.selectPrtFcltyRentFeeMngtListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 연체, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대료고지관리목록
	 * @exception Exception
	 */
    public GamTestPrtFcltyRentFeeMngtVO selectPrtFcltyRentFeeMngtSum(GamTestPrtFcltyRentFeeMngtVO searchVO) throws Exception {
        return gamTestPrtFcltyRentFeeMngtDao.selectPrtFcltyRentFeeMngtSum(searchVO);
    }

    /**
	 * 자산임대료고지관리정보를 수정한다.
	 * @param vo GamTestPrtFcltyRentFeeMngtVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentFeeMngt(GamTestPrtFcltyRentFeeMngtVO vo) throws Exception {
		gamTestPrtFcltyRentFeeMngtDao.updatePrtFcltyRentFeeMngt(vo);
	}

	/**
	 * 투자비보전 상계처리한다. 2017.12.17 김종민
	 * @param vo
	 * @throws Exception
	 */
	public void updatePrtFcltyRentFeeSaveSingle(GamTestPrtFcltyRentFeeMngtVO vo) throws Exception {
		gamTestPrtFcltyRentFeeMngtDao.updatePrtFcltyRentFeeSaveSingle(vo);
	}


	/**
	 * 투자비보전 상계취소한다. 2017.12.17 김종민
	 * @param vo
	 * @throws Exception
	 */
	public void cancelPrtFcltyRentFeeSaveSingle(GamTestPrtFcltyRentFeeMngtVO vo) throws Exception {
		gamTestPrtFcltyRentFeeMngtDao.cancelPrtFcltyRentFeeSaveSingle(vo);
	}

	/**
	 * 사용료를 변경한다.
	 * @param vo
	 * @throws Exception
	 */
	public void updatePrtFcltyRentFee(GamTestPrtFcltyRentFeeMngtVO vo) throws Exception {
		BigDecimal nticAmt=new BigDecimal(vo.getFee());
		nticAmt=nticAmt.add(new BigDecimal(vo.getIntrAmnt()));
		nticAmt=nticAmt.add(new BigDecimal(vo.getVat()));
		vo.setNticAmt(nticAmt.toString());

		gamTestPrtFcltyRentFeeMngtDao.updatePrtFcltyRentFee(vo);
	}

	/**
	 * 자산임대료고지관리 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대료고지관리정보
	 * @exception Exception
	 */
    public GamTestPrtFcltyRentFeeMngtVO selectPrtFcltyRentFeeMngtInfo(GamTestPrtFcltyRentFeeMngtVO searchVO) throws Exception {
        return gamTestPrtFcltyRentFeeMngtDao.selectPrtFcltyRentFeeMngtInfo(searchVO);
    }

    /**
	 * 세입징수 등록건수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAnlrveLevCnt(GamTestPrtFcltyRentFeeMngtVO searchVO) throws Exception {
		return gamTestPrtFcltyRentFeeMngtDao.selectAnlrveLevCnt(searchVO);
	}

    /**
	 * 세입징수를 등록한다.
	 * @param vo GamTestPrtFcltyRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertAnlrveLev(GamTestPrtFcltyRentFeeMngtVO vo) throws Exception {
		gamTestPrtFcltyRentFeeMngtDao.insertAnlrveLev(vo);
	}

	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamTestPrtFcltyRentFeeMngtVO
	 * @exception Exception
	 */
	public void deletePrtFcltyRentFeeMngt(GamTestPrtFcltyRentFeeMngtVO vo) throws Exception {
		gamTestPrtFcltyRentFeeMngtDao.deletePrtFcltyRentFeeMngt(vo);
	}

	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamTestPrtFcltyRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertPrtFcltyRentFeeMngtLevReqest(GamTestPrtFcltyRentFeeMngtVO vo) throws Exception {
		gamTestPrtFcltyRentFeeMngtDao.insertPrtFcltyRentFeeMngtLevReqest(vo);
	}

	@Override
	public List selectNpticPrintInfo(GamTestPrtFcltyRentFeeMngtVO searchVO) throws Exception {
		return gamTestPrtFcltyRentFeeMngtDao.selectNpticPrintInfo(searchVO);
	}

	@Override
	public List selectTaxNtcPrintInfo(Map searchVO) throws Exception {
		return gamTestPrtFcltyRentFeeMngtDao.selectTaxNtcPrintInfo(searchVO);
	}

	@Override
	public void updateAssetRentFeeMngtListDetail(GamTestPrtFcltyRentFeeMngtVO vo)
			throws Exception {
		gamTestPrtFcltyRentFeeMngtDao.updateAssetRentFeeMngtListDetail(vo);
	}

	@Override
	public List selectAssetRentFeeDetailList(GamTestPrtFcltyRentFeeMngtVO searchVO) {
		return gamTestPrtFcltyRentFeeMngtDao.selectAssetRentFeeDetailList(searchVO);
	}

	@Override
	public Map selectAssetRentFeeDetailMstPk(GamTestPrtFcltyRentFeeMngtVO searchVO) {
		return gamTestPrtFcltyRentFeeMngtDao.selectAssetRentFeeDetailMstPk(searchVO);
	}

	@Override
	public Map selectAssetRentFeeDetailSumPk(GamTestPrtFcltyRentFeeMngtVO searchVO) {
		return gamTestPrtFcltyRentFeeMngtDao.selectAssetRentFeeDetailSumPk(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamTestPrtFcltyRentFeeMngtService#selectNpticPrintInfo2(java.util.Map)
	 */
	@Override
	public List selectNpticPrintInfo2(Map searchVO) throws Exception {
		return gamTestPrtFcltyRentFeeMngtDao.selectNpticPrintInfo2(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamNticRequestMngtService#updateNticPrintYn(java.util.Map)
	 */
//	@Override
//	public void updateNticPrintState(GamTestPrtFcltyRentFeeMngtVO vo) throws Exception {
//		Map map = gamTestPrtFcltyRentFeeMngtDao.selectNpticPrintInfo(vo);
//		if(vo.getNhtPrintYn().equals(map.get("nhtPrintYn"))) {
//			log.debug("이미 처리 된 자료 입니다.");
//			return;
//		}
//		gamTestPrtFcltyRentFeeMngtDao.updateLevReqestNhtPrintYn(vo);
//		if(map.get("arrrgNo")!=null && "".equals(map.get("arrrgNo"))) {
//			// 연체 고지
//			gamTestPrtFcltyRentFeeMngtDao.updateUnpaidBillPrintYn(map);
//		}
//		gamTestPrtFcltyRentFeeMngtDao.updateRevCollBillPrintYn(map);
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
