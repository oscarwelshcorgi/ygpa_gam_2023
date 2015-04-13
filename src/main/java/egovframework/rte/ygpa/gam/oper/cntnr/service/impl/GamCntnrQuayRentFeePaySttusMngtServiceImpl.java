package egovframework.rte.ygpa.gam.oper.cntnr.service.impl;

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
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentFeePaySttusMngtService;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentFeePaySttusMngtVO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentArrrgMngtVO;




/**
 * @Class Name : GamCntnrQuayRentFeePaySttusMngtServiceImpl.java
 * @Description : 컨테이너부두납부현황관리 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-02-05
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamCntnrQuayRentFeePaySttusMngtService")

public class GamCntnrQuayRentFeePaySttusMngtServiceImpl  extends AbstractServiceImpl implements GamCntnrQuayRentFeePaySttusMngtService {

	@Resource(name="gamNticRequestMngtService")
    private GamNticRequestMngtService gamNticRequestMngtService;

	@Resource(name="gamCntnrQuayRentFeePaySttusMngtDao")
    private GamCntnrQuayRentFeePaySttusMngtDao gamCntnrQuayRentFeePaySttusMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 컨테이너부두임대납부현황관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCntnrQuayRentFeePaySttusMngtList(GamCntnrQuayRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamCntnrQuayRentFeePaySttusMngtDao.selectCntnrQuayRentFeePaySttusMngtList(searchVO);
    }

    /**
	 * 컨테이너부두임대납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCntnrQuayRentFeePaySttusMngtListTotCnt(GamCntnrQuayRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamCntnrQuayRentFeePaySttusMngtDao.selectCntnrQuayRentFeePaySttusMngtListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대납부현황관리목록
	 * @exception Exception
	 */
    public GamCntnrQuayRentFeePaySttusMngtVO selectCntnrQuayRentFeePaySttusSum(GamCntnrQuayRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamCntnrQuayRentFeePaySttusMngtDao.selectCntnrQuayRentFeePaySttusSum(searchVO);
    }





    /**
	 * 고지금액합계, 수납금액합계
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    public GamCntnrQuayRentFeePaySttusMngtVO selectCntnrQuayRentFeePaySttusMngtSum(GamCntnrQuayRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamCntnrQuayRentFeePaySttusMngtDao.selectCntnrQuayRentFeePaySttusMngtSum(searchVO);
    }

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectNticArrrgList(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public List<?> selectNticArrrgList(GamCntnrQuayRentArrrgMngtVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamCntnrQuayRentFeePaySttusMngtDao.selectNticArrrgList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectNticArrrgListTotCnt(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public int selectNticArrrgListTotCnt(GamCntnrQuayRentArrrgMngtVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamCntnrQuayRentFeePaySttusMngtDao.selectNticArrrgListTotCnt(searchVO);
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
		return gamCntnrQuayRentFeePaySttusMngtDao.mergeNticArrrgList(mergeMap);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectAssetRentFeePayDtlsMngtDetailList(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public List selectCntnrQuayRentFeePaySttusMngtDetailList(GamCntnrQuayRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamCntnrQuayRentFeePaySttusMngtDao.selectCntnrQuayRentFeePaySttusMngtDetailList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectCntnrQuayRentFeePaySttusMngtDetailMstPk(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public EgovMap selectCntnrQuayRentFeePaySttusMngtDetailMstPk(GamCntnrQuayRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamCntnrQuayRentFeePaySttusMngtDao.selectCntnrQuayRentFeePaySttusMngtDetailMstPk(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectAssetRentFeePayDtlsMngtDetailSumPk(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public EgovMap selectCntnrQuayRentFeePaySttusMngtDetailSumPk(GamCntnrQuayRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamCntnrQuayRentFeePaySttusMngtDao.selectCntnrQuayRentFeePaySttusMngtDetailSumPk(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectNticArrrgDetail(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public Map selectNticArrrgDetail(GamCntnrQuayRentFeePaySttusMngtVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamCntnrQuayRentFeePaySttusMngtDao.selectNticArrrgDetail(searchVO);
	}





	/* (non-Javadoc)change**
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectAssetRentFeePayDtlsMngtDetailList(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public List selectCntnrQuayRentFeePaySttusMngtDlyList(GamCntnrQuayRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamCntnrQuayRentFeePaySttusMngtDao.selectCntnrQuayRentFeePaySttusMngtDlyList(searchVO);
	}


	/**change**
	 * 컨테이너부두임대연체 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectCntnrQuayRentFeePaySttusMngtDlyListTotCnt(GamCntnrQuayRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamCntnrQuayRentFeePaySttusMngtDao.selectCntnrQuayRentFeePaySttusMngtDlyListTotCnt(searchVO);
	}

	/* (non-Javadoc)change**
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectAssetRentFeePayDtlsMngtDetailSumPk(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public EgovMap selectCntnrQuayRentFeePaySttusMngtDlyListSum(GamCntnrQuayRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamCntnrQuayRentFeePaySttusMngtDao.selectCntnrQuayRentFeePaySttusMngtDlyListSum(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentFeePaySttusMngtService#selectCntnrQuayRentFeePaySttusMngtDlyInfo(egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentFeePaySttusMngtVO)
	 */
	@Override
	public EgovMap selectCntnrQuayRentFeePaySttusMngtDlyInfo(
			GamCntnrQuayRentFeePaySttusMngtVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamCntnrQuayRentFeePaySttusMngtDao.selectCntnrQuayRentFeePaySttusMngtDlyInfo(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentFeePaySttusMngtService#selectArrrgNpticPrintInfo(java.util.Map)
	 */
	@Override
	public EgovMap selectArrrgNpticPrintInfo(Map searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamCntnrQuayRentFeePaySttusMngtDao.selectArrrgNpticPrintInfo(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentFeePaySttusMngtService#selectArrrgNpticPrintInfo2(java.util.Map)
	 */
	@Override
	public List selectArrrgNpticPrintInfo2(Map searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamCntnrQuayRentFeePaySttusMngtDao.selectArrrgNpticPrintInfo2(searchVO);
	}
	
	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentFeePaySttusMngtService#selectFeePayPopup(java.util.Map)
	 */
	@Override
	public EgovMap selectFeePayPopup(GamCntnrQuayRentFeePaySttusMngtVO vo) throws Exception {
		return gamCntnrQuayRentFeePaySttusMngtDao.selectFeePayPopup(vo);
	}
	
	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentFeePaySttusMngtService#updateRevCollRcvdTp(egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentFeePaySttusMngtVO)
	 */
	@Override
	public void updateRevCollRcvdTp(GamCntnrQuayRentFeePaySttusMngtVO vo)
			throws Exception {
     	Map<String, Object> map = new HashMap<String, Object>();

     	gamCntnrQuayRentFeePaySttusMngtDao.updateLevReqestRcivSe(vo);

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

     	gamCntnrQuayRentFeePaySttusMngtDao.updateRevCollRcvdTp(map);
	}
	
	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentFeePaySttusMngtService#selectCheckOcrResult(egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentFeePaySttusMngtVO)
	 */
	@Override
	public EgovMap selectCheckOcrResult(GamCntnrQuayRentFeePaySttusMngtVO vo)
			throws Exception {
		return gamCntnrQuayRentFeePaySttusMngtDao.selectCheckOcrResult(vo);

	}

}
