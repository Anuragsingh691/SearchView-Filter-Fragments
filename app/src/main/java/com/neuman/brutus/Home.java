package com.neuman.brutus;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.neuman.brutus.utils.FragmentHandler;
import com.neuman.brutus.utils.Globals;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity implements View.OnClickListener{
    private Fragment fragment = null;
    public FragmentHandler fragmentHandler;
    public FragmentManager fr_man = getSupportFragmentManager();
    Globals g;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        g = new Globals();
        g.addFragments(getSupportFragmentManager());

        imageView = findViewById(R.id.menu_nav_1);
        imageView.setTag("HOME");
        imageView.setOnClickListener(this);

        imageView = findViewById(R.id.menu_nav_2);
        imageView.setTag("ASSETS");
        imageView.setOnClickListener(this);

        imageView = findViewById(R.id.menu_nav_3);
        imageView.setTag("TICKETS");
        imageView.setOnClickListener(this);

        imageView = findViewById(R.id.menu_nav_4);
        imageView.setTag("ORGANISATION");
        imageView.setOnClickListener(this);

        imageView = findViewById(R.id.menu_nav_5);
        imageView.setTag("SPARES");
        imageView.setOnClickListener(this);

        imageView = findViewById(R.id.fab_btn);
        imageView.setTag("MORE");
        imageView.setOnClickListener(this);

        fragmentHandler = new FragmentHandler(fr_man, g);
        getSupportFragmentManager().beginTransaction().show(g.fragments.get("HOME")).commit();
        g.cur = "HOME";
    }

    @Override
    public void onClick(View v) {
        String next = v.getTag().toString();
        if (!next.equals(g.cur)) {
            fragmentHandler.transition(next, null);
            g.cur = next;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu_search,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search_mine) {
            displayView(0);
            return true;
        }
        if (id == R.id.price_filter) {
            displayView(1);
            return true;
        }
        if (id == R.id.Rank_filter) {
            displayView(2);
            return true;
        }
        if (id == R.id.Date_Sort_filter) {
            displayView(3);
            return true;
        }
        if (id == R.id.date_created) {
            displayView(4);
            return true;
        }
        if (id == R.id.price_between) {
            displayView(5);
            return true;
        }
        if (id == R.id.Status) {
            displayView(6);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
       startActivity(new Intent(Home.this,Home.class));
    }
    private void displayView(int position) {
        fragment = null;
        String fragmentTags = "";
        switch (position) {
            case 0:
                fragment = new SearchFragment();
                break;
            case 1:
                fragment = new PriceFilterFragment();
                break;
            case 2:
                fragment = new RankFilterFragment();
                break;
            case 3:
                fragment = new DateSortFilterFragment();
                break;
            case 4:
                fragment = new DateCreatedFilterFragment();
                break;
            case 5:
                fragment = new Price_BetweenFilter_Fragment();
                break;
            case 6:
                fragment = new StatusFilterFragment();
                break;
            default:
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,fragment).addToBackStack(null).commit();
    }
}
/*case 0:
                    fragment = new WorkingStatusFragment();
                    break;
                case 1:
                    fragment = new NotWorkingStatusFragment();
                    break;
                case 2:
                    fragment = new PendingStatusFragment();
                    break;

                default:
                    break;*/
