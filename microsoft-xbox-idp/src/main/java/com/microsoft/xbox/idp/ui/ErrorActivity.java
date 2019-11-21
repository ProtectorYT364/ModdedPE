package com.microsoft.xbox.idp.ui;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.microsoft.xbox.idp.R;
import com.microsoft.xbox.idp.compat.BaseActivity;
import com.microsoft.xbox.idp.compat.BaseFragment;
import com.microsoft.xbox.idp.interop.Interop.ErrorType;
import com.microsoft.xbox.idp.model.Const;
import com.microsoft.xbox.idp.telemetry.helpers.UTCError;
import com.microsoft.xbox.idp.telemetry.helpers.UTCPageView;
import com.microsoft.xbox.idp.ui.HeaderFragment.Callbacks;

public class ErrorActivity extends BaseActivity implements Callbacks, ErrorButtonsFragment.Callbacks {
    public static final String ARG_ERROR_TYPE = "ARG_ERROR_TYPE";
    public static final String ARG_GAMER_TAG = "ARG_GAMER_TAG";
    public static final int RESULT_TRY_AGAIN = 1;
    private static final String TAG = ErrorActivity.class.getSimpleName();
    private int activityResult = 0;

    public enum ErrorScreen {
        BAN(ErrorType.BAN, BanErrorFragment.class, R.string.xbid_more_info),
        CREATION(ErrorType.CREATION, CreationErrorFragment.class, R.string.xbid_try_again),
        OFFLINE(ErrorType.OFFLINE, OfflineErrorFragment.class, R.string.xbid_try_again),
        CATCHALL(ErrorType.CATCHALL, CatchAllErrorFragment.class, R.string.xbid_try_again);

        public final Class<? extends BaseFragment> errorFragmentClass;
        public final int leftButtonTextId;
        public final ErrorType type;

        private ErrorScreen(ErrorType type2, Class<? extends BaseFragment> errorFragmentClass2, int leftButtonId) {
            this.type = type2;
            this.errorFragmentClass = errorFragmentClass2;
            this.leftButtonTextId = leftButtonId;
        }

        public static ErrorScreen fromId(int id) {
            ErrorScreen[] values;
            for (ErrorScreen t : values()) {
                if (t.type.getId() == id) {
                    return t;
                }
            }
            return null;
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xbid_activity_error);
        Intent intent = getIntent();
        UiUtil.ensureHeaderFragment(this, R.id.xbid_header_fragment, intent.getExtras());
        if (intent.hasExtra(ARG_ERROR_TYPE)) {
            ErrorScreen errorScreen = ErrorScreen.fromId(intent.getIntExtra(ARG_ERROR_TYPE, -1));
            if (errorScreen != null) {
                UiUtil.ensureErrorFragment(this, errorScreen);
                UiUtil.ensureErrorButtonsFragment(this, errorScreen);
                UTCError.trackPageView(errorScreen, getTitle());
                return;
            }
            Log.e(TAG, "Incorrect error type was provided");
            return;
        }
        Log.e(TAG, "No error type was provided");
    }

    public void onClickCloseHeader() {
        Log.d(TAG, "onClickCloseHeader");
        UTCError.trackClose(ErrorScreen.fromId(getIntent().getIntExtra(ARG_ERROR_TYPE, -1)), getTitle());
        finish();
    }

    public void onClickedLeftButton() {
        Log.d(TAG, "onClickedLeftButton");
        ErrorScreen errorScreen = ErrorScreen.fromId(getIntent().getIntExtra(ARG_ERROR_TYPE, -1));
        if (errorScreen == ErrorScreen.BAN) {
            UTCError.trackGoToEnforcement(errorScreen, getTitle());
            try {
                startActivity(new Intent("android.intent.action.VIEW", Const.URL_ENFORCEMENT_XBOX_COM));
            } catch (ActivityNotFoundException e) {
                Log.e(TAG, e.getMessage());
            }
        } else {
            UTCError.trackTryAgain(errorScreen, getTitle());
            this.activityResult = 1;
            setResult(this.activityResult);
            finish();
        }
    }

    public void onClickedRightButton() {
        Log.d(TAG, "onClickedRightButton");
        UTCError.trackRightButton(ErrorScreen.fromId(getIntent().getIntExtra(ARG_ERROR_TYPE, -1)), getTitle());
        finish();
    }

    public void finish() {
        UTCPageView.removePage();
        super.finish();
    }
}
