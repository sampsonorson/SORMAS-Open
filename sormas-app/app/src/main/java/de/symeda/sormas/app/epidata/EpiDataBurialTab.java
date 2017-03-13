package de.symeda.sormas.app.epidata;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import de.symeda.sormas.app.R;
import de.symeda.sormas.app.backend.common.AbstractDomainObject;
import de.symeda.sormas.app.backend.common.DatabaseHelper;
import de.symeda.sormas.app.backend.epidata.EpiDataBurial;
import de.symeda.sormas.app.backend.location.Location;
import de.symeda.sormas.app.component.AbstractFormDialogFragment;
import de.symeda.sormas.app.component.LocationDialog;
import de.symeda.sormas.app.component.TextField;
import de.symeda.sormas.app.databinding.EpidataBurialEditFragmentLayoutBinding;
import de.symeda.sormas.app.util.Consumer;
import de.symeda.sormas.app.util.DataUtils;

/**
 * Created by Mate Strysewske on 09.03.2017.
 */

public class EpiDataBurialTab extends AbstractFormDialogFragment<EpiDataBurial> {

    private EpidataBurialEditFragmentLayoutBinding binding;

    @Override
    public View onCreateDialogView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.epidata_burial_edit_fragment_layout, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.setEpiDataBurial(getFormItem());
        binding.burialFrom.initialize(this);
        binding.burialTo.initialize(this);

        EpiDataBurial burial = binding.getEpiDataBurial();
        try {
            final Location location = burial.getBurialAddress() != null ? burial.getBurialAddress() : DataUtils.createNew(Location.class);
            LocationDialog.addLocationField(getActivity(), location, binding.burialAddress, binding.formCpBtnAddress, new Consumer() {
                @Override
                public void accept(Object parameter) {
                    if (parameter instanceof Location) {
                        binding.burialAddress.setValue(parameter.toString());
                        binding.getEpiDataBurial().setBurialAddress(((Location)parameter));
                    }
                }
            });
        } catch(Exception e) {
            Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public AbstractDomainObject getData() {
        return binding.getEpiDataBurial();
    }
}