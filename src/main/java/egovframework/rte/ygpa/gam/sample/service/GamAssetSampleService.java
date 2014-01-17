package egovframework.rte.ygpa.gam.sample.service;

import java.util.List;
import java.util.Map;

public interface GamAssetSampleService {
	
	List selectGamAssetUseList(Map searchOpt);
	
	int selectGamAssetUseListTotCnt(Map searchOpt);
}
