package cn.smbms.service.bill;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import cn.smbms.dao.bill.BillMapper;
import cn.smbms.pojo.Bill;

@Service
public class BillServiceImpl implements BillService {

	@Resource
	private BillMapper billMapper;

	@Override
	public int getBillCount(String queryProductName, Integer queryProviderId) throws Exception {
		// TODO Auto-generated method stub
		return billMapper.getBillCount(queryProductName, queryProviderId);
	}

	@Override
	public List<Bill> getBillList(String queryProductName, Integer queryProviderId, Integer currentPageNo,
			Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		currentPageNo = (currentPageNo - 1) * pageSize;
		return billMapper.getBillList(queryProductName, queryProviderId, currentPageNo, pageSize);
	}

}
