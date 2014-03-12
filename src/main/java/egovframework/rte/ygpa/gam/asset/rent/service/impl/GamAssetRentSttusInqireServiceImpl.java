package egovframework.rte.ygpa.gam.asset.rent.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentMngtVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentSttusInqireService;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentSttusInqireVO;

/**
 * @Class Name : GamAssetRentSttusInqireServiceImpl.java
 * @Description : 자산임대현황조회 Business Implement class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-22
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamAssetRentSttusInqireService")

public class GamAssetRentSttusInqireServiceImpl  extends AbstractServiceImpl implements GamAssetRentSttusInqireService {

	@Resource(name="gamAssetRentSttusInqireDao")
    private GamAssetRentSttusInqireDao gamAssetRentSttusInqireDao;

	/**
	 * 자산임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectAssetRentList(GamAssetRentSttusInqireVO searchVO) throws Exception {
        return gamAssetRentSttusInqireDao.selectAssetRentList(searchVO);
    }

    /**
	 * 자산임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetRentListTotCnt(GamAssetRentSttusInqireVO searchVO) throws Exception {
		return gamAssetRentSttusInqireDao.selectAssetRentListTotCnt(searchVO);
	}

    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    public GamAssetRentSttusInqireVO selectAssetRentSum(GamAssetRentSttusInqireVO searchVO) throws Exception {
        return gamAssetRentSttusInqireDao.selectAssetRentSum(searchVO);
    }

	/**
	 * 자산임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectAssetRentDetailList(GamAssetRentSttusInqireVO vo) throws Exception {
        return gamAssetRentSttusInqireDao.selectAssetRentDetailList(vo);
    }

    /**
	 * 자산임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetRentDetailListTotCnt(GamAssetRentSttusInqireVO vo) throws Exception {
		return gamAssetRentSttusInqireDao.selectAssetRentDetailListTotCnt(vo);
	}
    
    /**
	 * 파일 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectAssetRentFileList(GamAssetRentSttusInqireVO searchVO) throws Exception {
        return gamAssetRentSttusInqireDao.selectAssetRentFileList(searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectAssetRentFileListTotCnt(GamAssetRentSttusInqireVO searchVO) throws Exception {
		return gamAssetRentSttusInqireDao.selectAssetRentFileListTotCnt(searchVO);
	}
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void insertAssetRentFile(GamAssetRentSttusInqireVO vo) throws Exception {
		gamAssetRentSttusInqireDao.insertAssetRentFile(vo);
	}
	
	/**
	 * 파일을 삭제한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void deleteAssetRentPhotoSingle(GamAssetRentSttusInqireVO vo) throws Exception {
		gamAssetRentSttusInqireDao.deleteAssetRentPhotoSingle(vo);
	}

}
