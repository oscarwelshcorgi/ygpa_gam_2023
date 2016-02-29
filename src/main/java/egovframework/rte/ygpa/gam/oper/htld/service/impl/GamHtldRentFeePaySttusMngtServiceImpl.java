package egovframework.rte.ygpa.gam.oper.htld.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeePaySttusMngtVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentArrrgMngtVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeePaySttusMngtService;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeePaySttusMngtVO;

/**
 * @Class Name : GamHtldRentFeePaySttusMngtServiceImpl.java
 * @Description : 배후단지납부현황관리 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-02-05
 * @version 1.0
 * @see
 * 
 *  Copyright (C)  All right reserved.
 */
@Service("gamHtldRentFeePaySttusMngtService")

public class GamHtldRentFeePaySttusMngtServiceImpl  extends AbstractServiceImpl implements GamHtldRentFeePaySttusMngtService {

	@Resource(name="gamHtldRentFeePaySttusMngtDao")
    private GamHtldRentFeePaySttusMngtDao gamHtldRentFeePaySttusMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 배후단지임대납부현황관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectHtldRentFeePaySttusMngtList(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamHtldRentFeePaySttusMngtDao.selectHtldRentFeePaySttusMngtList(searchVO);
    }

    /**
	 * 배후단지임대납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectHtldRentFeePaySttusMngtListTotCnt(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamHtldRentFeePaySttusMngtDao.selectHtldRentFeePaySttusMngtListTotCnt(searchVO);
	}

    /**
   	 * 자료수, 고지금액합계, 수납금액합계을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 배후단지임대납부현황관리목록
   	 * @exception Exception
   	 */
    public GamHtldRentFeePaySttusMngtVO selectHtldRentFeePaySttusMngtSum(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamHtldRentFeePaySttusMngtDao.selectHtldRentFeePaySttusMngtSum(searchVO);
    }

    /**
     * 지로 수납된 자료인지 체크 한다.
     * @param searchVO
     * @return 납부유무자료
   	 * @exception Exception
     */
	public Map selectHtldCheckOcrResult(GamPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamHtldRentFeePaySttusMngtDao.selectHtldCheckOcrResult(searchVO);
	}

    /**
     * 수납처리
     * @param searchVO
     * @return
   	 * @exception Exception
     */
	public void htldUpdateRevCollRcvdTp(GamPrtFcltyRentFeePaySttusMngtVO vo) throws Exception {
		gamHtldRentFeePaySttusMngtDao.htldUpdateLevReqestRcivSe(vo);

		Map<String, Object> map = new HashMap<String, Object>();

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

     	gamHtldRentFeePaySttusMngtDao.htldUpdateRevCollRcvdTp(map);
	}

    /**
     * 배후단지임대료납뷰상세 원고지 정보
     * @param searchVO
     * @return 원고지 정보
   	 * @exception Exception
     */
	public EgovMap selectHtldRentFeePaySttusMngtDetailMstPk(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamHtldRentFeePaySttusMngtDao.selectHtldRentFeePaySttusMngtDetailMstPk(searchVO);
	}

    /**
     * 배후단지임대료납뷰상세 전체사용료목록
     * @param searchVO
     * @return 전체사용료목록
   	 * @exception Exception
     */
	public List selectHtldRentFeePaySttusMngtDetailList(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamHtldRentFeePaySttusMngtDao.selectHtldRentFeePaySttusMngtDetailList(searchVO);
	}
	
    /**
     * 배후단지임대료납뷰상세 총고지금액, 총납부금액, 관리비, 연체료, 과태료
     * @param searchVO
     * @return 총고지금액, 총납부금액, 관리비, 연체료, 과태료 정보
   	 * @exception Exception
     */
	public EgovMap selectHtldRentFeePaySttusMngtDetailSumPk(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamHtldRentFeePaySttusMngtDao.selectHtldRentFeePaySttusMngtDetailSumPk(searchVO);
	}


