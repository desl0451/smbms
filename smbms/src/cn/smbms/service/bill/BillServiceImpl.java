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
	public int getBillCount(String queryProductName, Integer queryProviderId, Integer queryIsPayment) throws Exception {
		// TODO Auto-generated method stub
		return billMapper.getBillCount(queryProductName, queryProviderId, queryIsPayment);
	}

	@Override
	public List<Bill> getBillList(String queryProductName, Integer queryProviderId, Integer queryIsPayment,
			Integer currentPageNo, Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		currentPageNo = (currentPageNo - 1) * pageSize;
		return billMapper.getBillList(queryProductName, queryProviderId, queryIsPayment, currentPageNo, pageSize);
	}

	/**
	 * 根据ID读取Bill信息
	 */
	@Override
	public Bill getBillById(Integer id) {
		// TODO Auto-generated method stub
		return billMapper.getBillById(id);
	}

}
