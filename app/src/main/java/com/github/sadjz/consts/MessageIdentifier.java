package com.github.sadjz.consts;

public enum MessageIdentifier {
    DonationItem("DonationItemData"),
    DonationEditItem("DonationEditItemData"),
    DonationRemoveItem("DonationRemoveItemData"),
    DonationAddItem("DonationAddItemData"),
    Location("LocationData"),
    Message("MessageData"),
    ;

    private final String identifier;

    MessageIdentifier(String endpointPath) {
        this.identifier = endpointPath;
    }

    public String getMessageIdentifier() {
        return this.identifier;
    }
}
