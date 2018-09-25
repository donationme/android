package com.github.sadjz.models.account;

import com.google.gson.annotations.SerializedName;


public class AccountCreationResponse {

    private String propertyName;
    private String errorMessage;
    private String attemptedValue;
    private String customState;
    private long severity;
    private String errorCode;
    private Object[] formattedMessageArguments;
    private FormattedMessagePlaceholderValues formattedMessagePlaceholderValues;
    private String resourceName;

    @SerializedName("propertyName")
    public String getPropertyName() { return propertyName; }
    @SerializedName("propertyName")
    public void setPropertyName(String value) { this.propertyName = value; }

    @SerializedName("errorMessage")
    public String getErrorMessage() { return errorMessage; }
    @SerializedName("errorMessage")
    public void setErrorMessage(String value) { this.errorMessage = value; }

    @SerializedName("attemptedValue")
    public String getAttemptedValue() { return attemptedValue; }
    @SerializedName("attemptedValue")
    public void setAttemptedValue(String value) { this.attemptedValue = value; }

    @SerializedName("customState")
    public String getCustomState() { return customState; }
    @SerializedName("customState")
    public void setCustomState(String value) { this.customState = value; }

    @SerializedName("severity")
    public long getSeverity() { return severity; }
    @SerializedName("severity")
    public void setSeverity(long value) { this.severity = value; }

    @SerializedName("errorCode")
    public String getErrorCode() { return errorCode; }
    @SerializedName("errorCode")
    public void setErrorCode(String value) { this.errorCode = value; }

    @SerializedName("formattedMessageArguments")
    public Object[] getFormattedMessageArguments() { return formattedMessageArguments; }
    @SerializedName("formattedMessageArguments")
    public void setFormattedMessageArguments(Object[] value) { this.formattedMessageArguments = value; }

    @SerializedName("formattedMessagePlaceholderValues")
    public FormattedMessagePlaceholderValues getFormattedMessagePlaceholderValues() { return formattedMessagePlaceholderValues; }
    @SerializedName("formattedMessagePlaceholderValues")
    public void setFormattedMessagePlaceholderValues(FormattedMessagePlaceholderValues value) { this.formattedMessagePlaceholderValues = value; }

    @SerializedName("resourceName")
    public String getResourceName() { return resourceName; }
    @SerializedName("resourceName")
    public void setResourceName(String value) { this.resourceName = value; }

}






    

