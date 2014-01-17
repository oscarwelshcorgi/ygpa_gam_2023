package egovframework.rte.ygpa.gam.popup.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupGisAssetsCdDefaultVO;
import egovframework.rte.ygpa.gam.popup.service.GamPopupGisAssetsCdService;
import egovframework.rte.ygpa.gam.popup.service.GamPopupGisAssetsCdVO;

/**
 * @Class Name : GisAssetsCdServiceImpl.java
 * @Description : GisAssetsCd Business Implement class
 * @Modification Information
 *
 * @author eunsungj
 * @since 2014-01-15
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Service("gamPopupGisAssetsCdService")
public class GamPopupGisAssetsCdServiceImpl extends AbstractServiceImpl implements
        GamPopupGisAssetsCdService {

    @Resource(name="gamPopupGisAssetsCdDAO")
    private GamPopupGisAssetsCdDAO gamPopupGisAssetsCdDAO;

    /** ID Generation */
    //@Resource(name="{egovGisAssetsCdIdGnrService}")
    //private EgovIdGnrService egovIdGnrService;

    /**
	 * GIS_ASSETS_CD_F을 조회한다.
	 * @param vo - 조회할 정보가 담긴 GamPopupGisAssetsCdVO
	 * @return 조회한 GIS_ASSETS_CD_F
	 * @exception Exception
	 */
    public GamPopupGisAssetsCdVO selectGisAssetsCd(GamPopupGisAssetsCdVO vo) throws Exception {
        GamPopupGisAssetsCdVO resultVO = gamPopupGisAssetsCdDAO.selectGisAssetsCd(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * GIS_ASSETS_CD_F 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return GIS_ASSETS_CD_F 목록
	 * @exception Exception
	 */
    public List selectGisAssetsCdList(GamPopupGisAssetsCdDefaultVO searchVO) throws Exception {
        return gamPopupGisAssetsCdDAO.selectGisAssetsCdList(searchVO);
    }

    /**
	 * GIS_ASSETS_CD_F 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return GIS_ASSETS_CD_F 총 갯수
	 * @exception
	 */
    public int selectGisAssetsCdListTotCnt(GamPopupGisAssetsCdDefaultVO searchVO) {
		return gamPopupGisAssetsCdDAO.selectGisAssetsCdListTotCnt(searchVO);
	}

}
