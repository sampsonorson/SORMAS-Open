package de.symeda.sormas.app.backend.epidata;

import de.symeda.sormas.api.epidata.EpiDataBurialDto;
import de.symeda.sormas.app.backend.common.AdoDtoHelper;
import de.symeda.sormas.app.backend.common.DaoException;
import de.symeda.sormas.app.backend.common.DatabaseHelper;
import de.symeda.sormas.app.backend.location.Location;
import de.symeda.sormas.app.backend.location.LocationDtoHelper;

/**
 * Created by Mate Strysewske on 08.03.2017.
 */

public class EpiDataBurialDtoHelper extends AdoDtoHelper<EpiDataBurial, EpiDataBurialDto> {

    private LocationDtoHelper locationHelper;

    public EpiDataBurialDtoHelper() {
        locationHelper = new LocationDtoHelper();
    }

    @Override
    public EpiDataBurial create() {
        return new EpiDataBurial();
    }

    @Override
    public EpiDataBurialDto createDto() {
        return new EpiDataBurialDto();
    }

    @Override
    public void fillInnerFromDto(EpiDataBurial target, EpiDataBurialDto source) {

        // epi data is set by calling method

        target.setBurialAddress(locationHelper.fillOrCreateFromDto(target.getBurialAddress(), source.getBurialAddress()));
        target.setBurialDateFrom(source.getBurialDateFrom());
        target.setBurialDateTo(source.getBurialDateTo());
        target.setBurialPersonname(source.getBurialPersonName());
        target.setBurialRelation(source.getBurialRelation());
        target.setBurialIll(source.getBurialIll());
        target.setBurialTouching(source.getBurialTouching());
    }

    @Override
    public void fillInnerFromAdo(EpiDataBurialDto a, EpiDataBurial b) {

        if (b.getBurialAddress() != null) {
            Location location = DatabaseHelper.getLocationDao().queryForId(b.getBurialAddress().getId());
            a.setBurialAddress(locationHelper.adoToDto(location));
        } else {
            a.setBurialAddress(null);
        }

        a.setBurialDateFrom(b.getBurialDateFrom());
        a.setBurialDateTo(b.getBurialDateTo());
        a.setBurialPersonName(b.getBurialPersonname());
        a.setBurialRelation(b.getBurialRelation());
        a.setBurialIll(b.getBurialIll());
        a.setBurialTouching(b.getBurialTouching());
    }
}