package egovframework.rte.ygpa.gam.asset.rent.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeeMngtService;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeeMngtVO;


/**
 * @Class Name : GamAssetRentFeeMngtSeriveImpl.java
 * @Description : 자산임대료관리  Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamAssetRentFeeMngtService")
public class GamAssetRentFeeMngtServiceImpl  extends AbstractServiceImpl implements GamAssetRentFeeMngtService {

	@Resource(name="gamAssetRentFeeMngtDao")
    private GamAssetRentFeeMngtDao gamAssetRentFeeMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 자산임대료고지관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectAssetRentFeeMngtList(GamAssetRentFeeMngtVO searchVO) throws Exception {
        return gamAssetRentFeeMngtDao.selectAssetRentFeeMngtList(searchVO);
    }

    /**
	 * 자산임대료고지관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetRentFeeMngtListTotCnt(GamAssetRentFeeMngtVO searchVO) throws Exception {
		return gamAssetRentFeeMngtDao.selectAssetRentFeeMngtListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 연체, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대료고지관리목록
	 * @exception Exception
	 */
    public GamAssetRentFeeMngtVO selectAssetRentFeeMngtSum(GamAssetRentFeeMngtVO searchVO) throws Exception {
        return gamAssetRentFeeMngtDao.selectAssetRentFeeMngtSum(searchVO);
    }

    /**
	 * 자산임대료고지관리정보를 수정한다.
	 * @param vo GamAssetRentFeeMngtVO
	 * @exception Exception
	 */
	public void updateAssetRentFeeMngt(GamAssetRentFeeMngtVO vo) throws Exception {
		gamAssetRentFeeMngtDao.updateAssetRentFeeMngt(vo);
	}

	/**
	 * 사용료를 변경한다.
	 * @param vo
	 * @throws Exception
	 */
	public void updateAssetRentFee(GamAssetRentFeeMngtVO vo) throws Exception {
		BigDecimal nticAmt=new BigDecimal(vo.getFee());
		nticAmt=nticAmt.add(new BigDecimal(vo.getVat()));
		vo.setNticAmt(nticAmt.toString());

		gamAssetRentFeeMngtDao.updateAssetRentFee(vo);
	}

	/**
	 * 자산임대료고지관리 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대료고지관리정보
	 * @exception Exception
	 */
    public GamAssetRentFeeMngtVO selectAssetRentFeeMngtInfo(GamAssetRentFeeMngtVO searchVO) throws Exception {
        return gamAssetRentFeeMngtDao.selectAssetRentFeeMngtInfo(searchVO);
    }

    /**
	 * 세입징수 등록건수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAnlrveLevCnt(GamAssetRentFeeMngtVO searchVO) throws Exception {
		return gamAssetRentFeeMngtDao.selectAnlrveLevCnt(searchVO);
	}

    /**
	 * 세입징수를 등록한다.
	 * @param vo GamAssetRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertAnlrveLev(GamAssetRentFeeMngtVO vo) throws Exception {
		gamAssetRentFeeMngtDao.insertAnlrveLev(vo);
	}

	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamAssetRentFeeMngtVO
	 * @exception Exception
	 */
	public void deleteAssetRentFeeMngt(GamAssetRentFeeMngtVO vo) throws Exception {
		gamAssetRentFeeMngtDao.deleteAssetRentFeeMngt(vo);
	}

	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamAssetRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertAssetRentFeeMngtLevReqest(GamAssetRentFeeMngtVO vo) throws Exception {
		gamAssetRentFeeMngtDao.insertAssetRentFeeMngtLevReqest(vo);
	}

	@Override
	public Map selectNpticPrintInfo(GamAssetRentFeeMngtVO searchVO) throws Exception {
		return gamAssetRentFeeMngtDao.selectNpticPrintInfo(searchVO);
	}

	@Override
	public List selectTaxNtcPrintInfo(Map searchVO) throws Exception {
		return gamAssetRentFeeMngtDao.selectTaxNtcPrintInfo(searchVO);
	}

	@Override
	public void updateAssetRentFeeMngtListDetail(GamAssetRentFeeMngtVO vo)
			throws Exception {
		gamAssetRentFeeMngtDao.updateAssetRentFeeMngtListDetail(vo);
	}

	@Override
	public List selectAssetRentFeeDetailList(GamAssetRentFeeMngtVO searchVO) {
		return gamAssetRentFeeMngtDao.selectAssetRentFeeDetailList(searchVO);
	}

	@Override
	public Map selectAssetRentFeeDetailMstPk(GamAssetRentFeeMngtVO searchVO) {
		return gamAssetRentFeeMngtDao.selectAssetRentFeeDetailMstPk(searchVO);
	}

	@Override
	public Map selectAssetRentFeeDetailSumPk(GamAssetRentFeeMngtVO searchVO) {
		return gamAssetRentFeeMngtDao.selectAssetRentFeeDetailSumPk(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeeMngtService#selectNpticPrintInfo2(java.util.Map)
	 */
	@Override
	public List selectNpticPrintInfo2(Map searchVO) throws Exception {
		return gamAssetRentFeeMngtDao.selectNpticPrintInfo2(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.cmmn.fclty.service.GamNticRequestMngtService#updateNticPrintYn(java.util.Map)
	 */
//	@Override
//	public void updateNticPrintState(GamAssetRentFeeMngtVO vo) throws Exception {
//		Map map = gamAssetRentFeeMngtDao.selectNpticPrintInfo(vo);
//		if(vo.getNhtPrintYn().equals(map.get("nhtPrintYn"))) {
//			log.debug("이미 처리 된 자료 입니다.");
//			return;
//		}
//		gamAssetRentFeeMngtDao.updateLevReqestNhtPrintYn(vo);
//		if(map.get("arrrgNo")!=null && "".equals(map.get("arrrgNo"))) {
//			// 연체 고지
//			gamAssetRentFeeMngtDao.updateUnpaidBillPrintYn(map);
//		}
//		gamAssetRentFeeMngtDao.updateRevCollBillPrintYn(map);
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
