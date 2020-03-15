package lincyu.chapter12_contactemail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactArrayAdapter extends ArrayAdapter<EmailItem> {

	Context context;
	
	public ContactArrayAdapter(Context context,
			ArrayList<EmailItem> items) {
		super(context, 0, items);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView,
			ViewGroup parent) {

		LayoutInflater inflater = LayoutInflater.from(context);
		
		LinearLayout itemlayout = null;
		if(convertView == null) {
			itemlayout = (LinearLayout)inflater.inflate
					(R.layout.listitem, null);
		} else {
			itemlayout = (LinearLayout) convertView;
		}
		EmailItem item = (EmailItem)getItem(position);
		
		TextView tv_name = (TextView)itemlayout.
				findViewById(R.id.name);
		tv_name.setText(item.name);

		TextView tv_email = (TextView)itemlayout.
				findViewById(R.id.email);
		tv_email.setText(item.email);
		return itemlayout;
	}
}
