package egovframework.rte.ygpa.gam.sample.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface GamGisCodeService {

	String insertGisCode(Map vo) throws Exception;

    void updateGisCode(Map vo) throws Exception;

    void deleteGisCode(Map vo) throws Exception;

    EgovMap selectGisCode(Map vo) throws Exception;

	List selectGisCodeList(Map searchOpt);
	
	int selectGisCodeListTotCnt(Map searchOpt);
}
