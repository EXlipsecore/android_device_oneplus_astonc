package org.lineageos.device.settings;
import android.os.Bundle;
import com.android.settingslib.collapsingtoolbar.CollapsingToolbarBaseActivity;
public class DeviceSettingsActivity extends CollapsingToolbarBaseActivity {
  @Override protected void onCreate(Bundle b) {
    super.onCreate(b);
    setTitle(getString(R.string.device_title));
    if (b == null) {
      getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new DeviceSettings()).commit();
    }
  }
}
