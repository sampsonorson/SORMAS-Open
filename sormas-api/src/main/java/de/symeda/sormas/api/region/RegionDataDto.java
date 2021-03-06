package de.symeda.sormas.api.region;

import de.symeda.sormas.api.DataTransferObject;

public class RegionDataDto extends DataTransferObject {

	private static final long serialVersionUID = -1610675328037466348L;

	public static final String I18N_PREFIX = "Region";

	private String name;
	private String epidCode;
	private Integer population;
	private Float growthRate;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEpidCode() {
		return epidCode;
	}
	public void setEpidCode(String epidCode) {
		this.epidCode = epidCode;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	public Integer getPopulation() {
		return population;
	}
	public void setPopulation(Integer population) {
		this.population = population;
	}
	public Float getGrowthRate() {
		return growthRate;
	}
	public void setGrowthRate(Float growthRate) {
		this.growthRate = growthRate;
	}
	
	/**
	 * TODO move generic to DataTransferObject and split ReferenceDto from DataTransferObject
	 * @return
	 */
	public RegionReferenceDto toReferenceDto() {
		RegionReferenceDto dto = new RegionReferenceDto();
		dto.setUuid(getUuid());
		dto.setCreationDate(getCreationDate());
		dto.setChangeDate(getChangeDate());
		dto.setCaption(toString());
		return dto;
	}
}
