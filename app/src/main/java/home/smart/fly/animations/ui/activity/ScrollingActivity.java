package home.smart.fly.animations.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import home.smart.fly.animations.R;
import home.smart.fly.animations.utils.DpConvert;
import home.smart.fly.animations.widget.CustomCoordinatorLayout;

public class ScrollingActivity extends AppCompatActivity {
    private static final String TAG = "ScrollingActivity";
    private TabLayout mTabLayout;
    private LinearLayout mLinearLayout;
    private View content;
    private TextView mTextView;
    private ViewPager mViewPager;

    private AppBarLayout mAppBarLayout;
    private int screenHeight;

    private boolean open = false;
    private Toolbar toolbar;

    private boolean isReverse;
    private CustomCoordinatorLayout mViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        mTabLayout = findViewById(R.id.bottom_tab);
        mLinearLayout = findViewById(R.id.bottom_container);
        content = findViewById(R.id.bottom);
        mTextView = findViewById(R.id.text);

        mAppBarLayout = findViewById(R.id.app_bar);

        mAppBarLayout.addOnOffsetChangedListener((appBarLayout, i) -> Log.e(TAG, "onOffsetChanged: i==" + i));


        mViewGroup = findViewById(R.id.shell);

        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setTabTextColors(R.color.green,R.color.black);
//        findViewById(R.id.image).setOnClickListener(v -> {
//            if (isReverse) {
//                mLinearLayout.animate().translationYBy(300).start();
//                content.animate().translationYBy(300).start();
//            } else {
//                mLinearLayout.animate().translationYBy(-300).start();
//                content.animate().translationYBy(-300).start();
//            }
//            isReverse = !isReverse;
//        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenHeight = metrics.heightPixels;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private int getStatusBarHeight() {
        int resourcesId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        return getResources().getDimensionPixelSize(resourcesId);
    }

    private int getRealH() {
        int h = screenHeight - getStatusBarHeight() - DpConvert.dip2px(this, 48) - mLinearLayout.getMeasuredHeight();
        return h;
    }
}
