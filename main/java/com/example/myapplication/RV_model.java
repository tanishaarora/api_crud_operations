package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RV_model {

    private List<SRMLight> energy_light;
    private String wsname;
    private String responsecode;
    private String responseMsg;
    private boolean customResponse;

    public String getResponsecode() {
        return responsecode;
    }

    public void setResponsecode(String responsecode) {
        this.responsecode = responsecode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public List<SRMLight> getEnergy_light() {
        return energy_light;
    }

    public void setEnergy_light(List<SRMLight> energy_light) {
        this.energy_light = energy_light;
    }

    public String getWsname() {
        return wsname;
    }

    public void setWsname(String wsname) {
        this.wsname = wsname;
    }

    public boolean isCustomResponse() {
        return customResponse;
    }

    public void setCustomResponse(boolean customResponse) {
        this.customResponse = customResponse;
    }
}