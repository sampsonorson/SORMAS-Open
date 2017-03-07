package de.symeda.sormas.api.epidata;

import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import de.symeda.sormas.api.DataTransferObject;
import de.symeda.sormas.api.Disease;
import de.symeda.sormas.api.location.LocationDto;
import de.symeda.sormas.api.utils.Diseases;
import de.symeda.sormas.api.utils.PreciseDateAdapter;

public class EpiDataGatheringDto extends DataTransferObject {

	private static final long serialVersionUID = 4953376180428831063L;

	public static final String I18N_PREFIX = "EpiDataGathering";
	
	public static final String DESCRIPTION = "description";
	public static final String GATHERING_DATE = "gatheringDate";
	public static final String GATHERING_ADDRESS = "gatheringAddress";
	
	private EpiDataDto epiData;
	@Diseases({Disease.EVD,Disease.LASSA,Disease.AVIAN_INFLUENCA,Disease.CSM,Disease.CHOLERA,Disease.MEASLES})
	private String description;
	@Diseases({Disease.EVD,Disease.LASSA,Disease.AVIAN_INFLUENCA,Disease.CSM,Disease.CHOLERA,Disease.MEASLES})
	private Date gatheringDate;
	@Diseases({Disease.EVD,Disease.LASSA,Disease.AVIAN_INFLUENCA,Disease.CSM,Disease.CHOLERA,Disease.MEASLES})
	private LocationDto gatheringAddress;
	
	public EpiDataDto getEpiData() {
		return epiData;
	}
	public void setEpiData(EpiDataDto epiData) {
		this.epiData = epiData;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@XmlJavaTypeAdapter(PreciseDateAdapter.class)
	public Date getGatheringDate() {
		return gatheringDate;
	}
	@XmlJavaTypeAdapter(PreciseDateAdapter.class)
	public void setGatheringDate(Date gatheringDate) {
		this.gatheringDate = gatheringDate;
	}
	
	public LocationDto getGatheringAddress() {
		return gatheringAddress;
	}
	public void setGatheringAddress(LocationDto gatheringAddress) {
		this.gatheringAddress = gatheringAddress;
	}
	
}