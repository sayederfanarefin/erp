package com.sweetitech.sweeterp.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import com.sweetitech.sweeterp.model.dto.DivisionDto;

import com.sweetitech.sweeterp.model.user.Division;

public interface IDivisionService {
	Division findById(long id);
    void delete(Division division);
    Division updateDivision(Division division);
    Page<Division> findAllDivision( int page);
  
    DivisionDto findDivisionDtoById(Long id);
    Page<DivisionDto> findAllDivisionDto( int page);
    
    DivisionDto addDivisionDto(DivisionDto divisionDto);
    DivisionDto updateDivisionDto(DivisionDto divisionDto);
    void deleteDivisionByDto(DivisionDto divisionDto);
    
    List<DivisionDto> search(String searchString);
}
