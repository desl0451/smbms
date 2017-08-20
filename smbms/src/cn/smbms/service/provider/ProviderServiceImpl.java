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
	 * ����ID��ȡ��Ӧ����Ϣ
	 */
	@Override
	public Provider getProviderById(Integer id) {
		// TODO Auto-generated method stub
		return providerMapper.getProviderById(id);
	}

	/*
	 * ��ѯ��Ӧ���ܼ�¼
	 */
	@Override
	public List<Provider> getProviderAll() {
		// TODO Auto-generated method stub
		return providerMapper.getProviderAll();
	}

	/**
	 * ��ӹ�Ӧ����Ϣ
	 */
	@Override
	public boolean add(Provider provider) {
		// TODO Auto-generated method stub
		if (providerMapper.save(provider) > 0) {
			return true;
		}
		return false;
	}
}
