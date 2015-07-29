package egovframework.rte.ygpa.gam.code.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface GamGisAssetCodeMngtService {

	String insertAssetCode(Map vo) throws Exception;

	Map updateAssetCode(Map vo) throws Exception;

    void deleteAssetCode(Map vo) throws Exception;

    void deleteAssetCodes(List vo) throws Exception;

    EgovMap selectAssetCode(Map vo) throws Exception;

    void updateAssetCodeGis(Map vo) throws Exception;

	List selectAssetCodeList(GamGisAssetCodeDefaultVO searchOpt);

	int selectAssetCodeListTotCnt(GamGisAssetCodeDefaultVO searchOpt);

	EgovMap selectAssetCodeOlnlp(Map vo) throws Exception;
}
