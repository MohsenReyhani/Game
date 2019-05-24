package com.mohsen.game.ui.activities;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mohsen.game.R;
import com.mohsen.game.application.helper.L;
import com.mohsen.game.ui.fragments.Fragment_Content;
import com.mohsen.game.ui.fragments.Fragment_MainMenu;
import com.mohsen.game.ui.fragments.Fragment_Videos;
import com.mohsen.game.ui.fragments.Fragment_Voice;
import com.mohsen.game.ui.helper.SnackBarHelper;
import com.mohsen.game.ui.helper.UiHelper;

import java.util.ArrayList;


public class MainActivity extends Activity_GeneralBase
        implements UiHelper.MenuEventListener {

    private MainActivity Activity_Main_Reference;

    private boolean isFragmentReplacementSuccessful = true;
    private static ArrayList<Integer> backStackFragments = new ArrayList<>();
    private int countBackPressed = 0;
    //private int defaultMenuItemOnOpen = -1;
    private int TempPreviousBackStack;

    DrawerLayout mDrawerLayout;
    LinearLayout mMainMenuContainer;


    @Override
    protected void onActivityFirstInit() {

        Activity_Main_Reference = this;

        initComponents();

        runBackgroundTasks();

        OnMenuItemClicked(R.id.vBox_nav_li_main_board, false);
		/*if (defaultMenuItemOnOpen > 0) {
			OnMenuItemClicked(defaultMenuItemOnOpen, false);
			defaultMenuItemOnOpen = -1;
		}*/


        //checkStuff();
    }

    public MainActivity getActivity_MainContext() {
        return Activity_Main_Reference;
    }

    private void initComponents() {

        //TODO : Fix Res First
        //mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //mMainMenuContainer = (LinearLayout) findViewById(R.id.frame_Container_MainMenu);

        initDrawer();
    }

    private void initDrawer() {

        mDrawerLayout.setFocusableInTouchMode(false);
        //mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment_MainMenu menuFragment = new Fragment_MainMenu();
        menuFragment.setMenuItemClickedListener(this);
        //TODO : Fix Res First
        //fragmentManager.beginTransaction().replace(R.id.frame_MainMenu, menuFragment, "frame_Container_MainMenu").commit();

    }

    private void runBackgroundTasks() {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected void onPreExecute() {

            }

            @Override
            protected Boolean doInBackground(Void... arg0) {
                try {

                    //getCloudData();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            protected void onPostExecute(Boolean result) {

            }

        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void setActionBarTitle(String title) {
        txtActionBarTitle.setText(title);
    }

    @Override
    public void initActionBar() {

        actionBarView = (Toolbar) findViewById(R.id.supportToolbar);

        btnRightTool = (ImageView) actionBarView.findViewById(R.id.btnRightTool);
        btnLeftTool = (ImageView) actionBarView.findViewById(R.id.btnLeftTool);

        txtActionBarTitle = (TextView) actionBarView.findViewById(R.id.txtTitle);

        btnRightTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDrawerLayout.isDrawerOpen(mMainMenuContainer)) {
                    mDrawerLayout.closeDrawer(mMainMenuContainer);
                } else {
                    mDrawerLayout.openDrawer(mMainMenuContainer);
                }
            }
        });
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onActionBarRightToolClicked() {
        mDrawerLayout.openDrawer(mMainMenuContainer);
    }

    @Override
    protected void onActionBarLeftToolClicked() {

    }

    @Override
    protected String getActionBarTitle() {
        return null;
    }


    @Override
    public void OnMenuItemClicked(int selectedViewResId, boolean saveBackStack) {

        Fragment fragment = null;
        countBackPressed = 0;
        mDrawerLayout.closeDrawer(mMainMenuContainer);


        switch (selectedViewResId) {

            //**************** mainMenu ****************//

            case R.id.vBox_nav_li_main_board:

                fragment = new Fragment_MainMenu();
                btnLeftTool.setVisibility(View.VISIBLE);
                btnLeftTool.setImageResource(R.drawable.ic_play);
                setActionBarTitle(getString(R.string.nav_li_main_board));
                clearAllBackStack();
                break;

            case R.id.vBox_nav_li_content:

                fragment = new Fragment_Content();
                setActionBarTitle(getString(R.string.nav_li_content));
                btnLeftTool.setVisibility(View.GONE);
                break;

            case R.id.vBox_nav_li_voice:

                fragment = new Fragment_Voice();
                //((Fragment_SubMenuTools) fragment).setMenuItemClickedListener(this);
                setActionBarTitle(getString(R.string.nav_li_voice));
                btnLeftTool.setVisibility(View.GONE);
                break;

            case R.id.vBox_nav_li_videos:

                fragment = new Fragment_Videos();
                setActionBarTitle(getString(R.string.nav_li_videos));
                btnLeftTool.setVisibility(View.VISIBLE);
                btnLeftTool.setImageResource(R.drawable.ic_home);
                break;

            case R.id.img_nav_btn_social_net:
				
				/*Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(Activity_About.TELEGRAM_CHANNEL_URL));
				startActivity(intent);
				return;*/

            default:
                break;
        }

        if (fragment != null) {
            mDrawerLayout.closeDrawer(mMainMenuContainer);

            if (saveBackStack && TempPreviousBackStack != 0) {
                addToBackStack(TempPreviousBackStack);
            }

            TempPreviousBackStack = selectedViewResId;

            final Fragment frag = fragment;
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {

                    try {
                        FragmentManager fragmentManager = getSupportFragmentManager();

                        android.support.v4.app.FragmentTransaction ft = fragmentManager.beginTransaction();
                        ft.setCustomAnimations(R.anim.activity_open_translate, R.anim.activity_close_translate);

                        ft.replace(R.id.frame_container, frag, "CurrentViewFragment").commit();
                        isFragmentReplacementSuccessful = true;

                    } catch (Exception e) {

                        isFragmentReplacementSuccessful = false;

                    }

                }
            }, 300);


        } else {
            L.e("ActivityAnimationTest - Error in creating fragment");
        }

    }

    @Override
    public void onBackPressed() {


        Fragment fragment = getSupportFragmentManager().findFragmentByTag("CurrentViewFragment");

        //Navigation Drawer Is Open?
        if (mDrawerLayout.isDrawerOpen(mMainMenuContainer)) {
            Fragment_MainMenu menuFragment = getMenuFragment();
            if (menuFragment != null) {
                //fragment.highlightMenuItemText(R.id.vBox_nav_li_Transaction);
                mDrawerLayout.closeDrawer(mMainMenuContainer);
            }

            //We Have Fragments in Stack?
        } else if (getLastStack() != 0) {

            int backStack = getLastStack();
            removeLastStack();
            OnMenuItemClicked(backStack, false);

            //Are We In MainFragment?
        } else if (fragment != null && fragment instanceof Fragment_MainMenu) {
            if (((Fragment_MainMenu) fragment).onBackPressed()) {
                if (countBackPressed == 1) {
                    countBackPressed = 0;
                    finish();
                } else {
                    SnackBarHelper.showSnack(UiHelper.getActivityRootView(this), SnackBarHelper.SnackState.Info, getString(R.string.hint_exit));
                    countBackPressed++;
                }
            }
            return;

            //IF None , Go Back To MainFragment!
        } else {
            TempPreviousBackStack = 0;
            OnMenuItemClicked(R.id.vBox_nav_li_main_board, false);
        }
    }

    private Fragment_MainMenu getMenuFragment() {
        return (Fragment_MainMenu) getSupportFragmentManager().findFragmentByTag("frame_Container_MainMenu");
    }

    //************************* BackStack Methods *************************

    public static void clearAllBackStack() {
        backStackFragments.clear();
    }

    public void addToBackStack(int id) {
        backStackFragments.add(id);
    }

    public int getLastStack() {
        int lastIndex = backStackFragments.size() - 1;
        if (lastIndex != -1)
            return backStackFragments.get(lastIndex);
        else
            return 0;
    }

    public void removeLastStack() {
        int lastIndex = backStackFragments.size() - 1;
        if (lastIndex != -1)
            backStackFragments.remove(lastIndex);
    }

    //************************* End *************************
}
