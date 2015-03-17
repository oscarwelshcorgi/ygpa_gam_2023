package egovframework.rte.ygpa.gam.oper.shed.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentFeeMngtService;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentFeeMngtVO;

/**
 * @Class Name : GamCmmnCntrRentFeeMngtServiceImpl.java
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
@Service("gamCmmnCntrRentFeeMngtService")
public class GamCmmnCntrRentFeeMngtServiceImpl extends AbstractServiceImpl implements GamCmmnCntrRentFeeMngtService {

	@Resource(name="gamCmmnCntrRentFeeMngtDao")
    private GamCmmnCntrRentFeeMngtDao gamCmmnCntrRentFeeMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 자산임대료고지관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCmmnCntrRentFeeMngtList(GamCmmnCntrRentFeeMngtVO searchVO) throws Exception {
        return gamCmmnCntrRentFeeMngtDao.selectCmmnCntrRentFeeMngtList(searchVO);
    }

    /**
	 * 자산임대료고지관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCmmnCntrRentFeeMngtListTotCnt(GamCmmnCntrRentFeeMngtVO searchVO) throws Exception {
		return gamCmmnCntrRentFeeMngtDao.selectCmmnCntrRentFeeMngtListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 연체, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대료고지관리목록
	 * @exception Exception
	 */
    public GamCmmnCntrRentFeeMngtVO selectCmmnCntrRentFeeMngtSum(GamCmmnCntrRentFeeMngtVO searchVO) throws Exception {
        return gamCmmnCntrRentFeeMngtDao.selectCmmnCntrRentFeeMngtSum(searchVO);
    }

    /**
	 * 자산임대료고지관리정보를 수정한다.
	 * @param vo GamCmmnCntrRentFeeMngtVO
	 * @exception Exception
	 */
	public void updateCmmnCntrRentFeeMngt(GamCmmnCntrRentFeeMngtVO vo) throws Exception {
		gamCmmnCntrRentFeeMngtDao.updateCmmnCntrRentFeeMngt(vo);
	}

	/**
	 * 사용료를 변경한다.
	 * @param vo
	 * @throws Exception
	 */
	public void updateCmmnCntrRentFee(GamCmmnCntrRentFeeMngtVO vo) throws Exception {
		BigDecimal nticAmt=new BigDecimal(vo.getFee());
		nticAmt=nticAmt.add(new BigDecimal(vo.getVat()));
		vo.setNticAmt(nticAmt.toString());

		gamCmmnCntrRentFeeMngtDao.updateCmmnCntrRentFee(vo);
	}

	/**
	 * 자산임대료고지관리 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대료고지관리정보
	 * @exception Exception
	 */
    public GamCmmnCntrRentFeeMngtVO selectCmmnCntrRentFeeMngtInfo(GamCmmnCntrRentFeeMngtVO searchVO) throws Exception {
        return gamCmmnCntrRentFeeMngtDao.selectCmmnCntrRentFeeMngtInfo(searchVO);
    }

    /**
	 * 세입징수 등록건수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAnlrveLevCnt(GamCmmnCntrRentFeeMngtVO searchVO) throws Exception {
		return gamCmmnCntrRentFeeMngtDao.selectAnlrveLevCnt(searchVO);
	}

    /**
	 * 세입징수를 등록한다.
	 * @param vo GamCmmnCntrRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertAnlrveLev(GamCmmnCntrRentFeeMngtVO vo) throws Exception {
		gamCmmnCntrRentFeeMngtDao.insertAnlrveLev(vo);
	}

	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamCmmnCntrRentFeeMngtVO
	 * @exception Exception
	 */
	public void deleteCmmnCntrRentFeeMngt(GamCmmnCntrRentFeeMngtVO vo) throws Exception {
		gamCmmnCntrRentFeeMngtDao.deleteCmmnCntrRentFeeMngt(vo);
	}

	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamCmmnCntrRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertCmmnCntrRentFeeMngtLevReqest(GamCmmnCntrRentFeeMngtVO vo) throws Exception {
		gamCmmnCntrRentFeeMngtDao.insertCmmnCntrRentFeeMngtLevReqest(vo);
	}

	@Override
	public Map selectNpticPrintInfo(GamCmmnCntrRentFeeMngtVO searchVO) throws Exception {
		return gamCmmnCntrRentFeeMngtDao.selectNpticPrintInfo(searchVO);
	}

	@Override
	public List selectTaxNtcPrintInfo(Map searchVO) throws Exception {
		return gamCmmnCntrRentFeeMngtDao.selectTaxNtcPrintInfo(searchVO);
	}

	@Override
	public void updateAssetRentFeeMngtListDetail(GamCmmnCntrRentFeeMngtVO vo)
			throws Exception {
		gamCmmnCntrRentFeeMngtDao.updateAssetRentFeeMngtListDetail(vo);
	}

	@Override
	public List selectAssetRentFeeDetailList(GamCmmnCntrRentFeeMngtVO searchVO) {
		return gamCmmnCntrRentFeeMngtDao.selectAssetRentFeeDetailList(searchVO);
	}

	@Override
	public Map selectAssetRentFeeDetailMstPk(GamCmmnCntrRentFeeMngtVO searchVO) {
		return gamCmmnCntrRentFeeMngtDao.selectAssetRentFeeDetailMstPk(searchVO);
	}

	@Override
	public Map selectAssetRentFeeDetailSumPk(GamCmmnCntrRentFeeMngtVO searchVO) {
		return gamCmmnCntrRentFeeMngtDao.selectAssetRentFeeDetailSumPk(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamCmmnCntrRentFeeMngtService#selectNpticPrintInfo2(java.util.Map)
	 */
	@Override
	public List selectNpticPrintInfo2(Map searchVO) throws Exception {
		return gamCmmnCntrRentFeeMngtDao.selectNpticPrintInfo2(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamNticRequestMngtService#updateNticPrintYn(java.util.Map)
	 */
//	@Override
//	public void updateNticPrintState(GamCmmnCntrRentFeeMngtVO vo) throws Exception {
//		Map map = gamCmmnCntrRentFeeMngtDao.selectNpticPrintInfo(vo);
//		if(vo.getNhtPrintYn().equals(map.get("nhtPrintYn"))) {
//			log.debug("이미 처리 된 자료 입니다.");
//			return;
//		}
//		gamCmmnCntrRentFeeMngtDao.updateLevReqestNhtPrintYn(vo);
//		if(map.get("arrrgNo")!=null && "".equals(map.get("arrrgNo"))) {
//			// 연체 고지
//			gamCmmnCntrRentFeeMngtDao.updateUnpaidBillPrintYn(map);
//		}
//		gamCmmnCntrRentFeeMngtDao.updateRevCollBillPrintYn(map);
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
