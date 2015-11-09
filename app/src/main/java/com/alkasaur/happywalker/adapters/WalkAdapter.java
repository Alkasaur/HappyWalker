package com.alkasaur.happywalker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.alkasaur.happywalker.R;
import com.alkasaur.happywalker.model.Entry;
import com.alkasaur.happywalker.model.EntryStatus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * TODO: add class header comment!
 */
public class WalkAdapter extends ArrayAdapter<Entry> {

    private static final SimpleDateFormat FORMATER = new SimpleDateFormat("dd/MM/yyyy");

    private List<Entry> entries;
    private LayoutInflater inflater;

    public WalkAdapter(Context context) {
        super(context, 0);
        entries = new ArrayList<>();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void add(Entry entry) {
        entries.add(entry);
    }

    @Override
    public Entry getItem(int position) {
        return entries.get(position);
    }

    @Override
    public int getCount() {
        return entries.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.entry_list_item_layout, null);
        }

        Entry entry = getItem(position);

        TextView time = (TextView) convertView.findViewById(R.id.date);
        TextView distance = (TextView) convertView.findViewById(R.id.distance);
        View spinner = convertView.findViewById(R.id.spinner);

        time.setText(FORMATER.format(new Date(entry.getTime())));
        distance.setText(String.valueOf(entry.getDistanceM() / 1000));
        if (entry.getStatus() == EntryStatus.active) {
            spinner.setVisibility(View.VISIBLE);
        } else {
            spinner.setVisibility(View.GONE);
        }

        return convertView;
    }
}
