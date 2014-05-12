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
	 * 자산임대료납부관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectTrainPortRentFeePayDtlsList(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePayDtlsList(searchVO);
    }

    /**
	 * 자산임대료납부관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectTrainPortRentFeePayDtlsListTotCnt(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePayDtlsListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대료납부관리목록
	 * @exception Exception
	 */
    public GamTrainPortRentFeePaySttusMngtVO selectTrainPortRentFeePayDtlsSum(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePayDtlsSum(searchVO);
    }


    /**
	 * 고지금액합계, 수납금액합계
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    public GamTrainPortRentFeePaySttusMngtVO selectTrainPortRentFeePayDtlsMngtSum(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePayDtlsMngtSum(searchVO);
    }

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamTrainPortRentFeePayDtlsMngtService#selectNticArrrgList(egovframework.rte.ygpa.gam.asset.rent.service.GamTrainPortRentFeePaySttusMngtVO)
	 */
	@Override
	public List<?> selectNticArrrgList(GamTrainPortRentArrrgMngtVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return gamTrainPortRentFeePaySttusMngtDao.selectNticArrrgList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamTrainPortRentFeePayDtlsMngtService#selectNticArrrgListTotCnt(egovframework.rte.ygpa.gam.asset.rent.service.GamTrainPortRentFeePaySttusMngtVO)
	 */
	@Override
	public int selectNticArrrgListTotCnt(GamTrainPortRentArrrgMngtVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return gamTrainPortRentFeePaySttusMngtDao.selectNticArrrgListTotCnt(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamTrainPortRentFeePayDtlsMngtService#mergeErpGisAssetCodeMngt(java.util.Map)
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
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamTrainPortRentFeePayDtlsMngtService#updateTrainPortRentFeePayDtlsMngtList()
	 */
	@Override
	public int updateTrainPortRentFeePayDtlsMngtList() throws Exception {
		return gamTrainPortRentFeePaySttusMngtDao.updateTrainPortRentFeePayDtlsMngtList();
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamTrainPortRentFeePayDtlsMngtService#selectTrainPortRentFeePayDtlsMngtDetailList(egovframework.rte.ygpa.gam.asset.rent.service.GamTrainPortRentFeePaySttusMngtVO)
	 */
	@Override
	public List selectTrainPortRentFeePayDtlsMngtDetailList(
			GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePayDtlsMngtDetailList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamTrainPortRentFeePayDtlsMngtService#selectTrainPortRentFeePayDtlsMngtDetailMstPk(egovframework.rte.ygpa.gam.asset.rent.service.GamTrainPortRentFeePaySttusMngtVO)
	 */
	@Override
	public EgovMap selectTrainPortRentFeePayDtlsMngtDetailMstPk(
			GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePayDtlsMngtDetailMstPk(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamTrainPortRentFeePayDtlsMngtService#selectTrainPortRentFeePayDtlsMngtDetailSumPk(egovframework.rte.ygpa.gam.asset.rent.service.GamTrainPortRentFeePaySttusMngtVO)
	 */
	@Override
	public EgovMap selectTrainPortRentFeePayDtlsMngtDetailSumPk(
			GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamTrainPortRentFeePaySttusMngtDao.selectTrainPortRentFeePayDtlsMngtDetailSumPk(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamTrainPortRentFeePayDtlsMngtService#selectNticArrrgDetail(egovframework.rte.ygpa.gam.asset.rent.service.GamTrainPortRentFeePaySttusMngtVO)
	 */
	@Override
	public Map selectNticArrrgDetail(GamTrainPortRentFeePaySttusMngtVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return gamTrainPortRentFeePaySttusMngtDao.selectNticArrrgDetail(searchVO);
	}

}
