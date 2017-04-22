package com.yiwen.guet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.yiwen.guet.R;
import com.yiwen.guet.db.LinkNode;

import java.util.List;

public class MenuAdapter extends ArrayAdapter<LinkNode> {
	private int resourceId;

	public MenuAdapter(Context context, int resource, List<LinkNode> objects) {
		super(context, resource, objects);
		this.resourceId = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinkNode item = getItem(position);
		View view;
		ViewHolder viewHolder;
		if (convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder = new ViewHolder();
			viewHolder.icon = (ImageView) view.findViewById(R.id.icon);
			viewHolder.title = (TextView) view.findViewById(R.id.title);
			view.setTag(viewHolder);
		} else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.icon.setBackgroundResource(R.drawable.icon);
		viewHolder.title.setText(item.getTitle());
		return view;
	}

	class ViewHolder {
		ImageView icon;
		TextView title;
	}
}
