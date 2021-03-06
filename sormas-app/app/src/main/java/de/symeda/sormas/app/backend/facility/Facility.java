package de.symeda.sormas.app.backend.facility;

import android.databinding.Bindable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import de.symeda.sormas.api.I18nProperties;
import de.symeda.sormas.api.facility.FacilityDto;
import de.symeda.sormas.api.facility.FacilityType;
import de.symeda.sormas.app.backend.common.AbstractDomainObject;
import de.symeda.sormas.app.backend.region.Community;
import de.symeda.sormas.app.backend.region.District;
import de.symeda.sormas.app.backend.region.Region;

@Entity(name=Facility.TABLE_NAME)
@DatabaseTable(tableName = Facility.TABLE_NAME)
public class Facility extends AbstractDomainObject {
	
	private static final long serialVersionUID = 8572137127616417072L;

	public static final String TABLE_NAME = "facility";
	public static final String I18N_PREFIX = "Facility";

	public static final String COMMUNITY = "community";
	public static final String NAME = "name";
	public static final String TYPE = "type";

	@Column
	private String name;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private Region region;
	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private District district;
	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private Community community;
	@Column(length = 255)
	private String city;
	@Column(columnDefinition = "float8")
	private Float latitude;
	@Column(columnDefinition = "float8")
	private Float longitude;

	@Enumerated(EnumType.STRING)
	private FacilityType type;
	@Column
	private boolean publicOwnership;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Bindable
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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

	public Float getLatitude() {
		return latitude;
	}
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
	
	public FacilityType getType() {
		return type;
	}
	public void setType(FacilityType type) {
		this.type = type;
	}
	
	public boolean isPublicOwnership() {
		return publicOwnership;
	}
	public void setPublicOwnership(boolean publicOwnership) {
		this.publicOwnership = publicOwnership;
	}

	@Override
	public String toString() {
		if (getUuid().equals(FacilityDto.OTHER_FACILITY_UUID)) {
			return I18nProperties.getPrefixFieldCaption(FacilityDto.I18N_PREFIX, FacilityDto.OTHER_FACILITY);
		}
		if (getUuid().equals(FacilityDto.NONE_FACILITY_UUID)) {
			return I18nProperties.getPrefixFieldCaption(FacilityDto.I18N_PREFIX, FacilityDto.NO_FACILITY);
		}

		StringBuilder caption = new StringBuilder();
		caption.append(name);
		if (community != null) {
			caption.append(" (").append(community.getName()).append(")");
		}
		return caption.toString();
	}

	@Override
	public String getI18nPrefix() {
		return I18N_PREFIX;
	}
}
