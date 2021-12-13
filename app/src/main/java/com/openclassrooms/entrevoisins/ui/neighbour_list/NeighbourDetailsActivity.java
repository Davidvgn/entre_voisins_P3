package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.databinding.NeighbourDetailActivityBinding;

public class NeighbourDetailsActivity extends AppCompatActivity {

    private NeighbourDetailActivityBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = NeighbourDetailActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String avatar = intent.getStringExtra("neighbour_detail_iv_avatar");
        String name = intent.getStringExtra("neighbour_detail_tv_name");
        String address = intent.getStringExtra("neighbour_detail_tv_address");
        String phone = intent.getStringExtra("neighbour_detail_tv_phone");
        String aboutMe = intent.getStringExtra("neighbour_detail_tv_aboutMe");

        ImageView mFavoriteAvatar = binding.neighbourDetailIvAvatar;
        TextView mFavoriteName = binding.neighbourDetailTvName;
        TextView mFavoriteAddress = binding.neighbourDetailTvAddress;
        TextView mFavoritePhone = binding.neighbourDetailTvPhone;
        TextView mFavoriteAboutMe = binding.neighbourDetailTvAboutMe;
        Toolbar mToolbar = binding.toolbar;
        CollapsingToolbarLayout toolBarLayout = binding.neighbourDetailCtl;
        Glide.with(this).asBitmap().load(avatar).into(mFavoriteAvatar);

        mFavoriteName.setText(name);
        mFavoriteAddress.setText(address);
        mFavoritePhone.setText(phone);
        mFavoriteAboutMe.setText(aboutMe);

        String neighbourName = name.toString();
        toolBarLayout.setTitle(neighbourName);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = binding.neighbourDetailFabAddFavorite;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Ajouté à vos favoris", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}