package edu.uw.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import static android.support.v4.view.PagerAdapter.POSITION_NONE;

public class MainActivity extends AppCompatActivity implements MovieListFragment.OnMovieSelectedListener, SearchFragment.OnSearchListener {

    public static final String MOVIE_LIST_FRAGMENT_TAG = "MoviesListFragment";
    public static final String MOVIE_DETAIL_FRAGMENT_TAG = "DetailFragment";
    private static final String TAG = "MainActivity";
    private SearchFragment sf;
    private MovieListFragment lf;
    private DetailFragment df;

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sf = SearchFragment.newInstance();

        viewPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new MoviePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);


    }

    @Override
    public void onMovieSelected(Movie movie) {
        df = DetailFragment.newInstance(movie);

        pagerAdapter.notifyDataSetChanged();
        viewPager.setCurrentItem(2);
    }

    @Override
    public void onSearchSubmitted(String searchTerm) {

        Log.v("MainActivity SearchTerm", searchTerm);

         lf = MovieListFragment.newInstance(searchTerm);

        pagerAdapter.notifyDataSetChanged();
        viewPager.setCurrentItem(1);

    }



    class MoviePagerAdapter extends FragmentStatePagerAdapter {

        public MoviePagerAdapter(FragmentManager fm) {


            super(fm);
        }

        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return sf;
            } else if (position == 1) {
                return lf;
            } else {
                return df;
            }
        }

        @Override
        public int getCount() {
            if (lf == null && df == null && sf != null) {
                return 1;
            } else if (sf != null && lf != null && df == null) {
                return 2;
            } else /*all are showing*/{
                return 3;
            }
        }

    }

}
