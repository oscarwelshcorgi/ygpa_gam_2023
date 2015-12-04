package egovframework.rte.ygpa.gam.oper.htld.service.impl;

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
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeeDefaultVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeeMngtService;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeeMngtVO;

/**
 * @Class Name : GamHtldRentFeeMngtServiceImpl.java
 * @Description : 배후단지임대료관리  Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamHtldRentFeeMngtService")
public class GamHtldRentFeeMngtServiceImpl extends AbstractServiceImpl implements GamHtldRentFeeMngtService {

	@Resource(name="gamHtldRentFeeMngtDao")
    private GamHtldRentFeeMngtDao gamHtldRentFeeMngtDao;

	@Resource(name="gamNticRequestMngtService")
	private GamNticRequestMngtService gamNticRequestMngtService;


	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 배후단지임대고지관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectHtldRentFeeMngtList(GamHtldRentFeeDefaultVO searchVO) throws Exception {
        return gamHtldRentFeeMngtDao.selectHtldRentFeeMngtList(searchVO);
    }

    /**
	 * 배후단지임대고지관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectHtldRentFeeMngtListTotCnt(GamHtldRentFeeDefaultVO searchVO) throws Exception {
		return gamHtldRentFeeMngtDao.selectHtldRentFeeMngtListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 연체, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대고지관리목록
	 * @exception Exception
	 */
    public EgovMap selectHtldRentFeeMngtSum(GamHtldRentFeeDefaultVO searchVO) throws Exception {
        return gamHtldRentFeeMngtDao.selectHtldRentFeeMngtSum(searchVO);
    }

    /**
	 * 배후단지임대고지관리정보를 수정한다.
	 * @param vo GamHtldRentFeeMngtVO
	 * @exception Exception
	 */
	public void updateHtldRentFeeMngt(GamHtldRentFeeMngtVO vo) throws Exception {
		gamHtldRentFeeMngtDao.updateHtldRentFeeMngt(vo);
	}

	/**
	 * 배후단지임대고지관리 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대고지관리정보
	 * @exception Exception
	 */
    public GamHtldRentFeeMngtVO selectHtldRentFeeMngtInfo(GamHtldRentFeeMngtVO searchVO) throws Exception {
        return gamHtldRentFeeMngtDao.selectHtldRentFeeMngtInfo(searchVO);
    }

    /**
	 * 세입징수 등록건수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAnlrveLevCnt(GamHtldRentFeeMngtVO searchVO) throws Exception {
		return gamHtldRentFeeMngtDao.selectAnlrveLevCnt(searchVO);
	}

    /**
	 * 세입징수를 등록한다.
	 * @param vo GamHtldRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertAnlrveLev(GamHtldRentFeeMngtVO vo) throws Exception {
		gamHtldRentFeeMngtDao.insertAnlrveLev(vo);
	}

	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamHtldRentFeeMngtVO
	 * @exception Exception
	 */
	public void deleteHtldRentFeeMngt(GamHtldRentFeeMngtVO vo) throws Exception {
		gamHtldRentFeeMngtDao.deleteHtldRentFeeMngt(vo);
	}

	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamHtldRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertHtldRentFeeMngtLevReqest(GamHtldRentFeeMngtVO vo) throws Exception {
		gamHtldRentFeeMngtDao.insertHtldRentFeeMngtLevReqest(vo);
	}


	@Override
	public List selectNpticPrintInfo(Map searchVO) throws Exception {
		return gamHtldRentFeeMngtDao.selectNpticPrintInfo(searchVO);
	}

	@Override
	public List selectTaxNtcPrintInfo(Map searchVO) throws Exception {
		return gamHtldRentFeeMngtDao.selectTaxNtcPrintInfo(searchVO);
	}

	@Override
	public void updateAssetRentFeeMngtListDetail(GamHtldRentFeeMngtVO vo)
			throws Exception {
		gamHtldRentFeeMngtDao.updateAssetRentFeeMngtListDetail(vo);
	}

	@Override
	public List selectAssetRentFeeDetailList(GamHtldRentFeeMngtVO searchVO) {
		return gamHtldRentFeeMngtDao.selectAssetRentFeeDetailList(searchVO);
	}

	@Override
	public Map selectAssetRentFeeDetailMstPk(GamHtldRentFeeMngtVO searchVO) {
		return gamHtldRentFeeMngtDao.selectAssetRentFeeDetailMstPk(searchVO);
	}

	@Override
	public Map selectAssetRentFeeDetailSumPk(GamHtldRentFeeMngtVO searchVO) {
		return gamHtldRentFeeMngtDao.selectAssetRentFeeDetailSumPk(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeeMngtService#selectCofixInfo()
	 */
	@Override
	public List selectCofixIntrrateList(GamHtldRentFeeMngtVO searchVO) throws Exception {
        return gamHtldRentFeeMngtDao.selectCofixIntrrateList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeeMngtService#selectNoticeRequest(egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeeMngtVO)
	 */
	@Override
	public Map selectNoticeRequest(GamHtldRentFeeMngtVO searchVO) {
		return gamHtldRentFeeMngtDao.selectNoticeRequest(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeeMngtService#updateHtldRentFee(java.util.List, java.util.List)
	 */
	@Override
	public void updateHtldRentFee(List<GamHtldRentFeeMngtVO> createList,
			List<GamHtldRentFeeMngtVO> updateList) throws Exception {
		if(createList!=null) {
			for(int i=0; i<createList.size(); i++) {
				GamHtldRentFeeMngtVO vo=createList.get(i);
				int lcount = gamHtldRentFeeMngtDao.selectInsertHtldRentFeeCnt(vo);
				if(lcount==0) gamHtldRentFeeMngtDao.insertHtldRentFee(vo);
				else {
					vo.setUpdUsr(vo.getRegUsr());
					gamHtldRentFeeMngtDao.updateHtldRentFee(vo);
				}
			}
		}

		if(updateList!=null) {
			for(int i=0; i<updateList.size(); i++) {
				GamHtldRentFeeMngtVO vo=updateList.get(i);
				gamHtldRentFeeMngtDao.updateHtldRentFee(vo);
			}
		}
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeeMngtService#selectAssetLevReqestNticPk(egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeeMngtVO)
	 */
	@Override
	public Map selectAssetLevReqestNticPk(GamHtldRentFeeMngtVO searchVO) {
		// TODO Auto-generated method stub
		return gamHtldRentFeeMngtDao.selectNoticeRequestPk(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeeMngtService#sendLevReqestRevCollF(java.util.Map)
	 */
	@Override
	public void sendLevReqestRevCollF(Map<String, Object> vo) throws Exception {
		// TODO Auto-generated method stub
        Map map = gamHtldRentFeeMngtDao.selectNticNoAccnutYear(vo);
        vo.put("accnutYear", map.get("accnutYear"));
		vo.put("nticno", map.get("nticno"));
//        map = gamHtldRentFeeMngtDao.selectIntrNticNoAccnutYear(vo);
//		vo.put("intrFeeNticno", map.get("nticno"));
		vo.put("nhtIsueYn", "Y");

		gamHtldRentFeeMngtDao.insertNticRequestRevCollF(vo);
//		if(!"".equals(vo.get("intrAmnt")) || !"0".equals(vo.get("intrAmnt"))) {
//			gamHtldRentFeeMngtDao.insertIntrNticRequestRevCollF(vo);
//		}
		gamHtldRentFeeMngtDao.updateLevReqestIssueYn(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeeMngtService#selectNticPrintMaster(java.util.Map)
	 */
	@Override
	public List selectNticPrintMaster(Map searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamHtldRentFeeMngtDao.selectNticPrintMaster(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeeMngtService#selectNticPrintDetail(java.util.Map)
	 */
	@Override
	public List selectNticPrintDetail(Map searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamHtldRentFeeMngtDao.selectNticPrintDetail(searchVO);
	}

	@Override
	public void cancelNticRequest(Map<String, Object> vo) throws Exception {
		Map map =gamHtldRentFeeMngtDao.selectNticRequestRcvdTp(vo);	// 수납 여부를 조회한다.
		if("3".equals((String)map.get("rcvdTp"))) {	// 수납 여부 확인
			throw processException("fail.cancelNticIssue.msg");	// 이미 수납 된 자료는 고지 취소 불가 함.
		}
		if("Y".equals((String)map.get("billPrtYn"))) {
//			egiroPrintCancel(vo);    // 고지가 된 경우 고지 취소를 한다. 2014-08-13 eunsungj.
			vo.put("nhtPrintYn", "N");
			Map opt = new HashMap();
			opt.putAll(vo);
			updateNticPrintState(opt);
			//return; 
		}
		gamHtldRentFeeMngtDao.deleteNticRequestRevCollF(vo);	// 고지정보를 삭제한다.
		vo.put("accnutYear", "");
		vo.put("nticno", "");
		vo.put("nhtIsueYn", "N");
		gamHtldRentFeeMngtDao.updateLevReqestIssueYn(vo);	// 고지를 취소한다.
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeeMngtService#updateNticPrintState(java.util.Map)
	 */
	@Override
	public void updateNticPrintState(Map<String, Object> vo) throws Exception {
		if(vo.get("arrrgNo")!=null) { // 연체 고지
			gamHtldRentFeeMngtDao.updateUnpaidPrintState(vo);
		}
		else gamHtldRentFeeMngtDao.updateRevCollPrintState(vo);

		gamHtldRentFeeMngtDao.updateLevReqestPrintState(vo);
		
		/* 2015.12.04 김종민 수정 egiro부분 삭제
		if("Y".equals(vo.get("nhtPrintYn"))) {
			gamNticRequestMngtService.egiroPrint(vo);
		}
		else {
			gamNticRequestMngtService.egiroPrintCancel(vo);
		}
		*/
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeeMngtService#deleteHtldRentFeeMngtList(egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeeDefaultVO)
	 */
	@Override
	public void deleteHtldRentFeeMngtList(GamHtldRentFeeDefaultVO vo)
			throws Exception {
		gamHtldRentFeeMngtDao.deleteHtldRentFeeMngtList(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeeMngtService#clearHtldRentFeeList(egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeeDefaultVO)
	 */
	@Override
	public void clearHtldRentFeeList(GamHtldRentFeeDefaultVO vo)
			throws Exception {
		gamHtldRentFeeMngtDao.clearHtldRentFeeList(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeeMngtService#selectHtldCofixPk()
	 */
	@Override
	public Map selectHtldCofixPk(GamHtldRentFeeDefaultVO vo) throws Exception {
		// TODO Auto-generated method stub
		return gamHtldRentFeeMngtDao.selectHtldCofixPk(vo);
	}
}
