package egovframework.rte.ygpa.gam.asset.service;

import java.util.List;
import java.util.Map;

public interface GamAssetService {
	
	List selectGamAssetUseList(Map searchOpt);
	
	int selectGamAssetUseListTotCnt(Map searchOpt);
}
