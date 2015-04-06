package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

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
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamFcltyRentArrrgMngtVO;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamNticRequestMngtService;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeePaySttusMngtService;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeePaySttusMngtVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentArrrgMngtVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO;


/**
 * @Class Name : GamPrtFcltyRentFeePaySttusMngtServiceImpl.java
 * @Description : 항만시설납부현황관리 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-02-05
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamPrtFcltyRentFeePaySttusMngtService")

public class GamPrtFcltyRentFeePaySttusMngtServiceImpl  extends AbstractServiceImpl implements GamPrtFcltyRentFeePaySttusMngtService {

	@Resource(name="gamNticRequestMngtService")
    private GamNticRequestMngtService gamNticRequestMngtService;

	@Resource(name="gamPrtFcltyRentFeePaySttusMngtDao")
    private GamPrtFcltyRentFeePaySttusMngtDao gamPrtFcltyRentFeePaySttusMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 항만시설납부현황관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyRentFeePaySttusMngtList(GamPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtList(searchVO);
    }

    /**
	 * 항만시설납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyRentFeePaySttusMngtListTotCnt(GamPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설납부현황관리목록
	 * @exception Exception
	 */
    public GamPrtFcltyRentFeePaySttusMngtVO selectPrtFcltyRentFeePaySttusSum(GamPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusSum(searchVO);
    }





    /**
	 * 고지금액합계, 수납금액합계
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    public GamPrtFcltyRentFeePaySttusMngtVO selectPrtFcltyRentFeePaySttusMngtSum(GamPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtSum(searchVO);
    }

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectNticArrrgList(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public List<?> selectNticArrrgList(GamFcltyRentArrrgMngtVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamPrtFcltyRentFeePaySttusMngtDao.selectNticArrrgList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectNticArrrgListTotCnt(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public int selectNticArrrgListTotCnt(GamFcltyRentArrrgMngtVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamPrtFcltyRentFeePaySttusMngtDao.selectNticArrrgListTotCnt(searchVO);
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
		return gamPrtFcltyRentFeePaySttusMngtDao.mergeNticArrrgList(mergeMap);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectAssetRentFeePayDtlsMngtDetailList(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public List selectPrtFcltyRentFeePaySttusMngtDetailList(GamPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtDetailList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectPrtFcltyRentFeePaySttusMngtDetailMstPk(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public EgovMap selectPrtFcltyRentFeePaySttusMngtDetailMstPk(GamPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtDetailMstPk(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectAssetRentFeePayDtlsMngtDetailSumPk(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public EgovMap selectPrtFcltyRentFeePaySttusMngtDetailSumPk(GamPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtDetailSumPk(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectNticArrrgDetail(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public Map selectNticArrrgDetail(GamPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamPrtFcltyRentFeePaySttusMngtDao.selectNticArrrgDetail(searchVO);
	}





	/* (non-Javadoc)change**
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectAssetRentFeePayDtlsMngtDetailList(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public List selectPrtFcltyRentFeePaySttusMngtDlyList(GamPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtDlyList(searchVO);
	}


	/**change**
	 * 항만시설연체 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyRentFeePaySttusMngtDlyListTotCnt(GamPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtDlyListTotCnt(searchVO);
	}

	/* (non-Javadoc)change**
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectAssetRentFeePayDtlsMngtDetailSumPk(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public EgovMap selectPrtFcltyRentFeePaySttusMngtDlyListSum(GamPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtDlyListSum(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeePaySttusMngtService#selectPrtFcltyRentFeePaySttusMngtDlyInfo(egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeePaySttusMngtVO)
	 */
	@Override
	public EgovMap selectPrtFcltyRentFeePaySttusMngtDlyInfo(
			GamPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtDlyInfo(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeePaySttusMngtService#selectArrrgNpticPrintInfo(java.util.Map)
	 */
	@Override
	public EgovMap selectArrrgNpticPrintInfo(Map searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamPrtFcltyRentFeePaySttusMngtDao.selectArrrgNpticPrintInfo(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeePaySttusMngtService#selectArrrgNpticPrintInfo2(java.util.Map)
	 */
	@Override
	public List selectArrrgNpticPrintInfo2(Map searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamPrtFcltyRentFeePaySttusMngtDao.selectArrrgNpticPrintInfo2(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeePaySttusMngtService#selectFeePayPopup(java.util.Map)
	 */
	@Override
	public EgovMap selectFeePayPopup(GamPrtFcltyRentFeePaySttusMngtVO vo) throws Exception {
		return gamPrtFcltyRentFeePaySttusMngtDao.selectFeePayPopup(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeePaySttusMngtService#updateRevCollRcvdTp(egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeePaySttusMngtVO)
	 */
	@Override
	public void updateRevCollRcvdTp(GamPrtFcltyRentFeePaySttusMngtVO vo)
			throws Exception {
     	Map<String, Object> map = new HashMap<String, Object>();

     	gamPrtFcltyRentFeePaySttusMngtDao.updateLevReqestRcivSe(vo);

     	map.put("rcvdSe", vo.getRcivSe());
     	map.put("updUsr", vo.getUpdUsr());
     	map.put("rcvdTp", vo.getRcivSe());
     	map.put("rcvdDt", vo.getRcivDt());
     	map.put("prtAtCode", vo.getPrtAtCode());
     	map.put("mngYear", vo.getMngYear());
     	map.put("mngNo", vo.getMngNo());
     	map.put("mngCnt", vo.getMngCnt());
     	map.put("nticCnt", vo.getNticCnt());
     	map.put("chrgeKnd", vo.getChrgeKnd());

     	gamPrtFcltyRentFeePaySttusMngtDao.updateRevCollRcvdTp(map);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeePaySttusMngtService#selectCheckOcrResult(egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeePaySttusMngtVO)
	 */
	@Override
	public EgovMap selectCheckOcrResult(GamPrtFcltyRentFeePaySttusMngtVO vo)
			throws Exception {
		return gamPrtFcltyRentFeePaySttusMngtDao.selectCheckOcrResult(vo);

	}


}
