package com.example.emmchierchie.fluxandpets.View.Adapters;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.emmchierchie.fluxandpets.Model.Pojo.Pet;
import com.example.emmchierchie.fluxandpets.R;
import com.example.emmchierchie.fluxandpets.View.Fragments.FragmentMaps;
import com.example.emmchierchie.fluxandpets.View.Fragments.FragmentPetData;
import java.util.ArrayList;
import java.util.List;

public class AdapterViewPager extends FragmentPagerAdapter {

    private Context context;
    private Pet pet;
    private final List<Fragment> fragmentList;
    private List<String> tabs;

    public AdapterViewPager(FragmentManager fm, Context context) {
        super(fm);
        fragmentList = new ArrayList<>();
        tabs = new ArrayList<>();

        // seteo los textos del tab
        tabs.add(context.getString(R.string.pet));
        tabs.add(context.getString(R.string.map));
    }

    // cargo la lista de fragments al ViewPager y notifico los cambios
    public void loadFragments(Pet pet) {
        fragmentList.add(FragmentPetData.createFragmentPetData(pet));
        fragmentList.add(FragmentMaps.createFragmentMaps());
        this.notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position);
    }
}
