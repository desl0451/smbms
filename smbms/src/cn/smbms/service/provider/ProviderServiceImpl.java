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

	/**
	 * 查询供应商列表
	 */
	@Override
	public List<Provider> getProviderList(String queryProCode, String queryProName, Integer currentPageNo,
			Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		currentPageNo = (currentPageNo - 1) * pageSize;
		return providerMapper.getProviderList(queryProCode, queryProName, currentPageNo, pageSize);
	}

	/**
	 * 返回记录数量
	 */
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
	public List<Provider> getProviderAll() {
		// TODO Auto-generated method stub
		return providerMapper.getProviderAll();
	}

	/**
	 * 添加供应商信息
	 */
	@Override
	public boolean add(Provider provider) {
		// TODO Auto-generated method stub
		if (providerMapper.insert(provider) > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 删除供应商信息
	 */
	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		if (providerMapper.delete(id) > 0) {
			return true;
		}
		return false;
	}

}
