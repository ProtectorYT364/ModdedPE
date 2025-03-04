package com.microsoft.xbox.xle.app.activity.Profile;

import android.content.Context;
import android.util.AttributeSet;

import com.mcal.mcpelauncher.R;
import com.microsoft.xbox.telemetry.helpers.UTCPeopleHub;
import com.microsoft.xbox.xle.app.activity.ActivityBase;

/**
 * 07.01.2021
 *
 * @author Тимашков Иван
 * @author https://github.com/TimScriptov
 */

public class ProfileScreen extends ActivityBase {
    public ProfileScreen() {
    }

    public ProfileScreen(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public String getActivityName() {
        return "PeopleHub Info";
    }

    public void onCreate() {
        super.onCreate();
        onCreateContentView();
        ProfileScreenViewModel profileScreenViewModel = new ProfileScreenViewModel(this);
        this.viewModel = profileScreenViewModel;
        UTCPeopleHub.trackPeopleHubView(getActivityName(), profileScreenViewModel.getXuid(), profileScreenViewModel.isMeProfile());
    }

    public void onCreateContentView() {
        setContentView(R.layout.profile_screen);
    }
}
