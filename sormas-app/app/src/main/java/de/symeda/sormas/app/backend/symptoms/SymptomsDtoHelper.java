package de.symeda.sormas.app.backend.symptoms;

import de.symeda.sormas.api.location.LocationDto;
import de.symeda.sormas.api.symptoms.SymptomsDto;
import de.symeda.sormas.app.backend.common.AdoDtoHelper;
import de.symeda.sormas.app.backend.common.DatabaseHelper;

/**
 * Created by Martin Wahnschaffe on 27.07.2016.
 */
public class SymptomsDtoHelper extends AdoDtoHelper<Symptoms, SymptomsDto> {

    @Override
    public Symptoms create() {
        return new Symptoms();
    }

    @Override
    public SymptomsDto createDto() {
        return new SymptomsDto();
    }

    @Override
    public void fillInnerFromDto(Symptoms a, SymptomsDto b) {
        a.setAbdominalPain(b.getAbdominalPain());
        a.setAlteredConsciousness(b.getAlteredConsciousness());
        a.setAnorexiaAppetiteLoss(b.getAnorexiaAppetiteLoss());
        a.setBleedingVagina(b.getBleedingVagina());
        a.setBloodInStool(b.getBloodInStool());
        a.setBloodUrine(b.getBloodUrine());
        a.setBloodyBlackStool(b.getBloodyBlackStool());
        a.setChestPain(b.getChestPain());
        a.setConfusedDisoriented(b.getConfusedDisoriented());
        a.setConjunctivitis(b.getConjunctivitis());
        a.setCough(b.getCough());
        a.setCoughingBlood(b.getCoughingBlood());
        a.setDehydration(b.getDehydration());
        a.setDiarrhea(b.getDiarrhea());
        a.setDifficultyBreathing(b.getDifficultyBreathing());
        a.setDigestedBloodVomit(b.getDigestedBloodVomit());
        a.setEyePainLightSensitive(b.getEyePainLightSensitive());
        a.setFatigueWeakness(b.getFatigueWeakness());
        a.setFever(b.getFever());
        a.setGumsBleeding(b.getGumsBleeding());
        a.setHeadache(b.getHeadache());
        a.setHearingloss(b.getHearingloss());
        a.setHiccups(b.getHiccups());
        a.setInjectionSiteBleeding(b.getInjectionSiteBleeding());
        a.setJointPain(b.getJointPain());
        a.setKopliksSpots(b.getKopliksSpots());
        a.setMusclePain(b.getMusclePain());
        a.setNausea(b.getNausea());
        a.setNeckStiffness(b.getNeckStiffness());
        a.setNoseBleeding(b.getNoseBleeding());
        a.setOnsetDate(b.getOnsetDate());
        a.setOnsetSymptom(b.getOnsetSymptom());
        a.setOtherHemorrhagicSymptoms(b.getOtherHemorrhagicSymptoms());
        a.setOtherHemorrhagicSymptomsText(b.getOtherHemorrhagicSymptomsText());
        a.setOtherNonHemorrhagicSymptoms(b.getOtherNonHemorrhagicSymptoms());
        a.setOtherNonHemorrhagicSymptomsText(b.getOtherNonHemorrhagicSymptomsText());
        a.setOtitisMedia(b.getOtitisMedia());
        a.setRedBloodVomit(b.getRedBloodVomit());
        a.setRefusalFeedorDrink(b.getRefusalFeedorDrink());
        a.setRunnyNose(b.getRunnyNose());
        a.setSeizures(b.getSeizures());
        a.setShock(b.getShock());
        a.setSkinBruising(b.getSkinBruising());
        a.setSkinRash(b.getSkinRash());
        a.setSoreThroat(b.getSoreThroat());
        a.setSymptomatic(b.getSymptomatic());
        a.setSymptomsComments(b.getSymptomsComments());
        a.setTemperature(b.getTemperature());
        a.setTemperatureSource(b.getTemperatureSource());
        a.setThrobocytopenia(b.getThrobocytopenia());
        a.setUnexplainedBleeding(b.getUnexplainedBleeding());
        a.setVomiting(b.getVomiting());
    }

    @Override
    public void fillInnerFromAdo(SymptomsDto a, Symptoms b) {
        a.setAbdominalPain(b.getAbdominalPain());
        a.setAlteredConsciousness(b.getAlteredConsciousness());
        a.setAnorexiaAppetiteLoss(b.getAnorexiaAppetiteLoss());
        a.setBleedingVagina(b.getBleedingVagina());
        a.setBloodInStool(b.getBloodInStool());
        a.setBloodUrine(b.getBloodUrine());
        a.setBloodyBlackStool(b.getBloodyBlackStool());
        a.setChestPain(b.getChestPain());
        a.setConfusedDisoriented(b.getConfusedDisoriented());
        a.setConjunctivitis(b.getConjunctivitis());
        a.setCough(b.getCough());
        a.setCoughingBlood(b.getCoughingBlood());
        a.setDehydration(b.getDehydration());
        a.setDiarrhea(b.getDiarrhea());
        a.setDifficultyBreathing(b.getDifficultyBreathing());
        a.setDigestedBloodVomit(b.getDigestedBloodVomit());
        a.setEyePainLightSensitive(b.getEyePainLightSensitive());
        a.setFatigueWeakness(b.getFatigueWeakness());
        a.setFever(b.getFever());
        a.setGumsBleeding(b.getGumsBleeding());
        a.setHeadache(b.getHeadache());
        a.setHearingloss(b.getHearingloss());
        a.setHiccups(b.getHiccups());
        a.setInjectionSiteBleeding(b.getInjectionSiteBleeding());
        a.setJointPain(b.getJointPain());
        a.setKopliksSpots(b.getKopliksSpots());
        a.setMusclePain(b.getMusclePain());
        a.setNausea(b.getNausea());
        a.setNeckStiffness(b.getNeckStiffness());
        a.setNoseBleeding(b.getNoseBleeding());
        a.setOnsetDate(b.getOnsetDate());
        a.setOnsetSymptom(b.getOnsetSymptom());
        a.setOtherHemorrhagicSymptoms(b.getOtherHemorrhagicSymptoms());
        a.setOtherHemorrhagicSymptomsText(b.getOtherHemorrhagicSymptomsText());
        a.setOtherNonHemorrhagicSymptoms(b.getOtherNonHemorrhagicSymptoms());
        a.setOtherNonHemorrhagicSymptomsText(b.getOtherNonHemorrhagicSymptomsText());
        a.setOtitisMedia(b.getOtitisMedia());
        a.setRedBloodVomit(b.getRedBloodVomit());
        a.setRefusalFeedorDrink(b.getRefusalFeedorDrink());
        a.setRunnyNose(b.getRunnyNose());
        a.setSeizures(b.getSeizures());
        a.setShock(b.getShock());
        a.setSkinBruising(b.getSkinBruising());
        a.setSkinRash(b.getSkinRash());
        a.setSoreThroat(b.getSoreThroat());
        a.setSymptomatic(b.getSymptomatic());
        a.setSymptomsComments(b.getSymptomsComments());
        a.setTemperature(b.getTemperature());
        a.setTemperatureSource(b.getTemperatureSource());
        a.setThrobocytopenia(b.getThrobocytopenia());
        a.setUnexplainedBleeding(b.getUnexplainedBleeding());
        a.setVomiting(b.getVomiting());
    }
}