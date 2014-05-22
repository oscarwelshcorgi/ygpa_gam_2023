package egovframework.rte.ygpa.gam.code.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.code.service.GamGisAssetCodeMngtService;
import egovframework.rte.ygpa.gam.code.service.GamGisAssetCodeVO;

@Service("gamGisAssetCodeMngtService")
public class GamGisAssetCodeMngtServiceImpl extends AbstractServiceImpl implements GamGisAssetCodeMngtService {

    @Resource(name="gamGisAssetCodeMngtDao")
    private GamGisAssetCodeMngtDao gamGisAssetCodeMngtDao;


	@Override
	public String insertAssetCode(Map vo) throws Exception {
		// TODO Auto-generated method stub
		Map result=gamGisAssetCodeMngtDao.selectGetNewAssetCode(vo);
		vo.put("gisAssetsCd", result.get("gisAssetsCd"));
		vo.put("gisAssetsSubCd", result.get("gisAssetsSubCd"));
    	log.debug("insert new gisAssetscd : "+result.get("gisAssetsCd")+"-"+result.get("gisAssetsSubCd"));

		return gamGisAssetCodeMngtDao.insertAssetCode(vo);
	}

	@Override
	public Map updateAssetCode(Map vo) throws Exception {
		String gisAssetsCd=(String)vo.get("gisAssetsCd");
		String newGisAssetsCd=(String)vo.get("gisAssetsPrprtySeCd")+(String)vo.get("gisAssetsLocCd")+(String)vo.get("gisAssetsQuayCd");

		if(!gisAssetsCd.equals(newGisAssetsCd)) {
			// change pk data;
			vo.put("gisAssetsCd", newGisAssetsCd);
			String newGisAssetsSubCd=gamGisAssetCodeMngtDao.selectAssetCodeGetSubCode(vo);
			vo.put("gisAssetsCd", gisAssetsCd);
			vo.put("newGisAssetsCd", newGisAssetsCd);
			vo.put("newGisAssetsSubCd", newGisAssetsSubCd);
			gamGisAssetCodeMngtDao.changeAssetCodePkGisAssetsPhoto(vo);
			gamGisAssetCodeMngtDao.changeAssetCodePkPrtFcltyPhoto(vo);
			gamGisAssetCodeMngtDao.changeAssetCodePkPrtFcltyCd(vo);
			gamGisAssetCodeMngtDao.changeAssetCodePkLevReqestPlotByFee(vo);
			gamGisAssetCodeMngtDao.changeAssetCodePkAssetsRentDetail(vo);
			gamGisAssetCodeMngtDao.changeAssetCodePk(vo);
			vo.put("gisAssetsCd", newGisAssetsCd);
			vo.put("gisAssetsSubCd", newGisAssetsSubCd);
		}
		gamGisAssetCodeMngtDao.updateAssetCode(vo);
		return vo;
	}

	@Override
	public void deleteAssetCode(Map vo) throws Exception {
		// TODO Auto-generated method stub
		gamGisAssetCodeMngtDao.deleteAssetCode(vo);
	}

	@Override
	public EgovMap selectAssetCode(Map vo) throws Exception {
		// TODO Auto-generated method stub
		return gamGisAssetCodeMngtDao.selectAssetCode(vo);
	}

	@Override
	public void updateAssetCodeGis(Map vo) throws Exception {
		// TODO Auto-generated method stub
		gamGisAssetCodeMngtDao.updateAssetCode(vo);
	}

	@Override
	public List selectAssetCodeList(GamGisAssetCodeVO searchOpt) {
		// TODO Auto-generated method stub
		return gamGisAssetCodeMngtDao.selectGamAssetCodeList(searchOpt);
	}

	@Override
	public int selectAssetCodeListTotCnt(GamGisAssetCodeVO searchOpt) {
		// TODO Auto-generated method stub
		return gamGisAssetCodeMngtDao.selectGamAssetCodeListTotCnt(searchOpt);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.code.service.GamGisAssetCodeMngtService#selectAssetCodeOlnlp(java.util.Map)
	 */
	@Override
	public EgovMap selectAssetCodeOlnlp(Map vo) throws Exception {
		// TODO Auto-generated method stub
		return gamGisAssetCodeMngtDao.selectAssetCodeOlnlp(vo);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.code.service.GamGisAssetCodeMngtService#deleteAssetCodes(java.util.List)
	 */
	@Override
	public void deleteAssetCodes(List vo) throws Exception {
		Map item;
		for(int i=0; i<vo.size(); i++) {
			item = (Map)vo.get(i);
			gamGisAssetCodeMngtDao.deleteAssetCode(item);
		}
	}

}
