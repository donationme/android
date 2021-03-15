package com.github.sadjz.models.message;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * The type Message model.
 */
public class MessageModel implements Parcelable {

    private final boolean state;

    /**
     * Instantiates a new Message model.
     *
     * @param state the state
     */
    public MessageModel(boolean state) {
        this.state = state;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    private MessageModel(Parcel in) {
        state = in.readInt() > 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(state ? 1 : 0);
    }

    /**
     * The constant CREATOR.
     */
    public static final Parcelable.Creator<MessageModel> CREATOR =
            new Parcelable.Creator<MessageModel>() {
                @Override
                public MessageModel createFromParcel(Parcel in) {
                    return new MessageModel(in);
                }

                @Override
                public MessageModel[] newArray(int size) {
                    return new MessageModel[size];
                }
            };

    /**
     * Gets state.
     *
     * @return the state
     */
    public boolean getState() {
        return state;
    }

}
