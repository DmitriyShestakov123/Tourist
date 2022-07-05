package com.example.tourist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Profile extends AppCompatActivity {

    public static class Favorite {
        public final String name;

        public Favorite(String name) {
            this.name = name;
        }

        public void remove() {
            FavoriteList.remove(this);
        }

        public String getName() {
            return name;
        }
    }

    static ArrayList<Favorite> FavoriteList = new ArrayList<>();

    public static void addToFavorite(String name) {
        AtomicBoolean nameFlag = new AtomicBoolean(false);
        if (FavoriteList.size()!=0) {
            FavoriteList.forEach(favorite -> {
                if (Objects.equals(name, favorite.getName())) {
                    nameFlag.set(true);
                }
            });
            if(!nameFlag.get()) {
                FavoriteList.add(new Favorite(name));
                nameFlag.set(true);
            }
        }
        if (FavoriteList.size()==0 || !nameFlag.get()) {
            FavoriteList.add(new Favorite(name));
            nameFlag.set(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        setSupportActionBar(findViewById(R.id.openMenu));

        LinearLayout lin = findViewById(R.id.cartlayout);
        lin.removeAllViews();
        if (FavoriteList != null) {
            AtomicInteger total = new AtomicInteger();
            FavoriteList.forEach((favorite) -> {
                TextView txtName = new TextView(Profile.this);
                txtName.setId(FavoriteList.size());
                String str = "Место: " + favorite.name;
                txtName.setText(str);
                txtName.setTextSize(20);
                lin.addView(txtName);
                /*total.addAndGet(favorite.price * favorite.amount);*/

            });
            /*TextView totalPrice = findViewById(R.id.totalPrice);
            totalPrice.append(total.toString());*/
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_map:
                Intent intent_map = new Intent(getApplicationContext(), Map.class);
                startActivity(intent_map);
                break;
            case R.id.navigation_home:
                Intent intent_main = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent_main);
                break;
            case R.id.navigation_profile:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void deleteAll(View view){
        if (FavoriteList != null) {
            FavoriteList.removeAll(FavoriteList);
            this.recreate();
        }
    }
}
