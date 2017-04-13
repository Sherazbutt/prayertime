package com.example.butt.newbar;

        import android.app.Activity;
        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

/**
 * Created by Belal on 7/22/2015.
 */
public class prayer_customlist extends ArrayAdapter<String> {
    private String[] names;
    private String[] desc;
    private Integer[] imageid;
    private Activity context;

    public prayer_customlist(Activity context, String[] names) {
        super(context, R.layout.list_single, names);
        this.context = context;
        this.names = names;
        // this.desc = desc;
        this.imageid = imageid;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.dua_list, null, true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        //  TextView textViewDesc = (TextView) listViewItem.findViewById(R.id.textViewDesc);

        textViewName.setText(names[position]);
        // textViewDesc.setText(desc[position]);
        return  listViewItem;
    }
}