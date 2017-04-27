package io.github.nullifythis.android.app.navigable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class Navigate {

    public static boolean onUpPressed(final FragmentManager fragmentManager, int containerId) {
        return onNavigation(UP_NAVIGATION, fragmentManager, containerId);
    }

    public static boolean onUpPressed(final FragmentManager fragmentManager, String tag) {
        return onNavigation(UP_NAVIGATION, fragmentManager, tag);
    }

    public static boolean onUpPressed(final Fragment fragment) {
        return onNavigation(UP_NAVIGATION, fragment);
    }

    public static boolean onBackPressed(final FragmentManager fragmentManager, int containerId) {
        return onNavigation(BACK_NAVIGATION, fragmentManager, containerId);
    }

    public static boolean onBackPressed(final FragmentManager fragmentManager, String tag) {
        return onNavigation(BACK_NAVIGATION, fragmentManager, tag);
    }

    public static boolean onBackPressed(final Fragment fragment) {
        return onNavigation(BACK_NAVIGATION, fragment);
    }

    private static boolean onNavigation(final Navigation navigation, final FragmentManager fragmentManager, int containerId) {
        if (null != fragmentManager) {
            Fragment fragment = fragmentManager.findFragmentById(containerId);
            return onNavigation(navigation, fragment);
        }
        return false;
    }

    private static boolean onNavigation(final Navigation navigation, final FragmentManager fragmentManager, String tag) {
        if (null != fragmentManager) {
            Fragment fragment = fragmentManager.findFragmentByTag(tag);
            return onNavigation(navigation, fragment);
        }
        return false;
    }

    private static boolean onNavigation(final Navigation navigation, final Fragment fragment) {
        if (fragment instanceof Navigable) {
            Navigable navigable = (Navigable) fragment;
            return navigation.execute(navigable);
        }
        return false;
    }

    private interface Navigation {
        boolean execute(Navigable navigable);
    }

    private static final Navigation UP_NAVIGATION = new Navigation() {
        @Override
        public boolean execute(Navigable navigable) {
            return navigable.onUpPressed();
        }
    };

    private static final Navigation BACK_NAVIGATION = new Navigation() {
        @Override
        public boolean execute(Navigable navigable) {
            return navigable.onBackPressed();
        }
    };
}
