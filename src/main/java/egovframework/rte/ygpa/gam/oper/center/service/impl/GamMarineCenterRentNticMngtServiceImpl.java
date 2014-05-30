package egovframework.rte.ygpa.gam.oper.center.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamNticRequestMngtService;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentArrrgMngtVO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentNticMngtService;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentNticMngtVO;

/**
 * @Class Name : GamMarineCenterRentNticMngtServiceImpl.java
 * @Description : 마린센터임대료납부관리 Business Implement class
 * @Modification Information
 *
 * @author heroin
 * @since 2014-02-05
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamMarineCenterRentNticMngtService")

public class GamMarineCenterRentNticMngtServiceImpl  extends AbstractServiceImpl implements GamMarineCenterRentNticMngtService {

	@Resource(name="gamNticRequestMngtService")
    private GamNticRequestMngtService gamNticRequestMngtService;

	@Resource(name="gamMarineCenterRentNticMngtDao")
    private GamMarineCenterRentNticMngtDao gamMarineCenterRentNticMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 자산임대료납부관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectMarineCenterRentFeePayDtlsList(GamMarineCenterRentNticMngtVO searchVO) throws Exception {
        return gamMarineCenterRentNticMngtDao.selectMarineCenterRentFeePayDtlsList(searchVO);
    }

    /**
	 * 자산임대료납부관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectMarineCenterRentFeePayDtlsListTotCnt(GamMarineCenterRentNticMngtVO searchVO) throws Exception {
		return gamMarineCenterRentNticMngtDao.selectMarineCenterRentFeePayDtlsListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대료납부관리목록
	 * @exception Exception
	 */
    public GamMarineCenterRentNticMngtVO selectMarineCenterRentFeePayDtlsSum(GamMarineCenterRentNticMngtVO searchVO) throws Exception {
        return gamMarineCenterRentNticMngtDao.selectMarineCenterRentFeePayDtlsSum(searchVO);
    }


    /**
	 * 고지금액합계, 수납금액합계
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    public GamMarineCenterRentNticMngtVO selectMarineCenterRentFeePayDtlsMngtSum(GamMarineCenterRentNticMngtVO searchVO) throws Exception {
        return gamMarineCenterRentNticMngtDao.selectMarineCenterRentFeePayDtlsMngtSum(searchVO);
    }

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamMarineCenterRentFeePayDtlsMngtService#selectNticArrrgList(egovframework.rte.ygpa.gam.asset.rent.service.GamMarineCenterRentNticMngtVO)
	 */
	@Override
	public List<?> selectNticArrrgList(GamMarineCenterRentArrrgMngtVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return gamMarineCenterRentNticMngtDao.selectNticArrrgList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamMarineCenterRentFeePayDtlsMngtService#selectNticArrrgListTotCnt(egovframework.rte.ygpa.gam.asset.rent.service.GamMarineCenterRentNticMngtVO)
	 */
	@Override
	public int selectNticArrrgListTotCnt(GamMarineCenterRentArrrgMngtVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return gamMarineCenterRentNticMngtDao.selectNticArrrgListTotCnt(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamMarineCenterRentFeePayDtlsMngtService#mergeErpGisAssetCodeMngt(java.util.Map)
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
		return gamMarineCenterRentNticMngtDao.mergeNticArrrgList(mergeMap);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamMarineCenterRentFeePayDtlsMngtService#updateMarineCenterRentFeePayDtlsMngtList()
	 */
	@Override
	public int updateMarineCenterRentFeePayDtlsMngtList() throws Exception {
		return gamMarineCenterRentNticMngtDao.updateMarineCenterRentFeePayDtlsMngtList();
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamMarineCenterRentFeePayDtlsMngtService#selectMarineCenterRentFeePayDtlsMngtDetailList(egovframework.rte.ygpa.gam.asset.rent.service.GamMarineCenterRentNticMngtVO)
	 */
	@Override
	public List selectMarineCenterRentFeePayDtlsMngtDetailList(
			GamMarineCenterRentNticMngtVO searchVO) throws Exception {
        return gamMarineCenterRentNticMngtDao.selectMarineCenterRentFeePayDtlsMngtDetailList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamMarineCenterRentFeePayDtlsMngtService#selectMarineCenterRentFeePayDtlsMngtDetailMstPk(egovframework.rte.ygpa.gam.asset.rent.service.GamMarineCenterRentNticMngtVO)
	 */
	@Override
	public EgovMap selectMarineCenterRentFeePayDtlsMngtDetailMstPk(
			GamMarineCenterRentNticMngtVO searchVO) throws Exception {
		return gamMarineCenterRentNticMngtDao.selectMarineCenterRentFeePayDtlsMngtDetailMstPk(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamMarineCenterRentFeePayDtlsMngtService#selectMarineCenterRentFeePayDtlsMngtDetailSumPk(egovframework.rte.ygpa.gam.asset.rent.service.GamMarineCenterRentNticMngtVO)
	 */
	@Override
	public EgovMap selectMarineCenterRentFeePayDtlsMngtDetailSumPk(
			GamMarineCenterRentNticMngtVO searchVO) throws Exception {
		return gamMarineCenterRentNticMngtDao.selectMarineCenterRentFeePayDtlsMngtDetailSumPk(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamMarineCenterRentFeePayDtlsMngtService#selectNticArrrgDetail(egovframework.rte.ygpa.gam.asset.rent.service.GamMarineCenterRentNticMngtVO)
	 */
	@Override
	public Map selectNticArrrgDetail(GamMarineCenterRentNticMngtVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return gamMarineCenterRentNticMngtDao.selectNticArrrgDetail(searchVO);
	}

	@Override
	public List selectMarineCenterRentNticMngtDlyList(GamMarineCenterRentNticMngtVO searchVO) throws Exception {
        return gamMarineCenterRentNticMngtDao.selectMarineCenterRentNticMngtDlyList(searchVO);
	}
	
	
	/**change**
	 * 항만시설연체 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectMarineCenterRentNticMngtDlyListTotCnt(GamMarineCenterRentNticMngtVO searchVO) throws Exception {
		return gamMarineCenterRentNticMngtDao.selectMarineCenterRentNticMngtDlyListTotCnt(searchVO);
	}
    
	/* (non-Javadoc)change**
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectAssetRentFeePayDtlsMngtDetailSumPk(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public EgovMap selectMarineCenterRentNticMngtDlyListSum(GamMarineCenterRentNticMngtVO searchVO) throws Exception {
		return gamMarineCenterRentNticMngtDao.selectMarineCenterRentNticMngtDlyListSum(searchVO);
	}
	
}
