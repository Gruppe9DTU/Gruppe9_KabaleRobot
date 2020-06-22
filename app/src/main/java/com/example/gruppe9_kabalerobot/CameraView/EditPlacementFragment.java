package com.example.gruppe9_kabalerobot.CameraView;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.example.gruppe9_kabalerobot.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditPlacementFragment extends Fragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private Button waste, foundation1, foundation2, foundation3, foundation4;
    private Button tab1For, tab2For, tab3For, tab4For, tab5For, tab6For, tab7For;
    private Button tab1Bag, tab2Bag, tab3Bag, tab4Bag, tab5Bag, tab6Bag, tab7Bag;
    private EditText hiddencardsTab1,hiddencardsTab2,hiddencardsTab3,
            hiddencardsTab4,hiddencardsTab5,hiddencardsTab6,hiddencardsTab7;
    private ToggleButton wasteToggle;
    private FloatingActionButton done;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) { super.onCreate(savedInstanceState); }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_edit_placement, container, false);

        waste               = view.findViewById(R.id.wasteCard);
        foundation1         = view.findViewById(R.id.foundation1);
        foundation2         = view.findViewById(R.id.foundation2);
        foundation3         = view.findViewById(R.id.foundation3);
        foundation4         = view.findViewById(R.id.foundation4);
        tab1For             = view.findViewById(R.id.tab1F);
        tab2For             = view.findViewById(R.id.tab2F);
        tab3For             = view.findViewById(R.id.tab3F);
        tab4For             = view.findViewById(R.id.tab4F);
        tab5For             = view.findViewById(R.id.tab5F);
        tab6For             = view.findViewById(R.id.tab6F);
        tab7For             = view.findViewById(R.id.tab7F);
        tab1Bag             = view.findViewById(R.id.tab1B);
        tab2Bag             = view.findViewById(R.id.tab2B);
        tab3Bag             = view.findViewById(R.id.tab3B);
        tab4Bag             = view.findViewById(R.id.tab4B);
        tab5Bag             = view.findViewById(R.id.tab5B);
        tab6Bag             = view.findViewById(R.id.tab6B);
        tab7Bag             = view.findViewById(R.id.tab7B);
        hiddencardsTab1     = view.findViewById(R.id.hiddencardsTab1);
        hiddencardsTab2     = view.findViewById(R.id.hiddencardsTab2);
        hiddencardsTab3     = view.findViewById(R.id.hiddencardsTab3);
        hiddencardsTab4     = view.findViewById(R.id.hiddencardsTab4);
        hiddencardsTab5     = view.findViewById(R.id.hiddencardsTab5);
        hiddencardsTab6     = view.findViewById(R.id.hiddencardsTab6);
        hiddencardsTab7     = view.findViewById(R.id.hiddencardsTab7);
        wasteToggle         = view.findViewById(R.id.toggleWaste);
        done                = view.findViewById(R.id.done);

        wasteToggle.setOnCheckedChangeListener(this);
        waste.setOnClickListener(this);
        foundation1.setOnClickListener(this);
        foundation2.setOnClickListener(this);
        foundation3.setOnClickListener(this);
        foundation4.setOnClickListener(this);
        tab1For.setOnClickListener(this);
        tab2For.setOnClickListener(this);
        tab3For.setOnClickListener(this);
        tab4For.setOnClickListener(this);
        tab5For.setOnClickListener(this);
        tab6For.setOnClickListener(this);
        tab7For.setOnClickListener(this);
        tab1Bag.setOnClickListener(this);
        tab2Bag.setOnClickListener(this);
        tab3Bag.setOnClickListener(this);
        tab4Bag.setOnClickListener(this);
        tab5Bag.setOnClickListener(this);
        tab6Bag.setOnClickListener(this);
        tab7Bag.setOnClickListener(this);
        done.setOnClickListener(this);






        return view;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.wasteCard:

            case R.id.foundation1:

            case R.id.foundation2:

            case R.id.foundation3:

            case R.id.foundation4:

            case R.id.tab1F:

            case R.id.tab2F:

            case R.id.tab3F:

            case R.id.tab4F:

            case R.id.tab5F:

            case R.id.tab6F:

            case R.id.tab7F:

            case R.id.tab1B:

            case R.id.tab2B:

            case R.id.tab3B:

            case R.id.tab4B:

            case R.id.tab5B:

            case R.id.tab6B:

            case R.id.tab7B:

            case R.id.done:

        }

    }
}
