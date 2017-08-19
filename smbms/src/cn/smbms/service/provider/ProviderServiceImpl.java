package cn.smbms.service.provider;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.smbms.dao.provider.ProviderMapper;
import cn.smbms.pojo.Provider;

@Service
public class ProviderServiceImpl implements ProviderService {


	@Resource
	private ProviderMapper providerMapper;

	@Override
	public List<Provider> getProviderList(String queryProCode, String queryProName, Integer currentPageNo,
			Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		currentPageNo = (currentPageNo - 1) * pageSize;
		return providerMapper.getProviderList(queryProCode, queryProName, currentPageNo, pageSize);
	}

	@Override
	public int getProviderCount(String queryProCode, String queryProName) throws Exception {
		// TODO Auto-generated method stub
		return providerMapper.getProviderCount(queryProCode, queryProName);
	}
	/**
	 * 根据ID读取供应商信息
	 */
	@Override
	public Provider getProviderById(Integer id) {
		// TODO Auto-generated method stub
		return providerMapper.getProviderById(id);
	}

	/*
	 * 查询供应商总记录
	 */
	@Override
	public List<Provider> getProviderAll() throws Exception {
		// TODO Auto-generated method stub
		return providerMapper.getProviderAll();
	}

}
