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
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldMngFeeMngtService;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldMngFeeDefaultVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldMngFeeMngtVO;

/**
 * @Class Name : GamHtldMngFeeMngtServiceImpl.java
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
@Service("gamHtldMngFeeMngtService")
public class GamHtldMngFeeMngtServiceImpl extends AbstractServiceImpl implements GamHtldMngFeeMngtService {

	@Resource(name="gamHtldMngFeeMngtDao")
    private GamHtldMngFeeMngtDao gamHtldMngFeeMngtDao;

	@Resource(name="gamNticRequestMngtService")
	private GamNticRequestMngtService gamNticRequestMngtService;


	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 배후단지임대고지관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectHtldMngFeeMngtList(GamHtldMngFeeDefaultVO searchVO) throws Exception {
        return gamHtldMngFeeMngtDao.selectHtldMngFeeMngtList(searchVO);
    }

    /**
	 * 배후단지임대고지관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectHtldMngFeeMngtListTotCnt(GamHtldMngFeeDefaultVO searchVO) throws Exception {
		return gamHtldMngFeeMngtDao.selectHtldMngFeeMngtListTotCnt(searchVO);
	}

    /**
	 * 자료수, 사용료, 연체, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대고지관리목록
	 * @exception Exception
	 */
    public EgovMap selectHtldMngFeeMngtSum(GamHtldMngFeeDefaultVO searchVO) throws Exception {
        return gamHtldMngFeeMngtDao.selectHtldMngFeeMngtSum(searchVO);
    }

    /**
	 * 배후단지임대고지관리정보를 수정한다.
	 * @param vo GamHtldMngFeeMngtVO
	 * @exception Exception
	 */
	public void updateHtldMngFeeMngt(GamHtldMngFeeMngtVO vo) throws Exception {
		gamHtldMngFeeMngtDao.updateHtldMngFeeMngt(vo);
	}

	/**
	 * 배후단지임대고지관리 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대고지관리정보
	 * @exception Exception
	 */
    public GamHtldMngFeeMngtVO selectHtldMngFeeMngtInfo(GamHtldMngFeeMngtVO searchVO) throws Exception {
        return gamHtldMngFeeMngtDao.selectHtldMngFeeMngtInfo(searchVO);
    }

    /**
	 * 세입징수 등록건수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAnlrveLevCnt(GamHtldMngFeeMngtVO searchVO) throws Exception {
		return gamHtldMngFeeMngtDao.selectAnlrveLevCnt(searchVO);
	}

    /**
	 * 세입징수를 등록한다.
	 * @param vo GamHtldMngFeeMngtVO
	 * @exception Exception
	 */
	public void insertAnlrveLev(GamHtldMngFeeMngtVO vo) throws Exception {
		gamHtldMngFeeMngtDao.insertAnlrveLev(vo);
	}

	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamHtldMngFeeMngtVO
	 * @exception Exception
	 */
	public void deleteHtldMngFeeMngt(GamHtldMngFeeMngtVO vo) throws Exception {
		gamHtldMngFeeMngtDao.deleteHtldMngFeeMngt(vo);
	}

	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamHtldMngFeeMngtVO
	 * @exception Exception
	 */
	public void insertHtldMngFeeMngtLevReqest(GamHtldMngFeeMngtVO vo) throws Exception {
		gamHtldMngFeeMngtDao.insertHtldMngFeeMngtLevReqest(vo);
	}


	@Override
	public List selectNpticPrintInfo(Map searchVO) throws Exception {
		return gamHtldMngFeeMngtDao.selectNpticPrintInfo(searchVO);
	}

	@Override
	public List selectTaxNtcPrintInfo(Map searchVO) throws Exception {
		return gamHtldMngFeeMngtDao.selectTaxNtcPrintInfo(searchVO);
	}

	@Override
	public void updateAssetMngFeeMngtListDetail(GamHtldMngFeeMngtVO vo)
			throws Exception {
		gamHtldMngFeeMngtDao.updateAssetMngFeeMngtListDetail(vo);
	}

	@Override
	public List selectAssetMngFeeDetailList(GamHtldMngFeeMngtVO searchVO) {
		return gamHtldMngFeeMngtDao.selectAssetMngFeeDetailList(searchVO);
	}

	@Override
	public Map selectAssetMngFeeDetailMstPk(GamHtldMngFeeMngtVO searchVO) {
		return gamHtldMngFeeMngtDao.selectAssetMngFeeDetailMstPk(searchVO);
	}

	@Override
	public Map selectAssetMngFeeDetailSumPk(GamHtldMngFeeMngtVO searchVO) {
		return gamHtldMngFeeMngtDao.selectAssetMngFeeDetailSumPk(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldMngFeeMngtService#selectNoticeRequest(egovframework.rte.ygpa.gam.oper.htld.service.GamHtldMngFeeMngtVO)
	 */
	@Override
	public Map selectNoticeRequest(GamHtldMngFeeMngtVO searchVO) {
		return gamHtldMngFeeMngtDao.selectNoticeRequest(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldMngFeeMngtService#updateHtldMngFee(java.util.List, java.util.List)
	 */
	@Override
	public void updateHtldMngFee(List<GamHtldMngFeeMngtVO> createList,
			List<GamHtldMngFeeMngtVO> updateList) throws Exception {
		if(createList!=null) {
			for(int i=0; i<createList.size(); i++) {
				GamHtldMngFeeMngtVO vo=createList.get(i);
				int lcount = gamHtldMngFeeMngtDao.selectInsertHtldMngFeeCnt(vo);
				if(lcount==0) gamHtldMngFeeMngtDao.insertHtldMngFee(vo);
				else {
					vo.setUpdUsr(vo.getRegUsr());
					gamHtldMngFeeMngtDao.updateHtldMngFee(vo);
				}
			}
		}

		if(updateList!=null) {
			for(int i=0; i<updateList.size(); i++) {
				GamHtldMngFeeMngtVO vo=updateList.get(i);
				gamHtldMngFeeMngtDao.updateHtldMngFee(vo);
			}
		}
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldMngFeeMngtService#selectAssetLevReqestNticPk(egovframework.rte.ygpa.gam.oper.htld.service.GamHtldMngFeeMngtVO)
	 */
	@Override
	public Map selectAssetLevReqestNticPk(GamHtldMngFeeMngtVO searchVO) {
		// TODO Auto-generated method stub
		return gamHtldMngFeeMngtDao.selectNoticeRequestPk(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldMngFeeMngtService#sendLevReqestRevCollF(java.util.Map)
	 */
	@Override
	public void sendLevReqestRevCollF(Map<String, Object> vo) throws Exception {
		// TODO Auto-generated method stub
        Map map = gamHtldMngFeeMngtDao.selectNticNoAccnutYear(vo);
        vo.put("accnutYear", map.get("accnutYear"));
		vo.put("nticno", map.get("nticno"));
//        map = gamHtldMngFeeMngtDao.selectIntrNticNoAccnutYear(vo);
//		vo.put("intrFeeNticno", map.get("nticno"));
		vo.put("nhtIsueYn", "Y");

		gamHtldMngFeeMngtDao.insertNticRequestRevCollF(vo);
//		if(!"".equals(vo.get("intrAmnt")) || !"0".equals(vo.get("intrAmnt"))) {
//			gamHtldMngFeeMngtDao.insertIntrNticRequestRevCollF(vo);
//		}
		gamHtldMngFeeMngtDao.updateLevReqestIssueYn(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldMngFeeMngtService#selectNticPrintMaster(java.util.Map)
	 */
	@Override
	public List selectNticPrintMaster(Map searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamHtldMngFeeMngtDao.selectNticPrintMaster(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldMngFeeMngtService#selectNticPrintDetail(java.util.Map)
	 */
	@Override
	public List selectNticPrintDetail(Map searchVO) throws Exception {
		// TODO Auto-generated method stub
		return gamHtldMngFeeMngtDao.selectNticPrintDetail(searchVO);
	}

	@Override
	public void cancelNticRequest(Map<String, Object> vo) throws Exception {
		Map map =gamHtldMngFeeMngtDao.selectNticRequestRcvdTp(vo);	// 수납 여부를 조회한다.
		if("3".equals((String)map.get("rcvdTp"))) {	// 수납 여부 확인
			throw processException("fail.cancelNticIssue.msg");	// 이미 수납 된 자료는 고지 취소 불가 함.
		}
		if("Y".equals((String)map.get("billPrtYn"))) {
//			egiroPrintCancel(vo);    // 고지가 된 경우 고지 취소를 한다. 2014-08-13 eunsungj.
			vo.put("nhtPrintYn", "N");
			Map opt = new HashMap();
			opt.putAll(vo);
			updateNticPrintState(opt);
			return;
		}
		gamHtldMngFeeMngtDao.deleteNticRequestRevCollF(vo);	// 고지정보를 삭제한다.
		vo.put("accnutYear", "");
		vo.put("nticno", "");
		vo.put("nhtIsueYn", "N");
		gamHtldMngFeeMngtDao.updateLevReqestIssueYn(vo);	// 고지를 취소한다.
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldMngFeeMngtService#updateNticPrintState(java.util.Map)
	 */
	@Override
	public void updateNticPrintState(Map<String, Object> vo) throws Exception {
		if(vo.get("arrrgNo")!=null) { // 연체 고지
			gamHtldMngFeeMngtDao.updateUnpaidPrintState(vo);
		}
		else gamHtldMngFeeMngtDao.updateRevCollPrintState(vo);

		gamHtldMngFeeMngtDao.updateLevReqestPrintState(vo);

		if("Y".equals(vo.get("nhtPrintYn"))) {
			gamNticRequestMngtService.egiroPrint(vo);
		}
		else {
			gamNticRequestMngtService.egiroPrintCancel(vo);
		}
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldMngFeeMngtService#clearHtldMngFeeList(egovframework.rte.ygpa.gam.oper.htld.service.GamHtldMngFeeDefaultVO)
	 */
	@Override
	public void clearHtldMngFeeList(GamHtldMngFeeDefaultVO vo)
			throws Exception {
		gamHtldMngFeeMngtDao.clearHtldMngFeeList(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldMngFeeMngtService#selectHtldCofixPk(egovframework.rte.ygpa.gam.oper.htld.service.GamHtldMngFeeDefaultVO)
	 */
	@Override
	public Map selectHtldCofixPk(GamHtldMngFeeDefaultVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldMngFeeMngtService#deleteHtldMngFeeMngtList(egovframework.rte.ygpa.gam.oper.htld.service.GamHtldMngFeeDefaultVO)
	 */
	@Override
	public void deleteHtldMngFeeMngtList(GamHtldMngFeeDefaultVO vo)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
