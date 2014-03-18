package egovframework.rte.ygpa.gam.sample.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.ygpa.gam.asset.service.GisAssetsCdDefaultVO;

public interface GamAssetSampleService {

	List selectGamAssetUseList(GisAssetsCdDefaultVO searchOpt);

	int selectGamAssetUseListTotCnt(GisAssetsCdDefaultVO searchOpt);
}
