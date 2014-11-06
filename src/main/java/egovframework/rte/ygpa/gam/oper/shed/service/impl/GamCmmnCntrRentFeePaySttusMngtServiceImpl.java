package egovframework.rte.ygpa.gam.oper.shed.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamNticRequestMngtService;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentFeePaySttusMngtService;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentFeePaySttusMngtVO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentArrrgMngtVO;

/**
 * @Class Name : GamCmmnCntrRentFeePaySttusMngtServiceImpl.java
 * @Description : 공컨장치장납부현황관리 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-02-05
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamCmmnCntrRentFeePaySttusMngtService")

public class GamCmmnCntrRentFeePaySttusMngtServiceImpl  extends AbstractServiceImpl implements GamCmmnCntrRentFeePaySttusMngtService {
	@Resource(name="gamNticRequestMngtService")
    private GamNticRequestMngtService gamNticRequestMngtService;

	@Resource(name="gamCmmnCntrRentFeePaySttusMngtDao")
    private GamCmmnCntrRentFeePaySttusMngtDao gamCmmnCntrRentFeePaySttusMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 자산임대료납부관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCmmnCntrRentFeePayDtlsList(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePayDtlsList(searchVO);
    }

    /**
	 * 자산임대료납부관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCmmnCntrRentFeePayDtlsListTotCnt(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePayDtlsListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대료납부관리목록
	 * @exception Exception
	 */
    public GamCmmnCntrRentFeePaySttusMngtVO selectCmmnCntrRentFeePayDtlsSum(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePayDtlsSum(searchVO);
    }


    /**
	 * 고지금액합계, 수납금액합계
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    public GamCmmnCntrRentFeePaySttusMngtVO selectCmmnCntrRentFeePayDtlsMngtSum(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePayDtlsMngtSum(searchVO);
    }

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamCmmnCntrRentFeePayDtlsMngtService#selectNticArrrgList(egovframework.rte.ygpa.gam.asset.rent.service.GamCmmnCntrRentFeePaySttusMngtVO)
	 */
	@Override
	public List<?> selectNticArrrgList(GamCmmnCntrRentArrrgMngtVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return gamCmmnCntrRentFeePaySttusMngtDao.selectNticArrrgList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamCmmnCntrRentFeePayDtlsMngtService#selectNticArrrgListTotCnt(egovframework.rte.ygpa.gam.asset.rent.service.GamCmmnCntrRentFeePaySttusMngtVO)
	 */
	@Override
	public int selectNticArrrgListTotCnt(GamCmmnCntrRentArrrgMngtVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return gamCmmnCntrRentFeePaySttusMngtDao.selectNticArrrgListTotCnt(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamCmmnCntrRentFeePayDtlsMngtService#mergeErpGisAssetCodeMngt(java.util.Map)
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
		return gamCmmnCntrRentFeePaySttusMngtDao.mergeNticArrrgList(mergeMap);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamCmmnCntrRentFeePayDtlsMngtService#selectCmmnCntrRentFeePayDtlsMngtDetailList(egovframework.rte.ygpa.gam.asset.rent.service.GamCmmnCntrRentFeePaySttusMngtVO)
	 */
	@Override
	public List selectCmmnCntrRentFeePayDtlsMngtDetailList(
			GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePayDtlsMngtDetailList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamCmmnCntrRentFeePayDtlsMngtService#selectCmmnCntrRentFeePayDtlsMngtDetailMstPk(egovframework.rte.ygpa.gam.asset.rent.service.GamCmmnCntrRentFeePaySttusMngtVO)
	 */
	@Override
	public EgovMap selectCmmnCntrRentFeePayDtlsMngtDetailMstPk(
			GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePayDtlsMngtDetailMstPk(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamCmmnCntrRentFeePayDtlsMngtService#selectCmmnCntrRentFeePayDtlsMngtDetailSumPk(egovframework.rte.ygpa.gam.asset.rent.service.GamCmmnCntrRentFeePaySttusMngtVO)
	 */
	@Override
	public EgovMap selectCmmnCntrRentFeePayDtlsMngtDetailSumPk(
			GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePayDtlsMngtDetailSumPk(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamCmmnCntrRentFeePayDtlsMngtService#selectNticArrrgDetail(egovframework.rte.ygpa.gam.asset.rent.service.GamCmmnCntrRentFeePaySttusMngtVO)
	 */
	@Override
	public Map selectNticArrrgDetail(GamCmmnCntrRentFeePaySttusMngtVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return gamCmmnCntrRentFeePaySttusMngtDao.selectNticArrrgDetail(searchVO);
	}


	/* (non-Javadoc)change**
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectAssetRentFeePayDtlsMngtDetailList(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public List selectCmmnCntrRentFeePaySttusMngtDlyList(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePaySttusMngtDlyList(searchVO);
	}


	/**change**
	 * 항만시설연체 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCmmnCntrRentFeePaySttusMngtDlyListTotCnt(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePaySttusMngtDlyListTotCnt(searchVO);
	}

	/* (non-Javadoc)change**
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectAssetRentFeePayDtlsMngtDetailSumPk(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public EgovMap selectCmmnCntrRentFeePaySttusMngtDlyListSum(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePaySttusMngtDlyListSum(searchVO);
	}

}
