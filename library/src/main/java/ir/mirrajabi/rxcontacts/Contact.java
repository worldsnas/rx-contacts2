/*
 * Copyright (C) 2016 Ulrich Raab.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ir.mirrajabi.rxcontacts;


import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;


/**
 * Contact entity.
 * @author Ulrich Raab
 * @author MADNESS
 */

public class Contact implements Comparable<Contact>,Parcelable {
    private final long mId;
    private int mInVisibleGroup;
    private String mDisplayName;
    private boolean mStarred;
    private Uri mPhoto;
    private Uri mThumbnail;
    private List<String> mEmails = new ArrayList<>();
    private List<String> mPhoneNumbers = new ArrayList<>();

    Contact(long id) {
        this.mId = id;
    }

    public long getId() {
        return mId;
    }

    public int getInVisibleGroup() {
        return mInVisibleGroup;
    }

    public void setInVisibleGroup(int inVisibleGroup) {
        mInVisibleGroup = inVisibleGroup;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public void setDisplayName(String displayName) {
        mDisplayName = displayName;
    }

    public boolean isStarred() {
        return mStarred;
    }

    public void setStarred(boolean starred) {
        mStarred = starred;
    }

    public Uri getPhoto() {
        return mPhoto;
    }

    public void setPhoto(Uri photo) {
        mPhoto = photo;
    }

    public Uri getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(Uri thumbnail) {
        mThumbnail = thumbnail;
    }

    public List<String> getEmails() {
        return mEmails;
    }

    public void setEmails(List<String> emails) {
        mEmails = emails;
    }

    public List<String> getPhoneNumbers() {
        return mPhoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        mPhoneNumbers = phoneNumbers;
    }


    @Override
    public int compareTo(@NonNull Contact other) {
        if(mDisplayName != null && other.mDisplayName != null)
            return mDisplayName.compareTo(other.mDisplayName);
        else return -1;
    }

    @Override
    public int hashCode () {
        return (int) (mId ^ (mId >>> 32));
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contact contact = (Contact) o;
        return mId == contact.mId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.mId);
        dest.writeInt(this.mInVisibleGroup);
        dest.writeString(this.mDisplayName);
        dest.writeByte(this.mStarred ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.mPhoto, flags);
        dest.writeParcelable(this.mThumbnail, flags);
        dest.writeStringList(this.mEmails);
        dest.writeStringList(this.mPhoneNumbers);
    }

    private Contact(Parcel in) {
        this.mId = in.readLong();
        this.mInVisibleGroup = in.readInt();
        this.mDisplayName = in.readString();
        this.mStarred = in.readByte() != 0;
        this.mPhoto = in.readParcelable(Uri.class.getClassLoader());
        this.mThumbnail = in.readParcelable(Uri.class.getClassLoader());
        this.mEmails = in.createStringArrayList();
        this.mPhoneNumbers = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
