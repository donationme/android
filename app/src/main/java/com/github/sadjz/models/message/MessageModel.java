package com.github.sadjz.models.message;

import android.os.Parcel;
import android.os.Parcelable;

public class MessageModel implements Parcelable {

    private final boolean state;

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

    public static final Parcelable.Creator<MessageModel> CREATOR =
            new Parcelable.Creator<MessageModel>() {
                public MessageModel createFromParcel(Parcel in) {
                    return new MessageModel(in);
                }

                public MessageModel[] newArray(int size) {
                    return new MessageModel[size];
                }
            };

    public boolean getState() {
        return state;
    }

}
