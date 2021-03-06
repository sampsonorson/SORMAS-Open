package de.symeda.sormas.api.facility;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import de.symeda.sormas.api.region.CommunityReferenceDto;
import de.symeda.sormas.api.region.DistrictReferenceDto;
import de.symeda.sormas.api.region.RegionReferenceDto;

@Remote
public interface FacilityFacade {

	List<FacilityReferenceDto> getAll();
	
    List<FacilityReferenceDto> getHealthFacilitiesByCommunity(CommunityReferenceDto community, boolean includeStaticFacilities);
    List<FacilityReferenceDto> getHealthFacilitiesByDistrict(DistrictReferenceDto district, boolean includeStaticFacilities);
    List<FacilityReferenceDto> getHealthFacilitiesByRegion(RegionReferenceDto region, boolean includeStaticFacilities);
    List<FacilityReferenceDto> getAllLaboratories();

	List<FacilityDto> getAllByRegionAfter(String regionUuid, Date date);
	List<FacilityDto> getAllWithoutRegionAfter(Date date);
	
	FacilityDto getByUuid(String uuid);
}
