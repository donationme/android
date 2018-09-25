package com.github.sadjz.models.account;

import com.google.gson.annotations.SerializedName;

public class FormattedMessagePlaceholderValues {

    private String propertyName;
    private String propertyValue;

    @SerializedName("PropertyName")
    public String getPropertyName() { return propertyName; }
    @SerializedName("PropertyName")
    public void setPropertyName(String value) { this.propertyName = value; }

    @SerializedName("PropertyValue")
    public String getPropertyValue() { return propertyValue; }
    @SerializedName("PropertyValue")
    public void setPropertyValue(String value) { this.propertyValue = value; }
}
