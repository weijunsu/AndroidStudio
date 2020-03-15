package lincyu.chapter13_culture;

import android.os.Parcel;
import android.os.Parcelable;

public class Facility implements Parcelable {
	
	String name;
	String intro;
	String opentime;
	String price;
	String website;
	double lng;
	double lat;
	
	Facility(String name, String intro, String opentime,
			String price, String website,
			double lng, double lat) {
		
		this.name = name;
		this.intro = intro;
		this.opentime = opentime;
		this.price = price;
		this.website = website;
		this.lng = lng;
		this.lat = lat;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public static Creator<Facility> CREATOR =
			new Creator<Facility>() {
		
		public Facility createFromParcel(Parcel in) {
			String name = in.readString();
			String intro = in.readString();
			String opentime = in.readString();
			String price = in.readString();
			String website = in.readString();
			double lng = in.readDouble();
			double lat =in.readDouble();
			return new Facility(name, intro, opentime,
					price, website, lng, lat);
		}
		public Facility [] newArray(int size) {
			return new Facility[size];
		}
	};
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {		
		out.writeString(name);
		out.writeString(intro);
		out.writeString(opentime);
		out.writeString(price);
		out.writeString(website);
		out.writeDouble(lng);
		out.writeDouble(lat);
	}
}