    /**
     * 배후단지임대료납뷰상세 연체정보
     * @param searchVO
     * @return 해당고지의 연체정보
   	 * @exception Exception
     */
	public Map selectNticArrrgDetail(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamHtldRentFeePaySttusMngtDao.selectNticArrrgDetail(searchVO);
	}

    /**
     * 연체정보 등록
     * @param searchVO
     * @return
   	 * @exception Exception
     */
	public void sendLevReqestUnpaidF(GamHtldRentArrrgMngtVO gamHtldRentFeeMngtVO) throws Exception {
		Map map = gamHtldRentFeePaySttusMngtDao.selectLevReqestUnpaidF(gamHtldRentFeeMngtVO);

		map.put("emplNo", gamHtldRentFeeMngtVO.getUpdUsr());
		map.put("userName", gamHtldRentFeeMngtVO.getUserName());

		gamHtldRentFeePaySttusMngtDao.insertNticRequestUnpaidF(map);
		map.put("rcivSe", "1"); //수납구분을 연체로 만든다.
		gamHtldRentFeePaySttusMngtDao.updateLevRequestUnpaidF(map); //2015.11.27 김종민 수정 
	}

    /**
     * 배후단지임대료연체현황관리 연체목록
     * @param searchVO
     * @return 연체현황 목록
   	 * @exception Exception
     */
	public List selectHtldRentFeePaySttusMngtDlyList(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {
        return gamHtldRentFeePaySttusMngtDao.selectHtldRentFeePaySttusMngtDlyList(searchVO);
	}

    /**
     * 배후단지임대료연체현황관리 연체정보
     * @param searchVO
     * @return 연체현황 목록
   	 * @exception Exception
     */
    public EgovMap selectHtldRentFeePaySttusMngtDlyInfo(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {
    	return gamHtldRentFeePaySttusMngtDao.selectHtldRentFeePaySttusMngtDlyInfo(searchVO);
    }

    /**
     * 배후단지 연체고지 취소 - 요금설정 및 되돌리는 방식이 틀리기에 일반부두와 약간 다름. 2015.12.14 추가 김종민
     * @param nticVo
     * @return
     * @throws Exception
     */
	@Override
	public void cancelUnpaidRequestPk(Map<String, Object> vo) throws Exception {
		//Parameter로 넘어온 연체데이터로 해당 고지정보와 연체정보를 가져온다.
		List dlyInfoList = gamHtldRentFeePaySttusMngtDao.selectLevReqestArrrgAmt(vo);
		if(dlyInfoList == null) throw processException("fail.nticArrg.cancel");
		if(dlyInfoList.size() < 1) throw processException("fail.nticArrg.cancel");
		
		EgovMap dlyInfo = (EgovMap)dlyInfoList.get(0);
		if(dlyInfo == null) throw processException("fail.nticArrg.cancel");
		
		//고지테이블 데이터를 변경한다.
		String dlySerNo = (dlyInfo.get("dlySerNo") != null) ? (String) dlyInfo.get("dlySerNo") : null;
		if(dlySerNo == null) throw processException("fail.nticArrg.cancel");
		
		if("01".equals(dlySerNo) && dlyInfoList.size() == 1) {
			//최초연체라면
			vo.put("rcivSe", "0");	// 수납 상태를 미수납("0") 으로 세팅
			//vo.put("prvBillDt", dlyInfo.get("frstBillDt"));  //삭제할 때 원고지 일자 지워짐.... 아마 firstBillDt에 데이터가 없어서 지워짐..
			vo.put("billDt", dlyInfo.get("prvBillDt"));
			vo.put("dlyDueDt", dlyInfo.get("prvDueDt"));
			gamHtldRentFeePaySttusMngtDao.updateLevReqestUnarrrgAmt(vo);
		} else {
			//최초연체가 아니라면
			if(dlyInfoList.size() > 1) {
				EgovMap prevDlyInfo = (EgovMap) dlyInfoList.get(dlyInfoList.size()-1); 
				vo.put("dlyBillAmnt", prevDlyInfo.get("dlyBillAmnt"));
				vo.put("dbillAmnt", prevDlyInfo.get("dbillAmnt"));
				vo.put("dlySerNo", prevDlyInfo.get("dlySerNo"));
				vo.put("arrrgTariff", prevDlyInfo.get("arrrgTariff"));
				vo.put("arrrgPayDates", prevDlyInfo.get("arrrgPayDates"));
				vo.put("dlyBillDt", prevDlyInfo.get("prvBillDt"));
				vo.put("dlyDueDt", prevDlyInfo.get("prvDueDt"));
				gamHtldRentFeePaySttusMngtDao.updateLevReqestArrrgAmt(vo);
			} else {
				throw processException("fail.nticArrg.cancel");
			}
		}
		
		//최초연체가 아닐 경우 변경된 dlySerNo값을 되돌린다.
		vo.put("dlySerNo", dlySerNo);
		
		//연체정보 삭제
		gamHtldRentFeePaySttusMngtDao.deleteUnpaidByPk(vo);
	}

    
	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectNticArrrgList(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public List<?> selectNticArrrgList(GamHtldRentArrrgMngtVO searchVO) throws Exception {
		return gamHtldRentFeePaySttusMngtDao.selectNticArrrgList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectNticArrrgListTotCnt(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public int selectNticArrrgListTotCnt(GamHtldRentArrrgMngtVO searchVO) throws Exception {
		return gamHtldRentFeePaySttusMngtDao.selectNticArrrgListTotCnt(searchVO);
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

		return gamHtldRentFeePaySttusMngtDao.mergeNticArrrgList(mergeMap);
	}






	/**change**
	 * 항만시설연체 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectHtldRentFeePaySttusMngtDlyListTotCnt(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamHtldRentFeePaySttusMngtDao.selectHtldRentFeePaySttusMngtDlyListTotCnt(searchVO);
	}

	/* (non-Javadoc)change**
	 * @see egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtService#selectAssetRentFeePayDtlsMngtDetailSumPk(egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeePayDtlsMngtVO)
	 */
	@Override
	public EgovMap selectHtldRentFeePaySttusMngtDlyListSum(GamHtldRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamHtldRentFeePaySttusMngtDao.selectHtldRentFeePaySttusMngtDlyListSum(searchVO);
	}


	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeePaySttusMngtService#selectArrrglevReqestPk(egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeeMngtVO)
	 */
	@Override
	public Map selectArrrglevReqestPk(GamHtldRentFeePaySttusMngtVO gamHtldRentFeeMngtVO) throws Exception {
		return gamHtldRentFeePaySttusMngtDao.selectArrrglevReqestPk(gamHtldRentFeeMngtVO);
	}



	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeePaySttusMngtService#selectHtldShowFeePayPopup(egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeePaySttusMngtVO)
	 */
	@Override
	public EgovMap selectHtldShowFeePayPopup(GamPrtFcltyRentFeePaySttusMngtVO searchVO) throws Exception {
		return gamHtldRentFeePaySttusMngtDao.selectHtldShowFeePayPopup(searchVO);
	}

	@Override
	public List selectNticPrintMaster(Map searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamHtldRentFeePaySttusMngtDao.selectNticPrintMaster(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeeMngtService#selectNticPrintDetail(java.util.Map)
	 */
	@Override
	public List selectNticPrintDetail(Map searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamHtldRentFeePaySttusMngtDao.selectNticPrintDetail(searchVO);
	}
	
	/**
	 * 연체고지서 인쇄 발부
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public void updateArrrgNticPrintState(Map<String, Object> vo) throws Exception {
		gamHtldRentFeePaySttusMngtDao.updateArrrgNticPrintState(vo);
	}

	public List selectNticPrintDetailHist(Map<String, Object> vo) throws Exception {
        return gamHtldRentFeePaySttusMngtDao.selectNticPrintDetailHist(vo);
    }	
}
