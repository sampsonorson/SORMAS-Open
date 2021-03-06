package de.symeda.sormas.app.backend.hospitalization;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import de.symeda.sormas.api.utils.DateHelper;
import de.symeda.sormas.api.utils.YesNoUnknown;
import de.symeda.sormas.app.backend.common.AbstractDomainObject;
import de.symeda.sormas.app.backend.common.EmbeddedAdo;
import de.symeda.sormas.app.backend.facility.Facility;
import de.symeda.sormas.app.backend.region.Community;
import de.symeda.sormas.app.backend.region.District;
import de.symeda.sormas.app.backend.region.Region;

/**
 * Created by Mate Strysewske on 22.02.2017.
 */

@Entity(name = PreviousHospitalization.TABLE_NAME)
@DatabaseTable(tableName = PreviousHospitalization.TABLE_NAME)
@EmbeddedAdo(parentAccessor = PreviousHospitalization.HOSPITALIZATION)
public class PreviousHospitalization extends AbstractDomainObject {

    private static final long serialVersionUID = 768263094433806267L;

    public static final String TABLE_NAME = "previoushospitalizations";
    public static final String I18N_PREFIX = "CasePreviousHospitalization";
    public static final String HOSPITALIZATION = "hospitalization";

    @DatabaseField(dataType = DataType.DATE_LONG)
    private Date admissionDate;

    @DatabaseField(dataType = DataType.DATE_LONG)
    private Date dischargeDate;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, maxForeignAutoRefreshLevel = 3)
    private Region region;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, maxForeignAutoRefreshLevel = 3)
    private District district;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, maxForeignAutoRefreshLevel = 3)
    private Community community;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, maxForeignAutoRefreshLevel = 3)
    private Facility healthFacility;

    @Enumerated(EnumType.STRING)
    private YesNoUnknown isolated;

    @Column(length=512)
    private String description;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Hospitalization hospitalization;

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public Facility getHealthFacility() {
        return healthFacility;
    }

    public void setHealthFacility(Facility healthFacility) {
        this.healthFacility = healthFacility;
    }

    public YesNoUnknown getIsolated() {
        return isolated;
    }

    public void setIsolated(YesNoUnknown isolated) {
        this.isolated = isolated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Hospitalization getHospitalization() {
        return hospitalization;
    }

    public void setHospitalization(Hospitalization hospitalization) {
        this.hospitalization = hospitalization;
    }

    @Override
    public String getI18nPrefix() {
        return I18N_PREFIX;
    }

    @Override
    public String toString() {
        return super.toString() + " " + DateHelper.formatShortDate(getDischargeDate());
    }
}
