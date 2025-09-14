package com.sporty.take_home.airport.view;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AirportDetailView {

    @JsonProperty("site_number")
    private String siteNumber;

    @JsonProperty("type")
    private String type;

    @JsonProperty("facility_name")
    private String facilityName;

    @JsonProperty("faa_ident")
    private String faaIdent;

    @JsonProperty("icao_ident")
    private String icaoIdent;

    @JsonProperty("district_office")
    private String districtOffice;

    @JsonProperty("state")
    private String state;

    @JsonProperty("state_full")
    private String stateFull;

    @JsonProperty("county")
    private String county;

    @JsonProperty("city")
    private String city;

    @JsonProperty("ownership")
    private String ownership;

    @JsonProperty("use")
    private String use;

    @JsonProperty("manager")
    private String manager;

    @JsonProperty("manager_phone")
    private String managerPhone;

    @JsonProperty("latitude")
    private String latitude;

    @JsonProperty("latitude_sec")
    private String latitudeSec;

    @JsonProperty("longitude")
    private String longitude;

    @JsonProperty("longitude_sec")
    private String longitudeSec;

    @JsonProperty("elevation")
    private String elevation;

    @JsonProperty("magnetic_variation")
    private String magneticVariation;

    @JsonProperty("tpa")
    private String tpa;

    @JsonProperty("vfr_sectional")
    private String vfrSectional;

    @JsonProperty("boundary_artcc")
    private String boundaryArtcc;

    @JsonProperty("boundary_artcc_name")
    private String boundaryArtccName;

    @JsonProperty("responsible_artcc")
    private String responsibleArtcc;

    @JsonProperty("responsible_artcc_name")
    private String responsibleArtccName;

    @JsonProperty("fss_phone_number")
    private String fssPhoneNumber;

    @JsonProperty("fss_phone_numer_tollfree")
    private String fssPhoneNumerTollfree;

    @JsonProperty("notam_facility_ident")
    private String notamFacilityIdent;

    @JsonProperty("status")
    private String status;

    @JsonProperty("certification_typedate")
    private String certificationTypedate;

    @JsonProperty("customs_airport_of_entry")
    private String customsAirportOfEntry;

    @JsonProperty("military_joint_use")
    private String militaryJointUse;

    @JsonProperty("military_landing")
    private String militaryLanding;

    @JsonProperty("lighting_schedule")
    private String lightingSchedule;

    @JsonProperty("beacon_schedule")
    private String beaconSchedule;

    @JsonProperty("control_tower")
    private String controlTower;

    @JsonProperty("unicom")
    private String unicom;

    @JsonProperty("ctaf")
    private String ctaf;

    @JsonProperty("effective_date")
    private String effectiveDate;

    // Getters and Setters
    public String getSiteNumber() {
        return siteNumber;
    }

    public void setSiteNumber(String siteNumber) {
        this.siteNumber = siteNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getFaaIdent() {
        return faaIdent;
    }

    public void setFaaIdent(String faaIdent) {
        this.faaIdent = faaIdent;
    }

    public String getIcaoIdent() {
        return icaoIdent;
    }

    public void setIcaoIdent(String icaoIdent) {
        this.icaoIdent = icaoIdent;
    }

    public String getDistrictOffice() {
        return districtOffice;
    }

    public void setDistrictOffice(String districtOffice) {
        this.districtOffice = districtOffice;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateFull() {
        return stateFull;
    }

    public void setStateFull(String stateFull) {
        this.stateFull = stateFull;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLatitudeSec() {
        return latitudeSec;
    }

    public void setLatitudeSec(String latitudeSec) {
        this.latitudeSec = latitudeSec;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLongitudeSec() {
        return longitudeSec;
    }

    public void setLongitudeSec(String longitudeSec) {
        this.longitudeSec = longitudeSec;
    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    public String getMagneticVariation() {
        return magneticVariation;
    }

    public void setMagneticVariation(String magneticVariation) {
        this.magneticVariation = magneticVariation;
    }

    public String getTpa() {
        return tpa;
    }

    public void setTpa(String tpa) {
        this.tpa = tpa;
    }

    public String getVfrSectional() {
        return vfrSectional;
    }

    public void setVfrSectional(String vfrSectional) {
        this.vfrSectional = vfrSectional;
    }

    public String getBoundaryArtcc() {
        return boundaryArtcc;
    }

    public void setBoundaryArtcc(String boundaryArtcc) {
        this.boundaryArtcc = boundaryArtcc;
    }

    public String getBoundaryArtccName() {
        return boundaryArtccName;
    }

    public void setBoundaryArtccName(String boundaryArtccName) {
        this.boundaryArtccName = boundaryArtccName;
    }

    public String getResponsibleArtcc() {
        return responsibleArtcc;
    }

    public void setResponsibleArtcc(String responsibleArtcc) {
        this.responsibleArtcc = responsibleArtcc;
    }

    public String getResponsibleArtccName() {
        return responsibleArtccName;
    }

    public void setResponsibleArtccName(String responsibleArtccName) {
        this.responsibleArtccName = responsibleArtccName;
    }

    public String getFssPhoneNumber() {
        return fssPhoneNumber;
    }

    public void setFssPhoneNumber(String fssPhoneNumber) {
        this.fssPhoneNumber = fssPhoneNumber;
    }

    public String getFssPhoneNumerTollfree() {
        return fssPhoneNumerTollfree;
    }

    public void setFssPhoneNumerTollfree(String fssPhoneNumerTollfree) {
        this.fssPhoneNumerTollfree = fssPhoneNumerTollfree;
    }

    public String getNotamFacilityIdent() {
        return notamFacilityIdent;
    }

    public void setNotamFacilityIdent(String notamFacilityIdent) {
        this.notamFacilityIdent = notamFacilityIdent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCertificationTypedate() {
        return certificationTypedate;
    }

    public void setCertificationTypedate(String certificationTypedate) {
        this.certificationTypedate = certificationTypedate;
    }

    public String getCustomsAirportOfEntry() {
        return customsAirportOfEntry;
    }

    public void setCustomsAirportOfEntry(String customsAirportOfEntry) {
        this.customsAirportOfEntry = customsAirportOfEntry;
    }

    public String getMilitaryJointUse() {
        return militaryJointUse;
    }

    public void setMilitaryJointUse(String militaryJointUse) {
        this.militaryJointUse = militaryJointUse;
    }

    public String getMilitaryLanding() {
        return militaryLanding;
    }

    public void setMilitaryLanding(String militaryLanding) {
        this.militaryLanding = militaryLanding;
    }

    public String getLightingSchedule() {
        return lightingSchedule;
    }

    public void setLightingSchedule(String lightingSchedule) {
        this.lightingSchedule = lightingSchedule;
    }

    public String getBeaconSchedule() {
        return beaconSchedule;
    }

    public void setBeaconSchedule(String beaconSchedule) {
        this.beaconSchedule = beaconSchedule;
    }

    public String getControlTower() {
        return controlTower;
    }

    public void setControlTower(String controlTower) {
        this.controlTower = controlTower;
    }

    public String getUnicom() {
        return unicom;
    }

    public void setUnicom(String unicom) {
        this.unicom = unicom;
    }

    public String getCtaf() {
        return ctaf;
    }

    public void setCtaf(String ctaf) {
        this.ctaf = ctaf;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
