package egovframework.rte.ygpa.gam.asset.rent.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentArrrgMngtVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamNticRequestMngtService;

/**
 * @Class Name : GamAssetRentFeePayDtlsMngtServiceImpl.java
 * @Description : 자산임대료납부관리 Business Implement class
 * @Modification Information
 *
 * @author heroin
 * @since 2014-02-05
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamAssetRentFeePayDtlsMngtService")

public class GamAssetRentFeePayDtlsMngtServiceImpl  extends AbstractServiceImpl implements GamAssetRentFeePayDtlsMngtService {

    public static final int TERM_FOR_PAYMENT15 = 15;


	@Resource(name="gamNticRequestMngtService")
    private GamNticRequestMngtService gamNticRequestMngtService;

	@Resource(name="gamAssetRentFeePayDtlsMngtDao")
    private GamAssetRentFeePayDtlsMngtDao gamAssetRentFeePayDtlsMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 자산임대료납부관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectAssetRentFeePayDtlsList(GamAssetRentFeePayDtlsMngtVO searchVO) throws Exception {
        return gamAssetRentFeePayDtlsMngtDao.selectAssetRentFeePayDtlsList(searchVO);
    }

    /**
	 * 자산임대료납부관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetRentFeePayDtlsListTotCnt(GamAssetRentFeePayDtlsMngtVO searchVO) throws Exception {
		return gamAssetRentFeePayDtlsMngtDao.selectAssetRentFeePayDtlsListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대료납부관리목록
	 * @exception Exception
	 */
    public GamAssetRentFeePayDtlsMngtVO selectAssetRentFeePayDtlsSum(GamAssetRentFeePayDtlsMngtVO searchVO) throws Exception {
        return gamAssetRentFeePayDtlsMngtDao.selectAssetRentFeePayDtlsSum(searchVO);
    }


    /**
	 * 고지금액합계, 수납금액합계
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    public GamAssetRentFeePayDtlsMngtVO selectAssetRentFeePayDtlsMngtSum(GamAssetRentFeePayDtlsMngtVO searchVO) throws Exception {
        return gamAssetRentFeePayDtlsMngtDao.selectAssetRentFeePayDtlsMngtSum(searchVO);
    }

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectNticArrrgList(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public List<?> selectNticArrrgList(GamAssetRentArrrgMngtVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return gamAssetRentFeePayDtlsMngtDao.selectNticArrrgList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectNticArrrgListTotCnt(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public int selectNticArrrgListTotCnt(GamAssetRentArrrgMngtVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return gamAssetRentFeePayDtlsMngtDao.selectNticArrrgListTotCnt(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#mergeErpGisAssetCodeMngt(java.util.Map)
	 */
	@Override
	public List mergeNticArrrgListMngt(Map mergeMap) throws Exception {

        ArrayList arraylistCU = (ArrayList)mergeMap.get("CU");
        HashMap[] hmCU = (HashMap[])arraylistCU.toArray(new HashMap[arraylistCU.size()]);
        Map result;

//        Integer photoSeq=1;

//		if(hmCU.length>0) photoSeq=gamErpGisAssetPhotoMngtDao.selectGamAssetPhotoMaxSeq(hmCU[0]);
        //수정처리 & 입력처리
        for (int i=0; i<hmCU.length; i++) {
        	if ("I".equals(hmCU[i].get("_updtId"))) {
//            	log.debug("#photoeq : "+photoSeq.toString());
            }
        	else if("U".equals(hmCU[i].get("_updtId"))){
        		// 연체 세입 선택 목록

        	}
            else {
            	log.debug("unknown RowStatus ["+i+"] : "+hmCU[i].get("_updtId"));
            }
        }



		// TODO Auto-generated method stub
		return gamAssetRentFeePayDtlsMngtDao.mergeNticArrrgList(mergeMap);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectAssetRentFeePayDtlsMngtDetailList(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public List selectAssetRentFeePayDtlsMngtDetailList(
			GamAssetRentFeePayDtlsMngtVO searchVO) throws Exception {
        return gamAssetRentFeePayDtlsMngtDao.selectAssetRentFeePayDtlsMngtDetailList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectAssetRentFeePayDtlsMngtDetailMstPk(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public EgovMap selectAssetRentFeePayDtlsMngtDetailMstPk(
			GamAssetRentFeePayDtlsMngtVO searchVO) throws Exception {
		return gamAssetRentFeePayDtlsMngtDao.selectAssetRentFeePayDtlsMngtDetailMstPk(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectAssetRentFeePayDtlsMngtDetailSumPk(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public EgovMap selectAssetRentFeePayDtlsMngtDetailSumPk(
			GamAssetRentFeePayDtlsMngtVO searchVO) throws Exception {
		return gamAssetRentFeePayDtlsMngtDao.selectAssetRentFeePayDtlsMngtDetailSumPk(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectNticArrrgDetail(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public Map selectNticArrrgDetail(GamAssetRentFeePayDtlsMngtVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return gamAssetRentFeePayDtlsMngtDao.selectNticArrrgDetail(searchVO);
	}

	@Override
	public List selectAssetRentFeePayDtlsMngtDlyList(GamAssetRentFeePayDtlsMngtVO searchVO) throws Exception {
        return gamAssetRentFeePayDtlsMngtDao.selectAssetRentFeePayDtlsMngtDlyList(searchVO);
	}


	/**change**
	 * 항만시설연체 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetRentFeePayDtlsMngtDlyListTotCnt(GamAssetRentFeePayDtlsMngtVO searchVO) throws Exception {
		return gamAssetRentFeePayDtlsMngtDao.selectAssetRentFeePayDtlsMngtDlyListTotCnt(searchVO);
	}

	/* (non-Javadoc)change**
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectAssetRentFeePayDtlsMngtDetailSumPk(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public EgovMap selectAssetRentFeePayDtlsMngtDlyListSum(GamAssetRentFeePayDtlsMngtVO searchVO) throws Exception {
		return gamAssetRentFeePayDtlsMngtDao.selectAssetRentFeePayDtlsMngtDlyListSum(searchVO);
	}

}
