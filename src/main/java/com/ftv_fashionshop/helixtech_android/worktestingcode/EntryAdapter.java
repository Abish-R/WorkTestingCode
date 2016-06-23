package com.ftv_fashionshop.helixtech_android.worktestingcode;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
/**
 * Created by helixtech-android on 13/6/16.
 */
public class EntryAdapter extends ArrayAdapter<Item> {

    private Context context;
    private ArrayList<Item> items;
    private LayoutInflater vi;
    boolean itemsStatus[];

    public EntryAdapter(Context context,ArrayList<Item> items) {
        super(context,0, items);
        this.context = context;
        this.items = items;
        itemsStatus=new boolean[items.size()];
        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final Item i = items.get(position);
        if (i != null) {
            final TextView sectionView;
            if(i.isSection()){
                SectionItem si = (SectionItem)i;
                v = vi.inflate(R.layout.list_item_category, null);

                v.setOnClickListener(null);
                v.setOnLongClickListener(null);
                v.setLongClickable(false);

                sectionView = (TextView) v.findViewById(R.id.title);
                sectionView.setText(si.getTitle());

            }else{
                EntryItem ei = (EntryItem)i;
                v = vi.inflate(R.layout.list_item_selection, null);
                final CheckBox title = (CheckBox)v.findViewById(R.id.check);

                //final TextView subtitle = (TextView)v.findViewById(R.id.list_item_entry_summary);


                if (title != null) {
                    title.setText(ei.title);
                    title.setChecked(itemsStatus[position]);
                }
//                if(subtitle != null)
//                    subtitle.setText(ei.subtitle);
                title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(title.isChecked()) {
                            itemsStatus[position]=true;
                        }
                        else {
                            itemsStatus[position]=false;
                        }
                    }
                });
            }

        }
        return v;
    }

}
