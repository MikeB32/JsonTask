package kspe.jsontask.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import kspe.jsontask.R;
import kspe.jsontask.model.DummyContent;

public class DetailFragment
        extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);


        mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));


        // Show the dummy content as text in a TextView.
        if (mItem != null) {

            ImageView detailed_profile_pic =rootView.findViewById(R.id.detail_profile_pic);
//
            Glide.with(getContext())
                    .load(mItem.profile_pic)
                    .fitCenter()
                    .override(500,500)
                    .into(detailed_profile_pic);

            ((TextView) rootView.findViewById(R.id.detail_full_name_txt)).setText(mItem.full_name);
            ((TextView) rootView.findViewById(R.id.detail_gender_txt)).setText(mItem.gender);
            ((TextView) rootView.findViewById(R.id.detail_email_txt)).setText(mItem.email);
            ((TextView) rootView.findViewById(R.id.detail_phone_no_txt)).setText(mItem.phone_no);
            ((TextView) rootView.findViewById(R.id.detail_cell_no)).setText(mItem.cell);

            ((TextView) rootView.findViewById(R.id.detailed_location)).setText(getString(R.string.street)+mItem.street +"\n"+getString(R.string.city)+mItem.city+
                    "\n"+getString(R.string.state)+mItem.state+"\n"+getString(R.string.postcode)+mItem.postcode+"\n"+getString(R.string.coordinates)+"\n"+getString(R.string.tab)+getString(R.string.latitude)+mItem.latitude+
                    "\n"+getString(R.string.tab)+getString(R.string.longitude)+mItem.longitude +"\n"+getString(R.string.timezone)+"\n"+getString(R.string.tab)+getString(R.string.offset)+mItem.offset+
                    "\n"+getString(R.string.tab)+getString(R.string.description)+mItem.description+
                    "\n"+getString(R.string.login)+"\n"+getString(R.string.tab)+getString(R.string.uuid)+mItem.uuid+"\n"+getString(R.string.tab)+getString(R.string.username)+mItem.username+"\n"+getString(R.string.tab)+getString(R.string.password)+mItem.password+"\n"+getString(R.string.tab)+getString(R.string.salt)+mItem.salt+"\n"+getString(R.string.tab)+getString(R.string.md5)+mItem.md5+"\n"+getString(R.string.tab)+getString(R.string.sha1)+mItem.sha1+"\n"+getString(R.string.tab)+getString(R.string.sha256)+mItem.sha256+
                    "\n"+getString(R.string.dob)+"\n"+getString(R.string.tab)+getString(R.string.date)+mItem.dobDate+ "\n"+getString(R.string.tab)+getString(R.string.age)+mItem.dobAge+
                    "\n"+getString(R.string.register)+"\n"+getString(R.string.tab)+getString(R.string.date)+mItem.registerDate+ "\n"+getString(R.string.tab)+getString(R.string.age)+mItem.registerAge+
                    "\n"+getString(R.string.id)+"\n"+getString(R.string.tab)+getString(R.string.name)+mItem.idName+ "\n"+getString(R.string.tab)+getString(R.string.value)+mItem.idValue+
                    "\n"+getString(R.string.nationality )+mItem.nat);


        }

        return rootView;
    }
}
