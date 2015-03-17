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
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamFcltyRentArrrgMngtVO;

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
	 * 공컨장치장임대납부현황관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCmmnCntrRentFeePaySttusMngtList(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePaySttusMngtList(searchVO);
    }

    /**
	 * 공컨장치장임대납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCmmnCntrRentFeePaySttusMngtListTotCnt(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePaySttusMngtListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 공컨장치장임대납부현황관리목록
	 * @exception Exception
	 */
    public GamCmmnCntrRentFeePaySttusMngtVO selectCmmnCntrRentFeePaySttusSum(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePaySttusSum(searchVO);
    }





    /**
	 * 고지금액합계, 수납금액합계
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    public GamCmmnCntrRentFeePaySttusMngtVO selectCmmnCntrRentFeePaySttusMngtSum(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePaySttusMngtSum(searchVO);
    }

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectNticArrrgList(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public List<?> selectNticArrrgList(GamFcltyRentArrrgMngtVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamCmmnCntrRentFeePaySttusMngtDao.selectNticArrrgList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectNticArrrgListTotCnt(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public int selectNticArrrgListTotCnt(GamFcltyRentArrrgMngtVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamCmmnCntrRentFeePaySttusMngtDao.selectNticArrrgListTotCnt(searchVO);
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
		return gamCmmnCntrRentFeePaySttusMngtDao.mergeNticArrrgList(mergeMap);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectAssetRentFeePayDtlsMngtDetailList(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public List selectCmmnCntrRentFeePaySttusMngtDetailList(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePaySttusMngtDetailList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectCmmnCntrRentFeePaySttusMngtDetailMstPk(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public EgovMap selectCmmnCntrRentFeePaySttusMngtDetailMstPk(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePaySttusMngtDetailMstPk(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectAssetRentFeePayDtlsMngtDetailSumPk(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public EgovMap selectCmmnCntrRentFeePaySttusMngtDetailSumPk(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePaySttusMngtDetailSumPk(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectNticArrrgDetail(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public Map selectNticArrrgDetail(GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
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
	 * 공컨장치장임대연체 목록 총 갯수를 조회한다.
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

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamCmmnCntrRentFeePaySttusMngtService#selectCmmnCntrRentFeePaySttusMngtDlyInfo(egovframework.rte.ygpa.gam.oper.gnrl.service.GamCmmnCntrRentFeePaySttusMngtVO)
	 */
	@Override
	public EgovMap selectCmmnCntrRentFeePaySttusMngtDlyInfo(
			GamCmmnCntrRentFeePaySttusMngtVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamCmmnCntrRentFeePaySttusMngtDao.selectCmmnCntrRentFeePaySttusMngtDlyInfo(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamCmmnCntrRentFeePaySttusMngtService#selectArrrgNpticPrintInfo(java.util.Map)
	 */
	@Override
	public EgovMap selectArrrgNpticPrintInfo(Map searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamCmmnCntrRentFeePaySttusMngtDao.selectArrrgNpticPrintInfo(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamCmmnCntrRentFeePaySttusMngtService#selectArrrgNpticPrintInfo2(java.util.Map)
	 */
	@Override
	public List selectArrrgNpticPrintInfo2(Map searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamCmmnCntrRentFeePaySttusMngtDao.selectArrrgNpticPrintInfo2(searchVO);
	}


}
