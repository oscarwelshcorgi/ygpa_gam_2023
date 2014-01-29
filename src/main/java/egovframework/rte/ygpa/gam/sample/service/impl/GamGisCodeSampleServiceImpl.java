package egovframework.rte.ygpa.gam.sample.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.sample.service.GamGisCodeSampleService;

@Service("gamGisCodeSampleService")
public class GamGisCodeSampleServiceImpl extends AbstractServiceImpl implements GamGisCodeSampleService {

    @Resource(name="gamGisCodeSampleDao")
    private GamGisCodeSampleDao gamGisCodeDao;


	@Override
	public String insertGisCode(Map vo) throws Exception {
		// TODO Auto-generated method stub
		return gamGisCodeDao.insertGisCode(vo);
	}

	@Override
	public void updateGisCode(Map vo) throws Exception {
		// TODO Auto-generated method stub
		gamGisCodeDao.updateGisCode(vo);
	}

	@Override
	public void deleteGisCode(Map vo) throws Exception {
		// TODO Auto-generated method stub
		gamGisCodeDao.deleteGisCode(vo);
	}

	@Override
	public EgovMap selectGisCode(Map vo) throws Exception {
		// TODO Auto-generated method stub
		return gamGisCodeDao.selectGisCode(vo);
	}

	@Override
	public List selectGisCodeList(Map searchOpt) {
		// TODO Auto-generated method stub
		return gamGisCodeDao.selectGisCodeList(searchOpt);
	}

	@Override
	public int selectGisCodeListTotCnt(Map searchOpt) {
		// TODO Auto-generated method stub
		return gamGisCodeDao.selectGisCodeListTotCnt(searchOpt);
	}

}
