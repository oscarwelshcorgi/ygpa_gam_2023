package egovframework.rte.ygpa.gam.oper.gnrltest.service.impl;

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
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestFcltyRentArrrgMngtVO;
import egovframework.rte.ygpa.gam.cmmn.fclty.service.GamTestNticRequestMngtService;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyRentFeePaySttusMngtService;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyRentFeePaySttusMngtVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentArrrgMngtVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO;


/**
 * @Class Name : GamTestPrtFcltyRentFeePaySttusMngtServiceImpl.java
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
@Service("gamTestPrtFcltyRentFeePaySttusMngtService")

public class GamTestPrtFcltyRentFeePaySttusMngtServiceImpl  extends AbstractServiceImpl implements GamTestPrtFcltyRentFeePaySttusMngtService {

	@Resource(name="gamTestNticRequestMngtService")
    private GamTestNticRequestMngtService gamTestNticRequestMngtService;

	@Resource(name="gamTestPrtFcltyRentFeePaySttusMngtDao")
    private GamTestPrtFcltyRentFeePaySttusMngtDao gamTestPrtFcltyRentFeePaySttusMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 항만시설납부현황관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyRentFeePaySttusMngtList(GamTestPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamTestPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtList(searchVO);
    }

    /**
	 * 항만시설납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyRentFeePaySttusMngtListTotCnt(GamTestPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamTestPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설납부현황관리목록
	 * @exception Exception
	 */
    public GamTestPrtFcltyRentFeePaySttusMngtVO selectPrtFcltyRentFeePaySttusSum(GamTestPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamTestPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusSum(searchVO);
    }





    /**
	 * 고지금액합계, 수납금액합계
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    public GamTestPrtFcltyRentFeePaySttusMngtVO selectPrtFcltyRentFeePaySttusMngtSum(GamTestPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamTestPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtSum(searchVO);
    }

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectNticArrrgList(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public List<?> selectNticArrrgList(GamTestFcltyRentArrrgMngtVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamTestPrtFcltyRentFeePaySttusMngtDao.selectNticArrrgList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectNticArrrgListTotCnt(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public int selectNticArrrgListTotCnt(GamTestFcltyRentArrrgMngtVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamTestPrtFcltyRentFeePaySttusMngtDao.selectNticArrrgListTotCnt(searchVO);
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
		return gamTestPrtFcltyRentFeePaySttusMngtDao.mergeNticArrrgList(mergeMap);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectAssetRentFeePayDtlsMngtDetailList(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public List selectPrtFcltyRentFeePaySttusMngtDetailList(GamTestPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamTestPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtDetailList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectPrtFcltyRentFeePaySttusMngtDetailMstPk(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public EgovMap selectPrtFcltyRentFeePaySttusMngtDetailMstPk(GamTestPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamTestPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtDetailMstPk(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectAssetRentFeePayDtlsMngtDetailSumPk(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public EgovMap selectPrtFcltyRentFeePaySttusMngtDetailSumPk(GamTestPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamTestPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtDetailSumPk(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectNticArrrgDetail(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public Map selectNticArrrgDetail(GamTestPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamTestPrtFcltyRentFeePaySttusMngtDao.selectNticArrrgDetail(searchVO);
	}





	/* (non-Javadoc)change**
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectAssetRentFeePayDtlsMngtDetailList(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public List selectPrtFcltyRentFeePaySttusMngtDlyList(GamTestPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamTestPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtDlyList(searchVO);
	}


	/**change**
	 * 항만시설연체 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtFcltyRentFeePaySttusMngtDlyListTotCnt(GamTestPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamTestPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtDlyListTotCnt(searchVO);
	}

	/* (non-Javadoc)change**
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectAssetRentFeePayDtlsMngtDetailSumPk(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public EgovMap selectPrtFcltyRentFeePaySttusMngtDlyListSum(GamTestPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamTestPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtDlyListSum(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamTestPrtFcltyRentFeePaySttusMngtService#selectPrtFcltyRentFeePaySttusMngtDlyInfo(egovframework.rte.ygpa.gam.oper.gnrl.service.GamTestPrtFcltyRentFeePaySttusMngtVO)
	 */
	@Override
	public EgovMap selectPrtFcltyRentFeePaySttusMngtDlyInfo(
			GamTestPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamTestPrtFcltyRentFeePaySttusMngtDao.selectPrtFcltyRentFeePaySttusMngtDlyInfo(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamTestPrtFcltyRentFeePaySttusMngtService#selectArrrgNpticPrintInfo(java.util.Map)
	 */
	@Override
	public List selectArrrgNpticPrintInfo(Map searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamTestPrtFcltyRentFeePaySttusMngtDao.selectArrrgNpticPrintInfo(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamTestPrtFcltyRentFeePaySttusMngtService#selectArrrgNpticPrintInfo2(java.util.Map)
	 */
	@Override
	public List selectArrrgNpticPrintInfo2(Map searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamTestPrtFcltyRentFeePaySttusMngtDao.selectArrrgNpticPrintInfo2(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamTestPrtFcltyRentFeePaySttusMngtService#selectFeePayPopup(java.util.Map)
	 */
	@Override
	public EgovMap selectFeePayPopup(GamTestPrtFcltyRentFeePaySttusMngtVO vo) throws Exception {
		return gamTestPrtFcltyRentFeePaySttusMngtDao.selectFeePayPopup(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamTestPrtFcltyRentFeePaySttusMngtService#updateRevCollRcvdTp(egovframework.rte.ygpa.gam.oper.gnrl.service.GamTestPrtFcltyRentFeePaySttusMngtVO)
	 */
	@Override
	public void updateRevCollRcvdTp(GamTestPrtFcltyRentFeePaySttusMngtVO vo)
			throws Exception {
     	Map<String, Object> map = new HashMap<String, Object>();

     	gamTestPrtFcltyRentFeePaySttusMngtDao.updateLevReqestRcivSe(vo);

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

     	gamTestPrtFcltyRentFeePaySttusMngtDao.updateRevCollRcvdTp(map);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamTestPrtFcltyRentFeePaySttusMngtService#selectCheckOcrResult(egovframework.rte.ygpa.gam.oper.gnrl.service.GamTestPrtFcltyRentFeePaySttusMngtVO)
	 */
	@Override
	public EgovMap selectCheckOcrResult(GamTestPrtFcltyRentFeePaySttusMngtVO vo)
			throws Exception {
		return gamTestPrtFcltyRentFeePaySttusMngtDao.selectCheckOcrResult(vo);

	}


}
