package egovframework.rte.ygpa.gam.code.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface GamGisAssetCodeMngtService {

	String insertAssetCode(Map vo) throws Exception;

    void updateAssetCode(Map vo) throws Exception;

    void deleteAssetCode(Map vo) throws Exception;

    EgovMap selectAssetCode(Map vo) throws Exception;

    void updateAssetCodeGis(Map vo) throws Exception;

	List selectAssetCodeList(GamGisAssetCodeVO searchOpt);

	int selectAssetCodeListTotCnt(GamGisAssetCodeVO searchOpt);
}
