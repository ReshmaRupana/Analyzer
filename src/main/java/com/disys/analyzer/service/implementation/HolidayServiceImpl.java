package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.disys.analyzer.model.dto.HolidayDTO;
import com.disys.analyzer.repository.HolidayRepository;
import com.disys.analyzer.service.HolidayService;
/**
 * @author Saravanan Dhurairaj
 * 
 */
@Service
@Transactional
public class HolidayServiceImpl implements HolidayService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private HolidayRepository holidayRepository;
	
	@Autowired
	Map<String, List<HolidayDTO>> holidayDownBean;
	
	@Override
	public List<HolidayDTO> getHolidayList(String userId, String companyCode) 
	{
		if(holidayDownBean.containsKey(companyCode)) {
			System.out.println("holidayDownBean has key : " + companyCode);
			return holidayDownBean.get(companyCode);
		}
		List<HolidayDTO> list = holidayRepository.getHolidayList(userId, companyCode);
		holidayDownBean.put(companyCode, list);
		return list;
	}
	
	@Override
	public List<HolidayDTO> getHolidayDescription(String userId, String companyCode, String recordCode) 
	{
		return holidayRepository.getHolidayDescription(userId, companyCode, recordCode);
	}
	
}
