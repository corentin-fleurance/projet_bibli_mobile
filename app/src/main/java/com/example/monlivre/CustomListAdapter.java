package com.example.monlivre;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<Livre> {

    ArrayList<Livre> livres;
    Context context;
    int resource;
    private static final String LOG_TAG = "Corentin";

    public CustomListAdapter(Context context, int resource, ArrayList<Livre> livres) {
        super(context, resource, livres);
        this.livres = livres;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.activity_list_item, null, true);

        }
        Livre livre = getItem(position);

        ImageView imageView = convertView.findViewById(R.id.couverture);
        Picasso.with(getContext()).load(livre.getCouverture()).into(imageView);
        Log.i(LOG_TAG,"Image url is: "+livre.getCouverture());

        TextView txtName = convertView.findViewById(R.id.titre);
        txtName.setText(livre.getTitre());
        Log.i(LOG_TAG,"Name is: "+livre.getTitre());

        TextView txtPrice = convertView.findViewById(R.id.auteur);
        txtPrice.setText(livre.getAuteur());
        Log.i(LOG_TAG,"Price is: "+livre.getAuteur());

        LinearLayout bckground = convertView.findViewById(R.id.lu);
        bckground.setBackgroundColor(Color.parseColor(livre.getColor()));
        Log.i(LOG_TAG,"Couleur is: "+livre.getColor());

        LinearLayout container = convertView.findViewsWithText();
        container.setId(livre.getId());


        return convertView;
    }
}
