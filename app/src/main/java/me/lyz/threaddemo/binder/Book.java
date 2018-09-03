package me.lyz.threaddemo.binder;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author LYZ 2018.08.30
 */
public class Book implements Parcelable {
    private int bookId;
    private String bookName;

    protected Book(Parcel in) {
        bookId = in.readInt();
        bookName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(bookId);
        dest.writeString(bookName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
