package egovframework.rte.ygpa.gam.oper.htld.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldAssessVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentAttachFileVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentDefaultVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtLevReqestVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtVO;

/**
 * @Class Name : GamHtldRentMngtDao.java
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
@Repository("gamHtldRentMngtDao")
public class GamHtldRentMngtDao extends YGPAAbstractDAO {

	/**
	 * 배후단지임대관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
    public List selectHtldRentMngtList(GamHtldRentDefaultVO searchVO) throws Exception {
        return list("gamHtldRentMngtDao.selectHtldRentMngtList_D", searchVO);
    }

    /**
	 * 배후단지임대관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectHtldRentMngtListTotCnt(GamHtldRentDefaultVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldRentMngtDao.selectHtldRentMngtListTotCnt_S", searchVO);
    }

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
	public EgovMap selectHtldRentMngtSum(GamHtldRentDefaultVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamHtldRentMngtDao.selectHtldRentMngtSum_S", searchVO);
	}

	/**
	 * 배후단지 임대관리 항목을 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public GamHtldRentMngtVO selectHtldRentMngtDetailPk(GamHtldRentMngtVO searchVO) throws Exception {
		return (GamHtldRentMngtVO) selectByPk("gamHtldRentMngtDao.selectHtldRentMngtByPk_S", searchVO);
	}

	public Integer selectHtldRentMngtExist(GamHtldRentMngtVO searchVO) throws Exception {
		return (Integer) selectByPk("gamHtldRentMngtDao.selectHtldRentMngtExist_S", searchVO);
	}

	/**
	 * 배후단지임대 신규저장시 키값 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
	public GamHtldRentMngtVO selectHtldRentMngtMaxKey(GamHtldRentMngtVO searchVO) throws Exception {
		return (GamHtldRentMngtVO) selectByPk("gamHtldRentMngtDao.selectHtldRentMngtMaxKey_S", searchVO);
	}

	/**
	 * 배후단지 임대 연장시 데이터 가져오기
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public GamHtldRentMngtVO selectHtldRentMngtExtend(GamHtldRentMngtVO searchVO) throws Exception {
		return (GamHtldRentMngtVO) selectByPk("gamHtldRentMngtDao.selectHtldRentMngtExtend_S", searchVO);
	}

	/**
	 * 배후단지임대관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
    public List selectHtldRentMngtExtendDetailList(GamHtldRentMngtVO vo) throws Exception {
        return list("gamHtldRentMngtDao.selectHtldRentMngtExtendDetailList_D", vo);
    }

	/**
	 * 배후단지 임대정보를 등록한다.
	 * @param vo
	 */
	public String insertHtldRentMngt(GamHtldRentMngtVO vo){
		return (String) insert("gamHtldRentMngtDao.insertHtldRentMngt_S", vo);
	}

	/**
	 * 배후단지임대 정보를 수정한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	public void updateHtldRentMngt(GamHtldRentMngtVO vo){
		update("gamHtldRentMngtDao.updateHtldRentMngt_S", vo);
	}

	/**
	 * 배후단지임대 정보를 삭제한다.
	 * @param vo GamHtldRentMngtVO
	 */
	public void deleteHtldRentMngt(GamHtldRentMngtVO vo){
		delete("gamHtldRentMngtDao.deleteHtldRentMngt_S", vo);
	}

	/**
	 * 배후단지임대관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
    public List selectHtldRentMngtDetailList(GamHtldRentMngtVO vo) throws Exception {
        return list("gamHtldRentMngtDao.selectHtldRentMngtDetailList_D", vo);
    }

	/**
	 * 배후단지임대 상세를 등록한다.
	 * @param vo GamHtldRentMngtDetailVO
	 * @exception Exception
	 */
	public void insertHtldRentMngtDetail(GamHtldRentMngtDetailVO vo){
		insert("gamHtldRentMngtDao.insertHtldRentMngtDetail_S", vo);
	}

	/**
	 * 배후단지임대 상세를 수정한다.
	 * @param vo GamHtldRentMngtDetailVO
	 * @exception Exception
	 */
	public void updateHtldRentMngtDetail(GamHtldRentMngtDetailVO vo){
		update("gamHtldRentMngtDao.updateHtldRentMngtDetail_S", vo);
	}

	/**
	 * 배후단지임대 상세정보를 삭제한다.
	 * @param vo GamHtldRentMngtVO
	 */
	public void deleteHtldRentMngtDetail(GamHtldRentMngtDetailVO vo){
		delete("gamHtldRentMngtDao.deleteHtldRentMngtDetail_S", vo);
	}

    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
    public List selectHtldRentMngtFileList(GamHtldRentMngtVO searchVO) throws Exception {
        return list("gamHtldRentMngtDao.selectHtldRentMngtFileList_D", searchVO);
    }

    /**
	 * 파일을 등록한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	public void insertHtldRentMngtFile(GamHtldRentAttachFileVO vo){
		insert("gamHtldRentMngtDao.insertHtldRentMngtFile_S", vo);
	}

	/**
	 * 파일을 수정한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void updateHtldRentMngtFile(GamHtldRentAttachFileVO vo){
		insert("gamHtldRentMngtDao.updateHtldRentMngtFile_S", vo);
	}

	/**
	 * 파일을 삭제한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	public void deleteHtldRentMngtPhotoSingle(GamHtldRentAttachFileVO vo){
		delete("gamHtldRentMngtDao.deleteHtldRentMngtPhotoSingle_S", vo);
	}

	/**
	 * 연장 신청 항목을 복사한다.
	 * @param vo
	 */
	public String insertHtldRentMngtExend(GamHtldRentMngtVO vo){
		return (String)insert("gamHtldRentMngtDao.insertHtldRentMngtExtend_S", vo);
	}

	/**
	 * 연장신청 상세 항목을 복사한다.
	 * @param vo
	 */
	public void insertHtldRentMngtExendDetail(GamHtldRentMngtVO vo){
		insert("gamHtldRentMngtDao.insertHtldRentMngtExtendDetail_D", vo);
	}

    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectHtldRentMngtLevReqestCnt(GamHtldRentMngtVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldRentMngtDao.selectHtldRentMngtLevReqestCnt_S", vo);
    }

	/**
	 * 배후단지 임대 상세 정보를 모두 삭제한다.
	 * @param vo
	 */
	public void deleteHtldRentMngtDetails(GamHtldRentMngtVO vo){
		delete("gamHtldRentMngtDao.deleteHtldRentMngtDetails_D", vo);
	}

    /**
	 * 배후단지임대 사진정보를 삭제한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	public void deleteHtldRentMngtPhoto(GamHtldRentMngtVO vo){
		delete("gamHtldRentMngtDao.deleteHtldRentMngtPhoto_S", vo);
	}

	/**
	 * 승낙할 배후단지임대 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대정보
	 * @exception Exception
	 */
	public GamHtldRentMngtVO selectHtldRentMngtPrmisnInfo(GamHtldRentMngtVO searchVO) throws Exception {
		return (GamHtldRentMngtVO) selectByPk("gamHtldRentMngtDao.selectHtldRentMngtPrmisnInfo_S", searchVO);
	}

	/**
	 * 코멘트를 수정한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	public void updateHtldRentMngtComment(GamHtldRentMngtVO vo){
		update("gamHtldRentMngtDao.updateHtldRentMngtComment_S", vo);
	}

	/**
	 * 코픽스 이자율 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectCofixInfo() throws Exception {
        return list("gamHtldRentMngtDao.selectCofixInfo_S", null);
    }

	/**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
	public EgovMap selectHtldRentMngtCofixInfo(Map searchVO) throws Exception {
		return (EgovMap) selectByPk("gamHtldRentMngtDao.selectHtldRentMngtCofixInfo_S", searchVO);
	}

	/**
	 * 가장 마지막데이터의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
	public EgovMap selectHtldRentMngtCofixInfoMax(Map searchVO) throws Exception {
		return (EgovMap) selectByPk("gamHtldRentMngtDao.selectHtldRentMngtCofixInfoMax_S", searchVO);
	}

	public List selectChargeKndList(GamHtldRentMngtVO vo) throws Exception {
		return list("gamHtldRentMngtDao.selectChargeKndList_D", vo);
	}

    public List selectHtldAssessList(GamHtldRentDefaultVO searchVO) throws Exception {
        return list("gamHtldRentMngtDao.selectHtldAssessList_D", searchVO);
    }

	public EgovMap selectHtldAssessSum(GamHtldRentDefaultVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamHtldRentMngtDao.selectHtldAssessSum_S", searchVO);
	}

    public List selectHtldNticRcivList(GamHtldRentDefaultVO searchVO) throws Exception {
        return list("gamHtldRentMngtDao.selectHtldNticRcivList_D", searchVO);
    }

	public EgovMap selectHtldNticRcivSum(GamHtldRentDefaultVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamHtldRentMngtDao.selectHtldNticRcivSum_S", searchVO);
	}

	public List selectHtldAssessSeNmList() throws Exception {
        return list("gamHtldRentMngtDao.selectHtldAssessSeNmList_D", null);
	}

	public String insertHtldAssess(GamHtldAssessVO vo) throws Exception {
		return (String)insert("gamHtldRentMngtDao.insertHtldAssess_S", vo);
	}

	public int updateHtldAssess(GamHtldAssessVO vo) throws Exception {
		return update("gamHtldRentMngtDao.updateHtldAssess_S", vo);
	}

	public int deleteHtldAssess(GamHtldAssessVO vo) throws Exception {
		return delete("gamHtldRentMngtDao.deleteHtldAssess_S", vo);
	}

	/**
	 * 고지 자료를 등록한다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public String insertHtldRentBill(GamHtldRentMngtLevReqestVO vo) throws Exception {
        return (String)insert("gamHtldRentMngtDao.insertBillCreate", vo);
	}

	public void deleteBillByRentMngtVO(GamHtldRentMngtVO vo) throws Exception {
		delete("gamHtldRentMngtDao.deleteBillByRentMngtVO_D", vo);
	}

	/**
	 * 고지된 항목이 있는지 조회 한다.
	 * @param searchVO
	 * @return
	 */
	public int selectHtldRentMngtNticLevReqestCnt(GamHtldRentMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldRentMngtDao.selectHtldRentMngtNticLevReqestCnt_S", searchVO);
    }

	/**
	 * 전체 사용료를 등록한다.
	 */
    public String insertBillCreateOnce(GamHtldRentMngtVO vo) throws Exception {
    	Map<String, String> map = new HashMap<String,String>();
    	map.put("chrgeKnd", vo.getChrgeKnd());
    	map.put("regUsr", vo.getRegUsr());
    	map.put("deptcd", vo.getDeptcd());
    	map.put("prtAtCode", vo.getPrtAtCode());
    	map.put("mngYear", vo.getPrtAtCode());
    	map.put("mngNo", vo.getMngNo());
    	map.put("mngCnt", vo.getMngCnt());
    	map.put("entrpscd", vo.getEntrpscd());
    	map.put("taxtSe", vo.getTaxtSe());
    	map.put("rm", vo.getRm());
    	map.put("nticMth", vo.getNticMth());
    	map.put("regUsr", vo.getRegUsr());
    	//map.put("blTpNum", "1");
    	map.put("grFee",  vo.getGrFee());
    	map.put("grUsagePdFrom", vo.getGrUsagePdFrom());
    	map.put("grUsagePdTo", vo.getGrUsagePdTo());
        return (String)insert("gamHtldRentMngtDao.insertBillCreateOnce", map);
    }

    /**
	 * 월 사용료를 등록한다.
	 */
    public String insertBillCreatePreMonth(GamHtldRentMngtVO vo) throws Exception {
    	Map<String, String> map = new HashMap<String,String>();
    	map.put("chrgeKnd", vo.getChrgeKnd());
    	map.put("regUsr", vo.getRegUsr());
    	map.put("deptcd", vo.getDeptcd());
    	map.put("prtAtCode", vo.getPrtAtCode());
    	map.put("mngYear", vo.getPrtAtCode());
    	map.put("mngNo", vo.getMngNo());
    	map.put("mngCnt", vo.getMngCnt());
    	map.put("entrpscd", vo.getEntrpscd());
    	map.put("taxtSe", vo.getTaxtSe());
    	map.put("rm", vo.getRm());
    	map.put("nticMth", vo.getNticMth());
    	map.put("regUsr", vo.getRegUsr());
    	map.put("grFee",  vo.getGrFee());
    	map.put("grUsagePdFrom", vo.getGrUsagePdFrom());
    	map.put("grUsagePdTo", vo.getGrUsagePdTo());
		map.put("blTpNum", "1");
        return (String)insert("gamHtldRentMngtDao.insertBillCreateMore", map);
    }

    /**
	 * 3분납 사용료를 등록한다.
	 */
    public String insertBillCreatePreThird(GamHtldRentMngtVO vo) throws Exception {
    	Map<String, String> map = new HashMap<String,String>();
    	map.put("chrgeKnd", vo.getChrgeKnd());
    	map.put("regUsr", vo.getRegUsr());
    	map.put("deptcd", vo.getDeptcd());
    	map.put("prtAtCode", vo.getPrtAtCode());
    	map.put("mngYear", vo.getPrtAtCode());
    	map.put("mngNo", vo.getMngNo());
    	map.put("mngCnt", vo.getMngCnt());
    	map.put("entrpscd", vo.getEntrpscd());
    	map.put("taxtSe", vo.getTaxtSe());
    	map.put("rm", vo.getRm());
    	map.put("nticMth", vo.getNticMth());
    	map.put("regUsr", vo.getRegUsr());
    	map.put("grFee",  vo.getGrFee());
    	map.put("grUsagePdFrom", vo.getGrUsagePdFrom());
    	map.put("grUsagePdTo", vo.getGrUsagePdTo());
		map.put("blTpNum", "4");
        return (String)insert("gamHtldRentMngtDao.insertBillCreateMore", map);
    }

    /**
	 * 분기별 사용료를 등록한다.
	 */
    public String insertBillCreatePreQuater(GamHtldRentMngtVO vo) throws Exception {
//		vo.put("blTpNum", "3");
    	Map<String, String> map = new HashMap<String,String>();
    	map.put("chrgeKnd", vo.getChrgeKnd());
    	map.put("regUsr", vo.getRegUsr());
    	map.put("deptcd", vo.getDeptcd());
    	map.put("prtAtCode", vo.getPrtAtCode());
    	map.put("mngYear", vo.getPrtAtCode());
    	map.put("mngNo", vo.getMngNo());
    	map.put("mngCnt", vo.getMngCnt());
    	map.put("entrpscd", vo.getEntrpscd());
    	map.put("taxtSe", vo.getTaxtSe());
    	map.put("rm", vo.getRm());
    	map.put("nticMth", vo.getNticMth());
    	map.put("regUsr", vo.getRegUsr());
    	map.put("grFee",  vo.getGrFee());
    	map.put("grUsagePdFrom", vo.getGrUsagePdFrom());
    	map.put("grUsagePdTo", vo.getGrUsagePdTo());
		map.put("blTpNum", "3");
        return (String)insert("gamHtldRentMngtDao.insertBillCreateMore", map);
    }

    /**
	 * 반기별 사용료를 등록한다.
	 */
    public String insertBillCreatePreHalf(GamHtldRentMngtVO vo) throws Exception {
    	Map<String, String> map = new HashMap<String,String>();
    	map.put("chrgeKnd", vo.getChrgeKnd());
    	map.put("regUsr", vo.getRegUsr());
    	map.put("deptcd", vo.getDeptcd());
    	map.put("prtAtCode", vo.getPrtAtCode());
    	map.put("mngYear", vo.getPrtAtCode());
    	map.put("mngNo", vo.getMngNo());
    	map.put("mngCnt", vo.getMngCnt());
    	map.put("entrpscd", vo.getEntrpscd());
    	map.put("taxtSe", vo.getTaxtSe());
    	map.put("rm", vo.getRm());
    	map.put("nticMth", vo.getNticMth());
    	map.put("regUsr", vo.getRegUsr());
    	map.put("grFee",  vo.getGrFee());
    	map.put("grUsagePdFrom", vo.getGrUsagePdFrom());
    	map.put("grUsagePdTo", vo.getGrUsagePdTo());
		map.put("blTpNum", "6");
        return (String)insert("gamHtldRentMngtDao.insertBillCreateMore", map);
    }

    /**
	 * 연별 사용료를 등록한다.
	 */
    public String insertBillCreatePreYear(GamHtldRentMngtVO vo) throws Exception {
    	Map<String, String> map = new HashMap<String,String>();
    	map.put("chrgeKnd", vo.getChrgeKnd());
    	map.put("regUsr", vo.getRegUsr());
    	map.put("deptcd", vo.getDeptcd());
    	map.put("prtAtCode", vo.getPrtAtCode());
    	map.put("mngYear", vo.getPrtAtCode());
    	map.put("mngNo", vo.getMngNo());
    	map.put("mngCnt", vo.getMngCnt());
    	map.put("entrpscd", vo.getEntrpscd());
    	map.put("taxtSe", vo.getTaxtSe());
    	map.put("rm", vo.getRm());
    	map.put("nticMth", vo.getNticMth());
    	map.put("regUsr", vo.getRegUsr());
    	map.put("grFee",  vo.getGrFee());
    	map.put("grUsagePdFrom", vo.getGrUsagePdFrom());
    	map.put("grUsagePdTo", vo.getGrUsagePdTo());
		map.put("blTpNum", "12");
        return (String)insert("gamHtldRentMngtDao.insertBillCreateMore", map);
    }

	/**
	 * @param createList
	 * @return
	 */
	public void applyHtldAssessList(GamHtldAssessVO vo) throws Exception {
		update("gamHtldRentMngtDao.applyHtldAssess_D", vo);
	}

}