package de.symeda.sormas.backend.epidata;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import de.symeda.auditlog.api.Audited;
import de.symeda.sormas.api.epidata.WaterSource;
import de.symeda.sormas.api.utils.YesNoUnknown;
import de.symeda.sormas.backend.common.AbstractDomainObject;

@Entity
@Audited
public class EpiData extends AbstractDomainObject {

	private static final long serialVersionUID = -8294812479501735785L;
	
	public static final String BURIAL_ATTENDED = "burialAttended";
	public static final String BURIALS = "burials";
	public static final String GATHERING_ATTENDED = "gatheringAttended";
	public static final String GATHERINGS = "gatherings";
	public static final String TRAVELED = "traveled";
	public static final String TRAVELS = "travels";
	public static final String RODENTS = "rodents";
	public static final String BATS = "bats";
	public static final String PRIMATES = "primates";
	public static final String SWINE = "swine";
	public static final String BIRDS = "birds";
	public static final String POULTRY_EAT = "poultryEat";
	public static final String POULTRY = "poultry";
	public static final String POULTRY_DETAILS = "poultryDetails";
	public static final String POULTRY_SICK = "poultrySick";
	public static final String POULTRY_SICK_DETAILS = "poultrySickDetails";
	public static final String POULTRY_DATE = "poultryDate";
	public static final String POULTRY_LOCATION = "poultryLocation";
	public static final String WILDBIRDS = "wildbirds";
	public static final String WILDBIRDS_DETAILS = "wildbirdsDetails";
	public static final String WILDBIRDS_DATE = "wildbirdsDate";
	public static final String WILDBIRDS_LOCATION = "wildbirdsLocation";
	public static final String CATTLE = "cattle";
	public static final String OTHER_ANIMALS = "otherAnimals";
	public static final String OTHER_ANIMALS_DETAILS = "otherAnimalsDetails";
	public static final String WATER_SOURCE = "waterSource";
	public static final String WATER_SOURCE_OTHER = "waterSourceOther";
	public static final String WATER_BODY = "waterBody";
	public static final String WATER_BODY_DETAILS = "waterBodyDetails";
	public static final String TICKBITE = "tickBite";
	
	private YesNoUnknown burialAttended;
	private YesNoUnknown gatheringAttended;
	private YesNoUnknown traveled;
	private YesNoUnknown rodents;
	private YesNoUnknown bats;
	private YesNoUnknown primates;
	private YesNoUnknown swine;
	private YesNoUnknown birds;
	private YesNoUnknown poultryEat;
	private YesNoUnknown poultry;
	private String poultryDetails;
	private YesNoUnknown poultrySick;
	private String poultrySickDetails;
	private Date poultryDate;
	private String poultryLocation;
	private YesNoUnknown wildbirds;
	private String wildbirdsDetails;
	private Date wildbirdsDate;
	private String wildbirdsLocation;
	private YesNoUnknown cattle;
	private YesNoUnknown otherAnimals;
	private String otherAnimalsDetails;
	private WaterSource waterSource;
	private String waterSourceOther;
	private YesNoUnknown waterBody;
	private String waterBodyDetails;
	private YesNoUnknown tickBite;

	private Date changeDateOfEmbeddedLists;
	private List<EpiDataBurial> burials = new ArrayList<>();
	private List<EpiDataGathering> gatherings = new ArrayList<>();
	private List<EpiDataTravel> travels = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	public YesNoUnknown getBurialAttended() {
		return burialAttended;
	}
	public void setBurialAttended(YesNoUnknown burialAttended) {
		this.burialAttended = burialAttended;
	}
	
	@Enumerated(EnumType.STRING)
	public YesNoUnknown getGatheringAttended() {
		return gatheringAttended;
	}
	public void setGatheringAttended(YesNoUnknown gatheringAttended) {
		this.gatheringAttended = gatheringAttended;
	}

	@Enumerated(EnumType.STRING)
	public YesNoUnknown getTraveled() {
		return traveled;
	}
	public void setTraveled(YesNoUnknown traveled) {
		this.traveled = traveled;
	}

	@Enumerated(EnumType.STRING)
	public YesNoUnknown getRodents() {
		return rodents;
	}
	public void setRodents(YesNoUnknown rodents) {
		this.rodents = rodents;
	}

	@Enumerated(EnumType.STRING)
	public YesNoUnknown getBats() {
		return bats;
	}
	public void setBats(YesNoUnknown bats) {
		this.bats = bats;
	}

	@Enumerated(EnumType.STRING)
	public YesNoUnknown getPrimates() {
		return primates;
	}
	public void setPrimates(YesNoUnknown primates) {
		this.primates = primates;
	}

	@Enumerated(EnumType.STRING)
	public YesNoUnknown getSwine() {
		return swine;
	}
	public void setSwine(YesNoUnknown swine) {
		this.swine = swine;
	}

	@Enumerated(EnumType.STRING)
	public YesNoUnknown getBirds() {
		return birds;
	}
	public void setBirds(YesNoUnknown birds) {
		this.birds = birds;
	}

	@Enumerated(EnumType.STRING)
	public YesNoUnknown getPoultryEat() {
		return poultryEat;
	}
	public void setPoultryEat(YesNoUnknown poultryEat) {
		this.poultryEat = poultryEat;
	}

