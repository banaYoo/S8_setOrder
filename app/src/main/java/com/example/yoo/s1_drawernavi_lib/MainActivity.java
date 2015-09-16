package com.example.yoo.s1_drawernavi_lib;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    public ArrayList<OptionObj> option;
    public ArrayList<Drinks> drinksBycategory;

    private Bundle category_args = new Bundle();
    private Bundle bundle_gridimage = new Bundle();
    private String cafeName = "none";
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    //private CharSequence mTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        cafeName = intent.getStringExtra(SelectCafeActivity.CAFE_NAME);
        ParsingData(cafeName);

        setContentView(R.layout.activity_main);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbar_background)));

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout),category_args);

        createOrderFragment();
    }

    private void createOrderFragment() {
        OrderFragment orderFragment = new OrderFragment();
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.slideLayout, orderFragment, OrderFragment.TAG)
                .commit();
    }

    public void ParsingData(String filename){
        //parse Json
        InputStream is = getResources().openRawResource(getResources().getIdentifier(filename, "raw", getPackageName()));
        Reader reader = new BufferedReader(new InputStreamReader(is), 8092);
        JsonParser parser = new JsonParser();
        JsonObject jsonObj = (JsonObject)parser.parse(reader);

        /**
         * <TypeToken>
         * Represents a generic type T. You can use this class to get the generic type for a class. For example, to get the generic type for Collection<Foo>, you can use:
         * Type typeOfCollectionOfFoo = new TypeToken<Collection<Foo>>(){}.getType()
         * */
        Gson gson = new Gson();
        Type optionCollectionType = new TypeToken<Collection<OptionObj>>(){}.getType();
        option = gson.fromJson(jsonObj.get("option").toString(), optionCollectionType);
        Type drinksCollectionType = new TypeToken<Collection<Drinks>>(){}.getType();
        drinksBycategory = gson.fromJson(jsonObj.get("drink").toString(), drinksCollectionType);

        setGridIMGBundle(0);
        setDrawerCategoryBundle();
    }

    public void setGridIMGBundle(int index){
        if(drinksBycategory!=null){
            bundle_gridimage.putString("cafename", cafeName);
            bundle_gridimage.putParcelableArrayList("productsList", drinksBycategory.get(index).getDrinkList());
        }
    }

    public void setDrawerCategoryBundle(){
        if(drinksBycategory!=null){
            ArrayList<String> categoryString = new ArrayList<String>();
            Iterator<Drinks> i = drinksBycategory.iterator();
            while (i.hasNext()) {
                categoryString.add(i.next().getCategory());//**
            }
            category_args.putStringArrayList("categorylist", categoryString);
        }
    }

    //Drawer navi 선택하면 ShowGridmenu의 newInstance메서드에 인자 값 넣어서 뿌리는 거징
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        //mine
        bundle_gridimage.putInt("position",position);
        setGridIMGBundle(position);
        GridmenuFragment frag_GridMenu = new GridmenuFragment();
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container,frag_GridMenu.newInstance(bundle_gridimage))
                .commit();
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        //actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
//            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_done) {
            Toast.makeText(this, "this!!!.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this ,OrderFormActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
