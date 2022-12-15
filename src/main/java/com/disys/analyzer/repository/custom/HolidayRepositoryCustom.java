package com.disys.analyzer.repository.custom;

import java.util.List;

import com.disys.analyzer.model.dto.HolidayDTO;

public interface HolidayRepositoryCustom {
	public List<HolidayDTO> getHolidayList(String userId, String companyCode);
	public List<HolidayDTO> getHolidayDescription(String userId, String companyCode, String recordCode);
}
