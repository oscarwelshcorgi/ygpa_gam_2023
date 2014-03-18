package egovframework.rte.ygpa.gam.sample.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.asset.service.GisAssetsCdDefaultVO;
import egovframework.rte.ygpa.gam.asset.service.GisAssetsCdVO;

public interface GamGisCodeSampleService {

	String insertGisCode(GisAssetsCdVO vo) throws Exception;

    void updateGisCode(GisAssetsCdVO vo) throws Exception;

    void deleteGisCode(GisAssetsCdVO vo) throws Exception;

    EgovMap selectGisCode(GisAssetsCdVO vo) throws Exception;

	List selectGisCodeList(GisAssetsCdDefaultVO searchOpt);

	int selectGisCodeListTotCnt(GisAssetsCdDefaultVO searchVO);
}
