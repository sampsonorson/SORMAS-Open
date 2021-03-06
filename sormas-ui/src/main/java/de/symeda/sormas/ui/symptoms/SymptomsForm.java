package de.symeda.sormas.ui.symptoms;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.vaadin.data.Validator;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.themes.ValoTheme;

import de.symeda.sormas.api.Disease;
import de.symeda.sormas.api.I18nProperties;
import de.symeda.sormas.api.symptoms.SymptomState;
import de.symeda.sormas.api.symptoms.SymptomsDto;
import de.symeda.sormas.api.symptoms.SymptomsHelper;
import de.symeda.sormas.api.utils.Diseases.DiseasesConfiguration;
import de.symeda.sormas.api.visit.VisitStatus;
import de.symeda.sormas.ui.location.LocationForm;
import de.symeda.sormas.ui.utils.AbstractEditForm;
import de.symeda.sormas.ui.utils.CssStyles;
import de.symeda.sormas.ui.utils.FieldHelper;
import de.symeda.sormas.ui.utils.LayoutUtil;

@SuppressWarnings("serial")
public class SymptomsForm extends AbstractEditForm<SymptomsDto> {

	private static final String BUTTONS_LOC = "buttonsLoc";
	private static final String ILLLOCATION_LOC = "illLocationLoc";
	private static final String STAY_PERIOD_LOC = "stayPeriodLoc";

	private static final String HTML_LAYOUT = LayoutUtil.h3(CssStyles.VSPACE3, "Clinical Signs and Symptoms")
			+ LayoutUtil.divCss(CssStyles.VSPACE3,
					LayoutUtil.fluidRowLocs(SymptomsDto.TEMPERATURE, SymptomsDto.TEMPERATURE_SOURCE))
			+ LayoutUtil.divCss(CssStyles.VSPACE3,
					LayoutUtil.fluidRowLocs(SymptomsDto.ONSET_DATE, SymptomsDto.ONSET_SYMPTOM))
			+ LayoutUtil.fluidRowCss(CssStyles.VSPACE3,
					LayoutUtil.fluidColumn(8, 0,
							LayoutUtil.div(I18nProperties.getFieldCaption("Symptoms.hint"))),
					LayoutUtil.fluidColumn(4, 0,
							LayoutUtil.locCss(CssStyles.ALIGN_RIGHT, BUTTONS_LOC)))
			+ LayoutUtil.fluidRow(
					LayoutUtil.fluidColumn(6, 0,
							LayoutUtil.locsCss(CssStyles.VSPACE3,
									SymptomsDto.ABDOMINAL_PAIN, SymptomsDto.HEARINGLOSS, SymptomsDto.ALTERED_CONSCIOUSNESS, SymptomsDto.ANOREXIA_APPETITE_LOSS,
									SymptomsDto.BACKACHE, SymptomsDto.STOMACH_BLEEDING, SymptomsDto.BLOOD_IN_STOOL, SymptomsDto.CHEST_PAIN,
									SymptomsDto.CONFUSED_DISORIENTED, SymptomsDto.CONJUNCTIVITIS, SymptomsDto.SEIZURES, SymptomsDto.COUGH,
									SymptomsDto.DARK_URINE, SymptomsDto.DEHYDRATION, SymptomsDto.DIARRHEA, SymptomsDto.DIFFICULTY_BREATHING,
									SymptomsDto.EYE_PAIN_LIGHT_SENSITIVE, SymptomsDto.EYES_BLEEDING, SymptomsDto.FATIGUE_WEAKNESS,
									SymptomsDto.FEVER, SymptomsDto.HEADACHE, SymptomsDto.HICCUPS,
									SymptomsDto.JAUNDICE, SymptomsDto.JOINT_PAIN, SymptomsDto.KOPLIKS_SPOTS, SymptomsDto.OTITIS_MEDIA, SymptomsDto.MUSCLE_PAIN,
									SymptomsDto.NAUSEA, SymptomsDto.NECK_STIFFNESS, SymptomsDto.RAPID_BREATHING,
									SymptomsDto.REFUSAL_FEEDOR_DRINK, SymptomsDto.RUNNY_NOSE)),
					LayoutUtil.fluidColumn(6, 0, 
							LayoutUtil.locsCss(CssStyles.VSPACE3,
									SymptomsDto.SHOCK, SymptomsDto.SKIN_RASH, SymptomsDto.SORE_THROAT,
									SymptomsDto.SWOLLEN_GLANDS, SymptomsDto.THROBOCYTOPENIA,
									SymptomsDto.UNEXPLAINED_BLEEDING, SymptomsDto.INJECTION_SITE_BLEEDING, SymptomsDto.BLEEDING_VAGINA, SymptomsDto.GUMS_BLEEDING,
									SymptomsDto.BLOOD_URINE, SymptomsDto.BLOODY_BLACK_STOOL, SymptomsDto.SKIN_BRUISING, SymptomsDto.COUGHING_BLOOD, 
									SymptomsDto.DIGESTED_BLOOD_VOMIT, SymptomsDto.RED_BLOOD_VOMIT, SymptomsDto.NOSE_BLEEDING, SymptomsDto.OTHER_HEMORRHAGIC_SYMPTOMS,
									SymptomsDto.OTHER_HEMORRHAGIC_SYMPTOMS_TEXT, SymptomsDto.VOMITING,
									SymptomsDto.OTHER_NON_HEMORRHAGIC_SYMPTOMS, SymptomsDto.OTHER_NON_HEMORRHAGIC_SYMPTOMS_TEXT)
							+ LayoutUtil.locsCss(CssStyles.VSPACE3,
									SymptomsDto.SYMPTOMS_COMMENTS))
						)
			+ LayoutUtil.loc(ILLLOCATION_LOC)
			+ LayoutUtil.divCss(CssStyles.VSPACE3,
					LayoutUtil.fluidRowLocs(SymptomsDto.ILLLOCATION))
			+ LayoutUtil.loc(STAY_PERIOD_LOC)
			+ LayoutUtil.divCss(CssStyles.VSPACE3,
					LayoutUtil.fluidRowLocs(SymptomsDto.ILLLOCATION_FROM, SymptomsDto.ILLLOCATION_TO));

