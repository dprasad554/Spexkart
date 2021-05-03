package com.gsatechworld.spexkart.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.gsatechworld.spexkart.R;
import com.gsatechworld.spexkart.adapter.FaceshapeAdapter;
import com.gsatechworld.spexkart.adapter.NavigationAdapter;
import com.gsatechworld.spexkart.adapter.Navigationmenu_ItemAdapter;
import com.gsatechworld.spexkart.fragment.HomeFragment;
import com.gsatechworld.spexkart.fragment.OrdersFragment;
import com.gsatechworld.spexkart.fragment.ProfileFragment;
import com.gsatechworld.spexkart.fragment.WishListFragment;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Fragment viewFragment;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    BottomNavigationView bottomNavigationView;
    ImageView iv_cart;
    private int mCurNavigationId;
    DrawerLayout drawer;
    ImageView iv_menu;
    ActionBarDrawerToggle toggle;
    ArrayList Typelist = new ArrayList<>(Arrays.asList("","• Full rim","• Half rim","• Rimless"));
    RecyclerView nav_recyclerView;
    ArrayList menuicons = new ArrayList<>(Arrays.asList(R.drawable.ic_menu_two,R.drawable.ic_menu_three,R.drawable.ic_menu_four,
            R.drawable.ic_menu_five,R.drawable.ic_menu_six));
    ArrayList menunames = new ArrayList<>(Arrays.asList("Computer glasses","Color Blink Glasses","Eyeglasses","Sunglasses","Power Glasses"));



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_cart = findViewById(R.id.iv_cart);
        iv_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CartListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        iv_menu = findViewById(R.id.iv_menu);
        //For Navigation Drawer
        mCurNavigationId = R.id.home;
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(MainActivity.this, drawer, null, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.LEFT);

            }
        });


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(MainActivity.this);
        navigationView.setItemIconTintList(null);
        View hView = navigationView.getHeaderView(0);

        //For Fragment
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new HomeFragment()).commit();
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        viewFragment = new HomeFragment();
                        mFragmentTransaction = mFragmentManager.beginTransaction();
                        mFragmentTransaction.replace(R.id.containerView,viewFragment).commit();
                        return true;
                    case R.id.Wishlist:
                        viewFragment = new WishListFragment();
                        mFragmentTransaction = mFragmentManager.beginTransaction();
                        mFragmentTransaction.replace(R.id.containerView,viewFragment).commit();
                        return true;
                    case R.id.Orders:
                        viewFragment = new OrdersFragment();
                        mFragmentTransaction = mFragmentManager.beginTransaction();
                        mFragmentTransaction.replace(R.id.containerView,viewFragment).commit();
                        return true;
                    case R.id.Profile:
                        viewFragment = new ProfileFragment();
                        mFragmentTransaction = mFragmentManager.beginTransaction();
                        mFragmentTransaction.replace(R.id.containerView,viewFragment).commit();
                        return true;

                }
                return false;
            }
        });
        Window window = MainActivity.this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.colorPrimaryDark));
        }

        //For Costomer review
        nav_recyclerView =(RecyclerView)findViewById(R.id.nav_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        nav_recyclerView.setLayoutManager(linearLayoutManager);
        NavigationAdapter navigationAdapter = new NavigationAdapter(this,menuicons,menunames);
        nav_recyclerView.setAdapter(navigationAdapter);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

       /* int id = menuItem.getItemId();
        if( id == R.id.profile){
            mCurNavigationId = id;
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        }*/
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.ViewHolder> {

        //Variables
        private Context context;
        ArrayList<Integer> menuicons;
        ArrayList<String> menunames ;
        ArrayList menunitems = new ArrayList<>(Arrays.asList("• Glasses","• Color Blink","• Eyeglasses","• Sunglasses","• Power Glasses"));

        public NavigationAdapter(Context context,ArrayList<Integer> menuicons,ArrayList<String> menunames) {
            this.context = context;
            this.menuicons = menuicons;
            this.menunames = menunames;
        }

        @NonNull
        @Override
        public NavigationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_navigationmenu,parent,false);
            return new NavigationAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final NavigationAdapter.ViewHolder holder, final int position) {
            holder.menu_icon.setImageResource(menuicons.get(position));
            holder.menu_name.setText(menunames.get(position));
            holder.iv_dropdown.setVisibility(View.VISIBLE);
            holder.iv_topup.setVisibility(View.GONE);
            holder.ll_menulist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.nav_item_recyclerView.setVisibility(View.VISIBLE);
                    holder.iv_topup.setVisibility(View.VISIBLE);
                    holder.iv_dropdown.setVisibility(View.GONE);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
                    holder.nav_item_recyclerView.setLayoutManager(linearLayoutManager);
                    Navigationmenu_ItemAdapter navigationmenu_itemAdapter = new Navigationmenu_ItemAdapter(context,menunitems);
                    holder.nav_item_recyclerView.setAdapter(navigationmenu_itemAdapter);

                }
            });
            holder.iv_topup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.nav_item_recyclerView.setVisibility(View.GONE);
                    holder.iv_topup.setVisibility(View.GONE);
                    holder.iv_dropdown.setVisibility(View.VISIBLE);
                }
            });
        }

        @Override
        public int getItemCount() {
            return 5;
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            ImageView menu_icon,iv_dropdown,iv_topup;
            TextView menu_name;
            RecyclerView nav_item_recyclerView;
            LinearLayout ll_menulist;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                menu_icon = itemView.findViewById(R.id.menu_icon);
                menu_name = itemView.findViewById(R.id.menu_name);
                iv_dropdown = itemView.findViewById(R.id.iv_dropdown);
                nav_item_recyclerView = itemView.findViewById(R.id.nav_item_recyclerView);
                iv_topup = itemView.findViewById(R.id.iv_topup);
                ll_menulist = itemView.findViewById(R.id.ll_menulist);
            }
        }
    }


}