package com.microsoft.xbox.idp.util;

import android.app.Fragment;
import android.os.Parcel;
import android.os.Parcelable;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * 07.01.2021
 *
 * @author Тимашков Иван
 * @author https://github.com/TimScriptov
 */

public class FragmentLoaderKey implements Parcelable {
    public static final Parcelable.Creator<FragmentLoaderKey> CREATOR = new Parcelable.Creator<FragmentLoaderKey>() {
        @Contract("_ -> new")
        public @NotNull FragmentLoaderKey createFromParcel(Parcel parcel) {
            return new FragmentLoaderKey(parcel);
        }

        @Contract(value = "_ -> new", pure = true)
        public FragmentLoaderKey @NotNull [] newArray(int i) {
            return new FragmentLoaderKey[i];
        }
    };
    private final String className;
    private final int loaderId;

    public FragmentLoaderKey(@NotNull Class<? extends Fragment> cls, int i) {
        this.className = cls.getName();
        this.loaderId = i;
    }

    protected FragmentLoaderKey(@NotNull Parcel parcel) {
        this.className = parcel.readString();
        this.loaderId = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FragmentLoaderKey fragmentLoaderKey = (FragmentLoaderKey) obj;
        if (this.loaderId != fragmentLoaderKey.loaderId) {
            return false;
        }
        return this.className.equals(fragmentLoaderKey.className);
    }

    public int hashCode() {
        return (this.className.hashCode() * 31) + this.loaderId;
    }

    public void writeToParcel(@NotNull Parcel parcel, int i) {
        parcel.writeString(this.className);
        parcel.writeInt(this.loaderId);
    }
}
