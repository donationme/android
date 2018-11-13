package com.github.sadjz.consts;

/**
 * The enum Message identifier.
 */
public enum MessageIdentifier {
    /**
     * Donation item message identifier.
     */
    DonationItem("DonationItemData"),
    /**
     * Donation edit item message identifier.
     */
    DonationEditItem("DonationEditItemData"),
    /**
     * Donation remove item message identifier.
     */
    DonationRemoveItem("DonationRemoveItemData"),
    /**
     * Donation add item message identifier.
     */
    DonationAddItem("DonationAddItemData"),
    /**
     * Location message identifier.
     */
    Location("LocationData"),
    /**
     * Message message identifier.
     */
    Message("MessageData"),
    ;

    private final String identifier;

    MessageIdentifier(String endpointPath) {
        this.identifier = endpointPath;
    }

    /**
     * Gets message identifier.
     *
     * @return the message identifier
     */
    public String getMessageIdentifier() {
        return this.identifier;
    }
}
