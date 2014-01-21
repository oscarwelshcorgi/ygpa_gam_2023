package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtOperRentMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtOperRentMngtService;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtOperRentMngtVO;

/**
 * @Class Name : GamPrtOperRentMngtServiceImpl.java
 * @Description : 항만시설사용목록관리 Implement class
 * @Modification Information
 *
 * @author 도명호
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Service("gamPrtOperRentMngtService")
public class GamPrtOperRentMngtServiceImpl implements GamPrtOperRentMngtService {
	
	@Resource(name="gamPrtOperRentMngtDao")
    private GamPrtOperRentMngtDao gamPrtOperRentMngtDao;

	/**
	 * 항만시설 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtOperRentMngtList(GamPrtOperRentMngtVO searchVO) throws Exception {
        return gamPrtOperRentMngtDao.selectPrtOperRentMngtList(searchVO);
    }

    /**
	 * 항만시설 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtOperRentMngtListTotCnt(GamPrtOperRentMngtVO searchVO) throws Exception {
		return gamPrtOperRentMngtDao.selectPrtOperRentMngtListTotCnt(searchVO);
	}
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamPrtOperRentMngtVO selectPrtOperRentMngtSum(GamPrtOperRentMngtVO searchVO) throws Exception {
        return gamPrtOperRentMngtDao.selectPrtOperRentMngtSum(searchVO);
    }
    
    /**
	 * 항만시설 최초 신청을 등록한다.
	 * @param vo GamPrtOperRentMngtVO
	 * @exception Exception
	 */
	public void insertPrtOperRentMngtFirst(GamPrtOperRentMngtVO vo) throws Exception {
		gamPrtOperRentMngtDao.insertPrtOperRentMngtFirst(vo);
	}
	
    /**
	 * 관리번호(MAX) 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamPrtOperRentMngtVO selectPrtOperRentMngtMaxNo(GamPrtOperRentMngtVO searchVO) throws Exception {
        return gamPrtOperRentMngtDao.selectPrtOperRentMngtMaxNo(searchVO);
    }
    
    /**
	 * 항만시설 연장 신청을 등록한다.
	 * @param vo GamPrtOperRentMngtVO
	 * @exception Exception
	 */
	public void insertPrtOperRentMngtRenew(GamPrtOperRentMngtVO vo) throws Exception {
		gamPrtOperRentMngtDao.insertPrtOperRentMngtRenew(vo);
	}
	
	/**
	 * 항만시설 정보를 수정한다.
	 * @param vo GamPrtOperRentMngtVO
	 * @exception Exception
	 */
	public void updatePrtOperRentMngt(GamPrtOperRentMngtVO vo) throws Exception {
		gamPrtOperRentMngtDao.updatePrtOperRentMngt(vo);
	}
	
	/**
	 * 항만시설 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtOperRentMngtDetailList(GamPrtOperRentMngtVO vo) throws Exception {
        return gamPrtOperRentMngtDao.selectPrtOperRentMngtDetailList(vo);
    }

    /**
	 * 항만시설 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtOperRentMngtDetailListTotCnt(GamPrtOperRentMngtVO vo) throws Exception {
		return gamPrtOperRentMngtDao.selectPrtOperRentMngtDetailListTotCnt(vo);
	}
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param VO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectPrtOperRentMngtLevReqestCnt(GamPrtOperRentMngtVO vo) throws Exception {
		return gamPrtOperRentMngtDao.selectPrtOperRentMngtLevReqestCnt(vo);
	}
    
    /**
	 * 항만시설 정보를 삭제한다.
	 * @param vo GamPrtOperRentMngtDetailVO
	 * @exception Exception
	 */
	public void deletePrtOperRentMngt(GamPrtOperRentMngtVO vo) throws Exception {
		
		gamPrtOperRentMngtDao.deletePrtOperRentMngtPhoto(vo); //자산임대 사진정보 삭제
		
		gamPrtOperRentMngtDao.deletePrtOperRentMngtDetail(vo); //자산임대 상세정보 삭제
		
		gamPrtOperRentMngtDao.deletePrtOperRentMngt(vo); // 자산임대정보 삭제
	}
    
	/**
	 * 항만시설 상세정보를 삭제한다.
	 * @param vo GamPrtOperRentMngtDetailVO
	 * @exception Exception
	 */
	public void deletePrtOperRentMngtDetail(GamPrtOperRentMngtVO vo) throws Exception {
		gamPrtOperRentMngtDao.deletePrtOperRentMngtDetail(vo);
	}
	
	/**
	 * 항만시설 상세를 등록한다.
	 * @param vo GamPrtOperRentMngtDetailVO
	 * @exception Exception
	 */
	public void insertPrtOperRentMngtDetail(GamPrtOperRentMngtDetailVO vo) throws Exception {
		gamPrtOperRentMngtDao.insertPrtOperRentMngtDetail(vo);
	}
	
	/**
	 * 항만시설 상세를 수정한다.
	 * @param vo GamPrtOperRentMngtDetailVO
	 * @exception Exception
	 */
	public void updatePrtOperRentMngtDetail(GamPrtOperRentMngtDetailVO vo) throws Exception {
		gamPrtOperRentMngtDao.updatePrtOperRentMngtDetail(vo);
	}
	
	/**
	 * 항만시설 상세를 삭제한다.
	 * @param vo GamPrtOperRentMngtDetailVO
	 * @exception Exception
	 */
	public void deletePrtOperRentMngtDetail2(GamPrtOperRentMngtDetailVO vo) throws Exception {
		gamPrtOperRentMngtDao.deletePrtOperRentMngtDetail2(vo);
	}

}