	@Enumerated(EnumType.STRING)
	public YesNoUnknown getPoultry() {
		return poultry;
	}
	public void setPoultry(YesNoUnknown poultry) {
		this.poultry = poultry;
	}

	@Column(length=512)
	public String getPoultryDetails() {
		return poultryDetails;
	}
	public void setPoultryDetails(String poultryDetails) {
		this.poultryDetails = poultryDetails;
	}

	@Enumerated(EnumType.STRING)
	public YesNoUnknown getPoultrySick() {
		return poultrySick;
	}
	public void setPoultrySick(YesNoUnknown poultrySick) {
		this.poultrySick = poultrySick;
	}

	@Column(length=512)
	public String getPoultrySickDetails() {
		return poultrySickDetails;
	}
	public void setPoultrySickDetails(String poultrySickDetails) {
		this.poultrySickDetails = poultrySickDetails;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getPoultryDate() {
		return poultryDate;
	}
	public void setPoultryDate(Date poultryDate) {
		this.poultryDate = poultryDate;
	}

	@Column(length=512)
	public String getPoultryLocation() {
		return poultryLocation;
	}
	public void setPoultryLocation(String poultryLocation) {
		this.poultryLocation = poultryLocation;
	}

	@Enumerated(EnumType.STRING)
	public YesNoUnknown getWildbirds() {
		return wildbirds;
	}
	public void setWildbirds(YesNoUnknown wildbirds) {
		this.wildbirds = wildbirds;
	}

	@Column(length=512)
	public String getWildbirdsDetails() {
		return wildbirdsDetails;
	}
	public void setWildbirdsDetails(String wildbirdsDetails) {
		this.wildbirdsDetails = wildbirdsDetails;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getWildbirdsDate() {
		return wildbirdsDate;
	}
	public void setWildbirdsDate(Date wildbirdsDate) {
		this.wildbirdsDate = wildbirdsDate;
	}

	@Column(length=512)
	public String getWildbirdsLocation() {
		return wildbirdsLocation;
	}
	public void setWildbirdsLocation(String wildbirdsLocation) {
		this.wildbirdsLocation = wildbirdsLocation;
	}

	@Enumerated(EnumType.STRING)
	public YesNoUnknown getCattle() {
		return cattle;
	}
	public void setCattle(YesNoUnknown cattle) {
		this.cattle = cattle;
	}

	@Enumerated(EnumType.STRING)
	public YesNoUnknown getOtherAnimals() {
		return otherAnimals;
	}
	public void setOtherAnimals(YesNoUnknown otherAnimals) {
		this.otherAnimals = otherAnimals;
	}

	@Column(length=512)
	public String getOtherAnimalsDetails() {
		return otherAnimalsDetails;
	}
	public void setOtherAnimalsDetails(String otherAnimalsDetails) {
		this.otherAnimalsDetails = otherAnimalsDetails;
	}

	@Enumerated(EnumType.STRING)
	public WaterSource getWaterSource() {
		return waterSource;
	}
	public void setWaterSource(WaterSource waterSource) {
		this.waterSource = waterSource;
	}

	@Column(length=512)
	public String getWaterSourceOther() {
		return waterSourceOther;
	}
	public void setWaterSourceOther(String waterSourceOther) {
		this.waterSourceOther = waterSourceOther;
	}

	@Enumerated(EnumType.STRING)
	public YesNoUnknown getWaterBody() {
		return waterBody;
	}
	public void setWaterBody(YesNoUnknown waterBody) {
		this.waterBody = waterBody;
	}

	@Column(length=512)
	public String getWaterBodyDetails() {
		return waterBodyDetails;
	}
	public void setWaterBodyDetails(String waterBodyDetails) {
		this.waterBodyDetails = waterBodyDetails;
	}

	@Enumerated(EnumType.STRING)
	public YesNoUnknown getTickBite() {
		return tickBite;
	}
	public void setTickBite(YesNoUnknown tickBite) {
		this.tickBite = tickBite;
	}
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = EpiDataBurial.EPI_DATA)
	public List<EpiDataBurial> getBurials() {
		return burials;
	}
	public void setBurials(List<EpiDataBurial> burials) {
		this.burials = burials;
	}
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = EpiDataGathering.EPI_DATA)
	public List<EpiDataGathering> getGatherings() {
		return gatherings;
	}
	public void setGatherings(List<EpiDataGathering> gatherings) {
		this.gatherings = gatherings;
	}

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = EpiDataTravel.EPI_DATA)
	public List<EpiDataTravel> getTravels() {
		return travels;
	}
	public void setTravels(List<EpiDataTravel> travels) {
		this.travels = travels;
	}
	
	/**
	 * This change date has to be set whenever one of the embedded lists is modified: !oldList.equals(newList)
	 * @return
	 */
	public Date getChangeDateOfEmbeddedLists() {
		return changeDateOfEmbeddedLists;
	}
	public void setChangeDateOfEmbeddedLists(Date changeDateOfEmbeddedLists) {
		this.changeDateOfEmbeddedLists = changeDateOfEmbeddedLists;
	}
	
}
