package de.symeda.sormas.ui.hospitalization;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextArea;

import de.symeda.sormas.api.FacadeProvider;
import de.symeda.sormas.api.hospitalization.PreviousHospitalizationDto;
import de.symeda.sormas.api.region.CommunityReferenceDto;
import de.symeda.sormas.api.region.DistrictReferenceDto;
import de.symeda.sormas.api.region.RegionReferenceDto;
import de.symeda.sormas.ui.utils.AbstractEditForm;
import de.symeda.sormas.ui.utils.LayoutUtil;

@SuppressWarnings("serial")
public class PreviousHospitalizationEditForm extends AbstractEditForm<PreviousHospitalizationDto> {


	private static final String HTML_LAYOUT = 
			LayoutUtil.fluidRowLocs(PreviousHospitalizationDto.ADMISSION_DATE, PreviousHospitalizationDto.DISCHARGE_DATE)+
			LayoutUtil.fluidRowLocs(PreviousHospitalizationDto.REGION, PreviousHospitalizationDto.DISTRICT)+
			LayoutUtil.fluidRowLocs(PreviousHospitalizationDto.COMMUNITY, PreviousHospitalizationDto.HEALTH_FACILITY)+
			LayoutUtil.fluidRowLocs(PreviousHospitalizationDto.ISOLATED)+
			LayoutUtil.fluidRowLocs(PreviousHospitalizationDto.DESCRIPTION)
			;

	public PreviousHospitalizationEditForm() {
		super(PreviousHospitalizationDto.class, PreviousHospitalizationDto.I18N_PREFIX);

		setWidth(540, Unit.PIXELS);
	}

	@Override
	protected void addFields() {

		addFields(PreviousHospitalizationDto.ADMISSION_DATE,
				PreviousHospitalizationDto.DISCHARGE_DATE);
		addField(PreviousHospitalizationDto.ISOLATED, OptionGroup.class);
		addField(PreviousHospitalizationDto.DESCRIPTION, TextArea.class).setRows(2);

		ComboBox facilityRegion = addField(PreviousHospitalizationDto.REGION, ComboBox.class);
		ComboBox facilityDistrict = addField(PreviousHospitalizationDto.DISTRICT, ComboBox.class);
		ComboBox facilityCommunity = addField(PreviousHospitalizationDto.COMMUNITY, ComboBox.class);
		ComboBox healthFacility = addField(PreviousHospitalizationDto.HEALTH_FACILITY, ComboBox.class);
		healthFacility.setImmediate(true);

		facilityRegion.addValueChangeListener(e -> {
			facilityDistrict.removeAllItems();
			RegionReferenceDto regionDto = (RegionReferenceDto)e.getProperty().getValue();
			if(regionDto != null) {
				facilityDistrict.addItems(FacadeProvider.getDistrictFacade().getAllByRegion(regionDto.getUuid()));
			}
		});
		facilityDistrict.addValueChangeListener(e -> {
			facilityCommunity.removeAllItems();
			DistrictReferenceDto districtDto = (DistrictReferenceDto)e.getProperty().getValue();
			if(districtDto != null) {
				facilityCommunity.addItems(FacadeProvider.getCommunityFacade().getAllByDistrict(districtDto.getUuid()));
			}
		});
		facilityCommunity.addValueChangeListener(e -> {
			healthFacility.removeAllItems();
			CommunityReferenceDto communityDto = (CommunityReferenceDto)e.getProperty().getValue();
			if(communityDto != null) {
				healthFacility.addItems(FacadeProvider.getFacilityFacade().getHealthFacilitiesByCommunity(communityDto, true));
			}
		});

		facilityRegion.addItems(FacadeProvider.getRegionFacade().getAllAsReference());

		setRequired(true,
				PreviousHospitalizationDto.ADMISSION_DATE, 
				PreviousHospitalizationDto.DISCHARGE_DATE, 
				PreviousHospitalizationDto.REGION,
				PreviousHospitalizationDto.DISTRICT,
				PreviousHospitalizationDto.COMMUNITY,
				PreviousHospitalizationDto.HEALTH_FACILITY);
	}

	@Override
	protected String createHtmlLayout() {
		return HTML_LAYOUT;
	}
}
