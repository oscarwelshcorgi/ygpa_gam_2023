package egovframework.rte.ygpa.gam.oper.train.service.impl;

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

import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentArrrgMngtVO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentFeePaySttusMngtService;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentFeePaySttusMngtVO;

/**
 * @Class Name : GamTrainPortRentFeePaySttusMngtServiceImpl.java
 * @Description : 철송장납부현황관리 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-02-05
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamTrainPortRentFeePaySttusMngtService")

public class GamTrainPortRentFeePaySttusMngtServiceImpl  extends AbstractServiceImpl implements GamTrainPortRentFeePaySttusMngtService {

	@Resource(name="gamNticRequestMngtService")
    private GamNticRequestMngtService gamNticRequestMngtService;

	@Resource(name="gamTrainPortRentFeePaySttusMngtDao")
    private GamTrainPortRentFeePaySttusMngtDao gamTrainPortRentFeePaySttusMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 철송장임대납부현황관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectTrainPortRentFeePaySttusMngtList(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePaySttusMngtList(searchVO);
    }

    /**
	 * 철송장임대납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectTrainPortRentFeePaySttusMngtListTotCnt(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePaySttusMngtListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대납부현황관리목록
	 * @exception Exception
	 */
    public GamTrainPortRentFeePaySttusMngtVO selectTrainPortRentFeePaySttusSum(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePaySttusSum(searchVO);
    }





    /**
	 * 고지금액합계, 수납금액합계
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    public GamTrainPortRentFeePaySttusMngtVO selectTrainPortRentFeePaySttusMngtSum(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePaySttusMngtSum(searchVO);
    }

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectNticArrrgList(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public List<?> selectNticArrrgList(GamFcltyRentArrrgMngtVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamTrainPortRentFeePaySttusMngtDao.selectNticArrrgList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectNticArrrgListTotCnt(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public int selectNticArrrgListTotCnt(GamFcltyRentArrrgMngtVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamTrainPortRentFeePaySttusMngtDao.selectNticArrrgListTotCnt(searchVO);
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
		return gamTrainPortRentFeePaySttusMngtDao.mergeNticArrrgList(mergeMap);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectAssetRentFeePayDtlsMngtDetailList(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public List selectTrainPortRentFeePaySttusMngtDetailList(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePaySttusMngtDetailList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectTrainPortRentFeePaySttusMngtDetailMstPk(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public EgovMap selectTrainPortRentFeePaySttusMngtDetailMstPk(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePaySttusMngtDetailMstPk(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectAssetRentFeePayDtlsMngtDetailSumPk(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public EgovMap selectTrainPortRentFeePaySttusMngtDetailSumPk(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePaySttusMngtDetailSumPk(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectNticArrrgDetail(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public Map selectNticArrrgDetail(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamTrainPortRentFeePaySttusMngtDao.selectNticArrrgDetail(searchVO);
	}





	/* (non-Javadoc)change**
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectAssetRentFeePayDtlsMngtDetailList(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public List selectTrainPortRentFeePaySttusMngtDlyList(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePaySttusMngtDlyList(searchVO);
	}


	/**change**
	 * 철송장임대연체 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectTrainPortRentFeePaySttusMngtDlyListTotCnt(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePaySttusMngtDlyListTotCnt(searchVO);
	}

	/* (non-Javadoc)change**
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectAssetRentFeePayDtlsMngtDetailSumPk(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public EgovMap selectTrainPortRentFeePaySttusMngtDlyListSum(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePaySttusMngtDlyListSum(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamTrainPortRentFeePaySttusMngtService#selectTrainPortRentFeePaySttusMngtDlyInfo(egovframework.rte.ygpa.gam.oper.gnrl.service.GamTrainPortRentFeePaySttusMngtVO)
	 */
	@Override
	public EgovMap selectTrainPortRentFeePaySttusMngtDlyInfo(
			GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePaySttusMngtDlyInfo(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamTrainPortRentFeePaySttusMngtService#selectArrrgNpticPrintInfo(java.util.Map)
	 */
	@Override
	public EgovMap selectArrrgNpticPrintInfo(Map searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamTrainPortRentFeePaySttusMngtDao.selectArrrgNpticPrintInfo(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamTrainPortRentFeePaySttusMngtService#selectArrrgNpticPrintInfo2(java.util.Map)
	 */
	@Override
	public List selectArrrgNpticPrintInfo2(Map searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamTrainPortRentFeePaySttusMngtDao.selectArrrgNpticPrintInfo2(searchVO);
	}


}
