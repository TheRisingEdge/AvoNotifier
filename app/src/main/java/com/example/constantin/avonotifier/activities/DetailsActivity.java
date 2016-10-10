package com.example.constantin.avonotifier.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.example.constantin.avonotifier.R;
import com.example.constantin.avonotifier.impl.GoogleCalenderScheduler;
import com.example.constantin.avonotifier.logic.ICalendarScheduler;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {
    @BindView(R.id.detail_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.details_activity);
        ButterKnife.bind(this);

        ShowActionBarUpButton();
        SetupTitle(bundle);
        AddDossieDetailsFragment(bundle);
    }

    private void SetupTitle(Bundle bundle) {
        Intent intent = getIntent();
        String dossierId = intent.getStringExtra(DetailsFragment.DOSSIE_ID);

        ActionBar a = getSupportActionBar();
        a.setTitle(dossierId);
    }

    private void ShowActionBarUpButton() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void AddDossieDetailsFragment(Bundle bundle) {
         boolean addFragment = bundle == null;
         if (addFragment) {
            Bundle arguments = new Bundle();
            Intent intent = getIntent();
            String dossieId = intent.getStringExtra(DetailsFragment.DOSSIE_ID);
            arguments.putString(DetailsFragment.DOSSIE_ID, dossieId);

            DetailsFragment fragment = new DetailsFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            NavUtils.navigateUpTo(this, new Intent(this, TracksActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
