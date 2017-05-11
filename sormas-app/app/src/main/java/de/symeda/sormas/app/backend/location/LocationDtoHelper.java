package de.symeda.sormas.app.backend.location;

import de.symeda.sormas.api.location.LocationDto;
import de.symeda.sormas.app.backend.common.AdoDtoHelper;
import de.symeda.sormas.app.backend.common.DatabaseHelper;
import de.symeda.sormas.app.backend.region.CommunityDtoHelper;
import de.symeda.sormas.app.backend.region.DistrictDtoHelper;
import de.symeda.sormas.app.backend.region.RegionDtoHelper;

/**
 * Created by Martin Wahnschaffe on 27.07.2016.
 */
public class LocationDtoHelper extends AdoDtoHelper<Location, LocationDto> {

    @Override
    public Location create() {
        return new Location();
    }

    @Override
    public LocationDto createDto() {
        return new LocationDto();
    }

    @Override
    public void fillInnerFromDto(Location target, LocationDto source) {

        target.setAddress(source.getAddress());
        target.setCity(source.getCity());
        target.setDetails(source.getDetails());
        target.setLatitude(source.getLatitude());
        target.setLongitude(source.getLongitude());

        target.setRegion(DatabaseHelper.getRegionDao().getByReferenceDto(source.getRegion()));
        target.setDistrict(DatabaseHelper.getDistrictDao().getByReferenceDto(source.getDistrict()));
        target.setCommunity(DatabaseHelper.getCommunityDao().getByReferenceDto(source.getCommunity()));
    }

    @Override
    public void fillInnerFromAdo(LocationDto dto, Location ado) {

        dto.setAddress(ado.getAddress());
        dto.setCity(ado.getCity());
        dto.setDetails(ado.getDetails());
        dto.setLatitude(ado.getLatitude());
        dto.setLongitude(ado.getLongitude());

        if (ado.getCommunity() != null) {
            dto.setCommunity(CommunityDtoHelper.toReferenceDto(DatabaseHelper.getCommunityDao().queryForId(ado.getCommunity().getId())));
        } else {
            dto.setCommunity(null);
        }
        if (ado.getDistrict() != null) {
            dto.setDistrict(DistrictDtoHelper.toReferenceDto(DatabaseHelper.getDistrictDao().queryForId(ado.getDistrict().getId())));
        } else {
            dto.setDistrict(null);
        }
        if (ado.getRegion() != null) {
            dto.setRegion(RegionDtoHelper.toReferenceDto(DatabaseHelper.getRegionDao().queryForId(ado.getRegion().getId())));
        } else {
            dto.setRegion(null);
        }
    }
}