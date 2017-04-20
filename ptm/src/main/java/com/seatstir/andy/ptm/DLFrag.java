package com.seatstir.andy.ptm;


import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;

/**
 * Created by fred on 3/14/2016.
 */

public class DLFrag extends DialogFragment {

    FCom fc;

    private OnFragmentInteractionListener mListener;

    public interface OnFragmentInteractionListener {
        public void onArticleSelected(int position);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onAttach(Activity activity) {
        fc = (FCom) activity;
        super.onAttach(activity);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder myAlert= new AlertDialog.Builder(getActivity());
        myAlert.setTitle("Do you really want to cancel this reservation?");
       //  myAlert.setCancelable(false);
        setCancelable(false);
        //  myAlert.setCancelable(true);
        myAlert.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //   ((MainActivity) getActivity()).doPositiveClick();
                        // fragment talks to activity, not the preferred method.
                        // use an interface
                       // ((resact) getActivity()).doPositiveClick("okokokok");
                        fc.Frag("Yes");
                    }
                }
        );
        myAlert.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //      ((resact) getActivity()).doNegativeClick();
                     //   ((resact) getActivity()).doNegativeClick("nononononon");
                        fc.Frag("noooooooooooo");

                    }
                });
        myAlert.create();
        return myAlert.create();
        // return super.onCreateDialog(savedInstanceState);
    }

}