	private final Disease disease;
	private final boolean showIllLocation;
	private transient List<Object> unconditionalSymptomFieldIds;
	private List<Object> conditionalBleedingSymptomFieldIds;

	public SymptomsForm(Disease disease, boolean showIllLocation) {
		super(SymptomsDto.class, SymptomsDto.I18N_PREFIX);
		this.disease = disease;
		this.showIllLocation = showIllLocation;
		if (disease == null) {
			throw new IllegalArgumentException("disease cannot be null");
		}
		addFields();
	}

	@Override
	protected void addFields() {
		if (disease == null) {
			// workaround to stop initialization until disease is set 
			return;
		}

		addField(SymptomsDto.ONSET_DATE);
		ComboBox temperature = addField(SymptomsDto.TEMPERATURE, ComboBox.class);
		for (Float temperatureValue : SymptomsHelper.getTemperatureValues()) {
			temperature.addItem(temperatureValue);
			temperature.setItemCaption(temperatureValue, SymptomsHelper.getTemperatureString(temperatureValue));
		}

		addField(SymptomsDto.TEMPERATURE_SOURCE);

		addFields(SymptomsDto.FEVER, SymptomsDto.VOMITING, SymptomsDto.DIARRHEA, SymptomsDto.BLOOD_IN_STOOL, SymptomsDto.NAUSEA, 
				SymptomsDto.ABDOMINAL_PAIN, SymptomsDto.HEADACHE, SymptomsDto.MUSCLE_PAIN, SymptomsDto.FATIGUE_WEAKNESS);
		addFields(SymptomsDto.SKIN_RASH, SymptomsDto.NECK_STIFFNESS, SymptomsDto.SORE_THROAT, SymptomsDto.COUGH, SymptomsDto.RUNNY_NOSE, 
				SymptomsDto.DIFFICULTY_BREATHING, SymptomsDto.CHEST_PAIN, SymptomsDto.CONFUSED_DISORIENTED, SymptomsDto.SEIZURES, SymptomsDto.ALTERED_CONSCIOUSNESS,
				SymptomsDto.CONJUNCTIVITIS, SymptomsDto.EYE_PAIN_LIGHT_SENSITIVE, SymptomsDto.KOPLIKS_SPOTS);
		addFields(SymptomsDto.THROBOCYTOPENIA, SymptomsDto.OTITIS_MEDIA, SymptomsDto.HEARINGLOSS, SymptomsDto.DEHYDRATION, SymptomsDto.ANOREXIA_APPETITE_LOSS, 
				SymptomsDto.REFUSAL_FEEDOR_DRINK, SymptomsDto.JOINT_PAIN, SymptomsDto.SHOCK, SymptomsDto.HICCUPS, SymptomsDto.BACKACHE, SymptomsDto.EYES_BLEEDING,
				SymptomsDto.JAUNDICE, SymptomsDto.DARK_URINE, SymptomsDto.STOMACH_BLEEDING, SymptomsDto.RAPID_BREATHING, SymptomsDto.SWOLLEN_GLANDS, SymptomsDto.SYMPTOMS_COMMENTS);
		addFields(SymptomsDto.UNEXPLAINED_BLEEDING, SymptomsDto.GUMS_BLEEDING, SymptomsDto.INJECTION_SITE_BLEEDING, SymptomsDto.NOSE_BLEEDING, 
				SymptomsDto.BLOODY_BLACK_STOOL, SymptomsDto.RED_BLOOD_VOMIT, SymptomsDto.DIGESTED_BLOOD_VOMIT, SymptomsDto.COUGHING_BLOOD,
				SymptomsDto.BLEEDING_VAGINA, SymptomsDto.SKIN_BRUISING, SymptomsDto.BLOOD_URINE);
		addFields(SymptomsDto.OTHER_HEMORRHAGIC_SYMPTOMS, SymptomsDto.OTHER_HEMORRHAGIC_SYMPTOMS_TEXT, SymptomsDto.OTHER_NON_HEMORRHAGIC_SYMPTOMS,
				SymptomsDto.OTHER_NON_HEMORRHAGIC_SYMPTOMS_TEXT);

		if (showIllLocation) {
			String illLocationCaptionLayout = LayoutUtil.h3(CssStyles.VSPACE3, "Location Where Person Became Symptomatic");
			Label illLocationCaptionLabel = new Label(illLocationCaptionLayout);
			illLocationCaptionLabel.setContentMode(ContentMode.HTML);
			getContent().addComponent(illLocationCaptionLabel, ILLLOCATION_LOC);
	    	addField(SymptomsDto.ILLLOCATION, LocationForm.class).setCaption(null);
	    	String stayPeriodCaptionLayout = LayoutUtil.h3(CssStyles.VSPACE3, "Time Period of Stay");
	    	Label stayPeriodCaptionLabel = new Label(stayPeriodCaptionLayout);
	    	stayPeriodCaptionLabel.setContentMode(ContentMode.HTML);
	    	getContent().addComponent(stayPeriodCaptionLabel, STAY_PERIOD_LOC);
	    	addField(SymptomsDto.ILLLOCATION_FROM);
	    	addField(SymptomsDto.ILLLOCATION_TO);
		}
		
		//		getFieldGroup().getField(SymptomsDto.OTHER_HEMORRHAGIC_SYMPTOMS_TEXT).setCaption(null);
		//		getFieldGroup().getField(SymptomsDto.OTHER_NON_HEMORRHAGIC_SYMPTOMS_TEXT).setCaption(null);

		conditionalBleedingSymptomFieldIds = Arrays.asList(SymptomsDto.GUMS_BLEEDING, SymptomsDto.INJECTION_SITE_BLEEDING, SymptomsDto.NOSE_BLEEDING, 
				SymptomsDto.BLOODY_BLACK_STOOL, SymptomsDto.RED_BLOOD_VOMIT, SymptomsDto.DIGESTED_BLOOD_VOMIT, 
				SymptomsDto.COUGHING_BLOOD, SymptomsDto.BLEEDING_VAGINA, SymptomsDto.SKIN_BRUISING, 
				SymptomsDto.BLOOD_URINE, SymptomsDto.OTHER_HEMORRHAGIC_SYMPTOMS);

		for (Object propertyId : getFieldGroup().getBoundPropertyIds()) {
			boolean visible = DiseasesConfiguration.isDefinedOrMissing(SymptomsDto.class, (String)propertyId, disease);
			getFieldGroup().getField(propertyId).setVisible(visible);
		}

		FieldHelper.setVisibleWhen(getFieldGroup(), 
				conditionalBleedingSymptomFieldIds,
				SymptomsDto.UNEXPLAINED_BLEEDING,
				Arrays.asList(SymptomState.YES), true);

		FieldHelper.setVisibleWhen(getFieldGroup(), 
				SymptomsDto.OTHER_HEMORRHAGIC_SYMPTOMS_TEXT,
				SymptomsDto.OTHER_HEMORRHAGIC_SYMPTOMS, 
				Arrays.asList(SymptomState.YES), true);

		FieldHelper.setVisibleWhen(getFieldGroup(), 
				SymptomsDto.OTHER_NON_HEMORRHAGIC_SYMPTOMS_TEXT,
				SymptomsDto.OTHER_NON_HEMORRHAGIC_SYMPTOMS, 
				Arrays.asList(SymptomState.YES), true);


		//		getFieldGroup().getField(SymptomsDto.OTHER_HEMORRHAGIC_SYMPTOMS_TEXT).setVisible(false);
		//		getFieldGroup().getField(SymptomsDto.OTHER_NON_HEMORRHAGIC_SYMPTOMS_TEXT).setVisible(false);
		//		for (Object fieldId : conditionalBleedingSymptomFieldIds) {
		//			getFieldGroup().getField(fieldId).setVisible(false);
		//		}

		unconditionalSymptomFieldIds = Arrays.asList(SymptomsDto.FEVER, SymptomsDto.VOMITING, SymptomsDto.DIARRHEA, SymptomsDto.BLOOD_IN_STOOL,
				SymptomsDto.NAUSEA, SymptomsDto.ABDOMINAL_PAIN, SymptomsDto.HEADACHE, SymptomsDto.MUSCLE_PAIN, SymptomsDto.FATIGUE_WEAKNESS, SymptomsDto.SKIN_RASH,
				SymptomsDto.NECK_STIFFNESS, SymptomsDto.SORE_THROAT, SymptomsDto.COUGH, SymptomsDto.RUNNY_NOSE, SymptomsDto.DIFFICULTY_BREATHING,
				SymptomsDto.CHEST_PAIN, SymptomsDto.CONFUSED_DISORIENTED, SymptomsDto.SEIZURES, SymptomsDto.ALTERED_CONSCIOUSNESS, SymptomsDto.CONJUNCTIVITIS,
				SymptomsDto.EYE_PAIN_LIGHT_SENSITIVE, SymptomsDto.KOPLIKS_SPOTS, SymptomsDto.THROBOCYTOPENIA, SymptomsDto.OTITIS_MEDIA, SymptomsDto.HEARINGLOSS,
				SymptomsDto.DEHYDRATION, SymptomsDto.ANOREXIA_APPETITE_LOSS, SymptomsDto.REFUSAL_FEEDOR_DRINK, SymptomsDto.JOINT_PAIN, SymptomsDto.SHOCK,
				SymptomsDto.HICCUPS, SymptomsDto.BACKACHE, SymptomsDto.EYES_BLEEDING, SymptomsDto.JAUNDICE, SymptomsDto.DARK_URINE, SymptomsDto.STOMACH_BLEEDING,
				SymptomsDto.RAPID_BREATHING, SymptomsDto.SWOLLEN_GLANDS, SymptomsDto.UNEXPLAINED_BLEEDING, SymptomsDto.OTHER_NON_HEMORRHAGIC_SYMPTOMS);


		FieldHelper.setRequiredWhen(getFieldGroup(), getFieldGroup().getField(SymptomsDto.UNEXPLAINED_BLEEDING), conditionalBleedingSymptomFieldIds, Arrays.asList(SymptomState.YES), disease);
		FieldHelper.setRequiredWhen(getFieldGroup(), getFieldGroup().getField(SymptomsDto.OTHER_HEMORRHAGIC_SYMPTOMS), 
				Arrays.asList(SymptomsDto.OTHER_HEMORRHAGIC_SYMPTOMS_TEXT), Arrays.asList(SymptomState.YES), disease);
		FieldHelper.setRequiredWhen(getFieldGroup(), getFieldGroup().getField(SymptomsDto.OTHER_NON_HEMORRHAGIC_SYMPTOMS), 
				Arrays.asList(SymptomsDto.OTHER_NON_HEMORRHAGIC_SYMPTOMS_TEXT), Arrays.asList(SymptomState.YES), disease);
		// setReadOnly(true, );

		ComboBox onsetSymptom = addField(SymptomsDto.ONSET_SYMPTOM, ComboBox.class);
		addListenerForOnsetSymptom(onsetSymptom);

		Button clearAllButton = new Button("Clear all");
		clearAllButton.addStyleName(ValoTheme.BUTTON_LINK);

		clearAllButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				for (Object symptomId : unconditionalSymptomFieldIds) {
					getFieldGroup().getField(symptomId).setValue(null);
				}
				for (Object symptomId : conditionalBleedingSymptomFieldIds) {
					getFieldGroup().getField(symptomId).setValue(null);
				}
			}
		});

		Button setEmptyToNoButton = new Button("Set cleared to No");
		setEmptyToNoButton.addStyleName(ValoTheme.BUTTON_LINK);

		setEmptyToNoButton.addClickListener(new ClickListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void buttonClick(ClickEvent event) {
				for (Object symptomId : unconditionalSymptomFieldIds) {
					Field<SymptomState> symptom = (Field<SymptomState>) getFieldGroup().getField(symptomId);
					if (symptom.isVisible() && symptom.getValue() == null) {
						symptom.setValue(SymptomState.NO);
					}
				}
				for (Object symptomId : conditionalBleedingSymptomFieldIds) {
					Field<SymptomState> symptom = (Field<SymptomState>) getFieldGroup().getField(symptomId);
					if (symptom.isVisible() && symptom.getValue() == null) {
						symptom.setValue(SymptomState.NO);
					}
				}
			}
		});

		HorizontalLayout buttonsLayout = new HorizontalLayout();
		buttonsLayout.addComponent(clearAllButton);
		buttonsLayout.addComponent(setEmptyToNoButton);
		buttonsLayout.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);
		getContent().addComponent(buttonsLayout, BUTTONS_LOC);
	}

	@Override
	protected String createHtmlLayout() {
		return HTML_LAYOUT;
	}

	public void initializeSymptomRequirementsForVisit(OptionGroup visitStatus) {
		FieldHelper.setRequiredWhen(getFieldGroup(), visitStatus, unconditionalSymptomFieldIds, Arrays.asList(VisitStatus.COOPERATIVE), disease);
		FieldHelper.setRequiredWhen(getFieldGroup(), visitStatus, Arrays.asList(SymptomsDto.TEMPERATURE, SymptomsDto.TEMPERATURE_SOURCE), Arrays.asList(VisitStatus.COOPERATIVE), disease);
		setRequiredWhenSymptomaticAndCooperative(getFieldGroup(), SymptomsDto.ONSET_DATE, unconditionalSymptomFieldIds, Arrays.asList(SymptomState.YES), visitStatus);
		setRequiredWhenSymptomaticAndCooperative(getFieldGroup(), SymptomsDto.ONSET_SYMPTOM, unconditionalSymptomFieldIds, Arrays.asList(SymptomState.YES), visitStatus);
		getFieldGroup().getField(SymptomsDto.FEVER).addValidator(new Validator() {
			@Override
			public void validate(Object value) throws InvalidValueException {
				if(getFieldGroup().getField(SymptomsDto.TEMPERATURE).getValue() != null) {
					if((Float)(getFieldGroup().getField(SymptomsDto.TEMPERATURE).getValue()) >= 38.0f) {
						if(value != SymptomState.YES) {
							throw new InvalidValueException("Fever needs to be set to 'Yes' for temperatures >= 38");
						}
					}
				}
			}
		});
	}	

	public void initializeSymptomRequirementsForCase() {
		setRequiredWhenSymptomatic(getFieldGroup(), SymptomsDto.ONSET_DATE, unconditionalSymptomFieldIds, Arrays.asList(SymptomState.YES));
		setRequiredWhenSymptomatic(getFieldGroup(), SymptomsDto.ONSET_SYMPTOM, unconditionalSymptomFieldIds, Arrays.asList(SymptomState.YES));
	}

	private void setRequiredWhenSymptomatic(FieldGroup fieldGroup, Object targetPropertyId, List<Object> sourcePropertyIds, 
			List<Object> sourceValues) {
		setRequiredWhenSymptomaticAndCooperative(fieldGroup, targetPropertyId, sourcePropertyIds, sourceValues, null);
	}

	/**
	 * Sets the fields defined by the ids contained in sourceValues to required when the person is symptomatic
	 * and - if a visit is processed - cooperative. When this method is called from within a case, it needs to 
	 * be called with visitStatusField set to null in order to ignore the visit status requirement.
	 */
	@SuppressWarnings("rawtypes")
	private void setRequiredWhenSymptomaticAndCooperative(FieldGroup fieldGroup, Object targetPropertyId,
			List<Object> sourcePropertyIds, List<Object> sourceValues, OptionGroup visitStatusField) {

		for(Object sourcePropertyId : sourcePropertyIds) {
			Field sourceField = fieldGroup.getField(sourcePropertyId);
			if(sourceField instanceof AbstractField<?>) {
				((AbstractField) sourceField).setImmediate(true);
			}
		}

		// Initialize
		final Field targetField = fieldGroup.getField(targetPropertyId);
		if(visitStatusField != null) {
			targetField.setRequired(isAnySymptomSetToYes(fieldGroup, sourcePropertyIds, sourceValues) && 
					visitStatusField.getValue() == VisitStatus.COOPERATIVE);
		} else {
			targetField.setRequired(isAnySymptomSetToYes(fieldGroup, sourcePropertyIds, sourceValues));
		}

		// Add listeners
		for(Object sourcePropertyId : sourcePropertyIds) {
			Field sourceField = fieldGroup.getField(sourcePropertyId);
			sourceField.addValueChangeListener(event -> {
				if(visitStatusField != null) {
					targetField.setRequired(isAnySymptomSetToYes(fieldGroup, sourcePropertyIds, sourceValues) &&
							visitStatusField.getValue() == VisitStatus.COOPERATIVE);
				} else {
					targetField.setRequired(isAnySymptomSetToYes(fieldGroup, sourcePropertyIds, sourceValues));
				}
			});
		}

		if(visitStatusField != null) {
			visitStatusField.addValueChangeListener(new ValueChangeListener() {
				@Override
				public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
					targetField.setRequired(isAnySymptomSetToYes(fieldGroup, sourcePropertyIds, sourceValues) &&
							visitStatusField.getValue() == VisitStatus.COOPERATIVE);
				}
			});
		}
	}

	/**
	 * Returns true if if the value of any field associated with the sourcePropertyIds
	 * is set to one of the values contained in sourceValues.
	 * 
	 * @param fieldGroup
	 * @param sourcePropertyIds
	 * @param sourceValues
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public boolean isAnySymptomSetToYes(FieldGroup fieldGroup, List<Object> sourcePropertyIds, 
			List<Object> sourceValues) {

		for(Object sourcePropertyId : sourcePropertyIds) {
			Field sourceField = fieldGroup.getField(sourcePropertyId);
			if(sourceValues.contains(sourceField.getValue())) {
				return true;
			}
		}

		return false;
	}

	@SuppressWarnings("rawtypes")
	private void addListenerForOnsetSymptom(ComboBox onsetSymptom) {
		List<Object> allPropertyIds = 
				Stream.concat(unconditionalSymptomFieldIds.stream(), conditionalBleedingSymptomFieldIds.stream())
				.collect(Collectors.toList());

		for (Object sourcePropertyId : allPropertyIds) {
			Field sourceField = getFieldGroup().getField(sourcePropertyId);
			sourceField.addValueChangeListener(event -> {
				if (sourceField.getValue() == SymptomState.YES) {
					onsetSymptom.addItem(sourceField.getCaption());
				} else {
					onsetSymptom.removeItem(sourceField.getCaption());
				}
			});
		}
	}
	
	public List<Object> getUnconditionalSymptomFieldIds() {
		return unconditionalSymptomFieldIds;
	}
}
