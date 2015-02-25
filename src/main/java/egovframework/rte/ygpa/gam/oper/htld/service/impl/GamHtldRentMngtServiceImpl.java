package egovframework.rte.ygpa.gam.oper.htld.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.Globals;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldAssessVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentAttachFileVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentDefaultVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtLevReqestVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtService;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtVO;

/**
 * @Class Name : GamHtldRentMngtServiceImpl.java
 * @Description : 배후단지임대목록관리
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamHtldRentMngtService")
public class GamHtldRentMngtServiceImpl extends AbstractServiceImpl implements GamHtldRentMngtService {

	@Resource(name="gamHtldRentMngtDao")
    private GamHtldRentMngtDao gamHtldRentMngtDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 배후단지임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectHtldRentMngtList(GamHtldRentDefaultVO searchVO) throws Exception {
        return gamHtldRentMngtDao.selectHtldRentMngtList(searchVO);
    }

    /**
	 * 배후단지임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectHtldRentMngtListTotCnt(GamHtldRentDefaultVO searchVO) throws Exception {
		return gamHtldRentMngtDao.selectHtldRentMngtListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    public EgovMap selectHtldRentMngtSum(GamHtldRentDefaultVO searchVO) throws Exception {
        return gamHtldRentMngtDao.selectHtldRentMngtSum(searchVO);
    }

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtService#selectHtldRentMngtDetailPk(egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtVO)
	 */
	@Override
	public GamHtldRentMngtVO selectHtldRentMngtDetailPk(GamHtldRentMngtVO searchVO)
			throws Exception {
		return gamHtldRentMngtDao.selectHtldRentMngtDetailPk(searchVO);
	}

    /**
     * 배후단지 임대 정보를 등록한다.
     * @param vo
     * @throws Exception
     */
	public GamHtldRentMngtVO insertHtldRentMngt(GamHtldRentMngtVO rentVo, List<GamHtldRentMngtDetailVO> createList) throws Exception {
		int i=0;
		String updtId=rentVo.getUpdUsr();
		//String deptCd=rentVo.getDeptcd();

		GamHtldRentMngtVO newRentVo = gamHtldRentMngtDao.selectHtldRentMngtMaxKey(rentVo);

		rentVo.setMngYear(newRentVo.getMngYear());
		rentVo.setMngNo(newRentVo.getMngNo());
		rentVo.setMngCnt(newRentVo.getMngCnt());

		GamHtldAssessVO assessVo = new GamHtldAssessVO();

		gamHtldRentMngtDao.insertHtldRentMngt(rentVo);

		assessVo.setPrtAtCode(rentVo.getPrtAtCode());
		assessVo.setMngYear(rentVo.getMngYear());
		assessVo.setMngNo(rentVo.getMngNo());
		assessVo.setMngCnt(rentVo.getMngCnt());
		assessVo.setApplcPrice(rentVo.getApplcPrice());
		assessVo.setDtFrom(rentVo.getGrUsagePdFrom());
		gamHtldRentMngtDao.insertHtldAssess(assessVo);

		for(i=0; i<createList.size(); i++) {
			GamHtldRentMngtDetailVO d= createList.get(i);
			d.setRegUsr(updtId);
			d.setPrtAtCode(rentVo.getPrtAtCode());
			d.setMngYear(rentVo.getMngYear());
			d.setMngNo(rentVo.getMngNo());
			d.setMngCnt(rentVo.getMngCnt());
			gamHtldRentMngtDao.insertHtldRentMngtDetail(d);
		}

		return rentVo;
	}

    /**
	 * 배후단지임대 연장 신청을 등록한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
    public GamHtldRentMngtVO insertHtldRentMngtExtend(GamHtldRentMngtVO vo) throws Exception {
    	GamHtldRentMngtVO gamExtendVo = gamHtldRentMngtDao.selectHtldRentMngtExtend(vo);
    	GamHtldRentMngtVO gamVo = new GamHtldRentMngtVO();
    	gamVo.setQuayGroupCd(gamExtendVo.getQuayGroupCd());
    	gamVo.setPrtAtCode(gamExtendVo.getPrtAtCode());
    	gamVo.setMngYear(gamExtendVo.getMngYear());
    	gamVo.setMngNo(gamExtendVo.getMngNo());
    	gamVo.setMngCnt(gamExtendVo.getMngCnt());
    	Integer n = gamHtldRentMngtDao.selectHtldRentMngtExist(gamExtendVo);	// 연장된 자료가 존재하는지
    	if(n>0) processException("gam.asset.rent.extend.reject1");
		gamHtldRentMngtDao.insertHtldRentMngt(gamExtendVo);
		List detailList = gamHtldRentMngtDao.selectHtldRentMngtExtendDetailList(vo);
		for(int i=0; i<detailList.size(); i++) {
			GamHtldRentMngtDetailVO detail = (GamHtldRentMngtDetailVO)detailList.get(i);
			detail.setRegUsr(gamExtendVo.getRegUsr());
			detail.setMngNo(gamExtendVo.getMngNo());
			gamHtldRentMngtDao.insertHtldRentMngtDetail(detail);
		}
		return gamExtendVo;
	}

	/**
	 * 배후단지임대정보를 수정한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	public void updateHtldRentMngt(GamHtldRentMngtVO rentVo, List<GamHtldRentMngtDetailVO> createList,  List<GamHtldRentMngtDetailVO> updateList,  List<GamHtldRentMngtDetailVO> deleteList) throws Exception {
		int i=0;
		String updtId=rentVo.getUpdUsr();
		//String deptCd=rentVo.getDeptcd();

		for(i=0; i<deleteList.size(); i++) {
			GamHtldRentMngtDetailVO d= deleteList.get(i);
			gamHtldRentMngtDao.deleteHtldRentMngtDetail(d);
		}
		for(i=0; i<updateList.size(); i++) {
			GamHtldRentMngtDetailVO d= updateList.get(i);
			d.setUpdUsr(updtId);
			gamHtldRentMngtDao.updateHtldRentMngtDetail(d);
		}
		for(i=0; i<createList.size(); i++) {
			GamHtldRentMngtDetailVO d= createList.get(i);
			d.setRegUsr(updtId);
			d.setPrtAtCode(rentVo.getPrtAtCode());
			d.setMngYear(rentVo.getMngYear());
			d.setMngNo(rentVo.getMngNo());
			d.setMngCnt(rentVo.getMngCnt());
			d.setQuayGroupCd(rentVo.getQuayGroupCd());
			gamHtldRentMngtDao.insertHtldRentMngtDetail(d);
		}

		gamHtldRentMngtDao.updateHtldRentMngt(rentVo);
	}

	/**
	 * 배후단지임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectHtldRentMngtDetailList(GamHtldRentMngtVO vo) throws Exception {
        return gamHtldRentMngtDao.selectHtldRentMngtDetailList(vo);
    }

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectHtldRentMngtLevReqestCnt(GamHtldRentMngtVO vo) throws Exception {
		return gamHtldRentMngtDao.selectHtldRentMngtLevReqestCnt(vo);
	}

    /**
	 * 배후단지임대 정보를 삭제한다.
	 * @param vo GamHtldRentMngtDetailVO
	 * @exception Exception
	 */
	public void deleteHtldRentMngt(GamHtldRentMngtVO vo) throws Exception {
		GamHtldRentMngtVO detail = gamHtldRentMngtDao.selectHtldRentMngtDetailPk(vo);

		if("Y".equals(detail.getPrmisnYn())) {
			processException("gam.asset.rent.delete.reject1");
		}

		gamHtldRentMngtDao.deleteHtldRentMngtPhoto(vo); //배후단지임대 사진정보 삭제

		gamHtldRentMngtDao.deleteHtldRentMngtDetails(vo); //배후단지임대 상세정보 삭제

		gamHtldRentMngtDao.deleteHtldRentMngt(vo); // 배후단지임대정보 삭제
	}

	/**
	 * 승낙할 배후단지임대 정보 조회.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대정보
	 * @exception Exception
	 */
    public GamHtldRentMngtVO selectHtldRentMngtPrmisnInfo(GamHtldRentMngtVO searchVO) throws Exception {
        return gamHtldRentMngtDao.selectHtldRentMngtPrmisnInfo(searchVO);
    }

    /**
	 * 코멘트를 수정한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	public void updateHtldRentMngtComment(GamHtldRentMngtVO vo) throws Exception {
		gamHtldRentMngtDao.updateHtldRentMngtComment(vo);
	}

	/**
   	 * 코픽스 이자율 목록을 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return list
   	 * @exception Exception
   	 */
    public List selectCofixInfo() throws Exception {
        return gamHtldRentMngtDao.selectCofixInfo();
    }

    /**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    public EgovMap selectHtldRentMngtCofixInfo(Map searchVO) throws Exception {
        return gamHtldRentMngtDao.selectHtldRentMngtCofixInfo(searchVO);
    }

    /**
	 * 가장 마지막데이터의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록
	 * @exception Exception
	 */
    public EgovMap selectHtldRentMngtCofixInfoMax(Map searchVO) throws Exception {
        return gamHtldRentMngtDao.selectHtldRentMngtCofixInfoMax(searchVO);
    }

    /* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtService#selectChargeKndList()
	 */
	@Override
	public List selectChargeKndList(GamHtldRentMngtVO vo) throws Exception {
		// TODO Auto-generated method stub
		return gamHtldRentMngtDao.selectChargeKndList(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtService#selectHtldRentMngtFileList(egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentAttachFileVO)
	 */
	@Override
	public List selectHtldRentMngtFileList(GamHtldRentMngtVO vo)
			throws Exception {
		return gamHtldRentMngtDao.selectHtldRentMngtFileList(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtService#selectHtldAssessList(egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentDefaultVO)
	 */
	@Override
	public List selectHtldAssessList(GamHtldRentDefaultVO searchVO)
			throws Exception {
        return gamHtldRentMngtDao.selectHtldAssessList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtService#selectHtldAssessSum(egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentDefaultVO)
	 */
	@Override
	public EgovMap selectHtldAssessSum(GamHtldRentDefaultVO searchVO)
			throws Exception {
        return gamHtldRentMngtDao.selectHtldAssessSum(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtService#selectHtldNticRcivList(egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentDefaultVO)
	 */
	@Override
	public List selectHtldNticRcivList(GamHtldRentDefaultVO searchVO)
			throws Exception {
        return gamHtldRentMngtDao.selectHtldNticRcivList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtService#selectHtldNticRcivSum(egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentDefaultVO)
	 */
	@Override
	public EgovMap selectHtldNticRcivSum(GamHtldRentDefaultVO searchVO)
			throws Exception {
        return gamHtldRentMngtDao.selectHtldNticRcivSum(searchVO);
	}

}