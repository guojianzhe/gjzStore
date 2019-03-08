package com.heima.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import com.heima.dao.ProductDao;
import com.heima.daoImpl.ProductDaoImpl;
import com.heima.domain.PageBean;
import com.heima.domain.Product;
import com.heima.service.ProductService;

public class ProductServiceImpl implements ProductService {

	@Override
	public PageBean findProduct(String cid, int page, int pageSize) throws SQLException {
		
		ProductDao productDao = new ProductDaoImpl();
		//��ȡ������
		int totalCount= productDao. findCount(cid);
		//��ȡ��ҳ��
		int totalPage = 0;
		
		if(totalCount%pageSize==0) {
			totalPage = totalCount/pageSize;
		}else {
			totalPage = totalCount/pageSize+1;
		}
		
		//ÿҳ��ʾ������
		List<Product> productsList = productDao.findList(cid,(page-1)*pageSize,pageSize);
		
		PageBean pb = new PageBean();
		pb.setPage(page);
		pb.setPageSize(pageSize);
		pb.setTotalCount(totalCount);
		pb.setTotalPage(totalPage);
		pb.setList(productsList);
		return pb;
		
	}

}
